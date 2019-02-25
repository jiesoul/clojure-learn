(ns com.jiesoul.codewar.funcij)

(defn sumin [n]
  (loop [n n
         r 0]
    (if (zero? n)
      r
      (let [v (+ n (* 2 (apply + (range 1 n))))]
        (recur (dec n) (+ r v)))))
  )

(defn sumax [n]
  (loop [n n
         r 0]
    (if (zero? n)
      r
      (let [v (* 2 (apply + (range 1 (inc n))))]
        (recur (dec n) (+ r v)))))
  )

(defn sumsum [n]
  ; your code
  )

(sumin 5)
(sumin 6)
(sumin 7)

(sumax 5)
(sumax 8)
