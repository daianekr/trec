(defproject trec "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [criterium "0.4.4"]]
  :source-paths ["src" "test"]
  :repl-options {:init-ns trec.core}
  :aliases {:test {:extra-paths ["test"]}})
