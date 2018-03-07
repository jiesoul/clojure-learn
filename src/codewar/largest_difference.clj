(ns codewar.largest-difference)

(defn largestDifference [data]
  (let [step (fn [coll]
               (loop [r []
                      coll coll]
                 (if (empty? coll)
                   r
                   (recur
                     (concat r (map #(conj [] (- (second %2) (second %1)) (first %1) (first %2))
                                  (repeat (first coll))
                                  (rest coll)))
                     (rest coll)))))
        fil- (fn [coll]
               (if (empty? coll)
                 []
                 (reduce (fn [a b]
                           (if (<= (first a) (first b))
                             b
                             a)) (first coll) (rest coll))))
        out (fn [coll]
              (if (empty? coll)
                0
                (- (second coll) (first coll))))]
    (->> data
         (map #(conj [] %1 %2) (range))
         step
         (filter #(> (first %) 0))
         fil-
         rest
         out)))