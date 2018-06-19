import java.util.regex.Pattern
import java.util.regex.Matcher

@Grab(group = 'commons-codec', module = 'commons-codec', version = '1.11')
import org.apache.commons.codec.digest.DigestUtils

import java.util.Map.Entry

class BNodeConverter {

    /*
      Simple method to add a key/value pair to an existing Map<key, Set<String>>
     */
    def static addToCollectionMap(key, value, map) {
        if (!map.containsKey(key)) {
            map.put(key, new HashSet<String>())
        }
        map.get(key).add(value)
    }

    /*
    Given a set of blank nodes, returns all lines associated with the blank nodes
     */
    def static getNodeToLinesMap(Set<String> nodes, Map<String, Set<String>> bnodeToLineMap) {
        Map<String, List<String>> nodeToLinesMap = new HashMap<String, List<String>>()
        nodes.each { node -> nodeToLinesMap.put(node, bnodeToLineMap.get(node)) }
        return nodeToLinesMap
    }


    /*

     */
    def static normalizeBnodeIds(List<String> lines, Map<String, String> genIdToNormIdMap) {
        def genIdPattern = Pattern.compile("(_:genid\\d+ )")
        List<String> normalizedLines = new ArrayList<String>()
        lines.each { line ->
            String updatedLine = line
            def m = genIdPattern.matcher(line)
            while (m.find()) {
                def id = m.group(1)
                def replacementId = genIdToNormIdMap.get(id)
                if (replacementId == null) {
                    throw new IllegalStateException("No replacement id found for id: " + id)
                }
                updatedLine = updatedLine.replaceAll(id, replacementId)
            }
            normalizedLines.add(updatedLine)
        }
        Collections.sort(normalizedLines)
        return normalizedLines
    }


    def static getNodeUri(String node, Map<String, Set<String>> subjBnodeToLineMap, Map<String, Set<String>> bnodeSubjToObjConnectionsMap, Map<String, String> bnodeToUriMap) {
        Set<String> neighborNodes = bnodeSubjToObjConnectionsMap.get(node)
        neighborNodes.remove(node)

        // ensure all neighbors have a URI that has already been computed
        neighborNodes.each { neighbor ->
            if (!bnodeToUriMap.containsKey(neighbor)) {
                bnodeToUriMap.put(neighbor, getNodeUri(neighbor, subjBnodeToLineMap, bnodeSubjToObjConnectionsMap, bnodeToUriMap))
            }
        }

        // for the lines where the node is the subject, fill in the _:genid12345 identifiers with the computed blank node URIs
        Set<String> lines = subjBnodeToLineMap.get(node)
        List<String> updatedLines = new ArrayList<String>()
        lines.each { line ->
            line = line.replaceAll(node, "GENID ")
            neighborNodes.each { neighbor ->
                line = line.replaceAll(neighbor, bnodeToUriMap.get(neighbor))
            }
            updatedLines.add(line)
        }
        Collections.sort(updatedLines)
        String sha256hex = DigestUtils.sha256Hex(updatedLines.toString())
        return "<http://ccp.ucdenver.edu/bnode/BN_" + sha256hex + "> "

    }


    def static populateBnodeToUriMap(inputStream) {
        Map<String, String> bnodeToUriMap = new HashMap<String, String>()
        def (Map<String, Set<String>> subjBnodeToLineMap, Map<String, Set<String>> bnodeSubjToObjConnectionsMap) = processBnodeLines(inputStream)

        // when ordering lines, use those that start with the node first (sorted) then sort the rest and append to the end of the list
        for (Entry<String, Set<String>> entry : bnodeSubjToObjConnectionsMap.entrySet()) {
            bnodeToUriMap.put(entry.getKey(), getNodeUri(entry.getKey(), subjBnodeToLineMap, bnodeSubjToObjConnectionsMap, bnodeToUriMap))
        }

        return bnodeToUriMap
    }



    /*
      From the given inputStream of n-triples, create two maps
       1) a mapping from blank nodes to the lines that they occur as the SUBJECT in
      and
      2) a mapping from subject bnode to object bnode when two blank nodes appear in the same line.

      @param inputStream
    */

    def static processBnodeLines(inputStream) {
        def genIdPattern = Pattern.compile("(_:genid\\d+ )")
        def genIdAtStartPattern = Pattern.compile("^(_:genid\\d+ )")

        def bnodeSubjToObjConnectionsMap = new HashMap<String, Set<String>>()
        def subjBnodeToLineMap = new HashMap<String, Set<String>>()

        inputStream.withReader { reader ->
            def line = ""
            while ((line = reader.readLine()) != null) {
                def m = genIdAtStartPattern.matcher(line)
                if (m.find()) {
                    def subjKey = m.group(1)
                    addToCollectionMap(subjKey, subjKey, bnodeSubjToObjConnectionsMap)
                    addToCollectionMap(subjKey, line, subjBnodeToLineMap)
                    m = genIdPattern.matcher(line)
                    if (m.find(subjKey.length())) {
                        def objKey = m.group(1)
                        addToCollectionMap(subjKey, objKey, bnodeSubjToObjConnectionsMap)
                    }
                }
            }
        }
        return new Tuple(subjBnodeToLineMap, bnodeSubjToObjConnectionsMap)
    }


    def static updateGenIdsInLine(line, bnodeToUriMap) {
        def genIdPattern = Pattern.compile("(_:genid\\d+ )")
        String updatedLine = line
        Matcher m = genIdPattern.matcher(line);
        while (m.find()) {
            def key = m.group(1)
            def uri = bnodeToUriMap.get(key)
            if (uri == null) {
                throw new IllegalStateException("Unknown hash for bnode: " + key)
            }
            updatedLine = updatedLine.replaceAll(key, uri)
        }
        return updatedLine
    }

    def static processFile(File inputFile, File outputFile) {

        def bnodeToUriMap = populateBnodeToUriMap(new FileInputStream(inputFile))

        String line = ""
        outputFile.withWriter('utf-8') { writer ->
            inputFile.withReader { reader ->
                while ((line = reader.readLine()) != null) {
                    String updatedLine = updateGenIdsInLine(line, bnodeToUriMap)
                    writer.writeLine updatedLine
                }
            }
        }
    }


    static void main(String[] args) {
        File inputFile = new File(args[0])
        File outputFile = new File(args[0] + ".noblank.nt")
        processFile(inputFile, outputFile)
    }

}
