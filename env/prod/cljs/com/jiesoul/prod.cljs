(ns com.jiesoul.prod
  (:require [com.jiesoul.core :as core]))

;; 生产环境忽略打印
(set! *print-fn* (fn [& _]))

(core/init!)