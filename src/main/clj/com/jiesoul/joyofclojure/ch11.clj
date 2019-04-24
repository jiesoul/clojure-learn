(ns com.jiesoul.joyofclojure.ch11
  (:require [com.jiesoul.joyofclojure.ch10 :refer [dothreads!]]
            [clojure.xml :as xml]
            [clojure.zip :as zip]
            [clojure.core.reducers :as r])
  (:import [java.util.regex Pattern]))

(time (let [x (future (do (Thread/sleep 5000) (+ 41 1)))]
        [@x @x]))

(defn feed->zipper [uri-str]
  (->> (xml/parse uri-str)
       zip/xml-zip))

(defn normalize [feed]
  (if (= :feed (:tag (first feed)))
    feed
    (zip/down feed)))

(defn feed-children [uri-str]
  (->> uri-str
       feed->zipper
       normalize
       zip/children
       (filter (comp #(:item :entry) :tag))))

(defn title [entry]
  (some->> entry
           :content
           (some #(when (= :title (:tag %)) %))
           :content
           first))

(defn count-text-task [extractor txt feed]
  (let [items (feed-children feed)
        re (Pattern/compile (str "(?i)" txt))]
    (->> items
         (map extractor)
         (mapcat #(re-seq re %))
         (count))))

;(count-text-task
;  title
;  "Erlang"
;  "http://feeds.feedburner.com/ElixirLang")
;
;(count-text-task
;  title
;  "Elixir"
;  "http://feeds.feedburner.com/ElixirLang")

(def feeds #{})
(let [results (for [feed feeds]
                (future
                  (count-text-task title "Elixir" feed)))]
  (reduce + (map deref results)))

(defmacro as-futures [[a args] & body]
  (let [parts (partition-by #{'=>} body)
        [acts _ [res]] (partition-by #{:as} (first parts))
        [_ _ task] parts]
    `(let [~res (for [~a ~args] (future ~@acts))]
       ~@task)))

(defn occurrences [extractor tag & feeds]
  (as-futures [feed feeds]
    (count-text-task extractor tag feed)
    :as results
    =>
    (reduce + (map deref results))))

(def x (promise))
(def y (promise))
(def z (promise))
(dothreads! #(deliver z (+ @x @y)))

(defmacro with-promise [[n tasks _ as] & body]
  (when as
    `(let [tasks# ~tasks
           n# (count tasks#)
           promises# (take n# (repeatedly promise))]
       (dotimes [i# n#]
         (dothreads!
           (fn []
             (deliver (nth promises# i#)
               ((nth tasks# i#))))))
       (let [~n tasks#
             ~as promises#]
         ~@body))))
(defrecord TestRun [run passed failed])
(defn pass [] true)
(defn fail [] false)
(defn run-tests [& all-tests]
  (with-promise
    [tests all-tests :as results]
    (into (TestRun. 0 0 0)
      (reduce #(merge-with + %1 %2) {}
        (for [r results]
          (if @r
            {:run 1 :passed 1}
            {:run 1 :fialed 1}))))))
(defn feed-items [k feed]
  (k
    (for [item (filter (comp #{:entry :item} :tag)
                 (feed-children feed))]
      (-> item
        :content
        first
        :content))))

(defn cps->fn [f k]
  (fn [& args]
    (let [p (promise)]
      (apply f (fn [x] (deliver p (k x))) args)
      @p)))

(def count-items (cps->fn feed-items count))

(def kant (promise))
(def hume (promise))
(dothreads!
  #(do (println "Kant has" @kant) (deliver hume :thinking)))

(dothreads!
  #(do (println "Hume is" @hume) (deliver kant :fork)))

(pvalues 1 2 (+ 1 2))
(defn sleeper [s thing] (Thread/sleep (* 1000 s)) thing)
(defn pvs []
  (pvalues
    (sleeper 2 :1st)
    (sleeper 3 :2nd)
    (keyword "3rd")))
(-> (pvs) first time)
(-> (pvs) last time)

(->> [1 2 3]
  (pmap (comp inc (partial sleeper 2)))
  doall
  time)

(-> (pcalls
      #(sleeper 2 :first)
      #(sleeper 3 :second)
      #(keyword "3rd"))
  doall
  time)

(def big-vec (vec (range (* 1000 1000))))
(time (reduce + big-vec))
(time (r/fold + big-vec))
