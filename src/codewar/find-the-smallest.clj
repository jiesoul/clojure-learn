(defn smallest [n]
  (let [s (map (comp #(Integer/parseInt %) str) (seq (str n)))
        fv (first s)
        mv (apply min (rest s))]
    (if (fv ))))

(smallest 261235)