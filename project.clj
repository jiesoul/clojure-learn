(defproject clojurelearning "0.1.0"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/clojurescript "1.10.516"]
                 [org.clojure/core.unify "0.5.7"]
                 [org.clojure/core.logic "0.8.11"]
                 [org.clojure/core.async "0.4.474"]
                 [org.clojure/core.match "0.3.0-alpha5"]
                 [incanter/incanter-core "1.9.2"]
                 [incanter/incanter-io "1.9.2"]
                 [incanter/incanter-excel "1.9.2"]
                 [org.clojure/data.json "0.2.1"]
                 [org.clojure/java.jdbc "0.2.3"]
                 [org.xerial/sqlite-jdbc "3.21.0.1"]
                 [enlive "1.1.6"]
                 [edu.ucdenver.ccp/kr-sesame-core "1.4.19"]
                 [org.slf4j/slf4j-simple "1.7.2"]
                 [clj-diff "1.0.0-SNAPSHOT"]
                 [clj-time "0.14.2"]
                 [parse-ez "0.3.6"]
                 [valip "0.2.0"]
                 [org.clojure/data.csv "0.1.4"]
                 [org.slf4j/slf4j-simple "1.7.25"]
                 [me.raynes/fs "1.4.6"]]
  :source-paths ["src/main/clj"]
  :java-source-paths ["src/main/java"]
  :test-paths ["test/main/clj" "test/main/java"]
  ;:aot :all
  :plugins [[lein-cljsbuild "1.1.7"]]
  :cljsbuild
  {:builds
   [{:source-paths ["src/main/cljs"]
     :compiler
     {:output-to "dev-target/all.js"
      :optimizations :whitespace
      :pretty-print true}}
    {:source-paths ["src/main/cljs"]
     :compiler
     {:output-to "prod-target/all.js"
      :optimizations :advanced
      :externs ["externs.js"]
      :pretty-print false}}]}
  :profiles {:dev {:dependencies [[org.clojure/test.check "0.9.0"]
                                  [midje "1.9.4"]]
                   :plugins [[com.jakemccrary/lein-test-refresh "0.23.0"]
                             [lein-midje "3.2.1"]]}})
