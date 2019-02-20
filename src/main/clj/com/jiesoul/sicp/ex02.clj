(ns com.jiesoul.sicp.ex02)

(defn make-interval [a b]
  [a b])

(defn lower-bound [x]
  (first x))

(defn upper-bound [x]
  (second x))

(defn add-interval [x y]
  (make-interval (+ (lower-bound x) (lower-bound y))
                 (+ (upper-bound x) (upper-bound y))))

(defn mul-interval [x y]
  (let [p1 (* (lower-bound x) (lower-bound y))
        p2 (* (lower-bound x) (upper-bound y))
        p3 (* (upper-bound x) (upper-bound y))
        p4 (* (upper-bound x) (upper-bound y))]
    (make-interval (apply min p1 p2 p3 p4)
                   (apply max p1 p2 p3 p4))))

(defn div-interval [x y]
  (mul-interval x
                (make-interval (/ 1.0 (upper-bound y))
                               (/ 1.0 (lower-bound y)))))