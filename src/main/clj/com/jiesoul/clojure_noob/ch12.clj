(ns com.jiesoul.clojure-noob.ch12)

(.toUpperCase "By Bluebeard's bananas!")

(.indexOf "Let's synergize our bleeding edges" "y")

(new String)
(String.)
(String. "To soul's Locker with ye hardies")

(java.util.Stack.)
(let [stack (java.util.Stack.)]
  (.push stack "Latest episode of Game of Thrones, ho!")
  stack)

(let [stack (java.util.Stack.)]
  (.push stack "Latest episode of Game of Thrones, ho!")
  (first stack))

(doto (java.util.Stack.)
  (.push "Latest episode of Game of Thrones, ho!")
  (.push "Whoops, I meant 'Land, ho!"))

(let [file (java.io.File. "/")]
  (println (.exists file))
  (println (.canWrite file))
  (println (.getPath file)))

