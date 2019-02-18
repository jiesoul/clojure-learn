(ns com.jiesoul.codewar.divparv7)

(defn seven [m]
  (loop [m m
         c 0]
    (if (< m 100)
      [m c]
      (let [q (quot m 10)
            r (rem m 10)]
        (recur (- q (* 2 r)) (inc c))))))

(seven 1603)
