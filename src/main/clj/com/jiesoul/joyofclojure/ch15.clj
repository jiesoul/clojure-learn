(ns com.jiesoul.joyofclojure.ch15)

(def gcd (memoize
           (fn [x y]
             (cond
               (> x y) (recur (- x y) y)
               (< x y) (recur x (- y x))
               :else x))))
(gcd 10006455475 56130776629010010)

(defprotocol CacheProtocol
  (lookup [cache e])
  (has? [cache e])
  (hit [cache e])
  (miss [cache e ret]))

(deftype BasicCache [cache]
  CacheProtocol
  (lookup [_ item]
    (get cache item))
  (has? [_ item]
    (contains? cache item))
  (hit [this item] this)
  (miss [_ item result]
    (BasicCache. (assoc cache item result))))

(def cache (BasicCache. {}))
(lookup (miss cache '(servo) :robot) '(servo))

(defn throuth [cache f item]
  (if (has? cache item)
    (hit cache item)
    (miss cache item (delay (apply f item)))))

(deftype PluggableMemoization [f cache]
  CacheProtocol
  (has? [_ item] (has? cache item))
  (hit [this item] this)
  (miss [_ item result]
    (PluggableMemoization. f (miss cache item result)))
  (lookup [_ item]
    (lookup cache item)))

(defn memoization-impl [cache-impl]
  (let [cache (atom cache-impl)]
    (with-meta
      (fn [& args]
        (let [cs (swap! cache throuth (.f cache-impl) args)]
          @(lookup cs args)))
      {:cache cache})))

(def slowly (fn [x] (Thread/sleep 3000) x))
(def sometimes-slowly (memoization-impl
                        (PluggableMemoization.
                          slowly
                          (BasicCache. {}))))
(time [(sometimes-slowly 108) (sometimes-slowly 108)])
(time [(sometimes-slowly 108) (sometimes-slowly 108)])

(defn factorial-a [original-x]
  (loop [x   original-x
         acc 1]
    (if (>= 1 x)
      acc
      (recur (dec x) (* x acc)))))
(factorial-a 10)
(factorial-a 20)
(time (dotimes [_ 1e5] (factorial-a 20)))

(defn factorial-b [original-x]
  (loop [x   (long original-x)
         acc 1]
    (if (>= 1 x)
      acc
      (recur (dec x) (* x acc)))))
(time (dotimes [_ 1e5] (factorial-b 20)))

(defn factorial-c [^long original-x]
  (loop [x   original-x
         acc 1]
    (if (>= 1 x)
      acc
      (recur (dec x) (* x acc)))))
(time (dotimes [_ 1e5] (factorial-b 20)))

(set! *unchecked-math* true)

(defn factorial-d [^long original-x]
  (loop [x   original-x
         acc 1]
    (if (>= 1 x)
      acc
      (recur (dec x) (* x acc)))))
(set! *unchecked-math* false)
(time (dotimes [_ 1e5] (factorial-d 20)))

(defn factorial-e [^double original-x]
  (loop [x   original-x
         acc 1.0]
    (if (>= 1.0 x)
      acc
      (recur (dec x) (* x acc)))))
(factorial-e 10.0)
(factorial-e 20.0)
(factorial-e 30.0)

(factorial-e 171.0)
(time (dotimes [_ 1e5] (factorial-e 20.0)))

(defn factorial-f [^long original-x]
  (loop [x   original-x
         acc 1]
    (if (>= 1 x)
      acc
      (recur (dec x) (*' x acc)))))
(factorial-f 20)
(factorial-f 30)
(factorial-f 171)
(time (dotimes [_ 1e5] (factorial-f 20)))

(defn empty-range? [start end step]
  (or (and (pos? step) (>= start end))
      (and (neg? step) (<= start end))))

(defn lazy-range [i end step]
  (lazy-seq
    (if (empty-range? i end step)
      nil
      (cons i
            (lazy-range (+ i step)
                        end
                        step)))))
(lazy-range 5 10 2)
(lazy-range 6 0 -1)
(reduce conj [] (lazy-range 6 0 -1))
(reduce + 0 (lazy-range 6 0 -1))

(defn reducible-range [start end step]
  (fn [reducing-fn init]
    (loop [result init
           i      start]
      (if (empty-range? i end step)
        result
        (recur (reducing-fn result i)
               (+ i step))))))

(defn half [x]
  (/ x 2))
(half 4)
(half 7)
(defn sum-half [result input]
  (+ result (half input)))
(reduce sum-half 0 (lazy-range 0 10 2))
((reducible-range 0 10 2) sum-half 0)

(defn half-transformer [f1]
  (fn f1-half [result input]
    (f1 result (half input))))

((reducible-range 0 10 2) (half-transformer +) 0)
((reducible-range 0 10 2) (half-transformer conj) [])

(defn mapping [map-fn]
  (fn map-transformer [f1]
    (fn [result input]
      (f1 result (map-fn input)))))
((reducible-range 0 10 2) ((mapping half) +) 0)
((reducible-range 0 10 2) ((mapping half) conj) [])
((reducible-range 0 10 2) ((mapping list) conj) [])

(defn filtering [filter-pred]
  (fn [f1]
    (fn [result input]
      (if (filter-pred input)
        (f1 result input)
        result))))

((reducible-range 0 10 2) ((filtering #(not= % 2)) +) 0)
((reducible-range 0 10 2) ((filtering #(not= % 2)) conj) [])
((reducible-range 0 10 2) ((filtering #(not= % 2)) ((mapping half) conj)) [])

(defn mapcating [map-fn]
  (fn [f1]
    (fn [result input]
      (let [reducible (map-fn input)]
        (reducible f1 result)))))
(defn and-plug-ten [x]
  (reducible-range x (+ 11 x) 10))
((and-plug-ten 5) conj [])
((reducible-range 0 10 2) ((mapcating and-plug-ten) conj) [])

(defn r-map [mapping-fn reducible]
  (fn new-reducible [reducing-fn init]
    (reducible (mapping mapping-fn) reducing-fn) init))

(defn r-filter [filter-pred reducible]
  (fn new-reducible [reducing-fn init]
    (reducible ((filtering filter-pred) reducing-fn) init)))

(def our-final-reducible
  (r-filter #(not= % 2)
            (r-map half
                   (reducible-range 0 10 2))))
(our-final-reducible conj [])
