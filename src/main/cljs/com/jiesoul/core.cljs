(ns com.jiesoul.core
  (:require [reagent.core :as r]
            [com.jiesoul.reagent.ch01 :refer [greet right-component timer-component
                                              my-component tracked-pos]]
            [com.jiesoul.joyofclojure.ch13 :refer [woo play!]]))

(defn home-page []
  [:div
   [:h1 "welcome cljs world"]
   [tracked-pos]
   [my-component 1 2 3]
   [timer-component]
   [greet "SOUL"]
   [right-component "武家坡"]
   [:p "龙潭虎穴孤去闯"]
   [:div {:class "parent"}
    [:p {:id "child-one"} "I'm first child element."]
    [:p "I'm the second child element."]
    ]
   [:button "ssssss"]
   ;[:button "play music"
   ; {:onclick (play! woo [{:cent 1100 :duration 1 :delay 0 :volume 0.4}])}]
   ])

;; -------
;; 初始化 app
(defn mount-root []
  (r/render
    home-page
    (.getElementById js/document "app")))

(defn init! []
  (mount-root))