(ns com.jiesoul.joyofclojure.ch06)

(def baselist (list :barnabas :adam))
(def lst1 (cons :willie baselist))
(def lst2 (cons :phoenix baselist))

(println lst1)
(println lst2)

(= (next lst1) (next lst2))
(identical? (next lst1) (next lst2))

(defn xconj [t v]
  (cond
    (nil? t) {:val v :L nil :R nil}
    (< v (:val t)) {:val (:val t)
                    :L (xconj (:L t) v)
                    :R (:R t)}
    :else {:val (:val t),
           :L (:L t),
           :R (xconj (:R t) v)}))

(xconj nil 5)
(def tree1 (xconj nil 5))
(println tree1)

(def tree2 (xconj tree1 3))
(println tree2)

(def tree3 (xconj tree2 2))

(defn xseq [t]
  (when t
    (concat (xseq (:L t)) [(:val t)] (xseq (:R t)))))
(xseq tree3)

(def tree4 (xconj tree3 7))
(xseq tree4)
(identical? (:L tree3) (:L tree4))

(defn if-chain [x y z]
  (if x
    (if y
      (if z
        (do
          (println "Made it!")
          :all-truthy)))))

(if-chain () 42 true)
(if-chain true true false)

(defn and-chain [x y z]
  (and x y z (do (println "Made it!") :all-truthy)))

(and-chain () 42 true)
(and-chain true false true)



(defn rec-step [[x & xs]]
  (if x
    [x (rec-step xs)]
    []))

(rec-step [1 2 3 4])
;(rec-step (range 20000))

(defn lz-rec-step [s]
  (lazy-seq
    (if (seq s)
      [(first s) (lz-rec-step (rest s))]
      [])))

(class (lz-rec-step [1 2 3 4]))
(dorun (lz-rec-step (range 200000)))

(def very-lazy (-> (iterate #(do (print \.) (inc %)) 1)
                   rest rest rest))

(def less-lazy (-> (iterate #(do (print \.) (inc %)) 1)
                   next next next))

(println (first very-lazy))
(println (first less-lazy))

(defn simple-range [i limit]
  (lazy-seq
   (when (< i limit)
     (cons i (simple-range (inc i) limit)))))

(simple-range 0 9)

;(let [r (range 1e9)]
;  (first r)
;  (last r))
;
;(let [r (range 1e9)]
;  (last r)
;  (first r))

;(iterate (fn [n] (/ n 2)) 1)

(defn triangle [n]
  (/ (* n (+ n 1)) 2))

(triangle 10)

(map triangle (range 1 11))

(def tri-nums (map triangle (iterate inc 1)))
(take 10 tri-nums)
(take 10 (filter even? tri-nums))
(nth tri-nums 99)
(double (reduce + (take 1000 (map / tri-nums))))
(take 2 (drop-while #(< % 10000) tri-nums))

(defn defer-expensive [cheap expensive]
  (if-let [good-enough (force cheap)]
    good-enough
    (force expensive)))
(defer-expensive (delay :cheap)
                 (delay (do (Thread/sleep 5000) :expensive)))
(defer-expensive (delay false)
                 (delay (do (Thread/sleep 5000) :expensive)))

(defn inf-triangles [n]
  {:head (triangle n)
   :tail (delay (inf-triangles (inc n)))})

(defn head [l] (:head l))
(defn tail [l] (force (:tail l)))

(def tri-nums1 (inf-triangles 1))
(head tri-nums1)
(head (tail tri-nums1))
(head (tail (tail tri-nums1)))

(defn taker [n l]
  (loop [t n, src l, ret []]
    (if (zero? t)
      ret
      (recur (dec t) (tail src) (conj ret (head src))))))

(defn nthr [l n]
  (if (zero? n)
    (head l)
    (recur (tail l) (dec n))))

(taker 10 tri-nums1)
(nthr tri-nums1 99)

(defn rand-ints [n]
  (take n (repeatedly #(rand-int n))))

(rand-ints 10)

(defn sort-parts [work]
  (lazy-seq
    (loop [[part & parts] work]
      (if-let [[pivot & xs] (seq part)]
        (let [smaller? #(< % pivot)]
          (recur (list*
                   (filter smaller? xs)
                   pivot
                   (remove smaller? xs)
                   parts)))
        (when-let [[x & parts] parts]
          (cons x (sort-parts parts)))))))

(defn qsort [xs]
  (sort-parts (list xs)))
(qsort (rand-ints 20))
(qsort [2 1 4 3])
(take 10 (qsort (rand-ints 10000)))