(ns advent.day-2
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn parse-input-line [line]
  (let [[policy password] (str/split line #": ")
        [nums character] (str/split policy #" ")
        [first-num second-num] (str/split nums #"-")]
    {:password (str/trim password)
     :policy   {:character  (-> character (.charAt 0))
                :first-num  (Integer/parseInt first-num)
                :second-num (Integer/parseInt second-num)}}))

(defn password-valid? [{:keys [password policy]}]
  (let [{:keys [first-num second-num character]} policy
        char-count (->> password seq (filter #{character}) count)]
    (and (>= char-count first-num)
         (<= char-count second-num))))

(defn password-valid-new-rule? [{:keys [password policy]}]
  (let [{:keys [first-num second-num character]} policy]
    (not= (= character (.charAt password (dec first-num)))
          (= character (.charAt password (dec second-num))))))

(defn read-input [file]
  (->> file
       io/resource
       io/reader
       line-seq
       (mapv parse-input-line)))

(defn part-1 []
  (let [inputs (read-input "day-2.input")]
    (->> inputs (filter password-valid?) count)))

(defn part-2 []
  (let [inputs (read-input "day-2.input")]
    (->> inputs (filter password-valid-new-rule?) count)))

(comment
  (part-1)
  ;439

  (part-2)
  ;584
  )
