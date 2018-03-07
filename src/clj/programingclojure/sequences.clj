(ns programingclojure.sequences)
(use '[clojure.java.io :only (reader)])

(defn minutes-to-millis [mins] (* mins 1000 60))

(defn recently-modifyied? [file]
  (> (.lastModified file)
     (- (.lastModified file)
        (- (System/currentTimeMillis) (minutes-to-millis 30)))))

(defn non-blank? [line]
  (if (re-find #"\S" line) true false))

(defn non-git? [file]
  (not (.contains (.toString file) ".git")))

(defn clojure-file? [file]
  (.endsWith (.toString file) ".clj"))

(defn clojure-loc [base-file]
  (reduce
   +
   (for [file (file-seq base-file)
         :when (and (clojure-file? file) (non-git? file))]
     (with-open [rdr (reader file)]
       (count (filter non-blank? (line-seq rdr)))))))
