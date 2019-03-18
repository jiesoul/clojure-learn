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

(defn f-11 [n]
  (if (< n 3)
    n
    (+ (f-11 (dec n)) (* 2 (f-11 (- n 2))) (* 3 (f-11 (- n 3))))))

;;
(defn f-11-iter [n]
  (let [step (fn [f s t n]
               ())]
    (step 0 0 0 n)))

(defn f-12 [n]
  (if (= n 1)
    [1]
    (let [new (f-12 (dec n))]
      (mapv + (conj new 0) (cons 0 new)))))


;; ex1.16
(defn fast-exqt-iter [b n]
  )