(ns com.jiesoul.sicp.ch02
  (:require [com.jiesoul.sicp.ch01 :refer [gcd square fib prime?
                                           abs sqrt]]
            [quil.core :as q]))

;; 2
(defn linear-combination [a b x y]
  (+ (* a x) (* b y)))

;; 2.1.1
(defn make-rat [n d]
  (let [g (abs (gcd n d))
        m (if (neg? d) (* -1 g) g)]
    [(/ n m) (/ d m)]))

(defn numer [x]
  (first x))

(defn denom [x]
  (second x))

;; 2.1.1
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

(defn make-rat [n d]
  (let [g (gcd n d)]
    [(/ n g) (/ d g)]))

;; EX2.1
(defn make-rat-real [n d]
  (let [g (abs (gcd n d))]
    (if
      (or (and (pos? n) (pos? d))
          (and (neg? n) (pos? d)))
      [(/ n g) (/ d g)]
      [(- (/ n g)) (- (/ d g))])))

;; EX2.2
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

(defn midpoint-segment [segment]
  (make-point
    (/ (+ (x-point (start-segment segment))
          (x-point (end-segment segment)))
       2)
    (/ (+ (y-point (start-segment segment))
          (y-point (end-segment segment)))
       2)))

;; 2.3
(defn make-rectangle [p1 p2 p3]
  )

(defn make-rectangle [point x y]
  (let [x1 (x-point point)
        y1 (y-point point)]
    [(make-segment point (make-point (+ x1 x) y1))
     (make-segment point (make-point x1 (+ y1 y)))]))


(defn point-rectangle [rectangle]
  (start-segment (first rectangle)))

(defn width-rectangle [rectangle]
  ())

(defn height-rectangle [rectangle]
  (last (rest rectangle)))

(defn len-rectangle [rectangle]
  )

;; EX2.5
(defn ex-23-pair [a b]
  )

;; EX2.6
(defn zero []
  (fn [f] (fn [x] x)))

(defn add-1 [n]
  (fn [f] (fn [x] (f ((n f) x)))))

;; EX2.7
(defn make-interval [a b]
  [a b])

;; 2.1.4
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

;; EX2.8
(defn sub-interval [x y]
  (make-interval (min (lower-bound x) (lower-bound y))
    (min (upper-bound x) (upper-bound y))))

;; EX2.9
(defn len-interval [x]
  (- (upper-bound x) (lower-bound x)))

(defn len-add-interval [x y]
  )

;; EX2.11
(defn make-center-width [c w]
  (make-interval (- c w) (+ c w)))

(defn center [i]
  (/ (+ (lower-bound i) (upper-bound i)) 2))

(defn width [i]
  (/ (- (upper-bound i) (lower-bound i)) 2))

;; EX2.12
(defn make-center-percent [i p]
  )

;; EX2.13
(defn par1 [r1 r2]
  (div-interval (mul-interval r1 r2)
                (add-interval r1 r2)))

(defn par2 [r1 r2]
  (let [one (make-interval 1 1)]
    (div-interval one
                  (add-interval (div-interval one r1)
                                (div-interval one r2)))))

;; EX2.14

;; EX2.15

;; EX2.16

;; 2.2


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

;; EX2.17
(defn list-pair [list]
  (if (< (count list) 2)
    (first list)
    (recur (next list))))

;; EX2.18
(defn reverse-1 [lst]
  (loop [lst lst
         result ()]
    (if (empty? lst)
      result
      (recur (next lst) (cons (first lst) result)))))

;; EX2.19
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

;; EX2.20
(defn same-parity [a & c]
  (if (odd? a)
    (conj (filter odd? c) a)
    (conj (filter even? c) a)))


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

(defn scale-list [items factor]
  (map-1 #(* % factor) items))

;; EX2.21
(defn square-list [items]
  (if (empty? items)
    nil
    (cons (* (first items) (first items)) (square-list (rest items)))))

(defn square-list-1 [items]
  (map #(* % %) items))

(defn square-list-iter [items]
  (let [step (fn step [things answer]
               (if (empty? things)
                 answer
                 (recur (rest things) (cons (square (first things))
                                        answer))))]
    (step items nil)))

;; EX2.22
; error
;(defn square-list-iter [items]
;  (let [step (fn step [things answer]
;               (if (empty? things)
;                 answer
;                 (recur (rest things) (cons answer (square (first things))))))]
;    (step items nil)))

;; EX2.23
(defn for-each [proc items]
  (if (empty? items)
    nil
    (do
      (proc (first items))
      (for-each proc (rest items)))))

;; 2.2.2
(def x (cons (list 1 2) (list 3 4)))

(defn count-leaves [x]
  (cond
    (and (seq? x) (empty? x)) 0
    (not (seq? x)) 1
    :else (+ (count-leaves (first x))
             (count-leaves (rest x)))))

;; EX2.24

;; EX2.27
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

;; EX2.28
(defn concat-1 [l1 l2]
  (loop [l1 l1
         l2 (reverse l2)]
    (if (empty? l1)
      (reverse l2)
      (recur (rest l1) (cons (first l1) l2)))))

(defn fringe [x]
  (loop [x      x
         result '()]
    (cond
      (sequential? (first x)) (recur (concat-1 (first x) (rest x)) result)
      (empty? x) (reverse result)
      :else (recur (rest x) (cons (first x) result)))))


;; EX2.29
(defn make-mobile [left right]
  (list left right))

(defn make-branch [length structure]
  (list length structure))

(defn left-branch [mobile]
  (first mobile))

(defn right-branch [mobile]
  (second mobile))

(defn branch-length [branch]
  (first branch))

(defn branch-structure [branch]
  (second branch))

(defn total-weight [mobile]
  (let [lb  (left-branch mobile)
        rb  (right-branch mobile)
        lbs (branch-structure lb)
        rbs (branch-structure rb)]
    (+ (if (number? lbs) lbs (total-weight lbs))
      (if (number? rbs) rbs (total-weight rbs)))))

(defn check-bran? [mobile]
  (loop [l (left-branch mobile)
         r (right-branch mobile)]
    (let [ll (branch-length l)
          ls (branch-structure l)
          rl (branch-length r)
          rs (branch-structure r)]
      (cond
        (and (number? ls) (number? rs)) (= (* ll ls) (* rl rs))
        (and (not (number? ls)) (not (number? rs)) (= ll rl)) (recur ls rs)
        :else false))))

;;

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

;; 2.30
(defn square-tree [x]
  (cond
    (and (sequential? x) (empty? x)) nil
    (not (pair? x)) (* x x)
    :else (cons (square-tree (first x))
            (square-tree (rest x)))))

(defn square-tree-map [x]
  (map #(if (pair? %)
          (square-tree %)
          (* % %))
    x))

;;2.31
(defn tree-map [f tree]
  (map #(if (pair? %)
          (tree-map f %)
          (f %))
    tree))

(defn square-tree-f [tree]
  (tree-map square tree))

;; 2.32
(defn subsets [s]
  (if (and (sequential? s) (empty? s))
    (list '())
    (let [rest (subsets (rest s))]
      (append rest (map #(cons (first s) %) rest)))))

;; 2.2.3
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

;; EX2.33
(defn map-p [p sequence]
  (accumulate #(cons (p %1) %2) nil sequence))

(defn append-1 [seq1 seq2]
  (accumulate cons seq2 seq1))

(defn length-1 [sequence]
  (accumulate #(+ %2 1) 0 sequence))

;; EX2.34
(defn horner-eval [x coefficient-sequence]
  (accumulate #(+ %1 (* x %2))
    0
    coefficient-sequence))

;; EX2.35
(defn count-leaves-tree [t]
  (accumulate +
    0
    (map #(if (seq? %)
            (count-leaves-tree %)
            1) t)))

;; EX2.36
(defn accumulate-n [op init seqs]
  (if (null? (first seqs))
    nil
    (cons (accumulate op init (map first seqs))
      (accumulate-n op init (map rest seqs)))))

;; EX2.37
(defn dot-product [v w]
  (accumulate + 0 (map * v w)))

(defn matrix-*-vector [m v]
  (map #() m))

(defn transpose [mat]
  (accumulate-n cons '() mat))

(defn matrix-*-matrix [m n]
  (let [cols (transpose n)]
    (map #(accumulate-n + 0 cols) m)))

;; EX2.38
(defn fold-left [op initial sequence]
  (loop [result initial
         col    sequence]
    (if-not (seq col)
      result
      (recur (op result (first col))
        (rest col)))))

(defn fold-right [op initial sequence]
  (fold-left op initial (reverse sequence)))

;; EX2.39

;;

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

;; EX2.40
(defn unique-pairs [n]
  )

;; EX2.41

(defn safe? [k x]
  )

(defn adjoin-position [row k x]
  )

;; EX2.42
(defn queens [board-size]
  (letfn [(queen-cols [k]
            (if (zero? k)
              (list empty)
              (filter
                #(safe? k %)
                (flatmap
                  #(map (fn [new-row]
                          (adjoin-position new-row k %))
                     (enumerate-interval 1 board-size))
                  (queen-cols (- k 1))))))]
    (queen-cols board-size)))

;; EX2.43
(def k 0)

(defn queen-cols [k]
  )

(def board-size 0)

;(flatmap
;  (fn [new-row]
;    (map (fn [rest-of-queens]
;           (adjoin-position new-row k rest-of-queens))
;      (queen-cols (- k 1)))
;    (enumerate-interval 1 board-size)))



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

;; EX2.53
(list 'a 'b 'c)
(list (list 'george))

;; EX2.54
(defn equal? [x y]
  (cond
    (and (empty? x) (empty? y)) true
    (not= (first x) (first y)) false
    :else (recur (rest x) (rest y))))

(defn variable? [x]
  (symbol? x))

(defn same-var? [v1 v2]
  (and (variable? v1) (variable? v2) (= v1 v2)))

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

;; EX2.56

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

;; EX2.63
(defn tree->list-1 [tree]
  (if (empty? tree)
    '()
    (append (tree->list-1 (left-branch tree))
            (cons (entry tree)
                  (tree->list-1 (right-branch tree))))))


(defn tree->list-2 [tree]
  (letfn [(copy-to-list [tree result-list]
            (if (empty? tree)
              result-list
              (recur (left-branch tree)
                     (cons (entry tree)
                           (copy-to-list (right-branch tree)
                                         result-list)))))]
    (copy-to-list tree '())))

;; EX2.64
(defn quotient [m n]
  n)

(defn partial-tree [elts n]
  (if (zero? n)
    (cons '() elts)
    (let [left-size (quotient (dec n) 2)]
      (let [left-result (partial-tree elts left-size)]
        (let [left-tree     (first left-result)
              non-left-elts (rest left-result)
              right-size    (- n (+ left-size 1))]
          (let [this-entry   (first non-left-elts)
                right-result (partial-tree (rest non-left-elts)
                                           right-size)]
            (let [right-tree     (first right-result)
                  remaining-elts (rest right-result)]
              (cons (make-tree this-entry left-tree right-tree)
                    remaining-elts))))))))

(defn list->tree [elements]
  (first (partial-tree elements (len elements))))

;;  EX

(defn lookup [given-key set-of-records]
  (cond
    (null? set-of-records) false
    (eq? given-key (key (first set-of-records))) (first set-of-records)
    :else (recur given-key (rest set-of-records))))

;; 2.3.4
(defn make-leaf [symbol weight]
  (list :leaf symbol weight))

(defn leaf? [object]
  (= (first object) :leaf))

(def symbol-leaf
  (comp first rest))

(def weight-leaf
  (comp second rest))

(defn symbols [tree]
  (if (leaf? tree)
    (list (symbol-leaf tree))
    (second (rest tree))))

(defn weight [tree]
  (if (leaf? tree)
    (weight-leaf tree)
    (second (rest (rest tree)))))

(defn make-code-tree [left right]
  (list left
        right
        (concat (symbols left) (symbols right))
        (+ (weight left) (weight right))))

(def left-branch first)
(def right-branch second)

(defn choose-branch [bit branch]
  (cond
    (= bit 0) (left-branch branch)
    (= bit 1) (right-branch branch)
    :else (throw (str "bad bit --CHOOSE-BRANCH" bit))))

(defn decode [bits tree]
  (loop [bits   bits
         branch tree
         acc    []]
    (if (nil? bits)
      acc
      (let [next-branch (choose-branch (first bits) branch)]
        (if (leaf? next-branch)
          (recur (next bits) tree (conj acc (symbol-leaf next-branch)))
          (recur (next bits) next-branch acc))))))

;; EX2.67
(def sample-tree
  (make-code-tree (make-leaf 'A 4)
                  (make-code-tree
                    (make-leaf 'B 2)
                    (make-code-tree (make-leaf 'D 1)
                                    (make-leaf 'C 1)))))

(def sample-message '(0 1 1 0 0 1 0 1 0 1 1 1 0))

;; EX2.68
(defn encode-symbol [m t]
  )

(defn encode [message tree]
  (if (nil? message)
    '()
    (concat (encode-symbol (first message) tree)
            (encode (rest message) tree))))

;; EX2.69
(defn successive-merge [set]
  )

(defn make-leaf-set [pairs]
  )

(defn generate-huffman-tree [pairs]
  (successive-merge (make-leaf-set pairs)))


;; 2.4
(defn real-part [z]
  (first z))

(defn imag-part [z]
  (second z))

(defn magnitude [z]
  (sqrt (+ (square (real-part z)) (square (imag-part z)))))

(defn angle [z]
  (Math/atan2 (imag-part z) (real-part z)))

(defn make-from-real-image [x y]
  [x y])

(defn make-from-mag-ang [r a]
  [(* r (Math/cos a)) (* r (Math/sin a))])

(defn add-complex [z1 z2]
  (make-from-real-image (+ (real-part z1) (real-part z2))
                        (+ (imag-part z1) (imag-part z2))))

(defn sub-complex [z1 z2]
  (make-from-real-image (- (real-part z1) (real-part z2))
                        (- (imag-part z1) (imag-part z2))))

(defn mul-complex [z1 z2]
  (make-from-mag-ang (* (magnitude z1) (magnitude z2))
                     (+ (angle z1) (angle z2))))

(defn div-complex [z1 z2]
  (make-from-mag-ang (/ (magnitude z1) (magnitude z2))
                     (- (angle z1) (angle z2))))


(defn magnitude [z]
  (first z))

(defn angle [z]
  (rest z))

(defn real-part-1 [z]
  (* (magnitude z) (Math/cos (angle z))))

(defn imag-part [z]
  (* (magnitude z) (Math/sin (angle z))))


(defn make-from-real-image [x y]
  [(sqrt (+ (square x) (square y)))
   (Math/atan2 y x)])

(defn make-from-mag-ang [r a]
  [r a])

(defn attach-tag [type-tag contents]
  [type-tag contents])

(defn type-tag [datum]
  (if (vector? datum)
    (first datum)
    (throw (IllegalArgumentException. (str "Bad tagged datum -- TYPE-TAG" datum)))))

(defn contents [datum]
  (if (vector? datum)
    (first datum)
    (throw (IllegalArgumentException. (str "Bad tagged datum -- CONTENTS" datum)))))

(defn rectangular? [z]
  (= (type-tag z) 'rectangular))

(defn polar? [z]
  (= (type-tag z) 'polar))

(defn real-part-rectangular [z]
  (first z))

(defn imag-part-rectangular [z]
  (rest z))

(defn magnitude-rectangular [z]
  (sqrt (+ (square (real-part-rectangular z))
           (square (imag-part-rectangular z)))))

(defn angle-rectangualr [z]
  (Math/atan2 (imag-part-rectangular z)
              (real-part-rectangular z)))

(defn make-from-real-image-rectangular [x y]
  (attach-tag 'rectangular [x y]))

(defn make-from-mag-rectangular [r a]
  (attach-tag 'rectangular [(* r (Math/cos a)) (* r (Math/sin a))]))

(defn magnitude-polar [z]
  (first z))

(defn angle-polar [z]
  (rest z))

(defn real-part-polar [z]
  (* (magnitude-polar z) (Math/cos (angle-polar z))))

(defn image-part-polar [z]
  (* (magnitude z) (Math/sin (angle-polar z))))


(defn make-from-real-image-polar [x y]
  (attach-tag 'polar
              (cons (sqrt (+ (square x) (square y)))
                    (Math/atan2 x y))))


(defn make-from-mag-ang-r [r a]
  (attach-tag 'polar (r a)))


(defn rectangeular? [z]
  )

(defn polar? [z]
  )

(defn real-part [z]
  (cond
    (rectangular? z)
    (real-part-rectangular (contents %))
    (polar? z)
    ))