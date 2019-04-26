(ns com.jiesoul.joyofclojure.ch14
  (:require [clojure.edn :as edn]))

(def ascii (map char (range 65 (+ 65 26))))
(defn rand-str [sz alphabet]
  (apply str (repeatedly sz #(rand-nth alphabet))))
(rand-str 10 ascii)
(def rand-sym #(symbol (rand-str %1 %2)))
(def rand-key #(keyword (rand-str %1 %2)))
(rand-key 10 ascii)
(rand-sym 10 ascii)
(defn rand-vec [& generators]
  (into [] (map #(%) generators)))
(rand-vec #(rand-sym 5 ascii)
  #(rand-key 10 ascii)
  #(rand-int 1024))
(defn rand-map [sz kgen vgen]
  (into {}
    (repeatedly sz #(rand-vec kgen vgen))))
(rand-map 3 #(rand-key 5 ascii) #(rand-int 100))

#inst "1969-08-18"

(edn/read-string "#uuid \"dae78a90-d491-11e2-8b8b-0800200c9a66\"")
(edn/read-string "42")
(edn/read-string "{:a 42, \"b\" 36, [:c] 9}")

(defn valid? [event]
  (boolean (:result event)))
(valid? {})
(valid? {:result 42})

(defn effect [{:keys [ab h] :or {ab 0, h 0}}
              event]
  (let [ab (inc ab)
        h (if (= :hit (:result event))
            (inc h)
            h)
        avg (double (/ h ab))]
    {:ab ab :h h :avg avg}))
(effect {} {:result :hit})
(effect {:ab 599 :h 180} {:result :out})

(defn apply-effect [state event]
  (if (valid? event)
    (effect state event)
    state))
(apply-effect {:ab 600 :h 180 :avg 0.3}
              {:result :hit})

(def effect-all #(reduce apply-effect %1 %2))
(effect-all {:ab 0 :h 0}
            [{:result :hit}
             {:result :out}
             {:result :hit}
             {:result :out}])

(def events (repeatedly 100
              (fn []
                (rand-map 1
                  #(-> :result)
                  #(if (< (rand-int 10) 3)
                     :hit
                     :out)))))
(effect-all {} events)
(effect-all {} (take 50 events))
(def fx-timeline #(reductions apply-effect %1 %2))
(fx-timeline {} (take 3 events))