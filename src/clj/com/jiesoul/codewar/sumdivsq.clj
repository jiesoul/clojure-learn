(ns com.jiesoul.codewar.sumdivsq)

(defn step [n]
  (reduce +' (map #(* % %) (filter #(zero? (rem n %)) (range 1 (inc n))))))

(defn list-squared [m n]
  (for [x (map #(conj [] % (step %)) (range m n))
        y (range 1 Long/MAX_VALUE)
        :when (= (second x) (second y))]
    [x]))