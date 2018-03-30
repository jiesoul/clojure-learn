(ns codewar.closest)

(def s "103 123 4444 99 2000")

(defn weight [n]
  (loop [n n
         r 0]
    (if (zero? n)
      r
      (recur (quot n 10) (+ r (rem n 10))))))

(defn change [s]
  (map (fn [a b]
         (let [v (Integer/parseInt a)]
           (conj [] (weight v) b v)))
       (clojure.string/split s #" ")
       (range 0 Long/MAX_VALUE)))

(defn find-min [coll]
  (let [xs (map #(conj [] (Math/abs (- (second %) (first %))) %)
             (partition 2 1 (sort < coll)))
        minv (apply min (map first xs))]
    (second (first (filter #(= minv (first %)) xs)))))

(defn select [[a b] fun coll]
  (filter #(or (= (fun %) a) (= (fun %) b)) coll))

(defn closest [strng]
  (if (= strng "")
    (list [] [])
    (let [xs  (change strng)
          fir (find-min (map first xs))]
      (if (= (first fir) (second fir))
        (let [xxs (filter #(= (first %) (first fir)) xs)
              sec (take 2 (sort < (map second xxs)))]
          (sort-by second (select sec second xxs)))
        (sort-by first (select fir first xs))))))

(closest "")
(closest s)
(closest "444 2000 445 544")
(closest (str "239382 162 254765 182 485944 468751 49780 108 54"))
(closest "54 239382 162 254765 182 485944 468751 49780 108")