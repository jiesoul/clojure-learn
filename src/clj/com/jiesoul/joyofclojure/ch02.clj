(ns com.jiesoul.joyofclojure.ch02
  (:require clojure.set))

[127 0x7f 0177 32r3v 2r011111]

(def make-set
  (fn [x y]
    (println "Making a set")
    #{x y}))

(make-set 1 2)

(defn make-set
  [x y]
  (println "Making a set")
  #{x y})

(defn make-set
  ([x] #{x})
  ([x y] #{x y}))

(make-set 42)
;(make-set 1 2 3)

(defn arity2+ [first second & more]
  (vector first second more))

(arity2+ 1 2)
(arity2+ 1 2 3 4)
;(arity2+ 1)

(def make-list0 #(list))

(make-list0)

(def make-list2 #(list %1 %2))

(make-list2 1 2)

(def make-list2+ #(list %1 %2 %&))

(make-list2+ 1 2 3 4 5)

(do
  (def x 5)
  (def y 4)
  (+ x y)
  [x y])

(let [r 5
      pi 3.1415
      r-squared (* r r)]
  (println "radius is" r)
  (* pi r-squared))

(defn print-down-from [x]
  (when (pos? x)
    (println x)
    (recur (dec x))))

(print-down-from 10)

(defn sum-down-from [sum x]
  (if (pos? x)
    (recur (+ sum x) (dec x))
    sum))

(sum-down-from 0 10)

(defn sum-down-from [initial-x]
  (loop [sum 0
         x initial-x]
    (if (pos? x)
      (recur (+ sum x) (dec x))
      sum)))

(sum-down-from 10)

(cons 1 [2 3])

(quote age)

(def age 9)
(quote age)

(quote (cons 1 [2 3]))

(cons 1 (2 3))

(cons 1 (quote (2 3)))

(cons 1 '(2 3))

[1 (+ 2 3)]
'(1 (+ 2 3))

`(1 2 3)

`(+ 10 (* 2 3))
`(+ 10 ~(* 2 3))

(let [x 2]
  `(1 ~x 3))

`(1 ~(2 3))

(let [x '(2 3)] `(1 ~x))
(let [x '(2 3)] `(1 -@x))

(new java.awt.Point 0 1)

(new java.util.HashMap {"foo" 42 "bar" 9 "baz" "quux"})

(java.util.HashMap. {"foo" 42 "bar" 9 "baz" "quux"})

(.-x (java.awt.Point. 10 20))

(.divide (java.math.BigDecimal. "42") 2M)

(let [origin (java.awt.Point. 0 0)]
  (set! (.-x origin) 15)
  (str origin))

(.endsWith (.toString (java.util.Date.)) "2018")

(.. (java.util.Date.) toString (endsWith "2018"))

(doto (java.util.HashMap.)
  (.put "Home" "/home/me")
  (.put "SRC" "src")
  (.put "BIN" "classes"))

(throw (Exception. "I done throwed"))

(defn throw-catch [f]
  [(try
     (f)
     (catch ArithmeticException e "No dividing by zero!")
     (catch Exception e (str "You are so bad " (.getMessage e)))
     (finally (println "returning...")))])

(throw-catch #(/ 10 5))
(throw-catch #(/ 10 0))
(throw-catch #(throw (Exception. "Crybaby")))

(clojure.set/intersection #{1 2 3} #{3 4 5})



