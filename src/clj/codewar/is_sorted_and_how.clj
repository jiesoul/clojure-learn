(ns codewar.is-sorted-and-how)

(defn sorted-and-how? [sq]
  (let [coll (->> sq
                 (partition 2 1)
                 (map #(- (first %) (second %))))]
    (cond
      (every? #(> % 0) coll) (str "yes, descending")
      (every? #(< % 0) coll) (str "yes, ascending")
      :else (str "no"))))

(sorted-and-how? [1 2])