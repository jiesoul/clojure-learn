(ns joyofclojure.ch07)

(def fifth (comp first rest rest rest rest))

(fifth [1 2 3 4 5])

(defn fnth [n]
  (apply comp (cons first (take (dec n) (repeat rest)))))

((fnth 5) '[a b c d e])

(map (comp keyword #(.toLowerCase %) name) '(a b c))

((partial + 5) 100 200)

(let [truthiness (fn [v] v)]
  [((complement truthiness) true)
   ((complement truthiness) 42)
   ((complement truthiness) false)
   ((complement truthiness) nil)])

(defn join
  {:test (fn []
           (assert
             (= (join "," [1 2 3]) "1,2,3")))}
  [seq s]
  (apply str (interpose seq s)))

(def times-two
  (let [x 2]
    (fn [y] (* x y))))

(times-two 5)

(def add-and-get
  (let [ai (java.util.concurrent.atomic.AtomicInteger.)]
    (fn [y] (.addAndGet ai y))))

(add-and-get 7)

(defn keys-apply [f ks m]
  (let [only (select-keys m ks)]
    (zipmap (keys only)
            (map f (vals only)))))

(defn slope [p1 p2]
  {:pre [(not= p1 p2) (vector? p1) (vector? p2)]
   :post [(float? %)]}
  (/ (- (p2 1) (p1 1))
     (- (p2 0) (p1 0))))

(slope [10 10] [10 10])
(slope [10 1] '(10 10))
(slope [10 1] [1 20])
(slope [10.0 1] [1 20])

(defn put-things [m]
  (into m {:meat "beef" :veggie "broccoil"}))

(put-things {})
(defn vegan-constraints [f m]
  {:pre [(:viggie m)]
   :post [(:viggie %) (nil? (:meat %))]}
  (f m))
(vegan-constraints put-things {:viggie "carrot"})

(def times-two
  (let [x 2]
    (fn [y] (* x y))))

(times-two 5)

(def add-and-get
  (let [ai (java.util.concurrent.atomic.AtomicInteger.)]
    (fn [y] (.addAndGet ai y))))
(add-and-get 2)
(add-and-get 7)

(defn times-n [n]
  (let [x n]
    (fn [y] (* x y))))
(times-n 2)
(def times-four (times-n 4))
(times-four 10)

(defn divisible [denom]
  (fn [num]
    (zero? (rem num denom))))

((divisible 3) 6)
((divisible 4) 3)

(filter even? (range 10))
(filter (divisible 4) (range 10))
(defn filter-divisible [denom s]
  (filter (fn [num] (zero? (rem num denom))) s))
(filter-divisible 4 (range 10))
(defn filter-divisible [denom s]
  (filter #(zero? (rem % denom)) s))
(filter-divisible 5 (range 10))

(defn pow [base exp]
  (if (zero? exp)
    1
    (* base (pow base (dec exp)))))
(pow 2 10)
(pow 1.01 925)
(pow 2 100000)

(defn pow [base exp]
  (letfn [(kapow [base exp acc]
            (if (zero? exp)
              acc
              (recur base (dec exp) (* base acc))))]
    (kapow base exp 1)))
(pow 2N 10000)

(def simple-metric {:meter 1
                    :km 1000
                    :cm 1/100
                    :mm [1/10 :cm]})

(defn convert [context descriptor]
  (reduce (fn [result [mag unit]]
            (+ result
               (let [val [get context unit]]
                 (if (vector? val)
                   (* mag (convert context val))
                   (* mag val)))))
          0
          (partition 2 descriptor)))

(convert simple-metric [1 :meter])

(defn mk-cps [accept? kend kont]
  (fn [n]
    ((fn [n k]
       (let [cont (fn [v]
                    (k ((partial kont v) n)))]
         (if (accept? n)
           (k 1)
           (recur (dec n) cont))))
     n kend)))

(def fac
  (mk-cps zero?
          identity
          #(* %1 %2)))
(fac 10)

(def tri
  (mk-cps #(== 1 %)
          identity
          #(+ %1 %2)))
(tri 10)

(def world [[ 1 1 1 1 1]
            [999 999 999 999 1]
            [ 1 1 1 1 1]
            [ 1 999 999 999 999]
            [ 1 1 1 1 1]])


