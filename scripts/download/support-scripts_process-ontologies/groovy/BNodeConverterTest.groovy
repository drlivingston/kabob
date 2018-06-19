@Grab(group = 'org.spockframework', module = 'spock-core', version = '1.0-groovy-2.4', scope = 'test')
import spock.lang.Specification
@Grab(group = 'commons-codec', module = 'commons-codec', version = '1.11')
import org.apache.commons.codec.digest.DigestUtils
import spock.lang.Ignore
import spock.lang.IgnoreRest

class BNodeConverterSpecification extends Specification {

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
        bnodeToLineMap.get("_:genid218001 ").size() == 3
        bnodeToLineMap.get("_:genid218002 ").size() == 3
        bnodeToLineMap.get("_:genid218003 ").size() == 6
        bnodeToLineMap.get("_:genid218004 ").size() == 5
        bnodeToLineMap.get("_:genid218001 ") == ["_:genid218001 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .",
                                                 "_:genid218001 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/RO_0000087> .",
                                                 "_:genid218001 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/CHEBI_75772> ."].toSet()
        bnodeToLineMap.get("_:genid218002 ") == ["_:genid218002 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .",
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


    def "testing cl vs hp assertions to be equal"() {
        given:
        def hp_lines = '''<http://purl.obolibrary.org/obo/CL_0000122> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid299 .
_:genid299 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid299 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/RO_0002202> .
_:genid299 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/CL_0000030> .'''

        def cl_lines = '''<http://purl.obolibrary.org/obo/CL_0000125> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid622 .
_:genid622 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid622 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/RO_0002202> .
_:genid622 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/CL_0000030> .'''

        def hp_inputStream = new ByteArrayInputStream(hp_lines.getBytes())
        def cl_inputStream = new ByteArrayInputStream(cl_lines.getBytes())
        def converter = new BNodeConverter()

        when:
        Map<String, String> hp_bnodeToUriMap = converter.populateBnodeToUriMap(hp_inputStream)
        Map<String, String> cl_bnodeToUriMap = converter.populateBnodeToUriMap(cl_inputStream)

        then:
        assert hp_bnodeToUriMap.get("_:genid299 ") != null
        assert cl_bnodeToUriMap.get("_:genid622 ") != null
        assert hp_bnodeToUriMap.get("_:genid299 ") == cl_bnodeToUriMap.get("_:genid622 ")

    }


    // ++++++++ processBnodeLines ++++++++
    def "test bnode gen with inter-node linkages"() {
        given:
        def lines1 = '''
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

        def lines2 = '''
_:genid1335 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#AllDifferent> .
_:genid1335 <http://www.w3.org/2002/07/owl#distinctMembers> _:genid9343 .
_:genid9343 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .
_:genid9343 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000120> .
_:genid9343 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> _:genid2342 .
_:genid2342 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .
_:genid2342 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000121> .
_:genid2342 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> _:genid8341 .
_:genid8341 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .
_:genid8341 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000122> .
_:genid8341 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> _:genid3340 .
_:genid3340 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .
_:genid3340 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000123> .
_:genid3340 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> _:genid7339 .
_:genid7339 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .
_:genid7339 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000124> .
_:genid7339 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> _:genid4338 .
_:genid4338 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .
_:genid4338 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000125> .
_:genid4338 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> _:genid6337 .
_:genid6337 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .
_:genid6337 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000423> .
_:genid6337 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> _:genid5336 .
_:genid5336 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .
_:genid5336 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000428> .
_:genid5336 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> <http://www.w3.org/1999/02/22-rdf-syntax-ns#nil> .
'''
        def inputStream1 = new ByteArrayInputStream(lines1.getBytes());
        def inputStream2 = new ByteArrayInputStream(lines2.getBytes());
        def converter = new BNodeConverter()

        when:
        Map<String, String> bnodeToUriMap1 = converter.populateBnodeToUriMap(inputStream1)
        Map<String, String> bnodeToUriMap2 = converter.populateBnodeToUriMap(inputStream2)

        then:
        bnodeToUriMap1.size() == 9
        bnodeToUriMap1.get("_:genid335 ") != null
        bnodeToUriMap1.get("_:genid343 ") != null
        bnodeToUriMap1.get("_:genid342 ") != null
        bnodeToUriMap1.get("_:genid341 ") != null
        bnodeToUriMap1.get("_:genid340 ") != null
        bnodeToUriMap1.get("_:genid339 ") != null
        bnodeToUriMap1.get("_:genid338 ") != null
        bnodeToUriMap1.get("_:genid337 ") != null
        bnodeToUriMap1.get("_:genid336 ") != null

        bnodeToUriMap2.size() == 9
        bnodeToUriMap2.get("_:genid1335 ") != null
        bnodeToUriMap2.get("_:genid9343 ") != null
        bnodeToUriMap2.get("_:genid2342 ") != null
        bnodeToUriMap2.get("_:genid8341 ") != null
        bnodeToUriMap2.get("_:genid3340 ") != null
        bnodeToUriMap2.get("_:genid7339 ") != null
        bnodeToUriMap2.get("_:genid4338 ") != null
        bnodeToUriMap2.get("_:genid6337 ") != null
        bnodeToUriMap2.get("_:genid5336 ") != null


        bnodeToUriMap1.get("_:genid335 ") == bnodeToUriMap2.get("_:genid1335 ")
        bnodeToUriMap1.get("_:genid343 ") == bnodeToUriMap2.get("_:genid9343 ")
        bnodeToUriMap1.get("_:genid342 ") == bnodeToUriMap2.get("_:genid2342 ")
        bnodeToUriMap1.get("_:genid341 ") == bnodeToUriMap2.get("_:genid8341 ")
        bnodeToUriMap1.get("_:genid340 ") == bnodeToUriMap2.get("_:genid3340 ")
        bnodeToUriMap1.get("_:genid339 ") == bnodeToUriMap2.get("_:genid7339 ")
        bnodeToUriMap1.get("_:genid338 ") == bnodeToUriMap2.get("_:genid4338 ")
        bnodeToUriMap1.get("_:genid337 ") == bnodeToUriMap2.get("_:genid6337 ")
        bnodeToUriMap1.get("_:genid336 ") == bnodeToUriMap2.get("_:genid5336 ")

    }



    // ++++++++ processBnodeLines ++++++++
    def "test bnode gen with inter-node linkages - note order does matter"() {
        given:
        def lines1 = '''
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

        // the positions of IAO_0000121 and IAO_0000125 have been swapped in comparison to lines2 above
        def lines2 = '''
_:genid1335 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#AllDifferent> .
_:genid1335 <http://www.w3.org/2002/07/owl#distinctMembers> _:genid9343 .
_:genid9343 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .
_:genid9343 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000120> .
_:genid9343 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> _:genid2342 .
_:genid2342 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .
_:genid2342 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000125> .
_:genid2342 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> _:genid8341 .
_:genid8341 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .
_:genid8341 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000122> .
_:genid8341 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> _:genid3340 .
_:genid3340 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .
_:genid3340 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000123> .
_:genid3340 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> _:genid7339 .
_:genid7339 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .
_:genid7339 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000124> .
_:genid7339 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> _:genid4338 .
_:genid4338 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .
_:genid4338 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000121> .
_:genid4338 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> _:genid6337 .
_:genid6337 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .
_:genid6337 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000423> .
_:genid6337 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> _:genid5336 .
_:genid5336 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .
_:genid5336 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/IAO_0000428> .
_:genid5336 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> <http://www.w3.org/1999/02/22-rdf-syntax-ns#nil> .
'''
        def inputStream1 = new ByteArrayInputStream(lines1.getBytes());
        def inputStream2 = new ByteArrayInputStream(lines2.getBytes());
        def converter = new BNodeConverter()

        when:
        Map<String, String> bnodeToUriMap1 = converter.populateBnodeToUriMap(inputStream1)
        Map<String, String> bnodeToUriMap2 = converter.populateBnodeToUriMap(inputStream2)

        then:
        bnodeToUriMap1.size() == 9
        bnodeToUriMap1.get("_:genid335 ") != null
        bnodeToUriMap1.get("_:genid343 ") != null
        bnodeToUriMap1.get("_:genid342 ") != null
        bnodeToUriMap1.get("_:genid341 ") != null
        bnodeToUriMap1.get("_:genid340 ") != null
        bnodeToUriMap1.get("_:genid339 ") != null
        bnodeToUriMap1.get("_:genid338 ") != null
        bnodeToUriMap1.get("_:genid337 ") != null
        bnodeToUriMap1.get("_:genid336 ") != null

        bnodeToUriMap2.size() == 9
        bnodeToUriMap2.get("_:genid1335 ") != null
        bnodeToUriMap2.get("_:genid9343 ") != null
        bnodeToUriMap2.get("_:genid2342 ") != null
        bnodeToUriMap2.get("_:genid8341 ") != null
        bnodeToUriMap2.get("_:genid3340 ") != null
        bnodeToUriMap2.get("_:genid7339 ") != null
        bnodeToUriMap2.get("_:genid4338 ") != null
        bnodeToUriMap2.get("_:genid6337 ") != null
        bnodeToUriMap2.get("_:genid5336 ") != null


        // note that these are now different due to ordering (see note about position swap above)
        bnodeToUriMap1.get("_:genid335 ") != bnodeToUriMap2.get("_:genid1335 ")
        bnodeToUriMap1.get("_:genid343 ") != bnodeToUriMap2.get("_:genid9343 ")
        bnodeToUriMap1.get("_:genid342 ") != bnodeToUriMap2.get("_:genid2342 ")
        bnodeToUriMap1.get("_:genid341 ") != bnodeToUriMap2.get("_:genid8341 ")
        bnodeToUriMap1.get("_:genid340 ") != bnodeToUriMap2.get("_:genid3340 ")
        bnodeToUriMap1.get("_:genid339 ") != bnodeToUriMap2.get("_:genid7339 ")
        bnodeToUriMap1.get("_:genid338 ") != bnodeToUriMap2.get("_:genid4338 ")

        // these two are unaffected and remain equal b/c they are isolated from the swap
        bnodeToUriMap1.get("_:genid337 ") == bnodeToUriMap2.get("_:genid6337 ")
        bnodeToUriMap1.get("_:genid336 ") == bnodeToUriMap2.get("_:genid5336 ")

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
        assert map.size() == 1
        assert map.get("a") == ["b", "c", "d"].toSet()
    }


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


    def "testing cl vs hp assertions to be equal -- added updated lines"() {
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
        assert hp_bnodeToUriMap.get("_:genid299 ") != null
        assert cl_bnodeToUriMap.get("_:genid622 ") != null
        assert hp_bnodeToUriMap.get("_:genid299 ") == cl_bnodeToUriMap.get("_:genid622 ")

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
        bnodeToLineMap.get("_:genid343 ").size() == 3
        bnodeToLineMap.get("_:genid342 ").size() == 3
        bnodeToLineMap.get("_:genid341 ").size() == 3
        bnodeToLineMap.get("_:genid340 ").size() == 3
        bnodeToLineMap.get("_:genid339 ").size() == 3
        bnodeToLineMap.get("_:genid338 ").size() == 3
        bnodeToLineMap.get("_:genid337 ").size() == 3
        bnodeToLineMap.get("_:genid336 ").size() == 3

        bnodeConnectionsMap.size() == 9
        bnodeConnectionsMap.get("_:genid335 ") == ["_:genid335 ", "_:genid343 "].toSet()
        bnodeConnectionsMap.get("_:genid343 ") == ["_:genid343 ", "_:genid342 "].toSet()
        bnodeConnectionsMap.get("_:genid342 ") == ["_:genid342 ", "_:genid341 "].toSet()
        bnodeConnectionsMap.get("_:genid341 ") == ["_:genid341 ", "_:genid340 "].toSet()
        bnodeConnectionsMap.get("_:genid340 ") == ["_:genid340 ", "_:genid339 "].toSet()
        bnodeConnectionsMap.get("_:genid339 ") == ["_:genid339 ", "_:genid338 "].toSet()
        bnodeConnectionsMap.get("_:genid338 ") == ["_:genid338 ", "_:genid337 "].toSet()
        bnodeConnectionsMap.get("_:genid337 ") == ["_:genid337 ", "_:genid336 "].toSet()
        bnodeConnectionsMap.get("_:genid336 ") == ["_:genid336 "].toSet()

    }





    def "testing taxon restriction assertions to be equal"() {
        given:
        def protein1_lines = '''# http://purl.obolibrary.org/obo/PR_000025130
<http://purl.obolibrary.org/obo/PR_000025130> <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Class> .
<http://purl.obolibrary.org/obo/PR_000025130> <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://purl.obolibrary.org/obo/SO_0001217> .
<http://purl.obolibrary.org/obo/PR_000025130> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid72520 .
_:genid72520 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid72520 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/RO_0002160> .
_:genid72520 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/NCBITaxon_9606> .
<http://purl.obolibrary.org/obo/PR_000025130> <http://purl.obolibrary.org/obo/IAO_0000115> "A protein coding gene ACOT7L in human."^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/PR_000025130> <http://www.geneontology.org/formats/oboInOwl#hasOBONamespace> "protein"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/PR_000025130> <http://www.geneontology.org/formats/oboInOwl#id> "PR:000025130"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/PR_000025130> <http://www.w3.org/2000/01/rdf-schema#comment> "Category=external."^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/PR_000025130> <http://www.w3.org/2000/01/rdf-schema#label> "ACOT7L (human)"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid72521 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .
_:genid72521 <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/PR_000025130> .
_:genid72521 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://purl.obolibrary.org/obo/IAO_0000115> .
_:genid72521 <http://www.w3.org/2002/07/owl#annotatedTarget> "A protein coding gene ACOT7L in human."^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid72521 <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "PRO:DAN"^^<http://www.w3.org/2001/XMLSchema#string> .
#
'''

        def protein2_lines = '''# http://purl.obolibrary.org/obo/PR_000025132
<http://purl.obolibrary.org/obo/PR_000025132> <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Class> .
<http://purl.obolibrary.org/obo/PR_000025132> <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://purl.obolibrary.org/obo/SO_0001217> .
<http://purl.obolibrary.org/obo/PR_000025132> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid72524 .
_:genid72524 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid72524 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/RO_0002160> .
_:genid72524 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/NCBITaxon_9606> .
<http://purl.obolibrary.org/obo/PR_000025132> <http://purl.obolibrary.org/obo/IAO_0000115> "A protein coding gene CT75 in human."^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/PR_000025132> <http://www.geneontology.org/formats/oboInOwl#hasOBONamespace> "protein"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/PR_000025132> <http://www.geneontology.org/formats/oboInOwl#id> "PR:000025132"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/PR_000025132> <http://www.w3.org/2000/01/rdf-schema#comment> "Category=external."^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/PR_000025132> <http://www.w3.org/2000/01/rdf-schema#label> "CT75 (human)"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid72525 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .
_:genid72525 <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/PR_000025132> .
_:genid72525 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://purl.obolibrary.org/obo/IAO_0000115> .
_:genid72525 <http://www.w3.org/2002/07/owl#annotatedTarget> "A protein coding gene CT75 in human."^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid72525 <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "PRO:DAN"^^<http://www.w3.org/2001/XMLSchema#string> .
#'''

        def protein1_inputStream = new ByteArrayInputStream(protein1_lines.getBytes())
        def protein2_inputStream = new ByteArrayInputStream(protein2_lines.getBytes())
        def converter = new BNodeConverter()

        when:
        Map<String, String> protein1_bnodeToUriMap = converter.populateBnodeToUriMap(protein1_inputStream)
        Map<String, String> protein2_bnodeToUriMap = converter.populateBnodeToUriMap(protein2_inputStream)

        def updated_protein1_lines = []
        def updated_protein2_lines = []
        protein1_lines.split("\\n").each { line -> updated_protein1_lines.add(converter.updateGenIdsInLine(line, protein1_bnodeToUriMap))}
        protein2_lines.split("\\n").each { line -> updated_protein2_lines.add(converter.updateGenIdsInLine(line, protein2_bnodeToUriMap))}


        then:
        assert protein1_bnodeToUriMap.get("_:genid72520 ") != null
        assert protein2_bnodeToUriMap.get("_:genid72524 ") != null
        assert protein1_bnodeToUriMap.get("_:genid72520 ") == protein2_bnodeToUriMap.get("_:genid72524 ")

    }






    def "testing taxon restriction assertions to be equal when in list"() {
        given:
        def protein1_lines = '''# http://purl.obolibrary.org/obo/PR_000025130
<http://purl.obolibrary.org/obo/PR_000025130> <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Class> .
<http://purl.obolibrary.org/obo/PR_000025130> <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://purl.obolibrary.org/obo/SO_0001217> .
<http://purl.obolibrary.org/obo/PR_000025130> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid72520 .
_:genid72520 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid72520 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/RO_0002160> .
_:genid72520 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/NCBITaxon_9606> .
<http://purl.obolibrary.org/obo/PR_000025130> <http://purl.obolibrary.org/obo/IAO_0000115> "A protein coding gene ACOT7L in human."^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/PR_000025130> <http://www.geneontology.org/formats/oboInOwl#hasOBONamespace> "protein"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/PR_000025130> <http://www.geneontology.org/formats/oboInOwl#id> "PR:000025130"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/PR_000025130> <http://www.w3.org/2000/01/rdf-schema#comment> "Category=external."^^<http://www.w3.org/2001/XMLSchema#string> .
<http://purl.obolibrary.org/obo/PR_000025130> <http://www.w3.org/2000/01/rdf-schema#label> "ACOT7L (human)"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid72521 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .
_:genid72521 <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/PR_000025130> .
_:genid72521 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://purl.obolibrary.org/obo/IAO_0000115> .
_:genid72521 <http://www.w3.org/2002/07/owl#annotatedTarget> "A protein coding gene ACOT7L in human."^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid72521 <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "PRO:DAN"^^<http://www.w3.org/2001/XMLSchema#string> .
#
'''

        def protein2_lines = '''# http://purl.obolibrary.org/obo/PR_Q8IVD9
<http://purl.obolibrary.org/obo/PR_Q8IVD9> <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Class> .
<http://purl.obolibrary.org/obo/PR_Q8IVD9> <http://www.w3.org/2002/07/owl#equivalentClass> _:genid1105042 .
_:genid1105042 <http://www.w3.org/2002/07/owl#intersectionOf> _:genid1105045 .
_:genid1105045 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .
_:genid1105045 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> <http://purl.obolibrary.org/obo/PR_000031240> .
_:genid1105045 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> _:genid1105043 .
_:genid1105043 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/1999/02/22-rdf-syntax-ns#List> .
_:genid1105043 <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> _:genid1967478 .
_:genid1967478 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid1967478 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/RO_0002160> .
_:genid1967478 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/NCBITaxon_9606> .
_:genid1105043 <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> <http://www.w3.org/1999/02/22-rdf-syntax-ns#nil> .
_:genid1105042 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Class> .
<http://purl.obolibrary.org/obo/PR_Q8IVD9> <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://purl.obolibrary.org/obo/PR_000029067> .
'''

        def protein1_inputStream = new ByteArrayInputStream(protein1_lines.getBytes())
        def protein2_inputStream = new ByteArrayInputStream(protein2_lines.getBytes())
        def converter = new BNodeConverter()

        when:
        Map<String, String> protein1_bnodeToUriMap = converter.populateBnodeToUriMap(protein1_inputStream)
        Map<String, String> protein2_bnodeToUriMap = converter.populateBnodeToUriMap(protein2_inputStream)

        def (Map<String, Set<String>> bnodeToLineMap,
        Map<String, Set<String>>      bnodeConnectionsMap) = converter.processBnodeLines(new ByteArrayInputStream(protein2_lines.getBytes()))

//        def updated_protein1_lines = []
//        def updated_protein2_lines = []
//        protein1_lines.split("\\n").each { line -> updated_protein1_lines.add(converter.updateGenIdsInLine(line, protein1_bnodeToUriMap))}
//        protein2_lines.split("\\n").each { line -> updated_protein2_lines.add(converter.updateGenIdsInLine(line, protein2_bnodeToUriMap))}


        then:

        bnodeToLineMap.size() == 4
        bnodeToLineMap.get("_:genid1105042 ").size() == 2
        bnodeToLineMap.get("_:genid1105045 ").size() == 3
        bnodeToLineMap.get("_:genid1105043 ").size() == 3
        bnodeToLineMap.get("_:genid1967478 ").size() == 3

        bnodeConnectionsMap.size() == 4
        bnodeConnectionsMap.get("_:genid1105042 ") == ["_:genid1105042 ", "_:genid1105045 "].toSet()
        bnodeConnectionsMap.get("_:genid1105045 ") == ["_:genid1105045 ", "_:genid1105043 "].toSet()
        bnodeConnectionsMap.get("_:genid1105043 ") == ["_:genid1105043 ", "_:genid1967478 "].toSet()
        bnodeConnectionsMap.get("_:genid1967478 ") == ["_:genid1967478 "].toSet()

        assert protein1_bnodeToUriMap.get("_:genid72520 ") != null
        assert protein2_bnodeToUriMap.get("_:genid1967478 ") != null
        assert protein1_bnodeToUriMap.get("_:genid72520 ") == protein2_bnodeToUriMap.get("_:genid1967478 ")

    }








    def "testing taxon restriction assertions to be equal NCBITaxon_9031"() {
        given:
        def protein1_lines = '''# http://birdgenenames.org/cgnc/GeneReport?id=10048
<http://birdgenenames.org/cgnc/GeneReport?id=10048> <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Class> .
<http://birdgenenames.org/cgnc/GeneReport?id=10048> <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://purl.obolibrary.org/obo/SO_0001217> .
<http://birdgenenames.org/cgnc/GeneReport?id=10048> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid4 .
_:genid4 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid4 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/RO_0002160> .
_:genid4 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/NCBITaxon_9031> .
<http://birdgenenames.org/cgnc/GeneReport?id=10048> <http://purl.obolibrary.org/obo/IAO_0000115> "A protein coding gene GBX2 in chicken."^^<http://www.w3.org/2001/XMLSchema#string> .
<http://birdgenenames.org/cgnc/GeneReport?id=10048> <http://www.geneontology.org/formats/oboInOwl#hasOBONamespace> "gene"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://birdgenenames.org/cgnc/GeneReport?id=10048> <http://www.geneontology.org/formats/oboInOwl#id> "CGNC:10048"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://birdgenenames.org/cgnc/GeneReport?id=10048> <http://www.w3.org/2000/01/rdf-schema#comment> "Category=external."^^<http://www.w3.org/2001/XMLSchema#string> .
<http://birdgenenames.org/cgnc/GeneReport?id=10048> <http://www.w3.org/2000/01/rdf-schema#label> "GBX2 (chicken)"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid5 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .
_:genid5 <http://www.w3.org/2002/07/owl#annotatedSource> <http://birdgenenames.org/cgnc/GeneReport?id=10048> .
_:genid5 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://purl.obolibrary.org/obo/IAO_0000115> .
_:genid5 <http://www.w3.org/2002/07/owl#annotatedTarget> "A protein coding gene GBX2 in chicken."^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid5 <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "PRO:DNx"^^<http://www.w3.org/2001/XMLSchema#string> .
#
# http://birdgenenames.org/cgnc/GeneReport?id=10067
<http://birdgenenames.org/cgnc/GeneReport?id=10067> <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Class> .
<http://birdgenenames.org/cgnc/GeneReport?id=10067> <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://purl.obolibrary.org/obo/SO_0001217> .
<http://birdgenenames.org/cgnc/GeneReport?id=10067> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid6 .
_:genid6 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid6 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/RO_0002160> .
_:genid6 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/NCBITaxon_9031> .
<http://birdgenenames.org/cgnc/GeneReport?id=10067> <http://purl.obolibrary.org/obo/IAO_0000115> "A protein coding gene IL7R in chicken."^^<http://www.w3.org/2001/XMLSchema#string> .
<http://birdgenenames.org/cgnc/GeneReport?id=10067> <http://www.geneontology.org/formats/oboInOwl#hasOBONamespace> "gene"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://birdgenenames.org/cgnc/GeneReport?id=10067> <http://www.geneontology.org/formats/oboInOwl#id> "CGNC:10067"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://birdgenenames.org/cgnc/GeneReport?id=10067> <http://www.w3.org/2000/01/rdf-schema#comment> "Category=external."^^<http://www.w3.org/2001/XMLSchema#string> .
<http://birdgenenames.org/cgnc/GeneReport?id=10067> <http://www.w3.org/2000/01/rdf-schema#label> "IL7R (chicken)"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid7 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .
_:genid7 <http://www.w3.org/2002/07/owl#annotatedSource> <http://birdgenenames.org/cgnc/GeneReport?id=10067> .
_:genid7 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://purl.obolibrary.org/obo/IAO_0000115> .
_:genid7 <http://www.w3.org/2002/07/owl#annotatedTarget> "A protein coding gene IL7R in chicken."^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid7 <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "PRO:DNx"^^<http://www.w3.org/2001/XMLSchema#string> .
#
# http://birdgenenames.org/cgnc/GeneReport?id=10092
<http://birdgenenames.org/cgnc/GeneReport?id=10092> <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Class> .
<http://birdgenenames.org/cgnc/GeneReport?id=10092> <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://purl.obolibrary.org/obo/SO_0001217> .
<http://birdgenenames.org/cgnc/GeneReport?id=10092> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid8 .
_:genid8 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid8 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/RO_0002160> .
_:genid8 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/NCBITaxon_9031> .
<http://birdgenenames.org/cgnc/GeneReport?id=10092> <http://purl.obolibrary.org/obo/IAO_0000115> "A protein coding gene PDLIM3 in chicken."^^<http://www.w3.org/2001/XMLSchema#string> .
<http://birdgenenames.org/cgnc/GeneReport?id=10092> <http://www.geneontology.org/formats/oboInOwl#hasOBONamespace> "gene"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://birdgenenames.org/cgnc/GeneReport?id=10092> <http://www.geneontology.org/formats/oboInOwl#id> "CGNC:10092"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://birdgenenames.org/cgnc/GeneReport?id=10092> <http://www.w3.org/2000/01/rdf-schema#comment> "Category=external."^^<http://www.w3.org/2001/XMLSchema#string> .
<http://birdgenenames.org/cgnc/GeneReport?id=10092> <http://www.w3.org/2000/01/rdf-schema#label> "PDLIM3 (chicken)"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid9 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .
_:genid9 <http://www.w3.org/2002/07/owl#annotatedSource> <http://birdgenenames.org/cgnc/GeneReport?id=10092> .
_:genid9 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://purl.obolibrary.org/obo/IAO_0000115> .
_:genid9 <http://www.w3.org/2002/07/owl#annotatedTarget> "A protein coding gene PDLIM3 in chicken."^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid9 <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "PRO:DNx"^^<http://www.w3.org/2001/XMLSchema#string> .
#
# http://birdgenenames.org/cgnc/GeneReport?id=10114
<http://birdgenenames.org/cgnc/GeneReport?id=10114> <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Class> .
<http://birdgenenames.org/cgnc/GeneReport?id=10114> <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://purl.obolibrary.org/obo/SO_0001217> .
<http://birdgenenames.org/cgnc/GeneReport?id=10114> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid10 .
_:genid10 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .
_:genid10 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/RO_0002160> .
_:genid10 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/NCBITaxon_9031> .
<http://birdgenenames.org/cgnc/GeneReport?id=10114> <http://purl.obolibrary.org/obo/IAO_0000115> "A protein coding gene FBXL12 in chicken."^^<http://www.w3.org/2001/XMLSchema#string> .
<http://birdgenenames.org/cgnc/GeneReport?id=10114> <http://www.geneontology.org/formats/oboInOwl#hasOBONamespace> "gene"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://birdgenenames.org/cgnc/GeneReport?id=10114> <http://www.geneontology.org/formats/oboInOwl#id> "CGNC:10114"^^<http://www.w3.org/2001/XMLSchema#string> .
<http://birdgenenames.org/cgnc/GeneReport?id=10114> <http://www.w3.org/2000/01/rdf-schema#comment> "Category=external."^^<http://www.w3.org/2001/XMLSchema#string> .
<http://birdgenenames.org/cgnc/GeneReport?id=10114> <http://www.w3.org/2000/01/rdf-schema#label> "FBXL12 (chicken)"^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid11 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .
_:genid11 <http://www.w3.org/2002/07/owl#annotatedSource> <http://birdgenenames.org/cgnc/GeneReport?id=10114> .
_:genid11 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://purl.obolibrary.org/obo/IAO_0000115> .
_:genid11 <http://www.w3.org/2002/07/owl#annotatedTarget> "A protein coding gene FBXL12 in chicken."^^<http://www.w3.org/2001/XMLSchema#string> .
_:genid11 <http://www.geneontology.org/formats/oboInOwl#hasDbXref> "PRO:DNx"^^<http://www.w3.org/2001/XMLSchema#string> .
#'''

        def protein1_inputStream = new ByteArrayInputStream(protein1_lines.getBytes())
        def converter = new BNodeConverter()

        when:
        Map<String, String> protein1_bnodeToUriMap = converter.populateBnodeToUriMap(protein1_inputStream)

        def updated_protein1_lines = []
        protein1_lines.split("\\n").each { line -> updated_protein1_lines.add(converter.updateGenIdsInLine(line, protein1_bnodeToUriMap))}

        then:
        assert protein1_bnodeToUriMap.get("_:genid4 ") != null
        assert protein1_bnodeToUriMap.get("_:genid6 ") != null
        assert protein1_bnodeToUriMap.get("_:genid8 ") != null
        assert protein1_bnodeToUriMap.get("_:genid10 ") != null
        assert protein1_bnodeToUriMap.get("_:genid4 ") == protein1_bnodeToUriMap.get("_:genid6 ")
        assert protein1_bnodeToUriMap.get("_:genid4 ") == protein1_bnodeToUriMap.get("_:genid8 ")
        assert protein1_bnodeToUriMap.get("_:genid4 ") == protein1_bnodeToUriMap.get("_:genid10 ")

    }







}





