;The aim is to calculate exponential(x) as an irreducible fraction, the numerator of this fraction having a given number of digits.
;
;We call this function expand, it takes two parameters, x of which we want to evaluate the exponential, digits which is the required number of digits for the numerator.
;
;The function expand will return an array of the form [numerator, denominator]; we stop the loop in the Taylor expansion (see references below) when the numerator has a number of digits >= the required number of digits
;
;Examples:
;expand(1, 2) --> 65/24 (we will write this [65, 24] or (65, 24) in Haskell;
;                           65/24 ~ 2.708...)
;
;expand(2, 5) --> [20947, 2835]
;
;expand(3, 10) --> [7205850259, 358758400]
;
;expand(1.5, 10) --> [36185315027,8074035200]
;Note expand(1,5) = [109601, 40320] is the same as expand(1, 6)
;
;#Method: As said above the way here is to use Taylor expansion of the exponential function though it is not always the best approximation by a rational.
;
;#References: https://en.wikipedia.org/wiki/Exponential_function#Formal_definition
;
;http://www.efunda.com/math/taylor_series/exponential.cfm

(defn exponential- [x]
  (map #(let [n (first %)] (if (ratio? n) [(numerator n) (denominator n)] [n 1]))
       (iterate (fn [[a b]]
                  (let [b (inc b)]
                    [(+' a (/ (reduce *' (repeat b x)) (reduce *' (range 1 (inc b))))) b]))
                [1 0])))

(defn exponential [x m]
  (letfn [(fac [n]
            (reduce *' (range 1 (inc n))))
          (step [x m]
            (loop [r 1
                   n 1]
              (let [num (+' r (/ (Math/pow x n) (fac n)))
                    nn  (if (ratio? num) (numerator num) num)
                    nm (if (ratio? num) (denominator num) 1)]
               (if (>= nn m)
                 [nn nm]
                 (recur num (inc n))))))]
    (lazy-seq (step x m))))

(defn expon- [x]
  (let [x (rationalize x)]
    (map #(if (ratio? %)
            [(numerator %) (denominator %)]
            [% 1])
         (reductions +' 1
                     (map #(/ (reduce *' (repeat % x))
                              (reduce *' (range 1 (inc %))))
                          (iterate inc 1))))))

(defn expand [x nDigits]
  (let [m (reduce *' (repeat (dec nDigits) 10))
        xs (expon- x)]
    (first (filter #(> (first %) m) xs))))