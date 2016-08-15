# fluent-logger-clojure

Fluent-logger-clojure serves as a simple facade for fluentd

## Usage

    (with-log (logger "tag-prefix")
       (log "tag" "key" "value")
       (log "tag" {:a "foo" :b "bar"})
       (flush)
       (close))

## License

Apache License, Version 2.0

