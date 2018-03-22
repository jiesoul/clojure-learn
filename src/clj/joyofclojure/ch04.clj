(ns joyofclojure.ch04)

(let [approx-interval (/ 209715 2097152)
      actual-interval (/ 1 10)
      hours (* 3600 100 10)
      actual-total (double (* hours actual-interval))
      approx-total (double (* hours approx-interval))]
  (- actual-total approx-total))

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


