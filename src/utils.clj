(ns utils
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [puget.color.ansi :as ansi]
            [puget.printer :as puget]))

(defn tap [x]
  (print (ansi/sgr " => " :bold :white))
  (puget/cprint x)
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
     io/reader))
