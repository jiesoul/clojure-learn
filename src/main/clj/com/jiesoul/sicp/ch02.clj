(ns com.jiesoul.sicp.ch02
  (:require [com.jiesoul.sicp.ch01 :refer [gcd square fib prime?]]))

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
  (if (empty? list1)
    list2
    (cons (first list1) (append (rest list1) list2))))

(defn null? [x]
  (if (sequential? x)
    (empty? x)
    (nil? x)))

(defn pair? [x]
  (and (sequential? x) (not (empty? x))))

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


(defn flatmap [proc seq]
  (accumulate append nil (map proc seq)))

(defn prime-sum? [pair]
  (prime? (+ (first pair) (second pair))))

(defn make-pair-sum [pair]
  (list (first pair) (second pair) (+ (first pair) (second pair))))

(defn prime-sum-pairs [n]
  (map make-pair-sum
       (filter prime-sum?
               (flatmap
                 (fn [i]
                   (map #(list i %) (enumerate-interval 1 (- i 1))))
                 (enumerate-interval 1 n)))))


(defn remove-1 [item sequence]
  (filter #(not (= % item)) sequence))

(defn permutations [s]
  (if (empty? s)
    '()
    (flatmap #(map (fn [p] (cons % p)) (permutations (remove-1 % s)))
             s)))

;; 2.2.4
(def beside)
(def flip-vert)
(def flip-horiz)
(def below)
(def up-split)
(def rotate180)

(defn flipped-pairs [painter]
  (let [painter2 (beside painter (flip-vert painter))]
    (below painter2 painter)))

(defn right-split [painter n]
  (if (zero? n)
    painter
    (let [smaller (right-split painter (dec n))]
      (beside painter (below smaller smaller)))))

(defn corner-split [painter n]
  (if (zero? n)
    painter
    (let [up (up-split painter (dec n))
          right (right-split painter (dec n))]
      (let [top-left (beside up up)
            bottom-right (below right right)
            corner (corner-split painter (dec n))]
        (beside (below painter top-left)
          (below bottom-right corner))))))

(defn square-limit [painter n]
  (let [quarter (corner-split painter n)]
    (let [half (beside (flip-horiz quarter) quarter)]
      (below (flip-vert half) half))))

(defn square-of-four [tl tr bl br]
  (fn [painter]
    (let [top (beside (tl painter) (tr painter))
          bottom (beside (bl painter) (br painter))]
      (below bottom top))))

(defn flipped-pairs [painter]
  (let [combine4 (square-of-four identity flip-vert identity flip-horiz)]
    (combine4 painter)))

(defn square-limit [painter n]
  (let [combine4 (square-of-four flip-horiz identity rotate180 flip-vert)]
    (combine4 (corner-split painter n))))

(def add-vect)
(def origin-frame)
(def scale-vect)
(def xcor-vect)
(def ycor-vect)
(def edge2-frame)

(defn frame-coord-map [frame]
  (fn [v]
    (add-vect
      (origin-frame frame)
      (add-vect (scale-vect (xcor-vect v))
        (scale-vect (ycor-vect v) (edge2-frame frame))))))

;; 2.3
(defn eq? [a b]
  (= a b))

(defn memq [item x]
  (cond
    (null? x) false
    (eq? item (first x)) x
    :else (recur item (rest x))))

(defn variable? [x]
  (symbol? x))

(defn same-var? [v1 v2]
  (and (variable? v1) (variable? v2) (eq? v1 v2)))

(defn sum? [x]
  (and (pair? x) (eq? (first x) '+)))

(defn =number? [exp num]
  (and (number? exp) (= exp num)))

(defn make-sum [x y]
  (list '+ x y))

(defn make-sum [a1 a2]
  (cond
    (=number? a1 0) a2
    (=number? a2 0) a1
    (and (number? a1) (number? a2)) (+ a1 a2)
    :else (list '+ a1 a2)))

(defn addend [x]
  (second x))

(defn augend [x]
  (second (rest x)))

(defn product? [x]
  (and (pair? x) (eq? (first x) '*)))

(defn make-product [a b]
  (list '* a b))

(defn make-product [m1 m2]
  (cond
    (or (=number? m1 0) (=number? m2 0)) 0
    (=number? m1 1) m2
    (=number? m2 1) m1
    (and (number? m1) (number? m2)) (* m1 m2)
    :else (list '* m1 m2)))

(defn multiplier [x]
  (second x))

(defn multiplicand [x]
  (second (rest x)))

(defn deriv [exp var]
  (cond
    (number? exp) 0
    (variable? exp) (if (same-var? exp var) 1 0)
    (sum? exp) (make-sum (deriv (addend exp) var)
                          (deriv (augend exp) var))
    (product? exp) (make-sum (make-product (multiplier exp)
                                            (deriv (multiplicand exp) var))
                              (make-product (deriv (multiplier exp) var)
                                            (multiplicand exp)))
    :else (str "unknown expression type -- DERIV")))

(defn element-of-set? [x set]
  (cond
    (null? set) false
    (eq? x (first set)) true
    :else (recur x (rest set))))

(defn adjoin-set [x set]
  (if (element-of-set? x set)
    set
    (cons x set)))

(defn intersection-set [set1 set2]
  (cond
    (or (null? set1) (null? set2)) '()
    (element-of-set? (first set1) set2) (cons (first set1)
                                              (intersection-set (rest set1) set2))
    :else (intersection-set (rest set1) set2)))

(defn element-of-set? [x set]
  (cond
    (null? set) false
    (= x (first set)) true
    (< x (first set)) false
    :else (recur x (rest set))))

(defn intersection-set [set1 set2]
  (if (or (null? set1) (null? set2))
    '()
    (let [x1 (first set1)
          x2 (first set2)]
      (cond
        (= x1 x2) (cons x1 (intersection-set (rest set1) (rest set2)))
        (< x1 x2) (intersection-set (rest set1) set2)
        (< x2 x1) (intersection-set set1 (rest set2))))))

(defn entry [tree]
  (first tree))

(defn left-branch [tree]
  (second tree))

(defn right-branch [tree]
  (second (rest tree)))

(defn make-tree [entry left right]
  (list entry left right))

(defn element-of-set? [x set]
  (cond
    (null? set) false
    (= x (entry set)) true
    (< x (entry set)) (recur x (left-branch set))
    (> x (entry set)) (recur x (right-branch set))))

(defn adjoin-set [x set]
  (cond
    (null? set) (make-tree x '() '())
    (= x (entry set)) set
    (< x (entry set)) (make-tree (entry set) (adjoin-set x (left-branch set)) (right-branch set))
    (> x (entry set)) (make-tree (entry set) (left-branch set) (adjoin-set x (right-branch set)))))

(defn lookup [given-key set-of-records]
  (cond
    (null? set-of-records) false
    (eq? given-key (key (first set-of-records))) (first set-of-records)
    :else (recur given-key (rest set-of-records))))

;; 2.3.4
(defn make-leaf [symbol weight]
  (list 'leaf symbol weight))

(defn leaf? [object]
  (eq? (first object) 'leaf))

(defn symbol-leaf [x]
  (second x))

(defn weight-leaf [x]
  (second (rest x)))

(defn caddr [x]
  (second (rest x)))

(defn symbols [tree]
  (if (leaf? tree)
    (list (symbol-leaf tree))
    (caddr tree)))

(defn weight [tree]
  (if (leaf? tree)
    (weight-leaf tree)
    (second (rest tree))))

(defn make-code-tree [left right]
  (list left right (append (symbol left) (symbol right)) (+ (weight left) (weight right))))

(defn left-branch-1 [tree]
  )

