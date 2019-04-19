(ns com.jiesoul.clojure-noob.ch10)

(def fred (atom {:cuddle-hunger-level 0
                 :percent-deteriorated 0}))
@fred

(let [zombie-state @fred]
  (if (>= (:percent-deteriorated zombie-state) 50)
    (future (println (:percent-deteriorated zombie-state)))))

(swap! fred
  (fn [current-state]
    (merge-with + current-state {:cuddle-hunger-level 1})))

(swap! fred
  (fn [current-state]
    (merge-with + current-state {:cuddle-hunger-level 1
                                 :percent-deteriorated 1})))

(defn increase-cuddle-hunger-level
  [zombie-state increase-by]
  (merge-with + zombie-state {:cuddle-hunger-level increase-by}))
(increase-cuddle-hunger-level @fred 10)
(swap! fred increase-cuddle-hunger-level 10)

(update-in {:a {:b 3}} [:a :b] inc)
(update-in {:a {:b 3}} [:a :b] + 10)

(swap! fred update-in [:cuddle-hunger-level] + 10)

(let [num (atom 1)
      s1 @num]
  (swap! num inc)
  (println "State 1:" s1)
  (println "Current state:" @num))

(reset! fred {:cuddle-hunger-level 0
              :percent-deteriorated 0})

(defn shuffle-speed
  [zombie]
  (* (:cuddle-hunger-level zombie)
    (- 100 (:percent-deteriorated zombie))))

(defn shuffle-alert
  [key watched old-state new-state]
  (let [sph (shuffle-speed new-state)]
    (if (> sph 5000)
      (do
        (println "Run, you foll!")
        (println "The zombie's SPH is now " sph)
        (println "This message brought to your courtesy of " key))
      (do
        (println "All's well with " key)
        (println "Cuddle hunger: " (:cuddle-hunger-level new-state))
        (println "Percent deteriorated: " (:percent-deteriorated new-state))
        (println "SPH: " sph)))))

(reset! fred {:cuddle-hunger-level 22
              :percent-deteriorated 2})
(add-watch fred :fred-shuffle-alert shuffle-alert)
(swap! fred update-in [:percent-deteriorated] + 1)
(swap! fred update-in [:cuddle-hunger-level] + 30)

(defn percent-deteriorated-validator
  [{:keys [percent-deteriorated]}]
  (or (and (>= percent-deteriorated 0)
        (>= percent-deteriorated 100))
    (throw (IllegalStateException. "That's not mathy!"))))

(def bobby
  (atom
    {:cuddle-hunger-level 0 :percent-deteriorated 0
     :validator           percent-deteriorated-validator}))

(swap! bobby update-in [:percent-deteriorated] + 200)

(def sock-varieties
  #{"darned" "argyle" "wool" "horsehair" "mulleted"
    "passive-aggressive" "striped" "polka-dotted"
    "athletic" "business" "power" "invisible" "gollumed"})

(defn sock-count
  [sock-variety count]
  {:variety sock-variety
   :count count})

(defn generate-sock-gnome
  [name]
  {:name name
   :socks #{}})

(def sock-name (ref (generate-sock-gnome "Barumpharumph")))
(def dryer (ref {:name "LG 1337"
                 :socks (set (map #(sock-count % 2) sock-varieties))}))
(:socks @dryer)

(defn steal-sock
  [gnome dryer]
  (dosync
    (when-let [pair (some #(if (= (:count %) 2) %) (:socks @dryer))]
      (let [updated-count (sock-count (:variety pair) 1)]
        (alter gnome update-in [:socks] conj updated-count)
        (alter dryer update-in [:socks] disj pair)
        (alter dryer update-in [:socks] conj updated-count)))))
(steal-sock sock-name dryer)
(:socks @sock-name)

(defn similar-socks
  [target-sock sock-set]
  (filter #(= (:variety %) (:variety target-sock)) sock-set))
(similar-socks (first (:socks @sock-name)) (:socks @dryer))

(def counter (ref 0))
(future
  (dosync
    (alter counter inc)
    (println @counter)
    (Thread/sleep 500)
    (alter count inc)
    (println @counter)))
(Thread/sleep 250)
(println @counter)

(defn sleep-print-update
  [sleep-time thread-name update-fn]
  (fn [state]
    (Thread/sleep sleep-time)
    (println (str thread-name ": " state))
    (update-fn state)))
(def counter (ref 0))
(future (dosync (commute counter (sleep-print-update 100 "Thread A" inc))))
(future (dosync (commute counter (sleep-print-update 150 "Thread A" inc))))

(def receiver-a (ref #{}))
(def receiver-b (ref #{}))
(def giver (ref #{1}))
(do (future (dosync (let [gift (first @giver)]
                      (Thread/sleep 10)
                      (commute receiver-a conj gift)
                      (commute giver disj gift))))
    (future (dosync (let [gift (first @giver)]
                      (Thread/sleep 50)
                      (commute receiver-b conj gift)
                      (commute giver disj gift)))))
@receiver-a
@receiver-b
@giver

(def ^:dynamic *notification-address* "dobby@elf.org")
(binding [*notification-address* "test@elf.org"]
  *notification-address*)

(binding [*notification-address* "tester-1@elf.org"]
  (println *notification-address*)
  (binding [*notification-address* "tester-2@elf.org"]
  (println *notification-address*))
  (println *notification-address*))

(defn notify
  [message]
  (str "TO: " *notification-address* "\n"
    "MESSAGE: " message))
(notify "I fell.")

(binding [*notification-address* "test@elf.org"]
  (notify "test!"))

(binding [*out* (clojure.java.io/writer "print-output")]
  (println "A man who carries a cat by the tail learns
            something he can learn in no other way. -- Mark Twain"))
(slurp "print-output")

(println ["Print" "all" "the" "things!"])
(binding [*print-length* 1]
  (println ["print" "just" "one!"]))

(def ^:dynamic *troll-thought* nil)
(defn troll-riddle
  [your-answer]
  (let [number "man meat"]
    (when (thread-bound? #'*troll-thought*)
      (set! *troll-thought* number))
    (if (= number your-answer)
      "TROLL: Your can cross the bridge!"
      "TROLL: Time to eat you, succulent human!")))
(binding [*troll-thought* nil]
  (println (troll-riddle 2))
  (println "SUCCULENT HUMAN: Oooooh! The answaer was" *troll-thought*))

(def power-source "hair")
(alter-var-root #'power-source (fn [_] "7-eleven parking lot"))
(with-redefs [*out* *out*]
  (doto (Thread. #(println "with redefs allows me to show up in the REPL"))
    .start
    .join))

(defn always-1
  []
  1)
(take 5 (repeatedly always-1))
(take 5 (repeatedly (partial rand-int 10)))

(def alphabet-length 26)
(def letters (mapv (comp str char (partial + 65)) (range alphabet-length)))
(defn random-string
  [length]
  (apply str (take length (repeatedly #(rand-nth letters)))))
(defn random-string-list
  [list-length string-length]
  (doall (take list-length (repeatedly (partial random-string string-length)))))
(def orc-names (random-string-list 3000 7000))
(time (dorun (map clojure.string/lower-case orc-names)))
(time (dorun (pmap clojure.string/lower-case orc-names)))

(def orc-name-abbrevs (random-string-list 20000 300))
(time (dorun (map clojure.string/lower-case orc-name-abbrevs)))
(time (dorun (pmap clojure.string/lower-case orc-name-abbrevs)))

(def numbers (range 1 11))
(partition-all 3 numbers)
(time
  (dorun
    (apply concat
           (pmap (fn [number-group] (doall (map inc number-group)))
                 (partition-all 3 numbers)))))

(defn ppmap
  [grain-size f & colls]
  (apply concat
         (apply pmap
                (fn [& pgroups] (doall (apply map f pgroups)))
                (map (partial partition-all grain-size) colls))))
(time (dorun (ppmap 1000 clojure.string/lower-case orc-name-abbrevs)))