;The number 81 has a special property, a certain power of the sum of its digits is equal to 81 (nine squared). Eighty one (81), is the first number in having this property (not considering numbers of one digit). The next one, is 512. Let's see both cases with the details
;
;8 + 1 = 9 and 92 = 81
;
;512 = 5 + 1 + 2 = 8 and 83 = 512
;
;We need to make a function, power_sumDigTerm(), that receives a number n and may output the n-th term of this sequence of numbers. The cases we presented above means that
;
;power_sumDigTerm(1) == 81
;
;power_sumDigTerm(2) == 512
;
;Happy coding!

(defn sort-parts [coll]
  (lazy-seq
    (loop [[part & parts] coll]
      (if-let [[pivot & xs] (seq part)]
        (let [smaller? #(< % pivot)]
          (recur (list*
                   (filter smaller? xs)
                   pivot
                   (remove smaller? xs)
                   parts)))
        (when-let [[x & parts] parts]
          (cons x (sort-parts parts)))))))

(defn qsort [xs]
  (sort-parts (list xs)))

(defn decode [n]
  (loop [r 0
         n n]
    (if (zero? n)
      r
      (recur (+' r (rem n 10)) (quot n 10)))))

(defn power-sum-dig-term
  [n]
  (let [xs (for [x (range 2 64)
                 y (range 2 180)
                 :let [v (Math/pow y x)]
                 :when (< v (Math/pow 2 63))]
             [(long v) y])
        ys (map first (filter #(= (decode (first %)) (second %)) xs))
        coll (sort ys)]
    (nth coll (dec n))))