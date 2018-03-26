(ns codewar.star-sort)

(defn star-sort [arr]
  (let [coll (seq (first (sort arr)))]
    (apply str (first coll) (map #(str "***" %) (rest coll)))))