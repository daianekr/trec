(ns trec.tsk1-test
  (:require [clojure.test :refer :all]
            [trec.tsk1 :as tsk1]
            [criterium.core :as c]))


;; arquivo de teste unitário para a função fatorial. Foi constrído apenas a rigor de teste pessoal. 
(deftest test-tsk1
  (is (= (tsk1/fatorial 0) 1))
  (is (= (tsk1/fatorial 1) 1))
  (is (= (tsk1/fatorial 2) 2))
  (is (= (tsk1/fatorial 3) 6))
  (is (= (tsk1/fatorial 4) 24))
  (is (= (tsk1/fatorial 5) 120))
  (is (= (tsk1/fatorial 6) 720))
  (is (= (tsk1/fatorial 7) 5040))
  (is (= (tsk1/fatorial 8) 40320))
  (is (= (tsk1/fatorial 9) 362880))
  (is (= (tsk1/fatorial 10) 3628800)))

(deftest performance-test-fatorial-10
  (let [result (c/quick-bench (tsk1/fatorial 10))]
    (is (< (:mean result) 100) "Mean execution time should be less than 100 ns")))

(run-tests 'trec.tsk1-test)