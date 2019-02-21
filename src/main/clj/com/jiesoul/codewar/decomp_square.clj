(ns com.jiesoul.codewar.decomp-square)

(defn find [coll target]
  )

(defn decompose [n]
  (let [sq (* n n)
        ll (map #(* % %) (range 1 (Math/sqrt sq)))]
    (loop [l1 ll
           l2 (rest ll)
           l3 (rest l2)
           r []]
      (if (empty? l1)
        nil
        (recur [] (rest l2) (rest l3) r)
        ))))


(decompose 11)