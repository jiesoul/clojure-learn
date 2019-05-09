(ns com.jiesoul.tips.made-easy-remote-calls
  (:require [cljs-http.client :as http]
            [cljs.core.async :refer [<!]])
  (:require-macros [cljs.core.async.macros :refer [go]]))

(defn make-remote-call [endpoint]
  (go
    (let [response (<! (http/get endpoint))]
      (js/console.log (:body response)))))

(make-remote-call "/response/clojure-langs.json")