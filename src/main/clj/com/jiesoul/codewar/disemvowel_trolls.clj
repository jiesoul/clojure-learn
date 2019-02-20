(ns com.jiesoul.codewar.disemvowel-trolls)

(defn disemvowel
  [string]
  (clojure.string/replace string #"[a|A|e|E|i|I|o|O|u|U]" ""))