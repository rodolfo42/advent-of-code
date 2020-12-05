(ns advent.day-1-test
  (:require [clojure.test :refer :all]
            [advent.day-1 :refer [except]]))

(deftest except-test
  (= (except [23 45 75 34 78] 2) [23 45 34 78])
  (= (except [23 45 75 34 78] 0) [45 75 34 78]))
