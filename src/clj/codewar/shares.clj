(ns codewar.shares)

(defn share-price [invested changes]
  (reduce *' invested (map #(+ 1 %) changes)))