(defproject clojurelearning "0.1.0"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.9.946"]
                 [org.clojure/core.unify "0.5.7"]
                 [org.clojure/core.logic "0.8.11"]
                 [org.clojure/core.async "0.4.474"]
                 [incanter/incanter-core "1.9.2"]
                 [incanter/incanter-io "1.9.2"]
                 [incanter/incanter-excel "1.9.2"]
                 [org.clojure/data.json "0.2.1"]
                 [org.xerial/sqlite-jdbc "3.21.0.1"]
                 [me.raynes/fs "1.4.6"]]

  :source-paths ["src/clj"]
  :plugins [[lein-cljsbuild "1.1.7"]]
  :cljsbuild
  {:builds
   [{:source-paths ["src/cljs"]
     :compiler
     {:output-to "dev-target/all.js"
      :optimizations :whitespace
      :pretty-print true}}
    {:source-paths ["src/cljs"]
     :compiler
     {:output-to "prod-target/all.js"
      :optimizations :advanced
      :externs ["externs.js"]
      :pretty-print false}}]})
