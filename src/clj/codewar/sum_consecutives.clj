(ns codewar.sum-consecutives)

(defn sum-consecutives [a]
  (->> a
       (partition-by identity)
       (map #(reduce + %))))

