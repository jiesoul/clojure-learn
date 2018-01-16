(ns clojurelearning.codewar
  (:require [clojure.string :as str]))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(defn max-rot [n]
  ; your code
  (let [num (str n)
        cou (count num)]
    (loop [index 0
           s num
           list []]
      (if (< index (dec cou))
        (let [ss (str (subs s 0 index) (subs s (inc index) cou) (subs s index (inc index)))]
          (recur (inc index)
                 ss
                 (conj list (Long/parseLong ss))))
        (apply max (conj list n))))))


(defn numMove [n index]
  (let [s (str n)]
    (str (subs s 0 index) (subs s (inc index) (count s)) (subs s index (inc index)))))



(defn gps [s x]
  (if (<= (count x) 1)
    0
    (loop [list x
           result []]
      (if (<= (count list) 1)
        (int (/ (* 3600 (apply max result)) s))
        (recur (rest list) (conj result (- (second list) (first list))))))))

;
(defn gps [s x]
  (if (<= (count x) 1)
    0
    (int (apply max (map #(/ (* % 3600) (float s)) (map - (rest x) x))))))




(defn filter-rem [n coll]
  (filter #(not= 0 (rem % n)) coll))

(defn primes? [n]
  (let [nums (range 2 (inc (int (Math/sqrt n))))]
    (not-any? zero? (map #(rem n %) nums))))

(defn first-primes [coll]
  (loop [l coll]
    (if (primes? (first l))
      (first l)
      (recur (rest l)))))

(defn primes-gen [n]
  (loop [coll (range 2 n)
         endcoll []]
    (if (> (first coll) (int (Math/sqrt (last coll))))
      (concat coll coll)
      (recur (filter-rem (first coll) coll) (conj endcoll (first coll))))))

(defn primes-n [n]
  (loop [coll (range 2 n)
         result []]
    (if (> (first coll) (int (Math/sqrt (last coll))))
      (concat result coll)
      (recur (filter #(not= 0 (rem % (first coll))) coll) (conj result (first coll))))))

(def m-primes-n
  (memoize primes-n))

(defn prime [g m n]
  (let [l (filter #(>= % m) (primes-n n))]
    (loop [coll (rest l)
           fact (first l)]
      (cond
        (empty? coll) nil
        (not (nil? (some #{(+ fact g)} coll))) [fact (+ fact g)]
        :else
        (recur (rest coll) (first coll))))))

(def step
  (memoize prime))

(defn finance-1 [n]
  (loop [start 0
         end (+ n 1)
         result 0]
    (if (> start (* 2 n))
      result
      (recur (+ 2 start) (inc end) (reduce + result (range start end))))))

(defn finance-sum [n]
  (/ (* (inc n) (* 3 n)) 2))

(defn finance [n]
  (loop [n n
         r 0]
    (if (zero? n)
      r
      (recur (dec n) (+ (finance-sum n) r)))))

(defn vert-mirror [strng]
  (clojure.string/join "\n" (map (comp (partial apply str) reverse) (clojure.string/split-lines strng))))

(defn hor-mirror [strng]
  (apply str (reverse (vert-mirror strng))))


(defn oper [fct s]
  (fct s))

(defn decm [n]
  (loop [n n
         r []]
    (if (zero? n)
      r
      (recur (quot n 10) (conj r (rem n 10))))))

(defn sum-decm [n]
  (reduce + (decm n)))

(defn digital-root [n]
  (if (< n 10)
    n
    (recur (sum-decm n))))

(defn round [s n]
  (.setScale (bigdec n) s java.math.RoundingMode/HALF_EVEN))

(defn iterPi [epsilon])



(defn compSame [a b]
  (cond
    (and (nil? a) (nil? b)) true
    (and (nil? a) (not (nil? b))) false
    (and (nil? b) (not (nil? a))) false
    (not= (count a) (count b)) false
    (and (empty? a) (empty? b)) true
    :else
    (loop [coll (map #(* % %) a)
           r b]
      (if (empty? coll)
        (empty? r)
        (recur (rest coll) (drop-while #(not= % (first coll)) r))))))

(defn primes-list [m n]
  (for [coll (range m n) :when primes?]
    coll))

(defn backwards [n]
  (loop [q (quot n 10)
         r (rem n 10)]
    (if (zero? q)
      r
      (recur (quot q 10) (+ (rem q 10) (* r 10))))))

(defn backwards-prime [start stop]
  (for [coll (filter #(>= % start) (primes-n stop)) :when (and (primes? (backwards coll)) (not= coll (backwards coll)))]
    coll))


;Number of trailing zeros of N!
(defn zeors [n]
  (loop [coll (range 5 (inc n) 5)
         sum 0]
    (if (empty? coll)
      sum
      (recur (filter #(zero? (rem % 5)) (map #(/ % 5) coll)) (+ sum (count coll))))))

(defn zeors [n]
  (let [coll (range 5 (inc n) 5)]
    (loop [i 5
           sum 0]
      (if (> i n)
        sum
        (recur (* i 5) (+ sum (count (filter #(zero? (rem % 5)) coll))))))))

