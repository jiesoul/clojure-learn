(ns user
  (:require [figwheel-sidecar.repl-api :as fig-repl]
            [midje.repl :as midje-repl]))

(defn start-fw []
  (fig-repl/start-figwheel!))


(defn stop-fw []
  (fig-repl/stop-figwheel!))

(defn cljs []
  (fig-repl/cljs-repl))


(defn autotest []
  (midje-repl/autotest))