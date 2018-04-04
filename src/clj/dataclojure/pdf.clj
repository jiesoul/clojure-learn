(ns dataclojure.pdf
  (:require [incanter.core :refer :all]
            [edu.ucdenver.ccp.kr.kb :refer :all]
            [edu.ucdenver.ccp.kr.rdf :refer :all])
  (:import [java.io File]))

(defn kb-memstore
  []
  (kb :sesame-mem))

(def tele-ont "http://telegraphis.net/ontology/")

(defn init-kb
  [kb-store]
  (register-namespaces
    kb-store
    '(("geographis" (str tele-ont
                         "geography/geography#"))
       ("code" (str tele-ont "measurement/code#"))
       ("money" (str tele-ont "money/money#"))
       ("owl" "http://www.w3.org/2002/07/owl#")
       ("rdf" (str "http://www.w3.org/"
                   "1999/02/22-rdf-syntax-ns#"))
       ("xsd" "http://www.w3.org/2001/XMLSchema#")
       ("currency" (str "http://telegraphis.net/"
                        "data/currencies/"))
       ("dbpedia" "http://dbpedia.org/resource/")
       ("dbpedia-ont" "http://dbpedia.org/ontology/")
       ("dbpedia-prop" "http://dbpedia.org/property/")
       ("err" "http://ericrochester.com/"))))

(defn tstore (init-kb (kb-memstore)))
