(ns com.jiesoul.sicp.ch01)

;; 1.1

486

;; 1.4
(defn square [x]
  (* x x))

(defn sum-of-square [x y]
  (+ (square x) (square y)))

(defn f [a]
  (sum-of-square (+ a 1) (* a 2)))

(defn abs [x]
  (cond
    (pos? x) x
    (zero? x) 0
    :else (- x)))

(defn abs-1 [x]
  (cond
    (neg? x) (- x)
    :else x))

(defn abs-2 [x]
  (if (neg? x)
    (- x)
    x))

(defn abs-3 [n]
  (max n (- n)))

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


(defn new-if [predicate then-clause else-clause]
  (cond
    predicate then-clause
    :else else-clause))

(defn new-sqrt-iter [guess x]
  (new-if (good-enough? guess x)
          guess
          (new-sqrt-iter (improve guess x) x)))

(defn new-sqrt [x]
  (new-sqrt-iter 1.0 x))

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

(defn fib [n]
  (if (< n 2)
    n
    (+ (fib (dec n)) (fib (- n 2)))))

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

(defn cube [x]
  (* x x x))

(defn p [x]
  (- (* 3 x) (* 4 (cube x))))

(defn sine [angle]
  (if (not (> (Math/abs angle) 0.1))
    angle
    (p (sine (/ angle 3.0)))))

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

(defn gcd [a b]
  (if (zero? b)
    a
    (recur b (rem a b))))

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

;; 1.3

(defn cube [x]
  (* x x x))

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