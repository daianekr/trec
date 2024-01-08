(ns trec.tsk1
  (:require [criterium.core :refer [quick-bench]]))

(set! *unchecked-math* true)
(set! *warn-on-reflection* true)

;; -------------------------------------------------------
;;arquivo usado para a questão 1
;; -------------------------------------------------------

(defn fatorial [n]
  ;;Implementa função clássica de cálculo de fatorial, utilizando recursão
  (if (<= n 1) ; atribui condição de parada
    1 
    (* n (fatorial (- n 1))))) ;faz o cáluclo do passo e chama a função novamente até chegar em 1

(defn fatorial-loop [n]
  ;; implementa a função de cálculo de fatorial, utilizando uma lógica de loop aliado ao recur 
  (loop [i 1 ; inicializa um contador i com o valor 1
         result 1] ;inicializa um acumulador result com o valor 1
    (if (<= i n) ;se i é menor ou igual a n (condição de parada)
      (recur (inc i) (* i result)) ;; se a condição for verdadeira, chama recursivamente o loop com i incrementado, multiplica i pelo acumulador
      result)))

(defn fatorial-tail-rec [n]
  (if (zero? n) ;;verifica se n é zero, caso seja, retorna 1 (condição de parada)
    1
    ;; se não for, retorna o produto de n pelo resultado da chamada recursiva com n decrementado
    (* n (fatorial-tail-rec (dec n)))))

(defn fatorial-1 [n]
  ;; cria função auxiliar fatorial-aux que usa trampoline para otimizar a recursão de cauda
  (letfn [(fatorial-aux [n resultado]
            ;; se n for 1, retorna o resultado acumulado           
            (if (= n 1)
              resultado
              ;; caso contrário, retorna uma função anônima que chama recursivamente fatorial-aux
              #(fatorial-aux (- n 1) (* n resultado))))] ; usando trampoline
    ;; faz a recursão de cauda com trampoline, passando n e 1 como argumentos iniciais
    (trampoline (fatorial-aux n 1))))

(defn run-bench*
  ;;inicia a execução do benchmark usando o Criterium
  [name f n]
  (println "\n" name ": ------------------------------")
  (criterium.core/bench (f n)))

(defmacro run-bench
  ;; macro que simplifica a chamada para run-bench*, que tonderaz automaticamente o nome da função 
  [[f n]]
  `(run-bench* ~(name f) ~f ~n))

(defn run-benches
  []
  (let [n 1000] ;; aqui o let define n com 1000 podendo ser facilmente alterado 
    ;; executa os benchmarks para as diferentes implementações da função fatorial
    (run-bench (fatorial n))
    (run-bench (fatorial-loop n))
    (run-bench (fatorial-tail-rec n))
    (run-bench (fatorial-1 n))
))

(run-benches)