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

(def very-lazy (-> (iterate #(do (print \.) (inc %)) 1)
                   rest rest rest))

(def less-lazy (-> (iterate #(do (print \.) (inc %)) 1)
                   next next next))

(defn simple-range [i limit]
  (lazy-seq
   (when (< i limit)
     (cons i (simple-range (inc i) limit)))))

(simple-range 0 9)


