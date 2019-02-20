(ns com.jiesoul.codewar.decomp-square)

(defn decompose [n]
  (let [sq (* n n)
        lst (reverse (map #(* % %) (range 1 (Math/sqrt sq))))]
    (loop [l lst
           ls lst
           r []]
      (let [sum (reduce + 0 r)]
        (cond
           (empty? l) nil
           (empty? ls) (recur (rest l) (rest l) [])
           (= sum sq) r
           (> sum sq) (recur (rest l) (rest l) [])
           (< sum sq) (recur l
                             (filter #(> (- sq sum) %) ls)
                             (conj r (first ls))))))))

(decompose 11)