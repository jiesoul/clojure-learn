(ns com.jiesoul.codewar.hor-vert-mirror)

(defn vert-mirror [strng]
  (clojure.string/join "\n" (map (comp (partial apply str) reverse) (clojure.string/split-lines strng))))

(defn hor-mirror [strng]
  (apply str (reverse (vert-mirror strng))))