(ns com.jiesoul.codewar.exclamation-mark)

(defn remove-bang [s]
  (str (clojure.string/replace s "!" "") "!"))
