;Backwards Read Primes are primes that when read backwards in base 10 (from right to left) are a different prime. (This rules out primes which are palindromes.)
;
;Examples:
;13 17 31 37 71 73 are Backwards Read Primes
;13 is such because it's prime and read from right to left writes 31 which is prime too. Same for the others.
;
;Task
;Find all Backwards Read Primes between two positive given numbers (both inclusive), the second one being greater than the first one. The resulting array or the resulting string will be ordered following the natural order of the prime numbers.
;
;Example
;backwardsPrime(2, 100) => [13, 17, 31, 37, 71, 73, 79, 97] backwardsPrime(9900, 10000) => [9923, 9931, 9941, 9967]
;
;(backwards-prime 2 100) => [13, 17, 31, 37, 71, 73, 79, 97]
;(backwards-prime 9900 10000) => [9923, 9931, 9941, 9967]

(def primes
  (concat
    [2 3 5 7]
    (lazy-seq
      (let [primes-from
                  (fn primes-from [n [f & r]]
                    (if (some #(zero? (rem n %))
                              (take-while #(<= (* % %) n) primes))
                      (recur (+ n f) r)
                      (lazy-seq (cons n (primes-from (+ n f) r)))))
            wheel (cycle [2 4 2 4 6 2 6 4 2 4 6 6 2 6 4 2
                          6 4 6 8 4 2 4 2 4 8 6 4 6 2 4 6
                          2 6 6 4 2 4 6 2 6 4 2 4 2 10 2 10])]
        (primes-from 11 wheel)))))


(defn primes-n [n]
  (letfn [(step [n]
            (let [coll (range 2 n)]
              (loop [result []
                     fv (first coll)
                     coll (rest coll)]
                (let [result (conj result fv)
                      rcoll (filter #(pos? (rem % fv)) coll)]
                  (if (empty? rcoll)
                    result
                    (recur result (first rcoll) (rest rcoll)))))))]
    (lazy-seq (step n))))

(defn backwards [n]
  (loop [q (quot n 10)
         r (rem n 10)]
    (if (zero? q)
      r
      (recur (quot q 10) (+ (rem q 10) (* r 10))))))

(defn backwards-prime [start stop]
  (for [coll (filter #(>= % start) (primes-n stop)) :when (and (primes? (backwards coll)) (not= coll (backwards coll)))]
    coll))