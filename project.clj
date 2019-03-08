(defproject clojure-learn "0.1.0"
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
                 [me.raynes/fs "1.4.6"]
                 [flames "0.4.0"]

                 [reagent "0.8.1" :exclusions [cljsjs/react cljsjs/react-dom cljsjs/react-dom-server]]
                 [cljsjs/react "16.6.0-0"]
                 [cljsjs/react-dom "16.6.0-0"]
                 [cljsjs/react-dom-server "16.6.0-0"]
                 [re-frame "0.10.6"]]

  :source-paths ["src/main/clj" "env/prod/clj"]
  :java-source-paths ["src/main/java"]

  :test-paths ["test/main/clj" "test/main/java"]
  :plugins [[lein-cljsbuild "1.1.7"]
            [lein-figwheel "0.5.18"]]

  :clean-target ^{:protect false}
[:target-path
 [:cljsbuild :builds :app :compiler :output-dir]
 [:cljsbuild :builds :app :compiler :output-to]]

  :figwheel {:http-server-root "public"
             :nrepl-port       7002
             :nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]
             :css-dirs         ["resources/public/css"]}

  :cljsbuild
  {:builds
   {:app
    {:source-paths ["src/main/cljs" "env/dev/cljs"]
     :compiler
                   {:main          "com.jiesoul.dev"
                    :asset-path    "js/out"
                    :output-to     "resources/public/js/app.js"
                    :output-dir    "resources/public/js/out"
                    :source-map    true
                    :optimizations :none
                    :pretty-print  true
                    :npm-deps      {:left-pad "1.1.3"}
                    :install-deps  true}
     :figwheel     {:on-jsload "com.jiesoul.core/mount-root"
                    :open-urls ["http://localhost:3449/index.html"]}}
    :release
    {:source-paths ["src/main/cljs" "env/prod/cljs"]
     :compiler     {:output-to     "resources/public/js/app.js"
                    :output-dir    "resources/public/js/release"
                    :optimizations :advanced
                    :infer-externs true
                    :externs       ["externs.js"]
                    :pretty-print  false}}}}

  :aliases {"package" ["do" "clean" ["cljsbuild" "once" "release"]]}

  :profiles {:dev {:source-paths ["env/dev/clj"]
                   :dependencies [[org.clojure/test.check "0.9.0"]
                                  [midje "1.9.4"]
                                  [eftest "0.5.6"]
                                  [binaryage/devtools "0.9.10"]
                                  [figwheel-sidecar "0.5.18"]
                                  [nrepl "0.6.0"]
                                  [cider/piggieback "0.4.0"]
                                  [com.cemerick/piggieback "0.2.2"]]
                   :plugins [[com.jakemccrary/lein-test-refresh "0.23.0"]
                             [lein-midje "3.2.1"]
                             [lein-eftest "0.5.6"]]}})
