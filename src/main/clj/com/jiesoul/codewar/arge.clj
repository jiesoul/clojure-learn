(ns com.jiesoul.codewar.arge)

(defn nb-year
  [p0 percent aug p]
  (let [f (fn [p0 percent aug]
            (int (+ p0 (* p0 (/ percent 100)) aug)))]
    (loop [p0 p0
           n 0]
      (if (>= p0 p)
        n
        (recur (f p0 percent aug) (inc n))))))

(defn nb-year1
  [p0 percent aug p]
  (count
    (take-while #(< % p)
      (iterate #(int (+ % (* % (/ percent 100.0)) aug)) p0))))
