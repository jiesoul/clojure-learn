(ns codewar.piapprox)

(defn round [s n]
	(.setScale (bigdec n) s java.math.RoundingMode/HALF_EVEN))

(defn iterPi [epsilon]
	(let [en (fn
						 [n]
						 (< (Math/abs (- n (/ Math/PI 4M))) epsilon))]
		(loop [n 1
					 r 1]
			(if (en n)
				[r n]
				(let [r (inc r)
							v (dec (* 2 r))
							v (if (even? r) (- v) v)]
					(recur (+ n (/ 1 v)) r))))))

(iterPi 0.1)