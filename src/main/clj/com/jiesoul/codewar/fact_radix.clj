(ns com.jiesoul.codewar.fact-radix)

(defn dec2FactString [nb]
  (let [step (fn [n]
               (loop [r []
                      n n
                      i 1]
                 (if (zero? n)
                   r
                   (let [rv (rem n i)
                         rv (if (> rv 9) (char (+ 55 rv)) rv)]
                     (recur (cons rv r) (quot n i) (inc i))))))]
    (->> nb
         step
         (apply str))))

(defn factString2Dec [strg]
  (loop [r 0
         xs (map int (seq strg))]
    (if (empty? xs)
      r
      (let [head (first xs)
            v (if (>= head 65) (-' head 55) (-' head 48))]
        (recur (+ r (*' v (apply *' (range 1 (count xs))))) (rest xs))))))