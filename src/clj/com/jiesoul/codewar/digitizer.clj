(ns com.jiesoul.codewar.digitizer)

(defn digitize [n]
  (loop [n n
         result []]
    (if (zero? n)
      result
      (recur (quot n 10) (conj result (rem n 10))))))