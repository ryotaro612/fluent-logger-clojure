(ns fluent-logger-clojure.core
  (:import (org.fluentd.logger FluentLogger)
           (java.util HashMap)))



(defn logger
  ([tag-prefix]
   (FluentLogger/getLogger tag-prefix))
  ([tag host port]
   (FluentLogger/getLogger tag host port)))

(def ^{:dynamic true} *fluent-logger*)

(defn log
  ([tag key value timestamp] (log tag {key value} timestamp))
  ([tag data timestamp]
    (let [dic (new HashMap)]
      (doseq [k (keys data)]
        (.put dic (name k) (get data k)))
      (.log *fluent-logger* tag dic timestamp))))

