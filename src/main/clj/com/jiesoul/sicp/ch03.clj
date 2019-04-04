(ns com.jiesoul.sicp.ch03)

(def balance 100)

(defn withdraw [amount]
  (if (>= balance amount)
    (do
      (alter balance (- balance amount))
      balance)
    "Insufficient funds"))

(withdraw 50)