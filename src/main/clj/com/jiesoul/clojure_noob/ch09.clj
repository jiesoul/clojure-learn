(ns com.jiesoul.clojure-noob.ch09)

(future (Thread/sleep 4000)
  (println "I'll print after 4 seconds"))

(println "I'll print immediately")

(let [result (future (println "this prints once")
               (+ 1 1))]
  (println "deref: " (deref result))
  (println "@:" @result))

(let [result (future (Thread/sleep 3000)
               (+ 1 1))]
  (println "The result is: " @result)
  (println "It will be at least 3 seconds before I print"))

(deref (future (Thread/sleep 1000) 0) 10 5)

(realized? (future (Thread/sleep 1000)))

(let [f (future)]
  @f
  (realized? f))

(def jackson-5-delay
  (delay (let [message "Just call my name and I'll be there"]
           (println "First deref:" message)
           message)))
(force jackson-5-delay)

(def gimli-headshots ["serious.jpg" "fun.jpg" "playful.jpg"])
(defn email-user
  [emial-address]
  (println "Sending headshot notification to" emial-address))
(defn upload-document
  [headshot]
  true)
(let [notify (delay (email-user "and-my-axe@gmail.com"))]
  (doseq [headshot gimli-headshots]
    (future (upload-document headshot)
      (force notify))))

(def my-promise (promise))
(deliver my-promise (+ 1 2))
@my-promise

(def yak-butter-international
  {:store "Yak Butter International"
   :price 90
   :smoothness 90})
(def butter-than-nothing
  {:store "Butter Than Nothing"
   :price 150
   :smoothness 83})
(def baby-got-yak
  {:store "Baby Got Yak"
   :price 94
   :smoothness 99})
(defn mock-api-call
  [result]
  (Thread/sleep 1000)
  result)

(defn satisfactory?
  [butter]
  (and (<= (:price butter) 100)
        (>= (:smoothness butter) 97)
    butter))
(time (some (comp satisfactory? mock-api-call)
        [yak-butter-international butter-than-nothing baby-got-yak]))

(time (let [butter-promise (promise)]
        (doseq [butter [yak-butter-international butter-than-nothing baby-got-yak]]
          (future
            (if-let [satisfactory-butter (satisfactory? (mock-api-call butter))]
              (deliver butter-promise satisfactory-butter))))
        (println "And the winner is:" @butter-promise)))

(let [p (promise)]
  (deref p 100 "time out"))

(let [ferengi-wisdom-promise (promise)]
  (future (println "Here's some Ferengi wisdom:" @ferengi-wisdom-promise))
  (Thread/sleep 100)
  (deliver ferengi-wisdom-promise "Whisper your way to success."))

(defmacro wait
  [timeout & body]
  `(do (Thread/sleep ~timeout) ~@body))

(let [saying3 (promise)]
  (future (deliver saying3 (wait 100 "Cheerio!")))
  @(let [saying2 (promise)]
     (future (deliver saying2 (wait 400 "Pip pip!")))
     @(let [saying1 (promise)]
        (future (deliver saying1 (wait 200 "'Ello, gov'na!")))
        (println @saying1)
        saying1)
     (println @saying2)
     saying2)
  (println @saying3)
  saying3)

(defmacro enqueue
  ([q concurrent-promise-name concurrent serialized]
    `(let [~concurrent-promise-name (promise)]
       (future (deliver ~concurrent-promise-name ~concurrent))
       (deref ~q)
       ~serialized
       ~concurrent-promise-name))
  ([concurrent-promise-name concurrent serialized]
    `(enqueue (future) ~concurrent-promise-name ~concurrent ~serialized)))
