(ns advent.day-5-test
  (:require [clojure.test :refer :all]
            [advent.day-5 :refer [find-missing]]))

(deftest find-missing-test
  (is (= (find-missing [450 453 454 451 455 456 449])
         452)))
