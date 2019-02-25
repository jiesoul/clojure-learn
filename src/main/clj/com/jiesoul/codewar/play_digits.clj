(ns com.jiesoul.codewar.play-digits)

(defn dig-pow
  [n p]
  (let [t (->> n
            (str)
            (seq)
            (map #(repeat (+ p %1) (Integer/parseInt (str %2))) (range))
            (map #(reduce * 1 %))
            (apply +))]
    (if (zero? (rem t n))
      (quot t n)
      -1)))

(dig-pow 89 1)
