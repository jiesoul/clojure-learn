(ns codewar.piapprox)

(defn round [s n]
	(.setScale (bigdec n) s java.math.RoundingMode/HALF_EVEN))

(defn good-enough? [guess epison]
  (< (Math/abs (- guess Math/PI)) epison))

(defn prod [n]
  (let [m (/ 1 (dec (* 2 n)))]
    (if (odd? n)
      m
      (- m))))

(defn step [result n epsion]
  (let [v (* result 4.0)]
    (if (good-enough? v epsion)
      [n (round 10 v)]
      (recur (+ result (prod (inc n))) (inc n) epsion))))

(defn iterPi [epsilon]
  (step 1.0 1 epsilon))

(/ Math/PI 4.0)
(good-enough? 3.14222 0.01)
(prod 4)
(iterPi 0.01)