(ns joyofclojure.ch05
  (:require clojure.set))

(def a-to-j (vec (map char (range 65 75))))

(seq a-to-j-)
(rseq a-to-j)
(assoc a-to-j 4 "sss")

(defn neighors
  ([size yx] (neighors [[-1 0] [1 0] [0 -1] [0 1]]
                       size
                       yx))
  ([deltals size yx]
   (filter (fn [new-yx]
             (every? #(< -1 % size) new-yx))
           (map #(vec (map + yx %)) deltals))))

(defn strict-map1 [f coll]
        (loop [coll coll
               acc nil]
          (if (empty? coll)
            (reverse acc)
            (recur (next coll)
                   (cons (f (first coll)) acc)))))

(strict-map1 - (range 5))

(defn strict-map2 [f coll]
  (loop [coll coll
         acc []]
    (if (empty? coll)
      acc
      (recur (next coll)
             (conj acc (f (first coll)))))))
(strict-map2 - (range 5))

(defmethod print-method clojure.lang.PersistentQueue
  [q w]
  (print-method '<- w)
  (print-method (seq q) w)
  (print-method '-< w))

(clojure.lang.PersistentQueue/EMPTY)

(def schedule
  (conj clojure.lang.PersistentQueue/EMPTY
        :wake-up :shower :brush-teeth))
(println schedule)

(#{:a :b :c :d} :c)

(into #{[]} [])
(into #{[1 2]} `[(1 2)])
(into #{[] #{} {}} [()])

(sorted-set :a :c :b)
(sorted-set [3 4] [1 2])
(sorted-set :b 2 :c :a 3 1) ;; 导致异常

(clojure.set/intersection #{:humas :fruit-bats :zombies}
                          #{:chupacabra :zombies :humas})

(clojure.set/intersection #{:pez :gum :dots :skor}
                          #{:pez :skor :pocky}
                          #{:pocky :gum :skor})

(clojure.set/union #{:humas :fruit-bats :zombies}
                          #{:chupacabra :zombies :humas})

(clojure.set/union #{:pez :gum :dots :skor}
                          #{:pez :skor :pocky}
                          #{:pocky :gum :skor})

(clojure.set/difference #{1 2 3 4} #{3 4 5 6})

(hash-map :a 1 :b 2 :c 3 :d 4)
(seq {:a 1 :b 2})

(into {} [[:a 1] [:b 2]])
(into {} (map vec '[(:a 1) (:b 2)]))
(apply hash-map [:a 1 :b 2])

(sorted-map :thx 1138 :r2d 2)
(sorted-map-by #(compare (subs %1 1) (subs %2 1)) "bac" 2 "abc" 9)

(assoc {1 :int} 1.0 :float)
(assoc (sorted-map 1 :int) 1.0 :float)

(seq (hash-map :a 1 :b 2 :c 3))
(seq (array-map :a 1 :b 2 :c 3))

(defn pos [e coll]
  (let [cmp (if (map? coll)
               #(= (second %1) %2)
               #(= %1 %2))]
    (loop [s coll idx 0]
      (when (seq s)
        (if (cmp (first s) e)
          (if (map? coll)
            (first (first s))
            idx)
          (recur (next s) (inc idx)))))))

(pos 3 [:a 1 :b 2 :c 3 :d 4])

(defn index [coll]
  (cond
    (map? coll) (seq coll)
    (set? coll) (map vector coll coll)
    :else (map vector (iterate inc 0) coll)))

(defn pos [e coll]
  (for [[i v] (index coll)
        :when (= e v)]
    i))

(defn pos [pred coll]
  (for [[i v] (index coll)
        :when (pred v)]
    i))
