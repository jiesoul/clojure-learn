(ns com.jiesoul.codewar.sumdivsq)

(defn step [n]
  (reduce +' (map #(int (Math/pow % 2)) (filter #(zero? (rem n %)) (range 1 (inc n))))))

(defn list-squared-old [m n]
  (println m n)
  (->> (range m (inc n))
       (map (fn [a] [a (step a)]))
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

(defn fac [n]
  (filter #(zero? (rem n %)) (range 1 (inc n))))

(defn list-squared2 [m n]
  (->> (range m (inc n))
       (map (fn [a] [a (reduce + (map #(* % %) (fac a)))]))
       (filter #(zero? (rem (Math/sqrt (second %)) 1)))))

(defn list-squared [m n]
  (loop [m m
         r []]
    (if (> m n)
      r
      (let [v (apply + (map #(* % %) (fac m)))
            b (int (Math/sqrt v))]
        (recur (inc m) (if (= v (* b b)) (conj r [m v]) r))))))

(time (list-squared2 1 250))
(time (list-squared 1 250))
;(time (list-squared 18000 20000))

(defn factors [n]
  (into
    (sorted-set)
    (reduce concat
            (for [x (range 1 (inc (Math/sqrt n))) :when (zero? (rem n x))]
              [(* x x) (* (/ n x) (/ n x))]))))

(defn sum-sq-factors [n]
  (let [s (reduce + (vec (fac n)))
        r (int (Math/sqrt s))]
    (if (= s (* r r))
      [n s]
      nil)))

(defn list-squared3 [m n]
  (remove nil? (map sum-sq-factors (range m n))))

(time (fac 42))
(time (factors 42))

(time (list-squared 1 250))
(time (list-squared2 1 250))
(time (list-squared3 1 250))

;(step 42)
;(list-sq 1 250)
;(list-squared 1 250)