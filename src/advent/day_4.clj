(ns advent.day-4
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [utils :as utils]))

(defn field-batch-str->id [field-batch-str]
  (apply hash-map (str/split field-batch-str #"( |:)")))

(defn group-id-fields [lines]
  (reduce (fn [all line]
            (if (seq line)
              (cons (str/trim (str (first all) " " line))
                    (next all))
              (cons "" all)))
          []
          lines))

(defn valid-id? [id]
  (let [required-keys ["byr" "iyr" "eyr" "hgt" "hcl" "ecl" "pid"]]
    (every? id required-keys)))

(defn read-input [file]
  (->>
    file
    io/resource
    io/reader
    line-seq
    group-id-fields
    (map field-batch-str->id)))

(comment

  (let [input (read-input "day-4.input")]
    (count (filter valid-id? input)))
  ; 235
  )
