(ns clojurelearning.forclojure)

; #19 last Element
(defn last-element [coll]
  (if (<= (count coll) 1)
    (first coll)
    (recur (rest coll))))


;#20
(defn penultimate-element [coll]
  (cond
    (<= (count coll) 1) nil
    (== (count coll) 2) (first coll)
    :else
    (recur (rest coll))))

;21
(defn nth-element [coll n]
  (loop [i 0
         coll coll]
    (if (== i n)
      (first coll)
      (recur (inc i) (rest coll)))))

;22
(fn [s]
  (loop [i 0
         coll s]
    (if (empty? coll)
      i
      (recur (inc i) (rest coll)))))


; #23 Reverse a Sequence
(defn rev-seq [seq]
  (loop [coll seq
         r ()]
    (if (empty? coll)
      (vec r)
      (recur (rest coll) (conj r (first coll))))))

;24
(fn [s]
  (reduce + 0 s))

;25
(fn [s]
  (filter odd? s))

;26
(defn lazy-seq-fibo
  ([]
   (concat [1 1] (lazy-seq-fibo 1 1)))
  ([a b]
   (let [n (+ a b)]
     (lazy-seq
       (cons n (lazy-seq-fibo b n))))))

(defn take-fibo [n]
  (letfn [(lazy-seq-fibo
            ([]
             (concat [1 1] (lazy-seq-fibo 1 1)))
            ([a b]
             (let [n (+ a b)]
               (lazy-seq
                 (cons n (lazy-seq-fibo b n))))))]
    (take n (lazy-seq-fibo))))

;27
(defn palindrome-detector [a]
  (= a (if (string? a)
         (apply str (reverse a))
         (reverse a))))
;28
(defn flatten-seq [s]
  (if-not (some seq? s)
    s
    (recur (concat s))))

;;
(defn fla-seq [coll]
  (reduce
    (fn fla [r c]
      (if (coll? c)
        (reduce fla r c)
        (conj r c)))
    [] coll))


;; 29
(defn get-caps [s]
  (apply str (re-seq #"[A-Z]+" s)))


;;30
(defn compress-seq [coll]
  (map first (partition-by identity coll)))


;;31
(fn [coll]
  (partition-by identity coll))


;;32
(fn [coll]
  (interleave coll coll))


;;33
(fn [coll n]
  (let [a (repeat (dec n) coll)]
    (reduce interleave a)))

;;39
(fn [x y]
  (loop [x x
         y y
         z []]
    (if (or (empty? x) (empty? y))
      z
      (recur (rest x) (rest y) (conj z (first x) (first y))))))

;;40 Interpose a Seq
(fn [n coll]
  (-> (interleave coll (repeat n))
      drop-last
      vec))


;; 41 Drop Every Nth Item
(fn [coll n]
  (mapcat #(take (dec n) %) (partition-all n coll)))

;; 42 Factorial Fun
#(reduce * (range 1 (inc %)))

;#43 Reverse Interleave
(defn rev-inter [coll n]
  (loop [coll coll
         r []]
    (if (empty? coll)
      (seq r)
      (recur (filter #(not= (rem (first coll) n) (rem % n)) coll)
             (conj r (filter #(== (rem (first coll) n) (rem % n)) coll))))))

;; 43 from internet
(fn [coll n]
  (apply map vector (partition n coll)))

;;44
(defn ss [n coll]
  (let [c (count coll)
        n (rem n c)
        i (if (>= n 0) n (- c (Math/abs n)))]
    (concat (drop i coll) (take i coll))))

;; 46
(fn [f]
  #(f %2 %1))


;;49
(fn [n coll]
  (conj [] (take n coll) (drop n coll)))

;; 50
#(vals (group-by type %))

;;58
(fn [f1 f2 & fs]
  (fn [args]
    (-> args
        f1
        f2)))

;; 69  别人的 自己想不出来
(fn [f & maps]
  (reduce (fn [m1 m2]
            (reduce (fn [m [k v]]
                      (if-let [vv (m k)]
                        (assoc m k (f vv v))
                        (assoc m k v)))
                    m1 m2))
          maps))

;;58 reduce 我的理解还差很远
(fn comp- [& funs]
  (fn [& args]
    (first
      (reduce #(vector (apply %1 %2)) args (reverse funs)))))
