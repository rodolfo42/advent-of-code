(ns utils
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [puget.color.ansi :as ansi]
            [puget.printer :as puget]))

(defn split-in-batches-by
  "Split a coll into groups at every element where (pred elem) returns true.
  Does not include these elements in any batch."
  [pred coll]
  (loop [batched   []
         remaining coll]
    (let [[batch rest] (split-with (complement pred) remaining)]
      (if (next rest)
        (recur (conj batched batch) (next rest))
        (conj batched batch)))))

(defn between?
  "True if n is between lower and upper (including), false otherwise"
  [n lower upper]
  (let [n' (cond-> n
             (string? n) Integer/parseInt)]
    (and (>= n' lower)
         (<= n' upper))))

(defn tap [x]
  (println (ansi/sgr " => " :bold :white)
           (puget/cprint-str x))
  x)

(defn tap-reader [x]
  `(let [x# ~x]
     (tap x#)))

(defn tapd-reader [x]
  `(let [x# ~x]
     (-> '~x puget/cprint-str str/trim print)
     (print (ansi/sgr " => " :bold :white))
     (puget/cprint x#)
     x#))

(defn read-input [filename]
  `(->>
     (str ~filename ".input")
     io/resource
     io/reader
     line-seq))
