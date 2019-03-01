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

(defn primes-1 [n]
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

(defn prime1 [n]
  (let [q (Math/sqrt n)]
    (lazy-seq (loop [r []
                     c (range 2 (inc n))]
                (let [v (first c)
                      coll (rest c)]
                  (if (> v q)
                    (concat r c)
                    (recur (conj r v) (filter #(pos? (rem % v)) coll))))))))

(defn prime [n]
  (letfn [(step [coll]
            (let [head (first coll)]
              (lazy-cat (cons head (step (filter #(pos? (rem % head)) coll))))))]
    (step (range 2 (inc n)))))

(defn step [n m]
  (loop [n n
         r 0]
    (if (zero? (rem n m))
      (recur (quot n m) (inc r))
      [n m r])))

(defn prime-factors [n]
  (let [coll (prime n)]
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

(defn prime-factors-old [n]
  (loop [r {}
         n n
         coll (range 2 (inc n))]
    (if (= n 1)
      r
      (let [fv (first coll)
            q (quot n fv)
            rv (rem n fv)
            v (get r fv 0)]
        (recur (if (zero? rv) (assoc r fv (inc v)) r)
               q
               (if (zero? rv) coll (filter #(pos? (rem % fv)) (rest coll))))))))

;(primes 9335553)
;(take-while #(<= % 933555431) prime-numbers)
;(count (primes 7919))
;(step 86240 2)
;(step 2 2)
;(prime-factors 20)
;(time (prime-factors 86240))
;(time (prime-factors 7919))