(ns com.jiesoul.sicp.ch03
  (:require [com.jiesoul.sicp.ch01 :refer [gcd sqrt]]))

(defn make-account [start-balance]
  (let [balance (atom start-balance)]
    (letfn [(withdraw [amount]
              (if (>= @balance amount)
                (swap! balance - amount)
                "Not enough money"))
            (deposit [amount]
              (swap! balance + amount))]
      (fn [m amount]
        (cond
          (= m 'withdraw) (withdraw amount)
          (= m 'deposit) (deposit amount)
          :else (throw (Exception. (str "Unknown request -- MAKE-ACCOUNT" m))))))))


;; EX3.1
(defn make-accumulator [n]
  (let [sum (atom n)]
    (fn [m]
      (swap! sum + m))))

;; EX3.2
(defn make-monitored [f]
  (let [c (atom 0)]
    (fn [arg]
      (cond
        (= arg 'how-many-calls?) @c
        (= arg 'reset-count) (reset! c 0)
        :else (do
                (swap! c inc)
                (f arg))))))


;; EX3.3
(defn make-account-secret [start-balance password]
  (let [balance (atom start-balance)
        pass    (atom password)]
    (letfn [(withdraw [amount]
              (if (>= @balance amount)
                (swap! balance - amount)
                "Not enough money"))
            (deposit [amount]
              (swap! balance + amount))]
      (fn [password m amount]
        (if (= @pass password)
          (condp = m
            'withdraw (withdraw amount)
            'deposit (deposit amount)
            :else (str "Unknown requrest -- MAKE-ACCOUNT-SECRET"))
          (str "Incorrect password"))))))

;; EX3.4
(defn make-account-secret-warn [start-balance password]
  (let [balance (atom start-balance)
        pass    (atom password)
        warn    (atom 0)]
    (letfn [(withdraw [amount]
              (if (>= @balance amount)
                (swap! balance - amount)
                "Not enough money"))
            (deposit [amount]
              (swap! balance + amount))]
      (fn [password m amount]
        (cond
          (>= @warn 7) (str "call-the-cops")
          (= @pass password)
          (condp = m
            'withdraw (withdraw amount)
            'deposit (deposit amount)
            :else (str "Unknown requrest -- MAKE-ACCOUNT-SECRET"))
          :else
          (do
            (swap! warn inc)
            (str "Incorrect password")))))))

;; 3.1.2
(defn rand-init []
  (atom (rand-int 100)))

(defn rand-update [x]
  (reset! x (rand-int 100)))

(defn rand-my []
  (let [x (rand-init)]
    #(swap! x rand-update x)
    x))

(defn cesaro-test []
  (= (gcd (rand-my) (rand-my)) 1))

(defn monte-carlo [trials experiment]
  (loop [trials-remaining trials
         trials-passed 0]
    (cond
      (zero? trials-remaining) (/ trials-passed trials)
      (experiment) (recur (dec trials-remaining) (inc trials-passed))
      :else (recur (dec trials-remaining) trials-passed))))

(defn estimate-pi [trials]
  (sqrt (/ 6 (monte-carlo trials cesaro-test))))

(defn random-gcd-test [trials initial-x]
  (loop [trials-remaining trials
         trials-passed 0
         x initial-x]
    (let [x1 (rand-update x)
          x2 (rand-update x1)]
      (cond
        (zero? trials-remaining) (/ trials-passed trials)
        (= (gcd x1 x2) 1) (recur (dec trials-remaining) (inc trials-passed) x2)
        :else (recur (dec trials-remaining) trials-passed x2)))))

;; EX3.5
(defn random-in-range [low high]
  (let [range (- high low)]
    (+ low (rand-int range))))

(defn estimate-integral [p x1 x2 y1 y2 trials]
  (let [area (* (- x2 x1) (- y2 y1))]
    (* area
       (monte-carlo trials #(p (random-in-range x1 x2)
                               (random-in-range y1 y2))))))

;; EX3.6
(defn rand-1 [s]
  (let [x (rand-init)]
    (cond
      (= s 'generate) x
      (= s 'reset) (rand-update x)
      :else (str "invalid"))))

;; 3.1.3
(defn make-simplified-withdraw [balance]
  (let [b (atom balance)]
    (fn [amount]
      (swap! b - amount)
      @b)))

(defn make-decrementer [balance]
  (fn [amount]
    (- balance amount)))

(defn factorial-1 [n]
  (let [product (atom 1)
        counter (atom 1)]
    (loop []
      (if (> @product n)
        @product
        (do
          (swap! product * counter)
          (swap! counter inc)
          (recur))))))

;; EX3.7
(defn make-joint [acct password new-pass]
  (fn [p m]
    (if (= p new-pass)
      (acct password m)
      (throw (RuntimeException. "Password incorrect.")))))

;; EX3.8
(defn ex-38 [n]
  (println n)
  n)

(+ (ex-38 0) (ex-38 1))

;; 3.2

;; EX3.9


(def make-wire)
(def or-gate)
(def and-gate)
(def inverter)

(defn half-adder [a b s c]
  (let [d (make-wire)
        e (make-wire)]
    (or-gate a b d)
    (and-gate a b c)
    (inverter c e)
    (and-gate d e s)
    'ok))

(defn full-adder [a b c-in sum c-out]
  (let [s  (make-wire)
        c1 (make-wire)
        c2 (make-wire)]
    (half-adder b c-in s c1)
    (half-adder a s sum c2)
    (or-gate c1 c2 c-out)
    'ok))

(defn inverter [input output]
  )
