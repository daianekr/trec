(ns trec.tsk3
  (:require [criterium.core :refer [quick-bench]]))

;; -------------------------------------------------------
;;arquivo usado para a questão 3
;; -------------------------------------------------------

;; função contagem-letras: recebe uma lista de palavras e retorna uma lista de vetores, cada vetor contendo a palavra e o número de letras na palavra.
(defn contagem-letras [palavras]
  (->> palavras
       (map #(vector % (count %)))))

;; função contagem-letras-v2: versão alternativa da função contagem-letras utilizando uma função anônima explícita para criar os vetores.
(defn contagem-letras-v2 [palavras]
  (->> palavras
       (map (fn [word] [word (count word)]))))

;; função contagem-letras-v3: versão alternativa da função contagem-letras utilizando map diretamente com a função anônima.
(defn contagem-letras-v3 [palavras]
  (map (fn [word] [word (count word)]) palavras))

(defn -main []
  (let [palavras ["teste" "teste2" "teste222" "teste33333" "teste555555555"]]
    ;; imprime os resultados das diferentes versões da função contagem-letras para o conjunto de palavras.
    (println "Original: " (contagem-letras palavras))
    (println "Versao 2: " (contagem-letras-v2 palavras))
    (println "Versao 3: " (contagem-letras-v3 palavras))

    ;; realiza benchmarks para avaliar o desempenho das diferentes versões.
    (println "\nBenchmarking:")
    (println "Original:")
    (quick-bench (contagem-letras palavras))
    (println "\nVersao 2:")
    (quick-bench (contagem-letras-v2 palavras))
    (println "\nVersao 3:")
    (quick-bench (contagem-letras-v3 palavras))))

(-main)