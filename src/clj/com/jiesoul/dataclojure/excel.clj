(ns com.jiesoul.dataclojure.excel
  (:require [incanter.core :refer :all]
            [incanter.excel :refer :all]))

(read-xls "data/small-sample-header.xls")
