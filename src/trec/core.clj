(ns trec.core)
(require '[criterium.core :refer [quick-bench]])

;; -------------------------------------------------------
;;arquivo usado para testes durante a criação do trabalho, as 
;;funções foram sendo transfiridas para cada arquivo específico
;; -------------------------------------------------------

(defn fatorial [n]
  (if (<= n 1)
    1
    (* n (fatorial (- n 1)))))

(defn fatorial-aux [n resultado]
  (if (= n 1)
    resultado
    (recur (- n 1) (* n resultado))))

(defn fatorial1 [n]
  (fatorial-aux n 1N))

(defn soma-lista [lista]
  (if (empty? lista)
    0
    (+ (first lista) (soma-lista (rest lista)))))


(defn filtra_maior_20 [prices]
  (filter (fn [price] (>= price 20)) prices))

(defn multiplica_por_010 [valores]
  (map (fn [price] (* price 0.10)) valores))

(defn calcular-desconto [precos]
  (reduce +  
      (map (fn [preco] (* preco 0.10))
          (filter (fn [preco] (>= preco 20.0)) precos))))

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

(defn square [x] (* x x))

(defn calcular-desconto-total [precos]
  ;; implementação de uma função utilizando funções de alta ordem já existentes no Clojure
  ;; filtra valores acima de 20 (filter). Aplica cálculo de 10 % em todos os valores (map).
  ;; faz a soma de todos os valores resultantes (reduce).
  (reduce + 
      (map (fn [preco] (* preco 0.10))
          (filter (fn [preco] (>= preco 20.0)) precos))))
    

(defn calcular-desconto-total-v2 [precos]
  (my-reduce-v2 + 0
             (my-map-v2 (fn [preco] (* preco 0.10))
                     (my-filter-v2 (fn [preco] (>= preco 20.0)) precos))))

(defn soma-naturais [n]
  ;; A função acima é uma implementação de função recursiva usando clojure que soma todos
  ;; os números naturais que precedem um número natural dado de entrada.  
  (if (<= n 0)
    0
    (+ n (soma-naturais (- n 1)))))

(defn soma-naturais-cauda [n]
   ;; A função acima é uma implementação de função recursiva usando clojure que soma todos
   ;; os números naturais que precedem um número natural dado de entrada. Implementada com 
   ;; recur, pelo método de recursão de cauda
  (loop [acc 0
         num n]
    (if (<= num 0)
      acc
      (recur (+ acc num) (- num 1)))))

(defn contagem-letras [palavras]
  ;; função que recebe uma lista e retorna a contagem de caracteres de cada elemento da lista
  (->> palavras
       (map #(vector % (count %)))))


(defn aplicar-desconto [produtos]
  (letfn [(aplicar-desconto-aux [produtos]
            (if (empty? produtos)
              '()
              (let [produto (first produtos)
                    preco (get produto :preco)
                    desconto (if (< preco 50) 0.05 0.1)]
                (cons (assoc produto :desconto (* preco desconto)) (aplicar-desconto-aux (rest produtos))))))]
    (aplicar-desconto-aux produtos)))


