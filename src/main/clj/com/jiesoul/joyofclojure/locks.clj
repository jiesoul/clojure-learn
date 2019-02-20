(ns com.jiesoul.joyofclojure.locks
  (:refer-clojure :exclude [aget aset count seq])
  (:require [clojure.core :as clj]
            [com.jiesoul.joyofclojure.mutation :refer [dothreads!]]))

(defprotocol SafeArray
  (aset [this i f])
  (aget [this i])
  (count [this])
  (seq [this]))

(defn make-dumb-array [t sz]
  (let [a (make-array t sz)]
    (reify
      SafeArray
      (count [_] (clj/count a))
      (seq [_] (clj/seq a))
      (aget [_ i] (clj/aget a i))
      (aset [this i f]
        (clj/aset a i (f (aget this i)))))))
