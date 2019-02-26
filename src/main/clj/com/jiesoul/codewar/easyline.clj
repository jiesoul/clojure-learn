(ns com.jiesoul.codewar.easyline)

(defn easy-line [n]
  (reduce #(+' %1 (*' %2 %2)) 0N
    (first
      (drop
        n
        (iterate
          (fn [c]
            (vec (map #(+ %1 %2) (conj c 0N) (cons 0N c))))
          [1N])))))

(easy-line 0)
(easy-line 1)
(easy-line 2)
(easy-line 3)
(easy-line 4)
(easy-line 5)
(easy-line 50)