(ns codewar.hiddenseq)

(defn fcn [n]
  (nth (map first (iterate (fn [[a b]] [b (/ (* 6 a b) (- (* 5 a) b))]) [1N 2N])) n))

(fcn 2)