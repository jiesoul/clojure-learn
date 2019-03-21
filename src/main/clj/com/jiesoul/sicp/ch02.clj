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

(defn scale-list [items factor]
  (if (empty? items)
    nil
    (cons (* (first items) factor)
          (scale-list (rest items) factor))))

(scale-list (list 1 2 3 4 5) 6)

(defn map- [proc items]
  (if (empty? items)
    nil
    (cons (proc (first items))
          (map- proc (rest items)))))

(defn abs [n]
  (Math/abs n))

(map- abs (list -10 -2 2.5))
(map- #(* % %) (list 1 2 3 4))

(defn scale-list [items factor]
  (map- #(* % factor) items))


