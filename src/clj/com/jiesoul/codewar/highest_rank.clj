(ns com.jiesoul.codewar.highest-rank)

(defn highest-rank [data]
  (let [d (map #(vector (key %) (count (val %)))
               (group-by identity data))]
    (ffirst (sort-by first > (sort-by second > d)))))

(defn highest-rank1 [data]
  (->> data
      frequencies
      (sort-by val)
      last
      key))

(highest-rank1 [12 12 3 4 14 14])
