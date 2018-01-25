(ns codewar.sumofk)

(defn som [l] (reduce + l))

(defn choose-best-sum [t k ls]
  (let [a (comb k ls) mx -1 res []
        b (map som a)
        c (filter (fn [x] (<= x t)) b)
        d (if (empty? c)
            nil
            (apply max c))
        ]
    d
    ))

(def ts [50, 55, 56, 57, 58])
(is (= (choose-best-sum 163, 3, ts) 163))
(def ts [50])
(is (= (choose-best-sum 163, 3, ts) nil))