(ns trec.tsk2
  (:require [criterium.core :refer [quick-bench]]))

;; definição de funções de manipulação de listas usando abordagens diferentes.

(defn my-map [f coll]
  (if (empty? coll)
    '()
    (cons (f (first coll)) (my-map f (rest coll)))))

(defn my-map-v2 [f coll]
  (loop [result '()
         remaining coll]
    (if (empty? remaining)
      result
      (recur (conj result (f (first remaining)))
             (rest remaining)))))

(defn my-filter [pred coll]
  (if (empty? coll)
    '()
    (if (pred (first coll))
      (cons (first coll) (my-filter pred (rest coll)))
      (my-filter pred (rest coll)))))

(defn my-filter-v2 [pred coll]
  (loop [result '()
         remaining coll]
    (if (empty? remaining)
      result
      (if (pred (first remaining))
        (recur (conj result (first remaining))
               (rest remaining))
        (recur result
               (rest remaining))))))

(defn my-reduce [f init coll]
  (if (empty? coll)
    init
    (my-reduce f (f init (first coll)) (rest coll))))

(defn my-reduce-v2 [f init coll]
  (loop [result init
         remaining coll]
    (if (empty? remaining)
      result
      (recur (f result (first remaining))
             (rest remaining)))))

;; função que calcula o desconto total em uma lista de preços.
(defn calcular-desconto-total [precos]
  (reduce +
          (map (fn [preco] (* preco 0.10))
               (filter (fn [preco] (>= preco 20.0)) precos))))

;; função equivalente a calcular-desconto-total usando as funções customizadas.
(defn calcular-desconto-total-v1 [precos]
  (my-reduce + 0
             (my-map (fn [preco] (* preco 0.10))
                     (my-filter (fn [preco] (>= preco 20.0)) precos))))

;; versão 2 da função calcular-desconto-total usando as funções customizadas.
(defn calcular-desconto-total-v2 [precos]
  (my-reduce-v2 + 0
                (my-map-v2 (fn [preco] (* preco 0.10))
                           (my-filter-v2 (fn [preco] (>= preco 20.0)) precos))))

(defn -main []
  ;; realiza benchmarks para comparar o desempenho das implementações.
  (println "Benchmarking calcular-desconto-total:")
  (quick-bench (calcular-desconto-total '(10 20 30 55 18 70)))

  (println "Benchmarking calcular-desconto-total-v1:")
  (quick-bench (calcular-desconto-total-v1 '(10 20 30 55 18 70)))

  (println "Benchmarking calcular-desconto-total-v2:")
  (quick-bench (calcular-desconto-total-v2 '(10 20 30 55 18 70))))

(-main)