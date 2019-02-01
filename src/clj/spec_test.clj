(ns spec-test
  (:require [clojure.spec.alpha :as s]))

(s/conform even? 100)

(s/valid? even? 100)

(s/valid? even? 99)

(s/def ::date inst?)
(s/def ::suit #{:club :diamond :heart :spade})

(s/valid? ::date (java.util.Date.))
(s/conform ::suit :club)

(s/def ::big-even (s/and int? even? #(> % 1000)))

(s/valid? ::big-even 100)
(s/valid? ::big-even 10000)

(def email-regex #"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,63}$")
(s/def ::email-type (s/and string? #(re-matches email-regex %)))
(s/def ::password string?)
(s/def ::pass-config string?)
(s/def ::email ::email-type)

(s/def ::userReg (s/keys :req [::email ::password ::pass-confirm]))

(s/valid? ::userReg
          {::email "jiesoul@gmail.com"
           ::password "12345678"
           ::pass-confirm "12345678"})

(s/explain ::userReg
          {"email" "string",
           "password" "string",
           "pass-confirm" "string"
           })

(s/explain ::userReg
           {::email "jiesoul@gmail.com"})

(s/def ::person
  (s/keys :req-un [::email ::password ::pass-confirm]))

(s/conform ::person
           {:email "jiesoul@gmail.com"
            :password "1234678"
            :pass-confirm "12345678"})

(s/explain ::person
           {:email "n/a"
            :password "12345678"
            :pass-confirm "12345678"})

(defrecord Person [email password pass-confirm])

(s/explain ::person (->Person "jiesoul@gmail" "" ""))

(s/def :event/type keyword?)
(s/def :event/timestamp int?)
(s/def :search/url string?)
(s/def :error/message string?)
(s/def :error/code int?)

(defmulti event-type :event/type)
(defmethod event-type :event/search [_]
  (s/keys :req [:event/type :event/timestamp :search/url]))
(defmethod event-type :event/error [_]
  (s/keys :req [:event/type :event/timestamp :error/message :error/code]))

(s/def :event/event (s/multi-spec event-type :event/type))

(s/valid? :event/event
          {:event/type :event/search
           :event/timestamp 12345678
           :search/url "https://clojure.org"})

(s/conform (s/coll-of keyword?) [:a :b :c])

(s/conform (s/coll-of number?) [1 10 3])



