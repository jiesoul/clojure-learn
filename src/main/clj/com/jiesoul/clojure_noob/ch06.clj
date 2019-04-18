(ns com.jiesoul.clojure-noob.ch06)

(def great-books ["East of Eden" "The Glass Bead Game"])
;great-books

(ns-interns *ns*)
(get (ns-interns *ns*) 'great-books)

;(deref #'user/great-books)

(defn latlng->point
  [latlng]
  (str (:lng latlng) "," (:lat latlng)))

(defn points
  [locations]
  (clojure.string/join " " (map latlng->point locations)))

