(ns codewar.granny)

(def friends  ["A1", "A2", "A3", "A4", "A5"])
(def fTowns 	[["A1", "X1"], ["A2", "X2"], ["A3", "X3"], ["A4", "X4"]]) 
(def distTble [["X1", 100.0], ["X2", 200.0], ["X3", 250.0], ["X4", 300.0]])

(defn step [a coll]
  (let [xs (filter #(= (first %) a) coll)]
    (when (not-empty xs)
      (second (first xs)))))

(defn tour [frnds frTwns disTable]
  (let [g (fn [[a b]] (Math/sqrt (- (* b b) (* a a))))
        xs (filter (complement nil?) (map #(step % frTwns) frnds))
        ys (filter (complement nil?) (map #(step % disTable) xs))
        zs (map #(g %) (partition 2 1 ys))]
    (int (reduce + (+ (first ys) (last ys)) zs))))

(step "A1" fTowns)
(tour friends fTowns distTble)
