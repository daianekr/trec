(ns trec.tsk4)

;; -------------------------------------------------------
;;arquivo usado para a questão 4
;; -------------------------------------------------------

;; cria função de adição
(def add (fn [a b] (+ a b)))

;; cria função de subtração
(def subtract (fn [a b] (- a b)))

;; cria função de multiplicação
(def multiply (fn [a b] (* a b)))

;; cria função de divisão
(def divide (fn [a b] (/ a b)))

;; cria função de potência
(defn power [base exponent]
  (if (= exponent 0)
    1
    (* base (power base (dec exponent)))))

;; cria função para verificar se um número é par
(def is-even? (fn [n] (zero? (mod n 2))))

;; cria função qu aplica uma operação a dois operandos usando uma função lambda
(defn apply-operation [a b operation]
  (operation a b))

;; printa a saída das função como teste
(defn demo []
  (println "Adicao: " (apply-operation 5 3 add))
  (println "Subtracao: " (apply-operation 5 3 subtract))
  (println "Multiplicacao: " (apply-operation 5 3 multiply))
  (println "Divisao: " (apply-operation 6 2 divide))
  (println "Potencia: " (power 2 3))
  (println "Eh par? " (is-even? 4)))


(demo)
