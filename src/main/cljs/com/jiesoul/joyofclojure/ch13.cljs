(ns com.jiesoul.joyofclojure.ch13
  (:require [clojure.walk :refer [prewalk]]
            [clojure.pprint :refer [pprint]]
            [cljs.compiler :as comp]
            [cljs.analyzer :as ana]))

(def code-string "(defn hello [x] (js/alert (pr-str 'greeting x)))")
;(def code-data (read-string code-string))

(defn print-ast [ast]
  (pprint
    (prewalk
      (fn [x]
        (if (map? x)
          (select-keys x [:children :name :form :op])
          x))
      ast)))

(defn soft-attack
  [ctx {:keys [volume delay duration]}]
  (let [node (.createGainNode ctx)]
    (doto
      (.-gain node)
      (.linearRampToValueAtTime 0 delay)
      (.linearRampToValueAtTime volume (+ delay 0.05))
      (.linearRampToValueAtTime 0 (+ delay duration)))
    node))

(defn sine-tone
  [ctx {:keys [cent delay duration]}]
  (let [node (.createOscillator ctx)]
    (set! (-> node .-frequency .-value) 440)
    (set! (-> node .-detune .-value) (- cent 900))
    (.noteOn node delay)
    (.noteOff node (+ delay duration))
    node))

(defn connect-to
  [node1 node2]
  (.connect node1 node2))

(defn woo
  [ctx node]
  (let [linger 1.5
        node (update-in node [:duration] * linger)]
    (-> (sine-tone ctx node)
        (connect-to (soft-attack ctx node)))))

(def make-once (memoize (fn [ctor] (new ctor))))

(defn play!
  [note-fn notes]
  (if-let [ctor (or (.-AudioContext js/window)
                    (.-webkitAudioContext js/window))]
    (let [ctx (make-once ctor)
          compressor (.createDynamicsCompressor ctx)]
      (let [now (.-currentTime ctx)]
        (doseq [note notes]
          (->
            (note-fn ctx (update-in note [:delay] + now))
            (connect-to compressor))))
      (connect-to compressor (.-destination ctx)))
    (js/alert "Sorry, this browser doesn't support AudioContext")))


