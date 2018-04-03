(ns codewar.pascal)

(defn pascal [n]
  (take n (iterate (fn [a] (mapv #(+ %1 %2) (conj a 0) (cons 0 a))) [1])))

(pascal 5)