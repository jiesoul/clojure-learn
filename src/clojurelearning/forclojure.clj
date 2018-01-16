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
(fn [f])
(fn [f & maps]
  (reduce (fn [m1 m2]
            (reduce (fn [m [k v]]
                      (if-let [vv (m k)]
                        (assoc m k (f vv v))
                        (assoc m k v)))
                    m1 m2))
          maps)  #(f %2 %1))


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

;;53 参考网上的答案
(defn last-sub-seq [coll]
  (let [a (reduce #(if (< (count %1) (count %2)) %2 %1)
                  []
                  (filter
                    (fn [[[x1 x2]]] (< x1 x2))
                    (partition-by
                      #(apply < %)
                      (partition 2 1 coll))))]
    (concat (first a) (rest (map second a)))))

;;54
(fn [n coll]
  (loop [coll coll
         r []]
    (if (> n (count coll))
      r
      (recur (drop n coll) (conj r (take n coll))))))

;;55
(defn count-occ [coll]
  (let [m (group-by identity coll)]
    (reduce (fn [m1 [k v]]
           (assoc m1 k (count v)))
            {}
            m)))

;;56
(defn find-dis [arg]
  (reduce (fn [s e]
            (if (some #(= % e) s)
              s
              (conj s e)))
          [] arg))

;;58 reduce
(defn comp- [& funs]
  (fn [& args]
    (first (reduce #(vector (apply %2 %1)) args (reverse funs)))))

;;59
(defn juxt- [& funcs]
  (fn [& args]
    (reduce #(conj %1 (apply %2 args)) [] funcs)))

;;60
(defn reductions-
  ([f coll]
    (reductions- f (first coll) (rest coll)))
  ([f r coll]
   (lazy-seq (if (empty? coll)
               (list r)
               (cons r (reductions- f
                                    (f r (first coll))
                                    (rest coll)))))))

;;61
(defn zipmap- [keys vals]
  (loop [m {}
         ks (seq keys)
         vs (seq vals)]
    (if (and ks vs)
      (recur (assoc m (first ks) (first vs))
             (next ks)
             (next vs))
      m)))

;;62
(defn re-impl-iter [f a]
  (lazy-seq
    (cons a (re-impl-iter f (f a)))))

;;65
(defn type- [coll]
  (let [s (first (str coll))]
    (case s
      \{ :map
      \[ :vector
      \# :set
      :list)))

;;66
(defn gcd [a b]
  (if (zero? b)
    a
    (recur b (mod a b))))

;;67
(defn primes? [a]
  (not-any? #(zero? (rem a %)) (range 2 (inc (Math/sqrt a)))))

(defn take-primes [n]
  (take n
        (filter (fn primes? [a]
                  (if (= a 2)
                    true
                    (not-any?
                      #(zero? (rem a %))
                      (range 2 (inc (Math/sqrt a))))))
                (range 2 1000))))

(def primes
  (concat
    [2 3 5 7]
    (lazy-seq
      (let [primes-form (fn primes-form [n [f & r]]
                          (if (some #(zero? (rem n %))
                                    (take-while #(<= (* % %) n) primes))
                            (recur (+ n f) r)
                            (lazy-seq (cons n (primes-form (+ n f) r)))))
            wheel (cycle [2])]
        primes-form 11 wheel))))



;; 69  别人的 自己想不出来
(fn [f & maps]
  (reduce (fn [m1 m2]
            (reduce (fn [m [k v]]
                      (if-let [vv (m k)]
                        (assoc m k (f vv v))
                        (assoc m k v)))
                    m1 m2))
          maps))

;;70
(defn word-sort [s]
  (vec (sort-by clojure.string/lower-case
                (clojure.string/split
                  (clojure.string/replace s #"[.|!]" "") #"\s+"))))

;;72
(fn [coll]
  (reduce + coll))

;;73
(defn filter-perfect-square [s]
  (clojure.string/join ","
    (filter (fn [x]
              (let [xi (Integer/parseInt x)
                    sq (int (Math/sqrt xi))]
                (= xi (* sq sq))))
            (clojure.string/split s #","))))

;;75
(defn euler-func [n]
  (if (= 1 n)
    1
    (count (filter #(= 1 ((fn gcd [a b]
                      (if (zero? b)
                        a
                        (recur b (mod a b)))) % n)) (range 1 n)))))

;;77
(defn anagram-finder [coll]
  (set (map (comp set val) (filter #(> (count (val %)) 1) (group-by (fn [s]
                                                                      (set s))
                                                                    coll)))))
;;78
(defn trampoline-
  ([f]
    (let [r (f)]
      (if (fn? r)
        (recur r)
        r)))
  ([f & args]
    (trampoline- #(apply f args))))

;;79
(defn min-path [coll]
  (reduce #(loop [r []
                     c1 %1
                     c2 %2]
                (if (<= (count c2) 1)
                  r
                  (recur
                    (conj r (+ (first c1) (first c2)) (+ (first c1) (second c2)))
                    (rest c1)
                    (rest c2))))
             (first coll) (rest coll)))

;;83
(defn half-truth [& args]
  (let [g (group-by identity args)]
    (if (> (count (keys g)) 1)
      true
      false)))


