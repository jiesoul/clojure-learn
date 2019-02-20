(ns com.jiesoul.dataclojure.ch03
  (:require [clojure.java.io :as io]
            [clojure.data.csv :as csv]))

(def data-file "data/all_160_in_51.P35.csv")

(defn lazy-read-csv
  [csv-file]
  (let [in-file (io/reader csv-file)
        csv-seq (csv/read-csv in-file)
        lazy (fn lazy [wrapped]
               (lazy-seq
                 (if-let [s (seq wrapped)]
                   (cons (first s) (lazy (rest s)))
                   (.close in-file))))]
    (lazy csv-seq)))

(def total-hu (ref 0))
(def total-fams (ref 0))

(defn with-header [coll]
  (let [headers (map keyword (first coll))]
    (map (partial zipmap headers) (next coll))))

(defn ->int ([i] (Integer. i)))

(defn sum-item
  ([fields] (partial sum-item fields))
  ([fields accum item]
    (mapv + accum (map ->int (map item fields)))))

(defn sum-items
  [accum fields coll]
  (reduce (sum-item fields) accum coll))

(defn update-totals [fields items]
  (let [mzero (mapv (constantly 0) fields)
        [sum-hu sum-fams] (sum-items mzero fields items)]
    (dosync
      (alter total-hu #(+ sum-hu %))
      (alter total-fams #(+ sum-fams %)))))

(defn update-totals [fields items]
  (let [mzero (mapv (constantly 0) fields)
        [sum-hu sum-fams] (sum-items mzero fields items)]
    (dosync
      (commute total-hu #(+ sum-hu %))
      (commute total-fams #(+ sum-fams %)))))

(defn thunk-update-totals-for
  [fields data-chunk]
  (fn [] (update-totals fields data-chunk)))

(defn main
  ([data-file] (main data-file [:HU100 :P035001] 5))
  ([data-file fields chunk-count]
    (doall
      (->>
        (lazy-read-csv data-file)
        with-header
        (partition-all chunk-count)
        (map (partial thunk-update-totals-for fields))
        (map future-call)
        (map deref)))
    (float (/ @total-fams @total-hu))))

(main data-file)

(defn accum-sums [a b] (mapv + a b))

(defn div-vec [[a b]] (float (/ a b)))

(defn force-val
  [a]
  (await a)
  @a)

(defn main
  ([data-file] (main data-file [:P035001 :HU100] 5 5))
  ([data-file fields agent-count chunk-count]
    (let [mzero (mapv (constantly 0) fields)
          agents (map agent (take agent-count (repeat mzero)))]
      (dorun
        (->>
          (lazy-read-csv data-file)
          with-header
          (partition-all chunk-count)
          (map #(send %1 sum-items fields %2)
               (cycle agents))))
      (->>
        agents
        (map force-val)
        (reduce accum-sums mzero)
        div-vec))))

(main data-file)