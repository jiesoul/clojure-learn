(ns com.jiesoul.codewar.digital-root)

(defn decm [n]
  (loop [n n
         r []]
    (if (zero? n)
      r
      (recur (quot n 10) (conj r (rem n 10))))))

(defn sum-decm [n]
  (reduce + (decm n)))

(defn digital-root [n]
  (if (< n 10)
    n
    (recur (sum-decm n))))