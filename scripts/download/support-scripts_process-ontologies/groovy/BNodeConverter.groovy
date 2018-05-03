import java.util.regex.Pattern
import java.util.regex.Matcher

@Grab(group = 'commons-codec', module = 'commons-codec', version = '1.11')
import org.apache.commons.codec.digest.DigestUtils

import java.util.Map.Entry

class BNodeConverter {

    def static addToCollectionMap(key, value, map) {
        if (!map.containsKey(key)) {
            map.put(key, new HashSet<String>())
        }
        map.get(key).add(value)
    }

    def static getLinesForNeighborhood(Set<String> nodes, Map<String, Set<String>> bnodeToLineMap) {
        List<String> lines = new ArrayList<String>()
        nodes.each { node -> lines.addAll(bnodeToLineMap.get(node)) }
        return lines
    }


    def static normalizeBnodeIds(List<String> lines, Map<String, String> genIdToNormIdMap) {
        def genIdPattern = Pattern.compile("(_:genid\\d+)")
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

    def static createGenIdToNormIdMap(List<String> lines) {
        def sortedLines = lines
        Collections.sort(sortedLines)
        def genIdPattern = Pattern.compile("(_:genid\\d+)")
        Map<String, String> genIdToNormIdMap = new HashMap<String, String>()
        int i = 1
        sortedLines.each { line ->
            def m = genIdPattern.matcher(line)
            while (m.find()) {
                def id = m.group(1)
                if (!genIdToNormIdMap.containsKey(id)) {
                    genIdToNormIdMap.put(id, "[ID" + i++ + "]")
//                    println "Storing mapping: " + id + " --> [ID" + (i-1) + "]"
                }
            }
        }
        return genIdToNormIdMap
    }

    def static populateBnodeToUriMap(inputStream) {
        Map<String, String> bnodeToUriMap = new HashMap<String, String>()
        def (Map<String, Set<String>> bnodeToLineMap, Map<String, Set<String>> bnodeConnectionsMap) = processBnodeLines(inputStream)

        // create node neighborhoods
        Map<String, Set<String>> nodeNeighborhoodMap = createNodeNeighborhoodMap(bnodeConnectionsMap)

        // when ordering lines, use those that start with the node first (sorted) then sort the rest and append to the end of the list
        for (Entry<String, Set<String>> entry : nodeNeighborhoodMap.entrySet()) {
            // when we normalize the genid's we will renumber them so that they are still unique
            // here we create a mapping between the original genid and the renumbered id, e.g. ID1
            def neighborNodes = entry.getValue()
            Map<String, String> genIdToNormIdMap = createGenIdToNormIdMap(getLinesForNeighborhood(neighborNodes, bnodeToLineMap))

            // for each node, create a string that contains first it's lines (sorted) then the lines of its neighborhood (also sorted)
            StringBuilder builder = new StringBuilder()
            def lines = new ArrayList<String>(bnodeToLineMap.get(entry.getKey()))
            lines = normalizeBnodeIds(lines, genIdToNormIdMap)
            lines.each { line -> builder.append(line) }


            neighborNodes.remove(entry.getKey())
            lines = getLinesForNeighborhood(neighborNodes, bnodeToLineMap)
            lines = normalizeBnodeIds(lines, genIdToNormIdMap)
            lines.each { line -> builder.append(line) }

            // sha-1 hash to form the basis of the node URI
//            println "******* " + entry.getKey() + " -- "+ builder.toString()
            String sha256hex = DigestUtils.sha256Hex(builder.toString())

            bnodeToUriMap.put(entry.getKey(), "http://BN_" + sha256hex)

        }

        return bnodeToUriMap
    }


    def
    static getNodeNeighborhood(String node, Map<String, Set<String>> nodeConnectionsMap, Set<String> neighborhoodSet) {
        def seeds = new HashSet<String>()
        if (nodeConnectionsMap.containsKey(node)) {
            seeds.addAll(nodeConnectionsMap.get(node))
        }

        seeds.removeAll(neighborhoodSet)
        for (String seed : seeds) {
            neighborhoodSet.add(seed)
            getNodeNeighborhood(seed, nodeConnectionsMap, neighborhoodSet)
        }
        neighborhoodSet.add(node)
    }

    def static createNodeNeighborhoodMap(Map<String, Set<String>> bnodeConnectionsMap) {
        def neighborhoodMap = new HashMap<String, Set<String>>()
        def keys = bnodeConnectionsMap.keySet()
        for (String key : keys) {
            def neighborhoodSet = new HashSet<String>()
            getNodeNeighborhood(key, bnodeConnectionsMap, neighborhoodSet)
            neighborhoodMap.put(key, neighborhoodSet)
        }
        return neighborhoodMap
    }


    def static processBnodeLines(inputStream) {
        def genIdPattern = Pattern.compile("(_:genid\\d+)")

        def bnodeConnectionsMap = new HashMap<String, Set<String>>()
        def bnodeToLineMap = new HashMap<String, Set<String>>()

        inputStream.withReader { reader ->
            def line = ""
            while ((line = reader.readLine()) != null) {
                def m = genIdPattern.matcher(line)
                def prevKey = null
                while (m.find()) {
                    def key = m.group(1)
                    addToCollectionMap(key, line, bnodeToLineMap)
                    if (prevKey != null) {
                        addToCollectionMap(prevKey, key, bnodeConnectionsMap)
                        addToCollectionMap(key, prevKey, bnodeConnectionsMap)
                    } else {
                        addToCollectionMap(key, key, bnodeConnectionsMap)
                    }
                    prevKey = key
                }
            }
        }

        return new Tuple2(bnodeToLineMap, bnodeConnectionsMap)
    }


    def static updateGenIdsInLine(line, bnodeToUriMap) {
        def genIdPattern = Pattern.compile("(_:genid\\d+)")
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

    def static processFile(inputFile, outputFile) {

        def bnodeToUriMap = populateBnodeToUriMap(new FileInputStream(inputFile))

        Set<String> redundant_uris = new HashSet<String>()
        Set<String> uris = new HashSet<String>()
        for (String value : bnodeToUriMap.values()) {
            if (uris.contains(value)) {
                redundant_uris.add(value)
            } else {
                uris.add(value)
            }
        }

        for (Entry<String, String> entry : bnodeToUriMap) {
            if (redundant_uris.contains(entry.getValue())) {
                println entry.getKey() + " -- " + entry.getValue()
            }
        }

        assert bnodeToUriMap.size() == new HashSet<String>(bnodeToUriMap.values()).size(): "URI collision detected"

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
