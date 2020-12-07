(ns advent.day-4-test
  (:require [clojure.test :refer :all]
            [advent.day-4 :refer [read-input strict-valid-id? part-1 part-2]]))

(deftest strict-valid-id?-test
  (testing "invalid passports"
    (let [input (read-input #advent/input "day-4/invalid-passports")]
      (is (= 0 (->> input (filter strict-valid-id?) count)))))

  (testing "valid passports"
    (let [input (read-input #advent/input "day-4/valid-passports")]
      (is (= 4 (->> input (filter strict-valid-id?) count))))))

(deftest part-1-test
  (is (= (part-1) 235)))

(deftest part-2-test
  (is (= (part-2) 194)))
