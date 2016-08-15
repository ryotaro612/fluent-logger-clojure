# fluent-logger-clojure

Fluent-logger-clojure serves as a simple facade for fluentd

## Install

### Leiningen

    [fluent-logger-clojure "0.1.0"]
    

## Usage

    (with-log (logger "tag-prefix")
       (log "tag" "key" "value")
       (log "tag" {:a "foo" :b "bar"})
       (flush)
       (close))

## License

Apache License, Version 2.0

