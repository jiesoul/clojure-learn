(ns com.jiesoul.clojure-noob.ch11
  (:require [clojure.core.async :as a :refer [chan]]))

(def echo-chan (clojure.core.async/chan))
(clojure.core.async/go (println (clojure.core.async/<! echo-chan)))
(clojure.core.async/>!! echo-chan "ketchup")

(def echo-buffer (clojure.core.async/chan 2))
(clojure.core.async/>!! echo-buffer "ketchup")

(def hi-chan (clojure.core.async/chan))
(doseq [n (range 1000)]
  (clojure.core.async/go (clojure.core.async/>! hi-chan (str "hi " n))))

(clojure.core.async/thread (println (clojure.core.async/<!! echo-chan)))
(clojure.core.async/>!! echo-chan "mustard")
