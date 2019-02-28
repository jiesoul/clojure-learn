(ns com.jiesoul.codewar.prime-decomp)

(defn primes [n]
  (if (<= n 2)
    [n]
    (letfn [(step [coll]
              (lazy-seq (loop [r    []
                               coll coll]
                          (let [fv (first coll)]
                            (if (> fv (Math/sqrt n))
                              (concat r coll)
                              (recur (conj r fv) (filter #(pos? (rem % fv)) (rest coll))))))))]
      (step (range 2 (inc n))))))

(defn primes [n]
  (let [v (Math/sqrt n)]
    (loop [c1 []
           c2 (range 2 (inc n))]
      (let [fv (first c2)]
        (if (> fv v)
          (concat c1 c2)
          (recur (conj c1 fv) (filter #(pos? (rem % fv)) (rest c2))))))))

(def prime-numbers
  ((fn f [x]
     (cons x
       (lazy-seq
         (f (first
              (drop-while
                (fn [n]
                  (some #(zero? (rem n %))
                    (take-while #(<= (* % %) n) prime-numbers)))
                (iterate inc (inc x))))))))
    2))

(defn step [n m]
  (loop [n n
         r 0]
    (if (zero? (rem n m))
      (recur (quot n m) (inc r))
      [n m r])))

(defn prime-factors [n]
  (let [coll (primes n)]
    (loop [n n
           r []
           coll coll]
      (if (= 1 n)
        (->> r
          (map (fn [[a b]]
                 (str "(" a (when (> b 1) (str "**" b)) ")")))
          (apply str))
        (let [v (first coll)
              c (step n v)]
          (recur (first c)
            (if (zero? (last c)) r (conj r (rest c)))
            (rest coll)))))))

(primes 933555431)
(take-while #(<= % 933555431) prime-numbers)
(count (primes 7919))
(step 86240 2)
(step 2 2)
(time (prime-factors 86240))
(time (prime-factors 7919))