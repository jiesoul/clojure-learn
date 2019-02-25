(ns com.jiesoul.codewar.array-diff)

(defn array-diff
  [a b]
  (reduce (fn [c v] (filter #(not= v %) c))
          a b))

(array-diff [1 2] [1])
(array-diff [1,2,2,2,3] [2])