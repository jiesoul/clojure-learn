(ns codewar.ball)

;Ball Upwards

(defn max-ball [v0]
  (let [v (/ v0 3.6)
        g 9.81
        t (/ v g)
        h (/ (* v v) (* 2 g))
        xs (iterate (fn [[a b]]
                      (let [t (* 10 (inc a))]
                        [(inc a) (- (* v t) (* 0.5 g t t))])) [0 0.0])]
    [t h]))