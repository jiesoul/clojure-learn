(ns com.jiesoul.codewar.nthrank)

(defn rank [st we n]
  (if (empty? st)
    "No participants"
    (let [f    (fn [c]
                 (let [l (count c)]
                   (->> c
                     (clojure.string/upper-case)
                     (seq)
                     (map #(inc (- (int %) (int \A))))
                     (reduce + l))))
          coll (map #(conj [] % (f %))
                 (clojure.string/split st #","))
          coll (map (fn [[fi se] w]
                      [fi (* se w)]) coll we)
          coll (sort-by first coll)
          coll (sort-by second > coll)]
      (if (> n (count coll))
        "Not enough participants"
        (first (nth coll (dec n)))))))

(rank "Addison,Jayden,Sofia,Michael,Andrew,Lily,Benjamin"
  [4, 2, 1, 4, 3, 1, 2]
  4)
(rank "Elijah,Chloe,Matthew,Elizabeth,Natalie,Jayden", [1, 3, 5, 5, 3, 6], 2)