(ns boot.new.boot-react-native
  (:require [clojure.string :as str]
            [clojure.java.io :as io]
            [cpath-clj.core :as cp]
            [camel-snake-kebab.core :as kebab]
            [boot.new.templates :refer [renderer name-to-path ->files]]))

(defn get-rel-path [path from]
  (last (str/split path from)))

(defn replace-excl [string]
  (str/replace string #"!" ""))

(defn list-files [path]
  (->>
    (cp/resources path)
    (map #(first (val %)))))

(defn sandr [path data]
  (str/replace path #"mattsum\/simple_example|mattsum.simple_example|SimpleExampleApp|mattsum.simple-example"
    {"mattsum/simple_example" (:sanitized data)
     "mattsum.simple_example" (:sanitized data)
     "mattsum.simple-example" (:name data)
     "SimpleExampleApp" (:capitalized data)}))

(defn boot-react-native
  "Main function. Moves and search/replaces files from resources."
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)
              :capitalized (kebab/->PascalCase name)}
        example-folder "boot/new/boot_react_native/"]

    (println "Generating fresh 'boot new' boot-react-native project.")

    (apply (partial ->files data)
      (->> (list-files example-folder)
        (mapv #(vector (sandr (get-rel-path (.toString %) #"boot/new/boot_react_native/example/") data)
                (sandr (slurp %) data)))))))
