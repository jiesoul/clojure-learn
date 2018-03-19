(ns clojure-noob.macro.core)

(defmacro backwards
  [form]
  (reverse form))

(def addtition-list (list + 1 2))
(eval addtition-list)

(eval (concat addtition-list [1]))

(read-string "(+ 1 2)")

(list? (read-string "(+ 1 2)"))

(conj (read-string "(+ 1 2)") :zagglewag)

(read-string "#(+ % 1)")                                    ; # 匿名函数宏字符

(read-string "@var")                                        ; @ 是 deref 的宏字符

(read-string "; ignore\n(+ 1 2)")                           ; 分号单行注释 忽略宏字符

