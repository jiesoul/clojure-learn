(ns com.jiesoul.codewar.band-name-generator)

(defn generate-band-name [noun]
  (if (= (first noun) (last noun))
    (apply str (clojure.string/capitalize noun) (rest noun))
    (str "The " (clojure.string/capitalize noun))))

(generate-band-name "knife")
(generate-band-name "akka")
