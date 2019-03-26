(ns com.jiesoul.sicp.ch02
  (:require [com.jiesoul.sicp.ch01 :refer [gcd]]))

(defn linear-combination [a b x y]
  (+ (* a x) (* b y)))

(defn make-rat [n d]
  (let [g (gcd n d)]
    [(/ n g) (/ d g)]))

(defn numer [x]
  (first x))

(defn denom [x]
  (second x))

(defn print-rat [x]
  (str (numer x) "/" (denom x)))

(defn add-rat [x y]
  (make-rat (+ (* (numer x) (denom y))
               (* (numer y) (denom x)))
            (* (denom x) (denom y))))

(defn sub-rat [x y]
  (make-rat (- (* (numer x) (denom y))
               (* (numer y) (denom x)))
            (* (denom x) (denom y))))

(defn mul-rat [x y]
  (make-rat (* (numer x) (numer y))
            (* (denom x) (denom y))))

(defn div-rat [x y]
  (make-rat (* (numer x) (denom y))
            (* (denom x) (numer y))))

(defn equal-rat? [x y]
  (= (* (numer x) (denom y))
     (* (denom y) (numer x))))

(defn abs [n]
  (Math/abs n))

(defn list-ref [items n]
  (if (zero? n)
    (first items)
    (recur (rest items) (dec n))))

(defn len [items]
  (if (nil? items)
    0
    (+ 1 (len (next items)))))

(defn length-iter [items]
  (let [step (fn [a count]
               (if (nil? a)
                 count
                 (recur (next a) (inc count))))]
    (step items 0)))

(defn append [list1 list2]
  (if (nil? list1)
    list2
    (cons (first list1) (append (next list1) list2))))

(defn scale-list [items factor]
  (if (empty? items)
    nil
    (cons (* (first items) factor)
          (scale-list (rest items) factor))))

(defn map-1 [proc items]
  (if (empty? items)
    nil
    (cons (proc (first items))
          (map-1 proc (rest items)))))

(defn new-scale-list [items factor]
  (map-1 #(* % factor) items))

(def x (cons (list 1 2) (list 3 4)))

(defn pair? [x]
  (seq? x))

(defn count-leaves [x]
  (cond
    (and (seq? x) (empty? x)) 0
    (not (seq? x)) 1
    :else (+ (count-leaves (first x))
             (count-leaves (rest x)))))




