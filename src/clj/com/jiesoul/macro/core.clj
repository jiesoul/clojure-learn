(ns com.jiesoul.macro.core)

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

(defmacro ignore-last-operand
  [function-call]
  (butlast function-call))

(ignore-last-operand (+ 1 2 10))
(ignore-last-operand (+ 1 2 (println "look at me!!!")))

(defn read-resource
  [path]
  (read-string (slurp (clojure.java.io/resource path))))

(defn read-resource
  [path]
  (-> path
      clojure.java.io/resource
      slurp
      read-string))

(defmacro report
  [to-try]
  `(if ~to-try
     (println (quote ~to-try) "was successful:" ~to-try)
     (println (quote ~to-try) "was not successful:" ~to-try)))
(report (do (Thread/sleep 1000) (+ 1 1)))

(defmacro report
  [to-try]
  `(let [result# ~to-try]
     (if result#
       (println (quote ~to-try) "was successful:" result#)
       (println (quote ~to-try) "was not successful:" result#))))
(report (do (Thread/sleep 1000) (+ 1 1)))

(report (= 1 1))
(report (= 1 2))
(doseq [code ['(= 1 1) '(= 1 2)]]
  (report code))

(defmacro doseq-macro
  [macroname & args]
  `(do
     ~@(map (fn [arg] (list macroname arg)) args)))
(doseq-macro report (= 1 1) (= 1 2))
