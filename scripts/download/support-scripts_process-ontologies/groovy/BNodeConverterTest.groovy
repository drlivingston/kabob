@Grab(group = 'org.spockframework', module = 'spock-core', version = '1.0-groovy-2.4', scope = 'test')
import spock.lang.Specification
@Grab(group = 'commons-codec', module = 'commons-codec', version = '1.11')
import org.apache.commons.codec.digest.DigestUtils
import spock.lang.Ignore
import spock.lang.IgnoreRest

class BNodeConverterSpecification extends Specification {

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
        map.get("_:genid335") == "[ID1]"
        map.get("_:genid343") == "[ID2]"
        map.get("_:genid342") == "[ID9]"
        map.get("_:genid341") == "[ID8]"
        map.get("_:genid340") == "[ID7]"
        map.get("_:genid339") == "[ID6]"
        map.get("_:genid338") == "[ID5]"
        map.get("_:genid337") == "[ID4]"
        map.get("_:genid336") == "[ID3]"
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
        bnodeToUriMap.get("_:genid335") == "http://BN_" + computeHash(nodeLines, true)
        bnodeToUriMap.get("_:genid336") == "http://BN_" + computeHash(nodeLines_last, true)

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
        bnodeToUriMap.get("_:genid218001") == "http://BN_" + computeHash(["<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.w3.org/2000/01/rdf-schema#subClassOf> [ID1] .",
                                                         "[ID1] <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .",
                                                         "[ID1] <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/RO_0000087> .",
                                                         "[ID1] <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/CHEBI_75772> ."], false)


        bnodeToUriMap.get("_:genid218002") == "http://BN_" + computeHash(["<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.w3.org/2000/01/rdf-schema#subClassOf> [ID1] .",
                                                         "[ID1] <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .",
                                                         "[ID1] <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/chebi#has_functional_parent> .",
                                                         "[ID1] <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/CHEBI_50607> ."], false)
        bnodeToUriMap.get("_:genid218003") == "http://BN_" + computeHash(["[ID1] <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .",
                                                         "[ID1] <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/CHEBI_50611> .",
                                                         "[ID1] <http://www.w3.org/2002/07/owl#annotatedProperty> <http://www.geneontology.org/formats/oboInOwl#hasExactSynonym> .",
                                                         "[ID1] <http://www.w3.org/2002/07/owl#annotatedTarget> \"3,3'-(6,6'-dihydroxybiphenyl-3,3'-diyl)bis[2-(formylamino)propanoic acid]\"^^<http://www.w3.org/2001/XMLSchema#string> .",
                                                         "[ID1] <http://www.geneontology.org/formats/oboInOwl#hasDbXref> \"IUPAC\"^^<http://www.w3.org/2001/XMLSchema#string> .",
                                                         "[ID1] <http://www.geneontology.org/formats/oboInOwl#hasSynonymType> <http://purl.obolibrary.org/obo/chebi#IUPAC_NAME> ."], false)
        bnodeToUriMap.get("_:genid218004") == "http://BN_" + computeHash(["[ID1] <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .",
                                                         "[ID1] <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/CHEBI_50611> .",
                                                         "[ID1] <http://www.w3.org/2002/07/owl#annotatedProperty> <http://www.geneontology.org/formats/oboInOwl#hasRelatedSynonym> .",
                                                         "[ID1] <http://www.w3.org/2002/07/owl#annotatedTarget> \"N,N'-bisformyl dityrosine\"^^<http://www.w3.org/2001/XMLSchema#string> .",
                                                         "[ID1] <http://www.geneontology.org/formats/oboInOwl#hasDbXref> \"ChEBI\"^^<http://www.w3.org/2001/XMLSchema#string> ."], false)

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
        bnodeToLineMap.get("_:genid218001").size() == 4
        bnodeToLineMap.get("_:genid218002").size() == 4
        bnodeToLineMap.get("_:genid218003").size() == 6
        bnodeToLineMap.get("_:genid218004").size() == 5
        bnodeToLineMap.get("_:genid218001") == ["<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid218001 .",
                                              "_:genid218001 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .",
                                              "_:genid218001 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/RO_0000087> .",
                                              "_:genid218001 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/CHEBI_75772> ."].toSet()
        bnodeToLineMap.get("_:genid218002") == ["<http://purl.obolibrary.org/obo/CHEBI_50611> <http://www.w3.org/2000/01/rdf-schema#subClassOf> _:genid218002 .",
                                              "_:genid218002 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> .",
                                              "_:genid218002 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/chebi#has_functional_parent> .",
                                              "_:genid218002 <http://www.w3.org/2002/07/owl#someValuesFrom> <http://purl.obolibrary.org/obo/CHEBI_50607> ."].toSet()
        bnodeToLineMap.get("_:genid218003") == ["_:genid218003 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .",
                                              "_:genid218003 <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/CHEBI_50611> .",
                                              "_:genid218003 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://www.geneontology.org/formats/oboInOwl#hasExactSynonym> .",
                                              "_:genid218003 <http://www.w3.org/2002/07/owl#annotatedTarget> \"3,3'-(6,6'-dihydroxybiphenyl-3,3'-diyl)bis[2-(formylamino)propanoic acid]\"^^<http://www.w3.org/2001/XMLSchema#string> .",
                                              "_:genid218003 <http://www.geneontology.org/formats/oboInOwl#hasDbXref> \"IUPAC\"^^<http://www.w3.org/2001/XMLSchema#string> .",
                                              "_:genid218003 <http://www.geneontology.org/formats/oboInOwl#hasSynonymType> <http://purl.obolibrary.org/obo/chebi#IUPAC_NAME> ."].toSet()
        bnodeToLineMap.get("_:genid218004") == ["_:genid218004 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Axiom> .",
                                              "_:genid218004 <http://www.w3.org/2002/07/owl#annotatedSource> <http://purl.obolibrary.org/obo/CHEBI_50611> .",
                                              "_:genid218004 <http://www.w3.org/2002/07/owl#annotatedProperty> <http://www.geneontology.org/formats/oboInOwl#hasRelatedSynonym> .",
                                              "_:genid218004 <http://www.w3.org/2002/07/owl#annotatedTarget> \"N,N'-bisformyl dityrosine\"^^<http://www.w3.org/2001/XMLSchema#string> .",
                                              "_:genid218004 <http://www.geneontology.org/formats/oboInOwl#hasDbXref> \"ChEBI\"^^<http://www.w3.org/2001/XMLSchema#string> ."].toSet()

        bnodeConnectionsMap.size() == 4
        bnodeConnectionsMap.get("_:genid218001") == ["_:genid218001"].toSet()
        bnodeConnectionsMap.get("_:genid218002") == ["_:genid218002"].toSet()
        bnodeConnectionsMap.get("_:genid218003") == ["_:genid218003"].toSet()
        bnodeConnectionsMap.get("_:genid218004") == ["_:genid218004"].toSet()

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
        bnodeToLineMap.get("_:genid335").size() == 2
        bnodeToLineMap.get("_:genid343").size() == 4
        bnodeToLineMap.get("_:genid342").size() == 4
        bnodeToLineMap.get("_:genid341").size() == 4
        bnodeToLineMap.get("_:genid340").size() == 4
        bnodeToLineMap.get("_:genid339").size() == 4
        bnodeToLineMap.get("_:genid338").size() == 4
        bnodeToLineMap.get("_:genid337").size() == 4
        bnodeToLineMap.get("_:genid336").size() == 4

        bnodeConnectionsMap.size() == 9
        bnodeConnectionsMap.get("_:genid335") == ["_:genid335", "_:genid343"].toSet()
        bnodeConnectionsMap.get("_:genid343") == ["_:genid343", "_:genid342", "_:genid335"].toSet()
        bnodeConnectionsMap.get("_:genid342") == ["_:genid342", "_:genid341", "_:genid343"].toSet()
        bnodeConnectionsMap.get("_:genid341") == ["_:genid341", "_:genid340", "_:genid342"].toSet()
        bnodeConnectionsMap.get("_:genid340") == ["_:genid340", "_:genid339", "_:genid341"].toSet()
        bnodeConnectionsMap.get("_:genid339") == ["_:genid339", "_:genid340", "_:genid338"].toSet()
        bnodeConnectionsMap.get("_:genid338") == ["_:genid338", "_:genid337", "_:genid339"].toSet()
        bnodeConnectionsMap.get("_:genid337") == ["_:genid337", "_:genid336", "_:genid338"].toSet()
        bnodeConnectionsMap.get("_:genid336") == ["_:genid336", "_:genid337"].toSet()

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





