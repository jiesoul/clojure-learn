(ns com.jiesoul.joyofclojure.DynaFrame
  (:gen-class
    :name com.jiesoul.joyofclojure.DynaFrame
    :extend javax.swing.JFrame
    :implements [clojure.lang.IMeta]
    :prefix df-
    :state state
    :init init
    :constructors {[String] [String]
                   []       [String]}
    :methods [[display [java.awt.Container] void]
              ^{:statia true} [version [] String]])
  (:import [javax.swing JFrame JPanel JComponent]
           [java.awt BorderLayout Container]))

(defn df-init [title]
  [[title] (atom {::title title})])
