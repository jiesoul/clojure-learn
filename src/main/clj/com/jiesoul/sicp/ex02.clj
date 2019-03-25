(ns com.jiesoul.sicp.ex02
  (:require [com.jiesoul.sicp.ch01 :refer [gcd]]))

;; 2.1
(defn make-rat-real [n d]
  (let [g (Math/abs (gcd n d))]
    (if
      (or (and (pos? n) (pos? d))
          (and (neg? n) (pos? d)))
      [(/ n g) (/ d g)]
      [(- (/ n g)) (- (/ d g))])))

;; 2.2
(defn make-point [x y]
  [x y])

(defn x-point [point]
  (first point))

(defn y-point [point]
  (second point))

(defn make-segment [p1 p2]
  [p1 p2])

(defn start-segment [s]
  (first s))

(defn end-segment [s]
  (second s))

(defn print-point [p]
  (str "(" (x-point p) "," (y-point p) ")"))

(defn midpoint-segment [s]
  (let [p1   (start-segment s)
        p2   (end-segment s)
        x1   (x-point p1)
        x2   (x-point p2)
        y1   (y-point p1)
        y2   (y-point p2)
        step (fn [v1 v2]
               (let [mi (min v1 v2)
                     ma (max v1 v2)]
                 (+ mi (/ (- ma mi) 2))))]
    [(step x1 x2) (step y1 y2)]))

;; 2.3
(defn make-rectangle [segment]
  (let [p1 (start-segment segment)
        p2 (end-segment segment)]
    [p1 (make-point (x-point p1) (y-point p2))
     p2 (make-point (x-point p2) (y-point p1))]))

(defn len-rectangle [rectangle])

;; 2.6
(defn zero []
  (fn [f] (fn [x] x)))

(defn add-1 [n]
  (fn [f] (fn [x] (f ((n f) x)))))

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


(defn list-ref [items n]
  (if (zero? n)
    (first items)
    (recur (rest items) (dec n))))

(defn length [items]
  (if (nil? items)
    0
    (+ 1 (length (rest items)))))

(defn length-iter [items]
  (let [step (fn [a count]
               (if (nil? a)
                 count
                 (recur (rest a) (inc count))))]
    (step items 0)))