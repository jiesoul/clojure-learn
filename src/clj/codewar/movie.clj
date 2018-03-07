(ns codewar.movie)

(defn movie [card ticket perc]
  (loop [n 1
         b (+ card (* ticket perc))]
    (let [a (* n ticket)]
      (if (> a (Math/ceil b))
        n
        (recur (inc n) (+ b (* ticket (Math/pow perc (inc n)))))))))