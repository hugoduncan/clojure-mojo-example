(ns maven.clojure.example.plugin
  (:import
   [maven.clojure.annotations
    Goal RequiresDependencyResolution Parameter]
   org.apache.maven.plugin.Mojo))

(deftype
  #^{Goal "example"
     RequiresDependencyResolution "compile"}
  Plugin
  [ #^{:volatile-mutable true} log
    #^{Parameter {:expression "${basedir}"
                  :required true
                  :readonly true}}
    base-directory]
  Mojo
  (setLog
   [_ logger]
   (set! log logger))
  (getLog
   [_]
   log)
  (execute
   [this]
   (.info log (str "Base directory" base-directory))))

(defn make-Plugin
  []
  (Plugin. nil nil))
