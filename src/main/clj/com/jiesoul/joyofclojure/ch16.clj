(ns com.jiesoul.joyofclojure.ch16
  (:require [clojure.set :as set]
            [com.jiesoul.joyofclojure.ch05 :refer [pos]]
            [clojure.walk :as walk]))

(def b1 '[3 - - - - 5 - 1 -
          - 7 - - - 6 - 3 -
          1 - - - 9 - - - -
          7 - 8 - - - - 9 -
          9 - - 4 - 8 - - 2
          - 6 - - - - 5 - 1
          - - - - 4 - - - 6
          - 4 - 7 - - - 2 -
          - 2 - 6 - - - - 3])

(defn prep [board]
  (map #(partition 3 %)
       (partition 9 board)))

(defn print-board [board]
  (let [row-sep (apply str (repeat 37 "-"))]
    (println row-sep)
    (dotimes [row (count board)]
      (print "| ")
      (doseq [subrow (nth board row)]
        (doseq [cell (butlast subrow)]
          (print (str cell " ")))
        (print (str (last subrow) " | ")))
      (println)
      (when (zero? (mod (inc row) 3))
        (println row-sep)))))

(-> b1 prep print-board)

(defn rows [board sz]
  (partition sz board))

(defn row-for [board index sz]
  (nth (rows board sz) (/ index 9)))

(row-for b1 1 9)

(defn column-for [board index sz]
  (let [col (mod index sz)]
    (map #(nth % col)
         (rows board sz))))
(column-for b1 2 9)

(defn subgrid-for [board i]
  (let [rows    (rows board 9)
        sgcol   (/ (mod i 9) 3)
        sgrow   (/ (/ i 9) 3)
        grp-col (column-for (mapcat #(partition 3 %) rows) sgcol 3)
        grp     (take 3 (drop (* 3 (int sgrow)) grp-col))]
    (flatten grp)))
(subgrid-for b1 0)

(defn numbers-present-for [board i]
  (set
    (concat (row-for board i 9)
            (column-for board i 9)
            (subgrid-for board i))))
(numbers-present-for b1 1)

(defn possible-placements [board index]
  (clojure.set/difference #{1 2 3 4 5 6 7 8 9}
                          (numbers-present-for board index)))

(defn solve [board]
  (if-let [[i & _] (and (some '#{-} board)
                        (pos '#{-} board))]
    (flatten (map #(solve (assoc board i %))
                  (possible-placements board i)))
    board))

(-> b1
    solve
    prep
    print-board)

(defn lvar?
  [x]
  (boolean
    (when (symbol? x)
      (re-matches #"^\?.*" (name x)))))
(lvar? '?x)

(defn satisfy1
  [l r knowledge]
  (let [L (get knowledge l l)
        R (get knowledge r r)]
    (cond
      (= L R) knowledge
      (lvar? L) (assoc knowledge L R)
      (lvar? R) (assoc knowledge R L)
      :default nil)))
(satisfy1 '?someting 2 {})
(satisfy1 2 '?someting {})

(defn satisfy
  [l r knowledge]
  (let [L (get knowledge l l)
        R (get knowledge r r)]
    (cond
      (not knowledge) nil
      (= L R) knowledge
      (lvar? L) (assoc knowledge L R)
      (lvar? R) (assoc knowledge R L)
      (every? seq? [L R]) (satisfy (rest L)
                                   (rest R)
                                   (satisfy (first L)
                                            (first R)
                                            knowledge))
      :default nil)))
(satisfy '(1 2 3) '(1 ?something 3) {})
(satisfy '((((?something)))) '((((2)))) {})
(satisfy '(?x 2 3 (4 5 ?z))
         '(1 2 ?y (4 5 6))
         {})

(defn subst [term binds]
  (walk/prewalk
    (fn [expr]
      (if (lvar? expr)
        (or (binds expr) expr)
        expr))
    term))
(subst '(1 ?x 3) '{?x 2})
