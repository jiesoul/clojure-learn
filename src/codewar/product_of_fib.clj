(ns codewar.product-of-fib)

(defn fib []
  (iterate (fn [[a b i]]
             [b (+ a b) (inc i)]) [0N 1N 0N]))

(defn product-fib [prod]
  (let [v (first (drop-while #(< (*' (first %) (second %)) prod) (fib)))
        fv (first v)
        sv (second v)]
    (conj [] fv sv (if (= prod (*' fv sv)) true false))))
