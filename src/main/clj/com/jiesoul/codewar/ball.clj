(ns com.jiesoul.codewar.ball)

;Ball Upwards

(defn max-ball [v0]
  (let [v (/ v0 (/ 1 3.6))
        xs (iterate (fn [[a _]]
                      (let [a (inc a)
                            t a]
                        [a (- (* v t) (* 0.5 9.81 t t))])) [0 0.0])]
    (take 21 xs)))

(max-ball 15)
(max-ball 25)