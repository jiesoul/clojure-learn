(ns com.jiesoul.programingclojure.protocols
  (:import (java.io InputStream FileInputStream InputStreamReader BufferedReader
                    OutputStream FileOutputStream OutputStreamWriter BufferedWriter
                    File)
           (java.net Socket URL)))


(defn make-reader [src]
  (-> (condp = (type src)
        java.io.InputStream src
        java.lang.String (FileInputStream. src)
        java.io.File (FileInputStream. src)
        java.net.Socket (.getInputStream src)
        java.net.URL (if (= "file" (.getProtocol src))
                       (-> src .getPath FileInputStream.)
                       (.openStream src)))
      InputStreamReader.
      BufferedReader.))

(defn make-writer [dst]
  (-> (condp = (type dst)
        java.io.OutputStream dst
        java.io.File (FileOutputStream. dst)
        java.lang.String (FileOutputStream. dst)
        java.net.Socket (.getOutputStream dst)
        java.net.URL (if (= "file" (.getProtocol dst))
                       (-> dst .getPath FileOutputStream.)
                       (throw (IllegalArgumentException.
                               "Can't write to non-file URL"))))
      OutputStreamWriter.
      BufferedWriter.))

(defn gulp [src]
  (let [sb (StringBuilder.)]
    (with-open
      [reader (make-reader src)]
      (loop [c (.read reader)]
        (if (neg? c)
          (str sb)
          (do
            (.append sb (char c))
            (recur (.read reader))))))))

(defn expectorate [dst content]
  (with-open
    [writer (make-writer)]
    (.write writer (str content))))

(defprotocol IOFactory
  (make-reader [this] "Creates a BufferedReader.")
  (make-writer [this] "Creates a BufferedWriter"))

(extend InputStream
  IOFactory
  {:make-reader (fn [src]
                  (-> src
                      InputStreamReader.
                      BufferedReader.))
   :make-writer (fn [dst]
                  (throw (IllegalArgumentException.
                          "Can't open as an InputStream.")))})

(extend OutputStream
  IOFactory
  {:make-writer (fn [dst]
                  (-> dst
                      OutputStreamWriter.
                      BufferedWriter.))
   :make-reader (fn [src]
                  (throw
                   (IllegalArgumentException.
                    "Can't open a InputStreamReader.")))})

(extend-type File
  IOFactory
  (make-reader [src]
    (make-reader (FileInputStream. src)))
  (make-writer [dst]
    (make-writer (FileOutputStream. dst))))

(extend-protocol IOFactory
  InputStream
  (make-reader [src]
    (make-reader (-> src InputStreamReader. BufferedReader.)))
  (make-writer [dst]
    (throw (IllegalArgumentException. "Can't open as an InputStreamReader")))
  OutputStreamWriter
  (make-reader [src]
    (throw (IllegalArgumentException. "Can't open as an OutputStreamWriter")))
  (make-writer [dst]
    (make-writer (-> dst OutputStreamWriter. BufferedWriter.)))
  File
  (make-reader [src]
    (make-reader (FileInputStream. src)))
  (make-writer [dst]
    (make-writer (FileOutputStream. dst)))
  Socket
  (make-reader [src]
    (make-reader (.getInputStream src)))
  (make-writer [dst]
    (make-writer (.getOutputStream dst)))
  URL
  (make-reader [src]
    (make-reader (if (= "file" (.getProtocol src))
                   (-> src .getPath FileInputStream.)
                   (.openStream src))))
  (make-writer [dst]
    (make-writer (if (= "file" (.getProtocol dst))
                   (-> dst .getPath FileInputStream.)
                   (throw (IllegalArgumentException. "Can't write to non-file URL"))))))



