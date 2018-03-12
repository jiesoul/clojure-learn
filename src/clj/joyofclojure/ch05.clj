(ns joyofclojure.ch05)

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
