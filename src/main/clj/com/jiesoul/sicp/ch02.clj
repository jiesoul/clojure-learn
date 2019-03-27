(ns com.jiesoul.sicp.ch02
  (:require [com.jiesoul.sicp.ch01 :refer [gcd square fib]]))

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

(defn null? [x]
  (if (sequential? x)
    (empty? x)
    (nil? x)))

(defn pair? [x]
  (and (sequential? x) (not (empty? x))))

(defn count-leaves [x]
  (cond
    (and (seq? x) (empty? x)) 0
    (not (seq? x)) 1
    :else (+ (count-leaves (first x))
             (count-leaves (rest x)))))

(defn scale-tree [tree factor]
  (cond
    (and (sequential? tree) (empty? tree)) nil
    (not (pair? tree)) (* tree factor)
    :else (cons (scale-tree (first tree) factor)
                (scale-tree (rest tree) factor))))

(defn scale-tree-map [tree factor]
  (map #(if (pair? %)
          (scale-tree-map % factor)
          (* % factor))
       tree))

;; 2.3
(defn sum-odd-squares [tree]
  (cond
    (null? tree) 0
    (not (pair? tree)) (if (odd? tree) (square tree) 0)
    :else (+ (sum-odd-squares (first tree))
             (sum-odd-squares (rest tree)))))

(defn even-fibs [n]
  (letfn [(next [k]
            (if (> k n)
              nil
              (let [f (fib k)]
                (if (even? f)
                  (cons f (next (+ k 1)))
                  (next (+ k 1))))))]
    (next 0)))

(defn filter-1 [predicate sequence]
  (cond
    (null? sequence) nil
    (predicate (first sequence)) (cons (first sequence) (filter-1 predicate (rest sequence)))
    :else (filter-1 predicate (rest sequence))))

(defn accumulate [op initial sequence]
  (if (null? sequence)
    initial
    (op (first sequence) (accumulate op initial (rest sequence)))))

(defn enumerate-interval [low high]
  (if (> low high)
    nil
    (cons low (enumerate-interval (inc low) high))))

(defn enumerate-tree [tree]
  (cond
    (null? tree) nil
    (not (pair? tree)) (list tree)
    :else (append (enumerate-tree (first tree))
                  (enumerate-tree (rest tree)))))

(defn sum-odd-squares-1 [tree]
  (accumulate +
              0
              (map square (filter-1 odd? (enumerate-tree tree)))))

(defn even-fibs-1 [n]
  (accumulate cons
              nil
              (filter even?
                      (map fib
                           (enumerate-interval 0 n)))))

(defn list-fib-squares [n]
  (accumulate cons
              nil
              (map square
                   (map fib
                        (enumerate-interval 0 n)))))

(defn product-of-squares-of-odd-elements [sequence]
  (accumulate *
              1
              (map square
                   (filter odd? sequence))))

(defn salary [a]
  )

(defn programmers? [p]
  true)

(defn salary-of-highest-paid-programmer [records]
  (accumulate max
              0
              (map salary
                   (filter programmers? records))))

