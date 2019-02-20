(ns com.jiesoul.codewar.PowerSetCounting)


(defn power-set
  [sets]
  (if (empty? sets)
    #{#{}}
    (let [v (first sets)
          ps (power-set (rest sets))]
      (set (concat (map #(conj % v) ps) ps)))))

(defn powers [list]
  (let [c (count list)]
    (reduce *' (repeat c 2))))