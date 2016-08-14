(ns fluent-logger-clojure.core-test
  (:require [clojure.test :refer :all]
            [fluent-logger-clojure.core :refer :all]))

(comment (deftest quickstart
           (testing "quickstart"
             (binding [*fluent-logger* (logger "tag-prefix")]
               (log "tag" "k" "val")))))
