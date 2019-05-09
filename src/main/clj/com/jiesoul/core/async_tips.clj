(ns com.jiesoul.core.async-tips
  (:require [clojure.core.async :as a]))

(declare load-page! WAIT-BETWEEN-LOAD num-pages)

(def NUM-PAGES-PER-LOAD 8)

(defn fill-load-chan [number-of-pages]
  (let [partitionos (partition-all NUM-PAGES-PER-LOAD (range 1 (inc number-of-pages)))
        c           (a/chan)]
    (a/go
      (doseq [page-numbers partitionos]
        (a/>! c page-numbers))
      (a/close! c))
    c))

(defn load-from-chan
  [c]
  (a/go
    (loop []
      (when-let [page-numbers (a/<! c)]
        (doseq [page-number page-numbers]
          (load-page! page-number))
        (a/<! (a/timeout WAIT-BETWEEN-LOAD))
        (recur)))))

(def c (fill-load-chan num-pages))
(load-from-chan c)