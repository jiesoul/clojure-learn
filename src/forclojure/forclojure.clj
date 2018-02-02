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
                (range 2 Long/MAX_VALUE))))

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
        primes-form 11 wheel []))))



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
  (reduce #((fn [c1 c2]
              (loop [r []
                      %1 %2])))
          [[(first coll)] [(first coll)]] (rest coll)))

;;80 Perfect Numbers
(defn perfect-num
  [n]
  (= n (reduce + (filter #(zero? (rem n %)) (range 1 (inc (quot n 2)))))))

;;81
(defn intersection- [a b]
  (set (filter #(contains? b %) a)))

;;82
(defn word-chain [st]
  (let [coll (map set (sort-by str st))]
    (letfn [(match? [ff coll])])))


;;83
(defn half-truth [& args]
  (let [g (group-by identity args)]
    (if (> (count (keys g)) 1)
      true
      false)))

;;85Power Set
(defn power-set
  [sets]
  (if (empty? sets)
    #{#{}}
    (let [v (first sets)
          ps (power-set (rest sets))]
      (set (concat (map #(conj % v) ps) ps)))))

(defn sum- [nv]
  (loop [r 0
         nv nv]
    (if (zero? nv)
      r
      (let [rv (rem nv 10)
            qv (quot nv 10)]
        (recur (+ r (* rv rv)) qv)))))

;;86Happy numbers
(defn happy-num
  [n]
  (letfn [(sum- [nv]
            (loop [r 0
                   nv nv]
              (if (zero? nv)
                r
                (let [rv (rem nv 10)
                      qv (quot nv 10)]
                  (recur (+ r (* rv rv)) qv)))))]
    (loop [r (conj #{} n)
           n (sum- n)]
      (cond
        (= n 1) true
        (some #{n} r) false
        :else
        (recur (conj r n) (sum- n))))))

;;88 Symmetric Difference
(defn sym-diff [s1 s2]
  (letfn [(diff [a b]
            (set (filter #(not (contains? b %)) a)))]
    (set (concat (diff s1 s2) (diff s2 s1)))))

;;90 Cartesian Product
(defn cart-pro [s1 s2]
  (set (for [x s1
             y s2]
         [x y])))

;; 93 Partially Flatten a Sequence
(defn partil-flatten-seq
  [root]
  (filter #(and (sequential? %)
                (every? (complement sequential?) %))
          (tree-seq sequential? identity root)))

;;95 To Tree, or not to Tree
(defn tree? [s]
  (if (and (seq s) (= 3 (count s)))
    (let [[v l r] s]
      (cond
        (nil? v)  false
        (and (nil? r) (nil? l)) true
        (coll? l) (tree? l)
        (coll? r) (tree? r)
        :else
        false))

    false))

;;96 Beauty is Symmetry
(defn beauty-is-symmetry? [[k l r]]
  (letfn [(comapre-ss [[k1 l1 r1] [k2 l2 r2]]
            (cond
              (or (not= k1 k2)
                  (not= (type l1) (type r2))
                  (not= (type l2) (type r1))) false
              (and (nil? l1) (nil? r1)) true
              (and (coll? l1) (coll? r1)) (and (comapre-ss l1 r2) (comapre-ss l2 r1))
              (and (nil? l1) (not (nil? l2))) (comapre-ss l2 r1)
              (and (not (nil? l1)) (nil? l2)) (comapre-ss l1 r2)
              :else
              true))]
    (cond
      (and (coll? l) (coll? r)) (comapre-ss l r)
      (and (nil? l) (nil? r)) true
      :else
      false)))

;;97Pascal's Triangle
(defn pascal-triangle [n]
  (lazy-seq (if (= 1 n)
              [1]
              (map #(+ (first %) (second %))
                   (partition 2 1  (concat [] [0] (pascal-triangle (- n 1)) [0]))))))

;;98Equivalence Classes
(defn equ-class
  [f sets]
  (set (map set (vals (group-by f sets)))))

;;99 Product Digits
(defn pro-digits [a b]
  (loop [x (* a b)
         r ()]
       (if (zero? x)
         r
         (recur (quot x 10) (conj r (rem x 10))))))

;;100 Least Common Multiple
(defn least-com-mul [& args]
  (letfn [(gcd [a b]
            (if (zero? b)
              a
              (recur b (mod a b))))]
    (reduce #(/ (* %1 %2) (gcd %1 %2)) args)))

;;102 intoCamelCase
(defn into-came-case
  [s]
  (let [s (clojure.string/split s #"-")]
    (if (= 1 (count s))
      (apply str s)
      (apply str (first s) (map #(apply str (clojure.string/upper-case (first %)) (rest %)) (rest s))))))

;;103 Generating k-combinations
(defn gen-k-comb
  [n coll]
  (set (filter #(= n (count %)) (reduce #(concat %1 (map (fn [i] (conj i %2)) %1)) #{#{}} coll))))


;;104 Write Roman Numerals
(defn write-roman-num
  [n]
  (let [dict {1000 "M", 900 "CM", 500 "D", 400 "CD", 100 "C", 90 "XC", 50 "L", 40 "XL", 10 "X", 9 "IX", 5 "V", 4 "IV", 1 "I"}
        qs (reverse (sort (keys dict)))]
    (letfn [(quot-roman [n q]
              (if (seq q)
                (let [fe (first q)]
                  (lazy-cat [[fe (quot n fe)]] (quot-roman (rem n fe) (rest q))))))]
      (apply str (flatten (map #(repeat (last %) (dict (first %))) (quot-roman n qs)))))))

;;105 Identify keys and values  本地测试没有问题  网站上过不了
(defn iden-keys-vals
  [coll]
  (reduce #(let [k (ffirst %2)]
             (assoc %1 k (vec (filter (complement keyword?) (last %2))))
             {})
          (filter #(keyword? (ffirst %)) (partition 2 1 (partition-by keyword coll)))))

;;107
(defn simple-closures [n]
  (fn [i]
    (reduce * (repeat n i))))


;;108 Lazy Searching
(defn lazy-search
  [& coll]
  (loop [xss coll]
    (let [c (map first xss)
          ma (apply max c)
          mi (apply min c)]
      (letfn [(filter-min [n xs]
                (filter #(>= % n) xs))]
        (if (= ma mi)
          ma
          (recur (map #(filter-min ma %) xss)))))))

(= 4 (lazy-search [1 2 3 4 5 6 7] [0.5 3/2 4 19]))

;;110Sequence of pronunciations
(defn seq-of-pro
  [coll]
  (lazy-seq
    (let [next-coll (flatten (map #(vector (count %) (first %)) (partition-by identity coll)))]
      (cons next-coll (seq-of-pro next-coll)))))


;;112 Sequs Horribilis
(defn seq-horr
  [n [x & xs]]
  (if (nil? x)
    []
    (if (sequential? x)
      (concat [(seq-horr n x)])
      (if (< n x)
        []
        (concat [x] (seq-horr (- n x) xs))))))

(defn sequs [sum [x & xs]]
  (if (nil? x) []
               (if (sequential? x)
                 (concat [(sequs sum x)] (sequs (- sum (reduce + (flatten x))) xs))
                 (if (< sum x)
                   []
                   (concat [x] (sequs (- sum x) xs))))))



;; 114 global take-while
(defn global-take-while
  [n pred coll]
  (lazy-seq (letfn [(take0 [pred coll]
                      (loop [r []
                             [fe & me] coll]
                        (if (pred fe)
                          r
                          (recur (conj r fe) me))))]
              (let [fe (take0 pred coll)
                    co (count fe)]
                (if (= 1 n)
                  fe
                  (lazy-seq (concat fe [(nth coll co)]
                                    (global-take-while (dec n) pred (drop (inc co) coll)))))))))


;;115 the balance of n
(defn blance-of-n
  [n]
  (let [coll (map (comp #(Integer/parseInt %) str) (str n))
        c (count coll)
        m (+ (quot c 2) (rem c 2))
        f (take m coll)
        l (take m (reverse coll))]

    (= (reduce + f) (reduce + l))))

;; 116 prime sandwich
(defn primes-san
  [n]
  (letfn [(primes []
            (letfn [(step-primes [coll]
                      (let [head (first coll)]
                        (lazy-seq (cons head (step-primes (filter #(pos? (mod % head)) coll))))))]
              (step-primes (range 2 Long/MAX_VALUE))))
          (blance-primes []
            (map #(second %) (filter #(= (second %) (/ (+ (first %) (last %)) 2)) (partition 3 1 (primes)))))]
    (= n (last (take-while #(<= % n) (blance-primes))))))


;; 埃拉托斯特尼筛法
(defn prime []
       (letfn [(step [coll]
                 (let [head (first coll)]
                   (lazy-cat (cons head (step (filter #(pos? (rem % head)) coll))))))]
         (step (range 2 Long/MAX_VALUE))))


;;118 re-implement map
(defn reimpl-map [f coll]
  (lazy-seq (reduce #(lazy-seq (conj %1 (f %2))) [] coll)))

(defn re-impl-map [f coll]
  (lazy-seq (when (seq coll)
              (cons (f (first coll)) (re-impl-map f (rest coll))))))

;;120 sum of square of digits
(defn sum-of-squ-dig [coll]
  (letfn [(sum-of-squ [n]
            (loop [r 0
                   n n]
              (if (zero? n)
                r
                (recur (+ r (* (rem n 10) (rem n 10))) (quot n 10)))))]
    (count (filter #(< % (sum-of-squ %)) coll))))


;;121 universal computation engine
(defn uce
  [[op & args]]
  (let [operator ({'/ / '* * '+ + '- -} op)]
    (fn [m]
      (let [eval- (fn [exp]
                   (if (seq? exp)
                     ((uce exp) m)
                     (or (m exp) exp)))]
        (apply operator (map eval- args))))))


;;122 read a binary number
(defn read-bin-num [s]
  (loop [x (map (comp #(Integer/parseInt %) str) (seq s))
         r 0]
    (if (empty? x)
      r
      (recur (rest x) (+ r (reduce * (first x) (repeat (dec (count x)) 2)))))))

;;through the looking class
(defn thr-look-class [])


;; 128 recognize playing cards
(defn rec-play-cards [s]
  (let [suit (zipmap [\d \h \c \s] [:diamond :heart :club :spade])
        rank (zipmap [\2 \3 \4 \5 \6 \7 \8 \9 \t \j \q \k \a] (range 13))]
    {:suit (suit (first s)) :rank (rank (second s))}))


;;131 sum some set subsets
(defn sum-some-set-sub
  [& sets]
  (letfn [(power-set [sets]
            (if (empty? sets)
              #{#{}}
              (let [fv (first sets)
                    rv (power-set (rest sets))]
                (set (concat (map #(conj % fv) rv) rv)))))
          (rm-empty [coll]
            (filter #(not-empty %) coll))
          (sum-sub-set [coll]
            (map #(reduce + %) coll))]
    (let [sets (map (comp #(set %) #(sum-sub-set %) #(rm-empty %) #(power-set %)) sets)
          c1 (reduce #(+ %1 (count %2)) (count (first sets)) (rest sets))
          c2 (count (flatten sets))
          cc (count sets)]
      (if (> cc 1)
        ((complement empty?) (apply clojure.set/intersection sets))
        true))))



;;132 insert between two items
(defn in-be-two-items
  [p v c]
  (if (seq c)
    (let [xs (map #(if (apply p %) [(first %) v (last %)] %) (partition 2 1 c))]
      (letfn [(step [coll]
                (if (seq coll)
                  (lazy-cat (drop-last (first coll)) (step (rest coll)))))]
        (lazy-cat (step xs) [(last c)])))))

(defn insert-items
  [p v c]
  (take 1 (reverse
            (if (empty? c)
              c
              (reductions (fn in [s ne]
                            (let [le (last s)]
                              (if (apply p le ne)
                                (cons [v ne] s)
                                (cons [ne] s))))
                          [(first c)]
                          (rest c))))))


(defn in [pred k coll]
  (if (seq coll)
    (let [temp-result (map #(if (apply pred %) [(first %) k (last %)] %) (partition 2 1 coll))]
      (letfn [(step [coll]
                (if (seq coll)
                  (lazy-cat (drop-last (first coll)) (step (next coll)))))]
        (lazy-cat (step temp-result) [(last coll)])))))

;;134
(defn nil-map? [key map]
  (if (contains? map key)
    (nil? (key map))
    false))

;;135 infix calculator
(defn infix-cal [& args]
  (reduce (fn [a [op b]]
            (op a b))
          (first args)
          (partition 2 (rest args))))

;;137 digits and bases
(defn dig-base
  [dig d]
  (lazy-seq
    (if (< dig d)
      [dig]
      (concat (dig-base (quot dig d) d) [(rem dig d)]))))

;; 141 tricky card games
(defn card-game
  [card]
  (fn [coll]
    (let [card (if (nil? card) (get (first coll) :suit) card)
          xs (filter #(= (:suit %) card) coll)
          maxr (apply max (map :rank xs))
          xs (filter #(= maxr (:rank %)) xs)]
      (first xs))))


  ;;143 dot product
(defn dot-pro [v1 v2]
  (reduce + (loop [r []
                   v1 v1
                   v2 v2]
              (if (empty? v1)
                r
                (recur (conj r (* (first v1) (first v2))) (rest v1) (rest v2))))))

;;144 oscilrate
(defn oscilrate
  [d & fs]
  (reductions #(%2 %1) d (cycle fs)))

(defn osclirate1
  [d & fs]
  (lazy-seq
    (loop [r [d]
           d d
           xs fs]
      (if (empty? xs)
        r
        (let [f (first xs)
              v (f d)]
          (recur (lazy-seq (conj r v))
                 v
                 (rest xs)))))))

;;146 trees into tables 这个我脑子抽了
(defn tree-into-tab
  [m]
  (into
    {}
    (for [[k v] m
          [vk vv] v]
      (vec [[k vk] vv]))))

;;147 pascal's trapezoid
(defn pascal-trap [coll]
  (lazy-seq (iterate (fn [c]
                       (map #(+' (first %) (second %))
                            (partition 2 1 (concat [] [0] c [0])))) coll)))


;; 148 the big divide
;; Write a function which calculates the sum of all natural numbers under n (first argument) which are evenly divisible by at least one of a and b (second and third argument). Numbers a and b are guaranteed to be coprimes.

; Note: Some test cases have a very large n, so the most obvious solution will exceed the time limit.
(defn big-div
  [n a b]
  (letfn [(sum- [n]
            (/ (*' (inc n) n) 2))]
    (let [n (dec n)
          aq (sum- (quot n a))
          bq (sum- (quot n b))
          ab (*' a b)
          abq (sum- (quot n ab))]
      (- (+ (*' aq a) (*' bq b)) (*' abq ab)))))


;;150 Palindromic Numbers
(defn back [n]
  (Long/parseLong (apply str (reverse (seq (str n))))))

(defn back- [n]
  (loop [r 0
         n n]
    (let [re (rem n 10)
          qe (quot n 10)]
      (if (zero? qe)
        (+ (* r 10) re)
        (recur (+ (* r 10) re) (quot n 10))))))

(defn pali-nums
  [n]
  (letfn [
          (pali [n]
            (filter #(= % (back- %)) (iterate inc n)))]
     (pali n)))

(defn pal-nums [n]
  (letfn [(decode [n]
            (if (< n 10)
              [n]
              (conj (decode (quot n 10)) (rem n 10))))
          (encode [coll]
            (reduce #(+ (* % 10) %2) 0 coll))
          (next-num [n]
            (let [ns (decode n)
                  l (count ns)
                  m (quot l 2)
                  ls (take m ns)
                  lr (reverse ls)
                  lv (encode lr)
                  mp (nth ns m)
                  rv (encode (take-last m ns))
                  ls1 (decode (inc (encode ls)))]
              (encode (cond
                        (and (even? l) (>= lv rv)) (concat ls lr)
                        (and (odd? l) (>= lv rv)) (concat ls [mp] lr)
                        (even? l) (concat ls1 (reverse ls1))
                        (and (odd? l) (< mp 9)) (concat ls [(inc mp)] lr)
                        :else (concat ls1 [0] (reverse ls1))))))]
    (iterate #((comp next-num inc) %) (next-num n))))

(defn pa [n]
  (letfn [(decode [n] (if (< n 10) [n] (conj (decode (quot n 10)) (rem n 10))))
          (encode [x] (reduce #(+ (* % 10) %2) 0 x))
          (next-pal [n]
            (let [N (decode n)
                  l (count N)
                  d (quot l 2)
                  H (take d N)
                  H1 (decode (inc (encode H)))
                  Hr (reverse H)
                  h (encode Hr)
                  p (nth N d)
                  t (encode (take-last d N))]
              (encode (cond
                        (and (even? l) (>= h t)) (concat H Hr)
                        (and (odd? l) (>= h t)) (concat H [p] Hr)
                        (even? l) (concat H1 (reverse H1))
                        (and (odd? l) (< p 9)) (concat H [(inc p)] Hr)
                        :else (concat H1 [0] (reverse H1))))))]
    (iterate (comp next-pal inc) (next-pal n))))

(defn back-q [[num dig]]
  (loop [a num, r (if (= dig :even) num (quot num 10))]
    (if (= 0 r)
      a
      (recur (+ (* a 10) (mod r 10)), (quot r 10)))))

;; 152 Latin Square Slicing



;;153 pairwise disjoint sets
(defn pair-dis-sets
  [sets]
  (= (count (reduce #(into %1 %2) #{} sets))
     (reduce #(+ %1 (count %2)) 0 sets)))

;;156
(defn map-default [v coll]
  (zipmap coll (repeat (count coll) v)))

;;157 indexing sequences
(defn index-seq [coll]
  (loop [r []
         coll coll
         i 0]
    (if (empty? coll)
      r
      (recur (conj r [(first coll) i]) (rest coll) (inc i)))))

;;158 decurry 还没有理解 todo
(defn decurry
  [f]
  (fn [& args]
    (reduce #(%1 %2) f args)))

;;166
(fn [op a b]
  (cond
    (op a b) :lt
    (op b a) :gt
    :else :eq))

;; 168 Infinite Matrix
(defn infi-mat
  ([f]
   (letfn [(inf-seq
             ([]
               (concat [] (inf-seq 0N)))
             ([n]
               (lazy-seq (cons n (inf-seq (inc n))))))
           (sub-seq [f]
             (map (fn [n] (map #(f n %) (inf-seq))) (inf-seq)))]
     (sub-seq f)))
  ([f m n]
    (letfn [(drop- [n coll]
              (let [step (fn [n coll]
                           (let [s (seq coll)]
                             (if (and (pos? n) s)
                               (recur (dec n) (rest coll))
                               s)))]
                (lazy-seq (step n coll))))]
      (map #(drop- n %) (drop- m (infi-mat f)))))
  ([f m n s t]
    (take s (map #(take t %) (infi-mat f m n)))))



;;171 intervals
(defn intervals
  [coll]
  (let [coll (sort coll)
        c (first coll)
        l (last coll)]
    (if (empty? coll)
      coll
      (partition 2 (concat [c]
                          (flatten
                            (filter #(> (- (second %) (first %)) 1)
                                    (partition 2 1 coll)))
                          [l])))))

(def c [19 4 17 1 3 10 2 13 13 2 16 4 2 15 13 9 6 14 2 11])

;;173 intro to destructuring 2
(defn intro-to-dest [])


(def s "class test {
      public static void main(string[] args) {
        system.out.println(\"hello world.\");
      }
    }")

;(def sn "([]([(()){()}(()(()))(([[]]({}()))())]((((()()))))))")

;;177 balancing brackets
(defn balancing-brackets
  [s]
  (let [coll (seq (apply str (re-seq #"\(|\{|\[|\]|\}|\)" s)))]
    (empty? (reduce (fn [stack s]
                      (let [fv (peek stack)]
                        (cond
                          (nil? fv) (conj stack s)
                          (and (= fv \{) (= s \})) (pop stack)
                          (and (= fv \[) (= s \])) (pop stack)
                          (and (= fv \() (= s \))) (pop stack)
                          :else
                          (conj stack s))))
                    []
                    coll))))