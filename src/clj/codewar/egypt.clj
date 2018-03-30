(ns codewar.egypt)

(defn str-rat [s]
  (if-let [coll (re-seq #"(.*)/(.*)" s)]
    (let [xs (rest (first coll))]
      (/ (Integer/parseInt (first xs)) (Integer/parseInt (second xs))))
    (rationalize (Double/parseDouble s))))

(defn decompose [r]
  (let [step (fn [coll rat]
               (if (zero? rat) coll
                 (let [nume (if (ratio? rat) (numerator rat) rat)
                       deno (if (ratio? rat) (denominator rat) 1)]
                   (if (or (zero? (rem deno nume)) (= deno 1))
                     (conj coll (str rat))
                     (let [v (/ 1 (inc (quot deno nume)))]
                       (recur (conj coll (str v)) (- rat v)))))))]
    (step [] (str-rat r))))

(decompose "21/23")
(decompose "12/4")