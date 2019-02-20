(ns com.jiesoul.joyofclojure.ch08
  (:require [clojure.walk :as walk])
  (:import [java.io BufferedReader InputStreamReader]
           [java.net URL]))

(list (int (Math/sqrt 25)))

(-> (/ 144 12) (/ 2 3) str keyword list)
(-> (/ 144 12) (* 4 (/ 2 3)) str keyword (list :33))

(eval 42)
(eval '(list 1 2))
;(eval (list 1 2))

(eval (list (symbol "+") 1 2))

(defn contextual-eval [ctx expr]
  (eval
    `(let [~@(mapcat (fn [[k v]] [k `'~v]) ctx)]
       ~expr)))

(contextual-eval '{a 1, b 2} '(+ a b))
(contextual-eval '{a 1, b 2} '(let [b 1000] (+ a b)))

(defmacro do-until [& clauses]
  (when clauses
    (list 'clojure.core/when (first clauses)
          (if (next clauses)
            (second clauses)
            (throw (IllegalArgumentException.
                     "do-until requires an even number of forms")))
          (cons 'do-until (nnext clauses)))))

(macroexpand-1 '(do-until true (prn 1) false (prn 2)))
(walk/macroexpand-all '(do-until true (prn 1) false (prn 2)))


(defn unless [condition & body]
  `(if (not ~condition)
     (do ~@body)))

(unless true (println "nope"))
(unless false (println "yep!"))

(def condition false)

(macroexpand `(if (not condition) "got it"))

(eval `(if (not condition) "got it"))

(eval `(if (not condition) "got it"))

(defmacro def-watched [name & value]
  `(do
     (def ~name ~@value)
     (add-watch (var ~name)
                :re-bind
                (fn [~'key ~'r old# new#]
                  (println old# " -> " new#)))))

(defn contextual-eval [ctx expr]
  (eval
    `(let [~@(mapcat (fn [[k v]] [k `'~v]) ctx)]
       ~expr)))
(declare handle-things)

(defmacro domain [name & body]
  `{:tag :domain,
    :attrs {:name (str '~name)},
    :content [~@body]})

(defmacro grouping [name & body]
  `{:tag :grouping,
    :attrs {:name (str '~name)},
    :content [~@(handle-things body)]})

(declare grok-attrs grok-props)
(defn handle-things [things]
  (for [t things]
    {:tag :thing,
     :attrs (grok-attrs (take-while (comp not vector?) t))
     :content (if-let [c (grok-props (drop-while (comp not vector?) t))]
                [c]
                [])}))

(defn grok-attrs [attrs]
  (into {:name (str (first attrs))}
        (for [a (rest attrs)]
          (cond
            (list? a) [:isa (str (second a))]
            (string? a) [:comment a]))))

(defn grok-props [props]
  (when props
    {:tag :properties, :attrs nil,
     :content (apply vector
                     (for [p props]
                       {:tag :property
                        :attrs {:name (str (first p))},
                        :content nil}))}))


(def d
  (domain man-vs-moster
         (grouping people
                   (Human "A stock human")
                   (Man (isa Human)
                        "A man, bady"
                        [name]
                        [has-beard?]))
         (grouping monsters
                   (Chupacabra
                     "A fierce, yet elusive creature"
                     [eats-goats?]))))

(defn joc-www []
  (-> "Http://joyofclojure.com/hello"
      URL.
      .openStream
      InputStreamReader.
      BufferedReader.))

(let [stream (joc-www)]
  (with-open [page stream]
    (println (.readLine page))
    (println "The stream will now close... "))
  (println "but let's read from it anyway.")
  (.readLine stream))

(declare collect-bodies)
(defmacro contract [name & forms]
  (list* `fn name (collect-bodies forms)))

(fn doubler
  ([f x]
    {:post [(= (* 2 x) %)],
     :pre [(pos? x)]}
    (f x)))

(declare build-contract)

(defn collect-bodies [forms]
  (for [form (partition 3 forms)]
    (build-contract form)))

(defn build-contract [c]
  (let [args (first c)]
    (list
      (into '[f] args)
      (apply merge
             (for [con (rest c)]
               (cond (= (first con) 'require)
                     (assoc {} :pre (vec (rest con)))
                     (= (first con) 'ensure)
                     (assoc {} :post (vec (rest con)))
                     :else (throw (Exception.
                                    (str "Unknown tag " (first con)))))))
      (list* 'f args))))

(def doubler-contract
  (contract doubler
            [x]
            (require
              (pos? x))
            (ensure
              (= (* 2 x) %))))

(def times2 (partial doubler-contract #(* 2 %)))

(times2 9)

(def times3 (partial doubler-contract #(* 3 %)))
(times3 9)

(def doubler-contract
  (contract doubler
            [x]
            (require
              (pos? x))
            (ensure
              (= (* 2 x) %))
            [x y]
            (require
              (pos? x)
              (pos? y))
            (ensure
              (= (* 2 (+ x y)) %))))

((partial doubler-contract #(* 2 (+ %1 %2))) 2 3)
((partial doubler-contract #(+ %1 %1 %2 %2)) 2 3)
((partial doubler-contract #(* 3 (+ %1 %2))) 2 3)