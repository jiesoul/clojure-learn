(ns com.jiesoul.joyofclojure.ch10
  (:import java.util.concurrent.Executors))

(def thread-pool
  (Executors/newFixedThreadPool
    (+ 2 (.availableProcessors (Runtime/getRuntime)))))

(defn dothreads!
  [f & {thread-count :threads
        exec-count   :times
        :or          {thread-count 1, exec-count 1}}]
  (dotimes [t thread-count]
    (.submit thread-pool
             #(dotimes [_ exec-count]
                (f)))))

(dothreads! #(.print System/out "Hi ") :thread-count 2 :times 2)

(def initial-board
  [[:- :k :-]
   [:- :- :-]
   [:- :K :-]])

(defn board-map [f board]
  (vec (map #(vec (for [s %] (f s)))
            board)))

(defn reset-board!
  []
  (def board (board-map ref initial-board))
  (def to-move (ref [[:K [2 1]] [:k [0 1]]]))
  (def num-moves (ref 0)))

(defn neighbors
  ([size yx] (neighbors [[-1 0] [1 0] [0 -1] [0 1]]
                        size
                        yx))
  ([deltas size yx]
    (filter (fn [new-yx]
              (every? #(< -1 % size) new-yx))
            (map #(vec (map + yx %))
                 deltas))))

(def king-moves
  (partial neighbors
           [[-1 -1] [-1 0] [-1 1] [0 -1] [0 -1] [0 1] [1 -1] [1 0] [1 1]] 3))

(defn good-move?
  [to enemy-sq]
  (when (not= to enemy-sq)
    to))

(defn choose-move
  [[[mover mpos] [_ enemy-pos]]]
  [mover (some #(good-move? % enemy-pos)
               (shuffle (king-moves mpos)))])

(reset-board!)
(take 5 (repeatedly #(choose-move @to-move)))

(defn place [from to] to)

(defn move-piece [[piece dest] [[_ src] _]]
  (alter (get-in board dest) place piece)
  (alter (get-in board src) place :-)
  (alter num-moves inc))

(defn update-to-move [move]
  (alter to-move #(vector (second %) move)))

(defn make-move []
  (let [move (choose-move @to-move)]
    (dosync (move-piece move @to-move))
    (dosync (update-to-move move))))

(reset-board!)

(make-move)
(board-map deref board)
(make-move)
(board-map deref board)

(dothreads! make-move :threads 100 :times 100)
(defn strees-ref [r]
  (let [slow-tries (atom 0)]
    (future
      (dosync
        (swap! slow-tries inc)
        (Thread/sleep 200)
        @r)
      (println (format "r is: %s, history: %d, after: %d tries @r"
                       @r (.getHistoryCount r) @slow-tries)))
    (dotimes [i 500]
      (Thread/sleep 10)
      (dosync (alter r inc)))
    :done))

(strees-ref (ref 0))
(strees-ref (ref 0 :max-history 30))
(strees-ref (ref 0 :min-history 15 :max-history 30))

(def joy (agent []))
(send joy conj "first edition")
@joy

(defn slow-conj [coll item]
  (Thread/sleep 1000)
  (conj coll item))

(send joy slow-conj "Second Edition")
@joy

(def log-agent (agent 0))

(defn do-log [msg-id message]
  (println msg-id ":" message)
  (inc msg-id))

(defn do-step [channel message]
  (Thread/sleep 1)
  (send-off log-agent do-log
            (str channel message)))

(defn three-step [channel]
  (do-step channel " ready to begin (step 0)")
  (do-step channel " warming up (step 1)")
  (do-step channel " really getting going new (step 2)")
  (do-step channel " done! (step 3)"))

(defn all-together-now []
  (dothreads! #(three-step "alpha"))
  (dothreads! #(three-step "beta"))
  (dothreads! #(three-step "omega")))

(all-together-now)

(defn exerise-agents [send-fn]
  (let [agents (map #(agent %) (range 10))]
    (doseq [a agents]
      (send-fn a (fn [_] (Thread/sleep 1000))))
    (doseq [a agents]
      (await a))))

(time (exerise-agents send-off))
(time (exerise-agents send))

(send log-agent (fn [_] 3000))
;@log-agent
;(restart-agent log-agent 2500 :clear-actions true)

(send-off log-agent do-log "The agent, it lives!")
;(restart-agent log-agent 2500 :clear-actions true)

(defn handle-log-error [the-agent the-err]
  (println "An action sent to the log-agent throw " the-err))

(set-error-handler! log-agent handle-log-error)
(set-error-mode! log-agent :continue)

(send log-agent (fn [x] (/ x 0)))
(send log-agent (fn [] 0))
(send-off log-agent do-log "Stayin' alive, stayin' alive...")

(def ^:dynamic *time* (atom 0))
(defn tick [] (swap! *time* inc))
(dothreads! tick :threads 1000 :times 100)
@*time*

(defn manipulable-memoize [function]
  (let [cache (atom {})]
    (with-meta
      (fn [& args]
        (or (second (find @cache args))
            (let [ret (apply function args)]
              (swap! cache assoc args ret)
              ret)))
      {:cache cache})))

(def slowly (fn [x] (Thread/sleep 1000) x))
(time [(slowly 9) (slowly 9)])

(def sometimes-slowly (manipulable-memoize slowly))

(time [(sometimes-slowly 108) (sometimes-slowly 108)])

(meta sometimes-slowly)
(let [cache (:cache (meta sometimes-slowly))]
  (swap! cache dissoc `(108)))

(meta sometimes-slowly)
(time [(sometimes-slowly 108) (sometimes-slowly 108)])

(defn print-read-only []
  (println "*read-eval* is currently" *read-eval*))

(defn binding-play []
  (print-read-only)
  (binding [*read-eval* false]
    (print-read-only))
  (print-read-only))

(binding-play)

(def favorite-color :green)
favorite-color
(var favorite-color)

(def x 42)
{:out-var-value x
 :with-locals (with-local-vars [x 9]
                {:local-var x
                 :local-var-value (var-get x)})}

(with-precision 4
  (/ 1M 3))