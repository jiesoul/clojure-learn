(ns com.jiesoul.sicp.ex01)

;; 1.2
(/ (+ 5 4 (- 2 (- 3 (+ 6 (/ 4 5)))))
   (* 3 (- 6 2) (- 2 7)))

;; 1.3
(defn max-three [a b c]
  (cond
    (or (> b a c) (> a b c)) (+ a b)
    (or (> c b a) (> b c a)) (+ b c)
    :else (+ c a)))

;; 1.4
(defn a-plus-abs-b [a b]
  ((if (> b 0) + 1) a b))

;; 1.5 采用正则序 p 会无限展开，

;; 1.6 因为使用应用序值 predicate 会无限展开

;; 1.9
(defn new-plus [a b]
  (if (zero? a)
    b
    (inc (+ (dec a) b))))


;; 1.10
(defn A [x y]
  (cond
    (zero? y) 0
    (zero? x) (* 2 y)
    (= y 1) 2
    :else (A (dec x) (A x (dec y)))))

;; 1.11
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

;; 1.12
(defn f-12 [n]
  (if (= n 1)
    [1]
    (let [new (f-12 (dec n))]
      (mapv + (conj new 0) (cons 0 new)))))


;; 1.16
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

;; 1.17
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

;; 1.18
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

;; 1.19
(defn fib-iter [a b p q count]
  (cond
    (zero? count) b
    (even? count) (fib-iter a b p q (/ count 2))
    :else (fib-iter (+ (* b q) (* a q) (* a p))
            (+ (* b p) (* a q))
            p
            q
            (- count 1))))

(defn fib [n]
  (fib-iter 1 0 0 1 n))
