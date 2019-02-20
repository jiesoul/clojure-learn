(ns com.jiesoul.codewar.twosum)

(defn twosum
  [numbers target]
  (loop [n (map-indexed vector numbers)
         n1 (rest n)
         r []]
    (if (<= (count n) 1)
      r
      (let [v1 (first n)
            v2 (first n1)]
        (cond
          (empty? n1) (recur (rest n) (rest (rest n)) [])
          (= (+ (second v1) (second v2)) target) (conj r (first v1) (first v2))
          :else
          (recur n (rest n1) r))))))

(defn twosum1
  [numbers target]
  (reduce-kv
    #(if-let [matched (%1 (- target %2))]
       (reduced [%2 matched])
       (assoc %1 %3 %2))
    {}
    numbers))

(twosum [1 2 3] 4)

(twosum [1234 5678 9012] 14690)
