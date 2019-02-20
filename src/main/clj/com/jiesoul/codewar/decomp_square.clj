(ns com.jiesoul.codewar.decomp-square)

(defn decompose [n]
  (let [sq (* n n)
        ll (map #(* % %) (range 1 (Math/sqrt sq)))]
    (loop [l1 ll
           l2 (rest ll)
           l3 (rest l2)
           r []]
      (println r)
      (if (empty? l1)
        nil
        (let [v1 (first l1)
              sum (reduce + 0 r)
              d (- sq sum)]
          (cond
            (< d v1) nil
            (= d v1) (conj r v1)
            (empty? l2) (recur (rest l1) (rest (rest l1)) (rest (rest (rest l1))) [])
            :else
            (let [v2 (first l2)
                  sum1 (reduce + r)
                  d1 (- sq -sum1)]
              (cond
                (> d1 v2) nil
                (= d1 v2) (conj r v2)
                (empty? l3) (recur l1 (rest l2) (rest (rest l2)) r)
                :else
                (let [v3 (first l3)
                      sum (reduce + r)]
                  (cond
                    (> sum sq) nil
                    (= sum sq) r
                    :else
                    (recur l1 l2 (rest l3) r)))))
            ))
        ))))


(decompose 11)