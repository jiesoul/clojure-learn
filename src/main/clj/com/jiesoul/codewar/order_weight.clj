(ns com.jiesoul.codewar.order-weight)

;;Weight for weight
(defn order-weight
  [strng]
  (letfn [(sum-of-str
            [s]
            (reduce + (map (comp #(Long/parseLong %) str) (seq s))))]
    (let [first-sort (sort-by #(sum-of-str %) (clojure.string/split strng #" "))
          group-coll (partition-by #(sum-of-str %) first-sort)
          result-seq (reduce concat [] (map #(sort-by str %) group-coll))
          fe (first result-seq)
          coll (rest result-seq)]
      (reduce #(apply str % " " %2) (str fe) coll))))
