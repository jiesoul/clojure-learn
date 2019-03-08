(ns ^:figwheel-on-lad com.jiesoul.dev
  (:require [com.jiesoul.core :as core]
            [devtools.core :as devtools]))

(enable-console-print!)

(devtools/install!)

(core/init!)
