(ns trec.tsk5
  (:require [criterium.core :refer [quick-bench]]))

(defn quicksort [coll]
  ;; implementa quicksort clássico utilzando recursão
  (if (empty? coll) ;; se coll estiver vazia já está ordenada, fujnção retorna a lista original 
    coll
    (let [pivot (first coll) ;; primeiro elemento escolhido com pivot
          lesser (filter #(< % pivot) (rest coll)) ;; filtra elementos menores que o pivot
          greater (filter #(>= % pivot) (rest coll))] ;; filtra elementos maiores ou iguais ao pivot
      (concat (quicksort lesser) [pivot] (quicksort greater))))) ;; concatena as duas partes 

(defn quicksort-tailrec
  ;; acresecenta um acumulador para acumular os elementos já ordenados
  ([coll] (quicksort-tailrec coll [])) ;; se chamada com um único argumento (coll), ela chama o segundo argumento com um acumulador vazio.
  ([coll acc] ;; realiza a rdenação com nos argumentos passados
   (if (empty? coll) ; se a lista estiver vazia retorna o acumulador
     acc
     (let [pivot (first coll)
           lesser (filter #(< % pivot) (rest coll))
           greater (filter #(>= % pivot) (rest coll))]
       (recur lesser (conj (conj acc pivot) (quicksort-tailrec greater))))))) 


(defn random-list [size] ; cria uma lista de número aleatórios
  (repeatedly size #(rand-int 1000)))

(defn test-quicksort-tailrec-performance [list-size] ; teste para o relatório de performance usando criterium 
  (let [input (random-list list-size)]
    (quick-bench (quicksort-tailrec input))))

(defn test-quicksort-performance [list-size] ; teste para o relatório de performance usando criterium
  (let [input (random-list list-size)]
    (quick-bench (quicksort input))))

;; retonra o desempenho com diferentes tamanhos de lista para o qs classico
(println "Performance para lista de tamanho 1000 com recursão:")
(test-quicksort-performance 1000)

(println "Performance para lista de tamanho 100000 com recursão:")
(test-quicksort-performance 100000)

;; retonra o desempenho com diferentes tamanhos de lista para o qs com recurssão de cauda
(println "Performance para lista de tamanho 1000 com recursão de cauda:")
(test-quicksort-tailrec-performance 1000)

(println "Performance para lista de tamanho 100000 com recursão de cauda:")
(test-quicksort-tailrec-performance 100000)



