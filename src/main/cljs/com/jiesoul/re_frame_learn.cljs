(ns com.jiesoul.re-frame-learn
  (:require [re-frame.core :as rf]
            [ajax.core :as ajax]
            [reagent.core :as r]))

(defn delete-button
  [item-id]
  [:div.garbage-bin
   {:onclick #(rf/dispatch [:delete-item item-id])}])

(defn h
  [coeffects event]
  (let [item-id (second event)
        db      (:db coeffects)]
    {:db (disj db [:item item-id])}))

(rf/reg-event-fx
  :delete-item
  h)

(defn query-fn
  [db v]
  (:items db))

(rf/reg-sub
  :query-items
  query-fn)

(defn item-render
  [])

(defn items-view
  []
  (let [items (rf/subscribe [:query-items])]
    [:div (map item-render @items)]))

(rf/dispatch [:buy 32343])

(def app-state (atom {}))

(defn buy-button
  [item-id]
  [:buton
   {:on-click (fn [e]
                (.preventDefault e)
                (rf/dispatch [:buy item-id]))}
   "Buy"])

(rf/reg-event-fx
  :buy
  [(rf/inject-cofx :temp-id)]
  (fn [cofx [_ item-id]]
    {:http-xhrio {:uri (str "http://url.com/product" item-id "/purchase")
                  :method :post
                  :timeout 10000
                  :response-format (ajax/json-response-format {:keywords? true})
                  :on-success [:added-cart (:temp-id cofx)]
                  :on-failure [:notified-error (:temp-id cofx)]}
     :db (update-in (:db cofx) [:cart :items] conj {:item item-id
                                                    :temp-id (:temp-id cofx)})}))

(rf/reg-fx
  :http-xhrio
  (fn [request]
    (case (:method request :get)
      :post (ajax/POST (:url request)))))

(rf/reg-event-db
  :save-name
  (fn [db [_ first-name last-name]]
    (update db :current-user assoc :first-name first-name :last-name last-name)))

(rf/reg-event-fx
  :save-name
  (fn [{:keys [db]} [_ first-name last-name]]
    {:db (update db :current-user assoc :first-name first-name :last-name last-name)}))

(rf/reg-cofx
  :now
  (fn [cofx _data]
    (assoc cofx :now (js/Date.))))

(defonce last-temp-id (atom 0))

(rf/reg-cofx
  :temp-id
  (fn [cofx _]
    (assoc cofx :temp-id (swap! last-temp-id inc))))

(def undos (r/atom ()))

(def undo-interceptor
  (rf/->interceptor
    :id :undo
    :before (fn [context]
              (swap! undos conj (-> context :coeffects :db))
              context)))

(rf/reg-event-fx
  :undo
  (fn [_ _]
    (let [undo-values @undos]
      (if (empty? undo-values)
        (do
          (js/console.log "No undo vallues, but :undo was dispatch")
          {})
        (let [[f & rs] undo-values]
          (reset! undos rs)
          {:db f})))))

(defn increment-button
  [key]
  [:button
   {:on-click #(swap! app-state update-in [:counter key :value])}
   "Increment"])

(defn counter-label
  [counter]
  [:span (get-in @app-state [:counter counter :value])])

(defn complex-component
  [a b c]
  (let [state (r/atom {})]
    (r/create-class
      {:component-did-mount (fn [] (println "I mounted"))
       :display-name "complex-component"
       :reagent-render (fn [a b c]
                         [:div {:class c}
                          [:i a] " b"])})))