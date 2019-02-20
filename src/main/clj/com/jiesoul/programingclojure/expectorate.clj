(ns com.jiesoul.programingclojure.expectorate
  (:import (java.io FileOutputStream OutputStreamWriter BufferedWriter)))

(defn expectorate [dst content]
  (with-open
    [writer (-> dst
                FileOutputStream.
                OutputStreamWriter.
                BufferedWriter.)]
    (.write writer (str content))))

(defn make-writer [dst]
  (-> dst
      FileOutputStream.
      OutputStreamWriter.
      BufferedWriter.))


