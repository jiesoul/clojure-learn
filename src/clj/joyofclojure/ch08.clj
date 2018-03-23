(ns joyofclojure.ch08)

(defmacro do-until
  [& clauses]
  (list 'clojure.core/when (first clauses)
        (if (next clauses)
          (second clauses)
          (throw (IllegalArgumentException. "do-until require the even number of forms")))
        (cons 'do-until (nnext clauses))))

(macroexpand-1 '(do-until true (prn 1) false (prn 2)))

(defn unless [condition & body]
  `(if (not ~condition)
     (do ~@body)))

(unless true (println "nope"))

(macroexpand `(if (not condition) "got it"))

(defn contextual-eval [ctx expr]
  (eval
    `(let [~@(mapcat (fn [[k v]] [k `'~v]) ctx)]
       ~expr)))
