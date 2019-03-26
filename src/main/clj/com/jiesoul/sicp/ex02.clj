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

;; 2.7
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
;; 2.8
(defn sub-interval [x y]
  (make-interval (min (lower-bound x) (lower-bound y))
    (min (upper-bound x) (upper-bound y))))

;; 2.9
(defn len-interval [x]
  (- (upper-bound x) (lower-bound x)))

(defn len-add-interval [x y]
  )

;; 2.17
(defn list-pair [list]
  (if (< (count list) 2)
    (first list)
    (recur (next list))))

;; 2.18
(defn reverse-1 [lst]
  (loop [lst lst
         result ()]
    (if (empty? lst)
      result
      (recur (next lst) (cons (first lst) result)))))


;; 2.18
(def us-coins (list 50 25 10 5 1))
(def uk-coins (list 100 50 20 10 5 2 1 0.5))

(defn no-more? [coins]
  (empty? coins))

(defn expect-first-denomination [coins]
  (rest coins))

(defn first-denomination [coins]
  (first coins))

(defn cc [amount coins-values]
  (cond
    (zero? amount) 1
    (or (neg? amount) (no-more? coins-values)) 0
    :else (+ (cc amount (expect-first-denomination coins-values))
              (cc (- amount (first-denomination coins-values)) coins-values))))

(defn same-parity [a & c]
  (if (odd? a)
    (conj (filter odd? c) a)
    (conj (filter even? c) a)))

;; 2.21
(defn square-list [items]
  (if (empty? items)
    nil
    (cons (* (first items) (first items)) (square-list (rest items)))))

(defn square-list-1 [items]
  (map #(* % %) items))

;; 2.22
(defn square [n]
  (* n n))

(defn square-list-iter [itmes]
  (let [step (fn step [things answer]
               (if (empty? things)
                 answer
                 (recur (rest things) (cons (square (first things))
                                            answer))))]
    (step itmes nil)))

; error
;(defn square-list-iter [itmes]
;  (let [step (fn step [things answer]
;               (if (empty? things)
;                 answer
;                 (recur (rest things) (cons answer (square (first things))))))]
;    (step itmes nil)))

;; 2.23
(defn for-each [proc items]
  (if (empty? items)
    nil
    (do
      (proc (first items))
      (for-each proc (rest items)))))

;; 2.27
(defn deep-reverse [x]
  (loop [result ()
         lst x]
    (if (empty? lst)
      result
      (recur
        (cons
          (let [v (first lst)]
            (if (seq? v)
              (deep-reverse v)
              v))
          result)
        (next lst)))))

;; 2.28
(defn fringe [x]
  (let [step (fn [])]
    (loop [lst    x
           result ()]
      (if (empty? lst)
        result
        (recur (rest lst) (cons (let [v (first lst)]
                                  (if (seq? v)
                                    (fringe v)
                                    v))
                            result))))))


;; 2.29
