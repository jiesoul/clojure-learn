(ns com.jiesoul.codewar.scramblies)

;;Scramblies
(defn scramble
  [s1 s2]
  (letfn [(group-iden [coll]
            (reduce (fn [m [k v]] (assoc m k (count v)))
                    {}
                    (group-by identity (seq coll))))]
    (let [s2 (group-iden s2)
          s1 (reduce (fn [m [k v]]
                       (if (>= v (s2 k))
                         (assoc m k v)))
                     {}
                     (select-keys (group-iden s1) (keys s2)))]
      (= (count s1) (count s2)))))


(defn scramble-
  [s1 s2]
  (let [f1 (frequencies s1)
        f2 (frequencies s2)]
    (every? (fn [[k v]] (<= v (get f1 k 0))) f2)))