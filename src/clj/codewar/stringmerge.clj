(ns codewar.stringmerge)


(defn is-merge
  "Returns whether a string is a merge of two other strings"
  [s p1 p2]
  (cond
    (and (empty? s) (empty? p1) (empty? p2)) true
    (empty? s) false
    (= (first s) (first p2)) (recur (rest s) p1 (rest p2))
    (= (first s) (first p1)) (recur (rest s) (rest p1) p2)
    :else false))