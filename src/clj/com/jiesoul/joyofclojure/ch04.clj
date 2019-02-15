(ns com.jiesoul.joyofclojure.ch04)

(+ Long/MAX_VALUE Long/MAX_VALUE)
(unchecked-add (Long/MAX_VALUE) (Long/MAX_VALUE))

(let [approx-interval (/ 209715 2097152)
      actual-interval (/ 1 10)
      hours (* 3600 100 10)
      actual-total (double (* hours actual-interval))
      approx-total (double (* hours approx-interval))]
  (- actual-total approx-total))

(def a (rationalize 1.0e50))
(def b (rationalize -1.0e50))
(def c (rationalize 17.0e00))

(+ (+ a b) c)

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

(re-seq #"\w+" "one-two/three")
(re-seq #"\w*(\w)" "one-two/three")

(def ds (into-array [:willie :barnabas :adam]))
(seq ds)
(aset ds 1 :quentin)
(seq ds)

(def ds [:willie :barnabas :adam])
(def ds1 (replace {:barnabas :quentin} ds))
(seq ds)
(seq ds1)

(vec (range 10))
(let [my-vector [:a :b :c]]
  (into my-vector (range 10)))

(defn do-blowfish [directive]
  (case directive
    :aquarium/blowfish (println "feed the fish")
    :crypto/blowfish (println "encode the message")
    :blowfish (println "not sure what to do")))

(into (vector-of :int) [Math/PI 2 1.3])
;; [3 2 1]
(into (vector-of :char) [100 101 102])
(into (vector-of :int) [1 2 2423402384024802482034982034823048])

(def a-toj (vec (map char (range 65 75))))
(nth a-toj 4)
(get a-toj 4)
(a-toj 4)


