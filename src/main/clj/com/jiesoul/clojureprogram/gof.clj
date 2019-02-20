(ns com.jiesoul.clojureprogram.gof)

(defn empty-board
  "create empty board"
  [w h]
  (vec (repeat w (vec (repeat h nil)))))

(defn populate
  [board living-cells]
  (reduce (fn [board coodrdinates]
            (assoc-in board coodrdinates :on))
          board
          living-cells))

(def glider
  (populate (empty-board 6 6) #{[2 0] [2 1] [2 2] [1 2] [0 1]}))

(defn neighours
  [[x y]]
  (for [dx [-1 0 1]
        dy [-1 0 1]
        :when (not= 0 dx dy)]
    [(+ dx x) (+ dy y)]))

(defn count-neighours
  [board loc]
  (count (filter #(get-in board %) (neighours loc))))

(defn index-step
  [board]
  (let [w (count board)
        h (count (first board))]
    (loop [new-board board
           x 0
           y 0]
      (cond
        (>= x w) new-board
        (>= y h) (recur new-board (inc x) 0)
        :else
        (let [new-liveness
              (case (count-neighours board [x y])
                2 (get-in board [x y])
                3 :on
                nil)]
          (recur (assoc-in new-board [x y] new-liveness) x (inc y)))))))
