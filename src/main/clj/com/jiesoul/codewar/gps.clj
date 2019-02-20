(ns com.jiesoul.codewar.gps)

(defn gps [s x]
  (if (<= (count x) 1)
    0
    (loop [list x
           result []]
      (if (<= (count list) 1)
        (int (/ (* 3600 (apply max result)) s))
        (recur (rest list) (conj result (- (second list) (first list))))))))

;
(defn gps [s x]
  (if (<= (count x) 1)
    0
    (int (apply max (map #(/ (* % 3600) (float s)) (map - (rest x) x))))))
