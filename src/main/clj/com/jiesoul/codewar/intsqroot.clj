(ns com.jiesoul.codewar.intsqroot)

(defn int-rac
  [n first-guess]
  (let [f (fn [n x]
            (Math/floor (/ (+ x (/ n x)) 2)))]
    (loop [n n
           x first-guess
           r []]
      (let [nx (f n x)]
        (if (< (Math/abs (- x nx)) 1)
          (count (conj r x))
          (recur n nx (conj r x)))))))

(defn int-rac1
  [n first-guess]
  (loop [x first-guess
         size 1]
    (let [x-new (quot (+ x (quot n x)) 2)]
      (if (< (Math/abs (- x x-new)) 1)
        size
        (recur x-new (inc size))))))

(int-rac 25 1)
(int-rac 125348 300)
(int-rac1 125348 300)
(int-rac 16000 400)
(int-rac 125348981764 356243)