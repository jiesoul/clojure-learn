;Given an array of positive or negative integers
;
;I= [i1,..,in]
;
;you have to produce a sorted array P of the form
;
;[ [p, sum of all ij of I for which p is a prime factor (p positive) of ij] ...]
;
;P will be sorted by increasing order of the prime numbers. The final result has to be given as a string in Java, C# or C++ and as an array of arrays in other languages.
;
;Example:
;
;I = [12, 15] ; result = [[2, 12], [3, 27], [5, 15]]
;[2, 3, 5] is the list of all prime factors of the elements of I, hence the result.
;
;Notes: It can happen that a sum is 0 if some numbers are negative!
;
;Example: I = [15, 30, -45] 5 divides 15, 30 and (-45) so 5 appears in the result, the sum of the numbers for which 5 is a factor is 0 so we have [5, 0] in the result amongst others.

(ns com.jiesoul.codewar.sumbyfactors)

(defn prime []
  (letfn [(step [coll]
            (let [head (first coll)]
              (lazy-cat (cons head (step (filter #(pos? (rem % head)) coll))))))]
    (step (range 2 Long/MAX_VALUE))))

(defn sum-of-divided [lst]
  (let [step (fn [n]
               (loop [r []
                      n (Math/abs n)
                      facts (prime)]
                 (let [fv (first facts)]
                   (if (= n fv)
                     (conj r n)
                     (let [q (quot n fv)
                           re (rem n fv)]
                       (if (zero? re)
                         (recur (conj r fv) q facts)
                         (recur r n (rest facts))))))))
        facted (fn [f coll] (map #(if (zero? (rem % f)) (quot % f) 0) coll))
        fac (sort (keys (frequencies (mapcat #(step %) lst))))]
    (map #(conj [] % (* % (reduce + (facted % lst)))) fac)))