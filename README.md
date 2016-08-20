# fluent-logger-clojure [![Build Status](https://travis-ci.org/satokazuma/fluent-logger-clojure.svg?branch=master)](https://travis-ci.org/satokazuma/fluent-logger-clojure)

Fluent-logger-clojure serves as a simple facade for fluentd

## Install

### Leiningen

    [fluent-logger-clojure "0.1.0.1"]
    

## Usage

    (with-log (logger "tag-prefix")
       (log "tag" "key" "value")
       (log "tag" {:a "foo" :b "bar"})
       (flush)
       (close))

## License

Apache License, Version 2.0

