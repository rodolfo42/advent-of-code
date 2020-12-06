(ns advent.day-5
  (:require [clojure.java.io :as io]
            [utils :refer [tap]]))

(defn find-loc
  "Given a loc-str like 'FBBBFFB' or 'LRL', and a char to indicate a bit set like \\B or \\R,
  returns the position of the seat expressed by the string."
  [loc-str set-char]
  (let [leftmost-bit (dec (count loc-str))]
    (reduce-kv
      (fn [loc index c]
        (cond-> loc
          (= c set-char)
          (bit-set (- leftmost-bit index))))
      0
      (vec loc-str))))

(defn seat-loc [binary-str]
  (let [[rows columns] (split-at 7 binary-str)]
    {:row    (-> rows (find-loc \B))
     :column (-> columns (find-loc \R))}))

(defn seat-id [{:keys [row column]}]
  (-> row (* 8) (+ column)))

(defn parse-contents [contents]
  (->> contents
       line-seq
       (map seat-loc)))

(defn read-input [file]
  (->
    file
    io/resource
    io/reader
    parse-contents))

(defn part-1 []
  (let [input (read-input "day-5.input")]
    (->> input
         (map seat-id)
         (apply max))))

(comment

  (part-1)
  ; 922
  )
