(ns com.jiesoul.codewar.lazy-next)

(defn next-item1
  [xs item]
  (loop [coll xs]
    (cond
      (empty? coll) nil
      (= item (first coll)) (second coll)
      :else (recur (rest coll)))))

(defn next-item
  [xs item]
  (second (drop-while (complement #{item}) xs)))

(next-item (range 1 10) 7)
(next-item ["Joe" "Bob" "Sally"] "Bob")