;If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
;
;Finish the solution so that it returns the sum of all the multiples of 3 or 5 below the number passed in.
;
;Note: If the number is a multiple of both 3 and 5, only count it once.
;
;Courtesy of ProjectEuler.net

(ns com.jiesoul.codewar.multiples)

(defn solution [number]
  (let [coll (range 1 number)
        step (fn [n coll]
               (reduce +' (filter #(zero? (rem % n)) coll)))
        s3 (step 3 coll)
        s5 (step 5 coll)
        s35 (step (* 3 5) coll)]
    (- (+ s3 s5) s35)))
