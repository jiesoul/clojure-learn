(ns codewar.binary-sxore)

(defn sxore [n]
  (let [rv (rem n 4)]
    (case rv
      0 n
      1 1
      2 (inc n)
      3 0)))