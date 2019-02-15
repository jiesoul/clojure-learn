(ns com.jiesoul.joyofclojure.web
  (:require [clojure.java.io :as io]
            [clojure.string :as string])
  (:import [com.sun.net.httpserver HttpHandler HttpExchange HttpServer]
           [java.net InetSocketAddress URLDecoder URI]
           [java.io File FileOutputStream]))

(def OK java.net.HttpURLConnection/HTTP_OK)

(defn respond
  ([exchange body]
    (respond identity exchange body))
  ([around exchange body]
    (.sendResponseHeaders exchange OK 0)
    (with-open [resp (.getResponseBody exchange)]
      (.write resp (.getBytes body)))))

(defn new-server
  [port path handler]
  (doto
    (HttpServer/create (InetSocketAddress. port) 0)
    (.createContext path handler)
    (.setExecutor nil)
    (.start)))

(defn default-handler
  [txt]
  (proxy [HttpHandler]
         []
    (handle [exchange]
      (respond exchange txt))))

(def server
  (new-server
    8123
    "/joy/hello"
    (default-handler "hello Cleveland")))


