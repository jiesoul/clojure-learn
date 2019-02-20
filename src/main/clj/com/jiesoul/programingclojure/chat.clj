(ns com.jiesoul.programingclojure.chat)

(defrecord Message [sender text])

(def messages (ref()))

(defn add-message [msg]
  (dosync
   (alter messages conj msg)))
