(ns com.jiesoul.codewar.sumdivsq)

(defn step1 [n]
  (reduce +' (map #(int (Math/pow % 2)) (filter #(zero? (rem n %)) (range 1 (inc n))))))

(defn list-squared1 [m n]
  (println m n)
  (->> (range m (inc n))
       (map (fn [a] [a (step1 a)]))
       (filter (fn [[_ b]] (zero? (rem (Math/sqrt b) 1))))))


(defn factor [n]
  (->>
    (for [a (range 1 n)
          b (range 1 (inc n))
          :let [c (* a b)]
          :when (and (< a b) (= c n))]
      [a b])
       (reduce #(concat %1 %2))
       (map #(* % %))
       (apply +)))

(defn step [n]
  (apply + (map #(* % %) (reduce #(concat %1 %2) (factor n)))))

(defn list-sq [m n]
  (for [d (range m (inc n))]
    [d (factor d)]))

(defn list-squared [m n]
  (->> (range m (inc n))
       (map (fn [a] [a (step a)]))
       (filter (fn [[_ b]] (zero? (rem (Math/sqrt b) 1))))))

(factor 42)
(step 42)
(list-sq 1 250)
(list-squared 1 250)