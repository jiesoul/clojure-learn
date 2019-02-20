(ns com.jiesoul.dataclojure.rdf
  (:require [incanter.core :refer :all]
            [edu.ucdenver.ccp.kr.kb :refer :all]
            [edu.ucdenver.ccp.kr.rdf :refer :all]
            [edu.ucdenver.ccp.kr.sparql :refer :all]
            [edu.ucdenver.ccp.kr.sesame.kb :refer :all]
            [clojure.set :refer :all])
  (:import [java.io File]))

(defn kb-memstore
  []
  (kb :sesame-mem))

(def tele-ont "http://telegraphis.net/ontology/")

(defn init-kb
  "This creates an in-memory knowledge base and
  initializes it with a default set of namespaces."
  [kb-store]
  (register-namespaces
    kb-store
    '(("geographis" "http://telegraphis.net/ontology/geography/geography#")
       ("code" "http://telegraphis.net/ontology/measurement/code#")
       ("money" "http://telegraphis.net/ontology/money/money#")
       ("owl" "http://www.w3.org/2002/07/owl#")
       ("rdf" "http://www.w3.org/1999/02/22-rdf-syntax-ns#")
       ("xsd" "http://www.w3.org/2001/XMLSchema#")
       ("currency" "http://telegraphis.net/data/currencies/")
       ("dbpedia" "http://dbpedia.org/resource/")
       ("dbpedia-ont" "http://dbpedia.org/ontology/")
       ("dbpedia-prop" "http://dbpedia.org/property/")
       ("err" "http://ericrochester.com/"))))

(def tstore (init-kb (kb-memstore)))

(def q '((?/c rdf/type money/Currency)
          (?/c money/name ?/full_name)
          (?/c money/shortName ?/name)
          (?/c money/symbol ?/symbol)
          (?/c money/minorName ?/minor_name)
          (?/c money/minorExponent ?/minor_exp)
          (?/c money/isoAlpha ?/iso)
          (?/c money/currencyOf ?/country)))

(defn header-keyword
  [header-symbol]
  (keyword (.replace (name header-keyword) \_ \-)))

(defn fix-headers
  [coll]
  (into {} (map (fn [[k v]] [(header-keyword k) v])
               coll)))

(defn load-data
  [k rdf-file q]
  (load-rdf-file k rdf-file)
  (to-dataset (map fix-headers (query k q))))

;(load-data tstore (File. "data/currencies.xml") q)