(ns com.jiesoul.codewar.moduli)

(defn prime? [n]
  (->> (range 2 (inc (/ n 2)))
       (map #(rem n %))
       (filter zero?)
       (count)
       (zero?)))

(defn co-primes? [coll]
  (loop [c (remove #{0} (sort coll))]
    (let [v (first c)
          t (filter #(zero? (rem % v)) (rest c))]
      (cond
        (empty? c) true
        (not-empty  t) false
        :else (recur (rest c))))))


(defn fromNb2Str
  [n arr]
  (let [v (apply * arr)]
    (if (< v n)
      "Not applicable"
      (let [c (map #(rem n %) arr)]
        (println c)
        (if (co-primes? c)
          (str "-" (clojure.string/join "--" c) "-")
          "Not applicable")))))