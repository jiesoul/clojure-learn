(ns com.jiesoul.joyofclojure.q)

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
                 (filter smaller? xs)
                 parts)))
       (when-let [[x & parts] parts]
         (cons x (sort-parts parts)))))))
