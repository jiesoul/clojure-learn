(ns com.jiesoul.codewar.number-of-trailing-zeros-of-N)


;Number of trailing zeros of N!
(defn zeors [n]
  (loop [coll (range 5 (inc n) 5)
         sum 0]
    (if (empty? coll)
      sum
      (recur (filter #(zero? (rem % 5)) (map #(/ % 5) coll)) (+ sum (count coll))))))

(defn zeors1 [n]
  (let [coll (range 5 (inc n) 5)]
    (loop [i 5
           sum 0]
      (if (> i n)
        sum
        (recur (* i 5) (+ sum (count (filter #(zero? (rem % 5)) coll))))))))