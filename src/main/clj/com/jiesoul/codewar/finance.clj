(ns com.jiesoul.codewar.finance)

(defn finance-1 [n]
  (loop [start 0
         end (+ n 1)
         result 0]
    (if (> start (* 2 n))
      result
      (recur (+ 2 start) (inc end) (reduce + result (range start end))))))

(defn finance-sum [n]
  (/ (* (inc n) (* 3 n)) 2))

(defn finance [n]
  (loop [n n
         r 0]
    (if (zero? n)
      r
      (recur (dec n) (+ (finance-sum n) r)))))