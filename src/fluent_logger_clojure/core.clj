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

(defn log-ts
  ([tag key value timestamp] (log-ts tag {key value} timestamp))
  ([tag data timestamp]
    (let [dic (new HashMap)]
      (doseq [k (keys data)]
        (.put dic (name k) (get data k)))
      (.log *fluent-logger* tag dic timestamp))))

(defn log
  ([tag key value] (log-ts tag key value 0))
  ([tag data] (log-ts tag data 0)))

(defn close
  ([logger] (.close logger))
  ([] (.close *fluent-logger*)))

(defn flush'
  ([logger] (.flush logger))
  ([] (.flush *fluent-logger*)))

(defmacro with-log [logger form & more]
  `(binding [*fluent-logger* ~logger]
     ~form ~@more))
