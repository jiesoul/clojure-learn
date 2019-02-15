(ns com.jiesoul.data-analysis.network-six.ego
  (:require [clojure.java.io :as io]
            [clojure.set :as set]
            [clojure.string :as string]
            [clojure.data.json :as json]
            [clojure.core.reducers :as r]
            [data-analysis.network-six.graph :as g]
            [me.raynes.fs :as fs])
  (:import [java.io File]))

(defn read-edge-file [filename]
  (with-open [f (io/reader filename)]
    (->> f
         line-seq
         (r/map #(string/split % #"\s+"))
         (r/map #(mapv (fn [x] (Long/parseLong x)) %))
         (r/reduce #(g/add %1 (first %2) (second %2))
                   g/empty-graph))))

(defn read-edge-files [ego-dir]
  (r/reduce g/merge-graph {}
            (r/map read-edge-file
                   (fs/find-files ego-dir #".*\.edges$"))))

(def graph (read-edge-files "data/facebook/"))

(count (g/get-vertices graph))

(count (g/get-edges graph))

(g/density graph)

(g/avg-degree graph)



