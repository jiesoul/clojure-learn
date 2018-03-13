(defn smallest [n]
  (let [s (map (comp #(Integer/parseInt %) str) (seq (str n)))
        v (first s)
        min (apply min (filter #(not= % v) (rest s)))
        sm (zipmap (take (count s) (iterate inc 0)) s)
        maxi (apply max (map first (filter #(= (val %) min) (rest sm))))
        rvals (vals (select-keys sm (filter #(not= % maxi) (keys (rest sm)))))]
    (if (> min v)
      [(concat [v min] rvals) maxi 1]
      (if (zero? min)
        [(concat [min v] rvals) maxi 0]
        [(concat [min v] rvals) maxi 0]))))

(smallest 21981)
(smallest 261235)
(smallest 2009017)
(smallest 269045)
(smallest 1000000)

209017
29017
20917

232345

293837
239837
239387

1000010