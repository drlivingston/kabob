(ns rules-tests.build-test.ccp-ext-ontology)

(def ccp-ext-ontology-triples '((ccp/ccp-extensions.owl rdf/type owl/Ontology)
;; 
;; 
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; ;;
;; ;;    Annotation properties
;; ;;
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 
;; 
;; http://ccp.ucdenver.edu/obo/ext/DC_EXT_0000000
(ccp/DC_EXT_0000000 rdf/type owl/AnnotationProperty)
(ccp/DC_EXT_0000000 rdfs/label ["download date" "en"])
(ccp/DC_EXT_0000000 rdfs/subPropertyOf dc/date)
;; 
;; http://ccp.ucdenver.edu/obo/ext/DC_EXT_0000001
(ccp/DC_EXT_0000001 rdf/type owl/AnnotationProperty)
(ccp/DC_EXT_0000001 rdfs/label ["last modified date" "en"])
(ccp/DC_EXT_0000001 rdfs/subPropertyOf dc/date)
;; 
;; http://ccp.ucdenver.edu/obo/ext/DC_EXT_0000002
(ccp/DC_EXT_0000002 rdf/type owl/AnnotationProperty)
(ccp/DC_EXT_0000002 rdfs/label ["file size (bytes)" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000109
(ccp/IAO_EXT_0000109 rdf/type owl/AnnotationProperty)
(ccp/IAO_EXT_0000109 rdfs/label ["repaired from" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001700
(ccp/IAO_EXT_0001700 rdf/type owl/AnnotationProperty)
(ccp/IAO_EXT_0001700 rdfs/label ["identifier validation regex" "en"])
;; 
;; http://purl.obolibrary.org/obo/BFO_0000179
(obo/BFO_0000179 rdf/type owl/AnnotationProperty)
(obo/BFO_0000179 obo/IAO_0000115 ["Relates an entity in the ontology to the name of the variable that is used to represent it in the code that generates the BFO OWL file from the lispy specification." "en"])
(obo/BFO_0000179 obo/IAO_0000232 ["Really of interest to developers only" "en"])
(obo/BFO_0000179 rdfs/label ["BFO OWL specification label" "en"])
(obo/BFO_0000179 rdfs/subPropertyOf rdfs/label)
;; 
;; http://purl.obolibrary.org/obo/BFO_0000180
(obo/BFO_0000180 rdf/type owl/AnnotationProperty)
(obo/BFO_0000180 obo/IAO_0000115 ["Relates an entity in the ontology to the term that is used to represent it in the the CLIF specification of BFO2" "en"])
(obo/BFO_0000180 obo/IAO_0000119 ["Person:Alan Ruttenberg"])
(obo/BFO_0000180 obo/IAO_0000232 ["Really of interest to developers only" "en"])
(obo/BFO_0000180 rdfs/label ["BFO CLIF specification label" "en"])
(obo/BFO_0000180 rdfs/subPropertyOf rdfs/label)
;; 
;; http://purl.obolibrary.org/obo/IAO_0000111
(obo/IAO_0000111 rdf/type owl/AnnotationProperty)
(obo/IAO_0000111 obo/IAO_0000111 ["editor preferred term" "en"])
(obo/IAO_0000111 obo/IAO_0000114 obo/IAO_0000122)
(obo/IAO_0000111 obo/IAO_0000115 ["The concise, meaningful, and human-friendly name for a class or property preferred by the ontology developers. (US-English)" "en"])
(obo/IAO_0000111 obo/IAO_0000117 ["PERSON:Daniel Schober" "en"])
(obo/IAO_0000111 obo/IAO_0000119 ["GROUP:OBI:obo/obi" "en"])
(obo/IAO_0000111 rdfs/isDefinedBy obo/iao.owl)
(obo/IAO_0000111 rdfs/label ["editor preferred term" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000112
(obo/IAO_0000112 rdf/type owl/AnnotationProperty)
(obo/IAO_0000112 obo/IAO_0000111 ["example" "en"])
(obo/IAO_0000112 obo/IAO_0000114 obo/IAO_0000122)
(obo/IAO_0000112 obo/IAO_0000115 ["A phrase describing how a term should be used and/or a citation to a work which uses it. May also include other kinds of examples that facilitate immediate understanding, such as widely know prototypes or instances of a class, or cases where a relation is said to hold." "en"])
(obo/IAO_0000112 obo/IAO_0000117 ["PERSON:Daniel Schober" "en"])
(obo/IAO_0000112 obo/IAO_0000119 ["GROUP:OBI:obo/obi" "en"])
(obo/IAO_0000112 rdfs/isDefinedBy obo/iao.owl)
(obo/IAO_0000112 rdfs/label ["example of usage" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000113
(obo/IAO_0000113 rdf/type owl/AnnotationProperty)
(obo/IAO_0000113 obo/IAO_0000111 ["in branch" "en"])
(obo/IAO_0000113 obo/IAO_0000115 ["An annotation property indicating which module the terms belong to. This is currently experimental and not implemented yet." "en"])
(obo/IAO_0000113 obo/IAO_0000117 ["GROUP:OBI" "en"])
(obo/IAO_0000113 obo/IAO_0000119 ["OBI_0000277" "en"])
(obo/IAO_0000113 rdfs/label ["in branch" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000114
(obo/IAO_0000114 rdf/type owl/AnnotationProperty)
(obo/IAO_0000114 obo/IAO_0000111 ["has curation status" "en"])
(obo/IAO_0000114 obo/IAO_0000117 ["PERSON:Alan Ruttenberg" "en"])
(obo/IAO_0000114 obo/IAO_0000117 ["PERSON:Bill Bug" "en"])
(obo/IAO_0000114 obo/IAO_0000117 ["PERSON:Melanie Courtot" "en"])
(obo/IAO_0000114 obo/IAO_0000119 ["OBI_0000281" "en"])
(obo/IAO_0000114 rdfs/label ["has curation status" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000115
(obo/IAO_0000115 rdf/type owl/AnnotationProperty)
(obo/IAO_0000115 obo/IAO_0000111 ["definition" "en"])
(obo/IAO_0000115 obo/IAO_0000114 obo/IAO_0000122)
(obo/IAO_0000115 obo/IAO_0000115 ["The official definition, explaining the meaning of a class or property. Shall be Aristotelian, formalized and normalized. Can be augmented with colloquial definitions." "en"])
(obo/IAO_0000115 obo/IAO_0000116 ["2012-04-05: \nBarry Smith\n\nThe official OBI definition, explaining the meaning of a class or property: 'Shall be Aristotelian, formalized and normalized. Can be augmented with colloquial definitions'  is terrible.\n\nCan you fix to something like:\n\nA statement of necessary and sufficient conditions explaining the meaning of an expression referring to a class or property.\n\nAlan Ruttenberg\n\nYour proposed definition is a reasonable candidate, except that it is very common that necessary and sufficient conditions are not given. Mostly they are necessary, occasionally they are necessary and sufficient or just sufficient. Often they use terms that are not themselves defined and so they effectively can't be evaluated by those criteria. \n\nOn the specifics of the proposed definition:\n\nWe don't have definitions of 'meaning' or 'expression' or 'property'. For 'reference' in the intended sense I think we use the term 'denotation'. For 'expression', I think we you mean symbol, or identifier. For 'meaning' it differs for class and property. For class we want documentation that let's the intended reader determine whether an entity is instance of the class, or not. For property we want documentation that let's the intended reader determine, given a pair of potential relata, whether the assertion that the relation holds is true. The 'intended reader' part suggests that we also specify who, we expect, would be able to understand the definition, and also generalizes over human and computer reader to include textual and logical definition. \n\nPersonally, I am more comfortable weakening definition to documentation, with instructions as to what is desirable. \n\nWe also have the outstanding issue of how to aim different definitions to different audiences. A clinical audience reading chebi wants a different sort of definition documentation/definition from a chemistry trained audience, and similarly there is a need for a definition that is adequate for an ontologist to work with.  " "en"])
(obo/IAO_0000115 obo/IAO_0000117 ["PERSON:Daniel Schober" "en"])
(obo/IAO_0000115 obo/IAO_0000119 ["GROUP:OBI:obo/obi" "en"])
(obo/IAO_0000115 rdfs/isDefinedBy obo/iao.owl)
(obo/IAO_0000115 rdfs/label ["definition" "en"])
(obo/IAO_0000115 rdfs/label ["definition"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000116
(obo/IAO_0000116 rdf/type owl/AnnotationProperty)
(obo/IAO_0000116 obo/IAO_0000111 ["editor note" "en"])
(obo/IAO_0000116 obo/IAO_0000114 obo/IAO_0000122)
(obo/IAO_0000116 obo/IAO_0000115 ["An administrative note intended for its editor. It may not be included in the publication version of the ontology, so it should contain nothing necessary for end users to understand the ontology." "en"])
(obo/IAO_0000116 obo/IAO_0000117 ["PERSON:Daniel Schober" "en"])
(obo/IAO_0000116 obo/IAO_0000119 ["GROUP:OBI:<http://purl.obfoundry.org/obo/obi" "en"])
(obo/IAO_0000116 rdfs/isDefinedBy obo/iao.owl)
(obo/IAO_0000116 rdfs/label ["editor note" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000117
(obo/IAO_0000117 rdf/type owl/AnnotationProperty)
(obo/IAO_0000117 obo/IAO_0000111 ["term editor" "en"])
(obo/IAO_0000117 obo/IAO_0000114 obo/IAO_0000122)
(obo/IAO_0000117 obo/IAO_0000115 ["Name of editor entering the term in the file. The term editor is a point of contact for information regarding the term. The term editor may be, but is not always, the author of the definition, which may have been worked upon by several people" "en"])
 ;(obo/IAO_0000117 obo/IAO_0000116 ["20110707, MC: label update to term editor and definition modified accordingly. See https://github.com/information-artifact-ontology/IAO/issues/115." "en"])
(obo/IAO_0000117 obo/IAO_0000117 ["PERSON:Daniel Schober" "en"])
(obo/IAO_0000117 obo/IAO_0000119 ["GROUP:OBI:obo/obi" "en"])
(obo/IAO_0000117 rdfs/isDefinedBy obo/iao.owl)
(obo/IAO_0000117 rdfs/label ["term editor" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000118
(obo/IAO_0000118 rdf/type owl/AnnotationProperty)
(obo/IAO_0000118 obo/IAO_0000111 ["alternative term" "en"])
(obo/IAO_0000118 obo/IAO_0000114 obo/IAO_0000125)
(obo/IAO_0000118 obo/IAO_0000115 ["An alternative name for a class or property which means the same thing as the preferred name (semantically equivalent)" "en"])
(obo/IAO_0000118 obo/IAO_0000117 ["PERSON:Daniel Schober" "en"])
(obo/IAO_0000118 obo/IAO_0000119 ["GROUP:OBI:obo/obi" "en"])
(obo/IAO_0000118 rdfs/isDefinedBy obo/iao.owl)
(obo/IAO_0000118 rdfs/label ["alternative term" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000119
(obo/IAO_0000119 rdf/type owl/AnnotationProperty)
(obo/IAO_0000119 obo/IAO_0000111 ["definition source" "en"])
(obo/IAO_0000119 obo/IAO_0000114 obo/IAO_0000122)
(obo/IAO_0000119 obo/IAO_0000115 ["formal citation, e.g. identifier in external database to indicate / attribute source(s) for the definition. Free text indicate / attribute source(s) for the definition. EXAMPLE: Author Name, URI, MeSH Term C04, PUBMED ID, Wiki uri on 31.01.2007" "en"])
(obo/IAO_0000119 obo/IAO_0000117 ["PERSON:Daniel Schober" "en"])
(obo/IAO_0000119 obo/IAO_0000119 ["Discussion on obo-discuss mailing-list, see http://bit.ly/hgm99w"])
(obo/IAO_0000119 obo/IAO_0000119 ["GROUP:OBI:obo/obi" "en"])
(obo/IAO_0000119 rdfs/isDefinedBy obo/iao.owl)
(obo/IAO_0000119 rdfs/label ["definition source" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000231
(obo/IAO_0000231 rdf/type owl/AnnotationProperty)
(obo/IAO_0000231 obo/IAO_0000111 ["has obsolescence reason" "en"])
(obo/IAO_0000231 obo/IAO_0000115 ["Relates an annotation property to an obsolescence reason. The values of obsolescence reasons come from a list of predefined terms, instances of the class obsolescence reason specification." "en"])
(obo/IAO_0000231 obo/IAO_0000117 ["PERSON:Alan Ruttenberg" "en"])
(obo/IAO_0000231 obo/IAO_0000117 ["PERSON:Melanie Courtot" "en"])
(obo/IAO_0000231 rdfs/label ["has obsolescence reason" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000232
(obo/IAO_0000232 rdf/type owl/AnnotationProperty)
(obo/IAO_0000232 obo/IAO_0000111 ["curator note" "en"])
(obo/IAO_0000232 obo/IAO_0000114 obo/IAO_0000122)
(obo/IAO_0000232 obo/IAO_0000115 ["An administrative note of use for a curator but of no use for a user" "en"])
(obo/IAO_0000232 obo/IAO_0000117 ["PERSON:Alan Ruttenberg" "en"])
(obo/IAO_0000232 rdfs/isDefinedBy obo/iao.owl)
(obo/IAO_0000232 rdfs/label ["curator note" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000233
(obo/IAO_0000233 rdf/type owl/AnnotationProperty)
(obo/IAO_0000233 obo/IAO_0000111 ["term tracker item"])
(obo/IAO_0000233 obo/IAO_0000112 ["the URI for an OBI Terms ticket at sourceforge, such as https://sourceforge.net/p/obi/obi-terms/772/"])
(obo/IAO_0000233 obo/IAO_0000114 obo/IAO_0000125)
(obo/IAO_0000233 obo/IAO_0000115 ["An IRI or similar locator for a request or discussion of an ontology term."])
(obo/IAO_0000233 obo/IAO_0000117 ["Person: Jie Zheng, Chris Stoeckert, Alan Ruttenberg"])
(obo/IAO_0000233 obo/IAO_0000119 ["Person: Jie Zheng, Chris Stoeckert, Alan Ruttenberg"])
(obo/IAO_0000233 rdfs/comment ["The 'tracker item' can associate a tracker with a specific ontology term."])
(obo/IAO_0000233 rdfs/label ["term tracker item" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000234
(obo/IAO_0000234 rdf/type owl/AnnotationProperty)
(obo/IAO_0000234 obo/IAO_0000114 obo/IAO_0000125)
(obo/IAO_0000234 obo/IAO_0000115 ["The name of the person, project, or organization that motivated inclusion of an ontology term by requesting its addition."])
(obo/IAO_0000234 obo/IAO_0000117 ["Person: Jie Zheng, Chris Stoeckert, Alan Ruttenberg"])
(obo/IAO_0000234 obo/IAO_0000119 ["Person: Jie Zheng, Chris Stoeckert, Alan Ruttenberg"])
(obo/IAO_0000234 rdfs/comment ["The 'term requester' can credit the person, organization or project who request the ontology term."])
(obo/IAO_0000234 rdfs/label ["ontology term requester" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000411
(obo/IAO_0000411 rdf/type owl/AnnotationProperty)
(obo/IAO_0000411 obo/IAO_0000111 ["is denotator type" "en"])
(obo/IAO_0000411 obo/IAO_0000115 ["relates an class defined in an ontology, to the type of it's denotator"])
(obo/IAO_0000411 obo/IAO_0000116 ["In OWL 2 add AnnotationPropertyRange('is denotator type' 'denotator type')"])
(obo/IAO_0000411 obo/IAO_0000117 ["Alan Ruttenberg"])
(obo/IAO_0000411 rdfs/label ["is denotator type"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000412
(obo/IAO_0000412 rdf/type owl/AnnotationProperty)
(obo/IAO_0000412 obo/IAO_0000111 ["imported from" "en"])
(obo/IAO_0000412 obo/IAO_0000114 obo/IAO_0000125)
(obo/IAO_0000412 obo/IAO_0000115 ["For external terms/classes, the ontology from which the term was imported" "en"])
(obo/IAO_0000412 obo/IAO_0000117 ["PERSON:Alan Ruttenberg" "en"])
(obo/IAO_0000412 obo/IAO_0000117 ["PERSON:Melanie Courtot" "en"])
(obo/IAO_0000412 obo/IAO_0000119 ["GROUP:OBI:obo/obi" "en"])
(obo/IAO_0000412 rdfs/isDefinedBy obo/iao.owl)
(obo/IAO_0000412 rdfs/label ["imported from" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000424
(obo/IAO_0000424 rdf/type owl/AnnotationProperty)
(obo/IAO_0000424 obo/IAO_0000111 ["expand expression to" "en"])
(obo/IAO_0000424 obo/IAO_0000112 ["ObjectProperty: RO_0002104\nLabel: has plasma membrane part\nAnnotations: IAO_0000424 \"http://purl.obolibrary.org/obo/BFO_0000051 some (http://purl.org/obo/owl/GO;;GO_0005886 and http://purl.obolibrary.org/obo/BFO_0000051 some ?Y)\n" "en"])
(obo/IAO_0000424 obo/IAO_0000115 ["A macro expansion tag applied to an object property (or possibly a data property)  which can be used by a macro-expansion engine to generate more complex expressions from simpler ones" "en"])
(obo/IAO_0000424 obo/IAO_0000117 ["Chris Mungall"])
(obo/IAO_0000424 rdfs/label ["expand expression to" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000425
(obo/IAO_0000425 rdf/type owl/AnnotationProperty)
(obo/IAO_0000425 obo/IAO_0000111 ["expand assertion to" "en"])
(obo/IAO_0000425 obo/IAO_0000112 ["ObjectProperty: RO???\nLabel: spatially disjoint from\nAnnotations: expand_assertion_to \"DisjointClasses: (http://purl.obolibrary.org/obo/BFO_0000051 some ?X)  (http://purl.obolibrary.org/obo/BFO_0000051 some ?Y)\n" "en"])
(obo/IAO_0000425 obo/IAO_0000115 ["A macro expansion tag applied to an annotation property which can be expanded into a more detailed axiom." "en"])
(obo/IAO_0000425 obo/IAO_0000117 ["Chris Mungall" "en"])
(obo/IAO_0000425 rdfs/label ["expand assertion to" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000426
(obo/IAO_0000426 rdf/type owl/AnnotationProperty)
(obo/IAO_0000426 obo/IAO_0000111 ["first order logic expression" "en"])
(obo/IAO_0000426 obo/IAO_0000117 ["PERSON:Alan Ruttenberg" "en"])
(obo/IAO_0000426 rdfs/label ["first order logic expression" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000427
(obo/IAO_0000427 rdf/type owl/AnnotationProperty)
(obo/IAO_0000427 obo/IAO_0000111 ["antisymmetric property" "en"])
(obo/IAO_0000427 obo/IAO_0000112 ["part_of antisymmetric property xsd:true" "en"])
(obo/IAO_0000427 obo/IAO_0000115 ["use boolean value xsd:true to indicate that the property is an antisymmetric property" "en"])
(obo/IAO_0000427 obo/IAO_0000117 ["Alan Ruttenberg" "en"])
(obo/IAO_0000427 rdfs/label ["antisymmetric property" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000589
(obo/IAO_0000589 rdf/type owl/AnnotationProperty)
(obo/IAO_0000589 obo/IAO_0000111 ["OBO foundry unique label" "en"])
(obo/IAO_0000589 obo/IAO_0000114 obo/IAO_0000125)
(obo/IAO_0000589 obo/IAO_0000115 ["An alternative name for a class or property which is unique across the OBO Foundry." "en"])
(obo/IAO_0000589 obo/IAO_0000116 ["The intended usage of that property is as follow: OBO foundry unique labels are automatically generated based on regular expressions provided by each ontology, so that SO could specify unique label = 'sequence ' + [label], etc. , MA could specify 'mouse + [label]' etc. Upon importing terms, ontology developers can choose to use the 'OBO foundry unique label' for an imported term or not. The same applies to tools ."])
(obo/IAO_0000589 obo/IAO_0000117 ["PERSON:Alan Ruttenberg" "en"])
(obo/IAO_0000589 obo/IAO_0000117 ["PERSON:Bjoern Peters" "en"])
(obo/IAO_0000589 obo/IAO_0000117 ["PERSON:Chris Mungall" "en"])
(obo/IAO_0000589 obo/IAO_0000117 ["PERSON:Melanie Courtot" "en"])
(obo/IAO_0000589 obo/IAO_0000119 ["GROUP:OBO Foundry <http://obofoundry.org/" "en"])
(obo/IAO_0000589 rdfs/label ["OBO foundry unique label" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000596
(obo/IAO_0000596 rdf/type owl/AnnotationProperty)
(obo/IAO_0000596 obo/IAO_0000112 ["Ontology: obo/ro/idrange/\n  Annotations: \n     'has ID prefix': \"http://purl.obolibrary.org/obo/RO_\"\n     'has ID digit count' : 7,\n     rdfs:label \"RO id policy\"\n     'has ID policy for': \"RO\""])
(obo/IAO_0000596 obo/IAO_0000115 ["Relates an ontology used to record id policy to the number of digits in the URI. The URI is: the 'has ID prefix\" annotation property value concatenated with an integer in the id range (left padded with \"0\"s to make this many digits)"])
(obo/IAO_0000596 obo/IAO_0000117 ["Person:Alan Ruttenberg"])
(obo/IAO_0000596 rdfs/label ["has ID digit count" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000597
(obo/IAO_0000597 rdf/type owl/AnnotationProperty)
(obo/IAO_0000597 obo/IAO_0000112 ["Datatype: idrange:1\nAnnotations: 'has ID range allocated to': \"Chris Mungall\"\nEquivalentTo: xsd:integer[ 2151 , <= 2300]\n"])
(obo/IAO_0000597 obo/IAO_0000115 ["Relates a datatype that encodes a range of integers to the name of the person or organization who can use those ids constructed in that range to define new terms"])
(obo/IAO_0000597 obo/IAO_0000117 ["Person:Alan Ruttenberg"])
(obo/IAO_0000597 rdfs/label ["has ID range allocated to" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000598
(obo/IAO_0000598 rdf/type owl/AnnotationProperty)
(obo/IAO_0000598 obo/IAO_0000112 ["Ontology: obo/ro/idrange/\n  Annotations: \n     'has ID prefix': \"http://purl.obolibrary.org/obo/RO_\"\n     'has ID digit count' : 7,\n     rdfs:label \"RO id policy\"\n     'has ID policy for': \"RO\""])
(obo/IAO_0000598 obo/IAO_0000115 ["Relating an ontology used to record id policy to the ontology namespace whose policy it manages"])
(obo/IAO_0000598 obo/IAO_0000117 ["Person:Alan Ruttenberg"])
(obo/IAO_0000598 rdfs/label ["has ID policy for"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000599
(obo/IAO_0000599 rdf/type owl/AnnotationProperty)
(obo/IAO_0000599 obo/IAO_0000112 ["Ontology: obo/ro/idrange/\n  Annotations: \n     'has ID prefix': \"http://purl.obolibrary.org/obo/RO_\"\n     'has ID digit count' : 7,\n     rdfs:label \"RO id policy\"\n     'has ID policy for': \"RO\""])
(obo/IAO_0000599 obo/IAO_0000115 ["Relates an ontology used to record id policy to a prefix concatenated with an integer in the id range (left padded with \"0\"s to make this many digits) to construct an ID for a term being created."])
(obo/IAO_0000599 obo/IAO_0000117 ["Person:Alan Ruttenberg"])
(obo/IAO_0000599 rdfs/label ["has ID prefix" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000600
(obo/IAO_0000600 rdf/type owl/AnnotationProperty)
(obo/IAO_0000600 obo/IAO_0000111 ["elucidation" "en"])
(obo/IAO_0000600 obo/IAO_0000117 ["person:Alan Ruttenberg" "en"])
(obo/IAO_0000600 obo/IAO_0000119 ["Person:Barry Smith" "en"])
(obo/IAO_0000600 obo/IAO_0000600 ["Primitive terms in a highest-level ontology such as BFO are terms which are so basic to our understanding of reality that there is no way of defining them in a non-circular fashion. For these, therefore, we can provide only elucidations, supplemented by examples and by axioms" "en"])
(obo/IAO_0000600 rdfs/isDefinedBy obo/iao.owl)
(obo/IAO_0000600 rdfs/label ["elucidation" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000601
(obo/IAO_0000601 rdf/type owl/AnnotationProperty)
(obo/IAO_0000601 obo/IAO_0000111 ["has associated axiom(nl)" "en"])
(obo/IAO_0000601 obo/IAO_0000117 ["Person:Alan Ruttenberg" "en"])
(obo/IAO_0000601 obo/IAO_0000119 ["Person:Alan Ruttenberg" "en"])
(obo/IAO_0000601 obo/IAO_0000600 ["An axiom associated with a term expressed using natural language" "en"])
(obo/IAO_0000601 rdfs/isDefinedBy obo/iao.owl)
(obo/IAO_0000601 rdfs/label ["has associated axiom(nl)" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000602
(obo/IAO_0000602 rdf/type owl/AnnotationProperty)
(obo/IAO_0000602 obo/IAO_0000111 ["has associated axiom(fol)" "en"])
(obo/IAO_0000602 obo/IAO_0000117 ["Person:Alan Ruttenberg" "en"])
(obo/IAO_0000602 obo/IAO_0000119 ["Person:Alan Ruttenberg" "en"])
(obo/IAO_0000602 obo/IAO_0000600 ["An axiom expressed in first order logic using CLIF syntax" "en"])
(obo/IAO_0000602 rdfs/isDefinedBy obo/iao.owl)
(obo/IAO_0000602 rdfs/label ["has associated axiom(fol)" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000603
(obo/IAO_0000603 rdf/type owl/AnnotationProperty)
(obo/IAO_0000603 obo/IAO_0000111 ["is allocated id range" "en"])
(obo/IAO_0000603 obo/IAO_0000115 ["Add as annotation triples in the granting ontology" "en"])
(obo/IAO_0000603 obo/IAO_0000115 ["Relates an ontology IRI to an (inclusive) range of IRIs in an OBO name space. The range is give as, e.g. \"IAO_0020000-IAO_0020999\"" "en"])
(obo/IAO_0000603 obo/IAO_0000117 ["PERSON:Alan Ruttenberg" "en"])
(obo/IAO_0000603 rdfs/label ["is allocated id range" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000604
(obo/IAO_0000604 rdf/type owl/AnnotationProperty)
(obo/IAO_0000604 obo/IAO_0000111 ["retired from use as of" "en"])
(obo/IAO_0000604 obo/IAO_0000115 ["relates a class of CRID to the date after which further instances should not be made, according to the central authority"])
(obo/IAO_0000604 obo/IAO_0000116 ["In OWL 2 add AnnotationPropertyRange xsd:dateTimeStamp"])
(obo/IAO_0000604 obo/IAO_0000117 ["Alan Ruttenberg"])
(obo/IAO_0000604 rdfs/label ["retired from use as of"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0010000
(obo/IAO_0010000 rdf/type owl/AnnotationProperty)
(obo/IAO_0010000 obo/IAO_0000111 ["has axiom id" "en"])
(obo/IAO_0010000 obo/IAO_0000117 ["Person:Alan Ruttenberg" "en"])
(obo/IAO_0010000 obo/IAO_0000119 ["Person:Alan Ruttenberg" "en"])
(obo/IAO_0010000 obo/IAO_0000600 ["A URI that is intended to be unique label for an axiom used for tracking change to the ontology. For an axiom expressed in different languages, each expression is given the same URI" "en"])
(obo/IAO_0010000 rdfs/isDefinedBy obo/iao.owl)
(obo/IAO_0010000 rdfs/label ["has axiom label" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0100001
(obo/IAO_0100001 rdf/type owl/AnnotationProperty)
(obo/IAO_0100001 obo/IAO_0000111 ["term replaced by" "en"])
(obo/IAO_0100001 obo/IAO_0000114 obo/IAO_0000125)
(obo/IAO_0100001 obo/IAO_0000115 ["Add as annotation triples in the granting ontology" "en"])
(obo/IAO_0100001 obo/IAO_0000115 ["Use on obsolete terms, relating the term to another term that can be used as a substitute" "en"])
(obo/IAO_0100001 obo/IAO_0000117 ["Person:Alan Ruttenberg" "en"])
(obo/IAO_0100001 obo/IAO_0000119 ["Person:Alan Ruttenberg" "en"])
(obo/IAO_0100001 rdfs/label ["term replaced by" "en"])
;; 
;; http://purl.obolibrary.org/obo/RO_0001900
(obo/RO_0001900 rdf/type owl/AnnotationProperty)
(obo/RO_0001900 rdfs/label ["temporal interpretation" "en"])
;; 
;; http://purl.org/dc/elements/1.1/contributor
(dc/contributor rdf/type owl/AnnotationProperty)
;; 
;; http://purl.org/dc/elements/1.1/coverage
(dc/coverage rdf/type owl/AnnotationProperty)
;; 
;; http://purl.org/dc/elements/1.1/creator
(dc/creator rdf/type owl/AnnotationProperty)
;; 
;; http://purl.org/dc/elements/1.1/date
(dc/date rdf/type owl/AnnotationProperty)
;; 
;; http://purl.org/dc/elements/1.1/description
(dc/description rdf/type owl/AnnotationProperty)
;; 
;; http://purl.org/dc/elements/1.1/format
(dc/format rdf/type owl/AnnotationProperty)
;; 
;; http://purl.org/dc/elements/1.1/identifier
(dc/identifier rdf/type owl/AnnotationProperty)
;; 
;; http://purl.org/dc/elements/1.1/language
(dc/language rdf/type owl/AnnotationProperty)
;; 
;; http://purl.org/dc/elements/1.1/member
(dc/member rdf/type owl/AnnotationProperty)
;; 
;; http://purl.org/dc/elements/1.1/publisher
(dc/publisher rdf/type owl/AnnotationProperty)
;; 
;; http://purl.org/dc/elements/1.1/relation
(dc/relation rdf/type owl/AnnotationProperty)
;; 
;; http://purl.org/dc/elements/1.1/rights
(dc/rights rdf/type owl/AnnotationProperty)
;; 
;; http://purl.org/dc/elements/1.1/source
(dc/source rdf/type owl/AnnotationProperty)
;; 
;; http://purl.org/dc/elements/1.1/subject
(dc/subject rdf/type owl/AnnotationProperty)
;; 
;; http://purl.org/dc/elements/1.1/title
(dc/title rdf/type owl/AnnotationProperty)
;; 
;; http://purl.org/dc/elements/1.1/type
(dc/type rdf/type owl/AnnotationProperty)
;; 
;; http://www.geneontology.org/formats/oboInOwl;;shorthand
(oboInOwl/shorthand rdf/type owl/AnnotationProperty)
(oboInOwl/shorthand rdfs/label ["shorthand"])
;; 
;; http://www.w3.org/2000/01/rdf-schema;;comment
(rdfs/comment rdf/type owl/AnnotationProperty)
;; 
;; http://www.w3.org/2000/01/rdf-schema;;isDefinedBy
(rdfs/isDefinedBy rdf/type owl/AnnotationProperty)
;; 
;; http://www.w3.org/2000/01/rdf-schema;;label
(rdfs/label rdf/type owl/AnnotationProperty)
;; 
;; http://www.w3.org/2000/01/rdf-schema;;seeAlso
(rdfs/seeAlso rdf/type owl/AnnotationProperty)
;; 
;; http://www.w3.org/2002/07/owl;;maxQualifiedCardinality
(owl/maxQualifiedCardinality rdf/type owl/AnnotationProperty)
;; 
;; http://www.w3.org/2002/07/owl;;minQualifiedCardinality
(owl/minQualifiedCardinality rdf/type owl/AnnotationProperty)
;; 
;; http://www.w3.org/2002/07/owl;;qualifiedCardinality
(owl/qualifiedCardinality rdf/type owl/AnnotationProperty)
;; 
;; http://xmlns.com/foaf/0.1/homepage
(foaf/homepage rdf/type owl/AnnotationProperty)
;; 
;; http://xmlns.com/foaf/0.1/page
(foaf/page rdf/type owl/AnnotationProperty)
;; 
;; 
;; 
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; ;;
;; ;;    Object Properties
;; ;;
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000328
(ccp/IAO_EXT_0000328 rdf/type owl/ObjectProperty)
(ccp/IAO_EXT_0000328 rdfs/label ["query hit count" "en"])
;; 
;; http://purl.obolibrary.org/obo/BFO_0000050
(obo/BFO_0000050 rdf/type owl/ObjectProperty)
(obo/BFO_0000050 owl/equivalentProperty bnode/ccp-extensions_genid1)
(bnode/ccp-extensions_genid1 owl/inverseOf obo/BFO_0000051)
(obo/BFO_0000051 owl/equivalentProperty bnode/ccp-extensions_genid386)
(bnode/ccp-extensions_genid386 owl/inverseOf obo/BFO_0000050)
(obo/BFO_0000050 rdfs/subPropertyOf owl/topObjectProperty)
(obo/BFO_0000050 owl/inverseOf obo/BFO_0000051)
(obo/BFO_0000050 owl/inverseOf bnode/ccp-extensions_genid385)
(bnode/ccp-extensions_genid385 owl/inverseOf obo/BFO_0000050)
(obo/BFO_0000050 rdf/type owl/TransitiveProperty)
(obo/BFO_0000050 obo/IAO_0000111 ["is part of" "en"])
(obo/BFO_0000050 obo/IAO_0000112 ["my brain is part of my body (continuant parthood, two material entities)" "en"])
(obo/BFO_0000050 obo/IAO_0000112 ["my stomach cavity is part of my stomach (continuant parthood, immaterial entity is part of material entity)" "en"])
(obo/BFO_0000050 obo/IAO_0000112 ["this day is part of this year (occurrent parthood)" "en"])
(obo/BFO_0000050 obo/IAO_0000115 ["a core relation that holds between a part and its whole" "en"])
(obo/BFO_0000050 obo/IAO_0000116 ["Everything is part of itself. Any part of any part of a thing is itself part of that thing. Two distinct things cannot be part of each other." "en"])
(obo/BFO_0000050 obo/IAO_0000116 ["Occurrents are not subject to change and so parthood between occurrents holds for all the times that the part exists. Many continuants are subject to change, so parthood between continuants will only hold at certain times, but this is difficult to specify in OWL. See https://code.google.com/p/obo-relations/wiki/ROAndTime" "en"])
(obo/BFO_0000050 obo/IAO_0000116 ["Parthood requires the part and the whole to have compatible classes: only an occurrent can be part of an occurrent; only a process can be part of a process; only a continuant can be part of a continuant; only an independent continuant can be part of an independent continuant; only an immaterial entity can be part of an immaterial entity; only a specifically dependent continuant can be part of a specifically dependent continuant; only a generically dependent continuant can be part of a generically dependent continuant. (This list is not exhaustive.)\n\nA continuant cannot be part of an occurrent: use 'participates in'. An occurrent cannot be part of a continuant: use 'has participant'. A material entity cannot be part of an immaterial entity: use 'has location'. A specifically dependent continuant cannot be part of an independent continuant: use 'inheres in'. An independent continuant cannot be part of a specifically dependent continuant: use 'bearer of'." "en"])
(obo/BFO_0000050 obo/IAO_0000118 ["part_of" "en"])
(obo/BFO_0000050 obo/RO_0001900 obo/RO_0001901)
(obo/BFO_0000050 rdfs/label ["part of" "en"])
(obo/BFO_0000050 rdfs/seeAlso ["http://www.obofoundry.org/ro/;;OBO_REL:part_of"])
;; 
;; http://purl.obolibrary.org/obo/BFO_0000051
(obo/BFO_0000051 rdf/type owl/ObjectProperty)
(obo/BFO_0000051 owl/equivalentProperty bnode/ccp-extensions_genid2)
(bnode/ccp-extensions_genid2 owl/inverseOf obo/BFO_0000050)
(obo/BFO_0000050 owl/equivalentProperty bnode/ccp-extensions_genid387)
(bnode/ccp-extensions_genid387 owl/inverseOf obo/BFO_0000051)
(obo/BFO_0000051 rdfs/subPropertyOf owl/topObjectProperty)
(obo/BFO_0000051 owl/inverseOf bnode/ccp-extensions_genid388)
(bnode/ccp-extensions_genid388 owl/inverseOf obo/BFO_0000051)
(obo/BFO_0000051 rdf/type owl/TransitiveProperty)
(obo/BFO_0000051 obo/IAO_0000111 ["has part" "en"])
(obo/BFO_0000051 obo/IAO_0000112 ["my body has part my brain (continuant parthood, two material entities)" "en"])
(obo/BFO_0000051 obo/IAO_0000112 ["my stomach has part my stomach cavity (continuant parthood, material entity has part immaterial entity)" "en"])
(obo/BFO_0000051 obo/IAO_0000112 ["this year has part this day (occurrent parthood)" "en"])
(obo/BFO_0000051 obo/IAO_0000115 ["a core relation that holds between a whole and its part" "en"])
(obo/BFO_0000051 obo/IAO_0000116 ["Everything has itself as a part. Any part of any part of a thing is itself part of that thing. Two distinct things cannot have each other as a part." "en"])
(obo/BFO_0000051 obo/IAO_0000116 ["Occurrents are not subject to change and so parthood between occurrents holds for all the times that the part exists. Many continuants are subject to change, so parthood between continuants will only hold at certain times, but this is difficult to specify in OWL. See https://code.google.com/p/obo-relations/wiki/ROAndTime" "en"])
(obo/BFO_0000051 obo/IAO_0000116 ["Parthood requires the part and the whole to have compatible classes: only an occurrent have an occurrent as part; only a process can have a process as part; only a continuant can have a continuant as part; only an independent continuant can have an independent continuant as part; only a specifically dependent continuant can have a specifically dependent continuant as part; only a generically dependent continuant can have a generically dependent continuant as part. (This list is not exhaustive.)\n\nA continuant cannot have an occurrent as part: use 'participates in'. An occurrent cannot have a continuant as part: use 'has participant'. An immaterial entity cannot have a material entity as part: use 'location of'. An independent continuant cannot have a specifically dependent continuant as part: use 'bearer of'. A specifically dependent continuant cannot have an independent continuant as part: use 'inheres in'." "en"])
(obo/BFO_0000051 obo/IAO_0000118 ["has_part" "en"])
(obo/BFO_0000051 obo/RO_0001900 obo/RO_0001901)
(obo/BFO_0000051 rdfs/label ["has part" "en"])
;; 
;; http://purl.obolibrary.org/obo/BFO_0000054
(obo/BFO_0000054 rdf/type owl/ObjectProperty)
(obo/BFO_0000054 owl/equivalentProperty bnode/ccp-extensions_genid3)
(bnode/ccp-extensions_genid3 owl/inverseOf obo/BFO_0000055)
(obo/BFO_0000055 owl/equivalentProperty bnode/ccp-extensions_genid389)
(bnode/ccp-extensions_genid389 owl/inverseOf obo/BFO_0000054)
(obo/BFO_0000054 rdfs/subPropertyOf owl/topObjectProperty)
(obo/BFO_0000054 owl/inverseOf obo/BFO_0000055)
(obo/BFO_0000054 owl/inverseOf bnode/ccp-extensions_genid390)
(bnode/ccp-extensions_genid390 owl/inverseOf obo/BFO_0000054)
(obo/BFO_0000054 rdf/type owl/AsymmetricProperty)
(obo/BFO_0000054 rdf/type owl/IrreflexiveProperty)
(obo/BFO_0000054 rdfs/domain obo/BFO_0000017)
(obo/BFO_0000054 rdfs/range obo/BFO_0000015)
(obo/BFO_0000054 obo/IAO_0000111 ["realized in" "en"])
(obo/BFO_0000054 obo/IAO_0000112 ["this disease is realized in this disease course" "en"])
(obo/BFO_0000054 obo/IAO_0000112 ["this fragility is realized in this shattering" "en"])
(obo/BFO_0000054 obo/IAO_0000112 ["this investigator role is realized in this investigation" "en"])
(obo/BFO_0000054 obo/IAO_0000118 ["is realized by" "en"])
(obo/BFO_0000054 obo/IAO_0000118 ["realized_in" "en"])
(obo/BFO_0000054 obo/IAO_0000600 ["[copied from inverse property 'realizes'] to say that b realizes c at t is to assert that there is some material entity d & b is a process which has participant d at t & c is a disposition or role of which d is bearer_of at t& the type instantiated by b is correlated with the type instantiated by c. (axiom label in BFO2 Reference: [059-003])" "en"])
(obo/BFO_0000054 rdfs/comment ["Paraphrase of elucidation: a relation between a realizable entity and a process, where there is some material entity that is bearer of the realizable entity and participates in the process, and the realizable entity comes to be realized in the course of the process"])
(obo/BFO_0000054 rdfs/isDefinedBy obo/bfo.owl)
(obo/BFO_0000054 rdfs/label ["realized in" "en"])
;; 
;; http://purl.obolibrary.org/obo/BFO_0000055
(obo/BFO_0000055 rdf/type owl/ObjectProperty)
(obo/BFO_0000055 owl/equivalentProperty bnode/ccp-extensions_genid4)
(bnode/ccp-extensions_genid4 owl/inverseOf obo/BFO_0000054)
(obo/BFO_0000054 owl/equivalentProperty bnode/ccp-extensions_genid392)
(bnode/ccp-extensions_genid392 owl/inverseOf obo/BFO_0000055)
(obo/BFO_0000055 rdfs/subPropertyOf owl/topObjectProperty)
(obo/BFO_0000055 owl/inverseOf bnode/ccp-extensions_genid391)
(bnode/ccp-extensions_genid391 owl/inverseOf obo/BFO_0000055)
(obo/BFO_0000055 rdf/type owl/AsymmetricProperty)
(obo/BFO_0000055 rdf/type owl/IrreflexiveProperty)
(obo/BFO_0000055 rdfs/domain obo/BFO_0000015)
(obo/BFO_0000055 rdfs/range obo/BFO_0000017)
(obo/BFO_0000055 obo/IAO_0000111 ["realizes" "en"])
(obo/BFO_0000055 obo/IAO_0000112 ["this disease course realizes this disease" "en"])
(obo/BFO_0000055 obo/IAO_0000112 ["this investigation realizes this investigator role" "en"])
(obo/BFO_0000055 obo/IAO_0000112 ["this shattering realizes this fragility" "en"])
(obo/BFO_0000055 obo/IAO_0000600 ["to say that b realizes c at t is to assert that there is some material entity d & b is a process which has participant d at t & c is a disposition or role of which d is bearer_of at t& the type instantiated by b is correlated with the type instantiated by c. (axiom label in BFO2 Reference: [059-003])" "en"])
(obo/BFO_0000055 rdfs/comment ["Paraphrase of elucidation: a relation between a process and a realizable entity, where there is some material entity that is bearer of the realizable entity and participates in the process, and the realizable entity comes to be realized in the course of the process"])
(obo/BFO_0000055 rdfs/isDefinedBy obo/iao.owl)
(obo/BFO_0000055 rdfs/label ["realizes" "en"])
;; 
;; http://purl.obolibrary.org/obo/BFO_0000066
(obo/BFO_0000066 rdf/type owl/ObjectProperty)
(obo/BFO_0000066 owl/equivalentProperty bnode/ccp-extensions_genid5)
(bnode/ccp-extensions_genid5 owl/inverseOf obo/BFO_0000067)
(obo/BFO_0000067 owl/equivalentProperty bnode/ccp-extensions_genid394)
(bnode/ccp-extensions_genid394 owl/inverseOf obo/BFO_0000066)
(obo/BFO_0000066 rdfs/subPropertyOf owl/topObjectProperty)
(obo/BFO_0000066 owl/inverseOf obo/BFO_0000067)
(obo/BFO_0000066 owl/inverseOf bnode/ccp-extensions_genid393)
(bnode/ccp-extensions_genid393 owl/inverseOf obo/BFO_0000066)
(obo/BFO_0000066 rdf/type owl/AsymmetricProperty)
(obo/BFO_0000066 rdf/type owl/IrreflexiveProperty)
(obo/BFO_0000066 rdfs/domain obo/BFO_0000003)
(obo/BFO_0000066 rdfs/range obo/BFO_0000004)
(obo/BFO_0000066 owl/propertyChainAxiom bnode/ccp-extensions_genid8)
(bnode/ccp-extensions_genid8 rdf/type rdf/List)
(bnode/ccp-extensions_genid8 rdf/first obo/BFO_0000050)
(bnode/ccp-extensions_genid8 rdf/rest bnode/ccp-extensions_genid7)
(bnode/ccp-extensions_genid7 rdf/type rdf/List)
(bnode/ccp-extensions_genid7 rdf/first obo/BFO_0000066)
(bnode/ccp-extensions_genid7 rdf/rest rdf/nil)
(obo/BFO_0000066 owl/propertyChainAxiom bnode/ccp-extensions_genid10)
(bnode/ccp-extensions_genid10 rdf/type rdf/List)
(bnode/ccp-extensions_genid10 rdf/first obo/BFO_0000066)
(bnode/ccp-extensions_genid10 rdf/rest bnode/ccp-extensions_genid9)
(bnode/ccp-extensions_genid9 rdf/type rdf/List)
(bnode/ccp-extensions_genid9 rdf/first obo/BFO_0000050)
(bnode/ccp-extensions_genid9 rdf/rest rdf/nil)
(obo/BFO_0000066 obo/IAO_0000111 ["occurs in" "en"])
(obo/BFO_0000066 obo/IAO_0000115 ["b occurs_in c =def b is a process and c is a material entity or immaterial entity& there exists a spatiotemporal region r and b occupies_spatiotemporal_region r.& forall(t) if b exists_at t then c exists_at t & there exist spatial regions s and s\u2019 where & b spatially_projects_onto s at t& c is occupies_spatial_region s\u2019 at t& s is a proper_continuant_part_of s\u2019 at t" "en"])
(obo/BFO_0000066 obo/IAO_0000118 ["occurs_in" "en"])
(obo/BFO_0000066 obo/IAO_0000118 ["unfolds in" "en"])
(obo/BFO_0000066 obo/IAO_0000118 ["unfolds_in" "en"])
(obo/BFO_0000066 rdfs/comment ["Paraphrase of definition: a relation between a process and an independent continuant, in which the process takes place entirely within the independent continuant"])
(obo/BFO_0000066 rdfs/isDefinedBy obo/bfo.owl)
(obo/BFO_0000066 rdfs/label ["occurs in" "en"])
;; 
;; http://purl.obolibrary.org/obo/BFO_0000067
(obo/BFO_0000067 rdf/type owl/ObjectProperty)
(obo/BFO_0000067 owl/equivalentProperty bnode/ccp-extensions_genid6)
(bnode/ccp-extensions_genid6 owl/inverseOf obo/BFO_0000066)
(obo/BFO_0000066 owl/equivalentProperty bnode/ccp-extensions_genid396)
(bnode/ccp-extensions_genid396 owl/inverseOf obo/BFO_0000067)
(obo/BFO_0000067 rdfs/subPropertyOf owl/topObjectProperty)
(obo/BFO_0000067 owl/inverseOf bnode/ccp-extensions_genid395)
(bnode/ccp-extensions_genid395 owl/inverseOf obo/BFO_0000067)
(obo/BFO_0000067 rdf/type owl/AsymmetricProperty)
(obo/BFO_0000067 rdf/type owl/IrreflexiveProperty)
(obo/BFO_0000067 obo/IAO_0000111 ["site of" "en"])
(obo/BFO_0000067 obo/IAO_0000115 ["[copied from inverse property 'occurs in'] b occurs_in c =def b is a process and c is a material entity or immaterial entity& there exists a spatiotemporal region r and b occupies_spatiotemporal_region r.& forall(t) if b exists_at t then c exists_at t & there exist spatial regions s and s\u2019 where & b spatially_projects_onto s at t& c is occupies_spatial_region s\u2019 at t& s is a proper_continuant_part_of s\u2019 at t" "en"])
(obo/BFO_0000067 rdfs/comment ["Paraphrase of definition: a relation between an independent continuant and a process, in which the process takes place entirely within the independent continuant"])
(obo/BFO_0000067 rdfs/isDefinedBy obo/bfo.owl)
(obo/BFO_0000067 rdfs/label ["contains process" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000039
(obo/IAO_0000039 rdf/type owl/ObjectProperty)
(obo/IAO_0000039 rdfs/subPropertyOf obo/BFO_0000051)
(obo/IAO_0000039 rdfs/subPropertyOf bnode/ccp-extensions_genid11)
(bnode/ccp-extensions_genid11 owl/inverseOf obo/BFO_0000050)
(obo/IAO_0000039 owl/inverseOf bnode/ccp-extensions_genid12)
(bnode/ccp-extensions_genid12 owl/inverseOf obo/IAO_0000039)
(obo/IAO_0000039 rdf/type owl/FunctionalProperty)
(obo/IAO_0000039 rdfs/range obo/IAO_0000003)
(obo/IAO_0000039 rdfs/label ["has measurement unit label" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000135
(obo/IAO_0000135 rdf/type owl/ObjectProperty)
(obo/IAO_0000135 rdfs/subPropertyOf oboInOwl/ObsoleteProperty)
(obo/IAO_0000135 owl/inverseOf bnode/ccp-extensions_genid13)
(bnode/ccp-extensions_genid13 owl/inverseOf obo/IAO_0000135)
(obo/IAO_0000135 obo/IAO_0000112 ["The process of creation is, for example,  writing down on paper the name of a friend by deliberately creating a certain pattern using ink.\n\nHere the ink + paper is the independent continuant and the carrier is the pattern in the ink.\n\nc = pattern in the ink\nb = paper + ink\nr = friend"])
(obo/IAO_0000135 obo/IAO_0000114 obo/IAO_0000125)
(obo/IAO_0000135 obo/IAO_0000115 ["c specifically denotes r =def \nr is a portion of reality\n& c is a particular quality\n& c depends specifically on some independent continuant b \n& b acquired c as the result of the achievement of an objective to enable pointing to r repeatedly. \n\nMarked means there is a changed or additional quality of the bearer - the  quality is the information carrier.\n\nCase 1\nMemory trace as mark created when reading some description of some friend. The trace can denote.\n\nCase 2\nPattern of ink arrayed on paper as mark when writing down a friend's name\n\nCase 3\nPattern of magnetic domains on scattered pieces of a hard disk platter as mark when saving a file."])
(obo/IAO_0000135 obo/IAO_0000116 ["8/6/2009 Alan Ruttenberg: The suggestions is to deprecate specific and generically denotes in favor of a single denote relationship that corresponds to the generic sense"])
 ;(obo/IAO_0000135 obo/IAO_0000116 ["see https://github.com/information-artifact-ontology/IAO/issues/25&q=denote"])
(obo/IAO_0000135 obo/IAO_0000117 ["Alan Ruttenberg"])
(obo/IAO_0000135 obo/IAO_0000119 ["Smith, Ceusters, Ruttenberg, 2000 years of philosophy"])
(obo/IAO_0000135 obo/IAO_0000231 obo/IAO_0000103)
(obo/IAO_0000135 rdfs/label ["obsolete_specifically denotes"])
(obo/IAO_0000135 owl/deprecated true)
;; 
;; http://purl.obolibrary.org/obo/IAO_0000136
(obo/IAO_0000136 rdf/type owl/ObjectProperty)
(obo/IAO_0000136 rdfs/subPropertyOf owl/topObjectProperty)
(obo/IAO_0000136 owl/inverseOf bnode/ccp-extensions_genid14)
(bnode/ccp-extensions_genid14 owl/inverseOf obo/IAO_0000136)
(obo/IAO_0000136 rdfs/domain obo/IAO_0000030)
(obo/IAO_0000136 obo/IAO_0000112 ["This document is about information artifacts and their representations" "en"])
(obo/IAO_0000136 obo/IAO_0000114 obo/IAO_0000125)
(obo/IAO_0000136 obo/IAO_0000115 ["is_about is a (currently) primitive relation that relates an information artifact to an entity." "en"])
(obo/IAO_0000136 obo/IAO_0000116 ["7/6/2009 Alan Ruttenberg. Following discussion with Jonathan Rees, and introduction of \"mentions\" relation. Weaken the is_about relationship to be primitive. \n\nWe will try to build it back up by elaborating the various subproperties that are more precisely defined.\n\nSome currently missing phenomena that should be considered \"about\" are predications - \"The only person who knows the answer is sitting beside me\" , Allegory, Satire, and other literary forms that can be topical without explicitly mentioning the topic." "en"])
(obo/IAO_0000136 obo/IAO_0000117 ["person:Alan Ruttenberg" "en"])
(obo/IAO_0000136 obo/IAO_0000119 ["Smith, Ceusters, Ruttenberg, 2000 years of philosophy" "en"])
(obo/IAO_0000136 rdfs/label ["is about" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000142
(obo/IAO_0000142 rdf/type owl/ObjectProperty)
(obo/IAO_0000142 rdfs/subPropertyOf obo/IAO_0000136)
(obo/IAO_0000142 owl/inverseOf bnode/ccp-extensions_genid15)
(bnode/ccp-extensions_genid15 owl/inverseOf obo/IAO_0000142)
(obo/IAO_0000142 obo/IAO_0000114 obo/IAO_0000125)
(obo/IAO_0000142 obo/IAO_0000115 ["An information artifact IA mentions an entity E exactly when it has a component/part that denotes E" "en"])
(obo/IAO_0000142 obo/IAO_0000116 ["7/6/2009 Alan Ruttenberg. P4 RC1 munges our GCI so remove it for now: mentions some entity equivalentTo has_part some ('generically denotes' some entity)" "en"])
(obo/IAO_0000142 obo/IAO_0000116 ["7/6/2009 Alan Ruttenberg: Add this relation following conversation with Jonathan Rees that N&S GCI for is_about was too strong. Really it was simply sufficient. To effect this change we introduce this relation, which is subproperty of is_about, and have previous GCI use this relation \"mentions\" in it's (logical) definition" "en"])
(obo/IAO_0000142 obo/IAO_0000117 ["PERSON: Jonathan Rees" "en"])
(obo/IAO_0000142 obo/IAO_0000117 ["Person: Alan Ruttenberg" "en"])
(obo/IAO_0000142 rdfs/label ["mentions" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000219
(obo/IAO_0000219 rdf/type owl/ObjectProperty)
(obo/IAO_0000219 rdfs/subPropertyOf obo/IAO_0000136)
(obo/IAO_0000219 owl/inverseOf bnode/ccp-extensions_genid16)
(bnode/ccp-extensions_genid16 owl/inverseOf obo/IAO_0000219)
(obo/IAO_0000219 obo/IAO_0000112 ["A person's name denotes the person. A variable name in a computer program denotes some piece of memory. Lexically equivalent strings can denote different things, for instance \"Alan\" can denote different people. In each case of use, there is a case of the denotation relation obtaining, between \"Alan\" and the person that is being named." "en"])
(obo/IAO_0000219 obo/IAO_0000115 ["denotes is a primitive, instance-level, relation obtaining between an information content entity and some portion of reality. Denotation is what happens when someone creates an information content entity E in order to specifically refer to something. The only relation between E and the thing is that E can be used to 'pick out' the thing. This relation connects those two together. Freedictionary.com sense 3: To signify directly; refer to specifically" "en"])
(obo/IAO_0000219 obo/IAO_0000116 ["2009-11-10 Alan Ruttenberg. Old definition said the following to emphasize the generic nature of this relation. We no longer have 'specifically denotes', which would have been primitive, so make this relation primitive.\ng denotes r =def \nr is a portion of reality\nthere is some c that is a concretization of g \nevery c that is a concretization of g specifically denotes r" "en"])
(obo/IAO_0000219 obo/IAO_0000117 ["person:Alan Ruttenberg" "en"])
(obo/IAO_0000219 obo/IAO_0000119 ["Conversations with Barry Smith, Werner Ceusters, Bjoern Peters, Michel Dumontier, Melanie Courtot, James Malone, Bill Hogan" "en"])
(obo/IAO_0000219 rdfs/comment ["" "en"])
(obo/IAO_0000219 rdfs/label ["denotes" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000220
(obo/IAO_0000220 rdf/type owl/ObjectProperty)
(obo/IAO_0000220 rdfs/subPropertyOf oboInOwl/ObsoleteProperty)
(obo/IAO_0000220 owl/inverseOf bnode/ccp-extensions_genid17)
(bnode/ccp-extensions_genid17 owl/inverseOf obo/IAO_0000220)
 ;(obo/IAO_0000220 obo/IAO_0000116 ["see https://github.com/information-artifact-ontology/IAO/issues/25&q=denote"])
(obo/IAO_0000220 obo/IAO_0000231 obo/IAO_0000103)
(obo/IAO_0000220 rdfs/label ["obsolete_materially denotes"])
(obo/IAO_0000220 owl/deprecated true)
;; 
;; http://purl.obolibrary.org/obo/IAO_0000221
(obo/IAO_0000221 rdf/type owl/ObjectProperty)
(obo/IAO_0000221 owl/equivalentProperty bnode/ccp-extensions_genid18)
(bnode/ccp-extensions_genid18 owl/inverseOf obo/IAO_0000417)
(obo/IAO_0000417 owl/equivalentProperty bnode/ccp-extensions_genid398)
(bnode/ccp-extensions_genid398 owl/inverseOf obo/IAO_0000221)
(obo/IAO_0000221 rdfs/subPropertyOf obo/IAO_0000136)
(obo/IAO_0000221 owl/inverseOf obo/IAO_0000417)
(obo/IAO_0000221 owl/inverseOf bnode/ccp-extensions_genid397)
(bnode/ccp-extensions_genid397 owl/inverseOf obo/IAO_0000221)
(obo/IAO_0000221 rdf/type owl/AsymmetricProperty)
(obo/IAO_0000221 rdf/type owl/IrreflexiveProperty)
(obo/IAO_0000221 rdfs/domain obo/IAO_0000109)
(obo/IAO_0000221 rdfs/range obo/BFO_0000019)
(obo/IAO_0000221 obo/IAO_0000115 ["m is a quality measurement of q at t when\nq is a quality \nthere is a measurement process p that has specified output m, a measurement datum, that is about q" "en"])
(obo/IAO_0000221 obo/IAO_0000116 ["8/6/2009 Alan Ruttenberg: The strategy is to be rather specific with this relationship. There are other kinds of measurements that are not of qualities, such as those that measure time. We will add these as separate properties for the moment and see about generalizing later" "en"])
(obo/IAO_0000221 obo/IAO_0000116 ["From the second IAO workshop [Alan Ruttenberg 8/6/2009: not completely current, though bringing in comparison is probably important]\n\nThis one is the one we are struggling with at the moment. The issue is what a measurement measures. On the one hand saying that it measures the quality would include it \"measuring\" the bearer = referring to the bearer in the measurement. However this makes comparisons of two different things not possible. On the other hand not having it inhere in the bearer, on the face of it, breaks the audit trail.\n\nWerner suggests a solution based on \"Magnitudes\" a proposal for which we are awaiting details.\n--\nFrom the second IAO workshop, various comments, [commented on by Alan Ruttenberg 8/6/2009]\n\nunit of measure is a quality, e.g. the length of a ruler.\n\n[We decided to hedge on what units of measure are, instead talking about measurement unit labels, which are the information content entities that are about whatever measurement units are. For IAO we need that information entity in any case. See the term measurement unit label]\n\n[Some struggling with the various subflavors of is_about. We subsequently removed the relation represents, and describes until and only when we have a better theory]\n\na represents b means either a denotes b or a describes\n\ndescribe:\na describes b means a is about b and a allows an inference of at least one quality of b\n\nWe have had a long discussion about denotes versus describes." "en"])
(obo/IAO_0000221 obo/IAO_0000116 ["From the second IAO workshop: An attempt at tieing the quality to the measurement datum more carefully.\n\na is a magnitude means a is a determinate quality particular inhering in some bearer b existing at a time t that can be represented/denoted by an information content entity e that has parts denoting a unit of measure, a number, and b. The unit of measure is an instance of the determinable quality." "en"])
(obo/IAO_0000221 obo/IAO_0000116 ["From the second meeting on IAO:\n\nAn attempt at defining assay using Barry's \"reliability\" wording\n\nassay:\nprocess and has_input some material entity\nand has_output some information content entity \nand which is such that instances of this process type reliably generate \noutputs that describes the input." "en"])
(obo/IAO_0000221 obo/IAO_0000116 ["This one is the one we are struggling with at the moment. The issue is what a measurement measures. On the one hand saying that it measures the quality would include it \"measuring\" the bearer = referring to the bearer in the measurement. However this makes comparisons of two different things not possible. On the other hand not having it inhere in the bearer, on the face of it, breaks the audit trail.\n\nWerner suggests a solution based on \"Magnitudes\" a proposal for which we are awaiting details." "en"])
(obo/IAO_0000221 obo/IAO_0000117 ["Alan Ruttenberg" "en"])
(obo/IAO_0000221 rdfs/label ["is quality measurement of" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000222
(obo/IAO_0000222 rdf/type owl/ObjectProperty)
(obo/IAO_0000222 rdfs/subPropertyOf oboInOwl/ObsoleteProperty)
(obo/IAO_0000222 owl/inverseOf bnode/ccp-extensions_genid20)
(bnode/ccp-extensions_genid20 owl/inverseOf obo/IAO_0000222)
(obo/IAO_0000222 obo/IAO_0000231 obo/IAO_0000103)
(obo/IAO_0000222 rdfs/label ["obsolete_describes"])
(obo/IAO_0000222 owl/deprecated true)
;; 
;; http://purl.obolibrary.org/obo/IAO_0000223
(obo/IAO_0000223 rdf/type owl/ObjectProperty)
(obo/IAO_0000223 rdfs/subPropertyOf oboInOwl/ObsoleteProperty)
(obo/IAO_0000223 owl/inverseOf bnode/ccp-extensions_genid21)
(bnode/ccp-extensions_genid21 owl/inverseOf obo/IAO_0000223)
(obo/IAO_0000223 obo/IAO_0000231 obo/IAO_0000103)
(obo/IAO_0000223 rdfs/label ["obsolete_represents"])
(obo/IAO_0000223 owl/deprecated true)
;; 
;; http://purl.obolibrary.org/obo/IAO_0000407
(obo/IAO_0000407 rdf/type owl/ObjectProperty)
(obo/IAO_0000407 rdfs/subPropertyOf obo/BFO_0000051)
(obo/IAO_0000407 rdfs/subPropertyOf bnode/ccp-extensions_genid22)
(bnode/ccp-extensions_genid22 owl/inverseOf obo/BFO_0000050)
(obo/IAO_0000407 owl/inverseOf bnode/ccp-extensions_genid23)
(bnode/ccp-extensions_genid23 owl/inverseOf obo/IAO_0000407)
(obo/IAO_0000407 rdf/type owl/FunctionalProperty)
(obo/IAO_0000407 rdfs/domain obo/IAO_0000400)
(obo/IAO_0000407 rdfs/range obo/UO_0000001)
(obo/IAO_0000407 rdfs/comment ["relating a cartesian spatial coordinate datum to a unit label that together with the values represent a point" "en"])
(obo/IAO_0000407 rdfs/label ["has coordinate unit label" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000413
(obo/IAO_0000413 rdf/type owl/ObjectProperty)
(obo/IAO_0000413 rdfs/subPropertyOf obo/IAO_0000136)
(obo/IAO_0000413 owl/inverseOf bnode/ccp-extensions_genid24)
(bnode/ccp-extensions_genid24 owl/inverseOf obo/IAO_0000413)
(obo/IAO_0000413 rdf/type owl/AsymmetricProperty)
(obo/IAO_0000413 rdf/type owl/IrreflexiveProperty)
(obo/IAO_0000413 rdfs/domain obo/IAO_0000416)
(obo/IAO_0000413 rdfs/range obo/BFO_0000015)
(obo/IAO_0000413 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000413 obo/IAO_0000115 ["relates a process to a time-measurement-datum that represents the duration of the process" "en"])
(obo/IAO_0000413 obo/IAO_0000117 ["Person:Alan Ruttenberg" "en"])
(obo/IAO_0000413 rdfs/label ["is duration of" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000417
(obo/IAO_0000417 rdf/type owl/ObjectProperty)
(obo/IAO_0000417 owl/equivalentProperty bnode/ccp-extensions_genid19)
(bnode/ccp-extensions_genid19 owl/inverseOf obo/IAO_0000221)
(obo/IAO_0000221 owl/equivalentProperty bnode/ccp-extensions_genid399)
(bnode/ccp-extensions_genid399 owl/inverseOf obo/IAO_0000417)
(obo/IAO_0000417 rdfs/subPropertyOf bnode/ccp-extensions_genid25)
(bnode/ccp-extensions_genid25 owl/inverseOf obo/IAO_0000136)
(obo/IAO_0000417 owl/inverseOf bnode/ccp-extensions_genid400)
(bnode/ccp-extensions_genid400 owl/inverseOf obo/IAO_0000417)
(obo/IAO_0000417 rdf/type owl/AsymmetricProperty)
(obo/IAO_0000417 rdf/type owl/IrreflexiveProperty)
(obo/IAO_0000417 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000417 obo/IAO_0000115 ["inverse of the relation of is quality measurement of" "en"])
(obo/IAO_0000417 obo/IAO_0000116 ["2009/10/19 Alan Ruttenberg. Named 'junk' relation useful in restrictions, but not a real instance relationship" "en"])
(obo/IAO_0000417 obo/IAO_0000117 ["Person:Alan Ruttenberg" "en"])
(obo/IAO_0000417 rdfs/label ["is quality measured as" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000418
(obo/IAO_0000418 rdf/type owl/ObjectProperty)
(obo/IAO_0000418 owl/equivalentProperty bnode/ccp-extensions_genid26)
(bnode/ccp-extensions_genid26 owl/inverseOf obo/IAO_0000419)
(obo/IAO_0000419 owl/equivalentProperty bnode/ccp-extensions_genid401)
(bnode/ccp-extensions_genid401 owl/inverseOf obo/IAO_0000418)
(obo/IAO_0000418 rdfs/subPropertyOf obo/IAO_0000136)
(obo/IAO_0000418 owl/inverseOf obo/IAO_0000419)
(obo/IAO_0000418 owl/inverseOf bnode/ccp-extensions_genid402)
(bnode/ccp-extensions_genid402 owl/inverseOf obo/IAO_0000418)
(obo/IAO_0000418 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000418 obo/IAO_0000115 ["a relation between a data item and a quality of a material entity where the material entity is the specified output of a material transformation which achieves an objective specification that indicates the intended value of the specified quality." "en"])
(obo/IAO_0000418 obo/IAO_0000117 ["Person:Alan Ruttenberg" "en"])
(obo/IAO_0000418 obo/IAO_0000117 ["Person:Bjoern Peters" "en"])
(obo/IAO_0000418 rdfs/label ["is quality specification of" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000419
(obo/IAO_0000419 rdf/type owl/ObjectProperty)
(obo/IAO_0000419 owl/equivalentProperty bnode/ccp-extensions_genid27)
(bnode/ccp-extensions_genid27 owl/inverseOf obo/IAO_0000418)
(obo/IAO_0000418 owl/equivalentProperty bnode/ccp-extensions_genid403)
(bnode/ccp-extensions_genid403 owl/inverseOf obo/IAO_0000419)
(obo/IAO_0000419 rdfs/subPropertyOf bnode/ccp-extensions_genid28)
(bnode/ccp-extensions_genid28 owl/inverseOf obo/IAO_0000136)
(obo/IAO_0000419 owl/inverseOf bnode/ccp-extensions_genid404)
(bnode/ccp-extensions_genid404 owl/inverseOf obo/IAO_0000419)
(obo/IAO_0000419 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000419 obo/IAO_0000115 ["inverse of the relation of is quality specification of" "en"])
(obo/IAO_0000419 obo/IAO_0000116 ["2009/10/19 Alan Ruttenberg. Named 'junk' relation useful in restrictions, but not a real instance relationship" "en"])
(obo/IAO_0000419 obo/IAO_0000117 ["Person:Alan Ruttenberg" "en"])
(obo/IAO_0000419 obo/IAO_0000117 ["Person:Bjoern Peters" "en"])
(obo/IAO_0000419 rdfs/label ["quality is specified as" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000581
(obo/IAO_0000581 rdf/type owl/ObjectProperty)
(obo/IAO_0000581 rdfs/subPropertyOf obo/BFO_0000051)
(obo/IAO_0000581 rdfs/subPropertyOf bnode/ccp-extensions_genid29)
(bnode/ccp-extensions_genid29 owl/inverseOf obo/BFO_0000050)
(obo/IAO_0000581 owl/inverseOf bnode/ccp-extensions_genid30)
(bnode/ccp-extensions_genid30 owl/inverseOf obo/IAO_0000581)
(obo/IAO_0000581 rdf/type owl/FunctionalProperty)
(obo/IAO_0000581 rdfs/domain obo/IAO_0000582)
(obo/IAO_0000581 rdfs/range obo/IAO_0000416)
(obo/IAO_0000581 obo/IAO_0000114 obo/IAO_0000123)
(obo/IAO_0000581 obo/IAO_0000115 ["relates a time stamped measurement datum to the time measurement datum that denotes the time when the measurement was taken" "en"])
(obo/IAO_0000581 obo/IAO_0000117 ["Alan Ruttenberg" "en"])
(obo/IAO_0000581 rdfs/label ["has time stamp" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000583
(obo/IAO_0000583 rdf/type owl/ObjectProperty)
(obo/IAO_0000583 rdfs/subPropertyOf obo/BFO_0000051)
(obo/IAO_0000583 rdfs/subPropertyOf bnode/ccp-extensions_genid31)
(bnode/ccp-extensions_genid31 owl/inverseOf obo/BFO_0000050)
(obo/IAO_0000583 owl/inverseOf bnode/ccp-extensions_genid32)
(bnode/ccp-extensions_genid32 owl/inverseOf obo/IAO_0000583)
(obo/IAO_0000583 rdfs/domain obo/IAO_0000582)
(obo/IAO_0000583 rdfs/range bnode/ccp-extensions_genid33)
(bnode/ccp-extensions_genid33 owl/intersectionOf bnode/ccp-extensions_genid36)
(bnode/ccp-extensions_genid36 rdf/type rdf/List)
(bnode/ccp-extensions_genid36 rdf/first obo/IAO_0000109)
(bnode/ccp-extensions_genid36 rdf/rest bnode/ccp-extensions_genid34)
(bnode/ccp-extensions_genid34 rdf/type rdf/List)
(bnode/ccp-extensions_genid34 rdf/first bnode/ccp-extensions_genid35)
(bnode/ccp-extensions_genid35 rdf/type owl/Class)
(bnode/ccp-extensions_genid35 owl/complementOf obo/IAO_0000416)
(bnode/ccp-extensions_genid34 rdf/rest rdf/nil)
(bnode/ccp-extensions_genid33 rdf/type owl/Class)
(obo/IAO_0000583 obo/IAO_0000114 obo/IAO_0000123)
(obo/IAO_0000583 obo/IAO_0000115 ["relates a time stamped measurement datum to the measurement datum that was measured" "en"])
(obo/IAO_0000583 obo/IAO_0000117 ["Alan Ruttenberg" "en"])
(obo/IAO_0000583 rdfs/label ["has measurement datum" "en"])
;; 
;; http://purl.obolibrary.org/obo/OBI_0000293
(obo/OBI_0000293 rdf/type owl/ObjectProperty)
(obo/OBI_0000293 owl/equivalentProperty bnode/ccp-extensions_genid37)
(bnode/ccp-extensions_genid37 owl/inverseOf obo/OBI_0000295)
(obo/OBI_0000295 owl/equivalentProperty bnode/ccp-extensions_genid405)
(bnode/ccp-extensions_genid405 owl/inverseOf obo/OBI_0000293)
(obo/OBI_0000293 rdfs/subPropertyOf obo/RO_0000057)
(obo/OBI_0000293 rdfs/subPropertyOf bnode/ccp-extensions_genid39)
(bnode/ccp-extensions_genid39 owl/inverseOf obo/RO_0000056)
(obo/OBI_0000293 owl/inverseOf obo/OBI_0000295)
(obo/OBI_0000293 owl/inverseOf bnode/ccp-extensions_genid406)
(bnode/ccp-extensions_genid406 owl/inverseOf obo/OBI_0000293)
(obo/OBI_0000293 rdf/type owl/AsymmetricProperty)
(obo/OBI_0000293 rdf/type owl/IrreflexiveProperty)
(obo/OBI_0000293 rdfs/domain obo/OBI_0000011)
(obo/OBI_0000293 obo/IAO_0000111 ["has_specified_input" "en"])
(obo/OBI_0000293 obo/IAO_0000112 ["see is_input_of example_of_usage" "en"])
(obo/OBI_0000293 obo/IAO_0000114 obo/IAO_0000122)
(obo/OBI_0000293 obo/IAO_0000115 ["A relation between a planned process and a continuant participating in that process that is not created during  the process. The presence of the continuant during the process is explicitly specified in the plan specification which the process realizes the concretization of." "en"])
(obo/OBI_0000293 obo/IAO_0000116 ["8/17/09: specified inputs of one process are not necessarily specified inputs of a larger process that it is part of. This is in contrast to how 'has participant' works."])
(obo/OBI_0000293 obo/IAO_0000117 ["PERSON: Alan Ruttenberg" "en"])
(obo/OBI_0000293 obo/IAO_0000117 ["PERSON: Bjoern Peters"])
(obo/OBI_0000293 obo/IAO_0000117 ["PERSON: Larry Hunter"])
(obo/OBI_0000293 obo/IAO_0000117 ["PERSON: Melanie Coutot"])
(obo/OBI_0000293 obo/IAO_0000412 obo/obi.owl)
(obo/OBI_0000293 rdfs/label ["has_specified_input" "en"])
;; 
;; http://purl.obolibrary.org/obo/OBI_0000295
(obo/OBI_0000295 rdf/type owl/ObjectProperty)
(obo/OBI_0000295 owl/equivalentProperty bnode/ccp-extensions_genid38)
(bnode/ccp-extensions_genid38 owl/inverseOf obo/OBI_0000293)
(obo/OBI_0000293 owl/equivalentProperty bnode/ccp-extensions_genid408)
(bnode/ccp-extensions_genid408 owl/inverseOf obo/OBI_0000295)
(obo/OBI_0000295 rdfs/subPropertyOf obo/RO_0000056)
(obo/OBI_0000295 rdfs/subPropertyOf bnode/ccp-extensions_genid40)
(bnode/ccp-extensions_genid40 owl/inverseOf obo/RO_0000057)
(obo/OBI_0000295 owl/inverseOf bnode/ccp-extensions_genid407)
(bnode/ccp-extensions_genid407 owl/inverseOf obo/OBI_0000295)
(obo/OBI_0000295 rdf/type owl/AsymmetricProperty)
(obo/OBI_0000295 rdf/type owl/IrreflexiveProperty)
(obo/OBI_0000295 rdfs/label ["is_specified_input_of" "en"])
;; 
;; http://purl.obolibrary.org/obo/OBI_0000299
(obo/OBI_0000299 rdf/type owl/ObjectProperty)
(obo/OBI_0000299 owl/equivalentProperty bnode/ccp-extensions_genid41)
(bnode/ccp-extensions_genid41 owl/inverseOf obo/OBI_0000312)
(obo/OBI_0000312 owl/equivalentProperty bnode/ccp-extensions_genid410)
(bnode/ccp-extensions_genid410 owl/inverseOf obo/OBI_0000299)
(obo/OBI_0000299 rdfs/subPropertyOf obo/RO_0000057)
(obo/OBI_0000299 rdfs/subPropertyOf bnode/ccp-extensions_genid43)
(bnode/ccp-extensions_genid43 owl/inverseOf obo/RO_0000056)
(obo/OBI_0000299 owl/inverseOf obo/OBI_0000312)
(obo/OBI_0000299 owl/inverseOf bnode/ccp-extensions_genid409)
(bnode/ccp-extensions_genid409 owl/inverseOf obo/OBI_0000299)
(obo/OBI_0000299 rdf/type owl/AsymmetricProperty)
(obo/OBI_0000299 rdf/type owl/IrreflexiveProperty)
(obo/OBI_0000299 rdfs/domain obo/OBI_0000011)
(obo/OBI_0000299 obo/IAO_0000111 ["has_specified_output" "en"])
(obo/OBI_0000299 obo/IAO_0000114 obo/IAO_0000122)
(obo/OBI_0000299 obo/IAO_0000115 ["A relation between a planned process and a continuant participating in that process. The presence of the continuant at the end of the process is explicitly specified in the objective specification which the process realizes the concretization of." "en"])
(obo/OBI_0000299 obo/IAO_0000117 ["PERSON: Alan Ruttenberg" "en"])
(obo/OBI_0000299 obo/IAO_0000117 ["PERSON: Bjoern Peters"])
(obo/OBI_0000299 obo/IAO_0000117 ["PERSON: Larry Hunter"])
(obo/OBI_0000299 obo/IAO_0000117 ["PERSON: Melanie Courtot"])
(obo/OBI_0000299 obo/IAO_0000412 obo/obi.owl)
(obo/OBI_0000299 rdfs/label ["has_specified_output" "en"])
;; 
;; http://purl.obolibrary.org/obo/OBI_0000312
(obo/OBI_0000312 rdf/type owl/ObjectProperty)
(obo/OBI_0000312 owl/equivalentProperty bnode/ccp-extensions_genid42)
(bnode/ccp-extensions_genid42 owl/inverseOf obo/OBI_0000299)
(obo/OBI_0000299 owl/equivalentProperty bnode/ccp-extensions_genid411)
(bnode/ccp-extensions_genid411 owl/inverseOf obo/OBI_0000312)
(obo/OBI_0000312 rdfs/subPropertyOf obo/RO_0000056)
(obo/OBI_0000312 rdfs/subPropertyOf bnode/ccp-extensions_genid44)
(bnode/ccp-extensions_genid44 owl/inverseOf obo/RO_0000057)
(obo/OBI_0000312 owl/inverseOf bnode/ccp-extensions_genid412)
(bnode/ccp-extensions_genid412 owl/inverseOf obo/OBI_0000312)
(obo/OBI_0000312 rdf/type owl/AsymmetricProperty)
(obo/OBI_0000312 rdf/type owl/IrreflexiveProperty)
(obo/OBI_0000312 rdfs/range obo/OBI_0000011)
(obo/OBI_0000312 obo/IAO_0000111 ["is_specified_output_of" "en"])
(obo/OBI_0000312 obo/IAO_0000114 obo/IAO_0000122)
(obo/OBI_0000312 obo/IAO_0000115 ["A relation between a planned process and a continuant participating in that process. The presence of the continuant at the end of the process is explicitly specified in the objective specification which the process realizes the concretization of." "en"])
(obo/OBI_0000312 obo/IAO_0000117 ["Alan Ruttenberg" "en"])
(obo/OBI_0000312 obo/IAO_0000117 ["PERSON:Bjoern Peters"])
(obo/OBI_0000312 obo/IAO_0000412 obo/obi.owl)
(obo/OBI_0000312 rdfs/label ["is_specified_output_of" "en"])
;; 
;; http://purl.obolibrary.org/obo/RO_0000052
(obo/RO_0000052 rdf/type owl/ObjectProperty)
(obo/RO_0000052 owl/equivalentProperty bnode/ccp-extensions_genid45)
(bnode/ccp-extensions_genid45 owl/inverseOf obo/RO_0000053)
(obo/RO_0000053 owl/equivalentProperty bnode/ccp-extensions_genid414)
(bnode/ccp-extensions_genid414 owl/inverseOf obo/RO_0000052)
(obo/RO_0000052 rdfs/subPropertyOf owl/topObjectProperty)
(obo/RO_0000052 owl/inverseOf obo/RO_0000053)
(obo/RO_0000052 owl/inverseOf bnode/ccp-extensions_genid413)
(bnode/ccp-extensions_genid413 owl/inverseOf obo/RO_0000052)
(obo/RO_0000052 obo/IAO_0000111 ["inheres in" "en"])
(obo/RO_0000052 obo/IAO_0000112 ["this fragility inheres in this vase" "en"])
(obo/RO_0000052 obo/IAO_0000112 ["this red color inheres in this apple" "en"])
(obo/RO_0000052 obo/IAO_0000115 ["a relation between a specifically dependent continuant (the dependent) and an independent continuant (the bearer), in which the dependent specifically depends on the bearer for its existence" "en"])
(obo/RO_0000052 obo/IAO_0000116 ["A dependent inheres in its bearer at all times for which the dependent exists." "en"])
(obo/RO_0000052 obo/IAO_0000118 ["inheres_in" "en"])
(obo/RO_0000052 obo/RO_0001900 obo/RO_0001901)
(obo/RO_0000052 rdfs/label ["inheres in" "en"])
;; 
;; http://purl.obolibrary.org/obo/RO_0000053
(obo/RO_0000053 rdf/type owl/ObjectProperty)
(obo/RO_0000053 owl/equivalentProperty bnode/ccp-extensions_genid46)
(bnode/ccp-extensions_genid46 owl/inverseOf obo/RO_0000052)
(obo/RO_0000052 owl/equivalentProperty bnode/ccp-extensions_genid415)
(bnode/ccp-extensions_genid415 owl/inverseOf obo/RO_0000053)
(obo/RO_0000053 rdfs/subPropertyOf owl/topObjectProperty)
(obo/RO_0000053 owl/inverseOf bnode/ccp-extensions_genid416)
(bnode/ccp-extensions_genid416 owl/inverseOf obo/RO_0000053)
(obo/RO_0000053 rdfs/range obo/BFO_0000020)
(obo/RO_0000053 obo/IAO_0000111 ["bearer of" "en"])
(obo/RO_0000053 obo/IAO_0000112 ["this apple is bearer of this red color" "en"])
(obo/RO_0000053 obo/IAO_0000112 ["this vase is bearer of this fragility" "en"])
(obo/RO_0000053 obo/IAO_0000115 ["a relation between an independent continuant (the bearer) and a specifically dependent continuant (the dependent), in which the dependent specifically depends on the bearer for its existence" "en"])
(obo/RO_0000053 obo/IAO_0000116 ["A bearer can have many dependents, and its dependents can exist for different periods of time, but none of its dependents can exist when the bearer does not exist." "en"])
(obo/RO_0000053 obo/IAO_0000118 ["bearer_of" "en"])
(obo/RO_0000053 obo/IAO_0000118 ["is bearer of" "en"])
(obo/RO_0000053 obo/RO_0001900 obo/RO_0001901)
(obo/RO_0000053 rdfs/label ["bearer of" "en"])
;; 
;; http://purl.obolibrary.org/obo/RO_0000056
(obo/RO_0000056 rdf/type owl/ObjectProperty)
(obo/RO_0000056 owl/equivalentProperty bnode/ccp-extensions_genid47)
(bnode/ccp-extensions_genid47 owl/inverseOf obo/RO_0000057)
(obo/RO_0000057 owl/equivalentProperty bnode/ccp-extensions_genid418)
(bnode/ccp-extensions_genid418 owl/inverseOf obo/RO_0000056)
(obo/RO_0000056 rdfs/subPropertyOf owl/topObjectProperty)
(obo/RO_0000056 owl/inverseOf obo/RO_0000057)
(obo/RO_0000056 owl/inverseOf bnode/ccp-extensions_genid417)
(bnode/ccp-extensions_genid417 owl/inverseOf obo/RO_0000056)
(obo/RO_0000056 rdf/type owl/AsymmetricProperty)
(obo/RO_0000056 rdf/type owl/IrreflexiveProperty)
(obo/RO_0000056 rdfs/domain obo/BFO_0000002)
(obo/RO_0000056 rdfs/range obo/BFO_0000003)
(obo/RO_0000056 obo/IAO_0000111 ["participates in" "en"])
(obo/RO_0000056 obo/IAO_0000112 ["this blood clot participates in this blood coagulation" "en"])
(obo/RO_0000056 obo/IAO_0000112 ["this input material (or this output material) participates in this process" "en"])
(obo/RO_0000056 obo/IAO_0000112 ["this investigator participates in this investigation" "en"])
(obo/RO_0000056 obo/IAO_0000115 ["a relation between a continuant and a process, in which the continuant is somehow involved in the process" "en"])
(obo/RO_0000056 obo/IAO_0000118 ["participates_in" "en"])
(obo/RO_0000056 rdfs/label ["participates in" "en"])
;; 
;; http://purl.obolibrary.org/obo/RO_0000057
(obo/RO_0000057 rdf/type owl/ObjectProperty)
(obo/RO_0000057 owl/equivalentProperty bnode/ccp-extensions_genid48)
(bnode/ccp-extensions_genid48 owl/inverseOf obo/RO_0000056)
(obo/RO_0000056 owl/equivalentProperty bnode/ccp-extensions_genid420)
(bnode/ccp-extensions_genid420 owl/inverseOf obo/RO_0000057)
(obo/RO_0000057 rdfs/subPropertyOf owl/topObjectProperty)
(obo/RO_0000057 owl/inverseOf bnode/ccp-extensions_genid419)
(bnode/ccp-extensions_genid419 owl/inverseOf obo/RO_0000057)
(obo/RO_0000057 rdf/type owl/AsymmetricProperty)
(obo/RO_0000057 rdf/type owl/IrreflexiveProperty)
(obo/RO_0000057 rdfs/domain obo/BFO_0000003)
(obo/RO_0000057 rdfs/range obo/BFO_0000002)
(obo/RO_0000057 obo/IAO_0000111 ["has participant" "en"])
(obo/RO_0000057 obo/IAO_0000112 ["this blood coagulation has participant this blood clot" "en"])
(obo/RO_0000057 obo/IAO_0000112 ["this investigation has participant this investigator" "en"])
(obo/RO_0000057 obo/IAO_0000112 ["this process has participant this input material (or this output material)" "en"])
(obo/RO_0000057 obo/IAO_0000115 ["a relation between a process and a continuant, in which the continuant is somehow involved in the process" "en"])
(obo/RO_0000057 obo/IAO_0000116 ["Has_participant is a primitive instance-level relation between a process, a continuant, and a time at which the continuant participates in some way in the process. The relation obtains, for example, when this particular process of oxygen exchange across this particular alveolar membrane has_participant this particular sample of hemoglobin at this particular time." "en"])
(obo/RO_0000057 obo/IAO_0000118 ["has_participant" "en"])
(obo/RO_0000057 dc/source ["http://www.obofoundry.org/ro/;;OBO_REL:has_participant"])
(obo/RO_0000057 rdfs/label ["has participant" "en"])
;; 
;; http://purl.obolibrary.org/obo/RO_0000058
(obo/RO_0000058 rdf/type owl/ObjectProperty)
(obo/RO_0000058 owl/equivalentProperty bnode/ccp-extensions_genid49)
(bnode/ccp-extensions_genid49 owl/inverseOf obo/RO_0000059)
(obo/RO_0000059 owl/equivalentProperty bnode/ccp-extensions_genid422)
(bnode/ccp-extensions_genid422 owl/inverseOf obo/RO_0000058)
(obo/RO_0000058 rdfs/subPropertyOf owl/topObjectProperty)
(obo/RO_0000058 owl/inverseOf obo/RO_0000059)
(obo/RO_0000058 owl/inverseOf bnode/ccp-extensions_genid421)
(bnode/ccp-extensions_genid421 owl/inverseOf obo/RO_0000058)
(obo/RO_0000058 rdf/type owl/AsymmetricProperty)
(obo/RO_0000058 rdf/type owl/IrreflexiveProperty)
(obo/RO_0000058 rdfs/domain obo/BFO_0000031)
(obo/RO_0000058 rdfs/range obo/BFO_0000020)
(obo/RO_0000058 obo/IAO_0000112 ["A journal article is an information artifact that inheres in some number of printed journals. For each copy of the printed journal there is some quality that carries the journal article, such as a pattern of ink. The journal article (a generically dependent continuant) is concretized as the quality (a specifically dependent continuant), and both depend on that copy of the printed journal (an independent continuant)." "en"])
(obo/RO_0000058 obo/IAO_0000112 ["An investigator reads a protocol and forms a plan to carry out an assay. The plan is a realizable entity (a specifically dependent continuant) that concretizes the protocol (a generically dependent continuant), and both depend on the investigator (an independent continuant). The plan is then realized by the assay (a process)." "en"])
(obo/RO_0000058 obo/IAO_0000115 ["A relationship between a generically dependent continuant and a specifically dependent continuant, in which the generically dependent continuant depends on some independent continuant in virtue of the fact that the specifically dependent continuant also depends on that same independent continuant. A generically dependent continuant may be concretized as multiple specifically dependent continuants." "en"])
(obo/RO_0000058 rdfs/label ["is concretized as" "en"])
;; 
;; http://purl.obolibrary.org/obo/RO_0000059
(obo/RO_0000059 rdf/type owl/ObjectProperty)
(obo/RO_0000059 owl/equivalentProperty bnode/ccp-extensions_genid50)
(bnode/ccp-extensions_genid50 owl/inverseOf obo/RO_0000058)
(obo/RO_0000058 owl/equivalentProperty bnode/ccp-extensions_genid423)
(bnode/ccp-extensions_genid423 owl/inverseOf obo/RO_0000059)
(obo/RO_0000059 rdfs/subPropertyOf owl/topObjectProperty)
(obo/RO_0000059 owl/inverseOf bnode/ccp-extensions_genid424)
(bnode/ccp-extensions_genid424 owl/inverseOf obo/RO_0000059)
(obo/RO_0000059 rdf/type owl/AsymmetricProperty)
(obo/RO_0000059 rdf/type owl/IrreflexiveProperty)
(obo/RO_0000059 rdfs/domain obo/BFO_0000020)
(obo/RO_0000059 rdfs/range obo/BFO_0000031)
(obo/RO_0000059 obo/IAO_0000112 ["A journal article is an information artifact that inheres in some number of printed journals. For each copy of the printed journal there is some quality that carries the journal article, such as a pattern of ink. The quality (a specifically dependent continuant) concretizes the journal article (a generically dependent continuant), and both depend on that copy of the printed journal (an independent continuant)." "en"])
(obo/RO_0000059 obo/IAO_0000112 ["An investigator reads a protocol and forms a plan to carry out an assay. The plan is a realizable entity (a specifically dependent continuant) that concretizes the protocol (a generically dependent continuant), and both depend on the investigator (an independent continuant). The plan is then realized by the assay (a process)." "en"])
(obo/RO_0000059 obo/IAO_0000115 ["A relationship between a specifically dependent continuant and a generically dependent continuant, in which the generically dependent continuant depends on some independent continuant in virtue of the fact that the specifically dependent continuant also depends on that same independent continuant. Multiple specifically dependent continuants can concretize the same generically dependent continuant." "en"])
(obo/RO_0000059 rdfs/label ["concretizes" "en"])
;; 
;; http://purl.obolibrary.org/obo/RO_0000079
(obo/RO_0000079 rdf/type owl/ObjectProperty)
(obo/RO_0000079 owl/equivalentProperty bnode/ccp-extensions_genid51)
(bnode/ccp-extensions_genid51 owl/inverseOf obo/RO_0000085)
(obo/RO_0000085 owl/equivalentProperty bnode/ccp-extensions_genid426)
(bnode/ccp-extensions_genid426 owl/inverseOf obo/RO_0000079)
(obo/RO_0000079 rdfs/subPropertyOf obo/RO_0000052)
(obo/RO_0000079 rdfs/subPropertyOf bnode/ccp-extensions_genid53)
(bnode/ccp-extensions_genid53 owl/inverseOf obo/RO_0000053)
(obo/RO_0000079 owl/inverseOf obo/RO_0000085)
(obo/RO_0000079 owl/inverseOf bnode/ccp-extensions_genid425)
(bnode/ccp-extensions_genid425 owl/inverseOf obo/RO_0000079)
(obo/RO_0000079 rdf/type owl/AsymmetricProperty)
(obo/RO_0000079 rdf/type owl/IrreflexiveProperty)
(obo/RO_0000079 rdfs/domain obo/BFO_0000034)
(obo/RO_0000079 obo/IAO_0000112 ["this catalysis function is a function of this enzyme" "en"])
(obo/RO_0000079 obo/IAO_0000115 ["a relation between a function and an independent continuant (the bearer), in which the function specifically depends on the bearer for its existence" "en"])
(obo/RO_0000079 obo/IAO_0000116 ["A function inheres in its bearer at all times for which the function exists, however the function need not be realized at all the times that the function exists." "en"])
(obo/RO_0000079 obo/IAO_0000118 ["function_of" "en"])
(obo/RO_0000079 obo/IAO_0000118 ["is function of" "en"])
(obo/RO_0000079 rdfs/label ["function of" "en"])
;; 
;; http://purl.obolibrary.org/obo/RO_0000080
(obo/RO_0000080 rdf/type owl/ObjectProperty)
(obo/RO_0000080 owl/equivalentProperty bnode/ccp-extensions_genid54)
(bnode/ccp-extensions_genid54 owl/inverseOf obo/RO_0000086)
(obo/RO_0000086 owl/equivalentProperty bnode/ccp-extensions_genid427)
(bnode/ccp-extensions_genid427 owl/inverseOf obo/RO_0000080)
(obo/RO_0000080 rdfs/subPropertyOf obo/RO_0000052)
(obo/RO_0000080 rdfs/subPropertyOf bnode/ccp-extensions_genid56)
(bnode/ccp-extensions_genid56 owl/inverseOf obo/RO_0000053)
(obo/RO_0000080 owl/inverseOf obo/RO_0000086)
(obo/RO_0000080 owl/inverseOf bnode/ccp-extensions_genid428)
(bnode/ccp-extensions_genid428 owl/inverseOf obo/RO_0000080)
(obo/RO_0000080 obo/IAO_0000112 ["this red color is a quality of this apple" "en"])
(obo/RO_0000080 obo/IAO_0000115 ["a relation between a quality and an independent continuant (the bearer), in which the quality specifically depends on the bearer for its existence" "en"])
(obo/RO_0000080 obo/IAO_0000116 ["A quality inheres in its bearer at all times for which the quality exists." "en"])
(obo/RO_0000080 obo/IAO_0000118 ["is quality of" "en"])
(obo/RO_0000080 obo/IAO_0000118 ["quality_of" "en"])
(obo/RO_0000080 rdfs/label ["quality of" "en"])
;; 
;; http://purl.obolibrary.org/obo/RO_0000081
(obo/RO_0000081 rdf/type owl/ObjectProperty)
(obo/RO_0000081 owl/equivalentProperty bnode/ccp-extensions_genid57)
(bnode/ccp-extensions_genid57 owl/inverseOf obo/RO_0000087)
(obo/RO_0000087 owl/equivalentProperty bnode/ccp-extensions_genid429)
(bnode/ccp-extensions_genid429 owl/inverseOf obo/RO_0000081)
(obo/RO_0000081 rdfs/subPropertyOf obo/RO_0000052)
(obo/RO_0000081 rdfs/subPropertyOf bnode/ccp-extensions_genid59)
(bnode/ccp-extensions_genid59 owl/inverseOf obo/RO_0000053)
(obo/RO_0000081 owl/inverseOf obo/RO_0000087)
(obo/RO_0000081 owl/inverseOf bnode/ccp-extensions_genid430)
(bnode/ccp-extensions_genid430 owl/inverseOf obo/RO_0000081)
(obo/RO_0000081 rdf/type owl/AsymmetricProperty)
(obo/RO_0000081 rdf/type owl/IrreflexiveProperty)
(obo/RO_0000081 obo/IAO_0000112 ["this investigator role is a role of this person" "en"])
(obo/RO_0000081 obo/IAO_0000115 ["a relation between a role and an independent continuant (the bearer), in which the role specifically depends on the bearer for its existence" "en"])
(obo/RO_0000081 obo/IAO_0000116 ["A role inheres in its bearer at all times for which the role exists, however the role need not be realized at all the times that the role exists." "en"])
(obo/RO_0000081 obo/IAO_0000118 ["is role of" "en"])
(obo/RO_0000081 obo/IAO_0000118 ["role_of" "en"])
(obo/RO_0000081 rdfs/label ["role of" "en"])
;; 
;; http://purl.obolibrary.org/obo/RO_0000085
(obo/RO_0000085 rdf/type owl/ObjectProperty)
(obo/RO_0000085 owl/equivalentProperty bnode/ccp-extensions_genid52)
(bnode/ccp-extensions_genid52 owl/inverseOf obo/RO_0000079)
(obo/RO_0000079 owl/equivalentProperty bnode/ccp-extensions_genid431)
(bnode/ccp-extensions_genid431 owl/inverseOf obo/RO_0000085)
(obo/RO_0000085 rdfs/subPropertyOf obo/RO_0000053)
(obo/RO_0000085 rdfs/subPropertyOf bnode/ccp-extensions_genid60)
(bnode/ccp-extensions_genid60 owl/inverseOf obo/RO_0000052)
(obo/RO_0000085 owl/inverseOf bnode/ccp-extensions_genid432)
(bnode/ccp-extensions_genid432 owl/inverseOf obo/RO_0000085)
(obo/RO_0000085 rdf/type owl/AsymmetricProperty)
(obo/RO_0000085 rdf/type owl/IrreflexiveProperty)
(obo/RO_0000085 rdfs/domain obo/BFO_0000004)
(obo/RO_0000085 rdfs/range obo/BFO_0000034)
(obo/RO_0000085 obo/IAO_0000112 ["this enzyme has function this catalysis function (more colloquially: this enzyme has this catalysis function)" "en"])
(obo/RO_0000085 obo/IAO_0000115 ["a relation between an independent continuant (the bearer) and a function, in which the function specifically depends on the bearer for its existence" "en"])
(obo/RO_0000085 obo/IAO_0000116 ["A bearer can have many functions, and its functions can exist for different periods of time, but none of its functions can exist when the bearer does not exist. A function need not be realized at all the times that the function exists." "en"])
(obo/RO_0000085 obo/IAO_0000118 ["has_function" "en"])
(obo/RO_0000085 rdfs/label ["has function" "en"])
;; 
;; http://purl.obolibrary.org/obo/RO_0000086
(obo/RO_0000086 rdf/type owl/ObjectProperty)
(obo/RO_0000086 owl/equivalentProperty bnode/ccp-extensions_genid55)
(bnode/ccp-extensions_genid55 owl/inverseOf obo/RO_0000080)
(obo/RO_0000080 owl/equivalentProperty bnode/ccp-extensions_genid434)
(bnode/ccp-extensions_genid434 owl/inverseOf obo/RO_0000086)
(obo/RO_0000086 rdfs/subPropertyOf obo/RO_0000053)
(obo/RO_0000086 rdfs/subPropertyOf bnode/ccp-extensions_genid61)
(bnode/ccp-extensions_genid61 owl/inverseOf obo/RO_0000052)
(obo/RO_0000086 owl/inverseOf bnode/ccp-extensions_genid433)
(bnode/ccp-extensions_genid433 owl/inverseOf obo/RO_0000086)
(obo/RO_0000086 rdfs/range obo/BFO_0000019)
(obo/RO_0000086 obo/IAO_0000112 ["this apple has quality this red color" "en"])
(obo/RO_0000086 obo/IAO_0000115 ["a relation between an independent continuant (the bearer) and a quality, in which the quality specifically depends on the bearer for its existence" "en"])
(obo/RO_0000086 obo/IAO_0000116 ["A bearer can have many qualities, and its qualities can exist for different periods of time, but none of its qualities can exist when the bearer does not exist." "en"])
(obo/RO_0000086 obo/IAO_0000118 ["has_quality" "en"])
(obo/RO_0000086 rdfs/label ["has quality" "en"])
;; 
;; http://purl.obolibrary.org/obo/RO_0000087
(obo/RO_0000087 rdf/type owl/ObjectProperty)
(obo/RO_0000087 owl/equivalentProperty bnode/ccp-extensions_genid58)
(bnode/ccp-extensions_genid58 owl/inverseOf obo/RO_0000081)
(obo/RO_0000081 owl/equivalentProperty bnode/ccp-extensions_genid435)
(bnode/ccp-extensions_genid435 owl/inverseOf obo/RO_0000087)
(obo/RO_0000087 rdfs/subPropertyOf obo/RO_0000053)
(obo/RO_0000087 rdfs/subPropertyOf bnode/ccp-extensions_genid62)
(bnode/ccp-extensions_genid62 owl/inverseOf obo/RO_0000052)
(obo/RO_0000087 owl/inverseOf bnode/ccp-extensions_genid436)
(bnode/ccp-extensions_genid436 owl/inverseOf obo/RO_0000087)
(obo/RO_0000087 rdf/type owl/AsymmetricProperty)
(obo/RO_0000087 rdf/type owl/IrreflexiveProperty)
(obo/RO_0000087 rdfs/domain obo/BFO_0000004)
(obo/RO_0000087 rdfs/range obo/BFO_0000023)
(obo/RO_0000087 obo/IAO_0000112 ["this person has role this investigator role (more colloquially: this person has this role of investigator)" "en"])
(obo/RO_0000087 obo/IAO_0000115 ["a relation between an independent continuant (the bearer) and a role, in which the role specifically depends on the bearer for its existence" "en"])
(obo/RO_0000087 obo/IAO_0000116 ["A bearer can have many roles, and its roles can exist for different periods of time, but none of its roles can exist when the bearer does not exist. A role need not be realized at all the times that the role exists." "en"])
(obo/RO_0000087 obo/IAO_0000118 ["has_role" "en"])
(obo/RO_0000087 rdfs/label ["has role" "en"])
;; 
;; http://purl.obolibrary.org/obo/RO_0000091
(obo/RO_0000091 rdf/type owl/ObjectProperty)
(obo/RO_0000091 owl/equivalentProperty bnode/ccp-extensions_genid63)
(bnode/ccp-extensions_genid63 owl/inverseOf obo/RO_0000092)
(obo/RO_0000092 owl/equivalentProperty bnode/ccp-extensions_genid438)
(bnode/ccp-extensions_genid438 owl/inverseOf obo/RO_0000091)
(obo/RO_0000091 rdfs/subPropertyOf obo/RO_0000053)
(obo/RO_0000091 rdfs/subPropertyOf bnode/ccp-extensions_genid65)
(bnode/ccp-extensions_genid65 owl/inverseOf obo/RO_0000052)
(obo/RO_0000091 owl/inverseOf obo/RO_0000092)
(obo/RO_0000091 owl/inverseOf bnode/ccp-extensions_genid437)
(bnode/ccp-extensions_genid437 owl/inverseOf obo/RO_0000091)
(obo/RO_0000091 rdf/type owl/AsymmetricProperty)
(obo/RO_0000091 rdf/type owl/IrreflexiveProperty)
(obo/RO_0000091 rdfs/domain obo/BFO_0000004)
(obo/RO_0000091 rdfs/range obo/BFO_0000016)
(obo/RO_0000091 obo/IAO_0000115 ["a relation between an independent continuant (the bearer) and a disposition, in which the disposition specifically depends on the bearer for its existence" "en"])
(obo/RO_0000091 rdfs/label ["has disposition" "en"])
;; 
;; http://purl.obolibrary.org/obo/RO_0000092
(obo/RO_0000092 rdf/type owl/ObjectProperty)
(obo/RO_0000092 owl/equivalentProperty bnode/ccp-extensions_genid64)
(bnode/ccp-extensions_genid64 owl/inverseOf obo/RO_0000091)
(obo/RO_0000091 owl/equivalentProperty bnode/ccp-extensions_genid439)
(bnode/ccp-extensions_genid439 owl/inverseOf obo/RO_0000092)
(obo/RO_0000092 rdfs/subPropertyOf obo/RO_0000052)
(obo/RO_0000092 rdfs/subPropertyOf bnode/ccp-extensions_genid66)
(bnode/ccp-extensions_genid66 owl/inverseOf obo/RO_0000053)
(obo/RO_0000092 owl/inverseOf bnode/ccp-extensions_genid440)
(bnode/ccp-extensions_genid440 owl/inverseOf obo/RO_0000092)
(obo/RO_0000092 rdf/type owl/AsymmetricProperty)
(obo/RO_0000092 rdf/type owl/IrreflexiveProperty)
(obo/RO_0000092 rdfs/label ["disposition of" "en"])
;; 
;; http://purl.obolibrary.org/obo/RO_0001000
(obo/RO_0001000 rdf/type owl/ObjectProperty)
(obo/RO_0001000 owl/equivalentProperty bnode/ccp-extensions_genid67)
(bnode/ccp-extensions_genid67 owl/inverseOf obo/RO_0001001)
(obo/RO_0001001 owl/equivalentProperty bnode/ccp-extensions_genid442)
(bnode/ccp-extensions_genid442 owl/inverseOf obo/RO_0001000)
(obo/RO_0001000 rdfs/subPropertyOf owl/topObjectProperty)
(obo/RO_0001000 owl/inverseOf obo/RO_0001001)
(obo/RO_0001000 owl/inverseOf bnode/ccp-extensions_genid441)
(bnode/ccp-extensions_genid441 owl/inverseOf obo/RO_0001000)
(obo/RO_0001000 obo/IAO_0000112 ["this cell derives from this parent cell (cell division)" "en"])
(obo/RO_0001000 obo/IAO_0000112 ["this nucleus derives from this parent nucleus (nuclear division)" "en"])
(obo/RO_0001000 obo/IAO_0000114 obo/IAO_0000125)
(obo/RO_0001000 obo/IAO_0000115 ["a relation between two distinct material entities, the new entity and the old entity, in which the new entity begins to exist when the old entity ceases to exist, and the new entity inherits the significant portion of the matter of the old entity" "en"])
(obo/RO_0001000 obo/IAO_0000116 ["This is a very general relation. More specific relations are preferred when applicable, such as 'directly develops from'." "en"])
(obo/RO_0001000 obo/IAO_0000118 ["derives_from" "en"])
(obo/RO_0001000 rdfs/label ["derives from" "en"])
;; 
;; http://purl.obolibrary.org/obo/RO_0001001
(obo/RO_0001001 rdf/type owl/ObjectProperty)
(obo/RO_0001001 owl/equivalentProperty bnode/ccp-extensions_genid68)
(bnode/ccp-extensions_genid68 owl/inverseOf obo/RO_0001000)
(obo/RO_0001000 owl/equivalentProperty bnode/ccp-extensions_genid443)
(bnode/ccp-extensions_genid443 owl/inverseOf obo/RO_0001001)
(obo/RO_0001001 rdfs/subPropertyOf owl/topObjectProperty)
(obo/RO_0001001 owl/inverseOf bnode/ccp-extensions_genid444)
(bnode/ccp-extensions_genid444 owl/inverseOf obo/RO_0001001)
(obo/RO_0001001 obo/IAO_0000112 ["this parent cell derives into this cell (cell division)" "en"])
(obo/RO_0001001 obo/IAO_0000112 ["this parent nucleus derives into this nucleus (nuclear division)" "en"])
(obo/RO_0001001 obo/IAO_0000114 obo/IAO_0000125)
(obo/RO_0001001 obo/IAO_0000115 ["a relation between two distinct material entities, the old entity and the new entity, in which the new entity begins to exist when the old entity ceases to exist, and the new entity inherits the significant portion of the matter of the old entity" "en"])
(obo/RO_0001001 obo/IAO_0000116 ["This is a very general relation. More specific relations are preferred when applicable, such as 'directly develops into'. To avoid making statements about a future that may not come to pass, it is often better to use the backward-looking 'derives from' rather than the forward-looking 'derives into'." "en"])
(obo/RO_0001001 obo/IAO_0000118 ["derives_into" "en"])
(obo/RO_0001001 rdfs/label ["derives into" "en"])
;; 
;; http://purl.obolibrary.org/obo/RO_0001015
(obo/RO_0001015 rdf/type owl/ObjectProperty)
(obo/RO_0001015 owl/equivalentProperty bnode/ccp-extensions_genid69)
(bnode/ccp-extensions_genid69 owl/inverseOf obo/RO_0001025)
(obo/RO_0001025 owl/equivalentProperty bnode/ccp-extensions_genid446)
(bnode/ccp-extensions_genid446 owl/inverseOf obo/RO_0001015)
(obo/RO_0001015 rdfs/subPropertyOf owl/topObjectProperty)
(obo/RO_0001015 owl/inverseOf obo/RO_0001025)
(obo/RO_0001015 owl/inverseOf bnode/ccp-extensions_genid445)
(bnode/ccp-extensions_genid445 owl/inverseOf obo/RO_0001015)
(obo/RO_0001015 rdf/type owl/TransitiveProperty)
(obo/RO_0001015 obo/IAO_0000111 ["is location of" "en"])
(obo/RO_0001015 obo/IAO_0000112 ["my head is the location of my brain" "en"])
(obo/RO_0001015 obo/IAO_0000112 ["this cage is the location of this rat" "en"])
(obo/RO_0001015 obo/IAO_0000115 ["a relation between two independent continuants, the location and the target, in which the target is entirely within the location" "en"])
(obo/RO_0001015 obo/IAO_0000116 ["Most location relations will only hold at certain times, but this is difficult to specify in OWL. See https://code.google.com/p/obo-relations/wiki/ROAndTime" "en"])
(obo/RO_0001015 obo/IAO_0000118 ["location_of" "en"])
(obo/RO_0001015 obo/RO_0001900 obo/RO_0001901)
(obo/RO_0001015 rdfs/label ["location of" "en"])
;; 
;; http://purl.obolibrary.org/obo/RO_0001025
(obo/RO_0001025 rdf/type owl/ObjectProperty)
(obo/RO_0001025 owl/equivalentProperty bnode/ccp-extensions_genid70)
(bnode/ccp-extensions_genid70 owl/inverseOf obo/RO_0001015)
(obo/RO_0001015 owl/equivalentProperty bnode/ccp-extensions_genid447)
(bnode/ccp-extensions_genid447 owl/inverseOf obo/RO_0001025)
(obo/RO_0001025 rdfs/subPropertyOf owl/topObjectProperty)
(obo/RO_0001025 owl/inverseOf bnode/ccp-extensions_genid448)
(bnode/ccp-extensions_genid448 owl/inverseOf obo/RO_0001025)
(obo/RO_0001025 rdf/type owl/TransitiveProperty)
(obo/RO_0001025 obo/IAO_0000111 ["located in" "en"])
(obo/RO_0001025 obo/IAO_0000112 ["my brain is located in my head" "en"])
(obo/RO_0001025 obo/IAO_0000112 ["this rat is located in this cage" "en"])
(obo/RO_0001025 obo/IAO_0000115 ["a relation between two independent continuants, the target and the location, in which the target is entirely within the location" "en"])
(obo/RO_0001025 obo/IAO_0000116 ["Location as a relation between instances: The primitive instance-level relation c located_in r at t reflects the fact that each continuant is at any given time associated with exactly one spatial region, namely its exact location. Following we can use this relation to define a further instance-level location relation - not between a continuant and the region which it exactly occupies, but rather between one continuant and another. c is located in c1, in this sense, whenever the spatial region occupied by c is part_of the spatial region occupied by c1.    Note that this relation comprehends both the relation of exact location between one continuant and another which obtains when r and r1 are identical (for example, when a portion of fluid exactly fills a cavity), as well as those sorts of inexact location relations which obtain, for example, between brain and head or between ovum and uterus" "en"])
(obo/RO_0001025 obo/IAO_0000116 ["Most location relations will only hold at certain times, but this is difficult to specify in OWL. See https://code.google.com/p/obo-relations/wiki/ROAndTime" "en"])
(obo/RO_0001025 obo/IAO_0000118 ["located_in" "en"])
(obo/RO_0001025 obo/RO_0001900 obo/RO_0001901)
(obo/RO_0001025 dc/source ["http://www.obofoundry.org/ro/;;OBO_REL:located_in"])
(obo/RO_0001025 rdfs/label ["located in" "en"])
;; 
;; http://purl.obolibrary.org/obo/RO_0002000
(obo/RO_0002000 rdf/type owl/ObjectProperty)
(obo/RO_0002000 owl/equivalentProperty bnode/ccp-extensions_genid71)
(bnode/ccp-extensions_genid71 owl/inverseOf obo/RO_0002002)
(obo/RO_0002002 owl/equivalentProperty bnode/ccp-extensions_genid450)
(bnode/ccp-extensions_genid450 owl/inverseOf obo/RO_0002000)
(obo/RO_0002000 rdfs/subPropertyOf owl/topObjectProperty)
(obo/RO_0002000 owl/inverseOf obo/RO_0002002)
(obo/RO_0002000 owl/inverseOf bnode/ccp-extensions_genid449)
(bnode/ccp-extensions_genid449 owl/inverseOf obo/RO_0002000)
(obo/RO_0002000 rdf/type owl/AsymmetricProperty)
(obo/RO_0002000 rdf/type owl/IrreflexiveProperty)
(obo/RO_0002000 obo/IAO_0000112 ["the surface of my skin is a 2D boundary of my body" "en"])
(obo/RO_0002000 obo/IAO_0000115 ["a relation between a 2D immaterial entity (the boundary) and a material entity, in which the boundary delimits the material entity" "en"])
(obo/RO_0002000 obo/IAO_0000116 ["A 2D boundary may have holes and gaps, but it must be a single connected entity, not an aggregate of several disconnected parts." "en"])
(obo/RO_0002000 obo/IAO_0000116 ["Although the boundary is two-dimensional, it exists in three-dimensional space and thus has a 3D shape." "en"])
(obo/RO_0002000 obo/IAO_0000118 ["2D_boundary_of" "en"])
(obo/RO_0002000 obo/IAO_0000118 ["boundary of" "en"])
(obo/RO_0002000 obo/IAO_0000118 ["is 2D boundary of" "en"])
(obo/RO_0002000 obo/IAO_0000118 ["is boundary of" "en"])
(obo/RO_0002000 obo/RO_0001900 obo/RO_0001901)
(obo/RO_0002000 rdfs/label ["2D boundary of" "en"])
;; 
;; http://purl.obolibrary.org/obo/RO_0002002
(obo/RO_0002002 rdf/type owl/ObjectProperty)
(obo/RO_0002002 owl/equivalentProperty bnode/ccp-extensions_genid72)
(bnode/ccp-extensions_genid72 owl/inverseOf obo/RO_0002000)
(obo/RO_0002000 owl/equivalentProperty bnode/ccp-extensions_genid452)
(bnode/ccp-extensions_genid452 owl/inverseOf obo/RO_0002002)
(obo/RO_0002002 rdfs/subPropertyOf owl/topObjectProperty)
(obo/RO_0002002 owl/inverseOf bnode/ccp-extensions_genid451)
(bnode/ccp-extensions_genid451 owl/inverseOf obo/RO_0002002)
(obo/RO_0002002 rdf/type owl/AsymmetricProperty)
(obo/RO_0002002 rdf/type owl/IrreflexiveProperty)
(obo/RO_0002002 rdfs/domain obo/BFO_0000040)
(obo/RO_0002002 rdfs/range obo/BFO_0000141)
(obo/RO_0002002 obo/IAO_0000112 ["my body has 2D boundary the surface of my skin" "en"])
(obo/RO_0002002 obo/IAO_0000115 ["a relation between a material entity and a 2D immaterial entity (the boundary), in which the boundary delimits the material entity" "en"])
(obo/RO_0002002 obo/IAO_0000116 ["A 2D boundary may have holes and gaps, but it must be a single connected entity, not an aggregate of several disconnected parts." "en"])
(obo/RO_0002002 obo/IAO_0000116 ["Although the boundary is two-dimensional, it exists in three-dimensional space and thus has a 3D shape." "en"])
(obo/RO_0002002 obo/IAO_0000118 ["has boundary" "en"])
(obo/RO_0002002 obo/IAO_0000118 ["has_2D_boundary" "en"])
(obo/RO_0002002 obo/RO_0001900 obo/RO_0001901)
(obo/RO_0002002 rdfs/label ["has 2D boundary" "en"])
;; 
;; http://purl.obolibrary.org/obo/RO_0002350
(obo/RO_0002350 rdf/type owl/ObjectProperty)
(obo/RO_0002350 owl/equivalentProperty bnode/ccp-extensions_genid73)
(bnode/ccp-extensions_genid73 owl/inverseOf obo/RO_0002351)
(obo/RO_0002351 owl/equivalentProperty bnode/ccp-extensions_genid453)
(bnode/ccp-extensions_genid453 owl/inverseOf obo/RO_0002350)
(obo/RO_0002350 rdfs/subPropertyOf obo/BFO_0000050)
(obo/RO_0002350 rdfs/subPropertyOf bnode/ccp-extensions_genid75)
(bnode/ccp-extensions_genid75 owl/inverseOf obo/BFO_0000051)
(obo/RO_0002350 owl/inverseOf obo/RO_0002351)
(obo/RO_0002350 owl/inverseOf bnode/ccp-extensions_genid454)
(bnode/ccp-extensions_genid454 owl/inverseOf obo/RO_0002350)
(obo/RO_0002350 rdf/type owl/IrreflexiveProperty)
(obo/RO_0002350 obo/IAO_0000112 ["An organism that is a member of a population of organisms"])
(obo/RO_0002350 obo/IAO_0000115 ["is member of is a mereological relation between a item and a collection."])
(obo/RO_0002350 obo/IAO_0000118 ["is member of"])
(obo/RO_0002350 obo/IAO_0000118 ["member part of"])
(obo/RO_0002350 obo/IAO_0000119 ["SIO"])
(obo/RO_0002350 obo/RO_0001900 obo/RO_0001901)
(obo/RO_0002350 rdfs/label ["member of" "en"])
;; 
;; http://purl.obolibrary.org/obo/RO_0002351
(obo/RO_0002351 rdf/type owl/ObjectProperty)
(obo/RO_0002351 owl/equivalentProperty bnode/ccp-extensions_genid74)
(bnode/ccp-extensions_genid74 owl/inverseOf obo/RO_0002350)
(obo/RO_0002350 owl/equivalentProperty bnode/ccp-extensions_genid456)
(bnode/ccp-extensions_genid456 owl/inverseOf obo/RO_0002351)
(obo/RO_0002351 rdfs/subPropertyOf obo/BFO_0000051)
(obo/RO_0002351 rdfs/subPropertyOf bnode/ccp-extensions_genid76)
(bnode/ccp-extensions_genid76 owl/inverseOf obo/BFO_0000050)
(obo/RO_0002351 owl/inverseOf bnode/ccp-extensions_genid455)
(bnode/ccp-extensions_genid455 owl/inverseOf obo/RO_0002351)
(obo/RO_0002351 rdf/type owl/IrreflexiveProperty)
(obo/RO_0002351 obo/IAO_0000115 ["has member is a mereological relation between a collection and an item."])
(obo/RO_0002351 obo/IAO_0000119 ["SIO"])
(obo/RO_0002351 obo/RO_0001900 obo/RO_0001901)
(obo/RO_0002351 rdfs/label ["has member" "en"])
;; 
;; http://www.geneontology.org/formats/oboInOwl;;ObsoleteProperty
(oboInOwl/ObsoleteProperty rdf/type owl/ObjectProperty)
(oboInOwl/ObsoleteProperty rdfs/subPropertyOf owl/topObjectProperty)
(oboInOwl/ObsoleteProperty owl/inverseOf bnode/ccp-extensions_genid77)
(bnode/ccp-extensions_genid77 owl/inverseOf oboInOwl/ObsoleteProperty)
;; 
;; 
;; 
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; ;;
;; ;;    Data properties
;; ;;
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 
;; 
;; http://purl.obolibrary.org/obo/IAO_0000004
(obo/IAO_0000004 rdf/type owl/DatatypeProperty)
(obo/IAO_0000004 rdfs/subPropertyOf owl/topDataProperty)
(obo/IAO_0000004 rdf/type owl/FunctionalProperty)
(obo/IAO_0000004 rdfs/domain obo/IAO_0000032)
(obo/IAO_0000004 rdfs/range xsd/double)
(obo/IAO_0000004 rdfs/label ["has measurement value" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000404
(obo/IAO_0000404 rdf/type owl/DatatypeProperty)
(obo/IAO_0000404 rdfs/subPropertyOf owl/topDataProperty)
(obo/IAO_0000404 rdf/type owl/FunctionalProperty)
(obo/IAO_0000404 rdfs/domain obo/IAO_0000400)
(obo/IAO_0000404 rdfs/range xsd/float)
(obo/IAO_0000404 rdfs/label ["has x coordinate value" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000405
(obo/IAO_0000405 rdf/type owl/DatatypeProperty)
(obo/IAO_0000405 rdfs/subPropertyOf owl/topDataProperty)
(obo/IAO_0000405 rdf/type owl/FunctionalProperty)
(obo/IAO_0000405 rdfs/domain obo/IAO_0000400)
(obo/IAO_0000405 rdfs/range xsd/float)
(obo/IAO_0000405 rdfs/label ["has z coordinate value" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000406
(obo/IAO_0000406 rdf/type owl/DatatypeProperty)
(obo/IAO_0000406 rdfs/subPropertyOf owl/topDataProperty)
(obo/IAO_0000406 rdf/type owl/FunctionalProperty)
(obo/IAO_0000406 rdfs/domain obo/IAO_0000400)
(obo/IAO_0000406 rdfs/range xsd/float)
(obo/IAO_0000406 rdfs/label ["has y coordinate value" "en"])
;; 
;; 
;; 
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; ;;
;; ;;    Classes
;; ;;
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000000
(ccp/IAO_EXT_0000000 rdf/type owl/Class)
(ccp/IAO_EXT_0000000 rdfs/subClassOf obo/IAO_0000027)
(ccp/IAO_EXT_0000000 obo/IAO_0000115 ["A data item that is an aggregate of other data items that are records and/or record fields. The  aggregated information contained in the subsumed records and/or record fields typically embodies knowledge about a single entity or concept, e.g. the information contained in a database record." "en"])
(ccp/IAO_EXT_0000000 obo/IAO_0000118 ["database record" "en"])
(ccp/IAO_EXT_0000000 rdfs/label ["record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000001
(ccp/IAO_EXT_0000001 rdf/type owl/Class)
(ccp/IAO_EXT_0000001 rdfs/subClassOf obo/IAO_0000027)
(ccp/IAO_EXT_0000001 obo/IAO_0000115 ["A data item that typically contains a single type data relevant to an entity or concept represented by the subsuming record. Record fields are analagous to columns in a database table for example."])
(ccp/IAO_EXT_0000001 obo/IAO_0000118 ["database field"])
(ccp/IAO_EXT_0000001 obo/IAO_0000118 ["database record field"])
(ccp/IAO_EXT_0000001 rdfs/label ["record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000006
(ccp/IAO_EXT_0000006 rdf/type owl/Class)
(ccp/IAO_EXT_0000006 rdfs/subClassOf ccp/IAO_EXT_0000000)
(ccp/IAO_EXT_0000006 rdfs/label ["GOA GAF record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000007
(ccp/IAO_EXT_0000007 rdf/type owl/Class)
(ccp/IAO_EXT_0000007 rdfs/subClassOf ccp/IAO_EXT_0000006)
(ccp/IAO_EXT_0000007 obo/IAO_0000119 ["http://www.geneontology.org/page/go-annotation-file-format-20" "en"])
(ccp/IAO_EXT_0000007 rdfs/label ["GOA GAF v2.0 Annotation record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000009
(ccp/IAO_EXT_0000009 rdf/type owl/Class)
(ccp/IAO_EXT_0000009 rdfs/subClassOf ccp/IAO_EXT_0001601)
(ccp/IAO_EXT_0000009 obo/IAO_0000115 ["The database name field refers to the database from which the identifier in the database object identifier field is drawn. This is not necessarily the group submitting the file. If a UniProtKB ID is the database object identifiers, then the database name should be UniProtKB.\nThe value of the database name field must be one of the values from the set of GO database cross-references [http://amigo.geneontology.org/xrefs]." "en"])
(ccp/IAO_EXT_0000009 obo/IAO_0000119 ["http://www.geneontology.org/page/go-annotation-file-format-20" "en"])
(ccp/IAO_EXT_0000009 rdfs/label ["GOA GAF v2.0 Annotation record - database designation field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000010
(ccp/IAO_EXT_0000010 rdf/type owl/Class)
(ccp/IAO_EXT_0000010 rdfs/subClassOf ccp/IAO_EXT_0001601)
(ccp/IAO_EXT_0000010 obo/IAO_0000115 ["The database object identifier field must reference a unique identifier from the database in database name field for the item being annotated. In GAF 2.0 format, the identifier must reference a top-level primary gene or gene product identifier: either a gene, or a protein that has a 1:1 correspondence to a gene. Identifiers referring to particular protein isoforms or post-translationally cleaved or modified proteins are not legal values in this field.\nThe database object identifier is the identifier for the database object, which may or may not correspond exactly to what is described in a paper. For example, a paper describing a protein may support annotations to the gene encoding the protein (gene ID in DB object ID field) or annotations to a protein object (protein ID in DB object ID field)." "en"])
(ccp/IAO_EXT_0000010 obo/IAO_0000119 ["http://www.geneontology.org/page/go-annotation-file-format-20" "en"])
(ccp/IAO_EXT_0000010 rdfs/label ["GOA GAF v2.0 Annotation record - database object identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000011
(ccp/IAO_EXT_0000011 rdf/type owl/Class)
(ccp/IAO_EXT_0000011 rdfs/subClassOf ccp/IAO_EXT_0001601)
(ccp/IAO_EXT_0000011 obo/IAO_0000115 ["The database object symbol field must reference a (unique and valid) symbol to which DB object ID is matched. The symbol can be the ORF name for an otherwise unnamed gene or protein.\nIf gene products are annotated, can use gene product symbol if available, or many gene product annotation entries can share a gene symbol. The DB Object Symbol field should be a symbol that means something to a biologist wherever possible (a gene symbol, for example). It is not an ID or an accession number (the DB object ID field provides the unique identifier), although IDs can be used as a DB object symbol if there is no more biologically meaningful symbol available (e.g., when an unnamed gene is annotated)." "en"])
(ccp/IAO_EXT_0000011 obo/IAO_0000119 ["http://www.geneontology.org/page/go-annotation-file-format-20" "en"])
(ccp/IAO_EXT_0000011 rdfs/label ["GOA GAF v2.0 Annotation record - database object symbol field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000012
(ccp/IAO_EXT_0000012 rdf/type owl/Class)
(ccp/IAO_EXT_0000012 rdfs/subClassOf obo/IAO_0000100)
(ccp/IAO_EXT_0000012 obo/IAO_0000115 ["A data item that is an aggregate of other data items that are records." "en"])
(ccp/IAO_EXT_0000012 rdfs/label ["record set" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000013
(ccp/IAO_EXT_0000013 rdf/type owl/Class)
(ccp/IAO_EXT_0000013 rdfs/subClassOf ccp/IAO_EXT_0001601)
(ccp/IAO_EXT_0000013 obo/IAO_0000115 ["The qualifier field references flags that modify the interpretation of an annotation using \none (or more) of NOT, contributes_to, colocalizes_with\nSee also the documentation on qualifiers in the GO annotation guide [http://geneontology.org/GO.annotation.conventions.shtml;;qual]." "en"])
(ccp/IAO_EXT_0000013 obo/IAO_0000119 ["http://www.geneontology.org/page/go-annotation-file-format-20" "en"])
(ccp/IAO_EXT_0000013 rdfs/label ["GOA GAF v2.0 Annotation record - qualifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000014
(ccp/IAO_EXT_0000014 rdf/type owl/Class)
(ccp/IAO_EXT_0000014 rdfs/subClassOf ccp/IAO_EXT_0000034)
(ccp/IAO_EXT_0000014 rdfs/subClassOf ccp/IAO_EXT_0001601)
(ccp/IAO_EXT_0000014 obo/IAO_0000115 ["The ontology identifier field references the ontology identifier for the term attributed to the DB object ID." "en"])
(ccp/IAO_EXT_0000014 obo/IAO_0000119 ["http://www.geneontology.org/page/go-annotation-file-format-20" "en"])
(ccp/IAO_EXT_0000014 rdfs/label ["GOA GAF v2.0 Annotation record - ontology term identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000015
(ccp/IAO_EXT_0000015 rdf/type owl/Class)
(ccp/IAO_EXT_0000015 rdfs/subClassOf ccp/IAO_EXT_0001601)
(ccp/IAO_EXT_0000015 obo/IAO_0000115 ["The database reference field references one or more unique identifiers for a single source cited as an authority for the attribution of the GO ID to the DB object ID in this GAF record. This may be a literature reference or a database record. The syntax is DB:accession_number.\nNote that only one reference can be cited on a single line in the gene association file. If a reference has identifiers in more than one database, multiple identifiers for that reference can be included on a single line. For example, if the reference is a published paper that has a PubMed ID, we strongly recommend that the PubMed ID be included, as well as an identifier within a model organism database. Note that if the model organism database has an identifier for the reference, that identifier should always be included, even if a PubMed ID is also used." "en"])
(ccp/IAO_EXT_0000015 obo/IAO_0000119 ["http://www.geneontology.org/page/go-annotation-file-format-20" "en"])
(ccp/IAO_EXT_0000015 rdfs/label ["GOA GAF v2.0 Annotation record - database reference accession identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000016
(ccp/IAO_EXT_0000016 rdf/type owl/Class)
(ccp/IAO_EXT_0000016 rdfs/subClassOf ccp/IAO_EXT_0001601)
(ccp/IAO_EXT_0000016 obo/IAO_0000115 ["The evidence code field references an evidence code detailing the evidence used to assert the ontology concept to gene identifier association. See the GO evidence code guide for the list of valid evidence codes for GO annotations [http://geneontology.org/page/guide-go-evidence-codes]." "en"])
(ccp/IAO_EXT_0000016 obo/IAO_0000119 ["http://www.geneontology.org/page/go-annotation-file-format-20" "en"])
(ccp/IAO_EXT_0000016 rdfs/label ["GOA GAF v2.0 Annotation record - evidence code field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000017
(ccp/IAO_EXT_0000017 rdf/type owl/Class)
(ccp/IAO_EXT_0000017 rdfs/subClassOf ccp/IAO_EXT_0001601)
(ccp/IAO_EXT_0000017 obo/IAO_0000115 ["The annotation with (or) from field is also referred to as with, from or the with/from column\nand references one of:\nDB:gene_symbol\nDB:gene_symbol[allele_symbol]\nDB:gene_id\nDB:protein_name\nDB:sequence_id\nGO:GO_id\nCHEBI:CHEBI_id\nthis field is not mandatory overall, but is required for some evidence codes (see below and the evidence code documentation for details); cardinality 0, 1, 1; for cardinality 1 use a pipe to separate entries (e.g. CGSC:pabA|CGSC:pabB)\nNote: This field is used to hold an additional identifier for annotations using certain evidence codes (IC, IEA, IGI, IPI, ISS). For example, it can identify another gene product to which the annotated gene product is similar (ISS) or interacts with (IPI). More information on the meaning of with or from column entries is available in the evidence code documentation entries for the relevant codes.\nCardinality = 0 is not recommended, but is permitted because cases can be found in literature where no database identifier can be found (e.g. physical interaction or sequence similarity to a protein, but no ID provided). Cardinality = 0 is not allowed for ISS annotations made after October 1, 2006. Annotations where evidence is IGI, IPI, or ISS and with cardinality = 0 should link to an explanation of why there is no entry in with. Cardinality may be 1 for any of the evidence codes that use with; for IPI and IGI cardinality 1 has a special meaning (see evidence documentation for more information). For cardinality 1 use a pipe to separate entries (e.g. FB:FBgn1111111|FB:FBgn2222222).\nNote that a gene ID may be used in the with column for a IPI annotation, or for an ISS annotation based on amino acid sequence or protein structure similarity, if the database does not have identifiers for individual gene products. A gene ID may also be used if the cited reference provides enough information to determine which gene ID should be used, but not enough to establish which protein ID is correct.\n'GO:GO_id' is used only when the evidence code is IC, and refers to the GO term(s) used as the basis of a curator inference. In these cases the entry in the 'DB:Reference' column will be that used to assign the GO term(s) from which the inference is made. This field is mandatory for evidence code IC.\nThe ID is usually an identifier for an individual entry in a database (such as a sequence ID, gene ID, GO ID, etc.). Identifiers from the Center for Biological Sequence Analysis (CBS), however, represent tools used to find homology or sequence similarity; these identifiers can be used in the with column for ISS annotations.\nThe with column may not be used with the evidence codes IDA, TAS, NAS, or ND." "en"])
(ccp/IAO_EXT_0000017 obo/IAO_0000119 ["http://www.geneontology.org/page/go-annotation-file-format-20" "en"])
(ccp/IAO_EXT_0000017 rdfs/label ["GOA GAF v2.0 Annotation record - with (or) from field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000018
(ccp/IAO_EXT_0000018 rdf/type owl/Class)
(ccp/IAO_EXT_0000018 rdfs/subClassOf ccp/IAO_EXT_0001601)
(ccp/IAO_EXT_0000018 obo/IAO_0000115 ["The annotation aspect field refers to the namespace or ontology to which the GO ID (ontology identifier) belongs; one of P (biological process), F (molecular function) or C (cellular component)." "en"])
(ccp/IAO_EXT_0000018 obo/IAO_0000119 ["http://www.geneontology.org/page/go-annotation-file-format-20" "en"])
(ccp/IAO_EXT_0000018 rdfs/label ["GOA GAF v2.0 Annotation record - aspect field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000019
(ccp/IAO_EXT_0000019 rdf/type owl/Class)
(ccp/IAO_EXT_0000019 rdfs/subClassOf ccp/IAO_EXT_0000197)
(ccp/IAO_EXT_0000019 rdfs/label ["GOA GAF record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000020
(ccp/IAO_EXT_0000020 rdf/type owl/Class)
(ccp/IAO_EXT_0000020 rdfs/subClassOf ccp/IAO_EXT_0000001)
(ccp/IAO_EXT_0000020 rdfs/label ["data source identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000021
(ccp/IAO_EXT_0000021 rdf/type owl/Class)
(ccp/IAO_EXT_0000021 rdfs/subClassOf ccp/IAO_EXT_0001601)
(ccp/IAO_EXT_0000021 obo/IAO_0000115 ["Gene symbol [or other text] Note that we strongly recommend that gene synonyms are included in the gene association file, as this aids the searching of GO." "en"])
(ccp/IAO_EXT_0000021 obo/IAO_0000119 ["http://www.geneontology.org/page/go-annotation-file-format-20" "en"])
(ccp/IAO_EXT_0000021 rdfs/label ["GOA GAF v2.0 Annotation record - database object synonym field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000022
(ccp/IAO_EXT_0000022 rdf/type owl/Class)
(ccp/IAO_EXT_0000022 rdfs/subClassOf ccp/IAO_EXT_0001601)
(ccp/IAO_EXT_0000022 obo/IAO_0000115 ["The database object name field references the name of gene or gene product." "en"])
(ccp/IAO_EXT_0000022 obo/IAO_0000119 ["http://www.geneontology.org/page/go-annotation-file-format-20" "en"])
(ccp/IAO_EXT_0000022 rdfs/label ["GOA GAF v2.0 Annotation record - database object name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000023
(ccp/IAO_EXT_0000023 rdf/type owl/Class)
(ccp/IAO_EXT_0000023 rdfs/subClassOf ccp/IAO_EXT_0001601)
(ccp/IAO_EXT_0000023 obo/IAO_0000115 ["A description of the type of gene product being annotated. If a gene product form ID (column 17) is supplied, the DB object type will refer to that entity; if no gene product form ID is present, it will refer to the entity that the DB object symbol (column 2) is believed to produce and which actively carries out the function or localization described. one of the following: protein_complex; protein; transcript; ncRNA; rRNA; tRNA; snRNA; snoRNA; any subtype of ncRNA in the Sequence Ontology. If the precise product type is unknown, gene_product should be used. this field is mandatory, cardinality 1\nThe object type (gene_product, transcript, protein, protein_complex, etc.) listed in the DB object type field must match the database entry identified by the gene product form ID, or, if this is absent, the expected product of the DB object ID. Note that DB object type refers to the database entry (i.e. it represents a protein, functional RNA, etc.); this column does not reflect anything about the GO term or the evidence on which the annotation is based. For example, if your database entry represents a protein-encoding gene, then protein goes in the DB object type column. The text entered in the DB object name and DB object symbol should refer to the entity in DB object ID. For example, several alternative transcripts from one gene may be annotated separately, each with the same gene ID in DB object ID, and specific gene product identifiers in gene product form ID, but list the same gene symbol in the DB object symbol column." "en"])
(ccp/IAO_EXT_0000023 obo/IAO_0000119 ["http://www.geneontology.org/page/go-annotation-file-format-20" "en"])
(ccp/IAO_EXT_0000023 rdfs/label ["GOA GAF v2.0 Annotation record - database object type field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000024
(ccp/IAO_EXT_0000024 rdf/type owl/Class)
(ccp/IAO_EXT_0000024 rdfs/subClassOf ccp/IAO_EXT_0001601)
(ccp/IAO_EXT_0000024 obo/IAO_0000115 ["taxonomic identifier(s) For cardinality 1, the ID of the species encoding the gene product. For cardinality 2, to be used only in conjunction with terms that have the biological process term multi-organism process or the cellular component term host cell as an ancestor. The first taxon ID should be that of the organism encoding the gene or gene product, and the taxon ID after the pipe should be that of the other organism in the interaction. this field is mandatory, cardinality 1, 2; for cardinality 2 use a pipe to separate entries (e.g. taxon:1|taxon:1000) See the GO annotation conventions for more information on multi-organism terms." "en"])
(ccp/IAO_EXT_0000024 obo/IAO_0000119 ["http://www.geneontology.org/page/go-annotation-file-format-20" "en"])
(ccp/IAO_EXT_0000024 rdfs/label ["GOA GAF v2.0 Annotation record - database object taxon identifier taxon field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000025
(ccp/IAO_EXT_0000025 rdf/type owl/Class)
(ccp/IAO_EXT_0000025 rdfs/subClassOf ccp/IAO_EXT_0001601)
(ccp/IAO_EXT_0000025 obo/IAO_0000115 ["Date on which the annotation was made" "en"])
(ccp/IAO_EXT_0000025 obo/IAO_0000119 ["http://www.geneontology.org/page/go-annotation-file-format-20" "en"])
(ccp/IAO_EXT_0000025 rdfs/label ["GOA GAF v2.0 Annotation record - date field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000026
(ccp/IAO_EXT_0000026 rdf/type owl/Class)
(ccp/IAO_EXT_0000026 rdfs/subClassOf ccp/IAO_EXT_0001601)
(ccp/IAO_EXT_0000026 obo/IAO_0000115 ["The database which made the annotation\none of the values from the set of GO database cross-references\nUsed for tracking the source of an individual annotation. Default value is value entered as the DB (column 1).\nValue will differ from column 1 for any annotation that is made by one database and incorporated into another." "en"])
(ccp/IAO_EXT_0000026 obo/IAO_0000119 ["http://www.geneontology.org/page/go-annotation-file-format-20" "en"])
(ccp/IAO_EXT_0000026 rdfs/label ["GOA GAF v2.0 Annotation record - assigned by field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000027
(ccp/IAO_EXT_0000027 rdf/type owl/Class)
(ccp/IAO_EXT_0000027 rdfs/subClassOf ccp/IAO_EXT_0001601)
(ccp/IAO_EXT_0000027 obo/IAO_0000115 ["one of:\nDB:gene_id\nDB:sequence_id\nCHEBI:CHEBI_id\nCell Type Ontology:CL_id\nGO:GO_id\nContains cross references to other ontologies that can be used to qualify or enhance the annotation. The cross-reference is prefaced by an appropriate GO relationship; references to multiple ontologies can be entered. For example, if a gene product is localized to the mitochondria of lymphocytes, the GO ID (column 5) would be mitochondrion ; GO:0005439, and the annotation extension column would contain a cross-reference to the term lymphocyte from the Cell Type Ontology.\nTargets of certain processes or functions can also be included in this field to indicate the gene, gene product, or chemical involved; for example, if a gene product is annotated to protein kinase activity, the annotation extension column would contain the UniProtKB protein ID for the protein phosphorylated in the reaction.\nSee the documentation on using the annotation extension column for details of practical usage; a wider discussion of the annotation extension column can be found on the GO wiki.\nthis field is optional, cardinality 0 or greater" "en"])
(ccp/IAO_EXT_0000027 obo/IAO_0000119 ["http://www.geneontology.org/page/go-annotation-file-format-20" "en"])
(ccp/IAO_EXT_0000027 rdfs/label ["GOA GAF v2.0 Annotation record - annotation extension field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000028
(ccp/IAO_EXT_0000028 rdf/type owl/Class)
(ccp/IAO_EXT_0000028 rdfs/subClassOf ccp/IAO_EXT_0001601)
(ccp/IAO_EXT_0000028 obo/IAO_0000115 ["As the DB Object ID (column 2) entry must be a canonical entity\u2014a gene OR an abstract protein that has a 1:1 correspondence to a gene\u2014this field allows the annotation of specific variants of that gene or gene product. Contents will frequently include protein sequence identifiers: for example, identifiers that specify distinct proteins produced by to differential splicing, alternative translational starts, post-translational cleavage or post-translational modification. Identifiers for functional RNAs can also be included in this column.\nThe identifier used must be a standard 2-part global identifier, e.g. UniProtKB:OK0206-2\nWhen the gene product form ID (column 17) is filled with a protein identifier, the value in DB object type (column 12) must be protein. Protein identifiers can include UniProtKB accession numbers, NCBI NP identifiers or Protein Ontology (PRO) identifiers.\nWhen the gene product form ID (column 17) is filled with a functional RNA identifier, the DB object type (column 12) must be either ncRNA, rRNA, tRNA, snRNA, or snoRNA.\nThis column may be left blank; if so, the value in DB object type (column 12) will provide a description of the expected gene product.\nMore information and examples are available from the GO wiki page on column 17.\nNote that several fields contain database cross-reference (dbxrefs) in the format dbname:dbaccession. The fields are: GO ID [column 5], where dbname is always GO; DB:Reference (column 6); With or From (column 8); and Taxon (column 13), where dbname is always taxon. For GO IDs, do not repeat the 'GO:' prefix (i.e. always use GO:0000000, not GO:GO:0000000)" "en"])
(ccp/IAO_EXT_0000028 obo/IAO_0000119 ["http://www.geneontology.org/page/go-annotation-file-format-20" "en"])
(ccp/IAO_EXT_0000028 rdfs/label ["GOA GAF v2.0 Annotation record - gene product form identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000029
(ccp/IAO_EXT_0000029 rdf/type owl/Class)
(ccp/IAO_EXT_0000029 rdfs/subClassOf ccp/IAO_EXT_0000355)
(ccp/IAO_EXT_0000029 rdfs/label ["HPO annotation record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000030
(ccp/IAO_EXT_0000030 rdf/type owl/Class)
(ccp/IAO_EXT_0000030 rdfs/subClassOf ccp/IAO_EXT_0000087)
(ccp/IAO_EXT_0000030 rdfs/label ["DNA identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000032
(ccp/IAO_EXT_0000032 rdf/type owl/Class)
(ccp/IAO_EXT_0000032 rdfs/subClassOf ccp/IAO_EXT_0000001)
(ccp/IAO_EXT_0000032 rdfs/label ["symbol field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000034
(ccp/IAO_EXT_0000034 rdf/type owl/Class)
(ccp/IAO_EXT_0000034 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000034 rdfs/subClassOf ccp/IAO_EXT_0000193)
(ccp/IAO_EXT_0000034 rdfs/label ["ontology concept identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000035
(ccp/IAO_EXT_0000035 rdf/type owl/Class)
(ccp/IAO_EXT_0000035 rdfs/subClassOf ccp/IAO_EXT_0000034)
(ccp/IAO_EXT_0000035 rdfs/label ["gene ontology identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000036
(ccp/IAO_EXT_0000036 rdf/type owl/Class)
(ccp/IAO_EXT_0000036 rdfs/subClassOf ccp/IAO_EXT_0000034)
(ccp/IAO_EXT_0000036 rdfs/label ["human phenotype ontology identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000038
(ccp/IAO_EXT_0000038 rdf/type owl/Class)
(ccp/IAO_EXT_0000038 rdfs/subClassOf ccp/IAO_EXT_0000032)
(ccp/IAO_EXT_0000038 rdfs/label ["previous symbol field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000039
(ccp/IAO_EXT_0000039 rdf/type owl/Class)
(ccp/IAO_EXT_0000039 rdfs/subClassOf ccp/IAO_EXT_0000001)
(ccp/IAO_EXT_0000039 rdfs/label ["name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000040
(ccp/IAO_EXT_0000040 rdf/type owl/Class)
(ccp/IAO_EXT_0000040 rdfs/subClassOf ccp/IAO_EXT_0000039)
(ccp/IAO_EXT_0000040 rdfs/label ["hgnc gene name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000041
(ccp/IAO_EXT_0000041 rdf/type owl/Class)
(ccp/IAO_EXT_0000041 rdfs/subClassOf ccp/IAO_EXT_0000132)
(ccp/IAO_EXT_0000041 obo/IAO_0000115 ["A unique ID provided by the HGNC."])
(ccp/IAO_EXT_0000041 obo/IAO_0000119 ["http://www.genenames.org/help/download"])
(ccp/IAO_EXT_0000041 rdfs/label ["HGNC gene record - HGNC identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000042
(ccp/IAO_EXT_0000042 rdf/type owl/Class)
(ccp/IAO_EXT_0000042 rdfs/subClassOf ccp/IAO_EXT_0000132)
(ccp/IAO_EXT_0000042 obo/IAO_0000115 ["The official gene symbol that has been approved by the HGNC and is publicly available. Symbols are approved based on specific HGNC nomenclature guidelines (http://www.genenames.org/guidelines)."])
(ccp/IAO_EXT_0000042 obo/IAO_0000119 ["http://www.genenames.org/help/download"])
(ccp/IAO_EXT_0000042 rdfs/label ["HGNC gene record - HGNC gene symbol field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000043
(ccp/IAO_EXT_0000043 rdf/type owl/Class)
(ccp/IAO_EXT_0000043 rdfs/subClassOf ccp/IAO_EXT_0000001)
(ccp/IAO_EXT_0000043 rdfs/label ["record status field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000044
(ccp/IAO_EXT_0000044 rdf/type owl/Class)
(ccp/IAO_EXT_0000044 rdfs/subClassOf ccp/IAO_EXT_0000356)
(ccp/IAO_EXT_0000044 rdfs/label ["locus type field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000045
(ccp/IAO_EXT_0000045 rdf/type owl/Class)
(ccp/IAO_EXT_0000045 rdfs/subClassOf ccp/IAO_EXT_0000356)
(ccp/IAO_EXT_0000045 rdfs/label ["locus group field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000046
(ccp/IAO_EXT_0000046 rdf/type owl/Class)
(ccp/IAO_EXT_0000046 rdfs/subClassOf ccp/IAO_EXT_0000039)
(ccp/IAO_EXT_0000046 rdfs/label ["previous name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000047
(ccp/IAO_EXT_0000047 rdf/type owl/Class)
(ccp/IAO_EXT_0000047 rdfs/subClassOf ccp/IAO_EXT_0000001)
(ccp/IAO_EXT_0000047 rdfs/label ["synonyms field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000048
(ccp/IAO_EXT_0000048 rdf/type owl/Class)
(ccp/IAO_EXT_0000048 rdfs/subClassOf ccp/IAO_EXT_0000047)
(ccp/IAO_EXT_0000048 rdfs/label ["name synonyms field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000050
(ccp/IAO_EXT_0000050 rdf/type owl/Class)
(ccp/IAO_EXT_0000050 rdfs/subClassOf ccp/IAO_EXT_0000001)
(ccp/IAO_EXT_0000050 rdfs/label ["date field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000051
(ccp/IAO_EXT_0000051 rdf/type owl/Class)
(ccp/IAO_EXT_0000051 rdfs/subClassOf ccp/IAO_EXT_0000050)
(ccp/IAO_EXT_0000051 rdfs/label ["date approved field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000052
(ccp/IAO_EXT_0000052 rdf/type owl/Class)
(ccp/IAO_EXT_0000052 rdfs/subClassOf ccp/IAO_EXT_0000050)
(ccp/IAO_EXT_0000052 obo/IAO_0000118 ["date updated field value" "en"])
(ccp/IAO_EXT_0000052 rdfs/label ["date modified field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000055
(ccp/IAO_EXT_0000055 rdf/type owl/Class)
(ccp/IAO_EXT_0000055 rdfs/subClassOf ccp/IAO_EXT_0000237)
(ccp/IAO_EXT_0000055 obo/IAO_0000115 ["Previous version of this record represented only a subset of the data in the HGNC download file. This version represents all data and includes the new \\\"gene family description\\\" column"])
(ccp/IAO_EXT_0000055 rdfs/label ["HGNC gene record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000061
(ccp/IAO_EXT_0000061 rdf/type owl/Class)
(ccp/IAO_EXT_0000061 rdfs/subClassOf ccp/IAO_EXT_0000000)
(ccp/IAO_EXT_0000061 rdfs/label ["UniProt record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000064
(ccp/IAO_EXT_0000064 rdf/type owl/Class)
(ccp/IAO_EXT_0000064 rdfs/subClassOf ccp/IAO_EXT_0000357)
(ccp/IAO_EXT_0000064 rdfs/label ["IRefWeb interaction record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000065
(ccp/IAO_EXT_0000065 rdf/type owl/Class)
(ccp/IAO_EXT_0000065 rdfs/subClassOf ccp/IAO_EXT_0000357)
(ccp/IAO_EXT_0000065 rdfs/label ["IRefWeb interactor record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000066
(ccp/IAO_EXT_0000066 rdf/type owl/Class)
(ccp/IAO_EXT_0000066 rdfs/subClassOf ccp/IAO_EXT_0000050)
(ccp/IAO_EXT_0000066 rdfs/label ["date created field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000067
(ccp/IAO_EXT_0000067 rdf/type owl/Class)
(ccp/IAO_EXT_0000067 rdfs/subClassOf ccp/IAO_EXT_0000001)
(ccp/IAO_EXT_0000067 rdfs/label ["source database field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000068
(ccp/IAO_EXT_0000068 rdf/type owl/Class)
(ccp/IAO_EXT_0000068 rdfs/subClassOf ccp/IAO_EXT_0000357)
(ccp/IAO_EXT_0000068 rdfs/label ["IRefWeb source database record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000069
(ccp/IAO_EXT_0000069 rdf/type owl/Class)
(ccp/IAO_EXT_0000069 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000069 rdfs/label ["unique identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000071
(ccp/IAO_EXT_0000071 rdf/type owl/Class)
(ccp/IAO_EXT_0000071 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000071 rdfs/label ["alternate identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000072
(ccp/IAO_EXT_0000072 rdf/type owl/Class)
(ccp/IAO_EXT_0000072 rdfs/subClassOf ccp/IAO_EXT_0000357)
(ccp/IAO_EXT_0000072 rdfs/label ["IRefWeb interactor type record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000073
(ccp/IAO_EXT_0000073 rdf/type owl/Class)
(ccp/IAO_EXT_0000073 rdfs/subClassOf ccp/IAO_EXT_0000001)
(ccp/IAO_EXT_0000073 rdfs/label ["interactor type field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000074
(ccp/IAO_EXT_0000074 rdf/type owl/Class)
(ccp/IAO_EXT_0000074 rdfs/subClassOf ccp/IAO_EXT_0000001)
(ccp/IAO_EXT_0000074 rdfs/label ["interactor type name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000075
(ccp/IAO_EXT_0000075 rdf/type owl/Class)
(ccp/IAO_EXT_0000075 rdfs/subClassOf ccp/IAO_EXT_0000001)
(ccp/IAO_EXT_0000075 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000075 rdfs/label ["interactor type identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000076
(ccp/IAO_EXT_0000076 rdf/type owl/Class)
(ccp/IAO_EXT_0000076 rdfs/subClassOf ccp/IAO_EXT_0000001)
(ccp/IAO_EXT_0000076 rdfs/label ["interactor field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000077
(ccp/IAO_EXT_0000077 rdf/type owl/Class)
(ccp/IAO_EXT_0000077 rdfs/subClassOf ccp/IAO_EXT_0000000)
(ccp/IAO_EXT_0000077 rdfs/label ["subrecord" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000078
(ccp/IAO_EXT_0000078 rdf/type owl/Class)
(ccp/IAO_EXT_0000078 rdfs/subClassOf ccp/IAO_EXT_0000357)
(ccp/IAO_EXT_0000078 rdfs/label ["IRefWeb interaction source database record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000079
(ccp/IAO_EXT_0000079 rdf/type owl/Class)
(ccp/IAO_EXT_0000079 rdfs/subClassOf ccp/IAO_EXT_0000067)
(ccp/IAO_EXT_0000079 rdfs/label ["source database identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000080
(ccp/IAO_EXT_0000080 rdf/type owl/Class)
(ccp/IAO_EXT_0000080 rdfs/subClassOf ccp/IAO_EXT_0000067)
(ccp/IAO_EXT_0000080 rdfs/label ["source database name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000081
(ccp/IAO_EXT_0000081 rdf/type owl/Class)
(ccp/IAO_EXT_0000081 rdfs/subClassOf ccp/IAO_EXT_0000342)
(ccp/IAO_EXT_0000081 obo/IAO_0000599 ["TAXONOMY_"])
(ccp/IAO_EXT_0000081 rdfs/label ["taxonomy identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000083
(ccp/IAO_EXT_0000083 rdf/type owl/Class)
(ccp/IAO_EXT_0000083 rdfs/subClassOf ccp/IAO_EXT_0000342)
(ccp/IAO_EXT_0000083 obo/IAO_0000599 ["GENE_OR_GENE_PRODUCT_OR_VARIANT_"])
(ccp/IAO_EXT_0000083 rdfs/label ["gene or gene product or variant identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000084
(ccp/IAO_EXT_0000084 rdf/type owl/Class)
(ccp/IAO_EXT_0000084 rdfs/subClassOf ccp/IAO_EXT_0000182)
(ccp/IAO_EXT_0000084 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000084 ccp/IAO_EXT_0001700 ["^\\d+$"])
(ccp/IAO_EXT_0000084 obo/IAO_0000599 ["NCBI_GENE_"])
(ccp/IAO_EXT_0000084 rdfs/label ["NCBI gene identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000085
(ccp/IAO_EXT_0000085 rdf/type owl/Class)
(ccp/IAO_EXT_0000085 rdfs/subClassOf ccp/IAO_EXT_0000000)
(ccp/IAO_EXT_0000085 rdfs/label ["NCBI record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000087
(ccp/IAO_EXT_0000087 rdf/type owl/Class)
(ccp/IAO_EXT_0000087 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000087 rdfs/label ["gene or gene product identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000088
(ccp/IAO_EXT_0000088 rdf/type owl/Class)
(ccp/IAO_EXT_0000088 rdfs/subClassOf ccp/IAO_EXT_0000307)
(ccp/IAO_EXT_0000088 rdfs/label ["ontology concept identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000089
(ccp/IAO_EXT_0000089 rdf/type owl/Class)
(ccp/IAO_EXT_0000089 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000089 rdfs/label ["taxonomy identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000090
(ccp/IAO_EXT_0000090 rdf/type owl/Class)
(ccp/IAO_EXT_0000090 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000090 rdfs/label ["database cross-reference identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000091
(ccp/IAO_EXT_0000091 rdf/type owl/Class)
(ccp/IAO_EXT_0000091 rdfs/subClassOf ccp/IAO_EXT_0000001)
(ccp/IAO_EXT_0000091 rdfs/label ["description field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000092
(ccp/IAO_EXT_0000092 rdf/type owl/Class)
(ccp/IAO_EXT_0000092 rdfs/subClassOf ccp/IAO_EXT_0000044)
(ccp/IAO_EXT_0000092 rdfs/label ["gene type field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000093
(ccp/IAO_EXT_0000093 rdf/type owl/Class)
(ccp/IAO_EXT_0000093 rdfs/subClassOf ccp/IAO_EXT_0000032)
(ccp/IAO_EXT_0000093 rdfs/label ["symbol from nomenclature authority field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000094
(ccp/IAO_EXT_0000094 rdf/type owl/Class)
(ccp/IAO_EXT_0000094 rdfs/subClassOf ccp/IAO_EXT_0000039)
(ccp/IAO_EXT_0000094 rdfs/label ["full name from nomenclature authority field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000095
(ccp/IAO_EXT_0000095 rdf/type owl/Class)
(ccp/IAO_EXT_0000095 rdfs/subClassOf ccp/IAO_EXT_0000001)
(ccp/IAO_EXT_0000095 rdfs/label ["status field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000096
(ccp/IAO_EXT_0000096 rdf/type owl/Class)
(ccp/IAO_EXT_0000096 rdfs/subClassOf ccp/IAO_EXT_0000095)
(ccp/IAO_EXT_0000096 rdfs/label ["nomenclature authority status" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000097
(ccp/IAO_EXT_0000097 rdf/type owl/Class)
(ccp/IAO_EXT_0000097 rdfs/subClassOf ccp/IAO_EXT_0000001)
(ccp/IAO_EXT_0000097 rdfs/label ["other field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000098
(ccp/IAO_EXT_0000098 rdf/type owl/Class)
(ccp/IAO_EXT_0000098 rdfs/subClassOf ccp/IAO_EXT_0000097)
(ccp/IAO_EXT_0000098 rdfs/label ["other designations field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000100
(ccp/IAO_EXT_0000100 rdf/type owl/Class)
(ccp/IAO_EXT_0000100 rdfs/subClassOf ccp/IAO_EXT_0000356)
(ccp/IAO_EXT_0000100 rdfs/label ["locus tag field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000101
(ccp/IAO_EXT_0000101 rdf/type owl/Class)
(ccp/IAO_EXT_0000101 rdfs/subClassOf ccp/IAO_EXT_0000001)
(ccp/IAO_EXT_0000101 rdfs/label ["map location field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000102
(ccp/IAO_EXT_0000102 rdf/type owl/Class)
(ccp/IAO_EXT_0000102 rdfs/subClassOf ccp/IAO_EXT_0000088)
(ccp/IAO_EXT_0000102 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000102 ccp/IAO_EXT_0001700 ["GO:\\d+"])
(ccp/IAO_EXT_0000102 obo/IAO_0000599 ["GO_"])
(ccp/IAO_EXT_0000102 rdfs/label ["Gene Ontology concept identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000103
(ccp/IAO_EXT_0000103 rdf/type owl/Class)
(ccp/IAO_EXT_0000103 rdfs/subClassOf ccp/IAO_EXT_0000102)
(ccp/IAO_EXT_0000103 ccp/IAO_EXT_0001700 ["GO:\\d+"])
(ccp/IAO_EXT_0000103 obo/IAO_0000599 ["BP_"])
(ccp/IAO_EXT_0000103 rdfs/label ["GO biological process concept identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000104
(ccp/IAO_EXT_0000104 rdf/type owl/Class)
(ccp/IAO_EXT_0000104 rdfs/subClassOf obo/IAO_0000578)
(ccp/IAO_EXT_0000104 rdfs/label ["repaired identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000110
(ccp/IAO_EXT_0000110 rdf/type owl/Class)
(ccp/IAO_EXT_0000110 rdfs/subClassOf obo/IAO_0000578)
(ccp/IAO_EXT_0000110 rdfs/label ["invalid identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000111
(ccp/IAO_EXT_0000111 rdf/type owl/Class)
(ccp/IAO_EXT_0000111 rdfs/subClassOf obo/IAO_0000578)
(ccp/IAO_EXT_0000111 rdfs/label ["identifier of unknown origin" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000112
(ccp/IAO_EXT_0000112 rdf/type owl/Class)
(ccp/IAO_EXT_0000112 rdfs/subClassOf ccp/IAO_EXT_0000088)
(ccp/IAO_EXT_0000112 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000112 obo/IAO_0000599 ["PR_"])
(ccp/IAO_EXT_0000112 rdfs/label ["Protein Ontology concept identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000113
(ccp/IAO_EXT_0000113 rdf/type owl/Class)
(ccp/IAO_EXT_0000113 rdfs/subClassOf ccp/IAO_EXT_0000001)
(ccp/IAO_EXT_0000113 rdfs/label ["ontology concept mapping type" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000114
(ccp/IAO_EXT_0000114 rdf/type owl/Class)
(ccp/IAO_EXT_0000114 rdfs/subClassOf ccp/IAO_EXT_0000000)
(ccp/IAO_EXT_0000114 rdfs/label ["identifier mapping record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000116
(ccp/IAO_EXT_0000116 rdf/type owl/Class)
(ccp/IAO_EXT_0000116 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000116 rdfs/label ["mapped data source identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000132
(ccp/IAO_EXT_0000132 rdf/type owl/Class)
(ccp/IAO_EXT_0000132 rdfs/subClassOf ccp/IAO_EXT_0000358)
(ccp/IAO_EXT_0000132 rdfs/label ["HGNC gene record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000133
(ccp/IAO_EXT_0000133 rdf/type owl/Class)
(ccp/IAO_EXT_0000133 rdfs/subClassOf ccp/IAO_EXT_0000132)
(ccp/IAO_EXT_0000133 obo/IAO_0000115 ["The official gene name that has been approved by the HGNC and is publicly available. Names are approved based on specific HGNC nomenclature guidelines (http://www.genenames.org/guidelines)."])
(ccp/IAO_EXT_0000133 obo/IAO_0000119 ["http://www.genenames.org/help/download"])
(ccp/IAO_EXT_0000133 rdfs/label ["HGNC gene record - HGNC gene name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000134
(ccp/IAO_EXT_0000134 rdf/type owl/Class)
(ccp/IAO_EXT_0000134 rdfs/subClassOf ccp/IAO_EXT_0000132)
(ccp/IAO_EXT_0000134 obo/IAO_0000115 ["Indicates whether the gene is classified as:\n\nApproved - these genes have HGNC-approved gene symbols\nEntry withdrawn - these previously approved genes are no longer thought to exist\nSymbol withdrawn - a previously approved record that has since been merged into a another record"])
(ccp/IAO_EXT_0000134 obo/IAO_0000119 ["http://www.genenames.org/help/download"])
(ccp/IAO_EXT_0000134 rdfs/label ["HGNC gene record - status field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000135
(ccp/IAO_EXT_0000135 rdf/type owl/Class)
(ccp/IAO_EXT_0000135 rdfs/subClassOf ccp/IAO_EXT_0000132)
(ccp/IAO_EXT_0000135 obo/IAO_0000115 ["Specifies the type of locus described by the given entry:\n\ncomplex locus constituent - transcriptional unit that is part of a named complex locus\nendogenous retrovirus - integrated retroviral elements that are transmitted through the germline (SO:0000100)\nfragile site - a heritable locus on a chromosome that is prone to DNA breakage\ngene with protein product - protein-coding genes (the protein may be predicted and of unknown function) (SO:0001217)\nimmunoglobulin gene - gene segments that undergo somatic recombination to form heavy or light chain immunoglobulin genes (SO:0000460)\nimmunoglobulin pseudogene - immunoglobulin gene segments that are inactivated due to frameshift mutations and/or stop codons in the open reading frame\nphenotype only - mapped phenotypes (SO:0001500)\nprotocadherin - gene segments that constitute the three clustered protocadherins (alpha, beta and gamma)\npseudogene - genomic DNA sequences that are similar to protein-coding genes but do not encode a functional protein (SO:0000336)\nreadthrough - a naturally occurring transcript containing coding sequence from two or more genes that can also be transcribed individually\nregion - extents of genomic sequence that contain one or more genes, also applied to non-gene areas that do not fall into other types\nRNA, cluster - region containing a cluster of small non-coding RNA genes\nRNA, long non-coding - non-protein coding genes that encode long non-coding RNAs (lncRNAs); these are at least 200 nt and are represented by a processed trancript and/or at least 3 ESTs\nRNA, micro - non-protein coding genes that encode microRNAs (miRNAs) (SO:0001265)\nRNA, misc - non-protein coding genes that encode miscellaneous types of small ncRNAs\nRNA, ribosomal - non-protein coding genes that encode ribosomal RNAs (rRNAs) (SO:0001637)\nRNA, small nuclear - non-protein coding genes that encode small nuclear RNAs (snRNAs) (SO:0001268)\nRNA, small nucleolar - non-protein coding genes that encode small nucleolar RNAs (snoRNAs) containing C/D or H/ACA box domains (SO:0001267)\nRNA, small cytoplasmic - non-protein coding genes that encode small cytoplasmic RNAs (scRNAs) (SO:0001266)\nRNA, transfer - non-protein coding genes that encode transfer RNAs (tRNAs) (SO:0001272)\nRNA, vault - non-protein coding genes that encode vault RNAs (SO:0000404)\nRNA, Y - non-protein coding genes that encode Y RNAs (SO:0000405)\nT cell receptor gene - gene segments that undergo somatic recombination to form either alpha, beta, gamma or delta chain T cell receptor genes (SO:0000460)\nT cell receptor pseudogene - T cell receptor gene segments that are inactivated due to frameshift mutations and/or stop codons in the open reading frame\ntransposable element - a segment of repetitive DNA that can move, or retrotranspose, to new sites within the genome (SO:0000101)\nunknown - entries where the locus type is currently unknown\nvirus integration site - target sequence for the integration of viral DNA into the genome"])
(ccp/IAO_EXT_0000135 obo/IAO_0000119 ["http://www.genenames.org/help/download"])
(ccp/IAO_EXT_0000135 rdfs/label ["HGNC gene record - locus type field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000136
(ccp/IAO_EXT_0000136 rdf/type owl/Class)
(ccp/IAO_EXT_0000136 rdfs/subClassOf ccp/IAO_EXT_0000132)
(ccp/IAO_EXT_0000136 obo/IAO_0000115 ["Groups locus types together into related sets. Below is a list of groups and the locus types within the group:\n\nprotein-coding gene - contains the \"gene with protein product\" locus type\n\nnon-coding RNA - contains the following locus types:\nRNA, Y\nRNA, cluster\nRNA, long non-coding\nRNA, micro\nRNA, misc\nRNA, ribosomal\nRNA, small cytoplasmic\nRNA, small nuclear\nRNA, small nucleolar\nRNA, transfer\nRNA, vault\n\nphenotype - contains the \"phenotype only\" locus type\n\npseudogene - contains the following locus types:\nT cell receptor pseudogene\nimmunoglobulin pseudogene\npseudogene\n\nother - contains the following types:\nT cell receptor gene\ncomplex locus constituent\nendogenous retrovirus\nfragile site\nimmunoglobulin gene\nprotocadherin\nreadthrough\nregion\ntransposable element\nunknown\nvirus integration site\n\nwithdrawn - contains the \"withdrawn\" locus type only"])
(ccp/IAO_EXT_0000136 obo/IAO_0000119 ["http://www.genenames.org/help/download"])
(ccp/IAO_EXT_0000136 rdfs/label ["HGNC gene record - locus group field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000137
(ccp/IAO_EXT_0000137 rdf/type owl/Class)
(ccp/IAO_EXT_0000137 rdfs/subClassOf ccp/IAO_EXT_0000132)
(ccp/IAO_EXT_0000137 obo/IAO_0000115 ["Symbols previously approved by the HGNC for this gene"])
(ccp/IAO_EXT_0000137 obo/IAO_0000119 ["http://www.genenames.org/help/download"])
(ccp/IAO_EXT_0000137 rdfs/label ["HGNC gene record - previous symbol field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000138
(ccp/IAO_EXT_0000138 rdf/type owl/Class)
(ccp/IAO_EXT_0000138 rdfs/subClassOf ccp/IAO_EXT_0000132)
(ccp/IAO_EXT_0000138 obo/IAO_0000115 ["Gene names previously approved by the HGNC for this gene"])
(ccp/IAO_EXT_0000138 obo/IAO_0000119 ["http://www.genenames.org/help/download"])
(ccp/IAO_EXT_0000138 rdfs/label ["HGNC gene record - previous name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000139
(ccp/IAO_EXT_0000139 rdf/type owl/Class)
(ccp/IAO_EXT_0000139 rdfs/subClassOf ccp/IAO_EXT_0000132)
(ccp/IAO_EXT_0000139 obo/IAO_0000115 ["Other symbols used to refer to this gene"])
(ccp/IAO_EXT_0000139 obo/IAO_0000119 ["http://www.genenames.org/help/download"])
(ccp/IAO_EXT_0000139 rdfs/label ["HGNC gene record - synonyms field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000140
(ccp/IAO_EXT_0000140 rdf/type owl/Class)
(ccp/IAO_EXT_0000140 rdfs/subClassOf ccp/IAO_EXT_0000132)
(ccp/IAO_EXT_0000140 obo/IAO_0000115 ["Other names used to refer to this gene"])
(ccp/IAO_EXT_0000140 obo/IAO_0000119 ["http://www.genenames.org/help/download"])
(ccp/IAO_EXT_0000140 rdfs/label ["HGNC gene record - name synonyms field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000141
(ccp/IAO_EXT_0000141 rdf/type owl/Class)
(ccp/IAO_EXT_0000141 rdfs/subClassOf ccp/IAO_EXT_0000132)
(ccp/IAO_EXT_0000141 obo/IAO_0000115 ["Indicates the location of the gene or region on the chromosome"])
(ccp/IAO_EXT_0000141 obo/IAO_0000119 ["http://www.genenames.org/help/download"])
(ccp/IAO_EXT_0000141 rdfs/label ["HGNC gene record - chromosome field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000142
(ccp/IAO_EXT_0000142 rdf/type owl/Class)
(ccp/IAO_EXT_0000142 rdfs/subClassOf ccp/IAO_EXT_0000132)
(ccp/IAO_EXT_0000142 obo/IAO_0000115 ["Date the gene symbol and name were approved by the HGNC"])
(ccp/IAO_EXT_0000142 obo/IAO_0000119 ["http://www.genenames.org/help/download"])
(ccp/IAO_EXT_0000142 rdfs/label ["HGNC gene record - date approved field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000143
(ccp/IAO_EXT_0000143 rdf/type owl/Class)
(ccp/IAO_EXT_0000143 rdfs/subClassOf ccp/IAO_EXT_0000132)
(ccp/IAO_EXT_0000143 obo/IAO_0000115 ["If applicable, the date the entry was modified by the HGNC"])
(ccp/IAO_EXT_0000143 obo/IAO_0000119 ["http://www.genenames.org/help/download"])
(ccp/IAO_EXT_0000143 rdfs/label ["HGNC gene record - date modified field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000144
(ccp/IAO_EXT_0000144 rdf/type owl/Class)
(ccp/IAO_EXT_0000144 rdfs/subClassOf ccp/IAO_EXT_0000132)
(ccp/IAO_EXT_0000144 obo/IAO_0000115 ["If applicable, the date the gene symbol was last changed by the HGNC from a previously approved symbol. Many genes receive approved symbols and names which are viewed as temporary (eg C2orf;;) or are non-ideal when considered in the light of subsequent information. In the case of individual genes a change to the name (and subsequently the symbol) is only made if the original name is seriously misleading."])
(ccp/IAO_EXT_0000144 obo/IAO_0000119 ["http://www.genenames.org/help/download"])
(ccp/IAO_EXT_0000144 rdfs/label ["HGNC gene record - date symbol changed field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000145
(ccp/IAO_EXT_0000145 rdf/type owl/Class)
(ccp/IAO_EXT_0000145 rdfs/subClassOf ccp/IAO_EXT_0000132)
(ccp/IAO_EXT_0000145 obo/IAO_0000115 ["If applicable, the date the gene name was last changed by the HGNC from a previously approved name"])
(ccp/IAO_EXT_0000145 obo/IAO_0000119 ["http://www.genenames.org/help/download"])
(ccp/IAO_EXT_0000145 rdfs/label ["HGNC gene record - date name changed field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000146
(ccp/IAO_EXT_0000146 rdf/type owl/Class)
(ccp/IAO_EXT_0000146 rdfs/subClassOf ccp/IAO_EXT_0000132)
(ccp/IAO_EXT_0000146 obo/IAO_0000115 ["Accession numbers for each entry selected by the HGNC"])
(ccp/IAO_EXT_0000146 obo/IAO_0000119 ["http://www.genenames.org/help/download"])
(ccp/IAO_EXT_0000146 rdfs/label ["HGNC gene record - accession numbers field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000147
(ccp/IAO_EXT_0000147 rdf/type owl/Class)
(ccp/IAO_EXT_0000147 rdfs/subClassOf ccp/IAO_EXT_0000132)
(ccp/IAO_EXT_0000147 obo/IAO_0000115 ["Enzyme entries have Enzyme Commission (EC) numbers associated with them that indicate the hierarchical functional classes to which they belong"])
(ccp/IAO_EXT_0000147 obo/IAO_0000119 ["http://www.genenames.org/help/download"])
(ccp/IAO_EXT_0000147 rdfs/label ["HGNC gene record - Enzyme Commission numbers field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000149
(ccp/IAO_EXT_0000149 rdf/type owl/Class)
(ccp/IAO_EXT_0000149 rdfs/subClassOf ccp/IAO_EXT_0000132)
(ccp/IAO_EXT_0000149 obo/IAO_0000115 ["This column contains a manually curated Ensembl Gene ID"])
(ccp/IAO_EXT_0000149 obo/IAO_0000119 ["http://www.genenames.org/help/download"])
(ccp/IAO_EXT_0000149 rdfs/label ["HGNC gene record - ensembl gene identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000150
(ccp/IAO_EXT_0000150 rdf/type owl/Class)
(ccp/IAO_EXT_0000150 rdfs/subClassOf ccp/IAO_EXT_0000132)
(ccp/IAO_EXT_0000150 obo/IAO_0000115 ["MGI identifier."])
(ccp/IAO_EXT_0000150 obo/IAO_0000119 ["http://www.genenames.org/help/download"])
(ccp/IAO_EXT_0000150 rdfs/label ["HGNC gene record - MGI identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000151
(ccp/IAO_EXT_0000151 rdf/type owl/Class)
(ccp/IAO_EXT_0000151 rdfs/subClassOf ccp/IAO_EXT_0000237)
(ccp/IAO_EXT_0000151 rdfs/label ["HGNC gene record component" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000152
(ccp/IAO_EXT_0000152 rdf/type owl/Class)
(ccp/IAO_EXT_0000152 rdfs/subClassOf ccp/IAO_EXT_0000151)
(ccp/IAO_EXT_0000152 rdfs/label ["HGNC Specialist Database identifier link pair record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000153
(ccp/IAO_EXT_0000153 rdf/type owl/Class)
(ccp/IAO_EXT_0000153 rdfs/subClassOf ccp/IAO_EXT_0000151)
(ccp/IAO_EXT_0000153 rdfs/label ["HGNC gene family tag description pair record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000154
(ccp/IAO_EXT_0000154 rdf/type owl/Class)
(ccp/IAO_EXT_0000154 rdfs/subClassOf ccp/IAO_EXT_0000151)
(ccp/IAO_EXT_0000154 rdfs/label ["HGNC locus specific database name link pair record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000155
(ccp/IAO_EXT_0000155 rdf/type owl/Class)
(ccp/IAO_EXT_0000155 rdfs/subClassOf ccp/IAO_EXT_0000132)
(ccp/IAO_EXT_0000155 obo/IAO_0000115 ["The Specialist Database Links column contains HTML links to the database in question. This column contains the database ID only. It is a comma delimited list with each position dedicated to a particular database:-\n\nmiRBase the microRNA database\nHORDE ID Human Olfactory Receptor Data Exploratorium\nCD Human Cell Differentiation Antigens\nRfam RNA families database of alignments and CMs\nsnoRNABase database of human H/ACA and C/D box snoRNAs\nKZNF Gene Catalog Human KZNF Gene Catalog\nIntermediate Filament DB Human Intermediate Filament Database\nIUPHAR Committee on Receptor Nomenclature and Drug Classification.(mapped)\nIMGT/GENE-DB the international ImMunoGeneTics information system for immunoglobulins (mapped)\nMEROPS the peptidase database\nCOSMIC Catalogue Of Somatic Mutations In Cancer\nOrphanet portal for rare diseases and orphan drugs\nPseudogene.org database of identified pseudogenes\npiRNABank database of piwi-interacting RNA clusters\nHomeoDB a database of homeobox gene diversity\nMamit-tRNAdb a compilation of mammalian mitochondrial tRNA genes\nlncRNAdb a database providing comprehensive annotations of eukaryotic long non-coding RNAs (lncRNAs).\nBioParadigms SLC tables provides the latest up-to-date information on the SLC families and their members.\n\nMost of these IDs have undergone manual curation, however a few are mapped from regularly updated files kindly provided by the specialist database. When we add new databases these will be appended to the end of this list"])
(ccp/IAO_EXT_0000155 obo/IAO_0000119 ["http://www.genenames.org/help/download"])
(ccp/IAO_EXT_0000155 rdfs/label ["HGNC gene record - specialist database identifier link pairings field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000156
(ccp/IAO_EXT_0000156 rdf/type owl/Class)
(ccp/IAO_EXT_0000156 rdfs/subClassOf ccp/IAO_EXT_0000132)
(ccp/IAO_EXT_0000156 obo/IAO_0000115 ["Identifier that links to published articles relevant to the entry in the NCBI's PubMed database."])
(ccp/IAO_EXT_0000156 obo/IAO_0000119 ["http://www.genenames.org/help/download"])
(ccp/IAO_EXT_0000156 rdfs/label ["HGNC gene record - PubMed identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000157
(ccp/IAO_EXT_0000157 rdf/type owl/Class)
(ccp/IAO_EXT_0000157 rdfs/subClassOf ccp/IAO_EXT_0000132)
(ccp/IAO_EXT_0000157 obo/IAO_0000115 ["The Reference Sequence (RefSeq) identifier for that entry, provided by the NCBI. As we do not aim to curate all variants of a gene only one selected RefSeq is displayed per gene report. RefSeq aims to provide a comprehensive, integrated, non-redundant set of sequences, including genomic DNA, transcript (RNA), and protein products. RefSeq identifiers are designed to provide a stable reference for gene identification and characterization, mutation analysis, expression studies, polymorphism discovery, and comparative analyses. In the HTML results page this ID links to the RefSeq page for that entry."])
(ccp/IAO_EXT_0000157 obo/IAO_0000119 ["http://www.genenames.org/help/download"])
(ccp/IAO_EXT_0000157 rdfs/label ["HGNC gene record - RefSeq identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000158
(ccp/IAO_EXT_0000158 rdf/type owl/Class)
(ccp/IAO_EXT_0000158 rdfs/subClassOf ccp/IAO_EXT_0000132)
(ccp/IAO_EXT_0000158 obo/IAO_0000115 ["ID used to designate a gene family or group the gene has been assigned to. Each gene family has an associated family ID and family name. If a particular gene is a member of more than one gene family, the IDs and the names will be shown in the same order delimited by a pipe."])
(ccp/IAO_EXT_0000158 obo/IAO_0000119 ["http://www.genenames.org/help/download"])
(ccp/IAO_EXT_0000158 rdfs/label ["HGNC gene record - gene family tag description pairings field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000159
(ccp/IAO_EXT_0000159 rdf/type owl/Class)
(ccp/IAO_EXT_0000159 rdfs/subClassOf ccp/IAO_EXT_0000132)
(ccp/IAO_EXT_0000159 obo/IAO_0000119 ["http://www.genenames.org/help/download"])
(ccp/IAO_EXT_0000159 rdfs/label ["HGNC gene record - record type field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000160
(ccp/IAO_EXT_0000160 rdf/type owl/Class)
(ccp/IAO_EXT_0000160 rdfs/subClassOf ccp/IAO_EXT_0000132)
(ccp/IAO_EXT_0000160 obo/IAO_0000119 ["http://www.genenames.org/help/download"])
(ccp/IAO_EXT_0000160 rdfs/label ["HGNC gene record - primary identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000161
(ccp/IAO_EXT_0000161 rdf/type owl/Class)
(ccp/IAO_EXT_0000161 rdfs/subClassOf ccp/IAO_EXT_0000132)
(ccp/IAO_EXT_0000161 obo/IAO_0000119 ["http://www.genenames.org/help/download"])
(ccp/IAO_EXT_0000161 rdfs/label ["HGNC gene record - secondary identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000162
(ccp/IAO_EXT_0000162 rdf/type owl/Class)
(ccp/IAO_EXT_0000162 rdfs/subClassOf ccp/IAO_EXT_0000132)
(ccp/IAO_EXT_0000162 obo/IAO_0000115 ["The Consensus CDS (CCDS) project is a collaborative effort to identify a core set of human and mouse protein coding regions that are consistently annotated and of high quality. The long term goal is to support convergence towards a standard set of gene annotations."])
(ccp/IAO_EXT_0000162 obo/IAO_0000119 ["http://www.genenames.org/help/download"])
(ccp/IAO_EXT_0000162 rdfs/label ["HGNC gene record - CCDS identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000163
(ccp/IAO_EXT_0000163 rdf/type owl/Class)
(ccp/IAO_EXT_0000163 rdfs/subClassOf ccp/IAO_EXT_0000132)
(ccp/IAO_EXT_0000163 obo/IAO_0000115 ["This contains a curated VEGA gene ID"])
(ccp/IAO_EXT_0000163 obo/IAO_0000119 ["http://www.genenames.org/help/download"])
(ccp/IAO_EXT_0000163 rdfs/label ["HGNC gene record - Vega identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000164
(ccp/IAO_EXT_0000164 rdf/type owl/Class)
(ccp/IAO_EXT_0000164 rdfs/subClassOf ccp/IAO_EXT_0000132)
(ccp/IAO_EXT_0000164 obo/IAO_0000115 ["This contains a list of links to databases or database entries pertinent to the gene"])
(ccp/IAO_EXT_0000164 obo/IAO_0000119 ["http://www.genenames.org/help/download"])
(ccp/IAO_EXT_0000164 rdfs/label ["HGNC gene record - locus specific database name link pairings field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000165
(ccp/IAO_EXT_0000165 rdf/type owl/Class)
(ccp/IAO_EXT_0000165 rdfs/subClassOf ccp/IAO_EXT_0000132)
(ccp/IAO_EXT_0000165 obo/IAO_0000115 ["Please note that mapped data are derived from external sources and as such are not subject to our strict checking and curation procedures. They should therefore be treated with some caution.\n\nEntrez Gene at the NCBI provide curated sequence and descriptive information about genetic loci including official nomenclature, synonyms, sequence accessions, phenotypes, EC numbers, MIM numbers, UniGene clusters, homology, map locations, and related web sites."])
(ccp/IAO_EXT_0000165 obo/IAO_0000119 ["http://www.genenames.org/help/download"])
(ccp/IAO_EXT_0000165 rdfs/label ["HGNC gene record - supplied Entrez gene identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000166
(ccp/IAO_EXT_0000166 rdf/type owl/Class)
(ccp/IAO_EXT_0000166 rdfs/subClassOf ccp/IAO_EXT_0000132)
(ccp/IAO_EXT_0000166 obo/IAO_0000115 ["Please note that mapped data are derived from external sources and as such are not subject to our strict checking and curation procedures. They should therefore be treated with some caution.\n\nIdentifier provided by Online Mendelian Inheritance in Man (OMIM). This database is described as a catalog of human genes and genetic disorders containing textual information and links to additional related resources."])
(ccp/IAO_EXT_0000166 obo/IAO_0000119 ["http://www.genenames.org/help/download"])
(ccp/IAO_EXT_0000166 rdfs/label ["HGNC gene record - supplied OMIM identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000167
(ccp/IAO_EXT_0000167 rdf/type owl/Class)
(ccp/IAO_EXT_0000167 rdfs/subClassOf ccp/IAO_EXT_0000132)
(ccp/IAO_EXT_0000167 obo/IAO_0000115 ["Please note that mapped data are derived from external sources and as such are not subject to our strict checking and curation procedures. They should therefore be treated with some caution.\n\nThe Reference Sequence (RefSeq) identifier for that entry, provided by the NCBI. As we do not aim to curate all variants of a gene only one mapped RefSeq is displayed per gene report. RefSeq aims to provide a comprehensive, integrated, non-redundant set of sequences, including genomic DNA, transcript (RNA), and protein products. RefSeq identifiers are designed to provide a stable reference for gene identification and characterization, mutation analysis, expression studies, polymorphism discovery, and comparative analyses."])
(ccp/IAO_EXT_0000167 obo/IAO_0000119 ["http://www.genenames.org/help/download"])
(ccp/IAO_EXT_0000167 rdfs/label ["HGNC gene record - supplied RefSeq identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000168
(ccp/IAO_EXT_0000168 rdf/type owl/Class)
(ccp/IAO_EXT_0000168 rdfs/subClassOf ccp/IAO_EXT_0000132)
(ccp/IAO_EXT_0000168 obo/IAO_0000115 ["Please note that mapped data are derived from external sources and as such are not subject to our strict checking and curation procedures. They should therefore be treated with some caution.\n\nThe UniProt identifier, provided by the EBI. The UniProt Protein Knowledgebase is described as a curated protein sequence database that provides a high level of annotation, a minimal level of redundancy and high level of integration with other databases."])
(ccp/IAO_EXT_0000168 obo/IAO_0000119 ["http://www.genenames.org/help/download"])
(ccp/IAO_EXT_0000168 rdfs/label ["HGNC gene record - supplied UniProt identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000169
(ccp/IAO_EXT_0000169 rdf/type owl/Class)
(ccp/IAO_EXT_0000169 rdfs/subClassOf ccp/IAO_EXT_0000132)
(ccp/IAO_EXT_0000169 obo/IAO_0000115 ["Please note that mapped data are derived from external sources and as such are not subject to our strict checking and curation procedures. They should therefore be treated with some caution.\n\nThe Ensembl ID is derived from the current build of the Ensembl database and provided by the Ensembl team."])
(ccp/IAO_EXT_0000169 obo/IAO_0000119 ["http://www.genenames.org/help/download"])
(ccp/IAO_EXT_0000169 rdfs/label ["HGNC gene record - supplied Ensembl identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000170
(ccp/IAO_EXT_0000170 rdf/type owl/Class)
(ccp/IAO_EXT_0000170 rdfs/subClassOf ccp/IAO_EXT_0000132)
(ccp/IAO_EXT_0000170 obo/IAO_0000115 ["Please note that mapped data are derived from external sources and as such are not subject to our strict checking and curation procedures. They should therefore be treated with some caution.\n\nThe Vega gene ID is derived from the current build of the Vega database and provided by the Vega team."])
(ccp/IAO_EXT_0000170 obo/IAO_0000119 ["http://www.genenames.org/help/download"])
(ccp/IAO_EXT_0000170 rdfs/label ["HGNC gene record - supplied Vega identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000171
(ccp/IAO_EXT_0000171 rdf/type owl/Class)
(ccp/IAO_EXT_0000171 rdfs/subClassOf ccp/IAO_EXT_0000132)
(ccp/IAO_EXT_0000171 obo/IAO_0000115 ["Please note that mapped data are derived from external sources and as such are not subject to our strict checking and curation procedures. They should therefore be treated with some caution.\n\nThe UCSC ID is derived from the current build of the UCSC database"])
(ccp/IAO_EXT_0000171 obo/IAO_0000119 ["http://www.genenames.org/help/download"])
(ccp/IAO_EXT_0000171 rdfs/label ["HGNC gene record - supplied UCSC genome browser identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000172
(ccp/IAO_EXT_0000172 rdf/type owl/Class)
(ccp/IAO_EXT_0000172 rdfs/subClassOf ccp/IAO_EXT_0000132)
(ccp/IAO_EXT_0000172 obo/IAO_0000115 ["Please note that mapped data are derived from external sources and as such are not subject to our strict checking and curation procedures. They should therefore be treated with some caution.\n\nMGI identifier."])
(ccp/IAO_EXT_0000172 obo/IAO_0000119 ["http://www.genenames.org/help/download"])
(ccp/IAO_EXT_0000172 rdfs/label ["HGNC gene record - supplied MGI identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000173
(ccp/IAO_EXT_0000173 rdf/type owl/Class)
(ccp/IAO_EXT_0000173 rdfs/subClassOf ccp/IAO_EXT_0000132)
(ccp/IAO_EXT_0000173 obo/IAO_0000115 ["Please note that mapped data are derived from external sources and as such are not subject to our strict checking and curation procedures. They should therefore be treated with some caution.\n\nRGD identifier."])
(ccp/IAO_EXT_0000173 obo/IAO_0000119 ["http://www.genenames.org/help/download"])
(ccp/IAO_EXT_0000173 rdfs/label ["HGNC gene record - supplied RGD identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000175
(ccp/IAO_EXT_0000175 rdf/type owl/Class)
(ccp/IAO_EXT_0000175 rdfs/subClassOf ccp/IAO_EXT_0000087)
(ccp/IAO_EXT_0000175 rdfs/label ["RNA identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000176
(ccp/IAO_EXT_0000176 rdf/type owl/Class)
(ccp/IAO_EXT_0000176 rdfs/subClassOf ccp/IAO_EXT_0000175)
(ccp/IAO_EXT_0000176 rdfs/label ["RNACentral identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000177
(ccp/IAO_EXT_0000177 rdf/type owl/Class)
(ccp/IAO_EXT_0000177 rdfs/subClassOf ccp/IAO_EXT_0000087)
(ccp/IAO_EXT_0000177 rdfs/label ["protein identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000178
(ccp/IAO_EXT_0000178 rdf/type owl/Class)
(ccp/IAO_EXT_0000178 rdfs/subClassOf ccp/IAO_EXT_0000177)
(ccp/IAO_EXT_0000178 rdfs/label ["UniProt identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000179
(ccp/IAO_EXT_0000179 rdf/type owl/Class)
(ccp/IAO_EXT_0000179 rdfs/subClassOf ccp/IAO_EXT_0000030)
(ccp/IAO_EXT_0000179 rdfs/label ["RGD DNA identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000180
(ccp/IAO_EXT_0000180 rdf/type owl/Class)
(ccp/IAO_EXT_0000180 rdfs/subClassOf ccp/IAO_EXT_0000030)
(ccp/IAO_EXT_0000180 rdfs/label ["MGI DNA identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000181
(ccp/IAO_EXT_0000181 rdf/type owl/Class)
(ccp/IAO_EXT_0000181 rdfs/subClassOf ccp/IAO_EXT_0000030)
(ccp/IAO_EXT_0000181 rdfs/label ["UCSC Genome Browser identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000182
(ccp/IAO_EXT_0000182 rdf/type owl/Class)
(ccp/IAO_EXT_0000182 rdfs/subClassOf ccp/IAO_EXT_0000083)
(ccp/IAO_EXT_0000182 rdfs/label ["genomic identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000183
(ccp/IAO_EXT_0000183 rdf/type owl/Class)
(ccp/IAO_EXT_0000183 rdfs/subClassOf ccp/IAO_EXT_0000083)
(ccp/IAO_EXT_0000183 rdfs/label ["gene product identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000184
(ccp/IAO_EXT_0000184 rdf/type owl/Class)
(ccp/IAO_EXT_0000184 rdfs/subClassOf ccp/IAO_EXT_0000188)
(ccp/IAO_EXT_0000184 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000184 rdfs/subClassOf ccp/IAO_EXT_0001713)
(ccp/IAO_EXT_0000184 ccp/IAO_EXT_0001700 ["[OPQ][0-9][A-Z0-9]{3}[0-9]|[A-NR-Z][0-9]([A-Z][A-Z0-9]{2}[0-9]){1,2}"])
(ccp/IAO_EXT_0000184 obo/IAO_0000599 ["UNIPROT_"])
(ccp/IAO_EXT_0000184 rdfs/label ["UniProt identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000185
(ccp/IAO_EXT_0000185 rdf/type owl/Class)
(ccp/IAO_EXT_0000185 rdfs/subClassOf ccp/IAO_EXT_0000182)
(ccp/IAO_EXT_0000185 rdfs/subClassOf ccp/IAO_EXT_0001697)
(ccp/IAO_EXT_0000185 ccp/IAO_EXT_0001700 ["^\\d+$"])
(ccp/IAO_EXT_0000185 obo/IAO_0000599 ["HGNC_"])
(ccp/IAO_EXT_0000185 rdfs/label ["HGNC gene identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000186
(ccp/IAO_EXT_0000186 rdf/type owl/Class)
(ccp/IAO_EXT_0000186 rdfs/subClassOf ccp/IAO_EXT_0000182)
(ccp/IAO_EXT_0000186 rdfs/subClassOf ccp/IAO_EXT_0001697)
(ccp/IAO_EXT_0000186 obo/IAO_0000599 ["HGNC_"])
(ccp/IAO_EXT_0000186 rdfs/label ["HGNC gene symbol identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000187
(ccp/IAO_EXT_0000187 rdf/type owl/Class)
(ccp/IAO_EXT_0000187 rdfs/subClassOf ccp/IAO_EXT_0000183)
(ccp/IAO_EXT_0000187 rdfs/label ["RNA identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000188
(ccp/IAO_EXT_0000188 rdf/type owl/Class)
(ccp/IAO_EXT_0000188 rdfs/subClassOf ccp/IAO_EXT_0000183)
(ccp/IAO_EXT_0000188 rdfs/label ["protein identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000189
(ccp/IAO_EXT_0000189 rdf/type owl/Class)
(ccp/IAO_EXT_0000189 rdfs/subClassOf ccp/IAO_EXT_0000187)
(ccp/IAO_EXT_0000189 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000189 obo/IAO_0000599 ["RNACENTRAL_"])
(ccp/IAO_EXT_0000189 rdfs/label ["RNACentral identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000190
(ccp/IAO_EXT_0000190 rdf/type owl/Class)
(ccp/IAO_EXT_0000190 rdfs/subClassOf ccp/IAO_EXT_0000088)
(ccp/IAO_EXT_0000190 rdfs/label ["ontology root concept identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000191
(ccp/IAO_EXT_0000191 rdf/type owl/Class)
(ccp/IAO_EXT_0000191 rdfs/subClassOf ccp/IAO_EXT_0000309)
(ccp/IAO_EXT_0000191 rdfs/label ["ontology concept record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000193
(ccp/IAO_EXT_0000193 rdf/type owl/Class)
(ccp/IAO_EXT_0000193 rdfs/subClassOf ccp/IAO_EXT_0000311)
(ccp/IAO_EXT_0000193 rdfs/label ["ontology concept record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000195
(ccp/IAO_EXT_0000195 rdf/type owl/Class)
(ccp/IAO_EXT_0000195 rdfs/subClassOf ccp/IAO_EXT_0000193)
(ccp/IAO_EXT_0000195 rdfs/label ["ontology concept definition field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000196
(ccp/IAO_EXT_0000196 rdf/type owl/Class)
(ccp/IAO_EXT_0000196 rdfs/subClassOf ccp/IAO_EXT_0000193)
(ccp/IAO_EXT_0000196 rdfs/label ["ontology concept label field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000197
(ccp/IAO_EXT_0000197 rdf/type owl/Class)
(ccp/IAO_EXT_0000197 rdfs/subClassOf ccp/IAO_EXT_0000001)
(ccp/IAO_EXT_0000197 rdfs/label ["record-specific field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000198
(ccp/IAO_EXT_0000198 rdf/type owl/Class)
(ccp/IAO_EXT_0000198 rdfs/subClassOf ccp/IAO_EXT_0000088)
(ccp/IAO_EXT_0000198 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000198 obo/IAO_0000599 ["CHEBI_"])
(ccp/IAO_EXT_0000198 rdfs/label ["CHEBI ontology identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000199
(ccp/IAO_EXT_0000199 rdf/type owl/Class)
(ccp/IAO_EXT_0000199 rdfs/subClassOf ccp/IAO_EXT_0000102)
(ccp/IAO_EXT_0000199 ccp/IAO_EXT_0001700 ["GO:\\d+"])
(ccp/IAO_EXT_0000199 obo/IAO_0000599 ["MF_"])
(ccp/IAO_EXT_0000199 rdfs/label ["GO molecular function concept identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000200
(ccp/IAO_EXT_0000200 rdf/type owl/Class)
(ccp/IAO_EXT_0000200 rdfs/subClassOf ccp/IAO_EXT_0000102)
(ccp/IAO_EXT_0000200 ccp/IAO_EXT_0001700 ["GO:\\d+"])
(ccp/IAO_EXT_0000200 obo/IAO_0000599 ["CC_"])
(ccp/IAO_EXT_0000200 rdfs/label ["GO cellular component concept identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000201
(ccp/IAO_EXT_0000201 rdf/type owl/Class)
(ccp/IAO_EXT_0000201 rdfs/subClassOf ccp/IAO_EXT_0000088)
(ccp/IAO_EXT_0000201 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000201 obo/IAO_0000599 ["UBERON_"])
(ccp/IAO_EXT_0000201 rdfs/label ["UBERON ontology concept identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000202
(ccp/IAO_EXT_0000202 rdf/type owl/Class)
(ccp/IAO_EXT_0000202 rdfs/subClassOf ccp/IAO_EXT_0000088)
(ccp/IAO_EXT_0000202 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000202 obo/IAO_0000599 ["CL_"])
(ccp/IAO_EXT_0000202 rdfs/label ["Cell Type Ontology concept identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000203
(ccp/IAO_EXT_0000203 rdf/type owl/Class)
(ccp/IAO_EXT_0000203 rdfs/subClassOf ccp/IAO_EXT_0000088)
(ccp/IAO_EXT_0000203 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000203 obo/IAO_0000599 ["SO_"])
(ccp/IAO_EXT_0000203 rdfs/label ["Sequence Ontology concept identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000204
(ccp/IAO_EXT_0000204 rdf/type owl/Class)
(ccp/IAO_EXT_0000204 rdfs/subClassOf ccp/IAO_EXT_0000081)
(ccp/IAO_EXT_0000204 rdfs/subClassOf ccp/IAO_EXT_0000088)
(ccp/IAO_EXT_0000204 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000204 ccp/IAO_EXT_0001700 ["^\\d+$"])
(ccp/IAO_EXT_0000204 obo/IAO_0000599 ["NCBITaxon_"])
(ccp/IAO_EXT_0000204 rdfs/label ["NCBI Taxonomy concept identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000205
(ccp/IAO_EXT_0000205 rdf/type owl/Class)
(ccp/IAO_EXT_0000205 rdfs/subClassOf ccp/IAO_EXT_0000088)
(ccp/IAO_EXT_0000205 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000205 obo/IAO_0000599 ["BFO_"])
(ccp/IAO_EXT_0000205 rdfs/label ["Basic Formal Ontology concept identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000206
(ccp/IAO_EXT_0000206 rdf/type owl/Class)
(ccp/IAO_EXT_0000206 rdfs/subClassOf ccp/IAO_EXT_0000088)
(ccp/IAO_EXT_0000206 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000206 obo/IAO_0000599 ["MP_"])
(ccp/IAO_EXT_0000206 rdfs/label ["Mammalian Phenotype Ontology concept identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000207
(ccp/IAO_EXT_0000207 rdf/type owl/Class)
(ccp/IAO_EXT_0000207 rdfs/subClassOf ccp/IAO_EXT_0000088)
(ccp/IAO_EXT_0000207 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000207 obo/IAO_0000599 ["BTO_"])
(ccp/IAO_EXT_0000207 rdfs/label ["BRENDA ontology concept identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000208
(ccp/IAO_EXT_0000208 rdf/type owl/Class)
(ccp/IAO_EXT_0000208 rdfs/subClassOf ccp/IAO_EXT_0000088)
(ccp/IAO_EXT_0000208 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000208 obo/IAO_0000599 ["HP_"])
(ccp/IAO_EXT_0000208 rdfs/label ["Human Phenotype Ontology concept identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000209
(ccp/IAO_EXT_0000209 rdf/type owl/Class)
(ccp/IAO_EXT_0000209 rdfs/subClassOf ccp/IAO_EXT_0000088)
(ccp/IAO_EXT_0000209 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000209 obo/IAO_0000599 ["DOID_"])
(ccp/IAO_EXT_0000209 rdfs/label ["Disease Ontology concept identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000210
(ccp/IAO_EXT_0000210 rdf/type owl/Class)
(ccp/IAO_EXT_0000210 rdfs/subClassOf ccp/IAO_EXT_0000088)
(ccp/IAO_EXT_0000210 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000210 obo/IAO_0000599 ["PO_"])
(ccp/IAO_EXT_0000210 rdfs/label ["Plant Ontology concept identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000211
(ccp/IAO_EXT_0000211 rdf/type owl/Class)
(ccp/IAO_EXT_0000211 rdfs/subClassOf ccp/IAO_EXT_0000088)
(ccp/IAO_EXT_0000211 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000211 obo/IAO_0000599 ["PATO_"])
(ccp/IAO_EXT_0000211 rdfs/label ["Phenotypic Quality Ontology concept identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000212
(ccp/IAO_EXT_0000212 rdf/type owl/Class)
(ccp/IAO_EXT_0000212 rdfs/subClassOf ccp/IAO_EXT_0000088)
(ccp/IAO_EXT_0000212 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000212 obo/IAO_0000599 ["NBO_"])
(ccp/IAO_EXT_0000212 rdfs/label ["Neuro Behavior Ontology concept identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000214
(ccp/IAO_EXT_0000214 rdf/type owl/Class)
(ccp/IAO_EXT_0000214 rdfs/subClassOf ccp/IAO_EXT_0000197)
(ccp/IAO_EXT_0000214 rdfs/label ["DIP record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000215
(ccp/IAO_EXT_0000215 rdf/type owl/Class)
(ccp/IAO_EXT_0000215 rdfs/subClassOf ccp/IAO_EXT_0000197)
(ccp/IAO_EXT_0000215 rdfs/label ["Drugbank record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000216
(ccp/IAO_EXT_0000216 rdf/type owl/Class)
(ccp/IAO_EXT_0000216 rdfs/subClassOf ccp/IAO_EXT_0000197)
(ccp/IAO_EXT_0000216 rdfs/label ["UniProt record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000217
(ccp/IAO_EXT_0000217 rdf/type owl/Class)
(ccp/IAO_EXT_0000217 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0000217 rdfs/label ["UniProt knowledge base record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000218
(ccp/IAO_EXT_0000218 rdf/type owl/Class)
(ccp/IAO_EXT_0000218 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0000218 rdfs/label ["UniProt identifier mapping record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000219
(ccp/IAO_EXT_0000219 rdf/type owl/Class)
(ccp/IAO_EXT_0000219 rdfs/subClassOf ccp/IAO_EXT_0000197)
(ccp/IAO_EXT_0000219 rdfs/label ["HPO record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000220
(ccp/IAO_EXT_0000220 rdf/type owl/Class)
(ccp/IAO_EXT_0000220 rdfs/subClassOf ccp/IAO_EXT_0000197)
(ccp/IAO_EXT_0000220 rdfs/label ["IRefWeb record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000221
(ccp/IAO_EXT_0000221 rdf/type owl/Class)
(ccp/IAO_EXT_0000221 rdfs/subClassOf ccp/IAO_EXT_0000197)
(ccp/IAO_EXT_0000221 rdfs/label ["MGI record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000222
(ccp/IAO_EXT_0000222 rdf/type owl/Class)
(ccp/IAO_EXT_0000222 rdfs/subClassOf ccp/IAO_EXT_0000197)
(ccp/IAO_EXT_0000222 rdfs/label ["miRBase record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000223
(ccp/IAO_EXT_0000223 rdf/type owl/Class)
(ccp/IAO_EXT_0000223 rdfs/subClassOf ccp/IAO_EXT_0000197)
(ccp/IAO_EXT_0000223 rdfs/label ["NCBI record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000224
(ccp/IAO_EXT_0000224 rdf/type owl/Class)
(ccp/IAO_EXT_0000224 rdfs/subClassOf ccp/IAO_EXT_0000223)
(ccp/IAO_EXT_0000224 rdfs/label ["NCBI homologene record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000225
(ccp/IAO_EXT_0000225 rdf/type owl/Class)
(ccp/IAO_EXT_0000225 rdfs/subClassOf ccp/IAO_EXT_0000223)
(ccp/IAO_EXT_0000225 rdfs/label ["NCBI OMIM record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000227
(ccp/IAO_EXT_0000227 rdf/type owl/Class)
(ccp/IAO_EXT_0000227 rdfs/subClassOf ccp/IAO_EXT_0000197)
(ccp/IAO_EXT_0000227 rdfs/label ["PharmGKB record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000228
(ccp/IAO_EXT_0000228 rdf/type owl/Class)
(ccp/IAO_EXT_0000228 rdfs/subClassOf ccp/IAO_EXT_0000227)
(ccp/IAO_EXT_0000228 rdfs/label ["PharmGKB gene record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000229
(ccp/IAO_EXT_0000229 rdf/type owl/Class)
(ccp/IAO_EXT_0000229 rdfs/subClassOf ccp/IAO_EXT_0000227)
(ccp/IAO_EXT_0000229 rdfs/label ["PharmGKB disease record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000230
(ccp/IAO_EXT_0000230 rdf/type owl/Class)
(ccp/IAO_EXT_0000230 rdfs/subClassOf ccp/IAO_EXT_0000227)
(ccp/IAO_EXT_0000230 rdfs/label ["PharmGKB drug record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000231
(ccp/IAO_EXT_0000231 rdf/type owl/Class)
(ccp/IAO_EXT_0000231 rdfs/subClassOf ccp/IAO_EXT_0000227)
(ccp/IAO_EXT_0000231 rdfs/label ["PharmGKB relation record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000232
(ccp/IAO_EXT_0000232 rdf/type owl/Class)
(ccp/IAO_EXT_0000232 rdfs/subClassOf ccp/IAO_EXT_0000197)
(ccp/IAO_EXT_0000232 rdfs/label ["Reactome record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000233
(ccp/IAO_EXT_0000233 rdf/type owl/Class)
(ccp/IAO_EXT_0000233 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0000233 rdfs/label ["Reactome UniProt to pathway identifier record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000234
(ccp/IAO_EXT_0000234 rdf/type owl/Class)
(ccp/IAO_EXT_0000234 rdfs/subClassOf ccp/IAO_EXT_0000061)
(ccp/IAO_EXT_0000234 rdfs/label ["UniProt knowledge base record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000235
(ccp/IAO_EXT_0000235 rdf/type owl/Class)
(ccp/IAO_EXT_0000235 rdfs/subClassOf ccp/IAO_EXT_0000061)
(ccp/IAO_EXT_0000235 rdfs/label ["UniProt identifier mapping record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000236
(ccp/IAO_EXT_0000236 rdf/type owl/Class)
(ccp/IAO_EXT_0000236 rdfs/subClassOf ccp/IAO_EXT_0000061)
(ccp/IAO_EXT_0000236 rdfs/label ["UniProt knowledge base record component" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000237
(ccp/IAO_EXT_0000237 rdf/type owl/Class)
(ccp/IAO_EXT_0000237 rdfs/subClassOf ccp/IAO_EXT_0000000)
(ccp/IAO_EXT_0000237 rdfs/label ["HGNC record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000238
(ccp/IAO_EXT_0000238 rdf/type owl/Class)
(ccp/IAO_EXT_0000238 rdfs/subClassOf ccp/IAO_EXT_0000000)
(ccp/IAO_EXT_0000238 rdfs/label ["IRefWeb record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000239
(ccp/IAO_EXT_0000239 rdf/type owl/Class)
(ccp/IAO_EXT_0000239 rdfs/subClassOf ccp/IAO_EXT_0000178)
(ccp/IAO_EXT_0000239 rdfs/subClassOf ccp/IAO_EXT_0000218)
(ccp/IAO_EXT_0000239 rdfs/label ["UniProt identifier mapping record - UniProt accession identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000240
(ccp/IAO_EXT_0000240 rdf/type owl/Class)
(ccp/IAO_EXT_0000240 rdfs/subClassOf ccp/IAO_EXT_0000217)
(ccp/IAO_EXT_0000240 rdfs/label ["UniProt knowledge base record - primary accession field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000241
(ccp/IAO_EXT_0000241 rdf/type owl/Class)
(ccp/IAO_EXT_0000241 rdfs/subClassOf ccp/IAO_EXT_0000218)
(ccp/IAO_EXT_0000241 rdfs/subClassOf ccp/IAO_EXT_0000303)
(ccp/IAO_EXT_0000241 rdfs/label ["UniProt identifier mapping record - UniProt entry name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000242
(ccp/IAO_EXT_0000242 rdf/type owl/Class)
(ccp/IAO_EXT_0000242 rdfs/subClassOf ccp/IAO_EXT_0000218)
(ccp/IAO_EXT_0000242 rdfs/label ["UniProt identifier mapping record -  Entrez gene identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000243
(ccp/IAO_EXT_0000243 rdf/type owl/Class)
(ccp/IAO_EXT_0000243 rdfs/subClassOf ccp/IAO_EXT_0000218)
(ccp/IAO_EXT_0000243 rdfs/subClassOf ccp/IAO_EXT_0000283)
(ccp/IAO_EXT_0000243 rdfs/label ["UniProt identifier mapping record - RefSeq identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000244
(ccp/IAO_EXT_0000244 rdf/type owl/Class)
(ccp/IAO_EXT_0000244 rdfs/subClassOf ccp/IAO_EXT_0000218)
(ccp/IAO_EXT_0000244 rdfs/subClassOf ccp/IAO_EXT_0000261)
(ccp/IAO_EXT_0000244 rdfs/label ["UniProt identifier mapping record - GeneInfo number identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000245
(ccp/IAO_EXT_0000245 rdf/type owl/Class)
(ccp/IAO_EXT_0000245 rdfs/subClassOf ccp/IAO_EXT_0000218)
(ccp/IAO_EXT_0000245 rdfs/subClassOf ccp/IAO_EXT_0000284)
(ccp/IAO_EXT_0000245 rdfs/label ["UniProt identifier mapping record - PDB identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000246
(ccp/IAO_EXT_0000246 rdf/type owl/Class)
(ccp/IAO_EXT_0000246 rdfs/subClassOf ccp/IAO_EXT_0000035)
(ccp/IAO_EXT_0000246 rdfs/subClassOf ccp/IAO_EXT_0000218)
(ccp/IAO_EXT_0000246 rdfs/label ["UniProt identifier mapping record - GO identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000247
(ccp/IAO_EXT_0000247 rdf/type owl/Class)
(ccp/IAO_EXT_0000247 rdfs/subClassOf ccp/IAO_EXT_0000218)
(ccp/IAO_EXT_0000247 rdfs/subClassOf ccp/IAO_EXT_0000286)
(ccp/IAO_EXT_0000247 rdfs/label ["UniProt identifier mapping record - UniRef100 identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000248
(ccp/IAO_EXT_0000248 rdf/type owl/Class)
(ccp/IAO_EXT_0000248 rdfs/subClassOf ccp/IAO_EXT_0000218)
(ccp/IAO_EXT_0000248 rdfs/subClassOf ccp/IAO_EXT_0000287)
(ccp/IAO_EXT_0000248 rdfs/label ["UniProt identifier mapping record - UniRef90 identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000249
(ccp/IAO_EXT_0000249 rdf/type owl/Class)
(ccp/IAO_EXT_0000249 rdfs/subClassOf ccp/IAO_EXT_0000218)
(ccp/IAO_EXT_0000249 rdfs/subClassOf ccp/IAO_EXT_0000288)
(ccp/IAO_EXT_0000249 rdfs/label ["UniProt identifier mapping record - UniRef50 identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000250
(ccp/IAO_EXT_0000250 rdf/type owl/Class)
(ccp/IAO_EXT_0000250 rdfs/subClassOf ccp/IAO_EXT_0000218)
(ccp/IAO_EXT_0000250 rdfs/subClassOf ccp/IAO_EXT_0000291)
(ccp/IAO_EXT_0000250 rdfs/label ["UniProt identifier mapping record - UniParc identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000251
(ccp/IAO_EXT_0000251 rdf/type owl/Class)
(ccp/IAO_EXT_0000251 rdfs/subClassOf ccp/IAO_EXT_0000218)
(ccp/IAO_EXT_0000251 rdfs/label ["UniProt identifier mapping record - taxonomy identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000252
(ccp/IAO_EXT_0000252 rdf/type owl/Class)
(ccp/IAO_EXT_0000252 rdfs/subClassOf ccp/IAO_EXT_0000218)
(ccp/IAO_EXT_0000252 rdfs/subClassOf ccp/IAO_EXT_0000293)
(ccp/IAO_EXT_0000252 rdfs/label ["UniProt identifier mapping record - OMIM identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000253
(ccp/IAO_EXT_0000253 rdf/type owl/Class)
(ccp/IAO_EXT_0000253 rdfs/subClassOf ccp/IAO_EXT_0000218)
(ccp/IAO_EXT_0000253 rdfs/subClassOf ccp/IAO_EXT_0000296)
(ccp/IAO_EXT_0000253 rdfs/label ["UniProt identifier mapping record - PubMed identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000254
(ccp/IAO_EXT_0000254 rdf/type owl/Class)
(ccp/IAO_EXT_0000254 rdfs/subClassOf ccp/IAO_EXT_0000218)
(ccp/IAO_EXT_0000254 rdfs/subClassOf ccp/IAO_EXT_0000298)
(ccp/IAO_EXT_0000254 rdfs/label ["UniProt identifier mapping record - EMBL identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000255
(ccp/IAO_EXT_0000255 rdf/type owl/Class)
(ccp/IAO_EXT_0000255 rdfs/subClassOf ccp/IAO_EXT_0000218)
(ccp/IAO_EXT_0000255 rdfs/subClassOf ccp/IAO_EXT_0000299)
(ccp/IAO_EXT_0000255 rdfs/label ["UniProt identifier mapping record - EMBL-CDS identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000256
(ccp/IAO_EXT_0000256 rdf/type owl/Class)
(ccp/IAO_EXT_0000256 rdfs/subClassOf ccp/IAO_EXT_0000218)
(ccp/IAO_EXT_0000256 rdfs/subClassOf ccp/IAO_EXT_0000300)
(ccp/IAO_EXT_0000256 rdfs/label ["UniProt identifier mapping record - Ensembl identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000257
(ccp/IAO_EXT_0000257 rdf/type owl/Class)
(ccp/IAO_EXT_0000257 rdfs/subClassOf ccp/IAO_EXT_0000218)
(ccp/IAO_EXT_0000257 rdfs/subClassOf ccp/IAO_EXT_0000301)
(ccp/IAO_EXT_0000257 rdfs/label ["UniProt identifier mapping record - Ensembl TRS identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000258
(ccp/IAO_EXT_0000258 rdf/type owl/Class)
(ccp/IAO_EXT_0000258 rdfs/subClassOf ccp/IAO_EXT_0000218)
(ccp/IAO_EXT_0000258 rdfs/subClassOf ccp/IAO_EXT_0000302)
(ccp/IAO_EXT_0000258 rdfs/label ["UniProt identifier mapping record - Ensembl PRO identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000259
(ccp/IAO_EXT_0000259 rdf/type owl/Class)
(ccp/IAO_EXT_0000259 rdfs/subClassOf ccp/IAO_EXT_0000218)
(ccp/IAO_EXT_0000259 rdfs/subClassOf ccp/IAO_EXT_0000296)
(ccp/IAO_EXT_0000259 rdfs/label ["UniProt identifier mapping record - additional PubMed identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000260
(ccp/IAO_EXT_0000260 rdf/type owl/Class)
(ccp/IAO_EXT_0000260 rdfs/subClassOf ccp/IAO_EXT_0000218)
(ccp/IAO_EXT_0000260 rdfs/subClassOf ccp/IAO_EXT_0000292)
(ccp/IAO_EXT_0000260 rdfs/label ["UniProt identifier mapping record - PIR identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000261
(ccp/IAO_EXT_0000261 rdf/type owl/Class)
(ccp/IAO_EXT_0000261 rdfs/subClassOf ccp/IAO_EXT_0000030)
(ccp/IAO_EXT_0000261 rdfs/label ["NCBI GI number identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000262
(ccp/IAO_EXT_0000262 rdf/type owl/Class)
(ccp/IAO_EXT_0000262 rdfs/subClassOf ccp/IAO_EXT_0000182)
(ccp/IAO_EXT_0000262 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000262 ccp/IAO_EXT_0001700 ["^\\d+$"])
(ccp/IAO_EXT_0000262 obo/IAO_0000599 ["NCBI_GI_"])
(ccp/IAO_EXT_0000262 rdfs/label ["NCBI GI number identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000263
(ccp/IAO_EXT_0000263 rdf/type owl/Class)
(ccp/IAO_EXT_0000263 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000263 ccp/IAO_EXT_0001700 ["((AC_)|(AP_)|(NC_)|(NG_)|(NM_)|(NP_)|(NR_)|(NT_)|(NW_)|(NZ_[A-Z]{1,4})|(XM_)|(XP_)|(XR_)|(YP_)|(ZP_)|(NS_)|(WP_))\\d+"])
(ccp/IAO_EXT_0000263 obo/IAO_0000599 ["REFSEQ_"])
(ccp/IAO_EXT_0000263 rdfs/label ["RefSeq identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000264
(ccp/IAO_EXT_0000264 rdf/type owl/Class)
(ccp/IAO_EXT_0000264 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000264 obo/IAO_0000599 ["PROTEIN_DATA_BANK_"])
(ccp/IAO_EXT_0000264 rdfs/label ["Protein Data Bank identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000265
(ccp/IAO_EXT_0000265 rdf/type owl/Class)
(ccp/IAO_EXT_0000265 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000265 obo/IAO_0000599 ["UNIREF_"])
(ccp/IAO_EXT_0000265 rdfs/label ["UniRef identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000266
(ccp/IAO_EXT_0000266 rdf/type owl/Class)
(ccp/IAO_EXT_0000266 rdfs/subClassOf ccp/IAO_EXT_0000265)
(ccp/IAO_EXT_0000266 obo/IAO_0000599 ["UNIREF100_"])
(ccp/IAO_EXT_0000266 rdfs/label ["UniRef100 identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000267
(ccp/IAO_EXT_0000267 rdf/type owl/Class)
(ccp/IAO_EXT_0000267 rdfs/subClassOf ccp/IAO_EXT_0000265)
(ccp/IAO_EXT_0000267 obo/IAO_0000599 ["UNIREF90_"])
(ccp/IAO_EXT_0000267 rdfs/label ["UniRef90 identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000268
(ccp/IAO_EXT_0000268 rdf/type owl/Class)
(ccp/IAO_EXT_0000268 rdfs/subClassOf ccp/IAO_EXT_0000265)
(ccp/IAO_EXT_0000268 obo/IAO_0000599 ["UNIREF50_"])
(ccp/IAO_EXT_0000268 rdfs/label ["UniRef50 identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000269
(ccp/IAO_EXT_0000269 rdf/type owl/Class)
(ccp/IAO_EXT_0000269 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000269 obo/IAO_0000599 ["UNIPARC_"])
(ccp/IAO_EXT_0000269 rdfs/label ["UniParc identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000270
(ccp/IAO_EXT_0000270 rdf/type owl/Class)
(ccp/IAO_EXT_0000270 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000270 obo/IAO_0000599 ["PIR_"])
(ccp/IAO_EXT_0000270 rdfs/label ["PIR identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000271
(ccp/IAO_EXT_0000271 rdf/type owl/Class)
(ccp/IAO_EXT_0000271 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000271 ccp/IAO_EXT_0001700 ["^\\d+$"])
(ccp/IAO_EXT_0000271 obo/IAO_0000599 ["OMIM_"])
(ccp/IAO_EXT_0000271 rdfs/label ["OMIM identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000272
(ccp/IAO_EXT_0000272 rdf/type owl/Class)
(ccp/IAO_EXT_0000272 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000272 obo/IAO_0000599 ["UNIGENE_"])
(ccp/IAO_EXT_0000272 rdfs/label ["UniGene identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000273
(ccp/IAO_EXT_0000273 rdf/type owl/Class)
(ccp/IAO_EXT_0000273 rdfs/subClassOf ccp/IAO_EXT_0000341)
(ccp/IAO_EXT_0000273 obo/IAO_0000599 ["DOCUMENT_"])
(ccp/IAO_EXT_0000273 rdfs/label ["document identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000274
(ccp/IAO_EXT_0000274 rdf/type owl/Class)
(ccp/IAO_EXT_0000274 rdfs/subClassOf ccp/IAO_EXT_0000273)
(ccp/IAO_EXT_0000274 ccp/IAO_EXT_0001700 ["^\\d+$"])
(ccp/IAO_EXT_0000274 obo/IAO_0000599 ["PUBMED_"])
(ccp/IAO_EXT_0000274 rdfs/label ["PubMed identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000275
(ccp/IAO_EXT_0000275 rdf/type owl/Class)
(ccp/IAO_EXT_0000275 rdfs/subClassOf ccp/IAO_EXT_0000273)
(ccp/IAO_EXT_0000275 obo/IAO_0000599 ["PUBMED_CENTRAL_"])
(ccp/IAO_EXT_0000275 rdfs/label ["PubMed Central identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000276
(ccp/IAO_EXT_0000276 rdf/type owl/Class)
(ccp/IAO_EXT_0000276 rdfs/subClassOf ccp/IAO_EXT_0000273)
(ccp/IAO_EXT_0000276 obo/IAO_0000599 ["DOI_"])
(ccp/IAO_EXT_0000276 rdfs/label ["DOI" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000277
(ccp/IAO_EXT_0000277 rdf/type owl/Class)
(ccp/IAO_EXT_0000277 rdfs/subClassOf ccp/IAO_EXT_0000218)
(ccp/IAO_EXT_0000277 rdfs/subClassOf ccp/IAO_EXT_0000294)
(ccp/IAO_EXT_0000277 rdfs/label ["UniProt identifier mapping record - UniGene identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000278
(ccp/IAO_EXT_0000278 rdf/type owl/Class)
(ccp/IAO_EXT_0000278 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000278 obo/IAO_0000599 ["EMBL_"])
(ccp/IAO_EXT_0000278 rdfs/label ["EMBL identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000279
(ccp/IAO_EXT_0000279 rdf/type owl/Class)
(ccp/IAO_EXT_0000279 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000279 obo/IAO_0000599 ["EMBL-CDS_"])
(ccp/IAO_EXT_0000279 rdfs/label ["EMBL-CDS identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000280
(ccp/IAO_EXT_0000280 rdf/type owl/Class)
(ccp/IAO_EXT_0000280 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000280 obo/IAO_0000599 ["ENSEMBL_"])
(ccp/IAO_EXT_0000280 rdfs/label ["Ensembl identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000281
(ccp/IAO_EXT_0000281 rdf/type owl/Class)
(ccp/IAO_EXT_0000281 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000281 obo/IAO_0000599 ["ENSEMBL_TRS_"])
(ccp/IAO_EXT_0000281 rdfs/label ["Ensembl TRS identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000282
(ccp/IAO_EXT_0000282 rdf/type owl/Class)
(ccp/IAO_EXT_0000282 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000282 obo/IAO_0000599 ["ENSEMBL_PRO_"])
(ccp/IAO_EXT_0000282 rdfs/label ["Ensembl PRO identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000283
(ccp/IAO_EXT_0000283 rdf/type owl/Class)
(ccp/IAO_EXT_0000283 rdfs/subClassOf ccp/IAO_EXT_0000087)
(ccp/IAO_EXT_0000283 rdfs/label ["RefSeq identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000284
(ccp/IAO_EXT_0000284 rdf/type owl/Class)
(ccp/IAO_EXT_0000284 rdfs/subClassOf ccp/IAO_EXT_0000087)
(ccp/IAO_EXT_0000284 rdfs/label ["Protein Data Bank identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000285
(ccp/IAO_EXT_0000285 rdf/type owl/Class)
(ccp/IAO_EXT_0000285 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000285 rdfs/label ["UniRef identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000286
(ccp/IAO_EXT_0000286 rdf/type owl/Class)
(ccp/IAO_EXT_0000286 rdfs/subClassOf ccp/IAO_EXT_0000285)
(ccp/IAO_EXT_0000286 rdfs/label ["UniRef100 identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000287
(ccp/IAO_EXT_0000287 rdf/type owl/Class)
(ccp/IAO_EXT_0000287 rdfs/subClassOf ccp/IAO_EXT_0000285)
(ccp/IAO_EXT_0000287 rdfs/label ["UniRef90 identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000288
(ccp/IAO_EXT_0000288 rdf/type owl/Class)
(ccp/IAO_EXT_0000288 rdfs/subClassOf ccp/IAO_EXT_0000285)
(ccp/IAO_EXT_0000288 rdfs/label ["UniRef50 identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000290
(ccp/IAO_EXT_0000290 rdf/type owl/Class)
(ccp/IAO_EXT_0000290 rdfs/subClassOf ccp/IAO_EXT_0000034)
(ccp/IAO_EXT_0000290 rdfs/label ["CHEBI identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000291
(ccp/IAO_EXT_0000291 rdf/type owl/Class)
(ccp/IAO_EXT_0000291 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000291 rdfs/label ["UniParc identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000292
(ccp/IAO_EXT_0000292 rdf/type owl/Class)
(ccp/IAO_EXT_0000292 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000292 rdfs/label ["PIR identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000293
(ccp/IAO_EXT_0000293 rdf/type owl/Class)
(ccp/IAO_EXT_0000293 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000293 rdfs/label ["OMIM identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000294
(ccp/IAO_EXT_0000294 rdf/type owl/Class)
(ccp/IAO_EXT_0000294 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000294 rdfs/label ["UniGene identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000295
(ccp/IAO_EXT_0000295 rdf/type owl/Class)
(ccp/IAO_EXT_0000295 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000295 rdfs/label ["document identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000296
(ccp/IAO_EXT_0000296 rdf/type owl/Class)
(ccp/IAO_EXT_0000296 rdfs/subClassOf ccp/IAO_EXT_0000295)
(ccp/IAO_EXT_0000296 rdfs/label ["PubMed identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000297
(ccp/IAO_EXT_0000297 rdf/type owl/Class)
(ccp/IAO_EXT_0000297 rdfs/subClassOf ccp/IAO_EXT_0000295)
(ccp/IAO_EXT_0000297 rdfs/label ["PubMed Central identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000298
(ccp/IAO_EXT_0000298 rdf/type owl/Class)
(ccp/IAO_EXT_0000298 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000298 rdfs/label ["EMBL identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000299
(ccp/IAO_EXT_0000299 rdf/type owl/Class)
(ccp/IAO_EXT_0000299 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000299 rdfs/label ["EMBL CDS identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000300
(ccp/IAO_EXT_0000300 rdf/type owl/Class)
(ccp/IAO_EXT_0000300 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000300 rdfs/label ["Ensembl identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000301
(ccp/IAO_EXT_0000301 rdf/type owl/Class)
(ccp/IAO_EXT_0000301 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000301 rdfs/label ["Ensembl TRS identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000302
(ccp/IAO_EXT_0000302 rdf/type owl/Class)
(ccp/IAO_EXT_0000302 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000302 rdfs/label ["Ensembl PRO identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000303
(ccp/IAO_EXT_0000303 rdf/type owl/Class)
(ccp/IAO_EXT_0000303 rdfs/subClassOf ccp/IAO_EXT_0000177)
(ccp/IAO_EXT_0000303 rdfs/label ["UniProt entry name identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000304
(ccp/IAO_EXT_0000304 rdf/type owl/Class)
(ccp/IAO_EXT_0000304 rdfs/subClassOf ccp/IAO_EXT_0000188)
(ccp/IAO_EXT_0000304 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000304 obo/IAO_0000599 ["UNIPROT_"])
(ccp/IAO_EXT_0000304 rdfs/label ["UniProt entry name" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000305
(ccp/IAO_EXT_0000305 rdf/type owl/Class)
(ccp/IAO_EXT_0000305 rdfs/subClassOf ccp/IAO_EXT_0000309)
(ccp/IAO_EXT_0000305 rdfs/label ["OWL restriction record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000306
(ccp/IAO_EXT_0000306 rdf/type owl/Class)
(ccp/IAO_EXT_0000306 rdfs/subClassOf ccp/IAO_EXT_0000307)
(ccp/IAO_EXT_0000306 obo/IAO_0000599 ["OBJECT_PROPERTY_"])
(ccp/IAO_EXT_0000306 rdfs/label ["object property identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000307
(ccp/IAO_EXT_0000307 rdf/type owl/Class)
(ccp/IAO_EXT_0000307 rdfs/subClassOf obo/IAO_0000578)
(ccp/IAO_EXT_0000307 rdfs/label ["ontology identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000308
(ccp/IAO_EXT_0000308 rdf/type owl/Class)
(ccp/IAO_EXT_0000308 rdfs/subClassOf ccp/IAO_EXT_0000306)
(ccp/IAO_EXT_0000308 rdfs/label ["top-level object property identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000309
(ccp/IAO_EXT_0000309 rdf/type owl/Class)
(ccp/IAO_EXT_0000309 rdfs/subClassOf ccp/IAO_EXT_0000000)
(ccp/IAO_EXT_0000309 rdfs/label ["ontology component record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000310
(ccp/IAO_EXT_0000310 rdf/type owl/Class)
(ccp/IAO_EXT_0000310 rdfs/subClassOf ccp/IAO_EXT_0000309)
(ccp/IAO_EXT_0000310 rdfs/label ["object property record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000311
(ccp/IAO_EXT_0000311 rdf/type owl/Class)
(ccp/IAO_EXT_0000311 rdfs/subClassOf ccp/IAO_EXT_0000001)
(ccp/IAO_EXT_0000311 rdfs/label ["ontology record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000312
(ccp/IAO_EXT_0000312 rdf/type owl/Class)
(ccp/IAO_EXT_0000312 rdfs/subClassOf ccp/IAO_EXT_0000311)
(ccp/IAO_EXT_0000312 rdfs/label ["object property record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000313
(ccp/IAO_EXT_0000313 rdf/type owl/Class)
(ccp/IAO_EXT_0000313 rdfs/subClassOf ccp/IAO_EXT_0000312)
(ccp/IAO_EXT_0000313 rdfs/label ["object property definition field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000314
(ccp/IAO_EXT_0000314 rdf/type owl/Class)
(ccp/IAO_EXT_0000314 rdfs/subClassOf ccp/IAO_EXT_0000312)
(ccp/IAO_EXT_0000314 rdfs/label ["object property identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000315
(ccp/IAO_EXT_0000315 rdf/type owl/Class)
(ccp/IAO_EXT_0000315 rdfs/subClassOf ccp/IAO_EXT_0000312)
(ccp/IAO_EXT_0000315 rdfs/label ["object property label field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000316
(ccp/IAO_EXT_0000316 rdf/type owl/Class)
(ccp/IAO_EXT_0000316 rdfs/subClassOf obo/IAO_0000027)
(ccp/IAO_EXT_0000316 rdfs/label ["identifier set" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000317
(ccp/IAO_EXT_0000317 rdf/type owl/Class)
(ccp/IAO_EXT_0000317 rdfs/subClassOf ccp/IAO_EXT_0000309)
(ccp/IAO_EXT_0000317 rdfs/label ["RDF list record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000329
(ccp/IAO_EXT_0000329 rdf/type owl/Class)
(ccp/IAO_EXT_0000329 rdfs/subClassOf obo/IAO_0000027)
(ccp/IAO_EXT_0000329 rdfs/label ["data about a forward-chaining rule" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000330
(ccp/IAO_EXT_0000330 rdf/type owl/Class)
(ccp/IAO_EXT_0000330 rdfs/subClassOf ccp/IAO_EXT_0000182)
(ccp/IAO_EXT_0000330 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000330 obo/IAO_0000599 ["CGNC_"])
(ccp/IAO_EXT_0000330 rdfs/label ["CGNC gene identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000331
(ccp/IAO_EXT_0000331 rdf/type owl/Class)
(ccp/IAO_EXT_0000331 rdfs/subClassOf ccp/IAO_EXT_0000182)
(ccp/IAO_EXT_0000331 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000331 ccp/IAO_EXT_0001700 ["^\\d+$"])
(ccp/IAO_EXT_0000331 obo/IAO_0000599 ["RGD_"])
(ccp/IAO_EXT_0000331 rdfs/label ["RGD gene identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000332
(ccp/IAO_EXT_0000332 rdf/type owl/Class)
(ccp/IAO_EXT_0000332 rdfs/subClassOf ccp/IAO_EXT_0000182)
(ccp/IAO_EXT_0000332 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000332 obo/IAO_0000599 ["TAIR_"])
(ccp/IAO_EXT_0000332 rdfs/label ["TAIR gene identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000333
(ccp/IAO_EXT_0000333 rdf/type owl/Class)
(ccp/IAO_EXT_0000333 rdfs/subClassOf ccp/IAO_EXT_0000182)
(ccp/IAO_EXT_0000333 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000333 obo/IAO_0000599 ["SGD_"])
(ccp/IAO_EXT_0000333 rdfs/label ["SGD gene identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000334
(ccp/IAO_EXT_0000334 rdf/type owl/Class)
(ccp/IAO_EXT_0000334 rdfs/subClassOf ccp/IAO_EXT_0000182)
(ccp/IAO_EXT_0000334 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000334 obo/IAO_0000599 ["FLYBASE_"])
(ccp/IAO_EXT_0000334 rdfs/label ["FlyBase gene identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000335
(ccp/IAO_EXT_0000335 rdf/type owl/Class)
(ccp/IAO_EXT_0000335 rdfs/subClassOf ccp/IAO_EXT_0000182)
(ccp/IAO_EXT_0000335 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000335 obo/IAO_0000599 ["ECOGENE_"])
(ccp/IAO_EXT_0000335 rdfs/label ["EcoGene gene identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000337
(ccp/IAO_EXT_0000337 rdf/type owl/Class)
(ccp/IAO_EXT_0000337 rdfs/subClassOf ccp/IAO_EXT_0000182)
(ccp/IAO_EXT_0000337 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000337 obo/IAO_0000599 ["WORMBASE_"])
(ccp/IAO_EXT_0000337 rdfs/label ["WormBase gene identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000338
(ccp/IAO_EXT_0000338 rdf/type owl/Class)
(ccp/IAO_EXT_0000338 rdfs/subClassOf ccp/IAO_EXT_0000182)
(ccp/IAO_EXT_0000338 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000338 obo/IAO_0000599 ["ZFIN_"])
(ccp/IAO_EXT_0000338 rdfs/label ["Zfin gene identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000339
(ccp/IAO_EXT_0000339 rdf/type owl/Class)
(ccp/IAO_EXT_0000339 rdfs/subClassOf ccp/IAO_EXT_0000182)
(ccp/IAO_EXT_0000339 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000339 obo/IAO_0000599 ["MGI_"])
(ccp/IAO_EXT_0000339 rdfs/label ["MGI gene identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000340
(ccp/IAO_EXT_0000340 rdf/type owl/Class)
(ccp/IAO_EXT_0000340 rdfs/subClassOf ccp/IAO_EXT_0000182)
(ccp/IAO_EXT_0000340 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0000340 obo/IAO_0000599 ["DICTYBASE_"])
(ccp/IAO_EXT_0000340 rdfs/label ["DictyBase gene identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000341
(ccp/IAO_EXT_0000341 rdf/type owl/Class)
(ccp/IAO_EXT_0000341 rdfs/subClassOf obo/IAO_0000578)
(ccp/IAO_EXT_0000341 rdfs/label ["identifier of an information content entity" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000342
(ccp/IAO_EXT_0000342 rdf/type owl/Class)
(ccp/IAO_EXT_0000342 rdfs/subClassOf obo/IAO_0000578)
(ccp/IAO_EXT_0000342 rdfs/label ["identifier of a biological entity" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000343
(ccp/IAO_EXT_0000343 rdf/type owl/Class)
(ccp/IAO_EXT_0000343 rdfs/subClassOf ccp/IAO_EXT_0000311)
(ccp/IAO_EXT_0000343 rdfs/label ["OWL restriction record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000344
(ccp/IAO_EXT_0000344 rdf/type owl/Class)
(ccp/IAO_EXT_0000344 rdfs/subClassOf ccp/IAO_EXT_0000311)
(ccp/IAO_EXT_0000344 rdfs/label ["RDF list record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000345
(ccp/IAO_EXT_0000345 rdf/type owl/Class)
(ccp/IAO_EXT_0000345 rdfs/subClassOf ccp/IAO_EXT_0000343)
(ccp/IAO_EXT_0000345 rdfs/label ["OWL restriction identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000346
(ccp/IAO_EXT_0000346 rdf/type owl/Class)
(ccp/IAO_EXT_0000346 rdfs/subClassOf ccp/IAO_EXT_0000344)
(ccp/IAO_EXT_0000346 rdfs/label ["RDF list identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000347
(ccp/IAO_EXT_0000347 rdf/type owl/Class)
(ccp/IAO_EXT_0000347 rdfs/subClassOf ccp/IAO_EXT_0000309)
(ccp/IAO_EXT_0000347 rdfs/label ["AllDisjointClasses record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000348
(ccp/IAO_EXT_0000348 rdf/type owl/Class)
(ccp/IAO_EXT_0000348 rdfs/subClassOf ccp/IAO_EXT_0000311)
(ccp/IAO_EXT_0000348 rdfs/label ["AllDisjointClasses record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000349
(ccp/IAO_EXT_0000349 rdf/type owl/Class)
(ccp/IAO_EXT_0000349 rdfs/subClassOf ccp/IAO_EXT_0000348)
(ccp/IAO_EXT_0000349 rdfs/label ["AllDisjointClasses identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000350
(ccp/IAO_EXT_0000350 rdf/type owl/Class)
(ccp/IAO_EXT_0000350 rdfs/subClassOf ccp/IAO_EXT_0000351)
(ccp/IAO_EXT_0000350 rdfs/label ["ontology component identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000351
(ccp/IAO_EXT_0000351 rdf/type owl/Class)
(ccp/IAO_EXT_0000351 rdfs/subClassOf obo/IAO_0000030)
(ccp/IAO_EXT_0000351 rdfs/label ["local identifier (not centrally registered)" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000352
(ccp/IAO_EXT_0000352 rdf/type owl/Class)
(ccp/IAO_EXT_0000352 rdfs/subClassOf ccp/IAO_EXT_0000350)
(ccp/IAO_EXT_0000352 rdfs/label ["OWL restriction identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000353
(ccp/IAO_EXT_0000353 rdf/type owl/Class)
(ccp/IAO_EXT_0000353 rdfs/subClassOf ccp/IAO_EXT_0000350)
(ccp/IAO_EXT_0000353 rdfs/label ["AllDisjointClasses identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000354
(ccp/IAO_EXT_0000354 rdf/type owl/Class)
(ccp/IAO_EXT_0000354 rdfs/subClassOf ccp/IAO_EXT_0000350)
(ccp/IAO_EXT_0000354 rdfs/label ["RDF list member identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000355
(ccp/IAO_EXT_0000355 rdf/type owl/Class)
(ccp/IAO_EXT_0000355 rdfs/subClassOf ccp/IAO_EXT_0000000)
(ccp/IAO_EXT_0000355 rdfs/label ["HPO record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000356
(ccp/IAO_EXT_0000356 rdf/type owl/Class)
(ccp/IAO_EXT_0000356 rdfs/subClassOf ccp/IAO_EXT_0000001)
(ccp/IAO_EXT_0000356 rdfs/label ["locus field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000357
(ccp/IAO_EXT_0000357 rdf/type owl/Class)
(ccp/IAO_EXT_0000357 rdfs/subClassOf ccp/IAO_EXT_0000238)
(ccp/IAO_EXT_0000357 rdfs/label ["IRefWeb PSI-MITAB 2_6 record component" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000358
(ccp/IAO_EXT_0000358 rdf/type owl/Class)
(ccp/IAO_EXT_0000358 rdfs/subClassOf ccp/IAO_EXT_0000197)
(ccp/IAO_EXT_0000358 rdfs/label ["HGNC record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000359
(ccp/IAO_EXT_0000359 rdf/type owl/Class)
(ccp/IAO_EXT_0000359 rdfs/subClassOf ccp/IAO_EXT_0000215)
(ccp/IAO_EXT_0000359 rdfs/label ["Drugbank drug record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000360
(ccp/IAO_EXT_0000360 rdf/type owl/Class)
(ccp/IAO_EXT_0000360 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000360 obo/IAO_0000115 ["Unique DrugBank accession number consisting of a 2 letter prefix (DB) and a 5 number suffix. This ID is used to access the drug entry via the URL. If an entry is deleted, it's DrugBank ID will not be reused"])
(ccp/IAO_EXT_0000360 rdfs/label ["Drugbank drug record - drugbank identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000361
(ccp/IAO_EXT_0000361 rdf/type owl/Class)
(ccp/IAO_EXT_0000361 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000361 rdfs/label ["Drugbank identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000362
(ccp/IAO_EXT_0000362 rdf/type owl/Class)
(ccp/IAO_EXT_0000362 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000362 obo/IAO_0000115 ["DrugBank release 1.0 accession number consisting of the 4 letter prefix and a 5 number suffix. Each Accession number is unique to the drug's generic name. The 4 letter suffix (APRD, EXPT, BIOD, NUTR) indicates the type of drug (APRD=approved small molecule drug, EXPT=experimental drug, BIOD=biotech drug, NUTR=nutraceutical or natural product)"])
(ccp/IAO_EXT_0000362 rdfs/label ["Drugbank drug record - secondary accession numbers field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000363
(ccp/IAO_EXT_0000363 rdf/type owl/Class)
(ccp/IAO_EXT_0000363 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000363 rdfs/label ["AHFS code identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000364
(ccp/IAO_EXT_0000364 rdf/type owl/Class)
(ccp/IAO_EXT_0000364 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000364 obo/IAO_0000115 ["Small molecule or Biotech. Small molecule drugs are drugs which are not from biological origin and are synthesized. Biotech drugs consist of peptide, protein or nucleic acid drugs"])
(ccp/IAO_EXT_0000364 rdfs/label ["Drugbank drug record - drug type field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000365
(ccp/IAO_EXT_0000365 rdf/type owl/Class)
(ccp/IAO_EXT_0000365 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000365 obo/IAO_0000115 ["Date/time the entry was created"])
(ccp/IAO_EXT_0000365 rdfs/label ["Drugbank drug record - created date field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000366
(ccp/IAO_EXT_0000366 rdf/type owl/Class)
(ccp/IAO_EXT_0000366 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000366 obo/IAO_0000115 ["Date/time the entry was last updated"])
(ccp/IAO_EXT_0000366 rdfs/label ["Drugbank drug record - updated date field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000367
(ccp/IAO_EXT_0000367 rdf/type owl/Class)
(ccp/IAO_EXT_0000367 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000367 obo/IAO_0000115 ["Standard name of drug as provided by drug manufacturer"])
(ccp/IAO_EXT_0000367 rdfs/label ["Drugbank drug record - drug name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000368
(ccp/IAO_EXT_0000368 rdf/type owl/Class)
(ccp/IAO_EXT_0000368 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000368 obo/IAO_0000115 ["Description of the drug describing general facts, composition and/or preparation"])
(ccp/IAO_EXT_0000368 rdfs/label ["Drugbank drug record - description field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000369
(ccp/IAO_EXT_0000369 rdf/type owl/Class)
(ccp/IAO_EXT_0000369 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000369 obo/IAO_0000115 ["Chemical Abstract Service identification number"])
(ccp/IAO_EXT_0000369 rdfs/label ["Drugbank drug record - chemical abstract service identifer number field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000370
(ccp/IAO_EXT_0000370 rdf/type owl/Class)
(ccp/IAO_EXT_0000370 rdfs/subClassOf ccp/IAO_EXT_0000001)
(ccp/IAO_EXT_0000370 rdfs/label ["cross references field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000371
(ccp/IAO_EXT_0000371 rdf/type owl/Class)
(ccp/IAO_EXT_0000371 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000371 obo/IAO_0000115 ["Can be one or more of: Approved \u2014 Drug has been approved in at least one country, Experimental \u2014 Drug has been shown experimentally to bind specific proteins in mammals, bacteria, viruses, fungi, or parasites. An experimental drug is not necessarily being formally investigated, Nutraceutical \u2014 Drug is a food product which has experimentally confirmed health benefits, Illicit \u2014 Drug is a scheduled drug in at least one country, Withdrawn \u2014 Drug has been removed from the market by a manufacturer in at least one country. A drug can be approved and withdrawn at the same time if it is still available commercially in one country while pulled in another country. Typically the Description field will contain further details"])
(ccp/IAO_EXT_0000371 rdfs/label ["Drugbank drug record - groups field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000372
(ccp/IAO_EXT_0000372 rdf/type owl/Class)
(ccp/IAO_EXT_0000372 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000372 obo/IAO_0000115 ["Reference or patent number to description of drug's synthesis"])
(ccp/IAO_EXT_0000372 rdfs/label ["Drugbank drug record - synthesis reference field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000373
(ccp/IAO_EXT_0000373 rdf/type owl/Class)
(ccp/IAO_EXT_0000373 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000373 obo/IAO_0000115 ["Description or common names of diseases that the drug is used to treat"])
(ccp/IAO_EXT_0000373 rdfs/label ["Drugbank drug record - indication field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000374
(ccp/IAO_EXT_0000374 rdf/type owl/Class)
(ccp/IAO_EXT_0000374 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000374 obo/IAO_0000115 ["Pharmacodynamcis - Description of how the drug works at a clinical or physiological level"])
(ccp/IAO_EXT_0000374 rdfs/label ["Drugbank drug record - pharmacodynamics field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000375
(ccp/IAO_EXT_0000375 rdf/type owl/Class)
(ccp/IAO_EXT_0000375 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000375 obo/IAO_0000115 ["Description of how the drug works or what it binds to at a molecular level"])
(ccp/IAO_EXT_0000375 rdfs/label ["Drugbank drug record - mechanism of action field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000376
(ccp/IAO_EXT_0000376 rdf/type owl/Class)
(ccp/IAO_EXT_0000376 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000376 obo/IAO_0000115 ["Lethal dose (LD50) values from test animals, description of side effects and toxic effects seen in humans"])
(ccp/IAO_EXT_0000376 rdfs/label ["Drugbank drug record - toxicity field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000377
(ccp/IAO_EXT_0000377 rdf/type owl/Class)
(ccp/IAO_EXT_0000377 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000377 obo/IAO_0000115 ["Mechanism by which or organ location where the drug is neutralized"])
(ccp/IAO_EXT_0000377 rdfs/label ["Drugbank drug record - metabolism field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000378
(ccp/IAO_EXT_0000378 rdf/type owl/Class)
(ccp/IAO_EXT_0000378 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000378 obo/IAO_0000115 ["Description of how much of the drug or how readily the drug is taken up by the body"])
(ccp/IAO_EXT_0000378 rdfs/label ["Drugbank drug record - absorption field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000379
(ccp/IAO_EXT_0000379 rdf/type owl/Class)
(ccp/IAO_EXT_0000379 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000379 obo/IAO_0000115 ["Half life of drug in body, measured in hours or days"])
(ccp/IAO_EXT_0000379 rdfs/label ["Drugbank drug record - half life field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000380
(ccp/IAO_EXT_0000380 rdf/type owl/Class)
(ccp/IAO_EXT_0000380 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000380 obo/IAO_0000115 ["Protein Binding Percentage of the drug that is bound in plasma proteins"])
(ccp/IAO_EXT_0000380 rdfs/label ["Drugbank drug record - protein binding statement field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000381
(ccp/IAO_EXT_0000381 rdf/type owl/Class)
(ccp/IAO_EXT_0000381 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000381 obo/IAO_0000115 ["Route by which the drug is eliminated. Drugs are cleared primarily by the liver and kidneys"])
(ccp/IAO_EXT_0000381 rdfs/label ["Drugbank drug record - route of elimination statement field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000382
(ccp/IAO_EXT_0000382 rdf/type owl/Class)
(ccp/IAO_EXT_0000382 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000382 obo/IAO_0000115 ["The apparent volume of distribution is the theoretical volume of fluid into which the total drug administered would have to be diluted to produce the concentration in plasma"])
(ccp/IAO_EXT_0000382 rdfs/label ["Drugbank drug record - volume of distribution field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000383
(ccp/IAO_EXT_0000383 rdf/type owl/Class)
(ccp/IAO_EXT_0000383 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000383 obo/IAO_0000115 ["Clearance is a descriptive term used to evaluate efficiency of drug removal from the body"])
(ccp/IAO_EXT_0000383 rdfs/label ["Drugbank drug record - clearance field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000384
(ccp/IAO_EXT_0000384 rdf/type owl/Class)
(ccp/IAO_EXT_0000384 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000384 obo/IAO_0000115 ["Drug classes form the major component of the classification system. Drugs with the same class are considered structurally similar"])
(ccp/IAO_EXT_0000384 rdfs/label ["Drugbank drug record - classification field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000385
(ccp/IAO_EXT_0000385 rdf/type owl/Class)
(ccp/IAO_EXT_0000385 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000385 rdfs/label ["Drugbank drug record - salts field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000386
(ccp/IAO_EXT_0000386 rdf/type owl/Class)
(ccp/IAO_EXT_0000386 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000386 obo/IAO_0000115 ["Alternate names of the drug"])
(ccp/IAO_EXT_0000386 rdfs/label ["Drugbank drug record - synonyms field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000387
(ccp/IAO_EXT_0000387 rdf/type owl/Class)
(ccp/IAO_EXT_0000387 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000387 obo/IAO_0000115 ["Brand names from different manufacturers"])
(ccp/IAO_EXT_0000387 rdfs/label ["Drugbank drug record - international brands field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000388
(ccp/IAO_EXT_0000388 rdf/type owl/Class)
(ccp/IAO_EXT_0000388 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000388 obo/IAO_0000115 ["Brand names and composition of mixtures that include the given drug"])
(ccp/IAO_EXT_0000388 rdfs/label ["Drugbank drug record - mixtures field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000389
(ccp/IAO_EXT_0000389 rdf/type owl/Class)
(ccp/IAO_EXT_0000389 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000389 obo/IAO_0000115 ["Companies which package and sell the given drug"])
(ccp/IAO_EXT_0000389 rdfs/label ["Drugbank drug record - packagers field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000390
(ccp/IAO_EXT_0000390 rdf/type owl/Class)
(ccp/IAO_EXT_0000390 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000390 obo/IAO_0000115 ["Companies known to manufacturer the given drug"])
(ccp/IAO_EXT_0000390 rdfs/label ["Drugbank drug record - manufacturers field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000391
(ccp/IAO_EXT_0000391 rdf/type owl/Class)
(ccp/IAO_EXT_0000391 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000391 obo/IAO_0000115 ["Unit cost drug prices in U.S. dollars"])
(ccp/IAO_EXT_0000391 rdfs/label ["Drugbank drug record - prices field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000392
(ccp/IAO_EXT_0000392 rdf/type owl/Class)
(ccp/IAO_EXT_0000392 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000392 obo/IAO_0000115 ["Therapeutic category or general category of drug (anti-convulsant, antibacterial, etc.)"])
(ccp/IAO_EXT_0000392 rdfs/label ["Drugbank drug record - categories field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000393
(ccp/IAO_EXT_0000393 rdf/type owl/Class)
(ccp/IAO_EXT_0000393 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000393 obo/IAO_0000115 ["Names of organisms for which the drug is most effective"])
(ccp/IAO_EXT_0000393 rdfs/label ["Drugbank drug record - affected organism field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000394
(ccp/IAO_EXT_0000394 rdf/type owl/Class)
(ccp/IAO_EXT_0000394 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000394 obo/IAO_0000115 ["How the drug is dispensed (tablets, capsules, solutions)"])
(ccp/IAO_EXT_0000394 rdfs/label ["Drugbank drug record - dosages field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000395
(ccp/IAO_EXT_0000395 rdf/type owl/Class)
(ccp/IAO_EXT_0000395 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000395 obo/IAO_0000115 ["WHO drug classification system (ATC) identifiers"])
(ccp/IAO_EXT_0000395 rdfs/label ["Drugbank drug record - ATC code identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000396
(ccp/IAO_EXT_0000396 rdf/type owl/Class)
(ccp/IAO_EXT_0000396 rdfs/subClassOf ccp/IAO_EXT_0000370)
(ccp/IAO_EXT_0000396 rdfs/label ["article references field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000397
(ccp/IAO_EXT_0000397 rdf/type owl/Class)
(ccp/IAO_EXT_0000397 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000397 obo/IAO_0000115 ["AHFS Drug Information identifiers"])
(ccp/IAO_EXT_0000397 rdfs/label ["Drugbank drug record - AHFS code identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000398
(ccp/IAO_EXT_0000398 rdf/type owl/Class)
(ccp/IAO_EXT_0000398 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000398 obo/IAO_0000115 ["The first and last drug patent, including approval and expiry dates"])
(ccp/IAO_EXT_0000398 rdfs/label ["Drugbank drug record - patents field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000399
(ccp/IAO_EXT_0000399 rdf/type owl/Class)
(ccp/IAO_EXT_0000399 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000399 obo/IAO_0000115 ["Foods that are known to interact, interfere or cause adverse reactions when taken with this drug"])
(ccp/IAO_EXT_0000399 rdfs/label ["Drugbank drug record - food interactions field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000400
(ccp/IAO_EXT_0000400 rdf/type owl/Class)
(ccp/IAO_EXT_0000400 rdfs/subClassOf ccp/IAO_EXT_0000215)
(ccp/IAO_EXT_0000400 rdfs/label ["Drugbank enzyme record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000401
(ccp/IAO_EXT_0000401 rdf/type owl/Class)
(ccp/IAO_EXT_0000401 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000401 obo/IAO_0000115 ["Amino acid sequence"])
(ccp/IAO_EXT_0000401 rdfs/label ["Drugbank drug record - sequences field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000402
(ccp/IAO_EXT_0000402 rdf/type owl/Class)
(ccp/IAO_EXT_0000402 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000402 rdfs/label ["Drugbank drug record - calculated properties field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000403
(ccp/IAO_EXT_0000403 rdf/type owl/Class)
(ccp/IAO_EXT_0000403 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000403 rdfs/label ["Drugbank drug record - experimental properties field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000404
(ccp/IAO_EXT_0000404 rdf/type owl/Class)
(ccp/IAO_EXT_0000404 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000404 obo/IAO_0000115 ["possibly any one of: KEGG Drug ID, KEGG Compound ID, PubChem Compound ID, PubChem Substance ID, ChemSpider ID, BindingDB ID, ChEBI ID, ChEMBL ID, Stitch ID, Therapeutic Targets Database (TTD) ID, PharmGKB ID, HET ID PDB identificatin number, UniProt ID/Name, GenBank ID, Drug Product ID"])
(ccp/IAO_EXT_0000404 rdfs/label ["Drugbank drug record - external identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000405
(ccp/IAO_EXT_0000405 rdf/type owl/Class)
(ccp/IAO_EXT_0000405 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000405 obo/IAO_0000115 ["possibly a link to any one of RxList, Drugs.com, PDRhealth, Wikipedia"])
(ccp/IAO_EXT_0000405 rdfs/label ["Drugbank drug record - external link field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000406
(ccp/IAO_EXT_0000406 rdf/type owl/Class)
(ccp/IAO_EXT_0000406 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000406 obo/IAO_0000115 ["Drug pathways in which the given drug is involved"])
(ccp/IAO_EXT_0000406 rdfs/label ["Drugbank drug record - pathways field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000407
(ccp/IAO_EXT_0000407 rdf/type owl/Class)
(ccp/IAO_EXT_0000407 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000407 rdfs/label ["Drugbank drug record - reactions field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000408
(ccp/IAO_EXT_0000408 rdf/type owl/Class)
(ccp/IAO_EXT_0000408 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000408 rdfs/label ["Drugbank drug record - SNP effects field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000409
(ccp/IAO_EXT_0000409 rdf/type owl/Class)
(ccp/IAO_EXT_0000409 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000409 rdfs/label ["Drugbank drug record - SNP adverse drug reaction field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000410
(ccp/IAO_EXT_0000410 rdf/type owl/Class)
(ccp/IAO_EXT_0000410 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000410 obo/IAO_0000115 ["A protein, macromolecule, nucleic acid, or small molecule to which a given drug binds, resulting in an alteration of the normal function of the bound molecule and a desirable therapeutic effect. Drug targets are most commonly proteins such as enzymes, ion channels, and receptors"])
(ccp/IAO_EXT_0000410 rdfs/label ["Drugbank drug record - targets field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000411
(ccp/IAO_EXT_0000411 rdf/type owl/Class)
(ccp/IAO_EXT_0000411 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000411 obo/IAO_0000115 ["A protein which catalyzes chemical reactions involving the a given drug (substrate). Most drugs are metabolized by the Cytochrome P450 enzymes"])
(ccp/IAO_EXT_0000411 rdfs/label ["Drugbank drug record - enzymes field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000412
(ccp/IAO_EXT_0000412 rdf/type owl/Class)
(ccp/IAO_EXT_0000412 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000412 obo/IAO_0000115 ["A secreted protein which binds to drugs, carrying them to cell transporters, where they are moved into the cell. Drug carriers may be used in drug design to increase the effectiveness of drug delivery to the target sites of pharmacological actions"])
(ccp/IAO_EXT_0000412 rdfs/label ["Drugbank drug record - carriers field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000413
(ccp/IAO_EXT_0000413 rdf/type owl/Class)
(ccp/IAO_EXT_0000413 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000413 obo/IAO_0000115 ["A membrane bound protein which shuttles ions, small molecules or macromolecules across membranes, into cells or out of cells"])
(ccp/IAO_EXT_0000413 rdfs/label ["Drugbank drug record - transporters field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000414
(ccp/IAO_EXT_0000414 rdf/type owl/Class)
(ccp/IAO_EXT_0000414 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000414 rdfs/label ["Drugbank drug record - UNII field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000415
(ccp/IAO_EXT_0000415 rdf/type owl/Class)
(ccp/IAO_EXT_0000415 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000415 obo/IAO_0000115 ["Molecular weight in g/mol, determined from molecular formula or sequence"])
(ccp/IAO_EXT_0000415 rdfs/label ["Drugbank drug record - average mass field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000416
(ccp/IAO_EXT_0000416 rdf/type owl/Class)
(ccp/IAO_EXT_0000416 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000416 obo/IAO_0000115 ["The sum of the masses of the atoms in a molecule using the unbound, ground-state, rest mass of the principle (most abundant) isotope for each element instead of the isotopic average mass"])
(ccp/IAO_EXT_0000416 rdfs/label ["Drugbank drug record - monoisotropic mass field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000417
(ccp/IAO_EXT_0000417 rdf/type owl/Class)
(ccp/IAO_EXT_0000417 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000417 obo/IAO_0000115 ["Physical state (solid, liquid, gas)"])
(ccp/IAO_EXT_0000417 rdfs/label ["Drugbank drug record - state field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000418
(ccp/IAO_EXT_0000418 rdf/type owl/Class)
(ccp/IAO_EXT_0000418 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000418 obo/IAO_0000115 ["Food and Drug Administration approval label (if it exists)"])
(ccp/IAO_EXT_0000418 rdfs/label ["Drugbank drug record - FDA label field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000419
(ccp/IAO_EXT_0000419 rdf/type owl/Class)
(ccp/IAO_EXT_0000419 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000419 obo/IAO_0000115 ["Material Safety Data Sheet (MSDS) (if it exists)"])
(ccp/IAO_EXT_0000419 rdfs/label ["Drugbank drug record - MSDS field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000420
(ccp/IAO_EXT_0000420 rdf/type owl/Class)
(ccp/IAO_EXT_0000420 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000420 obo/IAO_0000115 ["Drug Product Database Drug Identification Number"])
(ccp/IAO_EXT_0000420 rdfs/label ["Drugbank drug record - products field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000421
(ccp/IAO_EXT_0000421 rdf/type owl/Class)
(ccp/IAO_EXT_0000421 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000421 obo/IAO_0000115 ["General on-line reference to other details about the drug"])
(ccp/IAO_EXT_0000421 rdfs/label ["Drugbank drug record - article references field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000422
(ccp/IAO_EXT_0000422 rdf/type owl/Class)
(ccp/IAO_EXT_0000422 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000422 obo/IAO_0000115 ["General on-line reference to other details about the drug"])
(ccp/IAO_EXT_0000422 rdfs/label ["Drugbank drug record - textbook references field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000423
(ccp/IAO_EXT_0000423 rdf/type owl/Class)
(ccp/IAO_EXT_0000423 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000423 obo/IAO_0000115 ["General on-line reference to other details about the drug"])
(ccp/IAO_EXT_0000423 rdfs/label ["Drugbank drug record - link references field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000424
(ccp/IAO_EXT_0000424 rdf/type owl/Class)
(ccp/IAO_EXT_0000424 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000424 obo/IAO_0000115 ["PDB ID (if it exists)"])
(ccp/IAO_EXT_0000424 rdfs/label ["Drugbank drug record - PBD entries field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000425
(ccp/IAO_EXT_0000425 rdf/type owl/Class)
(ccp/IAO_EXT_0000425 rdfs/subClassOf ccp/IAO_EXT_0000000)
(ccp/IAO_EXT_0000425 rdfs/label ["DrugBank record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000426
(ccp/IAO_EXT_0000426 rdf/type owl/Class)
(ccp/IAO_EXT_0000426 rdfs/subClassOf ccp/IAO_EXT_0000425)
(ccp/IAO_EXT_0000426 rdfs/label ["DrugBank drug record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000427
(ccp/IAO_EXT_0000427 rdf/type owl/Class)
(ccp/IAO_EXT_0000427 rdfs/subClassOf ccp/IAO_EXT_0000425)
(ccp/IAO_EXT_0000427 rdfs/label ["DrugBank drug record component" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000428
(ccp/IAO_EXT_0000428 rdf/type owl/Class)
(ccp/IAO_EXT_0000428 rdfs/subClassOf ccp/IAO_EXT_0000427)
(ccp/IAO_EXT_0000428 rdfs/label ["DrugBank target record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000429
(ccp/IAO_EXT_0000429 rdf/type owl/Class)
(ccp/IAO_EXT_0000429 rdfs/subClassOf ccp/IAO_EXT_0000215)
(ccp/IAO_EXT_0000429 rdfs/label ["Drugbank target record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000430
(ccp/IAO_EXT_0000430 rdf/type owl/Class)
(ccp/IAO_EXT_0000430 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000430 rdfs/label ["ATC code identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000431
(ccp/IAO_EXT_0000431 rdf/type owl/Class)
(ccp/IAO_EXT_0000431 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000431 rdfs/label ["Chemical Abstract Service identifer number field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000432
(ccp/IAO_EXT_0000432 rdf/type owl/Class)
(ccp/IAO_EXT_0000432 rdfs/subClassOf ccp/IAO_EXT_0000091)
(ccp/IAO_EXT_0000432 rdfs/label ["DrugBank description field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000433
(ccp/IAO_EXT_0000433 rdf/type owl/Class)
(ccp/IAO_EXT_0000433 rdfs/subClassOf ccp/IAO_EXT_0000039)
(ccp/IAO_EXT_0000433 rdfs/label ["drug name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000434
(ccp/IAO_EXT_0000434 rdf/type owl/Class)
(ccp/IAO_EXT_0000434 rdfs/subClassOf ccp/IAO_EXT_0000400)
(ccp/IAO_EXT_0000434 rdfs/label ["Drugbank enzyme record - induction strength field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000435
(ccp/IAO_EXT_0000435 rdf/type owl/Class)
(ccp/IAO_EXT_0000435 rdfs/subClassOf ccp/IAO_EXT_0000400)
(ccp/IAO_EXT_0000435 rdfs/label ["Drugbank enzyme record - inhibition strength field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000436
(ccp/IAO_EXT_0000436 rdf/type owl/Class)
(ccp/IAO_EXT_0000436 rdfs/subClassOf ccp/IAO_EXT_0000050)
(ccp/IAO_EXT_0000436 rdfs/label ["date updated field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000438
(ccp/IAO_EXT_0000438 rdf/type owl/Class)
(ccp/IAO_EXT_0000438 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000438 rdfs/label ["drug product database drug identification number identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000439
(ccp/IAO_EXT_0000439 rdf/type owl/Class)
(ccp/IAO_EXT_0000439 rdfs/subClassOf ccp/IAO_EXT_0000429)
(ccp/IAO_EXT_0000439 obo/IAO_0000115 ["Name of the protein or macromolecule (or other small molecule)"])
(ccp/IAO_EXT_0000439 rdfs/label ["Drugbank target record - name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000440
(ccp/IAO_EXT_0000440 rdf/type owl/Class)
(ccp/IAO_EXT_0000440 rdfs/subClassOf ccp/IAO_EXT_0000429)
(ccp/IAO_EXT_0000440 rdfs/label ["Drugbank target record - known action field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000441
(ccp/IAO_EXT_0000441 rdf/type owl/Class)
(ccp/IAO_EXT_0000441 rdfs/subClassOf ccp/IAO_EXT_0000429)
(ccp/IAO_EXT_0000441 rdfs/label ["Drugbank target record - organism field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000442
(ccp/IAO_EXT_0000442 rdf/type owl/Class)
(ccp/IAO_EXT_0000442 rdfs/subClassOf ccp/IAO_EXT_0000429)
(ccp/IAO_EXT_0000442 rdfs/label ["Drugbank target record - position field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000443
(ccp/IAO_EXT_0000443 rdf/type owl/Class)
(ccp/IAO_EXT_0000443 rdfs/subClassOf ccp/IAO_EXT_0000429)
(ccp/IAO_EXT_0000443 obo/IAO_0000115 ["General on-line reference to other details about the drug"])
(ccp/IAO_EXT_0000443 rdfs/label ["Drugbank target record - article references field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000444
(ccp/IAO_EXT_0000444 rdf/type owl/Class)
(ccp/IAO_EXT_0000444 rdfs/subClassOf ccp/IAO_EXT_0000429)
(ccp/IAO_EXT_0000444 obo/IAO_0000115 ["General on-line reference to other details about the drug"])
(ccp/IAO_EXT_0000444 rdfs/label ["Drugbank target record - textbook references field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000445
(ccp/IAO_EXT_0000445 rdf/type owl/Class)
(ccp/IAO_EXT_0000445 rdfs/subClassOf ccp/IAO_EXT_0000429)
(ccp/IAO_EXT_0000445 obo/IAO_0000115 ["General on-line reference to other details about the drug"])
(ccp/IAO_EXT_0000445 rdfs/label ["Drugbank target record - link references field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000446
(ccp/IAO_EXT_0000446 rdf/type owl/Class)
(ccp/IAO_EXT_0000446 rdfs/subClassOf ccp/IAO_EXT_0000429)
(ccp/IAO_EXT_0000446 rdfs/label ["Drugbank target record - actions field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000447
(ccp/IAO_EXT_0000447 rdf/type owl/Class)
(ccp/IAO_EXT_0000447 rdfs/subClassOf ccp/IAO_EXT_0000429)
(ccp/IAO_EXT_0000447 rdfs/label ["Drugbank target record - polypeptides field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000448
(ccp/IAO_EXT_0000448 rdf/type owl/Class)
(ccp/IAO_EXT_0000448 rdfs/subClassOf ccp/IAO_EXT_0000215)
(ccp/IAO_EXT_0000448 rdfs/label ["Drugbank polypeptide record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000449
(ccp/IAO_EXT_0000449 rdf/type owl/Class)
(ccp/IAO_EXT_0000449 rdfs/subClassOf ccp/IAO_EXT_0000427)
(ccp/IAO_EXT_0000449 rdfs/label ["DrugBank polypeptide record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000450
(ccp/IAO_EXT_0000450 rdf/type owl/Class)
(ccp/IAO_EXT_0000450 rdfs/subClassOf ccp/IAO_EXT_0000215)
(ccp/IAO_EXT_0000450 rdfs/label ["Drugbank PFAM term record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000451
(ccp/IAO_EXT_0000451 rdf/type owl/Class)
(ccp/IAO_EXT_0000451 rdfs/subClassOf ccp/IAO_EXT_0000427)
(ccp/IAO_EXT_0000451 rdfs/label ["DrugBank PFAM term record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000452
(ccp/IAO_EXT_0000452 rdf/type owl/Class)
(ccp/IAO_EXT_0000452 rdfs/subClassOf ccp/IAO_EXT_0000215)
(ccp/IAO_EXT_0000452 rdfs/label ["Drugbank GO term record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000453
(ccp/IAO_EXT_0000453 rdf/type owl/Class)
(ccp/IAO_EXT_0000453 rdfs/subClassOf ccp/IAO_EXT_0000215)
(ccp/IAO_EXT_0000453 rdfs/label ["Drugbank drugbank organism record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000454
(ccp/IAO_EXT_0000454 rdf/type owl/Class)
(ccp/IAO_EXT_0000454 rdfs/subClassOf ccp/IAO_EXT_0000215)
(ccp/IAO_EXT_0000454 rdfs/label ["Drugbank SNP adverse drug reaction record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000455
(ccp/IAO_EXT_0000455 rdf/type owl/Class)
(ccp/IAO_EXT_0000455 rdfs/subClassOf ccp/IAO_EXT_0000427)
(ccp/IAO_EXT_0000455 rdfs/label ["DrugBank GO term record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000456
(ccp/IAO_EXT_0000456 rdf/type owl/Class)
(ccp/IAO_EXT_0000456 rdfs/subClassOf ccp/IAO_EXT_0000427)
(ccp/IAO_EXT_0000456 rdfs/label ["DrugBank drugbank organism record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000457
(ccp/IAO_EXT_0000457 rdf/type owl/Class)
(ccp/IAO_EXT_0000457 rdfs/subClassOf ccp/IAO_EXT_0000427)
(ccp/IAO_EXT_0000457 rdfs/label ["DrugBank SNP adverse drug reaction record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000458
(ccp/IAO_EXT_0000458 rdf/type owl/Class)
(ccp/IAO_EXT_0000458 rdfs/subClassOf ccp/IAO_EXT_0000215)
(ccp/IAO_EXT_0000458 rdfs/label ["Drugbank SNP effect record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000459
(ccp/IAO_EXT_0000459 rdf/type owl/Class)
(ccp/IAO_EXT_0000459 rdfs/subClassOf ccp/IAO_EXT_0000215)
(ccp/IAO_EXT_0000459 rdfs/label ["Drugbank reaction enzyme record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000460
(ccp/IAO_EXT_0000460 rdf/type owl/Class)
(ccp/IAO_EXT_0000460 rdfs/subClassOf ccp/IAO_EXT_0000215)
(ccp/IAO_EXT_0000460 rdfs/label ["Drugbank reaction element record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000461
(ccp/IAO_EXT_0000461 rdf/type owl/Class)
(ccp/IAO_EXT_0000461 rdfs/subClassOf ccp/IAO_EXT_0000215)
(ccp/IAO_EXT_0000461 rdfs/label ["Drugbank reaction record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000462
(ccp/IAO_EXT_0000462 rdf/type owl/Class)
(ccp/IAO_EXT_0000462 rdfs/subClassOf ccp/IAO_EXT_0000215)
(ccp/IAO_EXT_0000462 rdfs/label ["Drugbank pathway record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000463
(ccp/IAO_EXT_0000463 rdf/type owl/Class)
(ccp/IAO_EXT_0000463 rdfs/subClassOf ccp/IAO_EXT_0000215)
(ccp/IAO_EXT_0000463 rdfs/label ["Drugbank pathway drug record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000464
(ccp/IAO_EXT_0000464 rdf/type owl/Class)
(ccp/IAO_EXT_0000464 rdfs/subClassOf ccp/IAO_EXT_0000215)
(ccp/IAO_EXT_0000464 rdfs/label ["Drugbank category record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000465
(ccp/IAO_EXT_0000465 rdf/type owl/Class)
(ccp/IAO_EXT_0000465 rdfs/subClassOf ccp/IAO_EXT_0000215)
(ccp/IAO_EXT_0000465 rdfs/label ["Drugbank international brand record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000466
(ccp/IAO_EXT_0000466 rdf/type owl/Class)
(ccp/IAO_EXT_0000466 rdfs/subClassOf ccp/IAO_EXT_0000215)
(ccp/IAO_EXT_0000466 rdfs/label ["Drugbank synonym record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000467
(ccp/IAO_EXT_0000467 rdf/type owl/Class)
(ccp/IAO_EXT_0000467 rdfs/subClassOf ccp/IAO_EXT_0000215)
(ccp/IAO_EXT_0000467 rdfs/label ["Drugbank article record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000468
(ccp/IAO_EXT_0000468 rdf/type owl/Class)
(ccp/IAO_EXT_0000468 rdfs/subClassOf ccp/IAO_EXT_0000215)
(ccp/IAO_EXT_0000468 rdfs/label ["Drugbank textbook record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000469
(ccp/IAO_EXT_0000469 rdf/type owl/Class)
(ccp/IAO_EXT_0000469 rdfs/subClassOf ccp/IAO_EXT_0000215)
(ccp/IAO_EXT_0000469 rdfs/label ["Drugbank link record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000470
(ccp/IAO_EXT_0000470 rdf/type owl/Class)
(ccp/IAO_EXT_0000470 rdfs/subClassOf ccp/IAO_EXT_0000215)
(ccp/IAO_EXT_0000470 rdfs/label ["Drugbank product record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000471
(ccp/IAO_EXT_0000471 rdf/type owl/Class)
(ccp/IAO_EXT_0000471 rdfs/subClassOf ccp/IAO_EXT_0000215)
(ccp/IAO_EXT_0000471 rdfs/label ["Drugbank salt record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000472
(ccp/IAO_EXT_0000472 rdf/type owl/Class)
(ccp/IAO_EXT_0000472 rdfs/subClassOf ccp/IAO_EXT_0000215)
(ccp/IAO_EXT_0000472 rdfs/label ["Drugbank ATC code with level record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000473
(ccp/IAO_EXT_0000473 rdf/type owl/Class)
(ccp/IAO_EXT_0000473 rdfs/subClassOf ccp/IAO_EXT_0000215)
(ccp/IAO_EXT_0000473 rdfs/label ["Drugbank ATC code level record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000474
(ccp/IAO_EXT_0000474 rdf/type owl/Class)
(ccp/IAO_EXT_0000474 rdfs/subClassOf ccp/IAO_EXT_0000215)
(ccp/IAO_EXT_0000474 rdfs/label ["Drugbank dosage record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000475
(ccp/IAO_EXT_0000475 rdf/type owl/Class)
(ccp/IAO_EXT_0000475 rdfs/subClassOf ccp/IAO_EXT_0000215)
(ccp/IAO_EXT_0000475 rdfs/label ["Drugbank patent record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000476
(ccp/IAO_EXT_0000476 rdf/type owl/Class)
(ccp/IAO_EXT_0000476 rdfs/subClassOf ccp/IAO_EXT_0000215)
(ccp/IAO_EXT_0000476 rdfs/label ["Drugbank sequence record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000477
(ccp/IAO_EXT_0000477 rdf/type owl/Class)
(ccp/IAO_EXT_0000477 rdfs/subClassOf ccp/IAO_EXT_0000215)
(ccp/IAO_EXT_0000477 rdfs/label ["Drugbank property record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000478
(ccp/IAO_EXT_0000478 rdf/type owl/Class)
(ccp/IAO_EXT_0000478 rdfs/subClassOf ccp/IAO_EXT_0000215)
(ccp/IAO_EXT_0000478 rdfs/label ["Drugbank external link record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000479
(ccp/IAO_EXT_0000479 rdf/type owl/Class)
(ccp/IAO_EXT_0000479 rdfs/subClassOf ccp/IAO_EXT_0000215)
(ccp/IAO_EXT_0000479 rdfs/label ["Drugbank classification record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000480
(ccp/IAO_EXT_0000480 rdf/type owl/Class)
(ccp/IAO_EXT_0000480 rdfs/subClassOf ccp/IAO_EXT_0000215)
(ccp/IAO_EXT_0000480 rdfs/label ["Drugbank manufacturer record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000481
(ccp/IAO_EXT_0000481 rdf/type owl/Class)
(ccp/IAO_EXT_0000481 rdfs/subClassOf ccp/IAO_EXT_0000215)
(ccp/IAO_EXT_0000481 rdfs/label ["Drugbank price record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000482
(ccp/IAO_EXT_0000482 rdf/type owl/Class)
(ccp/IAO_EXT_0000482 rdfs/subClassOf ccp/IAO_EXT_0000215)
(ccp/IAO_EXT_0000482 rdfs/label ["Drugbank cost record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000483
(ccp/IAO_EXT_0000483 rdf/type owl/Class)
(ccp/IAO_EXT_0000483 rdfs/subClassOf ccp/IAO_EXT_0000215)
(ccp/IAO_EXT_0000483 rdfs/label ["Drugbank mixture record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000484
(ccp/IAO_EXT_0000484 rdf/type owl/Class)
(ccp/IAO_EXT_0000484 rdfs/subClassOf ccp/IAO_EXT_0000215)
(ccp/IAO_EXT_0000484 rdfs/label ["Drugbank drug taxonomy record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000485
(ccp/IAO_EXT_0000485 rdf/type owl/Class)
(ccp/IAO_EXT_0000485 rdfs/subClassOf ccp/IAO_EXT_0000215)
(ccp/IAO_EXT_0000485 rdfs/label ["Drugbank drug taxonomy substructure record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000486
(ccp/IAO_EXT_0000486 rdf/type owl/Class)
(ccp/IAO_EXT_0000486 rdfs/subClassOf ccp/IAO_EXT_0000215)
(ccp/IAO_EXT_0000486 rdfs/label ["Drugbank reference record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000487
(ccp/IAO_EXT_0000487 rdf/type owl/Class)
(ccp/IAO_EXT_0000487 rdfs/subClassOf ccp/IAO_EXT_0000215)
(ccp/IAO_EXT_0000487 rdfs/label ["Drugbank drug interaction record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000488
(ccp/IAO_EXT_0000488 rdf/type owl/Class)
(ccp/IAO_EXT_0000488 rdfs/subClassOf ccp/IAO_EXT_0000427)
(ccp/IAO_EXT_0000488 rdfs/label ["DrugBank SNP effect record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000489
(ccp/IAO_EXT_0000489 rdf/type owl/Class)
(ccp/IAO_EXT_0000489 rdfs/subClassOf ccp/IAO_EXT_0000427)
(ccp/IAO_EXT_0000489 rdfs/label ["DrugBank reaction enzyme record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000490
(ccp/IAO_EXT_0000490 rdf/type owl/Class)
(ccp/IAO_EXT_0000490 rdfs/subClassOf ccp/IAO_EXT_0000427)
(ccp/IAO_EXT_0000490 rdfs/label ["DrugBank reaction element record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000491
(ccp/IAO_EXT_0000491 rdf/type owl/Class)
(ccp/IAO_EXT_0000491 rdfs/subClassOf ccp/IAO_EXT_0000427)
(ccp/IAO_EXT_0000491 rdfs/label ["DrugBank reaction record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000492
(ccp/IAO_EXT_0000492 rdf/type owl/Class)
(ccp/IAO_EXT_0000492 rdfs/subClassOf ccp/IAO_EXT_0000427)
(ccp/IAO_EXT_0000492 rdfs/label ["DrugBank pathway record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000493
(ccp/IAO_EXT_0000493 rdf/type owl/Class)
(ccp/IAO_EXT_0000493 rdfs/subClassOf ccp/IAO_EXT_0000427)
(ccp/IAO_EXT_0000493 rdfs/label ["DrugBank pathway drug record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000494
(ccp/IAO_EXT_0000494 rdf/type owl/Class)
(ccp/IAO_EXT_0000494 rdfs/subClassOf ccp/IAO_EXT_0000427)
(ccp/IAO_EXT_0000494 rdfs/label ["DrugBank category record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000495
(ccp/IAO_EXT_0000495 rdf/type owl/Class)
(ccp/IAO_EXT_0000495 rdfs/subClassOf ccp/IAO_EXT_0000427)
(ccp/IAO_EXT_0000495 rdfs/label ["DrugBank international brand record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000496
(ccp/IAO_EXT_0000496 rdf/type owl/Class)
(ccp/IAO_EXT_0000496 rdfs/subClassOf ccp/IAO_EXT_0000427)
(ccp/IAO_EXT_0000496 rdfs/label ["DrugBank synonym record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000497
(ccp/IAO_EXT_0000497 rdf/type owl/Class)
(ccp/IAO_EXT_0000497 rdfs/subClassOf ccp/IAO_EXT_0000427)
(ccp/IAO_EXT_0000497 rdfs/label ["DrugBank article record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000498
(ccp/IAO_EXT_0000498 rdf/type owl/Class)
(ccp/IAO_EXT_0000498 rdfs/subClassOf ccp/IAO_EXT_0000427)
(ccp/IAO_EXT_0000498 rdfs/label ["DrugBank textbook record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000499
(ccp/IAO_EXT_0000499 rdf/type owl/Class)
(ccp/IAO_EXT_0000499 rdfs/subClassOf ccp/IAO_EXT_0000427)
(ccp/IAO_EXT_0000499 rdfs/label ["DrugBank link record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000500
(ccp/IAO_EXT_0000500 rdf/type owl/Class)
(ccp/IAO_EXT_0000500 rdfs/subClassOf ccp/IAO_EXT_0000427)
(ccp/IAO_EXT_0000500 rdfs/label ["DrugBank product record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000501
(ccp/IAO_EXT_0000501 rdf/type owl/Class)
(ccp/IAO_EXT_0000501 rdfs/subClassOf ccp/IAO_EXT_0000427)
(ccp/IAO_EXT_0000501 rdfs/label ["DrugBank salt record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000502
(ccp/IAO_EXT_0000502 rdf/type owl/Class)
(ccp/IAO_EXT_0000502 rdfs/subClassOf ccp/IAO_EXT_0000427)
(ccp/IAO_EXT_0000502 rdfs/label ["DrugBank ATC code with level record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000503
(ccp/IAO_EXT_0000503 rdf/type owl/Class)
(ccp/IAO_EXT_0000503 rdfs/subClassOf ccp/IAO_EXT_0000427)
(ccp/IAO_EXT_0000503 rdfs/label ["DrugBank dosage record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000504
(ccp/IAO_EXT_0000504 rdf/type owl/Class)
(ccp/IAO_EXT_0000504 rdfs/subClassOf ccp/IAO_EXT_0000427)
(ccp/IAO_EXT_0000504 rdfs/label ["DrugBank ATC code level record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000505
(ccp/IAO_EXT_0000505 rdf/type owl/Class)
(ccp/IAO_EXT_0000505 rdfs/subClassOf ccp/IAO_EXT_0000427)
(ccp/IAO_EXT_0000505 rdfs/label ["DrugBank patent record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000506
(ccp/IAO_EXT_0000506 rdf/type owl/Class)
(ccp/IAO_EXT_0000506 rdfs/subClassOf ccp/IAO_EXT_0000427)
(ccp/IAO_EXT_0000506 rdfs/label ["DrugBank sequence record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000507
(ccp/IAO_EXT_0000507 rdf/type owl/Class)
(ccp/IAO_EXT_0000507 rdfs/subClassOf ccp/IAO_EXT_0000427)
(ccp/IAO_EXT_0000507 rdfs/label ["DrugBank property record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000508
(ccp/IAO_EXT_0000508 rdf/type owl/Class)
(ccp/IAO_EXT_0000508 rdfs/subClassOf ccp/IAO_EXT_0000427)
(ccp/IAO_EXT_0000508 rdfs/label ["DrugBank external link record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000509
(ccp/IAO_EXT_0000509 rdf/type owl/Class)
(ccp/IAO_EXT_0000509 rdfs/subClassOf ccp/IAO_EXT_0000427)
(ccp/IAO_EXT_0000509 rdfs/label ["DrugBank packager record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000510
(ccp/IAO_EXT_0000510 rdf/type owl/Class)
(ccp/IAO_EXT_0000510 rdfs/subClassOf ccp/IAO_EXT_0000427)
(ccp/IAO_EXT_0000510 rdfs/label ["DrugBank classification record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000511
(ccp/IAO_EXT_0000511 rdf/type owl/Class)
(ccp/IAO_EXT_0000511 rdfs/subClassOf ccp/IAO_EXT_0000427)
(ccp/IAO_EXT_0000511 rdfs/label ["DrugBank manufacturer record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000512
(ccp/IAO_EXT_0000512 rdf/type owl/Class)
(ccp/IAO_EXT_0000512 rdfs/subClassOf ccp/IAO_EXT_0000427)
(ccp/IAO_EXT_0000512 rdfs/label ["DrugBank price record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000513
(ccp/IAO_EXT_0000513 rdf/type owl/Class)
(ccp/IAO_EXT_0000513 rdfs/subClassOf ccp/IAO_EXT_0000427)
(ccp/IAO_EXT_0000513 rdfs/label ["DrugBank cost record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000514
(ccp/IAO_EXT_0000514 rdf/type owl/Class)
(ccp/IAO_EXT_0000514 rdfs/subClassOf ccp/IAO_EXT_0000427)
(ccp/IAO_EXT_0000514 rdfs/label ["DrugBank mixture record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000515
(ccp/IAO_EXT_0000515 rdf/type owl/Class)
(ccp/IAO_EXT_0000515 rdfs/subClassOf ccp/IAO_EXT_0000427)
(ccp/IAO_EXT_0000515 rdfs/label ["DrugBank drug taxonomy record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000516
(ccp/IAO_EXT_0000516 rdf/type owl/Class)
(ccp/IAO_EXT_0000516 rdfs/subClassOf ccp/IAO_EXT_0000427)
(ccp/IAO_EXT_0000516 rdfs/label ["DrugBank drug taxonomy substructure record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000517
(ccp/IAO_EXT_0000517 rdf/type owl/Class)
(ccp/IAO_EXT_0000517 rdfs/subClassOf ccp/IAO_EXT_0000427)
(ccp/IAO_EXT_0000517 rdfs/label ["DrugBank reference record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000518
(ccp/IAO_EXT_0000518 rdf/type owl/Class)
(ccp/IAO_EXT_0000518 rdfs/subClassOf ccp/IAO_EXT_0000427)
(ccp/IAO_EXT_0000518 rdfs/label ["DrugBank drug interaction record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000519
(ccp/IAO_EXT_0000519 rdf/type owl/Class)
(ccp/IAO_EXT_0000519 rdfs/subClassOf ccp/IAO_EXT_0000448)
(ccp/IAO_EXT_0000519 rdfs/label ["Drugbank polypeptide record - amino acid sequence field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000520
(ccp/IAO_EXT_0000520 rdf/type owl/Class)
(ccp/IAO_EXT_0000520 rdfs/subClassOf ccp/IAO_EXT_0000448)
(ccp/IAO_EXT_0000520 obo/IAO_0000115 ["Location of the given protein or macromolecule inside or around the cell (cytoplasm, nucleus, membrane, etc.)"])
(ccp/IAO_EXT_0000520 rdfs/label ["Drugbank polypeptide record - cellular location field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000521
(ccp/IAO_EXT_0000521 rdf/type owl/Class)
(ccp/IAO_EXT_0000521 rdfs/subClassOf ccp/IAO_EXT_0000448)
(ccp/IAO_EXT_0000521 obo/IAO_0000115 ["Location of the molecule on any of the 23 human chromosomes (no location is given if the molecule is bacterial)"])
(ccp/IAO_EXT_0000521 rdfs/label ["Drugbank polypeptide record - chromosome location field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000522
(ccp/IAO_EXT_0000522 rdf/type owl/Class)
(ccp/IAO_EXT_0000522 rdfs/subClassOf ccp/IAO_EXT_0000448)
(ccp/IAO_EXT_0000522 rdfs/label ["Drugbank polypeptide record - external identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000523
(ccp/IAO_EXT_0000523 rdf/type owl/Class)
(ccp/IAO_EXT_0000523 rdfs/subClassOf ccp/IAO_EXT_0000448)
(ccp/IAO_EXT_0000523 rdfs/label ["Drugbank polypeptide record - gene name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000524
(ccp/IAO_EXT_0000524 rdf/type owl/Class)
(ccp/IAO_EXT_0000524 rdfs/subClassOf ccp/IAO_EXT_0000448)
(ccp/IAO_EXT_0000524 obo/IAO_0000115 ["Short 3-4 word summary of the primary functions"])
(ccp/IAO_EXT_0000524 rdfs/label ["Drugbank polypeptide record - general function field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000525
(ccp/IAO_EXT_0000525 rdf/type owl/Class)
(ccp/IAO_EXT_0000525 rdfs/subClassOf ccp/IAO_EXT_0000448)
(ccp/IAO_EXT_0000525 obo/IAO_0000115 ["DNA sequence (from cDNA) of the given molecule"])
(ccp/IAO_EXT_0000525 rdfs/label ["Drugbank polypeptide record - gene sequence field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000526
(ccp/IAO_EXT_0000526 rdf/type owl/Class)
(ccp/IAO_EXT_0000526 rdfs/subClassOf ccp/IAO_EXT_0000448)
(ccp/IAO_EXT_0000526 obo/IAO_0000115 ["Gene ontology classification including function, cellular process and location"])
(ccp/IAO_EXT_0000526 rdfs/label ["Drugbank polypeptide record - go classifiers field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000527
(ccp/IAO_EXT_0000527 rdf/type owl/Class)
(ccp/IAO_EXT_0000527 rdfs/subClassOf ccp/IAO_EXT_0000448)
(ccp/IAO_EXT_0000527 rdfs/label ["Drugbank polypeptide record - identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000528
(ccp/IAO_EXT_0000528 rdf/type owl/Class)
(ccp/IAO_EXT_0000528 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000528 rdfs/label ["identifier field identifier value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000529
(ccp/IAO_EXT_0000529 rdf/type owl/Class)
(ccp/IAO_EXT_0000529 rdfs/subClassOf ccp/IAO_EXT_0000448)
(ccp/IAO_EXT_0000529 obo/IAO_0000115 ["More detailed location of the chromosomal position of the gene"])
(ccp/IAO_EXT_0000529 rdfs/label ["Drugbank polypeptide record - locus field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000530
(ccp/IAO_EXT_0000530 rdf/type owl/Class)
(ccp/IAO_EXT_0000530 rdfs/subClassOf ccp/IAO_EXT_0000427)
(ccp/IAO_EXT_0000530 rdfs/label ["Drugbank enzyme record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000531
(ccp/IAO_EXT_0000531 rdf/type owl/Class)
(ccp/IAO_EXT_0000531 rdfs/subClassOf ccp/IAO_EXT_0000448)
(ccp/IAO_EXT_0000531 obo/IAO_0000115 ["Molecular weight given in Daltons or g/mol"])
(ccp/IAO_EXT_0000531 rdfs/label ["Drugbank polypeptide record - molecular weight field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000532
(ccp/IAO_EXT_0000532 rdf/type owl/Class)
(ccp/IAO_EXT_0000532 rdfs/subClassOf ccp/IAO_EXT_0000448)
(ccp/IAO_EXT_0000532 rdfs/label ["Drugbank polypeptide record - name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000533
(ccp/IAO_EXT_0000533 rdf/type owl/Class)
(ccp/IAO_EXT_0000533 rdfs/subClassOf ccp/IAO_EXT_0000448)
(ccp/IAO_EXT_0000533 rdfs/label ["Drugbank polypeptide record - organism field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000534
(ccp/IAO_EXT_0000534 rdf/type owl/Class)
(ccp/IAO_EXT_0000534 rdfs/subClassOf ccp/IAO_EXT_0000448)
(ccp/IAO_EXT_0000534 obo/IAO_0000115 ["Names and ID numbers of PFAM domains"])
(ccp/IAO_EXT_0000534 rdfs/label ["Drugbank polypeptide record - PFAM field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000535
(ccp/IAO_EXT_0000535 rdf/type owl/Class)
(ccp/IAO_EXT_0000535 rdfs/subClassOf ccp/IAO_EXT_0000448)
(ccp/IAO_EXT_0000535 obo/IAO_0000115 ["Location of signal peptide or other localization signals in the sequence"])
(ccp/IAO_EXT_0000535 rdfs/label ["Drugbank polypeptide record - signal regions field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000536
(ccp/IAO_EXT_0000536 rdf/type owl/Class)
(ccp/IAO_EXT_0000536 rdfs/subClassOf ccp/IAO_EXT_0000448)
(ccp/IAO_EXT_0000536 rdfs/label ["Drugbank polypeptide record - source field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000537
(ccp/IAO_EXT_0000537 rdf/type owl/Class)
(ccp/IAO_EXT_0000537 rdfs/subClassOf ccp/IAO_EXT_0000448)
(ccp/IAO_EXT_0000537 obo/IAO_0000115 ["Detailed 30-40 word summary of the specific functions"])
(ccp/IAO_EXT_0000537 rdfs/label ["Drugbank polypeptide record - specific function field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000538
(ccp/IAO_EXT_0000538 rdf/type owl/Class)
(ccp/IAO_EXT_0000538 rdfs/subClassOf ccp/IAO_EXT_0000448)
(ccp/IAO_EXT_0000538 rdfs/label ["Drugbank polypeptide record - synonyms field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000539
(ccp/IAO_EXT_0000539 rdf/type owl/Class)
(ccp/IAO_EXT_0000539 rdfs/subClassOf ccp/IAO_EXT_0000448)
(ccp/IAO_EXT_0000539 obo/IAO_0000115 ["Theoretical isoelectric point"])
(ccp/IAO_EXT_0000539 rdfs/label ["Drugbank polypeptide record - theoretical pi field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000540
(ccp/IAO_EXT_0000540 rdf/type owl/Class)
(ccp/IAO_EXT_0000540 rdfs/subClassOf ccp/IAO_EXT_0000448)
(ccp/IAO_EXT_0000540 obo/IAO_0000115 ["Number and location of the transmembrane helices"])
(ccp/IAO_EXT_0000540 rdfs/label ["Drugbank polypeptide record - transmembrane regions field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000541
(ccp/IAO_EXT_0000541 rdf/type owl/Class)
(ccp/IAO_EXT_0000541 rdfs/subClassOf ccp/IAO_EXT_0000450)
(ccp/IAO_EXT_0000541 obo/IAO_0000115 ["Names of PFAM domains"])
(ccp/IAO_EXT_0000541 rdfs/label ["Drugbank PFAM record - name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000542
(ccp/IAO_EXT_0000542 rdf/type owl/Class)
(ccp/IAO_EXT_0000542 rdfs/subClassOf ccp/IAO_EXT_0000450)
(ccp/IAO_EXT_0000542 obo/IAO_0000115 ["ID numbers of PFAM domains"])
(ccp/IAO_EXT_0000542 rdfs/label ["Drugbank PFAM record - identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000543
(ccp/IAO_EXT_0000543 rdf/type owl/Class)
(ccp/IAO_EXT_0000543 rdfs/subClassOf ccp/IAO_EXT_0000452)
(ccp/IAO_EXT_0000543 rdfs/label ["Drugbank GO term record - name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000544
(ccp/IAO_EXT_0000544 rdf/type owl/Class)
(ccp/IAO_EXT_0000544 rdfs/subClassOf ccp/IAO_EXT_0000452)
(ccp/IAO_EXT_0000544 rdfs/label ["Drugbank GO term record - namespace field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000545
(ccp/IAO_EXT_0000545 rdf/type owl/Class)
(ccp/IAO_EXT_0000545 rdfs/subClassOf ccp/IAO_EXT_0000453)
(ccp/IAO_EXT_0000545 rdfs/label ["Drugbank drugbank organism record - name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000546
(ccp/IAO_EXT_0000546 rdf/type owl/Class)
(ccp/IAO_EXT_0000546 rdfs/subClassOf ccp/IAO_EXT_0000453)
(ccp/IAO_EXT_0000546 rdfs/label ["Drugbank drugbank organism record - taxonomy identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000547
(ccp/IAO_EXT_0000547 rdf/type owl/Class)
(ccp/IAO_EXT_0000547 rdfs/subClassOf ccp/IAO_EXT_0000454)
(ccp/IAO_EXT_0000547 rdfs/label ["Drugbank SNP adverse drug reaction record - allele field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000548
(ccp/IAO_EXT_0000548 rdf/type owl/Class)
(ccp/IAO_EXT_0000548 rdfs/subClassOf ccp/IAO_EXT_0000454)
(ccp/IAO_EXT_0000548 rdfs/label ["Drugbank SNP adverse drug reaction record - adverse reaction field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000549
(ccp/IAO_EXT_0000549 rdf/type owl/Class)
(ccp/IAO_EXT_0000549 rdfs/subClassOf ccp/IAO_EXT_0000454)
(ccp/IAO_EXT_0000549 rdfs/label ["Drugbank SNP adverse drug reaction record - description field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000550
(ccp/IAO_EXT_0000550 rdf/type owl/Class)
(ccp/IAO_EXT_0000550 rdfs/subClassOf ccp/IAO_EXT_0000454)
(ccp/IAO_EXT_0000550 rdfs/label ["Drugbank SNP adverse drug reaction record - gene symbol field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000551
(ccp/IAO_EXT_0000551 rdf/type owl/Class)
(ccp/IAO_EXT_0000551 rdfs/subClassOf ccp/IAO_EXT_0000454)
(ccp/IAO_EXT_0000551 rdfs/label ["Drugbank SNP adverse drug reaction record - protein name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000552
(ccp/IAO_EXT_0000552 rdf/type owl/Class)
(ccp/IAO_EXT_0000552 rdfs/subClassOf ccp/IAO_EXT_0000454)
(ccp/IAO_EXT_0000552 rdfs/label ["Drugbank SNP adverse drug reaction record - PubMed identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000554
(ccp/IAO_EXT_0000554 rdf/type owl/Class)
(ccp/IAO_EXT_0000554 rdfs/subClassOf ccp/IAO_EXT_0000454)
(ccp/IAO_EXT_0000554 rdfs/label ["Drugbank SNP adverse drug reaction record - reference SNP identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000555
(ccp/IAO_EXT_0000555 rdf/type owl/Class)
(ccp/IAO_EXT_0000555 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000555 rdfs/label ["reference SNP identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000557
(ccp/IAO_EXT_0000557 rdf/type owl/Class)
(ccp/IAO_EXT_0000557 rdfs/subClassOf ccp/IAO_EXT_0000454)
(ccp/IAO_EXT_0000557 rdfs/label ["Drugbank SNP adverse drug reaction record - UniProt identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000558
(ccp/IAO_EXT_0000558 rdf/type owl/Class)
(ccp/IAO_EXT_0000558 rdfs/subClassOf ccp/IAO_EXT_0000458)
(ccp/IAO_EXT_0000558 rdfs/label ["Drugbank SNP effect record - allele field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000559
(ccp/IAO_EXT_0000559 rdf/type owl/Class)
(ccp/IAO_EXT_0000559 rdfs/subClassOf ccp/IAO_EXT_0000458)
(ccp/IAO_EXT_0000559 rdfs/label ["Drugbank SNP effect record - defining change field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000560
(ccp/IAO_EXT_0000560 rdf/type owl/Class)
(ccp/IAO_EXT_0000560 rdfs/subClassOf ccp/IAO_EXT_0000458)
(ccp/IAO_EXT_0000560 rdfs/label ["Drugbank SNP effect record - description field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000561
(ccp/IAO_EXT_0000561 rdf/type owl/Class)
(ccp/IAO_EXT_0000561 rdfs/subClassOf ccp/IAO_EXT_0000458)
(ccp/IAO_EXT_0000561 rdfs/label ["Drugbank SNP effect record - gene symbol field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000562
(ccp/IAO_EXT_0000562 rdf/type owl/Class)
(ccp/IAO_EXT_0000562 rdfs/subClassOf ccp/IAO_EXT_0000458)
(ccp/IAO_EXT_0000562 rdfs/label ["Drugbank SNP effect record - protein name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000563
(ccp/IAO_EXT_0000563 rdf/type owl/Class)
(ccp/IAO_EXT_0000563 rdfs/subClassOf ccp/IAO_EXT_0000458)
(ccp/IAO_EXT_0000563 rdfs/label ["Drugbank SNP effect record - PubMed identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000564
(ccp/IAO_EXT_0000564 rdf/type owl/Class)
(ccp/IAO_EXT_0000564 rdfs/subClassOf ccp/IAO_EXT_0000458)
(ccp/IAO_EXT_0000564 rdfs/label ["Drugbank SNP effect record - reference SNP identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000565
(ccp/IAO_EXT_0000565 rdf/type owl/Class)
(ccp/IAO_EXT_0000565 rdfs/subClassOf ccp/IAO_EXT_0000458)
(ccp/IAO_EXT_0000565 rdfs/label ["Drugbank SNP effect record - UniProt identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000566
(ccp/IAO_EXT_0000566 rdf/type owl/Class)
(ccp/IAO_EXT_0000566 rdfs/subClassOf ccp/IAO_EXT_0000459)
(ccp/IAO_EXT_0000566 rdfs/label ["Drugbank reaction enzyme record - name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000567
(ccp/IAO_EXT_0000567 rdf/type owl/Class)
(ccp/IAO_EXT_0000567 rdfs/subClassOf ccp/IAO_EXT_0000459)
(ccp/IAO_EXT_0000567 obo/IAO_0000115 ["Unique DrugBank accession number consisting of a 2 letter prefix (DB) and a 5 number suffix. This ID is used to access the drug entry via the URL. If an entry is deleted, it's DrugBank ID will not be reused."])
(ccp/IAO_EXT_0000567 rdfs/label ["Drugbank reaction enzyme record - drugbank identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000568
(ccp/IAO_EXT_0000568 rdf/type owl/Class)
(ccp/IAO_EXT_0000568 rdfs/subClassOf ccp/IAO_EXT_0000459)
(ccp/IAO_EXT_0000568 rdfs/label ["Drugbank reaction enzyme record - UniProt identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000569
(ccp/IAO_EXT_0000569 rdf/type owl/Class)
(ccp/IAO_EXT_0000569 rdfs/subClassOf ccp/IAO_EXT_0000460)
(ccp/IAO_EXT_0000569 rdfs/label ["Drugbank reaction element record - name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000570
(ccp/IAO_EXT_0000570 rdf/type owl/Class)
(ccp/IAO_EXT_0000570 rdfs/subClassOf ccp/IAO_EXT_0000460)
(ccp/IAO_EXT_0000570 obo/IAO_0000115 ["Unique DrugBank accession number consisting of a 2 letter prefix (DB) and a 5 number suffix. This ID is used to access the drug entry via the URL. If an entry is deleted, it's DrugBank ID will not be reused."])
(ccp/IAO_EXT_0000570 rdfs/label ["Drugbank reaction element record - drugbank identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000571
(ccp/IAO_EXT_0000571 rdf/type owl/Class)
(ccp/IAO_EXT_0000571 rdfs/subClassOf ccp/IAO_EXT_0000461)
(ccp/IAO_EXT_0000571 obo/IAO_0000115 ["Amino acid sequence"])
(ccp/IAO_EXT_0000571 rdfs/label ["Drugbank reaction record - sequence field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000572
(ccp/IAO_EXT_0000572 rdf/type owl/Class)
(ccp/IAO_EXT_0000572 rdfs/subClassOf ccp/IAO_EXT_0000461)
(ccp/IAO_EXT_0000572 rdfs/label ["Drugbank reaction record - left element field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000573
(ccp/IAO_EXT_0000573 rdf/type owl/Class)
(ccp/IAO_EXT_0000573 rdfs/subClassOf ccp/IAO_EXT_0000461)
(ccp/IAO_EXT_0000573 rdfs/label ["Drugbank reaction record - right element field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000574
(ccp/IAO_EXT_0000574 rdf/type owl/Class)
(ccp/IAO_EXT_0000574 rdfs/subClassOf ccp/IAO_EXT_0000461)
(ccp/IAO_EXT_0000574 rdfs/label ["Drugbank reaction record - reaction enzymes field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000575
(ccp/IAO_EXT_0000575 rdf/type owl/Class)
(ccp/IAO_EXT_0000575 rdfs/subClassOf ccp/IAO_EXT_0000576)
(ccp/IAO_EXT_0000575 rdfs/label ["left element field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000576
(ccp/IAO_EXT_0000576 rdf/type owl/Class)
(ccp/IAO_EXT_0000576 rdfs/subClassOf ccp/IAO_EXT_0000001)
(ccp/IAO_EXT_0000576 rdfs/label ["element field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000577
(ccp/IAO_EXT_0000577 rdf/type owl/Class)
(ccp/IAO_EXT_0000577 rdfs/subClassOf ccp/IAO_EXT_0000576)
(ccp/IAO_EXT_0000577 rdfs/label ["right element field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000578
(ccp/IAO_EXT_0000578 rdf/type owl/Class)
(ccp/IAO_EXT_0000578 rdfs/subClassOf ccp/IAO_EXT_0000462)
(ccp/IAO_EXT_0000578 rdfs/label ["Drugbank pathway record - name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000579
(ccp/IAO_EXT_0000579 rdf/type owl/Class)
(ccp/IAO_EXT_0000579 rdfs/subClassOf ccp/IAO_EXT_0000462)
(ccp/IAO_EXT_0000579 obo/IAO_0000115 ["Key pathways or processes (from SMPD) that the given molecule is involved in"])
(ccp/IAO_EXT_0000579 rdfs/label ["Drugbank pathway record - SMPDB identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000580
(ccp/IAO_EXT_0000580 rdf/type owl/Class)
(ccp/IAO_EXT_0000580 rdfs/subClassOf ccp/IAO_EXT_0000462)
(ccp/IAO_EXT_0000580 obo/IAO_0000115 ["Drug pathways in which the given drug is involved"])
(ccp/IAO_EXT_0000580 rdfs/label ["Drugbank pathway record - pathway drugs field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000581
(ccp/IAO_EXT_0000581 rdf/type owl/Class)
(ccp/IAO_EXT_0000581 rdfs/subClassOf ccp/IAO_EXT_0000462)
(ccp/IAO_EXT_0000581 rdfs/label ["Drugbank pathway record - pathway enzymes field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000582
(ccp/IAO_EXT_0000582 rdf/type owl/Class)
(ccp/IAO_EXT_0000582 rdfs/subClassOf ccp/IAO_EXT_0000463)
(ccp/IAO_EXT_0000582 obo/IAO_0000115 ["Drug pathways in which the given drug is involved"])
(ccp/IAO_EXT_0000582 rdfs/label ["Drugbank pathway drug record - name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000583
(ccp/IAO_EXT_0000583 rdf/type owl/Class)
(ccp/IAO_EXT_0000583 rdfs/subClassOf ccp/IAO_EXT_0000463)
(ccp/IAO_EXT_0000583 obo/IAO_0000115 ["Unique DrugBank accession number consisting of a 2 letter prefix (DB) and a 5 number suffix. This ID is used to access the drug entry via the URL. If an entry is deleted, it's DrugBank ID will not be reused."])
(ccp/IAO_EXT_0000583 rdfs/label ["Drugbank pathway drug record - drugbank identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000584
(ccp/IAO_EXT_0000584 rdf/type owl/Class)
(ccp/IAO_EXT_0000584 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000584 rdfs/label ["SMPDB identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000585
(ccp/IAO_EXT_0000585 rdf/type owl/Class)
(ccp/IAO_EXT_0000585 rdfs/subClassOf ccp/IAO_EXT_0000464)
(ccp/IAO_EXT_0000585 rdfs/label ["Drugbank category record - MeSH identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000586
(ccp/IAO_EXT_0000586 rdf/type owl/Class)
(ccp/IAO_EXT_0000586 rdfs/subClassOf ccp/IAO_EXT_0000464)
(ccp/IAO_EXT_0000586 obo/IAO_0000115 ["Therapeutic category or general category of drug (anti-convulsant, antibacterial, etc.)"])
(ccp/IAO_EXT_0000586 rdfs/label ["Drugbank category record - category field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000587
(ccp/IAO_EXT_0000587 rdf/type owl/Class)
(ccp/IAO_EXT_0000587 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000587 rdfs/label ["MESH IDs identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000588
(ccp/IAO_EXT_0000588 rdf/type owl/Class)
(ccp/IAO_EXT_0000588 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000588 rdfs/label ["ISBN identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000589
(ccp/IAO_EXT_0000589 rdf/type owl/Class)
(ccp/IAO_EXT_0000589 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000589 rdfs/label ["DPD identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000590
(ccp/IAO_EXT_0000590 rdf/type owl/Class)
(ccp/IAO_EXT_0000590 rdfs/subClassOf ccp/IAO_EXT_0000050)
(ccp/IAO_EXT_0000590 rdfs/label ["ended marketing on field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000591
(ccp/IAO_EXT_0000591 rdf/type owl/Class)
(ccp/IAO_EXT_0000591 rdfs/subClassOf ccp/IAO_EXT_0000050)
(ccp/IAO_EXT_0000591 rdfs/label ["started marketing on field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000592
(ccp/IAO_EXT_0000592 rdf/type owl/Class)
(ccp/IAO_EXT_0000592 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000592 rdfs/label ["NDC ID identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000593
(ccp/IAO_EXT_0000593 rdf/type owl/Class)
(ccp/IAO_EXT_0000593 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000593 rdfs/label ["INCHI key identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000594
(ccp/IAO_EXT_0000594 rdf/type owl/Class)
(ccp/IAO_EXT_0000594 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000594 rdfs/label ["primary ID identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000595
(ccp/IAO_EXT_0000595 rdf/type owl/Class)
(ccp/IAO_EXT_0000595 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000595 rdfs/label ["secondary identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000596
(ccp/IAO_EXT_0000596 rdf/type owl/Class)
(ccp/IAO_EXT_0000596 rdfs/subClassOf ccp/IAO_EXT_0000001)
(ccp/IAO_EXT_0000596 rdfs/label ["form field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000597
(ccp/IAO_EXT_0000597 rdf/type owl/Class)
(ccp/IAO_EXT_0000597 rdfs/subClassOf ccp/IAO_EXT_0000050)
(ccp/IAO_EXT_0000597 rdfs/label ["date expires field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000598
(ccp/IAO_EXT_0000598 rdf/type owl/Class)
(ccp/IAO_EXT_0000598 rdfs/subClassOf ccp/IAO_EXT_0000465)
(ccp/IAO_EXT_0000598 rdfs/label ["Drugbank international brand record - company field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000599
(ccp/IAO_EXT_0000599 rdf/type owl/Class)
(ccp/IAO_EXT_0000599 rdfs/subClassOf ccp/IAO_EXT_0000465)
(ccp/IAO_EXT_0000599 obo/IAO_0000115 ["Brand names from different manufacturers"])
(ccp/IAO_EXT_0000599 rdfs/label ["Drugbank international brand record - name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000600
(ccp/IAO_EXT_0000600 rdf/type owl/Class)
(ccp/IAO_EXT_0000600 rdfs/subClassOf ccp/IAO_EXT_0000466)
(ccp/IAO_EXT_0000600 rdfs/label ["Drugbank synonym record - coder field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000601
(ccp/IAO_EXT_0000601 rdf/type owl/Class)
(ccp/IAO_EXT_0000601 rdfs/subClassOf ccp/IAO_EXT_0000466)
(ccp/IAO_EXT_0000601 rdfs/label ["Drugbank synonym record - language field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000602
(ccp/IAO_EXT_0000602 rdf/type owl/Class)
(ccp/IAO_EXT_0000602 rdfs/subClassOf ccp/IAO_EXT_0000466)
(ccp/IAO_EXT_0000602 obo/IAO_0000115 ["Alternate names of the drug"])
(ccp/IAO_EXT_0000602 rdfs/label ["Drugbank synonym record - synonym field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000603
(ccp/IAO_EXT_0000603 rdf/type owl/Class)
(ccp/IAO_EXT_0000603 rdfs/subClassOf ccp/IAO_EXT_0000467)
(ccp/IAO_EXT_0000603 rdfs/label ["Drugbank article  record - citation field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000604
(ccp/IAO_EXT_0000604 rdf/type owl/Class)
(ccp/IAO_EXT_0000604 rdfs/subClassOf ccp/IAO_EXT_0000467)
(ccp/IAO_EXT_0000604 rdfs/label ["Drugbank article record - PubMed identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000605
(ccp/IAO_EXT_0000605 rdf/type owl/Class)
(ccp/IAO_EXT_0000605 rdfs/subClassOf ccp/IAO_EXT_0000468)
(ccp/IAO_EXT_0000605 rdfs/label ["Drugbank textbook record - citation field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000606
(ccp/IAO_EXT_0000606 rdf/type owl/Class)
(ccp/IAO_EXT_0000606 rdfs/subClassOf ccp/IAO_EXT_0000468)
(ccp/IAO_EXT_0000606 rdfs/label ["Drugbank textbook record - ISBN field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000607
(ccp/IAO_EXT_0000607 rdf/type owl/Class)
(ccp/IAO_EXT_0000607 rdfs/subClassOf ccp/IAO_EXT_0000469)
(ccp/IAO_EXT_0000607 rdfs/label ["Drugbank link record - title field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000608
(ccp/IAO_EXT_0000608 rdf/type owl/Class)
(ccp/IAO_EXT_0000608 rdfs/subClassOf ccp/IAO_EXT_0000469)
(ccp/IAO_EXT_0000608 rdfs/label ["Drugbank link record - URL field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000609
(ccp/IAO_EXT_0000609 rdf/type owl/Class)
(ccp/IAO_EXT_0000609 rdfs/subClassOf ccp/IAO_EXT_0000470)
(ccp/IAO_EXT_0000609 rdfs/label ["Drugbank product record - country field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000610
(ccp/IAO_EXT_0000610 rdf/type owl/Class)
(ccp/IAO_EXT_0000610 rdfs/subClassOf ccp/IAO_EXT_0000470)
(ccp/IAO_EXT_0000610 obo/IAO_0000115 ["How the drug is dispensed (tablets, capsules, solutions, etc.)"])
(ccp/IAO_EXT_0000610 rdfs/label ["Drugbank product record - dosage form field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000611
(ccp/IAO_EXT_0000611 rdf/type owl/Class)
(ccp/IAO_EXT_0000611 rdfs/subClassOf ccp/IAO_EXT_0000470)
(ccp/IAO_EXT_0000611 rdfs/label ["Drugbank product record - DPD ID field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000612
(ccp/IAO_EXT_0000612 rdf/type owl/Class)
(ccp/IAO_EXT_0000612 rdfs/subClassOf ccp/IAO_EXT_0000470)
(ccp/IAO_EXT_0000612 rdfs/label ["Drugbank product record - ended marketing on field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000613
(ccp/IAO_EXT_0000613 rdf/type owl/Class)
(ccp/IAO_EXT_0000613 rdfs/subClassOf ccp/IAO_EXT_0000470)
(ccp/IAO_EXT_0000613 rdfs/label ["Drugbank product record - FDA application number field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000614
(ccp/IAO_EXT_0000614 rdf/type owl/Class)
(ccp/IAO_EXT_0000614 rdfs/subClassOf ccp/IAO_EXT_0000470)
(ccp/IAO_EXT_0000614 rdfs/label ["Drugbank product record - labeller field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000615
(ccp/IAO_EXT_0000615 rdf/type owl/Class)
(ccp/IAO_EXT_0000615 rdfs/subClassOf ccp/IAO_EXT_0000470)
(ccp/IAO_EXT_0000615 rdfs/label ["Drugbank product record - name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000616
(ccp/IAO_EXT_0000616 rdf/type owl/Class)
(ccp/IAO_EXT_0000616 rdfs/subClassOf ccp/IAO_EXT_0000470)
(ccp/IAO_EXT_0000616 rdfs/label ["Drugbank product record - NDC identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000617
(ccp/IAO_EXT_0000617 rdf/type owl/Class)
(ccp/IAO_EXT_0000617 rdfs/subClassOf ccp/IAO_EXT_0000470)
(ccp/IAO_EXT_0000617 rdfs/label ["Drugbank product record - NDC product code field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000618
(ccp/IAO_EXT_0000618 rdf/type owl/Class)
(ccp/IAO_EXT_0000618 rdfs/subClassOf ccp/IAO_EXT_0000470)
(ccp/IAO_EXT_0000618 rdfs/label ["Drugbank product record - route field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000619
(ccp/IAO_EXT_0000619 rdf/type owl/Class)
(ccp/IAO_EXT_0000619 rdfs/subClassOf ccp/IAO_EXT_0000470)
(ccp/IAO_EXT_0000619 rdfs/label ["Drugbank product record - source field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000620
(ccp/IAO_EXT_0000620 rdf/type owl/Class)
(ccp/IAO_EXT_0000620 rdfs/subClassOf ccp/IAO_EXT_0000470)
(ccp/IAO_EXT_0000620 rdfs/label ["Drugbank product record - started marketing on field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000621
(ccp/IAO_EXT_0000621 rdf/type owl/Class)
(ccp/IAO_EXT_0000621 rdfs/subClassOf ccp/IAO_EXT_0000470)
(ccp/IAO_EXT_0000621 rdfs/label ["Drugbank product record - strength field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000622
(ccp/IAO_EXT_0000622 rdf/type owl/Class)
(ccp/IAO_EXT_0000622 rdfs/subClassOf ccp/IAO_EXT_0000471)
(ccp/IAO_EXT_0000622 obo/IAO_0000115 ["Chemical Abstract Service identification number"])
(ccp/IAO_EXT_0000622 rdfs/label ["Drugbank salt record - CAS number field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000623
(ccp/IAO_EXT_0000623 rdf/type owl/Class)
(ccp/IAO_EXT_0000623 rdfs/subClassOf ccp/IAO_EXT_0000471)
(ccp/IAO_EXT_0000623 rdfs/label ["Drugbank salt record - name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000624
(ccp/IAO_EXT_0000624 rdf/type owl/Class)
(ccp/IAO_EXT_0000624 rdfs/subClassOf ccp/IAO_EXT_0000471)
(ccp/IAO_EXT_0000624 obo/IAO_0000115 ["Standard InChI key"])
(ccp/IAO_EXT_0000624 rdfs/label ["Drugbank salt record - InChI key field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000625
(ccp/IAO_EXT_0000625 rdf/type owl/Class)
(ccp/IAO_EXT_0000625 rdfs/subClassOf ccp/IAO_EXT_0000471)
(ccp/IAO_EXT_0000625 rdfs/label ["Drugbank salt record - primary identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000626
(ccp/IAO_EXT_0000626 rdf/type owl/Class)
(ccp/IAO_EXT_0000626 rdfs/subClassOf ccp/IAO_EXT_0000471)
(ccp/IAO_EXT_0000626 rdfs/label ["Drugbank salt record - secondary identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000627
(ccp/IAO_EXT_0000627 rdf/type owl/Class)
(ccp/IAO_EXT_0000627 rdfs/subClassOf ccp/IAO_EXT_0000472)
(ccp/IAO_EXT_0000627 obo/IAO_0000115 ["WHO drug classification system (ATC) identifiers"])
(ccp/IAO_EXT_0000627 rdfs/label ["Drugbank ATC code with level record - ATC code field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000628
(ccp/IAO_EXT_0000628 rdf/type owl/Class)
(ccp/IAO_EXT_0000628 rdfs/subClassOf ccp/IAO_EXT_0000472)
(ccp/IAO_EXT_0000628 rdfs/label ["Drugbank ATC code with level record - levels field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000629
(ccp/IAO_EXT_0000629 rdf/type owl/Class)
(ccp/IAO_EXT_0000629 rdfs/subClassOf ccp/IAO_EXT_0000473)
(ccp/IAO_EXT_0000629 obo/IAO_0000115 ["WHO drug classification system (ATC) identifiers"])
(ccp/IAO_EXT_0000629 rdfs/label ["Drugbank atc code level record - code field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000630
(ccp/IAO_EXT_0000630 rdf/type owl/Class)
(ccp/IAO_EXT_0000630 rdfs/subClassOf ccp/IAO_EXT_0000473)
(ccp/IAO_EXT_0000630 rdfs/label ["Drugbank atc code level record - name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000631
(ccp/IAO_EXT_0000631 rdf/type owl/Class)
(ccp/IAO_EXT_0000631 rdfs/subClassOf ccp/IAO_EXT_0000474)
(ccp/IAO_EXT_0000631 rdfs/label ["Drugbank dosage record - form field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000632
(ccp/IAO_EXT_0000632 rdf/type owl/Class)
(ccp/IAO_EXT_0000632 rdfs/subClassOf ccp/IAO_EXT_0000474)
(ccp/IAO_EXT_0000632 rdfs/label ["Drugbank dosage record - route field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000633
(ccp/IAO_EXT_0000633 rdf/type owl/Class)
(ccp/IAO_EXT_0000633 rdfs/subClassOf ccp/IAO_EXT_0000474)
(ccp/IAO_EXT_0000633 rdfs/label ["Drugbank dosage record - strength field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000634
(ccp/IAO_EXT_0000634 rdf/type owl/Class)
(ccp/IAO_EXT_0000634 rdfs/subClassOf ccp/IAO_EXT_0000475)
(ccp/IAO_EXT_0000634 rdfs/label ["Drugbank patent record - number field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000635
(ccp/IAO_EXT_0000635 rdf/type owl/Class)
(ccp/IAO_EXT_0000635 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000635 rdfs/label ["patent number identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000636
(ccp/IAO_EXT_0000636 rdf/type owl/Class)
(ccp/IAO_EXT_0000636 rdfs/subClassOf ccp/IAO_EXT_0000475)
(ccp/IAO_EXT_0000636 rdfs/label ["Drugbank patent record - country field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000637
(ccp/IAO_EXT_0000637 rdf/type owl/Class)
(ccp/IAO_EXT_0000637 rdfs/subClassOf ccp/IAO_EXT_0000475)
(ccp/IAO_EXT_0000637 rdfs/label ["Drugbank patent record - date approved field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000638
(ccp/IAO_EXT_0000638 rdf/type owl/Class)
(ccp/IAO_EXT_0000638 rdfs/subClassOf ccp/IAO_EXT_0000475)
(ccp/IAO_EXT_0000638 rdfs/label ["Drugbank patent record - date expires field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000639
(ccp/IAO_EXT_0000639 rdf/type owl/Class)
(ccp/IAO_EXT_0000639 rdfs/subClassOf ccp/IAO_EXT_0000476)
(ccp/IAO_EXT_0000639 rdfs/label ["Drugbank sequence record - format field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000640
(ccp/IAO_EXT_0000640 rdf/type owl/Class)
(ccp/IAO_EXT_0000640 rdfs/subClassOf ccp/IAO_EXT_0000476)
(ccp/IAO_EXT_0000640 obo/IAO_0000115 ["Amino acid sequence"])
(ccp/IAO_EXT_0000640 rdfs/label ["Drugbank sequence record - sequence field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000641
(ccp/IAO_EXT_0000641 rdf/type owl/Class)
(ccp/IAO_EXT_0000641 rdfs/subClassOf ccp/IAO_EXT_0000477)
(ccp/IAO_EXT_0000641 rdfs/label ["Drugbank property record - kind field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000642
(ccp/IAO_EXT_0000642 rdf/type owl/Class)
(ccp/IAO_EXT_0000642 rdfs/subClassOf ccp/IAO_EXT_0000477)
(ccp/IAO_EXT_0000642 rdfs/label ["Drugbank property record - value field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000643
(ccp/IAO_EXT_0000643 rdf/type owl/Class)
(ccp/IAO_EXT_0000643 rdfs/subClassOf ccp/IAO_EXT_0000477)
(ccp/IAO_EXT_0000643 rdfs/label ["Drugbank property record - source field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000644
(ccp/IAO_EXT_0000644 rdf/type owl/Class)
(ccp/IAO_EXT_0000644 rdfs/subClassOf ccp/IAO_EXT_0000478)
(ccp/IAO_EXT_0000644 rdfs/label ["Drugbank external link record - resource field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000645
(ccp/IAO_EXT_0000645 rdf/type owl/Class)
(ccp/IAO_EXT_0000645 rdfs/subClassOf ccp/IAO_EXT_0000478)
(ccp/IAO_EXT_0000645 rdfs/label ["Drugbank external link record - URL field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000646
(ccp/IAO_EXT_0000646 rdf/type owl/Class)
(ccp/IAO_EXT_0000646 rdfs/subClassOf ccp/IAO_EXT_0000479)
(ccp/IAO_EXT_0000646 rdfs/label ["Drugbank classification record - description field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000647
(ccp/IAO_EXT_0000647 rdf/type owl/Class)
(ccp/IAO_EXT_0000647 rdfs/subClassOf ccp/IAO_EXT_0000479)
(ccp/IAO_EXT_0000647 rdfs/label ["Drugbank classification record - direct parent field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000648
(ccp/IAO_EXT_0000648 rdf/type owl/Class)
(ccp/IAO_EXT_0000648 rdfs/subClassOf ccp/IAO_EXT_0000479)
(ccp/IAO_EXT_0000648 obo/IAO_0000115 ["Organic or Inorganic"])
(ccp/IAO_EXT_0000648 rdfs/label ["Drugbank classification record - kingdom field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000649
(ccp/IAO_EXT_0000649 rdf/type owl/Class)
(ccp/IAO_EXT_0000649 rdfs/subClassOf ccp/IAO_EXT_0000479)
(ccp/IAO_EXT_0000649 rdfs/label ["Drugbank classification record - super class field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000650
(ccp/IAO_EXT_0000650 rdf/type owl/Class)
(ccp/IAO_EXT_0000650 rdfs/subClassOf ccp/IAO_EXT_0000479)
(ccp/IAO_EXT_0000650 obo/IAO_0000115 ["Drug classes form the major component of the classification system. Drugs with the same class are considered structurally similar."])
(ccp/IAO_EXT_0000650 rdfs/label ["Drugbank classification record - CLS field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000651
(ccp/IAO_EXT_0000651 rdf/type owl/Class)
(ccp/IAO_EXT_0000651 rdfs/subClassOf ccp/IAO_EXT_0000479)
(ccp/IAO_EXT_0000651 rdfs/label ["Drugbank classification record - subclass field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000652
(ccp/IAO_EXT_0000652 rdf/type owl/Class)
(ccp/IAO_EXT_0000652 rdfs/subClassOf ccp/IAO_EXT_0000480)
(ccp/IAO_EXT_0000652 rdfs/label ["Drugbank manufacturer record - is generic field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000653
(ccp/IAO_EXT_0000653 rdf/type owl/Class)
(ccp/IAO_EXT_0000653 rdfs/subClassOf ccp/IAO_EXT_0000480)
(ccp/IAO_EXT_0000653 obo/IAO_0000115 ["Standard name of drug as provided by drug manufacturer"])
(ccp/IAO_EXT_0000653 rdfs/label ["Drugbank manufacturer record - name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000654
(ccp/IAO_EXT_0000654 rdf/type owl/Class)
(ccp/IAO_EXT_0000654 rdfs/subClassOf ccp/IAO_EXT_0000481)
(ccp/IAO_EXT_0000654 rdfs/label ["Drugbank price record - description  field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000655
(ccp/IAO_EXT_0000655 rdf/type owl/Class)
(ccp/IAO_EXT_0000655 rdfs/subClassOf ccp/IAO_EXT_0000481)
(ccp/IAO_EXT_0000655 rdfs/label ["Drugbank price record - cost field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000656
(ccp/IAO_EXT_0000656 rdf/type owl/Class)
(ccp/IAO_EXT_0000656 rdfs/subClassOf ccp/IAO_EXT_0000481)
(ccp/IAO_EXT_0000656 rdfs/label ["Drugbank price record - unit field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000657
(ccp/IAO_EXT_0000657 rdf/type owl/Class)
(ccp/IAO_EXT_0000657 rdfs/subClassOf ccp/IAO_EXT_0000482)
(ccp/IAO_EXT_0000657 rdfs/label ["Drugbank cost record - currency field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000658
(ccp/IAO_EXT_0000658 rdf/type owl/Class)
(ccp/IAO_EXT_0000658 rdfs/subClassOf ccp/IAO_EXT_0000482)
(ccp/IAO_EXT_0000658 rdfs/label ["Drugbank cost record - cost field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000659
(ccp/IAO_EXT_0000659 rdf/type owl/Class)
(ccp/IAO_EXT_0000659 rdfs/subClassOf ccp/IAO_EXT_0000483)
(ccp/IAO_EXT_0000659 rdfs/label ["Drugbank mixture record - name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000660
(ccp/IAO_EXT_0000660 rdf/type owl/Class)
(ccp/IAO_EXT_0000660 rdfs/subClassOf ccp/IAO_EXT_0000483)
(ccp/IAO_EXT_0000660 rdfs/label ["Drugbank mixture record - ingredients field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000661
(ccp/IAO_EXT_0000661 rdf/type owl/Class)
(ccp/IAO_EXT_0000661 rdfs/subClassOf ccp/IAO_EXT_0000484)
(ccp/IAO_EXT_0000661 obo/IAO_0000115 ["Organic or Inorganic"])
(ccp/IAO_EXT_0000661 rdfs/label ["Drugbank drug taxonomy record - kingdom field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000662
(ccp/IAO_EXT_0000662 rdf/type owl/Class)
(ccp/IAO_EXT_0000662 rdfs/subClassOf ccp/IAO_EXT_0000484)
(ccp/IAO_EXT_0000662 obo/IAO_0000115 ["All substructures and functional groups calculated from the structure for the drug. This is a superset of the Classes."])
(ccp/IAO_EXT_0000662 rdfs/label ["Drugbank drug taxonomy record - substructure field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000663
(ccp/IAO_EXT_0000663 rdf/type owl/Class)
(ccp/IAO_EXT_0000663 rdfs/subClassOf ccp/IAO_EXT_0000485)
(ccp/IAO_EXT_0000663 obo/IAO_0000115 ["Drug classes form the major component of the classification system. Drugs with the same class are considered structurally similar."])
(ccp/IAO_EXT_0000663 rdfs/label ["Drugbank drug taxonomy substructure record - is class field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000664
(ccp/IAO_EXT_0000664 rdf/type owl/Class)
(ccp/IAO_EXT_0000664 rdfs/subClassOf ccp/IAO_EXT_0000485)
(ccp/IAO_EXT_0000664 obo/IAO_0000115 ["All substructures and functional groups calculated from the structure for the drug. This is a superset of the Classes"])
(ccp/IAO_EXT_0000664 rdfs/label ["Drugbank drug taxonomy substructure record - substructure field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000665
(ccp/IAO_EXT_0000665 rdf/type owl/Class)
(ccp/IAO_EXT_0000665 rdfs/subClassOf ccp/IAO_EXT_0000486)
(ccp/IAO_EXT_0000665 rdfs/label ["Drugbank reference record - reference STR field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000666
(ccp/IAO_EXT_0000666 rdf/type owl/Class)
(ccp/IAO_EXT_0000666 rdfs/subClassOf ccp/IAO_EXT_0000486)
(ccp/IAO_EXT_0000666 rdfs/label ["Drugbank reference record - PubMed identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000667
(ccp/IAO_EXT_0000667 rdf/type owl/Class)
(ccp/IAO_EXT_0000667 rdfs/subClassOf ccp/IAO_EXT_0000487)
(ccp/IAO_EXT_0000667 obo/IAO_0000115 ["Unique DrugBank accession number consisting of a 2 letter prefix (DB) and a 5 number suffix. This ID is used to access the drug entry via the URL. If an entry is deleted, it's DrugBank ID will not be reused."])
(ccp/IAO_EXT_0000667 rdfs/label ["Drugbank drug interaction record - drugbank identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000668
(ccp/IAO_EXT_0000668 rdf/type owl/Class)
(ccp/IAO_EXT_0000668 rdfs/subClassOf ccp/IAO_EXT_0000487)
(ccp/IAO_EXT_0000668 obo/IAO_0000115 ["Standard name of drug as provided by drug manufacturer"])
(ccp/IAO_EXT_0000668 rdfs/label ["Drugbank drug interaction record - name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000669
(ccp/IAO_EXT_0000669 rdf/type owl/Class)
(ccp/IAO_EXT_0000669 rdfs/subClassOf ccp/IAO_EXT_0000487)
(ccp/IAO_EXT_0000669 obo/IAO_0000115 ["Description of the drug describing general facts, composition and/or preparation"])
(ccp/IAO_EXT_0000669 rdfs/label ["Drugbank drug interaction record - description field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000670
(ccp/IAO_EXT_0000670 rdf/type owl/Class)
(ccp/IAO_EXT_0000670 rdfs/subClassOf ccp/IAO_EXT_0000359)
(ccp/IAO_EXT_0000670 rdfs/label ["Drugbank drug record - drug interaction field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000671
(ccp/IAO_EXT_0000671 rdf/type owl/Class)
(ccp/IAO_EXT_0000671 rdfs/subClassOf ccp/IAO_EXT_0000085)
(ccp/IAO_EXT_0000671 rdfs/label ["NCBI gene record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000672
(ccp/IAO_EXT_0000672 rdf/type owl/Class)
(ccp/IAO_EXT_0000672 rdfs/subClassOf ccp/IAO_EXT_0000671)
(ccp/IAO_EXT_0000672 rdfs/label ["NCBI gene 2 accession record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000673
(ccp/IAO_EXT_0000673 rdf/type owl/Class)
(ccp/IAO_EXT_0000673 rdfs/subClassOf ccp/IAO_EXT_0000671)
(ccp/IAO_EXT_0000673 rdfs/label ["NCBI gene 2 PubMed record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000674
(ccp/IAO_EXT_0000674 rdf/type owl/Class)
(ccp/IAO_EXT_0000674 rdfs/subClassOf ccp/IAO_EXT_0000050)
(ccp/IAO_EXT_0000674 rdfs/label ["date changed field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000675
(ccp/IAO_EXT_0000675 rdf/type owl/Class)
(ccp/IAO_EXT_0000675 rdfs/subClassOf ccp/IAO_EXT_0000132)
(ccp/IAO_EXT_0000675 obo/IAO_0000115 ["Entrez gene identifier"])
(ccp/IAO_EXT_0000675 rdfs/label ["HGNC gene record - Entrez gene identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000676
(ccp/IAO_EXT_0000676 rdf/type owl/Class)
(ccp/IAO_EXT_0000676 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000676 rdfs/label ["Entrez Gene identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000677
(ccp/IAO_EXT_0000677 rdf/type owl/Class)
(ccp/IAO_EXT_0000677 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000677 rdfs/label ["Mouse Genome Informatics identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000678
(ccp/IAO_EXT_0000678 rdf/type owl/Class)
(ccp/IAO_EXT_0000678 rdfs/subClassOf ccp/IAO_EXT_0000671)
(ccp/IAO_EXT_0000678 rdfs/label ["NCBI gene 2 RefSeq record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000679
(ccp/IAO_EXT_0000679 rdf/type owl/Class)
(ccp/IAO_EXT_0000679 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000679 rdfs/label ["Concensus CDS identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000680
(ccp/IAO_EXT_0000680 rdf/type owl/Class)
(ccp/IAO_EXT_0000680 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000680 rdfs/label ["Vega identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000681
(ccp/IAO_EXT_0000681 rdf/type owl/Class)
(ccp/IAO_EXT_0000681 rdfs/subClassOf ccp/IAO_EXT_0000671)
(ccp/IAO_EXT_0000681 rdfs/label ["NCBI gene info record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000682
(ccp/IAO_EXT_0000682 rdf/type owl/Class)
(ccp/IAO_EXT_0000682 rdfs/subClassOf ccp/IAO_EXT_0000671)
(ccp/IAO_EXT_0000682 rdfs/label ["NCBI gene MIM 2 gene record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000683
(ccp/IAO_EXT_0000683 rdf/type owl/Class)
(ccp/IAO_EXT_0000683 rdfs/subClassOf ccp/IAO_EXT_0000358)
(ccp/IAO_EXT_0000683 rdfs/label ["HGNC specialist database link pair record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000684
(ccp/IAO_EXT_0000684 rdf/type owl/Class)
(ccp/IAO_EXT_0000684 rdfs/subClassOf ccp/IAO_EXT_0000358)
(ccp/IAO_EXT_0000684 rdfs/label ["HGNC gene family tag description pair record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000685
(ccp/IAO_EXT_0000685 rdf/type owl/Class)
(ccp/IAO_EXT_0000685 rdfs/subClassOf ccp/IAO_EXT_0000358)
(ccp/IAO_EXT_0000685 rdfs/label ["HGNC locus specific database name link pair record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000686
(ccp/IAO_EXT_0000686 rdf/type owl/Class)
(ccp/IAO_EXT_0000686 rdfs/subClassOf ccp/IAO_EXT_0000683)
(ccp/IAO_EXT_0000686 rdfs/label ["HGNC specialist database link pair record - data source identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000687
(ccp/IAO_EXT_0000687 rdf/type owl/Class)
(ccp/IAO_EXT_0000687 rdfs/subClassOf ccp/IAO_EXT_0000683)
(ccp/IAO_EXT_0000687 rdfs/label ["HGNC specialist database link pair record - specialist database URL field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000688
(ccp/IAO_EXT_0000688 rdf/type owl/Class)
(ccp/IAO_EXT_0000688 rdfs/subClassOf ccp/IAO_EXT_0000684)
(ccp/IAO_EXT_0000688 rdfs/label ["HGNC gene family tag description pair record - gene family tag field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000689
(ccp/IAO_EXT_0000689 rdf/type owl/Class)
(ccp/IAO_EXT_0000689 rdfs/subClassOf ccp/IAO_EXT_0000684)
(ccp/IAO_EXT_0000689 rdfs/label ["HGNC gene family tag description pair record - gene family description field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000690
(ccp/IAO_EXT_0000690 rdf/type owl/Class)
(ccp/IAO_EXT_0000690 rdfs/subClassOf ccp/IAO_EXT_0000685)
(ccp/IAO_EXT_0000690 rdfs/label ["HGNC locus specific database name link pair record - database name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000691
(ccp/IAO_EXT_0000691 rdf/type owl/Class)
(ccp/IAO_EXT_0000691 rdfs/subClassOf ccp/IAO_EXT_0000685)
(ccp/IAO_EXT_0000691 rdfs/label ["HGNC locus specific database name link pair record - link field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000692
(ccp/IAO_EXT_0000692 rdf/type owl/Class)
(ccp/IAO_EXT_0000692 rdfs/subClassOf ccp/IAO_EXT_0000671)
(ccp/IAO_EXT_0000692 rdfs/label ["NCBI gene RefSeq UniProtKB collaboration record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000693
(ccp/IAO_EXT_0000693 rdf/type owl/Class)
(ccp/IAO_EXT_0000693 rdfs/subClassOf ccp/IAO_EXT_0000694)
(ccp/IAO_EXT_0000693 rdfs/label ["miRBase modified base sequence feature qualifier set record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000694
(ccp/IAO_EXT_0000694 rdf/type owl/Class)
(ccp/IAO_EXT_0000694 rdfs/subClassOf ccp/IAO_EXT_0000818)
(ccp/IAO_EXT_0000694 rdfs/label ["miRBase microRNA modified base sequence feature record component" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000695
(ccp/IAO_EXT_0000695 rdf/type owl/Class)
(ccp/IAO_EXT_0000695 rdfs/subClassOf ccp/IAO_EXT_0000696)
(ccp/IAO_EXT_0000695 rdfs/label ["miRBase microRNA sequence feature qualifier set record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000696
(ccp/IAO_EXT_0000696 rdf/type owl/Class)
(ccp/IAO_EXT_0000696 rdfs/subClassOf ccp/IAO_EXT_0000818)
(ccp/IAO_EXT_0000696 rdfs/label ["miRBase microRNA sequence feature record component" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000697
(ccp/IAO_EXT_0000697 rdf/type owl/Class)
(ccp/IAO_EXT_0000697 rdfs/subClassOf ccp/IAO_EXT_0000219)
(ccp/IAO_EXT_0000697 rdfs/label ["HPO annotation record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000698
(ccp/IAO_EXT_0000698 rdf/type owl/Class)
(ccp/IAO_EXT_0000698 rdfs/subClassOf ccp/IAO_EXT_0000697)
(ccp/IAO_EXT_0000698 rdfs/label ["HPO annotation record - gene identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000699
(ccp/IAO_EXT_0000699 rdf/type owl/Class)
(ccp/IAO_EXT_0000699 rdfs/subClassOf ccp/IAO_EXT_0000697)
(ccp/IAO_EXT_0000699 rdfs/label ["HPO annotation record - gene symbol field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000700
(ccp/IAO_EXT_0000700 rdf/type owl/Class)
(ccp/IAO_EXT_0000700 rdfs/subClassOf ccp/IAO_EXT_0000697)
(ccp/IAO_EXT_0000700 rdfs/label ["HPO annotation record - HPO term name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000701
(ccp/IAO_EXT_0000701 rdf/type owl/Class)
(ccp/IAO_EXT_0000701 rdfs/subClassOf ccp/IAO_EXT_0000697)
(ccp/IAO_EXT_0000701 rdfs/label ["HPO annotation record - HPO term field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000702
(ccp/IAO_EXT_0000702 rdf/type owl/Class)
(ccp/IAO_EXT_0000702 rdfs/subClassOf ccp/IAO_EXT_0000220)
(ccp/IAO_EXT_0000702 rdfs/label ["IRefWeb interaction record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000703
(ccp/IAO_EXT_0000703 rdf/type owl/Class)
(ccp/IAO_EXT_0000703 rdfs/subClassOf ccp/IAO_EXT_0000220)
(ccp/IAO_EXT_0000703 rdfs/label ["IRefWeb host organism record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000704
(ccp/IAO_EXT_0000704 rdf/type owl/Class)
(ccp/IAO_EXT_0000704 rdfs/subClassOf ccp/IAO_EXT_0000220)
(ccp/IAO_EXT_0000704 rdfs/label ["IRefWeb interaction detection method record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000705
(ccp/IAO_EXT_0000705 rdf/type owl/Class)
(ccp/IAO_EXT_0000705 rdfs/subClassOf ccp/IAO_EXT_0000220)
(ccp/IAO_EXT_0000705 rdfs/label ["IRefWeb interaction source database record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000706
(ccp/IAO_EXT_0000706 rdf/type owl/Class)
(ccp/IAO_EXT_0000706 rdfs/subClassOf ccp/IAO_EXT_0000220)
(ccp/IAO_EXT_0000706 rdfs/label ["IRefWeb interaction type record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000707
(ccp/IAO_EXT_0000707 rdf/type owl/Class)
(ccp/IAO_EXT_0000707 rdfs/subClassOf ccp/IAO_EXT_0000220)
(ccp/IAO_EXT_0000707 rdfs/label ["IRefWeb interactor record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000708
(ccp/IAO_EXT_0000708 rdf/type owl/Class)
(ccp/IAO_EXT_0000708 rdfs/subClassOf ccp/IAO_EXT_0000220)
(ccp/IAO_EXT_0000708 rdfs/label ["IRefWeb interactor biological role record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000709
(ccp/IAO_EXT_0000709 rdf/type owl/Class)
(ccp/IAO_EXT_0000709 rdfs/subClassOf ccp/IAO_EXT_0000220)
(ccp/IAO_EXT_0000709 rdfs/label ["IRefWeb interactor organism record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000710
(ccp/IAO_EXT_0000710 rdf/type owl/Class)
(ccp/IAO_EXT_0000710 rdfs/subClassOf ccp/IAO_EXT_0000220)
(ccp/IAO_EXT_0000710 rdfs/label ["IRefWeb interactor type record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000711
(ccp/IAO_EXT_0000711 rdf/type owl/Class)
(ccp/IAO_EXT_0000711 rdfs/subClassOf ccp/IAO_EXT_0000220)
(ccp/IAO_EXT_0000711 rdfs/label ["IRefWeb PSI-MITAB 2_6 record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000712
(ccp/IAO_EXT_0000712 rdf/type owl/Class)
(ccp/IAO_EXT_0000712 rdfs/subClassOf ccp/IAO_EXT_0000703)
(ccp/IAO_EXT_0000712 rdfs/label ["IRefWeb host organism record - host organism identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000713
(ccp/IAO_EXT_0000713 rdf/type owl/Class)
(ccp/IAO_EXT_0000713 rdfs/subClassOf ccp/IAO_EXT_0000703)
(ccp/IAO_EXT_0000713 rdfs/label ["IRefWeb host organism record - host organism name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000714
(ccp/IAO_EXT_0000714 rdf/type owl/Class)
(ccp/IAO_EXT_0000714 rdfs/subClassOf ccp/IAO_EXT_0000357)
(ccp/IAO_EXT_0000714 rdfs/label ["IRefWeb host organism record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000715
(ccp/IAO_EXT_0000715 rdf/type owl/Class)
(ccp/IAO_EXT_0000715 rdfs/subClassOf ccp/IAO_EXT_0000357)
(ccp/IAO_EXT_0000715 rdfs/label ["IRefWeb interaction detection method record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000716
(ccp/IAO_EXT_0000716 rdf/type owl/Class)
(ccp/IAO_EXT_0000716 rdfs/subClassOf ccp/IAO_EXT_0000357)
(ccp/IAO_EXT_0000716 rdfs/label ["IRefWeb interaction type record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000717
(ccp/IAO_EXT_0000717 rdf/type owl/Class)
(ccp/IAO_EXT_0000717 rdfs/subClassOf ccp/IAO_EXT_0000222)
(ccp/IAO_EXT_0000717 rdfs/label ["EMBL microRNA sequence database file database record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000718
(ccp/IAO_EXT_0000718 rdf/type owl/Class)
(ccp/IAO_EXT_0000718 rdfs/subClassOf ccp/IAO_EXT_0000357)
(ccp/IAO_EXT_0000718 rdfs/label ["IRefWeb interactor biological role record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000719
(ccp/IAO_EXT_0000719 rdf/type owl/Class)
(ccp/IAO_EXT_0000719 rdfs/subClassOf ccp/IAO_EXT_0000357)
(ccp/IAO_EXT_0000719 rdfs/label ["IRefWeb interactor experimental role record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000720
(ccp/IAO_EXT_0000720 rdf/type owl/Class)
(ccp/IAO_EXT_0000720 rdfs/subClassOf ccp/IAO_EXT_0000357)
(ccp/IAO_EXT_0000720 rdfs/label ["IRefWeb interactor organism record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000722
(ccp/IAO_EXT_0000722 rdf/type owl/Class)
(ccp/IAO_EXT_0000722 rdfs/subClassOf ccp/IAO_EXT_0000238)
(ccp/IAO_EXT_0000722 rdfs/label ["IRefWeb PSI-MITAB 2_6 record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000723
(ccp/IAO_EXT_0000723 rdf/type owl/Class)
(ccp/IAO_EXT_0000723 rdfs/subClassOf ccp/IAO_EXT_0000702)
(ccp/IAO_EXT_0000723 rdfs/label ["IRefWeb interaction record - detection method field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000724
(ccp/IAO_EXT_0000724 rdf/type owl/Class)
(ccp/IAO_EXT_0000724 rdfs/subClassOf ccp/IAO_EXT_0000702)
(ccp/IAO_EXT_0000724 rdfs/label ["IRefWeb interaction record - author field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000725
(ccp/IAO_EXT_0000725 rdf/type owl/Class)
(ccp/IAO_EXT_0000725 rdfs/subClassOf ccp/IAO_EXT_0000702)
(ccp/IAO_EXT_0000725 rdfs/label ["IRefWeb interaction record - PubMed identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000726
(ccp/IAO_EXT_0000726 rdf/type owl/Class)
(ccp/IAO_EXT_0000726 rdfs/subClassOf ccp/IAO_EXT_0000702)
(ccp/IAO_EXT_0000726 rdfs/label ["IRefWeb interaction record - interaction type field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000727
(ccp/IAO_EXT_0000727 rdf/type owl/Class)
(ccp/IAO_EXT_0000727 rdfs/subClassOf ccp/IAO_EXT_0000702)
(ccp/IAO_EXT_0000727 rdfs/label ["IRefWeb interaction record - interaction database identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000728
(ccp/IAO_EXT_0000728 rdf/type owl/Class)
(ccp/IAO_EXT_0000728 rdfs/subClassOf ccp/IAO_EXT_0000702)
(ccp/IAO_EXT_0000728 rdfs/label ["IRefWeb interaction record - confidence score field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000729
(ccp/IAO_EXT_0000729 rdf/type owl/Class)
(ccp/IAO_EXT_0000729 rdfs/subClassOf ccp/IAO_EXT_0000702)
(ccp/IAO_EXT_0000729 rdfs/label ["IRefWeb interaction record - expansion field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000730
(ccp/IAO_EXT_0000730 rdf/type owl/Class)
(ccp/IAO_EXT_0000730 rdfs/subClassOf ccp/IAO_EXT_0000222)
(ccp/IAO_EXT_0000730 rdfs/label ["miRBase microRNA modified base sequence feature record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000731
(ccp/IAO_EXT_0000731 rdf/type owl/Class)
(ccp/IAO_EXT_0000731 rdfs/subClassOf ccp/IAO_EXT_0000702)
(ccp/IAO_EXT_0000731 rdfs/label ["IRefWeb interaction record - cross-reference interaction field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000732
(ccp/IAO_EXT_0000732 rdf/type owl/Class)
(ccp/IAO_EXT_0000732 rdfs/subClassOf ccp/IAO_EXT_0000702)
(ccp/IAO_EXT_0000732 rdfs/label ["IRefWeb interaction record - annotation interaction field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000733
(ccp/IAO_EXT_0000733 rdf/type owl/Class)
(ccp/IAO_EXT_0000733 rdfs/subClassOf ccp/IAO_EXT_0000702)
(ccp/IAO_EXT_0000733 rdfs/label ["IRefWeb interaction record - host organism taxonomy identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000734
(ccp/IAO_EXT_0000734 rdf/type owl/Class)
(ccp/IAO_EXT_0000734 rdfs/subClassOf ccp/IAO_EXT_0000702)
(ccp/IAO_EXT_0000734 rdfs/label ["IRefWeb interaction record - parameters interaction field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000735
(ccp/IAO_EXT_0000735 rdf/type owl/Class)
(ccp/IAO_EXT_0000735 rdfs/subClassOf ccp/IAO_EXT_0000702)
(ccp/IAO_EXT_0000735 rdfs/label ["IRefWeb interaction record - check sum interaction field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000736
(ccp/IAO_EXT_0000736 rdf/type owl/Class)
(ccp/IAO_EXT_0000736 rdfs/subClassOf ccp/IAO_EXT_0000702)
(ccp/IAO_EXT_0000736 rdfs/label ["IRefWeb interaction record - negative field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000737
(ccp/IAO_EXT_0000737 rdf/type owl/Class)
(ccp/IAO_EXT_0000737 rdfs/subClassOf ccp/IAO_EXT_0000702)
(ccp/IAO_EXT_0000737 rdfs/label ["IRefWeb interaction record - integer RIG identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000738
(ccp/IAO_EXT_0000738 rdf/type owl/Class)
(ccp/IAO_EXT_0000738 rdfs/subClassOf ccp/IAO_EXT_0000702)
(ccp/IAO_EXT_0000738 rdfs/label ["IRefWeb interaction record - alphanumeric RIG identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000739
(ccp/IAO_EXT_0000739 rdf/type owl/Class)
(ccp/IAO_EXT_0000739 rdfs/subClassOf ccp/IAO_EXT_0000702)
(ccp/IAO_EXT_0000739 rdfs/label ["IRefWeb interaction record - ICRIG identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000740
(ccp/IAO_EXT_0000740 rdf/type owl/Class)
(ccp/IAO_EXT_0000740 rdfs/subClassOf ccp/IAO_EXT_0000702)
(ccp/IAO_EXT_0000740 rdfs/label ["IRefWeb interaction record - IMEX identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000741
(ccp/IAO_EXT_0000741 rdf/type owl/Class)
(ccp/IAO_EXT_0000741 rdfs/subClassOf ccp/IAO_EXT_0000702)
(ccp/IAO_EXT_0000741 rdfs/label ["IRefWeb interaction record - edge type field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000742
(ccp/IAO_EXT_0000742 rdf/type owl/Class)
(ccp/IAO_EXT_0000742 rdfs/subClassOf ccp/IAO_EXT_0000702)
(ccp/IAO_EXT_0000742 rdfs/label ["IRefWeb interaction record - number of participants field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000743
(ccp/IAO_EXT_0000743 rdf/type owl/Class)
(ccp/IAO_EXT_0000743 rdfs/subClassOf ccp/IAO_EXT_0000222)
(ccp/IAO_EXT_0000743 rdfs/label ["miRBase microRNA sequence feature record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000744
(ccp/IAO_EXT_0000744 rdf/type owl/Class)
(ccp/IAO_EXT_0000744 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000744 rdfs/label ["MI identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000746
(ccp/IAO_EXT_0000746 rdf/type owl/Class)
(ccp/IAO_EXT_0000746 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000746 rdfs/label ["IMEX identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000747
(ccp/IAO_EXT_0000747 rdf/type owl/Class)
(ccp/IAO_EXT_0000747 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000747 rdfs/label ["RIG identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000748
(ccp/IAO_EXT_0000748 rdf/type owl/Class)
(ccp/IAO_EXT_0000748 rdfs/subClassOf ccp/IAO_EXT_0000704)
(ccp/IAO_EXT_0000748 rdfs/label ["IRefWeb interaction detection method record - detection method identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000749
(ccp/IAO_EXT_0000749 rdf/type owl/Class)
(ccp/IAO_EXT_0000749 rdfs/subClassOf ccp/IAO_EXT_0000704)
(ccp/IAO_EXT_0000749 rdfs/label ["IRefWeb interaction detection method record - detection method name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000750
(ccp/IAO_EXT_0000750 rdf/type owl/Class)
(ccp/IAO_EXT_0000750 rdfs/subClassOf ccp/IAO_EXT_0000705)
(ccp/IAO_EXT_0000750 rdfs/label ["IRefWeb interaction source database record - source database identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000751
(ccp/IAO_EXT_0000751 rdf/type owl/Class)
(ccp/IAO_EXT_0000751 rdfs/subClassOf ccp/IAO_EXT_0000705)
(ccp/IAO_EXT_0000751 rdfs/label ["IRefWeb interaction source database record - source database name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000752
(ccp/IAO_EXT_0000752 rdf/type owl/Class)
(ccp/IAO_EXT_0000752 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000752 rdfs/label ["interaction type identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000753
(ccp/IAO_EXT_0000753 rdf/type owl/Class)
(ccp/IAO_EXT_0000753 rdfs/subClassOf ccp/IAO_EXT_0000706)
(ccp/IAO_EXT_0000753 rdfs/label ["IRefWeb interaction type record -  interaction type identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000754
(ccp/IAO_EXT_0000754 rdf/type owl/Class)
(ccp/IAO_EXT_0000754 rdfs/subClassOf ccp/IAO_EXT_0000706)
(ccp/IAO_EXT_0000754 rdfs/label ["IRefWeb interaction type record -  interaction type name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000755
(ccp/IAO_EXT_0000755 rdf/type owl/Class)
(ccp/IAO_EXT_0000755 rdfs/subClassOf ccp/IAO_EXT_0000707)
(ccp/IAO_EXT_0000755 rdfs/label ["IRefWeb interactor record - unique identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000756
(ccp/IAO_EXT_0000756 rdf/type owl/Class)
(ccp/IAO_EXT_0000756 rdfs/subClassOf ccp/IAO_EXT_0000707)
(ccp/IAO_EXT_0000756 rdfs/label ["IRefWeb interactor record - alternative identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000757
(ccp/IAO_EXT_0000757 rdf/type owl/Class)
(ccp/IAO_EXT_0000757 rdfs/subClassOf ccp/IAO_EXT_0000707)
(ccp/IAO_EXT_0000757 rdfs/label ["IRefWeb interactor record - alias symbols field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000758
(ccp/IAO_EXT_0000758 rdf/type owl/Class)
(ccp/IAO_EXT_0000758 rdfs/subClassOf ccp/IAO_EXT_0000707)
(ccp/IAO_EXT_0000758 rdfs/label ["IRefWeb interactor record - alias identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000759
(ccp/IAO_EXT_0000759 rdf/type owl/Class)
(ccp/IAO_EXT_0000759 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000759 rdfs/label ["alias identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000760
(ccp/IAO_EXT_0000760 rdf/type owl/Class)
(ccp/IAO_EXT_0000760 rdfs/subClassOf ccp/IAO_EXT_0000707)
(ccp/IAO_EXT_0000760 rdfs/label ["IRefWeb interactor record - NCBI taxonomy identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000761
(ccp/IAO_EXT_0000761 rdf/type owl/Class)
(ccp/IAO_EXT_0000761 rdfs/subClassOf ccp/IAO_EXT_0000707)
(ccp/IAO_EXT_0000761 rdfs/label ["IRefWeb interactor record - database cross-reference identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000762
(ccp/IAO_EXT_0000762 rdf/type owl/Class)
(ccp/IAO_EXT_0000762 rdfs/subClassOf ccp/IAO_EXT_0000707)
(ccp/IAO_EXT_0000762 rdfs/label ["IRefWeb interactor record - biological role field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000763
(ccp/IAO_EXT_0000763 rdf/type owl/Class)
(ccp/IAO_EXT_0000763 rdfs/subClassOf ccp/IAO_EXT_0000707)
(ccp/IAO_EXT_0000763 rdfs/label ["IRefWeb interactor record - experimental role field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000764
(ccp/IAO_EXT_0000764 rdf/type owl/Class)
(ccp/IAO_EXT_0000764 rdfs/subClassOf ccp/IAO_EXT_0000707)
(ccp/IAO_EXT_0000764 rdfs/label ["IRefWeb interactor record - interactor type field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000765
(ccp/IAO_EXT_0000765 rdf/type owl/Class)
(ccp/IAO_EXT_0000765 rdfs/subClassOf ccp/IAO_EXT_0000707)
(ccp/IAO_EXT_0000765 rdfs/label ["IRefWeb interactor record - annotations field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000766
(ccp/IAO_EXT_0000766 rdf/type owl/Class)
(ccp/IAO_EXT_0000766 rdfs/subClassOf ccp/IAO_EXT_0000707)
(ccp/IAO_EXT_0000766 rdfs/label ["IRefWeb interactor record - check sum field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000767
(ccp/IAO_EXT_0000767 rdf/type owl/Class)
(ccp/IAO_EXT_0000767 rdfs/subClassOf ccp/IAO_EXT_0000001)
(ccp/IAO_EXT_0000767 rdfs/label ["interaction type field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000768
(ccp/IAO_EXT_0000768 rdf/type owl/Class)
(ccp/IAO_EXT_0000768 rdfs/subClassOf ccp/IAO_EXT_0000707)
(ccp/IAO_EXT_0000768 rdfs/label ["IRefWeb interactor record - original reference field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000769
(ccp/IAO_EXT_0000769 rdf/type owl/Class)
(ccp/IAO_EXT_0000769 rdfs/subClassOf ccp/IAO_EXT_0000707)
(ccp/IAO_EXT_0000769 rdfs/label ["IRefWeb interactor record - final reference field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000770
(ccp/IAO_EXT_0000770 rdf/type owl/Class)
(ccp/IAO_EXT_0000770 rdfs/subClassOf ccp/IAO_EXT_0000707)
(ccp/IAO_EXT_0000770 rdfs/label ["IRefWeb interactor record - mapping score field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000771
(ccp/IAO_EXT_0000771 rdf/type owl/Class)
(ccp/IAO_EXT_0000771 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000771 rdfs/label ["ROG identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000772
(ccp/IAO_EXT_0000772 rdf/type owl/Class)
(ccp/IAO_EXT_0000772 rdfs/subClassOf ccp/IAO_EXT_0000707)
(ccp/IAO_EXT_0000772 rdfs/label ["IRefWeb interactor record - integer ROG identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000773
(ccp/IAO_EXT_0000773 rdf/type owl/Class)
(ccp/IAO_EXT_0000773 rdfs/subClassOf ccp/IAO_EXT_0000707)
(ccp/IAO_EXT_0000773 rdfs/label ["IRefWeb interactor record - alphanumeric ROG identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000774
(ccp/IAO_EXT_0000774 rdf/type owl/Class)
(ccp/IAO_EXT_0000774 rdfs/subClassOf ccp/IAO_EXT_0000707)
(ccp/IAO_EXT_0000774 rdfs/label ["IRefWeb interactor record - ICROG identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000775
(ccp/IAO_EXT_0000775 rdf/type owl/Class)
(ccp/IAO_EXT_0000775 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000775 rdfs/label ["role identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000776
(ccp/IAO_EXT_0000776 rdf/type owl/Class)
(ccp/IAO_EXT_0000776 rdfs/subClassOf ccp/IAO_EXT_0000708)
(ccp/IAO_EXT_0000776 rdfs/label ["IRefWeb interactor biological role record - biological role identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000777
(ccp/IAO_EXT_0000777 rdf/type owl/Class)
(ccp/IAO_EXT_0000777 rdfs/subClassOf ccp/IAO_EXT_0000708)
(ccp/IAO_EXT_0000777 rdfs/label ["IRefWeb interactor biological role record - biological role name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000778
(ccp/IAO_EXT_0000778 rdf/type owl/Class)
(ccp/IAO_EXT_0000778 rdfs/subClassOf ccp/IAO_EXT_0000220)
(ccp/IAO_EXT_0000778 rdfs/label ["IRefWeb interactor experimental role record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000779
(ccp/IAO_EXT_0000779 rdf/type owl/Class)
(ccp/IAO_EXT_0000779 rdfs/subClassOf ccp/IAO_EXT_0000778)
(ccp/IAO_EXT_0000779 rdfs/label ["IRefWeb interactor experimental role record - experimental role identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000780
(ccp/IAO_EXT_0000780 rdf/type owl/Class)
(ccp/IAO_EXT_0000780 rdfs/subClassOf ccp/IAO_EXT_0000778)
(ccp/IAO_EXT_0000780 rdfs/label ["IRefWeb interactor experimental role record - experimental role name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000781
(ccp/IAO_EXT_0000781 rdf/type owl/Class)
(ccp/IAO_EXT_0000781 rdfs/subClassOf ccp/IAO_EXT_0000709)
(ccp/IAO_EXT_0000781 rdfs/label ["IRefWeb interactor interactor organism record - taxonomy identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000782
(ccp/IAO_EXT_0000782 rdf/type owl/Class)
(ccp/IAO_EXT_0000782 rdfs/subClassOf ccp/IAO_EXT_0000709)
(ccp/IAO_EXT_0000782 rdfs/label ["IRefWeb interactor interactor organism record - taxonomy name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000783
(ccp/IAO_EXT_0000783 rdf/type owl/Class)
(ccp/IAO_EXT_0000783 rdfs/subClassOf ccp/IAO_EXT_0000710)
(ccp/IAO_EXT_0000783 rdfs/label ["IRefWeb interactor interactor type record - interactor type identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000784
(ccp/IAO_EXT_0000784 rdf/type owl/Class)
(ccp/IAO_EXT_0000784 rdfs/subClassOf ccp/IAO_EXT_0000710)
(ccp/IAO_EXT_0000784 rdfs/label ["IRefWeb interactor interactor type record - interactor type name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000785
(ccp/IAO_EXT_0000785 rdf/type owl/Class)
(ccp/IAO_EXT_0000785 rdfs/subClassOf ccp/IAO_EXT_0000711)
(ccp/IAO_EXT_0000785 rdfs/label ["IRefWeb PSI-MITAB 2_6 record - source database field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000786
(ccp/IAO_EXT_0000786 rdf/type owl/Class)
(ccp/IAO_EXT_0000786 rdfs/subClassOf ccp/IAO_EXT_0000711)
(ccp/IAO_EXT_0000786 rdfs/label ["IRefWeb PSI-MITAB 2_6 record - creation date field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000787
(ccp/IAO_EXT_0000787 rdf/type owl/Class)
(ccp/IAO_EXT_0000787 rdfs/subClassOf ccp/IAO_EXT_0000711)
(ccp/IAO_EXT_0000787 rdfs/label ["IRefWeb PSI-MITAB 2_6 record - update date field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000788
(ccp/IAO_EXT_0000788 rdf/type owl/Class)
(ccp/IAO_EXT_0000788 rdfs/subClassOf ccp/IAO_EXT_0000711)
(ccp/IAO_EXT_0000788 rdfs/label ["IRefWeb PSI-MITAB 2_6 record - interactor a field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000789
(ccp/IAO_EXT_0000789 rdf/type owl/Class)
(ccp/IAO_EXT_0000789 rdfs/subClassOf ccp/IAO_EXT_0000711)
(ccp/IAO_EXT_0000789 rdfs/label ["IRefWeb PSI-MITAB 2_6 record - interactor b field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000790
(ccp/IAO_EXT_0000790 rdf/type owl/Class)
(ccp/IAO_EXT_0000790 rdfs/subClassOf ccp/IAO_EXT_0000711)
(ccp/IAO_EXT_0000790 rdfs/label ["IRefWeb PSI-MITAB 2_6 record - interaction field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000791
(ccp/IAO_EXT_0000791 rdf/type owl/Class)
(ccp/IAO_EXT_0000791 rdfs/subClassOf ccp/IAO_EXT_0000000)
(ccp/IAO_EXT_0000791 rdfs/label ["RefSeq record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000792
(ccp/IAO_EXT_0000792 rdf/type owl/Class)
(ccp/IAO_EXT_0000792 rdfs/subClassOf ccp/IAO_EXT_0000791)
(ccp/IAO_EXT_0000792 rdfs/label ["RefSeq catalog record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000793
(ccp/IAO_EXT_0000793 rdf/type owl/Class)
(ccp/IAO_EXT_0000793 rdfs/subClassOf ccp/IAO_EXT_0000717)
(ccp/IAO_EXT_0000793 rdfs/label ["EMBL microRNA sequence database file database record - primary accession number field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000794
(ccp/IAO_EXT_0000794 rdf/type owl/Class)
(ccp/IAO_EXT_0000794 rdfs/subClassOf ccp/IAO_EXT_0000223)
(ccp/IAO_EXT_0000794 rdfs/label ["RefSeq record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000795
(ccp/IAO_EXT_0000795 rdf/type owl/Class)
(ccp/IAO_EXT_0000795 rdfs/subClassOf ccp/IAO_EXT_0000794)
(ccp/IAO_EXT_0000795 rdfs/label ["RefSeq catalog record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000796
(ccp/IAO_EXT_0000796 rdf/type owl/Class)
(ccp/IAO_EXT_0000796 rdfs/subClassOf ccp/IAO_EXT_0000795)
(ccp/IAO_EXT_0000796 rdfs/label ["RefSeq catalog record - taxonomy identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000797
(ccp/IAO_EXT_0000797 rdf/type owl/Class)
(ccp/IAO_EXT_0000797 rdfs/subClassOf ccp/IAO_EXT_0000795)
(ccp/IAO_EXT_0000797 rdfs/label ["RefSeq catalog record - species name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000799
(ccp/IAO_EXT_0000799 rdf/type owl/Class)
(ccp/IAO_EXT_0000799 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000799 rdfs/label ["GeneInfo identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000800
(ccp/IAO_EXT_0000800 rdf/type owl/Class)
(ccp/IAO_EXT_0000800 rdfs/subClassOf ccp/IAO_EXT_0000795)
(ccp/IAO_EXT_0000800 rdfs/label ["RefSeq catalog record - RefSeq identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000801
(ccp/IAO_EXT_0000801 rdf/type owl/Class)
(ccp/IAO_EXT_0000801 rdfs/subClassOf ccp/IAO_EXT_0000795)
(ccp/IAO_EXT_0000801 rdfs/label ["RefSeq catalog record - GeneInfo identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000802
(ccp/IAO_EXT_0000802 rdf/type owl/Class)
(ccp/IAO_EXT_0000802 rdfs/subClassOf ccp/IAO_EXT_0000795)
(ccp/IAO_EXT_0000802 rdfs/label ["RefSeq catalog record - release directory inclusion field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000803
(ccp/IAO_EXT_0000803 rdf/type owl/Class)
(ccp/IAO_EXT_0000803 rdfs/subClassOf ccp/IAO_EXT_0000795)
(ccp/IAO_EXT_0000803 rdfs/label ["RefSeq catalog record - RefSeq status field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000804
(ccp/IAO_EXT_0000804 rdf/type owl/Class)
(ccp/IAO_EXT_0000804 rdfs/subClassOf ccp/IAO_EXT_0000795)
(ccp/IAO_EXT_0000804 rdfs/label ["RefSeq catalog record - length field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000805
(ccp/IAO_EXT_0000805 rdf/type owl/Class)
(ccp/IAO_EXT_0000805 rdfs/subClassOf ccp/IAO_EXT_0000795)
(ccp/IAO_EXT_0000805 rdfs/label ["RefSeq catalog record - molecule type field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000806
(ccp/IAO_EXT_0000806 rdf/type owl/Class)
(ccp/IAO_EXT_0000806 rdfs/subClassOf ccp/IAO_EXT_0000795)
(ccp/IAO_EXT_0000806 rdfs/label ["RefSeq catalog record - is predicted field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000807
(ccp/IAO_EXT_0000807 rdf/type owl/Class)
(ccp/IAO_EXT_0000807 rdfs/subClassOf ccp/IAO_EXT_0000000)
(ccp/IAO_EXT_0000807 rdfs/label ["PRO record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000808
(ccp/IAO_EXT_0000808 rdf/type owl/Class)
(ccp/IAO_EXT_0000808 rdfs/subClassOf ccp/IAO_EXT_0000807)
(ccp/IAO_EXT_0000808 rdfs/label ["PRO identifier mapping record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000809
(ccp/IAO_EXT_0000809 rdf/type owl/Class)
(ccp/IAO_EXT_0000809 rdfs/subClassOf ccp/IAO_EXT_0000717)
(ccp/IAO_EXT_0000809 rdfs/label ["EMBL microRNA sequence database file database record - sequence version number field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000810
(ccp/IAO_EXT_0000810 rdf/type owl/Class)
(ccp/IAO_EXT_0000810 rdfs/subClassOf ccp/IAO_EXT_0000197)
(ccp/IAO_EXT_0000810 rdfs/label ["PRO record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000811
(ccp/IAO_EXT_0000811 rdf/type owl/Class)
(ccp/IAO_EXT_0000811 rdfs/subClassOf ccp/IAO_EXT_0000810)
(ccp/IAO_EXT_0000811 rdfs/label ["PRO identifier mapping record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000812
(ccp/IAO_EXT_0000812 rdf/type owl/Class)
(ccp/IAO_EXT_0000812 rdfs/subClassOf ccp/IAO_EXT_0000717)
(ccp/IAO_EXT_0000812 rdfs/label ["EMBL microRNA sequence database file database record - sequence topology field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000813
(ccp/IAO_EXT_0000813 rdf/type owl/Class)
(ccp/IAO_EXT_0000813 rdfs/subClassOf ccp/IAO_EXT_0000811)
(ccp/IAO_EXT_0000813 rdfs/label ["PRO identifier mapping record - mapping type field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000814
(ccp/IAO_EXT_0000814 rdf/type owl/Class)
(ccp/IAO_EXT_0000814 rdfs/subClassOf ccp/IAO_EXT_0000811)
(ccp/IAO_EXT_0000814 rdfs/label ["PRO identifier mapping record - target record identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000815
(ccp/IAO_EXT_0000815 rdf/type owl/Class)
(ccp/IAO_EXT_0000815 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000815 rdfs/label ["PRO identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000816
(ccp/IAO_EXT_0000816 rdf/type owl/Class)
(ccp/IAO_EXT_0000816 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0000816 rdfs/label ["target record identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000817
(ccp/IAO_EXT_0000817 rdf/type owl/Class)
(ccp/IAO_EXT_0000817 rdfs/subClassOf ccp/IAO_EXT_0000811)
(ccp/IAO_EXT_0000817 rdfs/label ["PRO identifier mapping record - PRO identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000818
(ccp/IAO_EXT_0000818 rdf/type owl/Class)
(ccp/IAO_EXT_0000818 rdfs/subClassOf ccp/IAO_EXT_0000000)
(ccp/IAO_EXT_0000818 rdfs/label ["miRBase record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000819
(ccp/IAO_EXT_0000819 rdf/type owl/Class)
(ccp/IAO_EXT_0000819 rdfs/subClassOf ccp/IAO_EXT_0000000)
(ccp/IAO_EXT_0000819 rdfs/label ["PharmGKB record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000820
(ccp/IAO_EXT_0000820 rdf/type owl/Class)
(ccp/IAO_EXT_0000820 rdfs/subClassOf ccp/IAO_EXT_0000819)
(ccp/IAO_EXT_0000820 rdfs/label ["PharmGKB disease record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000821
(ccp/IAO_EXT_0000821 rdf/type owl/Class)
(ccp/IAO_EXT_0000821 rdfs/subClassOf ccp/IAO_EXT_0000819)
(ccp/IAO_EXT_0000821 rdfs/label ["PharmGKB drug record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000822
(ccp/IAO_EXT_0000822 rdf/type owl/Class)
(ccp/IAO_EXT_0000822 rdfs/subClassOf ccp/IAO_EXT_0000819)
(ccp/IAO_EXT_0000822 rdfs/label ["PharmGKB gene record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000823
(ccp/IAO_EXT_0000823 rdf/type owl/Class)
(ccp/IAO_EXT_0000823 rdfs/subClassOf ccp/IAO_EXT_0000819)
(ccp/IAO_EXT_0000823 rdfs/label ["PharmGKB relation record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000824
(ccp/IAO_EXT_0000824 rdf/type owl/Class)
(ccp/IAO_EXT_0000824 rdfs/subClassOf ccp/IAO_EXT_0000717)
(ccp/IAO_EXT_0000824 rdfs/label ["EMBL microRNA sequence database file database record - molecule type field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000825
(ccp/IAO_EXT_0000825 rdf/type owl/Class)
(ccp/IAO_EXT_0000825 rdfs/subClassOf ccp/IAO_EXT_0000818)
(ccp/IAO_EXT_0000825 rdfs/label ["miRBase microRNA sequence feature record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000826
(ccp/IAO_EXT_0000826 rdf/type owl/Class)
(ccp/IAO_EXT_0000826 rdfs/subClassOf ccp/IAO_EXT_0000818)
(ccp/IAO_EXT_0000826 rdfs/label ["miRBase microRNA modified base sequence feature record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000827
(ccp/IAO_EXT_0000827 rdf/type owl/Class)
(ccp/IAO_EXT_0000827 rdfs/subClassOf ccp/IAO_EXT_0000818)
(ccp/IAO_EXT_0000827 rdfs/label ["EMBL microRNA sequence database file database record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000828
(ccp/IAO_EXT_0000828 rdf/type owl/Class)
(ccp/IAO_EXT_0000828 rdfs/subClassOf ccp/IAO_EXT_0000717)
(ccp/IAO_EXT_0000828 rdfs/label ["EMBL microRNA sequence database file database record - data class field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000829
(ccp/IAO_EXT_0000829 rdf/type owl/Class)
(ccp/IAO_EXT_0000829 rdfs/subClassOf ccp/IAO_EXT_0000717)
(ccp/IAO_EXT_0000829 rdfs/label ["EMBL microRNA sequence database file database record - taxonomic division field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000830
(ccp/IAO_EXT_0000830 rdf/type owl/Class)
(ccp/IAO_EXT_0000830 rdfs/subClassOf ccp/IAO_EXT_0000717)
(ccp/IAO_EXT_0000830 rdfs/label ["EMBL microRNA sequence database file database record - sequence length in base pairs field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000831
(ccp/IAO_EXT_0000831 rdf/type owl/Class)
(ccp/IAO_EXT_0000831 rdfs/subClassOf ccp/IAO_EXT_0000717)
(ccp/IAO_EXT_0000831 rdfs/label ["EMBL microRNA sequence database file database record - accession number field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000832
(ccp/IAO_EXT_0000832 rdf/type owl/Class)
(ccp/IAO_EXT_0000832 rdfs/subClassOf ccp/IAO_EXT_0000717)
(ccp/IAO_EXT_0000832 rdfs/label ["EMBL microRNA sequence database file database record - project identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000833
(ccp/IAO_EXT_0000833 rdf/type owl/Class)
(ccp/IAO_EXT_0000833 rdfs/subClassOf ccp/IAO_EXT_0000717)
(ccp/IAO_EXT_0000833 rdfs/label ["EMBL microRNA sequence database file database record - dates field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000834
(ccp/IAO_EXT_0000834 rdf/type owl/Class)
(ccp/IAO_EXT_0000834 rdfs/subClassOf ccp/IAO_EXT_0000717)
(ccp/IAO_EXT_0000834 rdfs/label ["EMBL microRNA sequence database file database record - description field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000835
(ccp/IAO_EXT_0000835 rdf/type owl/Class)
(ccp/IAO_EXT_0000835 rdfs/subClassOf ccp/IAO_EXT_0000717)
(ccp/IAO_EXT_0000835 rdfs/label ["EMBL microRNA sequence database file database record - keywords field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000836
(ccp/IAO_EXT_0000836 rdf/type owl/Class)
(ccp/IAO_EXT_0000836 rdfs/subClassOf ccp/IAO_EXT_0000717)
(ccp/IAO_EXT_0000836 rdfs/label ["EMBL microRNA sequence database file database record - organism species name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000837
(ccp/IAO_EXT_0000837 rdf/type owl/Class)
(ccp/IAO_EXT_0000837 rdfs/subClassOf ccp/IAO_EXT_0000717)
(ccp/IAO_EXT_0000837 rdfs/label ["EMBL microRNA sequence database file database record - organism classification field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000838
(ccp/IAO_EXT_0000838 rdf/type owl/Class)
(ccp/IAO_EXT_0000838 rdfs/subClassOf ccp/IAO_EXT_0000717)
(ccp/IAO_EXT_0000838 rdfs/label ["EMBL microRNA sequence database file database record - organelle field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000839
(ccp/IAO_EXT_0000839 rdf/type owl/Class)
(ccp/IAO_EXT_0000839 rdfs/subClassOf ccp/IAO_EXT_0000717)
(ccp/IAO_EXT_0000839 rdfs/label ["EMBL microRNA sequence database file database record - reference citation field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000840
(ccp/IAO_EXT_0000840 rdf/type owl/Class)
(ccp/IAO_EXT_0000840 rdfs/subClassOf ccp/IAO_EXT_0000717)
(ccp/IAO_EXT_0000840 rdfs/label ["EMBL microRNA sequence database file database record - database cross-reference field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000841
(ccp/IAO_EXT_0000841 rdf/type owl/Class)
(ccp/IAO_EXT_0000841 rdfs/subClassOf ccp/IAO_EXT_0000717)
(ccp/IAO_EXT_0000841 rdfs/label ["EMBL microRNA sequence database file database record - comments field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000842
(ccp/IAO_EXT_0000842 rdf/type owl/Class)
(ccp/IAO_EXT_0000842 rdfs/subClassOf ccp/IAO_EXT_0000717)
(ccp/IAO_EXT_0000842 rdfs/label ["EMBL microRNA sequence database file database record - sequence features field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000843
(ccp/IAO_EXT_0000843 rdf/type owl/Class)
(ccp/IAO_EXT_0000843 rdfs/subClassOf ccp/IAO_EXT_0000717)
(ccp/IAO_EXT_0000843 rdfs/label ["EMBL microRNA sequence database file database record - sequence length field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000844
(ccp/IAO_EXT_0000844 rdf/type owl/Class)
(ccp/IAO_EXT_0000844 rdfs/subClassOf ccp/IAO_EXT_0000717)
(ccp/IAO_EXT_0000844 rdfs/label ["EMBL microRNA sequence database file database record - number of As field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000845
(ccp/IAO_EXT_0000845 rdf/type owl/Class)
(ccp/IAO_EXT_0000845 rdfs/subClassOf ccp/IAO_EXT_0000717)
(ccp/IAO_EXT_0000845 rdfs/label ["EMBL microRNA sequence database file database record - number of Cs field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000846
(ccp/IAO_EXT_0000846 rdf/type owl/Class)
(ccp/IAO_EXT_0000846 rdfs/subClassOf ccp/IAO_EXT_0000717)
(ccp/IAO_EXT_0000846 rdfs/label ["EMBL microRNA sequence database file database record - number of Gs field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000847
(ccp/IAO_EXT_0000847 rdf/type owl/Class)
(ccp/IAO_EXT_0000847 rdfs/subClassOf ccp/IAO_EXT_0000717)
(ccp/IAO_EXT_0000847 rdfs/label ["EMBL microRNA sequence database file database record - number of Ts field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000848
(ccp/IAO_EXT_0000848 rdf/type owl/Class)
(ccp/IAO_EXT_0000848 rdfs/subClassOf ccp/IAO_EXT_0000717)
(ccp/IAO_EXT_0000848 rdfs/label ["EMBL microRNA sequence database file database record - number of others field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000849
(ccp/IAO_EXT_0000849 rdf/type owl/Class)
(ccp/IAO_EXT_0000849 rdfs/subClassOf ccp/IAO_EXT_0000717)
(ccp/IAO_EXT_0000849 rdfs/label ["EMBL microRNA sequence database file database record - sequence field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000850
(ccp/IAO_EXT_0000850 rdf/type owl/Class)
(ccp/IAO_EXT_0000850 rdfs/subClassOf ccp/IAO_EXT_0000717)
(ccp/IAO_EXT_0000850 rdfs/label ["EMBL microRNA sequence database file database record - constructed sequence info field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000851
(ccp/IAO_EXT_0000851 rdf/type owl/Class)
(ccp/IAO_EXT_0000851 rdfs/subClassOf ccp/IAO_EXT_0000717)
(ccp/IAO_EXT_0000851 rdfs/label ["EMBL microRNA sequence database file database record - assembly info field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000852
(ccp/IAO_EXT_0000852 rdf/type owl/Class)
(ccp/IAO_EXT_0000852 rdfs/subClassOf ccp/IAO_EXT_0000743)
(ccp/IAO_EXT_0000852 rdfs/label ["miRBase microRNA sequence feature record - key field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000853
(ccp/IAO_EXT_0000853 rdf/type owl/Class)
(ccp/IAO_EXT_0000853 rdfs/subClassOf ccp/IAO_EXT_0000743)
(ccp/IAO_EXT_0000853 rdfs/label ["miRBase microRNA sequence feature record - location field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000854
(ccp/IAO_EXT_0000854 rdf/type owl/Class)
(ccp/IAO_EXT_0000854 rdfs/subClassOf ccp/IAO_EXT_0000743)
(ccp/IAO_EXT_0000854 rdfs/label ["miRBase microRNA sequence feature record - qualifier set field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000855
(ccp/IAO_EXT_0000855 rdf/type owl/Class)
(ccp/IAO_EXT_0000855 rdfs/subClassOf ccp/IAO_EXT_0000222)
(ccp/IAO_EXT_0000855 rdfs/label ["miRBase microRNA sequence feature qualifier set record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000856
(ccp/IAO_EXT_0000856 rdf/type owl/Class)
(ccp/IAO_EXT_0000856 rdfs/subClassOf ccp/IAO_EXT_0000855)
(ccp/IAO_EXT_0000856 rdfs/label ["miRBase microRNA sequence feature qualifier set record - accession field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000857
(ccp/IAO_EXT_0000857 rdf/type owl/Class)
(ccp/IAO_EXT_0000857 rdfs/subClassOf ccp/IAO_EXT_0000855)
(ccp/IAO_EXT_0000857 rdfs/label ["miRBase microRNA sequence feature qualifier set record - product field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000858
(ccp/IAO_EXT_0000858 rdf/type owl/Class)
(ccp/IAO_EXT_0000858 rdfs/subClassOf ccp/IAO_EXT_0000855)
(ccp/IAO_EXT_0000858 rdfs/label ["miRBase microRNA sequence feature qualifier set record - evidence field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000859
(ccp/IAO_EXT_0000859 rdf/type owl/Class)
(ccp/IAO_EXT_0000859 rdfs/subClassOf ccp/IAO_EXT_0000855)
(ccp/IAO_EXT_0000859 rdfs/label ["miRBase microRNA sequence feature qualifier set record - experiment field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000860
(ccp/IAO_EXT_0000860 rdf/type owl/Class)
(ccp/IAO_EXT_0000860 rdfs/subClassOf ccp/IAO_EXT_0000855)
(ccp/IAO_EXT_0000860 rdfs/label ["miRBase microRNA sequence feature qualifier set record - similarity field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000861
(ccp/IAO_EXT_0000861 rdf/type owl/Class)
(ccp/IAO_EXT_0000861 rdfs/subClassOf ccp/IAO_EXT_0000222)
(ccp/IAO_EXT_0000861 rdfs/label ["miRBase modified base sequence feature qualifier set record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000862
(ccp/IAO_EXT_0000862 rdf/type owl/Class)
(ccp/IAO_EXT_0000862 rdfs/subClassOf ccp/IAO_EXT_0000730)
(ccp/IAO_EXT_0000862 rdfs/label ["miRBase microRNA modified base sequence feature record - key field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000863
(ccp/IAO_EXT_0000863 rdf/type owl/Class)
(ccp/IAO_EXT_0000863 rdfs/subClassOf ccp/IAO_EXT_0000730)
(ccp/IAO_EXT_0000863 rdfs/label ["miRBase microRNA modified base sequence feature record - location field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000864
(ccp/IAO_EXT_0000864 rdf/type owl/Class)
(ccp/IAO_EXT_0000864 rdfs/subClassOf ccp/IAO_EXT_0000730)
(ccp/IAO_EXT_0000864 rdfs/label ["miRBase microRNA modified base sequence feature record - qualifier set field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000865
(ccp/IAO_EXT_0000865 rdf/type owl/Class)
(ccp/IAO_EXT_0000865 rdfs/subClassOf ccp/IAO_EXT_0000861)
(ccp/IAO_EXT_0000865 rdfs/label ["miRBase modified base sequence feature qualifier set record - modified base field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000866
(ccp/IAO_EXT_0000866 rdf/type owl/Class)
(ccp/IAO_EXT_0000866 rdfs/subClassOf ccp/IAO_EXT_0000223)
(ccp/IAO_EXT_0000866 rdfs/label ["NCBI gene record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000867
(ccp/IAO_EXT_0000867 rdf/type owl/Class)
(ccp/IAO_EXT_0000867 rdfs/subClassOf ccp/IAO_EXT_0000866)
(ccp/IAO_EXT_0000867 rdfs/label ["NCBI gene RefSeq UniProtKB collaboration record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000868
(ccp/IAO_EXT_0000868 rdf/type owl/Class)
(ccp/IAO_EXT_0000868 rdfs/subClassOf ccp/IAO_EXT_0000866)
(ccp/IAO_EXT_0000868 rdfs/label ["NCBI gene MIM 2 gene record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000869
(ccp/IAO_EXT_0000869 rdf/type owl/Class)
(ccp/IAO_EXT_0000869 rdfs/subClassOf ccp/IAO_EXT_0000866)
(ccp/IAO_EXT_0000869 rdfs/label ["NCBI gene info record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000870
(ccp/IAO_EXT_0000870 rdf/type owl/Class)
(ccp/IAO_EXT_0000870 rdfs/subClassOf ccp/IAO_EXT_0000866)
(ccp/IAO_EXT_0000870 rdfs/label ["NCBI gene 2 RefSeq record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000871
(ccp/IAO_EXT_0000871 rdf/type owl/Class)
(ccp/IAO_EXT_0000871 rdfs/subClassOf ccp/IAO_EXT_0000866)
(ccp/IAO_EXT_0000871 rdfs/label ["NCBI gene 2 PubMed record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000872
(ccp/IAO_EXT_0000872 rdf/type owl/Class)
(ccp/IAO_EXT_0000872 rdfs/subClassOf ccp/IAO_EXT_0000866)
(ccp/IAO_EXT_0000872 rdfs/label ["NCBI gene 2 accession record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000873
(ccp/IAO_EXT_0000873 rdf/type owl/Class)
(ccp/IAO_EXT_0000873 rdfs/subClassOf ccp/IAO_EXT_0000871)
(ccp/IAO_EXT_0000873 rdfs/label ["NCBI gene 2 PubMed record - taxonomy identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000874
(ccp/IAO_EXT_0000874 rdf/type owl/Class)
(ccp/IAO_EXT_0000874 rdfs/subClassOf ccp/IAO_EXT_0000871)
(ccp/IAO_EXT_0000874 rdfs/label ["NCBI gene 2 PubMed record - Entrez gene identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000875
(ccp/IAO_EXT_0000875 rdf/type owl/Class)
(ccp/IAO_EXT_0000875 rdfs/subClassOf ccp/IAO_EXT_0000869)
(ccp/IAO_EXT_0000875 rdfs/label ["NCBI gene info record - taxon identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000876
(ccp/IAO_EXT_0000876 rdf/type owl/Class)
(ccp/IAO_EXT_0000876 rdfs/subClassOf ccp/IAO_EXT_0000869)
(ccp/IAO_EXT_0000876 rdfs/label ["NCBI gene info record - gene identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000877
(ccp/IAO_EXT_0000877 rdf/type owl/Class)
(ccp/IAO_EXT_0000877 rdfs/subClassOf ccp/IAO_EXT_0000869)
(ccp/IAO_EXT_0000877 rdfs/label ["NCBI gene info record - symbol field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000878
(ccp/IAO_EXT_0000878 rdf/type owl/Class)
(ccp/IAO_EXT_0000878 rdfs/subClassOf ccp/IAO_EXT_0000869)
(ccp/IAO_EXT_0000878 rdfs/label ["NCBI gene info record - locus tag field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000879
(ccp/IAO_EXT_0000879 rdf/type owl/Class)
(ccp/IAO_EXT_0000879 rdfs/subClassOf ccp/IAO_EXT_0000869)
(ccp/IAO_EXT_0000879 rdfs/label ["NCBI gene info record - synonym field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000880
(ccp/IAO_EXT_0000880 rdf/type owl/Class)
(ccp/IAO_EXT_0000880 rdfs/subClassOf ccp/IAO_EXT_0000869)
(ccp/IAO_EXT_0000880 rdfs/label ["NCBI gene info record - database cross-reference field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000881
(ccp/IAO_EXT_0000881 rdf/type owl/Class)
(ccp/IAO_EXT_0000881 rdfs/subClassOf ccp/IAO_EXT_0000869)
(ccp/IAO_EXT_0000881 rdfs/label ["NCBI gene info record - chromosome field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000882
(ccp/IAO_EXT_0000882 rdf/type owl/Class)
(ccp/IAO_EXT_0000882 rdfs/subClassOf ccp/IAO_EXT_0000869)
(ccp/IAO_EXT_0000882 rdfs/label ["NCBI gene info record - map location field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000883
(ccp/IAO_EXT_0000883 rdf/type owl/Class)
(ccp/IAO_EXT_0000883 rdfs/subClassOf ccp/IAO_EXT_0000869)
(ccp/IAO_EXT_0000883 rdfs/label ["NCBI gene info record - description field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000884
(ccp/IAO_EXT_0000884 rdf/type owl/Class)
(ccp/IAO_EXT_0000884 rdfs/subClassOf ccp/IAO_EXT_0000869)
(ccp/IAO_EXT_0000884 rdfs/label ["NCBI gene info record - type of gene field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000885
(ccp/IAO_EXT_0000885 rdf/type owl/Class)
(ccp/IAO_EXT_0000885 rdfs/subClassOf ccp/IAO_EXT_0000869)
(ccp/IAO_EXT_0000885 rdfs/label ["NCBI gene info record - symbol from nomenclature authority field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000886
(ccp/IAO_EXT_0000886 rdf/type owl/Class)
(ccp/IAO_EXT_0000886 rdfs/subClassOf ccp/IAO_EXT_0000869)
(ccp/IAO_EXT_0000886 rdfs/label ["NCBI gene info record - full name from nomenclature authority field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000887
(ccp/IAO_EXT_0000887 rdf/type owl/Class)
(ccp/IAO_EXT_0000887 rdfs/subClassOf ccp/IAO_EXT_0000869)
(ccp/IAO_EXT_0000887 rdfs/label ["NCBI gene info record - nomenclature status field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000888
(ccp/IAO_EXT_0000888 rdf/type owl/Class)
(ccp/IAO_EXT_0000888 rdfs/subClassOf ccp/IAO_EXT_0000869)
(ccp/IAO_EXT_0000888 rdfs/label ["NCBI gene info record - other designations field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000889
(ccp/IAO_EXT_0000889 rdf/type owl/Class)
(ccp/IAO_EXT_0000889 rdfs/subClassOf ccp/IAO_EXT_0000869)
(ccp/IAO_EXT_0000889 rdfs/label ["NCBI gene info record - modification date field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000890
(ccp/IAO_EXT_0000890 rdf/type owl/Class)
(ccp/IAO_EXT_0000890 rdfs/subClassOf ccp/IAO_EXT_0000872)
(ccp/IAO_EXT_0000890 rdfs/label ["NCBI gene 2 accession record - taxon identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000891
(ccp/IAO_EXT_0000891 rdf/type owl/Class)
(ccp/IAO_EXT_0000891 rdfs/subClassOf ccp/IAO_EXT_0000872)
(ccp/IAO_EXT_0000891 rdfs/label ["NCBI gene 2 accession record - gene identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000892
(ccp/IAO_EXT_0000892 rdf/type owl/Class)
(ccp/IAO_EXT_0000892 rdfs/subClassOf ccp/IAO_EXT_0000872)
(ccp/IAO_EXT_0000892 rdfs/label ["NCBI gene 2 accession record - status field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000893
(ccp/IAO_EXT_0000893 rdf/type owl/Class)
(ccp/IAO_EXT_0000893 rdfs/subClassOf ccp/IAO_EXT_0000872)
(ccp/IAO_EXT_0000893 rdfs/label ["NCBI gene 2 accession record - RNA nucleotide accession dot version identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000894
(ccp/IAO_EXT_0000894 rdf/type owl/Class)
(ccp/IAO_EXT_0000894 rdfs/subClassOf ccp/IAO_EXT_0000872)
(ccp/IAO_EXT_0000894 rdfs/label ["NCBI gene 2 accession record - RNA nucleotide GeneInfo identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000895
(ccp/IAO_EXT_0000895 rdf/type owl/Class)
(ccp/IAO_EXT_0000895 rdfs/subClassOf ccp/IAO_EXT_0000872)
(ccp/IAO_EXT_0000895 rdfs/label ["NCBI gene 2 accession record - protein accession dot version identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000896
(ccp/IAO_EXT_0000896 rdf/type owl/Class)
(ccp/IAO_EXT_0000896 rdfs/subClassOf ccp/IAO_EXT_0000872)
(ccp/IAO_EXT_0000896 rdfs/label ["NCBI gene 2 accession record - protein GeneInfo identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000897
(ccp/IAO_EXT_0000897 rdf/type owl/Class)
(ccp/IAO_EXT_0000897 rdfs/subClassOf ccp/IAO_EXT_0000872)
(ccp/IAO_EXT_0000897 rdfs/label ["NCBI gene 2 accession record - genomic nucleotide accession dot version identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000898
(ccp/IAO_EXT_0000898 rdf/type owl/Class)
(ccp/IAO_EXT_0000898 rdfs/subClassOf ccp/IAO_EXT_0000872)
(ccp/IAO_EXT_0000898 rdfs/label ["NCBI gene 2 accession record - genomic nucleotide GeneInfo identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000899
(ccp/IAO_EXT_0000899 rdf/type owl/Class)
(ccp/IAO_EXT_0000899 rdfs/subClassOf ccp/IAO_EXT_0000872)
(ccp/IAO_EXT_0000899 rdfs/label ["NCBI gene 2 accession record - start position on the genomic accession field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000900
(ccp/IAO_EXT_0000900 rdf/type owl/Class)
(ccp/IAO_EXT_0000900 rdfs/subClassOf ccp/IAO_EXT_0000872)
(ccp/IAO_EXT_0000900 rdfs/label ["NCBI gene 2 accession record - end position on the genomic accession field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000901
(ccp/IAO_EXT_0000901 rdf/type owl/Class)
(ccp/IAO_EXT_0000901 rdfs/subClassOf ccp/IAO_EXT_0000872)
(ccp/IAO_EXT_0000901 rdfs/label ["NCBI gene 2 accession record - orientation field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000902
(ccp/IAO_EXT_0000902 rdf/type owl/Class)
(ccp/IAO_EXT_0000902 rdfs/subClassOf ccp/IAO_EXT_0000872)
(ccp/IAO_EXT_0000902 rdfs/label ["NCBI gene 2 accession record - assembly field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000903
(ccp/IAO_EXT_0000903 rdf/type owl/Class)
(ccp/IAO_EXT_0000903 rdfs/subClassOf ccp/IAO_EXT_0000872)
(ccp/IAO_EXT_0000903 rdfs/label ["NCBI gene 2 accession record - mature peptide accession dot version identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000904
(ccp/IAO_EXT_0000904 rdf/type owl/Class)
(ccp/IAO_EXT_0000904 rdfs/subClassOf ccp/IAO_EXT_0000872)
(ccp/IAO_EXT_0000904 rdfs/label ["NCBI gene 2 accession record - mature peptide GeneInfo identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000905
(ccp/IAO_EXT_0000905 rdf/type owl/Class)
(ccp/IAO_EXT_0000905 rdfs/subClassOf ccp/IAO_EXT_0000872)
(ccp/IAO_EXT_0000905 rdfs/label ["NCBI gene 2 accession record - symbol field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000906
(ccp/IAO_EXT_0000906 rdf/type owl/Class)
(ccp/IAO_EXT_0000906 rdfs/subClassOf ccp/IAO_EXT_0000870)
(ccp/IAO_EXT_0000906 rdfs/label ["NCBI gene 2 RefSeq record - taxon identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000907
(ccp/IAO_EXT_0000907 rdf/type owl/Class)
(ccp/IAO_EXT_0000907 rdfs/subClassOf ccp/IAO_EXT_0000870)
(ccp/IAO_EXT_0000907 rdfs/label ["NCBI gene 2 RefSeq record - gene identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000908
(ccp/IAO_EXT_0000908 rdf/type owl/Class)
(ccp/IAO_EXT_0000908 rdfs/subClassOf ccp/IAO_EXT_0000870)
(ccp/IAO_EXT_0000908 rdfs/label ["NCBI gene 2 RefSeq record - status field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000909
(ccp/IAO_EXT_0000909 rdf/type owl/Class)
(ccp/IAO_EXT_0000909 rdfs/subClassOf ccp/IAO_EXT_0000870)
(ccp/IAO_EXT_0000909 rdfs/label ["NCBI gene 2 RefSeq record - RNA nucleotide accession dot version identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000910
(ccp/IAO_EXT_0000910 rdf/type owl/Class)
(ccp/IAO_EXT_0000910 rdfs/subClassOf ccp/IAO_EXT_0000870)
(ccp/IAO_EXT_0000910 rdfs/label ["NCBI gene 2 RefSeq record - RNA nucleotide GeneInfo identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000911
(ccp/IAO_EXT_0000911 rdf/type owl/Class)
(ccp/IAO_EXT_0000911 rdfs/subClassOf ccp/IAO_EXT_0000870)
(ccp/IAO_EXT_0000911 rdfs/label ["NCBI gene 2 RefSeq record - protein accession dot version identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000912
(ccp/IAO_EXT_0000912 rdf/type owl/Class)
(ccp/IAO_EXT_0000912 rdfs/subClassOf ccp/IAO_EXT_0000870)
(ccp/IAO_EXT_0000912 rdfs/label ["NCBI gene 2 RefSeq record - protein GeneInfo identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000913
(ccp/IAO_EXT_0000913 rdf/type owl/Class)
(ccp/IAO_EXT_0000913 rdfs/subClassOf ccp/IAO_EXT_0000870)
(ccp/IAO_EXT_0000913 rdfs/label ["NCBI gene 2 RefSeq record - genomic nucleotide accession dot version identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000914
(ccp/IAO_EXT_0000914 rdf/type owl/Class)
(ccp/IAO_EXT_0000914 rdfs/subClassOf ccp/IAO_EXT_0000870)
(ccp/IAO_EXT_0000914 rdfs/label ["NCBI gene 2 RefSeq record - genomic nucleotide GeneInfo identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000915
(ccp/IAO_EXT_0000915 rdf/type owl/Class)
(ccp/IAO_EXT_0000915 rdfs/subClassOf ccp/IAO_EXT_0000870)
(ccp/IAO_EXT_0000915 rdfs/label ["NCBI gene 2 RefSeq record - start position on the genomic accession field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000916
(ccp/IAO_EXT_0000916 rdf/type owl/Class)
(ccp/IAO_EXT_0000916 rdfs/subClassOf ccp/IAO_EXT_0000870)
(ccp/IAO_EXT_0000916 rdfs/label ["NCBI gene 2 RefSeq record - end position on the genomic accession field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000917
(ccp/IAO_EXT_0000917 rdf/type owl/Class)
(ccp/IAO_EXT_0000917 rdfs/subClassOf ccp/IAO_EXT_0000870)
(ccp/IAO_EXT_0000917 rdfs/label ["NCBI gene 2 RefSeq record - orientation field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000918
(ccp/IAO_EXT_0000918 rdf/type owl/Class)
(ccp/IAO_EXT_0000918 rdfs/subClassOf ccp/IAO_EXT_0000870)
(ccp/IAO_EXT_0000918 rdfs/label ["NCBI gene 2 RefSeq record - mature peptide accession dot verstion identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000919
(ccp/IAO_EXT_0000919 rdf/type owl/Class)
(ccp/IAO_EXT_0000919 rdfs/subClassOf ccp/IAO_EXT_0000870)
(ccp/IAO_EXT_0000919 rdfs/label ["NCBI gene 2 RefSeq record - mature peptide GeneInfo identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000920
(ccp/IAO_EXT_0000920 rdf/type owl/Class)
(ccp/IAO_EXT_0000920 rdfs/subClassOf ccp/IAO_EXT_0000870)
(ccp/IAO_EXT_0000920 rdfs/label ["NCBI gene 2 RefSeq record - symbol field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000921
(ccp/IAO_EXT_0000921 rdf/type owl/Class)
(ccp/IAO_EXT_0000921 rdfs/subClassOf ccp/IAO_EXT_0000868)
(ccp/IAO_EXT_0000921 rdfs/label ["NCBI gene MIM 2 gene record - MIM identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000922
(ccp/IAO_EXT_0000922 rdf/type owl/Class)
(ccp/IAO_EXT_0000922 rdfs/subClassOf ccp/IAO_EXT_0000868)
(ccp/IAO_EXT_0000922 rdfs/label ["NCBI gene MIM 2 gene record - Entrez gene identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000923
(ccp/IAO_EXT_0000923 rdf/type owl/Class)
(ccp/IAO_EXT_0000923 rdfs/subClassOf ccp/IAO_EXT_0000868)
(ccp/IAO_EXT_0000923 rdfs/label ["NCBI gene MIM 2 gene record - association type field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000924
(ccp/IAO_EXT_0000924 rdf/type owl/Class)
(ccp/IAO_EXT_0000924 rdfs/subClassOf ccp/IAO_EXT_0000868)
(ccp/IAO_EXT_0000924 rdfs/label ["NCBI gene MIM 2 gene record - sources field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000925
(ccp/IAO_EXT_0000925 rdf/type owl/Class)
(ccp/IAO_EXT_0000925 rdfs/subClassOf ccp/IAO_EXT_0000868)
(ccp/IAO_EXT_0000925 rdfs/label ["NCBI gene MIM 2 gene record - MedGen identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000926
(ccp/IAO_EXT_0000926 rdf/type owl/Class)
(ccp/IAO_EXT_0000926 rdfs/subClassOf ccp/IAO_EXT_0000868)
(ccp/IAO_EXT_0000926 rdfs/label ["NCBI gene MIM 2 gene record - comment field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000927
(ccp/IAO_EXT_0000927 rdf/type owl/Class)
(ccp/IAO_EXT_0000927 rdfs/subClassOf ccp/IAO_EXT_0000867)
(ccp/IAO_EXT_0000927 rdfs/label ["NCBI gene RefSeq UniProtKB collaboration record - RefSeq protein identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000928
(ccp/IAO_EXT_0000928 rdf/type owl/Class)
(ccp/IAO_EXT_0000928 rdfs/subClassOf ccp/IAO_EXT_0000867)
(ccp/IAO_EXT_0000928 rdfs/label ["NCBI gene RefSeq UniProtKB collaboration record - UniProt identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000929
(ccp/IAO_EXT_0000929 rdf/type owl/Class)
(ccp/IAO_EXT_0000929 rdfs/subClassOf ccp/IAO_EXT_0000871)
(ccp/IAO_EXT_0000929 rdfs/label ["NCBI gene 2 PubMed record - PubMed identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000930
(ccp/IAO_EXT_0000930 rdf/type owl/Class)
(ccp/IAO_EXT_0000930 rdfs/subClassOf ccp/IAO_EXT_0000870)
(ccp/IAO_EXT_0000930 rdfs/label ["NCBI gene 2 RefSeq record - assembly field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000931
(ccp/IAO_EXT_0000931 rdf/type owl/Class)
(ccp/IAO_EXT_0000931 rdfs/subClassOf ccp/IAO_EXT_0000217)
(ccp/IAO_EXT_0000931 rdfs/label ["UniProt knowledge base record - accession field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000932
(ccp/IAO_EXT_0000932 rdf/type owl/Class)
(ccp/IAO_EXT_0000932 rdfs/subClassOf ccp/IAO_EXT_0000217)
(ccp/IAO_EXT_0000932 rdfs/label ["UniProt knowledge base record - name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000933
(ccp/IAO_EXT_0000933 rdf/type owl/Class)
(ccp/IAO_EXT_0000933 rdfs/subClassOf ccp/IAO_EXT_0000217)
(ccp/IAO_EXT_0000933 rdfs/label ["UniProt knowledge base record - protein field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000934
(ccp/IAO_EXT_0000934 rdf/type owl/Class)
(ccp/IAO_EXT_0000934 rdfs/subClassOf ccp/IAO_EXT_0000217)
(ccp/IAO_EXT_0000934 rdfs/label ["UniProt knowledge base record - gene field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000935
(ccp/IAO_EXT_0000935 rdf/type owl/Class)
(ccp/IAO_EXT_0000935 rdfs/subClassOf ccp/IAO_EXT_0000217)
(ccp/IAO_EXT_0000935 rdfs/label ["UniProt knowledge base record - organism field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000936
(ccp/IAO_EXT_0000936 rdf/type owl/Class)
(ccp/IAO_EXT_0000936 rdfs/subClassOf ccp/IAO_EXT_0000217)
(ccp/IAO_EXT_0000936 rdfs/label ["UniProt knowledge base record - organism host field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000937
(ccp/IAO_EXT_0000937 rdf/type owl/Class)
(ccp/IAO_EXT_0000937 rdfs/subClassOf ccp/IAO_EXT_0000217)
(ccp/IAO_EXT_0000937 rdfs/label ["UniProt knowledge base record - gene location field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000938
(ccp/IAO_EXT_0000938 rdf/type owl/Class)
(ccp/IAO_EXT_0000938 rdfs/subClassOf ccp/IAO_EXT_0000217)
(ccp/IAO_EXT_0000938 rdfs/label ["UniProt knowledge base record - reference field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000939
(ccp/IAO_EXT_0000939 rdf/type owl/Class)
(ccp/IAO_EXT_0000939 rdfs/subClassOf ccp/IAO_EXT_0000217)
(ccp/IAO_EXT_0000939 rdfs/label ["UniProt knowledge base record - comment field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000940
(ccp/IAO_EXT_0000940 rdf/type owl/Class)
(ccp/IAO_EXT_0000940 rdfs/subClassOf ccp/IAO_EXT_0000217)
(ccp/IAO_EXT_0000940 rdfs/label ["UniProt knowledge base record - database reference field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000941
(ccp/IAO_EXT_0000941 rdf/type owl/Class)
(ccp/IAO_EXT_0000941 rdfs/subClassOf ccp/IAO_EXT_0000217)
(ccp/IAO_EXT_0000941 rdfs/label ["UniProt knowledge base record - protein existence field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000942
(ccp/IAO_EXT_0000942 rdf/type owl/Class)
(ccp/IAO_EXT_0000942 rdfs/subClassOf ccp/IAO_EXT_0000217)
(ccp/IAO_EXT_0000942 rdfs/label ["UniProt knowledge base record - keyword field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000943
(ccp/IAO_EXT_0000943 rdf/type owl/Class)
(ccp/IAO_EXT_0000943 rdfs/subClassOf ccp/IAO_EXT_0000217)
(ccp/IAO_EXT_0000943 rdfs/label ["UniProt knowledge base record - feature field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000944
(ccp/IAO_EXT_0000944 rdf/type owl/Class)
(ccp/IAO_EXT_0000944 rdfs/subClassOf ccp/IAO_EXT_0000217)
(ccp/IAO_EXT_0000944 rdfs/label ["UniProt knowledge base record - evidence field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000945
(ccp/IAO_EXT_0000945 rdf/type owl/Class)
(ccp/IAO_EXT_0000945 rdfs/subClassOf ccp/IAO_EXT_0000217)
(ccp/IAO_EXT_0000945 rdfs/label ["UniProt knowledge base record - sequence field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000946
(ccp/IAO_EXT_0000946 rdf/type owl/Class)
(ccp/IAO_EXT_0000946 rdfs/subClassOf ccp/IAO_EXT_0000217)
(ccp/IAO_EXT_0000946 rdfs/label ["UniProt knowledge base record - dataset field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000947
(ccp/IAO_EXT_0000947 rdf/type owl/Class)
(ccp/IAO_EXT_0000947 rdfs/subClassOf ccp/IAO_EXT_0000217)
(ccp/IAO_EXT_0000947 rdfs/label ["UniProt knowledge base record - modified field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000948
(ccp/IAO_EXT_0000948 rdf/type owl/Class)
(ccp/IAO_EXT_0000948 rdfs/subClassOf ccp/IAO_EXT_0000217)
(ccp/IAO_EXT_0000948 rdfs/label ["UniProt knowledge base record - version field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000949
(ccp/IAO_EXT_0000949 rdf/type owl/Class)
(ccp/IAO_EXT_0000949 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0000949 rdfs/label ["UniProt citation record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000950
(ccp/IAO_EXT_0000950 rdf/type owl/Class)
(ccp/IAO_EXT_0000950 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0000950 rdfs/label ["UniProt comment record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000951
(ccp/IAO_EXT_0000951 rdf/type owl/Class)
(ccp/IAO_EXT_0000951 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0000951 rdfs/label ["UniProt absorption record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000952
(ccp/IAO_EXT_0000952 rdf/type owl/Class)
(ccp/IAO_EXT_0000952 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0000952 rdfs/label ["UniProt conflict record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000953
(ccp/IAO_EXT_0000953 rdf/type owl/Class)
(ccp/IAO_EXT_0000953 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0000953 rdfs/label ["UniProt kinetics record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000954
(ccp/IAO_EXT_0000954 rdf/type owl/Class)
(ccp/IAO_EXT_0000954 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0000954 rdfs/label ["UniProt link record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000955
(ccp/IAO_EXT_0000955 rdf/type owl/Class)
(ccp/IAO_EXT_0000955 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0000955 rdfs/label ["UniProt database reference record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000956
(ccp/IAO_EXT_0000956 rdf/type owl/Class)
(ccp/IAO_EXT_0000956 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0000956 rdfs/label ["UniProt event record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000957
(ccp/IAO_EXT_0000957 rdf/type owl/Class)
(ccp/IAO_EXT_0000957 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0000957 rdfs/label ["UniProt evidence string record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000958
(ccp/IAO_EXT_0000958 rdf/type owl/Class)
(ccp/IAO_EXT_0000958 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0000958 rdfs/label ["UniProt evidence record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000959
(ccp/IAO_EXT_0000959 rdf/type owl/Class)
(ccp/IAO_EXT_0000959 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0000959 rdfs/label ["UniProt feature record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000960
(ccp/IAO_EXT_0000960 rdf/type owl/Class)
(ccp/IAO_EXT_0000960 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0000960 rdfs/label ["UniProt gene type record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000961
(ccp/IAO_EXT_0000961 rdf/type owl/Class)
(ccp/IAO_EXT_0000961 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0000961 rdfs/label ["UniProt molecule type record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000962
(ccp/IAO_EXT_0000962 rdf/type owl/Class)
(ccp/IAO_EXT_0000962 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0000962 rdfs/label ["UniProt gene location record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000963
(ccp/IAO_EXT_0000963 rdf/type owl/Class)
(ccp/IAO_EXT_0000963 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0000963 rdfs/label ["UniProt gene name record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000964
(ccp/IAO_EXT_0000964 rdf/type owl/Class)
(ccp/IAO_EXT_0000964 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0000964 rdfs/label ["UniProt imported from record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000965
(ccp/IAO_EXT_0000965 rdf/type owl/Class)
(ccp/IAO_EXT_0000965 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0000965 rdfs/label ["UniProt interactant record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000966
(ccp/IAO_EXT_0000966 rdf/type owl/Class)
(ccp/IAO_EXT_0000966 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0000966 rdfs/label ["UniProt isoform record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000967
(ccp/IAO_EXT_0000967 rdf/type owl/Class)
(ccp/IAO_EXT_0000967 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0000967 rdfs/label ["UniProt note record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000968
(ccp/IAO_EXT_0000968 rdf/type owl/Class)
(ccp/IAO_EXT_0000968 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0000968 rdfs/label ["UniProt subcellular location record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000969
(ccp/IAO_EXT_0000969 rdf/type owl/Class)
(ccp/IAO_EXT_0000969 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0000969 rdfs/label ["UniProt keyword record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000970
(ccp/IAO_EXT_0000970 rdf/type owl/Class)
(ccp/IAO_EXT_0000970 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0000970 rdfs/label ["UniProt location record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000971
(ccp/IAO_EXT_0000971 rdf/type owl/Class)
(ccp/IAO_EXT_0000971 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0000971 rdfs/label ["UniProt organism name record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000972
(ccp/IAO_EXT_0000972 rdf/type owl/Class)
(ccp/IAO_EXT_0000972 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0000972 rdfs/label ["UniProt organism record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000973
(ccp/IAO_EXT_0000973 rdf/type owl/Class)
(ccp/IAO_EXT_0000973 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0000973 rdfs/label ["UniProt lineage record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000974
(ccp/IAO_EXT_0000974 rdf/type owl/Class)
(ccp/IAO_EXT_0000974 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0000974 rdfs/label ["UniProt position record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000975
(ccp/IAO_EXT_0000975 rdf/type owl/Class)
(ccp/IAO_EXT_0000975 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0000975 rdfs/label ["UniProt property record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000976
(ccp/IAO_EXT_0000976 rdf/type owl/Class)
(ccp/IAO_EXT_0000976 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0000976 rdfs/label ["UniProt protein existence record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000977
(ccp/IAO_EXT_0000977 rdf/type owl/Class)
(ccp/IAO_EXT_0000977 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0000977 rdfs/label ["UniProt protein record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000978
(ccp/IAO_EXT_0000978 rdf/type owl/Class)
(ccp/IAO_EXT_0000978 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0000978 rdfs/label ["UniProt alternative name record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000979
(ccp/IAO_EXT_0000979 rdf/type owl/Class)
(ccp/IAO_EXT_0000979 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0000979 rdfs/label ["UniProt component record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000980
(ccp/IAO_EXT_0000980 rdf/type owl/Class)
(ccp/IAO_EXT_0000980 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0000980 rdfs/label ["UniProt domain record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000981
(ccp/IAO_EXT_0000981 rdf/type owl/Class)
(ccp/IAO_EXT_0000981 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0000981 rdfs/label ["UniProt recommended name record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000982
(ccp/IAO_EXT_0000982 rdf/type owl/Class)
(ccp/IAO_EXT_0000982 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0000982 rdfs/label ["UniProt submitted name record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000983
(ccp/IAO_EXT_0000983 rdf/type owl/Class)
(ccp/IAO_EXT_0000983 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0000983 rdfs/label ["UniProt reference record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000984
(ccp/IAO_EXT_0000984 rdf/type owl/Class)
(ccp/IAO_EXT_0000984 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0000984 rdfs/label ["UniProt source data record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000985
(ccp/IAO_EXT_0000985 rdf/type owl/Class)
(ccp/IAO_EXT_0000985 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0000985 rdfs/label ["UniProt source record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000986
(ccp/IAO_EXT_0000986 rdf/type owl/Class)
(ccp/IAO_EXT_0000986 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0000986 rdfs/label ["UniProt status record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000987
(ccp/IAO_EXT_0000987 rdf/type owl/Class)
(ccp/IAO_EXT_0000987 rdfs/subClassOf ccp/IAO_EXT_0000061)
(ccp/IAO_EXT_0000987 rdfs/label ["Sparse UniProt record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000988
(ccp/IAO_EXT_0000988 rdf/type owl/Class)
(ccp/IAO_EXT_0000988 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0000988 rdfs/label ["Sparse UniProt record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000989
(ccp/IAO_EXT_0000989 rdf/type owl/Class)
(ccp/IAO_EXT_0000989 rdfs/subClassOf ccp/IAO_EXT_0000988)
(ccp/IAO_EXT_0000989 rdfs/label ["Sparse UniProt record - primary accession field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000990
(ccp/IAO_EXT_0000990 rdf/type owl/Class)
(ccp/IAO_EXT_0000990 rdfs/subClassOf ccp/IAO_EXT_0000988)
(ccp/IAO_EXT_0000990 rdfs/label ["Sparse UniProt record - accession field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000991
(ccp/IAO_EXT_0000991 rdf/type owl/Class)
(ccp/IAO_EXT_0000991 rdfs/subClassOf ccp/IAO_EXT_0000988)
(ccp/IAO_EXT_0000991 rdfs/label ["Sparse UniProt record - name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000992
(ccp/IAO_EXT_0000992 rdf/type owl/Class)
(ccp/IAO_EXT_0000992 rdfs/subClassOf ccp/IAO_EXT_0000988)
(ccp/IAO_EXT_0000992 rdfs/label ["Sparse UniProt record - organism field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000993
(ccp/IAO_EXT_0000993 rdf/type owl/Class)
(ccp/IAO_EXT_0000993 rdfs/subClassOf ccp/IAO_EXT_0000988)
(ccp/IAO_EXT_0000993 rdfs/label ["Sparse UniProt record - organism host field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000994
(ccp/IAO_EXT_0000994 rdf/type owl/Class)
(ccp/IAO_EXT_0000994 rdfs/subClassOf ccp/IAO_EXT_0000988)
(ccp/IAO_EXT_0000994 rdfs/label ["Sparse UniProt record - database reference field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000995
(ccp/IAO_EXT_0000995 rdf/type owl/Class)
(ccp/IAO_EXT_0000995 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0000995 rdfs/label ["UniProt evidence string record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000996
(ccp/IAO_EXT_0000996 rdf/type owl/Class)
(ccp/IAO_EXT_0000996 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0000996 rdfs/label ["UniProt event record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000997
(ccp/IAO_EXT_0000997 rdf/type owl/Class)
(ccp/IAO_EXT_0000997 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0000997 rdfs/label ["UniProt database reference record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000998
(ccp/IAO_EXT_0000998 rdf/type owl/Class)
(ccp/IAO_EXT_0000998 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0000998 rdfs/label ["UniProt link record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000999
(ccp/IAO_EXT_0000999 rdf/type owl/Class)
(ccp/IAO_EXT_0000999 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0000999 rdfs/label ["UniProt kinetics record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001000
(ccp/IAO_EXT_0001000 rdf/type owl/Class)
(ccp/IAO_EXT_0001000 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0001000 rdfs/label ["UniProt conflict record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001001
(ccp/IAO_EXT_0001001 rdf/type owl/Class)
(ccp/IAO_EXT_0001001 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0001001 rdfs/label ["UniProt absorption record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001002
(ccp/IAO_EXT_0001002 rdf/type owl/Class)
(ccp/IAO_EXT_0001002 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0001002 rdfs/label ["UniProt comment record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001003
(ccp/IAO_EXT_0001003 rdf/type owl/Class)
(ccp/IAO_EXT_0001003 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0001003 rdfs/label ["UniProt citation record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001004
(ccp/IAO_EXT_0001004 rdf/type owl/Class)
(ccp/IAO_EXT_0001004 rdfs/subClassOf ccp/IAO_EXT_0000217)
(ccp/IAO_EXT_0001004 rdfs/label ["UniProt knowledge base record - created field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001005
(ccp/IAO_EXT_0001005 rdf/type owl/Class)
(ccp/IAO_EXT_0001005 rdfs/subClassOf ccp/IAO_EXT_0000229)
(ccp/IAO_EXT_0001005 rdfs/label ["PharmGKB disease record - accession identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001006
(ccp/IAO_EXT_0001006 rdf/type owl/Class)
(ccp/IAO_EXT_0001006 rdfs/subClassOf ccp/IAO_EXT_0000229)
(ccp/IAO_EXT_0001006 rdfs/label ["PharmGKB disease record - name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001007
(ccp/IAO_EXT_0001007 rdf/type owl/Class)
(ccp/IAO_EXT_0001007 rdfs/subClassOf ccp/IAO_EXT_0000229)
(ccp/IAO_EXT_0001007 rdfs/label ["PharmGKB disease record - alternative name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001008
(ccp/IAO_EXT_0001008 rdf/type owl/Class)
(ccp/IAO_EXT_0001008 rdfs/subClassOf ccp/IAO_EXT_0000229)
(ccp/IAO_EXT_0001008 rdfs/label ["PharmGKB disease record - cross-reference field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001009
(ccp/IAO_EXT_0001009 rdf/type owl/Class)
(ccp/IAO_EXT_0001009 rdfs/subClassOf ccp/IAO_EXT_0000229)
(ccp/IAO_EXT_0001009 rdfs/label ["PharmGKB disease record - external vocabulary field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001010
(ccp/IAO_EXT_0001010 rdf/type owl/Class)
(ccp/IAO_EXT_0001010 rdfs/subClassOf ccp/IAO_EXT_0000230)
(ccp/IAO_EXT_0001010 rdfs/label ["PharmGKB drug record - accession identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001011
(ccp/IAO_EXT_0001011 rdf/type owl/Class)
(ccp/IAO_EXT_0001011 rdfs/subClassOf ccp/IAO_EXT_0000230)
(ccp/IAO_EXT_0001011 rdfs/label ["PharmGKB drug record - name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001012
(ccp/IAO_EXT_0001012 rdf/type owl/Class)
(ccp/IAO_EXT_0001012 rdfs/subClassOf ccp/IAO_EXT_0000230)
(ccp/IAO_EXT_0001012 rdfs/label ["PharmGKB drug record - generic names field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001013
(ccp/IAO_EXT_0001013 rdf/type owl/Class)
(ccp/IAO_EXT_0001013 rdfs/subClassOf ccp/IAO_EXT_0000230)
(ccp/IAO_EXT_0001013 rdfs/label ["PharmGKB drug record - trade names field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001014
(ccp/IAO_EXT_0001014 rdf/type owl/Class)
(ccp/IAO_EXT_0001014 rdfs/subClassOf ccp/IAO_EXT_0000230)
(ccp/IAO_EXT_0001014 rdfs/label ["PharmGKB drug record - brand mixtures field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001015
(ccp/IAO_EXT_0001015 rdf/type owl/Class)
(ccp/IAO_EXT_0001015 rdfs/subClassOf ccp/IAO_EXT_0000230)
(ccp/IAO_EXT_0001015 rdfs/label ["PharmGKB drug record - type field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001016
(ccp/IAO_EXT_0001016 rdf/type owl/Class)
(ccp/IAO_EXT_0001016 rdfs/subClassOf ccp/IAO_EXT_0000230)
(ccp/IAO_EXT_0001016 rdfs/label ["PharmGKB drug record - cross-reference field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001017
(ccp/IAO_EXT_0001017 rdf/type owl/Class)
(ccp/IAO_EXT_0001017 rdfs/subClassOf ccp/IAO_EXT_0000230)
(ccp/IAO_EXT_0001017 rdfs/label ["PharmGKB drug record - URL field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001018
(ccp/IAO_EXT_0001018 rdf/type owl/Class)
(ccp/IAO_EXT_0001018 rdfs/subClassOf ccp/IAO_EXT_0000230)
(ccp/IAO_EXT_0001018 rdfs/label ["PharmGKB drug record - SMILES field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001019
(ccp/IAO_EXT_0001019 rdf/type owl/Class)
(ccp/IAO_EXT_0001019 rdfs/subClassOf ccp/IAO_EXT_0000230)
(ccp/IAO_EXT_0001019 rdfs/label ["PharmGKB drug record - InChI field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001020
(ccp/IAO_EXT_0001020 rdf/type owl/Class)
(ccp/IAO_EXT_0001020 rdfs/subClassOf ccp/IAO_EXT_0000230)
(ccp/IAO_EXT_0001020 rdfs/label ["PharmGKB drug record - dosing guideline field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001021
(ccp/IAO_EXT_0001021 rdf/type owl/Class)
(ccp/IAO_EXT_0001021 rdfs/subClassOf ccp/IAO_EXT_0000230)
(ccp/IAO_EXT_0001021 rdfs/label ["PharmGKB drug record - external vocabulary field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001022
(ccp/IAO_EXT_0001022 rdf/type owl/Class)
(ccp/IAO_EXT_0001022 rdfs/subClassOf ccp/IAO_EXT_0000228)
(ccp/IAO_EXT_0001022 rdfs/label ["PharmGKB gene record - accession identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001023
(ccp/IAO_EXT_0001023 rdf/type owl/Class)
(ccp/IAO_EXT_0001023 rdfs/subClassOf ccp/IAO_EXT_0000228)
(ccp/IAO_EXT_0001023 rdfs/label ["PharmGKB gene record - Entrez gene identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001024
(ccp/IAO_EXT_0001024 rdf/type owl/Class)
(ccp/IAO_EXT_0001024 rdfs/subClassOf ccp/IAO_EXT_0000228)
(ccp/IAO_EXT_0001024 rdfs/label ["PharmGKB gene record - HGNC identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001025
(ccp/IAO_EXT_0001025 rdf/type owl/Class)
(ccp/IAO_EXT_0001025 rdfs/subClassOf ccp/IAO_EXT_0000228)
(ccp/IAO_EXT_0001025 rdfs/label ["PharmGKB gene record - Ensembl gene identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001026
(ccp/IAO_EXT_0001026 rdf/type owl/Class)
(ccp/IAO_EXT_0001026 rdfs/subClassOf ccp/IAO_EXT_0000228)
(ccp/IAO_EXT_0001026 rdfs/label ["PharmGKB gene record - name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001027
(ccp/IAO_EXT_0001027 rdf/type owl/Class)
(ccp/IAO_EXT_0001027 rdfs/subClassOf ccp/IAO_EXT_0000228)
(ccp/IAO_EXT_0001027 rdfs/label ["PharmGKB gene record - symbol field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001028
(ccp/IAO_EXT_0001028 rdf/type owl/Class)
(ccp/IAO_EXT_0001028 rdfs/subClassOf ccp/IAO_EXT_0000228)
(ccp/IAO_EXT_0001028 rdfs/label ["PharmGKB gene record - alternative symbol identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001029
(ccp/IAO_EXT_0001029 rdf/type owl/Class)
(ccp/IAO_EXT_0001029 rdfs/subClassOf ccp/IAO_EXT_0000228)
(ccp/IAO_EXT_0001029 rdfs/label ["PharmGKB gene record - is VIP field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001030
(ccp/IAO_EXT_0001030 rdf/type owl/Class)
(ccp/IAO_EXT_0001030 rdfs/subClassOf ccp/IAO_EXT_0000228)
(ccp/IAO_EXT_0001030 rdfs/label ["PharmGKB gene record - has variant annotation field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001031
(ccp/IAO_EXT_0001031 rdf/type owl/Class)
(ccp/IAO_EXT_0001031 rdfs/subClassOf ccp/IAO_EXT_0000228)
(ccp/IAO_EXT_0001031 rdfs/label ["PharmGKB gene record - cross-reference field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001032
(ccp/IAO_EXT_0001032 rdf/type owl/Class)
(ccp/IAO_EXT_0001032 rdfs/subClassOf ccp/IAO_EXT_0000228)
(ccp/IAO_EXT_0001032 rdfs/label ["PharmGKB gene record - has CPIC dosing guideline field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001033
(ccp/IAO_EXT_0001033 rdf/type owl/Class)
(ccp/IAO_EXT_0001033 rdfs/subClassOf ccp/IAO_EXT_0000228)
(ccp/IAO_EXT_0001033 rdfs/label ["PharmGKB gene record - chromosome field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001034
(ccp/IAO_EXT_0001034 rdf/type owl/Class)
(ccp/IAO_EXT_0001034 rdfs/subClassOf ccp/IAO_EXT_0000228)
(ccp/IAO_EXT_0001034 rdfs/label ["PharmGKB gene record - chromosomal start field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001035
(ccp/IAO_EXT_0001035 rdf/type owl/Class)
(ccp/IAO_EXT_0001035 rdfs/subClassOf ccp/IAO_EXT_0000228)
(ccp/IAO_EXT_0001035 rdfs/label ["PharmGKB gene record - chromosomal end field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001036
(ccp/IAO_EXT_0001036 rdf/type owl/Class)
(ccp/IAO_EXT_0001036 rdfs/subClassOf ccp/IAO_EXT_0000231)
(ccp/IAO_EXT_0001036 rdfs/label ["PharmGKB relation record - entity 1 identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001037
(ccp/IAO_EXT_0001037 rdf/type owl/Class)
(ccp/IAO_EXT_0001037 rdfs/subClassOf ccp/IAO_EXT_0000231)
(ccp/IAO_EXT_0001037 rdfs/label ["PharmGKB relation record - entity 1 name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001038
(ccp/IAO_EXT_0001038 rdf/type owl/Class)
(ccp/IAO_EXT_0001038 rdfs/subClassOf ccp/IAO_EXT_0000231)
(ccp/IAO_EXT_0001038 rdfs/label ["PharmGKB relation record - entity 1 type field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001039
(ccp/IAO_EXT_0001039 rdf/type owl/Class)
(ccp/IAO_EXT_0001039 rdfs/subClassOf ccp/IAO_EXT_0000231)
(ccp/IAO_EXT_0001039 rdfs/label ["PharmGKB relation record - entity 2 name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001040
(ccp/IAO_EXT_0001040 rdf/type owl/Class)
(ccp/IAO_EXT_0001040 rdfs/subClassOf ccp/IAO_EXT_0000231)
(ccp/IAO_EXT_0001040 rdfs/label ["PharmGKB relation record - entity 2 type field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001041
(ccp/IAO_EXT_0001041 rdf/type owl/Class)
(ccp/IAO_EXT_0001041 rdfs/subClassOf ccp/IAO_EXT_0000231)
(ccp/IAO_EXT_0001041 rdfs/label ["PharmGKB relation record - entity 2 identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001042
(ccp/IAO_EXT_0001042 rdf/type owl/Class)
(ccp/IAO_EXT_0001042 rdfs/subClassOf ccp/IAO_EXT_0000231)
(ccp/IAO_EXT_0001042 rdfs/label ["PharmGKB relation record - evidence field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001043
(ccp/IAO_EXT_0001043 rdf/type owl/Class)
(ccp/IAO_EXT_0001043 rdfs/subClassOf ccp/IAO_EXT_0000231)
(ccp/IAO_EXT_0001043 rdfs/label ["PharmGKB relation record - association field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001044
(ccp/IAO_EXT_0001044 rdf/type owl/Class)
(ccp/IAO_EXT_0001044 rdfs/subClassOf ccp/IAO_EXT_0000231)
(ccp/IAO_EXT_0001044 rdfs/label ["PharmGKB relation record - pharmacokinetics field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001045
(ccp/IAO_EXT_0001045 rdf/type owl/Class)
(ccp/IAO_EXT_0001045 rdfs/subClassOf ccp/IAO_EXT_0000231)
(ccp/IAO_EXT_0001045 rdfs/label ["PharmGKB relation record - pharmacodynamics field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001046
(ccp/IAO_EXT_0001046 rdf/type owl/Class)
(ccp/IAO_EXT_0001046 rdfs/subClassOf ccp/IAO_EXT_0000231)
(ccp/IAO_EXT_0001046 rdfs/label ["PharmGKB relation record - PubMed identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001047
(ccp/IAO_EXT_0001047 rdf/type owl/Class)
(ccp/IAO_EXT_0001047 rdfs/subClassOf ccp/IAO_EXT_0000228)
(ccp/IAO_EXT_0001047 rdfs/label ["PharmGKB gene record - alternative name identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001048
(ccp/IAO_EXT_0001048 rdf/type owl/Class)
(ccp/IAO_EXT_0001048 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0001048 rdfs/label ["UniProt evidence record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001049
(ccp/IAO_EXT_0001049 rdf/type owl/Class)
(ccp/IAO_EXT_0001049 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0001049 rdfs/label ["UniProt feature record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001050
(ccp/IAO_EXT_0001050 rdf/type owl/Class)
(ccp/IAO_EXT_0001050 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0001050 rdfs/label ["UniProt gene type record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001051
(ccp/IAO_EXT_0001051 rdf/type owl/Class)
(ccp/IAO_EXT_0001051 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0001051 rdfs/label ["UniProt molecule type record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001052
(ccp/IAO_EXT_0001052 rdf/type owl/Class)
(ccp/IAO_EXT_0001052 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0001052 rdfs/label ["UniProt gene location record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001053
(ccp/IAO_EXT_0001053 rdf/type owl/Class)
(ccp/IAO_EXT_0001053 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0001053 rdfs/label ["UniProt gene name record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001054
(ccp/IAO_EXT_0001054 rdf/type owl/Class)
(ccp/IAO_EXT_0001054 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0001054 rdfs/label ["UniProt imported from record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001055
(ccp/IAO_EXT_0001055 rdf/type owl/Class)
(ccp/IAO_EXT_0001055 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0001055 rdfs/label ["UniProt interactant record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001056
(ccp/IAO_EXT_0001056 rdf/type owl/Class)
(ccp/IAO_EXT_0001056 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0001056 rdfs/label ["UniProt isoform record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001057
(ccp/IAO_EXT_0001057 rdf/type owl/Class)
(ccp/IAO_EXT_0001057 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0001057 rdfs/label ["UniProt note record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001058
(ccp/IAO_EXT_0001058 rdf/type owl/Class)
(ccp/IAO_EXT_0001058 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0001058 rdfs/label ["UniProt keyword record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001059
(ccp/IAO_EXT_0001059 rdf/type owl/Class)
(ccp/IAO_EXT_0001059 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0001059 rdfs/label ["UniProt location record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001060
(ccp/IAO_EXT_0001060 rdf/type owl/Class)
(ccp/IAO_EXT_0001060 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0001060 rdfs/label ["UniProt organism name record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001061
(ccp/IAO_EXT_0001061 rdf/type owl/Class)
(ccp/IAO_EXT_0001061 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0001061 rdfs/label ["UniProt organism record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001062
(ccp/IAO_EXT_0001062 rdf/type owl/Class)
(ccp/IAO_EXT_0001062 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0001062 rdfs/label ["UniProt lineage record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001063
(ccp/IAO_EXT_0001063 rdf/type owl/Class)
(ccp/IAO_EXT_0001063 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0001063 rdfs/label ["UniProt position record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001064
(ccp/IAO_EXT_0001064 rdf/type owl/Class)
(ccp/IAO_EXT_0001064 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0001064 rdfs/label ["UniProt property record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001065
(ccp/IAO_EXT_0001065 rdf/type owl/Class)
(ccp/IAO_EXT_0001065 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0001065 rdfs/label ["UniProt protein existence record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001066
(ccp/IAO_EXT_0001066 rdf/type owl/Class)
(ccp/IAO_EXT_0001066 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0001066 rdfs/label ["UniProt protein record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001067
(ccp/IAO_EXT_0001067 rdf/type owl/Class)
(ccp/IAO_EXT_0001067 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0001067 rdfs/label ["UniProt alternative name record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001068
(ccp/IAO_EXT_0001068 rdf/type owl/Class)
(ccp/IAO_EXT_0001068 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0001068 rdfs/label ["UniProt component record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001069
(ccp/IAO_EXT_0001069 rdf/type owl/Class)
(ccp/IAO_EXT_0001069 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0001069 rdfs/label ["UniProt domain record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001070
(ccp/IAO_EXT_0001070 rdf/type owl/Class)
(ccp/IAO_EXT_0001070 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0001070 rdfs/label ["UniProt recommended name record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001071
(ccp/IAO_EXT_0001071 rdf/type owl/Class)
(ccp/IAO_EXT_0001071 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0001071 rdfs/label ["UniProt submitted name record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001072
(ccp/IAO_EXT_0001072 rdf/type owl/Class)
(ccp/IAO_EXT_0001072 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0001072 rdfs/label ["UniProt reference record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001073
(ccp/IAO_EXT_0001073 rdf/type owl/Class)
(ccp/IAO_EXT_0001073 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0001073 rdfs/label ["UniProt source data record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001074
(ccp/IAO_EXT_0001074 rdf/type owl/Class)
(ccp/IAO_EXT_0001074 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0001074 rdfs/label ["UniProt source record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001075
(ccp/IAO_EXT_0001075 rdf/type owl/Class)
(ccp/IAO_EXT_0001075 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0001075 rdfs/label ["UniProt status record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001076
(ccp/IAO_EXT_0001076 rdf/type owl/Class)
(ccp/IAO_EXT_0001076 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0001076 rdfs/label ["UniProt subcellular location record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001077
(ccp/IAO_EXT_0001077 rdf/type owl/Class)
(ccp/IAO_EXT_0001077 rdfs/subClassOf ccp/IAO_EXT_0001003)
(ccp/IAO_EXT_0001077 rdfs/label ["UniProt citation record - title field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001078
(ccp/IAO_EXT_0001078 rdf/type owl/Class)
(ccp/IAO_EXT_0001078 rdfs/subClassOf ccp/IAO_EXT_0001003)
(ccp/IAO_EXT_0001078 rdfs/label ["UniProt citation record - editor list field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001079
(ccp/IAO_EXT_0001079 rdf/type owl/Class)
(ccp/IAO_EXT_0001079 rdfs/subClassOf ccp/IAO_EXT_0001003)
(ccp/IAO_EXT_0001079 rdfs/label ["UniProt citation record - author list field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001080
(ccp/IAO_EXT_0001080 rdf/type owl/Class)
(ccp/IAO_EXT_0001080 rdfs/subClassOf ccp/IAO_EXT_0001003)
(ccp/IAO_EXT_0001080 rdfs/label ["UniProt citation record - locator field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001081
(ccp/IAO_EXT_0001081 rdf/type owl/Class)
(ccp/IAO_EXT_0001081 rdfs/subClassOf ccp/IAO_EXT_0001003)
(ccp/IAO_EXT_0001081 rdfs/label ["UniProt citation record - database reference field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001082
(ccp/IAO_EXT_0001082 rdf/type owl/Class)
(ccp/IAO_EXT_0001082 rdfs/subClassOf ccp/IAO_EXT_0001003)
(ccp/IAO_EXT_0001082 rdfs/label ["UniProt citation record - type field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001083
(ccp/IAO_EXT_0001083 rdf/type owl/Class)
(ccp/IAO_EXT_0001083 rdfs/subClassOf ccp/IAO_EXT_0001003)
(ccp/IAO_EXT_0001083 rdfs/label ["UniProt citation record - date field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001084
(ccp/IAO_EXT_0001084 rdf/type owl/Class)
(ccp/IAO_EXT_0001084 rdfs/subClassOf ccp/IAO_EXT_0001003)
(ccp/IAO_EXT_0001084 rdfs/label ["UniProt citation record - name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001085
(ccp/IAO_EXT_0001085 rdf/type owl/Class)
(ccp/IAO_EXT_0001085 rdfs/subClassOf ccp/IAO_EXT_0001003)
(ccp/IAO_EXT_0001085 rdfs/label ["UniProt citation record - volume field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001086
(ccp/IAO_EXT_0001086 rdf/type owl/Class)
(ccp/IAO_EXT_0001086 rdfs/subClassOf ccp/IAO_EXT_0001003)
(ccp/IAO_EXT_0001086 rdfs/label ["UniProt citation record - first field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001087
(ccp/IAO_EXT_0001087 rdf/type owl/Class)
(ccp/IAO_EXT_0001087 rdfs/subClassOf ccp/IAO_EXT_0001003)
(ccp/IAO_EXT_0001087 rdfs/label ["UniProt citation record - last field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001088
(ccp/IAO_EXT_0001088 rdf/type owl/Class)
(ccp/IAO_EXT_0001088 rdfs/subClassOf ccp/IAO_EXT_0001003)
(ccp/IAO_EXT_0001088 rdfs/label ["UniProt citation record - publisher field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001089
(ccp/IAO_EXT_0001089 rdf/type owl/Class)
(ccp/IAO_EXT_0001089 rdfs/subClassOf ccp/IAO_EXT_0001003)
(ccp/IAO_EXT_0001089 rdfs/label ["UniProt citation record - city field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001090
(ccp/IAO_EXT_0001090 rdf/type owl/Class)
(ccp/IAO_EXT_0001090 rdfs/subClassOf ccp/IAO_EXT_0001003)
(ccp/IAO_EXT_0001090 rdfs/label ["UniProt citation record - database field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001091
(ccp/IAO_EXT_0001091 rdf/type owl/Class)
(ccp/IAO_EXT_0001091 rdfs/subClassOf ccp/IAO_EXT_0001003)
(ccp/IAO_EXT_0001091 rdfs/label ["UniProt citation record - number field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001092
(ccp/IAO_EXT_0001092 rdf/type owl/Class)
(ccp/IAO_EXT_0001092 rdfs/subClassOf ccp/IAO_EXT_0001003)
(ccp/IAO_EXT_0001092 rdfs/label ["UniProt citation record - institute field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001093
(ccp/IAO_EXT_0001093 rdf/type owl/Class)
(ccp/IAO_EXT_0001093 rdfs/subClassOf ccp/IAO_EXT_0001003)
(ccp/IAO_EXT_0001093 rdfs/label ["UniProt citation record - country field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001094
(ccp/IAO_EXT_0001094 rdf/type owl/Class)
(ccp/IAO_EXT_0001094 rdfs/subClassOf ccp/IAO_EXT_0001002)
(ccp/IAO_EXT_0001094 rdfs/label ["UniProt comment record - absorption field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001095
(ccp/IAO_EXT_0001095 rdf/type owl/Class)
(ccp/IAO_EXT_0001095 rdfs/subClassOf ccp/IAO_EXT_0001002)
(ccp/IAO_EXT_0001095 rdfs/label ["UniProt comment record - kinetics field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001096
(ccp/IAO_EXT_0001096 rdf/type owl/Class)
(ccp/IAO_EXT_0001096 rdfs/subClassOf ccp/IAO_EXT_0001002)
(ccp/IAO_EXT_0001096 rdfs/label ["UniProt comment record - pH dependence field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001097
(ccp/IAO_EXT_0001097 rdf/type owl/Class)
(ccp/IAO_EXT_0001097 rdfs/subClassOf ccp/IAO_EXT_0001002)
(ccp/IAO_EXT_0001097 rdfs/label ["UniProt comment record - redox potential field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001098
(ccp/IAO_EXT_0001098 rdf/type owl/Class)
(ccp/IAO_EXT_0001098 rdfs/subClassOf ccp/IAO_EXT_0001002)
(ccp/IAO_EXT_0001098 rdfs/label ["UniProt comment record - temperature dependence field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001099
(ccp/IAO_EXT_0001099 rdf/type owl/Class)
(ccp/IAO_EXT_0001099 rdfs/subClassOf ccp/IAO_EXT_0001002)
(ccp/IAO_EXT_0001099 rdfs/label ["UniProt comment record - molecule field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001100
(ccp/IAO_EXT_0001100 rdf/type owl/Class)
(ccp/IAO_EXT_0001100 rdfs/subClassOf ccp/IAO_EXT_0001002)
(ccp/IAO_EXT_0001100 rdfs/label ["UniProt comment record - subcellular location field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001101
(ccp/IAO_EXT_0001101 rdf/type owl/Class)
(ccp/IAO_EXT_0001101 rdfs/subClassOf ccp/IAO_EXT_0001002)
(ccp/IAO_EXT_0001101 rdfs/label ["UniProt comment record - conflict field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001102
(ccp/IAO_EXT_0001102 rdf/type owl/Class)
(ccp/IAO_EXT_0001102 rdfs/subClassOf ccp/IAO_EXT_0001002)
(ccp/IAO_EXT_0001102 rdfs/label ["UniProt comment record - link field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001103
(ccp/IAO_EXT_0001103 rdf/type owl/Class)
(ccp/IAO_EXT_0001103 rdfs/subClassOf ccp/IAO_EXT_0001002)
(ccp/IAO_EXT_0001103 rdfs/label ["UniProt comment record - event field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001104
(ccp/IAO_EXT_0001104 rdf/type owl/Class)
(ccp/IAO_EXT_0001104 rdfs/subClassOf ccp/IAO_EXT_0001002)
(ccp/IAO_EXT_0001104 rdfs/label ["UniProt comment record - isoform field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001105
(ccp/IAO_EXT_0001105 rdf/type owl/Class)
(ccp/IAO_EXT_0001105 rdfs/subClassOf ccp/IAO_EXT_0001002)
(ccp/IAO_EXT_0001105 rdfs/label ["UniProt comment record - interactant field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001106
(ccp/IAO_EXT_0001106 rdf/type owl/Class)
(ccp/IAO_EXT_0001106 rdfs/subClassOf ccp/IAO_EXT_0001002)
(ccp/IAO_EXT_0001106 rdfs/label ["UniProt comment record - organisms differ field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001107
(ccp/IAO_EXT_0001107 rdf/type owl/Class)
(ccp/IAO_EXT_0001107 rdfs/subClassOf ccp/IAO_EXT_0001002)
(ccp/IAO_EXT_0001107 rdfs/label ["UniProt comment record - experiments field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001108
(ccp/IAO_EXT_0001108 rdf/type owl/Class)
(ccp/IAO_EXT_0001108 rdfs/subClassOf ccp/IAO_EXT_0001002)
(ccp/IAO_EXT_0001108 rdfs/label ["UniProt comment record - location field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001109
(ccp/IAO_EXT_0001109 rdf/type owl/Class)
(ccp/IAO_EXT_0001109 rdfs/subClassOf ccp/IAO_EXT_0001002)
(ccp/IAO_EXT_0001109 rdfs/label ["UniProt comment record - text field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001110
(ccp/IAO_EXT_0001110 rdf/type owl/Class)
(ccp/IAO_EXT_0001110 rdfs/subClassOf ccp/IAO_EXT_0001002)
(ccp/IAO_EXT_0001110 rdfs/label ["UniProt comment record - type field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001111
(ccp/IAO_EXT_0001111 rdf/type owl/Class)
(ccp/IAO_EXT_0001111 rdfs/subClassOf ccp/IAO_EXT_0001002)
(ccp/IAO_EXT_0001111 rdfs/label ["UniProt comment record - location type field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001112
(ccp/IAO_EXT_0001112 rdf/type owl/Class)
(ccp/IAO_EXT_0001112 rdfs/subClassOf ccp/IAO_EXT_0001002)
(ccp/IAO_EXT_0001112 rdfs/label ["UniProt comment record - name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001113
(ccp/IAO_EXT_0001113 rdf/type owl/Class)
(ccp/IAO_EXT_0001113 rdfs/subClassOf ccp/IAO_EXT_0001002)
(ccp/IAO_EXT_0001113 rdfs/label ["UniProt comment record - mass field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001114
(ccp/IAO_EXT_0001114 rdf/type owl/Class)
(ccp/IAO_EXT_0001114 rdfs/subClassOf ccp/IAO_EXT_0001002)
(ccp/IAO_EXT_0001114 rdfs/label ["UniProt comment record - error field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001115
(ccp/IAO_EXT_0001115 rdf/type owl/Class)
(ccp/IAO_EXT_0001115 rdfs/subClassOf ccp/IAO_EXT_0001002)
(ccp/IAO_EXT_0001115 rdfs/label ["UniProt comment record - method field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001116
(ccp/IAO_EXT_0001116 rdf/type owl/Class)
(ccp/IAO_EXT_0001116 rdfs/subClassOf ccp/IAO_EXT_0001002)
(ccp/IAO_EXT_0001116 rdfs/label ["UniProt comment record - evidence field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001117
(ccp/IAO_EXT_0001117 rdf/type owl/Class)
(ccp/IAO_EXT_0001117 rdfs/subClassOf ccp/IAO_EXT_0001001)
(ccp/IAO_EXT_0001117 rdfs/label ["UniProt absorption record - max field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001118
(ccp/IAO_EXT_0001118 rdf/type owl/Class)
(ccp/IAO_EXT_0001118 rdfs/subClassOf ccp/IAO_EXT_0001001)
(ccp/IAO_EXT_0001118 rdfs/label ["UniProt absorption record - text field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001119
(ccp/IAO_EXT_0001119 rdf/type owl/Class)
(ccp/IAO_EXT_0001119 rdfs/subClassOf ccp/IAO_EXT_0001000)
(ccp/IAO_EXT_0001119 rdfs/label ["UniProt conflict record - type field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001120
(ccp/IAO_EXT_0001120 rdf/type owl/Class)
(ccp/IAO_EXT_0001120 rdfs/subClassOf ccp/IAO_EXT_0001000)
(ccp/IAO_EXT_0001120 rdfs/label ["UniProt conflict record - reference field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001121
(ccp/IAO_EXT_0001121 rdf/type owl/Class)
(ccp/IAO_EXT_0001121 rdfs/subClassOf ccp/IAO_EXT_0000999)
(ccp/IAO_EXT_0001121 rdfs/label ["UniProt kinetics record - Km field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001122
(ccp/IAO_EXT_0001122 rdf/type owl/Class)
(ccp/IAO_EXT_0001122 rdfs/subClassOf ccp/IAO_EXT_0000999)
(ccp/IAO_EXT_0001122 rdfs/label ["UniProt kinetics record - Vmax field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001123
(ccp/IAO_EXT_0001123 rdf/type owl/Class)
(ccp/IAO_EXT_0001123 rdfs/subClassOf ccp/IAO_EXT_0000999)
(ccp/IAO_EXT_0001123 rdfs/label ["UniProt kinetics record - text field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001124
(ccp/IAO_EXT_0001124 rdf/type owl/Class)
(ccp/IAO_EXT_0001124 rdfs/subClassOf ccp/IAO_EXT_0000998)
(ccp/IAO_EXT_0001124 rdfs/label ["UniProt link record - URI field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001125
(ccp/IAO_EXT_0001125 rdf/type owl/Class)
(ccp/IAO_EXT_0001125 rdfs/subClassOf ccp/IAO_EXT_0000997)
(ccp/IAO_EXT_0001125 rdfs/label ["UniProt database reference record - property field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001126
(ccp/IAO_EXT_0001126 rdf/type owl/Class)
(ccp/IAO_EXT_0001126 rdfs/subClassOf ccp/IAO_EXT_0000997)
(ccp/IAO_EXT_0001126 rdfs/label ["UniProt database reference record - type field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001127
(ccp/IAO_EXT_0001127 rdf/type owl/Class)
(ccp/IAO_EXT_0001127 rdfs/subClassOf ccp/IAO_EXT_0000997)
(ccp/IAO_EXT_0001127 rdfs/label ["UniProt database reference record - identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001128
(ccp/IAO_EXT_0001128 rdf/type owl/Class)
(ccp/IAO_EXT_0001128 rdfs/subClassOf ccp/IAO_EXT_0000997)
(ccp/IAO_EXT_0001128 rdfs/label ["UniProt database reference record - evidence field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001129
(ccp/IAO_EXT_0001129 rdf/type owl/Class)
(ccp/IAO_EXT_0001129 rdfs/subClassOf ccp/IAO_EXT_0000996)
(ccp/IAO_EXT_0001129 rdfs/label ["UniProt event record - type field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001130
(ccp/IAO_EXT_0001130 rdf/type owl/Class)
(ccp/IAO_EXT_0001130 rdfs/subClassOf ccp/IAO_EXT_0000995)
(ccp/IAO_EXT_0001130 rdfs/label ["UniProt evidence string record - value field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001131
(ccp/IAO_EXT_0001131 rdf/type owl/Class)
(ccp/IAO_EXT_0001131 rdfs/subClassOf ccp/IAO_EXT_0000995)
(ccp/IAO_EXT_0001131 rdfs/label ["UniProt evidence string record - evidence field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001132
(ccp/IAO_EXT_0001132 rdf/type owl/Class)
(ccp/IAO_EXT_0001132 rdfs/subClassOf ccp/IAO_EXT_0000995)
(ccp/IAO_EXT_0001132 rdfs/label ["UniProt evidence string record - status field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001133
(ccp/IAO_EXT_0001133 rdf/type owl/Class)
(ccp/IAO_EXT_0001133 rdfs/subClassOf ccp/IAO_EXT_0001048)
(ccp/IAO_EXT_0001133 rdfs/label ["UniProt evidence record - source field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001134
(ccp/IAO_EXT_0001134 rdf/type owl/Class)
(ccp/IAO_EXT_0001134 rdfs/subClassOf ccp/IAO_EXT_0001048)
(ccp/IAO_EXT_0001134 rdfs/label ["UniProt evidence record - imported from field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001135
(ccp/IAO_EXT_0001135 rdf/type owl/Class)
(ccp/IAO_EXT_0001135 rdfs/subClassOf ccp/IAO_EXT_0001048)
(ccp/IAO_EXT_0001135 rdfs/label ["UniProt evidence record - type field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001136
(ccp/IAO_EXT_0001136 rdf/type owl/Class)
(ccp/IAO_EXT_0001136 rdfs/subClassOf ccp/IAO_EXT_0001048)
(ccp/IAO_EXT_0001136 rdfs/label ["UniProt evidence record - key field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001137
(ccp/IAO_EXT_0001137 rdf/type owl/Class)
(ccp/IAO_EXT_0001137 rdfs/subClassOf ccp/IAO_EXT_0001049)
(ccp/IAO_EXT_0001137 rdfs/label ["UniProt feature record - original field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001138
(ccp/IAO_EXT_0001138 rdf/type owl/Class)
(ccp/IAO_EXT_0001138 rdfs/subClassOf ccp/IAO_EXT_0001049)
(ccp/IAO_EXT_0001138 rdfs/label ["UniProt feature record - variation field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001139
(ccp/IAO_EXT_0001139 rdf/type owl/Class)
(ccp/IAO_EXT_0001139 rdfs/subClassOf ccp/IAO_EXT_0001049)
(ccp/IAO_EXT_0001139 rdfs/label ["UniProt feature record - location field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001140
(ccp/IAO_EXT_0001140 rdf/type owl/Class)
(ccp/IAO_EXT_0001140 rdfs/subClassOf ccp/IAO_EXT_0001049)
(ccp/IAO_EXT_0001140 rdfs/label ["UniProt feature record - type field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001141
(ccp/IAO_EXT_0001141 rdf/type owl/Class)
(ccp/IAO_EXT_0001141 rdfs/subClassOf ccp/IAO_EXT_0001049)
(ccp/IAO_EXT_0001141 rdfs/label ["UniProt feature record - status field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001142
(ccp/IAO_EXT_0001142 rdf/type owl/Class)
(ccp/IAO_EXT_0001142 rdfs/subClassOf ccp/IAO_EXT_0001049)
(ccp/IAO_EXT_0001142 rdfs/label ["UniProt feature record - identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001143
(ccp/IAO_EXT_0001143 rdf/type owl/Class)
(ccp/IAO_EXT_0001143 rdfs/subClassOf ccp/IAO_EXT_0001049)
(ccp/IAO_EXT_0001143 rdfs/label ["UniProt feature record - description field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001144
(ccp/IAO_EXT_0001144 rdf/type owl/Class)
(ccp/IAO_EXT_0001144 rdfs/subClassOf ccp/IAO_EXT_0001049)
(ccp/IAO_EXT_0001144 rdfs/label ["UniProt feature record - evidence field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001145
(ccp/IAO_EXT_0001145 rdf/type owl/Class)
(ccp/IAO_EXT_0001145 rdfs/subClassOf ccp/IAO_EXT_0001049)
(ccp/IAO_EXT_0001145 rdfs/label ["UniProt feature record - reference field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001146
(ccp/IAO_EXT_0001146 rdf/type owl/Class)
(ccp/IAO_EXT_0001146 rdfs/subClassOf ccp/IAO_EXT_0001050)
(ccp/IAO_EXT_0001146 rdfs/label ["UniProt gene type record - name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001147
(ccp/IAO_EXT_0001147 rdf/type owl/Class)
(ccp/IAO_EXT_0001147 rdfs/subClassOf ccp/IAO_EXT_0001051)
(ccp/IAO_EXT_0001147 rdfs/label ["UniProt molecule type record - identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001148
(ccp/IAO_EXT_0001148 rdf/type owl/Class)
(ccp/IAO_EXT_0001148 rdfs/subClassOf ccp/IAO_EXT_0001051)
(ccp/IAO_EXT_0001148 rdfs/label ["UniProt molecule type record - value field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001149
(ccp/IAO_EXT_0001149 rdf/type owl/Class)
(ccp/IAO_EXT_0001149 rdfs/subClassOf ccp/IAO_EXT_0001052)
(ccp/IAO_EXT_0001149 rdfs/label ["UniProt gene location record - status field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001150
(ccp/IAO_EXT_0001150 rdf/type owl/Class)
(ccp/IAO_EXT_0001150 rdfs/subClassOf ccp/IAO_EXT_0001052)
(ccp/IAO_EXT_0001150 rdfs/label ["UniProt gene location record - type field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001151
(ccp/IAO_EXT_0001151 rdf/type owl/Class)
(ccp/IAO_EXT_0001151 rdfs/subClassOf ccp/IAO_EXT_0001052)
(ccp/IAO_EXT_0001151 rdfs/label ["UniProt gene location record - evidence field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001152
(ccp/IAO_EXT_0001152 rdf/type owl/Class)
(ccp/IAO_EXT_0001152 rdfs/subClassOf ccp/IAO_EXT_0001053)
(ccp/IAO_EXT_0001152 rdfs/label ["UniProt gene name record - value field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001153
(ccp/IAO_EXT_0001153 rdf/type owl/Class)
(ccp/IAO_EXT_0001153 rdfs/subClassOf ccp/IAO_EXT_0001053)
(ccp/IAO_EXT_0001153 rdfs/label ["UniProt gene name record - evidence field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001154
(ccp/IAO_EXT_0001154 rdf/type owl/Class)
(ccp/IAO_EXT_0001154 rdfs/subClassOf ccp/IAO_EXT_0001053)
(ccp/IAO_EXT_0001154 rdfs/label ["UniProt gene name record - type field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001155
(ccp/IAO_EXT_0001155 rdf/type owl/Class)
(ccp/IAO_EXT_0001155 rdfs/subClassOf ccp/IAO_EXT_0001054)
(ccp/IAO_EXT_0001155 rdfs/label ["UniProt imported from record - database reference field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001156
(ccp/IAO_EXT_0001156 rdf/type owl/Class)
(ccp/IAO_EXT_0001156 rdfs/subClassOf ccp/IAO_EXT_0001055)
(ccp/IAO_EXT_0001156 rdfs/label ["UniProt interactant record - identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001157
(ccp/IAO_EXT_0001157 rdf/type owl/Class)
(ccp/IAO_EXT_0001157 rdfs/subClassOf ccp/IAO_EXT_0001055)
(ccp/IAO_EXT_0001157 rdfs/label ["UniProt interactant record - label field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001158
(ccp/IAO_EXT_0001158 rdf/type owl/Class)
(ccp/IAO_EXT_0001158 rdfs/subClassOf ccp/IAO_EXT_0001055)
(ccp/IAO_EXT_0001158 rdfs/label ["UniProt interactant record - intact identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001159
(ccp/IAO_EXT_0001159 rdf/type owl/Class)
(ccp/IAO_EXT_0001159 rdfs/subClassOf ccp/IAO_EXT_0001056)
(ccp/IAO_EXT_0001159 rdfs/label ["UniProt isoform record - identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001160
(ccp/IAO_EXT_0001160 rdf/type owl/Class)
(ccp/IAO_EXT_0001160 rdfs/subClassOf ccp/IAO_EXT_0001056)
(ccp/IAO_EXT_0001160 rdfs/label ["UniProt isoform record - name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001161
(ccp/IAO_EXT_0001161 rdf/type owl/Class)
(ccp/IAO_EXT_0001161 rdfs/subClassOf ccp/IAO_EXT_0001056)
(ccp/IAO_EXT_0001161 rdfs/label ["UniProt isoform record - sequence field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001162
(ccp/IAO_EXT_0001162 rdf/type owl/Class)
(ccp/IAO_EXT_0001162 rdfs/subClassOf ccp/IAO_EXT_0001056)
(ccp/IAO_EXT_0001162 rdfs/label ["UniProt isoform record - note field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001163
(ccp/IAO_EXT_0001163 rdf/type owl/Class)
(ccp/IAO_EXT_0001163 rdfs/subClassOf ccp/IAO_EXT_0001057)
(ccp/IAO_EXT_0001163 rdfs/label ["UniProt note record - value field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001164
(ccp/IAO_EXT_0001164 rdf/type owl/Class)
(ccp/IAO_EXT_0001164 rdfs/subClassOf ccp/IAO_EXT_0001057)
(ccp/IAO_EXT_0001164 rdfs/label ["UniProt note record - evidence field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001165
(ccp/IAO_EXT_0001165 rdf/type owl/Class)
(ccp/IAO_EXT_0001165 rdfs/subClassOf ccp/IAO_EXT_0001058)
(ccp/IAO_EXT_0001165 rdfs/label ["UniProt keyword record - value field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001166
(ccp/IAO_EXT_0001166 rdf/type owl/Class)
(ccp/IAO_EXT_0001166 rdfs/subClassOf ccp/IAO_EXT_0001058)
(ccp/IAO_EXT_0001166 rdfs/label ["UniProt keyword record - evidence field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001167
(ccp/IAO_EXT_0001167 rdf/type owl/Class)
(ccp/IAO_EXT_0001167 rdfs/subClassOf ccp/IAO_EXT_0001058)
(ccp/IAO_EXT_0001167 rdfs/label ["UniProt keyword record - identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001168
(ccp/IAO_EXT_0001168 rdf/type owl/Class)
(ccp/IAO_EXT_0001168 rdfs/subClassOf ccp/IAO_EXT_0001059)
(ccp/IAO_EXT_0001168 rdfs/label ["UniProt location record - begin field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001169
(ccp/IAO_EXT_0001169 rdf/type owl/Class)
(ccp/IAO_EXT_0001169 rdfs/subClassOf ccp/IAO_EXT_0001059)
(ccp/IAO_EXT_0001169 rdfs/label ["UniProt location record - end field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001170
(ccp/IAO_EXT_0001170 rdf/type owl/Class)
(ccp/IAO_EXT_0001170 rdfs/subClassOf ccp/IAO_EXT_0001059)
(ccp/IAO_EXT_0001170 rdfs/label ["UniProt location record - position field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001171
(ccp/IAO_EXT_0001171 rdf/type owl/Class)
(ccp/IAO_EXT_0001171 rdfs/subClassOf ccp/IAO_EXT_0001059)
(ccp/IAO_EXT_0001171 rdfs/label ["UniProt location record - sequence field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001172
(ccp/IAO_EXT_0001172 rdf/type owl/Class)
(ccp/IAO_EXT_0001172 rdfs/subClassOf ccp/IAO_EXT_0001060)
(ccp/IAO_EXT_0001172 rdfs/label ["UniProt organism name record - value field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001173
(ccp/IAO_EXT_0001173 rdf/type owl/Class)
(ccp/IAO_EXT_0001173 rdfs/subClassOf ccp/IAO_EXT_0001060)
(ccp/IAO_EXT_0001173 rdfs/label ["UniProt organism name record - type field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001174
(ccp/IAO_EXT_0001174 rdf/type owl/Class)
(ccp/IAO_EXT_0001174 rdfs/subClassOf ccp/IAO_EXT_0001061)
(ccp/IAO_EXT_0001174 rdfs/label ["UniProt organism record - name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001175
(ccp/IAO_EXT_0001175 rdf/type owl/Class)
(ccp/IAO_EXT_0001175 rdfs/subClassOf ccp/IAO_EXT_0001061)
(ccp/IAO_EXT_0001175 rdfs/label ["UniProt organism record - database reference field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001176
(ccp/IAO_EXT_0001176 rdf/type owl/Class)
(ccp/IAO_EXT_0001176 rdfs/subClassOf ccp/IAO_EXT_0001061)
(ccp/IAO_EXT_0001176 rdfs/label ["UniProt organism record - lineage field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001177
(ccp/IAO_EXT_0001177 rdf/type owl/Class)
(ccp/IAO_EXT_0001177 rdfs/subClassOf ccp/IAO_EXT_0001061)
(ccp/IAO_EXT_0001177 rdfs/label ["UniProt organism record - evidence field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001178
(ccp/IAO_EXT_0001178 rdf/type owl/Class)
(ccp/IAO_EXT_0001178 rdfs/subClassOf ccp/IAO_EXT_0001062)
(ccp/IAO_EXT_0001178 rdfs/label ["UniProt lineage record - taxon field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001179
(ccp/IAO_EXT_0001179 rdf/type owl/Class)
(ccp/IAO_EXT_0001179 rdfs/subClassOf ccp/IAO_EXT_0001063)
(ccp/IAO_EXT_0001179 rdfs/label ["UniProt position record - position field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001180
(ccp/IAO_EXT_0001180 rdf/type owl/Class)
(ccp/IAO_EXT_0001180 rdfs/subClassOf ccp/IAO_EXT_0001063)
(ccp/IAO_EXT_0001180 rdfs/label ["UniProt position record - status field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001181
(ccp/IAO_EXT_0001181 rdf/type owl/Class)
(ccp/IAO_EXT_0001181 rdfs/subClassOf ccp/IAO_EXT_0001063)
(ccp/IAO_EXT_0001181 rdfs/label ["UniProt position record - evidence field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001182
(ccp/IAO_EXT_0001182 rdf/type owl/Class)
(ccp/IAO_EXT_0001182 rdfs/subClassOf ccp/IAO_EXT_0001064)
(ccp/IAO_EXT_0001182 rdfs/label ["UniProt property record - type field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001183
(ccp/IAO_EXT_0001183 rdf/type owl/Class)
(ccp/IAO_EXT_0001183 rdfs/subClassOf ccp/IAO_EXT_0001064)
(ccp/IAO_EXT_0001183 rdfs/label ["UniProt property record - value field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001184
(ccp/IAO_EXT_0001184 rdf/type owl/Class)
(ccp/IAO_EXT_0001184 rdfs/subClassOf ccp/IAO_EXT_0001065)
(ccp/IAO_EXT_0001184 rdfs/label ["UniProt protein existence record - type field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001185
(ccp/IAO_EXT_0001185 rdf/type owl/Class)
(ccp/IAO_EXT_0001185 rdfs/subClassOf ccp/IAO_EXT_0001066)
(ccp/IAO_EXT_0001185 rdfs/label ["UniProt protein record - recommended name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001186
(ccp/IAO_EXT_0001186 rdf/type owl/Class)
(ccp/IAO_EXT_0001186 rdfs/subClassOf ccp/IAO_EXT_0001066)
(ccp/IAO_EXT_0001186 rdfs/label ["UniProt protein record - alternative name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001187
(ccp/IAO_EXT_0001187 rdf/type owl/Class)
(ccp/IAO_EXT_0001187 rdfs/subClassOf ccp/IAO_EXT_0001066)
(ccp/IAO_EXT_0001187 rdfs/label ["UniProt protein record - submitted name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001188
(ccp/IAO_EXT_0001188 rdf/type owl/Class)
(ccp/IAO_EXT_0001188 rdfs/subClassOf ccp/IAO_EXT_0001066)
(ccp/IAO_EXT_0001188 rdfs/label ["UniProt protein record - allergen name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001189
(ccp/IAO_EXT_0001189 rdf/type owl/Class)
(ccp/IAO_EXT_0001189 rdfs/subClassOf ccp/IAO_EXT_0001066)
(ccp/IAO_EXT_0001189 rdfs/label ["UniProt protein record - biotech name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001190
(ccp/IAO_EXT_0001190 rdf/type owl/Class)
(ccp/IAO_EXT_0001190 rdfs/subClassOf ccp/IAO_EXT_0001066)
(ccp/IAO_EXT_0001190 rdfs/label ["UniProt protein record - CD antigen name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001191
(ccp/IAO_EXT_0001191 rdf/type owl/Class)
(ccp/IAO_EXT_0001191 rdfs/subClassOf ccp/IAO_EXT_0001066)
(ccp/IAO_EXT_0001191 rdfs/label ["UniProt protein record - INN name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001192
(ccp/IAO_EXT_0001192 rdf/type owl/Class)
(ccp/IAO_EXT_0001192 rdfs/subClassOf ccp/IAO_EXT_0001066)
(ccp/IAO_EXT_0001192 rdfs/label ["UniProt protein record - domain field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001193
(ccp/IAO_EXT_0001193 rdf/type owl/Class)
(ccp/IAO_EXT_0001193 rdfs/subClassOf ccp/IAO_EXT_0001066)
(ccp/IAO_EXT_0001193 rdfs/label ["UniProt protein record - component field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001194
(ccp/IAO_EXT_0001194 rdf/type owl/Class)
(ccp/IAO_EXT_0001194 rdfs/subClassOf ccp/IAO_EXT_0001067)
(ccp/IAO_EXT_0001194 rdfs/label ["UniProt alternative name record - full name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001195
(ccp/IAO_EXT_0001195 rdf/type owl/Class)
(ccp/IAO_EXT_0001195 rdfs/subClassOf ccp/IAO_EXT_0001067)
(ccp/IAO_EXT_0001195 rdfs/label ["UniProt alternative name record - short name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001196
(ccp/IAO_EXT_0001196 rdf/type owl/Class)
(ccp/IAO_EXT_0001196 rdfs/subClassOf ccp/IAO_EXT_0001067)
(ccp/IAO_EXT_0001196 rdfs/label ["UniProt alternative name record - EC number field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001197
(ccp/IAO_EXT_0001197 rdf/type owl/Class)
(ccp/IAO_EXT_0001197 rdfs/subClassOf ccp/IAO_EXT_0001068)
(ccp/IAO_EXT_0001197 rdfs/label ["UniProt component record - recommended name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001198
(ccp/IAO_EXT_0001198 rdf/type owl/Class)
(ccp/IAO_EXT_0001198 rdfs/subClassOf ccp/IAO_EXT_0001068)
(ccp/IAO_EXT_0001198 rdfs/label ["UniProt component record - alternative name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001199
(ccp/IAO_EXT_0001199 rdf/type owl/Class)
(ccp/IAO_EXT_0001199 rdfs/subClassOf ccp/IAO_EXT_0001068)
(ccp/IAO_EXT_0001199 rdfs/label ["UniProt component record -submitted name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001200
(ccp/IAO_EXT_0001200 rdf/type owl/Class)
(ccp/IAO_EXT_0001200 rdfs/subClassOf ccp/IAO_EXT_0001068)
(ccp/IAO_EXT_0001200 rdfs/label ["UniProt component record - allergen name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001201
(ccp/IAO_EXT_0001201 rdf/type owl/Class)
(ccp/IAO_EXT_0001201 rdfs/subClassOf ccp/IAO_EXT_0001068)
(ccp/IAO_EXT_0001201 rdfs/label ["UniProt component record - biotech name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001202
(ccp/IAO_EXT_0001202 rdf/type owl/Class)
(ccp/IAO_EXT_0001202 rdfs/subClassOf ccp/IAO_EXT_0001068)
(ccp/IAO_EXT_0001202 rdfs/label ["UniProt component record - CD antigen name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001203
(ccp/IAO_EXT_0001203 rdf/type owl/Class)
(ccp/IAO_EXT_0001203 rdfs/subClassOf ccp/IAO_EXT_0001068)
(ccp/IAO_EXT_0001203 rdfs/label ["UniProt component record - INN name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001204
(ccp/IAO_EXT_0001204 rdf/type owl/Class)
(ccp/IAO_EXT_0001204 rdfs/subClassOf ccp/IAO_EXT_0001069)
(ccp/IAO_EXT_0001204 rdfs/label ["UniProt domain record - recommended name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001205
(ccp/IAO_EXT_0001205 rdf/type owl/Class)
(ccp/IAO_EXT_0001205 rdfs/subClassOf ccp/IAO_EXT_0001069)
(ccp/IAO_EXT_0001205 rdfs/label ["UniProt domain record - submitted name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001206
(ccp/IAO_EXT_0001206 rdf/type owl/Class)
(ccp/IAO_EXT_0001206 rdfs/subClassOf ccp/IAO_EXT_0001069)
(ccp/IAO_EXT_0001206 rdfs/label ["UniProt domain record - allergen name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001207
(ccp/IAO_EXT_0001207 rdf/type owl/Class)
(ccp/IAO_EXT_0001207 rdfs/subClassOf ccp/IAO_EXT_0001069)
(ccp/IAO_EXT_0001207 rdfs/label ["UniProt domain record - biotech name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001208
(ccp/IAO_EXT_0001208 rdf/type owl/Class)
(ccp/IAO_EXT_0001208 rdfs/subClassOf ccp/IAO_EXT_0001069)
(ccp/IAO_EXT_0001208 rdfs/label ["UniProt domain record - CD antigen name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001209
(ccp/IAO_EXT_0001209 rdf/type owl/Class)
(ccp/IAO_EXT_0001209 rdfs/subClassOf ccp/IAO_EXT_0001069)
(ccp/IAO_EXT_0001209 rdfs/label ["UniProt domain record - INN name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001210
(ccp/IAO_EXT_0001210 rdf/type owl/Class)
(ccp/IAO_EXT_0001210 rdfs/subClassOf ccp/IAO_EXT_0001070)
(ccp/IAO_EXT_0001210 rdfs/label ["UniProt recommended name record - full name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001211
(ccp/IAO_EXT_0001211 rdf/type owl/Class)
(ccp/IAO_EXT_0001211 rdfs/subClassOf ccp/IAO_EXT_0001070)
(ccp/IAO_EXT_0001211 rdfs/label ["UniProt recommended name record - short name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001212
(ccp/IAO_EXT_0001212 rdf/type owl/Class)
(ccp/IAO_EXT_0001212 rdfs/subClassOf ccp/IAO_EXT_0001070)
(ccp/IAO_EXT_0001212 rdfs/label ["UniProt recommended name record - EC number field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001213
(ccp/IAO_EXT_0001213 rdf/type owl/Class)
(ccp/IAO_EXT_0001213 rdfs/subClassOf ccp/IAO_EXT_0001071)
(ccp/IAO_EXT_0001213 rdfs/label ["UniProt submitted name record - full name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001214
(ccp/IAO_EXT_0001214 rdf/type owl/Class)
(ccp/IAO_EXT_0001214 rdfs/subClassOf ccp/IAO_EXT_0001071)
(ccp/IAO_EXT_0001214 rdfs/label ["UniProt submitted name record - EC number field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001215
(ccp/IAO_EXT_0001215 rdf/type owl/Class)
(ccp/IAO_EXT_0001215 rdfs/subClassOf ccp/IAO_EXT_0001072)
(ccp/IAO_EXT_0001215 rdfs/label ["UniProt reference record - citation field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001216
(ccp/IAO_EXT_0001216 rdf/type owl/Class)
(ccp/IAO_EXT_0001216 rdfs/subClassOf ccp/IAO_EXT_0001072)
(ccp/IAO_EXT_0001216 rdfs/label ["UniProt reference record - scope field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001217
(ccp/IAO_EXT_0001217 rdf/type owl/Class)
(ccp/IAO_EXT_0001217 rdfs/subClassOf ccp/IAO_EXT_0001072)
(ccp/IAO_EXT_0001217 rdfs/label ["UniProt reference record - source field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001218
(ccp/IAO_EXT_0001218 rdf/type owl/Class)
(ccp/IAO_EXT_0001218 rdfs/subClassOf ccp/IAO_EXT_0001072)
(ccp/IAO_EXT_0001218 rdfs/label ["UniProt reference record - evidence field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001219
(ccp/IAO_EXT_0001219 rdf/type owl/Class)
(ccp/IAO_EXT_0001219 rdfs/subClassOf ccp/IAO_EXT_0001072)
(ccp/IAO_EXT_0001219 rdfs/label ["UniProt reference record - key field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001220
(ccp/IAO_EXT_0001220 rdf/type owl/Class)
(ccp/IAO_EXT_0001220 rdfs/subClassOf ccp/IAO_EXT_0001073)
(ccp/IAO_EXT_0001220 rdfs/label ["UniProt source data record - type field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001221
(ccp/IAO_EXT_0001221 rdf/type owl/Class)
(ccp/IAO_EXT_0001221 rdfs/subClassOf ccp/IAO_EXT_0001073)
(ccp/IAO_EXT_0001221 rdfs/label ["UniProt source data record - value field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001222
(ccp/IAO_EXT_0001222 rdf/type owl/Class)
(ccp/IAO_EXT_0001222 rdfs/subClassOf ccp/IAO_EXT_0001073)
(ccp/IAO_EXT_0001222 rdfs/label ["UniProt source data record - evidence field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001223
(ccp/IAO_EXT_0001223 rdf/type owl/Class)
(ccp/IAO_EXT_0001223 rdfs/subClassOf ccp/IAO_EXT_0001074)
(ccp/IAO_EXT_0001223 rdfs/label ["UniProt source record - database reference field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001224
(ccp/IAO_EXT_0001224 rdf/type owl/Class)
(ccp/IAO_EXT_0001224 rdfs/subClassOf ccp/IAO_EXT_0001074)
(ccp/IAO_EXT_0001224 rdfs/label ["UniProt source record - reference field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001225
(ccp/IAO_EXT_0001225 rdf/type owl/Class)
(ccp/IAO_EXT_0001225 rdfs/subClassOf ccp/IAO_EXT_0001075)
(ccp/IAO_EXT_0001225 rdfs/label ["UniProt status record - value field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001226
(ccp/IAO_EXT_0001226 rdf/type owl/Class)
(ccp/IAO_EXT_0001226 rdfs/subClassOf ccp/IAO_EXT_0001075)
(ccp/IAO_EXT_0001226 rdfs/label ["UniProt status record - status field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001227
(ccp/IAO_EXT_0001227 rdf/type owl/Class)
(ccp/IAO_EXT_0001227 rdfs/subClassOf ccp/IAO_EXT_0001076)
(ccp/IAO_EXT_0001227 rdfs/label ["UniProt subcellular location record - location field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001228
(ccp/IAO_EXT_0001228 rdf/type owl/Class)
(ccp/IAO_EXT_0001228 rdfs/subClassOf ccp/IAO_EXT_0001076)
(ccp/IAO_EXT_0001228 rdfs/label ["UniProt subcellular location record - topology field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001229
(ccp/IAO_EXT_0001229 rdf/type owl/Class)
(ccp/IAO_EXT_0001229 rdfs/subClassOf ccp/IAO_EXT_0001076)
(ccp/IAO_EXT_0001229 rdfs/label ["UniProt subcellular location record - orientation field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001230
(ccp/IAO_EXT_0001230 rdf/type owl/Class)
(ccp/IAO_EXT_0001230 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0001230 rdfs/label ["UniProt isoform name record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001231
(ccp/IAO_EXT_0001231 rdf/type owl/Class)
(ccp/IAO_EXT_0001231 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0001231 rdfs/label ["UniProt name record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001232
(ccp/IAO_EXT_0001232 rdf/type owl/Class)
(ccp/IAO_EXT_0001232 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0001232 rdfs/label ["UniProt conflict record sequence record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001233
(ccp/IAO_EXT_0001233 rdf/type owl/Class)
(ccp/IAO_EXT_0001233 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0001233 rdfs/label ["UniProt isoform sequence record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001234
(ccp/IAO_EXT_0001234 rdf/type owl/Class)
(ccp/IAO_EXT_0001234 rdfs/subClassOf ccp/IAO_EXT_0000236)
(ccp/IAO_EXT_0001234 rdfs/label ["UniProt sequence record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001235
(ccp/IAO_EXT_0001235 rdf/type owl/Class)
(ccp/IAO_EXT_0001235 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0001235 rdfs/label ["UniProt isoform sequence record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001236
(ccp/IAO_EXT_0001236 rdf/type owl/Class)
(ccp/IAO_EXT_0001236 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0001236 rdfs/label ["UniProt name record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001237
(ccp/IAO_EXT_0001237 rdf/type owl/Class)
(ccp/IAO_EXT_0001237 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0001237 rdfs/label ["UniProt sequence record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001238
(ccp/IAO_EXT_0001238 rdf/type owl/Class)
(ccp/IAO_EXT_0001238 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0001238 rdfs/label ["UniProt conflict record sequence record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001239
(ccp/IAO_EXT_0001239 rdf/type owl/Class)
(ccp/IAO_EXT_0001239 rdfs/subClassOf ccp/IAO_EXT_0000216)
(ccp/IAO_EXT_0001239 rdfs/label ["UniProt isoform name record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001240
(ccp/IAO_EXT_0001240 rdf/type owl/Class)
(ccp/IAO_EXT_0001240 rdfs/subClassOf ccp/IAO_EXT_0001237)
(ccp/IAO_EXT_0001240 rdfs/label ["UniProt sequence record - value field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001241
(ccp/IAO_EXT_0001241 rdf/type owl/Class)
(ccp/IAO_EXT_0001241 rdfs/subClassOf ccp/IAO_EXT_0001237)
(ccp/IAO_EXT_0001241 rdfs/label ["UniProt sequence record - length field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001242
(ccp/IAO_EXT_0001242 rdf/type owl/Class)
(ccp/IAO_EXT_0001242 rdfs/subClassOf ccp/IAO_EXT_0001237)
(ccp/IAO_EXT_0001242 rdfs/label ["UniProt sequence record - mass field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001243
(ccp/IAO_EXT_0001243 rdf/type owl/Class)
(ccp/IAO_EXT_0001243 rdfs/subClassOf ccp/IAO_EXT_0001237)
(ccp/IAO_EXT_0001243 rdfs/label ["UniProt sequence record - check sum field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001244
(ccp/IAO_EXT_0001244 rdf/type owl/Class)
(ccp/IAO_EXT_0001244 rdfs/subClassOf ccp/IAO_EXT_0001237)
(ccp/IAO_EXT_0001244 rdfs/label ["UniProt sequence record - modified field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001245
(ccp/IAO_EXT_0001245 rdf/type owl/Class)
(ccp/IAO_EXT_0001245 rdfs/subClassOf ccp/IAO_EXT_0001237)
(ccp/IAO_EXT_0001245 rdfs/label ["UniProt sequence record - version field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001246
(ccp/IAO_EXT_0001246 rdf/type owl/Class)
(ccp/IAO_EXT_0001246 rdfs/subClassOf ccp/IAO_EXT_0001237)
(ccp/IAO_EXT_0001246 rdfs/label ["UniProt sequence record - precursor field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001247
(ccp/IAO_EXT_0001247 rdf/type owl/Class)
(ccp/IAO_EXT_0001247 rdfs/subClassOf ccp/IAO_EXT_0001237)
(ccp/IAO_EXT_0001247 rdfs/label ["UniProt sequence record - fragment field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001248
(ccp/IAO_EXT_0001248 rdf/type owl/Class)
(ccp/IAO_EXT_0001248 rdfs/subClassOf ccp/IAO_EXT_0001236)
(ccp/IAO_EXT_0001248 rdfs/label ["UniProt name record - consortium or person field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001249
(ccp/IAO_EXT_0001249 rdf/type owl/Class)
(ccp/IAO_EXT_0001249 rdfs/subClassOf ccp/IAO_EXT_0001236)
(ccp/IAO_EXT_0001249 rdfs/label ["UniProt name record - name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001250
(ccp/IAO_EXT_0001250 rdf/type owl/Class)
(ccp/IAO_EXT_0001250 rdfs/subClassOf ccp/IAO_EXT_0001235)
(ccp/IAO_EXT_0001250 rdfs/label ["UniProt isoform sequence record - type field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001251
(ccp/IAO_EXT_0001251 rdf/type owl/Class)
(ccp/IAO_EXT_0001251 rdfs/subClassOf ccp/IAO_EXT_0001235)
(ccp/IAO_EXT_0001251 rdfs/label ["UniProt isoform sequence record - reference field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001252
(ccp/IAO_EXT_0001252 rdf/type owl/Class)
(ccp/IAO_EXT_0001252 rdfs/subClassOf ccp/IAO_EXT_0001239)
(ccp/IAO_EXT_0001252 rdfs/label ["UniProt isoform name record - value field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001253
(ccp/IAO_EXT_0001253 rdf/type owl/Class)
(ccp/IAO_EXT_0001253 rdfs/subClassOf ccp/IAO_EXT_0001239)
(ccp/IAO_EXT_0001253 rdfs/label ["UniProt isoform name record - evidence field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001254
(ccp/IAO_EXT_0001254 rdf/type owl/Class)
(ccp/IAO_EXT_0001254 rdfs/subClassOf ccp/IAO_EXT_0001238)
(ccp/IAO_EXT_0001254 rdfs/label ["UniProt conflict record sequence record - resource field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001255
(ccp/IAO_EXT_0001255 rdf/type owl/Class)
(ccp/IAO_EXT_0001255 rdfs/subClassOf ccp/IAO_EXT_0001238)
(ccp/IAO_EXT_0001255 rdfs/label ["UniProt conflict record sequence record - identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001256
(ccp/IAO_EXT_0001256 rdf/type owl/Class)
(ccp/IAO_EXT_0001256 rdfs/subClassOf ccp/IAO_EXT_0001238)
(ccp/IAO_EXT_0001256 rdfs/label ["UniProt conflict record sequence record - version field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001257
(ccp/IAO_EXT_0001257 rdf/type owl/Class)
(ccp/IAO_EXT_0001257 rdfs/subClassOf ccp/IAO_EXT_0001000)
(ccp/IAO_EXT_0001257 rdfs/label ["UniProt conflict record - sequence field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001258
(ccp/IAO_EXT_0001258 rdf/type owl/Class)
(ccp/IAO_EXT_0001258 rdfs/subClassOf ccp/IAO_EXT_0001069)
(ccp/IAO_EXT_0001258 rdfs/label ["UniProt domain record - alternative name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001259
(ccp/IAO_EXT_0001259 rdf/type owl/Class)
(ccp/IAO_EXT_0001259 rdfs/subClassOf ccp/IAO_EXT_0000215)
(ccp/IAO_EXT_0001259 rdfs/label ["Drugbank packager record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001260
(ccp/IAO_EXT_0001260 rdf/type owl/Class)
(ccp/IAO_EXT_0001260 rdfs/subClassOf ccp/IAO_EXT_0001259)
(ccp/IAO_EXT_0001260 rdfs/label ["Drugbank packager record - name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001261
(ccp/IAO_EXT_0001261 rdf/type owl/Class)
(ccp/IAO_EXT_0001261 rdfs/subClassOf ccp/IAO_EXT_0001259)
(ccp/IAO_EXT_0001261 rdfs/label ["Drugbank packager record - URL field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001262
(ccp/IAO_EXT_0001262 rdf/type owl/Class)
(ccp/IAO_EXT_0001262 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001262 ccp/IAO_EXT_0001700 ["MI:\\d+"])
(ccp/IAO_EXT_0001262 obo/IAO_0000599 ["MI_"])
(ccp/IAO_EXT_0001262 rdfs/label ["Molecular Interaction Ontology concept identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001263
(ccp/IAO_EXT_0001263 rdf/type owl/Class)
(ccp/IAO_EXT_0001263 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001263 ccp/IAO_EXT_0001700 ["^\\d+$"])
(ccp/IAO_EXT_0001263 obo/IAO_0000599 ["AFCS_"])
(ccp/IAO_EXT_0001263 rdfs/label ["Afcs identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001264
(ccp/IAO_EXT_0001264 rdf/type owl/Class)
(ccp/IAO_EXT_0001264 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001264 obo/IAO_0000599 ["AGRICOLA_"])
(ccp/IAO_EXT_0001264 rdfs/label ["Agricola identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001265
(ccp/IAO_EXT_0001265 rdf/type owl/Class)
(ccp/IAO_EXT_0001265 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001265 obo/IAO_0000599 ["AHFSCODE_"])
(ccp/IAO_EXT_0001265 rdfs/label ["AhfsCode identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001266
(ccp/IAO_EXT_0001266 rdf/type owl/Class)
(ccp/IAO_EXT_0001266 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001266 obo/IAO_0000599 ["ALFRED_"])
(ccp/IAO_EXT_0001266 rdfs/label ["Alfred identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001267
(ccp/IAO_EXT_0001267 rdf/type owl/Class)
(ccp/IAO_EXT_0001267 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001267 obo/IAO_0000599 ["ALLERGOME_"])
(ccp/IAO_EXT_0001267 rdfs/label ["Allergome identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001268
(ccp/IAO_EXT_0001268 rdf/type owl/Class)
(ccp/IAO_EXT_0001268 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001268 obo/IAO_0000599 ["ANIMALQTLDB_"])
(ccp/IAO_EXT_0001268 rdfs/label ["AnimalQtlDb identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001269
(ccp/IAO_EXT_0001269 rdf/type owl/Class)
(ccp/IAO_EXT_0001269 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001269 obo/IAO_0000599 ["APHIDBASE_"])
(ccp/IAO_EXT_0001269 rdfs/label ["AphidBase identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001270
(ccp/IAO_EXT_0001270 rdf/type owl/Class)
(ccp/IAO_EXT_0001270 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001270 obo/IAO_0000599 ["APIDBCRYPTODB_"])
(ccp/IAO_EXT_0001270 rdfs/label ["ApiDbCryptoDb identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001271
(ccp/IAO_EXT_0001271 rdf/type owl/Class)
(ccp/IAO_EXT_0001271 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001271 obo/IAO_0000599 ["ARACHNOSERVER_"])
(ccp/IAO_EXT_0001271 rdfs/label ["ArachnoServer identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001272
(ccp/IAO_EXT_0001272 rdf/type owl/Class)
(ccp/IAO_EXT_0001272 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001272 obo/IAO_0000599 ["ARRAYEXPRESS_"])
(ccp/IAO_EXT_0001272 rdfs/label ["ArrayExpress identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001273
(ccp/IAO_EXT_0001273 rdf/type owl/Class)
(ccp/IAO_EXT_0001273 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001273 obo/IAO_0000599 ["ASRP_"])
(ccp/IAO_EXT_0001273 rdfs/label ["Asrp identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001274
(ccp/IAO_EXT_0001274 rdf/type owl/Class)
(ccp/IAO_EXT_0001274 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001274 obo/IAO_0000599 ["ATCCODE_"])
(ccp/IAO_EXT_0001274 rdfs/label ["AtcCode identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001275
(ccp/IAO_EXT_0001275 rdf/type owl/Class)
(ccp/IAO_EXT_0001275 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001275 obo/IAO_0000599 ["BEEBASE_"])
(ccp/IAO_EXT_0001275 rdfs/label ["BeeBase identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001276
(ccp/IAO_EXT_0001276 rdf/type owl/Class)
(ccp/IAO_EXT_0001276 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001276 obo/IAO_0000599 ["BEETLEBASE_"])
(ccp/IAO_EXT_0001276 rdfs/label ["BeetleBase identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001277
(ccp/IAO_EXT_0001277 rdf/type owl/Class)
(ccp/IAO_EXT_0001277 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001277 obo/IAO_0000599 ["BGEE_"])
(ccp/IAO_EXT_0001277 rdfs/label ["Bgee identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001278
(ccp/IAO_EXT_0001278 rdf/type owl/Class)
(ccp/IAO_EXT_0001278 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001278 obo/IAO_0000599 ["BINDINGDB_"])
(ccp/IAO_EXT_0001278 rdfs/label ["BindingDb identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001279
(ccp/IAO_EXT_0001279 rdf/type owl/Class)
(ccp/IAO_EXT_0001279 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001279 ccp/IAO_EXT_0001700 ["^\\d+$"])
(ccp/IAO_EXT_0001279 obo/IAO_0000599 ["BINDINTERACTION_"])
(ccp/IAO_EXT_0001279 rdfs/label ["BindInteraction identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001280
(ccp/IAO_EXT_0001280 rdf/type owl/Class)
(ccp/IAO_EXT_0001280 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001280 ccp/IAO_EXT_0001700 ["^\\d+$"])
(ccp/IAO_EXT_0001280 obo/IAO_0000599 ["BINDTRANSLATION_"])
(ccp/IAO_EXT_0001280 rdfs/label ["BindTranslation identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001281
(ccp/IAO_EXT_0001281 rdf/type owl/Class)
(ccp/IAO_EXT_0001281 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001281 obo/IAO_0000599 ["BIOCYC_"])
(ccp/IAO_EXT_0001281 rdfs/label ["BioCyc identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001282
(ccp/IAO_EXT_0001282 rdf/type owl/Class)
(ccp/IAO_EXT_0001282 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001282 ccp/IAO_EXT_0001700 ["^\\d+$"])
(ccp/IAO_EXT_0001282 obo/IAO_0000599 ["BIOGRID_"])
(ccp/IAO_EXT_0001282 rdfs/label ["BioGrid identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001283
(ccp/IAO_EXT_0001283 rdf/type owl/Class)
(ccp/IAO_EXT_0001283 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001283 obo/IAO_0000599 ["CAMJEDB_"])
(ccp/IAO_EXT_0001283 rdfs/label ["CamjeDb identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001284
(ccp/IAO_EXT_0001284 rdf/type owl/Class)
(ccp/IAO_EXT_0001284 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001284 obo/IAO_0000599 ["CAZY_"])
(ccp/IAO_EXT_0001284 rdfs/label ["CAZy identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001285
(ccp/IAO_EXT_0001285 rdf/type owl/Class)
(ccp/IAO_EXT_0001285 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001285 obo/IAO_0000599 ["CCDS_"])
(ccp/IAO_EXT_0001285 rdfs/label ["Ccds identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001286
(ccp/IAO_EXT_0001286 rdf/type owl/Class)
(ccp/IAO_EXT_0001286 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001286 obo/IAO_0000599 ["CGD_"])
(ccp/IAO_EXT_0001286 rdfs/label ["CGD identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001287
(ccp/IAO_EXT_0001287 rdf/type owl/Class)
(ccp/IAO_EXT_0001287 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001287 obo/IAO_0000599 ["CHEMBL_"])
(ccp/IAO_EXT_0001287 rdfs/label ["Chembl identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001288
(ccp/IAO_EXT_0001288 rdf/type owl/Class)
(ccp/IAO_EXT_0001288 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001288 obo/IAO_0000599 ["CHEMICALABSTRACTSSERVICE_"])
(ccp/IAO_EXT_0001288 rdfs/label ["ChemicalAbstractsService identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001289
(ccp/IAO_EXT_0001289 rdf/type owl/Class)
(ccp/IAO_EXT_0001289 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001289 ccp/IAO_EXT_0001700 ["^\\d+$"])
(ccp/IAO_EXT_0001289 obo/IAO_0000599 ["CHEMSPIDER_"])
(ccp/IAO_EXT_0001289 rdfs/label ["ChemSpider identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001290
(ccp/IAO_EXT_0001290 rdf/type owl/Class)
(ccp/IAO_EXT_0001290 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001290 obo/IAO_0000599 ["CHITARS_"])
(ccp/IAO_EXT_0001290 rdfs/label ["ChiTaRS identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001291
(ccp/IAO_EXT_0001291 rdf/type owl/Class)
(ccp/IAO_EXT_0001291 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001291 obo/IAO_0000599 ["CLEANEX_"])
(ccp/IAO_EXT_0001291 rdfs/label ["CleanEx identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001292
(ccp/IAO_EXT_0001292 rdf/type owl/Class)
(ccp/IAO_EXT_0001292 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001292 obo/IAO_0000599 ["CLINICALTRIALSGOV_"])
(ccp/IAO_EXT_0001292 rdfs/label ["ClinicalTrialsGov identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001293
(ccp/IAO_EXT_0001293 rdf/type owl/Class)
(ccp/IAO_EXT_0001293 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001293 obo/IAO_0000599 ["COMPLUYEAST_2DPAGE_"])
(ccp/IAO_EXT_0001293 rdfs/label ["COMPLUYEAST_2DPAGE identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001294
(ccp/IAO_EXT_0001294 rdf/type owl/Class)
(ccp/IAO_EXT_0001294 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001294 obo/IAO_0000599 ["CONOSERVER_"])
(ccp/IAO_EXT_0001294 rdfs/label ["ConoServer identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001295
(ccp/IAO_EXT_0001295 rdf/type owl/Class)
(ccp/IAO_EXT_0001295 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001295 ccp/IAO_EXT_0001700 ["^\\d+$"])
(ccp/IAO_EXT_0001295 obo/IAO_0000599 ["CORUM_"])
(ccp/IAO_EXT_0001295 rdfs/label ["Corum identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001296
(ccp/IAO_EXT_0001296 rdf/type owl/Class)
(ccp/IAO_EXT_0001296 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001296 obo/IAO_0000599 ["COSMIC_"])
(ccp/IAO_EXT_0001296 rdfs/label ["Cosmic identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001297
(ccp/IAO_EXT_0001297 rdf/type owl/Class)
(ccp/IAO_EXT_0001297 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001297 obo/IAO_0000599 ["CTD_"])
(ccp/IAO_EXT_0001297 rdfs/label ["Ctd identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001298
(ccp/IAO_EXT_0001298 rdf/type owl/Class)
(ccp/IAO_EXT_0001298 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001298 obo/IAO_0000599 ["CYGD_"])
(ccp/IAO_EXT_0001298 rdfs/label ["Cygd identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001299
(ccp/IAO_EXT_0001299 rdf/type owl/Class)
(ccp/IAO_EXT_0001299 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001299 obo/IAO_0000599 ["DAILYMED_"])
(ccp/IAO_EXT_0001299 rdfs/label ["DailyMed identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001300
(ccp/IAO_EXT_0001300 rdf/type owl/Class)
(ccp/IAO_EXT_0001300 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001300 obo/IAO_0000599 ["DBJ_"])
(ccp/IAO_EXT_0001300 rdfs/label ["Dbj identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001301
(ccp/IAO_EXT_0001301 rdf/type owl/Class)
(ccp/IAO_EXT_0001301 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001301 obo/IAO_0000599 ["DBSNP_"])
(ccp/IAO_EXT_0001301 rdfs/label ["DbSnp identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001302
(ccp/IAO_EXT_0001302 rdf/type owl/Class)
(ccp/IAO_EXT_0001302 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001302 obo/IAO_0000599 ["DDBJ_"])
(ccp/IAO_EXT_0001302 rdfs/label ["Ddbj identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001303
(ccp/IAO_EXT_0001303 rdf/type owl/Class)
(ccp/IAO_EXT_0001303 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001303 ccp/IAO_EXT_0001700 ["DIP-\\d+E"])
(ccp/IAO_EXT_0001303 obo/IAO_0000599 ["DIP_"])
(ccp/IAO_EXT_0001303 rdfs/label ["DipInteraction identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001304
(ccp/IAO_EXT_0001304 rdf/type owl/Class)
(ccp/IAO_EXT_0001304 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001304 ccp/IAO_EXT_0001700 ["DIP-\\d+N"])
(ccp/IAO_EXT_0001304 obo/IAO_0000599 ["DIP_"])
(ccp/IAO_EXT_0001304 rdfs/label ["DipInteractor identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001305
(ccp/IAO_EXT_0001305 rdf/type owl/Class)
(ccp/IAO_EXT_0001305 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001305 obo/IAO_0000599 ["DISPROT_"])
(ccp/IAO_EXT_0001305 rdfs/label ["DisProt identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001306
(ccp/IAO_EXT_0001306 rdf/type owl/Class)
(ccp/IAO_EXT_0001306 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001306 obo/IAO_0000599 ["DMDM_"])
(ccp/IAO_EXT_0001306 rdfs/label ["Dmdm identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001307
(ccp/IAO_EXT_0001307 rdf/type owl/Class)
(ccp/IAO_EXT_0001307 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001307 obo/IAO_0000599 ["DNASU_"])
(ccp/IAO_EXT_0001307 rdfs/label ["Dnasu identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001308
(ccp/IAO_EXT_0001308 rdf/type owl/Class)
(ccp/IAO_EXT_0001308 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001308 obo/IAO_0000599 ["DOSAC_COBS_2DPAGE_"])
(ccp/IAO_EXT_0001308 rdfs/label ["DOSAC_COBS_2DPAGE identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001309
(ccp/IAO_EXT_0001309 rdf/type owl/Class)
(ccp/IAO_EXT_0001309 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001309 ccp/IAO_EXT_0001700 ["((DB)|(BIOD)|(BTD)|(APRD)|(NUTR)|(EXPT)|(BE)|(DBSALT)|(DBMET))\\d+"])
(ccp/IAO_EXT_0001309 obo/IAO_0000599 ["DRUGBANK_"])
(ccp/IAO_EXT_0001309 rdfs/label ["DrugBank identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001310
(ccp/IAO_EXT_0001310 rdf/type owl/Class)
(ccp/IAO_EXT_0001310 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001310 obo/IAO_0000599 ["DRUGCODEDIRECTORY_"])
(ccp/IAO_EXT_0001310 rdfs/label ["DrugCodeDirectory identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001311
(ccp/IAO_EXT_0001311 rdf/type owl/Class)
(ccp/IAO_EXT_0001311 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001311 ccp/IAO_EXT_0001700 ["^\\d+$"])
(ccp/IAO_EXT_0001311 obo/IAO_0000599 ["DRUGPRODUCTSDB_"])
(ccp/IAO_EXT_0001311 rdfs/label ["DrugProductsDb identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001313
(ccp/IAO_EXT_0001313 rdf/type owl/Class)
(ccp/IAO_EXT_0001313 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001313 obo/IAO_0000599 ["ECHOBASE_"])
(ccp/IAO_EXT_0001313 rdfs/label ["EchoBase identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001314
(ccp/IAO_EXT_0001314 rdf/type owl/Class)
(ccp/IAO_EXT_0001314 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001314 obo/IAO_0000599 ["ECOCYC_"])
(ccp/IAO_EXT_0001314 rdfs/label ["EcoCyc identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001315
(ccp/IAO_EXT_0001315 rdf/type owl/Class)
(ccp/IAO_EXT_0001315 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001315 obo/IAO_0000599 ["EGGNOG_"])
(ccp/IAO_EXT_0001315 rdfs/label ["EggNOG identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001316
(ccp/IAO_EXT_0001316 rdf/type owl/Class)
(ccp/IAO_EXT_0001316 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001316 obo/IAO_0000599 ["EMB_"])
(ccp/IAO_EXT_0001316 rdfs/label ["Emb identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001317
(ccp/IAO_EXT_0001317 rdf/type owl/Class)
(ccp/IAO_EXT_0001317 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001317 obo/IAO_0000599 ["ENSEMBLBACTERIA_"])
(ccp/IAO_EXT_0001317 rdfs/label ["EnsemblBacteria identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001318
(ccp/IAO_EXT_0001318 rdf/type owl/Class)
(ccp/IAO_EXT_0001318 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001318 obo/IAO_0000599 ["ENSEMBLFUNGI_"])
(ccp/IAO_EXT_0001318 rdfs/label ["EnsemblFungi identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001319
(ccp/IAO_EXT_0001319 rdf/type owl/Class)
(ccp/IAO_EXT_0001319 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001319 obo/IAO_0000599 ["ENSEMBLMETAZOA_"])
(ccp/IAO_EXT_0001319 rdfs/label ["EnsemblMetazoa identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001320
(ccp/IAO_EXT_0001320 rdf/type owl/Class)
(ccp/IAO_EXT_0001320 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001320 obo/IAO_0000599 ["ENSEMBLPLANTS_"])
(ccp/IAO_EXT_0001320 rdfs/label ["EnsemblPlants identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001321
(ccp/IAO_EXT_0001321 rdf/type owl/Class)
(ccp/IAO_EXT_0001321 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001321 obo/IAO_0000599 ["ENSEMBLPROTISTS_"])
(ccp/IAO_EXT_0001321 rdfs/label ["EnsemblProtists identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001322
(ccp/IAO_EXT_0001322 rdf/type owl/Class)
(ccp/IAO_EXT_0001322 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001322 obo/IAO_0000599 ["ENZYMECOMMISSION_"])
(ccp/IAO_EXT_0001322 rdfs/label ["EnzymeCommission identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001323
(ccp/IAO_EXT_0001323 rdf/type owl/Class)
(ccp/IAO_EXT_0001323 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001323 obo/IAO_0000599 ["ENZYME_"])
(ccp/IAO_EXT_0001323 rdfs/label ["ENZYME identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001324
(ccp/IAO_EXT_0001324 rdf/type owl/Class)
(ccp/IAO_EXT_0001324 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001324 obo/IAO_0000599 ["EUHCVDB_"])
(ccp/IAO_EXT_0001324 rdfs/label ["EuHCVdb identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001325
(ccp/IAO_EXT_0001325 rdf/type owl/Class)
(ccp/IAO_EXT_0001325 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001325 obo/IAO_0000599 ["EUPATHDB_"])
(ccp/IAO_EXT_0001325 rdfs/label ["EuPathDB identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001326
(ccp/IAO_EXT_0001326 rdf/type owl/Class)
(ccp/IAO_EXT_0001326 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001326 obo/IAO_0000599 ["EVOLUTIONARYTRACE_"])
(ccp/IAO_EXT_0001326 rdfs/label ["EvolutionaryTrace identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001327
(ccp/IAO_EXT_0001327 rdf/type owl/Class)
(ccp/IAO_EXT_0001327 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001327 obo/IAO_0000599 ["FISSIONYEASTPHENOTYPE_"])
(ccp/IAO_EXT_0001327 rdfs/label ["FissionYeastPhenotype identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001328
(ccp/IAO_EXT_0001328 rdf/type owl/Class)
(ccp/IAO_EXT_0001328 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001328 ccp/IAO_EXT_0001700 ["^\\d+$"])
(ccp/IAO_EXT_0001328 obo/IAO_0000599 ["GAD_"])
(ccp/IAO_EXT_0001328 rdfs/label ["Gad identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001329
(ccp/IAO_EXT_0001329 rdf/type owl/Class)
(ccp/IAO_EXT_0001329 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001329 obo/IAO_0000599 ["GDB_"])
(ccp/IAO_EXT_0001329 rdfs/label ["Gdb identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001330
(ccp/IAO_EXT_0001330 rdf/type owl/Class)
(ccp/IAO_EXT_0001330 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001330 obo/IAO_0000599 ["GENATLAS_"])
(ccp/IAO_EXT_0001330 rdfs/label ["GenAtlas identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001331
(ccp/IAO_EXT_0001331 rdf/type owl/Class)
(ccp/IAO_EXT_0001331 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001331 obo/IAO_0000599 ["GENBANK_"])
(ccp/IAO_EXT_0001331 rdfs/label ["GenBank identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001332
(ccp/IAO_EXT_0001332 rdf/type owl/Class)
(ccp/IAO_EXT_0001332 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001332 obo/IAO_0000599 ["GENE3D_"])
(ccp/IAO_EXT_0001332 rdfs/label ["Gene3d identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001333
(ccp/IAO_EXT_0001333 rdf/type owl/Class)
(ccp/IAO_EXT_0001333 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001333 obo/IAO_0000599 ["GENECARD_"])
(ccp/IAO_EXT_0001333 rdfs/label ["GeneCard identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001334
(ccp/IAO_EXT_0001334 rdf/type owl/Class)
(ccp/IAO_EXT_0001334 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001334 obo/IAO_0000599 ["GENEFARM_"])
(ccp/IAO_EXT_0001334 rdfs/label ["GeneFarm identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001335
(ccp/IAO_EXT_0001335 rdf/type owl/Class)
(ccp/IAO_EXT_0001335 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001335 obo/IAO_0000599 ["GENETREE_"])
(ccp/IAO_EXT_0001335 rdfs/label ["GeneTree identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001336
(ccp/IAO_EXT_0001336 rdf/type owl/Class)
(ccp/IAO_EXT_0001336 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001336 obo/IAO_0000599 ["GENEVESTIGATOR_"])
(ccp/IAO_EXT_0001336 rdfs/label ["Genevestigator identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001337
(ccp/IAO_EXT_0001337 rdf/type owl/Class)
(ccp/IAO_EXT_0001337 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001337 obo/IAO_0000599 ["GENOLIST_"])
(ccp/IAO_EXT_0001337 rdfs/label ["GenoList identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001338
(ccp/IAO_EXT_0001338 rdf/type owl/Class)
(ccp/IAO_EXT_0001338 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001338 obo/IAO_0000599 ["GENOMEREVIEWS_"])
(ccp/IAO_EXT_0001338 rdfs/label ["GenomeReviews identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001339
(ccp/IAO_EXT_0001339 rdf/type owl/Class)
(ccp/IAO_EXT_0001339 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001339 obo/IAO_0000599 ["GENOMERNAI_"])
(ccp/IAO_EXT_0001339 rdfs/label ["GenomeRNAi identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001340
(ccp/IAO_EXT_0001340 rdf/type owl/Class)
(ccp/IAO_EXT_0001340 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001340 obo/IAO_0000599 ["GEO_"])
(ccp/IAO_EXT_0001340 rdfs/label ["Geo identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001341
(ccp/IAO_EXT_0001341 rdf/type owl/Class)
(ccp/IAO_EXT_0001341 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001341 obo/IAO_0000599 ["GERMONLINE_"])
(ccp/IAO_EXT_0001341 rdfs/label ["GermOnline identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001342
(ccp/IAO_EXT_0001342 rdf/type owl/Class)
(ccp/IAO_EXT_0001342 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001342 obo/IAO_0000599 ["GLYCOSUITEDB_"])
(ccp/IAO_EXT_0001342 rdfs/label ["GlycoSuiteDB identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001343
(ccp/IAO_EXT_0001343 rdf/type owl/Class)
(ccp/IAO_EXT_0001343 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001343 obo/IAO_0000599 ["GPCRDB_"])
(ccp/IAO_EXT_0001343 rdfs/label ["GPCRDB identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001344
(ccp/IAO_EXT_0001344 rdf/type owl/Class)
(ccp/IAO_EXT_0001344 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001344 obo/IAO_0000599 ["GRAMENE_"])
(ccp/IAO_EXT_0001344 rdfs/label ["Gramene identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001345
(ccp/IAO_EXT_0001345 rdf/type owl/Class)
(ccp/IAO_EXT_0001345 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001345 ccp/IAO_EXT_0001700 ["^\\d+$"])
(ccp/IAO_EXT_0001345 obo/IAO_0000599 ["GUIDETOPHARMACOLOGY_"])
(ccp/IAO_EXT_0001345 rdfs/label ["GuideToPharmacology identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001346
(ccp/IAO_EXT_0001346 rdf/type owl/Class)
(ccp/IAO_EXT_0001346 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001346 obo/IAO_0000599 ["H_INVDB_"])
(ccp/IAO_EXT_0001346 rdfs/label ["H_InvDB identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001347
(ccp/IAO_EXT_0001347 rdf/type owl/Class)
(ccp/IAO_EXT_0001347 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001347 obo/IAO_0000599 ["HAMAPANNOTATIONRULE_"])
(ccp/IAO_EXT_0001347 rdfs/label ["HamapAnnotationRule identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001348
(ccp/IAO_EXT_0001348 rdf/type owl/Class)
(ccp/IAO_EXT_0001348 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001348 obo/IAO_0000599 ["HAMAP_"])
(ccp/IAO_EXT_0001348 rdfs/label ["HAMAP identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001349
(ccp/IAO_EXT_0001349 rdf/type owl/Class)
(ccp/IAO_EXT_0001349 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001349 obo/IAO_0000599 ["HAMAPRULE_"])
(ccp/IAO_EXT_0001349 rdfs/label ["HamapRule identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001350
(ccp/IAO_EXT_0001350 rdf/type owl/Class)
(ccp/IAO_EXT_0001350 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001350 obo/IAO_0000599 ["HCDM_"])
(ccp/IAO_EXT_0001350 rdfs/label ["Hcdm identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001351
(ccp/IAO_EXT_0001351 rdf/type owl/Class)
(ccp/IAO_EXT_0001351 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001351 obo/IAO_0000599 ["HINV_"])
(ccp/IAO_EXT_0001351 rdfs/label ["Hinv identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001352
(ccp/IAO_EXT_0001352 rdf/type owl/Class)
(ccp/IAO_EXT_0001352 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001352 obo/IAO_0000599 ["HOGENOM_"])
(ccp/IAO_EXT_0001352 rdfs/label ["HOGENOM identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001353
(ccp/IAO_EXT_0001353 rdf/type owl/Class)
(ccp/IAO_EXT_0001353 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001353 obo/IAO_0000599 ["HOMEODB_"])
(ccp/IAO_EXT_0001353 rdfs/label ["HomeoDb identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001354
(ccp/IAO_EXT_0001354 rdf/type owl/Class)
(ccp/IAO_EXT_0001354 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001354 ccp/IAO_EXT_0001700 ["^\\d+$"])
(ccp/IAO_EXT_0001354 obo/IAO_0000599 ["HOMOLOGENEGROUP_"])
(ccp/IAO_EXT_0001354 rdfs/label ["HomologeneGroup identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001355
(ccp/IAO_EXT_0001355 rdf/type owl/Class)
(ccp/IAO_EXT_0001355 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001355 obo/IAO_0000599 ["HORDE_"])
(ccp/IAO_EXT_0001355 rdfs/label ["Horde identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001356
(ccp/IAO_EXT_0001356 rdf/type owl/Class)
(ccp/IAO_EXT_0001356 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001356 obo/IAO_0000599 ["HOVERGEN_"])
(ccp/IAO_EXT_0001356 rdfs/label ["HOVERGEN identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001357
(ccp/IAO_EXT_0001357 rdf/type owl/Class)
(ccp/IAO_EXT_0001357 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001357 obo/IAO_0000599 ["HPA_"])
(ccp/IAO_EXT_0001357 rdfs/label ["HPA identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001358
(ccp/IAO_EXT_0001358 rdf/type owl/Class)
(ccp/IAO_EXT_0001358 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001358 obo/IAO_0000599 ["HPRD_"])
(ccp/IAO_EXT_0001358 rdfs/label ["Hprd identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001359
(ccp/IAO_EXT_0001359 rdf/type owl/Class)
(ccp/IAO_EXT_0001359 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001359 obo/IAO_0000599 ["HSSP_"])
(ccp/IAO_EXT_0001359 rdfs/label ["HSSP identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001360
(ccp/IAO_EXT_0001360 rdf/type owl/Class)
(ccp/IAO_EXT_0001360 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001360 obo/IAO_0000599 ["HUGE_"])
(ccp/IAO_EXT_0001360 rdfs/label ["Huge identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001361
(ccp/IAO_EXT_0001361 rdf/type owl/Class)
(ccp/IAO_EXT_0001361 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001361 obo/IAO_0000599 ["HUMANCYCGENE_"])
(ccp/IAO_EXT_0001361 rdfs/label ["HumanCycGene identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001362
(ccp/IAO_EXT_0001362 rdf/type owl/Class)
(ccp/IAO_EXT_0001362 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001362 obo/IAO_0000599 ["IMEX_"])
(ccp/IAO_EXT_0001362 rdfs/label ["Imex identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001363
(ccp/IAO_EXT_0001363 rdf/type owl/Class)
(ccp/IAO_EXT_0001363 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001363 obo/IAO_0000599 ["IMGT_"])
(ccp/IAO_EXT_0001363 rdfs/label ["Imgt identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001364
(ccp/IAO_EXT_0001364 rdf/type owl/Class)
(ccp/IAO_EXT_0001364 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001364 obo/IAO_0000599 ["INCRNADB_"])
(ccp/IAO_EXT_0001364 rdfs/label ["IncRnaDb identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001365
(ccp/IAO_EXT_0001365 rdf/type owl/Class)
(ccp/IAO_EXT_0001365 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001365 ccp/IAO_EXT_0001700 ["^\\d+$"])
(ccp/IAO_EXT_0001365 obo/IAO_0000599 ["INNATEDB_"])
(ccp/IAO_EXT_0001365 rdfs/label ["InnateDb identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001366
(ccp/IAO_EXT_0001366 rdf/type owl/Class)
(ccp/IAO_EXT_0001366 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001366 obo/IAO_0000599 ["INPARANOID_"])
(ccp/IAO_EXT_0001366 rdfs/label ["InParanoid identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001367
(ccp/IAO_EXT_0001367 rdf/type owl/Class)
(ccp/IAO_EXT_0001367 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001367 ccp/IAO_EXT_0001700 ["^\\d+$"])
(ccp/IAO_EXT_0001367 obo/IAO_0000599 ["INSDCPROJECT_"])
(ccp/IAO_EXT_0001367 rdfs/label ["InsdcProject identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001368
(ccp/IAO_EXT_0001368 rdf/type owl/Class)
(ccp/IAO_EXT_0001368 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001368 obo/IAO_0000599 ["INTACT_"])
(ccp/IAO_EXT_0001368 rdfs/label ["IntAct identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001369
(ccp/IAO_EXT_0001369 rdf/type owl/Class)
(ccp/IAO_EXT_0001369 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001369 obo/IAO_0000599 ["INTERFILDB_"])
(ccp/IAO_EXT_0001369 rdfs/label ["InterFilDb identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001370
(ccp/IAO_EXT_0001370 rdf/type owl/Class)
(ccp/IAO_EXT_0001370 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001370 ccp/IAO_EXT_0001700 ["IPR\\d+"])
(ccp/IAO_EXT_0001370 obo/IAO_0000599 ["INTERPRO_"])
(ccp/IAO_EXT_0001370 rdfs/label ["InterPro identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001371
(ccp/IAO_EXT_0001371 rdf/type owl/Class)
(ccp/IAO_EXT_0001371 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001371 obo/IAO_0000599 ["IPI_"])
(ccp/IAO_EXT_0001371 rdfs/label ["Ipi identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001372
(ccp/IAO_EXT_0001372 rdf/type owl/Class)
(ccp/IAO_EXT_0001372 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001372 obo/IAO_0000599 ["IREFWEBCRIG_"])
(ccp/IAO_EXT_0001372 rdfs/label ["IRefWebCrig identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001373
(ccp/IAO_EXT_0001373 rdf/type owl/Class)
(ccp/IAO_EXT_0001373 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001373 obo/IAO_0000599 ["IREFWEBCROG_"])
(ccp/IAO_EXT_0001373 rdfs/label ["IRefWebCrog identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001374
(ccp/IAO_EXT_0001374 rdf/type owl/Class)
(ccp/IAO_EXT_0001374 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001374 ccp/IAO_EXT_0001700 ["^\\d+$"])
(ccp/IAO_EXT_0001374 obo/IAO_0000599 ["IREFWEBICRIG_"])
(ccp/IAO_EXT_0001374 rdfs/label ["IRefWebIcrig identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001375
(ccp/IAO_EXT_0001375 rdf/type owl/Class)
(ccp/IAO_EXT_0001375 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001375 ccp/IAO_EXT_0001700 ["^\\d+$"])
(ccp/IAO_EXT_0001375 obo/IAO_0000599 ["IREFWEBICROG_"])
(ccp/IAO_EXT_0001375 rdfs/label ["IRefWebIcrog identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001376
(ccp/IAO_EXT_0001376 rdf/type owl/Class)
(ccp/IAO_EXT_0001376 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001376 ccp/IAO_EXT_0001700 ["^\\d+$"])
(ccp/IAO_EXT_0001376 obo/IAO_0000599 ["IREFWEBIRIG_"])
(ccp/IAO_EXT_0001376 rdfs/label ["IRefWebIrig identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001377
(ccp/IAO_EXT_0001377 rdf/type owl/Class)
(ccp/IAO_EXT_0001377 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001377 ccp/IAO_EXT_0001700 ["^\\d+$"])
(ccp/IAO_EXT_0001377 obo/IAO_0000599 ["IREFWEBIROG_"])
(ccp/IAO_EXT_0001377 rdfs/label ["IRefWebIrog identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001378
(ccp/IAO_EXT_0001378 rdf/type owl/Class)
(ccp/IAO_EXT_0001378 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001378 obo/IAO_0000599 ["IREFWEBRIG_"])
(ccp/IAO_EXT_0001378 rdfs/label ["IRefWebRig identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001379
(ccp/IAO_EXT_0001379 rdf/type owl/Class)
(ccp/IAO_EXT_0001379 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001379 obo/IAO_0000599 ["IREFWEBROG_"])
(ccp/IAO_EXT_0001379 rdfs/label ["IRefWebRog identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001380
(ccp/IAO_EXT_0001380 rdf/type owl/Class)
(ccp/IAO_EXT_0001380 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001380 obo/IAO_0000599 ["ISRCTN_"])
(ccp/IAO_EXT_0001380 rdfs/label ["Isrctn identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001381
(ccp/IAO_EXT_0001381 rdf/type owl/Class)
(ccp/IAO_EXT_0001381 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001381 ccp/IAO_EXT_0001700 ["^\\d+$"])
(ccp/IAO_EXT_0001381 obo/IAO_0000599 ["IUPHARLIGAND_"])
(ccp/IAO_EXT_0001381 rdfs/label ["IupharLigand identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001382
(ccp/IAO_EXT_0001382 rdf/type owl/Class)
(ccp/IAO_EXT_0001382 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001382 ccp/IAO_EXT_0001700 ["^\\d+$"])
(ccp/IAO_EXT_0001382 obo/IAO_0000599 ["IUPHARRECEPTOR_"])
(ccp/IAO_EXT_0001382 rdfs/label ["IupharReceptor identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001383
(ccp/IAO_EXT_0001383 rdf/type owl/Class)
(ccp/IAO_EXT_0001383 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001383 obo/IAO_0000599 ["KEGGCOMPOUND_"])
(ccp/IAO_EXT_0001383 rdfs/label ["KeggCompound identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001384
(ccp/IAO_EXT_0001384 rdf/type owl/Class)
(ccp/IAO_EXT_0001384 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001384 obo/IAO_0000599 ["KEGGDRUG_"])
(ccp/IAO_EXT_0001384 rdfs/label ["KeggDrug identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001385
(ccp/IAO_EXT_0001385 rdf/type owl/Class)
(ccp/IAO_EXT_0001385 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001385 obo/IAO_0000599 ["KEGGGENE_"])
(ccp/IAO_EXT_0001385 rdfs/label ["KeggGene identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001386
(ccp/IAO_EXT_0001386 rdf/type owl/Class)
(ccp/IAO_EXT_0001386 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001386 obo/IAO_0000599 ["KEGGPATHWAY_"])
(ccp/IAO_EXT_0001386 rdfs/label ["KeggPathway identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001387
(ccp/IAO_EXT_0001387 rdf/type owl/Class)
(ccp/IAO_EXT_0001387 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001387 obo/IAO_0000599 ["LEGIOLIST_"])
(ccp/IAO_EXT_0001387 rdfs/label ["LegioList identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001388
(ccp/IAO_EXT_0001388 rdf/type owl/Class)
(ccp/IAO_EXT_0001388 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001388 obo/IAO_0000599 ["LEPROMA_"])
(ccp/IAO_EXT_0001388 rdfs/label ["Leproma identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001389
(ccp/IAO_EXT_0001389 rdf/type owl/Class)
(ccp/IAO_EXT_0001389 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001389 ccp/IAO_EXT_0001700 ["^\\d+$"])
(ccp/IAO_EXT_0001389 obo/IAO_0000599 ["MAIZEGDB_"])
(ccp/IAO_EXT_0001389 rdfs/label ["MaizeGdb identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001390
(ccp/IAO_EXT_0001390 rdf/type owl/Class)
(ccp/IAO_EXT_0001390 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001390 obo/IAO_0000599 ["MAMITTRNADB_"])
(ccp/IAO_EXT_0001390 rdfs/label ["MamitTrnaDb identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001391
(ccp/IAO_EXT_0001391 rdf/type owl/Class)
(ccp/IAO_EXT_0001391 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001391 obo/IAO_0000599 ["MATRIXDB_"])
(ccp/IAO_EXT_0001391 rdfs/label ["MatrixDb identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001392
(ccp/IAO_EXT_0001392 rdf/type owl/Class)
(ccp/IAO_EXT_0001392 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001392 obo/IAO_0000599 ["MEDGEN_"])
(ccp/IAO_EXT_0001392 rdfs/label ["MedGen identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001393
(ccp/IAO_EXT_0001393 rdf/type owl/Class)
(ccp/IAO_EXT_0001393 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001393 obo/IAO_0000599 ["MEROPS_"])
(ccp/IAO_EXT_0001393 rdfs/label ["Merops identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001394
(ccp/IAO_EXT_0001394 rdf/type owl/Class)
(ccp/IAO_EXT_0001394 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001394 obo/IAO_0000599 ["MESH_"])
(ccp/IAO_EXT_0001394 rdfs/label ["Mesh identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001395
(ccp/IAO_EXT_0001395 rdf/type owl/Class)
(ccp/IAO_EXT_0001395 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001395 obo/IAO_0000599 ["MICADO_"])
(ccp/IAO_EXT_0001395 rdfs/label ["Micado identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001396
(ccp/IAO_EXT_0001396 rdf/type owl/Class)
(ccp/IAO_EXT_0001396 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001396 obo/IAO_0000599 ["MINT_"])
(ccp/IAO_EXT_0001396 rdfs/label ["Mint identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001397
(ccp/IAO_EXT_0001397 rdf/type owl/Class)
(ccp/IAO_EXT_0001397 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001397 obo/IAO_0000599 ["MIRBASE_"])
(ccp/IAO_EXT_0001397 rdfs/label ["MiRBase identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001398
(ccp/IAO_EXT_0001398 rdf/type owl/Class)
(ccp/IAO_EXT_0001398 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001398 obo/IAO_0000599 ["MIRTE_"])
(ccp/IAO_EXT_0001398 rdfs/label ["Mirte identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001399
(ccp/IAO_EXT_0001399 rdf/type owl/Class)
(ccp/IAO_EXT_0001399 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001399 obo/IAO_0000599 ["MODBASE_"])
(ccp/IAO_EXT_0001399 rdfs/label ["ModBase identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001400
(ccp/IAO_EXT_0001400 rdf/type owl/Class)
(ccp/IAO_EXT_0001400 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001400 obo/IAO_0000599 ["MPACT_"])
(ccp/IAO_EXT_0001400 rdfs/label ["Mpact identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001401
(ccp/IAO_EXT_0001401 rdf/type owl/Class)
(ccp/IAO_EXT_0001401 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001401 obo/IAO_0000599 ["MPIDB_"])
(ccp/IAO_EXT_0001401 rdfs/label ["MpiDb identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001402
(ccp/IAO_EXT_0001402 rdf/type owl/Class)
(ccp/IAO_EXT_0001402 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001402 obo/IAO_0000599 ["MUTDB_"])
(ccp/IAO_EXT_0001402 rdfs/label ["MutDb identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001403
(ccp/IAO_EXT_0001403 rdf/type owl/Class)
(ccp/IAO_EXT_0001403 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001403 obo/IAO_0000599 ["MYCOCLAP_"])
(ccp/IAO_EXT_0001403 rdfs/label ["MycoCLAP identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001404
(ccp/IAO_EXT_0001404 rdf/type owl/Class)
(ccp/IAO_EXT_0001404 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001404 obo/IAO_0000599 ["NASONIABASE_"])
(ccp/IAO_EXT_0001404 rdfs/label ["NasoniaBase identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001405
(ccp/IAO_EXT_0001405 rdf/type owl/Class)
(ccp/IAO_EXT_0001405 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001405 obo/IAO_0000599 ["NDC_"])
(ccp/IAO_EXT_0001405 rdfs/label ["NationalDrugCodeDirectory identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001406
(ccp/IAO_EXT_0001406 rdf/type owl/Class)
(ccp/IAO_EXT_0001406 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001406 obo/IAO_0000599 ["NCBITRACE_"])
(ccp/IAO_EXT_0001406 rdfs/label ["NcbiTrace identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001407
(ccp/IAO_EXT_0001407 rdf/type owl/Class)
(ccp/IAO_EXT_0001407 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001407 obo/IAO_0000599 ["NEXTBIO_"])
(ccp/IAO_EXT_0001407 rdfs/label ["NextBio identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001408
(ccp/IAO_EXT_0001408 rdf/type owl/Class)
(ccp/IAO_EXT_0001408 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001408 obo/IAO_0000599 ["NEXTPROT_"])
(ccp/IAO_EXT_0001408 rdfs/label ["NeXtProt identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001409
(ccp/IAO_EXT_0001409 rdf/type owl/Class)
(ccp/IAO_EXT_0001409 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001409 obo/IAO_0000599 ["OGP_"])
(ccp/IAO_EXT_0001409 rdfs/label ["OGP identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001410
(ccp/IAO_EXT_0001410 rdf/type owl/Class)
(ccp/IAO_EXT_0001410 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001410 obo/IAO_0000599 ["OMA_"])
(ccp/IAO_EXT_0001410 rdfs/label ["OMA identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001412
(ccp/IAO_EXT_0001412 rdf/type owl/Class)
(ccp/IAO_EXT_0001412 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001412 obo/IAO_0000599 ["OPHID_"])
(ccp/IAO_EXT_0001412 rdfs/label ["Ophid identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001413
(ccp/IAO_EXT_0001413 rdf/type owl/Class)
(ccp/IAO_EXT_0001413 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001413 ccp/IAO_EXT_0001700 ["^\\d+$"])
(ccp/IAO_EXT_0001413 obo/IAO_0000599 ["ORDO_"])
(ccp/IAO_EXT_0001413 rdfs/label ["Orphanet identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001414
(ccp/IAO_EXT_0001414 rdf/type owl/Class)
(ccp/IAO_EXT_0001414 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001414 obo/IAO_0000599 ["ORTHODB_"])
(ccp/IAO_EXT_0001414 rdfs/label ["OrthoDB identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001415
(ccp/IAO_EXT_0001415 rdf/type owl/Class)
(ccp/IAO_EXT_0001415 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001415 obo/IAO_0000599 ["PANTHER_"])
(ccp/IAO_EXT_0001415 rdfs/label ["Panther identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001416
(ccp/IAO_EXT_0001416 rdf/type owl/Class)
(ccp/IAO_EXT_0001416 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001416 obo/IAO_0000599 ["PATHEMA_"])
(ccp/IAO_EXT_0001416 rdfs/label ["Pathema identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001417
(ccp/IAO_EXT_0001417 rdf/type owl/Class)
(ccp/IAO_EXT_0001417 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001417 obo/IAO_0000599 ["PATHWAY_INTERACTION_DB_"])
(ccp/IAO_EXT_0001417 rdfs/label ["Pathway_Interaction_DB identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001418
(ccp/IAO_EXT_0001418 rdf/type owl/Class)
(ccp/IAO_EXT_0001418 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001418 obo/IAO_0000599 ["PATRIC_"])
(ccp/IAO_EXT_0001418 rdfs/label ["PATRIC identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001419
(ccp/IAO_EXT_0001419 rdf/type owl/Class)
(ccp/IAO_EXT_0001419 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001419 obo/IAO_0000599 ["PAXDB_"])
(ccp/IAO_EXT_0001419 rdfs/label ["PaxDb identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001420
(ccp/IAO_EXT_0001420 rdf/type owl/Class)
(ccp/IAO_EXT_0001420 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001420 ccp/IAO_EXT_0001700 ["^\\d+$"])
(ccp/IAO_EXT_0001420 obo/IAO_0000599 ["PBR_"])
(ccp/IAO_EXT_0001420 rdfs/label ["Pbr identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001421
(ccp/IAO_EXT_0001421 rdf/type owl/Class)
(ccp/IAO_EXT_0001421 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001421 obo/IAO_0000599 ["PDB_J_"])
(ccp/IAO_EXT_0001421 rdfs/label ["PDB_J identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001422
(ccp/IAO_EXT_0001422 rdf/type owl/Class)
(ccp/IAO_EXT_0001422 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001422 obo/IAO_0000599 ["PDB_SUM_"])
(ccp/IAO_EXT_0001422 rdfs/label ["PDB_sum identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001423
(ccp/IAO_EXT_0001423 rdf/type owl/Class)
(ccp/IAO_EXT_0001423 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001423 obo/IAO_0000599 ["PDBEUROPE_"])
(ccp/IAO_EXT_0001423 rdfs/label ["PdbEurope identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001424
(ccp/IAO_EXT_0001424 rdf/type owl/Class)
(ccp/IAO_EXT_0001424 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001424 obo/IAO_0000599 ["PDBLIGAND_"])
(ccp/IAO_EXT_0001424 rdfs/label ["PdbLigand identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001425
(ccp/IAO_EXT_0001425 rdf/type owl/Class)
(ccp/IAO_EXT_0001425 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001425 obo/IAO_0000599 ["PEPTIDEATLAS_"])
(ccp/IAO_EXT_0001425 rdfs/label ["PeptideAtlas identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001426
(ccp/IAO_EXT_0001426 rdf/type owl/Class)
(ccp/IAO_EXT_0001426 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001426 obo/IAO_0000599 ["PEROXIBASE_"])
(ccp/IAO_EXT_0001426 rdfs/label ["PeroxiBase identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001427
(ccp/IAO_EXT_0001427 rdf/type owl/Class)
(ccp/IAO_EXT_0001427 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001427 obo/IAO_0000599 ["PFAM_"])
(ccp/IAO_EXT_0001427 rdfs/label ["Pfam identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001428
(ccp/IAO_EXT_0001428 rdf/type owl/Class)
(ccp/IAO_EXT_0001428 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001428 obo/IAO_0000599 ["PHARMGKBHAPLOTYPE_"])
(ccp/IAO_EXT_0001428 rdfs/label ["PharmGkbHaplotype identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001429
(ccp/IAO_EXT_0001429 rdf/type owl/Class)
(ccp/IAO_EXT_0001429 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001429 ccp/IAO_EXT_0001700 ["PA\\d+"])
(ccp/IAO_EXT_0001429 obo/IAO_0000599 ["PHARMGKB_"])
(ccp/IAO_EXT_0001429 rdfs/label ["PharmGkb identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001430
(ccp/IAO_EXT_0001430 rdf/type owl/Class)
(ccp/IAO_EXT_0001430 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001430 ccp/IAO_EXT_0001700 ["chr.*?:\\d+ \\(hg\\d+\\)"])
(ccp/IAO_EXT_0001430 obo/IAO_0000599 ["PHARMGKBVARIANTLOCATION_"])
(ccp/IAO_EXT_0001430 rdfs/label ["PharmGkbVariantLocation identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001431
(ccp/IAO_EXT_0001431 rdf/type owl/Class)
(ccp/IAO_EXT_0001431 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001431 obo/IAO_0000599 ["PHOSPHOSITE_"])
(ccp/IAO_EXT_0001431 rdfs/label ["PhosphoSite identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001432
(ccp/IAO_EXT_0001432 rdf/type owl/Class)
(ccp/IAO_EXT_0001432 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001432 obo/IAO_0000599 ["PHOSSITE_"])
(ccp/IAO_EXT_0001432 rdfs/label ["PhosSite identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001433
(ccp/IAO_EXT_0001433 rdf/type owl/Class)
(ccp/IAO_EXT_0001433 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001433 obo/IAO_0000599 ["PHYLOMEDB_"])
(ccp/IAO_EXT_0001433 rdfs/label ["PhylomeDB identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001434
(ccp/IAO_EXT_0001434 rdf/type owl/Class)
(ccp/IAO_EXT_0001434 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001434 obo/IAO_0000599 ["PICTAR_"])
(ccp/IAO_EXT_0001434 rdfs/label ["Pictar identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001435
(ccp/IAO_EXT_0001435 rdf/type owl/Class)
(ccp/IAO_EXT_0001435 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001435 obo/IAO_0000599 ["PIRNABANK_"])
(ccp/IAO_EXT_0001435 rdfs/label ["PiRnaBank identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001436
(ccp/IAO_EXT_0001436 rdf/type owl/Class)
(ccp/IAO_EXT_0001436 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001436 obo/IAO_0000599 ["PIRNR_"])
(ccp/IAO_EXT_0001436 rdfs/label ["Pirnr identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001437
(ccp/IAO_EXT_0001437 rdf/type owl/Class)
(ccp/IAO_EXT_0001437 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001437 ccp/IAO_EXT_0001700 ["PIRSF\\d+"])
(ccp/IAO_EXT_0001437 obo/IAO_0000599 ["PIRSF_"])
(ccp/IAO_EXT_0001437 rdfs/label ["PirSf identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001438
(ccp/IAO_EXT_0001438 rdf/type owl/Class)
(ccp/IAO_EXT_0001438 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001438 obo/IAO_0000599 ["PIRSR_"])
(ccp/IAO_EXT_0001438 rdfs/label ["Pirsr identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001439
(ccp/IAO_EXT_0001439 rdf/type owl/Class)
(ccp/IAO_EXT_0001439 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001439 obo/IAO_0000599 ["PMAP_CUTDB_"])
(ccp/IAO_EXT_0001439 rdfs/label ["PMAP_CutDB identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001440
(ccp/IAO_EXT_0001440 rdf/type owl/Class)
(ccp/IAO_EXT_0001440 rdfs/subClassOf ccp/IAO_EXT_0000182)
(ccp/IAO_EXT_0001440 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001440 obo/IAO_0000599 ["POMBASE_"])
(ccp/IAO_EXT_0001440 rdfs/label ["PomBase identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001441
(ccp/IAO_EXT_0001441 rdf/type owl/Class)
(ccp/IAO_EXT_0001441 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001441 obo/IAO_0000599 ["PPTASEDB_"])
(ccp/IAO_EXT_0001441 rdfs/label ["PptaseDB identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001442
(ccp/IAO_EXT_0001442 rdf/type owl/Class)
(ccp/IAO_EXT_0001442 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001442 obo/IAO_0000599 ["PREMOD_"])
(ccp/IAO_EXT_0001442 rdfs/label ["PreMod identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001443
(ccp/IAO_EXT_0001443 rdf/type owl/Class)
(ccp/IAO_EXT_0001443 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001443 obo/IAO_0000599 ["PRF_"])
(ccp/IAO_EXT_0001443 rdfs/label ["Prf identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001444
(ccp/IAO_EXT_0001444 rdf/type owl/Class)
(ccp/IAO_EXT_0001444 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001444 obo/IAO_0000599 ["PRIDE_"])
(ccp/IAO_EXT_0001444 rdfs/label ["PRIDE identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001445
(ccp/IAO_EXT_0001445 rdf/type owl/Class)
(ccp/IAO_EXT_0001445 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001445 obo/IAO_0000599 ["PRINTS_"])
(ccp/IAO_EXT_0001445 rdfs/label ["Prints identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001446
(ccp/IAO_EXT_0001446 rdf/type owl/Class)
(ccp/IAO_EXT_0001446 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001446 obo/IAO_0000599 ["PRODOM_"])
(ccp/IAO_EXT_0001446 rdfs/label ["ProDom identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001447
(ccp/IAO_EXT_0001447 rdf/type owl/Class)
(ccp/IAO_EXT_0001447 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001447 obo/IAO_0000599 ["PROMEX_"])
(ccp/IAO_EXT_0001447 rdfs/label ["ProMEX identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001448
(ccp/IAO_EXT_0001448 rdf/type owl/Class)
(ccp/IAO_EXT_0001448 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001448 obo/IAO_0000599 ["PROSITE_"])
(ccp/IAO_EXT_0001448 rdfs/label ["Prosite identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001449
(ccp/IAO_EXT_0001449 rdf/type owl/Class)
(ccp/IAO_EXT_0001449 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001449 obo/IAO_0000599 ["PROTCLUSTDB_"])
(ccp/IAO_EXT_0001449 rdfs/label ["ProtClustDB identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001450
(ccp/IAO_EXT_0001450 rdf/type owl/Class)
(ccp/IAO_EXT_0001450 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001450 obo/IAO_0000599 ["PROTEINMODELPORTAL_"])
(ccp/IAO_EXT_0001450 rdfs/label ["ProteinModelPortal identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001451
(ccp/IAO_EXT_0001451 rdf/type owl/Class)
(ccp/IAO_EXT_0001451 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001451 obo/IAO_0000599 ["PROTONET_"])
(ccp/IAO_EXT_0001451 rdfs/label ["ProtoNet identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001452
(ccp/IAO_EXT_0001452 rdf/type owl/Class)
(ccp/IAO_EXT_0001452 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001452 obo/IAO_0000599 ["PSEUDOCAP_"])
(ccp/IAO_EXT_0001452 rdfs/label ["PseudoCap identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001453
(ccp/IAO_EXT_0001453 rdf/type owl/Class)
(ccp/IAO_EXT_0001453 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001453 obo/IAO_0000599 ["PSEUDOGENEORG_"])
(ccp/IAO_EXT_0001453 rdfs/label ["PseudogeneOrg identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001454
(ccp/IAO_EXT_0001454 rdf/type owl/Class)
(ccp/IAO_EXT_0001454 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001454 obo/IAO_0000599 ["PSIMOD_"])
(ccp/IAO_EXT_0001454 rdfs/label ["PsiMod identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001455
(ccp/IAO_EXT_0001455 rdf/type owl/Class)
(ccp/IAO_EXT_0001455 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001455 obo/IAO_0000599 ["PUBCHEMBIOASSAY_"])
(ccp/IAO_EXT_0001455 rdfs/label ["PubChemBioAssay identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001456
(ccp/IAO_EXT_0001456 rdf/type owl/Class)
(ccp/IAO_EXT_0001456 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001456 obo/IAO_0000599 ["PUBCHEMCOMPOUND_"])
(ccp/IAO_EXT_0001456 rdfs/label ["PubChemCompound identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001457
(ccp/IAO_EXT_0001457 rdf/type owl/Class)
(ccp/IAO_EXT_0001457 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001457 obo/IAO_0000599 ["PUBCHEMSUBSTANCE_"])
(ccp/IAO_EXT_0001457 rdfs/label ["PubChemSubstance identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001458
(ccp/IAO_EXT_0001458 rdf/type owl/Class)
(ccp/IAO_EXT_0001458 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001458 obo/IAO_0000599 ["PW_"])
(ccp/IAO_EXT_0001458 rdfs/label ["Pw identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001459
(ccp/IAO_EXT_0001459 rdf/type owl/Class)
(ccp/IAO_EXT_0001459 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001459 ccp/IAO_EXT_0001700 ["^\\d+$"])
(ccp/IAO_EXT_0001459 obo/IAO_0000599 ["RATMAP_"])
(ccp/IAO_EXT_0001459 rdfs/label ["RatMap identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001460
(ccp/IAO_EXT_0001460 rdf/type owl/Class)
(ccp/IAO_EXT_0001460 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001460 obo/IAO_0000599 ["RDO_"])
(ccp/IAO_EXT_0001460 rdfs/label ["Rdo identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001461
(ccp/IAO_EXT_0001461 rdf/type owl/Class)
(ccp/IAO_EXT_0001461 rdfs/subClassOf ccp/IAO_EXT_0001643)
(ccp/IAO_EXT_0001461 ccp/IAO_EXT_0001700 ["R-([A-Z]{3})-(\\d+)"])
(ccp/IAO_EXT_0001461 obo/IAO_0000599 ["REACTOMEREACTION_"])
(ccp/IAO_EXT_0001461 rdfs/label ["ReactomeReaction identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001462
(ccp/IAO_EXT_0001462 rdf/type owl/Class)
(ccp/IAO_EXT_0001462 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001462 obo/IAO_0000599 ["REBASE_"])
(ccp/IAO_EXT_0001462 rdfs/label ["REBASE identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001463
(ccp/IAO_EXT_0001463 rdf/type owl/Class)
(ccp/IAO_EXT_0001463 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001463 ccp/IAO_EXT_0001700 ["rs\\d+"])
(ccp/IAO_EXT_0001463 obo/IAO_0000599 ["REFSNP_"])
(ccp/IAO_EXT_0001463 dc/description ["From dbSNP Handbook:\nOnce a new SNP is submitted to dbSNP, it is assigned a unique submitted SNP ID number (ss;;). Once the ss number is assigned, we align the flanking sequence of each submitted SNP to its appropriate genomic contig. If several ss numbers map to the same position on the contig, we cluster them together, call the cluster a \"reference SNP cluster\", or \"refSNP\", and provide the cluster with a unique RefSNP ID number (rs;;). If only one ss number maps to a specific position, then that ss is assigned an rs number and is the only member of its RefSNP cluster until another submitted SNP is found that maps to the same position."])
(ccp/IAO_EXT_0001463 rdfs/label ["RefSnp identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001464
(ccp/IAO_EXT_0001464 rdf/type owl/Class)
(ccp/IAO_EXT_0001464 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001464 obo/IAO_0000599 ["REPRODUCTION_2DPAGE_"])
(ccp/IAO_EXT_0001464 rdfs/label ["REPRODUCTION_2DPAGE identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001465
(ccp/IAO_EXT_0001465 rdf/type owl/Class)
(ccp/IAO_EXT_0001465 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001465 obo/IAO_0000599 ["RFAM_"])
(ccp/IAO_EXT_0001465 rdfs/label ["Rfam identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001466
(ccp/IAO_EXT_0001466 rdf/type owl/Class)
(ccp/IAO_EXT_0001466 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001466 obo/IAO_0000599 ["ROUGE_"])
(ccp/IAO_EXT_0001466 rdfs/label ["Rouge identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001467
(ccp/IAO_EXT_0001467 rdf/type owl/Class)
(ccp/IAO_EXT_0001467 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001467 obo/IAO_0000599 ["RULEBASE_"])
(ccp/IAO_EXT_0001467 rdfs/label ["RuleBase identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001468
(ccp/IAO_EXT_0001468 rdf/type owl/Class)
(ccp/IAO_EXT_0001468 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001468 obo/IAO_0000599 ["SAAS_"])
(ccp/IAO_EXT_0001468 rdfs/label ["Saas identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001469
(ccp/IAO_EXT_0001469 rdf/type owl/Class)
(ccp/IAO_EXT_0001469 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001469 obo/IAO_0000599 ["SABIO_RK_"])
(ccp/IAO_EXT_0001469 rdfs/label ["SABIO_RK identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001470
(ccp/IAO_EXT_0001470 rdf/type owl/Class)
(ccp/IAO_EXT_0001470 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001470 obo/IAO_0000599 ["SBKB_"])
(ccp/IAO_EXT_0001470 rdfs/label ["SBKB identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001471
(ccp/IAO_EXT_0001471 rdf/type owl/Class)
(ccp/IAO_EXT_0001471 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001471 obo/IAO_0000599 ["SLC_"])
(ccp/IAO_EXT_0001471 rdfs/label ["Slc identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001472
(ccp/IAO_EXT_0001472 rdf/type owl/Class)
(ccp/IAO_EXT_0001472 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001472 obo/IAO_0000599 ["SMART_"])
(ccp/IAO_EXT_0001472 rdfs/label ["Smart identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001473
(ccp/IAO_EXT_0001473 rdf/type owl/Class)
(ccp/IAO_EXT_0001473 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001473 obo/IAO_0000599 ["SMR_"])
(ccp/IAO_EXT_0001473 rdfs/label ["SMR identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001474
(ccp/IAO_EXT_0001474 rdf/type owl/Class)
(ccp/IAO_EXT_0001474 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001474 ccp/IAO_EXT_0001700 ["^\\d+$"])
(ccp/IAO_EXT_0001474 obo/IAO_0000599 ["SNOMEDCT_"])
(ccp/IAO_EXT_0001474 rdfs/label ["SnoMedCt identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001475
(ccp/IAO_EXT_0001475 rdf/type owl/Class)
(ccp/IAO_EXT_0001475 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001475 obo/IAO_0000599 ["SNORNABASE_"])
(ccp/IAO_EXT_0001475 rdfs/label ["SnoRnaBase identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001477
(ccp/IAO_EXT_0001477 rdf/type owl/Class)
(ccp/IAO_EXT_0001477 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001477 obo/IAO_0000599 ["SOURCE_"])
(ccp/IAO_EXT_0001477 rdfs/label ["SOURCE identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001478
(ccp/IAO_EXT_0001478 rdf/type owl/Class)
(ccp/IAO_EXT_0001478 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001478 obo/IAO_0000599 ["STRING_"])
(ccp/IAO_EXT_0001478 rdfs/label ["STRING identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001479
(ccp/IAO_EXT_0001479 rdf/type owl/Class)
(ccp/IAO_EXT_0001479 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001479 obo/IAO_0000599 ["SUPERFAM_"])
(ccp/IAO_EXT_0001479 rdfs/label ["SuperFam identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001480
(ccp/IAO_EXT_0001480 rdf/type owl/Class)
(ccp/IAO_EXT_0001480 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001480 obo/IAO_0000599 ["SUPFAM_"])
(ccp/IAO_EXT_0001480 rdfs/label ["SUPFAM identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001481
(ccp/IAO_EXT_0001481 rdf/type owl/Class)
(ccp/IAO_EXT_0001481 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001481 obo/IAO_0000599 ["SWISS_2DPAGE_"])
(ccp/IAO_EXT_0001481 rdfs/label ["SWISS_2DPAGE identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001482
(ccp/IAO_EXT_0001482 rdf/type owl/Class)
(ccp/IAO_EXT_0001482 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001482 obo/IAO_0000599 ["TCDB_"])
(ccp/IAO_EXT_0001482 rdfs/label ["TCDB identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001483
(ccp/IAO_EXT_0001483 rdf/type owl/Class)
(ccp/IAO_EXT_0001483 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001483 obo/IAO_0000599 ["THERAPEUTICTARGETSDATABASE_"])
(ccp/IAO_EXT_0001483 rdfs/label ["TherapeuticTargetsDatabase identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001484
(ccp/IAO_EXT_0001484 rdf/type owl/Class)
(ccp/IAO_EXT_0001484 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001484 obo/IAO_0000599 ["TIGRFAMS_"])
(ccp/IAO_EXT_0001484 rdfs/label ["TigrFams identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001485
(ccp/IAO_EXT_0001485 rdf/type owl/Class)
(ccp/IAO_EXT_0001485 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001485 ccp/IAO_EXT_0001700 ["T\\d+"])
(ccp/IAO_EXT_0001485 obo/IAO_0000599 ["TRANSFACFACTOR_"])
(ccp/IAO_EXT_0001485 rdfs/label ["TransfacFactor identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001486
(ccp/IAO_EXT_0001486 rdf/type owl/Class)
(ccp/IAO_EXT_0001486 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001486 ccp/IAO_EXT_0001700 ["G\\d+"])
(ccp/IAO_EXT_0001486 obo/IAO_0000599 ["TRANSFACGENE_"])
(ccp/IAO_EXT_0001486 rdfs/label ["TransfacGene identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001487
(ccp/IAO_EXT_0001487 rdf/type owl/Class)
(ccp/IAO_EXT_0001487 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001487 ccp/IAO_EXT_0001700 ["M\\d+"])
(ccp/IAO_EXT_0001487 obo/IAO_0000599 ["TRANSFACMATRIX_"])
(ccp/IAO_EXT_0001487 rdfs/label ["TransfacMatrix identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001488
(ccp/IAO_EXT_0001488 rdf/type owl/Class)
(ccp/IAO_EXT_0001488 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001488 obo/IAO_0000599 ["TUBERCULIST_"])
(ccp/IAO_EXT_0001488 rdfs/label ["TubercuList identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001489
(ccp/IAO_EXT_0001489 rdf/type owl/Class)
(ccp/IAO_EXT_0001489 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001489 obo/IAO_0000599 ["UCD_2DPAGE_"])
(ccp/IAO_EXT_0001489 rdfs/label ["UCD_2DPAGE identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001490
(ccp/IAO_EXT_0001490 rdf/type owl/Class)
(ccp/IAO_EXT_0001490 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001490 obo/IAO_0000599 ["UCSCGENOMEBROWSER_"])
(ccp/IAO_EXT_0001490 rdfs/label ["UcscGenomeBrowser identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001492
(ccp/IAO_EXT_0001492 rdf/type owl/Class)
(ccp/IAO_EXT_0001492 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001492 obo/IAO_0000599 ["UNCHARACTERIZEDPFAM_"])
(ccp/IAO_EXT_0001492 rdfs/label ["UncharacterizedPfam identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001493
(ccp/IAO_EXT_0001493 rdf/type owl/Class)
(ccp/IAO_EXT_0001493 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001493 obo/IAO_0000599 ["UNIPATHWAY_"])
(ccp/IAO_EXT_0001493 rdfs/label ["UniPathway identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001494
(ccp/IAO_EXT_0001494 rdf/type owl/Class)
(ccp/IAO_EXT_0001494 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001494 rdfs/subClassOf ccp/IAO_EXT_0001713)
(ccp/IAO_EXT_0001494 ccp/IAO_EXT_0001700 ["[OPQ][0-9][A-Z0-9]{3}[0-9]|[A-NR-Z][0-9]([A-Z][A-Z0-9]{2}[0-9]){1,2}-\\d+"])
(ccp/IAO_EXT_0001494 obo/IAO_0000599 ["UNIPROTENTRYNAME_"])
(ccp/IAO_EXT_0001494 rdfs/label ["UniProtEntryName identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001495
(ccp/IAO_EXT_0001495 rdf/type owl/Class)
(ccp/IAO_EXT_0001495 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001495 rdfs/subClassOf ccp/IAO_EXT_0001713)
(ccp/IAO_EXT_0001495 obo/IAO_0000599 ["UNIPROTISOFORM_"])
(ccp/IAO_EXT_0001495 rdfs/label ["UniProtIsoform identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001496
(ccp/IAO_EXT_0001496 rdf/type owl/Class)
(ccp/IAO_EXT_0001496 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001496 obo/IAO_0000599 ["UNNORMALIZEDMOLECULARINTERACTIONONTOLOGYTERM_"])
(ccp/IAO_EXT_0001496 rdfs/label ["UnNormalizedMolecularInteractionOntologyTerm identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001497
(ccp/IAO_EXT_0001497 rdf/type owl/Class)
(ccp/IAO_EXT_0001497 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001497 ccp/IAO_EXT_0001700 ["^\\d+$"])
(ccp/IAO_EXT_0001497 obo/IAO_0000599 ["VBRC_"])
(ccp/IAO_EXT_0001497 rdfs/label ["Vbrc identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001498
(ccp/IAO_EXT_0001498 rdf/type owl/Class)
(ccp/IAO_EXT_0001498 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001498 obo/IAO_0000599 ["VECTORBASE_"])
(ccp/IAO_EXT_0001498 rdfs/label ["VectorBase identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001499
(ccp/IAO_EXT_0001499 rdf/type owl/Class)
(ccp/IAO_EXT_0001499 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001499 obo/IAO_0000599 ["VEGA_"])
(ccp/IAO_EXT_0001499 rdfs/label ["Vega identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001500
(ccp/IAO_EXT_0001500 rdf/type owl/Class)
(ccp/IAO_EXT_0001500 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001500 obo/IAO_0000599 ["WORLD_2DPAGE_"])
(ccp/IAO_EXT_0001500 rdfs/label ["World_2DPAGE identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001501
(ccp/IAO_EXT_0001501 rdf/type owl/Class)
(ccp/IAO_EXT_0001501 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001501 obo/IAO_0000599 ["XENBASE_"])
(ccp/IAO_EXT_0001501 rdfs/label ["XenBase identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001502
(ccp/IAO_EXT_0001502 rdf/type owl/Class)
(ccp/IAO_EXT_0001502 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001502 obo/IAO_0000599 ["ZNFGENECATALOG_"])
(ccp/IAO_EXT_0001502 rdfs/label ["ZnfGeneCatalog identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001503
(ccp/IAO_EXT_0001503 rdf/type owl/Class)
(ccp/IAO_EXT_0001503 rdfs/subClassOf ccp/IAO_EXT_0000341)
(ccp/IAO_EXT_0001503 obo/IAO_0000599 ["CROSSREFERENCEURL_"])
(ccp/IAO_EXT_0001503 rdfs/label ["CrossReferenceUrl identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001504
(ccp/IAO_EXT_0001504 rdf/type owl/Class)
(ccp/IAO_EXT_0001504 rdfs/subClassOf ccp/IAO_EXT_0000341)
(ccp/IAO_EXT_0001504 obo/IAO_0000599 ["DIPEXPERIMENT_"])
(ccp/IAO_EXT_0001504 rdfs/label ["DipInteractionExperiment identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001505
(ccp/IAO_EXT_0001505 rdf/type owl/Class)
(ccp/IAO_EXT_0001505 rdfs/subClassOf ccp/IAO_EXT_0000341)
(ccp/IAO_EXT_0001505 obo/IAO_0000599 ["ISBN_"])
(ccp/IAO_EXT_0001505 rdfs/label ["ISBN identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001506
(ccp/IAO_EXT_0001506 rdf/type owl/Class)
(ccp/IAO_EXT_0001506 rdfs/subClassOf ccp/IAO_EXT_0000341)
(ccp/IAO_EXT_0001506 obo/IAO_0000599 ["MEDLINE_"])
(ccp/IAO_EXT_0001506 rdfs/label ["Medline identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001508
(ccp/IAO_EXT_0001508 rdf/type owl/Class)
(ccp/IAO_EXT_0001508 rdfs/subClassOf ccp/IAO_EXT_0000341)
(ccp/IAO_EXT_0001508 obo/IAO_0000599 ["WIKIPEDIA_"])
(ccp/IAO_EXT_0001508 rdfs/label ["Wikipedia identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001509
(ccp/IAO_EXT_0001509 rdf/type owl/Class)
(ccp/IAO_EXT_0001509 rdfs/subClassOf ccp/IAO_EXT_0000132)
(ccp/IAO_EXT_0001509 rdfs/label ["HGNC gene record - supplied RNACentral identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001510
(ccp/IAO_EXT_0001510 rdf/type owl/Class)
(ccp/IAO_EXT_0001510 rdfs/subClassOf ccp/IAO_EXT_0000341)
(ccp/IAO_EXT_0001510 obo/IAO_0000599 ["GOA_REFERENCE_"])
(ccp/IAO_EXT_0001510 rdfs/label ["GOA reference identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001511
(ccp/IAO_EXT_0001511 rdf/type owl/Class)
(ccp/IAO_EXT_0001511 rdfs/subClassOf ccp/IAO_EXT_0000341)
(ccp/IAO_EXT_0001511 obo/IAO_0000599 ["GO_REFERENCE_"])
(ccp/IAO_EXT_0001511 rdfs/label ["GO reference identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001512
(ccp/IAO_EXT_0001512 rdf/type owl/Class)
(ccp/IAO_EXT_0001512 rdfs/subClassOf ccp/IAO_EXT_0000000)
(ccp/IAO_EXT_0001512 rdfs/label ["Reactome record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001513
(ccp/IAO_EXT_0001513 rdf/type owl/Class)
(ccp/IAO_EXT_0001513 rdfs/subClassOf ccp/IAO_EXT_0001512)
(ccp/IAO_EXT_0001513 rdfs/label ["Reactome protein record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001514
(ccp/IAO_EXT_0001514 rdf/type owl/Class)
(ccp/IAO_EXT_0001514 rdfs/subClassOf ccp/IAO_EXT_0001512)
(ccp/IAO_EXT_0001514 rdfs/label ["Reactome small molecule record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001515
(ccp/IAO_EXT_0001515 rdf/type owl/Class)
(ccp/IAO_EXT_0001515 rdfs/subClassOf ccp/IAO_EXT_0001512)
(ccp/IAO_EXT_0001515 rdfs/label ["Reactome physical entity record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001516
(ccp/IAO_EXT_0001516 rdf/type owl/Class)
(ccp/IAO_EXT_0001516 rdfs/subClassOf ccp/IAO_EXT_0001512)
(ccp/IAO_EXT_0001516 rdfs/label ["Reactome complex record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001517
(ccp/IAO_EXT_0001517 rdf/type owl/Class)
(ccp/IAO_EXT_0001517 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001517 rdfs/label ["Reactome record - Reactome identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001518
(ccp/IAO_EXT_0001518 rdf/type owl/Class)
(ccp/IAO_EXT_0001518 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001518 rdfs/label ["Reactome record - entity reference field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001519
(ccp/IAO_EXT_0001519 rdf/type owl/Class)
(ccp/IAO_EXT_0001519 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001519 rdfs/label ["Reactome record - database field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001520
(ccp/IAO_EXT_0001520 rdf/type owl/Class)
(ccp/IAO_EXT_0001520 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001520 rdfs/label ["Reactome record - database id field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001521
(ccp/IAO_EXT_0001521 rdf/type owl/Class)
(ccp/IAO_EXT_0001521 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001521 rdfs/label ["Reactome record - cellular location field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001522
(ccp/IAO_EXT_0001522 rdf/type owl/Class)
(ccp/IAO_EXT_0001522 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001522 rdfs/label ["Reactome record - UniProt accession field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001523
(ccp/IAO_EXT_0001523 rdf/type owl/Class)
(ccp/IAO_EXT_0001523 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001523 rdfs/subClassOf ccp/IAO_EXT_0000300)
(ccp/IAO_EXT_0001523 rdfs/label ["Reactome record - ENSEMBL identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001524
(ccp/IAO_EXT_0001524 rdf/type owl/Class)
(ccp/IAO_EXT_0001524 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001524 rdfs/label ["Reactome record - external reference field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001525
(ccp/IAO_EXT_0001525 rdf/type owl/Class)
(ccp/IAO_EXT_0001525 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001525 rdfs/label ["Reactome record - name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001526
(ccp/IAO_EXT_0001526 rdf/type owl/Class)
(ccp/IAO_EXT_0001526 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001526 rdfs/label ["Reactome record - display name field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001527
(ccp/IAO_EXT_0001527 rdf/type owl/Class)
(ccp/IAO_EXT_0001527 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001527 rdfs/label ["Reactome record - feature field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001528
(ccp/IAO_EXT_0001528 rdf/type owl/Class)
(ccp/IAO_EXT_0001528 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001528 rdfs/label ["Reactome record - member entity reference field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001529
(ccp/IAO_EXT_0001529 rdf/type owl/Class)
(ccp/IAO_EXT_0001529 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001529 rdfs/label ["Reactome record - author field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001530
(ccp/IAO_EXT_0001530 rdf/type owl/Class)
(ccp/IAO_EXT_0001530 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001530 rdfs/label ["Reactome record - year field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001531
(ccp/IAO_EXT_0001531 rdf/type owl/Class)
(ccp/IAO_EXT_0001531 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001531 rdfs/label ["Reactome record - source field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001532
(ccp/IAO_EXT_0001532 rdf/type owl/Class)
(ccp/IAO_EXT_0001532 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001532 rdfs/label ["Reactome record - feature location field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001533
(ccp/IAO_EXT_0001533 rdf/type owl/Class)
(ccp/IAO_EXT_0001533 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001533 rdfs/label ["Reactome record - PubMed identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001534
(ccp/IAO_EXT_0001534 rdf/type owl/Class)
(ccp/IAO_EXT_0001534 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001534 rdfs/label ["Reactome record - sequence interval start field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001535
(ccp/IAO_EXT_0001535 rdf/type owl/Class)
(ccp/IAO_EXT_0001535 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001535 rdfs/label ["Reactome record - sequence interval end field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001536
(ccp/IAO_EXT_0001536 rdf/type owl/Class)
(ccp/IAO_EXT_0001536 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001536 rdfs/label ["Reactome record - title field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001537
(ccp/IAO_EXT_0001537 rdf/type owl/Class)
(ccp/IAO_EXT_0001537 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001537 rdfs/label ["Reactome record - position status field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001538
(ccp/IAO_EXT_0001538 rdf/type owl/Class)
(ccp/IAO_EXT_0001538 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001538 rdfs/label ["Reactome record - sequence position field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001539
(ccp/IAO_EXT_0001539 rdf/type owl/Class)
(ccp/IAO_EXT_0001539 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001539 rdfs/label ["Reactome record - physical entity field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001540
(ccp/IAO_EXT_0001540 rdf/type owl/Class)
(ccp/IAO_EXT_0001540 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001540 rdfs/label ["Reactome record - stoichiometric coefficient field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001541
(ccp/IAO_EXT_0001541 rdf/type owl/Class)
(ccp/IAO_EXT_0001541 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001541 rdfs/label ["Reactome record - pathway component field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001542
(ccp/IAO_EXT_0001542 rdf/type owl/Class)
(ccp/IAO_EXT_0001542 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001542 rdfs/label ["Reactome record - pathway order field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001543
(ccp/IAO_EXT_0001543 rdf/type owl/Class)
(ccp/IAO_EXT_0001543 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001543 rdfs/label ["Reactome record - organism field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001544
(ccp/IAO_EXT_0001544 rdf/type owl/Class)
(ccp/IAO_EXT_0001544 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001544 rdfs/label ["Reactome record - component field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001545
(ccp/IAO_EXT_0001545 rdf/type owl/Class)
(ccp/IAO_EXT_0001545 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001545 rdfs/label ["Reactome record - component stoichiometry field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001546
(ccp/IAO_EXT_0001546 rdf/type owl/Class)
(ccp/IAO_EXT_0001546 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001546 rdfs/label ["Reactome record - conversion direction field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001547
(ccp/IAO_EXT_0001547 rdf/type owl/Class)
(ccp/IAO_EXT_0001547 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001547 rdfs/label ["Reactome record - modification type field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001548
(ccp/IAO_EXT_0001548 rdf/type owl/Class)
(ccp/IAO_EXT_0001548 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001548 rdfs/label ["Reactome record - step process field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001549
(ccp/IAO_EXT_0001549 rdf/type owl/Class)
(ccp/IAO_EXT_0001549 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001549 rdfs/label ["Reactome record - left field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001550
(ccp/IAO_EXT_0001550 rdf/type owl/Class)
(ccp/IAO_EXT_0001550 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001550 rdfs/label ["Reactome record - right field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001551
(ccp/IAO_EXT_0001551 rdf/type owl/Class)
(ccp/IAO_EXT_0001551 rdfs/subClassOf ccp/IAO_EXT_0001512)
(ccp/IAO_EXT_0001551 rdfs/label ["Reactome protein reference record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001552
(ccp/IAO_EXT_0001552 rdf/type owl/Class)
(ccp/IAO_EXT_0001552 rdfs/subClassOf ccp/IAO_EXT_0001512)
(ccp/IAO_EXT_0001552 rdfs/label ["Reactome small molecule reference record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001553
(ccp/IAO_EXT_0001553 rdf/type owl/Class)
(ccp/IAO_EXT_0001553 rdfs/subClassOf ccp/IAO_EXT_0001512)
(ccp/IAO_EXT_0001553 rdfs/label ["Reactome pathway record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001554
(ccp/IAO_EXT_0001554 rdf/type owl/Class)
(ccp/IAO_EXT_0001554 rdfs/subClassOf ccp/IAO_EXT_0001512)
(ccp/IAO_EXT_0001554 rdfs/label ["Reactome biochemical reaction record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001555
(ccp/IAO_EXT_0001555 rdf/type owl/Class)
(ccp/IAO_EXT_0001555 rdfs/subClassOf ccp/IAO_EXT_0001512)
(ccp/IAO_EXT_0001555 rdfs/label ["Reactome physical entity reference record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001556
(ccp/IAO_EXT_0001556 rdf/type owl/Class)
(ccp/IAO_EXT_0001556 rdfs/subClassOf ccp/IAO_EXT_0001512)
(ccp/IAO_EXT_0001556 rdfs/label ["Reactome DNA record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001557
(ccp/IAO_EXT_0001557 rdf/type owl/Class)
(ccp/IAO_EXT_0001557 rdfs/subClassOf ccp/IAO_EXT_0001512)
(ccp/IAO_EXT_0001557 rdfs/label ["Reactome DNA region record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001558
(ccp/IAO_EXT_0001558 rdf/type owl/Class)
(ccp/IAO_EXT_0001558 rdfs/subClassOf ccp/IAO_EXT_0001512)
(ccp/IAO_EXT_0001558 rdfs/label ["Reactome RNA record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001559
(ccp/IAO_EXT_0001559 rdf/type owl/Class)
(ccp/IAO_EXT_0001559 rdfs/subClassOf ccp/IAO_EXT_0001512)
(ccp/IAO_EXT_0001559 rdfs/label ["Reactome RNA region record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001560
(ccp/IAO_EXT_0001560 rdf/type owl/Class)
(ccp/IAO_EXT_0001560 rdfs/subClassOf ccp/IAO_EXT_0001512)
(ccp/IAO_EXT_0001560 rdfs/label ["Reactome DNA reference record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001561
(ccp/IAO_EXT_0001561 rdf/type owl/Class)
(ccp/IAO_EXT_0001561 rdfs/subClassOf ccp/IAO_EXT_0001512)
(ccp/IAO_EXT_0001561 rdfs/label ["Reactome RNA reference record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001562
(ccp/IAO_EXT_0001562 rdf/type owl/Class)
(ccp/IAO_EXT_0001562 rdfs/subClassOf ccp/IAO_EXT_0001512)
(ccp/IAO_EXT_0001562 rdfs/label ["Reactome DNA region reference record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001563
(ccp/IAO_EXT_0001563 rdf/type owl/Class)
(ccp/IAO_EXT_0001563 rdfs/subClassOf ccp/IAO_EXT_0001512)
(ccp/IAO_EXT_0001563 rdfs/label ["Reactome RNA region reference record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001564
(ccp/IAO_EXT_0001564 rdf/type owl/Class)
(ccp/IAO_EXT_0001564 rdfs/subClassOf ccp/IAO_EXT_0001512)
(ccp/IAO_EXT_0001564 rdfs/label ["Reactome control record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001565
(ccp/IAO_EXT_0001565 rdf/type owl/Class)
(ccp/IAO_EXT_0001565 rdfs/subClassOf ccp/IAO_EXT_0001512)
(ccp/IAO_EXT_0001565 rdfs/label ["Reactome stoichiometry record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001566
(ccp/IAO_EXT_0001566 rdf/type owl/Class)
(ccp/IAO_EXT_0001566 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001566 rdfs/label ["Reactome record - controller field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001567
(ccp/IAO_EXT_0001567 rdf/type owl/Class)
(ccp/IAO_EXT_0001567 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001567 rdfs/label ["Reactome record - controlled field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001568
(ccp/IAO_EXT_0001568 rdf/type owl/Class)
(ccp/IAO_EXT_0001568 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001568 rdfs/label ["Reactome record - control type field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001569
(ccp/IAO_EXT_0001569 rdf/type owl/Class)
(ccp/IAO_EXT_0001569 rdfs/subClassOf ccp/IAO_EXT_0001512)
(ccp/IAO_EXT_0001569 rdfs/label ["Reactome modulation record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001570
(ccp/IAO_EXT_0001570 rdf/type owl/Class)
(ccp/IAO_EXT_0001570 rdfs/subClassOf ccp/IAO_EXT_0001512)
(ccp/IAO_EXT_0001570 rdfs/label ["Reactome relationship xref record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001571
(ccp/IAO_EXT_0001571 rdf/type owl/Class)
(ccp/IAO_EXT_0001571 rdfs/subClassOf ccp/IAO_EXT_0001512)
(ccp/IAO_EXT_0001571 rdfs/label ["Reactome publication xref record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001572
(ccp/IAO_EXT_0001572 rdf/type owl/Class)
(ccp/IAO_EXT_0001572 rdfs/subClassOf ccp/IAO_EXT_0001512)
(ccp/IAO_EXT_0001572 rdfs/label ["Reactome unification xref record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001573
(ccp/IAO_EXT_0001573 rdf/type owl/Class)
(ccp/IAO_EXT_0001573 rdfs/subClassOf ccp/IAO_EXT_0001512)
(ccp/IAO_EXT_0001573 rdfs/label ["Reactome pathway step record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001574
(ccp/IAO_EXT_0001574 rdf/type owl/Class)
(ccp/IAO_EXT_0001574 rdfs/subClassOf ccp/IAO_EXT_0001512)
(ccp/IAO_EXT_0001574 rdfs/label ["Reactome catalysis record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001575
(ccp/IAO_EXT_0001575 rdf/type owl/Class)
(ccp/IAO_EXT_0001575 rdfs/subClassOf ccp/IAO_EXT_0001512)
(ccp/IAO_EXT_0001575 rdfs/label ["Reactome sequence site record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001576
(ccp/IAO_EXT_0001576 rdf/type owl/Class)
(ccp/IAO_EXT_0001576 rdfs/subClassOf ccp/IAO_EXT_0001512)
(ccp/IAO_EXT_0001576 rdfs/label ["Reactome sequence interval record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001577
(ccp/IAO_EXT_0001577 rdf/type owl/Class)
(ccp/IAO_EXT_0001577 rdfs/subClassOf ccp/IAO_EXT_0001512)
(ccp/IAO_EXT_0001577 rdfs/label ["Reactome provenance record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001578
(ccp/IAO_EXT_0001578 rdf/type owl/Class)
(ccp/IAO_EXT_0001578 rdfs/subClassOf ccp/IAO_EXT_0001512)
(ccp/IAO_EXT_0001578 rdfs/label ["Reactome biosource record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001579
(ccp/IAO_EXT_0001579 rdf/type owl/Class)
(ccp/IAO_EXT_0001579 rdfs/subClassOf ccp/IAO_EXT_0001512)
(ccp/IAO_EXT_0001579 rdfs/label ["Reactome relationship type vocabulary record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001580
(ccp/IAO_EXT_0001580 rdf/type owl/Class)
(ccp/IAO_EXT_0001580 rdfs/subClassOf ccp/IAO_EXT_0001512)
(ccp/IAO_EXT_0001580 rdfs/label ["Reactome template reaction record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001581
(ccp/IAO_EXT_0001581 rdf/type owl/Class)
(ccp/IAO_EXT_0001581 rdfs/subClassOf ccp/IAO_EXT_0001512)
(ccp/IAO_EXT_0001581 rdfs/label ["Reactome template reaction regulation record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001583
(ccp/IAO_EXT_0001583 rdf/type owl/Class)
(ccp/IAO_EXT_0001583 rdfs/subClassOf ccp/IAO_EXT_0001512)
(ccp/IAO_EXT_0001583 rdfs/label ["Reactome sequence modification vocabulary record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001584
(ccp/IAO_EXT_0001584 rdf/type owl/Class)
(ccp/IAO_EXT_0001584 rdfs/subClassOf ccp/IAO_EXT_0001512)
(ccp/IAO_EXT_0001584 rdfs/label ["Reactome cellular location vocabulary record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001585
(ccp/IAO_EXT_0001585 rdf/type owl/Class)
(ccp/IAO_EXT_0001585 rdfs/subClassOf ccp/IAO_EXT_0001512)
(ccp/IAO_EXT_0001585 rdfs/label ["Reactome degradation record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001586
(ccp/IAO_EXT_0001586 rdf/type owl/Class)
(ccp/IAO_EXT_0001586 rdfs/subClassOf ccp/IAO_EXT_0001512)
(ccp/IAO_EXT_0001586 rdfs/label ["Reactome modification feature record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001587
(ccp/IAO_EXT_0001587 rdf/type owl/Class)
(ccp/IAO_EXT_0001587 rdfs/subClassOf ccp/IAO_EXT_0001512)
(ccp/IAO_EXT_0001587 rdfs/label ["Reactome fragment feature record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001588
(ccp/IAO_EXT_0001588 rdf/type owl/Class)
(ccp/IAO_EXT_0001588 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001588 rdfs/label ["Reactome record - xref field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001589
(ccp/IAO_EXT_0001589 rdf/type owl/Class)
(ccp/IAO_EXT_0001589 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0001589 rdfs/label ["Gene Ontology field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001591
(ccp/IAO_EXT_0001591 rdf/type owl/Class)
(ccp/IAO_EXT_0001591 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001591 rdfs/subClassOf ccp/IAO_EXT_0001592)
(ccp/IAO_EXT_0001591 rdfs/label ["Reactome record - PSI-MOD identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001592
(ccp/IAO_EXT_0001592 rdf/type owl/Class)
(ccp/IAO_EXT_0001592 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0001592 rdfs/label ["PSI-MOD identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001593
(ccp/IAO_EXT_0001593 rdf/type owl/Class)
(ccp/IAO_EXT_0001593 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001593 rdfs/subClassOf ccp/IAO_EXT_0000298)
(ccp/IAO_EXT_0001593 rdfs/label ["Reactome record - EMBL identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001594
(ccp/IAO_EXT_0001594 rdf/type owl/Class)
(ccp/IAO_EXT_0001594 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001594 rdfs/subClassOf ccp/IAO_EXT_0001595)
(ccp/IAO_EXT_0001594 rdfs/label ["Reactome record - miRBase identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001595
(ccp/IAO_EXT_0001595 rdf/type owl/Class)
(ccp/IAO_EXT_0001595 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0001595 rdfs/label ["miRBase identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001596
(ccp/IAO_EXT_0001596 rdf/type owl/Class)
(ccp/IAO_EXT_0001596 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001596 rdfs/subClassOf ccp/IAO_EXT_0001589)
(ccp/IAO_EXT_0001596 rdfs/label ["Reactome record - Gene Ontology identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001597
(ccp/IAO_EXT_0001597 rdf/type owl/Class)
(ccp/IAO_EXT_0001597 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001597 rdfs/subClassOf ccp/IAO_EXT_0001598)
(ccp/IAO_EXT_0001597 rdfs/label ["Reactome record - NCBI nucleotide identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001598
(ccp/IAO_EXT_0001598 rdf/type owl/Class)
(ccp/IAO_EXT_0001598 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0001598 rdfs/label ["NCBI nucleotide identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001599
(ccp/IAO_EXT_0001599 rdf/type owl/Class)
(ccp/IAO_EXT_0001599 rdfs/subClassOf ccp/IAO_EXT_0000232)
(ccp/IAO_EXT_0001599 rdfs/subClassOf ccp/IAO_EXT_0001600)
(ccp/IAO_EXT_0001599 rdfs/label ["Reactome record - UniProt Isoform field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001600
(ccp/IAO_EXT_0001600 rdf/type owl/Class)
(ccp/IAO_EXT_0001600 rdfs/subClassOf ccp/IAO_EXT_0000020)
(ccp/IAO_EXT_0001600 rdfs/label ["Uniprot Isoform identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001601
(ccp/IAO_EXT_0001601 rdf/type owl/Class)
(ccp/IAO_EXT_0001601 rdfs/subClassOf ccp/IAO_EXT_0000019)
(ccp/IAO_EXT_0001601 rdfs/label ["GOA GAF v2.0 Annotation record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001602
(ccp/IAO_EXT_0001602 rdf/type owl/Class)
(ccp/IAO_EXT_0001602 rdfs/subClassOf ccp/IAO_EXT_0001601)
(ccp/IAO_EXT_0001602 obo/IAO_0000115 ["taxonomic identifier(s) For cardinality 1, the ID of the species encoding the gene product. For cardinality 2, to be used only in conjunction with terms that have the biological process term multi-organism process or the cellular component term host cell as an ancestor. The first taxon ID should be that of the organism encoding the gene or gene product, and the taxon ID after the pipe should be that of the other organism in the interaction. this field is mandatory, cardinality 1, 2; for cardinality 2 use a pipe to separate entries (e.g. taxon:1|taxon:1000) See the GO annotation conventions for more information on multi-organism terms."])
(ccp/IAO_EXT_0001602 rdfs/label ["GOA GAF v2.0 Annotation record - interacting taxon identifier taxon field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001603
(ccp/IAO_EXT_0001603 rdf/type owl/Class)
(ccp/IAO_EXT_0001603 rdfs/subClassOf ccp/IAO_EXT_0000197)
(ccp/IAO_EXT_0001603 rdfs/label ["EMBL record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001604
(ccp/IAO_EXT_0001604 rdf/type owl/Class)
(ccp/IAO_EXT_0001604 rdfs/subClassOf ccp/IAO_EXT_0001603)
(ccp/IAO_EXT_0001604 rdfs/label ["EMBL date record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001605
(ccp/IAO_EXT_0001605 rdf/type owl/Class)
(ccp/IAO_EXT_0001605 rdfs/subClassOf ccp/IAO_EXT_0001604)
(ccp/IAO_EXT_0001605 rdfs/label ["EMBL date record - date field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001606
(ccp/IAO_EXT_0001606 rdf/type owl/Class)
(ccp/IAO_EXT_0001606 rdfs/subClassOf ccp/IAO_EXT_0001604)
(ccp/IAO_EXT_0001606 rdfs/label ["EMBL date record - created or last updated field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001607
(ccp/IAO_EXT_0001607 rdf/type owl/Class)
(ccp/IAO_EXT_0001607 rdfs/subClassOf ccp/IAO_EXT_0001604)
(ccp/IAO_EXT_0001607 rdfs/label ["EMBL date record - release field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001608
(ccp/IAO_EXT_0001608 rdf/type owl/Class)
(ccp/IAO_EXT_0001608 rdfs/subClassOf ccp/IAO_EXT_0001604)
(ccp/IAO_EXT_0001608 rdfs/label ["EMBL date record - version field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001609
(ccp/IAO_EXT_0001609 rdf/type owl/Class)
(ccp/IAO_EXT_0001609 rdfs/subClassOf ccp/IAO_EXT_0000000)
(ccp/IAO_EXT_0001609 rdfs/label ["EMBL record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001610
(ccp/IAO_EXT_0001610 rdf/type owl/Class)
(ccp/IAO_EXT_0001610 rdfs/subClassOf ccp/IAO_EXT_0001609)
(ccp/IAO_EXT_0001610 rdfs/label ["EMBL sequence database file record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001611
(ccp/IAO_EXT_0001611 rdf/type owl/Class)
(ccp/IAO_EXT_0001611 rdfs/subClassOf ccp/IAO_EXT_0001609)
(ccp/IAO_EXT_0001611 rdfs/label ["EMBL sequence database file record component" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001612
(ccp/IAO_EXT_0001612 rdf/type owl/Class)
(ccp/IAO_EXT_0001612 rdfs/subClassOf ccp/IAO_EXT_0001611)
(ccp/IAO_EXT_0001612 rdfs/label ["EMBL date record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001613
(ccp/IAO_EXT_0001613 rdf/type owl/Class)
(ccp/IAO_EXT_0001613 rdfs/subClassOf ccp/IAO_EXT_0001611)
(ccp/IAO_EXT_0001613 rdfs/label ["EMBL reference citation record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001614
(ccp/IAO_EXT_0001614 rdf/type owl/Class)
(ccp/IAO_EXT_0001614 rdfs/subClassOf ccp/IAO_EXT_0001611)
(ccp/IAO_EXT_0001614 rdfs/label ["EMBL sequence feature location record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001615
(ccp/IAO_EXT_0001615 rdf/type owl/Class)
(ccp/IAO_EXT_0001615 rdfs/subClassOf ccp/IAO_EXT_0001611)
(ccp/IAO_EXT_0001615 rdfs/label ["EMBL assembly information record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001616
(ccp/IAO_EXT_0001616 rdf/type owl/Class)
(ccp/IAO_EXT_0001616 rdfs/subClassOf ccp/IAO_EXT_0001603)
(ccp/IAO_EXT_0001616 rdfs/label ["EMBL assembly information record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001617
(ccp/IAO_EXT_0001617 rdf/type owl/Class)
(ccp/IAO_EXT_0001617 rdfs/subClassOf ccp/IAO_EXT_0001603)
(ccp/IAO_EXT_0001617 rdfs/label ["EMBL reference citation record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001618
(ccp/IAO_EXT_0001618 rdf/type owl/Class)
(ccp/IAO_EXT_0001618 rdfs/subClassOf ccp/IAO_EXT_0001603)
(ccp/IAO_EXT_0001618 rdfs/label ["EMBL sequence feature location field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001619
(ccp/IAO_EXT_0001619 rdf/type owl/Class)
(ccp/IAO_EXT_0001619 rdfs/subClassOf ccp/IAO_EXT_0001616)
(ccp/IAO_EXT_0001619 rdfs/label ["EMBL assembly info record - local span field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001620
(ccp/IAO_EXT_0001620 rdf/type owl/Class)
(ccp/IAO_EXT_0001620 rdfs/subClassOf ccp/IAO_EXT_0001616)
(ccp/IAO_EXT_0001620 rdfs/label ["EMBL assembly info record - primary identifier field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001621
(ccp/IAO_EXT_0001621 rdf/type owl/Class)
(ccp/IAO_EXT_0001621 rdfs/subClassOf ccp/IAO_EXT_0001616)
(ccp/IAO_EXT_0001621 rdfs/label ["EMBL assembly info record - primary span field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001622
(ccp/IAO_EXT_0001622 rdf/type owl/Class)
(ccp/IAO_EXT_0001622 rdfs/subClassOf ccp/IAO_EXT_0001616)
(ccp/IAO_EXT_0001622 rdfs/label ["EMBL assembly info record - originates from complementary span field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001623
(ccp/IAO_EXT_0001623 rdf/type owl/Class)
(ccp/IAO_EXT_0001623 rdfs/subClassOf ccp/IAO_EXT_0001617)
(ccp/IAO_EXT_0001623 rdfs/label ["EMBL reference citation record - reference number field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001624
(ccp/IAO_EXT_0001624 rdf/type owl/Class)
(ccp/IAO_EXT_0001624 rdfs/subClassOf ccp/IAO_EXT_0001617)
(ccp/IAO_EXT_0001624 rdfs/label ["EMBL reference citation record - reference comment field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001625
(ccp/IAO_EXT_0001625 rdf/type owl/Class)
(ccp/IAO_EXT_0001625 rdfs/subClassOf ccp/IAO_EXT_0001617)
(ccp/IAO_EXT_0001625 rdfs/label ["EMBL reference citation record - reference positions field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001626
(ccp/IAO_EXT_0001626 rdf/type owl/Class)
(ccp/IAO_EXT_0001626 rdfs/subClassOf ccp/IAO_EXT_0001617)
(ccp/IAO_EXT_0001626 rdfs/label ["EMBL reference citation record - reference cross-references field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001627
(ccp/IAO_EXT_0001627 rdf/type owl/Class)
(ccp/IAO_EXT_0001627 rdfs/subClassOf ccp/IAO_EXT_0001617)
(ccp/IAO_EXT_0001627 rdfs/label ["EMBL reference citation record - reference groups field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001628
(ccp/IAO_EXT_0001628 rdf/type owl/Class)
(ccp/IAO_EXT_0001628 rdfs/subClassOf ccp/IAO_EXT_0001617)
(ccp/IAO_EXT_0001628 rdfs/label ["EMBL reference citation record - reference authors field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001629
(ccp/IAO_EXT_0001629 rdf/type owl/Class)
(ccp/IAO_EXT_0001629 rdfs/subClassOf ccp/IAO_EXT_0001617)
(ccp/IAO_EXT_0001629 rdfs/label ["EMBL reference citation record - reference title field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001630
(ccp/IAO_EXT_0001630 rdf/type owl/Class)
(ccp/IAO_EXT_0001630 rdfs/subClassOf ccp/IAO_EXT_0001617)
(ccp/IAO_EXT_0001630 rdfs/label ["EMBL reference citation record - reference location field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001631
(ccp/IAO_EXT_0001631 rdf/type owl/Class)
(ccp/IAO_EXT_0001631 rdfs/subClassOf ccp/IAO_EXT_0001618)
(ccp/IAO_EXT_0001631 rdfs/label ["EMBL sequence feature location record - start offset field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001632
(ccp/IAO_EXT_0001632 rdf/type owl/Class)
(ccp/IAO_EXT_0001632 rdfs/subClassOf ccp/IAO_EXT_0001618)
(ccp/IAO_EXT_0001632 rdfs/label ["EMBL sequence feature location record - end offset field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001633
(ccp/IAO_EXT_0001633 rdf/type owl/Class)
(ccp/IAO_EXT_0001633 rdfs/subClassOf ccp/IAO_EXT_0000006)
(ccp/IAO_EXT_0001633 rdfs/label ["GOA GAF v2.0 Annotation record component" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001634
(ccp/IAO_EXT_0001634 rdf/type owl/Class)
(ccp/IAO_EXT_0001634 rdfs/subClassOf ccp/IAO_EXT_0001633)
(ccp/IAO_EXT_0001634 rdfs/label ["GOA GAF v2.0 annotation extension record" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001635
(ccp/IAO_EXT_0001635 rdf/type owl/Class)
(ccp/IAO_EXT_0001635 rdfs/subClassOf ccp/IAO_EXT_0000019)
(ccp/IAO_EXT_0001635 rdfs/label ["GOA GAF v2.0 annotation extension record field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001636
(ccp/IAO_EXT_0001636 rdf/type owl/Class)
(ccp/IAO_EXT_0001636 rdfs/subClassOf ccp/IAO_EXT_0001635)
(ccp/IAO_EXT_0001636 rdfs/label ["GOA annotation extension record - relation field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001637
(ccp/IAO_EXT_0001637 rdf/type owl/Class)
(ccp/IAO_EXT_0001637 rdfs/subClassOf ccp/IAO_EXT_0001635)
(ccp/IAO_EXT_0001637 rdfs/label ["GOA annotation extension record - ontology term field value" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001638
(ccp/IAO_EXT_0001638 rdf/type owl/Class)
(ccp/IAO_EXT_0001638 rdfs/subClassOf ccp/IAO_EXT_0000188)
(ccp/IAO_EXT_0001638 rdfs/subClassOf ccp/IAO_EXT_0000263)
(ccp/IAO_EXT_0001638 obo/IAO_0000599 ["REFSEQ_"])
(ccp/IAO_EXT_0001638 rdfs/label ["RefSeq protein identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001639
(ccp/IAO_EXT_0001639 rdf/type owl/Class)
(ccp/IAO_EXT_0001639 rdfs/subClassOf ccp/IAO_EXT_0000183)
(ccp/IAO_EXT_0001639 rdfs/label ["mRNA identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001640
(ccp/IAO_EXT_0001640 rdf/type owl/Class)
(ccp/IAO_EXT_0001640 rdfs/subClassOf ccp/IAO_EXT_0000182)
(ccp/IAO_EXT_0001640 rdfs/subClassOf ccp/IAO_EXT_0000263)
(ccp/IAO_EXT_0001640 obo/IAO_0000599 ["REFSEQ_"])
(ccp/IAO_EXT_0001640 rdfs/label ["RefSeq genomic identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001641
(ccp/IAO_EXT_0001641 rdf/type owl/Class)
(ccp/IAO_EXT_0001641 rdfs/subClassOf ccp/IAO_EXT_0000187)
(ccp/IAO_EXT_0001641 rdfs/subClassOf ccp/IAO_EXT_0000263)
(ccp/IAO_EXT_0001641 obo/IAO_0000599 ["REFSEQ_"])
(ccp/IAO_EXT_0001641 rdfs/label ["RefSeq RNA identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001642
(ccp/IAO_EXT_0001642 rdf/type owl/Class)
(ccp/IAO_EXT_0001642 rdfs/subClassOf ccp/IAO_EXT_0000263)
(ccp/IAO_EXT_0001642 rdfs/subClassOf ccp/IAO_EXT_0001639)
(ccp/IAO_EXT_0001642 obo/IAO_0000599 ["REFSEQ_"])
(ccp/IAO_EXT_0001642 rdfs/label ["RefSeq mRNA identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001643
(ccp/IAO_EXT_0001643 rdf/type owl/Class)
(ccp/IAO_EXT_0001643 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001643 obo/IAO_0000599 ["REACTOME_"])
(ccp/IAO_EXT_0001643 rdfs/label ["Reactome identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001644
(ccp/IAO_EXT_0001644 rdf/type owl/Class)
(ccp/IAO_EXT_0001644 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001644 obo/IAO_0000115 ["Unified Medical Language System"])
(ccp/IAO_EXT_0001644 obo/IAO_0000599 ["UMLS_"])
(ccp/IAO_EXT_0001644 rdfs/label ["UMLS identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001645
(ccp/IAO_EXT_0001645 rdf/type owl/Class)
(ccp/IAO_EXT_0001645 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001645 obo/IAO_0000115 ["Common Terminology Criteria for Adverse Events"])
(ccp/IAO_EXT_0001645 obo/IAO_0000599 ["NCI_CTCAE_"])
(ccp/IAO_EXT_0001645 rdfs/label ["NCI_CTCAE identifier" "en"])
(ccp/IAO_EXT_0001645 owl/versionInfo [""])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001646
(ccp/IAO_EXT_0001646 rdf/type owl/Class)
(ccp/IAO_EXT_0001646 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001646 obo/IAO_0000115 ["International Classification for Nursing Practice"])
(ccp/IAO_EXT_0001646 obo/IAO_0000599 ["ICNP_"])
(ccp/IAO_EXT_0001646 rdfs/label ["ICNP identifier" "en"])
(ccp/IAO_EXT_0001646 owl/versionInfo [""])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001647
(ccp/IAO_EXT_0001647 rdf/type owl/Class)
(ccp/IAO_EXT_0001647 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001647 obo/IAO_0000115 ["Biomedical Research Integrated Domain Group Model"])
(ccp/IAO_EXT_0001647 obo/IAO_0000599 ["NCI_BRIDG_"])
(ccp/IAO_EXT_0001647 rdfs/label ["NCI_BRIDG identifier" "en"])
(ccp/IAO_EXT_0001647 owl/versionInfo [""])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001648
(ccp/IAO_EXT_0001648 rdf/type owl/Class)
(ccp/IAO_EXT_0001648 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001648 obo/IAO_0000115 ["National Institute of Child Health and Human Development"])
(ccp/IAO_EXT_0001648 obo/IAO_0000599 ["NCI_NICHD_"])
(ccp/IAO_EXT_0001648 rdfs/label ["NCI_NICHD identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001649
(ccp/IAO_EXT_0001649 rdf/type owl/Class)
(ccp/IAO_EXT_0001649 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001649 obo/IAO_0000115 ["UMDNS: product category thesaurus"])
(ccp/IAO_EXT_0001649 obo/IAO_0000599 ["UMD_"])
(ccp/IAO_EXT_0001649 rdfs/label ["UMD identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001650
(ccp/IAO_EXT_0001650 rdf/type owl/Class)
(ccp/IAO_EXT_0001650 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001650 obo/IAO_0000115 ["Zebrafish Model Organism Database"])
(ccp/IAO_EXT_0001650 obo/IAO_0000599 ["NCI_ZFIN_"])
(ccp/IAO_EXT_0001650 rdfs/label ["NCI_ZFin identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001651
(ccp/IAO_EXT_0001651 rdf/type owl/Class)
(ccp/IAO_EXT_0001651 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001651 obo/IAO_0000115 ["Healthcare Common Procedure Coding System"])
(ccp/IAO_EXT_0001651 obo/IAO_0000599 ["HCPCS_"])
(ccp/IAO_EXT_0001651 rdfs/label ["HCPCS identifier" "en"])
(ccp/IAO_EXT_0001651 owl/versionInfo [""])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001652
(ccp/IAO_EXT_0001652 rdf/type owl/Class)
(ccp/IAO_EXT_0001652 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001652 obo/IAO_0000115 ["Prostate Imaging Reporting and Data System"])
(ccp/IAO_EXT_0001652 obo/IAO_0000599 ["NCI_PI_RADS_"])
(ccp/IAO_EXT_0001652 rdfs/label ["NCI_PI-RADS identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001653
(ccp/IAO_EXT_0001653 rdf/type owl/Class)
(ccp/IAO_EXT_0001653 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001653 obo/IAO_0000115 ["Neuronames Brain Hierarchy"])
(ccp/IAO_EXT_0001653 obo/IAO_0000599 ["NEU_"])
(ccp/IAO_EXT_0001653 rdfs/label ["NEU identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001654
(ccp/IAO_EXT_0001654 rdf/type owl/Class)
(ccp/IAO_EXT_0001654 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001654 obo/IAO_0000115 ["Physicians' Current Procedural Terminology"])
(ccp/IAO_EXT_0001654 obo/IAO_0000599 ["CPT_"])
(ccp/IAO_EXT_0001654 rdfs/label ["CPT identifier" "en"])
(ccp/IAO_EXT_0001654 owl/versionInfo [""])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001655
(ccp/IAO_EXT_0001655 rdf/type owl/Class)
(ccp/IAO_EXT_0001655 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001655 obo/IAO_0000115 ["Clinical Care Classification"])
(ccp/IAO_EXT_0001655 obo/IAO_0000599 ["CCC_"])
(ccp/IAO_EXT_0001655 rdfs/label ["CCC identifier" "en"])
(ccp/IAO_EXT_0001655 owl/versionInfo [""])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001656
(ccp/IAO_EXT_0001656 rdf/type owl/Class)
(ccp/IAO_EXT_0001656 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001656 obo/IAO_0000115 ["Code on Dental Procedures and Nomenclature 2017 (CDT 2017)"])
(ccp/IAO_EXT_0001656 obo/IAO_0000599 ["CDT_"])
(ccp/IAO_EXT_0001656 rdfs/label ["CDT identifier" "en"])
(ccp/IAO_EXT_0001656 owl/versionInfo [""])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001657
(ccp/IAO_EXT_0001657 rdf/type owl/Class)
(ccp/IAO_EXT_0001657 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001657 obo/IAO_0000115 ["NCI Health Level 7"])
(ccp/IAO_EXT_0001657 obo/IAO_0000599 ["NCI_NCI_HL7_"])
(ccp/IAO_EXT_0001657 rdfs/label ["NCI_NCI-HL7 identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001658
(ccp/IAO_EXT_0001658 rdf/type owl/Class)
(ccp/IAO_EXT_0001658 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001658 obo/IAO_0000115 ["Cancer Research Center of Hawaii Nutrition Terminology"])
(ccp/IAO_EXT_0001658 obo/IAO_0000599 ["NCI_CRCH_"])
(ccp/IAO_EXT_0001658 rdfs/label ["NCI_CRCH identifier" "en"])
(ccp/IAO_EXT_0001658 owl/versionInfo [""])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001659
(ccp/IAO_EXT_0001659 rdf/type owl/Class)
(ccp/IAO_EXT_0001659 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001659 obo/IAO_0000115 ["HL7 Vocabulary Version 3.0"])
(ccp/IAO_EXT_0001659 obo/IAO_0000599 ["HL7V3.0_"])
(ccp/IAO_EXT_0001659 rdfs/label ["HL7V3.0 identifier" "en"])
(ccp/IAO_EXT_0001659 owl/versionInfo [""])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001660
(ccp/IAO_EXT_0001660 rdf/type owl/Class)
(ccp/IAO_EXT_0001660 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001660 obo/IAO_0000115 ["Perioperative Nursing Data Set"])
(ccp/IAO_EXT_0001660 obo/IAO_0000599 ["PNDS_"])
(ccp/IAO_EXT_0001660 rdfs/label ["PNDS identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001661
(ccp/IAO_EXT_0001661 rdf/type owl/Class)
(ccp/IAO_EXT_0001661 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001661 obo/IAO_0000115 ["Source of Payment Typology"])
(ccp/IAO_EXT_0001661 obo/IAO_0000599 ["SOP_"])
(ccp/IAO_EXT_0001661 rdfs/label ["SOP identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001662
(ccp/IAO_EXT_0001662 rdf/type owl/Class)
(ccp/IAO_EXT_0001662 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001662 obo/IAO_0000115 ["Library of Congress Subject Headings"])
(ccp/IAO_EXT_0001662 obo/IAO_0000599 ["LCH_NW_"])
(ccp/IAO_EXT_0001662 rdfs/label ["LCH_NW identifier" "en"])
(ccp/IAO_EXT_0001662 owl/versionInfo [""])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001663
(ccp/IAO_EXT_0001663 rdf/type owl/Class)
(ccp/IAO_EXT_0001663 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001663 obo/IAO_0000115 ["Clinical Data Interchange Standards Consortium"])
(ccp/IAO_EXT_0001663 obo/IAO_0000599 ["NCI_CDISC_"])
(ccp/IAO_EXT_0001663 rdfs/label ["NCI_CDISC identifier" "en"])
(ccp/IAO_EXT_0001663 owl/versionInfo [""])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001664
(ccp/IAO_EXT_0001664 rdf/type owl/Class)
(ccp/IAO_EXT_0001664 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001664 obo/IAO_0000115 ["NCI Dictionary of Cancer Terms"])
(ccp/IAO_EXT_0001664 obo/IAO_0000599 ["NCI_NCI_GLOSS_"])
(ccp/IAO_EXT_0001664 rdfs/label ["NCI_NCI-GLOSS identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001665
(ccp/IAO_EXT_0001665 rdf/type owl/Class)
(ccp/IAO_EXT_0001665 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001665 obo/IAO_0000115 ["MEDCIN"])
(ccp/IAO_EXT_0001665 obo/IAO_0000599 ["MEDCIN_"])
(ccp/IAO_EXT_0001665 rdfs/label ["MEDCIN identifier" "en"])
(ccp/IAO_EXT_0001665 owl/versionInfo [""])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001666
(ccp/IAO_EXT_0001666 rdf/type owl/Class)
(ccp/IAO_EXT_0001666 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001666 obo/IAO_0000115 ["National Cancer Institute Nature Pathway Interaction Database"])
(ccp/IAO_EXT_0001666 obo/IAO_0000599 ["NCI_PID_"])
(ccp/IAO_EXT_0001666 rdfs/label ["NCI_PID identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001667
(ccp/IAO_EXT_0001667 rdf/type owl/Class)
(ccp/IAO_EXT_0001667 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001667 obo/IAO_0000115 ["Geopolitical Entities"])
(ccp/IAO_EXT_0001667 obo/IAO_0000599 ["NCI_GENC_"])
(ccp/IAO_EXT_0001667 rdfs/label ["NCI_GENC identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001668
(ccp/IAO_EXT_0001668 rdf/type owl/Class)
(ccp/IAO_EXT_0001668 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001668 obo/IAO_0000115 ["LOINC 2.15"])
(ccp/IAO_EXT_0001668 obo/IAO_0000599 ["LNC_"])
(ccp/IAO_EXT_0001668 rdfs/label ["LNC identifier" "en"])
(ccp/IAO_EXT_0001668 owl/versionInfo [""])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001669
(ccp/IAO_EXT_0001669 rdf/type owl/Class)
(ccp/IAO_EXT_0001669 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001669 obo/IAO_0000115 ["Cancer Therapy Evaluation Program - Simple Disease Classification"])
(ccp/IAO_EXT_0001669 obo/IAO_0000599 ["NCI_CTEP_SDC_"])
(ccp/IAO_EXT_0001669 rdfs/label ["NCI_CTEP-SDC identifier" "en"])
(ccp/IAO_EXT_0001669 owl/versionInfo [""])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001670
(ccp/IAO_EXT_0001670 rdf/type owl/Class)
(ccp/IAO_EXT_0001670 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001670 obo/IAO_0000115 ["ICD-10-PCS"])
(ccp/IAO_EXT_0001670 obo/IAO_0000599 ["ICD10PCS_"])
(ccp/IAO_EXT_0001670 rdfs/label ["ICD10PCS identifier" "en"])
(ccp/IAO_EXT_0001670 owl/versionInfo [""])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001671
(ccp/IAO_EXT_0001671 rdf/type owl/Class)
(ccp/IAO_EXT_0001671 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001671 obo/IAO_0000115 ["Manufacturers of Vaccines"])
(ccp/IAO_EXT_0001671 obo/IAO_0000599 ["MVX_"])
(ccp/IAO_EXT_0001671 rdfs/label ["MVX identifier" "en"])
(ccp/IAO_EXT_0001671 owl/versionInfo [""])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001672
(ccp/IAO_EXT_0001672 rdf/type owl/Class)
(ccp/IAO_EXT_0001672 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001672 obo/IAO_0000115 ["Jackson Laboratories Mouse Terminology"])
(ccp/IAO_EXT_0001672 obo/IAO_0000599 ["NCI_JAX_"])
(ccp/IAO_EXT_0001672 rdfs/label ["NCI_JAX identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001673
(ccp/IAO_EXT_0001673 rdf/type owl/Class)
(ccp/IAO_EXT_0001673 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001673 obo/IAO_0000115 ["NCI Developmental Therapeutics Program"])
(ccp/IAO_EXT_0001673 obo/IAO_0000599 ["NCI_DTP_"])
(ccp/IAO_EXT_0001673 rdfs/label ["NCI_DTP identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001674
(ccp/IAO_EXT_0001674 rdf/type owl/Class)
(ccp/IAO_EXT_0001674 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001674 obo/IAO_0000115 ["NCI Division of Cancer Prevention Program"])
(ccp/IAO_EXT_0001674 obo/IAO_0000599 ["NCI_DCP_"])
(ccp/IAO_EXT_0001674 rdfs/label ["NCI_DCP identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001675
(ccp/IAO_EXT_0001675 rdf/type owl/Class)
(ccp/IAO_EXT_0001675 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001675 obo/IAO_0000115 ["International Classification of Functioning"])
(ccp/IAO_EXT_0001675 obo/IAO_0000599 ["ICF_"])
(ccp/IAO_EXT_0001675 rdfs/label ["ICF identifier" "en"])
(ccp/IAO_EXT_0001675 owl/versionInfo [""])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001676
(ccp/IAO_EXT_0001676 rdf/type owl/Class)
(ccp/IAO_EXT_0001676 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001676 obo/IAO_0000115 ["BioCarta online maps of molecular pathways"])
(ccp/IAO_EXT_0001676 obo/IAO_0000599 ["NCI_BIOC_"])
(ccp/IAO_EXT_0001676 rdfs/label ["NCI_BioC identifier" "en"])
(ccp/IAO_EXT_0001676 owl/versionInfo [""])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001677
(ccp/IAO_EXT_0001677 rdf/type owl/Class)
(ccp/IAO_EXT_0001677 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001677 obo/IAO_0000115 ["Digital Imaging Communications in Medicine"])
(ccp/IAO_EXT_0001677 obo/IAO_0000599 ["NCI_DICOM_"])
(ccp/IAO_EXT_0001677 rdfs/label ["NCI_DICOM identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001678
(ccp/IAO_EXT_0001678 rdf/type owl/Class)
(ccp/IAO_EXT_0001678 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001678 obo/IAO_0000115 ["HCPCS Version of Current Procedural Terminology (CPT)"])
(ccp/IAO_EXT_0001678 obo/IAO_0000599 ["HCPT_"])
(ccp/IAO_EXT_0001678 rdfs/label ["HCPT identifier" "en"])
(ccp/IAO_EXT_0001678 owl/versionInfo [""])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001679
(ccp/IAO_EXT_0001679 rdf/type owl/Class)
(ccp/IAO_EXT_0001679 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001679 obo/IAO_0000115 ["Consumer Health Vocabulary"])
(ccp/IAO_EXT_0001679 obo/IAO_0000599 ["CHV_"])
(ccp/IAO_EXT_0001679 rdfs/label ["CHV identifier" "en"])
(ccp/IAO_EXT_0001679 owl/versionInfo [""])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001680
(ccp/IAO_EXT_0001680 rdf/type owl/Class)
(ccp/IAO_EXT_0001680 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001680 obo/IAO_0000115 ["RxNorm Vocabulary"])
(ccp/IAO_EXT_0001680 obo/IAO_0000599 ["RXNORM_"])
(ccp/IAO_EXT_0001680 rdfs/label ["RXNORM identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001681
(ccp/IAO_EXT_0001681 rdf/type owl/Class)
(ccp/IAO_EXT_0001681 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001681 obo/IAO_0000115 ["Unified Code for Units of Measure"])
(ccp/IAO_EXT_0001681 obo/IAO_0000599 ["NCI_UCUM_"])
(ccp/IAO_EXT_0001681 rdfs/label ["NCI_UCUM identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001682
(ccp/IAO_EXT_0001682 rdf/type owl/Class)
(ccp/IAO_EXT_0001682 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001682 obo/IAO_0000115 ["International Classification of Functioning"])
(ccp/IAO_EXT_0001682 obo/IAO_0000599 ["ICF_CY_"])
(ccp/IAO_EXT_0001682 rdfs/label ["ICF-CY identifier" "en"])
(ccp/IAO_EXT_0001682 owl/versionInfo [""])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001683
(ccp/IAO_EXT_0001683 rdf/type owl/Class)
(ccp/IAO_EXT_0001683 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001683 obo/IAO_0000115 ["Foundational Model of Anatomy Ontology"])
(ccp/IAO_EXT_0001683 obo/IAO_0000599 ["FMA_"])
(ccp/IAO_EXT_0001683 rdfs/label ["FMA identifier" "en"])
(ccp/IAO_EXT_0001683 owl/versionInfo [""])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001684
(ccp/IAO_EXT_0001684 rdf/type owl/Class)
(ccp/IAO_EXT_0001684 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001684 obo/IAO_0000115 ["U.S. Centers for Disease Control and Prevention"])
(ccp/IAO_EXT_0001684 obo/IAO_0000599 ["NCI_CDC_"])
(ccp/IAO_EXT_0001684 rdfs/label ["NCI_CDC identifier" "en"])
(ccp/IAO_EXT_0001684 owl/versionInfo [""])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001685
(ccp/IAO_EXT_0001685 rdf/type owl/Class)
(ccp/IAO_EXT_0001685 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001685 obo/IAO_0000115 ["National Council for Prescription Drug Programs"])
(ccp/IAO_EXT_0001685 obo/IAO_0000599 ["NCI_NCPDP_"])
(ccp/IAO_EXT_0001685 rdfs/label ["NCI_NCPDP identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001686
(ccp/IAO_EXT_0001686 rdf/type owl/Class)
(ccp/IAO_EXT_0001686 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001686 obo/IAO_0000115 ["National Uniform Claim Committee - Health Care Provider Taxonomy"])
(ccp/IAO_EXT_0001686 obo/IAO_0000599 ["NUCCPT_"])
(ccp/IAO_EXT_0001686 rdfs/label ["NUCCPT identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001687
(ccp/IAO_EXT_0001687 rdf/type owl/Class)
(ccp/IAO_EXT_0001687 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001687 obo/IAO_0000115 ["Content Archive Resource Exchange Lexicon"])
(ccp/IAO_EXT_0001687 obo/IAO_0000599 ["NCI_CARELEX_"])
(ccp/IAO_EXT_0001687 rdfs/label ["NCI_CareLex identifier" "en"])
(ccp/IAO_EXT_0001687 owl/versionInfo [""])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001688
(ccp/IAO_EXT_0001688 rdf/type owl/Class)
(ccp/IAO_EXT_0001688 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001688 obo/IAO_0000115 ["International Conference on Harmonization"])
(ccp/IAO_EXT_0001688 obo/IAO_0000599 ["NCI_ICH_"])
(ccp/IAO_EXT_0001688 rdfs/label ["NCI_ICH identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001689
(ccp/IAO_EXT_0001689 rdf/type owl/Class)
(ccp/IAO_EXT_0001689 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001689 obo/IAO_0000115 ["Nursing Interventions Classification (NIC)"])
(ccp/IAO_EXT_0001689 obo/IAO_0000599 ["NIC_"])
(ccp/IAO_EXT_0001689 rdfs/label ["NIC identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001690
(ccp/IAO_EXT_0001690 rdf/type owl/Class)
(ccp/IAO_EXT_0001690 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001690 obo/IAO_0000115 ["Global Alignment of Immunization Safety Assessment in pregnancy"])
(ccp/IAO_EXT_0001690 obo/IAO_0000599 ["NCI_GAIA_"])
(ccp/IAO_EXT_0001690 rdfs/label ["NCI_GAIA identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001691
(ccp/IAO_EXT_0001691 rdf/type owl/Class)
(ccp/IAO_EXT_0001691 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001691 obo/IAO_0000115 ["HCPCS Version of Current Dental Terminology (CDT)"])
(ccp/IAO_EXT_0001691 obo/IAO_0000599 ["HCDT_"])
(ccp/IAO_EXT_0001691 rdfs/label ["HCDT identifier" "en"])
(ccp/IAO_EXT_0001691 owl/versionInfo [""])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001692
(ccp/IAO_EXT_0001692 rdf/type owl/Class)
(ccp/IAO_EXT_0001692 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001692 obo/IAO_0000115 ["NCI Thesaurus"])
(ccp/IAO_EXT_0001692 obo/IAO_0000599 ["NCIT_"])
(ccp/IAO_EXT_0001692 rdfs/label ["NCI identifier" "en"])
(ccp/IAO_EXT_0001692 owl/versionInfo [""])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001693
(ccp/IAO_EXT_0001693 rdf/type owl/Class)
(ccp/IAO_EXT_0001693 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001693 obo/IAO_0000115 ["National Drug File"])
(ccp/IAO_EXT_0001693 obo/IAO_0000599 ["NDFRT_"])
(ccp/IAO_EXT_0001693 rdfs/label ["NDFRT identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001694
(ccp/IAO_EXT_0001694 rdf/type owl/Class)
(ccp/IAO_EXT_0001694 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001694 obo/IAO_0000115 ["Alternative Billing Concepts"])
(ccp/IAO_EXT_0001694 obo/IAO_0000599 ["ALT_"])
(ccp/IAO_EXT_0001694 rdfs/label ["ALT identifier" "en"])
(ccp/IAO_EXT_0001694 owl/versionInfo [""])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001695
(ccp/IAO_EXT_0001695 rdf/type owl/Class)
(ccp/IAO_EXT_0001695 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001695 obo/IAO_0000115 ["Metathesaurus HCPCS Hierarchical Terms"])
(ccp/IAO_EXT_0001695 obo/IAO_0000599 ["MTHHH_"])
(ccp/IAO_EXT_0001695 rdfs/label ["MTHHH identifier" "en"])
(ccp/IAO_EXT_0001695 owl/versionInfo [""])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001696
(ccp/IAO_EXT_0001696 rdf/type owl/Class)
(ccp/IAO_EXT_0001696 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001696 obo/IAO_0000115 ["Registry Nomenclature Information System"])
(ccp/IAO_EXT_0001696 obo/IAO_0000599 ["NCI_RENI_"])
(ccp/IAO_EXT_0001696 rdfs/label ["NCI_RENI identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001697
(ccp/IAO_EXT_0001697 rdf/type owl/Class)
(ccp/IAO_EXT_0001697 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001697 obo/IAO_0000115 ["HUGO Gene Nomenclature Committee"])
(ccp/IAO_EXT_0001697 obo/IAO_0000599 ["HGNC_"])
(ccp/IAO_EXT_0001697 rdfs/label ["HGNC identifier" "en"])
(ccp/IAO_EXT_0001697 owl/versionInfo [""])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001698
(ccp/IAO_EXT_0001698 rdf/type owl/Class)
(ccp/IAO_EXT_0001698 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001698 obo/IAO_0000115 ["U.S. Food and Drug Administration"])
(ccp/IAO_EXT_0001698 obo/IAO_0000599 ["NCI_FDA_"])
(ccp/IAO_EXT_0001698 rdfs/label ["NCI_FDA identifier" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001699
(ccp/IAO_EXT_0001699 rdf/type owl/Class)
(ccp/IAO_EXT_0001699 rdfs/subClassOf ccp/IAO_EXT_0000342)
(ccp/IAO_EXT_0001699 rdfs/label ["source-specific identifier of biological entity" "en"])
;; 
;; http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001713
(ccp/IAO_EXT_0001713 rdf/type owl/Class)
(ccp/IAO_EXT_0001713 rdfs/subClassOf ccp/IAO_EXT_0001699)
(ccp/IAO_EXT_0001713 obo/IAO_0000115 ["This is the general identifier type for any UniProt-specific identifier, i.e. this class encompasses the UniProt isoform id, UniProt entry name id, and the regular UniProt id."])
(ccp/IAO_EXT_0001713 rdfs/label ["UniProt KB identifier" "en"])
;; 
;; http://purl.obolibrary.org/obo/BFO_0000001
(obo/BFO_0000001 rdf/type owl/Class)
(obo/BFO_0000001 rdfs/subClassOf owl/Thing)
(obo/BFO_0000001 obo/BFO_0000179 ["entity"])
(obo/BFO_0000001 obo/BFO_0000180 ["Entity"])
(obo/BFO_0000001 obo/IAO_0000112 ["Julius Caesar" "en"])
(obo/BFO_0000001 obo/IAO_0000112 ["Verdi\u2019s Requiem" "en"])
(obo/BFO_0000001 obo/IAO_0000112 ["the Second World War" "en"])
(obo/BFO_0000001 obo/IAO_0000112 ["your body mass index" "en"])
(obo/BFO_0000001 obo/IAO_0000116 ["BFO 2 Reference: In all areas of empirical inquiry we encounter general terms of two sorts. First are general terms which refer to universals or types:animaltuberculosissurgical procedurediseaseSecond, are general terms used to refer to groups of entities which instantiate a given universal but do not correspond to the extension of any subuniversal of that universal because there is nothing intrinsic to the entities in question by virtue of which they \u2013 and only they \u2013 are counted as belonging to the given group. Examples are: animal purchased by the Emperortuberculosis diagnosed on a Wednesdaysurgical procedure performed on a patient from Stockholmperson identified as candidate for clinical trial ;;2056-555person who is signatory of Form 656-PPVpainting by Leonardo da VinciSuch terms, which represent what are called \u2018specializations\u2019 in [81" "en"])
(obo/BFO_0000001 obo/IAO_0000116 ["Entity doesn't have a closure axiom because the subclasses don't necessarily exhaust all possibilites. For example Werner Ceusters 'portions of reality' include 4 sorts, entities (as BFO construes them), universals, configurations, and relations. It is an open question as to whether entities as construed in BFO will at some point also include these other portions of reality. See, for example, 'How to track absolutely everything' at http://www.referent-tracking.com/_RTU/papers/CeustersICbookRevised.pdf" "en"])
(obo/BFO_0000001 obo/IAO_0000600 ["An entity is anything that exists or has existed or will exist. (axiom label in BFO2 Reference: [001-001])" "en"])
(obo/BFO_0000001 rdfs/isDefinedBy obo/bfo.owl)
(obo/BFO_0000001 rdfs/label ["entity" "en"])
(bnode/ccp-extensions_genid78 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid78 owl/annotatedSource obo/BFO_0000001)
(bnode/ccp-extensions_genid78 owl/annotatedProperty obo/IAO_0000116)
(bnode/ccp-extensions_genid78 owl/annotatedTarget ["Entity doesn't have a closure axiom because the subclasses don't necessarily exhaust all possibilites. For example Werner Ceusters 'portions of reality' include 4 sorts, entities (as BFO construes them), universals, configurations, and relations. It is an open question as to whether entities as construed in BFO will at some point also include these other portions of reality. See, for example, 'How to track absolutely everything' at http://www.referent-tracking.com/_RTU/papers/CeustersICbookRevised.pdf" "en"])
 ;(bnode/ccp-extensions_genid78 obo/IAO_0010000 obo/bfo/axiom/0000004)
(bnode/ccp-extensions_genid78 rdfs/comment ["per discussion with Barry Smith"])
;(bnode/ccp-extensions_genid78 rdfs/seeAlso <http://www.referent-tracking.com/_RTU/papers/CeustersICbookRevised.pdf)
(bnode/ccp-extensions_genid79 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid79 owl/annotatedSource obo/BFO_0000001)
(bnode/ccp-extensions_genid79 owl/annotatedProperty obo/IAO_0000600)
(bnode/ccp-extensions_genid79 owl/annotatedTarget ["An entity is anything that exists or has existed or will exist. (axiom label in BFO2 Reference: [001-001])" "en"])
 ;(bnode/ccp-extensions_genid79 obo/IAO_0010000 obo/bfo/axiom/001-001)
;; 
;; http://purl.obolibrary.org/obo/BFO_0000002
(obo/BFO_0000002 rdf/type owl/Class)
(obo/BFO_0000002 rdfs/subClassOf obo/BFO_0000001)
(obo/BFO_0000002 owl/disjointWith obo/BFO_0000003)
(obo/BFO_0000002 obo/BFO_0000179 ["continuant"])
(obo/BFO_0000002 obo/BFO_0000180 ["Continuant"])
(obo/BFO_0000002 obo/IAO_0000115 ["An entity that exists in full at any time in which it exists at all, persists through time while maintaining its identity and has no temporal parts." "en"])
(obo/BFO_0000002 obo/IAO_0000116 ["BFO 2 Reference: Continuant entities are entities which can be sliced to yield parts only along the spatial dimension, yielding for example the parts of your table which we call its legs, its top, its nails. \u2018My desk stretches from the window to the door. It has spatial parts, and can be sliced (in space) in two. With respect to time, however, a thing is a continuant.\u2019 [60, p. 240" "en"])
(obo/BFO_0000002 obo/IAO_0000116 ["Continuant doesn't have a closure axiom because the subclasses don't necessarily exhaust all possibilites. For example, in an expansion involving bringing in some of Ceuster's other portions of reality, questions are raised as to whether universals are continuants" "en"])
(obo/BFO_0000002 obo/IAO_0000600 ["A continuant is an entity that persists, endures, or continues to exist through time while maintaining its identity. (axiom label in BFO2 Reference: [008-002])" "en"])
(obo/BFO_0000002 obo/IAO_0000601 ["if b is a continuant and if, for some t, c has_continuant_part b at t, then c is a continuant. (axiom label in BFO2 Reference: [126-001])" "en"])
(obo/BFO_0000002 obo/IAO_0000601 ["if b is a continuant and if, for some t, cis continuant_part of b at t, then c is a continuant. (axiom label in BFO2 Reference: [009-002])" "en"])
(obo/BFO_0000002 obo/IAO_0000601 ["if b is a material entity, then there is some temporal interval (referred to below as a one-dimensional temporal region) during which b exists. (axiom label in BFO2 Reference: [011-002])" "en"])
(obo/BFO_0000002 obo/IAO_0000602 ["(forall (x y) (if (and (Continuant x) (exists (t) (continuantPartOfAt y x t))) (Continuant y))) // axiom label in BFO2 CLIF: [009-002] "])
(obo/BFO_0000002 obo/IAO_0000602 ["(forall (x y) (if (and (Continuant x) (exists (t) (hasContinuantPartOfAt y x t))) (Continuant y))) // axiom label in BFO2 CLIF: [126-001] "])
(obo/BFO_0000002 obo/IAO_0000602 ["(forall (x) (if (Continuant x) (Entity x))) // axiom label in BFO2 CLIF: [008-002] "])
(obo/BFO_0000002 obo/IAO_0000602 ["(forall (x) (if (Material Entity x) (exists (t) (and (TemporalRegion t) (existsAt x t))))) // axiom label in BFO2 CLIF: [011-002] "])
(obo/BFO_0000002 rdfs/isDefinedBy obo/bfo.owl)
(obo/BFO_0000002 rdfs/label ["continuant" "en"])
(bnode/ccp-extensions_genid80 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid80 owl/annotatedSource obo/BFO_0000002)
(bnode/ccp-extensions_genid80 owl/annotatedProperty obo/IAO_0000116)
(bnode/ccp-extensions_genid80 owl/annotatedTarget ["Continuant doesn't have a closure axiom because the subclasses don't necessarily exhaust all possibilites. For example, in an expansion involving bringing in some of Ceuster's other portions of reality, questions are raised as to whether universals are continuants" "en"])
 ;;(bnode/ccp-extensions_genid80 obo/IAO_0010000 obo/bfo/axiom/0000007)
(bnode/ccp-extensions_genid81 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid81 owl/annotatedSource obo/BFO_0000002)
(bnode/ccp-extensions_genid81 owl/annotatedProperty obo/IAO_0000600)
(bnode/ccp-extensions_genid81 owl/annotatedTarget ["A continuant is an entity that persists, endures, or continues to exist through time while maintaining its identity. (axiom label in BFO2 Reference: [008-002])" "en"])
 ;;(bnode/ccp-extensions_genid81 obo/IAO_0010000 obo/bfo/axiom/008-002)
(bnode/ccp-extensions_genid82 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid82 owl/annotatedSource obo/BFO_0000002)
(bnode/ccp-extensions_genid82 owl/annotatedProperty obo/IAO_0000601)
(bnode/ccp-extensions_genid82 owl/annotatedTarget ["if b is a continuant and if, for some t, c has_continuant_part b at t, then c is a continuant. (axiom label in BFO2 Reference: [126-001])" "en"])
 ;;(bnode/ccp-extensions_genid82 obo/IAO_0010000 obo/bfo/axiom/126-001)
(bnode/ccp-extensions_genid83 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid83 owl/annotatedSource obo/BFO_0000002)
(bnode/ccp-extensions_genid83 owl/annotatedProperty obo/IAO_0000601)
(bnode/ccp-extensions_genid83 owl/annotatedTarget ["if b is a continuant and if, for some t, cis continuant_part of b at t, then c is a continuant. (axiom label in BFO2 Reference: [009-002])" "en"])
 ;;(bnode/ccp-extensions_genid83 obo/IAO_0010000 obo/bfo/axiom/009-002)
(bnode/ccp-extensions_genid84 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid84 owl/annotatedSource obo/BFO_0000002)
(bnode/ccp-extensions_genid84 owl/annotatedProperty obo/IAO_0000601)
(bnode/ccp-extensions_genid84 owl/annotatedTarget ["if b is a material entity, then there is some temporal interval (referred to below as a one-dimensional temporal region) during which b exists. (axiom label in BFO2 Reference: [011-002])" "en"])
 ;;(bnode/ccp-extensions_genid84 obo/IAO_0010000 obo/bfo/axiom/011-002)
(bnode/ccp-extensions_genid85 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid85 owl/annotatedSource obo/BFO_0000002)
(bnode/ccp-extensions_genid85 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid85 owl/annotatedTarget ["(forall (x y) (if (and (Continuant x) (exists (t) (continuantPartOfAt y x t))) (Continuant y))) // axiom label in BFO2 CLIF: [009-002] "])
 ;;(bnode/ccp-extensions_genid85 obo/IAO_0010000 obo/bfo/axiom/009-002)
(bnode/ccp-extensions_genid86 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid86 owl/annotatedSource obo/BFO_0000002)
(bnode/ccp-extensions_genid86 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid86 owl/annotatedTarget ["(forall (x y) (if (and (Continuant x) (exists (t) (hasContinuantPartOfAt y x t))) (Continuant y))) // axiom label in BFO2 CLIF: [126-001] "])
 ;;(bnode/ccp-extensions_genid86 obo/IAO_0010000 obo/bfo/axiom/126-001)
(bnode/ccp-extensions_genid87 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid87 owl/annotatedSource obo/BFO_0000002)
(bnode/ccp-extensions_genid87 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid87 owl/annotatedTarget ["(forall (x) (if (Continuant x) (Entity x))) // axiom label in BFO2 CLIF: [008-002] "])
 ;;(bnode/ccp-extensions_genid87 obo/IAO_0010000 obo/bfo/axiom/008-002)
(bnode/ccp-extensions_genid88 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid88 owl/annotatedSource obo/BFO_0000002)
(bnode/ccp-extensions_genid88 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid88 owl/annotatedTarget ["(forall (x) (if (Material Entity x) (exists (t) (and (TemporalRegion t) (existsAt x t))))) // axiom label in BFO2 CLIF: [011-002] "])
 ;;(bnode/ccp-extensions_genid88 obo/IAO_0010000 obo/bfo/axiom/011-002)
;; 
;; http://purl.obolibrary.org/obo/BFO_0000003
(obo/BFO_0000003 rdf/type owl/Class)
(obo/BFO_0000003 rdfs/subClassOf obo/BFO_0000001)
(obo/BFO_0000003 obo/BFO_0000179 ["occurrent"])
(obo/BFO_0000003 obo/BFO_0000180 ["Occurrent"])
(obo/BFO_0000003 obo/IAO_0000115 ["An entity that has temporal parts and that happens, unfolds or develops through time." "en"])
(obo/BFO_0000003 obo/IAO_0000116 ["BFO 2 Reference: every occurrent that is not a temporal or spatiotemporal region is s-dependent on some independent continuant that is not a spatial region" "en"])
(obo/BFO_0000003 obo/IAO_0000116 ["BFO 2 Reference: s-dependence obtains between every process and its participants in the sense that, as a matter of necessity, this process could not have existed unless these or those participants existed also. A process may have a succession of participants at different phases of its unfolding. Thus there may be different players on the field at different times during the course of a football game; but the process which is the entire game s-depends_on all of these players nonetheless. Some temporal parts of this process will s-depend_on on only some of the players." "en"])
(obo/BFO_0000003 obo/IAO_0000116 ["Occurrent doesn't have a closure axiom because the subclasses don't necessarily exhaust all possibilites. An example would be the sum of a process and the process boundary of another process." "en"])
(obo/BFO_0000003 obo/IAO_0000116 ["Simons uses different terminology for relations of occurrents to regions: Denote the spatio-temporal location of a given occurrent e by 'spn[e]' and call this region its span. We may say an occurrent is at its span, in any larger region, and covers any smaller region. Now suppose we have fixed a frame of reference so that we can speak not merely of spatio-temporal but also of spatial regions (places) and temporal regions (times). The spread of an occurrent, (relative to a frame of reference) is the space it exactly occupies, and its spell is likewise the time it exactly occupies. We write 'spr[e]' and `spl[e]' respectively for the spread and spell of e, omitting mention of the frame."])
(obo/BFO_0000003 obo/IAO_0000600 ["An occurrent is an entity that unfolds itself in time or it is the instantaneous boundary of such an entity (for example a beginning or an ending) or it is a temporal or spatiotemporal region which such an entity occupies_temporal_region or occupies_spatiotemporal_region. (axiom label in BFO2 Reference: [077-002])" "en"])
(obo/BFO_0000003 obo/IAO_0000601 ["Every occurrent occupies_spatiotemporal_region some spatiotemporal region. (axiom label in BFO2 Reference: [108-001])" "en"])
(obo/BFO_0000003 obo/IAO_0000601 ["b is an occurrent entity iff b is an entity that has temporal parts. (axiom label in BFO2 Reference: [079-001])" "en"])
(obo/BFO_0000003 obo/IAO_0000602 ["(forall (x) (if (Occurrent x) (exists (r) (and (SpatioTemporalRegion r) (occupiesSpatioTemporalRegion x r))))) // axiom label in BFO2 CLIF: [108-001] "])
(obo/BFO_0000003 obo/IAO_0000602 ["(forall (x) (iff (Occurrent x) (and (Entity x) (exists (y) (temporalPartOf y x))))) // axiom label in BFO2 CLIF: [079-001] "])
(obo/BFO_0000003 rdfs/isDefinedBy obo/bfo.owl)
(obo/BFO_0000003 rdfs/label ["occurrent" "en"])
(bnode/ccp-extensions_genid89 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid89 owl/annotatedSource obo/BFO_0000003)
(bnode/ccp-extensions_genid89 owl/annotatedProperty obo/IAO_0000116)
(bnode/ccp-extensions_genid89 owl/annotatedTarget ["Occurrent doesn't have a closure axiom because the subclasses don't necessarily exhaust all possibilites. An example would be the sum of a process and the process boundary of another process." "en"])
 ;;(bnode/ccp-extensions_genid89 obo/IAO_0010000 obo/bfo/axiom/0000006)
(bnode/ccp-extensions_genid89 rdfs/comment ["per discussion with Barry Smith"])
(bnode/ccp-extensions_genid90 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid90 owl/annotatedSource obo/BFO_0000003)
(bnode/ccp-extensions_genid90 owl/annotatedProperty obo/IAO_0000116)
(bnode/ccp-extensions_genid90 owl/annotatedTarget ["Simons uses different terminology for relations of occurrents to regions: Denote the spatio-temporal location of a given occurrent e by 'spn[e]' and call this region its span. We may say an occurrent is at its span, in any larger region, and covers any smaller region. Now suppose we have fixed a frame of reference so that we can speak not merely of spatio-temporal but also of spatial regions (places) and temporal regions (times). The spread of an occurrent, (relative to a frame of reference) is the space it exactly occupies, and its spell is likewise the time it exactly occupies. We write 'spr[e]' and `spl[e]' respectively for the spread and spell of e, omitting mention of the frame."])
 ;;(bnode/ccp-extensions_genid90 obo/IAO_0010000 obo/bfo/axiom/0000012)
(bnode/ccp-extensions_genid91 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid91 owl/annotatedSource obo/BFO_0000003)
(bnode/ccp-extensions_genid91 owl/annotatedProperty obo/IAO_0000600)
(bnode/ccp-extensions_genid91 owl/annotatedTarget ["An occurrent is an entity that unfolds itself in time or it is the instantaneous boundary of such an entity (for example a beginning or an ending) or it is a temporal or spatiotemporal region which such an entity occupies_temporal_region or occupies_spatiotemporal_region. (axiom label in BFO2 Reference: [077-002])" "en"])
 ;;(bnode/ccp-extensions_genid91 obo/IAO_0010000 obo/bfo/axiom/077-002)
(bnode/ccp-extensions_genid92 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid92 owl/annotatedSource obo/BFO_0000003)
(bnode/ccp-extensions_genid92 owl/annotatedProperty obo/IAO_0000601)
(bnode/ccp-extensions_genid92 owl/annotatedTarget ["Every occurrent occupies_spatiotemporal_region some spatiotemporal region. (axiom label in BFO2 Reference: [108-001])" "en"])
 ;;(bnode/ccp-extensions_genid92 obo/IAO_0010000 obo/bfo/axiom/108-001)
(bnode/ccp-extensions_genid93 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid93 owl/annotatedSource obo/BFO_0000003)
(bnode/ccp-extensions_genid93 owl/annotatedProperty obo/IAO_0000601)
(bnode/ccp-extensions_genid93 owl/annotatedTarget ["b is an occurrent entity iff b is an entity that has temporal parts. (axiom label in BFO2 Reference: [079-001])" "en"])
 ;;(bnode/ccp-extensions_genid93 obo/IAO_0010000 obo/bfo/axiom/079-001)
(bnode/ccp-extensions_genid94 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid94 owl/annotatedSource obo/BFO_0000003)
(bnode/ccp-extensions_genid94 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid94 owl/annotatedTarget ["(forall (x) (if (Occurrent x) (exists (r) (and (SpatioTemporalRegion r) (occupiesSpatioTemporalRegion x r))))) // axiom label in BFO2 CLIF: [108-001] "])
 ;;(bnode/ccp-extensions_genid94 obo/IAO_0010000 obo/bfo/axiom/108-001)
(bnode/ccp-extensions_genid95 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid95 owl/annotatedSource obo/BFO_0000003)
(bnode/ccp-extensions_genid95 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid95 owl/annotatedTarget ["(forall (x) (iff (Occurrent x) (and (Entity x) (exists (y) (temporalPartOf y x))))) // axiom label in BFO2 CLIF: [079-001] "])
 ;;(bnode/ccp-extensions_genid95 obo/IAO_0010000 obo/bfo/axiom/079-001)
;; 
;; http://purl.obolibrary.org/obo/BFO_0000004
(obo/BFO_0000004 rdf/type owl/Class)
(obo/BFO_0000004 rdfs/subClassOf obo/BFO_0000002)
(obo/BFO_0000004 owl/disjointWith obo/BFO_0000020)
(obo/BFO_0000004 owl/disjointWith obo/BFO_0000031)
(obo/BFO_0000004 obo/BFO_0000179 ["ic"])
(obo/BFO_0000004 obo/BFO_0000180 ["IndependentContinuant"])
(obo/BFO_0000004 obo/IAO_0000112 ["a chair" "en"])
(obo/BFO_0000004 obo/IAO_0000112 ["a heart" "en"])
(obo/BFO_0000004 obo/IAO_0000112 ["a leg" "en"])
(obo/BFO_0000004 obo/IAO_0000112 ["a molecule" "en"])
(obo/BFO_0000004 obo/IAO_0000112 ["a spatial region" "en"])
(obo/BFO_0000004 obo/IAO_0000112 ["an atom" "en"])
(obo/BFO_0000004 obo/IAO_0000112 ["an orchestra." "en"])
(obo/BFO_0000004 obo/IAO_0000112 ["an organism" "en"])
(obo/BFO_0000004 obo/IAO_0000112 ["the bottom right portion of a human torso" "en"])
(obo/BFO_0000004 obo/IAO_0000112 ["the interior of your mouth" "en"])
(obo/BFO_0000004 obo/IAO_0000115 ["A continuant that is a bearer of quality and realizable entity entities, in which other entities inhere and which itself cannot inhere in anything." "en"])
(obo/BFO_0000004 obo/IAO_0000115 ["b is an independent continuant = Def. b is a continuant which is such that there is no c and no t such that b s-depends_on c at t. (axiom label in BFO2 Reference: [017-002])" "en"])
(obo/BFO_0000004 obo/IAO_0000601 ["For any independent continuant b and any time t there is some spatial region r such that b is located_in r at t. (axiom label in BFO2 Reference: [134-001])" "en"])
(obo/BFO_0000004 obo/IAO_0000601 ["For every independent continuant b and time t during the region of time spanned by its life, there are entities which s-depends_on b during t. (axiom label in BFO2 Reference: [018-002])" "en"])
(obo/BFO_0000004 obo/IAO_0000602 ["(forall (x t) (if (IndependentContinuant x) (exists (r) (and (SpatialRegion r) (locatedInAt x r t))))) // axiom label in BFO2 CLIF: [134-001] "])
(obo/BFO_0000004 obo/IAO_0000602 ["(forall (x t) (if (and (IndependentContinuant x) (existsAt x t)) (exists (y) (and (Entity y) (specificallyDependsOnAt y x t))))) // axiom label in BFO2 CLIF: [018-002] "])
(obo/BFO_0000004 obo/IAO_0000602 ["(iff (IndependentContinuant a) (and (Continuant a) (not (exists (b t) (specificallyDependsOnAt a b t))))) // axiom label in BFO2 CLIF: [017-002] "])
(obo/BFO_0000004 rdfs/isDefinedBy obo/bfo.owl)
(obo/BFO_0000004 rdfs/label ["independent continuant" "en"])
(bnode/ccp-extensions_genid100 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid100 owl/annotatedSource obo/BFO_0000004)
(bnode/ccp-extensions_genid100 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid100 owl/annotatedTarget ["(forall (x t) (if (and (IndependentContinuant x) (existsAt x t)) (exists (y) (and (Entity y) (specificallyDependsOnAt y x t))))) // axiom label in BFO2 CLIF: [018-002] "])
 ;;(bnode/ccp-extensions_genid100 obo/IAO_0010000 obo/bfo/axiom/018-002)
(bnode/ccp-extensions_genid101 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid101 owl/annotatedSource obo/BFO_0000004)
(bnode/ccp-extensions_genid101 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid101 owl/annotatedTarget ["(iff (IndependentContinuant a) (and (Continuant a) (not (exists (b t) (specificallyDependsOnAt a b t))))) // axiom label in BFO2 CLIF: [017-002] "])
 ;;(bnode/ccp-extensions_genid101 obo/IAO_0010000 obo/bfo/axiom/017-002)
(bnode/ccp-extensions_genid96 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid96 owl/annotatedSource obo/BFO_0000004)
(bnode/ccp-extensions_genid96 owl/annotatedProperty obo/IAO_0000115)
(bnode/ccp-extensions_genid96 owl/annotatedTarget ["b is an independent continuant = Def. b is a continuant which is such that there is no c and no t such that b s-depends_on c at t. (axiom label in BFO2 Reference: [017-002])" "en"])
 ;;(bnode/ccp-extensions_genid96 obo/IAO_0010000 obo/bfo/axiom/017-002)
(bnode/ccp-extensions_genid97 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid97 owl/annotatedSource obo/BFO_0000004)
(bnode/ccp-extensions_genid97 owl/annotatedProperty obo/IAO_0000601)
(bnode/ccp-extensions_genid97 owl/annotatedTarget ["For any independent continuant b and any time t there is some spatial region r such that b is located_in r at t. (axiom label in BFO2 Reference: [134-001])" "en"])
 ;;(bnode/ccp-extensions_genid97 obo/IAO_0010000 obo/bfo/axiom/134-001)
(bnode/ccp-extensions_genid98 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid98 owl/annotatedSource obo/BFO_0000004)
(bnode/ccp-extensions_genid98 owl/annotatedProperty obo/IAO_0000601)
(bnode/ccp-extensions_genid98 owl/annotatedTarget ["For every independent continuant b and time t during the region of time spanned by its life, there are entities which s-depends_on b during t. (axiom label in BFO2 Reference: [018-002])" "en"])
 ;;(bnode/ccp-extensions_genid98 obo/IAO_0010000 obo/bfo/axiom/018-002)
(bnode/ccp-extensions_genid99 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid99 owl/annotatedSource obo/BFO_0000004)
(bnode/ccp-extensions_genid99 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid99 owl/annotatedTarget ["(forall (x t) (if (IndependentContinuant x) (exists (r) (and (SpatialRegion r) (locatedInAt x r t))))) // axiom label in BFO2 CLIF: [134-001] "])
 ;;(bnode/ccp-extensions_genid99 obo/IAO_0010000 obo/bfo/axiom/134-001)
;; 
;; http://purl.obolibrary.org/obo/BFO_0000005
(obo/BFO_0000005 rdf/type owl/Class)
(obo/BFO_0000005 rdfs/subClassOf oboInOwl/ObsoleteClass)
(obo/BFO_0000005 obo/IAO_0000115 ["A continuant  that is either dependent on one or other independent continuant  bearers or inheres in or is borne by other entities." "en"])
(obo/BFO_0000005 rdfs/label ["obsolete dependent continuant" "en"])
(obo/BFO_0000005 owl/deprecated true)
;; 
;; http://purl.obolibrary.org/obo/BFO_0000006
(obo/BFO_0000006 rdf/type owl/Class)
(obo/BFO_0000006 rdfs/subClassOf obo/BFO_0000141)
(obo/BFO_0000006 owl/disjointWith obo/BFO_0000029)
(obo/BFO_0000006 owl/disjointWith obo/BFO_0000140)
(obo/BFO_0000006 obo/BFO_0000179 ["s-region"])
(obo/BFO_0000006 obo/BFO_0000180 ["SpatialRegion"])
(obo/BFO_0000006 obo/IAO_0000116 ["BFO 2 Reference: Spatial regions do not participate in processes." "en"])
(obo/BFO_0000006 obo/IAO_0000116 ["Spatial region doesn't have a closure axiom because the subclasses don't exhaust all possibilites. An example would be the union of a spatial point and a spatial line that doesn't overlap the point, or two spatial lines that intersect at a single point. In both cases the resultant spatial region is neither 0-dimensional, 1-dimensional, 2-dimensional, or 3-dimensional." "en"])
(obo/BFO_0000006 obo/IAO_0000600 ["A spatial region is a continuant entity that is a continuant_part_of spaceR as defined relative to some frame R. (axiom label in BFO2 Reference: [035-001])" "en"])
(obo/BFO_0000006 obo/IAO_0000601 ["All continuant parts of spatial regions are spatial regions.\u00A0(axiom label in BFO2 Reference: [036-001])" "en"])
(obo/BFO_0000006 obo/IAO_0000602 ["(forall (x y t) (if (and (SpatialRegion x) (continuantPartOfAt y x t)) (SpatialRegion y))) // axiom label in BFO2 CLIF: [036-001] "])
(obo/BFO_0000006 obo/IAO_0000602 ["(forall (x) (if (SpatialRegion x) (Continuant x))) // axiom label in BFO2 CLIF: [035-001] "])
(obo/BFO_0000006 rdfs/isDefinedBy obo/bfo.owl)
(obo/BFO_0000006 rdfs/label ["spatial region" "en"])
(bnode/ccp-extensions_genid102 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid102 owl/annotatedSource obo/BFO_0000006)
(bnode/ccp-extensions_genid102 owl/annotatedProperty obo/IAO_0000116)
(bnode/ccp-extensions_genid102 owl/annotatedTarget ["Spatial region doesn't have a closure axiom because the subclasses don't exhaust all possibilites. An example would be the union of a spatial point and a spatial line that doesn't overlap the point, or two spatial lines that intersect at a single point. In both cases the resultant spatial region is neither 0-dimensional, 1-dimensional, 2-dimensional, or 3-dimensional." "en"])
 ;;(bnode/ccp-extensions_genid102 obo/IAO_0010000 obo/bfo/axiom/0000002)
(bnode/ccp-extensions_genid102 rdfs/comment ["per discussion with Barry Smith"])
(bnode/ccp-extensions_genid103 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid103 owl/annotatedSource obo/BFO_0000006)
(bnode/ccp-extensions_genid103 owl/annotatedProperty obo/IAO_0000600)
(bnode/ccp-extensions_genid103 owl/annotatedTarget ["A spatial region is a continuant entity that is a continuant_part_of spaceR as defined relative to some frame R. (axiom label in BFO2 Reference: [035-001])" "en"])
 ;;(bnode/ccp-extensions_genid103 obo/IAO_0010000 obo/bfo/axiom/035-001)
(bnode/ccp-extensions_genid104 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid104 owl/annotatedSource obo/BFO_0000006)
(bnode/ccp-extensions_genid104 owl/annotatedProperty obo/IAO_0000601)
(bnode/ccp-extensions_genid104 owl/annotatedTarget ["All continuant parts of spatial regions are spatial regions.\u00A0(axiom label in BFO2 Reference: [036-001])" "en"])
 ;;(bnode/ccp-extensions_genid104 obo/IAO_0010000 obo/bfo/axiom/036-001)
(bnode/ccp-extensions_genid105 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid105 owl/annotatedSource obo/BFO_0000006)
(bnode/ccp-extensions_genid105 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid105 owl/annotatedTarget ["(forall (x y t) (if (and (SpatialRegion x) (continuantPartOfAt y x t)) (SpatialRegion y))) // axiom label in BFO2 CLIF: [036-001] "])
 ;;(bnode/ccp-extensions_genid105 obo/IAO_0010000 obo/bfo/axiom/036-001)
(bnode/ccp-extensions_genid106 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid106 owl/annotatedSource obo/BFO_0000006)
(bnode/ccp-extensions_genid106 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid106 owl/annotatedTarget ["(forall (x) (if (SpatialRegion x) (Continuant x))) // axiom label in BFO2 CLIF: [035-001] "])
 ;;(bnode/ccp-extensions_genid106 obo/IAO_0010000 obo/bfo/axiom/035-001)
;; 
;; http://purl.obolibrary.org/obo/BFO_0000008
(obo/BFO_0000008 rdf/type owl/Class)
(obo/BFO_0000008 rdfs/subClassOf obo/BFO_0000003)
(obo/BFO_0000008 owl/disjointWith obo/BFO_0000011)
(obo/BFO_0000008 owl/disjointWith obo/BFO_0000015)
(obo/BFO_0000008 owl/disjointWith obo/BFO_0000035)
(obo/BFO_0000008 obo/BFO_0000179 ["t-region"])
(obo/BFO_0000008 obo/BFO_0000180 ["TemporalRegion"])
(obo/BFO_0000008 obo/IAO_0000116 ["Temporal region doesn't have a closure axiom because the subclasses don't exhaust all possibilites. An example would be the mereological sum of a temporal instant and a temporal interval that doesn't overlap the instant. In this case the resultant temporal region is neither 0-dimensional nor 1-dimensional" "en"])
(obo/BFO_0000008 obo/IAO_0000600 ["A temporal region is an occurrent entity that is part of time as defined relative to some reference frame. (axiom label in BFO2 Reference: [100-001])" "en"])
(obo/BFO_0000008 obo/IAO_0000601 ["All parts of temporal regions are temporal regions.\u00A0(axiom label in BFO2 Reference: [101-001])" "en"])
(obo/BFO_0000008 obo/IAO_0000601 ["Every temporal region t is such that t occupies_temporal_region t. (axiom label in BFO2 Reference: [119-002])" "en"])
(obo/BFO_0000008 obo/IAO_0000602 ["(forall (r) (if (TemporalRegion r) (occupiesTemporalRegion r r))) // axiom label in BFO2 CLIF: [119-002] "])
(obo/BFO_0000008 obo/IAO_0000602 ["(forall (x y) (if (and (TemporalRegion x) (occurrentPartOf y x)) (TemporalRegion y))) // axiom label in BFO2 CLIF: [101-001] "])
(obo/BFO_0000008 obo/IAO_0000602 ["(forall (x) (if (TemporalRegion x) (Occurrent x))) // axiom label in BFO2 CLIF: [100-001] "])
(obo/BFO_0000008 rdfs/isDefinedBy obo/bfo.owl)
(obo/BFO_0000008 rdfs/label ["temporal region" "en"])
(bnode/ccp-extensions_genid107 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid107 owl/annotatedSource obo/BFO_0000008)
(bnode/ccp-extensions_genid107 owl/annotatedProperty obo/IAO_0000116)
(bnode/ccp-extensions_genid107 owl/annotatedTarget ["Temporal region doesn't have a closure axiom because the subclasses don't exhaust all possibilites. An example would be the mereological sum of a temporal instant and a temporal interval that doesn't overlap the instant. In this case the resultant temporal region is neither 0-dimensional nor 1-dimensional" "en"])
 ;;(bnode/ccp-extensions_genid107 obo/IAO_0010000 obo/bfo/axiom/0000003)
(bnode/ccp-extensions_genid107 rdfs/comment ["per discussion with Barry Smith"])
(bnode/ccp-extensions_genid108 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid108 owl/annotatedSource obo/BFO_0000008)
(bnode/ccp-extensions_genid108 owl/annotatedProperty obo/IAO_0000600)
(bnode/ccp-extensions_genid108 owl/annotatedTarget ["A temporal region is an occurrent entity that is part of time as defined relative to some reference frame. (axiom label in BFO2 Reference: [100-001])" "en"])
 ;;(bnode/ccp-extensions_genid108 obo/IAO_0010000 obo/bfo/axiom/100-001)
(bnode/ccp-extensions_genid109 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid109 owl/annotatedSource obo/BFO_0000008)
(bnode/ccp-extensions_genid109 owl/annotatedProperty obo/IAO_0000601)
(bnode/ccp-extensions_genid109 owl/annotatedTarget ["All parts of temporal regions are temporal regions.\u00A0(axiom label in BFO2 Reference: [101-001])" "en"])
 ;;(bnode/ccp-extensions_genid109 obo/IAO_0010000 obo/bfo/axiom/101-001)
(bnode/ccp-extensions_genid110 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid110 owl/annotatedSource obo/BFO_0000008)
(bnode/ccp-extensions_genid110 owl/annotatedProperty obo/IAO_0000601)
(bnode/ccp-extensions_genid110 owl/annotatedTarget ["Every temporal region t is such that t occupies_temporal_region t. (axiom label in BFO2 Reference: [119-002])" "en"])
 ;;(bnode/ccp-extensions_genid110 obo/IAO_0010000 obo/bfo/axiom/119-002)
(bnode/ccp-extensions_genid111 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid111 owl/annotatedSource obo/BFO_0000008)
(bnode/ccp-extensions_genid111 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid111 owl/annotatedTarget ["(forall (r) (if (TemporalRegion r) (occupiesTemporalRegion r r))) // axiom label in BFO2 CLIF: [119-002] "])
 ;;(bnode/ccp-extensions_genid111 obo/IAO_0010000 obo/bfo/axiom/119-002)
(bnode/ccp-extensions_genid112 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid112 owl/annotatedSource obo/BFO_0000008)
(bnode/ccp-extensions_genid112 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid112 owl/annotatedTarget ["(forall (x y) (if (and (TemporalRegion x) (occurrentPartOf y x)) (TemporalRegion y))) // axiom label in BFO2 CLIF: [101-001] "])
 ;;(bnode/ccp-extensions_genid112 obo/IAO_0010000 obo/bfo/axiom/101-001)
(bnode/ccp-extensions_genid113 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid113 owl/annotatedSource obo/BFO_0000008)
(bnode/ccp-extensions_genid113 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid113 owl/annotatedTarget ["(forall (x) (if (TemporalRegion x) (Occurrent x))) // axiom label in BFO2 CLIF: [100-001] "])
 ;;(bnode/ccp-extensions_genid113 obo/IAO_0010000 obo/bfo/axiom/100-001)
;; 
;; http://purl.obolibrary.org/obo/BFO_0000009
(obo/BFO_0000009 rdf/type owl/Class)
(obo/BFO_0000009 rdfs/subClassOf obo/BFO_0000006)
(obo/BFO_0000009 owl/disjointWith obo/BFO_0000028)
(obo/BFO_0000009 obo/BFO_0000179 ["2d-s-region"])
(obo/BFO_0000009 obo/BFO_0000180 ["TwoDimensionalSpatialRegion"])
(obo/BFO_0000009 obo/IAO_0000112 ["an infinitely thin plane in space." "en"])
(obo/BFO_0000009 obo/IAO_0000112 ["the surface of a sphere-shaped part of space" "en"])
(obo/BFO_0000009 obo/IAO_0000600 ["A two-dimensional spatial region is a spatial region that is of two dimensions. (axiom label in BFO2 Reference: [039-001])" "en"])
(obo/BFO_0000009 obo/IAO_0000602 ["(forall (x) (if (TwoDimensionalSpatialRegion x) (SpatialRegion x))) // axiom label in BFO2 CLIF: [039-001] "])
(obo/BFO_0000009 rdfs/isDefinedBy obo/bfo.owl)
(obo/BFO_0000009 rdfs/label ["two-dimensional spatial region" "en"])
(bnode/ccp-extensions_genid114 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid114 owl/annotatedSource obo/BFO_0000009)
(bnode/ccp-extensions_genid114 owl/annotatedProperty obo/IAO_0000600)
(bnode/ccp-extensions_genid114 owl/annotatedTarget ["A two-dimensional spatial region is a spatial region that is of two dimensions. (axiom label in BFO2 Reference: [039-001])" "en"])
 ; ;(bnode/ccp-extensions_genid114 obo/IAO_0010000 obo/bfo/axiom/039-001)
(bnode/ccp-extensions_genid115 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid115 owl/annotatedSource obo/BFO_0000009)
(bnode/ccp-extensions_genid115 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid115 owl/annotatedTarget ["(forall (x) (if (TwoDimensionalSpatialRegion x) (SpatialRegion x))) // axiom label in BFO2 CLIF: [039-001] "])
 ; ;(bnode/ccp-extensions_genid115 obo/IAO_0010000 obo/bfo/axiom/039-001)
;; 
;; http://purl.obolibrary.org/obo/BFO_0000011
(obo/BFO_0000011 rdf/type owl/Class)
(obo/BFO_0000011 rdfs/subClassOf obo/BFO_0000003)
(obo/BFO_0000011 obo/BFO_0000179 ["st-region"])
(obo/BFO_0000011 obo/BFO_0000180 ["SpatiotemporalRegion"])
(obo/BFO_0000011 obo/IAO_0000112 ["the spatiotemporal region occupied by a human life" "en"])
(obo/BFO_0000011 obo/IAO_0000112 ["the spatiotemporal region occupied by a process of cellular meiosis." "en"])
(obo/BFO_0000011 obo/IAO_0000112 ["the spatiotemporal region occupied by the development of a cancer tumor" "en"])
(obo/BFO_0000011 obo/IAO_0000600 ["A spatiotemporal region is an occurrent entity that is part of spacetime. (axiom label in BFO2 Reference: [095-001])" "en"])
(obo/BFO_0000011 obo/IAO_0000601 ["All parts of spatiotemporal regions are spatiotemporal regions.\u00A0(axiom label in BFO2 Reference: [096-001])" "en"])
(obo/BFO_0000011 obo/IAO_0000601 ["Each spatiotemporal region at any time t projects_onto some spatial region at t. (axiom label in BFO2 Reference: [099-001])" "en"])
(obo/BFO_0000011 obo/IAO_0000601 ["Each spatiotemporal region projects_onto some temporal region. (axiom label in BFO2 Reference: [098-001])" "en"])
(obo/BFO_0000011 obo/IAO_0000601 ["Every spatiotemporal region occupies_spatiotemporal_region itself." "en"])
(obo/BFO_0000011 obo/IAO_0000601 ["Every spatiotemporal region s is such that s occupies_spatiotemporal_region s. (axiom label in BFO2 Reference: [107-002])" "en"])
(obo/BFO_0000011 obo/IAO_0000602 ["(forall (r) (if (SpatioTemporalRegion r) (occupiesSpatioTemporalRegion r r))) // axiom label in BFO2 CLIF: [107-002] "])
(obo/BFO_0000011 obo/IAO_0000602 ["(forall (x t) (if (SpatioTemporalRegion x) (exists (y) (and (SpatialRegion y) (spatiallyProjectsOntoAt x y t))))) // axiom label in BFO2 CLIF: [099-001] "])
(obo/BFO_0000011 obo/IAO_0000602 ["(forall (x y) (if (and (SpatioTemporalRegion x) (occurrentPartOf y x)) (SpatioTemporalRegion y))) // axiom label in BFO2 CLIF: [096-001] "])
(obo/BFO_0000011 obo/IAO_0000602 ["(forall (x) (if (SpatioTemporalRegion x) (Occurrent x))) // axiom label in BFO2 CLIF: [095-001] "])
(obo/BFO_0000011 obo/IAO_0000602 ["(forall (x) (if (SpatioTemporalRegion x) (exists (y) (and (TemporalRegion y) (temporallyProjectsOnto x y))))) // axiom label in BFO2 CLIF: [098-001] "])
(obo/BFO_0000011 rdfs/isDefinedBy obo/bfo.owl)
(obo/BFO_0000011 rdfs/label ["spatiotemporal region" "en"])
(bnode/ccp-extensions_genid116 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid116 owl/annotatedSource obo/BFO_0000011)
(bnode/ccp-extensions_genid116 owl/annotatedProperty obo/IAO_0000600)
(bnode/ccp-extensions_genid116 owl/annotatedTarget ["A spatiotemporal region is an occurrent entity that is part of spacetime. (axiom label in BFO2 Reference: [095-001])" "en"])
 ;(bnode/ccp-extensions_genid116 obo/IAO_0010000 obo/bfo/axiom/095-001)
(bnode/ccp-extensions_genid117 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid117 owl/annotatedSource obo/BFO_0000011)
(bnode/ccp-extensions_genid117 owl/annotatedProperty obo/IAO_0000601)
(bnode/ccp-extensions_genid117 owl/annotatedTarget ["All parts of spatiotemporal regions are spatiotemporal regions.\u00A0(axiom label in BFO2 Reference: [096-001])" "en"])
 ;(bnode/ccp-extensions_genid117 obo/IAO_0010000 obo/bfo/axiom/096-001)
(bnode/ccp-extensions_genid118 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid118 owl/annotatedSource obo/BFO_0000011)
(bnode/ccp-extensions_genid118 owl/annotatedProperty obo/IAO_0000601)
(bnode/ccp-extensions_genid118 owl/annotatedTarget ["Each spatiotemporal region at any time t projects_onto some spatial region at t. (axiom label in BFO2 Reference: [099-001])" "en"])
 ;(bnode/ccp-extensions_genid118 obo/IAO_0010000 obo/bfo/axiom/099-001)
(bnode/ccp-extensions_genid119 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid119 owl/annotatedSource obo/BFO_0000011)
(bnode/ccp-extensions_genid119 owl/annotatedProperty obo/IAO_0000601)
(bnode/ccp-extensions_genid119 owl/annotatedTarget ["Each spatiotemporal region projects_onto some temporal region. (axiom label in BFO2 Reference: [098-001])" "en"])
 ;(bnode/ccp-extensions_genid119 obo/IAO_0010000 obo/bfo/axiom/098-001)
(bnode/ccp-extensions_genid120 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid120 owl/annotatedSource obo/BFO_0000011)
(bnode/ccp-extensions_genid120 owl/annotatedProperty obo/IAO_0000601)
(bnode/ccp-extensions_genid120 owl/annotatedTarget ["Every spatiotemporal region s is such that s occupies_spatiotemporal_region s. (axiom label in BFO2 Reference: [107-002])" "en"])
 ;(bnode/ccp-extensions_genid120 obo/IAO_0010000 obo/bfo/axiom/107-002)
(bnode/ccp-extensions_genid121 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid121 owl/annotatedSource obo/BFO_0000011)
(bnode/ccp-extensions_genid121 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid121 owl/annotatedTarget ["(forall (r) (if (SpatioTemporalRegion r) (occupiesSpatioTemporalRegion r r))) // axiom label in BFO2 CLIF: [107-002] "])
 ;(bnode/ccp-extensions_genid121 obo/IAO_0010000 obo/bfo/axiom/107-002)
(bnode/ccp-extensions_genid122 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid122 owl/annotatedSource obo/BFO_0000011)
(bnode/ccp-extensions_genid122 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid122 owl/annotatedTarget ["(forall (x t) (if (SpatioTemporalRegion x) (exists (y) (and (SpatialRegion y) (spatiallyProjectsOntoAt x y t))))) // axiom label in BFO2 CLIF: [099-001] "])
 ;(bnode/ccp-extensions_genid122 obo/IAO_0010000 obo/bfo/axiom/099-001)
(bnode/ccp-extensions_genid123 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid123 owl/annotatedSource obo/BFO_0000011)
(bnode/ccp-extensions_genid123 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid123 owl/annotatedTarget ["(forall (x y) (if (and (SpatioTemporalRegion x) (occurrentPartOf y x)) (SpatioTemporalRegion y))) // axiom label in BFO2 CLIF: [096-001] "])
 ;(bnode/ccp-extensions_genid123 obo/IAO_0010000 obo/bfo/axiom/096-001)
(bnode/ccp-extensions_genid124 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid124 owl/annotatedSource obo/BFO_0000011)
(bnode/ccp-extensions_genid124 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid124 owl/annotatedTarget ["(forall (x) (if (SpatioTemporalRegion x) (Occurrent x))) // axiom label in BFO2 CLIF: [095-001] "])
 ;(bnode/ccp-extensions_genid124 obo/IAO_0010000 obo/bfo/axiom/095-001)
(bnode/ccp-extensions_genid125 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid125 owl/annotatedSource obo/BFO_0000011)
(bnode/ccp-extensions_genid125 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid125 owl/annotatedTarget ["(forall (x) (if (SpatioTemporalRegion x) (exists (y) (and (TemporalRegion y) (temporallyProjectsOnto x y))))) // axiom label in BFO2 CLIF: [098-001] "])
 ;(bnode/ccp-extensions_genid125 obo/IAO_0010000 obo/bfo/axiom/098-001)
;; 
;; http://purl.obolibrary.org/obo/BFO_0000015
(obo/BFO_0000015 rdf/type owl/Class)
(obo/BFO_0000015 rdfs/subClassOf obo/BFO_0000003)
(obo/BFO_0000015 obo/BFO_0000179 ["process"])
(obo/BFO_0000015 obo/BFO_0000180 ["Process"])
(obo/BFO_0000015 obo/IAO_0000112 ["a process of cell-division, \\ a beating of the heart" "en"])
(obo/BFO_0000015 obo/IAO_0000112 ["a process of meiosis" "en"])
(obo/BFO_0000015 obo/IAO_0000112 ["a process of sleeping" "en"])
(obo/BFO_0000015 obo/IAO_0000112 ["the course of a disease" "en"])
(obo/BFO_0000015 obo/IAO_0000112 ["the flight of a bird" "en"])
(obo/BFO_0000015 obo/IAO_0000112 ["the life of an organism" "en"])
(obo/BFO_0000015 obo/IAO_0000112 ["your process of aging." "en"])
(obo/BFO_0000015 obo/IAO_0000115 ["An occurrent that has temporal proper parts and for some time t, p s-depends_on some material entity at t." "en"])
(obo/BFO_0000015 obo/IAO_0000115 ["p is a process = Def. p is an occurrent that has temporal proper parts and for some time t, p s-depends_on some material entity at t. (axiom label in BFO2 Reference: [083-003])" "en"])
(obo/BFO_0000015 obo/IAO_0000116 ["BFO 2 Reference: The realm of occurrents is less pervasively marked by the presence of natural units than is the case in the realm of independent continuants. Thus there is here no counterpart of \u2018object\u2019. In BFO 1.0 \u2018process\u2019 served as such a counterpart. In BFO 2.0 \u2018process\u2019 is, rather, the occurrent counterpart of \u2018material entity\u2019. Those natural \u2013 as contrasted with engineered, which here means: deliberately executed \u2013 units which do exist in the realm of occurrents are typically either parasitic on the existence of natural units on the continuant side, or they are fiat in nature. Thus we can count lives; we can count football games; we can count chemical reactions performed in experiments or in chemical manufacturing. We cannot count the processes taking place, for instance, in an episode of insect mating behavior.Even where natural units are identifiable, for example cycles in a cyclical process such as the beating of a heart or an organism\u2019s sleep/wake cycle, the processes in question form a sequence with no discontinuities (temporal gaps) of the sort that we find for instance where billiard balls or zebrafish or planets are separated by clear spatial gaps. Lives of organisms are process units, but they too unfold in a continuous series from other, prior processes such as fertilization, and they unfold in turn in continuous series of post-life processes such as post-mortem decay. Clear examples of boundaries of processes are almost always of the fiat sort (midnight, a time of death as declared in an operating theater or on a death certificate, the initiation of a state of war)" "en"])
(obo/BFO_0000015 obo/IAO_0000602 ["(iff (Process a) (and (Occurrent a) (exists (b) (properTemporalPartOf b a)) (exists (c t) (and (MaterialEntity c) (specificallyDependsOnAt a c t))))) // axiom label in BFO2 CLIF: [083-003] "])
(obo/BFO_0000015 rdfs/isDefinedBy obo/bfo.owl)
(obo/BFO_0000015 rdfs/label ["process" "en"])
(bnode/ccp-extensions_genid126 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid126 owl/annotatedSource obo/BFO_0000015)
(bnode/ccp-extensions_genid126 owl/annotatedProperty obo/IAO_0000115)
(bnode/ccp-extensions_genid126 owl/annotatedTarget ["p is a process = Def. p is an occurrent that has temporal proper parts and for some time t, p s-depends_on some material entity at t. (axiom label in BFO2 Reference: [083-003])" "en"])
 ;(bnode/ccp-extensions_genid126 obo/IAO_0010000 obo/bfo/axiom/083-003)
(bnode/ccp-extensions_genid127 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid127 owl/annotatedSource obo/BFO_0000015)
(bnode/ccp-extensions_genid127 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid127 owl/annotatedTarget ["(iff (Process a) (and (Occurrent a) (exists (b) (properTemporalPartOf b a)) (exists (c t) (and (MaterialEntity c) (specificallyDependsOnAt a c t))))) // axiom label in BFO2 CLIF: [083-003] "])
 ;(bnode/ccp-extensions_genid127 obo/IAO_0010000 obo/bfo/axiom/083-003)
;; 
;; http://purl.obolibrary.org/obo/BFO_0000016
(obo/BFO_0000016 rdf/type owl/Class)
(obo/BFO_0000016 rdfs/subClassOf obo/BFO_0000017)
(obo/BFO_0000016 owl/disjointWith obo/BFO_0000023)
(obo/BFO_0000016 obo/BFO_0000179 ["disposition"])
(obo/BFO_0000016 obo/BFO_0000180 ["Disposition"])
(obo/BFO_0000016 obo/IAO_0000112 ["an atom of element X has the disposition to decay to an atom of element Y" "en"])
(obo/BFO_0000016 obo/IAO_0000112 ["certain people have a predisposition to colon cancer" "en"])
(obo/BFO_0000016 obo/IAO_0000112 ["children are innately disposed to categorize objects in certain ways." "en"])
(obo/BFO_0000016 obo/IAO_0000112 ["the cell wall is disposed to filter chemicals in endocytosis and exocytosis" "en"])
(obo/BFO_0000016 obo/IAO_0000116 ["BFO 2 Reference: Dispositions exist along a strength continuum. Weaker forms of disposition are realized in only a fraction of triggering cases. These forms occur in a significant number of cases of a similar type." "en"])
(obo/BFO_0000016 obo/IAO_0000600 ["b is a disposition means: b is a realizable entity & b\u2019s bearer is some material entity & b is such that if it ceases to exist, then its bearer is physically changed, & b\u2019s realization occurs when and because this bearer is in some special physical circumstances, & this realization occurs in virtue of the bearer\u2019s physical make-up. (axiom label in BFO2 Reference: [062-002])" "en"])
(obo/BFO_0000016 obo/IAO_0000601 ["If b is a realizable entity then for all t at which b exists, b s-depends_on some material entity at t. (axiom label in BFO2 Reference: [063-002])" "en"])
(obo/BFO_0000016 obo/IAO_0000602 ["(forall (x t) (if (and (RealizableEntity x) (existsAt x t)) (exists (y) (and (MaterialEntity y) (specificallyDepends x y t))))) // axiom label in BFO2 CLIF: [063-002] "])
(obo/BFO_0000016 obo/IAO_0000602 ["(forall (x) (if (Disposition x) (and (RealizableEntity x) (exists (y) (and (MaterialEntity y) (bearerOfAt x y t)))))) // axiom label in BFO2 CLIF: [062-002] "])
(obo/BFO_0000016 rdfs/isDefinedBy obo/bfo.owl)
(obo/BFO_0000016 rdfs/label ["disposition" "en"])
(bnode/ccp-extensions_genid128 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid128 owl/annotatedSource obo/BFO_0000016)
(bnode/ccp-extensions_genid128 owl/annotatedProperty obo/IAO_0000600)
(bnode/ccp-extensions_genid128 owl/annotatedTarget ["b is a disposition means: b is a realizable entity & b\u2019s bearer is some material entity & b is such that if it ceases to exist, then its bearer is physically changed, & b\u2019s realization occurs when and because this bearer is in some special physical circumstances, & this realization occurs in virtue of the bearer\u2019s physical make-up. (axiom label in BFO2 Reference: [062-002])" "en"])
 ;(bnode/ccp-extensions_genid128 obo/IAO_0010000 obo/bfo/axiom/062-002)
(bnode/ccp-extensions_genid129 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid129 owl/annotatedSource obo/BFO_0000016)
(bnode/ccp-extensions_genid129 owl/annotatedProperty obo/IAO_0000601)
(bnode/ccp-extensions_genid129 owl/annotatedTarget ["If b is a realizable entity then for all t at which b exists, b s-depends_on some material entity at t. (axiom label in BFO2 Reference: [063-002])" "en"])
 ;(bnode/ccp-extensions_genid129 obo/IAO_0010000 obo/bfo/axiom/063-002)
(bnode/ccp-extensions_genid130 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid130 owl/annotatedSource obo/BFO_0000016)
(bnode/ccp-extensions_genid130 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid130 owl/annotatedTarget ["(forall (x t) (if (and (RealizableEntity x) (existsAt x t)) (exists (y) (and (MaterialEntity y) (specificallyDepends x y t))))) // axiom label in BFO2 CLIF: [063-002] "])
 ;(bnode/ccp-extensions_genid130 obo/IAO_0010000 obo/bfo/axiom/063-002)
(bnode/ccp-extensions_genid131 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid131 owl/annotatedSource obo/BFO_0000016)
(bnode/ccp-extensions_genid131 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid131 owl/annotatedTarget ["(forall (x) (if (Disposition x) (and (RealizableEntity x) (exists (y) (and (MaterialEntity y) (bearerOfAt x y t)))))) // axiom label in BFO2 CLIF: [062-002] "])
 ;(bnode/ccp-extensions_genid131 obo/IAO_0010000 obo/bfo/axiom/062-002)
;; 
;; http://purl.obolibrary.org/obo/BFO_0000017
(obo/BFO_0000017 rdf/type owl/Class)
(obo/BFO_0000017 rdfs/subClassOf obo/BFO_0000020)
(obo/BFO_0000017 owl/disjointWith obo/BFO_0000019)
(obo/BFO_0000017 obo/BFO_0000179 ["realizable"])
(obo/BFO_0000017 obo/BFO_0000180 ["RealizableEntity"])
(obo/BFO_0000017 obo/IAO_0000112 ["the disposition of this piece of metal to conduct electricity." "en"])
(obo/BFO_0000017 obo/IAO_0000112 ["the disposition of your blood to coagulate" "en"])
(obo/BFO_0000017 obo/IAO_0000112 ["the function of your reproductive organs" "en"])
(obo/BFO_0000017 obo/IAO_0000112 ["the role of being a doctor" "en"])
(obo/BFO_0000017 obo/IAO_0000112 ["the role of this boundary to delineate where Utah and Colorado meet" "en"])
(obo/BFO_0000017 obo/IAO_0000115 ["A specifically dependent continuant  that inheres in continuant  entities and are not exhibited in full at every time in which it inheres in an entity or group of entities. The exhibition or actualization of a realizable entity is a particular manifestation, functioning or process that occurs under certain circumstances." "en"])
(obo/BFO_0000017 obo/IAO_0000600 ["To say that b is a realizable entity is to say that b is a specifically dependent continuant that inheres in some independent continuant which is not a spatial region and is of a type instances of which are realized in processes of a correlated type. (axiom label in BFO2 Reference: [058-002])" "en"])
(obo/BFO_0000017 obo/IAO_0000601 ["All realizable dependent continuants have independent continuants that are not spatial regions as their bearers. (axiom label in BFO2 Reference: [060-002])" "en"])
(obo/BFO_0000017 obo/IAO_0000602 ["(forall (x t) (if (RealizableEntity x) (exists (y) (and (IndependentContinuant y) (not (SpatialRegion y)) (bearerOfAt y x t))))) // axiom label in BFO2 CLIF: [060-002] "])
(obo/BFO_0000017 obo/IAO_0000602 ["(forall (x) (if (RealizableEntity x) (and (SpecificallyDependentContinuant x) (exists (y) (and (IndependentContinuant y) (not (SpatialRegion y)) (inheresIn x y)))))) // axiom label in BFO2 CLIF: [058-002] "])
(obo/BFO_0000017 rdfs/isDefinedBy obo/bfo.owl)
(obo/BFO_0000017 rdfs/label ["realizable entity" "en"])
(bnode/ccp-extensions_genid132 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid132 owl/annotatedSource obo/BFO_0000017)
(bnode/ccp-extensions_genid132 owl/annotatedProperty obo/IAO_0000600)
(bnode/ccp-extensions_genid132 owl/annotatedTarget ["To say that b is a realizable entity is to say that b is a specifically dependent continuant that inheres in some independent continuant which is not a spatial region and is of a type instances of which are realized in processes of a correlated type. (axiom label in BFO2 Reference: [058-002])" "en"])
 ;(bnode/ccp-extensions_genid132 obo/IAO_0010000 obo/bfo/axiom/058-002)
(bnode/ccp-extensions_genid133 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid133 owl/annotatedSource obo/BFO_0000017)
(bnode/ccp-extensions_genid133 owl/annotatedProperty obo/IAO_0000601)
(bnode/ccp-extensions_genid133 owl/annotatedTarget ["All realizable dependent continuants have independent continuants that are not spatial regions as their bearers. (axiom label in BFO2 Reference: [060-002])" "en"])
 ;(bnode/ccp-extensions_genid133 obo/IAO_0010000 obo/bfo/axiom/060-002)
(bnode/ccp-extensions_genid134 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid134 owl/annotatedSource obo/BFO_0000017)
(bnode/ccp-extensions_genid134 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid134 owl/annotatedTarget ["(forall (x t) (if (RealizableEntity x) (exists (y) (and (IndependentContinuant y) (not (SpatialRegion y)) (bearerOfAt y x t))))) // axiom label in BFO2 CLIF: [060-002] "])
 ;(bnode/ccp-extensions_genid134 obo/IAO_0010000 obo/bfo/axiom/060-002)
(bnode/ccp-extensions_genid135 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid135 owl/annotatedSource obo/BFO_0000017)
(bnode/ccp-extensions_genid135 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid135 owl/annotatedTarget ["(forall (x) (if (RealizableEntity x) (and (SpecificallyDependentContinuant x) (exists (y) (and (IndependentContinuant y) (not (SpatialRegion y)) (inheresIn x y)))))) // axiom label in BFO2 CLIF: [058-002] "])
 ;(bnode/ccp-extensions_genid135 obo/IAO_0010000 obo/bfo/axiom/058-002)
;; 
;; http://purl.obolibrary.org/obo/BFO_0000018
(obo/BFO_0000018 rdf/type owl/Class)
(obo/BFO_0000018 rdfs/subClassOf obo/BFO_0000006)
(obo/BFO_0000018 owl/disjointWith obo/BFO_0000028)
(obo/BFO_0000018 obo/BFO_0000179 ["0d-s-region"])
(obo/BFO_0000018 obo/BFO_0000180 ["ZeroDimensionalSpatialRegion"])
(obo/BFO_0000018 obo/IAO_0000600 ["A zero-dimensional spatial region is a point in space. (axiom label in BFO2 Reference: [037-001])" "en"])
(obo/BFO_0000018 obo/IAO_0000602 ["(forall (x) (if (ZeroDimensionalSpatialRegion x) (SpatialRegion x))) // axiom label in BFO2 CLIF: [037-001] "])
(obo/BFO_0000018 rdfs/isDefinedBy obo/bfo.owl)
(obo/BFO_0000018 rdfs/label ["zero-dimensional spatial region" "en"])
(bnode/ccp-extensions_genid136 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid136 owl/annotatedSource obo/BFO_0000018)
(bnode/ccp-extensions_genid136 owl/annotatedProperty obo/IAO_0000600)
(bnode/ccp-extensions_genid136 owl/annotatedTarget ["A zero-dimensional spatial region is a point in space. (axiom label in BFO2 Reference: [037-001])" "en"])
 ;(bnode/ccp-extensions_genid136 obo/IAO_0010000 obo/bfo/axiom/037-001)
(bnode/ccp-extensions_genid137 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid137 owl/annotatedSource obo/BFO_0000018)
(bnode/ccp-extensions_genid137 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid137 owl/annotatedTarget ["(forall (x) (if (ZeroDimensionalSpatialRegion x) (SpatialRegion x))) // axiom label in BFO2 CLIF: [037-001] "])
 ;(bnode/ccp-extensions_genid137 obo/IAO_0010000 obo/bfo/axiom/037-001)
;; 
;; http://purl.obolibrary.org/obo/BFO_0000019
(obo/BFO_0000019 rdf/type owl/Class)
(obo/BFO_0000019 rdfs/subClassOf obo/BFO_0000020)
(obo/BFO_0000019 obo/BFO_0000179 ["quality"])
(obo/BFO_0000019 obo/BFO_0000180 ["Quality"])
(obo/BFO_0000019 obo/IAO_0000112 ["the ambient temperature of this portion of air" "en"])
(obo/BFO_0000019 obo/IAO_0000112 ["the color of a tomato" "en"])
(obo/BFO_0000019 obo/IAO_0000112 ["the length of the circumference of your waist" "en"])
(obo/BFO_0000019 obo/IAO_0000112 ["the mass of this piece of gold." "en"])
(obo/BFO_0000019 obo/IAO_0000112 ["the shape of your nose" "en"])
(obo/BFO_0000019 obo/IAO_0000112 ["the shape of your nostril" "en"])
(obo/BFO_0000019 obo/IAO_0000600 ["a quality is a specifically dependent continuant that, in contrast to roles and dispositions, does not require any further process in order to be realized. (axiom label in BFO2 Reference: [055-001])" "en"])
(obo/BFO_0000019 obo/IAO_0000601 ["If an entity is a quality at any time that it exists, then it is a quality at every time that it exists. (axiom label in BFO2 Reference: [105-001])" "en"])
(obo/BFO_0000019 obo/IAO_0000602 ["(forall (x) (if (Quality x) (SpecificallyDependentContinuant x))) // axiom label in BFO2 CLIF: [055-001] "])
(obo/BFO_0000019 obo/IAO_0000602 ["(forall (x) (if (exists (t) (and (existsAt x t) (Quality x))) (forall (t_1) (if (existsAt x t_1) (Quality x))))) // axiom label in BFO2 CLIF: [105-001] "])
(obo/BFO_0000019 rdfs/isDefinedBy obo/bfo.owl)
(obo/BFO_0000019 rdfs/label ["quality" "en"])
(bnode/ccp-extensions_genid138 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid138 owl/annotatedSource obo/BFO_0000019)
(bnode/ccp-extensions_genid138 owl/annotatedProperty obo/IAO_0000600)
(bnode/ccp-extensions_genid138 owl/annotatedTarget ["a quality is a specifically dependent continuant that, in contrast to roles and dispositions, does not require any further process in order to be realized. (axiom label in BFO2 Reference: [055-001])" "en"])
 ;(bnode/ccp-extensions_genid138 obo/IAO_0010000 obo/bfo/axiom/055-001)
(bnode/ccp-extensions_genid139 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid139 owl/annotatedSource obo/BFO_0000019)
(bnode/ccp-extensions_genid139 owl/annotatedProperty obo/IAO_0000601)
(bnode/ccp-extensions_genid139 owl/annotatedTarget ["If an entity is a quality at any time that it exists, then it is a quality at every time that it exists. (axiom label in BFO2 Reference: [105-001])" "en"])
 ;(bnode/ccp-extensions_genid139 obo/IAO_0010000 obo/bfo/axiom/105-001)
(bnode/ccp-extensions_genid140 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid140 owl/annotatedSource obo/BFO_0000019)
(bnode/ccp-extensions_genid140 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid140 owl/annotatedTarget ["(forall (x) (if (Quality x) (SpecificallyDependentContinuant x))) // axiom label in BFO2 CLIF: [055-001] "])
 ;(bnode/ccp-extensions_genid140 obo/IAO_0010000 obo/bfo/axiom/055-001)
(bnode/ccp-extensions_genid141 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid141 owl/annotatedSource obo/BFO_0000019)
(bnode/ccp-extensions_genid141 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid141 owl/annotatedTarget ["(forall (x) (if (exists (t) (and (existsAt x t) (Quality x))) (forall (t_1) (if (existsAt x t_1) (Quality x))))) // axiom label in BFO2 CLIF: [105-001] "])
 ;(bnode/ccp-extensions_genid141 obo/IAO_0010000 obo/bfo/axiom/105-001)
;; 
;; http://purl.obolibrary.org/obo/BFO_0000020
(obo/BFO_0000020 rdf/type owl/Class)
(obo/BFO_0000020 rdfs/subClassOf obo/BFO_0000002)
(obo/BFO_0000020 owl/disjointWith obo/BFO_0000031)
(obo/BFO_0000020 obo/BFO_0000179 ["sdc"])
(obo/BFO_0000020 obo/BFO_0000180 ["SpecificallyDependentContinuant"])
(obo/BFO_0000020 obo/IAO_0000112 ["Reciprocal specifically dependent continuants: the function of this key to open this lock and the mutually dependent disposition of this lock: to be opened by this key" "en"])
(obo/BFO_0000020 obo/IAO_0000112 ["of one-sided specifically dependent continuants: the mass of this tomato" "en"])
(obo/BFO_0000020 obo/IAO_0000112 ["of relational dependent continuants (multiple bearers): John\u2019s love for Mary, the ownership relation between John and this statue, the relation of authority between John and his subordinates." "en"])
(obo/BFO_0000020 obo/IAO_0000112 ["the disposition of this fish to decay" "en"])
(obo/BFO_0000020 obo/IAO_0000112 ["the function of this heart: to pump blood" "en"])
(obo/BFO_0000020 obo/IAO_0000112 ["the mutual dependence of proton donors and acceptors in chemical reactions [79" "en"])
(obo/BFO_0000020 obo/IAO_0000112 ["the mutual dependence of the role predator and the role prey as played by two organisms in a given interaction" "en"])
(obo/BFO_0000020 obo/IAO_0000112 ["the pink color of a medium rare piece of grilled filet mignon at its center" "en"])
(obo/BFO_0000020 obo/IAO_0000112 ["the role of being a doctor" "en"])
(obo/BFO_0000020 obo/IAO_0000112 ["the shape of this hole." "en"])
(obo/BFO_0000020 obo/IAO_0000112 ["the smell of this portion of mozzarella" "en"])
(obo/BFO_0000020 obo/IAO_0000115 ["A continuant that inheres in or is borne by other entities. Every instance of A requires some specific instance of B which must always be the same." "en"])
(obo/BFO_0000020 obo/IAO_0000115 ["b is a specifically dependent continuant = Def. b is a continuant & there is some independent continuant c which is not a spatial region and which is such that b s-depends_on c at every time t during the course of b\u2019s existence. (axiom label in BFO2 Reference: [050-003])" "en"])
(obo/BFO_0000020 obo/IAO_0000116 ["Specifically dependent continuant doesn't have a closure axiom because the subclasses don't necessarily exhaust all possibilites. We're not sure what else will develop here, but for example there are questions such as what are promises, obligation, etc." "en"])
(obo/BFO_0000020 obo/IAO_0000602 ["(iff (SpecificallyDependentContinuant a) (and (Continuant a) (forall (t) (if (existsAt a t) (exists (b) (and (IndependentContinuant b) (not (SpatialRegion b)) (specificallyDependsOnAt a b t))))))) // axiom label in BFO2 CLIF: [050-003] "])
(obo/BFO_0000020 rdfs/isDefinedBy obo/bfo.owl)
(obo/BFO_0000020 rdfs/label ["specifically dependent continuant" "en"])
(bnode/ccp-extensions_genid142 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid142 owl/annotatedSource obo/BFO_0000020)
(bnode/ccp-extensions_genid142 owl/annotatedProperty obo/IAO_0000115)
(bnode/ccp-extensions_genid142 owl/annotatedTarget ["b is a specifically dependent continuant = Def. b is a continuant & there is some independent continuant c which is not a spatial region and which is such that b s-depends_on c at every time t during the course of b\u2019s existence. (axiom label in BFO2 Reference: [050-003])" "en"])
 ;(bnode/ccp-extensions_genid142 obo/IAO_0010000 obo/bfo/axiom/050-003)
(bnode/ccp-extensions_genid143 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid143 owl/annotatedSource obo/BFO_0000020)
(bnode/ccp-extensions_genid143 owl/annotatedProperty obo/IAO_0000116)
(bnode/ccp-extensions_genid143 owl/annotatedTarget ["Specifically dependent continuant doesn't have a closure axiom because the subclasses don't necessarily exhaust all possibilites. We're not sure what else will develop here, but for example there are questions such as what are promises, obligation, etc." "en"])
 ;(bnode/ccp-extensions_genid143 obo/IAO_0010000 obo/bfo/axiom/0000005)
(bnode/ccp-extensions_genid143 rdfs/comment ["per discussion with Barry Smith"])
(bnode/ccp-extensions_genid144 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid144 owl/annotatedSource obo/BFO_0000020)
(bnode/ccp-extensions_genid144 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid144 owl/annotatedTarget ["(iff (SpecificallyDependentContinuant a) (and (Continuant a) (forall (t) (if (existsAt a t) (exists (b) (and (IndependentContinuant b) (not (SpatialRegion b)) (specificallyDependsOnAt a b t))))))) // axiom label in BFO2 CLIF: [050-003] "])
 ;(bnode/ccp-extensions_genid144 obo/IAO_0010000 obo/bfo/axiom/050-003)
;; 
;; http://purl.obolibrary.org/obo/BFO_0000023
(obo/BFO_0000023 rdf/type owl/Class)
(obo/BFO_0000023 rdfs/subClassOf obo/BFO_0000017)
(obo/BFO_0000023 obo/BFO_0000179 ["role"])
(obo/BFO_0000023 obo/BFO_0000180 ["Role"])
(obo/BFO_0000023 obo/IAO_0000112 ["John\u2019s role of husband to Mary is dependent on Mary\u2019s role of wife to John, and both are dependent on the object aggregate comprising John and Mary as member parts joined together through the relational quality of being married." "en"])
(obo/BFO_0000023 obo/IAO_0000112 ["the priest role" "en"])
(obo/BFO_0000023 obo/IAO_0000112 ["the role of a boundary to demarcate two neighboring administrative territories" "en"])
(obo/BFO_0000023 obo/IAO_0000112 ["the role of a building in serving as a military target" "en"])
(obo/BFO_0000023 obo/IAO_0000112 ["the role of a stone in marking a property boundary" "en"])
(obo/BFO_0000023 obo/IAO_0000112 ["the role of subject in a clinical trial" "en"])
(obo/BFO_0000023 obo/IAO_0000112 ["the student role" "en"])
(obo/BFO_0000023 obo/IAO_0000115 ["A realizable entity  the manifestation of which brings about some result or end that is not essential to a continuant  in virtue of the kind of thing that it is but that can be served or participated in by that kind of continuant  in some kinds of natural, social or institutional contexts." "en"])
(obo/BFO_0000023 obo/IAO_0000116 ["BFO 2 Reference: One major family of examples of non-rigid universals involves roles, and ontologies developed for corresponding administrative purposes may consist entirely of representatives of entities of this sort. Thus \u2018professor\u2019, defined as follows,b instance_of professor at t =Def. there is some c, c instance_of professor role & c inheres_in b at t.denotes a non-rigid universal and so also do \u2018nurse\u2019, \u2018student\u2019, \u2018colonel\u2019, \u2018taxpayer\u2019, and so forth. (These terms are all, in the jargon of philosophy, phase sortals.) By using role terms in definitions, we can create a BFO conformant treatment of such entities drawing on the fact that, while an instance of professor may be simultaneously an instance of trade union member, no instance of the type professor role is also (at any time) an instance of the type trade union member role (any more than any instance of the type color is at any time an instance of the type length).If an ontology of employment positions should be defined in terms of roles following the above pattern, this enables the ontology to do justice to the fact that individuals instantiate the corresponding universals \u2013  professor, sergeant, nurse \u2013 only during certain phases in their lives." "en"])
(obo/BFO_0000023 obo/IAO_0000600 ["b is a role means: b is a realizable entity & b exists because there is some single bearer that is in some special physical, social, or institutional set of circumstances in which this bearer does not have to be& b is not such that, if it ceases to exist, then the physical make-up of the bearer is thereby changed. (axiom label in BFO2 Reference: [061-001])" "en"])
(obo/BFO_0000023 obo/IAO_0000602 ["(forall (x) (if (Role x) (RealizableEntity x))) // axiom label in BFO2 CLIF: [061-001] "])
(obo/BFO_0000023 rdfs/isDefinedBy obo/bfo.owl)
(obo/BFO_0000023 rdfs/label ["role" "en"])
(bnode/ccp-extensions_genid145 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid145 owl/annotatedSource obo/BFO_0000023)
(bnode/ccp-extensions_genid145 owl/annotatedProperty obo/IAO_0000600)
(bnode/ccp-extensions_genid145 owl/annotatedTarget ["b is a role means: b is a realizable entity & b exists because there is some single bearer that is in some special physical, social, or institutional set of circumstances in which this bearer does not have to be& b is not such that, if it ceases to exist, then the physical make-up of the bearer is thereby changed. (axiom label in BFO2 Reference: [061-001])" "en"])
 ;(bnode/ccp-extensions_genid145 obo/IAO_0010000 obo/bfo/axiom/061-001)
(bnode/ccp-extensions_genid146 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid146 owl/annotatedSource obo/BFO_0000023)
(bnode/ccp-extensions_genid146 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid146 owl/annotatedTarget ["(forall (x) (if (Role x) (RealizableEntity x))) // axiom label in BFO2 CLIF: [061-001] "])
 ;(bnode/ccp-extensions_genid146 obo/IAO_0010000 obo/bfo/axiom/061-001)
;; 
;; http://purl.obolibrary.org/obo/BFO_0000024
(obo/BFO_0000024 rdf/type owl/Class)
(obo/BFO_0000024 rdfs/subClassOf obo/BFO_0000040)
(obo/BFO_0000024 obo/BFO_0000179 ["fiat-object-part"])
(obo/BFO_0000024 obo/BFO_0000180 ["FiatObjectPart"])
(obo/BFO_0000024 obo/IAO_0000112 ["or with divisions drawn by cognitive subjects for practical reasons, such as the division of a cake (before slicing) into (what will become) slices (and thus member parts of an object aggregate). However, this does not mean that fiat object parts are dependent for their existence on divisions or delineations effected by cognitive subjects. If, for example, it is correct to conceive geological layers of the Earth as fiat object parts of the Earth, then even though these layers were first delineated in recent times, still existed long before such delineation and what holds of these layers (for example that the oldest layers are also the lowest layers) did not begin to hold because of our acts of delineation.Treatment of material entity in BFOExamples viewed by some as problematic cases for the trichotomy of fiat object part, object, and object aggregate include: a mussel on (and attached to) a rock, a slime mold, a pizza, a cloud, a galaxy, a railway train with engine and multiple carriages, a clonal stand of quaking aspen, a bacterial community (biofilm), a broken femur. Note that, as Aristotle already clearly recognized, such problematic cases \u2013 which lie at or near the penumbra of instances defined by the categories in question \u2013 need not invalidate these categories. The existence of grey objects does not prove that there are not objects which are black and objects which are white; the existence of mules does not prove that there are not objects which are donkeys and objects which are horses. It does, however, show that the examples in question need to be addressed carefully in order to show how they can be fitted into the proposed scheme, for example by recognizing additional subdivisions [29" "en"])
(obo/BFO_0000024 obo/IAO_0000112 ["the FMA:regional parts of an intact human body." "en"])
(obo/BFO_0000024 obo/IAO_0000112 ["the Western hemisphere of the Earth" "en"])
(obo/BFO_0000024 obo/IAO_0000112 ["the division of the brain into regions" "en"])
(obo/BFO_0000024 obo/IAO_0000112 ["the division of the planet into hemispheres" "en"])
(obo/BFO_0000024 obo/IAO_0000112 ["the dorsal and ventral surfaces of the body" "en"])
(obo/BFO_0000024 obo/IAO_0000112 ["the upper and lower lobes of the left lung" "en"])
(obo/BFO_0000024 obo/IAO_0000116 ["BFO 2 Reference: Most examples of fiat object parts are associated with theoretically drawn divisions" "en"])
(obo/BFO_0000024 obo/IAO_0000600 ["b is a fiat object part = Def. b is a material entity which is such that for all times t, if b exists at t then there is some object c such that b proper continuant_part of  c at t and c is demarcated from the remainder of c by a two-dimensional continuant fiat boundary. (axiom label in BFO2 Reference: [027-004])" "en"])
(obo/BFO_0000024 obo/IAO_0000602 ["(forall (x) (if (FiatObjectPart x) (and (MaterialEntity x) (forall (t) (if (existsAt x t) (exists (y) (and (Object y) (properContinuantPartOfAt x y t)))))))) // axiom label in BFO2 CLIF: [027-004] "])
(obo/BFO_0000024 rdfs/isDefinedBy obo/bfo.owl)
(obo/BFO_0000024 rdfs/label ["fiat object part" "en"])
(bnode/ccp-extensions_genid147 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid147 owl/annotatedSource obo/BFO_0000024)
(bnode/ccp-extensions_genid147 owl/annotatedProperty obo/IAO_0000600)
(bnode/ccp-extensions_genid147 owl/annotatedTarget ["b is a fiat object part = Def. b is a material entity which is such that for all times t, if b exists at t then there is some object c such that b proper continuant_part of  c at t and c is demarcated from the remainder of c by a two-dimensional continuant fiat boundary. (axiom label in BFO2 Reference: [027-004])" "en"])
 ;(bnode/ccp-extensions_genid147 obo/IAO_0010000 obo/bfo/axiom/027-004)
(bnode/ccp-extensions_genid148 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid148 owl/annotatedSource obo/BFO_0000024)
(bnode/ccp-extensions_genid148 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid148 owl/annotatedTarget ["(forall (x) (if (FiatObjectPart x) (and (MaterialEntity x) (forall (t) (if (existsAt x t) (exists (y) (and (Object y) (properContinuantPartOfAt x y t)))))))) // axiom label in BFO2 CLIF: [027-004] "])
 ;(bnode/ccp-extensions_genid148 obo/IAO_0010000 obo/bfo/axiom/027-004)
;; 
;; http://purl.obolibrary.org/obo/BFO_0000026
(obo/BFO_0000026 rdf/type owl/Class)
(obo/BFO_0000026 rdfs/subClassOf obo/BFO_0000006)
(obo/BFO_0000026 owl/disjointWith obo/BFO_0000028)
(obo/BFO_0000026 obo/BFO_0000179 ["1d-s-region"])
(obo/BFO_0000026 obo/BFO_0000180 ["OneDimensionalSpatialRegion"])
(obo/BFO_0000026 obo/IAO_0000112 ["an edge of a cube-shaped portion of space." "en"])
(obo/BFO_0000026 obo/IAO_0000600 ["A one-dimensional spatial region is a line or aggregate of lines stretching from one point in space to another. (axiom label in BFO2 Reference: [038-001])" "en"])
(obo/BFO_0000026 obo/IAO_0000602 ["(forall (x) (if (OneDimensionalSpatialRegion x) (SpatialRegion x))) // axiom label in BFO2 CLIF: [038-001] "])
(obo/BFO_0000026 rdfs/isDefinedBy obo/bfo.owl)
(obo/BFO_0000026 rdfs/label ["one-dimensional spatial region" "en"])
(bnode/ccp-extensions_genid149 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid149 owl/annotatedSource obo/BFO_0000026)
(bnode/ccp-extensions_genid149 owl/annotatedProperty obo/IAO_0000600)
(bnode/ccp-extensions_genid149 owl/annotatedTarget ["A one-dimensional spatial region is a line or aggregate of lines stretching from one point in space to another. (axiom label in BFO2 Reference: [038-001])" "en"])
 ;(bnode/ccp-extensions_genid149 obo/IAO_0010000 obo/bfo/axiom/038-001)
(bnode/ccp-extensions_genid150 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid150 owl/annotatedSource obo/BFO_0000026)
(bnode/ccp-extensions_genid150 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid150 owl/annotatedTarget ["(forall (x) (if (OneDimensionalSpatialRegion x) (SpatialRegion x))) // axiom label in BFO2 CLIF: [038-001] "])
 ;(bnode/ccp-extensions_genid150 obo/IAO_0010000 obo/bfo/axiom/038-001)
;; 
;; http://purl.obolibrary.org/obo/BFO_0000027
(obo/BFO_0000027 rdf/type owl/Class)
(obo/BFO_0000027 rdfs/subClassOf obo/BFO_0000040)
(obo/BFO_0000027 obo/BFO_0000179 ["object-aggregate"])
(obo/BFO_0000027 obo/BFO_0000180 ["ObjectAggregate"])
(obo/BFO_0000027 obo/IAO_0000112 ["a collection of cells in a blood biobank." "en"])
(obo/BFO_0000027 obo/IAO_0000112 ["a swarm of bees is an aggregate of members who are linked together through natural bonds" "en"])
(obo/BFO_0000027 obo/IAO_0000112 ["a symphony orchestra" "en"])
(obo/BFO_0000027 obo/IAO_0000112 ["an organization is an aggregate whose member parts have roles of specific types (for example in a jazz band, a chess club, a football team)" "en"])
(obo/BFO_0000027 obo/IAO_0000112 ["defined by fiat: the aggregate of members of an organization" "en"])
(obo/BFO_0000027 obo/IAO_0000112 ["defined through physical attachment: the aggregate of atoms in a lump of granite" "en"])
(obo/BFO_0000027 obo/IAO_0000112 ["defined through physical containment: the aggregate of molecules of carbon dioxide in a sealed container" "en"])
(obo/BFO_0000027 obo/IAO_0000112 ["defined via attributive delimitations such as: the patients in this hospital" "en"])
(obo/BFO_0000027 obo/IAO_0000112 ["the aggregate of bearings in a constant velocity axle joint" "en"])
(obo/BFO_0000027 obo/IAO_0000112 ["the aggregate of blood cells in your body" "en"])
(obo/BFO_0000027 obo/IAO_0000112 ["the nitrogen atoms in the atmosphere" "en"])
(obo/BFO_0000027 obo/IAO_0000112 ["the restaurants in Palo Alto" "en"])
(obo/BFO_0000027 obo/IAO_0000112 ["your collection of Meissen ceramic plates." "en"])
(obo/BFO_0000027 obo/IAO_0000116 ["An entity a is an object aggregate if and only if there is a mutually exhaustive and pairwise disjoint partition of a into objects "])
(obo/BFO_0000027 obo/IAO_0000116 ["BFO 2 Reference: object aggregates may gain and lose parts while remaining numerically identical (one and the same individual) over time. This holds both for aggregates whose membership is determined naturally (the aggregate of cells in your body) and aggregates determined by fiat (a baseball team, a congressional committee)." "en"])
(obo/BFO_0000027 obo/IAO_0000119 ["ISBN:978-3-938793-98-5pp124-158;;Thomas Bittner and Barry Smith, 'A Theory of Granular Partitions', in K. Munn and B. Smith (eds.), Applied Ontology: An Introduction, Frankfurt/Lancaster: ontos, 2008, 125-158."])
(obo/BFO_0000027 obo/IAO_0000600 ["b is an object aggregate means: b is a material entity consisting exactly of a plurality of objects as member_parts at all times at which b exists. (axiom label in BFO2 Reference: [025-004])" "en"])
(obo/BFO_0000027 obo/IAO_0000602 ["(forall (x) (if (ObjectAggregate x) (and (MaterialEntity x) (forall (t) (if (existsAt x t) (exists (y z) (and (Object y) (Object z) (memberPartOfAt y x t) (memberPartOfAt z x t) (not (= y z)))))) (not (exists (w t_1) (and (memberPartOfAt w x t_1) (not (Object w)))))))) // axiom label in BFO2 CLIF: [025-004] "])
(obo/BFO_0000027 rdfs/isDefinedBy obo/bfo.owl)
(obo/BFO_0000027 rdfs/label ["object aggregate" "en"])
(bnode/ccp-extensions_genid151 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid151 owl/annotatedSource obo/BFO_0000027)
(bnode/ccp-extensions_genid151 owl/annotatedProperty obo/IAO_0000116)
(bnode/ccp-extensions_genid151 owl/annotatedTarget ["An entity a is an object aggregate if and only if there is a mutually exhaustive and pairwise disjoint partition of a into objects "])
 ;(bnode/ccp-extensions_genid151 obo/IAO_0010000 obo/bfo/axiom/0000011)
(bnode/ccp-extensions_genid152 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid152 owl/annotatedSource obo/BFO_0000027)
(bnode/ccp-extensions_genid152 owl/annotatedProperty obo/IAO_0000116)
(bnode/ccp-extensions_genid152 owl/annotatedTarget ["An entity a is an object aggregate if and only if there is a mutually exhaustive and pairwise disjoint partition of a into objects "])
 ;(bnode/ccp-extensions_genid152 obo/IAO_0010000 obo/bfo/axiom/0000301)
(bnode/ccp-extensions_genid153 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid153 owl/annotatedSource obo/BFO_0000027)
(bnode/ccp-extensions_genid153 owl/annotatedProperty obo/IAO_0000119)
(bnode/ccp-extensions_genid153 owl/annotatedTarget ["ISBN:978-3-938793-98-5pp124-158;;Thomas Bittner and Barry Smith, 'A Theory of Granular Partitions', in K. Munn and B. Smith (eds.), Applied Ontology: An Introduction, Frankfurt/Lancaster: ontos, 2008, 125-158."])
 ;(bnode/ccp-extensions_genid153 obo/IAO_0010000 obo/bfo/axiom/0000300)
(bnode/ccp-extensions_genid154 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid154 owl/annotatedSource obo/BFO_0000027)
(bnode/ccp-extensions_genid154 owl/annotatedProperty obo/IAO_0000600)
(bnode/ccp-extensions_genid154 owl/annotatedTarget ["b is an object aggregate means: b is a material entity consisting exactly of a plurality of objects as member_parts at all times at which b exists. (axiom label in BFO2 Reference: [025-004])" "en"])
 ;(bnode/ccp-extensions_genid154 obo/IAO_0010000 obo/bfo/axiom/025-004)
(bnode/ccp-extensions_genid155 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid155 owl/annotatedSource obo/BFO_0000027)
(bnode/ccp-extensions_genid155 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid155 owl/annotatedTarget ["(forall (x) (if (ObjectAggregate x) (and (MaterialEntity x) (forall (t) (if (existsAt x t) (exists (y z) (and (Object y) (Object z) (memberPartOfAt y x t) (memberPartOfAt z x t) (not (= y z)))))) (not (exists (w t_1) (and (memberPartOfAt w x t_1) (not (Object w)))))))) // axiom label in BFO2 CLIF: [025-004] "])
 ;(bnode/ccp-extensions_genid155 obo/IAO_0010000 obo/bfo/axiom/025-004)
;; 
;; http://purl.obolibrary.org/obo/BFO_0000028
(obo/BFO_0000028 rdf/type owl/Class)
(obo/BFO_0000028 rdfs/subClassOf obo/BFO_0000006)
(obo/BFO_0000028 obo/BFO_0000179 ["3d-s-region"])
(obo/BFO_0000028 obo/BFO_0000180 ["ThreeDimensionalSpatialRegion"])
(obo/BFO_0000028 obo/IAO_0000112 ["a cube-shaped region of space" "en"])
(obo/BFO_0000028 obo/IAO_0000112 ["a sphere-shaped region of space," "en"])
(obo/BFO_0000028 obo/IAO_0000600 ["A three-dimensional spatial region is a spatial region that is of three dimensions. (axiom label in BFO2 Reference: [040-001])" "en"])
(obo/BFO_0000028 obo/IAO_0000602 ["(forall (x) (if (ThreeDimensionalSpatialRegion x) (SpatialRegion x))) // axiom label in BFO2 CLIF: [040-001] "])
(obo/BFO_0000028 rdfs/isDefinedBy obo/bfo.owl)
(obo/BFO_0000028 rdfs/label ["three-dimensional spatial region" "en"])
(bnode/ccp-extensions_genid156 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid156 owl/annotatedSource obo/BFO_0000028)
(bnode/ccp-extensions_genid156 owl/annotatedProperty obo/IAO_0000600)
(bnode/ccp-extensions_genid156 owl/annotatedTarget ["A three-dimensional spatial region is a spatial region that is of three dimensions. (axiom label in BFO2 Reference: [040-001])" "en"])
 ;(bnode/ccp-extensions_genid156 obo/IAO_0010000 obo/bfo/axiom/040-001)
(bnode/ccp-extensions_genid157 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid157 owl/annotatedSource obo/BFO_0000028)
(bnode/ccp-extensions_genid157 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid157 owl/annotatedTarget ["(forall (x) (if (ThreeDimensionalSpatialRegion x) (SpatialRegion x))) // axiom label in BFO2 CLIF: [040-001] "])
 ;(bnode/ccp-extensions_genid157 obo/IAO_0010000 obo/bfo/axiom/040-001)
;; 
;; http://purl.obolibrary.org/obo/BFO_0000029
(obo/BFO_0000029 rdf/type owl/Class)
(obo/BFO_0000029 rdfs/subClassOf obo/BFO_0000141)
(obo/BFO_0000029 obo/BFO_0000179 ["site"])
(obo/BFO_0000029 obo/BFO_0000180 ["Site"])
(obo/BFO_0000029 obo/IAO_0000112 ["Manhattan Canyon)" "en"])
(obo/BFO_0000029 obo/IAO_0000112 ["a hole in the interior of a portion of cheese" "en"])
(obo/BFO_0000029 obo/IAO_0000112 ["a rabbit hole" "en"])
(obo/BFO_0000029 obo/IAO_0000112 ["an air traffic control region defined in the airspace above an airport" "en"])
(obo/BFO_0000029 obo/IAO_0000112 ["the Grand Canyon" "en"])
(obo/BFO_0000029 obo/IAO_0000112 ["the Piazza San Marco" "en"])
(obo/BFO_0000029 obo/IAO_0000112 ["the cockpit of an aircraft" "en"])
(obo/BFO_0000029 obo/IAO_0000112 ["the hold of a ship" "en"])
(obo/BFO_0000029 obo/IAO_0000112 ["the interior of a kangaroo pouch" "en"])
(obo/BFO_0000029 obo/IAO_0000112 ["the interior of the trunk of your car" "en"])
(obo/BFO_0000029 obo/IAO_0000112 ["the interior of your bedroom" "en"])
(obo/BFO_0000029 obo/IAO_0000112 ["the interior of your office" "en"])
(obo/BFO_0000029 obo/IAO_0000112 ["the interior of your refrigerator" "en"])
(obo/BFO_0000029 obo/IAO_0000112 ["the lumen of your gut" "en"])
(obo/BFO_0000029 obo/IAO_0000112 ["your left nostril (a fiat part \u2013 the opening \u2013 of your left nasal cavity)" "en"])
(obo/BFO_0000029 obo/IAO_0000600 ["b is a site means: b is a three-dimensional immaterial entity that is (partially or wholly) bounded by a material entity or it is a three-dimensional immaterial part thereof. (axiom label in BFO2 Reference: [034-002])" "en"])
(obo/BFO_0000029 obo/IAO_0000602 ["(forall (x) (if (Site x) (ImmaterialEntity x))) // axiom label in BFO2 CLIF: [034-002] "])
(obo/BFO_0000029 rdfs/isDefinedBy obo/bfo.owl)
(obo/BFO_0000029 rdfs/label ["site" "en"])
(bnode/ccp-extensions_genid158 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid158 owl/annotatedSource obo/BFO_0000029)
(bnode/ccp-extensions_genid158 owl/annotatedProperty obo/IAO_0000600)
(bnode/ccp-extensions_genid158 owl/annotatedTarget ["b is a site means: b is a three-dimensional immaterial entity that is (partially or wholly) bounded by a material entity or it is a three-dimensional immaterial part thereof. (axiom label in BFO2 Reference: [034-002])" "en"])
 ;(bnode/ccp-extensions_genid158 obo/IAO_0010000 obo/bfo/axiom/034-002)
(bnode/ccp-extensions_genid159 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid159 owl/annotatedSource obo/BFO_0000029)
(bnode/ccp-extensions_genid159 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid159 owl/annotatedTarget ["(forall (x) (if (Site x) (ImmaterialEntity x))) // axiom label in BFO2 CLIF: [034-002] "])
 ;(bnode/ccp-extensions_genid159 obo/IAO_0010000 obo/bfo/axiom/034-002)
;; 
;; http://purl.obolibrary.org/obo/BFO_0000030
(obo/BFO_0000030 rdf/type owl/Class)
(obo/BFO_0000030 rdfs/subClassOf obo/BFO_0000040)
(obo/BFO_0000030 obo/BFO_0000179 ["object"])
(obo/BFO_0000030 obo/BFO_0000180 ["Object"])
(obo/BFO_0000030 obo/IAO_0000112 ["atom" "en"])
(obo/BFO_0000030 obo/IAO_0000112 ["cell" "en"])
(obo/BFO_0000030 obo/IAO_0000112 ["cells and organisms" "en"])
(obo/BFO_0000030 obo/IAO_0000112 ["engineered artifacts" "en"])
(obo/BFO_0000030 obo/IAO_0000112 ["grain of sand" "en"])
(obo/BFO_0000030 obo/IAO_0000112 ["molecule" "en"])
(obo/BFO_0000030 obo/IAO_0000112 ["organelle" "en"])
(obo/BFO_0000030 obo/IAO_0000112 ["organism" "en"])
(obo/BFO_0000030 obo/IAO_0000112 ["planet" "en"])
(obo/BFO_0000030 obo/IAO_0000112 ["solid portions of matter" "en"])
(obo/BFO_0000030 obo/IAO_0000112 ["star" "en"])
(obo/BFO_0000030 obo/IAO_0000116 ["BFO 2 Reference: BFO rests on the presupposition that at multiple micro-, meso- and macroscopic scales reality exhibits certain stable, spatially separated or separable material units, combined or combinable into aggregates of various sorts (for example organisms into what are called \u2018populations\u2019). Such units play a central role in almost all domains of natural science from particle physics to cosmology. Many scientific laws govern the units in question, employing general terms (such as \u2018molecule\u2019 or \u2018planet\u2019) referring to the types and subtypes of units, and also to the types and subtypes of the processes through which such units develop and interact. The division of reality into such natural units is at the heart of biological science, as also is the fact that these units may form higher-level units (as cells form multicellular organisms) and that they may also form aggregates of units, for example as cells form portions of tissue and organs form families, herds, breeds, species, and so on. At the same time, the division of certain portions of reality into engineered units (manufactured artifacts) is the basis of modern industrial technology, which rests on the distributed mass production of engineered parts through division of labor and on their assembly into larger, compound units such as cars and laptops. The division of portions of reality into units is one starting point for the phenomenon of counting." "en"])
(obo/BFO_0000030 obo/IAO_0000116 ["BFO 2 Reference: Each object is such that there are entities of which we can assert unproblematically that they lie in its interior, and other entities of which we can assert unproblematically that they lie in its exterior. This may not be so for entities lying at or near the boundary between the interior and exterior. This means that two objects \u2013 for example the two cells depicted in Figure 3 \u2013 may be such that there are material entities crossing their boundaries which belong determinately to neither cell. Something similar obtains in certain cases of conjoined twins (see below)." "en"])
(obo/BFO_0000030 obo/IAO_0000116 ["BFO 2 Reference: To say that b is causally unified means: b is a material entity which is such that its material parts are tied together in such a way that, in environments typical for entities of the type in question,if c, a continuant part of b that is in the interior of b at t, is larger than a certain threshold size (which will be determined differently from case to case, depending on factors such as porosity of external cover) and is moved in space to be at t at a location on the exterior of the spatial region that had been occupied by b at t, then either b\u2019s other parts will be moved in coordinated fashion or b will be damaged (be affected, for example, by breakage or tearing) in the interval between t and t.causal changes in one part of b can have consequences for other parts of b without the mediation of any entity that lies on the exterior of b. Material entities with no proper material parts would satisfy these conditions trivially. Candidate examples of types of causal unity for material entities of more complex sorts are as follows (this is not intended to be an exhaustive list):CU1: Causal unity via physical coveringHere the parts in the interior of the unified entity are combined together causally through a common membrane or other physical covering\\. The latter points outwards toward and may serve a protective function in relation to what lies on the exterior of the entity [13, 47" "en"])
(obo/BFO_0000030 obo/IAO_0000116 ["BFO 2 Reference: an object is a maximal causally unified material entity" "en"])
(obo/BFO_0000030 obo/IAO_0000116 ["BFO 2 Reference: \u2018objects\u2019 are sometimes referred to as \u2018grains\u2019 [74" "en"])
(obo/BFO_0000030 obo/IAO_0000600 ["b is an object means: b is a material entity which manifests causal unity of one or other of the types CUn listed above & is of a type (a material universal) instances of which are maximal relative to this criterion of causal unity. (axiom label in BFO2 Reference: [024-001])" "en"])
(obo/BFO_0000030 rdfs/isDefinedBy obo/bfo.owl)
(obo/BFO_0000030 rdfs/label ["object" "en"])
(bnode/ccp-extensions_genid160 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid160 owl/annotatedSource obo/BFO_0000030)
(bnode/ccp-extensions_genid160 owl/annotatedProperty obo/IAO_0000600)
(bnode/ccp-extensions_genid160 owl/annotatedTarget ["b is an object means: b is a material entity which manifests causal unity of one or other of the types CUn listed above & is of a type (a material universal) instances of which are maximal relative to this criterion of causal unity. (axiom label in BFO2 Reference: [024-001])" "en"])
 ;(bnode/ccp-extensions_genid160 obo/IAO_0010000 obo/bfo/axiom/024-001)
;; 
;; http://purl.obolibrary.org/obo/BFO_0000031
(obo/BFO_0000031 rdf/type owl/Class)
(obo/BFO_0000031 rdfs/subClassOf obo/BFO_0000002)
(obo/BFO_0000031 obo/BFO_0000179 ["gdc"])
(obo/BFO_0000031 obo/BFO_0000180 ["GenericallyDependentContinuant"])
(obo/BFO_0000031 obo/IAO_0000112 ["The entries in your database are patterns instantiated as quality instances in your hard drive. The database itself is an aggregate of such patterns. When you create the database you create a particular instance of the generically dependent continuant type database. Each entry in the database is an instance of the generically dependent continuant type IAO: information content entity." "en"])
(obo/BFO_0000031 obo/IAO_0000112 ["the pdf file on your laptop, the pdf file that is a copy thereof on my laptop" "en"])
(obo/BFO_0000031 obo/IAO_0000112 ["the sequence of this protein molecule; the sequence that is a copy thereof in that protein molecule." "en"])
(obo/BFO_0000031 obo/IAO_0000115 ["A continuant that is dependent on one or other independent continuant bearers. For every instance of A requires some instance of (an independent continuant type) B but which instance of B serves can change from time to time." "en"])
(obo/BFO_0000031 obo/IAO_0000115 ["b is a generically dependent continuant = Def. b is a continuant that g-depends_on one or more other entities. (axiom label in BFO2 Reference: [074-001])" "en"])
(obo/BFO_0000031 obo/IAO_0000602 ["(iff (GenericallyDependentContinuant a) (and (Continuant a) (exists (b t) (genericallyDependsOnAt a b t)))) // axiom label in BFO2 CLIF: [074-001] "])
(obo/BFO_0000031 rdfs/isDefinedBy obo/bfo.owl)
(obo/BFO_0000031 rdfs/label ["generically dependent continuant" "en"])
(bnode/ccp-extensions_genid161 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid161 owl/annotatedSource obo/BFO_0000031)
(bnode/ccp-extensions_genid161 owl/annotatedProperty obo/IAO_0000115)
(bnode/ccp-extensions_genid161 owl/annotatedTarget ["b is a generically dependent continuant = Def. b is a continuant that g-depends_on one or more other entities. (axiom label in BFO2 Reference: [074-001])" "en"])
 ;(bnode/ccp-extensions_genid161 obo/IAO_0010000 obo/bfo/axiom/074-001)
(bnode/ccp-extensions_genid162 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid162 owl/annotatedSource obo/BFO_0000031)
(bnode/ccp-extensions_genid162 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid162 owl/annotatedTarget ["(iff (GenericallyDependentContinuant a) (and (Continuant a) (exists (b t) (genericallyDependsOnAt a b t)))) // axiom label in BFO2 CLIF: [074-001] "])
 ;(bnode/ccp-extensions_genid162 obo/IAO_0010000 obo/bfo/axiom/074-001)
;; 
;; http://purl.obolibrary.org/obo/BFO_0000034
(obo/BFO_0000034 rdf/type owl/Class)
(obo/BFO_0000034 rdfs/subClassOf obo/BFO_0000016)
(obo/BFO_0000034 obo/BFO_0000179 ["function"])
(obo/BFO_0000034 obo/BFO_0000180 ["Function"])
(obo/BFO_0000034 obo/IAO_0000112 ["the function of a hammer to drive in nails" "en"])
(obo/BFO_0000034 obo/IAO_0000112 ["the function of a heart pacemaker to regulate the beating of a heart through electricity" "en"])
(obo/BFO_0000034 obo/IAO_0000112 ["the function of amylase in saliva to break down starch into sugar" "en"])
(obo/BFO_0000034 obo/IAO_0000116 ["BFO 2 Reference: In the past, we have distinguished two varieties of function, artifactual function and biological function. These are not asserted subtypes of BFO:function however, since the same function \u2013 for example: to pump, to transport \u2013 can exist both in artifacts and in biological entities. The asserted subtypes of function that would be needed in order to yield a separate monoheirarchy are not artifactual function, biological function, etc., but rather transporting function, pumping function, etc." "en"])
(obo/BFO_0000034 obo/IAO_0000600 ["A function is a disposition that exists in virtue of the bearer\u2019s physical make-up and this physical make-up is something the bearer possesses because it came into being, either through evolution (in the case of natural biological entities) or through intentional design (in the case of artifacts), in order to realize processes of a certain sort. (axiom label in BFO2 Reference: [064-001])" "en"])
(obo/BFO_0000034 obo/IAO_0000602 ["(forall (x) (if (Function x) (Disposition x))) // axiom label in BFO2 CLIF: [064-001] "])
(obo/BFO_0000034 rdfs/isDefinedBy obo/bfo.owl)
(obo/BFO_0000034 rdfs/label ["function" "en"])
(bnode/ccp-extensions_genid163 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid163 owl/annotatedSource obo/BFO_0000034)
(bnode/ccp-extensions_genid163 owl/annotatedProperty obo/IAO_0000600)
(bnode/ccp-extensions_genid163 owl/annotatedTarget ["A function is a disposition that exists in virtue of the bearer\u2019s physical make-up and this physical make-up is something the bearer possesses because it came into being, either through evolution (in the case of natural biological entities) or through intentional design (in the case of artifacts), in order to realize processes of a certain sort. (axiom label in BFO2 Reference: [064-001])" "en"])
 ;(bnode/ccp-extensions_genid163 obo/IAO_0010000 obo/bfo/axiom/064-001)
(bnode/ccp-extensions_genid164 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid164 owl/annotatedSource obo/BFO_0000034)
(bnode/ccp-extensions_genid164 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid164 owl/annotatedTarget ["(forall (x) (if (Function x) (Disposition x))) // axiom label in BFO2 CLIF: [064-001] "])
 ;(bnode/ccp-extensions_genid164 obo/IAO_0010000 obo/bfo/axiom/064-001)
;; 
;; http://purl.obolibrary.org/obo/BFO_0000035
(obo/BFO_0000035 rdf/type owl/Class)
(obo/BFO_0000035 rdfs/subClassOf obo/BFO_0000003)
(obo/BFO_0000035 obo/BFO_0000179 ["p-boundary"])
(obo/BFO_0000035 obo/BFO_0000180 ["ProcessBoundary"])
(obo/BFO_0000035 obo/IAO_0000112 ["the boundary between the 2nd and 3rd year of your life." "en"])
(obo/BFO_0000035 obo/IAO_0000115 ["p is a process boundary =Def. p is a temporal part of a process & p has no proper temporal parts. (axiom label in BFO2 Reference: [084-001])" "en"])
(obo/BFO_0000035 obo/IAO_0000601 ["Every process boundary occupies_temporal_region a zero-dimensional temporal region. (axiom label in BFO2 Reference: [085-002])" "en"])
(obo/BFO_0000035 obo/IAO_0000602 ["(forall (x) (if (ProcessBoundary x) (exists (y) (and (ZeroDimensionalTemporalRegion y) (occupiesTemporalRegion x y))))) // axiom label in BFO2 CLIF: [085-002] "])
(obo/BFO_0000035 obo/IAO_0000602 ["(iff (ProcessBoundary a) (exists (p) (and (Process p) (temporalPartOf a p) (not (exists (b) (properTemporalPartOf b a)))))) // axiom label in BFO2 CLIF: [084-001] "])
(obo/BFO_0000035 rdfs/isDefinedBy obo/bfo.owl)
(obo/BFO_0000035 rdfs/label ["process boundary" "en"])
(bnode/ccp-extensions_genid165 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid165 owl/annotatedSource obo/BFO_0000035)
(bnode/ccp-extensions_genid165 owl/annotatedProperty obo/IAO_0000115)
(bnode/ccp-extensions_genid165 owl/annotatedTarget ["p is a process boundary =Def. p is a temporal part of a process & p has no proper temporal parts. (axiom label in BFO2 Reference: [084-001])" "en"])
 ;(bnode/ccp-extensions_genid165 obo/IAO_0010000 obo/bfo/axiom/084-001)
(bnode/ccp-extensions_genid166 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid166 owl/annotatedSource obo/BFO_0000035)
(bnode/ccp-extensions_genid166 owl/annotatedProperty obo/IAO_0000601)
(bnode/ccp-extensions_genid166 owl/annotatedTarget ["Every process boundary occupies_temporal_region a zero-dimensional temporal region. (axiom label in BFO2 Reference: [085-002])" "en"])
 ;(bnode/ccp-extensions_genid166 obo/IAO_0010000 obo/bfo/axiom/085-002)
(bnode/ccp-extensions_genid167 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid167 owl/annotatedSource obo/BFO_0000035)
(bnode/ccp-extensions_genid167 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid167 owl/annotatedTarget ["(forall (x) (if (ProcessBoundary x) (exists (y) (and (ZeroDimensionalTemporalRegion y) (occupiesTemporalRegion x y))))) // axiom label in BFO2 CLIF: [085-002] "])
 ;(bnode/ccp-extensions_genid167 obo/IAO_0010000 obo/bfo/axiom/085-002)
(bnode/ccp-extensions_genid168 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid168 owl/annotatedSource obo/BFO_0000035)
(bnode/ccp-extensions_genid168 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid168 owl/annotatedTarget ["(iff (ProcessBoundary a) (exists (p) (and (Process p) (temporalPartOf a p) (not (exists (b) (properTemporalPartOf b a)))))) // axiom label in BFO2 CLIF: [084-001] "])
 ;(bnode/ccp-extensions_genid168 obo/IAO_0010000 obo/bfo/axiom/084-001)
;; 
;; http://purl.obolibrary.org/obo/BFO_0000038
(obo/BFO_0000038 rdf/type owl/Class)
(obo/BFO_0000038 rdfs/subClassOf obo/BFO_0000008)
(obo/BFO_0000038 owl/disjointWith obo/BFO_0000148)
(obo/BFO_0000038 obo/BFO_0000179 ["1d-t-region"])
(obo/BFO_0000038 obo/BFO_0000180 ["OneDimensionalTemporalRegion"])
(obo/BFO_0000038 obo/IAO_0000112 ["the temporal region during which a process occurs." "en"])
(obo/BFO_0000038 obo/IAO_0000116 ["BFO 2 Reference: A temporal interval is a special kind of one-dimensional temporal region, namely one that is self-connected (is without gaps or breaks)." "en"])
(obo/BFO_0000038 obo/IAO_0000600 ["A one-dimensional temporal region is a temporal region that is extended. (axiom label in BFO2 Reference: [103-001])" "en"])
(obo/BFO_0000038 obo/IAO_0000602 ["(forall (x) (if (OneDimensionalTemporalRegion x) (TemporalRegion x))) // axiom label in BFO2 CLIF: [103-001] "])
(obo/BFO_0000038 rdfs/isDefinedBy obo/bfo.owl)
(obo/BFO_0000038 rdfs/label ["one-dimensional temporal region" "en"])
(bnode/ccp-extensions_genid169 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid169 owl/annotatedSource obo/BFO_0000038)
(bnode/ccp-extensions_genid169 owl/annotatedProperty obo/IAO_0000600)
(bnode/ccp-extensions_genid169 owl/annotatedTarget ["A one-dimensional temporal region is a temporal region that is extended. (axiom label in BFO2 Reference: [103-001])" "en"])
 ;(bnode/ccp-extensions_genid169 obo/IAO_0010000 obo/bfo/axiom/103-001)
(bnode/ccp-extensions_genid170 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid170 owl/annotatedSource obo/BFO_0000038)
(bnode/ccp-extensions_genid170 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid170 owl/annotatedTarget ["(forall (x) (if (OneDimensionalTemporalRegion x) (TemporalRegion x))) // axiom label in BFO2 CLIF: [103-001] "])
 ;(bnode/ccp-extensions_genid170 obo/IAO_0010000 obo/bfo/axiom/103-001)
;; 
;; http://purl.obolibrary.org/obo/BFO_0000040
(obo/BFO_0000040 rdf/type owl/Class)
(obo/BFO_0000040 rdfs/subClassOf obo/BFO_0000004)
(obo/BFO_0000040 owl/disjointWith obo/BFO_0000141)
(obo/BFO_0000040 obo/BFO_0000179 ["material"])
(obo/BFO_0000040 obo/BFO_0000180 ["MaterialEntity"])
(obo/BFO_0000040 obo/IAO_0000112 ["a flame" "en"])
(obo/BFO_0000040 obo/IAO_0000112 ["a forest fire" "en"])
(obo/BFO_0000040 obo/IAO_0000112 ["a human being" "en"])
(obo/BFO_0000040 obo/IAO_0000112 ["a hurricane" "en"])
(obo/BFO_0000040 obo/IAO_0000112 ["a photon" "en"])
(obo/BFO_0000040 obo/IAO_0000112 ["a puff of smoke" "en"])
(obo/BFO_0000040 obo/IAO_0000112 ["a sea wave" "en"])
(obo/BFO_0000040 obo/IAO_0000112 ["a tornado" "en"])
(obo/BFO_0000040 obo/IAO_0000112 ["an aggregate of human beings." "en"])
(obo/BFO_0000040 obo/IAO_0000112 ["an energy wave" "en"])
(obo/BFO_0000040 obo/IAO_0000112 ["an epidemic" "en"])
(obo/BFO_0000040 obo/IAO_0000112 ["the undetached arm of a human being" "en"])
(obo/BFO_0000040 obo/IAO_0000115 ["An independent continuant that is spatially extended whose identity is independent of that of other entities and can be maintained through time." "en"])
(obo/BFO_0000040 obo/IAO_0000116 ["BFO 2 Reference: Material entities (continuants) can preserve their identity even while gaining and losing material parts. Continuants are contrasted with occurrents, which unfold themselves in successive temporal parts or phases [60" "en"])
(obo/BFO_0000040 obo/IAO_0000116 ["BFO 2 Reference: Object, Fiat Object Part and Object Aggregate are not intended to be exhaustive of Material Entity. Users are invited to propose new subcategories of Material Entity." "en"])
(obo/BFO_0000040 obo/IAO_0000116 ["BFO 2 Reference: \u2018Matter\u2019 is intended to encompass both mass and energy (we will address the ontological treatment of portions of energy in a later version of BFO). A portion of matter is anything that includes elementary particles among its proper or improper parts: quarks and leptons, including electrons, as the smallest particles thus far discovered; baryons (including protons and neutrons) at a higher level of granularity; atoms and molecules at still higher levels, forming the cells, organs, organisms and other material entities studied by biologists, the portions of rock studied by geologists, the fossils studied by paleontologists, and so on.Material entities are three-dimensional entities (entities extended in three spatial dimensions), as contrasted with the processes in which they participate, which are four-dimensional entities (entities extended also along the dimension of time).According to the FMA, material entities may have immaterial entities as parts \u2013 including the entities identified below as sites; for example the interior (or \u2018lumen\u2019) of your small intestine is a part of your body. BFO 2.0 embodies a decision to follow the FMA here." "en"])
(obo/BFO_0000040 obo/IAO_0000600 ["A material entity is an independent continuant that has some portion of matter as proper or improper continuant part. (axiom label in BFO2 Reference: [019-002])" "en"])
(obo/BFO_0000040 obo/IAO_0000601 ["Every entity which has a material entity as continuant part is a material entity. (axiom label in BFO2 Reference: [020-002])" "en"])
(obo/BFO_0000040 obo/IAO_0000601 ["every entity of which a material entity is continuant part is also a material entity. (axiom label in BFO2 Reference: [021-002])" "en"])
(obo/BFO_0000040 obo/IAO_0000602 ["(forall (x) (if (MaterialEntity x) (IndependentContinuant x))) // axiom label in BFO2 CLIF: [019-002] "])
(obo/BFO_0000040 obo/IAO_0000602 ["(forall (x) (if (and (Entity x) (exists (y t) (and (MaterialEntity y) (continuantPartOfAt x y t)))) (MaterialEntity x))) // axiom label in BFO2 CLIF: [021-002] "])
(obo/BFO_0000040 obo/IAO_0000602 ["(forall (x) (if (and (Entity x) (exists (y t) (and (MaterialEntity y) (continuantPartOfAt y x t)))) (MaterialEntity x))) // axiom label in BFO2 CLIF: [020-002] "])
(obo/BFO_0000040 rdfs/isDefinedBy obo/bfo.owl)
(obo/BFO_0000040 rdfs/label ["material entity" "en"])
(bnode/ccp-extensions_genid171 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid171 owl/annotatedSource obo/BFO_0000040)
(bnode/ccp-extensions_genid171 owl/annotatedProperty obo/IAO_0000600)
(bnode/ccp-extensions_genid171 owl/annotatedTarget ["A material entity is an independent continuant that has some portion of matter as proper or improper continuant part. (axiom label in BFO2 Reference: [019-002])" "en"])
 ;(bnode/ccp-extensions_genid171 obo/IAO_0010000 obo/bfo/axiom/019-002)
(bnode/ccp-extensions_genid172 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid172 owl/annotatedSource obo/BFO_0000040)
(bnode/ccp-extensions_genid172 owl/annotatedProperty obo/IAO_0000601)
(bnode/ccp-extensions_genid172 owl/annotatedTarget ["Every entity which has a material entity as continuant part is a material entity. (axiom label in BFO2 Reference: [020-002])" "en"])
 ;(bnode/ccp-extensions_genid172 obo/IAO_0010000 obo/bfo/axiom/020-002)
(bnode/ccp-extensions_genid173 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid173 owl/annotatedSource obo/BFO_0000040)
(bnode/ccp-extensions_genid173 owl/annotatedProperty obo/IAO_0000601)
(bnode/ccp-extensions_genid173 owl/annotatedTarget ["every entity of which a material entity is continuant part is also a material entity. (axiom label in BFO2 Reference: [021-002])" "en"])
 ;(bnode/ccp-extensions_genid173 obo/IAO_0010000 obo/bfo/axiom/021-002)
(bnode/ccp-extensions_genid174 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid174 owl/annotatedSource obo/BFO_0000040)
(bnode/ccp-extensions_genid174 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid174 owl/annotatedTarget ["(forall (x) (if (MaterialEntity x) (IndependentContinuant x))) // axiom label in BFO2 CLIF: [019-002] "])
 ;(bnode/ccp-extensions_genid174 obo/IAO_0010000 obo/bfo/axiom/019-002)
(bnode/ccp-extensions_genid175 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid175 owl/annotatedSource obo/BFO_0000040)
(bnode/ccp-extensions_genid175 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid175 owl/annotatedTarget ["(forall (x) (if (and (Entity x) (exists (y t) (and (MaterialEntity y) (continuantPartOfAt x y t)))) (MaterialEntity x))) // axiom label in BFO2 CLIF: [021-002] "])
 ;(bnode/ccp-extensions_genid175 obo/IAO_0010000 obo/bfo/axiom/021-002)
(bnode/ccp-extensions_genid176 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid176 owl/annotatedSource obo/BFO_0000040)
(bnode/ccp-extensions_genid176 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid176 owl/annotatedTarget ["(forall (x) (if (and (Entity x) (exists (y t) (and (MaterialEntity y) (continuantPartOfAt y x t)))) (MaterialEntity x))) // axiom label in BFO2 CLIF: [020-002] "])
 ;(bnode/ccp-extensions_genid176 obo/IAO_0010000 obo/bfo/axiom/020-002)
;; 
;; http://purl.obolibrary.org/obo/BFO_0000140
(obo/BFO_0000140 rdf/type owl/Class)
(obo/BFO_0000140 rdfs/subClassOf obo/BFO_0000141)
(obo/BFO_0000140 obo/BFO_0000179 ["cf-boundary"])
(obo/BFO_0000140 obo/BFO_0000180 ["ContinuantFiatBoundary"])
(obo/BFO_0000140 obo/IAO_0000115 ["b is a continuant fiat boundary = Def. b is an immaterial entity that is of zero, one or two dimensions and does not include a spatial region as part. (axiom label in BFO2 Reference: [029-001])" "en"])
(obo/BFO_0000140 obo/IAO_0000116 ["BFO 2 Reference: In BFO 1.1 the assumption was made that the external surface of a material entity such as a cell could be treated as if it were a boundary in the mathematical sense. The new document propounds the view that when we talk about external surfaces of material objects in this way then we are talking about something fiat. To be dealt with in a future version: fiat boundaries at different levels of granularity.More generally, the focus in discussion of boundaries in BFO 2.0 is now on fiat boundaries, which means: boundaries for which there is no assumption that they coincide with physical discontinuities. The ontology of boundaries becomes more closely allied with the ontology of regions." "en"])
(obo/BFO_0000140 obo/IAO_0000116 ["BFO 2 Reference: a continuant fiat boundary is a boundary of some material entity (for example: the plane separating the Northern and Southern hemispheres; the North Pole), or it is a boundary of some immaterial entity (for example of some portion of airspace). Three basic kinds of continuant fiat boundary can be distinguished (together with various combination kinds [29" "en"])
(obo/BFO_0000140 obo/IAO_0000116 ["Continuant fiat boundary doesn't have a closure axiom because the subclasses don't necessarily exhaust all possibilites. An example would be the mereological sum of two-dimensional continuant fiat boundary and a one dimensional continuant fiat boundary that doesn't overlap it. The situation is analogous to temporal and spatial regions." "en"])
(obo/BFO_0000140 obo/IAO_0000601 ["Every continuant fiat boundary is located at some spatial region at every time at which it exists" "en"])
(obo/BFO_0000140 obo/IAO_0000602 ["(iff (ContinuantFiatBoundary a) (and (ImmaterialEntity a) (exists (b) (and (or (ZeroDimensionalSpatialRegion b) (OneDimensionalSpatialRegion b) (TwoDimensionalSpatialRegion b)) (forall (t) (locatedInAt a b t)))) (not (exists (c t) (and (SpatialRegion c) (continuantPartOfAt c a t)))))) // axiom label in BFO2 CLIF: [029-001] "])
(obo/BFO_0000140 rdfs/isDefinedBy obo/bfo.owl)
(obo/BFO_0000140 rdfs/label ["continuant fiat boundary" "en"])
(bnode/ccp-extensions_genid177 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid177 owl/annotatedSource obo/BFO_0000140)
(bnode/ccp-extensions_genid177 owl/annotatedProperty obo/IAO_0000115)
(bnode/ccp-extensions_genid177 owl/annotatedTarget ["b is a continuant fiat boundary = Def. b is an immaterial entity that is of zero, one or two dimensions and does not include a spatial region as part. (axiom label in BFO2 Reference: [029-001])" "en"])
 ;(bnode/ccp-extensions_genid177 obo/IAO_0010000 obo/bfo/axiom/029-001)
(bnode/ccp-extensions_genid178 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid178 owl/annotatedSource obo/BFO_0000140)
(bnode/ccp-extensions_genid178 owl/annotatedProperty obo/IAO_0000116)
(bnode/ccp-extensions_genid178 owl/annotatedTarget ["Continuant fiat boundary doesn't have a closure axiom because the subclasses don't necessarily exhaust all possibilites. An example would be the mereological sum of two-dimensional continuant fiat boundary and a one dimensional continuant fiat boundary that doesn't overlap it. The situation is analogous to temporal and spatial regions." "en"])
 ;(bnode/ccp-extensions_genid178 obo/IAO_0010000 obo/bfo/axiom/0000008)
(bnode/ccp-extensions_genid179 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid179 owl/annotatedSource obo/BFO_0000140)
(bnode/ccp-extensions_genid179 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid179 owl/annotatedTarget ["(iff (ContinuantFiatBoundary a) (and (ImmaterialEntity a) (exists (b) (and (or (ZeroDimensionalSpatialRegion b) (OneDimensionalSpatialRegion b) (TwoDimensionalSpatialRegion b)) (forall (t) (locatedInAt a b t)))) (not (exists (c t) (and (SpatialRegion c) (continuantPartOfAt c a t)))))) // axiom label in BFO2 CLIF: [029-001] "])
 ;(bnode/ccp-extensions_genid179 obo/IAO_0010000 obo/bfo/axiom/029-001)
;; 
;; http://purl.obolibrary.org/obo/BFO_0000141
(obo/BFO_0000141 rdf/type owl/Class)
(obo/BFO_0000141 rdfs/subClassOf obo/BFO_0000004)
(obo/BFO_0000141 obo/BFO_0000179 ["immaterial"])
(obo/BFO_0000141 obo/BFO_0000180 ["ImmaterialEntity"])
(obo/BFO_0000141 obo/IAO_0000116 ["BFO 2 Reference: Immaterial entities are divided into two subgroups:boundaries and sites, which bound, or are demarcated in relation, to material entities, and which can thus change location, shape and size and as their material hosts move or change shape or size (for example: your nasal passage; the hold of a ship; the boundary of Wales (which moves with the rotation of the Earth) [38, 7, 10" "en"])
(obo/BFO_0000141 rdfs/isDefinedBy obo/bfo.owl)
(obo/BFO_0000141 rdfs/label ["immaterial entity" "en"])
;; 
;; http://purl.obolibrary.org/obo/BFO_0000142
(obo/BFO_0000142 rdf/type owl/Class)
(obo/BFO_0000142 rdfs/subClassOf obo/BFO_0000140)
(obo/BFO_0000142 owl/disjointWith obo/BFO_0000146)
(obo/BFO_0000142 owl/disjointWith obo/BFO_0000147)
(obo/BFO_0000142 obo/BFO_0000179 ["1d-cf-boundary"])
(obo/BFO_0000142 obo/BFO_0000180 ["OneDimensionalContinuantFiatBoundary"])
(obo/BFO_0000142 obo/IAO_0000112 ["The Equator" "en"])
(obo/BFO_0000142 obo/IAO_0000112 ["all geopolitical boundaries" "en"])
(obo/BFO_0000142 obo/IAO_0000112 ["all lines of latitude and longitude" "en"])
(obo/BFO_0000142 obo/IAO_0000112 ["the line separating the outer surface of the mucosa of the lower lip from the outer surface of the skin of the chin." "en"])
(obo/BFO_0000142 obo/IAO_0000112 ["the median sulcus of your tongue" "en"])
(obo/BFO_0000142 obo/IAO_0000600 ["a one-dimensional continuant fiat boundary is a continuous fiat line whose location is defined in relation to some material entity. (axiom label in BFO2 Reference: [032-001])" "en"])
(obo/BFO_0000142 obo/IAO_0000602 ["(iff (OneDimensionalContinuantFiatBoundary a) (and (ContinuantFiatBoundary a) (exists (b) (and (OneDimensionalSpatialRegion b) (forall (t) (locatedInAt a b t)))))) // axiom label in BFO2 CLIF: [032-001] "])
(obo/BFO_0000142 rdfs/isDefinedBy obo/bfo.owl)
(obo/BFO_0000142 rdfs/label ["one-dimensional continuant fiat boundary" "en"])
(bnode/ccp-extensions_genid180 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid180 owl/annotatedSource obo/BFO_0000142)
(bnode/ccp-extensions_genid180 owl/annotatedProperty obo/IAO_0000600)
(bnode/ccp-extensions_genid180 owl/annotatedTarget ["a one-dimensional continuant fiat boundary is a continuous fiat line whose location is defined in relation to some material entity. (axiom label in BFO2 Reference: [032-001])" "en"])
 ;(bnode/ccp-extensions_genid180 obo/IAO_0010000 obo/bfo/axiom/032-001)
(bnode/ccp-extensions_genid181 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid181 owl/annotatedSource obo/BFO_0000142)
(bnode/ccp-extensions_genid181 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid181 owl/annotatedTarget ["(iff (OneDimensionalContinuantFiatBoundary a) (and (ContinuantFiatBoundary a) (exists (b) (and (OneDimensionalSpatialRegion b) (forall (t) (locatedInAt a b t)))))) // axiom label in BFO2 CLIF: [032-001] "])
 ;(bnode/ccp-extensions_genid181 obo/IAO_0010000 obo/bfo/axiom/032-001)
;; 
;; http://purl.obolibrary.org/obo/BFO_0000144
(obo/BFO_0000144 rdf/type owl/Class)
(obo/BFO_0000144 rdfs/subClassOf obo/BFO_0000015)
(obo/BFO_0000144 owl/disjointWith obo/BFO_0000182)
(obo/BFO_0000144 obo/BFO_0000179 ["process-profile"])
(obo/BFO_0000144 obo/BFO_0000180 ["ProcessProfile"])
(obo/BFO_0000144 obo/IAO_0000112 ["On a somewhat higher level of complexity are what we shall call rate process profiles, which are the targets of selective abstraction focused not on determinate quality magnitudes plotted over time, but rather on certain ratios between these magnitudes and elapsed times. A speed process profile, for example, is represented by a graph plotting against time the ratio of distance covered per unit of time. Since rates may change, and since such changes, too, may have rates of change, we have to deal here with a hierarchy of process profile universals at successive levels" "en"])
(obo/BFO_0000144 obo/IAO_0000112 ["One important sub-family of rate process profiles is illustrated by the beat or frequency profiles of cyclical processes, illustrated by the 60 beats per minute beating process of John\u2019s heart, or the 120 beats per minute drumming process involved in one of John\u2019s performances in a rock band, and so on. Each such process includes what we shall call a beat process profile instance as part, a subtype of rate process profile in which the salient ratio is not distance covered but rather number of beat cycles per unit of time. Each beat process profile instance instantiates the determinable universal beat process profile. But it also instantiates multiple more specialized universals at lower levels of generality, selected from   rate process profilebeat process profileregular beat process profile3 bpm beat process profile4 bpm beat process profileirregular beat process profileincreasing beat process profileand so on.In the case of a regular beat process profile, a rate can be assigned in the simplest possible fashion by dividing the number of cycles by the length of the temporal region occupied by the beating process profile as a whole. Irregular process profiles of this sort, for example as identified in the clinic, or in the readings on an aircraft instrument panel, are often of diagnostic significance." "en"])
(obo/BFO_0000144 obo/IAO_0000112 ["The simplest type of process profiles are what we shall call \u2018quality process profiles\u2019, which are the process profiles which serve as the foci of the sort of selective abstraction that is involved when measurements are made of changes in single qualities, as illustrated, for example, by process profiles of mass, temperature, aortic pressure, and so on." "en"])
(obo/BFO_0000144 obo/IAO_0000115 ["b is a process_profile =Def. there is some process c such that b process_profile_of c (axiom label in BFO2 Reference: [093-002])" "en"])
(obo/BFO_0000144 obo/IAO_0000600 ["b process_profile_of c holds when b proper_occurrent_part_of c& there is some proper_occurrent_part d of c which has no parts in common with b & is mutually dependent on b& is such that b, c and d occupy the same temporal region (axiom label in BFO2 Reference: [094-005])" "en"])
(obo/BFO_0000144 obo/IAO_0000602 ["(forall (x y) (if (processProfileOf x y) (and (properContinuantPartOf x y) (exists (z t) (and (properOccurrentPartOf z y) (TemporalRegion t) (occupiesSpatioTemporalRegion x t) (occupiesSpatioTemporalRegion y t) (occupiesSpatioTemporalRegion z t) (not (exists (w) (and (occurrentPartOf w x) (occurrentPartOf w z))))))))) // axiom label in BFO2 CLIF: [094-005] "])
(obo/BFO_0000144 obo/IAO_0000602 ["(iff (ProcessProfile a) (exists (b) (and (Process b) (processProfileOf a b)))) // axiom label in BFO2 CLIF: [093-002] "])
(obo/BFO_0000144 rdfs/isDefinedBy obo/bfo.owl)
(obo/BFO_0000144 rdfs/label ["process profile" "en"])
(bnode/ccp-extensions_genid182 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid182 owl/annotatedSource obo/BFO_0000144)
(bnode/ccp-extensions_genid182 owl/annotatedProperty obo/IAO_0000115)
(bnode/ccp-extensions_genid182 owl/annotatedTarget ["b is a process_profile =Def. there is some process c such that b process_profile_of c (axiom label in BFO2 Reference: [093-002])" "en"])
 ;(bnode/ccp-extensions_genid182 obo/IAO_0010000 obo/bfo/axiom/093-002)
(bnode/ccp-extensions_genid183 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid183 owl/annotatedSource obo/BFO_0000144)
(bnode/ccp-extensions_genid183 owl/annotatedProperty obo/IAO_0000600)
(bnode/ccp-extensions_genid183 owl/annotatedTarget ["b process_profile_of c holds when b proper_occurrent_part_of c& there is some proper_occurrent_part d of c which has no parts in common with b & is mutually dependent on b& is such that b, c and d occupy the same temporal region (axiom label in BFO2 Reference: [094-005])" "en"])
 ;(bnode/ccp-extensions_genid183 obo/IAO_0010000 obo/bfo/axiom/094-005)
(bnode/ccp-extensions_genid184 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid184 owl/annotatedSource obo/BFO_0000144)
(bnode/ccp-extensions_genid184 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid184 owl/annotatedTarget ["(forall (x y) (if (processProfileOf x y) (and (properContinuantPartOf x y) (exists (z t) (and (properOccurrentPartOf z y) (TemporalRegion t) (occupiesSpatioTemporalRegion x t) (occupiesSpatioTemporalRegion y t) (occupiesSpatioTemporalRegion z t) (not (exists (w) (and (occurrentPartOf w x) (occurrentPartOf w z))))))))) // axiom label in BFO2 CLIF: [094-005] "])
 ;(bnode/ccp-extensions_genid184 obo/IAO_0010000 obo/bfo/axiom/094-005)
(bnode/ccp-extensions_genid185 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid185 owl/annotatedSource obo/BFO_0000144)
(bnode/ccp-extensions_genid185 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid185 owl/annotatedTarget ["(iff (ProcessProfile a) (exists (b) (and (Process b) (processProfileOf a b)))) // axiom label in BFO2 CLIF: [093-002] "])
 ;(bnode/ccp-extensions_genid185 obo/IAO_0010000 obo/bfo/axiom/093-002)
;; 
;; http://purl.obolibrary.org/obo/BFO_0000145
(obo/BFO_0000145 rdf/type owl/Class)
(obo/BFO_0000145 rdfs/subClassOf obo/BFO_0000019)
(obo/BFO_0000145 obo/BFO_0000179 ["r-quality"])
(obo/BFO_0000145 obo/BFO_0000180 ["RelationalQuality"])
(obo/BFO_0000145 obo/IAO_0000112 ["John\u2019s role of husband to Mary is dependent on Mary\u2019s role of wife to John, and both are dependent on the object aggregate comprising John and Mary as member parts joined together through the relational quality of being married." "en"])
(obo/BFO_0000145 obo/IAO_0000112 ["a marriage bond, an instance of love, an obligation between one person and another." "en"])
(obo/BFO_0000145 obo/IAO_0000115 ["b is a relational quality = Def. for some independent continuants c, d and for some time t: b quality_of c at t & b quality_of d at t. (axiom label in BFO2 Reference: [057-001])" "en"])
(obo/BFO_0000145 obo/IAO_0000602 ["(iff (RelationalQuality a) (exists (b c t) (and (IndependentContinuant b) (IndependentContinuant c) (qualityOfAt a b t) (qualityOfAt a c t)))) // axiom label in BFO2 CLIF: [057-001] "])
(obo/BFO_0000145 rdfs/isDefinedBy obo/bfo.owl)
(obo/BFO_0000145 rdfs/label ["relational quality" "en"])
(bnode/ccp-extensions_genid186 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid186 owl/annotatedSource obo/BFO_0000145)
(bnode/ccp-extensions_genid186 owl/annotatedProperty obo/IAO_0000115)
(bnode/ccp-extensions_genid186 owl/annotatedTarget ["b is a relational quality = Def. for some independent continuants c, d and for some time t: b quality_of c at t & b quality_of d at t. (axiom label in BFO2 Reference: [057-001])" "en"])
 ;(bnode/ccp-extensions_genid186 obo/IAO_0010000 obo/bfo/axiom/057-001)
(bnode/ccp-extensions_genid187 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid187 owl/annotatedSource obo/BFO_0000145)
(bnode/ccp-extensions_genid187 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid187 owl/annotatedTarget ["(iff (RelationalQuality a) (exists (b c t) (and (IndependentContinuant b) (IndependentContinuant c) (qualityOfAt a b t) (qualityOfAt a c t)))) // axiom label in BFO2 CLIF: [057-001] "])
 ;(bnode/ccp-extensions_genid187 obo/IAO_0010000 obo/bfo/axiom/057-001)
;; 
;; http://purl.obolibrary.org/obo/BFO_0000146
(obo/BFO_0000146 rdf/type owl/Class)
(obo/BFO_0000146 rdfs/subClassOf obo/BFO_0000140)
(obo/BFO_0000146 obo/BFO_0000179 ["2d-cf-boundary"])
(obo/BFO_0000146 obo/BFO_0000180 ["TwoDimensionalContinuantFiatBoundary"])
(obo/BFO_0000146 obo/IAO_0000600 ["a two-dimensional continuant fiat boundary (surface) is a self-connected fiat surface whose location is defined in relation to some material entity. (axiom label in BFO2 Reference: [033-001])" "en"])
(obo/BFO_0000146 obo/IAO_0000602 ["(iff (TwoDimensionalContinuantFiatBoundary a) (and (ContinuantFiatBoundary a) (exists (b) (and (TwoDimensionalSpatialRegion b) (forall (t) (locatedInAt a b t)))))) // axiom label in BFO2 CLIF: [033-001] "])
(obo/BFO_0000146 rdfs/isDefinedBy obo/bfo.owl)
(obo/BFO_0000146 rdfs/label ["two-dimensional continuant fiat boundary" "en"])
(bnode/ccp-extensions_genid188 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid188 owl/annotatedSource obo/BFO_0000146)
(bnode/ccp-extensions_genid188 owl/annotatedProperty obo/IAO_0000600)
(bnode/ccp-extensions_genid188 owl/annotatedTarget ["a two-dimensional continuant fiat boundary (surface) is a self-connected fiat surface whose location is defined in relation to some material entity. (axiom label in BFO2 Reference: [033-001])" "en"])
 ;(bnode/ccp-extensions_genid188 obo/IAO_0010000 obo/bfo/axiom/033-001)
(bnode/ccp-extensions_genid189 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid189 owl/annotatedSource obo/BFO_0000146)
(bnode/ccp-extensions_genid189 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid189 owl/annotatedTarget ["(iff (TwoDimensionalContinuantFiatBoundary a) (and (ContinuantFiatBoundary a) (exists (b) (and (TwoDimensionalSpatialRegion b) (forall (t) (locatedInAt a b t)))))) // axiom label in BFO2 CLIF: [033-001] "])
 ;(bnode/ccp-extensions_genid189 obo/IAO_0010000 obo/bfo/axiom/033-001)
;; 
;; http://purl.obolibrary.org/obo/BFO_0000147
(obo/BFO_0000147 rdf/type owl/Class)
(obo/BFO_0000147 rdfs/subClassOf obo/BFO_0000140)
(obo/BFO_0000147 obo/BFO_0000179 ["0d-cf-boundary"])
(obo/BFO_0000147 obo/BFO_0000180 ["ZeroDimensionalContinuantFiatBoundary"])
(obo/BFO_0000147 obo/IAO_0000112 ["the geographic North Pole" "en"])
(obo/BFO_0000147 obo/IAO_0000112 ["the point of origin of some spatial coordinate system." "en"])
(obo/BFO_0000147 obo/IAO_0000112 ["the quadripoint where the boundaries of Colorado, Utah, New Mexico, and Arizona meet" "en"])
(obo/BFO_0000147 obo/IAO_0000116 ["zero dimension continuant fiat boundaries are not spatial points. Considering the example 'the quadripoint where the boundaries of Colorado, Utah, New Mexico, and Arizona meet' : There are many frames in which that point is zooming through many points in space. Whereas, no matter what the frame, the quadripoint is always in the same relation to the boundaries of Colorado, Utah, New Mexico, and Arizona." "en"])
(obo/BFO_0000147 obo/IAO_0000600 ["a zero-dimensional continuant fiat boundary is a fiat point whose location is defined in relation to some material entity. (axiom label in BFO2 Reference: [031-001])" "en"])
(obo/BFO_0000147 obo/IAO_0000602 ["(iff (ZeroDimensionalContinuantFiatBoundary a) (and (ContinuantFiatBoundary a) (exists (b) (and (ZeroDimensionalSpatialRegion b) (forall (t) (locatedInAt a b t)))))) // axiom label in BFO2 CLIF: [031-001] "])
(obo/BFO_0000147 rdfs/isDefinedBy obo/bfo.owl)
(obo/BFO_0000147 rdfs/label ["zero-dimensional continuant fiat boundary" "en"])
(bnode/ccp-extensions_genid190 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid190 owl/annotatedSource obo/BFO_0000147)
(bnode/ccp-extensions_genid190 owl/annotatedProperty obo/IAO_0000116)
(bnode/ccp-extensions_genid190 owl/annotatedTarget ["zero dimension continuant fiat boundaries are not spatial points. Considering the example 'the quadripoint where the boundaries of Colorado, Utah, New Mexico, and Arizona meet' : There are many frames in which that point is zooming through many points in space. Whereas, no matter what the frame, the quadripoint is always in the same relation to the boundaries of Colorado, Utah, New Mexico, and Arizona." "en"])
 ;(bnode/ccp-extensions_genid190 obo/IAO_0010000 obo/bfo/axiom/0000001)
(bnode/ccp-extensions_genid190 rdfs/comment ["requested by Melanie Courtot"])
;(bnode/ccp-extensions_genid190 rdfs/seeAlso <https://groups.google.com/d/msg/bfo-owl-devel/s9Uug5QmAws/ZDRnpiIi_TUJ)
(bnode/ccp-extensions_genid191 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid191 owl/annotatedSource obo/BFO_0000147)
(bnode/ccp-extensions_genid191 owl/annotatedProperty obo/IAO_0000600)
(bnode/ccp-extensions_genid191 owl/annotatedTarget ["a zero-dimensional continuant fiat boundary is a fiat point whose location is defined in relation to some material entity. (axiom label in BFO2 Reference: [031-001])" "en"])
 ;(bnode/ccp-extensions_genid191 obo/IAO_0010000 obo/bfo/axiom/031-001)
(bnode/ccp-extensions_genid192 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid192 owl/annotatedSource obo/BFO_0000147)
(bnode/ccp-extensions_genid192 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid192 owl/annotatedTarget ["(iff (ZeroDimensionalContinuantFiatBoundary a) (and (ContinuantFiatBoundary a) (exists (b) (and (ZeroDimensionalSpatialRegion b) (forall (t) (locatedInAt a b t)))))) // axiom label in BFO2 CLIF: [031-001] "])
 ;(bnode/ccp-extensions_genid192 obo/IAO_0010000 obo/bfo/axiom/031-001)
;; 
;; http://purl.obolibrary.org/obo/BFO_0000148
(obo/BFO_0000148 rdf/type owl/Class)
(obo/BFO_0000148 rdfs/subClassOf obo/BFO_0000008)
(obo/BFO_0000148 obo/BFO_0000179 ["0d-t-region"])
(obo/BFO_0000148 obo/BFO_0000180 ["ZeroDimensionalTemporalRegion"])
(obo/BFO_0000148 obo/IAO_0000112 ["a temporal region that is occupied by a process boundary" "en"])
(obo/BFO_0000148 obo/IAO_0000112 ["right now" "en"])
(obo/BFO_0000148 obo/IAO_0000112 ["the moment at which a child is born" "en"])
(obo/BFO_0000148 obo/IAO_0000112 ["the moment at which a finger is detached in an industrial accident" "en"])
(obo/BFO_0000148 obo/IAO_0000112 ["the moment of death." "en"])
(obo/BFO_0000148 obo/IAO_0000118 ["temporal instant." "en"])
(obo/BFO_0000148 obo/IAO_0000600 ["A zero-dimensional temporal region is a temporal region that is without extent. (axiom label in BFO2 Reference: [102-001])" "en"])
(obo/BFO_0000148 obo/IAO_0000602 ["(forall (x) (if (ZeroDimensionalTemporalRegion x) (TemporalRegion x))) // axiom label in BFO2 CLIF: [102-001] "])
(obo/BFO_0000148 rdfs/isDefinedBy obo/bfo.owl)
(obo/BFO_0000148 rdfs/label ["zero-dimensional temporal region" "en"])
(bnode/ccp-extensions_genid193 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid193 owl/annotatedSource obo/BFO_0000148)
(bnode/ccp-extensions_genid193 owl/annotatedProperty obo/IAO_0000600)
(bnode/ccp-extensions_genid193 owl/annotatedTarget ["A zero-dimensional temporal region is a temporal region that is without extent. (axiom label in BFO2 Reference: [102-001])" "en"])
 ;(bnode/ccp-extensions_genid193 obo/IAO_0010000 obo/bfo/axiom/102-001)
(bnode/ccp-extensions_genid194 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid194 owl/annotatedSource obo/BFO_0000148)
(bnode/ccp-extensions_genid194 owl/annotatedProperty obo/IAO_0000602)
(bnode/ccp-extensions_genid194 owl/annotatedTarget ["(forall (x) (if (ZeroDimensionalTemporalRegion x) (TemporalRegion x))) // axiom label in BFO2 CLIF: [102-001] "])
 ;(bnode/ccp-extensions_genid194 obo/IAO_0010000 obo/bfo/axiom/102-001)
;; 
;; http://purl.obolibrary.org/obo/BFO_0000182
(obo/BFO_0000182 rdf/type owl/Class)
(obo/BFO_0000182 rdfs/subClassOf obo/BFO_0000015)
(obo/BFO_0000182 obo/BFO_0000179 ["history"])
(obo/BFO_0000182 obo/BFO_0000180 ["History"])
(obo/BFO_0000182 obo/IAO_0000600 ["A history is a process that is the sum of the totality of processes taking place in the spatiotemporal region occupied by a material entity or site, including processes on the surface of the entity or within the cavities to which it serves as host. (axiom label in BFO2 Reference: [138-001])" "en"])
(obo/BFO_0000182 rdfs/isDefinedBy obo/bfo.owl)
(obo/BFO_0000182 rdfs/label ["history" "en"])
(bnode/ccp-extensions_genid195 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid195 owl/annotatedSource obo/BFO_0000182)
(bnode/ccp-extensions_genid195 owl/annotatedProperty obo/IAO_0000600)
(bnode/ccp-extensions_genid195 owl/annotatedTarget ["A history is a process that is the sum of the totality of processes taking place in the spatiotemporal region occupied by a material entity or site, including processes on the surface of the entity or within the cavities to which it serves as host. (axiom label in BFO2 Reference: [138-001])" "en"])
 ;(bnode/ccp-extensions_genid195 obo/IAO_0010000 obo/bfo/axiom/138-001)
;; 
;; http://purl.obolibrary.org/obo/GAZ_00000448
(obo/GAZ_00000448 rdf/type owl/Class)
(obo/GAZ_00000448 rdfs/subClassOf obo/BFO_0000029)
(obo/GAZ_00000448 obo/IAO_0000111 ["geographic location"])
(obo/GAZ_00000448 obo/IAO_0000115 ["A reference to a place on the Earth, by its name or by its geographical location."])
(obo/GAZ_00000448 obo/IAO_0000412 obo/gaz.owl)
(obo/GAZ_00000448 rdfs/label ["geographic location" "en"]) ;; added "en" to allow labels to match during unit testing
;; 
;; http://purl.obolibrary.org/obo/IAO_0000001
(obo/IAO_0000001 rdf/type owl/Class)
(obo/IAO_0000001 rdfs/subClassOf obo/IAO_0000033)
(obo/IAO_0000001 obo/IAO_0000111 ["conditional specification" "en"])
(obo/IAO_0000001 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000001 obo/IAO_0000115 ["a directive information entity that specifies what should happen if the trigger condition is fulfilled" "en"])
(obo/IAO_0000001 obo/IAO_0000117 ["PlanAndPlannedProcess Branch" "en"])
(obo/IAO_0000001 obo/IAO_0000119 ["OBI branch derived" "en"])
(obo/IAO_0000001 obo/IAO_0000119 ["OBI_0000349" "en"])
(obo/IAO_0000001 rdfs/label ["conditional specification" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000003
(obo/IAO_0000003 rdf/type owl/Class)
(obo/IAO_0000003 rdfs/subClassOf obo/IAO_0000009)
(obo/IAO_0000003 obo/IAO_0000111 ["measurement unit label" "en"])
(obo/IAO_0000003 obo/IAO_0000112 ["Examples of measurement unit labels are liters, inches, weight per volume." "en"])
(obo/IAO_0000003 obo/IAO_0000114 obo/IAO_0000125)
(obo/IAO_0000003 obo/IAO_0000115 ["A measurement unit label is as a label that is part of a scalar measurement datum and denotes a unit of measure." "en"])
(obo/IAO_0000003 obo/IAO_0000116 ["2009-03-16: provenance: a term measurement unit was\nproposed for OBI (OBI_0000176) , edited by Chris Stoeckert and\nCristian Cocos, and subsequently moved to IAO where the objective for\nwhich the original term was defined was satisfied with the definition\nof this, different, term." "en"])
(obo/IAO_0000003 obo/IAO_0000116 ["2009-03-16: review of this term done during during the OBI workshop winter 2009 and the current definition was considered acceptable for use in OBI. If there is a need to modify this definition please notify OBI." "en"])
(obo/IAO_0000003 obo/IAO_0000117 ["PERSON: Alan Ruttenberg" "en"])
(obo/IAO_0000003 obo/IAO_0000117 ["PERSON: Melanie Courtot" "en"])
(obo/IAO_0000003 rdfs/label ["measurement unit label" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000005
(obo/IAO_0000005 rdf/type owl/Class)
(obo/IAO_0000005 rdfs/subClassOf obo/IAO_0000033)
(obo/IAO_0000005 obo/IAO_0000111 ["objective specification" "en"])
(obo/IAO_0000005 obo/IAO_0000112 ["In the protocol of a ChIP assay the objective specification says to identify protein and DNA interaction." "en"])
(obo/IAO_0000005 obo/IAO_0000114 obo/IAO_0000122)
(obo/IAO_0000005 obo/IAO_0000115 ["a directive information entity that describes an intended process endpoint. When part of a plan specification the concretization is realized in a planned process in which the bearer tries to effect the world so that the process endpoint is achieved." "en"])
(obo/IAO_0000005 obo/IAO_0000116 ["2009-03-16: original definition when imported from OBI read: \"objective is an non realizable information entity which can serve as that  proper part of a plan towards which the realization of the plan is directed.\"" "en"])
(obo/IAO_0000005 obo/IAO_0000116 ["2014-03-31: In the example of usage (\"In the protocol of a ChIP assay the objective specification says to identify protein and DNA interaction\") there is a protocol which is the ChIP assay protocol. In addition to being concretized on paper, the protocol can be concretized as a realizable entity, such as a plan that inheres in a person. The objective specification is the part that says that some protein and DNA interactions are identified. This is a specification of a process endpoint: the boundary in the process before which they are not identified and after which they are. During the realization of the plan, the goal is to get to the point of having the interactions, and participants in the realization of the plan try to do that." "en"])
(obo/IAO_0000005 obo/IAO_0000116 ["Answers the question, why did you do this experiment?" "en"])
(obo/IAO_0000005 obo/IAO_0000117 ["PERSON: Alan Ruttenberg" "en"])
(obo/IAO_0000005 obo/IAO_0000117 ["PERSON: Barry Smith" "en"])
(obo/IAO_0000005 obo/IAO_0000117 ["PERSON: Bjoern Peters" "en"])
(obo/IAO_0000005 obo/IAO_0000117 ["PERSON: Jennifer Fostel" "en"])
(obo/IAO_0000005 obo/IAO_0000118 ["goal specification" "en"])
(obo/IAO_0000005 obo/IAO_0000119 ["OBI Plan and Planned Process/Roles Branch" "en"])
(obo/IAO_0000005 obo/IAO_0000119 ["OBI_0000217" "en"])
(obo/IAO_0000005 rdfs/label ["objective specification" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000006
(obo/IAO_0000006 rdf/type owl/Class)
(obo/IAO_0000006 rdfs/subClassOf obo/IAO_0000030)
(obo/IAO_0000006 obo/IAO_0000111 ["narrative object" "en"])
(obo/IAO_0000006 obo/IAO_0000112 ["Examples of narrative objects are reports, journal  articles, and patents submission." "en"])
(obo/IAO_0000006 obo/IAO_0000114 obo/IAO_0000123)
(obo/IAO_0000006 obo/IAO_0000115 ["A narrative object is an information content entity that is a set of propositions." "en"])
(obo/IAO_0000006 obo/IAO_0000116 ["2009-08-10 Alan Ruttenberg: Larry Hunter suggests that this be obsoleted and replaced by 'textual entity' and 'figure'. Alan restored as there are OBI dependencies and this merits further discussion" "en"])
(obo/IAO_0000006 obo/IAO_0000116 ["agree - DENRIE. Issue(alan) do we only mean text? What about a story told by mime. Does music count? (no) what about an oral report. Regarding definition, saying it is a set of propositions means we loose the idea that wording matters. Maybe adjust saying a narrative object has some relationshop to a set of propositions" "en"])
(obo/IAO_0000006 obo/IAO_0000117 ["person:Chris Stoeckert" "en"])
(obo/IAO_0000006 obo/IAO_0000119 ["OBI_0000013" "en"])
(obo/IAO_0000006 obo/IAO_0000119 ["group:OBI" "en"])
(obo/IAO_0000006 rdfs/label ["narrative object" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000007
(obo/IAO_0000007 rdf/type owl/Class)
(obo/IAO_0000007 rdfs/subClassOf obo/IAO_0000033)
(obo/IAO_0000007 obo/IAO_0000112 ["Pour the contents of flask 1 into flask 2" "en"])
(obo/IAO_0000007 obo/IAO_0000114 obo/IAO_0000122)
(obo/IAO_0000007 obo/IAO_0000115 ["a directive information entity that describes an action the bearer will take" "en"])
(obo/IAO_0000007 obo/IAO_0000117 ["Alan Ruttenberg" "en"])
(obo/IAO_0000007 obo/IAO_0000119 ["OBI Plan and Planned Process branch" "en"])
(obo/IAO_0000007 rdfs/label ["action specification" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000008
(obo/IAO_0000008 rdf/type owl/Class)
(obo/IAO_0000008 rdfs/subClassOf oboInOwl/ObsoleteClass)
(obo/IAO_0000008 obo/IAO_0000231 obo/IAO_0000103)
(obo/IAO_0000008 rdfs/label ["obsolete_artifact"])
(obo/IAO_0000008 owl/deprecated true)
;; 
;; http://purl.obolibrary.org/obo/IAO_0000009
(obo/IAO_0000009 rdf/type owl/Class)
(obo/IAO_0000009 rdfs/subClassOf obo/IAO_0000030)
(obo/IAO_0000009 obo/IAO_0000111 ["datum label" "en"])
(obo/IAO_0000009 obo/IAO_0000114 obo/IAO_0000123)
(obo/IAO_0000009 obo/IAO_0000115 ["A label is a symbol that is part of some other datum and is used to either partially define  the denotation of that datum or to provide a means for identifying the datum as a member of the set of data with the same label" "en"])
(obo/IAO_0000009 obo/IAO_0000116 ["http://www.golovchenko.org/cgi-bin/wnsearch?q=label;;4n" "en"])
(obo/IAO_0000009 obo/IAO_0000117 ["GROUP: IAO" "en"])
(obo/IAO_0000009 obo/IAO_0000232 ["9/22/11 BP: changed the rdfs:label for this class from 'label' to 'datum label' to convey that this class is not intended to cover all kinds of labels (stickers, radiolabels, etc.), and not even all kind of textual labels, but rather the kind of labels occuring in a datum. \n"])
(obo/IAO_0000009 rdfs/label ["datum label" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000010
(obo/IAO_0000010 rdf/type owl/Class)
(obo/IAO_0000010 rdfs/subClassOf obo/IAO_0000104)
(obo/IAO_0000010 obo/IAO_0000111 ["software" "en"])
(obo/IAO_0000010 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000010 obo/IAO_0000115 ["Software is a plan specification composed of a series of instructions that can be \ninterpreted by or directly executed by a processing unit." "en"])
(obo/IAO_0000010 obo/IAO_0000116 ["see sourceforge tracker discussion at http://sourceforge.net/tracker/index.php?func=detail&aid=1958818&group_id=177891&atid=886178" "en"])
(obo/IAO_0000010 obo/IAO_0000117 ["PERSON: Alan Ruttenberg" "en"])
(obo/IAO_0000010 obo/IAO_0000117 ["PERSON: Bjoern Peters" "en"])
(obo/IAO_0000010 obo/IAO_0000117 ["PERSON: Chris Stoeckert" "en"])
(obo/IAO_0000010 obo/IAO_0000117 ["PERSON: Melanie Courtot" "en"])
(obo/IAO_0000010 obo/IAO_0000119 ["GROUP: OBI" "en"])
(obo/IAO_0000010 rdfs/label ["software" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000012
(obo/IAO_0000012 rdf/type owl/Class)
(obo/IAO_0000012 rdfs/subClassOf oboInOwl/ObsoleteClass)
(obo/IAO_0000012 obo/IAO_0000111 ["obsolete_digital entity" "en"])
(obo/IAO_0000012 obo/IAO_0000115 ["A digital entity is an information entity which is a collection of bits that can be interpreted by a computer. Two digital entities are the same if they are bitwise identical." "en"])
(obo/IAO_0000012 obo/IAO_0000116 ["3/22/2009 Alan Ruttenberg, obsoleted per http://groups.google.com/group/information-ontology/browse_thread/thread/789ad4b7708d5cf4\n\nSuperclass was 'digitial quality'" "en"])
(obo/IAO_0000012 obo/IAO_0000117 ["person:Chris Stoeckert" "en"])
(obo/IAO_0000012 obo/IAO_0000119 ["OBI_0000261"])
(obo/IAO_0000012 obo/IAO_0000119 ["group:OBI" "en"])
(obo/IAO_0000012 obo/IAO_0000231 obo/IAO_0000103)
(obo/IAO_0000012 rdfs/label ["obsolete2_digital entity" "en"])
(obo/IAO_0000012 owl/deprecated true)
;; 
;; http://purl.obolibrary.org/obo/IAO_0000013
(obo/IAO_0000013 rdf/type owl/Class)
(obo/IAO_0000013 rdfs/subClassOf obo/IAO_0000088)
(obo/IAO_0000013 obo/IAO_0000111 ["journal article" "en"])
(obo/IAO_0000013 obo/IAO_0000112 ["Examples are articles published in the journals, Nature and Science. The content can often be cited by reference to a paper based encoding, e.g.  Authors, Title of article, Journal name, date or year of publication, volume and page number." "en"])
(obo/IAO_0000013 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000013 obo/IAO_0000115 ["a report that is published in a journal" "en"])
(obo/IAO_0000013 obo/IAO_0000117 ["person:Alan Ruttenberg" "en"])
(obo/IAO_0000013 obo/IAO_0000117 ["person:Chris Stoeckert" "en"])
(obo/IAO_0000013 obo/IAO_0000119 ["OBI_0000159" "en"])
(obo/IAO_0000013 obo/IAO_0000119 ["group:OBI" "en"])
(obo/IAO_0000013 rdfs/label ["journal article" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000015
(obo/IAO_0000015 rdf/type owl/Class)
(obo/IAO_0000015 owl/equivalentClass bnode/ccp-extensions_genid196)
(bnode/ccp-extensions_genid196 owl/intersectionOf bnode/ccp-extensions_genid199)
(bnode/ccp-extensions_genid199 rdf/type rdf/List)
(bnode/ccp-extensions_genid199 rdf/first obo/BFO_0000019)
(bnode/ccp-extensions_genid199 rdf/rest bnode/ccp-extensions_genid197)
(bnode/ccp-extensions_genid197 rdf/type rdf/List)
(bnode/ccp-extensions_genid197 rdf/first bnode/ccp-extensions_genid198)
(bnode/ccp-extensions_genid198 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid198 owl/onProperty obo/RO_0000059)
(bnode/ccp-extensions_genid198 owl/someValuesFrom obo/IAO_0000030)
(bnode/ccp-extensions_genid197 rdf/rest rdf/nil)
(bnode/ccp-extensions_genid196 rdf/type owl/Class)
(obo/IAO_0000015 rdfs/subClassOf obo/BFO_0000019)
(obo/IAO_0000015 obo/IAO_0000111 ["information carrier" "en"])
(obo/IAO_0000015 obo/IAO_0000112 ["In the case of a printed paperback novel the physicality of the ink and of the paper form part of the information bearer. The qualities of appearing black and having a certain pattern for the ink and appearing white for the paper form part of the information carrier in this case." "en"])
(obo/IAO_0000015 obo/IAO_0000114 obo/IAO_0000125)
(obo/IAO_0000015 obo/IAO_0000115 ["A quality of an information bearer that imparts the information content" "en"])
(obo/IAO_0000015 obo/IAO_0000116 ["12/15/09: There is a concern that some ways that carry information may be processes rather than qualities, such as in a 'delayed wave carrier'." "en"])
(obo/IAO_0000015 obo/IAO_0000116 ["2014-03-10: We are not certain that all information carriers are qualities. There was a discussion of dropping it."])
(obo/IAO_0000015 obo/IAO_0000117 ["PERSON: Alan Ruttenberg" "en"])
(obo/IAO_0000015 obo/IAO_0000119 ["Smith, Ceusters, Ruttenberg, 2000 years of philosophy" "en"])
(obo/IAO_0000015 rdfs/label ["information carrier" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000017
(obo/IAO_0000017 rdf/type owl/Class)
(obo/IAO_0000017 rdfs/subClassOf obo/IAO_0000028)
(obo/IAO_0000017 obo/IAO_0000111 ["model number" "en"])
(obo/IAO_0000017 obo/IAO_0000114 obo/IAO_0000125)
(obo/IAO_0000017 obo/IAO_0000115 ["A model number is an information content entity specifically borne by catalogs, design specifications, advertising materials, inventory systems and similar  that is about manufactured objects of the same class. The model number is an alternative term for the class. The manufactered objects may or may not also bear the model number. Model numbers can be encoded in a variety of other information objects, such as bar codes, numerals, or patterns of dots." "en"])
(obo/IAO_0000017 obo/IAO_0000116 ["manufactered items may have more than one model number, sometimes by rebranding, or because companies are sold and the products issued new model numbers" "en"])
(obo/IAO_0000017 obo/IAO_0000117 ["Person: Alan Ruttenberg" "en"])
(obo/IAO_0000017 rdfs/label ["model number" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000018
(obo/IAO_0000018 rdf/type owl/Class)
(obo/IAO_0000018 rdfs/subClassOf oboInOwl/ObsoleteClass)
(obo/IAO_0000018 obo/IAO_0000231 obo/IAO_0000226)
(obo/IAO_0000018 rdfs/label ["obsolete_material_entity"])
(obo/IAO_0000018 owl/deprecated true)
;; 
;; http://purl.obolibrary.org/obo/IAO_0000019
(obo/IAO_0000019 rdf/type owl/Class)
(obo/IAO_0000019 rdfs/subClassOf oboInOwl/ObsoleteClass)
(obo/IAO_0000019 obo/IAO_0000111 ["binary digital entity" "en"])
(obo/IAO_0000019 obo/IAO_0000112 ["MS Word document, ZIP file, DICOM file, JPEG file" "en"])
(obo/IAO_0000019 obo/IAO_0000115 ["A binary digital entity is a digital entity that is encoded in a way that is not easily human readable and that contains other than text characters." "en"])
(obo/IAO_0000019 obo/IAO_0000116 ["3/22/2009 Alan Ruttenberg, obsoleted per http://groups.google.com/group/information-ontology/browse_thread/thread/789ad4b7708d5cf4\nSuperclass was 'digital entity'" "en"])
(obo/IAO_0000019 obo/IAO_0000116 ["digital_entity" "en"])
(obo/IAO_0000019 obo/IAO_0000117 ["person:Chris Stoeckert" "en"])
(obo/IAO_0000019 obo/IAO_0000119 ["OBI_0000244"])
(obo/IAO_0000019 obo/IAO_0000119 ["group:OBI" "en"])
(obo/IAO_0000019 obo/IAO_0000231 obo/IAO_0000103)
(obo/IAO_0000019 rdfs/label ["obsolete2_binary digital entity" "en"])
(obo/IAO_0000019 owl/deprecated true)
;; 
;; http://purl.obolibrary.org/obo/IAO_0000024
(obo/IAO_0000024 rdf/type owl/Class)
(obo/IAO_0000024 rdfs/subClassOf oboInOwl/ObsoleteClass)
(obo/IAO_0000024 obo/IAO_0000112 ["The length of a ruler."])
(obo/IAO_0000024 obo/IAO_0000115 ["a unit of measure is the quality of some material entity compared to which another quality is some multiple of."])
(obo/IAO_0000024 obo/IAO_0000117 ["Alan Ruttenberg"])
(obo/IAO_0000024 obo/IAO_0000119 ["Smith, Ceusters, Ruttenberg, 2000 years of philosophy"])
(obo/IAO_0000024 obo/IAO_0000231 obo/IAO_0000103)
(obo/IAO_0000024 rdfs/label ["obsolete_unit of measure"])
(obo/IAO_0000024 owl/deprecated true)
;; 
;; http://purl.obolibrary.org/obo/IAO_0000025
(obo/IAO_0000025 rdf/type owl/Class)
(obo/IAO_0000025 rdfs/subClassOf obo/IAO_0000104)
(obo/IAO_0000025 obo/IAO_0000111 ["programming language" "en"])
(obo/IAO_0000025 obo/IAO_0000112 ["R, Perl, Java" "en"])
(obo/IAO_0000025 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000025 obo/IAO_0000115 ["A language in which source code is written that is intended to be executed/run by a software interpreter. Programming languages are ways to write instructions that specify what to do, and sometimes, how to do it." "en"])
(obo/IAO_0000025 obo/IAO_0000117 ["person:Alan Ruttenberg" "en"])
(obo/IAO_0000025 obo/IAO_0000117 ["person:Chris Stoeckert" "en"])
(obo/IAO_0000025 obo/IAO_0000119 ["OBI_0000058" "en"])
(obo/IAO_0000025 obo/IAO_0000119 ["group:OBI" "en"])
(obo/IAO_0000025 rdfs/label ["programming language" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000027
(obo/IAO_0000027 rdf/type owl/Class)
(obo/IAO_0000027 rdfs/subClassOf obo/IAO_0000030)
(obo/IAO_0000027 obo/IAO_0000111 ["data item" "en"])
(obo/IAO_0000027 obo/IAO_0000112 ["Data items include counts of things, analyte concentrations, and statistical summaries." "en"])
(obo/IAO_0000027 obo/IAO_0000114 obo/IAO_0000125)
(obo/IAO_0000027 obo/IAO_0000115 ["a data item is an information content entity that is intended to be a truthful statement about something (modulo, e.g., measurement precision or other systematic errors) and is constructed/acquired by a method which reliably tends to produce (approximately) truthful statements." "en"])
(obo/IAO_0000027 obo/IAO_0000116 ["2/2/2009 Alan and Bjoern discussing FACS run output data. This is a data item because it is about the cell population. Each element records an event and is typically further composed a set of measurment data items that record the fluorescent intensity stimulated by one of the lasers." "en"])
(obo/IAO_0000027 obo/IAO_0000116 ["2009-03-16: data item deliberatly ambiguous: we merged data set and datum to be one entity, not knowing how to define singular versus plural. So data item is more general than datum." "en"])
(obo/IAO_0000027 obo/IAO_0000116 ["2009-03-16: removed datum as alternative term as datum specifically refers to singular form, and is thus not an exact synonym." "en"])
(obo/IAO_0000027 obo/IAO_0000116 ["2014-03-31: See discussion at http://odontomachus.wordpress.com/2014/03/30/aboutness-objects-propositions/"])
(obo/IAO_0000027 obo/IAO_0000116 ["JAR: datum     -- well, this will be very tricky to define, but maybe some \ninformation-like stuff that might be put into a computer and that is \nmeant, by someone, to denote and/or to be interpreted by some \nprocess... I would include lists, tables, sentences... I think I might \ndefer to Barry, or to Brian Cantwell Smith\n\nJAR: A data item is an approximately justified approximately true approximate belief" "en"])
(obo/IAO_0000027 obo/IAO_0000117 ["PERSON: Alan Ruttenberg" "en"])
(obo/IAO_0000027 obo/IAO_0000117 ["PERSON: Chris Stoeckert" "en"])
(obo/IAO_0000027 obo/IAO_0000117 ["PERSON: Jonathan Rees" "en"])
(obo/IAO_0000027 obo/IAO_0000118 ["data" "en"])
(obo/IAO_0000027 rdfs/label ["data item" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000028
(obo/IAO_0000028 rdf/type owl/Class)
(obo/IAO_0000028 rdfs/subClassOf obo/IAO_0000030)
(obo/IAO_0000028 obo/IAO_0000111 ["symbol" "en"])
(obo/IAO_0000028 obo/IAO_0000112 ["a serial number such as \"12324X\"" "en"])
(obo/IAO_0000028 obo/IAO_0000112 ["a stop sign" "en"])
(obo/IAO_0000028 obo/IAO_0000112 ["a written proper name such as \"OBI\"" "en"])
(obo/IAO_0000028 obo/IAO_0000114 obo/IAO_0000125)
(obo/IAO_0000028 obo/IAO_0000115 ["An information content entity that is a mark(s) or character(s) used as a conventional representation of another entity." "en"])
(obo/IAO_0000028 obo/IAO_0000116 ["20091104, MC: this needs work and will most probably change" "en"])
 ;(obo/IAO_0000028 obo/IAO_0000116 ["2014-03-31: We would like to have a deeper analysis of 'mark' and 'sign' in the future (see https://github.com/information-artifact-ontology/IAO/issues/154)." "en"])
(obo/IAO_0000028 obo/IAO_0000117 ["PERSON: James A. Overton" "en"])
(obo/IAO_0000028 obo/IAO_0000117 ["PERSON: Jonathan Rees" "en"])
(obo/IAO_0000028 obo/IAO_0000119 ["based on Oxford English Dictionary" "en"])
(obo/IAO_0000028 rdfs/label ["symbol" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000029
(obo/IAO_0000029 rdf/type owl/Class)
(obo/IAO_0000029 rdfs/subClassOf obo/IAO_0000028)
(obo/IAO_0000029 obo/IAO_0000111 ["numeral" "en"])
(obo/IAO_0000029 obo/IAO_0000114 obo/IAO_0000123)
(obo/IAO_0000029 obo/IAO_0000115 ["A symbol that denotes a number." "en"])
(obo/IAO_0000029 obo/IAO_0000117 ["PERSON: Jonathan Rees" "en"])
(obo/IAO_0000029 rdfs/label ["numeral" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000030
(obo/IAO_0000030 rdf/type owl/Class)
(obo/IAO_0000030 rdfs/subClassOf obo/BFO_0000031)
(obo/IAO_0000030 rdfs/subClassOf bnode/ccp-extensions_genid200)
(bnode/ccp-extensions_genid200 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid200 owl/onProperty obo/IAO_0000136)
(bnode/ccp-extensions_genid200 owl/someValuesFrom obo/BFO_0000001)
(obo/IAO_0000030 obo/IAO_0000111 ["information content entity" "en"])
(obo/IAO_0000030 obo/IAO_0000112 ["Examples of information content entites include journal articles, data, graphical layouts, and graphs." "en"])
(obo/IAO_0000030 obo/IAO_0000114 obo/IAO_0000122)
(obo/IAO_0000030 obo/IAO_0000115 ["A generically dependent continuant that is about some thing." "en"])
(obo/IAO_0000030 obo/IAO_0000116 ["2014-03-10: The use of \"thing\" is intended to be general enough to include universals and configurations (see https://groups.google.com/d/msg/information-ontology/GBxvYZCk1oc/-L6B5fSBBTQJ)." "en"])
(obo/IAO_0000030 obo/IAO_0000116 ["information_content_entity 'is_encoded_in' some digital_entity in obi before split (040907). information_content_entity 'is_encoded_in' some physical_document in obi before split (040907).\n\nPrevious. An information content entity is a non-realizable information entity that 'is encoded in' some digital or physical entity." "en"])
(obo/IAO_0000030 obo/IAO_0000117 ["PERSON: Chris Stoeckert" "en"])
(obo/IAO_0000030 obo/IAO_0000119 ["OBI_0000142" "en"])
(obo/IAO_0000030 rdfs/label ["information content entity" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000031
(obo/IAO_0000031 rdf/type owl/Class)
(obo/IAO_0000031 rdfs/subClassOf obo/IAO_0000029)
(obo/IAO_0000031 obo/IAO_0000111 ["integer numeral" "en"])
(obo/IAO_0000031 obo/IAO_0000114 obo/IAO_0000123)
(obo/IAO_0000031 obo/IAO_0000115 ["a numeral that denotes an integer" "en"])
(obo/IAO_0000031 obo/IAO_0000117 ["PERSON: Jonathan Rees" "en"])
(obo/IAO_0000031 rdfs/label ["integer numeral" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000032
(obo/IAO_0000032 rdf/type owl/Class)
(obo/IAO_0000032 rdfs/subClassOf obo/IAO_0000109)
(obo/IAO_0000032 rdfs/subClassOf bnode/ccp-extensions_genid201)
(bnode/ccp-extensions_genid201 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid201 owl/onProperty obo/IAO_0000039)
(bnode/ccp-extensions_genid201 owl/minCardinality 1)
(obo/IAO_0000032 rdfs/subClassOf bnode/ccp-extensions_genid202)
(bnode/ccp-extensions_genid202 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid202 owl/onProperty obo/IAO_0000004)
(bnode/ccp-extensions_genid202 owl/minCardinality 1)
(obo/IAO_0000032 obo/IAO_0000112 ["10 feet. 3 ml." "en"])
(obo/IAO_0000032 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000032 obo/IAO_0000115 ["a scalar measurement datum is a measurement datum that is composed of two parts, numerals and a unit label." "en"])
(obo/IAO_0000032 obo/IAO_0000116 ["2009-03-16: we decided to keep datum singular in scalar measurement datum, as in\nthis case we explicitly refer to the singular form" "en"])
(obo/IAO_0000032 obo/IAO_0000116 ["Would write this as: has_part some 'measurement unit label' and has_part some numeral and has_part exactly 2, except for the fact that this won't let us take advantage of OWL reasoning over the numbers. Instead use has measurment value property to represent the same. Use has measurement unit label (subproperty of has_part) so we can easily say that there is only one of them." "en"])
(obo/IAO_0000032 obo/IAO_0000117 ["PERSON: Alan Ruttenberg" "en"])
(obo/IAO_0000032 obo/IAO_0000117 ["PERSON: Melanie Courtot" "en"])
(obo/IAO_0000032 rdfs/label ["scalar measurement datum" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000033
(obo/IAO_0000033 rdf/type owl/Class)
(obo/IAO_0000033 rdfs/subClassOf obo/IAO_0000030)
(obo/IAO_0000033 rdfs/subClassOf bnode/ccp-extensions_genid203)
(bnode/ccp-extensions_genid203 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid203 owl/onProperty obo/IAO_0000136)
(bnode/ccp-extensions_genid203 owl/someValuesFrom obo/BFO_0000017)
(obo/IAO_0000033 obo/IAO_0000114 obo/IAO_0000122)
(obo/IAO_0000033 obo/IAO_0000115 ["An information content entity whose concretizations indicate to their bearer how to realize them in a process." "en"])
(obo/IAO_0000033 obo/IAO_0000116 ["2009-03-16: provenance: a term realizable information entity was proposed for OBI (OBI_0000337) , edited by the PlanAndPlannedProcess branch. Original definition was  \"is the specification of a process that can be concretized and realized by an actor\" with alternative term  \"instruction\".It has been subsequently moved to IAO where the objective for which the original term was defined was satisfied with the definitionof this, different, term." "en"])
(obo/IAO_0000033 obo/IAO_0000116 ["2013-05-30 Alan Ruttenberg: What differentiates a directive information entity from an information concretization is that it can have concretizations that are either qualities or realizable entities. The concretizations that are realizable entities are created when an individual chooses to take up the direction, i.e. has the intention to (try to) realize it." "en"])
(obo/IAO_0000033 obo/IAO_0000116 ["8/6/2009 Alan Ruttenberg: Changed label from \"information entity about a realizable\" after discussions at ICBO" "en"])
(obo/IAO_0000033 obo/IAO_0000116 ["Werner pushed back on calling it realizable information entity as it isn't realizable. However this name isn't right either. An example would be a recipe. The realizable entity would be a plan, but the information entity isn't about the plan, it, once concretized, *is* the plan. -Alan" "en"])
(obo/IAO_0000033 obo/IAO_0000117 ["PERSON: Alan Ruttenberg" "en"])
(obo/IAO_0000033 obo/IAO_0000117 ["PERSON: Bjoern Peters" "en"])
(obo/IAO_0000033 rdfs/label ["directive information entity" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000034
(obo/IAO_0000034 rdf/type owl/Class)
(obo/IAO_0000034 rdfs/subClassOf obo/IAO_0000001)
(obo/IAO_0000034 obo/IAO_0000111 ["time trigger" "en"])
(obo/IAO_0000034 obo/IAO_0000114 obo/IAO_0000124)
(obo/IAO_0000034 obo/IAO_0000115 ["revisit?" "en"])
(obo/IAO_0000034 obo/IAO_0000117 ["PlanAndPlannedProcess Branch" "en"])
(obo/IAO_0000034 obo/IAO_0000119 ["OBI branch derived" "en"])
(obo/IAO_0000034 obo/IAO_0000119 ["OBI_0000331" "en"])
(obo/IAO_0000034 rdfs/label ["time trigger" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000035
(obo/IAO_0000035 rdf/type owl/Class)
(obo/IAO_0000035 rdfs/subClassOf oboInOwl/ObsoleteClass)
(obo/IAO_0000035 obo/IAO_0000111 ["obsolete_study interpretation" "en"])
(obo/IAO_0000035 obo/IAO_0000114 obo/IAO_0000125)
(obo/IAO_0000035 obo/IAO_0000115 ["A study interpretation is a textual entity about the implications of a study result. Examples include discussion of whether a hypothesis is false, whether the study failed to address the hypothesis, and whether the study results have led to new hypotheses" "en"])
(obo/IAO_0000035 obo/IAO_0000116 ["2009-03-16: definition was \"A conclusion is a narrative object which can be published in a paper summerizing and interpreting a protocol application.\""])
(obo/IAO_0000035 obo/IAO_0000116 ["2009-03-16: work has been done on this term during during the OBI workshop winter 2009 and the current definition was considered acceptable for use in OBI. If there is a need to modify this definition please notify OBI."])
(obo/IAO_0000035 obo/IAO_0000116 ["The obsoleting of narrative object required a modest change in the definition of this term.  Circularity with \"interpretation... interprets\" has been removed, using \"about the implications\" instead." "en"])
(obo/IAO_0000035 obo/IAO_0000117 ["Lawrence Hunter" "en"])
(obo/IAO_0000035 obo/IAO_0000117 ["PERSON: Alan Ruttenberg"])
(obo/IAO_0000035 obo/IAO_0000117 ["PERSON: Jennifer Fostel" "en"])
(obo/IAO_0000035 obo/IAO_0000117 ["PERSON: Melanie Courtot"])
(obo/IAO_0000035 obo/IAO_0000118 ["conclusion"])
(obo/IAO_0000035 obo/IAO_0000119 ["OBI_0000005"])
(obo/IAO_0000035 obo/IAO_0000231 obo/IAO_0000103)
(obo/IAO_0000035 rdfs/label ["obsolete_study interpretation" "en"])
(obo/IAO_0000035 owl/deprecated true)
;; 
;; http://purl.obolibrary.org/obo/IAO_0000037
(obo/IAO_0000037 rdf/type owl/Class)
(obo/IAO_0000037 rdfs/subClassOf obo/IAO_0000038)
(obo/IAO_0000037 obo/IAO_0000111 ["dot plot" "en"])
(obo/IAO_0000037 obo/IAO_0000112 ["Dot plot of SSC-H and FSC-H." "en"])
(obo/IAO_0000037 obo/IAO_0000114 obo/IAO_0000002)
(obo/IAO_0000037 obo/IAO_0000115 ["A dot plot is a report graph which is a graphical representation of data where each data point is represented by a single dot placed on coordinates corresponding to data point values in particular dimensions." "en"])
(obo/IAO_0000037 obo/IAO_0000117 ["person:Allyson Lister" "en"])
(obo/IAO_0000037 obo/IAO_0000117 ["person:Chris Stoeckert" "en"])
(obo/IAO_0000037 obo/IAO_0000119 ["OBI_0000123" "en"])
(obo/IAO_0000037 obo/IAO_0000119 ["group:OBI" "en"])
(obo/IAO_0000037 rdfs/label ["dot plot" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000038
(obo/IAO_0000038 rdf/type owl/Class)
(obo/IAO_0000038 rdfs/subClassOf obo/IAO_0000309)
(obo/IAO_0000038 obo/IAO_0000111 ["graph" "en"])
(obo/IAO_0000038 obo/IAO_0000114 obo/IAO_0000125)
(obo/IAO_0000038 obo/IAO_0000115 ["A diagram that presents one or more tuples of information by mapping those tuples in to a two dimensional space in a non arbitrary way." "en"])
(obo/IAO_0000038 obo/IAO_0000117 ["PERSON: Lawrence Hunter" "en"])
(obo/IAO_0000038 obo/IAO_0000117 ["person:Alan Ruttenberg" "en"])
(obo/IAO_0000038 obo/IAO_0000117 ["person:Allyson Lister" "en"])
(obo/IAO_0000038 obo/IAO_0000119 ["OBI_0000240" "en"])
(obo/IAO_0000038 obo/IAO_0000119 ["group:OBI" "en"])
(obo/IAO_0000038 rdfs/label ["graph" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000047
(obo/IAO_0000047 rdf/type owl/Class)
(obo/IAO_0000047 rdfs/subClassOf oboInOwl/ObsoleteClass)
(obo/IAO_0000047 obo/IAO_0000111 ["text based digital entity" "en"])
(obo/IAO_0000047 obo/IAO_0000112 ["XML file, C++ source code file" "en"])
(obo/IAO_0000047 obo/IAO_0000115 ["A text based digital entity is a digital entity that is encoded so that it only contains text characters." "en"])
(obo/IAO_0000047 obo/IAO_0000116 ["3/22/2009 Alan Ruttenberg, obsoleted per http://groups.google.com/group/information-ontology/browse_thread/thread/789ad4b7708d5cf4\nsuperclass was 'digital document'" "en"])
(obo/IAO_0000047 obo/IAO_0000116 ["digital_entity" "en"])
(obo/IAO_0000047 obo/IAO_0000117 ["person:Chris Stoeckert" "en"])
(obo/IAO_0000047 obo/IAO_0000119 ["OBI_0000132"])
(obo/IAO_0000047 obo/IAO_0000119 ["group:OBI"])
(obo/IAO_0000047 obo/IAO_0000231 obo/IAO_0000103)
(obo/IAO_0000047 rdfs/label ["obsolete2_text based digital entity" "en"])
(obo/IAO_0000047 owl/deprecated true)
;; 
;; http://purl.obolibrary.org/obo/IAO_0000055
(obo/IAO_0000055 rdf/type owl/Class)
(obo/IAO_0000055 rdfs/subClassOf obo/IAO_0000001)
(obo/IAO_0000055 obo/IAO_0000111 ["rule" "en"])
(obo/IAO_0000055 obo/IAO_0000112 ["example to be added" "en"])
(obo/IAO_0000055 obo/IAO_0000114 obo/IAO_0000123)
(obo/IAO_0000055 obo/IAO_0000115 ["a rule is an executable which guides, defines, restricts actions" "en"])
(obo/IAO_0000055 obo/IAO_0000116 ["MSI" "en"])
(obo/IAO_0000055 obo/IAO_0000117 ["PRS" "en"])
(obo/IAO_0000055 obo/IAO_0000119 ["OBI_0500021" "en"])
(obo/IAO_0000055 obo/IAO_0000119 ["PRS" "en"])
(obo/IAO_0000055 rdfs/label ["rule" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000057
(obo/IAO_0000057 rdf/type owl/Class)
(obo/IAO_0000057 rdfs/subClassOf obo/IAO_0000038)
(obo/IAO_0000057 obo/IAO_0000111 ["contour plot" "en"])
(obo/IAO_0000057 obo/IAO_0000112 ["Contour plot of SSC-H, FSC-H, and FL1-H." "en"])
(obo/IAO_0000057 obo/IAO_0000114 obo/IAO_0000002)
(obo/IAO_0000057 obo/IAO_0000116 ["generically_dependent_continuants" "en"])
(obo/IAO_0000057 obo/IAO_0000117 ["person:Allyson Lister" "en"])
(obo/IAO_0000057 obo/IAO_0000117 ["person:Chris Stoeckert" "en"])
(obo/IAO_0000057 obo/IAO_0000119 ["OBI_0000246" "en"])
(obo/IAO_0000057 obo/IAO_0000119 ["group:Flow Cytometry community" "en"])
(obo/IAO_0000057 rdfs/label ["contour plot" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000059
(obo/IAO_0000059 rdf/type owl/Class)
(obo/IAO_0000059 rdfs/subClassOf oboInOwl/ObsoleteClass)
(obo/IAO_0000059 obo/IAO_0000111 ["report figure" "en"])
(obo/IAO_0000059 obo/IAO_0000114 obo/IAO_0000124)
(obo/IAO_0000059 obo/IAO_0000115 ["A report figure is a report display element that has some aspect of illustration, but may be a composite of figures, images, and other elements" "en"])
(obo/IAO_0000059 obo/IAO_0000116 ["I prepended the 'report ' to make it clear that we mean parts of reports here. We may want a more generic version of 'figure', in which case this would become a defined class - figure and part_of some report" "en"])
(obo/IAO_0000059 obo/IAO_0000116 ["Replaced by defined version of figure" "en"])
(obo/IAO_0000059 obo/IAO_0000117 ["person:Alan Ruttenberg" "en"])
(obo/IAO_0000059 obo/IAO_0000117 ["person:Allyson Lister" "en"])
(obo/IAO_0000059 obo/IAO_0000119 ["OBI_0000027"])
(obo/IAO_0000059 obo/IAO_0000119 ["group:OBI" "en"])
(obo/IAO_0000059 obo/IAO_0000231 obo/IAO_0000103)
(obo/IAO_0000059 rdfs/label ["obsolete2_report figure" "en"])
(obo/IAO_0000059 owl/deprecated true)
;; 
;; http://purl.obolibrary.org/obo/IAO_0000064
(obo/IAO_0000064 rdf/type owl/Class)
(obo/IAO_0000064 rdfs/subClassOf obo/IAO_0000104)
(obo/IAO_0000064 obo/IAO_0000111 ["algorithm" "en"])
(obo/IAO_0000064 obo/IAO_0000112 ["PMID: 18378114.Genomics. 2008 Mar 28. LINKGEN: A new algorithm to process data in genetic linkage studies." "en"])
(obo/IAO_0000064 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000064 obo/IAO_0000115 ["A plan specification which describes the inputs and output of mathematical functions as well as workflow of execution for achieving an predefined objective. Algorithms are realized usually by means of implementation as computer programs for execution by automata." "en"])
(obo/IAO_0000064 obo/IAO_0000117 ["Philippe Rocca-Serra" "en"])
(obo/IAO_0000064 obo/IAO_0000117 ["PlanAndPlannedProcess Branch" "en"])
(obo/IAO_0000064 obo/IAO_0000119 ["OBI_0000270" "en"])
(obo/IAO_0000064 obo/IAO_0000119 ["adapted from discussion on OBI list (Matthew Pocock, Christian Cocos, Alan Ruttenberg)" "en"])
(obo/IAO_0000064 rdfs/label ["algorithm" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000065
(obo/IAO_0000065 rdf/type owl/Class)
(obo/IAO_0000065 rdfs/subClassOf obo/IAO_0000064)
(obo/IAO_0000065 obo/IAO_0000111 ["software interpreter" "en"])
(obo/IAO_0000065 obo/IAO_0000112 ["R program, Perl interpreter, Java virtual machine" "en"])
(obo/IAO_0000065 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000065 obo/IAO_0000115 ["A software interpreter is a software application that executes some specified input software. " "en"])
(obo/IAO_0000065 obo/IAO_0000116 ["Do we care? Jennifer: Yes, there was a particular version of R that had a bug and it was fixed later. That would imply that we mean specific version of an interpreter. So an instance of this would be a particular version of the interpreter" "en"])
(obo/IAO_0000065 obo/IAO_0000117 ["person:Alan Ruttenberg" "en"])
(obo/IAO_0000065 obo/IAO_0000117 ["person:Chris Stoeckert" "en"])
(obo/IAO_0000065 obo/IAO_0000119 ["OBI_0000199" "en"])
(obo/IAO_0000065 obo/IAO_0000119 ["group:OBI" "en"])
(obo/IAO_0000065 rdfs/label ["software interpreter" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000078
(obo/IAO_0000078 rdf/type owl/Class)
(obo/IAO_0000078 owl/equivalentClass bnode/ccp-extensions_genid204)
(bnode/ccp-extensions_genid204 rdf/type owl/Class)
(bnode/ccp-extensions_genid204 owl/oneOf bnode/ccp-extensions_genid213)
(bnode/ccp-extensions_genid213 rdf/type rdf/List)
(bnode/ccp-extensions_genid213 rdf/first obo/IAO_0000002)
(bnode/ccp-extensions_genid213 rdf/rest bnode/ccp-extensions_genid212)
(bnode/ccp-extensions_genid212 rdf/type rdf/List)
(bnode/ccp-extensions_genid212 rdf/first obo/IAO_0000120)
(bnode/ccp-extensions_genid212 rdf/rest bnode/ccp-extensions_genid211)
(bnode/ccp-extensions_genid211 rdf/type rdf/List)
(bnode/ccp-extensions_genid211 rdf/first obo/IAO_0000121)
(bnode/ccp-extensions_genid211 rdf/rest bnode/ccp-extensions_genid210)
(bnode/ccp-extensions_genid210 rdf/type rdf/List)
(bnode/ccp-extensions_genid210 rdf/first obo/IAO_0000122)
(bnode/ccp-extensions_genid210 rdf/rest bnode/ccp-extensions_genid209)
(bnode/ccp-extensions_genid209 rdf/type rdf/List)
(bnode/ccp-extensions_genid209 rdf/first obo/IAO_0000123)
(bnode/ccp-extensions_genid209 rdf/rest bnode/ccp-extensions_genid208)
(bnode/ccp-extensions_genid208 rdf/type rdf/List)
(bnode/ccp-extensions_genid208 rdf/first obo/IAO_0000124)
(bnode/ccp-extensions_genid208 rdf/rest bnode/ccp-extensions_genid207)
(bnode/ccp-extensions_genid207 rdf/type rdf/List)
(bnode/ccp-extensions_genid207 rdf/first obo/IAO_0000125)
(bnode/ccp-extensions_genid207 rdf/rest bnode/ccp-extensions_genid206)
(bnode/ccp-extensions_genid206 rdf/type rdf/List)
(bnode/ccp-extensions_genid206 rdf/first obo/IAO_0000423)
(bnode/ccp-extensions_genid206 rdf/rest bnode/ccp-extensions_genid205)
(bnode/ccp-extensions_genid205 rdf/type rdf/List)
(bnode/ccp-extensions_genid205 rdf/first obo/IAO_0000428)
(bnode/ccp-extensions_genid205 rdf/rest rdf/nil)
(obo/IAO_0000078 rdfs/subClassOf obo/IAO_0000102)
(obo/IAO_0000078 obo/IAO_0000111 ["curation status specification" "en"])
(obo/IAO_0000078 obo/IAO_0000114 obo/IAO_0000125)
(obo/IAO_0000078 obo/IAO_0000115 ["The curation status of the term. The allowed values come from an enumerated list of predefined terms. See the specification of these instances for more detailed definitions of each enumerated value." "en"])
(obo/IAO_0000078 obo/IAO_0000116 ["Better to represent curation as a process with parts and then relate labels to that process (in IAO meeting)" "en"])
(obo/IAO_0000078 obo/IAO_0000117 ["PERSON:Bill Bug" "en"])
(obo/IAO_0000078 obo/IAO_0000119 ["GROUP:OBI:obo/obi" "en"])
(obo/IAO_0000078 obo/IAO_0000119 ["OBI_0000266" "en"])
(obo/IAO_0000078 rdfs/label ["curation status specification" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000079
(obo/IAO_0000079 rdf/type owl/Class)
(obo/IAO_0000079 rdfs/subClassOf obo/IAO_0000038)
(obo/IAO_0000079 obo/IAO_0000111 ["density plot" "en"])
(obo/IAO_0000079 obo/IAO_0000112 ["Density plot of SSC-H and FSC-H." "en"])
(obo/IAO_0000079 obo/IAO_0000114 obo/IAO_0000002)
(obo/IAO_0000079 obo/IAO_0000115 ["A density plot is a report graph which is a graphical representation of data where the tint of a particular pixel corresponds to some kind of function corresponding the the amount of data points relativelly with their distance from the the pixel." "en"])
(obo/IAO_0000079 obo/IAO_0000117 ["person:Allyson Lister" "en"])
(obo/IAO_0000079 obo/IAO_0000117 ["person:Chris Stoeckert" "en"])
(obo/IAO_0000079 obo/IAO_0000119 ["OBI_0000179" "en"])
(obo/IAO_0000079 obo/IAO_0000119 ["group:Flow Cytometry community" "en"])
(obo/IAO_0000079 rdfs/label ["density plot" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000088
(obo/IAO_0000088 rdf/type owl/Class)
(obo/IAO_0000088 rdfs/subClassOf obo/IAO_0000310)
(obo/IAO_0000088 obo/IAO_0000111 ["report" "en"])
(obo/IAO_0000088 obo/IAO_0000112 ["Examples of reports are gene lists and investigation reports. These are not published (journal) articles but may be included in a journal article." "en"])
(obo/IAO_0000088 obo/IAO_0000114 obo/IAO_0000125)
(obo/IAO_0000088 obo/IAO_0000115 ["a document assembled by an author for the purpose of providing information for the audience. A report is the output of a documenting process and has the objective to be consumed by a specific audience. Topic of the report is on something that has completed. A report is not a single figure. Examples of reports are journal article, patent application, grant progress report, case report (not patient record)" "en"])
(obo/IAO_0000088 obo/IAO_0000116 ["2009-03-16: comment from Darren Natale: I am slightly uneasy with the sentence \"Topic of the report is on \nsomething that has completed.\"  Should it be restricted to those things \nthat are completed?  For example, a progress report is (usually) about \nsomething that definitely has *not* been completed, or may include \n(only) projections.  I think the definition would not suffer if the \nwhole sentence is deleted." "en"])
(obo/IAO_0000088 obo/IAO_0000116 ["2009-03-16: this was report of results with definition: A report is a narrative object that is a formal statement of the results of an investigation, or of any matter on which definite information is required, made by some person or body instructed or required to do so." "en"])
(obo/IAO_0000088 obo/IAO_0000116 ["2009-03-16: work has been done on this term during during the OBI workshop winter 2009 and the current definition was considered acceptable for use in OBI. If there is a need to modify this definition please notify OBI." "en"])
(obo/IAO_0000088 obo/IAO_0000116 ["2009-08-10 Alan Ruttenberg: Larry Hunter suggests that this be obsoleted and replaced by 'document'. Alan restored as there are OBI dependencies and this merits further discussion" "en"])
(obo/IAO_0000088 obo/IAO_0000116 ["disagreement about where reports go. alan: only some gene lists are reports. Is a report all the content of some document? The example of usage suggests that a report may be part of  some article. Term needs clarification" "en"])
(obo/IAO_0000088 obo/IAO_0000117 ["PERSON: Alan Ruttenberg" "en"])
(obo/IAO_0000088 obo/IAO_0000117 ["PERSON: Melanie Courtot" "en"])
(obo/IAO_0000088 obo/IAO_0000117 ["PERSON:Chris Stoeckert" "en"])
(obo/IAO_0000088 obo/IAO_0000119 ["GROUP: OBI" "en"])
(obo/IAO_0000088 obo/IAO_0000119 ["OBI_0000099" "en"])
(obo/IAO_0000088 rdfs/label ["report" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000091
(obo/IAO_0000091 rdf/type owl/Class)
(obo/IAO_0000091 rdfs/subClassOf oboInOwl/ObsoleteClass)
(obo/IAO_0000091 obo/IAO_0000111 ["report element" "en"])
(obo/IAO_0000091 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000091 obo/IAO_0000115 ["A report element is a narrative object in which information is presented and consumed by a human being, and is part of a report. Examples of report elements are figure (dot plot), table, text portion (may include a movie or audio clip on a web page)." "en"])
(obo/IAO_0000091 obo/IAO_0000116 ["2009-03-16: needs some more work (clarify relations)."])
(obo/IAO_0000091 obo/IAO_0000116 ["2009-03-16: was report display element with definition: A report display element is a narrative object that is part of a report. Report display elements are set off from the textual parts of a report and are typically given a label(e.g. Figure 2) which is used to refer to the element from the text. Typically the 2d layout is part of the identity of such elements."])
(obo/IAO_0000091 obo/IAO_0000116 ["2009-03-16: work has been done on this term during during the OBI workshop winter 2009 and the current definition was considered acceptable for use in OBI. If there is a need to modify this definition please notify OBI."])
(obo/IAO_0000091 obo/IAO_0000116 ["2009-08-10 Alan Ruttenberg: Larry Hunter suggests that this be obsoleted and replaced by 'textual entity' and 'figure'. Alan restored as there are OBI dependencies and this merits further discussion"])
(obo/IAO_0000091 obo/IAO_0000116 ["Replaced by textual entity and figure"])
(obo/IAO_0000091 obo/IAO_0000116 ["There will be some issue here about whether these are defined classes. As intended these are meant to denote the parts of the report that are not textual but are typically boxed and set within the text, labelled with some identifier, and referred to in the text" "en"])
(obo/IAO_0000091 obo/IAO_0000117 ["PERSON: Alan Ruttenberg" "en"])
(obo/IAO_0000091 obo/IAO_0000117 ["PERSON: Allyson Lister" "en"])
(obo/IAO_0000091 obo/IAO_0000117 ["PERSON: Melanie Courtot"])
(obo/IAO_0000091 obo/IAO_0000119 ["GROUP:OBI" "en"])
(obo/IAO_0000091 obo/IAO_0000119 ["OBI_0000001"])
(obo/IAO_0000091 obo/IAO_0000231 obo/IAO_0000103)
(obo/IAO_0000091 rdfs/label ["obsolete_report element" "en"])
(obo/IAO_0000091 owl/deprecated true)
;; 
;; http://purl.obolibrary.org/obo/IAO_0000093
(obo/IAO_0000093 rdf/type owl/Class)
(obo/IAO_0000093 rdfs/subClassOf oboInOwl/ObsoleteClass)
(obo/IAO_0000093 obo/IAO_0000111 ["binary executable" "en"])
(obo/IAO_0000093 obo/IAO_0000115 ["Binary executable is a digital entity consisting of the binary representation of machine instructions of a specific processor or they may be binary pseudocode for a virtual machine. A non-source executable file is also called an object program. It is assumed that the binary executable file contains properly-formatted computer instructions.  (derived from Wikipedia, Nov 1, 2007)" "en"])
(obo/IAO_0000093 obo/IAO_0000116 ["3/22/2009 Alan Ruttenberg, obsoleted per http://groups.google.com/group/information-ontology/browse_thread/thread/789ad4b7708d5cf4\nsuperclass was 'digital entity'" "en"])
(obo/IAO_0000093 obo/IAO_0000117 ["person:Jennifer Fostel" "en"])
(obo/IAO_0000093 obo/IAO_0000119 ["OBI_0000222"])
(obo/IAO_0000093 obo/IAO_0000119 ["group:OBI" "en"])
(obo/IAO_0000093 obo/IAO_0000231 obo/IAO_0000103)
(obo/IAO_0000093 rdfs/label ["obsolete2_binary executable" "en"])
(obo/IAO_0000093 owl/deprecated true)
;; 
;; http://purl.obolibrary.org/obo/IAO_0000096
(obo/IAO_0000096 rdf/type owl/Class)
(obo/IAO_0000096 rdfs/subClassOf obo/IAO_0000033)
(obo/IAO_0000096 obo/IAO_0000111 ["source code module" "en"])
(obo/IAO_0000096 obo/IAO_0000112 ["The written source code that implements part of an algorithm. Test - if you know that it was written in a specific language, then it can be source code module. We mean here, roughly, the wording of a document such as a perl script." "en"])
(obo/IAO_0000096 obo/IAO_0000114 obo/IAO_0000123)
(obo/IAO_0000096 obo/IAO_0000115 ["A source code module is a directive information entity that specifies, using a programming language, some algorithm." "en"])
(obo/IAO_0000096 obo/IAO_0000117 ["person:Alan Ruttenberg" "en"])
(obo/IAO_0000096 obo/IAO_0000117 ["person:Chris Stoeckert" "en"])
(obo/IAO_0000096 obo/IAO_0000119 ["OBI_0000039" "en"])
(obo/IAO_0000096 obo/IAO_0000119 ["group:OBI" "en"])
(obo/IAO_0000096 rdfs/label ["source code module" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000097
(obo/IAO_0000097 rdf/type owl/Class)
(obo/IAO_0000097 rdfs/subClassOf oboInOwl/ObsoleteClass)
(obo/IAO_0000097 obo/IAO_0000111 ["report table" "en"])
(obo/IAO_0000097 obo/IAO_0000114 obo/IAO_0000002)
(obo/IAO_0000097 obo/IAO_0000115 ["A report table is a report display element consisting of a matrix of cells layed out in a grid, some set of which are filled with some information content" "en"])
(obo/IAO_0000097 obo/IAO_0000116 ["2009-08-10 Alan Ruttenberg: Larry Hunter suggests that this be obsoleted and replaced by 'textual entity table'. Alan restored as there are OBI dependencies and this merits further discussion"])
(obo/IAO_0000097 obo/IAO_0000117 ["person:Alan Ruttenberg" "en"])
(obo/IAO_0000097 obo/IAO_0000117 ["person:Allyson Lister" "en"])
(obo/IAO_0000097 obo/IAO_0000119 ["OBI_0000265"])
(obo/IAO_0000097 obo/IAO_0000119 ["group:OBI" "en"])
(obo/IAO_0000097 obo/IAO_0000231 obo/IAO_0000103)
(obo/IAO_0000097 rdfs/label ["obsolete_report table" "en"])
(obo/IAO_0000097 owl/deprecated true)
;; 
;; http://purl.obolibrary.org/obo/IAO_0000098
(obo/IAO_0000098 rdf/type owl/Class)
(obo/IAO_0000098 rdfs/subClassOf obo/IAO_0000033)
(obo/IAO_0000098 obo/IAO_0000111 ["data format specification" "en"])
(obo/IAO_0000098 obo/IAO_0000114 obo/IAO_0000123)
(obo/IAO_0000098 obo/IAO_0000115 ["A data format specification is the information content borne by the document published defining the specification.\nExample: The ISO document specifying what encompasses an XML document; The instructions in a XSD file" "en"])
(obo/IAO_0000098 obo/IAO_0000116 ["2009-03-16: provenance: term imported from OBI_0000187, which had original definition \"A data format specification is a plan which organizes\ninformation. Example: The ISO document specifying what encompasses an\nXML document; The instructions in a XSD file\"" "en"])
(obo/IAO_0000098 obo/IAO_0000117 ["PERSON: Alan Ruttenberg" "en"])
(obo/IAO_0000098 obo/IAO_0000117 ["PlanAndPlannedProcess Branch" "en"])
(obo/IAO_0000098 obo/IAO_0000119 ["OBI branch derived" "en"])
(obo/IAO_0000098 obo/IAO_0000119 ["OBI_0000187" "en"])
(obo/IAO_0000098 rdfs/label ["data format specification" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000100
(obo/IAO_0000100 rdf/type owl/Class)
(obo/IAO_0000100 rdfs/subClassOf obo/IAO_0000027)
(obo/IAO_0000100 obo/IAO_0000111 ["data set" "en"])
(obo/IAO_0000100 obo/IAO_0000112 ["Intensity values in a CEL file or from multiple CEL files comprise a data set (as opposed to the CEL files themselves)." "en"])
(obo/IAO_0000100 obo/IAO_0000114 obo/IAO_0000125)
(obo/IAO_0000100 obo/IAO_0000115 ["A data item that is an aggregate of other data items of the same type that have something in common. Averages and distributions can be determined for data sets." "en"])
(obo/IAO_0000100 obo/IAO_0000116 ["2009/10/23 Alan Ruttenberg. The intention is that this term represent collections of like data. So this isn't for, e.g. the whole contents of a cel file, which includes parameters, metadata etc. This is more like java arrays of a certain rather specific type" "en"])
(obo/IAO_0000100 obo/IAO_0000116 ["2014-05-05: Data sets are aggregates and thus must include two or more data items. We have chosen not to add logical axioms to make this restriction."])
(obo/IAO_0000100 obo/IAO_0000117 ["person:Allyson Lister" "en"])
(obo/IAO_0000100 obo/IAO_0000117 ["person:Chris Stoeckert" "en"])
(obo/IAO_0000100 obo/IAO_0000119 ["OBI_0000042" "en"])
(obo/IAO_0000100 obo/IAO_0000119 ["group:OBI" "en"])
(obo/IAO_0000100 rdfs/label ["data set" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000101
(obo/IAO_0000101 rdf/type owl/Class)
(obo/IAO_0000101 rdfs/subClassOf obo/IAO_0000308)
(obo/IAO_0000101 obo/IAO_0000111 ["image" "en"])
(obo/IAO_0000101 obo/IAO_0000114 obo/IAO_0000122)
(obo/IAO_0000101 obo/IAO_0000115 ["An image is an affine projection to a two dimensional surface, of measurements of some quality of an entity or entities repeated at regular intervals across a spatial range, where the measurements are represented as color and luminosity on the projected on surface." "en"])
(obo/IAO_0000101 obo/IAO_0000117 ["person:Alan Ruttenberg" "en"])
(obo/IAO_0000101 obo/IAO_0000117 ["person:Allyson" "en"])
(obo/IAO_0000101 obo/IAO_0000117 ["person:Chris Stoeckert" "en"])
(obo/IAO_0000101 obo/IAO_0000119 ["OBI_0000030" "en"])
(obo/IAO_0000101 obo/IAO_0000119 ["group:OBI" "en"])
(obo/IAO_0000101 rdfs/label ["image" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000102
(obo/IAO_0000102 rdf/type owl/Class)
(obo/IAO_0000102 rdfs/subClassOf obo/IAO_0000027)
(obo/IAO_0000102 obo/IAO_0000115 ["data about an ontology part is a data item about a part of an ontology, for example a term" "en"])
(obo/IAO_0000102 obo/IAO_0000117 ["Person:Alan Ruttenberg"])
(obo/IAO_0000102 rdfs/label ["data about an ontology part" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000104
(obo/IAO_0000104 rdf/type owl/Class)
(obo/IAO_0000104 rdfs/subClassOf obo/IAO_0000033)
(obo/IAO_0000104 rdfs/subClassOf bnode/ccp-extensions_genid214)
(bnode/ccp-extensions_genid214 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid214 owl/onProperty obo/BFO_0000051)
(bnode/ccp-extensions_genid214 owl/someValuesFrom obo/IAO_0000005)
(obo/IAO_0000104 rdfs/subClassOf bnode/ccp-extensions_genid215)
(bnode/ccp-extensions_genid215 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid215 owl/onProperty obo/BFO_0000051)
(bnode/ccp-extensions_genid215 owl/someValuesFrom obo/IAO_0000007)
(obo/IAO_0000104 obo/IAO_0000111 ["plan specification" "en"])
(obo/IAO_0000104 obo/IAO_0000112 ["PMID: 18323827.Nat Med. 2008 Mar;14(3):226.New plan proposed to help resolve conflicting medical advice." "en"])
(obo/IAO_0000104 obo/IAO_0000114 obo/IAO_0000122)
(obo/IAO_0000104 obo/IAO_0000115 ["A directive information entity with action specifications and objective specifications as parts that, when concretized, is realized in a process in which the bearer tries to achieve the objectives by taking the actions specified." "en"])
(obo/IAO_0000104 obo/IAO_0000116 ["2009-03-16: provenance: a term a plan was proposed for OBI (OBI_0000344) , edited by the PlanAndPlannedProcess branch. Original definition was \" a plan is a specification of a process that is realized by an actor to achieve the objective specified as part of the plan\". It has been subsequently moved to IAO where the objective for which the original term was defined was satisfied with the definitionof this, different, term." "en"])
(obo/IAO_0000104 obo/IAO_0000116 ["2014-03-31: A plan specification can have other parts, such as conditional specifications." "en"])
(obo/IAO_0000104 obo/IAO_0000116 ["Alternative previous definition: a plan is a set of instructions that specify how an objective should be achieved" "en"])
(obo/IAO_0000104 obo/IAO_0000117 ["Alan Ruttenberg" "en"])
(obo/IAO_0000104 obo/IAO_0000119 ["OBI Plan and Planned Process branch" "en"])
(obo/IAO_0000104 obo/IAO_0000119 ["OBI_0000344" "en"])
(obo/IAO_0000104 rdfs/comment ["2/3/2009 Comment from OBI review.\n\nAction specification not well enough specified.\nConditional specification not well enough specified.\nQuestion whether all plan specifications have objective specifications.\n\nRequest that IAO either clarify these or change definitions not to use them" "en"])
(obo/IAO_0000104 rdfs/label ["plan specification" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000105
(obo/IAO_0000105 rdf/type owl/Class)
(obo/IAO_0000105 rdfs/subClassOf oboInOwl/ObsoleteClass)
(obo/IAO_0000105 obo/IAO_0000111 ["digital document" "en"])
(obo/IAO_0000105 obo/IAO_0000115 ["A digital document is a digital entity consisting of an electronic file which can be rendered into human-readable form by one or more computational applications.  The digital document does not refer to the information content of the document but to an instance of the file." "en"])
(obo/IAO_0000105 obo/IAO_0000116 ["3/22/2009 Alan Ruttenberg, obsoleted per http://groups.google.com/group/information-ontology/browse_thread/thread/789ad4b7708d5cf4\nsuperclass was 'digial entity'" "en"])
(obo/IAO_0000105 obo/IAO_0000117 ["person:Jennifer Fostel" "en"])
(obo/IAO_0000105 obo/IAO_0000119 ["OBI_0000195"])
(obo/IAO_0000105 obo/IAO_0000119 ["group:OBI" "en"])
(obo/IAO_0000105 obo/IAO_0000231 obo/IAO_0000103)
(obo/IAO_0000105 rdfs/label ["obsolete2_digital document" "en"])
(obo/IAO_0000105 owl/deprecated true)
;; 
;; http://purl.obolibrary.org/obo/IAO_0000109
(obo/IAO_0000109 rdf/type owl/Class)
(obo/IAO_0000109 rdfs/subClassOf obo/IAO_0000027)
(obo/IAO_0000109 obo/IAO_0000111 ["measurement datum" "en"])
(obo/IAO_0000109 obo/IAO_0000112 ["Examples of measurement data are the recoding of the weight of a mouse as {40,mass,\"grams\"}, the recording of an observation of the behavior of the mouse {,process,\"agitated\"}, the recording of the expression level of a gene as measured through the process of microarray experiment {3.4,luminosity,}." "en"])
(obo/IAO_0000109 obo/IAO_0000114 obo/IAO_0000125)
(obo/IAO_0000109 obo/IAO_0000115 ["A measurement datum is an information content entity that is a recording of the output of a measurement such as produced by a device." "en"])
(obo/IAO_0000109 obo/IAO_0000116 ["2/2/2009 is_specified_output of some assay?" "en"])
(obo/IAO_0000109 obo/IAO_0000117 ["person:Chris Stoeckert" "en"])
(obo/IAO_0000109 obo/IAO_0000119 ["OBI_0000305" "en"])
(obo/IAO_0000109 obo/IAO_0000119 ["group:OBI" "en"])
(obo/IAO_0000109 rdfs/label ["measurement datum" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000128
(obo/IAO_0000128 rdf/type owl/Class)
(obo/IAO_0000128 rdfs/subClassOf oboInOwl/ObsoleteClass)
(obo/IAO_0000128 obo/IAO_0000114 obo/IAO_0000121)
(obo/IAO_0000128 obo/IAO_0000116 ["_identifier is a container under information content entity for collecting types of terms to indicate a specific instance or clas of what was used or participated in an investigation. Identifiers are borne by a product or its packaging, and can be encoded in a variety of other information objects, such as bar codes, numerals, or patterns of dots.\n\nNote: everybody agreed that identifier is probably a too general term. We however felt that it would be appropriate to group \"identifiying\" terms under some kind of umbrella. We therefore propose to use _identifier for that purpose. As per OBI conventions, the _ prefixing identifier indicates that this is a helper class and shouldn't be considered as final."])
(obo/IAO_0000128 obo/IAO_0000231 obo/IAO_0000226)
(obo/IAO_0000128 rdfs/label ["obsolete_identifier"])
(obo/IAO_0000128 owl/deprecated true)
;; 
;; http://purl.obolibrary.org/obo/IAO_0000129
(obo/IAO_0000129 rdf/type owl/Class)
(obo/IAO_0000129 rdfs/subClassOf obo/IAO_0000028)
(obo/IAO_0000129 obo/IAO_0000111 ["version number" "en"])
(obo/IAO_0000129 obo/IAO_0000114 obo/IAO_0000123)
(obo/IAO_0000129 obo/IAO_0000115 ["A version number is an information content entity which is a sequence of characters borne by part of each of a class of manufactured products or its packaging and indicates its order within a set of other products having the same name." "en"])
(obo/IAO_0000129 obo/IAO_0000116 ["Note: we feel that at the moment we are happy with a general version number, and that we will subclass as needed in the future. For example, see 7. genome sequence version" "en"])
(obo/IAO_0000129 obo/IAO_0000117 ["GROUP: IAO" "en"])
(obo/IAO_0000129 rdfs/label ["version number" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000131
(obo/IAO_0000131 rdf/type owl/Class)
(obo/IAO_0000131 rdfs/subClassOf obo/IAO_0000028)
(obo/IAO_0000131 obo/IAO_0000111 ["serial number" "en"])
(obo/IAO_0000131 obo/IAO_0000114 obo/IAO_0000123)
(obo/IAO_0000131 obo/IAO_0000115 ["A serial number is an information content entity which is a unique sequence of characters borne by part of manufactured product or its packaging that is assigned to each individual in some class of products, and so can serve as a way to identify an individual product within the class. Serial numbers can be encoded in a variety of other information objects, such as bar codes, numerals, or patterns of dots." "en"])
(obo/IAO_0000131 obo/IAO_0000116 ["Note: during the call there was some confusion between serial number and model number. We agreed that it would be very helpful for all those terms to have example of usages - please add if you have any :-)" "en"])
(obo/IAO_0000131 obo/IAO_0000117 ["GROUP: IAO" "en"])
(obo/IAO_0000131 rdfs/label ["serial number" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000132
(obo/IAO_0000132 rdf/type owl/Class)
(obo/IAO_0000132 rdfs/subClassOf obo/IAO_0000028)
(obo/IAO_0000132 obo/IAO_0000111 ["lot number" "en"])
(obo/IAO_0000132 obo/IAO_0000114 obo/IAO_0000123)
(obo/IAO_0000132 obo/IAO_0000115 ["A lot number is an information content entity which is an identical sequence of character borne by part of manufactured product or its packaging for each instances of a product class in a discrete batch of an item. Lot numbers are usually assigned to each separate production run of an item. Manufacturing as a lot might be due to a variety of reasons, for example,  a single process during which many individuals are made from the same portion of source material. Lot numbers can be encoded in a pattern  of other information objects, such as bar codes, numerals, or patterns of dots." "en"])
(obo/IAO_0000132 obo/IAO_0000117 ["GROUP: IAO" "en"])
(obo/IAO_0000132 obo/IAO_0000118 ["batch number" "en"])
(obo/IAO_0000132 rdfs/label ["lot number" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000140
(obo/IAO_0000140 rdf/type owl/Class)
(obo/IAO_0000140 rdfs/subClassOf obo/IAO_0000027)
(obo/IAO_0000140 obo/IAO_0000114 obo/IAO_0000123)
(obo/IAO_0000140 obo/IAO_0000115 ["A settings datum is a datum that denotes some configuration of an instrument." "en"])
(obo/IAO_0000140 obo/IAO_0000116 ["2/3/2009 Feedback from OBI\n\nThis should be a \"setting specification\". There is a question of whether it is information about a realizable or not.\n\nPro other specification are about realizables.\nCons sometimes specifies a quality which is not a realizable." "en"])
(obo/IAO_0000140 obo/IAO_0000116 ["Alan grouped these in placeholder for the moment. Name by analogy to measurement datum." "en"])
(obo/IAO_0000140 rdfs/label ["setting datum" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000141
(obo/IAO_0000141 rdf/type owl/Class)
(obo/IAO_0000141 rdfs/subClassOf oboInOwl/ObsoleteClass)
(obo/IAO_0000141 obo/IAO_0000116 ["3/22/2009 Alan Ruttenberg, obsoleted per http://groups.google.com/group/information-ontology/browse_thread/thread/789ad4b7708d5cf4"])
(obo/IAO_0000141 obo/IAO_0000116 ["Need to rework digital entity. Digital quality was suggested by Barry."])
(obo/IAO_0000141 obo/IAO_0000231 obo/IAO_0000103)
(obo/IAO_0000141 rdfs/label ["obsolete_digital quality"])
(obo/IAO_0000141 owl/deprecated true)
;; 
;; http://purl.obolibrary.org/obo/IAO_0000144
(obo/IAO_0000144 rdf/type owl/Class)
(obo/IAO_0000144 rdfs/subClassOf obo/IAO_0000300)
(obo/IAO_0000144 obo/IAO_0000111 ["conclusion textual entity" "en"])
(obo/IAO_0000144 obo/IAO_0000112 ["that fucoidan has a small statistically significant effect on AT3 level but no useful clinical effect as in-vivo anticoagulant, a paraphrase of part of the last paragraph of the discussion section of the paper 'Pilot clinical study to evaluate the anticoagulant activity of fucoidan', by Lowenthal et. al.PMID:19696660" "en"])
(obo/IAO_0000144 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000144 obo/IAO_0000115 ["A textual entity that expresses the results of reasoning about a problem, for instance as typically found towards the end of scientific papers." "en"])
(obo/IAO_0000144 obo/IAO_0000116 ["2009/09/28 Alan Ruttenberg. Fucoidan-use-case" "en"])
(obo/IAO_0000144 obo/IAO_0000116 ["2009/10/23 Alan Ruttenberg: We need to work on the definition still" "en"])
(obo/IAO_0000144 obo/IAO_0000117 ["Person:Alan Ruttenberg" "en"])
(obo/IAO_0000144 rdfs/label ["conclusion textual entity" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000178
(obo/IAO_0000178 rdf/type owl/Class)
(obo/IAO_0000178 owl/equivalentClass bnode/ccp-extensions_genid216)
(bnode/ccp-extensions_genid216 owl/intersectionOf bnode/ccp-extensions_genid220)
(bnode/ccp-extensions_genid220 rdf/type rdf/List)
(bnode/ccp-extensions_genid220 rdf/first obo/BFO_0000040)
(bnode/ccp-extensions_genid220 rdf/rest bnode/ccp-extensions_genid217)
(bnode/ccp-extensions_genid217 rdf/type rdf/List)
(bnode/ccp-extensions_genid217 rdf/first bnode/ccp-extensions_genid218)
(bnode/ccp-extensions_genid218 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid218 owl/onProperty obo/RO_0000053)
(bnode/ccp-extensions_genid218 owl/someValuesFrom bnode/ccp-extensions_genid219)
(bnode/ccp-extensions_genid219 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid219 owl/onProperty obo/RO_0000059)
(bnode/ccp-extensions_genid219 owl/someValuesFrom obo/IAO_0000030)
(bnode/ccp-extensions_genid217 rdf/rest rdf/nil)
(bnode/ccp-extensions_genid216 rdf/type owl/Class)
(obo/IAO_0000178 rdfs/subClassOf obo/BFO_0000040)
(obo/IAO_0000178 obo/IAO_0000111 ["material information bearer" "en"])
(obo/IAO_0000178 obo/IAO_0000112 ["A page of a paperback novel with writing on it. The paper itself is a material information bearer, the pattern of ink is the information carrier." "en"])
(obo/IAO_0000178 obo/IAO_0000112 ["a brain" "en"])
(obo/IAO_0000178 obo/IAO_0000112 ["a hard drive" "en"])
(obo/IAO_0000178 obo/IAO_0000114 obo/IAO_0000122)
(obo/IAO_0000178 obo/IAO_0000115 ["A material entity in which a concretization of an information content entity inheres." "en"])
(obo/IAO_0000178 obo/IAO_0000117 ["GROUP: IAO" "en"])
(obo/IAO_0000178 rdfs/label ["material information bearer" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000179
(obo/IAO_0000179 rdf/type owl/Class)
(obo/IAO_0000179 rdfs/subClassOf obo/IAO_0000038)
(obo/IAO_0000179 obo/IAO_0000111 ["histogram" "en"])
(obo/IAO_0000179 obo/IAO_0000114 obo/IAO_0000122)
(obo/IAO_0000179 obo/IAO_0000115 ["A histogram is a report graph which is a statistical description of a\ndistribution in terms of occurrence frequencies of different event classes." "en"])
(obo/IAO_0000179 obo/IAO_0000117 ["PERSON:Chris Stoeckert" "en"])
(obo/IAO_0000179 obo/IAO_0000117 ["PERSON:James Malone" "en"])
(obo/IAO_0000179 obo/IAO_0000117 ["PERSON:Melanie Courtot" "en"])
(obo/IAO_0000179 obo/IAO_0000119 ["GROUP:OBI" "en"])
(obo/IAO_0000179 rdfs/label ["histogram" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000180
(obo/IAO_0000180 rdf/type owl/Class)
(obo/IAO_0000180 rdfs/subClassOf obo/IAO_0000038)
(obo/IAO_0000180 obo/IAO_0000111 ["heatmap" "en"])
(obo/IAO_0000180 obo/IAO_0000114 obo/IAO_0000122)
(obo/IAO_0000180 obo/IAO_0000115 ["A heatmap is a report graph which is a graphical representation of data\nwhere the values taken by a variable(s) are shown as colors in a\ntwo-dimensional map." "en"])
(obo/IAO_0000180 obo/IAO_0000117 ["PERSON:Chris Stoeckert" "en"])
(obo/IAO_0000180 obo/IAO_0000117 ["PERSON:James Malone" "en"])
(obo/IAO_0000180 obo/IAO_0000117 ["PERSON:Melanie Courtot" "en"])
(obo/IAO_0000180 obo/IAO_0000119 ["GROUP:OBI" "en"])
(obo/IAO_0000180 rdfs/label ["heatmap" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000181
(obo/IAO_0000181 rdf/type owl/Class)
(obo/IAO_0000181 rdfs/subClassOf obo/IAO_0000038)
(obo/IAO_0000181 obo/IAO_0000111 ["Venn diagram" "en"])
(obo/IAO_0000181 obo/IAO_0000114 obo/IAO_0000122)
(obo/IAO_0000181 obo/IAO_0000115 ["A Venn diagram is a report graph showing all hypothetically possible\nlogical relations between a finite collection of sets." "en"])
(obo/IAO_0000181 obo/IAO_0000117 ["PERSON:Chris Stoeckert" "en"])
(obo/IAO_0000181 obo/IAO_0000117 ["PERSON:James Malone" "en"])
(obo/IAO_0000181 obo/IAO_0000117 ["PERSON:Melanie Courtot" "en"])
(obo/IAO_0000181 obo/IAO_0000119 ["WEB: http://en.wikipedia.org/wiki/Venn_diagram" "en"])
(obo/IAO_0000181 rdfs/label ["Venn diagram" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000182
(obo/IAO_0000182 rdf/type owl/Class)
(obo/IAO_0000182 rdfs/subClassOf oboInOwl/ObsoleteClass)
(obo/IAO_0000182 obo/IAO_0000111 ["obsolete_survival curve" "en"])
(obo/IAO_0000182 obo/IAO_0000114 obo/IAO_0000122)
(obo/IAO_0000182 obo/IAO_0000115 ["A survival curve is a report graph which is a graphical representation of data where the percentage of survival is plotted as a function of time." "en"])
(obo/IAO_0000182 obo/IAO_0000117 ["PERSON:Chris Stoeckert" "en"])
(obo/IAO_0000182 obo/IAO_0000117 ["PERSON:James Malone" "en"])
(obo/IAO_0000182 obo/IAO_0000117 ["PERSON:Melanie Courtot" "en"])
(obo/IAO_0000182 obo/IAO_0000119 ["WEB: http://www.graphpad.com/www/book/survive.htm" "en"])
(obo/IAO_0000182 obo/IAO_0000231 obo/IAO_0000103)
(obo/IAO_0000182 rdfs/label ["obsolete_survival curve" "en"])
(obo/IAO_0000182 owl/deprecated true)
;; 
;; http://purl.obolibrary.org/obo/IAO_0000183
(obo/IAO_0000183 rdf/type owl/Class)
(obo/IAO_0000183 rdfs/subClassOf obo/IAO_0000038)
(obo/IAO_0000183 obo/IAO_0000111 ["dendrogram" "en"])
(obo/IAO_0000183 obo/IAO_0000112 ["Dendrograms are often used in computational biology to\nillustrate the clustering of genes." "en"])
(obo/IAO_0000183 obo/IAO_0000114 obo/IAO_0000122)
(obo/IAO_0000183 obo/IAO_0000115 ["A dendrogram is a report graph which is a tree diagram\nfrequently used to illustrate the arrangement of the clusters produced by a\nclustering algorithm." "en"])
(obo/IAO_0000183 obo/IAO_0000117 ["PERSON:Chris Stoeckert" "en"])
(obo/IAO_0000183 obo/IAO_0000117 ["PERSON:James Malone" "en"])
(obo/IAO_0000183 obo/IAO_0000117 ["PERSON:Melanie Courtot" "en"])
(obo/IAO_0000183 obo/IAO_0000119 ["WEB: http://en.wikipedia.org/wiki/Dendrogram" "en"])
(obo/IAO_0000183 rdfs/label ["dendrogram" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000184
(obo/IAO_0000184 rdf/type owl/Class)
(obo/IAO_0000184 rdfs/subClassOf obo/IAO_0000038)
(obo/IAO_0000184 obo/IAO_0000111 ["scatter plot" "en"])
(obo/IAO_0000184 obo/IAO_0000112 ["Comparison of gene expression values in two samples can be displayed in a scatter plot" "en"])
(obo/IAO_0000184 obo/IAO_0000114 obo/IAO_0000122)
(obo/IAO_0000184 obo/IAO_0000115 ["A scatterplot is a graph which uses Cartesian coordinates to display values for two variables for a set of data. The data is displayed as a collection of points, each having the value of one variable determining the position on the horizontal axis and the value of the other variable determining the position on the vertical axis." "en"])
(obo/IAO_0000184 obo/IAO_0000117 ["PERSON:Chris Stoeckert" "en"])
(obo/IAO_0000184 obo/IAO_0000117 ["PERSON:James Malone" "en"])
(obo/IAO_0000184 obo/IAO_0000117 ["PERSON:Melanie Courtot" "en"])
(obo/IAO_0000184 obo/IAO_0000118 ["scattergraph" "en"])
(obo/IAO_0000184 obo/IAO_0000119 ["WEB: http://en.wikipedia.org/wiki/Scatterplot" "en"])
(obo/IAO_0000184 rdfs/label ["scatter plot" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000185
(obo/IAO_0000185 rdf/type owl/Class)
(obo/IAO_0000185 rdfs/subClassOf obo/IAO_0000101)
(obo/IAO_0000185 obo/IAO_0000114 obo/IAO_0000122)
(obo/IAO_0000185 obo/IAO_0000115 ["A photograph is created by projecting an image onto a photosensitive surface such as a chemically treated plate or film, CCD receptor, etc." "en"])
(obo/IAO_0000185 obo/IAO_0000117 ["PERSON:Alan Ruttenberg" "en"])
(obo/IAO_0000185 obo/IAO_0000117 ["PERSON:Joanne Luciano" "en"])
(obo/IAO_0000185 obo/IAO_0000117 ["PERSON:Melanie Courtot" "en"])
(obo/IAO_0000185 obo/IAO_0000119 ["WEB: http://en.wiktionary.org/wiki/photograph" "en"])
(obo/IAO_0000185 rdfs/label ["photograph" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000186
(obo/IAO_0000186 rdf/type owl/Class)
(obo/IAO_0000186 owl/equivalentClass bnode/ccp-extensions_genid221)
(bnode/ccp-extensions_genid221 owl/intersectionOf bnode/ccp-extensions_genid225)
(bnode/ccp-extensions_genid225 rdf/type rdf/List)
(bnode/ccp-extensions_genid225 rdf/first obo/BFO_0000040)
(bnode/ccp-extensions_genid225 rdf/rest bnode/ccp-extensions_genid222)
(bnode/ccp-extensions_genid222 rdf/type rdf/List)
(bnode/ccp-extensions_genid222 rdf/first bnode/ccp-extensions_genid223)
(bnode/ccp-extensions_genid223 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid223 owl/onProperty obo/RO_0000053)
(bnode/ccp-extensions_genid223 owl/someValuesFrom bnode/ccp-extensions_genid224)
(bnode/ccp-extensions_genid224 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid224 owl/onProperty obo/RO_0000059)
(bnode/ccp-extensions_genid224 owl/someValuesFrom obo/IAO_0000185)
(bnode/ccp-extensions_genid222 rdf/rest rdf/nil)
(bnode/ccp-extensions_genid221 rdf/type owl/Class)
(obo/IAO_0000186 rdfs/subClassOf obo/IAO_0000178)
(obo/IAO_0000186 obo/IAO_0000111 ["photographic print" "en"])
(obo/IAO_0000186 obo/IAO_0000114 obo/IAO_0000125)
(obo/IAO_0000186 obo/IAO_0000115 ["A photographic print is a material entity upon which a photograph generically depends." "en"])
(obo/IAO_0000186 obo/IAO_0000117 ["PERSON:Alan Ruttenberg" "en"])
(obo/IAO_0000186 obo/IAO_0000117 ["PERSON:Melanie Courtot" "en"])
(obo/IAO_0000186 rdfs/label ["photographic print" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000225
(obo/IAO_0000225 rdf/type owl/Class)
(obo/IAO_0000225 owl/equivalentClass bnode/ccp-extensions_genid226)
(bnode/ccp-extensions_genid226 rdf/type owl/Class)
(bnode/ccp-extensions_genid226 owl/oneOf bnode/ccp-extensions_genid231)
(bnode/ccp-extensions_genid231 rdf/type rdf/List)
(bnode/ccp-extensions_genid231 rdf/first obo/IAO_0000103)
(bnode/ccp-extensions_genid231 rdf/rest bnode/ccp-extensions_genid230)
(bnode/ccp-extensions_genid230 rdf/type rdf/List)
(bnode/ccp-extensions_genid230 rdf/first obo/IAO_0000226)
(bnode/ccp-extensions_genid230 rdf/rest bnode/ccp-extensions_genid229)
(bnode/ccp-extensions_genid229 rdf/type rdf/List)
(bnode/ccp-extensions_genid229 rdf/first obo/IAO_0000227)
(bnode/ccp-extensions_genid229 rdf/rest bnode/ccp-extensions_genid228)
(bnode/ccp-extensions_genid228 rdf/type rdf/List)
(bnode/ccp-extensions_genid228 rdf/first obo/IAO_0000228)
(bnode/ccp-extensions_genid228 rdf/rest bnode/ccp-extensions_genid227)
(bnode/ccp-extensions_genid227 rdf/type rdf/List)
(bnode/ccp-extensions_genid227 rdf/first obo/IAO_0000229)
(bnode/ccp-extensions_genid227 rdf/rest rdf/nil)
(obo/IAO_0000225 rdfs/subClassOf obo/IAO_0000102)
(obo/IAO_0000225 obo/IAO_0000111 ["obsolescence reason specification" "en"])
(obo/IAO_0000225 obo/IAO_0000114 obo/IAO_0000125)
(obo/IAO_0000225 obo/IAO_0000115 ["The reason for which a term has been deprecated. The allowed values come from an enumerated list of predefined terms. See the specification of these instances for more detailed definitions of each enumerated value." "en"])
(obo/IAO_0000225 obo/IAO_0000116 ["The creation of this class has been inspired in part by Werner Ceusters' paper, Applying evolutionary terminology auditing to the Gene Ontology." "en"])
(obo/IAO_0000225 obo/IAO_0000117 ["PERSON: Alan Ruttenberg" "en"])
(obo/IAO_0000225 obo/IAO_0000117 ["PERSON: Melanie Courtot" "en"])
(obo/IAO_0000225 rdfs/label ["obsolescence reason specification" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000300
(obo/IAO_0000300 rdf/type owl/Class)
(obo/IAO_0000300 rdfs/subClassOf obo/IAO_0000030)
(obo/IAO_0000300 obo/IAO_0000111 ["textual entity" "en"])
(obo/IAO_0000300 obo/IAO_0000112 ["Words, sentences, paragraphs, and the written (non-figure) parts of publications are all textual entities" "en"])
(obo/IAO_0000300 obo/IAO_0000114 obo/IAO_0000123)
(obo/IAO_0000300 obo/IAO_0000115 ["A textual entity is a part of a manifestation (FRBR sense), a generically dependent continuant whose concretizations are patterns of glyphs intended to be interpreted as words, formulas, etc." "en"])
(obo/IAO_0000300 obo/IAO_0000116 ["AR, (IAO call 2009-09-01): a document as a whole is not typically a textual entity, because it has pictures in it - rather there are parts of it that are textual entities. Examples: The title, paragraph 2 sentence 7, etc." "en"])
(obo/IAO_0000300 obo/IAO_0000116 ["MC, 2009-09-14 (following IAO call 2009-09-01): textual entities live at the FRBR (http://en.wikipedia.org/wiki/Functional_Requirements_for_Bibliographic_Records) manifestation level. Everything is significant: line break, pdf and html versions of same document are different textual entities." "en"])
(obo/IAO_0000300 obo/IAO_0000117 ["PERSON: Lawrence Hunter" "en"])
(obo/IAO_0000300 obo/IAO_0000118 ["text" "en"])
(obo/IAO_0000300 rdfs/label ["textual entity" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000301
(obo/IAO_0000301 rdf/type owl/Class)
(obo/IAO_0000301 rdfs/subClassOf obo/IAO_0000300)
(obo/IAO_0000301 rdfs/subClassOf bnode/ccp-extensions_genid232)
(bnode/ccp-extensions_genid232 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid232 owl/onProperty obo/IAO_0000136)
(bnode/ccp-extensions_genid232 owl/someValuesFrom obo/IAO_0000310)
(obo/IAO_0000301 obo/IAO_0000111 ["citation" "en"])
(obo/IAO_0000301 obo/IAO_0000112 ["Verspoor, K., Cohen, KB., Hunter, L. Textual characteristics of traditional and Open Access scientific journals are similar, BMC Bioinformatics 2009, 10:183." "en"])
(obo/IAO_0000301 obo/IAO_0000114 obo/IAO_0000125)
(obo/IAO_0000301 obo/IAO_0000115 ["a textual entity intended to identify a particular publication" "en"])
(obo/IAO_0000301 obo/IAO_0000117 ["PERSON: Lawrence Hunter" "en"])
(obo/IAO_0000301 rdfs/label ["citation" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000302
(obo/IAO_0000302 rdf/type owl/Class)
(obo/IAO_0000302 rdfs/subClassOf obo/IAO_0000590)
(obo/IAO_0000302 obo/IAO_0000111 ["author identification" "en"])
(obo/IAO_0000302 obo/IAO_0000112 ["L. Hunter" "en"])
(obo/IAO_0000302 obo/IAO_0000114 obo/IAO_0000125)
(obo/IAO_0000302 obo/IAO_0000115 ["A textual entity intended to identify a particular author" "en"])
(obo/IAO_0000302 obo/IAO_0000117 ["PERSON: Lawrence Hunter" "en"])
(obo/IAO_0000302 rdfs/label ["author identification" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000303
(obo/IAO_0000303 rdf/type owl/Class)
(obo/IAO_0000303 rdfs/subClassOf obo/IAO_0000300)
(obo/IAO_0000303 obo/IAO_0000111 ["institutional identification" "en"])
(obo/IAO_0000303 obo/IAO_0000112 ["University of Colorado Denver School of Medicine" "en"])
(obo/IAO_0000303 obo/IAO_0000114 obo/IAO_0000125)
(obo/IAO_0000303 obo/IAO_0000115 ["A textual entity intended to identify a particular institution" "en"])
(obo/IAO_0000303 obo/IAO_0000117 ["PERSON: Lawrence Hunter" "en"])
(obo/IAO_0000303 rdfs/label ["institutional identification" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000304
(obo/IAO_0000304 rdf/type owl/Class)
(obo/IAO_0000304 rdfs/subClassOf obo/IAO_0000300)
(obo/IAO_0000304 rdfs/subClassOf bnode/ccp-extensions_genid233)
(bnode/ccp-extensions_genid233 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid233 owl/onProperty obo/IAO_0000136)
(bnode/ccp-extensions_genid233 owl/someValuesFrom obo/IAO_0000308)
(obo/IAO_0000304 obo/IAO_0000111 ["caption" "en"])
(obo/IAO_0000304 obo/IAO_0000112 ["Figure 1: A system diagram describing the modules of the Hanalyzer.  Reading methods (green) take external sources of knowledge (blue) and extract information from them, either by parsing structured data or biomedical language processing to extract information from unstructured data.  Reading modules are responsible for tracking the provenance of all knowledge.  Reasoning methods (yellow) enrich the knowledge that results from reading by, for example, noting two genes that are annotated to the same ontology term or database entry.  All knowledge sources, read or reasoned, are assigned a reliability score, and all are combined using that score into a knowledge network (orange) that represents the integration of all sorts of relationship between a pair of genes and a combined reliability score.  A data network (also orange) is created from experimental results to be analyzed.  The reporting modules (pink) integrate the data and knowledge networks, producing visualizations that can be queried with the associated drill-down tool." "en"])
(obo/IAO_0000304 obo/IAO_0000114 obo/IAO_0000125)
(obo/IAO_0000304 obo/IAO_0000115 ["A textual entity that describes a figure" "en"])
(obo/IAO_0000304 obo/IAO_0000117 ["PERSON: Lawrence Hunter" "en"])
(obo/IAO_0000304 rdfs/label ["caption" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000305
(obo/IAO_0000305 rdf/type owl/Class)
(obo/IAO_0000305 rdfs/subClassOf obo/IAO_0000300)
(obo/IAO_0000305 obo/IAO_0000111 ["document title" "en"])
(obo/IAO_0000305 obo/IAO_0000112 ["Textual characteristics of traditional and Open Access scientific journals are similar" "en"])
(obo/IAO_0000305 obo/IAO_0000114 obo/IAO_0000125)
(obo/IAO_0000305 obo/IAO_0000115 ["A textual entity that names a document" "en"])
(obo/IAO_0000305 obo/IAO_0000117 ["PERSON: Lawrence Hunter" "en"])
(obo/IAO_0000305 rdfs/label ["document title" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000306
(obo/IAO_0000306 rdf/type owl/Class)
(obo/IAO_0000306 rdfs/subClassOf obo/IAO_0000300)
(obo/IAO_0000306 obo/IAO_0000111 ["table" "en"])
(obo/IAO_0000306 obo/IAO_0000112 ["| T     F\n--+-----\nT   | T     F\nF   |  F    F" "en"])
(obo/IAO_0000306 obo/IAO_0000114 obo/IAO_0000125)
(obo/IAO_0000306 obo/IAO_0000115 ["A textual entity that contains a two-dimensional arrangement of texts repeated at regular intervals across a spatial range, such that the spatial relationships among the constituent texts expresses propositions" "en"])
(obo/IAO_0000306 obo/IAO_0000117 ["PERSON: Lawrence Hunter" "en"])
(obo/IAO_0000306 rdfs/label ["table" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000307
(obo/IAO_0000307 rdf/type owl/Class)
(obo/IAO_0000307 rdfs/subClassOf obo/IAO_0000306)
(obo/IAO_0000307 obo/IAO_0000111 ["table of abbreviations" "en"])
(obo/IAO_0000307 obo/IAO_0000112 ["IAO  information artifact ontology\nOBI  ontology of biomedical investiations\nGO   gene ontology" "en"])
(obo/IAO_0000307 obo/IAO_0000114 obo/IAO_0000125)
(obo/IAO_0000307 obo/IAO_0000115 ["A table where the constituent texts are abbreviations and their expansions" "en"])
(obo/IAO_0000307 obo/IAO_0000117 ["PERSON: Lawrence Hunter" "en"])
(obo/IAO_0000307 rdfs/label ["table of abbreviations" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000308
(obo/IAO_0000308 rdf/type owl/Class)
(obo/IAO_0000308 rdfs/subClassOf obo/IAO_0000030)
(obo/IAO_0000308 obo/IAO_0000111 ["figure" "en"])
(obo/IAO_0000308 obo/IAO_0000112 ["Any picture, diagram or table" "en"])
(obo/IAO_0000308 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000308 obo/IAO_0000115 ["An information content entity consisting of a two dimensional arrangement of information content entities such that the arrangement itself is about something." "en"])
(obo/IAO_0000308 obo/IAO_0000117 ["PERSON: Lawrence Hunter" "en"])
(obo/IAO_0000308 rdfs/label ["figure" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000309
(obo/IAO_0000309 rdf/type owl/Class)
(obo/IAO_0000309 rdfs/subClassOf obo/IAO_0000308)
(obo/IAO_0000309 obo/IAO_0000111 ["diagram" "en"])
(obo/IAO_0000309 obo/IAO_0000112 ["A molecular structure ribbon cartoon showing helices, turns and sheets and their relations to each other in space." "en"])
(obo/IAO_0000309 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000309 obo/IAO_0000115 ["A figure that expresses one or more propositions" "en"])
(obo/IAO_0000309 obo/IAO_0000117 ["PERSON: Lawrence Hunter" "en"])
(obo/IAO_0000309 rdfs/label ["diagram" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000310
(obo/IAO_0000310 rdf/type owl/Class)
(obo/IAO_0000310 rdfs/subClassOf obo/IAO_0000030)
(obo/IAO_0000310 obo/IAO_0000111 ["document" "en"])
(obo/IAO_0000310 obo/IAO_0000112 ["A journal article, patent application, laboratory notebook, or a book" "en"])
(obo/IAO_0000310 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000310 obo/IAO_0000115 ["A collection of information content entities intended to be understood together as a whole" "en"])
(obo/IAO_0000310 obo/IAO_0000117 ["PERSON: Lawrence Hunter" "en"])
(obo/IAO_0000310 rdfs/label ["document" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000311
(obo/IAO_0000311 rdf/type owl/Class)
(obo/IAO_0000311 rdfs/subClassOf obo/IAO_0000310)
(obo/IAO_0000311 obo/IAO_0000111 ["publication" "en"])
(obo/IAO_0000311 obo/IAO_0000112 ["A journal article or book" "en"])
(obo/IAO_0000311 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000311 obo/IAO_0000115 ["A document that has been accepted by a publisher" "en"])
(obo/IAO_0000311 obo/IAO_0000117 ["PERSON: Lawrence Hunter" "en"])
(obo/IAO_0000311 rdfs/label ["publication" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000312
(obo/IAO_0000312 rdf/type owl/Class)
(obo/IAO_0000312 rdfs/subClassOf obo/IAO_0000311)
(obo/IAO_0000312 rdfs/subClassOf bnode/ccp-extensions_genid234)
(bnode/ccp-extensions_genid234 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid234 owl/onProperty obo/IAO_0000136)
(bnode/ccp-extensions_genid234 owl/someValuesFrom obo/OBI_0000066)
(obo/IAO_0000312 obo/IAO_0000111 ["publication about an investigation" "en"])
(obo/IAO_0000312 obo/IAO_0000112 ["Most scientific journal articles" "en"])
(obo/IAO_0000312 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000312 obo/IAO_0000115 ["A publication that is about an investigation" "en"])
(obo/IAO_0000312 obo/IAO_0000117 ["PERSON: Lawrence Hunter" "en"])
(obo/IAO_0000312 obo/IAO_0000118 ["scientific publication" "en"])
(obo/IAO_0000312 rdfs/label ["publication about an investigation" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000313
(obo/IAO_0000313 rdf/type owl/Class)
(obo/IAO_0000313 rdfs/subClassOf obo/IAO_0000310)
(obo/IAO_0000313 obo/IAO_0000111 ["patent" "en"])
(obo/IAO_0000313 obo/IAO_0000112 ["US Patent 6,449,603" "en"])
(obo/IAO_0000313 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000313 obo/IAO_0000115 ["A document that has been accepted by a patent authority" "en"])
(obo/IAO_0000313 obo/IAO_0000117 ["PERSON: Lawrence Hunter" "en"])
(obo/IAO_0000313 rdfs/label ["patent" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000314
(obo/IAO_0000314 rdf/type owl/Class)
(obo/IAO_0000314 rdfs/subClassOf obo/IAO_0000030)
(obo/IAO_0000314 rdfs/subClassOf bnode/ccp-extensions_genid235)
(bnode/ccp-extensions_genid235 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid235 owl/onProperty obo/BFO_0000050)
(bnode/ccp-extensions_genid235 owl/someValuesFrom obo/IAO_0000310)
(obo/IAO_0000314 obo/IAO_0000111 ["document part" "en"])
(obo/IAO_0000314 obo/IAO_0000112 ["An abstract, introduction, method or results section." "en"])
(obo/IAO_0000314 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000314 obo/IAO_0000115 ["an information content entity that is part of a document" "en"])
(obo/IAO_0000314 obo/IAO_0000117 ["PERSON: Lawrence Hunter" "en"])
(obo/IAO_0000314 rdfs/label ["document part" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000315
(obo/IAO_0000315 rdf/type owl/Class)
(obo/IAO_0000315 rdfs/subClassOf obo/IAO_0000314)
(obo/IAO_0000315 rdfs/subClassOf bnode/ccp-extensions_genid236)
(bnode/ccp-extensions_genid236 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid236 owl/onProperty obo/IAO_0000136)
(bnode/ccp-extensions_genid236 owl/someValuesFrom obo/IAO_0000310)
(obo/IAO_0000315 obo/IAO_0000111 ["abstract" "en"])
(obo/IAO_0000315 obo/IAO_0000112 ["The profusion of high-throughput instruments and the explosion of new results in the scientific literature, particularly in molecular biomedicine, is both a blessing and a curse to the bench researcher.   Even knowledgeable and experienced scientists can benefit from computational tools that help navigate this vast and rapidly evolving terrain. In this paper, we describe a novel computational approach to this challenge, a knowledge-based system that combines reading, reasoning and reporting methods to facilitate analysis of experimental data. Reading methods extract information from external resources, either by parsing structured data or biomedical language processing to extract information from unstructured data, and track knowledge provenance.  Reasoning methods enrich the knowledge that results from reading by, for example, noting two genes that are annotated to the same ontology term or database entry.  Reasoning is also used to combine all sources into a knowledge network that represents the integration of all sorts of relationships between a pair of genes, and to calculate a combined reliability score. Reporting methods combine the knowledge network with a congruent network constructed from experimental data and visualize the combined network in a tool that facilitates the knowledge-based analysis of that data." "en"])
(obo/IAO_0000315 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000315 obo/IAO_0000115 ["A summary of the entire document that is substantially smaller than the document it summarizes.  It is about the document it summarizes." "en"])
(obo/IAO_0000315 obo/IAO_0000117 ["PERSON: Lawrence Hunter" "en"])
(obo/IAO_0000315 rdfs/label ["abstract" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000316
(obo/IAO_0000316 rdf/type owl/Class)
(obo/IAO_0000316 rdfs/subClassOf obo/IAO_0000314)
(obo/IAO_0000316 rdfs/subClassOf bnode/ccp-extensions_genid237)
(bnode/ccp-extensions_genid237 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid237 owl/onProperty obo/IAO_0000136)
(bnode/ccp-extensions_genid237 owl/someValuesFrom obo/IAO_0000005)
(obo/IAO_0000316 obo/IAO_0000111 ["introduction to a publication about an investigation" "en"])
(obo/IAO_0000316 obo/IAO_0000112 ["Section labelled 'introduction' of a typical scientific journal article" "en"])
(obo/IAO_0000316 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000316 obo/IAO_0000115 ["A part of a publication about an investigation that is about the objective specification (why the investigation is being done)" "en"])
(obo/IAO_0000316 obo/IAO_0000117 ["PERSON: Lawrence Hunter" "en"])
(obo/IAO_0000316 obo/IAO_0000118 ["background" "en"])
(obo/IAO_0000316 obo/IAO_0000118 ["introduction" "en"])
(obo/IAO_0000316 rdfs/label ["introduction to a publication about an investigation" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000317
(obo/IAO_0000317 rdf/type owl/Class)
(obo/IAO_0000317 rdfs/subClassOf obo/IAO_0000314)
(obo/IAO_0000317 rdfs/subClassOf bnode/ccp-extensions_genid238)
(bnode/ccp-extensions_genid238 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid238 owl/onProperty obo/IAO_0000136)
(bnode/ccp-extensions_genid238 owl/someValuesFrom obo/OBI_0500000)
(obo/IAO_0000317 obo/IAO_0000111 ["methods section" "en"])
(obo/IAO_0000317 obo/IAO_0000112 ["The section labelled 'Methods' or 'Materials and Methods' in a typical scientific journal article." "en"])
(obo/IAO_0000317 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000317 obo/IAO_0000115 ["A part of a publication about an investigation that is about the study design of the investigation" "en"])
(obo/IAO_0000317 obo/IAO_0000117 ["PERSON: Lawrence Hunter" "en"])
(obo/IAO_0000317 obo/IAO_0000118 ["experimental" "en"])
(obo/IAO_0000317 obo/IAO_0000118 ["experimental procedures" "en"])
(obo/IAO_0000317 obo/IAO_0000118 ["experimental section" "en"])
(obo/IAO_0000317 obo/IAO_0000118 ["methods" "en"])
(obo/IAO_0000317 rdfs/label ["methods section" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000318
(obo/IAO_0000318 rdf/type owl/Class)
(obo/IAO_0000318 rdfs/subClassOf obo/IAO_0000314)
(obo/IAO_0000318 rdfs/subClassOf bnode/ccp-extensions_genid239)
(bnode/ccp-extensions_genid239 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid239 owl/onProperty obo/IAO_0000136)
(bnode/ccp-extensions_genid239 owl/someValuesFrom obo/OBI_0000471)
(obo/IAO_0000318 obo/IAO_0000111 ["results section" "en"])
(obo/IAO_0000318 obo/IAO_0000112 ["The section labelled 'results' in a typical scientific journal article" "en"])
(obo/IAO_0000318 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000318 obo/IAO_0000115 ["A part of a publication about an investigation that is about a study design execution" "en"])
(obo/IAO_0000318 obo/IAO_0000117 ["PERSON: Lawrence Hunter" "en"])
(obo/IAO_0000318 obo/IAO_0000118 ["results" "en"])
(obo/IAO_0000318 rdfs/label ["results section" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000319
(obo/IAO_0000319 rdf/type owl/Class)
(obo/IAO_0000319 rdfs/subClassOf obo/IAO_0000314)
(obo/IAO_0000319 obo/IAO_0000111 ["discussion section of a publication about an investigation" "en"])
(obo/IAO_0000319 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000319 obo/IAO_0000115 ["A part of a publication about an investigation that is about the study interpretation of the investigation" "en"])
(obo/IAO_0000319 obo/IAO_0000117 ["PERSON: Lawrence Hunter" "en"])
(obo/IAO_0000319 obo/IAO_0000118 ["discussion" "en"])
(obo/IAO_0000319 obo/IAO_0000118 ["discussion section" "en"])
(obo/IAO_0000319 rdfs/label ["discussion section of a publication about an investigation" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000320
(obo/IAO_0000320 rdf/type owl/Class)
(obo/IAO_0000320 rdfs/subClassOf obo/IAO_0000314)
(obo/IAO_0000320 rdfs/subClassOf bnode/ccp-extensions_genid240)
(bnode/ccp-extensions_genid240 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid240 owl/onProperty obo/BFO_0000051)
(bnode/ccp-extensions_genid240 owl/someValuesFrom obo/IAO_0000301)
(obo/IAO_0000320 obo/IAO_0000111 ["references section" "en"])
(obo/IAO_0000320 obo/IAO_0000112 ["The list of citations found at the end of a scientific publication, grant proposal or patent application, sometimes called \"literature cited\" or \"bibliography\"" "en"])
(obo/IAO_0000320 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000320 obo/IAO_0000115 ["A part of a document that has citations as parts" "en"])
(obo/IAO_0000320 obo/IAO_0000117 ["PERSON: Lawrence Hunter" "en"])
(obo/IAO_0000320 rdfs/label ["references section" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000321
(obo/IAO_0000321 rdf/type owl/Class)
(obo/IAO_0000321 rdfs/subClassOf obo/IAO_0000314)
(obo/IAO_0000321 rdfs/subClassOf bnode/ccp-extensions_genid241)
(bnode/ccp-extensions_genid241 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid241 owl/onProperty obo/BFO_0000051)
(bnode/ccp-extensions_genid241 owl/someValuesFrom obo/IAO_0000302)
(obo/IAO_0000321 obo/IAO_0000111 ["author list" "en"])
(obo/IAO_0000321 obo/IAO_0000112 ["Lawrence Hunter and Kevin Brettonel Cohen" "en"])
(obo/IAO_0000321 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000321 obo/IAO_0000115 ["part of a document that enumerates the authors of the document" "en"])
(obo/IAO_0000321 obo/IAO_0000117 ["PERSON: Lawrence Hunter" "en"])
(obo/IAO_0000321 rdfs/label ["author list" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000322
(obo/IAO_0000322 rdf/type owl/Class)
(obo/IAO_0000322 rdfs/subClassOf obo/IAO_0000314)
(obo/IAO_0000322 rdfs/subClassOf bnode/ccp-extensions_genid242)
(bnode/ccp-extensions_genid242 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid242 owl/onProperty obo/BFO_0000051)
(bnode/ccp-extensions_genid242 owl/someValuesFrom obo/IAO_0000303)
(obo/IAO_0000322 obo/IAO_0000111 ["institution list" "en"])
(obo/IAO_0000322 obo/IAO_0000112 ["The University of Colorado Denver School of Medicine and the University of Colorado Boulder." "en"])
(obo/IAO_0000322 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000322 obo/IAO_0000115 ["part of a document that has parts that are institution identifications associated with the authors of the document" "en"])
(obo/IAO_0000322 obo/IAO_0000117 ["PERSON: Lawrence Hunter" "en"])
(obo/IAO_0000322 rdfs/label ["institution list" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000323
(obo/IAO_0000323 rdf/type owl/Class)
(obo/IAO_0000323 rdfs/subClassOf obo/IAO_0000314)
(obo/IAO_0000323 obo/IAO_0000111 ["author contributions section" "en"])
(obo/IAO_0000323 obo/IAO_0000112 ["LH conceived of the hypothesis, designed the study and contributed to the writing of the manuscript.  KBC executed the experiments, analyzed the data, and contributed to the writing of the manuscript." "en"])
(obo/IAO_0000323 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000323 obo/IAO_0000115 ["A part of a publication that is about the specific contributions of each author" "en"])
(obo/IAO_0000323 obo/IAO_0000117 ["PERSON: Lawrence Hunter" "en"])
(obo/IAO_0000323 obo/IAO_0000118 ["author contributions" "en"])
(obo/IAO_0000323 obo/IAO_0000118 ["contributions by the authors" "en"])
(obo/IAO_0000323 rdfs/label ["author contributions section" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000324
(obo/IAO_0000324 rdf/type owl/Class)
(obo/IAO_0000324 rdfs/subClassOf obo/IAO_0000314)
(obo/IAO_0000324 obo/IAO_0000111 ["acknowledgements section" "en"])
(obo/IAO_0000324 obo/IAO_0000112 ["The authors wish to thank Alan Ruttenberg for his constructive comments about an earlier draft of this manuscript" "en"])
(obo/IAO_0000324 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000324 obo/IAO_0000115 ["Part of a publication that is about the contributions of people or institutions other than the authors." "en"])
(obo/IAO_0000324 obo/IAO_0000117 ["PERSON: Lawrence Hunter" "en"])
(obo/IAO_0000324 obo/IAO_0000118 ["acknowledgements" "en"])
(obo/IAO_0000324 obo/IAO_0000118 ["acknowledgments" "en"])
(obo/IAO_0000324 rdfs/label ["acknowledgements section" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000325
(obo/IAO_0000325 rdf/type owl/Class)
(obo/IAO_0000325 rdfs/subClassOf obo/IAO_0000314)
(obo/IAO_0000325 rdfs/subClassOf bnode/ccp-extensions_genid243)
(bnode/ccp-extensions_genid243 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid243 owl/onProperty obo/IAO_0000136)
(bnode/ccp-extensions_genid243 owl/someValuesFrom obo/IAO_0000314)
(obo/IAO_0000325 obo/IAO_0000111 ["footnote" "en"])
(obo/IAO_0000325 obo/IAO_0000112 ["The referent in the text is usually indicated by a special typographic character such as * or a superscripted number, which is also used to indicate the footnote that refers to that text." "en"])
(obo/IAO_0000325 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000325 obo/IAO_0000115 ["A part of a document that is about a specific other part of the document.  Usually footnotes are spatially segregated from the rest of the document." "en"])
(obo/IAO_0000325 obo/IAO_0000117 ["PERSON: Lawrence Hunter" "en"])
(obo/IAO_0000325 obo/IAO_0000118 ["endnote" "en"])
(obo/IAO_0000325 rdfs/label ["footnote" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000326
(obo/IAO_0000326 rdf/type owl/Class)
(obo/IAO_0000326 rdfs/subClassOf obo/IAO_0000314)
(obo/IAO_0000326 obo/IAO_0000111 ["supplementary material to a document" "en"])
(obo/IAO_0000326 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000326 obo/IAO_0000115 ["part of a document that is segregated from the rest of the document due to its size" "en"])
(obo/IAO_0000326 obo/IAO_0000117 ["PERSON: Lawrence Hunter" "en"])
(obo/IAO_0000326 obo/IAO_0000118 ["additional information" "en"])
(obo/IAO_0000326 obo/IAO_0000118 ["appendix" "en"])
(obo/IAO_0000326 obo/IAO_0000118 ["supplemental information" "en"])
(obo/IAO_0000326 obo/IAO_0000118 ["supplementary material" "en"])
(obo/IAO_0000326 obo/IAO_0000118 ["supporting information" "en"])
(obo/IAO_0000326 rdfs/label ["supplementary material to a document" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000327
(obo/IAO_0000327 rdf/type owl/Class)
(obo/IAO_0000327 rdfs/subClassOf obo/IAO_0000306)
(obo/IAO_0000327 obo/IAO_0000111 ["table of contents" "en"])
(obo/IAO_0000327 obo/IAO_0000114 obo/IAO_0000125)
(obo/IAO_0000327 obo/IAO_0000115 ["A table that relates document parts to specific locations in a document (usually page numbers).  This is also a document part (subsumption there should be inferred)." "en"])
(obo/IAO_0000327 obo/IAO_0000117 ["PERSON: Lawrence Hunter" "en"])
(obo/IAO_0000327 rdfs/label ["table of contents" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000328
(obo/IAO_0000328 rdf/type owl/Class)
(obo/IAO_0000328 rdfs/subClassOf obo/IAO_0000306)
(obo/IAO_0000328 obo/IAO_0000111 ["table of figures" "en"])
(obo/IAO_0000328 obo/IAO_0000114 obo/IAO_0000125)
(obo/IAO_0000328 obo/IAO_0000115 ["A table that relates figures in a document to specific locations in that document (usually page numbers).  This is also a document part (subsumption there should be inferred)." "en"])
(obo/IAO_0000328 obo/IAO_0000117 ["PERSON: Lawrence Hunter" "en"])
(obo/IAO_0000328 rdfs/label ["table of figures" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000329
(obo/IAO_0000329 rdf/type owl/Class)
(obo/IAO_0000329 rdfs/subClassOf obo/IAO_0000305)
(obo/IAO_0000329 obo/IAO_0000111 ["running title" "en"])
(obo/IAO_0000329 obo/IAO_0000114 obo/IAO_0000125)
(obo/IAO_0000329 obo/IAO_0000115 ["A shorter version of a document title" "en"])
(obo/IAO_0000329 obo/IAO_0000117 ["PERSON: Lawrence Hunter" "en"])
(obo/IAO_0000329 rdfs/label ["running title" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000330
(obo/IAO_0000330 rdf/type owl/Class)
(obo/IAO_0000330 rdfs/subClassOf obo/IAO_0000314)
(obo/IAO_0000330 obo/IAO_0000111 ["copyright section" "en"])
(obo/IAO_0000330 obo/IAO_0000112 ["This work is licensed under a Creative Commons Attribution-Share Alike 3.0 United States License." "en"])
(obo/IAO_0000330 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000330 obo/IAO_0000115 ["A document part that describes  legal restrictions on making or distributing copies of the document" "en"])
(obo/IAO_0000330 obo/IAO_0000117 ["PERSON: Lawrence Hunter" "en"])
(obo/IAO_0000330 rdfs/label ["copyright section" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000400
(obo/IAO_0000400 rdf/type owl/Class)
(obo/IAO_0000400 rdfs/subClassOf obo/IAO_0000027)
(obo/IAO_0000400 rdfs/subClassOf bnode/ccp-extensions_genid244)
(bnode/ccp-extensions_genid244 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid244 owl/onProperty obo/IAO_0000219)
(bnode/ccp-extensions_genid244 owl/someValuesFrom obo/BFO_0000018)
(obo/IAO_0000400 rdfs/subClassOf bnode/ccp-extensions_genid245)
(bnode/ccp-extensions_genid245 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid245 owl/onProperty obo/IAO_0000407)
(bnode/ccp-extensions_genid245 owl/cardinality 1)
(obo/IAO_0000400 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000400 obo/IAO_0000115 ["A cartesian spatial coordinate datum is a representation of a point in a spatial region, in which equal changes in the magnitude of a coordinate value denote length qualities with the same magnitude" "en"])
(obo/IAO_0000400 obo/IAO_0000116 ["2009-08-18 Alan Ruttenberg - question to BFO list about whether the BFO sense of the lower dimensional regions is that they are always part of actual space (the three dimensional sort) http://groups.google.com/group/bfo-discuss/browse_thread/thread/9d04e717e39fb617" "en"])
(obo/IAO_0000400 obo/IAO_0000117 ["Alan Ruttenberg" "en"])
(obo/IAO_0000400 obo/IAO_0000232 ["AR notes: We need to discuss whether it should include site."])
(obo/IAO_0000400 rdfs/label ["cartesian spatial coordinate datum" "en"])
(obo/IAO_0000400 rdfs/seeAlso ["http://groups.google.com/group/bfo-discuss/browse_thread/thread/9d04e717e39fb617" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000401
(obo/IAO_0000401 rdf/type owl/Class)
(obo/IAO_0000401 rdfs/subClassOf obo/IAO_0000400)
(obo/IAO_0000401 rdfs/subClassOf bnode/ccp-extensions_genid246)
(bnode/ccp-extensions_genid246 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid246 owl/onProperty obo/IAO_0000219)
(bnode/ccp-extensions_genid246 owl/someValuesFrom bnode/ccp-extensions_genid247)
(bnode/ccp-extensions_genid247 owl/intersectionOf bnode/ccp-extensions_genid250)
(bnode/ccp-extensions_genid250 rdf/type rdf/List)
(bnode/ccp-extensions_genid250 rdf/first obo/BFO_0000018)
(bnode/ccp-extensions_genid250 rdf/rest bnode/ccp-extensions_genid248)
(bnode/ccp-extensions_genid248 rdf/type rdf/List)
(bnode/ccp-extensions_genid248 rdf/first bnode/ccp-extensions_genid249)
(bnode/ccp-extensions_genid249 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid249 owl/onProperty obo/BFO_0000050)
(bnode/ccp-extensions_genid249 owl/someValuesFrom obo/BFO_0000026)
(bnode/ccp-extensions_genid248 rdf/rest rdf/nil)
(bnode/ccp-extensions_genid247 rdf/type owl/Class)
(obo/IAO_0000401 rdfs/subClassOf bnode/ccp-extensions_genid251)
(bnode/ccp-extensions_genid251 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid251 owl/onProperty obo/IAO_0000404)
(bnode/ccp-extensions_genid251 owl/cardinality 1)
(obo/IAO_0000401 obo/IAO_0000114 obo/IAO_0000123)
(obo/IAO_0000401 obo/IAO_0000115 ["A cartesion spatial coordinate datum that  uses one value to specify a position along a one dimensional spatial region" "en"])
(obo/IAO_0000401 obo/IAO_0000117 ["Alan Ruttenberg" "en"])
(obo/IAO_0000401 rdfs/label ["one dimensional cartesian spatial coordinate datum" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000402
(obo/IAO_0000402 rdf/type owl/Class)
(obo/IAO_0000402 rdfs/subClassOf obo/IAO_0000400)
(obo/IAO_0000402 rdfs/subClassOf bnode/ccp-extensions_genid252)
(bnode/ccp-extensions_genid252 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid252 owl/onProperty obo/IAO_0000219)
(bnode/ccp-extensions_genid252 owl/someValuesFrom bnode/ccp-extensions_genid253)
(bnode/ccp-extensions_genid253 owl/intersectionOf bnode/ccp-extensions_genid256)
(bnode/ccp-extensions_genid256 rdf/type rdf/List)
(bnode/ccp-extensions_genid256 rdf/first obo/BFO_0000018)
(bnode/ccp-extensions_genid256 rdf/rest bnode/ccp-extensions_genid254)
(bnode/ccp-extensions_genid254 rdf/type rdf/List)
(bnode/ccp-extensions_genid254 rdf/first bnode/ccp-extensions_genid255)
(bnode/ccp-extensions_genid255 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid255 owl/onProperty obo/BFO_0000050)
(bnode/ccp-extensions_genid255 owl/someValuesFrom obo/BFO_0000009)
(bnode/ccp-extensions_genid254 rdf/rest rdf/nil)
(bnode/ccp-extensions_genid253 rdf/type owl/Class)
(obo/IAO_0000402 rdfs/subClassOf bnode/ccp-extensions_genid257)
(bnode/ccp-extensions_genid257 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid257 owl/onProperty obo/IAO_0000404)
(bnode/ccp-extensions_genid257 owl/cardinality 1)
(obo/IAO_0000402 rdfs/subClassOf bnode/ccp-extensions_genid258)
(bnode/ccp-extensions_genid258 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid258 owl/onProperty obo/IAO_0000406)
(bnode/ccp-extensions_genid258 owl/cardinality 1)
(obo/IAO_0000402 obo/IAO_0000114 obo/IAO_0000123)
(obo/IAO_0000402 obo/IAO_0000115 ["A cartesion spatial coordinate datum that  uses two values to specify a position within a two dimensional spatial region" "en"])
(obo/IAO_0000402 obo/IAO_0000117 ["Alan Ruttenberg" "en"])
(obo/IAO_0000402 rdfs/label ["two dimensional cartesian spatial coordinate datum" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000403
(obo/IAO_0000403 rdf/type owl/Class)
(obo/IAO_0000403 rdfs/subClassOf obo/IAO_0000400)
(obo/IAO_0000403 rdfs/subClassOf bnode/ccp-extensions_genid259)
(bnode/ccp-extensions_genid259 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid259 owl/onProperty obo/IAO_0000219)
(bnode/ccp-extensions_genid259 owl/someValuesFrom bnode/ccp-extensions_genid260)
(bnode/ccp-extensions_genid260 owl/intersectionOf bnode/ccp-extensions_genid263)
(bnode/ccp-extensions_genid263 rdf/type rdf/List)
(bnode/ccp-extensions_genid263 rdf/first obo/BFO_0000018)
(bnode/ccp-extensions_genid263 rdf/rest bnode/ccp-extensions_genid261)
(bnode/ccp-extensions_genid261 rdf/type rdf/List)
(bnode/ccp-extensions_genid261 rdf/first bnode/ccp-extensions_genid262)
(bnode/ccp-extensions_genid262 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid262 owl/onProperty obo/BFO_0000050)
(bnode/ccp-extensions_genid262 owl/someValuesFrom obo/BFO_0000028)
(bnode/ccp-extensions_genid261 rdf/rest rdf/nil)
(bnode/ccp-extensions_genid260 rdf/type owl/Class)
(obo/IAO_0000403 rdfs/subClassOf bnode/ccp-extensions_genid264)
(bnode/ccp-extensions_genid264 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid264 owl/onProperty obo/IAO_0000404)
(bnode/ccp-extensions_genid264 owl/cardinality 1)
(obo/IAO_0000403 rdfs/subClassOf bnode/ccp-extensions_genid265)
(bnode/ccp-extensions_genid265 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid265 owl/onProperty obo/IAO_0000405)
(bnode/ccp-extensions_genid265 owl/cardinality 1)
(obo/IAO_0000403 rdfs/subClassOf bnode/ccp-extensions_genid266)
(bnode/ccp-extensions_genid266 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid266 owl/onProperty obo/IAO_0000406)
(bnode/ccp-extensions_genid266 owl/cardinality 1)
(obo/IAO_0000403 obo/IAO_0000114 obo/IAO_0000123)
(obo/IAO_0000403 obo/IAO_0000115 ["A cartesion spatial coordinate datum that  uses three values to specify a position within a three dimensional spatial region" "en"])
(obo/IAO_0000403 obo/IAO_0000117 ["Alan Ruttenberg" "en"])
(obo/IAO_0000403 rdfs/label ["three dimensional cartesian spatial coordinate datum" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000408
(obo/IAO_0000408 rdf/type owl/Class)
(obo/IAO_0000408 rdfs/subClassOf obo/IAO_0000032)
(obo/IAO_0000408 rdfs/subClassOf bnode/ccp-extensions_genid267)
(bnode/ccp-extensions_genid267 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid267 owl/onProperty obo/IAO_0000039)
(bnode/ccp-extensions_genid267 owl/someValuesFrom obo/UO_0000001)
(obo/IAO_0000408 rdfs/subClassOf bnode/ccp-extensions_genid268)
(bnode/ccp-extensions_genid268 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid268 owl/onProperty obo/IAO_0000221)
(bnode/ccp-extensions_genid268 owl/someValuesFrom obo/PATO_0000122)
(obo/IAO_0000408 obo/IAO_0000114 obo/IAO_0000123)
(obo/IAO_0000408 obo/IAO_0000115 ["A scalar measurement datum that is the result of measurement of length quality" "en"])
(obo/IAO_0000408 obo/IAO_0000117 ["Alan Ruttenberg" "en"])
(obo/IAO_0000408 rdfs/label ["length measurement datum" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000409
(obo/IAO_0000409 rdf/type owl/Class)
(obo/IAO_0000409 owl/equivalentClass bnode/ccp-extensions_genid269)
(bnode/ccp-extensions_genid269 rdf/type owl/Class)
(bnode/ccp-extensions_genid269 owl/oneOf bnode/ccp-extensions_genid272)
(bnode/ccp-extensions_genid272 rdf/type rdf/List)
(bnode/ccp-extensions_genid272 rdf/first obo/IAO_0000410)
(bnode/ccp-extensions_genid272 rdf/rest bnode/ccp-extensions_genid271)
(bnode/ccp-extensions_genid271 rdf/type rdf/List)
(bnode/ccp-extensions_genid271 rdf/first obo/IAO_0000420)
(bnode/ccp-extensions_genid271 rdf/rest bnode/ccp-extensions_genid270)
(bnode/ccp-extensions_genid270 rdf/type rdf/List)
(bnode/ccp-extensions_genid270 rdf/first obo/IAO_0000421)
(bnode/ccp-extensions_genid270 rdf/rest rdf/nil)
(obo/IAO_0000409 rdfs/subClassOf obo/IAO_0000102)
(obo/IAO_0000409 obo/IAO_0000112 ["The Basic Formal Ontology ontology makes a distinction between Universals and defined classes, where the formal are \"natural kinds\" and the latter arbitrary collections of entities."])
(obo/IAO_0000409 obo/IAO_0000115 ["A denotator type indicates how a term should be interpreted from an ontological perspective."])
(obo/IAO_0000409 obo/IAO_0000117 ["Alan Ruttenberg"])
(obo/IAO_0000409 obo/IAO_0000119 ["Barry Smith, Werner Ceusters"])
(obo/IAO_0000409 rdfs/label ["denotator type"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000414
(obo/IAO_0000414 rdf/type owl/Class)
(obo/IAO_0000414 rdfs/subClassOf obo/IAO_0000032)
(obo/IAO_0000414 rdfs/subClassOf bnode/ccp-extensions_genid273)
(bnode/ccp-extensions_genid273 owl/intersectionOf bnode/ccp-extensions_genid276)
(bnode/ccp-extensions_genid276 rdf/type rdf/List)
(bnode/ccp-extensions_genid276 rdf/first bnode/ccp-extensions_genid277)
(bnode/ccp-extensions_genid277 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid277 owl/onProperty obo/IAO_0000039)
(bnode/ccp-extensions_genid277 owl/allValuesFrom obo/UO_0000002)
(bnode/ccp-extensions_genid276 rdf/rest bnode/ccp-extensions_genid274)
(bnode/ccp-extensions_genid274 rdf/type rdf/List)
(bnode/ccp-extensions_genid274 rdf/first bnode/ccp-extensions_genid275)
(bnode/ccp-extensions_genid275 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid275 owl/onProperty obo/IAO_0000221)
(bnode/ccp-extensions_genid275 owl/allValuesFrom obo/PATO_0000125)
(bnode/ccp-extensions_genid274 rdf/rest rdf/nil)
(bnode/ccp-extensions_genid273 rdf/type owl/Class)
(obo/IAO_0000414 obo/IAO_0000114 obo/IAO_0000123)
(obo/IAO_0000414 obo/IAO_0000115 ["A scalar measurement datum that is the result of measurement of mass quality" "en"])
(obo/IAO_0000414 obo/IAO_0000116 ["2009/09/28 Alan Ruttenberg. Fucoidan-use-case" "en"])
(obo/IAO_0000414 obo/IAO_0000117 ["Person:Alan Ruttenberg" "en"])
(obo/IAO_0000414 rdfs/label ["mass measurement datum" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000415
(obo/IAO_0000415 rdf/type owl/Class)
(obo/IAO_0000415 rdfs/subClassOf obo/IAO_0000300)
(obo/IAO_0000415 obo/IAO_0000111 ["hypothesis textual entity" "en"])
(obo/IAO_0000415 obo/IAO_0000112 ["that fucoidan has a small statistically significant effect on AT3 level but no useful clinical effect as in-vivo anticoagulant, a paraphrase of part of the last paragraph of the discussion section of the paper 'Pilot clinical study to evaluate the anticoagulant activity of fucoidan', by Lowenthal et. al.PMID:19696660" "en"])
(obo/IAO_0000415 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000415 obo/IAO_0000115 ["A textual entity that expresses an assertion that is intended to be tested." "en"])
(obo/IAO_0000415 obo/IAO_0000116 ["2009/09/28 Alan Ruttenberg. Fucoidan-use-case" "en"])
(obo/IAO_0000415 obo/IAO_0000117 ["Person:Alan Ruttenberg" "en"])
(obo/IAO_0000415 rdfs/label ["hypothesis textual entity" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000416
(obo/IAO_0000416 rdf/type owl/Class)
(obo/IAO_0000416 rdfs/subClassOf obo/IAO_0000032)
(obo/IAO_0000416 rdfs/subClassOf bnode/ccp-extensions_genid278)
(bnode/ccp-extensions_genid278 owl/intersectionOf bnode/ccp-extensions_genid281)
(bnode/ccp-extensions_genid281 rdf/type rdf/List)
(bnode/ccp-extensions_genid281 rdf/first bnode/ccp-extensions_genid282)
(bnode/ccp-extensions_genid282 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid282 owl/onProperty obo/IAO_0000039)
(bnode/ccp-extensions_genid282 owl/allValuesFrom obo/UO_0000003)
(bnode/ccp-extensions_genid281 rdf/rest bnode/ccp-extensions_genid279)
(bnode/ccp-extensions_genid279 rdf/type rdf/List)
(bnode/ccp-extensions_genid279 rdf/first bnode/ccp-extensions_genid280)
(bnode/ccp-extensions_genid280 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid280 owl/onProperty obo/IAO_0000413)
(bnode/ccp-extensions_genid280 owl/allValuesFrom obo/BFO_0000015)
(bnode/ccp-extensions_genid279 rdf/rest rdf/nil)
(bnode/ccp-extensions_genid278 rdf/type owl/Class)
(obo/IAO_0000416 obo/IAO_0000114 obo/IAO_0000123)
(obo/IAO_0000416 obo/IAO_0000115 ["A scalar measurement datum that is the result of measuring a temporal interval" "en"])
(obo/IAO_0000416 obo/IAO_0000116 ["2009/09/28 Alan Ruttenberg. Fucoidan-use-case" "en"])
(obo/IAO_0000416 obo/IAO_0000117 ["Person:Alan Ruttenberg" "en"])
(obo/IAO_0000416 rdfs/label ["time measurement datum" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000422
(obo/IAO_0000422 rdf/type owl/Class)
(obo/IAO_0000422 rdfs/subClassOf obo/IAO_0000300)
(obo/IAO_0000422 rdfs/subClassOf bnode/ccp-extensions_genid283)
(bnode/ccp-extensions_genid283 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid283 owl/onProperty obo/IAO_0000136)
(bnode/ccp-extensions_genid283 owl/someValuesFrom obo/GAZ_00000448)
(obo/IAO_0000422 obo/IAO_0000114 obo/IAO_0000123)
(obo/IAO_0000422 obo/IAO_0000115 ["A textual entity that is used as directive to deliver something to a person, or organization"])
 ;(obo/IAO_0000422 obo/IAO_0000116 ["2010-05-24 Alan Ruttenberg. Use label for the string representation. See issue https://github.com/information-artifact-ontology/IAO/issues/59"])
(obo/IAO_0000422 rdfs/label ["postal address" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000429
(obo/IAO_0000429 rdf/type owl/Class)
(obo/IAO_0000429 rdfs/subClassOf obo/IAO_0000030)
(obo/IAO_0000429 obo/IAO_0000111 ["email address" "en"])
(obo/IAO_0000429 obo/IAO_0000114 obo/IAO_0000123)
 ;(obo/IAO_0000429 obo/IAO_0000116 ["Alan Ruttenberg 1/3/2012 - Provisional id, see issue at https://github.com/information-artifact-ontology/IAO/issues/130&thanks=130&ts=1325636583" "en"])
(obo/IAO_0000429 obo/IAO_0000117 ["Person:Alan Ruttenberg" "en"])
(obo/IAO_0000429 obo/IAO_0000117 ["Person:Chris Stoeckart" "en"])
(obo/IAO_0000429 rdfs/label ["email address" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000442
(obo/IAO_0000442 rdf/type owl/Class)
(obo/IAO_0000442 rdfs/subClassOf obo/BFO_0000023)
(obo/IAO_0000442 obo/IAO_0000111 ["author role" "en"])
(obo/IAO_0000442 obo/IAO_0000114 obo/IAO_0000125)
(obo/IAO_0000442 obo/IAO_0000115 ["A role inhering in a person or organization that is realized when the bearer participates in the work which is the basis of the document, in the writing of the document, and signs it with their name." "en"])
(obo/IAO_0000442 obo/IAO_0000117 ["PERSON: Alan Ruttenberg" "en"])
(obo/IAO_0000442 obo/IAO_0000117 ["PERSON: Melanie Courtot" "en"])
(obo/IAO_0000442 rdfs/label ["author role" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000443
(obo/IAO_0000443 rdf/type owl/Class)
(obo/IAO_0000443 rdfs/subClassOf obo/OBI_0000011)
(obo/IAO_0000443 rdfs/subClassOf bnode/ccp-extensions_genid284)
(bnode/ccp-extensions_genid284 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid284 owl/onProperty obo/OBI_0000293)
(bnode/ccp-extensions_genid284 owl/someValuesFrom obo/IAO_0000013)
(obo/IAO_0000443 rdfs/subClassOf bnode/ccp-extensions_genid285)
(bnode/ccp-extensions_genid285 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid285 owl/onProperty obo/OBI_0000299)
(bnode/ccp-extensions_genid285 owl/someValuesFrom obo/IAO_0000027)
(obo/IAO_0000443 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000443 obo/IAO_0000115 ["a planned process in which journal articles are read or processed and data items are extracted, typically for further analysis or indexing"])
(obo/IAO_0000443 obo/IAO_0000117 ["Person:Alan Ruttenberg"])
(obo/IAO_0000443 rdfs/label ["data item extraction from journal article"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000572
(obo/IAO_0000572 rdf/type owl/Class)
(obo/IAO_0000572 rdfs/subClassOf obo/OBI_0000011)
(obo/IAO_0000572 rdfs/subClassOf bnode/ccp-extensions_genid286)
(bnode/ccp-extensions_genid286 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid286 owl/onProperty obo/OBI_0000293)
(bnode/ccp-extensions_genid286 owl/someValuesFrom obo/IAO_0000030)
(obo/IAO_0000572 rdfs/subClassOf bnode/ccp-extensions_genid287)
(bnode/ccp-extensions_genid287 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid287 owl/onProperty obo/OBI_0000299)
(bnode/ccp-extensions_genid287 owl/someValuesFrom obo/IAO_0000310)
(obo/IAO_0000572 obo/IAO_0000112 ["Recording the current temperature in a laboratory notebook. Writing a journal article. Updating a patient record in a database." "en"])
(obo/IAO_0000572 obo/IAO_0000114 obo/IAO_0000125)
(obo/IAO_0000572 obo/IAO_0000115 ["a planned process in which a document is created or added to by including the specified input in it." "en"])
(obo/IAO_0000572 obo/IAO_0000116 ["6/11/9: Edited at OBI workshop. We need to be able identify a child form of information artifact which corresponds to something enduring (not brain like). This used to be restricted to physical document or digital entity as the output, but that excludes e.g. an audio cassette tape" "en"])
(obo/IAO_0000572 obo/IAO_0000117 ["Bjoern Peters" "en"])
(obo/IAO_0000572 obo/IAO_0000119 ["wikipedia http://en.wikipedia.org/wiki/Documenting" "en"])
(obo/IAO_0000572 rdfs/label ["documenting" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000573
(obo/IAO_0000573 rdf/type owl/Class)
(obo/IAO_0000573 rdfs/subClassOf obo/IAO_0000038)
(obo/IAO_0000573 obo/IAO_0000111 ["line graph" "en"])
(obo/IAO_0000573 obo/IAO_0000114 obo/IAO_0000122)
(obo/IAO_0000573 obo/IAO_0000115 ["A line graph is a type of graph created by connecting a series of data\npoints together with a line." "en"])
(obo/IAO_0000573 obo/IAO_0000117 ["PERSON:Chris Stoeckert" "en"])
(obo/IAO_0000573 obo/IAO_0000117 ["PERSON:Melanie Courtot" "en"])
(obo/IAO_0000573 obo/IAO_0000118 ["line chart" "en"])
(obo/IAO_0000573 obo/IAO_0000119 ["GROUP:OBI" "en"])
(obo/IAO_0000573 obo/IAO_0000119 ["WEB: http://en.wikipedia.org/wiki/Line_chart" "en"])
(obo/IAO_0000573 rdfs/label ["line graph" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000574
(obo/IAO_0000574 rdf/type owl/Class)
(obo/IAO_0000574 rdfs/subClassOf obo/OBI_0000011)
(obo/IAO_0000574 rdfs/subClassOf bnode/ccp-extensions_genid288)
(bnode/ccp-extensions_genid288 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid288 owl/onProperty obo/OBI_0000299)
(bnode/ccp-extensions_genid288 owl/someValuesFrom obo/IAO_0000577)
(obo/IAO_0000574 rdfs/subClassOf bnode/ccp-extensions_genid289)
(bnode/ccp-extensions_genid289 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid289 owl/onProperty obo/RO_0000057)
(bnode/ccp-extensions_genid289 owl/someValuesFrom obo/IAO_0000579)
(obo/IAO_0000574 obo/IAO_0000112 ["A new pubmed ID being created for a journal article, and the associated pubmed record containing information to the journal article. A license plate number registered at the DMV to be belonging to a specific vehicle and owner. Placing a barcode on a product and entering information in a database that this barcode is assigned." "en"])
(obo/IAO_0000574 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000574 obo/IAO_0000115 ["a planned process in which a new CRID is created, associated with an entity, and stored in the CRID registry thereby registering it as being associated with some entity" "en"])
(obo/IAO_0000574 obo/IAO_0000116 ["2014-05-05: It is the CRID registry that assigns CRIDs, not the users of the registry." "en"])
(obo/IAO_0000574 obo/IAO_0000117 ["Person:Alan Ruttenberg" "en"])
(obo/IAO_0000574 obo/IAO_0000117 ["Person:Bjoern Peters" "en"])
(obo/IAO_0000574 obo/IAO_0000117 ["Person:Melanie Courtot" "en"])
(obo/IAO_0000574 obo/IAO_0000118 ["assigning a CRID"])
(obo/IAO_0000574 rdfs/label ["assigning a centrally registered identifier" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000575
(obo/IAO_0000575 rdf/type owl/Class)
(obo/IAO_0000575 rdfs/subClassOf obo/OBI_0000011)
(obo/IAO_0000575 rdfs/subClassOf bnode/ccp-extensions_genid290)
(bnode/ccp-extensions_genid290 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid290 owl/onProperty obo/OBI_0000293)
(bnode/ccp-extensions_genid290 owl/someValuesFrom obo/IAO_0000030)
(obo/IAO_0000575 rdfs/subClassOf bnode/ccp-extensions_genid291)
(bnode/ccp-extensions_genid291 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid291 owl/onProperty obo/OBI_0000293)
(bnode/ccp-extensions_genid291 owl/someValuesFrom obo/IAO_0000577)
(obo/IAO_0000575 rdfs/subClassOf bnode/ccp-extensions_genid292)
(bnode/ccp-extensions_genid292 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid292 owl/onProperty obo/RO_0000057)
(bnode/ccp-extensions_genid292 owl/someValuesFrom obo/IAO_0000579)
(obo/IAO_0000575 obo/IAO_0000112 ["Articles in Pubmed are reviewed by curators who add MESH terms to the Pubmed records in order to categorize them better and improve the ability to search for them. "])
(obo/IAO_0000575 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000575 obo/IAO_0000115 ["A planned process in which a CRID registry associates an information content entity with a CRID symbol" "en"])
(obo/IAO_0000575 obo/IAO_0000117 ["PERSON:Alan Ruttenberg" "en"])
(obo/IAO_0000575 obo/IAO_0000118 ["associating information with a CRID in the CRID registry"])
(obo/IAO_0000575 rdfs/label ["associating information with a centrally registered identifier in its registry" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000576
(obo/IAO_0000576 rdf/type owl/Class)
(obo/IAO_0000576 rdfs/subClassOf oboInOwl/ObsoleteClass)
(obo/IAO_0000576 obo/IAO_0000115 ["a planned process with the objective to establish a system that allows to refer to specific entities of a certain kind and store information about them, by establishing a CRID registry and plan specifications for the process of 1) assigning a CRID and 2) looking up a CRID."])
(obo/IAO_0000576 obo/IAO_0000116 ["MC, 20101124: deprecated following discussion at IAO call 20101124. Term was deemed not necessary - no use case for now."])
(obo/IAO_0000576 obo/IAO_0000231 obo/IAO_0000103)
(obo/IAO_0000576 rdfs/label ["obsolete_establishing a CRID registry"])
(obo/IAO_0000576 owl/deprecated true)
;; 
;; http://purl.obolibrary.org/obo/IAO_0000577
(obo/IAO_0000577 rdf/type owl/Class)
(obo/IAO_0000577 rdfs/subClassOf obo/IAO_0000028)
(obo/IAO_0000577 rdfs/subClassOf bnode/ccp-extensions_genid293)
(bnode/ccp-extensions_genid293 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid293 owl/onProperty obo/BFO_0000050)
(bnode/ccp-extensions_genid293 owl/someValuesFrom obo/IAO_0000578)
(obo/IAO_0000577 obo/IAO_0000112 ["The sentence \"The article has Pubmed ID 12345.\" contains a CRID that has two parts: one part is the CRID symbol, which is '12345'; the other part denotes the CRID registry, which is Pubmed." "en"])
(obo/IAO_0000577 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000577 obo/IAO_0000115 ["A symbol that is part of a CRID and that is sufficient to look up a record from the CRID's registry." "en"])
(obo/IAO_0000577 obo/IAO_0000117 ["PERSON: Alan Ruttenberg" "en"])
(obo/IAO_0000577 obo/IAO_0000117 ["PERSON: Bill Hogan" "en"])
(obo/IAO_0000577 obo/IAO_0000117 ["PERSON: Bjoern Peters" "en"])
(obo/IAO_0000577 obo/IAO_0000117 ["PERSON: Melanie Courtot" "en"])
(obo/IAO_0000577 obo/IAO_0000118 ["CRID symbol"])
(obo/IAO_0000577 obo/IAO_0000119 ["Original proposal from Bjoern, discussions at IAO calls" "en"])
(obo/IAO_0000577 rdfs/label ["centrally registered identifier symbol" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000578
(obo/IAO_0000578 rdf/type owl/Class)
(obo/IAO_0000578 rdfs/subClassOf obo/IAO_0000030)
(obo/IAO_0000578 rdfs/subClassOf bnode/ccp-extensions_genid294)
(bnode/ccp-extensions_genid294 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid294 owl/onProperty obo/BFO_0000051)
(bnode/ccp-extensions_genid294 owl/someValuesFrom obo/IAO_0000577)
(obo/IAO_0000578 rdfs/subClassOf bnode/ccp-extensions_genid295)
(bnode/ccp-extensions_genid295 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid295 owl/onProperty obo/BFO_0000051)
(bnode/ccp-extensions_genid295 owl/someValuesFrom bnode/ccp-extensions_genid296)
(bnode/ccp-extensions_genid296 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid296 owl/onProperty obo/IAO_0000219)
(bnode/ccp-extensions_genid296 owl/someValuesFrom obo/IAO_0000579)
(obo/IAO_0000578 obo/IAO_0000112 ["The sentence \"The article has Pubmed ID 12345.\" contains a CRID that has two parts: one part is the CRID symbol, which is '12345'; the other part denotes the CRID registry, which is Pubmed." "en"])
(obo/IAO_0000578 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000578 obo/IAO_0000115 ["An information content entity that consists of a CRID symbol and additional information about the CRID registry to which it belongs." "en"])
(obo/IAO_0000578 obo/IAO_0000116 ["2014-05-05: In defining this term we take no position on what the CRID denotes. In particular do not assume it denotes a *record* in the CRID registry (since the registry might not have 'records')." "en"])
(obo/IAO_0000578 obo/IAO_0000116 ["Alan, IAO call 20101124: potentially the CRID denotes the instance it was associated with during creation. \n" "en"])
(obo/IAO_0000578 obo/IAO_0000116 ["Note, IAO call 20101124: URIs are not always CRID, as not centrally registered. We acknowledge that CRID is a subset of a larger identifier class, but this subset fulfills our current needs. OBI PURLs are CRID as they are registered with OCLC. UPCs (Universal Product Codes from AC Nielsen)are not CRID as they are not centrally registered." "en"])
(obo/IAO_0000578 obo/IAO_0000117 ["PERSON: Alan Ruttenberg" "en"])
(obo/IAO_0000578 obo/IAO_0000117 ["PERSON: Bill Hogan" "en"])
(obo/IAO_0000578 obo/IAO_0000117 ["PERSON: Bjoern Peters" "en"])
(obo/IAO_0000578 obo/IAO_0000117 ["PERSON: Melanie Courtot" "en"])
(obo/IAO_0000578 obo/IAO_0000118 ["CRID"])
(obo/IAO_0000578 obo/IAO_0000119 ["Original proposal from Bjoern, discussions at IAO calls" "en"])
(obo/IAO_0000578 rdfs/label ["centrally registered identifier" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000579
(obo/IAO_0000579 rdf/type owl/Class)
(obo/IAO_0000579 rdfs/subClassOf obo/IAO_0000100)
(obo/IAO_0000579 obo/IAO_0000112 ["PubMed is a CRID registry. It has a dataset of PubMed identifiers associated with journal articles. " "en"])
(obo/IAO_0000579 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000579 obo/IAO_0000115 ["A CRID registry is a dataset of CRID records, each consisting of a CRID symbol and additional information which was recorded in the dataset through a assigning a centrally registered identifier process." "en"])
(obo/IAO_0000579 obo/IAO_0000117 ["PERSON: Alan Ruttenberg" "en"])
(obo/IAO_0000579 obo/IAO_0000117 ["PERSON: Bill Hogan" "en"])
(obo/IAO_0000579 obo/IAO_0000117 ["PERSON: Bjoern Peters" "en"])
(obo/IAO_0000579 obo/IAO_0000117 ["PERSON: Melanie Courtot" "en"])
(obo/IAO_0000579 obo/IAO_0000118 ["CRID registry"])
(obo/IAO_0000579 obo/IAO_0000119 ["Original proposal from Bjoern, discussions at IAO calls" "en"])
(obo/IAO_0000579 rdfs/label ["centrally registered identifier registry" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000580
(obo/IAO_0000580 rdf/type owl/Class)
(obo/IAO_0000580 rdfs/subClassOf obo/OBI_0000011)
(obo/IAO_0000580 rdfs/subClassOf bnode/ccp-extensions_genid297)
(bnode/ccp-extensions_genid297 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid297 owl/onProperty obo/OBI_0000293)
(bnode/ccp-extensions_genid297 owl/someValuesFrom obo/IAO_0000577)
(obo/IAO_0000580 rdfs/subClassOf bnode/ccp-extensions_genid298)
(bnode/ccp-extensions_genid298 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid298 owl/onProperty obo/OBI_0000299)
(bnode/ccp-extensions_genid298 owl/someValuesFrom obo/IAO_0000030)
(obo/IAO_0000580 rdfs/subClassOf bnode/ccp-extensions_genid299)
(bnode/ccp-extensions_genid299 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid299 owl/onProperty obo/RO_0000057)
(bnode/ccp-extensions_genid299 owl/someValuesFrom obo/IAO_0000579)
(obo/IAO_0000580 obo/IAO_0000112 ["Going to the PubMed website and entering a PubMed ID in order to retrieve the Pubmed information associated with that ID. "])
(obo/IAO_0000580 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000580 obo/IAO_0000115 ["A planned process in which a request to a CRID registry is made to return the information associated with a CRID symbol" "en"])
(obo/IAO_0000580 obo/IAO_0000117 ["PERSON: Alan Ruttenberg" "en"])
(obo/IAO_0000580 obo/IAO_0000117 ["PERSON: Bill Hogan" "en"])
(obo/IAO_0000580 obo/IAO_0000117 ["PERSON: Bjoern Peters" "en"])
(obo/IAO_0000580 obo/IAO_0000117 ["PERSON: Melanie Courtot" "en"])
(obo/IAO_0000580 obo/IAO_0000118 ["looking up a CRID"])
(obo/IAO_0000580 rdfs/label ["looking up a centrally registered identifier" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000582
(obo/IAO_0000582 rdf/type owl/Class)
(obo/IAO_0000582 rdfs/subClassOf obo/IAO_0000109)
(obo/IAO_0000582 rdfs/subClassOf bnode/ccp-extensions_genid300)
(bnode/ccp-extensions_genid300 owl/intersectionOf bnode/ccp-extensions_genid303)
(bnode/ccp-extensions_genid303 rdf/type rdf/List)
(bnode/ccp-extensions_genid303 rdf/first bnode/ccp-extensions_genid304)
(bnode/ccp-extensions_genid304 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid304 owl/onProperty obo/IAO_0000581)
(bnode/ccp-extensions_genid304 owl/someValuesFrom obo/IAO_0000416)
(bnode/ccp-extensions_genid303 rdf/rest bnode/ccp-extensions_genid301)
(bnode/ccp-extensions_genid301 rdf/type rdf/List)
(bnode/ccp-extensions_genid301 rdf/first bnode/ccp-extensions_genid302)
(bnode/ccp-extensions_genid302 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid302 owl/onProperty obo/IAO_0000583)
(bnode/ccp-extensions_genid302 owl/someValuesFrom obo/IAO_0000109)
(bnode/ccp-extensions_genid301 rdf/rest rdf/nil)
(bnode/ccp-extensions_genid300 rdf/type owl/Class)
(obo/IAO_0000582 obo/IAO_0000114 obo/IAO_0000123)
(obo/IAO_0000582 rdfs/label ["time stamped measurement datum" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000584
(obo/IAO_0000584 rdf/type owl/Class)
(obo/IAO_0000584 rdfs/subClassOf obo/IAO_0000100)
(obo/IAO_0000584 rdfs/subClassOf bnode/ccp-extensions_genid305)
(bnode/ccp-extensions_genid305 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid305 owl/onProperty obo/BFO_0000051)
(bnode/ccp-extensions_genid305 owl/someValuesFrom obo/IAO_0000582)
(obo/IAO_0000584 obo/IAO_0000112 ["pmid:20604925 - time-lapse live cell microscopy" "en"])
(obo/IAO_0000584 obo/IAO_0000114 obo/IAO_0000120)
(obo/IAO_0000584 obo/IAO_0000115 ["A data set that is an aggregate of data recording some measurement at a number of time points. The time series data set is an ordered list of pairs of time measurement data and the corresponding measurement data acquired at that time." "en"])
(obo/IAO_0000584 obo/IAO_0000117 ["Alan Ruttenberg" "en"])
(obo/IAO_0000584 obo/IAO_0000118 ["experimental time series" "en"])
(obo/IAO_0000584 rdfs/label ["time sampled measurement data set" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000590
(obo/IAO_0000590 rdf/type owl/Class)
(obo/IAO_0000590 rdfs/subClassOf obo/IAO_0000300)
(obo/IAO_0000590 obo/IAO_0000111 ["written name" "en"])
(obo/IAO_0000590 obo/IAO_0000112 ["\"Bill Clinton\"" "en"])
(obo/IAO_0000590 obo/IAO_0000112 ["\"The Eiffel Tower\"" "en"])
(obo/IAO_0000590 obo/IAO_0000112 ["\"United States of America\"" "en"])
(obo/IAO_0000590 obo/IAO_0000114 obo/IAO_0000125)
(obo/IAO_0000590 obo/IAO_0000115 ["A textual entity that denotes a particular in reality." "en"])
(obo/IAO_0000590 obo/IAO_0000117 ["PERSON: Bill Hogan" "en"])
 ;(obo/IAO_0000590 obo/IAO_0000119 ["https://github.com/information-artifact-ontology/IAO/issues/114" "en"])
(obo/IAO_0000590 obo/IAO_0000232 ["The qualifier \"written\" is to set it apart from spoken names.  Also, note the restrictions to particulars.  We are not naming universals.   We could however, be naming, attributive collections which are particulars, so \"All people located in the boundaries of the city of Little Rock, AR on June 18, 2011 at 9:50a CDT\" would be a name." "en"])
(obo/IAO_0000590 rdfs/label ["written name" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000591
(obo/IAO_0000591 rdf/type owl/Class)
(obo/IAO_0000591 rdfs/subClassOf obo/IAO_0000010)
(obo/IAO_0000591 obo/IAO_0000114 obo/IAO_0000123)
(obo/IAO_0000591 obo/IAO_0000115 ["A software method (also called subroutine, subprogram, procedure, method, function, or routine) is software designed to execute a specific task." "en"])
(obo/IAO_0000591 obo/IAO_0000117 ["PERSON: Melanie Courtot" "en"])
(obo/IAO_0000591 obo/IAO_0000117 ["PERSON: Michel Dumontier" "en"])
 ;(obo/IAO_0000591 obo/IAO_0000119 ["https://github.com/information-artifact-ontology/IAO/issues/80" "en"])
(obo/IAO_0000591 rdfs/label ["software method" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000592
(obo/IAO_0000592 rdf/type owl/Class)
(obo/IAO_0000592 rdfs/subClassOf obo/IAO_0000010)
(obo/IAO_0000592 obo/IAO_0000114 obo/IAO_0000123)
(obo/IAO_0000592 obo/IAO_0000115 [" A software module is software composed of a collection of software methods." "en"])
(obo/IAO_0000592 obo/IAO_0000117 ["PERSON: Melanei Courtot" "en"])
(obo/IAO_0000592 obo/IAO_0000117 ["PERSON: Michel Dumontier" "en"])
 ;(obo/IAO_0000592 obo/IAO_0000119 ["https://github.com/information-artifact-ontology/IAO/issues/80" "en"])
(obo/IAO_0000592 rdfs/label ["software module" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000593
(obo/IAO_0000593 rdf/type owl/Class)
(obo/IAO_0000593 rdfs/subClassOf obo/IAO_0000010)
(obo/IAO_0000593 obo/IAO_0000114 obo/IAO_0000123)
(obo/IAO_0000593 obo/IAO_0000115 ["A software library is software composed of a collection of software modules and/or software methods in a form that can be statically or dynamically linked to some software application." "en"])
(obo/IAO_0000593 obo/IAO_0000117 ["PERSON: Melanie Courtot" "en"])
(obo/IAO_0000593 obo/IAO_0000117 ["PERSON: Michel Dumontier" "en"])
 ;(obo/IAO_0000593 obo/IAO_0000119 ["https://github.com/information-artifact-ontology/IAO/issues/80" "en"])
(obo/IAO_0000593 rdfs/label ["software library" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000594
(obo/IAO_0000594 rdf/type owl/Class)
(obo/IAO_0000594 rdfs/subClassOf obo/IAO_0000010)
(obo/IAO_0000594 obo/IAO_0000114 obo/IAO_0000123)
(obo/IAO_0000594 obo/IAO_0000115 ["A software application is software that can be directly executed by some processing unit." "en"])
(obo/IAO_0000594 obo/IAO_0000117 ["PERSON: Melanie Courtot" "en"])
(obo/IAO_0000594 obo/IAO_0000117 ["PERSON: Michel Dumontier" "en"])
 ;(obo/IAO_0000594 obo/IAO_0000119 ["https://github.com/information-artifact-ontology/IAO/issues/80" "en"])
(obo/IAO_0000594 rdfs/label ["software application" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000595
(obo/IAO_0000595 rdf/type owl/Class)
(obo/IAO_0000595 rdfs/subClassOf obo/IAO_0000010)
(obo/IAO_0000595 obo/IAO_0000114 obo/IAO_0000123)
(obo/IAO_0000595 obo/IAO_0000115 [" A software script is software whose instructions can be executed using a software \ninterpreter." "en"])
(obo/IAO_0000595 obo/IAO_0000117 ["PERSON: Melanie Courtot" "en"])
(obo/IAO_0000595 obo/IAO_0000117 ["PERSON: Michel Dumontier" "en"])
 ;(obo/IAO_0000595 obo/IAO_0000119 ["https://github.com/information-artifact-ontology/IAO/issues/80" "en"])
(obo/IAO_0000595 rdfs/label ["software script" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000605
(obo/IAO_0000605 rdf/type owl/Class)
(obo/IAO_0000605 rdfs/subClassOf obo/IAO_0000300)
(obo/IAO_0000605 obo/IAO_0000111 ["abbreviation textual entity" "en"])
(obo/IAO_0000605 obo/IAO_0000112 ["From Shiba et al. Acta Neuropathol Commun. 2013; 1: 45. (http://www.ncbi.nlm.nih.gov/pmc/articles/PMC3893467/):\n\nBAC: Bacterial artificial chromosome; CR: Calretinin; GFAP: Glial fibrillary acidic protein; MAP: Microtubule-associated protein; MRI: Magnetic resonance imaging; NSC: Neural stem cell; PDA: Patent ductus arteriosus; PMG: Polymicrogyria; PNH: Periventricular nodular heterotopia; VSD: Ventricular septal defect." "en"])
(obo/IAO_0000605 obo/IAO_0000115 ["A textual entity listing abbreviations and their expansions that are used in a document." "en"])
(obo/IAO_0000605 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
 ;(obo/IAO_0000605 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000605 rdfs/label ["abbreviation textual entity" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000606
(obo/IAO_0000606 rdf/type owl/Class)
(obo/IAO_0000606 rdfs/subClassOf obo/IAO_0000314)
(obo/IAO_0000606 rdfs/subClassOf bnode/ccp-extensions_genid306)
(bnode/ccp-extensions_genid306 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid306 owl/onProperty obo/BFO_0000051)
(bnode/ccp-extensions_genid306 owl/someValuesFrom obo/IAO_0000605)
(obo/IAO_0000606 obo/IAO_0000111 ["abbreviations section" "en"])
(obo/IAO_0000606 obo/IAO_0000112 ["The section labelled 'abbreviations' in a typical scientific journal article." "en"])
(obo/IAO_0000606 obo/IAO_0000115 ["A part of a document where abbreviations and their long-forms used within the document are listed." "en"])
(obo/IAO_0000606 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
(obo/IAO_0000606 obo/IAO_0000118 ["abbreviations" "en"])
(obo/IAO_0000606 obo/IAO_0000118 ["abbreviations list" "en"])
(obo/IAO_0000606 obo/IAO_0000118 ["abbreviations used" "en"])
(obo/IAO_0000606 obo/IAO_0000118 ["list of abbreviations" "en"])
(obo/IAO_0000606 obo/IAO_0000118 ["list of abbreviations used" "en"])
 ;(obo/IAO_0000606 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000606 rdfs/label ["abbreviations section" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000607
(obo/IAO_0000607 rdf/type owl/Class)
(obo/IAO_0000607 rdfs/subClassOf obo/IAO_0000314)
(obo/IAO_0000607 rdfs/subClassOf bnode/ccp-extensions_genid307)
(bnode/ccp-extensions_genid307 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid307 owl/onProperty obo/BFO_0000051)
(bnode/ccp-extensions_genid307 owl/someValuesFrom obo/IAO_0000608)
(obo/IAO_0000607 obo/IAO_0000111 ["author information section" "en"])
(obo/IAO_0000607 obo/IAO_0000112 ["The section labelled 'author information' in a typical scientific journal article, e.g. in Takon. Ann Gen Psychiatry. 2011; 10: 25. (http://www.ncbi.nlm.nih.gov/pmc/articles/PMC3204268/)" "en"])
(obo/IAO_0000607 obo/IAO_0000115 ["A part of a document about the authors that provides biographical information and may discuss how the authors' professional experiences are relevant to the work described in the document." "en"])
(obo/IAO_0000607 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
(obo/IAO_0000607 obo/IAO_0000118 ["author information" "en"])
(obo/IAO_0000607 obo/IAO_0000118 ["authors\u2019 information" "en"])
 ;(obo/IAO_0000607 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000607 rdfs/label ["author information section" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000608
(obo/IAO_0000608 rdf/type owl/Class)
(obo/IAO_0000608 rdfs/subClassOf obo/IAO_0000300)
(obo/IAO_0000608 obo/IAO_0000111 ["author information textual entity" "en"])
(obo/IAO_0000608 obo/IAO_0000112 ["From Takon. Ann Gen Psychiatry. 2011; 10: 25. (http://www.ncbi.nlm.nih.gov/pmc/articles/PMC3204268/):\n\nIT [the author] is the lead paediatrician for ADHD services in East Hertfordshire, UK, where she runs a weekly joint ADHD clinic with the Child and Adolescent psychiatrist and works within an ADHD specialist team. IT also sees children with other neurodisability issues who may have comorbid ADHD, where the presentation may be more complex and challenging to manage. IT has vast experience in managing children with complex ADHD. She has 18 years of experience in paediatrics and also has extensive experience in the use of psychopharmacologic agents in managing children with ADHD." "en"])
(obo/IAO_0000608 obo/IAO_0000115 ["A textual entity expression information about an author of a document. This information may include biographical information and may discuss how the authors' professional experiences are relevant to the work described in the document." "en"])
(obo/IAO_0000608 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
 ;(obo/IAO_0000608 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000608 rdfs/label ["author information textual entity" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000609
(obo/IAO_0000609 rdf/type owl/Class)
(obo/IAO_0000609 rdfs/subClassOf obo/IAO_0000314)
(obo/IAO_0000609 rdfs/subClassOf bnode/ccp-extensions_genid308)
(bnode/ccp-extensions_genid308 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid308 owl/onProperty obo/BFO_0000051)
(bnode/ccp-extensions_genid308 owl/someValuesFrom obo/IAO_0000610)
(obo/IAO_0000609 obo/IAO_0000111 ["author summary section" "en"])
(obo/IAO_0000609 obo/IAO_0000112 ["The section labelled 'synopsis' in a typical scientific journal article, e.g. in Pendse et al. BMC Genomics. 2013; 14: 136. (http://www.ncbi.nlm.nih.gov/pmc/articles/PMC3608171/)" "en"])
(obo/IAO_0000609 obo/IAO_0000115 ["A part of a document, distinct from the abstract, that describes the significance and broader context of the document content. The author summary is often written in a non-technical manner and is aimed at both scientists and non-scientist readers." "en"])
(obo/IAO_0000609 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
(obo/IAO_0000609 obo/IAO_0000118 ["author summary" "en"])
(obo/IAO_0000609 obo/IAO_0000118 ["summary" "en"])
(obo/IAO_0000609 obo/IAO_0000118 ["synopsis" "en"])
(obo/IAO_0000609 obo/IAO_0000119 ["Article submission guidelines for PLoS Genetics (http://journals.plos.org/plosgenetics/s/submission-guidelines)" "en"])
 ;(obo/IAO_0000609 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000609 rdfs/label ["author summary section" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000610
(obo/IAO_0000610 rdf/type owl/Class)
(obo/IAO_0000610 rdfs/subClassOf obo/IAO_0000300)
(obo/IAO_0000610 obo/IAO_0000111 ["author summary textual entity" "en"])
(obo/IAO_0000610 obo/IAO_0000112 ["From Pendse et al. BMC Genomics. 2013; 14: 136. (http://www.ncbi.nlm.nih.gov/pmc/articles/PMC3608171/):\n\n\nThe search for genetic risk factors for common human diseases often relies on the use of linkage and association studies to establish correlation between genomic markers and disease risk. These studies require additional functional evaluation of candidate genes, including their possible interaction with diet and environment. The number of candidate genes is typically large and the development of appropriate genetic tools in mammalian systems is slow. By contrast, large-scale genetic screens, using widely available genetic tools, are routinely conducted in the fruit fly Drosophila melanogaster. In this study, we used Drosophila to screen candidate genes identified in human genome-wide scans as associated with risk of metabolic abnormalities such as type 2 diabetes. We show that a number of human candidate genes have fly orthologs that play an important role in Drosophila tolerance to high dietary sucrose. We further explored some of the specific metabolic abnormalities that can result when these genes\u2019 activities are reduced in flies, focusing on a gene we call dHHEX (CG7056), the fly ortholog of human HHEX." "en"])
(obo/IAO_0000610 obo/IAO_0000115 ["A textual entity, distinct from the abstract, that describes the significance and broader context of the document content. The author summary is often written in a non-technical manner and is aimed at both scientists and non-scientist readers, e.g as described in the article submission guidelines for PLoS Genetics (http://journals.plos.org/plosgenetics/s/submission-guidelines)." "en"])
(obo/IAO_0000610 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
(obo/IAO_0000610 obo/IAO_0000119 ["Article submission guidelines for PLoS Genetics (http://journals.plos.org/plosgenetics/s/submission-guidelines)." "en"])
 ;(obo/IAO_0000610 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000610 rdfs/label ["author summary textual entity" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000611
(obo/IAO_0000611 rdf/type owl/Class)
(obo/IAO_0000611 rdfs/subClassOf obo/IAO_0000314)
(obo/IAO_0000611 rdfs/subClassOf bnode/ccp-extensions_genid309)
(bnode/ccp-extensions_genid309 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid309 owl/onProperty obo/BFO_0000051)
(bnode/ccp-extensions_genid309 owl/someValuesFrom obo/IAO_0000612)
(obo/IAO_0000611 obo/IAO_0000111 ["availability section" "en"])
(obo/IAO_0000611 obo/IAO_0000112 ["The section labelled 'availability and requirements' in a typical scientific journal article, e.g. Qi et al. BMC Bioinformatics. 2014; 15: 11. (http://www.ncbi.nlm.nih.gov/pmc/articles/PMC3897912/)." "en"])
(obo/IAO_0000611 obo/IAO_0000115 ["A part of a document about a resource described in the document, e.g. software, that describes where and/or how that resource can be obtained." "en"])
(obo/IAO_0000611 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
(obo/IAO_0000611 obo/IAO_0000118 ["availability" "en"])
 ;(obo/IAO_0000611 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000611 rdfs/label ["availability section" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000612
(obo/IAO_0000612 rdf/type owl/Class)
(obo/IAO_0000612 rdfs/subClassOf obo/IAO_0000300)
(obo/IAO_0000612 obo/IAO_0000111 ["availability textual entity" "en"])
(obo/IAO_0000612 obo/IAO_0000112 ["From Qi et al. BMC Bioinformatics. 2014; 15: 11. (http://www.ncbi.nlm.nih.gov/pmc/articles/PMC3897912/):\n\nProject home page:http://krux.googlecode.com" "en"])
(obo/IAO_0000612 obo/IAO_0000115 ["A textual entity expressing the location of a resource, e.g. software, or the manner in which a resource can be obtained." "en"])
(obo/IAO_0000612 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
 ;(obo/IAO_0000612 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000612 rdfs/label ["availability textual entity" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000613
(obo/IAO_0000613 rdf/type owl/Class)
(obo/IAO_0000613 rdfs/subClassOf obo/IAO_0000314)
(obo/IAO_0000613 rdfs/subClassOf bnode/ccp-extensions_genid310)
(bnode/ccp-extensions_genid310 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid310 owl/onProperty obo/BFO_0000051)
(bnode/ccp-extensions_genid310 owl/someValuesFrom obo/IAO_0000614)
(obo/IAO_0000613 obo/IAO_0000111 ["case report section" "en"])
(obo/IAO_0000613 obo/IAO_0000112 ["The section labelled 'case report' in a typical scientific journal article, e.g. in Taglia et al. Acta Myol. 2012 Dec; 31(3): 201\u2013203. (http://www.ncbi.nlm.nih.gov/pmc/articles/PMC3631801/)" "en"])
(obo/IAO_0000613 obo/IAO_0000115 ["A part of a document about the medical history of a specific patient as it relates to the topic of the document." "en"])
(obo/IAO_0000613 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
(obo/IAO_0000613 obo/IAO_0000118 ["case presentation" "en"])
(obo/IAO_0000613 obo/IAO_0000118 ["case report" "en"])
 ;(obo/IAO_0000613 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000613 rdfs/label ["case report section" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000614
(obo/IAO_0000614 rdf/type owl/Class)
(obo/IAO_0000614 rdfs/subClassOf obo/IAO_0000300)
(obo/IAO_0000614 obo/IAO_0000111 ["case report textual entity" "en"])
(obo/IAO_0000614 obo/IAO_0000112 ["Excerpt from Taglia et al. Acta Myol. 2012 Dec; 31(3): 201\u2013203. (http://www.ncbi.nlm.nih.gov/pmc/articles/PMC3631801/):\n\nThe patient is a 50-year-old man. His medical history was not contributory. At the age of 37 years, he complained of persistent fatigue and dyspnoea even for modest efforts and oedema of lower limbs. The patient was examined at the department of internal medicine of the local hospital, and hospitalised with a diagnosis of dilated cardiomyopathy probably consequence of a myocarditis process. Soon after he was transferred to the cardiologic department of the regional hospital, and pharmacologically treated for heart failure and pulmonary hypertension." "en"])
(obo/IAO_0000614 obo/IAO_0000115 ["A textual entity that expresses a detailed account of a portion of the medical history for a specific patient." "en"])
(obo/IAO_0000614 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
 ;(obo/IAO_0000614 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000614 rdfs/label ["case report textual entity" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000615
(obo/IAO_0000615 rdf/type owl/Class)
(obo/IAO_0000615 rdfs/subClassOf obo/IAO_0000314)
(obo/IAO_0000615 rdfs/subClassOf bnode/ccp-extensions_genid311)
(bnode/ccp-extensions_genid311 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid311 owl/onProperty obo/BFO_0000051)
(bnode/ccp-extensions_genid311 owl/someValuesFrom obo/IAO_0000144)
(obo/IAO_0000615 obo/IAO_0000111 ["conclusion section" "en"])
(obo/IAO_0000615 obo/IAO_0000112 ["The section labelled 'conclusion' in a typical scientific journal article." "en"])
(obo/IAO_0000615 obo/IAO_0000115 ["A part of a document used to summarize the findings discussed in the document. The conclusion section is typically found near the end of a document. " "en"])
(obo/IAO_0000615 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
(obo/IAO_0000615 obo/IAO_0000118 ["concluding remarks" "en"])
(obo/IAO_0000615 obo/IAO_0000118 ["conclusion" "en"])
(obo/IAO_0000615 obo/IAO_0000118 ["conclusions" "en"])
(obo/IAO_0000615 obo/IAO_0000118 ["findings" "en"])
(obo/IAO_0000615 obo/IAO_0000118 ["summary" "en"])
 ;(obo/IAO_0000615 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000615 rdfs/label ["conclusion section" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000616
(obo/IAO_0000616 rdf/type owl/Class)
(obo/IAO_0000616 rdfs/subClassOf obo/IAO_0000314)
(obo/IAO_0000616 rdfs/subClassOf bnode/ccp-extensions_genid312)
(bnode/ccp-extensions_genid312 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid312 owl/onProperty obo/BFO_0000051)
(bnode/ccp-extensions_genid312 owl/someValuesFrom obo/IAO_0000617)
(obo/IAO_0000616 obo/IAO_0000111 ["conflict of interest section" "en"])
(obo/IAO_0000616 obo/IAO_0000112 ["The section labelled 'conflict of interest statement' in a typical scientific journal article." "en"])
(obo/IAO_0000616 obo/IAO_0000115 ["A part of a document used to declare any competing interests regarding the authors and/or funding organization for the work described in the document." "en"])
(obo/IAO_0000616 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
(obo/IAO_0000616 obo/IAO_0000118 ["competing interests" "en"])
(obo/IAO_0000616 obo/IAO_0000118 ["conflict of interest" "en"])
(obo/IAO_0000616 obo/IAO_0000118 ["conflict of interest statement" "en"])
(obo/IAO_0000616 obo/IAO_0000118 ["declaration of competing interests" "en"])
(obo/IAO_0000616 obo/IAO_0000118 ["disclosure of potential conflicts of interest" "en"])
 ;(obo/IAO_0000616 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000616 rdfs/label ["conflict of interest section" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000617
(obo/IAO_0000617 rdf/type owl/Class)
(obo/IAO_0000617 rdfs/subClassOf obo/IAO_0000300)
(obo/IAO_0000617 obo/IAO_0000111 ["conflict of interest statement" "en"])
(obo/IAO_0000617 obo/IAO_0000112 ["SD [an author] is a Merck employee and Merck is the sponsor of this study. [Taken from 'Effects of obstructive sleep apnoea risk on postoperative respiratory complications: protocol for a hospital-based registry study' Shin et al. 2016  (http://www.ncbi.nlm.nih.gov/pmc/articles/PMC4735131/)] " "en"])
(obo/IAO_0000617 obo/IAO_0000115 ["A textual entity that expresses a situation involving one or more of the authors, or the funding source of a document whereby the authors or funding source stand to potentially gain (typically financially) from the results reported in the document." "en"])
(obo/IAO_0000617 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
 ;(obo/IAO_0000617 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000617 rdfs/label ["conflict of interest textual entity" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000618
(obo/IAO_0000618 rdf/type owl/Class)
(obo/IAO_0000618 rdfs/subClassOf obo/IAO_0000314)
(obo/IAO_0000618 rdfs/subClassOf bnode/ccp-extensions_genid313)
(bnode/ccp-extensions_genid313 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid313 owl/onProperty obo/BFO_0000051)
(bnode/ccp-extensions_genid313 owl/someValuesFrom obo/IAO_0000619)
(obo/IAO_0000618 obo/IAO_0000111 ["consent section" "en"])
(obo/IAO_0000618 obo/IAO_0000112 ["The section labelled 'consent' in a typical scientific journal article, e.g. Shiba et al. Acta Neuropathol Commun. 2013; 1: 45. (http://www.ncbi.nlm.nih.gov/pmc/articles/PMC3893467/)" "en"])
(obo/IAO_0000618 obo/IAO_0000115 ["A part of a document about the consent process that was used to enroll patients in a study." "en"])
(obo/IAO_0000618 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
(obo/IAO_0000618 obo/IAO_0000118 ["consent" "en"])
 ;(obo/IAO_0000618 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000618 rdfs/label ["consent section" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000619
(obo/IAO_0000619 rdf/type owl/Class)
(obo/IAO_0000619 rdfs/subClassOf obo/IAO_0000300)
(obo/IAO_0000619 obo/IAO_0000111 ["consent textual entity" "en"])
(obo/IAO_0000619 obo/IAO_0000112 ["From Shiba et al. Acta Neuropathol Commun. 2013; 1: 45. (http://www.ncbi.nlm.nih.gov/pmc/articles/PMC3893467/):\n\nWritten informed consent was obtained from the patient\u2019s parents for publication of this Case report and any accompanying images. A copy of the written consent is available for review by the Editor-in chief of this journal." "en"])
(obo/IAO_0000619 obo/IAO_0000115 ["A textual entity that documents the consenting process used to enroll patients in a study." "en"])
(obo/IAO_0000619 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
 ;(obo/IAO_0000619 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000619 rdfs/label ["consent textual entity" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000620
(obo/IAO_0000620 rdf/type owl/Class)
(obo/IAO_0000620 rdfs/subClassOf obo/IAO_0000314)
(obo/IAO_0000620 rdfs/subClassOf bnode/ccp-extensions_genid314)
(bnode/ccp-extensions_genid314 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid314 owl/onProperty obo/BFO_0000051)
(bnode/ccp-extensions_genid314 owl/someValuesFrom obo/IAO_0000621)
(obo/IAO_0000620 obo/IAO_0000111 ["ethical approval section" "en"])
(obo/IAO_0000620 obo/IAO_0000112 ["The section labelled 'ethical approval' in a typical scientific journal article." "en"])
(obo/IAO_0000620 obo/IAO_0000115 ["A part of a document about the governance body responsible for approving the work discussed in a document on an ethical basis." "en"])
(obo/IAO_0000620 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
(obo/IAO_0000620 obo/IAO_0000118 ["ethical approval" "en"])
 ;(obo/IAO_0000620 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000620 rdfs/label ["ethical approval section" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000621
(obo/IAO_0000621 rdf/type owl/Class)
(obo/IAO_0000621 rdfs/subClassOf obo/IAO_0000300)
(obo/IAO_0000621 rdfs/subClassOf bnode/ccp-extensions_genid315)
(bnode/ccp-extensions_genid315 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid315 owl/onProperty obo/IAO_0000136)
(bnode/ccp-extensions_genid315 owl/someValuesFrom obo/OBI_0500000)
(obo/IAO_0000621 obo/IAO_0000111 ["ethical approval textual entity" "en"])
(obo/IAO_0000621 obo/IAO_0000112 ["From McLean et al. Br J Gen Pract. 2014 Jul; 64(624): e440\u2013e447 (http://www.ncbi.nlm.nih.gov/pmc/articles/PMC4073730/):\n\nThe NHS National Research Ethics Service had previously approved the use of these anonymised data for research purposes and this analysis did not require independent review." "en"])
(obo/IAO_0000621 obo/IAO_0000115 ["A textual entity that documents the ethical approval of some study design." "en"])
(obo/IAO_0000621 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
 ;(obo/IAO_0000621 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000621 rdfs/label ["ethical approval textual entity" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000622
(obo/IAO_0000622 rdf/type owl/Class)
(obo/IAO_0000622 rdfs/subClassOf obo/IAO_0000314)
(obo/IAO_0000622 rdfs/subClassOf bnode/ccp-extensions_genid316)
(bnode/ccp-extensions_genid316 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid316 owl/onProperty obo/BFO_0000051)
(bnode/ccp-extensions_genid316 owl/someValuesFrom obo/IAO_0000308)
(obo/IAO_0000622 obo/IAO_0000111 ["figures section" "en"])
(obo/IAO_0000622 obo/IAO_0000112 ["The section labelled 'figures' in a typical scientific journal article." "en"])
(obo/IAO_0000622 obo/IAO_0000115 ["A part of a document that contains one or more figures." "en"])
(obo/IAO_0000622 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
(obo/IAO_0000622 obo/IAO_0000118 ["figures" "en"])
 ;(obo/IAO_0000622 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000622 rdfs/label ["figures section" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000623
(obo/IAO_0000623 rdf/type owl/Class)
(obo/IAO_0000623 rdfs/subClassOf obo/IAO_0000314)
(obo/IAO_0000623 rdfs/subClassOf bnode/ccp-extensions_genid317)
(bnode/ccp-extensions_genid317 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid317 owl/onProperty obo/BFO_0000051)
(bnode/ccp-extensions_genid317 owl/someValuesFrom obo/IAO_0000624)
(obo/IAO_0000623 obo/IAO_0000111 ["funding source declaration section" "en"])
(obo/IAO_0000623 obo/IAO_0000112 ["The section labelled 'funding' in a typical scientific journal article." "en"])
(obo/IAO_0000623 obo/IAO_0000115 ["A part of a document used to detail information regarding the source of funding used in support of the generation of the document content." "en"])
(obo/IAO_0000623 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
(obo/IAO_0000623 obo/IAO_0000118 ["funding" "en"])
(obo/IAO_0000623 obo/IAO_0000118 ["funding information" "en"])
(obo/IAO_0000623 obo/IAO_0000118 ["funding sources" "en"])
(obo/IAO_0000623 obo/IAO_0000118 ["funding statement" "en"])
(obo/IAO_0000623 obo/IAO_0000118 ["funding/support" "en"])
(obo/IAO_0000623 obo/IAO_0000118 ["source of funding" "en"])
(obo/IAO_0000623 obo/IAO_0000118 ["sources of funding" "en"])
 ;(obo/IAO_0000623 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000623 rdfs/label ["funding source declaration section" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000624
(obo/IAO_0000624 rdf/type owl/Class)
(obo/IAO_0000624 rdfs/subClassOf obo/IAO_0000300)
(obo/IAO_0000624 obo/IAO_0000111 ["funding souce declaration textual entity" "en"])
(obo/IAO_0000624 obo/IAO_0000112 ["From Stephan et al. Accid Anal Prev. 2011 May; 43(3): 1062\u20131067. (http://www.ncbi.nlm.nih.gov/pmc/articles/PMC3062852/):\n\nThis study was supported by the International Collaborative Research Grants Scheme with joint grants from the Wellcome Trust UK (GR071587MA) and the Australian NHMRC (268055). The funding sources played no role in study design, data collection, analysis or interpretation, writing the report, or the decision to submit the paper for publication." "en"])
(obo/IAO_0000624 obo/IAO_0000115 ["A textual entity documenting the source of funding that supported some study." "en"])
(obo/IAO_0000624 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
 ;(obo/IAO_0000624 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000624 rdfs/label ["funding source declaration textual entity" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000625
(obo/IAO_0000625 rdf/type owl/Class)
(obo/IAO_0000625 rdfs/subClassOf obo/IAO_0000314)
(obo/IAO_0000625 rdfs/subClassOf bnode/ccp-extensions_genid318)
(bnode/ccp-extensions_genid318 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid318 owl/onProperty obo/BFO_0000051)
(bnode/ccp-extensions_genid318 owl/someValuesFrom obo/IAO_0000626)
(obo/IAO_0000625 obo/IAO_0000111 ["future directions section" "en"])
(obo/IAO_0000625 obo/IAO_0000112 ["The section labelled 'future directions' in a typical scientific journal article." "en"])
(obo/IAO_0000625 obo/IAO_0000115 ["A part of a document detailing extensions of the described work that may be implemented at some future point in time." "en"])
(obo/IAO_0000625 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
(obo/IAO_0000625 obo/IAO_0000118 ["future challenges" "en"])
(obo/IAO_0000625 obo/IAO_0000118 ["future considerations" "en"])
(obo/IAO_0000625 obo/IAO_0000118 ["future developments" "en"])
(obo/IAO_0000625 obo/IAO_0000118 ["future directions" "en"])
(obo/IAO_0000625 obo/IAO_0000118 ["future outlook" "en"])
(obo/IAO_0000625 obo/IAO_0000118 ["future perspectives" "en"])
(obo/IAO_0000625 obo/IAO_0000118 ["future plans" "en"])
(obo/IAO_0000625 obo/IAO_0000118 ["future prospects" "en"])
(obo/IAO_0000625 obo/IAO_0000118 ["future research" "en"])
(obo/IAO_0000625 obo/IAO_0000118 ["future research directions" "en"])
(obo/IAO_0000625 obo/IAO_0000118 ["future studies" "en"])
(obo/IAO_0000625 obo/IAO_0000118 ["future work" "en"])
 ;(obo/IAO_0000625 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000625 rdfs/label ["future directions section" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000626
(obo/IAO_0000626 rdf/type owl/Class)
(obo/IAO_0000626 rdfs/subClassOf obo/IAO_0000300)
(obo/IAO_0000626 obo/IAO_0000111 ["future directions textual entity" "en"])
(obo/IAO_0000626 obo/IAO_0000112 ["Excerpt from Wang and Li. Acta Pharmacol Sin. 2016 Jan; 37(1): 25\u201333. (http://www.ncbi.nlm.nih.gov/pmc/articles/PMC4722976/):\n\nIn the future, several questions will need to be resolved regarding the physiological assembly of KCNQ channels and their functional implications in complex neural circuits. First, we still lack sufficiently selective inhibitors and activators among the KCNQ family members." "en"])
(obo/IAO_0000626 obo/IAO_0000115 ["A textual entity expressing ideas regarding future work relevant to work described in a document that could be done." "en"])
(obo/IAO_0000626 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
 ;(obo/IAO_0000626 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000626 rdfs/label ["future directions textual entity" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000627
(obo/IAO_0000627 rdf/type owl/Class)
(obo/IAO_0000627 rdfs/subClassOf obo/IAO_0000314)
(obo/IAO_0000627 rdfs/subClassOf bnode/ccp-extensions_genid319)
(bnode/ccp-extensions_genid319 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid319 owl/onProperty obo/BFO_0000051)
(bnode/ccp-extensions_genid319 owl/someValuesFrom obo/IAO_0000628)
(obo/IAO_0000627 obo/IAO_0000111 ["genome announcement section" "en"])
(obo/IAO_0000627 obo/IAO_0000112 ["The section labelled 'genome announcement' in a typical scientific journal article, e.g. in Kim et al. J Bacteriol. 2011 Oct; 193(19): 5537. (http://www.ncbi.nlm.nih.gov/pmc/articles/PMC3187466/)" "en"])
(obo/IAO_0000627 obo/IAO_0000115 ["A document part announcing the publication of a novel draft genome sequence." "en"])
(obo/IAO_0000627 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
(obo/IAO_0000627 obo/IAO_0000118 ["genome announcement" "en"])
 ;(obo/IAO_0000627 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000627 rdfs/label ["genome announcement section" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000628
(obo/IAO_0000628 rdf/type owl/Class)
(obo/IAO_0000628 rdfs/subClassOf obo/IAO_0000300)
(obo/IAO_0000628 obo/IAO_0000111 ["genome announcement textual entity" "en"])
(obo/IAO_0000628 obo/IAO_0000112 ["Excerpt from Kim et al. J Bacteriol. 2011 Oct; 193(19): 5537. (http://www.ncbi.nlm.nih.gov/pmc/articles/PMC3187466/):\n\nHere we report the genome sequence of Lactobacillus malefermentans KCTC 3548, which we obtained using a whole-genome shotgun strategy (4) with Roche 454 GS (FLX Titanium) pyrosequencing (257,559 reads totaling \u223C89.8 Mb; \u223C45-fold coverage of the genome) at the Genome Resource Center, Korea Research Institute of Bioscience and Biotechnology (KRIBB)." "en"])
(obo/IAO_0000628 obo/IAO_0000115 ["A textual entity that describes the generation and public release of a novel, draft genome sequence." "en"])
(obo/IAO_0000628 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
 ;(obo/IAO_0000628 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000628 rdfs/label ["genome announcement textual entity" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000629
(obo/IAO_0000629 rdf/type owl/Class)
(obo/IAO_0000629 rdfs/subClassOf obo/IAO_0000300)
(obo/IAO_0000629 obo/IAO_0000111 ["keyword textual entity" "en"])
(obo/IAO_0000629 obo/IAO_0000112 ["From: Fu and Lin. Identification of gene-oriented exon orthology between human and mouse. BMC Genomics. 2012; 13(Suppl 1): S10. (http://www.ncbi.nlm.nih.gov/pmc/articles/PMC3303729/):\n\nExon orthology; alternative splicing; exon duplication; intron-exon structure." "en"])
(obo/IAO_0000629 obo/IAO_0000115 ["A textual entity listing keywords indicating the major theme(s) of a document." "en"])
(obo/IAO_0000629 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
 ;(obo/IAO_0000629 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000629 rdfs/label ["keyword textual entity" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000630
(obo/IAO_0000630 rdf/type owl/Class)
(obo/IAO_0000630 rdfs/subClassOf obo/IAO_0000314)
(obo/IAO_0000630 rdfs/subClassOf bnode/ccp-extensions_genid320)
(bnode/ccp-extensions_genid320 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid320 owl/onProperty obo/BFO_0000051)
(bnode/ccp-extensions_genid320 owl/someValuesFrom obo/IAO_0000629)
(obo/IAO_0000630 obo/IAO_0000111 ["keywords section" "en"])
(obo/IAO_0000630 obo/IAO_0000112 ["The section labelled 'keywords' in a typical scientific journal article." "en"])
(obo/IAO_0000630 obo/IAO_0000115 ["A part of a document where keywords selected by the author to categorize the major theme(s) of a document are listed." "en"])
(obo/IAO_0000630 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
(obo/IAO_0000630 obo/IAO_0000118 ["keywords" "en"])
 ;(obo/IAO_0000630 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000630 rdfs/label ["keywords section" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000631
(obo/IAO_0000631 rdf/type owl/Class)
(obo/IAO_0000631 rdfs/subClassOf obo/IAO_0000314)
(obo/IAO_0000631 rdfs/subClassOf bnode/ccp-extensions_genid321)
(bnode/ccp-extensions_genid321 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid321 owl/onProperty obo/BFO_0000051)
(bnode/ccp-extensions_genid321 owl/someValuesFrom obo/IAO_0000632)
(obo/IAO_0000631 rdfs/subClassOf bnode/ccp-extensions_genid322)
(bnode/ccp-extensions_genid322 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid322 owl/onProperty obo/IAO_0000136)
(bnode/ccp-extensions_genid322 owl/someValuesFrom obo/OBI_0500000)
(obo/IAO_0000631 obo/IAO_0000111 ["study limitations section" "en"])
(obo/IAO_0000631 obo/IAO_0000112 ["The section labelled 'limitations' in a typical scientific journal article." "en"])
(obo/IAO_0000631 obo/IAO_0000115 ["A part of a document about biases or short comings related to the study design and execution." "en"])
(obo/IAO_0000631 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
(obo/IAO_0000631 obo/IAO_0000118 ["limitations" "en"])
(obo/IAO_0000631 obo/IAO_0000118 ["study limitations" "en"])
(obo/IAO_0000631 obo/IAO_0000119 ["Author guidelines published by The Society for Academic Emergency Medicine. (http://onlinelibrary.wiley.com/journal/10.1111/(ISSN)1553-2712/homepage/ForAuthors.html)" "en"])
 ;(obo/IAO_0000631 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000631 rdfs/label ["study limitations section" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000632
(obo/IAO_0000632 rdf/type owl/Class)
(obo/IAO_0000632 rdfs/subClassOf obo/IAO_0000300)
(obo/IAO_0000632 obo/IAO_0000111 ["study limitations textual entity" "en"])
(obo/IAO_0000632 obo/IAO_0000112 ["Excerpt from the Limitations section of Fermann et al 2015, Acad Emerg Med. 2015 Mar; 22(3): 299\u2013307 (http://www.ncbi.nlm.nih.gov/pmc/articles/PMC4405051/). \n\nOwing to the nature of a post hoc study, any significant values must be interpreted with caution. In the current analysis, no multiple testing was conducted and p-values remain unadjusted. Moreover, a selection bias arising from the randomized open-label design of the original EINSTEIN PE study cannot be ruled out." "en"])
(obo/IAO_0000632 obo/IAO_0000115 ["A textual entity addressing a shortcoming or bias of a study design or execution." "en"])
(obo/IAO_0000632 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
(obo/IAO_0000632 obo/IAO_0000119 ["Author guidelines published by The Society for Academic Emergency Medicine. (http://onlinelibrary.wiley.com/journal/10.1111/(ISSN)1553-2712/homepage/ForAuthors.html)" "en"])
 ;(obo/IAO_0000632 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000632 rdfs/label ["study limitations textual entity" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000633
(obo/IAO_0000633 rdf/type owl/Class)
(obo/IAO_0000633 rdfs/subClassOf obo/IAO_0000314)
(obo/IAO_0000633 obo/IAO_0000111 ["materials section" "en"])
(obo/IAO_0000633 obo/IAO_0000112 ["The section labelled 'materials' in a typical scientific journal article, e.g. Nguyen et al. BMC Bioinformatics. 2010; 11: 279. (http://www.ncbi.nlm.nih.gov/pmc/articles/PMC2889936/)" "en"])
(obo/IAO_0000633 obo/IAO_0000115 ["A part of a document about the materials required to reproduce the content of the document." "en"])
(obo/IAO_0000633 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
(obo/IAO_0000633 obo/IAO_0000118 ["materials" "en"])
 ;(obo/IAO_0000633 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000633 rdfs/label ["materials section" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000634
(obo/IAO_0000634 rdf/type owl/Class)
(obo/IAO_0000634 rdfs/subClassOf obo/IAO_0000314)
(obo/IAO_0000634 obo/IAO_0000111 ["notes section" "en"])
(obo/IAO_0000634 obo/IAO_0000112 ["The section labelled 'notes' in a typical scientific journal article, e.g. McLean et al. Br J Gen Pract. 2014 Jul; 64(624): e440\u2013e447 (http://www.ncbi.nlm.nih.gov/pmc/articles/PMC4073730/):" "en"])
(obo/IAO_0000634 obo/IAO_0000115 ["A part of a document containing typically short notes about the document itself and/or the authors. Often the notes section contains subsections related to funding, competing interests, ethical approval, etc." "en"])
(obo/IAO_0000634 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
(obo/IAO_0000634 obo/IAO_0000118 ["footnotes" "en"])
(obo/IAO_0000634 obo/IAO_0000118 ["notes" "en"])
 ;(obo/IAO_0000634 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000634 rdfs/label ["notes section" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000635
(obo/IAO_0000635 rdf/type owl/Class)
(obo/IAO_0000635 rdfs/subClassOf obo/IAO_0000314)
(obo/IAO_0000635 rdfs/subClassOf bnode/ccp-extensions_genid323)
(bnode/ccp-extensions_genid323 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid323 owl/onProperty obo/BFO_0000051)
(bnode/ccp-extensions_genid323 owl/someValuesFrom obo/IAO_0000636)
(obo/IAO_0000635 obo/IAO_0000111 ["patients section" "en"])
(obo/IAO_0000635 obo/IAO_0000112 ["The section labelled 'patients' in a typical scientific journal article, e.g. in Citak et al. Acta Orthop. 2013 Jun; 84(3): 326\u2013327. (http://www.ncbi.nlm.nih.gov/pmc/articles/PMC3715825/)" "en"])
(obo/IAO_0000635 obo/IAO_0000115 ["A part of a document about the patients that participated in a study." "en"])
(obo/IAO_0000635 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
 ;(obo/IAO_0000635 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000635 rdfs/label ["patients section" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000636
(obo/IAO_0000636 rdf/type owl/Class)
(obo/IAO_0000636 rdfs/subClassOf obo/IAO_0000300)
(obo/IAO_0000636 obo/IAO_0000111 ["patients textual entity" "en"])
(obo/IAO_0000636 obo/IAO_0000112 ["Excerpt from Citak et al. Acta Orthop. 2013 Jun; 84(3): 326\u2013327. (http://www.ncbi.nlm.nih.gov/pmc/articles/PMC3715825/):\n\nBetween January 1996 and February 2012, we treated 4 patients with interprosthetic femoral fractures (3 of them women) (Figure 2) using a custom-made interposition device (Waldemar Link GmbH, Hamburg, Germany) (Figure 1). Mean age was 74 (59\u201386) years. The fractures occurred mean 18 (13\u201328) years after primary THA and mean 14 (10\u201317) years after primary TKA. At the latest follow-up, after mean 8 (0.5\u201316) years, revision surgery with a total femur replacement was required in 1 case due to aseptic loosening. No other complications requiring revision surgery occurred." "en"])
(obo/IAO_0000636 obo/IAO_0000115 ["A textual entity expressing information regarding the patients used in a study." "en"])
(obo/IAO_0000636 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
 ;(obo/IAO_0000636 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000636 rdfs/label ["patients textual entity" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000637
(obo/IAO_0000637 rdf/type owl/Class)
(obo/IAO_0000637 rdfs/subClassOf obo/IAO_0000314)
(obo/IAO_0000637 rdfs/subClassOf bnode/ccp-extensions_genid324)
(bnode/ccp-extensions_genid324 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid324 owl/onProperty obo/BFO_0000051)
(bnode/ccp-extensions_genid324 owl/someValuesFrom obo/IAO_0000638)
(obo/IAO_0000637 obo/IAO_0000111 ["pre-publication history section" "en"])
(obo/IAO_0000637 obo/IAO_0000112 ["The section labelled 'pre-publication history' in a typical scientific journal article, e.g. in Xiao et al. BMC Anesthesiol. 2013; 13: 33. (http://www.ncbi.nlm.nih.gov/pmc/articles/PMC4016475/)" "en"])
(obo/IAO_0000637 obo/IAO_0000115 ["A part of the document about the publication history of a document. This section typically details dates of document submission to a journal and dates of any re-submissions as well as reviewer comments and responses to reviewers by the authors." "en"])
(obo/IAO_0000637 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
(obo/IAO_0000637 obo/IAO_0000118 ["notice of republication" "en"])
(obo/IAO_0000637 obo/IAO_0000118 ["pre-publication history" "en"])
 ;(obo/IAO_0000637 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000637 rdfs/label ["pre-publication history section" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000638
(obo/IAO_0000638 rdf/type owl/Class)
(obo/IAO_0000638 rdfs/subClassOf obo/IAO_0000300)
(obo/IAO_0000638 obo/IAO_0000111 ["pre-publication history textual entity" "en"])
(obo/IAO_0000638 obo/IAO_0000112 ["From Xiao et al. BMC Anesthesiol. 2013; 13: 33. (http://www.ncbi.nlm.nih.gov/pmc/articles/PMC4016475/):\n\nThe pre-publication history for this paper can be accessed here:\nhttp://www.biomedcentral.com/1471-2253/13/33/prepub" "en"])
(obo/IAO_0000638 obo/IAO_0000115 ["A textual entity that expresses the pre-publication history (submission dates, reviewer comments, etc) for a document, often including a hyperlink to a web page detailing the information." "en"])
(obo/IAO_0000638 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
 ;(obo/IAO_0000638 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000638 rdfs/label ["pre-publication history textual entity" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000639
(obo/IAO_0000639 rdf/type owl/Class)
(obo/IAO_0000639 rdfs/subClassOf obo/IAO_0000314)
(obo/IAO_0000639 rdfs/subClassOf bnode/ccp-extensions_genid325)
(bnode/ccp-extensions_genid325 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid325 owl/onProperty obo/BFO_0000051)
(bnode/ccp-extensions_genid325 owl/someValuesFrom obo/IAO_0000640)
(obo/IAO_0000639 obo/IAO_0000111 ["related work section" "en"])
(obo/IAO_0000639 obo/IAO_0000112 ["The section labelled 'related work' in a typical scientific journal article, e.g. \u017Ditnik and Zupan. Bioinformatics. 2015 Jun 15; 31(12): i230\u2013i239. (http://www.ncbi.nlm.nih.gov/pmc/articles/PMC4542780/)" "en"])
(obo/IAO_0000639 obo/IAO_0000115 ["A part of a document about work in other publications that is relevant to the content of the document." "en"])
(obo/IAO_0000639 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
(obo/IAO_0000639 obo/IAO_0000118 ["related literature" "en"])
(obo/IAO_0000639 obo/IAO_0000118 ["related work" "en"])
 ;(obo/IAO_0000639 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000639 rdfs/label ["related work section" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000640
(obo/IAO_0000640 rdf/type owl/Class)
(obo/IAO_0000640 rdfs/subClassOf obo/IAO_0000300)
(obo/IAO_0000640 obo/IAO_0000111 ["related work textual entity" "en"])
(obo/IAO_0000640 obo/IAO_0000112 ["Excerpt from \u017Ditnik and Zupan. Bioinformatics. 2015 Jun 15; 31(12): i230\u2013i239. (http://www.ncbi.nlm.nih.gov/pmc/articles/PMC4542780/):\n\nOur work presented here is similar in spirit to our recently developed methodology for data fusion via collective matrix factorization (\u017Ditnik and Zupan, 2015).\n\n" "en"])
(obo/IAO_0000640 obo/IAO_0000115 ["A textual entity that discusses work from other publications and expresses their relevancy to the content of a document." "en"])
(obo/IAO_0000640 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
 ;(obo/IAO_0000640 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000640 rdfs/label ["related work textual entity" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000641
(obo/IAO_0000641 rdf/type owl/Class)
(obo/IAO_0000641 rdfs/subClassOf obo/IAO_0000314)
(obo/IAO_0000641 rdfs/subClassOf bnode/ccp-extensions_genid326)
(bnode/ccp-extensions_genid326 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid326 owl/onProperty obo/BFO_0000051)
(bnode/ccp-extensions_genid326 owl/someValuesFrom obo/IAO_0000642)
(obo/IAO_0000641 obo/IAO_0000111 ["requirements section" "en"])
(obo/IAO_0000641 obo/IAO_0000112 ["The section labelled 'availability and requirements' in a typical scientific journal article, e.g. Qi et al. BMC Bioinformatics. 2014; 15: 11. (http://www.ncbi.nlm.nih.gov/pmc/articles/PMC3897912/)." "en"])
(obo/IAO_0000641 obo/IAO_0000115 ["A part of a document about a resource described in the document, e.g. software, that describes the requirements necessary to use the resource, e.g. operating systems, hardware, etc. in the case of a software resource." "en"])
(obo/IAO_0000641 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
(obo/IAO_0000641 obo/IAO_0000118 ["requirements" "en"])
 ;(obo/IAO_0000641 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000641 rdfs/label ["requirements section" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000642
(obo/IAO_0000642 rdf/type owl/Class)
(obo/IAO_0000642 rdfs/subClassOf obo/IAO_0000300)
(obo/IAO_0000642 obo/IAO_0000111 ["requirements textual entity" "en"])
(obo/IAO_0000642 obo/IAO_0000112 ["From Qi et al. BMC Bioinformatics. 2014; 15: 11. (http://www.ncbi.nlm.nih.gov/pmc/articles/PMC3897912/):\n\n\u2022 Operating systems: Platform independent\n\n\u2022 Programming language: Matlab, R, Python\n\n\u2022 Other requirements: None\n\n\u2022 License: GNU GPL v3\n\n\u2022 Any restrictions to use by non-academics: None" "en"])
(obo/IAO_0000642 obo/IAO_0000115 ["A textual entity that expresses the requirements necessary to use a resource, e.g. software." "en"])
(obo/IAO_0000642 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
 ;(obo/IAO_0000642 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000642 rdfs/label ["requirements textual entity" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000643
(obo/IAO_0000643 rdf/type owl/Class)
(obo/IAO_0000643 rdfs/subClassOf obo/IAO_0000300)
(obo/IAO_0000643 obo/IAO_0000111 ["statistical analysis textual entity" "en"])
(obo/IAO_0000643 obo/IAO_0000112 ["From Mondo et al. Cardiovasc J Afr. 2013 Mar; 24(2): 28\u201333. (http://www.ncbi.nlm.nih.gov/pmc/articles/PMC3734881/):\n\nData were captured into EPI-DATA (version 3.1), cleaned and then exported to Stata version 10 for analysis. Continuous variables were summarised as mean (\u00B1 standard deviation) and median (inter-quartile range), and presented in the tables. Categorical data were analysed using frequency and percentages, and results are presented in frequency tables and bar charts. Test of significance (p-value) was determined using the chi-square test. A p-value of less than 0.05 was considered significant." "en"])
(obo/IAO_0000643 obo/IAO_0000115 ["A textual entity documenting statistical analysis tools and techniques employed." "en"])
(obo/IAO_0000643 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
 ;(obo/IAO_0000643 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000643 rdfs/label ["statistical analysis textual entity" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000644
(obo/IAO_0000644 rdf/type owl/Class)
(obo/IAO_0000644 rdfs/subClassOf obo/IAO_0000314)
(obo/IAO_0000644 rdfs/subClassOf bnode/ccp-extensions_genid327)
(bnode/ccp-extensions_genid327 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid327 owl/onProperty obo/BFO_0000051)
(bnode/ccp-extensions_genid327 owl/someValuesFrom obo/IAO_0000643)
(obo/IAO_0000644 obo/IAO_0000111 ["statistical analysis section" "en"])
(obo/IAO_0000644 obo/IAO_0000112 ["The section labelled 'statistical analysis' in a typical scientific journal article, e.g. Mondo et al. Cardiovasc J Afr. 2013 Mar; 24(2): 28\u201333. (http://www.ncbi.nlm.nih.gov/pmc/articles/PMC3734881/)" "en"])
(obo/IAO_0000644 obo/IAO_0000115 ["A part of the document used to describe the statistical methodologies employed in the work presented in the document." "en"])
(obo/IAO_0000644 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
(obo/IAO_0000644 obo/IAO_0000118 ["statistical analysis" "en"])
 ;(obo/IAO_0000644 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000644 rdfs/label ["statistical analysis section" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000645
(obo/IAO_0000645 rdf/type owl/Class)
(obo/IAO_0000645 rdfs/subClassOf obo/IAO_0000314)
(obo/IAO_0000645 rdfs/subClassOf bnode/ccp-extensions_genid328)
(bnode/ccp-extensions_genid328 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid328 owl/onProperty obo/BFO_0000051)
(bnode/ccp-extensions_genid328 owl/someValuesFrom obo/IAO_0000306)
(obo/IAO_0000645 obo/IAO_0000111 ["tables section" "en"])
(obo/IAO_0000645 obo/IAO_0000112 ["The section labelled 'tables' in a typical scientific journal article." "en"])
(obo/IAO_0000645 obo/IAO_0000115 ["A part of a document that contains one or more tables." "en"])
(obo/IAO_0000645 obo/IAO_0000117 ["PERSON: Bill Baumgartner" "en"])
(obo/IAO_0000645 obo/IAO_0000118 ["tables" "en"])
 ;(obo/IAO_0000645 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/183)
(obo/IAO_0000645 rdfs/label ["tables section" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000650
(obo/IAO_0000650 rdf/type owl/Class)
(obo/IAO_0000650 rdfs/subClassOf obo/OBI_0000011)
(obo/IAO_0000650 rdfs/subClassOf bnode/ccp-extensions_genid329)
(bnode/ccp-extensions_genid329 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid329 owl/onProperty obo/OBI_0000293)
(bnode/ccp-extensions_genid329 owl/someValuesFrom obo/IAO_0000030)
(obo/IAO_0000650 rdfs/subClassOf bnode/ccp-extensions_genid330)
(bnode/ccp-extensions_genid330 rdf/type owl/Restriction)
(bnode/ccp-extensions_genid330 owl/onProperty obo/OBI_0000299)
(bnode/ccp-extensions_genid330 owl/someValuesFrom obo/IAO_0000030)
(obo/IAO_0000650 obo/IAO_0000111 ["database extract, transform, and load process" "en"])
(obo/IAO_0000650 obo/IAO_0000115 ["A planned process which takes as input a database and fills another database by extracting concretizations of information entities from the first, transforming them, and loading the transformed concretizations into the second." "en"])
(obo/IAO_0000650 obo/IAO_0000116 ["Alan Ruttenberg 12/21/16: Maybe this definition instead: A planned process which takes as input a database and copies concretizations from the first, optionally transforms then copies the result to the second"])
(obo/IAO_0000650 obo/IAO_0000116 ["Alan Ruttenberg 12/21/16: We don't define database in IAO, currently, as the bare word is ambiguous. Reasonable interpretations of the word might be the material entity, an information structure, an information content entity. However this definition commits, at least, to there being some material thing which bear concretizations of information entities and that there are new concretizations created during the process.  We consider the ETL process in terms of information entities rather than the concretizations. No committment is made as to whether the specified output." "en"])
(obo/IAO_0000650 obo/IAO_0000117 ["PERSON:Alan Ruttenberg"])
(obo/IAO_0000650 obo/IAO_0000118 ["ETL" "en"])
(obo/IAO_0000650 obo/IAO_0000119 ["WEB:https://en.wikipedia.org/wiki/Extract,_transform,_load"])
 ;(obo/IAO_0000650 obo/IAO_0000233 <https://github.com/information-artifact-ontology/IAO/issues/187)
;(obo/IAO_0000650 rdfs/isDefinedBy <https://github.com/information-artifact-ontology/IAO)
(obo/IAO_0000650 rdfs/label ["database extract, transform, and load process" "en"])
;(obo/IAO_0000650 foaf/page <https://en.wikipedia.org/wiki/Extract,_transform,_load)
;; 
;; http://purl.obolibrary.org/obo/OBI_0000011
(obo/OBI_0000011 rdf/type owl/Class)
(obo/OBI_0000011 rdfs/subClassOf obo/BFO_0000015)
(obo/OBI_0000011 obo/IAO_0000111 ["planned process" "en"])
(obo/OBI_0000011 obo/IAO_0000115 ["A processual entity that realizes a plan which is the concretization of a plan specification." "en"])
(obo/OBI_0000011 obo/IAO_0000412 obo/obi.owl)
(obo/OBI_0000011 rdfs/label ["planned process" "en"])
;; 
;; http://purl.obolibrary.org/obo/OBI_0000066
(obo/OBI_0000066 rdf/type owl/Class)
(obo/OBI_0000066 rdfs/subClassOf obo/OBI_0000011)
(obo/OBI_0000066 obo/IAO_0000111 ["investigation" "en"])
(obo/OBI_0000066 obo/IAO_0000115 ["a planned process that consists of parts: planning, study design execution, documentation and which produce conclusion(s)." "en"])
(obo/OBI_0000066 obo/IAO_0000412 obo/obi.owl)
(obo/OBI_0000066 rdfs/label ["investigation" "en"])
;; 
;; http://purl.obolibrary.org/obo/OBI_0000471
(obo/OBI_0000471 rdf/type owl/Class)
(obo/OBI_0000471 rdfs/subClassOf obo/OBI_0000011)
(obo/OBI_0000471 obo/IAO_0000111 ["study design execution"])
(obo/OBI_0000471 obo/IAO_0000115 ["a planned process that carries out a study design"])
(obo/OBI_0000471 obo/IAO_0000412 obo/obi.owl)
(obo/OBI_0000471 rdfs/label ["study design execution" "en"]) ;; added "en" to allow labels to match during unit testing
;; 
;; http://purl.obolibrary.org/obo/OBI_0500000
(obo/OBI_0500000 rdf/type owl/Class)
(obo/OBI_0500000 rdfs/subClassOf obo/IAO_0000104)
(obo/OBI_0500000 obo/IAO_0000111 ["study design" "en"])
(obo/OBI_0500000 obo/IAO_0000115 ["A plan specification comprised of protocols (which may specify how and what kinds of data will be gathered) that are executed as part of an investigation and is realized during a study design execution."])
(obo/OBI_0500000 obo/IAO_0000412 obo/obi.owl)
(obo/OBI_0500000 rdfs/label ["study design" "en"])
;; 
;; http://purl.obolibrary.org/obo/PATO_0000122
(obo/PATO_0000122 rdf/type owl/Class)
(obo/PATO_0000122 rdfs/subClassOf obo/BFO_0000019)
(obo/PATO_0000122 obo/IAO_0000111 ["length"])
(obo/PATO_0000122 obo/IAO_0000115 ["A 1-D extent quality which is equal to the distance between two points."])
(obo/PATO_0000122 obo/IAO_0000412 obo/pato.owl)
(obo/PATO_0000122 rdfs/label ["length"  "en"]) ;; added "en" to allow labels to match during unit testing
;; 
;; http://purl.obolibrary.org/obo/PATO_0000125
(obo/PATO_0000125 rdf/type owl/Class)
(obo/PATO_0000125 rdfs/subClassOf obo/BFO_0000019)
(obo/PATO_0000125 obo/IAO_0000111 ["mass"])
(obo/PATO_0000125 obo/IAO_0000115 ["A physical quality that inheres in a bearer by virtue of the proportion of the bearer's amount of matter."])
(obo/PATO_0000125 obo/IAO_0000412 obo/pato.owl)
(obo/PATO_0000125 rdfs/label ["mass"  "en"]) ;; added "en" to allow labels to match during unit testing
;; 
;; http://purl.obolibrary.org/obo/UO_0000001
(obo/UO_0000001 rdf/type owl/Class)
(obo/UO_0000001 rdfs/subClassOf obo/IAO_0000003)
(obo/UO_0000001 obo/IAO_0000111 ["length unit"])
(obo/UO_0000001 obo/IAO_0000115 ["A unit which is a standard measure of the distance between two points."])
(obo/UO_0000001 obo/IAO_0000412 obo/uo.owl)
(obo/UO_0000001 rdfs/label ["length unit"])
;; 
;; http://purl.obolibrary.org/obo/UO_0000002
(obo/UO_0000002 rdf/type owl/Class)
(obo/UO_0000002 rdfs/subClassOf obo/IAO_0000003)
(obo/UO_0000002 obo/IAO_0000111 ["mass unit"])
(obo/UO_0000002 obo/IAO_0000115 ["A unit which is a standard measure of the amount of matter/energy of a physical object."])
(obo/UO_0000002 obo/IAO_0000412 obo/uo.owl)
(obo/UO_0000002 rdfs/label ["mass unit"])
;; 
;; http://purl.obolibrary.org/obo/UO_0000003
(obo/UO_0000003 rdf/type owl/Class)
(obo/UO_0000003 rdfs/subClassOf obo/IAO_0000003)
(obo/UO_0000003 obo/IAO_0000111 ["time unit"])
(obo/UO_0000003 obo/IAO_0000115 ["A unit which is a standard measure of the dimension in which events occur in sequence."])
(obo/UO_0000003 obo/IAO_0000412 obo/uo.owl)
(obo/UO_0000003 rdfs/label ["time unit"])
;; 
;; http://www.geneontology.org/formats/oboInOwl;;DbXref
(oboInOwl/DbXref rdf/type owl/Class)
(oboInOwl/DbXref rdfs/subClassOf obo/IAO_0000102)
;; 
;; http://www.geneontology.org/formats/oboInOwl;;Definition
(oboInOwl/Definition rdf/type owl/Class)
(oboInOwl/Definition rdfs/subClassOf obo/IAO_0000102)
;; 
;; http://www.geneontology.org/formats/oboInOwl;;ObsoleteClass
(oboInOwl/ObsoleteClass rdf/type owl/Class)
(oboInOwl/ObsoleteClass rdfs/subClassOf owl/Thing)
(oboInOwl/ObsoleteClass rdfs/label ["Obsolete Class"])
;; 
;; http://www.geneontology.org/formats/oboInOwl;;Subset
(oboInOwl/Subset rdf/type owl/Class)
(oboInOwl/Subset owl/equivalentClass bnode/ccp-extensions_genid331)
(bnode/ccp-extensions_genid331 rdf/type owl/Class)
(bnode/ccp-extensions_genid331 owl/oneOf bnode/ccp-extensions_genid332)
(bnode/ccp-extensions_genid332 rdf/type rdf/List)
(bnode/ccp-extensions_genid332 rdf/first obo/IAO_0000224)
(bnode/ccp-extensions_genid332 rdf/rest rdf/nil)
(oboInOwl/Subset rdfs/subClassOf obo/IAO_0000102)
;; 
;; http://www.geneontology.org/formats/oboInOwl;;Synonym
(oboInOwl/Synonym rdf/type owl/Class)
(oboInOwl/Synonym rdfs/subClassOf obo/IAO_0000102)
;; 
;; http://www.geneontology.org/formats/oboInOwl;;SynonymType
(oboInOwl/SynonymType rdf/type owl/Class)
(oboInOwl/SynonymType rdfs/subClassOf obo/IAO_0000102)
;; 
;; 
;; 
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; ;;
;; ;;    Individuals
;; ;;
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 
;; 
;; http://purl.obolibrary.org/obo/IAO_0000002
(obo/IAO_0000002 rdf/type owl/NamedIndividual)
(obo/IAO_0000002 rdf/type obo/IAO_0000078)
(obo/IAO_0000002 rdfs/label ["example to be eventually removed" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000103
(obo/IAO_0000103 rdf/type owl/NamedIndividual)
(obo/IAO_0000103 rdf/type obo/IAO_0000225)
(obo/IAO_0000103 rdf/type owl/Thing)
(obo/IAO_0000103 obo/IAO_0000115 ["The term was used in an attempt to structure part of the ontology but in retrospect failed to do a good job" "en"])
(obo/IAO_0000103 obo/IAO_0000117 ["Person:Alan Ruttenberg"])
(obo/IAO_0000103 rdfs/label ["failed exploratory term" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000120
(obo/IAO_0000120 rdf/type owl/NamedIndividual)
(obo/IAO_0000120 rdf/type obo/IAO_0000078)
(obo/IAO_0000120 rdf/type owl/Thing)
(obo/IAO_0000120 obo/IAO_0000115 ["Class has all its metadata, but is either not guaranteed to be in its final location in the asserted IS_A hierarchy or refers to another class that is not complete." "en"])
(obo/IAO_0000120 rdfs/label ["metadata complete" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000121
(obo/IAO_0000121 rdf/type owl/NamedIndividual)
(obo/IAO_0000121 rdf/type obo/IAO_0000078)
(obo/IAO_0000121 obo/IAO_0000115 ["term created to ease viewing/sort terms for development purpose, and will not be included in a release" "en"])
(obo/IAO_0000121 rdfs/label ["organizational term" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000122
(obo/IAO_0000122 rdf/type owl/NamedIndividual)
(obo/IAO_0000122 rdf/type obo/IAO_0000078)
(obo/IAO_0000122 obo/IAO_0000115 ["Class has undergone final review, is ready for use, and will be included in the next release. Any class lacking \"ready_for_release\" should be considered likely to change place in hierarchy, have its definition refined, or be obsoleted in the next release.  Those classes deemed \"ready_for_release\" will also derived from a chain of ancestor classes that are also \"ready_for_release.\"" "en"])
(obo/IAO_0000122 rdfs/label ["ready for release" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000123
(obo/IAO_0000123 rdf/type owl/NamedIndividual)
(obo/IAO_0000123 rdf/type obo/IAO_0000078)
(obo/IAO_0000123 obo/IAO_0000115 ["Class is being worked on; however, the metadata (including definition) are not complete or sufficiently clear to the branch editors." "en"])
(obo/IAO_0000123 rdfs/label ["metadata incomplete" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000124
(obo/IAO_0000124 rdf/type owl/NamedIndividual)
(obo/IAO_0000124 rdf/type obo/IAO_0000078)
(obo/IAO_0000124 obo/IAO_0000115 ["Nothing done yet beyond assigning a unique class ID and proposing a preferred term." "en"])
(obo/IAO_0000124 rdfs/label ["uncurated" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000125
(obo/IAO_0000125 rdf/type owl/NamedIndividual)
(obo/IAO_0000125 rdf/type obo/IAO_0000078)
(obo/IAO_0000125 obo/IAO_0000115 ["All definitions, placement in the asserted IS_A hierarchy and required minimal metadata are complete. The class is awaiting a final review by someone other than the term editor." "en"])
(obo/IAO_0000125 rdfs/label ["pending final vetting" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000224
(obo/IAO_0000224 rdf/type owl/NamedIndividual)
(obo/IAO_0000224 rdf/type oboInOwl/Subset)
(obo/IAO_0000224 obo/IAO_0000115 ["Core is an instance of a grouping of terms from an ontology or ontologies. It is used by the ontology to identify main classes." "en"])
(obo/IAO_0000224 obo/IAO_0000117 ["PERSON: Alan Ruttenberg" "en"])
(obo/IAO_0000224 obo/IAO_0000117 ["PERSON: Melanie Courtot" "en"])
(obo/IAO_0000224 rdfs/label ["core" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000226
(obo/IAO_0000226 rdf/type owl/NamedIndividual)
(obo/IAO_0000226 rdf/type obo/IAO_0000225)
(obo/IAO_0000226 rdf/type owl/Thing)
(obo/IAO_0000226 rdfs/label ["placeholder removed" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000227
(obo/IAO_0000227 rdf/type owl/NamedIndividual)
(obo/IAO_0000227 rdf/type obo/IAO_0000225)
(obo/IAO_0000227 obo/IAO_0000116 ["An editor note should explain what were the merged terms and the reason for the merge." "en"])
(obo/IAO_0000227 rdfs/label ["terms merged" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000228
(obo/IAO_0000228 rdf/type owl/NamedIndividual)
(obo/IAO_0000228 rdf/type obo/IAO_0000225)
(obo/IAO_0000228 obo/IAO_0000116 ["This is to be used when the original term has been replaced by a term imported from an other ontology. An editor note should indicate what is the URI of the new term to use." "en"])
(obo/IAO_0000228 rdfs/label ["term imported" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000229
(obo/IAO_0000229 rdf/type owl/NamedIndividual)
(obo/IAO_0000229 rdf/type obo/IAO_0000225)
(obo/IAO_0000229 obo/IAO_0000116 ["This is to be used when a term has been split in two or more new terms. An editor note should indicate the reason for the split and indicate the URIs of the new terms created." "en"])
(obo/IAO_0000229 rdfs/label ["term split" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000230
(obo/IAO_0000230 rdf/type owl/NamedIndividual)
(obo/IAO_0000230 rdf/type obo/IAO_0000225)
(obo/IAO_0000230 obo/IAO_0000116 ["This is to be used if none of the existing instances cover the reason for obsolescence. An editor note should indicate this new reason." "en"])
(obo/IAO_0000230 obo/IAO_0000116 ["We expect to be able to mine these new reasons and add instances as required." "en"])
(obo/IAO_0000230 rdfs/label ["other" "en"])
(obo/IAO_0000230 owl/deprecated true)
;; 
;; http://purl.obolibrary.org/obo/IAO_0000410
(obo/IAO_0000410 rdf/type owl/NamedIndividual)
(obo/IAO_0000410 rdf/type obo/IAO_0000409)
(obo/IAO_0000410 obo/IAO_0000116 ["Hard to give a definition for. Intuitively a \"natural kind\" rather than a collection of any old things, which a class is able to be, formally. At the meta level, universals are defined as positives, are disjoint with their siblings, have single asserted parents."])
(obo/IAO_0000410 obo/IAO_0000117 ["Alan Ruttenberg"])
(obo/IAO_0000410 obo/IAO_0000119 ["A Formal Theory of Substances, Qualities, and Universals, http://ontology.buffalo.edu/bfo/SQU.pdf"])
(obo/IAO_0000410 rdfs/label ["universal"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000420
(obo/IAO_0000420 rdf/type owl/NamedIndividual)
(obo/IAO_0000420 rdf/type obo/IAO_0000409)
(obo/IAO_0000420 obo/IAO_0000115 ["A defined class is a class that is defined by a set of logically necessary and sufficient conditions but is not a universal"])
(obo/IAO_0000420 obo/IAO_0000116 ["\"definitions\", in some readings, always are given by necessary and sufficient conditions. So one must be careful (and this is difficult sometimes) to distinguish between defined classes and universal."])
(obo/IAO_0000420 obo/IAO_0000117 ["Alan Ruttenberg"])
(obo/IAO_0000420 rdfs/label ["defined class"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000421
(obo/IAO_0000421 rdf/type owl/NamedIndividual)
(obo/IAO_0000421 rdf/type obo/IAO_0000409)
(obo/IAO_0000421 obo/IAO_0000115 ["A named class expression is a logical expression that is given a name. The name can be used in place of the expression."])
(obo/IAO_0000421 obo/IAO_0000116 ["named class expressions are used in order to have more concise logical definition but their extensions may not be interesting classes on their own. In languages such as OWL, with no provisions for macros, these show up as actuall classes. Tools may with to not show them as such, and to replace uses of the macros with their expansions"])
(obo/IAO_0000421 obo/IAO_0000117 ["Alan Ruttenberg"])
(obo/IAO_0000421 rdfs/label ["named class expression"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000423
(obo/IAO_0000423 rdf/type owl/NamedIndividual)
(obo/IAO_0000423 rdf/type obo/IAO_0000078)
(obo/IAO_0000423 obo/IAO_0000115 ["Terms with this status should eventually replaced with a term from another ontology." "en"])
(obo/IAO_0000423 obo/IAO_0000117 ["Alan Ruttenberg" "en"])
(obo/IAO_0000423 obo/IAO_0000119 ["group:OBI" "en"])
(obo/IAO_0000423 rdfs/label ["to be replaced with external ontology term" "en"])
;; 
;; http://purl.obolibrary.org/obo/IAO_0000428
(obo/IAO_0000428 rdf/type owl/NamedIndividual)
(obo/IAO_0000428 rdf/type obo/IAO_0000078)
(obo/IAO_0000428 obo/IAO_0000115 ["A term that is metadata complete, has been reviewed, and problems have been identified that require discussion before release. Such a term requires editor note(s) to identify the outstanding issues." "en"])
(obo/IAO_0000428 obo/IAO_0000117 ["Alan Ruttenberg" "en"])
(obo/IAO_0000428 obo/IAO_0000119 ["group:OBI" "en"])
(obo/IAO_0000428 rdfs/label ["requires discussion" "en"])
;; 
;; http://purl.obolibrary.org/obo/RO_0001901
(obo/RO_0001901 rdf/type owl/NamedIndividual)
(obo/RO_0001901 obo/IAO_0000115 ["\n\n;;;; Elucidation\n\nThis is used when the statement/axiom is assumed to hold true 'eternally'\n\n;;;; How to interpret (informal)\n\nFirst the \"atemporal\" FOL is derived from the OWL using the standard\ninterpretation. This axiom is temporalized by embedding the axiom\nwithin a for-all-times quantified sentence. The t argument is added to\nall instantiation predicates and predicates that use this relation.\n\n;;;; Example\n\n    Class: nucleus\n    SubClassOf: part_of some cell\n\n    forall t :\n      forall n :\n        instance_of(n,Nucleus,t)\n         implies\n        exists c :\n          instance_of(c,Cell,t)\n          part_of(n,c,t)\n\n;;;; Notes\n\nThis interpretation is *not* the same as an at-all-times relation\n\n"])
(obo/RO_0001901 rdfs/label ["axiom holds for all times" "en"])
;; 
;; http://purl.obolibrary.org/obo/RO_0001902
(obo/RO_0001902 rdf/type owl/NamedIndividual)
(obo/RO_0001902 obo/IAO_0000115 ["\n\n;;;; Elucidation\n\nThis is used when the first-order logic form of the relation is\nbinary, and takes no temporal argument.\n\n;;;; Example:\n\n    Class: limb\n    SubClassOf: develops_from some lateral-plate-mesoderm\n\n     forall t, t2:\n      forall x :\n        instance_of(x,Limb,t)\n         implies\n        exists y :\n          instance_of(y,LPM,t2)\n          develops_from(x,y)\n\n\n"])
(obo/RO_0001902 rdfs/label ["relation has no temporal argument" "en"])
;; 
;; http://purl.obolibrary.org/obo/iao.owl
(obo/iao.owl rdf/type owl/NamedIndividual)
;; 
;; http://purl.obolibrary.org/obo/obi.owl
(obo/obi.owl rdf/type owl/NamedIndividual)
;; 
;; 
;; 
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; ;;
;; ;;    Annotations
;; ;;
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 
;; 
(ex/bfo-spec-label obo/IAO_0000119 ["Person:Alan Ruttenberg"])
;; 
(obo/BFO_0000134 obo/IAO_0000600 ["To say that each spatiotemporal region s temporally_projects_onto some temporal region t is to say that t is the temporal extension of s. (axiom label in BFO2 Reference: [080-003])" "en"])
(obo/BFO_0000134 obo/IAO_0000600 ["To say that spatiotemporal region s spatially_projects_onto spatial region r at t is to say that r is the spatial extent of s at t. (axiom label in BFO2 Reference: [081-003])" "en"])
(bnode/ccp-extensions_genid333 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid333 owl/annotatedSource obo/BFO_0000134)
(bnode/ccp-extensions_genid333 owl/annotatedProperty obo/IAO_0000600)
(bnode/ccp-extensions_genid333 owl/annotatedTarget ["To say that each spatiotemporal region s temporally_projects_onto some temporal region t is to say that t is the temporal extension of s. (axiom label in BFO2 Reference: [080-003])" "en"])
 ;(bnode/ccp-extensions_genid333 obo/IAO_0010000 obo/bfo/axiom/080-003)
(bnode/ccp-extensions_genid334 rdf/type owl/Axiom)
(bnode/ccp-extensions_genid334 owl/annotatedSource obo/BFO_0000134)
(bnode/ccp-extensions_genid334 owl/annotatedProperty obo/IAO_0000600)
(bnode/ccp-extensions_genid334 owl/annotatedTarget ["To say that spatiotemporal region s spatially_projects_onto spatial region r at t is to say that r is the spatial extent of s at t. (axiom label in BFO2 Reference: [081-003])" "en"])
 ;(bnode/ccp-extensions_genid334 obo/IAO_0010000 obo/bfo/axiom/081-003)
;; 
;(obo/iao/d-acts.owl obo/IAO_0000603 ["IAO_0021000-IAO_0021999"])
;; 
;(obo/iao/pno.owl obo/IAO_0000603 ["IAO_0020000-IAO_0020999"])
;; 
;; 
;; 
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; ;;
;; ;;    General axioms
;; ;;
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 
;; 
(bnode/ccp-extensions_genid335 rdf/type owl/AllDifferent)
(bnode/ccp-extensions_genid335 owl/distinctMembers bnode/ccp-extensions_genid343)
(bnode/ccp-extensions_genid343 rdf/type rdf/List)
(bnode/ccp-extensions_genid343 rdf/first obo/IAO_0000120)
(bnode/ccp-extensions_genid343 rdf/rest bnode/ccp-extensions_genid342)
(bnode/ccp-extensions_genid342 rdf/type rdf/List)
(bnode/ccp-extensions_genid342 rdf/first obo/IAO_0000121)
(bnode/ccp-extensions_genid342 rdf/rest bnode/ccp-extensions_genid341)
(bnode/ccp-extensions_genid341 rdf/type rdf/List)
(bnode/ccp-extensions_genid341 rdf/first obo/IAO_0000122)
(bnode/ccp-extensions_genid341 rdf/rest bnode/ccp-extensions_genid340)
(bnode/ccp-extensions_genid340 rdf/type rdf/List)
(bnode/ccp-extensions_genid340 rdf/first obo/IAO_0000123)
(bnode/ccp-extensions_genid340 rdf/rest bnode/ccp-extensions_genid339)
(bnode/ccp-extensions_genid339 rdf/type rdf/List)
(bnode/ccp-extensions_genid339 rdf/first obo/IAO_0000124)
(bnode/ccp-extensions_genid339 rdf/rest bnode/ccp-extensions_genid338)
(bnode/ccp-extensions_genid338 rdf/type rdf/List)
(bnode/ccp-extensions_genid338 rdf/first obo/IAO_0000125)
(bnode/ccp-extensions_genid338 rdf/rest bnode/ccp-extensions_genid337)
(bnode/ccp-extensions_genid337 rdf/type rdf/List)
(bnode/ccp-extensions_genid337 rdf/first obo/IAO_0000423)
(bnode/ccp-extensions_genid337 rdf/rest bnode/ccp-extensions_genid336)
(bnode/ccp-extensions_genid336 rdf/type rdf/List)
(bnode/ccp-extensions_genid336 rdf/first obo/IAO_0000428)
(bnode/ccp-extensions_genid336 rdf/rest rdf/nil)
;; 
(bnode/ccp-extensions_genid344 rdf/type owl/AllDifferent)
(bnode/ccp-extensions_genid344 owl/distinctMembers bnode/ccp-extensions_genid348)
(bnode/ccp-extensions_genid348 rdf/type rdf/List)
(bnode/ccp-extensions_genid348 rdf/first obo/IAO_0000226)
(bnode/ccp-extensions_genid348 rdf/rest bnode/ccp-extensions_genid347)
(bnode/ccp-extensions_genid347 rdf/type rdf/List)
(bnode/ccp-extensions_genid347 rdf/first obo/IAO_0000227)
(bnode/ccp-extensions_genid347 rdf/rest bnode/ccp-extensions_genid346)
(bnode/ccp-extensions_genid346 rdf/type rdf/List)
(bnode/ccp-extensions_genid346 rdf/first obo/IAO_0000228)
(bnode/ccp-extensions_genid346 rdf/rest bnode/ccp-extensions_genid345)
(bnode/ccp-extensions_genid345 rdf/type rdf/List)
(bnode/ccp-extensions_genid345 rdf/first obo/IAO_0000229)
(bnode/ccp-extensions_genid345 rdf/rest rdf/nil)
;; 
;; Generated by the OWL API (version 4.2.6) https://github.com/owlcs/owlapi
))