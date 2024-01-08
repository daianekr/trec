(ns trec.teste
  (:require [criterium.core :refer [quick-bench]]))

;; -------------------------------------------------------
;;arquivo usado para testes durante a criação do trabalho 
;; -------------------------------------------------------

(defn my-map-v1 [f coll]
  ;; implementada usando a map do próprio clojure
  (map f coll))

(defn my-map-v2 [f coll]
  ;; implementada usando recursão
  (if (empty? coll)
    '()
    (cons (f (first coll)) (my-map-v2 f (rest coll)))))

(defn my-map-v3 [f coll]
  ;; implementada usando recursão de cauda
  (loop [result '()
         remaining coll]
    (if (empty? remaining)
      result
      (recur (conj result (f (first remaining)))
             (rest remaining)))))

(defn -main []
  (println "Benchmarking my-map-v1:")
  (quick-bench (my-map-v1 inc (range 1000)))

  (println "Benchmarking my-map-v2:")
  (quick-bench (my-map-v2 inc (range 1000)))

  (println "Benchmarking my-map-v3:")
  (quick-bench (my-map-v3 inc (range 1000))))

(-main)