(ns utils
  (:require [puget.printer :as puget]
            [clojure.string :as str]
            [puget.color.ansi :as ansi]))

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
