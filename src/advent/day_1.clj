(ns advent.day-1
  (:require [clojure.java.io :as io]))

(defn read-numbers [file]
  (->>
    file
    io/resource
    io/reader
    line-seq
    (mapv #(Integer/parseInt %))))

(defn find-two-nums [numbers target]
  (let [found (atom nil)]
    (doseq [n numbers
            :while (nil? @found)]
      (let [search (- target n)]
        (when (some #{search} numbers)
          (reset! found [n search]))))
    @found))

(defn find-three-nums [numbers target]
  (let [found (atom nil)]
    (doseq [n numbers
            :while (nil? @found)]
      (let [search (- target n)]
        (when-let [two-nums (find-two-nums numbers search)]
          (reset! found (conj two-nums n)))))
    @found))

(defn part-1 []
  (let [numbers (read-numbers "day-1.input")]
    (when-let [two-nums (find-two-nums numbers 2020)]
      (apply * two-nums))))

(defn part-2 []
  (let [numbers (read-numbers "day-1.input")]
    (when-let [three-nums (find-three-nums numbers 2020)]
      (apply * three-nums))))


(comment

  (part-1)
  ; 1013211

  (part-2)
  ; 13891280
  )
