;; This rule set applies a sequence type to MGI identifiers based on
;; the type field in the MGI MRK_List file records
;;
;; Gene types and their definitions obtained from: 
;;     http://www.informatics.jax.org/userdocs/marker_feature_types.shtml 
;; Possible gene types include:
;; BAC/YAC end - A region of sequence from the end of a BAC or YAC clone used 
;;               as a reagent in mapping and genome assembly.
;; Complex/Cluster/Region - A group of linked markers characterized by related
;;                          sequence and/or function where the precise location
;;                          or identity of the individual components is obscure.
;; Cytogenetic Marker - A structure within a chromosome or a chromosomal 
;;                      rearrangement that is visible by microscopic 
;;                      examination.
;; DNA Segment - A region of the genome associated with experimental interest, 
;;               often used as a reagent for genetic mapping. Includes RFLP and
;;               other hybridization probes, sequence-tagged sites (STS), and 
;;               regions defined by PCR primer pairs such as microsatellite 
;;               markers).
;; Gene - A region (or regions) that include all of the sequence elements 
;;        necessary to encode a functional transcript. A gene may include 
;;        regulatory regions, transcribed regions, and/or other functional 
;;        sequence regions.
;; Other Genome Feature - A region of the genome associated with biological 
;;                        interest (includes regulatory regions, conserved 
;;                        regions and related sequences, repetitive sequences, 
;;                        and viral integrations).
;; Pseudogene - A sequence that closely resembles a known functional gene, at 
;;              another locus within the genome, that is non-functional a 
;;              consequence of (usually several) mutations that prevent either 
;;              its transcription or translation (or both). In general, 
;;              pseudogenes result from either reverse transcription of a 
;;              transcript of their normal paralog, in which case the pseudogene
;;              typically lacks introns and includes a poly(A) tail, or from 
;;              recombination, in which case the pseudogene is typically a 
;;              tandem duplication of its normal paralog.
;; QTL - A quantitative trait locus (QTL) is a polymorphic locus which contains
;;       alleles that differentially affect the expression of a continuously 
;;       distributed phenotypic trait. Usually it is a marker described by 
;;       statistical association to quantitative variation in the particular 
;;       phenotypic trait that is thought to be controlled by the cumulative 
;;       action of alleles at multiple loci.
;; Transgene - A gene that has been transferred naturally or by any of a number
;;             of genetic engineering techniques from one organism to another.

;; BAC/YAC end maps to SO:0000150 (so:read)
`{:name "mgi-entity-bacyac-type-assertion"
  :head ((?/bio-entity rdf/type so/S0_0000150))
  :body ((_/record kiao/hasTemplate iaomgi/egMTKListFileDataSchema1)
         ~@(kabob/rtv _/record
                      iaomig/mgimgiAccessionIDDataField1 _/ice
                      iaomgi/mgitypeDataField1 ["BAC/YAC end" "en"])
         (_/ice iao/IAO_0000219 ?/bio-entity))} ; denotes

;; Complex/Cluster/Region maps to SO:0000001 (so:region)
`{:name "mgi-entity-ccr-type-assertion"
  :head ((?/bio-entity rdf/type so/S0_))
  :body ((_/record kiao/hasTemplate iaomgi/egMTKListFileDataSchema1)
         ~@(kabob/rtv _/record
                      iaomig/mgimgiAccessionIDDataField1 _/ice
                      iaomgi/mgitypeDataField1 ["Complex/Cluster/Region" "en"])
         (_/ice iao/IAO_0000219 ?/bio-entity))} ; denotes

;; Cytogenetic Marker maps to SO:0001645 (so:genetic_marker)
`{:name "mgi-entity-cytomarker-type-assertion"
 :head ((?/bio-entity rdf/type so/S0_0001645))
  :body ((_/record kiao/hasTemplate iaomgi/egMTKListFileDataSchema1)
         ~@(kabob/rtv _/record
                      iaomig/mgimgiAccessionIDDataField1 _/ice
                      iaomgi/mgitypeDataField1 ["" "en"])
         (_/ice iao/IAO_0000219 ?/bio-entity))} ; denotes

;; STILL NEEDS CLASSIFICATION !!!!!!!
;; DNA Segment maps to SO: (so:)
`{:name "mgi-entity--type-assertion"
 :head ((?/bio-entity rdf/type so/S0_))
  :body ((_/record kiao/hasTemplate iaomgi/egMTKListFileDataSchema1)
         ~@(kabob/rtv _/record
                      iaomig/mgimgiAccessionIDDataField1 _/ice
                      iaomgi/mgitypeDataField1 ["" "en"])
         (_/ice iao/IAO_0000219 ?/bio-entity))} ; denotes

;; Gene maps to SO:0000704 (so:gene)
`{:name "mgi-entity-gene-type-assertion"
 :head ((?/bio-entity rdf/type so/S0_0000704))
  :body ((_/record kiao/hasTemplate iaomgi/egMTKListFileDataSchema1)
         ~@(kabob/rtv _/record
                      iaomig/mgimgiAccessionIDDataField1 _/ice
                      iaomgi/mgitypeDataField1 ["gene" "en"])
            (_/ice iao/IAO_0000219 ?/bio-entity))} ; denotes

;; Other Genome Feature maps to SO:0000110 (so:sequence_feature)
`{:name "mgi-entity-ogf-type-assertion"
 :head ((?/bio-entity rdf/type so/S0_0000110))
  :body ((_/record kiao/hasTemplate iaomgi/egMTKListFileDataSchema1)
         ~@(kabob/rtv _/record
                      iaomig/mgimgiAccessionIDDataField1 _/ice
                      iaomgi/mgitypeDataField1 ["Other Genome Feature" "en"])
         (_/ice iao/IAO_0000219 ?/bio-entity))} ; denotes

;; Pseudogene maps to SO:0000336 (so:pseudogene)
`{:name "mgi-entity-pseudogene-type-assertion"
 :head ((?/bio-entity rdf/type so/S0_0000336))
  :body ((_/record kiao/hasTemplate iaomgi/egMTKListFileDataSchema1)
         ~@(kabob/rtv _/record
                      iaomig/mgimgiAccessionIDDataField1 _/ice
                      iaomgi/mgitypeDataField1 ["pseudogene" "en"])
         (_/ice iao/IAO_0000219 ?/bio-entity))} ; denotes

;; QTL maps to SO:0000771 (so:QTL)
`{:name "mgi-entity-qtl-type-assertion"
 :head ((?/bio-entity rdf/type so/S0_0000771))
  :body ((_/record kiao/hasTemplate iaomgi/egMTKListFileDataSchema1)
         ~@(kabob/rtv _/record
                      iaomig/mgimgiAccessionIDDataField1 _/ice
                      iaomgi/mgitypeDataField1 ["QTL" "en"])
         (_/ice iao/IAO_0000219 ?/bio-entity))} ; denotes

;; Transgene maps to SO:0000902 (so:transgene)
`{:name "mgi-entity-transgene-type-assertion"
  :head ((?/bio-entity rdf/type so/S0_))
  :body ((_/record kiao/hasTemplate iaomgi/egMTKListFileDataSchema1)
         ~@(kabob/rtv _/record
                      iaomig/mgimgiAccessionIDDataField1 _/ice
                      iaomgi/mgitypeDataField1 ["transgene" "en"])
         (_/ice iao/IAO_0000219 ?/bio-entity))} ; denotes