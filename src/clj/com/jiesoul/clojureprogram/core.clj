(ns com.jiesoul.clojureprogram.core)

(defn average [numbers]
  (/ (apply + numbers) (count numbers)))

(average [1 2 3 4 5])

(read-string "(+ 1 2 #_(* 2 2) 8)")

(def x 1)

*ns*

(defn hypot
  [x y]
  (let [x2 (* x x)
        y2 (* y y)]
    (Math/sqrt (+ x2 y2))))

(hypot 3 4)

(defn make-user
  [username & {:keys [email join-date]
               :or {join-date (java.util.Date.)}}]
  {:username username
   :join-date join-date
   :email email
   ;; 2.593e9 -> 一个月的毫秒
   :exp-date (java.util.Date. (long (+ 2.592e9 (.getTime join-date))))})

(make-user "Boddy")

(make-user "Boddy"
           :join-date (java.util.Date. 111 0 1)
           :email "boddy@gmail.com")

(defn embedded-repl
  []
  (print (str (ns-name *ns*) ">>> "))
  (flush)
  (let [expr (read)
        value (eval expr)]
    (when (not= :quit value)
      (println value)
      (recur))))

(embedded-repl)
