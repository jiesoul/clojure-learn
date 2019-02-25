(ns com.jiesoul.codewar.regex-vowel)

(defn vowel [str]
  (let [v #{"A" "E" "I" "O" "U"
            "a" "e" "i" "o" "u"}]
    (contains? v str)))

(vowel "")
(vowel "a")
(vowel "E")
(vowel "AE")
