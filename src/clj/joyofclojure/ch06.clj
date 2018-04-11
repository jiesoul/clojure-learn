(ns joyofclojure.ch06)

(def baselist (list :barnabas :adam))
(def lst1 (cons :willie baselist))
(def lst2 (cons :phoenix baselist))

(println lst1)
(println lst2)

(= (next lst1) (next lst2))
(identical? (next lst1) (next lst2))

(defn xconj [t v]
  (cond
    (nil? t) {:val v :L nil :R nil}
    (< v (:val t)) {:val (:val t)
                    :L (xconj (:L t) v)
                    :R (:R t)}))

(xconj nil 5)
(def tree1 (xconj nil 5))
(def tree1 (xconj tree1 3))

(defn if-chain [x y z]
  (if x
    (if y
      (if z
        (do
          (println "Made it!")
          :all-truthy)))))

(if-chain () 42 true)
(if-chain true true false)

(defn rec-step [[x & xs]]
  (if x
    [x (rec-step xs)]
    []))

(rec-step [1 2 3 4])
;(rec-step (range 20000))

(defn lz-rec-step [s]
  (lazy-seq
    (if (seq s)
      [(first s) (lz-rec-step (rest s))]
      [])))

(class (lz-rec-step [1 2 3 4]))
(lz-rec-step (range 2000))

(def very-lazy (-> (iterate #(do (print \.) (inc %)) 1)
                   rest rest rest))

(def less-lazy (-> (iterate #(do (print \.) (inc %)) 1)
                   next next next))

(println (first very-lazy))
(println (first less-lazy))

(defn simple-range [i limit]
  (lazy-seq
   (when (< i limit)
     (cons i (simple-range (inc i) limit)))))

(simple-range 0 9)

(let [r (range 1e9)]
  (first r)
  (last r))

(let [r (range 1e9)]
  (last r)
  (first r))