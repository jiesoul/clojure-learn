(ns com.jiesoul.joyofclojure.ch05
  (:require clojure.set))

(def ds (into-array [:willie :barnabas :adam]))
(seq ds)
(aset ds 1 :quentin)
(seq ds)

(def ds [:willie :barnabas :adam])
(def ds1 (replace {:barnabas :quentin} ds))
(seq ds1)
(seq ds)

(= [1 2 3] '(1 2 3))
(= [1 2 3] #{1 2 3})

(class (hash-map :a 1))
(seq (hash-map :a 1))
(class (seq (hash-map :a 1)))
(seq (keys (hash-map :a 1)))
(class (keys (hash-map :a 1)))

(vec (range 10))
(let [my-vector [:a :b :c]]
  (into my-vector (range 10)))

(into (vector-of :int) [Math/PI 2 1.3])
(into (vector-of :char) [100 101 102])
;(into (vector-of :int) [1 2 1234567890000])

(def a-to-j (vec (map char (range 65 75))))

(println a-to-j)
(seq a-to-j)
(rseq a-to-j)
(nth a-to-j 4)
(get a-to-j 4)
(a-to-j 4)
(assoc a-to-j 4 "sss")

(replace {2 :a, 4 :b} [1 2 3 2 3 4])
(def martix
  [[1 2 3]
   [4 5 6]
   [7 8 9]])
(get-in martix [1 2])
(assoc-in martix [1 2] 'x)
(update-in martix [1 2] * 100)


(defn neighbors
  ([size yx] (neighbors [[-1 0] [1 0] [0 -1] [0 1]]
                       size
                       yx))
  ([deltals size yx]
   (filter (fn [new-yx]
             (every? #(< -1 % size) new-yx))
           (map #(vec (map + yx %)) deltals))))

(neighbors 3 [0 0])
(neighbors 3 [1 1])
(map #(get-in martix %) (neighbors 3 [0 0]))

(def my-stack [1 2 3])
(peek my-stack)
(pop my-stack)
(conj my-stack 4)
(+ (peek my-stack) (peek (pop my-stack)))

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

(subvec a-to-j 3 6)

(first {:width 10, :height 20, :depth 15})
(vector? (first {:width 10, :height 20, :depth 15}))

(doseq [[dimension amuout] {:width 10, :height 20, :depth 15}]
  (println (str (name dimension) ":") amuout "inches"))

(cons 1 '(2 3))
(conj '(2 3) 1)

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
(peek schedule)
(pop schedule)
(rest schedule)

(#{:a :b :c :d} :c)
(#{:a :b :c :d} :e)
(get #{:a 1 :b 2} :b)
(get #{:a 1 :b 2} :z :nothing-doing)

(into #{[]} [])
(into #{[1 2]} `[(1 2)])
(into #{[] #{} {}} [()])

(sorted-set :a :c :b)
(sorted-set [3 4] [1 2])
;(sorted-set :b 2 :c :a 3 1) ;; 导致异常

(def my-set (sorted-set :a :b))
;(conj my-set "a")

(contains? #{1 2 3 4 5} 4)
(contains? [1 2 4 3] 4)

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
(let [m {:a 1, 1 :b, [1 2 3] "4 5 6"}]
  [(get m :a) (get m [1 2 3])])
(seq {:a 1 :b 2})

(into {} [[:a 1] [:b 2]])
(into {} (map vec '[(:a 1) (:b 2)]))
(apply hash-map [:a 1 :b 2])
(zipmap [:a :b] [1 2])

(sorted-map :thx 1138 :r2d 2)
(sorted-map "bac" 2 "abc" 9)
(sorted-map-by #(compare (subs %1 1) (subs %2 1)) "bac" 2 "abc" 9)

;(sorted-map :a 1, "b" 2)

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
