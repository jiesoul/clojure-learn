(ns com.jiesoul.codewar.powofdigits)

(defn powof [n p]
  (letfn [(step [r n]
            (if (zero? n)
              r
              (recur (conj r (rem n 10)) (quot n 10))))]
    (let [xs (step [] n)]
      (long (reduce + (map #(Math/pow % p) xs))))))

(defn eq-sum-pow-dig [hmax po]
  (filter #(= % (powof % po)) (range 10 hmax)))