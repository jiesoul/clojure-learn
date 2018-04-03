(ns dataclojure.json
  (:require [incanter.core :refer :all]
            [clojure.data.json :refer :all]
            [clojure.java.jdbc ]))

(to-dataset (read-json (slurp "data/small-sample.json")))
