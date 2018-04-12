(ns sicp.ch02
  (:require [sicp.ch01 :refer [gcd]]))

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

(def one-half (make-rat 1 2))
(print-rat one-half)

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

(def one-three (make-rat 1 3))

(print-rat (add-rat one-half one-three))
(print-rat (sub-rat one-half one-three))
(print-rat (add-rat one-three one-three))