(ns clojureprogram.agents)

(def a (agent 500))

(send a range 1000)

(def a (agent 0))
(send a inc)

(def a (agent 5000))
(def b (agent 10000))
(send-off a #(Thread/sleep %))
(send-off b #(Thread/sleep %))

(await a b)

(def a (agent nil))
(send a (fn [_] (throw (Exception. "something is wrong"))))
(send a identity)
(restart-agent a 42)
(send a inc)
(reduce send a (for [x (range 3)]
                 (fn [_] (throw (Exception. (str "error #" x))))))
(agent-error a)
(restart-agent a 42)
(agent-error a)
(restart-agent a 42 :clear-actions true)
(agent-error a)

(def a (agent nil :error-mode :continue))
(send a (fn [_] (throw (Exception. "something is wrong."))))
(send a identity)

(def a (agent nil
              :error-mode :continue
              :error-handler (fn [the-agent exception]
                               (. println System/out (. getMessage exception)))))
