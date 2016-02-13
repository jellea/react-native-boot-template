(def project 'boot-react-native/boot-template)
(def version "0.1.0-SNAPSHOT")

(set-env! :resource-paths #{"resources" "src"}
          :dependencies   '[[org.clojure/clojure "RELEASE"]
                            [cpath-clj "0.1.2"]
                            [seancorfield/boot-new "RELEASE"]
                            [camel-snake-kebab "0.3.2"]
                            [adzerk/bootlaces "0.1.13" :scope "test"]
                            [adzerk/boot-test "RELEASE" :scope "test"]])

(task-options!
 pom {:project     project
      :version     version
      :description "A Boot template for boot-react-native"
      :url         "https://github.com/jellea/boot-react-native-template"
      :scm         {:url "https://github.com/jellea/boot-react-native-template"}
      :license     {"Eclipse Public License"
                    "http://www.eclipse.org/legal/epl-v10.html"}})

(deftask build
  "Build and install the project locally."
  []
  (comp (pom) (jar) (install)))

(require '[adzerk.boot-test :refer [test]]
         '[adzerk.bootlaces :refer :all]
         '[boot.new :refer [new]])

 (bootlaces! version)
