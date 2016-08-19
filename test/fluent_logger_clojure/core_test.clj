(ns fluent-logger-clojure.core-test
  (:require [clojure.test :refer :all]
            [fluent-logger-clojure.core :refer :all]))

(gen-class
  :name fluent_logger_clojure.core_test.Logger
  :prefix "-"
  :init init
  :methods [[log [String java.util.Map Long] Boolean]]
  :state state)

(defn -init []
  [[] (atom [])])

(defn -log [this tag data timestamp]
  (swap! (.state this) merge {:tag tag :data (zipmap (.keySet data) (.values data)) :timestamp timestamp})
  true)

(deftest quickstart
  (testing "quickstart"
    (let [logger (fluent_logger_clojure.core_test.Logger.)]
      ;; log
      (with-log logger
                (log "tag" "key" "val")
                (log "tag2" {:key1 1 :key2 "a"})
                (log-ts "tag3" :a "v" 42)
                (log-ts "tag3" {:2 "v"} 42))
      ;; validate log
      (let [results @(.state logger)
            log (nth results 0)
            log1 (nth results 1)
            log2 (nth results 2)
            log3 (nth results 3)]
        (is (= '{:tag "tag", :data {"key" "val"}, :timestamp 0} log))
        (is (= '{:tag "tag2", :data {"key1" 1, "key2" "a"}, :timestamp 0} log1))
        (is (= '{:tag "tag3", :data {"a" "v"}, :timestamp 42} log2))
        (is (= '{:tag "tag3", :data {"2" "v"}, :timestamp 42} log3))))))

