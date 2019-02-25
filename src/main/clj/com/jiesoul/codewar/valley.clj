(ns com.jiesoul.codewar.valley)

(defn make-valley
  [arr]
  (let [arr (sort > arr)]
    (loop [l []
           r []
           coll arr]
      (if (<= (count coll) 1)
        (concat l coll (reverse r))
        (let [v1   (first coll)
              v2   (second coll)
              coll (rest (rest coll))]
          (recur (conj l v1) (conj r v2) coll))))))

(make-valley [20, 7, 6, 2])
(sort > [11 3 -13 -8 -19 6 -15 6 18 -9 20 -11 20])
(make-valley [11 3 -13 -8 -19 6 -15 6 18 -9 20 -11 20])
