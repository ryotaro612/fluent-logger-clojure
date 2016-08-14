(ns fluent-logger-clojure.core
  (:import (org.fluentd.logger FluentLogger)
           (java.util HashMap)))



(defn logger
  ([tag-prefix]
   (FluentLogger/getLogger tag-prefix))
  ([tag host port]
   (FluentLogger/getLogger tag host port))
  ([tag-prefix host port timeout buffer-capacity]
   (FluentLogger/getLogger tag-prefix host port timeout buffer-capacity))
  ([tag-prefix host port timeout buffer-capacity reconnector]
   (FluentLogger/getLogger tag-prefix host port timeout buffer-capacity reconnector)))

(def ^{:dynamic true} *fluent-logger*)

(defn log
  ([tag key value timestamp] (log tag {key value} timestamp))
  ([tag data timestamp]
    (let [dic (new HashMap)]
      (doseq [k (keys data)]
        (.put dic (name k) (get data k)))
      (.log *fluent-logger* tag dic timestamp))))

(defn close [logger] (.close logger))
(defn flush [logger] (.flush logger))
