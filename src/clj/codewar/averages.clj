(ns codewar.averages)

(defn averages [ls]
  (map #(/ (+ (first %) (second %)) 2M) (partition 2 1 ls)))