(ns edu.ucdenver.ccp.kabob.build.simple-query-kb
  (use edu.ucdenver.ccp.kr.kb
       edu.ucdenver.ccp.kr.clj-ify
       [edu.ucdenver.ccp.kr.rdf :exclude (resource)]
       edu.ucdenver.ccp.kr.sparql
       edu.ucdenver.ccp.kr.sesame.kb
       [edu.ucdenver.ccp.kr.sesame.rdf :exclude (resource)]
       [clojure.java.io :exclude (resource)]

       ;;for the kabob namespaces
       edu.ucdenver.ccp.kabob.namespace)
  (require [edu.ucdenver.ccp.kr.sesame.sparql :as sparql]
           [clojure.java.io :as io])
  (import  ;;org.openrdf.model.impl.ValueFactoryBase
           java.net.URL
           java.net.URLEncoder
           java.io.DataOutputStream

           org.openrdf.query.resultio.QueryResultIO
           org.openrdf.query.resultio.TupleQueryResultFormat
           org.openrdf.query.TupleQueryResultHandlerBase
           org.openrdf.query.resultio.TupleQueryResultParser


           org.openrdf.model.impl.ValueFactoryImpl))


;;; --------------------------------------------------------
;;; connections
;;; --------------------------------------------------------

;;this is nonsese becasue to the circular defintions
;;  and what can and cannot be forward delcared
(declare initialize-simple-query-kb
         open-simple-query-kb
         close-simple-query-kb
         simple-query-ask-pattern
         simple-query-query-pattern
         simple-query-count-pattern
         simple-query-visit-pattern
         simple-query-construct-pattern
         simple-query-construct-visit-pattern

         simple-query-ask-sparql
         simple-query-query-sparql
         simple-query-count-sparql
         simple-query-visit-sparql
         simple-query-construct-sparql
         simple-query-construct-visit-sparql

         ;;sesame-write-statement
         ;;sesame-write-statements
         )

;;; --------------------------------------------------------
;;; protocol implementation
;;; --------------------------------------------------------

(defrecord SimpleQueryKB [url params connection]
  KB

  (native [kb] url)
  (initialize [kb] kb) ;(initialize-sesame-writer kb))
  (open [kb] kb)
  (close [kb] kb)
  ;; (open [kb] (open-simple-query-kb kb))
  ;; (close [kb] (close-simple-query-kb kb))

  rdfKB

  ;; (ns-maps [kb] ns-maps-var)
  ;; (ns-map-to-short [kb] (:ns-map-to-short (deref ns-maps-var)))
  ;; (ns-map-to-long [kb] (:ns-map-to-long (deref ns-maps-var)))
  (root-ns-map [kb] (ns-map-to-long kb))
  (register-ns [kb short long] nil) ; no-op
  
  (create-resource [kb name] (sesame-create-resource kb name))
  (create-property [kb name] (sesame-create-property kb name))
  (create-literal [kb val] (sesame-create-literal kb val))
  (create-literal [kb val type] (sesame-create-literal kb val type))

  (create-string-literal [kb str] (sesame-create-literal kb str))
  (create-string-literal [kb str lang] 
                         (sesame-create-literal kb str lang))


  (create-blank-node [kb name] (sesame-create-blank-node kb name))
  (create-statement [kb s p o] (sesame-create-statement kb s p o))

 sparqlKB

  (ask-pattern [kb pattern] 
             (simple-query-ask-pattern kb pattern))
  (ask-pattern [kb pattern options]
             (simple-query-ask-pattern kb pattern options))

  (query-pattern [kb pattern]
        (simple-query-query-pattern kb pattern))
  (query-pattern [kb pattern options]
        (simple-query-query-pattern kb pattern options))

  ;; (count-pattern [kb pattern]
  ;;       (simple-query-count-pattern kb pattern))
  ;; (count-pattern [kb pattern options]
  ;;       (simple-query-count-pattern kb pattern options))

  (visit-pattern [kb visitor pattern]
    (simple-query-visit-pattern kb visitor pattern))
  (visit-pattern [kb visitor pattern options]
    (simple-query-visit-pattern kb visitor pattern options))

  ;; (construct-pattern [kb create-pattern pattern]
  ;;       (simple-query-construct-pattern kb create-pattern pattern))
  ;; (construct-pattern [kb create-pattern pattern options]
  ;;       (simple-query-construct-pattern kb create-pattern pattern options))
  ;; (construct-visit-pattern [kb visitor create-pattern pattern]
  ;;   (simple-query-construct-visit-pattern kb visitor create-pattern pattern))
  ;; (construct-visit-pattern [kb visitor create-pattern pattern options]
  ;;   (simple-query-construct-visit-pattern kb visitor create-pattern pattern options))

  
  (ask-sparql [kb query-string]
            (simple-query-ask-sparql kb query-string))
  (query-sparql [kb query-string]
        (simple-query-query-sparql kb query-string))
  ;; (count-sparql [kb query-string]
  ;;   (simple-query-count-sparql kb query-string))
  (visit-sparql [kb visitor query-string]
    (simple-query-visit-sparql kb visitor query-string))

  ;; (construct-sparql [kb sparql-string]
  ;;       (simple-query-construct-sparql kb sparql-string))
  ;; (construct-visit-sparql [kb visitor sparql-string]
  ;;   (simple-query-construct-visit-sparql kb visitor sparql-string))

)


;;; --------------------------------------------------------
;;; send query
;;; --------------------------------------------------------

;;(def ^:dynamic *optional-query-params* [])

(def ^:dynamic *optional-query-string* "")

  ;; private void sendPost() throws Exception 
  ;; {

  ;;  String url = "https://selfsolve.apple.com/wcResults.do";
  ;;  URL obj = new URL(url);
  ;;  HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
   
  ;;  //add reuqest header
  ;;  con.setRequestMethod("POST");
  ;;  con.setRequestProperty("User-Agent", USER_AGENT);
  ;;  con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
   
  ;;  String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
   
  ;;  // Send post request
  ;;  con.setDoOutput(true);
  ;;  DataOutputStream wr = new DataOutputStream(con.getOutputStream());
  ;;  wr.writeBytes(urlParameters);
  ;;  wr.flush();
  ;;  wr.close();
   
  ;;  int responseCode = con.getResponseCode();
  ;;  System.out.println("\nSending 'POST' request to URL : " + url);
  ;;  System.out.println("Post parameters : " + urlParameters);
  ;;  System.out.println("Response Code : " + responseCode);
   
  ;;  BufferedReader in = new BufferedReader(
  ;;                                         new InputStreamReader(con.getInputStream()));
  ;;  String inputLine;
  ;;  StringBuffer response = new StringBuffer();
   
  ;;  while ((inputLine = in.readLine()) != null) {
  ;;                                               response.append(inputLine);
  ;;                                               }
  ;;  in.close();
   
  ;;  //print result
  ;;  System.out.println(response.toString());
   
  ;;  }

;; UriBuilder.fromUri("http://{host}/{path}?q={param}")
;;     .resolveTemplate("host", "localhost")
;;     .resolveTemplate("path", "myApp")
;;     .resolveTemplate("param", "value").build();
 
;; uri.toString();

;; (def default-params [["format" "application/sparql-results+xml"]
;;                      ["timeout" "0"]
;;                      ["debug"  "on"]
;;                      ["default-graph-uri" ""]
;;                      ["query" "select ?Concept where {[] a ?Concept} LIMIT 100"]])

(def rgd-params [["format" "application/sparql-results+xml"]
                     ["timeout" "0"]
                     ["debug"  "on"]
                     ["default-graph-uri" ""]
                     ["query"
"SELECT ?ice 
WHERE {  _:f <http://kabob.ucdenver.edu/iao/hasTemplate> <http://kabob.ucdenver.edu/iao/rgd/RgdGeneFileRecord_geneIdDataField1> .  
 _:f <http://purl.obolibrary.org/obo/IAO_0000219> ?ice .  
}"]])


(def eg-params [["format" "application/sparql-results+xml"]
                ;["timeout" "0"]
                     ;; ["timeout" "1000000000"]
                     ;; ["maxrows" "1000000000"]
                     ["timeout" "100000000"]
                     ["maxrows" "2000000"]
                     ;["debug"  "off"]
                     ["debug"  "on"]
                     ;;["default-graph-uri" ""]
                     ["default-graph-uri" "http://kabob.ucdenver.edu"]
                     ["query"
"SELECT ?ice 
WHERE {  ?f <http://kabob.ucdenver.edu/iao/hasTemplate> <http://kabob.ucdenver.edu/iao/eg/EntrezGeneInfoFileData_geneIDDataField1> .  
 ?f <http://purl.obolibrary.org/obo/IAO_0000219> ?ice .  
} "]])


(def ^:dynamic *default-params* [["format" "application/sparql-results+xml"]
                                 ["timeout" "100000000"]
                                 ["maxrows" "2000000"]
                                 ["timeout" "0"]
                                 ["debug"  "off"]
                                 ["default-graph-uri" ""]])


(defn encode-param [param]
  (cond
   (nil? param) ""
   (= "" param) ""
   :else (URLEncoder/encode param "UTF-8")))

(defn make-param-string [params]
  (apply str
         (doall
          (interpose "&"
                     (doall
                      (map (fn [[k v]]
                             (str k "=" (encode-param v)))
                           params))))))

(def ^:dynamic *out-location* (io/file "/temp/test-query.xml"))

;; (defn http-post [url-string params ]
;;   (let [url (URL. url-string)
;;         con (.openConnection url)]
;;     (doto con
;;       (.setRequestMethod "POST")
;;       (.setRequestProperty "User-Agent" "clojure/simple-query")
;;       (.setRequestProperty "Accept-Language" "en-US,en;q=0.5")
;;       (.setRequestProperty "Content-Type" "application/x-www-form-urlencoded")
;;       (.setDoOutput true))
;;     (let [ostream (DataOutputStream. (.getOutputStream con))]
;;       (doto ostream
;;         (.writeBytes (make-param-string params))
;;         (.flush)
;;         (.close)))
;;     (let [response (.getInputStream con)]
;;       (io/copy response *out-location*))))
        

(defn http-post [url-string params]
  (let [url (URL. url-string)
        con (.openConnection url)]
    (doto con
      (.setRequestMethod "POST")
      (.setRequestProperty "User-Agent" "clojure/simple-query")
      (.setRequestProperty "Accept-Language" "en-US,en;q=0.5")
      (.setRequestProperty "Content-Type" "application/x-www-form-urlencoded")
      (.setDoOutput true))
    (let [ostream (DataOutputStream. (.getOutputStream con))]
      (doto ostream
        (.writeBytes (make-param-string params))
        (.flush)
        (.close)))
    con))

(defn http-send-query [url-string query-string params]
  (http-post url-string (conj params
                              ["query" query-string])))

;;; --------------------------------------------------------
;;; main queries - use sparql strings
;;; --------------------------------------------------------

;; Look at using one of these to handle the result stream as it comes back:
;; https://bitbucket.org/openrdf/sesame/src/344c7976bba504905614d38097df3454a2721e99/core/http/client/src/main/java/org/openrdf/http/client/BackgroundTupleResult.java?at=master


;;this returns a boolean
(defn simple-query-ask-sparql [kb query-string]
  false)
  ;; (.evaluate ^BooleanQuery
  ;;            (.prepareBooleanQuery ^RepositoryConnection (connection! kb)
  ;;                                QueryLanguage/SPARQL
  ;;                                query-string)))

;;TupleQueryResult result = QueryResultIO.parse(in, TupleQueryResultFormat/SPARQL);

(defn simple-query-query-sparql [kb query-string]
  (with-open [in (.getInputStream (http-send-query (:url kb) query-string (:params kb)))]
    (doall
     (clj-ify kb (QueryResultIO/parse in TupleQueryResultFormat/SPARQL)))))

  ;; (let [tuplequery (.prepareTupleQuery ^RepositoryConnection (connection! kb)
  ;;                                      QueryLanguage/SPARQL
  ;;                                      query-string)]
  ;;   (.setIncludeInferred tuplequery *use-inference*)
  ;;   (clj-ify kb (.evaluate ^TupleQuery tuplequery))))


(defn simple-query-visit-sparql [kb visitor query-string]
  (with-open [in (.getInputStream (http-send-query (:url kb) query-string (:params kb)))]
    (let [parser (QueryResultIO/createParser TupleQueryResultFormat/SPARQL)
          handler (proxy [TupleQueryResultHandlerBase] []
                    (handleSolution [bindings]
                      (visitor (sparql/clj-ify-bindings kb bindings))))]
      ;;(.setQueryResultHandler ^TupleQueryResultParser parser handler)
      ;;(.setTupleQueryResultHandler ^TupleQueryResultParser parser handler)
      (.setTupleQueryResultHandler parser handler)
      (.parse parser in))))
    


  ;; (let [tuplequery (.prepareTupleQuery ^RepositoryConnection (connection! kb)
  ;;                                      QueryLanguage/SPARQL
  ;;                                      query-string)]
  ;;   (.setIncludeInferred tuplequery *use-inference*)
  ;;   (.evaluate ^TupleQuery tuplequery
  ;;              (proxy [TupleQueryResultHandlerBase] []
  ;;                (handleSolution [bindings]
  ;;                  (visitor (clj-ify-bindings kb bindings)))))))

;; (defn simple-query-visit-count-sparql [kb query-string]
;;   (let [count (atom 0)
;;         tuplequery (.prepareTupleQuery ^RepositoryConnection (connection! kb)
;;                                        QueryLanguage/SPARQL
;;                                        query-string)]
;;     (.setIncludeInferred tuplequery *use-inference*)
;;     (.evaluate ^TupleQuery tuplequery
;;                (proxy [TupleQueryResultHandlerBase] []
;;                  (handleSolution [bindings]
;;                    (swap! count inc))))
;;     @count))



;; (defn simple-query-count-sparql [kb query-string]
;;   (simple-query-visit-count-sparql kb query-string))

;; (defn simple-query-count-1-1 [kb pattern options]
;;   ;;TODO this is rediculous, really? there's nothing better?
;;   ;;     why is the number a raw string? (is this true of all stores?)
;;   ;;(read-string
;;    (second
;;     (first
;;      (first 
;;       (simple-query-query-sparql kb
;;                            (sparql-1-1-count-query pattern options))))));)


;; (defn simple-query-construct-sparql [kb sparql-string]
;;   ;(prn query-string)
;;   (let [graphquery (.prepareGraphQuery ^RepositoryConnection (connection! kb)
;;                                        QueryLanguage/SPARQL
;;                                        sparql-string)]
;;     (.setIncludeInferred graphquery *use-inference*)
;;     (clj-ify kb (.evaluate ^GraphQuery graphquery))))
;;     ;;(.evaluate ^GraphQuery graphquery)))


;; (defn simple-query-construct-visit-sparql [kb visitor sparql-string]
;;   (let [graphquery (.prepareGraphQuery ^RepositoryConnection (connection! kb)
;;                                        QueryLanguage/SPARQL
;;                                        sparql-string)]
;;     (.setIncludeInferred graphquery *use-inference*)
;;     (.evaluate ^GraphQuery graphquery
;;                (proxy [RDFHandlerBase] []
;;                  (handleStatement [stmt]
;;                    (visitor (clj-ify kb stmt)))))))


;;; --------------------------------------------------------
;;; pattern queries - just construct a sparql string and call the above
;;; --------------------------------------------------------

;;this returns a boolean
(defn ^boolean simple-query-ask-pattern [kb pattern & [options]]
  (simple-query-ask-sparql kb (sparql-ask-query pattern options)))

(defn simple-query-query-pattern [kb pattern & [options]]
  (simple-query-query-sparql kb (sparql-select-query pattern options)))

;; (defn simple-query-count-pattern [kb pattern & [options]]
;;   ;;check if 1.1 support is available and if so try that way
;;   (if (has-feature? kb sparql-1-1) ;use fast 1.1 query
;;     (simple-query-count-1-1 kb pattern options)
;;     ;;otherwise turn and burn
;;     (simple-query-count-sparql kb (sparql-select-query pattern options))))

(defn simple-query-visit-pattern [kb visitor pattern & [options]]
  (simple-query-visit-sparql kb visitor (sparql-select-query pattern options)))

;; (defn simple-query-construct-pattern [kb create-pattern pattern & [options]]
;;   (simple-query-construct-sparql kb
;;                            (sparql-construct-query create-pattern
;;                                                    pattern
;;                                                    options)))

;; (defn simple-query-construct-visit-pattern [kb visitor create-pattern pattern
;;                                       & [options]]
;;   (simple-query-construct-visit-sparql kb
;;                                  visitor
;;                                  (sparql-construct-query create-pattern
;;                                                          pattern
;;                                                          options)))


;;; --------------------------------------------------------
;;; kb constructors
;;; --------------------------------------------------------
    

;;   (open [kb] (open-simple-query-kb kb))
;;   (close [kb] (close-simple-query-kb kb))

;; (defn new-sesame-connection [kb]
;;   (copy-sesame-slots (assoc (SesameKB. (:server kb)
;;                                        (.getConnection (:server kb))
;;                                        (features kb))
;;                        :value-factory (:value-factory kb))
;;                      kb))

;; (defn close-existing-sesame-connection [kb]
;;   (when (:connection kb)
;;     (.close (:connection kb)))
;;   (copy-sesame-slots (assoc (SesameKB. (:server kb) nil (features kb))
;;                        :value-factory (:value-factory kb))
;;                      kb))


;; (defn new-writer [out-stream]
;;   (let [writer (Rio/createWriter RDFFormat/NTRIPLES out-stream)]
;;                                  ;(output-stream target))]
;;     (.startRDF writer) ;side effect function doesn't return itself
;;     writer))

;; (defn open-simple-query-writer [kb]
;;   (let [out (output-stream (:target kb))
;;         writer (new-writer out)]
;;     (copy-sesame-slots (assoc (SesameWriterKB. (:target kb) writer)
;;                                                ;(new-writer (:target kb)))
;;                          :output-stream out
;;                          :value-factory (:value-factory kb))
;;                        kb)))

;; (defn close-simple-query-writer [kb]
;;   (when (:connection kb)
;;     (.endRDF (:connection kb))
;;     (.close (:output-stream kb)))
;;   (copy-sesame-slots (assoc (SesameWriterKB. (:target kb)
;;                                              nil)
;;                        :value-factory (:value-factory kb))
;;                      kb))


;;if the target is a zipped output stream it will happily write there
;; e.g. pass in (GZIPOutputStream. (output-stream ...))
(defn new-simple-query-kb [url params]
  (initialize-ns-mappings
   (assoc (SimpleQueryKB. url params nil) ;(initial-ns-mappings) nil)
     :value-factory (org.openrdf.model.impl.ValueFactoryImpl.))))
  ;;(.getValueFactory repository)))


(def ^:dynamic *simple-kb-url* "http://localhost:8890/sparql/")

(defn test-simple-query-kb []
  (let [kb (new-simple-query-kb *simple-kb-url* *default-params*)]
    (update-namespaces (connection kb)
                       *namespaces*)))

;;these can't handle graphs ... TODO change to NQUAD writer??

;; (defn simple-query-write-statement
;;   ([kb stmt] (.handleStatement (connection! kb)
;;                                ^Statment stmt))
;;   ([kb stmt context] (.handleStatement (connection! kb)
;;                                        ^Statement stmt))
;;   ([kb s p o] (.handleStatement (connection! kb) 
;;                                 ^Statement (statement kb s p o)))
;;   ([kb s p o context] (.handleStatement (connection! kb) 
;;                                         ^Statement (statement kb s p o))))


;; (defn simple-query-write-statements
;;   ([kb stmts] (dorun (map (partial simple-query-write-statement kb) stmts)))
;;   ([kb stmts context]  (dorun (map (partial simple-query-write-statement kb) stmts))))


;;; --------------------------------------------------------
;;; END
;;; --------------------------------------------------------
