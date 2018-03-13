(ns codewar.sumofk)

(defn power-set [ls]
  (lazy-seq
    (if (empty? ls)
     [[]]
     (let [head  (first ls)
           rests (power-set (rest ls))]
       (concat (map #(conj % head) rests) rests)))))

(defn choose-best-sum [t k ls]
  (let [xs (->> ls
                power-set
                (filter #(= k (count %)))
                (map #(reduce + %))
                (filter #(<= % t))
                )]
    (if (empty? xs)
      nil
      (apply max xs))))

(def ts [50, 55, 56, 57, 58])
(is (= (choose-best-sum 163, 3, ts) 163))
(def ts [50])
(is (= (choose-best-sum 163, 3, ts) nil))