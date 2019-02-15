(ns com.jiesoul.the-divine-cheese-code.core
  (:gen-class))

(read-string "(+ 1 2)")
(list? (read-string "(+ 1 2)"))
(conj (read-string "(+ 1 2)") :zagglewag)
(eval (read-string "(+ 1 2)"))
