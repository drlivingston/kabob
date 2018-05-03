@Grab(group = 'org.spockframework', module = 'spock-core', version = '1.0-groovy-2.4', scope = 'test')
import spock.lang.Specification
@Grab(group = 'commons-codec', module = 'commons-codec', version = '1.11')
import org.apache.commons.codec.digest.DigestUtils
import spock.lang.Ignore
import spock.lang.IgnoreRest

class BNodeConverterSpecification extends Specification {


    def "testing updateGenIdsInLine -- real case when one genid is substring of another"() {
        given:
        def line1 = "_:genid247 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> ."
        def line2 = "_:genid247 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> _:genid24768 ."
        def line3 = "_:genid24768 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> ."
        def converter = new BNodeConverter()

        def expectedLine1 = "<http://a> <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> ."
        def expectedLine2 = "<http://a> <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://b> ."
        def expectedLine3 = "<http://b> <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> ."

        Map<String, String> bnodeToUriMap = new HashMap<String, String>()
        bnodeToUriMap.put("_:genid247 ", "<http://a> ")
        bnodeToUriMap.put("_:genid24768 ", "<http://b> ")

        when:
        def updatedLine1 = converter.updateGenIdsInLine(line1, bnodeToUriMap)
        def updatedLine2 = converter.updateGenIdsInLine(line2, bnodeToUriMap)
        def updatedLine3 = converter.updateGenIdsInLine(line3, bnodeToUriMap)

        then:
        expectedLine1 == updatedLine1
        expectedLine2 == updatedLine2
        expectedLine3 == updatedLine3

    }


    def "testing cl vs hp assertions to be equal"() {
        given:
        def hp_lines = '''<http://purl.obolibrary.org/obo/CL_0000125> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid299 .
_:genid299 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid299 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/RO_0002202> .
_:genid299 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/CL_0000030> .'''

        def cl_lines = '''<http://purl.obolibrary.org/obo/CL_0000125> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid622 .
_:genid622 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid622 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/RO_0002202> .
_:genid622 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/CL_0000030> .
_:genid623 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .
_:genid623 <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/CL_0000125> .
_:genid623 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://www.w3.org/2000/01/rdf-schema#subClassOf> .
_:genid623 <http://www.w3.org/2002/07/owl#annotatedTarget> _:genid622 .
_:genid623 <http://www.w3.org/2000/01/rdf-schema#comment> "It is unclear that all glial cells develop from a cell type called a glioblast. Radial glial cells develop from neuroepithelial cells and other types of glial cells develop from other precursors. Unless glioblast is meant to describe any cell that can give rise to a glial cell, this relationship needs further investigation." .'''

        def hp_inputStream = new ByteArrayInputStream(hp_lines.getBytes())
        def cl_inputStream = new ByteArrayInputStream(cl_lines.getBytes())
        def converter = new BNodeConverter()

        when:
        Map<String, String> hp_bnodeToUriMap = converter.populateBnodeToUriMap(hp_inputStream)
        Map<String, String> cl_bnodeToUriMap = converter.populateBnodeToUriMap(cl_inputStream)

        def updated_hp_lines = []
        def updated_cl_lines = []
        hp_lines.split("\\n").each { line -> updated_hp_lines.add(converter.updateGenIdsInLine(line, hp_bnodeToUriMap))}
        cl_lines.split("\\n").each { line -> updated_cl_lines.add(converter.updateGenIdsInLine(line, cl_bnodeToUriMap))}

        then:
        updated_hp_lines.each {line ->
//            println "HP LINE: " + line
            assert updated_cl_lines.contains(line)
        }

    }


    def "testing UBERON_0000004 assertions to be equal"() {
        given:
        def uberon_lines = '''# http://purl.obolibrary.org/obo/UBERON_0000004
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Class> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://purl.obolibrary.org/obo/UBERON_0002268> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://purl.obolibrary.org/obo/UBERON_0004121> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://purl.obolibrary.org/obo/UBERON_0010314> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid8089 .
_:genid8089 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid8089 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/BFO_0000050> .
_:genid8089 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/UBERON_0001456> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid8091 .
_:genid8091 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid8091 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/RO_0002160> .
_:genid8091 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/NCBITaxon_7742> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid8092 .
_:genid8092 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid8092 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/RO_0002202> .
_:genid8092 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/UBERON_0003050> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid8094 .
_:genid8094 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid8094 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/RO_0002433> .
_:genid8094 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/UBERON_0001004> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid8095 .
_:genid8095 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid8095 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/RO_0002433> .
_:genid8095 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/UBERON_0001456> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid8096 .
_:genid8096 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid8096 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/RO_0002551> .
_:genid8096 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/UBERON_0006813> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://purl.obolibrary.org/obo/IAO_0000115> "The olfactory organ of vertebrates, consisting of nares, olfactory epithelia and the structures and skeletal framework of the nasal cavity."^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://purl.obolibrary.org/obo/UBPROP_0000001> "Organ that is the specialized structure of the face that contains olfactory neurons. The peripheral olfactory organ is paired[ZFA:0000047]."^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://purl.obolibrary.org/obo/UBPROP_0000001> "a protuberance in vertebrates that houses the nostrils, or nares, which admit and expel air for respiration in conjunction with the mouth. Behind the nose are the olfactory mucosa and the sinuses. Behind the nasal cavity, air next passes through the pharynx, shared with the digestive system, and then into the rest of the respiratory system. In humans, the nose is located centrally on the face; on most other mammals, it is on the upper tip of the snout[WP]. GO: The nose is the specialized structure of the face that serves as the organ of the sense of smell and as part of the respiratory system. Includes the nasi externus (external nose) and cavitas nasi (nasal cavity)[Wikipedia:Nose]."^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://purl.obolibrary.org/obo/UBPROP_0000001> "the organ that is specialized for smell and is part of the respiratory system"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://purl.obolibrary.org/obo/UBPROP_0000008> "the structure of the nose varies across vertebrates. In tetrapods the nose is part of the respiratory system.[PMID:25312359]"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> <http://en.wikipedia.org/wiki/Nose> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> <http://linkedlifedata.com/resource/umls/id/C0028429> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> <http://www.snomedbrowser.com/Codes/Details/181195007> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "BTO:0000840"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "CALOHA:TS-2037"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "EHDAA2:0001274"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "EHDAA:1502"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "EMAPA:16542"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "EV:0100037"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "EV:0100370"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "FMA:46472"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "GAID:77"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "MA:0000281"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "MAT:0000139"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "MESH:D009666"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "MIAA:0000139"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "NCIT:C12756"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "OpenCyc:Mx4rvViCbJwpEbGdrcN5Y29ycA"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "TAO:0000047"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "UMLS:C0028429"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "ZFA:0000047"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "galen:Nose"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasExactSynonym> "nasal sac"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasExactSynonym> "nose"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasExactSynonym> "peripheral olfactory organ"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasOBONamespace> "uberon"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasRelatedSynonym> "nasus"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasRelatedSynonym> "olfactory apparatus"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasRelatedSynonym> "proboscis"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#id> "UBERON:0000004"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#inSubset> <http://purl.obolibrary.org/obo/uberon/core#efo_slim> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#inSubset> <http://purl.obolibrary.org/obo/uberon/core#major_organ> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#inSubset> <http://purl.obolibrary.org/obo/uberon/core#pheno_slim> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#inSubset> <http://purl.obolibrary.org/obo/uberon/core#uberon_slim> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#inSubset> <http://purl.obolibrary.org/obo/uberon/core#vertebrate_core> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#label> "nose"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://xmlns.com/foaf/0.1/depicted_by> <http://upload.wikimedia.org/wikipedia/commons/d/d0/Canine-nose.jpg> .
_:genid8090 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .
_:genid8090 <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/UBERON_0000004> .
_:genid8090 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://www.w3.org/2000/01/rdf-schema#subClassOf> .
_:genid8090 <http://www.w3.org/2002/07/owl#annotatedTarget> _:genid8089 .
_:genid8090 <http://www.geneontology.org/formats/oboInOwl#source> "FMA"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid8090 <http://www.geneontology.org/formats/oboInOwl#source> "ZFA-def"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid8093 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .
_:genid8093 <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/UBERON_0000004> .
_:genid8093 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://www.w3.org/2000/01/rdf-schema#subClassOf> .
_:genid8093 <http://www.w3.org/2002/07/owl#annotatedTarget> _:genid8092 .
_:genid8093 <http://www.geneontology.org/formats/oboInOwl#source> "ZFA"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid8097 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .
_:genid8097 <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/UBERON_0000004> .
_:genid8097 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://purl.obolibrary.org/obo/IAO_0000115> .
_:genid8097 <http://www.w3.org/2002/07/owl#annotatedTarget> "The olfactory organ of vertebrates, consisting of nares, olfactory epithelia and the structures and skeletal framework of the nasal cavity."^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid8097 <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "UBERON:cjm"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid8098 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .
_:genid8098 <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/UBERON_0000004> .
_:genid8098 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://purl.obolibrary.org/obo/UBPROP_0000001> .
_:genid8098 <http://www.w3.org/2002/07/owl#annotatedTarget> "Organ that is the specialized structure of the face that contains olfactory neurons. The peripheral olfactory organ is paired[ZFA:0000047]."^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid8098 <http://www.geneontology.org/formats/oboInOwl#source> "ZFA:0000047"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid8099 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .
_:genid8099 <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/UBERON_0000004> .
_:genid8099 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://purl.obolibrary.org/obo/UBPROP_0000001> .
_:genid8099 <http://www.w3.org/2002/07/owl#annotatedTarget> "a protuberance in vertebrates that houses the nostrils, or nares, which admit and expel air for respiration in conjunction with the mouth. Behind the nose are the olfactory mucosa and the sinuses. Behind the nasal cavity, air next passes through the pharynx, shared with the digestive system, and then into the rest of the respiratory system. In humans, the nose is located centrally on the face; on most other mammals, it is on the upper tip of the snout[WP]. GO: The nose is the specialized structure of the face that serves as the organ of the sense of smell and as part of the respiratory system. Includes the nasi externus (external nose) and cavitas nasi (nasal cavity)[Wikipedia:Nose]."^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid8099 <http://www.geneontology.org/formats/oboInOwl#source> <http://en.wikipedia.org/wiki/Nose> .
_:genid8100 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .
_:genid8100 <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/UBERON_0000004> .
_:genid8100 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://purl.obolibrary.org/obo/UBPROP_0000001> .
_:genid8100 <http://www.w3.org/2002/07/owl#annotatedTarget> "the organ that is specialized for smell and is part of the respiratory system"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid8100 <http://www.geneontology.org/formats/oboInOwl#source> "MP:0002233"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid8101 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .
_:genid8101 <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/UBERON_0000004> .
_:genid8101 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> .
_:genid8101 <http://www.w3.org/2002/07/owl#annotatedTarget> "UMLS:C0028429"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid8101 <http://www.geneontology.org/formats/oboInOwl#source> "ncithesaurus:Nose"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid8102 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .
_:genid8102 <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/UBERON_0000004> .
_:genid8102 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://www.geneontology.org/formats/oboInOwl#hasExactSynonym> .
_:genid8102 <http://www.w3.org/2002/07/owl#annotatedTarget> "nasal sac"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid8102 <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "ZFA:0000047"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid8102 <http://www.geneontology.org/formats/oboInOwl#hasSynonymType> <http://purl.obolibrary.org/obo/uberon/core#SENSU> .
_:genid8103 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .
_:genid8103 <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/UBERON_0000004> .
_:genid8103 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://www.geneontology.org/formats/oboInOwl#hasExactSynonym> .
_:genid8103 <http://www.w3.org/2002/07/owl#annotatedTarget> "nose"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid8103 <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "MA:0000281"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid8103 <http://www.geneontology.org/formats/oboInOwl#hasSynonymType> <http://purl.obolibrary.org/obo/uberon/core#HUMAN_PREFERRED> .
_:genid8104 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .
_:genid8104 <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/UBERON_0000004> .
_:genid8104 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://www.geneontology.org/formats/oboInOwl#hasExactSynonym> .
_:genid8104 <http://www.w3.org/2002/07/owl#annotatedTarget> "peripheral olfactory organ"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid8104 <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "ZFA:0000047"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid8105 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .
_:genid8105 <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/UBERON_0000004> .
_:genid8105 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://www.geneontology.org/formats/oboInOwl#hasRelatedSynonym> .
_:genid8105 <http://www.w3.org/2002/07/owl#annotatedTarget> "nasus"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid8105 <http://www.geneontology.org/formats/oboInOwl#hasDbXref> <http://en.wikipedia.org/wiki/Nose> .
_:genid8105 <http://www.geneontology.org/formats/oboInOwl#hasSynonymType> <http://purl.obolibrary.org/obo/uberon/core#LATIN> .
_:genid8106 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .
_:genid8106 <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/UBERON_0000004> .
_:genid8106 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://www.geneontology.org/formats/oboInOwl#hasRelatedSynonym> .
_:genid8106 <http://www.w3.org/2002/07/owl#annotatedTarget> "olfactory apparatus"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid8106 <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "UBERON:cjm"^^<http://www.w3.org/2001/XMLSchema#string> .
#'''

        def go_plus_lines = '''# http://purl.obolibrary.org/obo/UBERON_0000004
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Class> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://purl.obolibrary.org/obo/UBERON_0002268> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://purl.obolibrary.org/obo/UBERON_0004121> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://purl.obolibrary.org/obo/UBERON_0010314> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid272494 .
_:genid272494 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid272494 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/BFO_0000050> .
_:genid272494 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/UBERON_0001456> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid272495 .
_:genid272495 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid272495 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/RO_0002160> .
_:genid272495 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/NCBITaxon_7742> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid272496 .
_:genid272496 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid272496 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/RO_0002202> .
_:genid272496 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/UBERON_0003050> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid272497 .
_:genid272497 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid272497 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/RO_0002433> .
_:genid272497 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/UBERON_0001004> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid272498 .
_:genid272498 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid272498 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/RO_0002433> .
_:genid272498 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/UBERON_0001456> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid272499 .
_:genid272499 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid272499 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/RO_0002551> .
_:genid272499 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/UBERON_0006813> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://purl.obolibrary.org/obo/IAO_0000115> "The olfactory organ of vertebrates, consisting of nares, olfactory epithelia and the structures and skeletal framework of the nasal cavity."^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasExactSynonym> "nasal sac"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasExactSynonym> "nose"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasExactSynonym> "peripheral olfactory organ"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasRelatedSynonym> "nasus"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasRelatedSynonym> "olfactory apparatus"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasRelatedSynonym> "proboscis"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#label> "nose"^^<http://www.w3.org/2001/XMLSchema#string> .
#'''

        def hp_lines = '''# http://purl.obolibrary.org/obo/UBERON_0000004
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Class> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://purl.obolibrary.org/obo/UBERON_0002268> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://purl.obolibrary.org/obo/UBERON_0004121> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://purl.obolibrary.org/obo/UBERON_0010314> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid74324 .
_:genid74324 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid74324 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/BFO_0000050> .
_:genid74324 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/UBERON_0001456> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid74325 .
_:genid74325 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid74325 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/RO_0002202> .
_:genid74325 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/UBERON_0003050> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#label> "nose"^^<http://www.w3.org/2001/XMLSchema#string> .
#'''

        def nbo_lines = '''# http://purl.obolibrary.org/obo/UBERON_0000004
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Class> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://purl.obolibrary.org/obo/UBERON_0002268> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://purl.obolibrary.org/obo/UBERON_0004121> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://purl.obolibrary.org/obo/UBERON_0010314> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid690038 .
_:genid690038 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid690038 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/BFO_0000050> .
_:genid690038 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/UBERON_0001456> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid690040 .
_:genid690040 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid690040 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/RO_0002202> .
_:genid690040 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/UBERON_0003050> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid690042 .
_:genid690042 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid690042 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/RO_0002433> .
_:genid690042 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/UBERON_0001004> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid690043 .
_:genid690043 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid690043 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/RO_0002433> .
_:genid690043 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/UBERON_0001456> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid690044 .
_:genid690044 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid690044 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/RO_0002551> .
_:genid690044 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/UBERON_0006813> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://purl.obolibrary.org/obo/IAO_0000115> "The olfactory organ of vertebrates, consisting of nares, olfactory epithelia and the structures and skeletal framework of the nasal cavity."^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://purl.obolibrary.org/obo/UBPROP_0000001> "Organ that is the specialized structure of the face that contains olfactory neurons. The peripheral olfactory organ is paired[ZFA:0000047]."^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://purl.obolibrary.org/obo/UBPROP_0000001> "a protuberance in vertebrates that houses the nostrils, or nares, which admit and expel air for respiration in conjunction with the mouth. Behind the nose are the olfactory mucosa and the sinuses. Behind the nasal cavity, air next passes through the pharynx, shared with the digestive system, and then into the rest of the respiratory system. In humans, the nose is located centrally on the face; on most other mammals, it is on the upper tip of the snout[WP]. GO: The nose is the specialized structure of the face that serves as the organ of the sense of smell and as part of the respiratory system. Includes the nasi externus (external nose) and cavitas nasi (nasal cavity)[Wikipedia:Nose]."^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://purl.obolibrary.org/obo/UBPROP_0000001> "the organ that is specialized for smell and is part of the respiratory system"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://purl.obolibrary.org/obo/UBPROP_0000008> "the structure of the nose varies across vertebrates. In tetrapods the nose is part of the respiratory system.[PMID:25312359]"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> <http://en.wikipedia.org/wiki/Nose> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> <http://linkedlifedata.com/resource/umls/id/C0028429> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> <http://www.snomedbrowser.com/Codes/Details/181195007> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "BTO:0000840"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "CALOHA:TS-2037"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "EHDAA2:0001274"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "EHDAA:1502"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "EMAPA:16542"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "EV:0100037"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "EV:0100370"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "FMA:46472"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "GAID:77"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "MA:0000281"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "MAT:0000139"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "MESH:D009666"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "MIAA:0000139"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "NCIT:C12756"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "OpenCyc:Mx4rvViCbJwpEbGdrcN5Y29ycA"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "TAO:0000047"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "UMLS:C0028429"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "ZFA:0000047"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "galen:Nose"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasExactSynonym> "nasal sac"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasExactSynonym> "nose"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasExactSynonym> "peripheral olfactory organ"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasOBONamespace> "uberon"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasRelatedSynonym> "nasus"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasRelatedSynonym> "olfactory apparatus"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasRelatedSynonym> "proboscis"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#id> "UBERON:0000004"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#inSubset> <http://purl.obolibrary.org/obo/uberon/core#efo_slim> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#inSubset> <http://purl.obolibrary.org/obo/uberon/core#major_organ> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#inSubset> <http://purl.obolibrary.org/obo/uberon/core#pheno_slim> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#inSubset> <http://purl.obolibrary.org/obo/uberon/core#uberon_slim> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#inSubset> <http://purl.obolibrary.org/obo/uberon/core#vertebrate_core> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#label> "nose"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://xmlns.com/foaf/0.1/depicted_by> <http://upload.wikimedia.org/wikipedia/commons/d/d0/Canine-nose.jpg> .
_:genid690039 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .
_:genid690039 <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/UBERON_0000004> .
_:genid690039 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://www.w3.org/2000/01/rdf-schema#subClassOf> .
_:genid690039 <http://www.w3.org/2002/07/owl#annotatedTarget> _:genid690038 .
_:genid690039 <http://www.geneontology.org/formats/oboInOwl#source> "FMA"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid690039 <http://www.geneontology.org/formats/oboInOwl#source> "ZFA-def"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid690041 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .
_:genid690041 <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/UBERON_0000004> .
_:genid690041 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://www.w3.org/2000/01/rdf-schema#subClassOf> .
_:genid690041 <http://www.w3.org/2002/07/owl#annotatedTarget> _:genid690040 .
_:genid690041 <http://www.geneontology.org/formats/oboInOwl#source> "ZFA"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid690045 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .
_:genid690045 <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/UBERON_0000004> .
_:genid690045 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://purl.obolibrary.org/obo/IAO_0000115> .
_:genid690045 <http://www.w3.org/2002/07/owl#annotatedTarget> "The olfactory organ of vertebrates, consisting of nares, olfactory epithelia and the structures and skeletal framework of the nasal cavity."^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid690045 <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "UBERON:cjm"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid690046 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .
_:genid690046 <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/UBERON_0000004> .
_:genid690046 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://purl.obolibrary.org/obo/UBPROP_0000001> .
_:genid690046 <http://www.w3.org/2002/07/owl#annotatedTarget> "Organ that is the specialized structure of the face that contains olfactory neurons. The peripheral olfactory organ is paired[ZFA:0000047]."^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid690046 <http://www.geneontology.org/formats/oboInOwl#source> "ZFA:0000047"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid690047 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .
_:genid690047 <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/UBERON_0000004> .
_:genid690047 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://purl.obolibrary.org/obo/UBPROP_0000001> .
_:genid690047 <http://www.w3.org/2002/07/owl#annotatedTarget> "a protuberance in vertebrates that houses the nostrils, or nares, which admit and expel air for respiration in conjunction with the mouth. Behind the nose are the olfactory mucosa and the sinuses. Behind the nasal cavity, air next passes through the pharynx, shared with the digestive system, and then into the rest of the respiratory system. In humans, the nose is located centrally on the face; on most other mammals, it is on the upper tip of the snout[WP]. GO: The nose is the specialized structure of the face that serves as the organ of the sense of smell and as part of the respiratory system. Includes the nasi externus (external nose) and cavitas nasi (nasal cavity)[Wikipedia:Nose]."^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid690047 <http://www.geneontology.org/formats/oboInOwl#source> <http://en.wikipedia.org/wiki/Nose> .
_:genid690048 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .
_:genid690048 <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/UBERON_0000004> .
_:genid690048 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://purl.obolibrary.org/obo/UBPROP_0000001> .
_:genid690048 <http://www.w3.org/2002/07/owl#annotatedTarget> "the organ that is specialized for smell and is part of the respiratory system"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid690048 <http://www.geneontology.org/formats/oboInOwl#source> "MP:0002233"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid690049 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .
_:genid690049 <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/UBERON_0000004> .
_:genid690049 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://www.geneontology.org/formats/oboInOwl#hasDbXref> .
_:genid690049 <http://www.w3.org/2002/07/owl#annotatedTarget> "UMLS:C0028429"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid690049 <http://www.geneontology.org/formats/oboInOwl#source> "ncithesaurus:Nose"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid690050 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .
_:genid690050 <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/UBERON_0000004> .
_:genid690050 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://www.geneontology.org/formats/oboInOwl#hasExactSynonym> .
_:genid690050 <http://www.w3.org/2002/07/owl#annotatedTarget> "nasal sac"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid690050 <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "ZFA:0000047"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid690050 <http://www.geneontology.org/formats/oboInOwl#hasSynonymType> <http://purl.obolibrary.org/obo/uberon/core#SENSU> .
_:genid690051 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .
_:genid690051 <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/UBERON_0000004> .
_:genid690051 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://www.geneontology.org/formats/oboInOwl#hasExactSynonym> .
_:genid690051 <http://www.w3.org/2002/07/owl#annotatedTarget> "nose"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid690051 <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "MA:0000281"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid690051 <http://www.geneontology.org/formats/oboInOwl#hasSynonymType> <http://purl.obolibrary.org/obo/uberon/core#HUMAN_PREFERRED> .
_:genid690052 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .
_:genid690052 <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/UBERON_0000004> .
_:genid690052 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://www.geneontology.org/formats/oboInOwl#hasExactSynonym> .
_:genid690052 <http://www.w3.org/2002/07/owl#annotatedTarget> "peripheral olfactory organ"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid690052 <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "ZFA:0000047"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid690053 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .
_:genid690053 <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/UBERON_0000004> .
_:genid690053 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://www.geneontology.org/formats/oboInOwl#hasRelatedSynonym> .
_:genid690053 <http://www.w3.org/2002/07/owl#annotatedTarget> "nasus"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid690053 <http://www.geneontology.org/formats/oboInOwl#hasDbXref> <http://en.wikipedia.org/wiki/Nose> .
_:genid690053 <http://www.geneontology.org/formats/oboInOwl#hasSynonymType> <http://purl.obolibrary.org/obo/uberon/core#LATIN> .
_:genid690054 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .
_:genid690054 <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/UBERON_0000004> .
_:genid690054 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://www.geneontology.org/formats/oboInOwl#hasRelatedSynonym> .
_:genid690054 <http://www.w3.org/2002/07/owl#annotatedTarget> "olfactory apparatus"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid690054 <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "UBERON:cjm"^^<http://www.w3.org/2001/XMLSchema#string> .
#'''

        def cl_lines = '''# http://purl.obolibrary.org/obo/UBERON_0000004
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Class> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://purl.obolibrary.org/obo/UBERON_0002268> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://purl.obolibrary.org/obo/UBERON_0004121> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://purl.obolibrary.org/obo/UBERON_0010314> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid11989 .
_:genid11989 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid11989 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/BFO_0000050> .
_:genid11989 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/UBERON_0001456> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid11990 .
_:genid11990 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid11990 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/BFO_0000051> .
_:genid11990 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/UBERON_0006813> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid11991 .
_:genid11991 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid11991 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/RO_0002202> .
_:genid11991 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/UBERON_0003050> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://purl.obolibrary.org/obo/IAO_0000115> "The olfactory organ of vertebrates, consisting of nares, olfactory epithelia and the structures and skeletal framework of the nasal cavity."^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasExactSynonym> "nasal sac"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasExactSynonym> "nose"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasExactSynonym> "peripheral olfactory organ"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasRelatedSynonym> "nasus"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasRelatedSynonym> "olfactory apparatus"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.geneontology.org/formats/oboInOwl#hasRelatedSynonym> "proboscis"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/UBERON_0000004> <http://www.w3.org/2000/01/rdf-schema#label> "nose"^^<http://www.w3.org/2001/XMLSchema#string> .
#'''

        def hp_inputStream = new ByteArrayInputStream(hp_lines.getBytes())
        def cl_inputStream = new ByteArrayInputStream(cl_lines.getBytes())
        def uberon_inputStream = new ByteArrayInputStream(uberon_lines.getBytes())
        def go_plus_inputStream = new ByteArrayInputStream(go_plus_lines.getBytes())
        def nbo_inputStream = new ByteArrayInputStream(nbo_lines.getBytes())
        def converter = new BNodeConverter()

        when:
        Map<String, String> hp_bnodeToUriMap = converter.populateBnodeToUriMap(hp_inputStream)
        Map<String, String> cl_bnodeToUriMap = converter.populateBnodeToUriMap(cl_inputStream)
        Map<String, String> uberon_bnodeToUriMap = converter.populateBnodeToUriMap(uberon_inputStream)
        Map<String, String> go_plus_bnodeToUriMap = converter.populateBnodeToUriMap(go_plus_inputStream)
        Map<String, String> nbo_bnodeToUriMap = converter.populateBnodeToUriMap(nbo_inputStream)

        def updated_hp_lines = []
        def updated_cl_lines = []
        def updated_uberon_lines = []
        def updated_go_plus_lines = []
        def updated_nbo_lines = []
        hp_lines.split("\\n").each { line -> updated_hp_lines.add(converter.updateGenIdsInLine(line, hp_bnodeToUriMap))}
        cl_lines.split("\\n").each { line -> updated_cl_lines.add(converter.updateGenIdsInLine(line, cl_bnodeToUriMap))}
        uberon_lines.split("\\n").each { line -> updated_uberon_lines.add(converter.updateGenIdsInLine(line, uberon_bnodeToUriMap))}
        go_plus_lines.split("\\n").each { line -> updated_go_plus_lines.add(converter.updateGenIdsInLine(line, go_plus_bnodeToUriMap))}
        nbo_lines.split("\\n").each { line -> updated_nbo_lines.add(converter.updateGenIdsInLine(line, nbo_bnodeToUriMap))}

        then:
        updated_hp_lines.each {line ->
//            println "HP LINE: "  + line
            assert updated_uberon_lines.contains(line)
            assert updated_nbo_lines.contains(line)
            assert updated_cl_lines.contains(line)
            assert updated_go_plus_lines.contains(line)
        }

    }




    def "testing chebi vs. nbo assertions to be equal"() {
        given:
        def chebi_lines = '''# http://purl.obolibrary.org/obo/CHEBI_50611
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Class> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://purl.obolibrary.org/obo/CHEBI_22888> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://purl.obolibrary.org/obo/CHEBI_50759> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid218001 .
_:genid218001 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid218001 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/RO_0000087> .
_:genid218001 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/CHEBI_75772> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid218002 .
_:genid218002 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid218002 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/chebi#has_functional_parent> .
_:genid218002 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/CHEBI_50607> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://purl.obolibrary.org/obo/chebi/charge> "0"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://purl.obolibrary.org/obo/chebi/formula> "C20H20N2O8"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://purl.obolibrary.org/obo/chebi/inchi> "InChI=1S/C20H20N2O8/c23-9-21-15(19(27)28)7-11-1-3-17(25)13(5-11)14-6-12(2-4-18(14)26)8-16(20(29)30)22-10-24/h1-6,9-10,15-16,25-26H,7-8H2,(H,21,23)(H,22,24)(H,27,28)(H,29,30)"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://purl.obolibrary.org/obo/chebi/inchikey> "OUNKRBSXIMLJRR-UHFFFAOYSA-N"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://purl.obolibrary.org/obo/chebi/mass> "416.38148"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://purl.obolibrary.org/obo/chebi/monoisotopicmass> "416.122"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://purl.obolibrary.org/obo/chebi/smiles> "[H]C(=O)NC(Cc1ccc(O)c(c1)-c1cc(CC(NC([H])=O)C(O)=O)ccc1O)C(O)=O"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.geneontology.org/formats/oboInOwl#hasExactSynonym> "3,3'-(6,6'-dihydroxybiphenyl-3,3'-diyl)bis[2-(formylamino)propanoic acid]"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.geneontology.org/formats/oboInOwl#hasOBONamespace> "chebi_ontology"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.geneontology.org/formats/oboInOwl#hasRelatedSynonym> "N,N'-bisformyl dityrosine"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.geneontology.org/formats/oboInOwl#id> "CHEBI:50611"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.geneontology.org/formats/oboInOwl#inSubset> <http://purl.obolibrary.org/obo/chebi#3_STAR> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.w3.org/2000/01/rdf-schema#label> "N,N'-diformyldityrosine"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid218003 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .
_:genid218003 <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/CHEBI_50611> .
_:genid218003 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://www.geneontology.org/formats/oboInOwl#hasExactSynonym> .
_:genid218003 <http://www.w3.org/2002/07/owl#annotatedTarget> "3,3'-(6,6'-dihydroxybiphenyl-3,3'-diyl)bis[2-(formylamino)propanoic acid]"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid218003 <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "IUPAC"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid218003 <http://www.geneontology.org/formats/oboInOwl#hasSynonymType> <http://purl.obolibrary.org/obo/chebi#IUPAC_NAME> .
_:genid218004 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .
_:genid218004 <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/CHEBI_50611> .
_:genid218004 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://www.geneontology.org/formats/oboInOwl#hasRelatedSynonym> .
_:genid218004 <http://www.w3.org/2002/07/owl#annotatedTarget> "N,N'-bisformyl dityrosine"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid218004 <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "ChEBI"^^<http://www.w3.org/2001/XMLSchema#string> .
#'''

        def nbo_lines = '''# http://purl.obolibrary.org/obo/CHEBI_50611
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Class> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://purl.obolibrary.org/obo/CHEBI_22888> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://purl.obolibrary.org/obo/CHEBI_50759> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid218193 .
_:genid218193 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid218193 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/RO_0000087> .
_:genid218193 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/CHEBI_75772> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid218194 .
_:genid218194 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid218194 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/chebi#has_functional_parent> .
_:genid218194 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/CHEBI_50607> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://purl.obolibrary.org/obo/chebi/charge> "0"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://purl.obolibrary.org/obo/chebi/formula> "C20H20N2O8"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://purl.obolibrary.org/obo/chebi/inchi> "InChI=1S/C20H20N2O8/c23-9-21-15(19(27)28)7-11-1-3-17(25)13(5-11)14-6-12(2-4-18(14)26)8-16(20(29)30)22-10-24/h1-6,9-10,15-16,25-26H,7-8H2,(H,21,23)(H,22,24)(H,27,28)(H,29,30)"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://purl.obolibrary.org/obo/chebi/inchikey> "OUNKRBSXIMLJRR-UHFFFAOYSA-N"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://purl.obolibrary.org/obo/chebi/mass> "416.38148"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://purl.obolibrary.org/obo/chebi/monoisotopicmass> "416.122"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://purl.obolibrary.org/obo/chebi/smiles> "[H]C(=O)NC(Cc1ccc(O)c(c1)-c1cc(CC(NC([H])=O)C(O)=O)ccc1O)C(O)=O"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.geneontology.org/formats/oboInOwl#hasExactSynonym> "3,3'-(6,6'-dihydroxybiphenyl-3,3'-diyl)bis[2-(formylamino)propanoic acid]"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.geneontology.org/formats/oboInOwl#hasOBONamespace> "chebi_ontology"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.geneontology.org/formats/oboInOwl#hasRelatedSynonym> "N,N'-bisformyl dityrosine"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.geneontology.org/formats/oboInOwl#id> "CHEBI:50611"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.geneontology.org/formats/oboInOwl#inSubset> <http://purl.obolibrary.org/obo/chebi#3_STAR> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.w3.org/2000/01/rdf-schema#label> "N,N'-diformyldityrosine"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid218195 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .
_:genid218195 <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/CHEBI_50611> .
_:genid218195 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://www.geneontology.org/formats/oboInOwl#hasExactSynonym> .
_:genid218195 <http://www.w3.org/2002/07/owl#annotatedTarget> "3,3'-(6,6'-dihydroxybiphenyl-3,3'-diyl)bis[2-(formylamino)propanoic acid]"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid218195 <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "IUPAC"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid218195 <http://www.geneontology.org/formats/oboInOwl#hasSynonymType> <http://purl.obolibrary.org/obo/chebi#IUPAC_NAME> .
_:genid218196 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .
_:genid218196 <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/CHEBI_50611> .
_:genid218196 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://www.geneontology.org/formats/oboInOwl#hasRelatedSynonym> .
_:genid218196 <http://www.w3.org/2002/07/owl#annotatedTarget> "N,N'-bisformyl dityrosine"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid218196 <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "ChEBI"^^<http://www.w3.org/2001/XMLSchema#string> .
#'''

        def chebi_inputStream = new ByteArrayInputStream(chebi_lines.getBytes())
        def nbo_inputStream = new ByteArrayInputStream(nbo_lines.getBytes())
        def converter = new BNodeConverter()

        when:
        Map<String, String> chebi_bnodeToUriMap = converter.populateBnodeToUriMap(chebi_inputStream)
        Map<String, String> nbo_bnodeToUriMap = converter.populateBnodeToUriMap(nbo_inputStream)

        def updated_chebi_lines = []
        def updated_nbo_lines = []
        chebi_lines.split("\\n").each { line -> updated_chebi_lines.add(converter.updateGenIdsInLine(line, chebi_bnodeToUriMap))}
        nbo_lines.split("\\n").each { line -> updated_nbo_lines.add(converter.updateGenIdsInLine(line, nbo_bnodeToUriMap))}


        then:
        updated_chebi_lines == updated_nbo_lines

    }

    def "testing creation of gen ID to normalized ID map"() {
        given:
        def lines = [
"_:genid335 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#AllDifferent> .",
"_:genid335 <http://www.w3.org/2002/07/owl#distinctMembers> _:genid343 .",
"_:genid343 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .",
"_:genid343 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000120> .",
"_:genid343 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> _:genid342 .",
"_:genid342 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .",
"_:genid342 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000121> .",
"_:genid342 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> _:genid341 .",
"_:genid341 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .",
"_:genid341 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000122> .",
"_:genid341 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> _:genid340 .",
"_:genid340 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .",
"_:genid340 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000123> .",
"_:genid340 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> _:genid339 .",
"_:genid339 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .",
"_:genid339 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000124> .",
"_:genid339 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> _:genid338 .",
"_:genid338 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .",
"_:genid338 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000125> .",
"_:genid338 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> _:genid337 .",
"_:genid337 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .",
"_:genid337 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000423> .",
"_:genid337 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> _:genid336 .",
"_:genid336 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .",
"_:genid336 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000428> .",
"_:genid336 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> <http://www.w3.org/1999/02/22-rdf-syntax-ns#nil> ."
]
        def converter = new BNodeConverter()
        when:
        Map<String, String> map = converter.createGenIdToNormIdMap(lines)

        then:
        map.get("_:genid335 ") == "[ID1] "
        map.get("_:genid343 ") == "[ID2] "
        map.get("_:genid342 ") == "[ID9] "
        map.get("_:genid341 ") == "[ID8] "
        map.get("_:genid340 ") == "[ID7] "
        map.get("_:genid339 ") == "[ID6] "
        map.get("_:genid338 ") == "[ID5] "
        map.get("_:genid337 ") == "[ID4] "
        map.get("_:genid336 ") == "[ID3] "
    }



    def computeHash(List<String> lines, boolean linesAlreadySorted) {
        if (!linesAlreadySorted) {
            Collections.sort(lines)
        }
        StringBuilder builder = new StringBuilder()
        lines.each { line -> builder.append(line) }
//        println "&&&&&&&& " + builder.toString()
        return DigestUtils.sha256Hex(builder.toString())
    }


    def "test bnode URI generation -- owl:AllDifferent"() {
        given:
        def converter = new BNodeConverter()
        def lines = '''
_:genid335 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#AllDifferent> .
_:genid335 <http://www.w3.org/2002/07/owl#distinctMembers> _:genid343 .
_:genid343 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .
_:genid343 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000120> .
_:genid343 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> _:genid342 .
_:genid342 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .
_:genid342 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000121> .
_:genid342 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> _:genid341 .
_:genid341 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .
_:genid341 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000122> .
_:genid341 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> _:genid340 .
_:genid340 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .
_:genid340 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000123> .
_:genid340 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> _:genid339 .
_:genid339 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .
_:genid339 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000124> .
_:genid339 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> _:genid338 .
_:genid338 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .
_:genid338 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000125> .
_:genid338 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> _:genid337 .
_:genid337 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .
_:genid337 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000423> .
_:genid337 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> _:genid336 .
_:genid336 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .
_:genid336 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000428> .
_:genid336 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> <http://www.w3.org/1999/02/22-rdf-syntax-ns#nil> .
'''
        def inputStream = new ByteArrayInputStream(lines.getBytes());

        when:
        Map<String, String> bnodeToUriMap = converter.populateBnodeToUriMap(inputStream)

        def nodeLines = ["[ID1] <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#AllDifferent> .",
                         "[ID1] <http://www.w3.org/2002/07/owl#distinctMembers> [ID2] ."]

        def neighborLines = ["[ID1] <http://www.w3.org/2002/07/owl#distinctMembers> [ID2] .",
                             "[ID2] <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .",
                             "[ID2] <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000120> .",
                             "[ID2] <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> [ID9] .",
                             "[ID2] <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> [ID9] .",
                             "[ID9] <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .",
                             "[ID9] <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000121> .",
                             "[ID9] <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> [ID8] .",
                             "[ID9] <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> [ID8] .",
                             "[ID8] <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .",
                             "[ID8] <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000122> .",
                             "[ID8] <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> [ID7] .",
                             "[ID8] <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> [ID7] .",
                             "[ID7] <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .",
                             "[ID7] <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000123> .",
                             "[ID7] <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> [ID6] .",
                             "[ID7] <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> [ID6] .",
                             "[ID6] <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .",
                             "[ID6] <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000124> .",
                             "[ID6] <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> [ID5] .",
                             "[ID6] <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> [ID5] .",
                             "[ID5] <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .",
                             "[ID5] <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000125> .",
                             "[ID5] <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> [ID4] .",
                             "[ID5] <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> [ID4] .",
                             "[ID4] <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .",
                             "[ID4] <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000423> .",
                             "[ID4] <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> [ID3] .",
                             "[ID4] <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> [ID3] .",
                             "[ID3] <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .",
                             "[ID3] <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000428> .",
                             "[ID3] <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> <http://www.w3.org/1999/02/22-rdf-syntax-ns#nil> ."]

        Collections.sort(nodeLines)
        Collections.sort(neighborLines)
        nodeLines.addAll(neighborLines)

        def nodeLines_last = ["[ID4] <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> [ID3] .",
                              "[ID3] <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .",
                              "[ID3] <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000428> .",
                              "[ID3] <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> <http://www.w3.org/1999/02/22-rdf-syntax-ns#nil> ."]

        def neighborLines_last = ["[ID1] <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#AllDifferent> .",
                                  "[ID1] <http://www.w3.org/2002/07/owl#distinctMembers> [ID2] .",
                                  "[ID1] <http://www.w3.org/2002/07/owl#distinctMembers> [ID2] .",
                                  "[ID2] <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .",
                                  "[ID2] <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000120> .",
                                  "[ID2] <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> [ID9] .",
                                  "[ID2] <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> [ID9] .",
                                  "[ID9] <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .",
                                  "[ID9] <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000121> .",
                                  "[ID9] <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> [ID8] .",
                                  "[ID9] <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> [ID8] .",
                                  "[ID8] <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .",
                                  "[ID8] <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000122> .",
                                  "[ID8] <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> [ID7] .",
                                  "[ID8] <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> [ID7] .",
                                  "[ID7] <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .",
                                  "[ID7] <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000123> .",
                                  "[ID7] <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> [ID6] .",
                                  "[ID7] <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> [ID6] .",
                                  "[ID6] <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .",
                                  "[ID6] <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000124> .",
                                  "[ID6] <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> [ID5] .",
                                  "[ID6] <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> [ID5] .",
                                  "[ID5] <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .",
                                  "[ID5] <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000125> .",
                                  "[ID5] <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> [ID4] .",
                                  "[ID5] <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> [ID4] .",
                                  "[ID4] <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .",
                                  "[ID4] <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000423> .",
                                  "[ID4] <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> [ID3] ."]

        Collections.sort(nodeLines_last)
        Collections.sort(neighborLines_last)
        nodeLines_last.addAll(neighborLines_last)


        then:
        bnodeToUriMap.size() == 9
        bnodeToUriMap.get("_:genid335 ") == "<http://BN_" + computeHash(nodeLines, true) + "> "
        bnodeToUriMap.get("_:genid336 ") == "<http://BN_" + computeHash(nodeLines_last, true) + "> "

    }


// ++++++++ populateBnodeToUriMap ++++++++
    def "test bnode uri generation -- restrictions"() {
        given:
        def converter = new BNodeConverter()
        def lines =
                '''# http://purl.obolibrary.org/obo/CHEBI_50611 <http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Class> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://purl.obolibrary.org/obo/CHEBI_22888> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://purl.obolibrary.org/obo/CHEBI_50759> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid218001 .
_:genid218001 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid218001 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/RO_0000087> .
_:genid218001 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/CHEBI_75772> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid218002 .
_:genid218002 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid218002 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/chebi#has_functional_parent> .
_:genid218002 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/CHEBI_50607> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://purl.obolibrary.org/obo/chebi/charge> "0"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://purl.obolibrary.org/obo/chebi/formula> "C20H20N2O8"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://purl.obolibrary.org/obo/chebi/inchi> "InChI=1S/C20H20N2O8/c23-9-21-15(19(27)28)7-11-1-3-17(25)13(5-11)14-6-12(2-4-18(14)26)8-16(20(29)30)22-10-24/h1-6,9-10,15-16,25-26H,7-8H2,(H,21,23)(H,22,24)(H,27,28)(H,29,30)"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://purl.obolibrary.org/obo/chebi/inchikey> "OUNKRBSXIMLJRR-UHFFFAOYSA-N"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://purl.obolibrary.org/obo/chebi/mass> "416.38148"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://purl.obolibrary.org/obo/chebi/monoisotopicmass> "416.122"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://purl.obolibrary.org/obo/chebi/smiles> "[H]C(=O)NC(Cc1ccc(O)c(c1)-c1cc(CC(NC([H])=O)C(O)=O)ccc1O)C(O)=O"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.geneontology.org/formats/oboInOwl#hasExactSynonym> "3,3'-(6,6'-dihydroxybiphenyl-3,3'-diyl)bis[2-(formylamino)propanoic acid]"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.geneontology.org/formats/oboInOwl#hasOBONamespace> "chebi_ontology"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.geneontology.org/formats/oboInOwl#hasRelatedSynonym> "N,N'-bisformyl dityrosine"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.geneontology.org/formats/oboInOwl#id> "CHEBI:50611"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.geneontology.org/formats/oboInOwl#inSubset> <http://purl.obolibrary.org/obo/chebi#3_STAR> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.w3.org/2000/01/rdf-schema#label> "N,N'-diformyldityrosine"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid218003 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .
_:genid218003 <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/CHEBI_50611> .
_:genid218003 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://www.geneontology.org/formats/oboInOwl#hasExactSynonym> .
_:genid218003 <http://www.w3.org/2002/07/owl#annotatedTarget> "3,3'-(6,6'-dihydroxybiphenyl-3,3'-diyl)bis[2-(formylamino)propanoic acid]"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid218003 <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "IUPAC"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid218003 <http://www.geneontology.org/formats/oboInOwl#hasSynonymType> <http://purl.obolibrary.org/obo/chebi#IUPAC_NAME> .
_:genid218004 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .
_:genid218004 <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/CHEBI_50611> .
_:genid218004 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://www.geneontology.org/formats/oboInOwl#hasRelatedSynonym> .
_:genid218004 <http://www.w3.org/2002/07/owl#annotatedTarget> "N,N'-bisformyl dityrosine"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid218004 <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "ChEBI"^^<http://www.w3.org/2001/XMLSchema#string> .
#
'''
        def inputStream = new ByteArrayInputStream(lines.getBytes())

        when:
        Map<String, String> bnodeToUriMap = converter.populateBnodeToUriMap(inputStream)

        then:
        bnodeToUriMap.size() == 4
        bnodeToUriMap.get("_:genid218001 ") == "<http://BN_" + computeHash(["<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.w3.org/2000/01/rdf-schema#subClassOf> [ID1] .",
                                                         "[ID1] <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .",
                                                         "[ID1] <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/RO_0000087> .",
                                                         "[ID1] <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/CHEBI_75772> ."], false)  + "> "


        bnodeToUriMap.get("_:genid218002 ") == "<http://BN_" + computeHash(["<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.w3.org/2000/01/rdf-schema#subClassOf> [ID1] .",
                                                         "[ID1] <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .",
                                                         "[ID1] <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/chebi#has_functional_parent> .",
                                                         "[ID1] <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/CHEBI_50607> ."], false)+ "> "
        bnodeToUriMap.get("_:genid218003 ") == "<http://BN_" + computeHash(["[ID1] <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .",
                                                         "[ID1] <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/CHEBI_50611> .",
                                                         "[ID1] <http://www.w3.org/2002/07/owl#annotatedProperty> <http://www.geneontology.org/formats/oboInOwl#hasExactSynonym> .",
                                                         "[ID1] <http://www.w3.org/2002/07/owl#annotatedTarget> \"3,3'-(6,6'-dihydroxybiphenyl-3,3'-diyl)bis[2-(formylamino)propanoic acid]\"^^<http://www.w3.org/2001/XMLSchema#string> .",
                                                         "[ID1] <http://www.geneontology.org/formats/oboInOwl#hasDbXref> \"IUPAC\"^^<http://www.w3.org/2001/XMLSchema#string> .",
                                                         "[ID1] <http://www.geneontology.org/formats/oboInOwl#hasSynonymType> <http://purl.obolibrary.org/obo/chebi#IUPAC_NAME> ."], false)+ "> "
        bnodeToUriMap.get("_:genid218004 ") == "<http://BN_" + computeHash(["[ID1] <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .",
                                                         "[ID1] <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/CHEBI_50611> .",
                                                         "[ID1] <http://www.w3.org/2002/07/owl#annotatedProperty> <http://www.geneontology.org/formats/oboInOwl#hasRelatedSynonym> .",
                                                         "[ID1] <http://www.w3.org/2002/07/owl#annotatedTarget> \"N,N'-bisformyl dityrosine\"^^<http://www.w3.org/2001/XMLSchema#string> .",
                                                         "[ID1] <http://www.geneontology.org/formats/oboInOwl#hasDbXref> \"ChEBI\"^^<http://www.w3.org/2001/XMLSchema#string> ."], false)+ "> "

    }

// ++++++++ processBnodeLines ++++++++
    def "should return two maps -- no inter-bnode linkages"() {
        given:
        def converter = new BNodeConverter()
        def lines =
                '''# http://purl.obolibrary.org/obo/CHEBI_50611 <http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Class> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://purl.obolibrary.org/obo/CHEBI_22888> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://purl.obolibrary.org/obo/CHEBI_50759> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid218001 .
_:genid218001 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid218001 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/RO_0000087> .
_:genid218001 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/CHEBI_75772> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid218002 .
_:genid218002 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid218002 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/chebi#has_functional_parent> .
_:genid218002 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/CHEBI_50607> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://purl.obolibrary.org/obo/chebi/charge> "0"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://purl.obolibrary.org/obo/chebi/formula> "C20H20N2O8"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://purl.obolibrary.org/obo/chebi/inchi> "InChI=1S/C20H20N2O8/c23-9-21-15(19(27)28)7-11-1-3-17(25)13(5-11)14-6-12(2-4-18(14)26)8-16(20(29)30)22-10-24/h1-6,9-10,15-16,25-26H,7-8H2,(H,21,23)(H,22,24)(H,27,28)(H,29,30)"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://purl.obolibrary.org/obo/chebi/inchikey> "OUNKRBSXIMLJRR-UHFFFAOYSA-N"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://purl.obolibrary.org/obo/chebi/mass> "416.38148"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://purl.obolibrary.org/obo/chebi/monoisotopicmass> "416.122"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://purl.obolibrary.org/obo/chebi/smiles> "[H]C(=O)NC(Cc1ccc(O)c(c1)-c1cc(CC(NC([H])=O)C(O)=O)ccc1O)C(O)=O"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.geneontology.org/formats/oboInOwl#hasExactSynonym> "3,3'-(6,6'-dihydroxybiphenyl-3,3'-diyl)bis[2-(formylamino)propanoic acid]"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.geneontology.org/formats/oboInOwl#hasOBONamespace> "chebi_ontology"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.geneontology.org/formats/oboInOwl#hasRelatedSynonym> "N,N'-bisformyl dityrosine"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.geneontology.org/formats/oboInOwl#id> "CHEBI:50611"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.geneontology.org/formats/oboInOwl#inSubset> <http://purl.obolibrary.org/obo/chebi#3_STAR> .
<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.w3.org/2000/01/rdf-schema#label> "N,N'-diformyldityrosine"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid218003 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .
_:genid218003 <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/CHEBI_50611> .
_:genid218003 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://www.geneontology.org/formats/oboInOwl#hasExactSynonym> .
_:genid218003 <http://www.w3.org/2002/07/owl#annotatedTarget> "3,3'-(6,6'-dihydroxybiphenyl-3,3'-diyl)bis[2-(formylamino)propanoic acid]"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid218003 <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "IUPAC"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid218003 <http://www.geneontology.org/formats/oboInOwl#hasSynonymType> <http://purl.obolibrary.org/obo/chebi#IUPAC_NAME> .
_:genid218004 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .
_:genid218004 <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/CHEBI_50611> .
_:genid218004 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://www.geneontology.org/formats/oboInOwl#hasRelatedSynonym> .
_:genid218004 <http://www.w3.org/2002/07/owl#annotatedTarget> "N,N'-bisformyl dityrosine"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid218004 <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "ChEBI"^^<http://www.w3.org/2001/XMLSchema#string> .
#
'''
        def inputStream = new ByteArrayInputStream(lines.getBytes());

        when:
        def (Map<String, Set<String>> bnodeToLineMap,
        Map<String, Set<String>>      bnodeConnectionsMap) = converter.processBnodeLines(inputStream)

        then:
        bnodeToLineMap.size() == 4
        bnodeToLineMap.get("_:genid218001 ").size() == 4
        bnodeToLineMap.get("_:genid218002 ").size() == 4
        bnodeToLineMap.get("_:genid218003 ").size() == 6
        bnodeToLineMap.get("_:genid218004 ").size() == 5
        bnodeToLineMap.get("_:genid218001 ") == ["<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid218001 .",
                                              "_:genid218001 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .",
                                              "_:genid218001 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/RO_0000087> .",
                                              "_:genid218001 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/CHEBI_75772> ."].toSet()
        bnodeToLineMap.get("_:genid218002 ") == ["<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid218002 .",
                                              "_:genid218002 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .",
                                              "_:genid218002 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/chebi#has_functional_parent> .",
                                              "_:genid218002 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/CHEBI_50607> ."].toSet()
        bnodeToLineMap.get("_:genid218003 ") == ["_:genid218003 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .",
                                              "_:genid218003 <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/CHEBI_50611> .",
                                              "_:genid218003 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://www.geneontology.org/formats/oboInOwl#hasExactSynonym> .",
                                              "_:genid218003 <http://www.w3.org/2002/07/owl#annotatedTarget> \"3,3'-(6,6'-dihydroxybiphenyl-3,3'-diyl)bis[2-(formylamino)propanoic acid]\"^^<http://www.w3.org/2001/XMLSchema#string> .",
                                              "_:genid218003 <http://www.geneontology.org/formats/oboInOwl#hasDbXref> \"IUPAC\"^^<http://www.w3.org/2001/XMLSchema#string> .",
                                              "_:genid218003 <http://www.geneontology.org/formats/oboInOwl#hasSynonymType> <http://purl.obolibrary.org/obo/chebi#IUPAC_NAME> ."].toSet()
        bnodeToLineMap.get("_:genid218004 ") == ["_:genid218004 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .",
                                              "_:genid218004 <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/CHEBI_50611> .",
                                              "_:genid218004 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://www.geneontology.org/formats/oboInOwl#hasRelatedSynonym> .",
                                              "_:genid218004 <http://www.w3.org/2002/07/owl#annotatedTarget> \"N,N'-bisformyl dityrosine\"^^<http://www.w3.org/2001/XMLSchema#string> .",
                                              "_:genid218004 <http://www.geneontology.org/formats/oboInOwl#hasDbXref> \"ChEBI\"^^<http://www.w3.org/2001/XMLSchema#string> ."].toSet()

        bnodeConnectionsMap.size() == 4
        bnodeConnectionsMap.get("_:genid218001 ") == ["_:genid218001 "].toSet()
        bnodeConnectionsMap.get("_:genid218002 ") == ["_:genid218002 "].toSet()
        bnodeConnectionsMap.get("_:genid218003 ") == ["_:genid218003 "].toSet()
        bnodeConnectionsMap.get("_:genid218004 ") == ["_:genid218004 "].toSet()

    }

    // ++++++++ processBnodeLines ++++++++
    def "should return two maps - with inter-node linkages"() {
        given:
        def converter = new BNodeConverter()
        def lines = '''
_:genid335 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#AllDifferent> .
_:genid335 <http://www.w3.org/2002/07/owl#distinctMembers> _:genid343 .
_:genid343 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .
_:genid343 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000120> .
_:genid343 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> _:genid342 .
_:genid342 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .
_:genid342 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000121> .
_:genid342 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> _:genid341 .
_:genid341 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .
_:genid341 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000122> .
_:genid341 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> _:genid340 .
_:genid340 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .
_:genid340 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000123> .
_:genid340 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> _:genid339 .
_:genid339 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .
_:genid339 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000124> .
_:genid339 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> _:genid338 .
_:genid338 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .
_:genid338 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000125> .
_:genid338 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> _:genid337 .
_:genid337 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .
_:genid337 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000423> .
_:genid337 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> _:genid336 .
_:genid336 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .
_:genid336 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000428> .
_:genid336 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> <http://www.w3.org/1999/02/22-rdf-syntax-ns#nil> .
'''
        def inputStream = new ByteArrayInputStream(lines.getBytes());

        when:
        def (Map<String, Set<String>> bnodeToLineMap,
        Map<String, Set<String>>      bnodeConnectionsMap) = converter.processBnodeLines(inputStream)

        then:
        bnodeToLineMap.size() == 9
        bnodeToLineMap.get("_:genid335 ").size() == 2
        bnodeToLineMap.get("_:genid343 ").size() == 4
        bnodeToLineMap.get("_:genid342 ").size() == 4
        bnodeToLineMap.get("_:genid341 ").size() == 4
        bnodeToLineMap.get("_:genid340 ").size() == 4
        bnodeToLineMap.get("_:genid339 ").size() == 4
        bnodeToLineMap.get("_:genid338 ").size() == 4
        bnodeToLineMap.get("_:genid337 ").size() == 4
        bnodeToLineMap.get("_:genid336 ").size() == 4

        bnodeConnectionsMap.size() == 9
        bnodeConnectionsMap.get("_:genid335 ") == ["_:genid335 ", "_:genid343 "].toSet()
        bnodeConnectionsMap.get("_:genid343 ") == ["_:genid343 ", "_:genid342 ", "_:genid335 "].toSet()
        bnodeConnectionsMap.get("_:genid342 ") == ["_:genid342 ", "_:genid341 ", "_:genid343 "].toSet()
        bnodeConnectionsMap.get("_:genid341 ") == ["_:genid341 ", "_:genid340 ", "_:genid342 "].toSet()
        bnodeConnectionsMap.get("_:genid340 ") == ["_:genid340 ", "_:genid339 ", "_:genid341 "].toSet()
        bnodeConnectionsMap.get("_:genid339 ") == ["_:genid339 ", "_:genid340 ", "_:genid338 "].toSet()
        bnodeConnectionsMap.get("_:genid338 ") == ["_:genid338 ", "_:genid337 ", "_:genid339 "].toSet()
        bnodeConnectionsMap.get("_:genid337 ") == ["_:genid337 ", "_:genid336 ", "_:genid338 "].toSet()
        bnodeConnectionsMap.get("_:genid336 ") == ["_:genid336 ", "_:genid337 "].toSet()

    }

    // ++++++++ createNodeNeighborhoodMap ++++++++
    def "should create 2 node neighborhoods [a,b,c,d,z] and [i,j,k,l]"() {
        given:
        def nodeConnectionsMap = new HashMap<String, Set<String>>()
        nodeConnectionsMap.put("a", ["b", "c"].toSet())
        nodeConnectionsMap.put("b", ["a"].toSet())
        nodeConnectionsMap.put("c", ["d", "a"].toSet())
        nodeConnectionsMap.put("d", ["c", "z"].toSet())
        nodeConnectionsMap.put("z", ["d"].toSet())

        nodeConnectionsMap.put("i", ["j", "k"].toSet())
        nodeConnectionsMap.put("j", ["i"].toSet())
        nodeConnectionsMap.put("k", ["l", "i"].toSet())
        nodeConnectionsMap.put("l", ["k"].toSet())

        def converter = new BNodeConverter()

        when:
        def map = converter.createNodeNeighborhoodMap(nodeConnectionsMap)

        then:
        map.size() == 9
        map.get("a") == ["a", "b", "c", "d", "z"].toSet()
        map.get("b") == ["a", "b", "c", "d", "z"].toSet()
        map.get("c") == ["a", "b", "c", "d", "z"].toSet()
        map.get("d") == ["a", "b", "c", "d", "z"].toSet()
        map.get("z") == ["a", "b", "c", "d", "z"].toSet()

        map.get("i") == ["i", "j", "k", "l"].toSet()
        map.get("j") == ["i", "j", "k", "l"].toSet()
        map.get("k") == ["i", "j", "k", "l"].toSet()
        map.get("l") == ["i", "j", "k", "l"].toSet()

    }

    // ++++++++ getNodeNeighborhood ++++++++
    def "node neighborhood should be a,b,c,d"() {
        given:
        def nodeConnectionsMap = new HashMap<String, Set<String>>()
        nodeConnectionsMap.put("a", ["b", "c"].toSet())
        nodeConnectionsMap.put("c", ["d"].toSet())
        def neighborhoodSet = new HashSet<String>()
        def converter = new BNodeConverter()

        when:
        converter.getNodeNeighborhood("a", nodeConnectionsMap, neighborhoodSet)

        then:
        neighborhoodSet.size() == 4
        neighborhoodSet == ["a", "b", "c", "d"].toSet()
    }

    // ++++++++ getNodeNeighborhood ++++++++
    def "node neighborhood should be a,b,c,d -- avoid cycle"() {
        given:
        def nodeConnectionsMap = new HashMap<String, Set<String>>()
        nodeConnectionsMap.put("a", ["b", "c"].toSet())
        nodeConnectionsMap.put("c", ["d", "a"].toSet())
        def neighborhoodSet = new HashSet<String>()
        def converter = new BNodeConverter()

        when:
        converter.getNodeNeighborhood("a", nodeConnectionsMap, neighborhoodSet)

        then:
        neighborhoodSet.size() == 4
        neighborhoodSet == ["a", "b", "c", "d"].toSet()
    }

    // ++++++++ getNodeNeighborhood ++++++++
    def "node neighborhood should be a,b,c,d,z -- shows need for cataloging bidirectional links"() {
        given:
        def nodeConnectionsMap = new HashMap<String, Set<String>>()
        nodeConnectionsMap.put("a", ["b", "c"].toSet())
        nodeConnectionsMap.put("b", ["a"].toSet())
        nodeConnectionsMap.put("c", ["d", "a"].toSet())
        nodeConnectionsMap.put("d", ["c", "z"].toSet())
        nodeConnectionsMap.put("z", ["d"].toSet())

        def neighborhoodSet = new HashSet<String>()
        def converter = new BNodeConverter()

        when:
        converter.getNodeNeighborhood("z", nodeConnectionsMap, neighborhoodSet)

        then:
        neighborhoodSet.size() == 5
        neighborhoodSet == ["a", "b", "c", "d", "z"].toSet()

    }

    // ++++++++ addToCollectionMap ++++++++
    def "adding a new key to a collection map should result in a new entry"() {
        given:
        def map = new HashMap<String, List<String>>()
        def converter = new BNodeConverter()

        when:
        converter.addToCollectionMap("a", "b", map)

        then:
        map.size() == 1
        map.get("a") == ["b"].toSet()
    }

    // ++++++++ addToCollectionMap ++++++++
    def "adding an existing key to a collection map should result in an update to the entry"() {
        given:
        def map = new HashMap<String, Set<String>>()
        map.put("a", ["b", "c"].toSet())
        def converter = new BNodeConverter()

        when:
        converter.addToCollectionMap("a", "d", map)

        then:
        map.size() == 1
        map.get("a") == ["b", "c", "d"].toSet()
    }

}





