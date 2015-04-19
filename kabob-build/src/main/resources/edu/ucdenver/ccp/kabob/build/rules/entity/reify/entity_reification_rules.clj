;; <http://kabob.ucdenver.edu/iao/KaBOB-ID-Set-aWMnMcT47Ppv2-iyaSV56GAeMAQ> <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://kabob.ucdenver.edu/iao/ID-Set> .
;; <http://kabob.ucdenver.edu/iao/KaBOB-ID-Set-aWMnMcT47Ppv2-iyaSV56GAeMAQ> <http://kabob.ucdenver.edu/ro/hasMember> <http://kabob.ucdenver.edu/iao/refseq/REFSEQ_YP_003325284_ICE> .


;;converts ID sets into reified entities
`{:name "idset-mentions-entity"
  :head ((?/idset obo/IAO_0000142 ?/entity))
  :body ((?/idset rdf/type kiao/ID-Set))
  :reify ([?/entity {:ln (:md5 ?/idset)
                     :ns "kbio" :prefix "BIO_" :suffix ""}])}


`{:name "ice-denotes-entity"
  :head ((?/ice obo/IAO_0000219 ?/entity))
  :body ((?/idset rdf/type kiao/ID-Set)
         (?/idset kro/hasMember ?/ice))
  :reify ([?/entity {:ln (:md5 ?/idset)
                     :ns "kbio" :prefix "BIO_" :suffix ""}])}

