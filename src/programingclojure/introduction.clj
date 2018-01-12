(ns programingclojure.introduction)

(defn blank? [str]
  (every? #(Character/isWhitespace %) str))

(defrecord Person [first-name last-name])

(defn hello-world [username]
  (println (format "Hello, %s" username)))

(def accounts (ref #{}))

(defrecord Account [id balance])

(dosync
 (alter accounts conj (->Account "CLJ" 10000)))

(System/getProperties)

(defn hello [name]
  (str "hello" name))

(conj #{} "sut")

(def visitors (atom #{}))

(swap! visitors conj "Stu")

(deref visitors)

@visitors

(defn hello
  "写下问候信息"
  [username]
  (swap! visitors conj username)
  (str "Hello, " username))

 
