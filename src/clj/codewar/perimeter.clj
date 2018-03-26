(ns codewar.perimeter)

(defn perimeter [n]
  (* 4 (reduce + (take (inc n) (map first (iterate (fn [[a b]]
                                               [(+ a b) a]) [1N 0N]))))))