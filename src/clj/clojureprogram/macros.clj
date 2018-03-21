(ns clojureprogram.macros
  (:require [clojure.string :as str]
            [clojure.walk :as walk]))

(defmacro reverse-it
  [form]
  (walk/postwalk #(if (symbol? %)
                    (symbol (str/reverse (name %)))
                    %)
                 form))

(macroexpand-1 '(reverse-it
   (qesod [gra (egnar 5)]
          (nltnirp (cni gra)))))

(defmacro hello
  [name]
  (list 'println name))

(macroexpand-1 '(hello "soul"))

(defmacro while
  [test & body]
  (list 'loop []
        (concat (list 'when test) body)
        '(recur)))

(defmacro while
  [test & body]
  `(loop []
     (when ~test
       ~@body
       (recur))))

(def foo 123)

`(map println [~foo])
`(println ~(keyword (str foo)))

(let [defs '((def x 123)
             (def y 456))]
  (concat (list 'do) defs))

(let [defs '((def x 123)
             (def y 456))]
  `(do ~@defs))

`'(map println ~[foo])

(defn hello
  [name]
  (str "hello " name))

(defmacro macro-hello
  [name]
  `(str "hello " ~name))

(macro-hello "soul")

(defmacro our-doto
  [expr & form]
  `(let [obj# ~expr]
     ~@(map (fn [[f & args]]
              `(~f obj# ~@args)) form)
     obj#))

(our-doto "it works"
          (println "I can't believe it"))

(defmacro our-doto
  [expr & form]
  (let [obj (gensym "obj")]
      `(let [~obj ~expr]
         ~@(map (fn [[f & args]]
                  `(~f ~obj ~@args)) form)
         ~obj)))

(defmacro spy
  [x]
  `(do
     (println "spied" '~x ~x)
     ~x))

(spy 2)
(spy (rand-int 10))

(defmacro spy [x]
  `(let [x# ~x]
     (println "spied" '~x x#)
     x#))
(spy (rand-int 10))
