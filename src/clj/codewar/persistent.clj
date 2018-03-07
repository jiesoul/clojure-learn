(ns codewar.persistent)

(defn persistence [n]
  (letfn [(step [r n]
            (if (< n 10)
              r
              (let [n (reduce *' (map (comp #(Integer/parseInt %) str) (seq (str n))))]
                (recur (inc r) n))))]
    (step 0 n)))