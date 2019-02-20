(ns com.jiesoul.dataclojure.csv
  (:require [incanter.core :refer :all]
            [incanter.io :refer :all]))

(read-dataset "data/small-sample.csv")
(read-dataset "data/small-sample.csv" :header true)

