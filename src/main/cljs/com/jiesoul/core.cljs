(ns com.jiesoul.core
  (:require [reagent.core :as r]))

(defn home-page []
  [:div
   [:h1 "welcome cljs world"]
   [:div {:class "parent"}
    [:p {:id "child-one"} "I'm first child element."]
    [:p "I'm the second child element."]]])

;; -------
;; 初始化 app
(defn mount-root []
  (r/render
    home-page
    (.getElementById js/document "app")))

(defn init! []
  (mount-root))