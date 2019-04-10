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

(defn rand []
  (let [x (rand-init)]
    #(swap! x rand-update x)
    x))

(defn cesaro-test []
  (= (gcd (rand) (rand)) 1))

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