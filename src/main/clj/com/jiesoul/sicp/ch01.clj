(ns com.jiesoul.sicp.ch01)

;; 1.1.1
486
(+ 137 349)
(- 1000 334)
(* 5 99)
(/ 10 5)
(+ 2.7 10)
(+ 21 35 12 7)
(* 25 4 12)
(+ (* 3 5) (- 10 6))
(+ (* 3 (+ (* 2 4) (+ 3 5))) (+ (- 10 7) 6))

;; 1.1.2
(def size 2)
size

(* 5 size)

(def pi 3.14159)
(def radius 10)
(* pi (* radius radius))

(def circumference (* 2 pi radius))
circumference

;; 1.1.3
(* (+ 2 (* 4 6))
   (+ 3 5 7))

;; 1.1.4
(defn square [x]
  (* x x))

(defn sum-of-square [x y]
  (+ (square x) (square y)))

(defn f [a]
  (sum-of-square (+ a 1) (* a 2)))

;; 1.1.5

;; 1.1.6
(defn abs [x]
  (cond
    (pos? x) x
    (zero? x) 0
    :else (- x)))

(defn abs [x]
  (cond
    (neg? x) (- x)
    :else x))

(defn abs [x]
  (if (neg? x)
    (- x)
    x))

(defn >=-1 [x y]
  (or (> x y) (= x y)))

(defn >=-1 [x y]
  (not (< x y)))

;; EX1.1
10
(+ 5 3 4)
(- 9 1)
(/ 6 2)
(+ (* 2 4) (- 4 6))
(def a 3)
(def b (+ a 1))
(+ a b (* a b))
(= a b)
(if (and (> b a) (< b (* a b)))
  b
  a)

(cond
  (= a 4) 6
  (= b 4) (+ 6 7 a)
  :else 25)

(+ 2 (if (> b a) b a))
(* (cond
     (> a b) a
     (< a b) b
     :else -1)
  (+ a 1))

;; EX1.2
(/ (+ 5 4 (- 2 (- 3 (+ 6 (/ 4 5)))))
   (* 3 (- 6 2) (- 2 7)))

;; EX1.3
(defn max-three [a b c]
  (cond
    (or (> b a c) (> a b c)) (+ a b)
    (or (> c b a) (> b c a)) (+ b c)
    :else (+ c a)))

;; EX1.4
(defn a-plus-abs-b [a b]
  ((if (> b 0) + 1) a b))

;; EX1.5
;(defn p []
;  p)

(defn test-1 [x y]
  (if (zero? x)
    0
    y))

(defn abs [n]
  (max n (- n)))

;; EX1.7
(defn grow-rate [y x]
  (abs (/ (- (* y y) x) x)))

(defn average [x y]
  (/ (+ x y) 2))

(defn improve [guess x]
  (average guess (/ x guess)))

(defn good-enough? [guess x]
  (< (grow-rate guess x) 0.001))

(defn sqrt-iter [guess x]
  (if (good-enough? guess x)
    guess
    (recur (improve guess x) x)))

(defn sqrt [x]
  (sqrt-iter 1.0 x))

(defn sqrt-1 [x]
  (let [good-enough? (fn [guess] (< grow-rate guess x) 0.0001)
        improve      (fn [guess] (average guess (/ x guess)))
        sqrt-iter    (fn [guess] (if (good-enough? guess) guess (recur (improve guess))))]
    (sqrt-iter 1.0)))

;; EX1.6
;; if 是一种特殊形式 会直接求值 predicate，如果采用下面的 new-if 在谓词为表达式的情况下 会无限展开
(defn new-if [predicate then-clause else-clause]
  (cond
    predicate then-clause
    :else else-clause))

;(defn sqrt-iter [guess x]
;  (new-if (good-enough? guess x)
;          guess
;          (new-sqrt-iter (improve guess x) x)))

;; EX1.8
(defn cube-grow-rate [y x]
  (abs (/ (- (* y y y) x) x)))

(defn cube-good-enough? [y x]
  (< (cube-grow-rate y x) 0.000001))

(defn cube-improve [y x]
  (/ (+ (/ (x (* y y))) (* 2 y)) 3))

(defn cube-root-iter [guess x]
  (if (cube-good-enough? guess x)
    guess
    (recur (cube-improve guess x) x)))

(defn cube-root [n]
  (cube-root-iter 1.0 n))

;; 1.1.8
(defn sqrt [x]
  (letfn [(good-enough? [guess x]
            (< (abs (- (square guess) x)) 0.001))
          (improve [guess x]
            (average guess (/ x guess)))
          (sqrt-iter [guess x]
            (if (good-enough? guess x)
              guess
              (sqrt-iter (improve guess x) x)))]
    (sqrt-iter 1.0 x)))

(defn sqrt [x]
  (letfn [(good-enough? [guess]
            (< (abs (- (square guess) x)) 0.001))
          (improve [guess]
            (average guess (/ x guess)))
          (sqrt-iter [guess]
            (if (good-enough? guess)
              guess
              (sqrt-iter (improve guess))))]
    (sqrt-iter 1.0)))

;; 1.2

;; 1.2.1
(defn factorial [n]
  (if (= n 1)
    1
    (* n (factorial (- n 1)))))

(defn fact-iter [product counter max-count]
  (if (> counter max-count)
    product
    (recur (* counter product)
           (+ counter 1)
           max-count)))

(defn factorial [n]
  (fact-iter 1N 1N n))

(defn factorial [n]
  (letfn [(iter [product counter]
            (if (> counter n)
              product
              (iter (* counter product)
                    (+ counter 1))))]
    (iter 1N 1N)))

;; EX1.9
(defn plus [a b]
  (if (zero? a)
    b
    (inc (plus (dec a) b))))

(defn plus [a b]
  (if (= a 0)
    b
    (plus (dec a) (inc b))))

;; EX1.10
(defn A [x y]
  (cond
    (zero? y) 0
    (zero? x) (* 2 y)
    (= y 1) 2
    :else (A (dec x) (A x (dec y)))))

(defn f-A [n]
  (A 0 n))

(defn g-A [n]
  (A 1 n))

(defn h-A [n]
  (A 2 n))

(defn k-A [n]
  (* 5 n n))

;; 1.2.2
(defn fib [n]
  (cond
    (= n 0) 0
    (= n 1) 1
    :else (+ (fib (- n 1))
             (fib (- n 2)))))

(defn fib [n]
  (if (< n 2)
    n
    (+ (fib (dec n))
       (fib (- n 2)))))

(defn fib-iter [n]
  (let [step (fn [a b count]
               (if (zero? count)
                 b
                 (recur (+ a b) a (dec count))))]
    (step 1 0 n)))

(defn first-denomination [kinds-of-coins]
  (case kinds-of-coins
    1 1
    2 5
    3 10
    4 25
    5 50))

(defn cc [amount kinds-of-coins]
  (cond
    (zero? amount) 1
    (or (neg? amount) (zero? kinds-of-coins)) 0
    :else (+ (cc amount (dec kinds-of-coins))
             (cc (- amount (first-denomination kinds-of-coins))
                 kinds-of-coins))))

(defn count-change [amount]
  (cc amount 5))

;; EX1.11
(defn f-11 [n]
  (if (< n 3)
    n
    (+ (f-11 (dec n)) (* 2 (f-11 (- n 2))) (* 3 (f-11 (- n 3))))))

(defn f-11-iter [n]
  (let [sum  (fn [a b c] (+ a (* 2 b) (* 3 c)))
        step (fn [a b c counter n]
               (cond
                 (< n 3) n
                 (> counter n) c
                 :else
                 (recur b c (sum a b c) (inc counter) n)))]
    (step 0 1 2 3 n)))

;; EX1.12
(defn f-12 [n]
  (if (= n 1)
    [1]
    (let [new (f-12 (dec n))]
      (mapv + (conj new 0) (cons 0 new)))))


;; EX1.13
(defn f-13 [n]
  )

;; 1.2.3

;; EX1.14

;; EX1.15
(defn cube [x]
  (* x x x))

(defn p [x]
  (- (* 3 x) (* 4 (cube x))))

(defn sine [angle]
  (if (not (> (Math/abs angle) 0.1))
    angle
    (p (sine (/ angle 3.0)))))

;; 1.2.4
(defn expt [b n]
  (if (zero? n)
    1
    (* b (expt b (dec n)))))

(defn expt-iter [b n]
  (let [step (fn [b counter product]
               (if (zero? counter)
                 product
                 (recur b (dec counter) (* b product))))]
    (step b n 1)))

;; 这样增长的阶就为 log(n) 了
(defn fast-expt [b n]
  (cond
    (zero? n) 1
    (even? n) (square (fast-expt b (/ n 2)))
    :else (* b (fast-expt b (dec n)))))

;; EX1.16
(defn fast-expt-iter [b n]
  (let [step (fn [b n]
               (cond
                 (zero? n) 1
                 (= n 1) b
                 :else
                 (recur (* b b) (/ n 2))))]
    (if (even? n)
      (step b n)
      (* b (step b (dec n))))))

(defn fast-extp-iter [b n]
  (loop [b b
         n n
         r 1]
    (cond
      (zero? n) r
      (even? n) (recur (* b b) (/ n 2) r)
      :else (recur (* b b) (/ (dec n) 2) (* r b)))))

;; EX1.17
(defn new-mul [a b]
  (if (zero? b)
    0
    (+ a (new-mul a (dec b)))))

(defn new-double [a]
  (+ a a))

(defn halve [a]
  (/ a 2))

(defn new-mul-expt [a b]
  (cond
    (zero? a) 0
    (= a 1) b
    (even? a) (new-mul-expt (halve a) (new-double b))
    :else
    (+ b (new-mul-expt (halve (dec a)) (new-double b)))))


;; EX1.18
(defn new-mul-iter [a b]
  (let [step (fn [a b counter]
               (cond
                 (zero? a) counter
                 (= a 1) (+ b counter)
                 :else
                 (recur (halve a) (new-double b) counter)))]
    (if (even? a)
      (step a b 0)
      (step (dec a) b b))))

;; EX1.19
(defn fib-iter [a b p q count]
  (cond
    (zero? count) b
    (even? count) (fib-iter
                    a
                    b
                    (+ (square p) (square q))
                    (+ (square q) (* 2 p q))
                    (/ count 2))
    :else (fib-iter
            (+ (* b q) (* a q) (* a p))
            (+ (* b p) (* a q))
            p
            q
            (- count 1))))

(defn fib [n]
  (fib-iter 1 0 0 1 n))

;; 1.2.5
(defn gcd [a b]
  (if (zero? b)
    a
    (recur b (rem a b))))

;; EX1.20

;; 1.2.6
(defn divides? [a b]
  (zero? (rem b a)))

(defn find-divisor [n test-divisor]
  (cond
    (> (square test-divisor) n) n
    (divides? test-divisor n) test-divisor
    :else (recur n (inc test-divisor))))

(defn smallest-divisor [n]
  (find-divisor n 2))

(defn prime? [n]
  (= n (smallest-divisor n)))

(defn expmod [base exp m]
  (cond
    (zero? exp) 1
    (even? exp) (rem (square (expmod base (/ exp 2) m))
                     m)
    :else (rem (* base (expmod base (dec exp) m))
               m)))

(defn fermat-test [n]
  (let [try-it (fn [a]
                 (= (expmod a n n) a))]
    (try-it (+ 1 (rand-int (dec n))))))

(defn fast-primes? [n times]
  (cond
    (zero? times) true
    (fermat-test n) (fast-primes? n (dec times))
    :else false))

;; EX1.21
(smallest-divisor 199)
(smallest-divisor 1999)
(smallest-divisor 19999)

;; EX1.22
(defn report-prime-test [elapsed-time]
  (print " *** ")
  (print elapsed-time))

(defn start-prime-test [n start-time]
  (if (prime? n)
    (report-prime-test (- (System/currentTimeMillis) start-time))))

(defn timed-prime-test [n]
  (println)
  (print n)
  (start-prime-test n (System/currentTimeMillis)))

(defn search-for-primes [n]
  (loop [c 1]
    (if (and (> c n) (prime? c))
      c
      (recur (+ c 2)))))

;; EX1.23
(defn smallest-divisor [n]
  (letfn [(next-test [n]
            (if (= n 2)
              3
              (+ n 2)))
          (find-divisor [test]
            (cond
              (> (square test) n) n
              (= (rem n test) 0) test
              :else (recur (next-test test))))]
    (find-divisor 2)))

;; EX1.24


;; 1.3
(defn cube [x]
  (* x x x))

;; 1.3.1
(defn sum-integers [a b]
  (if (> a b)
    0
    (+ a (sum-integers (inc a) b))))

(defn sum-cubes [a b]
  (if (> a b)
    0
    (+ (cube a) (sum-cubes (inc a) b))))

(defn pi-sum [a b]
  (if (> a b)
    0
    (+ (/ 1.0 (* a (+ a 2))) (pi-sum (+ a 4) b))))

(defn sum [term a next b]
  (if (> a b)
    0
    (+ (term a)
       (sum term (next a) next b))))

(defn sum-cubes [a b]
  (sum cube a inc b))

(defn sum-integers [a b]
  (sum identity a inc b))

(defn pi-term [x]
  (/ 1.0 (* x (+ x 2))))

(defn pi-next [x]
  (+ x 4))

(defn pi-sum [a b]
  (sum pi-term a pi-next b))

(defn integral [f a b dx]
  (let [add-dx (fn [x] (+ x dx))]
    (* (sum f (+ a (/ dx 2.0)) add-dx b) dx)))

;; EX1.29
(defn xps-integral [f a b n]
  (let [h (/ (- b a) n)]
    (loop [k 0
           r 0]
      (if (> k n)
        (* (/ h 3.0) r)
        (let [y (f (+ a (* k h)))]
          (recur (inc k)
                 (+ r
                    (* y
                       (cond
                         (or (zero? k) (= k n)) 1
                         (even? k) 2
                         :else 4)))))))))

;; EX1.30
(defn sum [term a next-f b]
  (let [iter (fn [a result]
               (if (> a b)
                 result
                 (recur (next-f a) (+ result (term a)))))]
    (iter a 0)))

(defn sum-cubes [a b]
  (sum cube a inc b))

(defn sum-integers [a b]
  (sum identity a inc b))

(defn pi-sum [a b]
  (sum pi-term a pi-next b))

;; EX1.31
(defn product [t-f a next-f b]
  (if (> a b)
    1
    (* (t-f a)
      (product
        t-f
        (next-f a)
        next-f
        b))))

(defn product-factorial [n]
  (product (fn [x] x) 1 #(inc %) n))

(defn pi-f [a]
  (if
    (odd? a)
    (/ (inc a) (+ a 2) 1.0)
    (/ (+ a 2) (inc a) 1.0)))

(defn product-pi [n]
  (product pi-f
    1
    inc
    n))

(defn product-iter [tf a nf b]
  (let [step (fn [a result]
               (if (> a b)
                 result
                 (recur (nf a) (* result (tf a)))))]
    (step a 1)))

(defn product-iter-factorial [n]
  (product-iter identity 1 inc n))

(defn product-iter-pi [n]
  (product-iter pi-f 1 inc n))


;; ex1.32
(defn accumulate [combiner null-value term a next b]
  (if (> a b)
    null-value
    (combiner (term a)
              (accumulate combiner null-value term (next a) next b))))

(defn accumulate-sum-fac [a b]
  (accumulate + 0 identity a inc b))

(defn accumulate-product-factorial [n]
  (accumulate * 1 identity 1 inc n))

(defn accumulate-iter [combiner null-value term a next b]
  (let [step (fn [a result]
               (if (> a b)
                 result
                 (recur (next a) (combiner result (term a)))))]
    (step a null-value)))

;; 1.33
(defn filtered-accumulate [filtered combiner null-value term a next b]
  (let [step (fn [a result]
               (if (> a b)
                 result
                 (recur (next a) (if (filtered a)
                                   (combiner result (term a))
                                   result))))]
    (step a null-value)))

(defn filtered-accumulate-prime-sum [a b]
  (filtered-accumulate prime? + 0 identity a inc b))

(defn filtered-accumulate-product-gcd [n]
  (filtered-accumulate (fn [a]
                         (= (gcd a n) 1))
                       *
                       1
                       identity
                       1
                       inc
                       n))

;; 1.3.2
(defn pi-sum [a b]
  (sum #(/ 1.0 (* % (+ % 2)))
       a
       #(+ % 4)
       b))


(defn integral [f a b dx]
  (* (sum f
          (+ a (/ dx 2.0))
          #(+ % dx)
          b)
     dx))

(defn plus [x]
  (+ x 4))

(def plus4 #(+ % 4))

;; 1.3.3
(defn close-enough? [x y]
  (< (abs (- x y)) 0.001))

(defn search [f neg-point pos-point]
  (let [midpoint (average neg-point pos-point)]
    (if (close-enough? neg-point pos-point)
      midpoint
      (let [test-value (f midpoint)]
        (cond
          (pos? test-value) (search f neg-point midpoint)
          (neg? test-value) (search f midpoint pos-point)
          :else midpoint)))))

(defn half-interval-method [f a b]
  (let [a-value (f a)
        b-value (f b)]
    (cond
      (and (neg? a-value) (pos? b-value)) (search f a b)
      (and (neg? b-value) (pos? a-value)) (search f b a)
      :else (println "Values are not of opposite sign" a b))))

(defn sin [n]
  (Math/sin n))

(def tolerance 0.00001)

(defn fixed-point [f first-guess]
  (let [close-enough? (fn [v1 v2]
            (< (abs (- v1 v2)) tolerance))
        step (fn [guess]
              (let [next (f guess)]
                (if (close-enough? guess next)
                  next
                  (recur next))))]
    (step first-guess)))

(defn cos [d]
  (Math/cos d))

;; 无限循环
(defn sqrt-0 [x]
  (fixed-point #(/ x %) 1.0))

(defn sqrt-1 [x]
  (fixed-point #(average % (/ x %)) 1.0))

;; EX1.35
(defn ex-35 []
  (fixed-point #(+ 1 (/ 1.0 %)) 1.0))

;; EX1.36

;; EX1.37
(defn cont-frac [n d k]
  (let [step (fn step [i]
               (if (> i k)
                 0
                 (/ (n i) (+ (d i) (step (inc i))))))]
    (step 1)))

(defn cont-frac-iter [n d k]
  (let [step (fn [k r]
               (if (zero? k)
                 r
                 (recur (dec k) (+ (n k) (+ (d k) r)))))]
    (step k (/ (n k) (d k)))))


;; EX1.38
(defn ex-38 [])

(defn average-damp [f]
  #(average % (f %)))
((average-damp square) 10)

(defn sqrt [x]
  (fixed-point (average-damp #(/ x %)) 1.0))
(sqrt 4)

(defn cube-root [x]
  (fixed-point (average-damp #(/ x (square %))) 1.0))
(cube-root 27)

(def dx 0.00001)

(defn deriv [g]
  #(/ (- (g (+ % dx)) (g %))
      dx))

((deriv cube) 5)

(defn newton-transform [g]
  #(- % (/ (g %) ((deriv g) %))))

(defn newtons-method [g guess]
  (fixed-point (newton-transform g) guess))

(defn sqrt-newtons [x]
  (newtons-method #(- (square %) x) 1.0))

(defn fixed-point-of-transform [g transform guess]
  (fixed-point (transform g) guess))

(defn sqrt-fiexed-point [x]
  (fixed-point-of-transform #(/ x %)
                            average-damp
                            1.0))

(defn sqrt-3 [x]
  (fixed-point-of-transform #(- (square %) x)
                            newton-transform
                            1.0))