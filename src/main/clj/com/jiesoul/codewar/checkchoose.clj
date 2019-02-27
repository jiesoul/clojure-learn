(ns com.jiesoul.codewar.checkchoose)

(defn factorial [n]
  (apply * (range 1N (inc n))))

(defn checkchoose
  [m n]
  (loop [x 0N]
    (let [v (/ (factorial n) (* (factorial x) (factorial (- n x))))
          b (- m v)]
      (cond
        (zero? b) x
        (< v 1) -1
        :else (recur (inc x))))))

(factorial 6)
(checkchoose 6 4)
(checkchoose 4 4)
(checkchoose 4 2)
(checkchoose 36N 7)
(checkchoose 35N 7)
(checkchoose 566196948064697546150348674880N 133)
(checkchoose 4751621214095169993709641280N 129)