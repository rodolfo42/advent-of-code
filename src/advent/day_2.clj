(ns advent.day-2
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn parse-input-line [line]
  (let [[policy password] (str/split line #": ")
        [min-max character] (str/split policy #" ")
        [min-chars max-chars] (str/split min-max #"-")]
    {:password (str/trim password)
     :policy   {:character (-> character (.charAt 0))
                :min-chars (Integer/parseInt min-chars)
                :max-chars (Integer/parseInt max-chars)}}))

(defn password-valid? [{:keys [password policy]}]
  (let [{:keys [min-chars max-chars character]} policy
        char-count (->> password seq (filter #{character}) count)]
    (and (>= char-count min-chars)
         (<= char-count max-chars))))

(defn read-input [file]
  (->> file
       io/resource
       io/reader
       line-seq
       (mapv parse-input-line)))

(defn day-3 []
  (let [inputs (read-input "day-2.input")]
    (->> inputs (filter password-valid?) count)))

(comment
  (day-3)
  ;439
  )
