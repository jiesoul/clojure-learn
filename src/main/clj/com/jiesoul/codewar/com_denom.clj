(ns com.jiesoul.codewar.com-denom)

(defn gcd [a b]
  (if (zero? b)
    a
    (recur b (rem a b))))

(defn convert-fracts [lst]
  (let [dv (map second lst)
        mv (reduce #(/ (* %1 %2) (gcd %1 %2)) dv)]
    (map #(conj [] (* (first %) (/ mv (second %))) mv) lst)))
