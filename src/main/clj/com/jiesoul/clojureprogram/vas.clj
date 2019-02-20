(ns com.jiesoul.clojureprogram.vas)

(def ^:private everything 42)

(def max-value 255)
(defn valid-value?
  [v]
  (<= v max-value))
(valid-value? 218)
(valid-value? 299)
(def max-value 500)
(valid-value? 299)

(def ^:const max-value 255)
(defn valid-value?
  [v]
  (<= v max-value))
(def max-value 500)
(valid-value? 299)

(let [a 1
      b 2]
  (println (+ a b))
  (let [b 3
        + -]
    (println (+ a b))))

(def ^:dynamic *max-value* 255)
(defn valid-value?
  [v]
  (<= v *max-value*))
;(binding [*max-value* 500]
;  (valid-value? 299))
;(binding [*max-value* 500]
;  (println (valid-value? 299))
;  (doto (Thread. #(println "in other thread:" (valid-value? 299)))
;    .start
;    .join))

(def ^:dynamic *var* :root)
(defn get-*var* [] *var*)
(binding [*var* :a]
  (binding [*var* :b]
    (binding [*var* :c]
      (get-*var*))))

(defn http-get
  [url-string]
  (let [conn (-> url-string java.net.URL. .openConnection)
        response-code (.getResponseCode conn)]
    (if (== 404 response-code)
      [response-code]
      [response-code (-> conn .getInputStream slurp)])))
;(http-get "http://google.com/bad-url")
;(http-get "http://google.com")

(def ^:dynamic *res-code* nil)
(defn http-get
  [url]
  (let [conn (-> url java.net.URL. .openConnection)
        res-code (.getResponseCode conn)]
    (when (thread-bound? #'*res-code*)
      (set! *res-code* res-code))
    (when (not= 404 res-code)
      (-> conn .getInputStream slurp))))
;(http-get "http://google.com")
*res-code*
(binding [*res-code* nil]
  (let [content (http-get "http://google.com/bad-url")]
    (println "Response code was:" *res-code*)))
