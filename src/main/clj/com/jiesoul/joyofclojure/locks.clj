(ns com.jiesoul.joyofclojure.locks
  (:use [com.jiesoul.joyofclojure.mutation :refer [dothreads!]]))

(defprotocol SafeArray
  (aset-1 [this i f])
  (aget-1 [this i])
  (count-1 [this])
  (seq-1 [this]))

(defn make-dumb-array [t sz]
  (let [a (make-array t sz)]
    (reify
      SafeArray
      (count-1 [_] (count a))
      (seq-1 [_] (seq a))
      (aget-1 [_ i] (aget a i))
      (aset-1 [this i f]
        (aset a i (f (aget-1 this i)))))))

(defn pummel [a]
  (dothreads! #(dotimes [i (count a)]
                 (aset a i inc))
    :threads 100))

(def D (make-dumb-array Integer/TYPE 8))
