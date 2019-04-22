(ns com.jiesoul.joyofclojure.ch04)

(def clueless 9)
(class clueless)
(class (+ clueless 900000000000000))
(class (+ clueless 90000000000000000000000))
(class (+ clueless 9.0))

;(+ Long/MAX_VALUE Long/MAX_VALUE)
(unchecked-add (Long/MAX_VALUE) (Long/MAX_VALUE))
(float 0.000000000000000000000000000000000000000000000001)
1.0E-430

(let [approx-interval (/ 209715 2097152)
      actual-interval (/ 1 10)
      hours (* 3600 100 10)
      actual-total (double (* hours actual-interval))
      approx-total (double (* hours approx-interval))]
  (- actual-total approx-total))

(+ 0.1M 0.1M 0.1  0.1M 0.1M 0.1M 0.1M 0.1M 0.1M)

1.0E-430000000M
;1.0E-4300000000M


(def a 1.0e50)
(def b -1.0e50)
(def c 17.0e00)

(+ (+ a b) c)
(+ a (+ b c))

(def a (rationalize 1.0e50))
(def b (rationalize -1.0e50))
(def c (rationalize 17.0e00))

(+ (+ a b) c)
(+ a (+ b c))

(numerator (/ 123 10))
(denominator (/ 123 10))

:a-keyword
::also-a-keyword

(def population {:zombies 2700 :humas 9})
(get population :zombies)
(println (/ (get population :zombies)
           (get population :humas))
  "zombies pre capita")

(:zombies population)
(println (/ (:zombies population)
           (:humas population))
  "zombies per cpita")

(defn pour [lb ub]
  (cond
    (= ub :toujours) (iterate inc lb)
    :else
    (range lb ub)))
(pour 1 10)
(take 10 (pour 1 :toujours))

::not-in-ns

(identical? 'goat 'goat)
(= 'goat 'goat)
(name 'goat)
(let [x 'goat y x]
  (identical? x y))

(let [x (with-meta 'goat {:ornery true})
      y (with-meta 'goat {:ornery false})]
  [(= x y)
   (identical? x y)
   (meta x)
   (meta y)])

(def a-symbol 'where-am-i)

(defn best [f xs]
  (reduce #(if (f % %2) % %2) xs))

(best > [1 3 4 2 7 5 3])

(class #"example")
(java.util.regex.Pattern/compile "\\d")

(class #"example")
(java.util.regex.Pattern/compile "\\d")

(re-seq #"\w+" "one-two/three")
(re-seq #"\w*(\w)" "one-two/three")


