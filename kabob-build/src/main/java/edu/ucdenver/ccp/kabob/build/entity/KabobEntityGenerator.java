/**
 * 
 */
// package edu.ucdenver.ccp.kabob.build.entity;

// import java.io.File;
// import java.io.IOException;
// import java.util.HashMap;
// import java.util.HashSet;
// import java.util.Map;
// import java.util.Map.Entry;
// import java.util.Properties;
// import java.util.Set;

// import org.apache.log4j.BasicConfigurator;
// import org.apache.log4j.Level;
// import org.apache.log4j.Logger;
// import org.neo4j.graphdb.GraphDatabaseService;
// import org.neo4j.graphdb.Node;
// import org.neo4j.graphdb.Transaction;
// import org.neo4j.tooling.GlobalGraphOperations;
// import org.openrdf.model.Statement;
// import org.openrdf.model.URI;
// import org.openrdf.model.impl.StatementImpl;
// import org.openrdf.model.impl.URIImpl;
// import org.openrdf.query.BindingSet;
// import org.openrdf.query.MalformedQueryException;
// import org.openrdf.query.QueryEvaluationException;
// import org.openrdf.query.QueryLanguage;
// import org.openrdf.query.TupleQuery;
// import org.openrdf.query.TupleQueryResult;
// import org.openrdf.repository.RepositoryException;
// import org.openrdf.rio.RDFHandlerException;
// import org.openrdf.rio.RDFWriter;
// import org.openrdf.sail.SailException;

// import com.bigdata.rdf.sail.BigdataSail;
// import com.bigdata.rdf.sail.BigdataSailRepository;
// import com.bigdata.rdf.sail.BigdataSailRepositoryConnection;

// import edu.ucdenver.ccp.common.collections.CollectionsUtil;
// import edu.ucdenver.ccp.common.file.CharacterEncoding;
// import edu.ucdenver.ccp.common.file.FileUtil;
// import edu.ucdenver.ccp.common.properties.PropertiesUtil;
// import edu.ucdenver.ccp.common.string.StringUtil;
// import edu.ucdenver.ccp.datasource.identifiers.DataSource;
// import edu.ucdenver.ccp.datasource.identifiers.DataSourceIdResolver;
// import edu.ucdenver.ccp.datasource.identifiers.DataSourceIdentifier;
// import edu.ucdenver.ccp.datasource.identifiers.ncbi.gene.EntrezGeneID;
// import edu.ucdenver.ccp.datasource.identifiers.network.index.IndexOperation;
// import edu.ucdenver.ccp.datasource.identifiers.network.neo4j.Neo4jNetworkBuilder;
// import edu.ucdenver.ccp.datasource.identifiers.network.neo4j.Neo4jNetworkSettings;
// import edu.ucdenver.ccp.datasource.identifiers.network.neo4j.modify.Neo4jIdentifierEdgeInserter;
// import edu.ucdenver.ccp.datasource.identifiers.network.neo4j.modify.Neo4jIdentifierNodeInserter;
// import edu.ucdenver.ccp.datasource.identifiers.network.neo4j.modify.Neo4jIdentifierNodeMerger;
// import edu.ucdenver.ccp.datasource.identifiers.network.neo4j.modify.Neo4jIdentifierNodeUtil;
// import edu.ucdenver.ccp.datasource.identifiers.network.neo4j.skos.Neo4jSkosMatchRelationshipType;
// import edu.ucdenver.ccp.datasource.identifiers.network.skos.SkosMatchNetworkBuilder;
// import edu.ucdenver.ccp.rdfizer.rdf.RdfId;
// import edu.ucdenver.ccp.rdfizer.rdf.RdfNamespace;
// import edu.ucdenver.ccp.rdfizer.rdf.RdfUtil;
// import edu.ucdenver.ccp.rdfizer.rdf.RdfUtil.RdfFormat;
// import edu.ucdenver.ccp.rdfizer.validate.RdfValidator;
// import edu.ucdenver.ccp.rdfizer.validate.RdfValidatorFactory;
// import edu.ucdenver.ccp.wrapper.neo4j.Neo4jUtil;

// /**
//  * This class queries a KB loaded with output from the id_typing and id_mapping rules, and merges
//  * those entities that are connected via skos:exactMatch predicates. The merged entities are then
//  * output to RDF whereby the ICE URIs denote the newly created KaBOB entity URIs. This class uses a
//  * Neo4j backend to keep track of and link the entities.
//  * 
//  * @author Colorado Computational Pharmacology, UC Denver; ccpsupport@ucdenver.edu
//  * 
//  */
// public class KabobEntityGenerator {

// 	private static final Logger logger = Logger.getLogger(KabobEntityGenerator.class);

// 	private static final int ID_NODE_INSERT_INDEX_FLUSH_THRESHOLD = 10000;
// 	private static int idNodeInsertCount = 0;

// 	private static Set<DataSourceIdentifier<?>> alreadyInsertedIdNodes;

// 	private static final URI IAO_DENOTES = new URIImpl(RdfUtil.createUri(RdfNamespace.IAO, "IAO_0000219").toString());

// 	public enum IdentifierType {
// 		DRUG_IDENTIFIER("DrugIdentifier"),
// 		DISEASE_IDENTIFIER("DiseaseIdentifier"),
// 		mRNA_IDENTIFIER("mRNAIdentifier"),
// 		RNA_IDENTIFIER("RNAIdentifier"),
// 		PROTEIN_IDENTIFIER("ProteinIdentifier"),
// 		DNA_IDENTIFIER("DNAIdentifier");

// 		private final String type;

// 		private IdentifierType(String type) {
// 			this.type = type;
// 		}

// 		public String type() {
// 			return type;
// 		}

// 		public static IdentifierType getIdentifierType(String type) {
// 			if (type.equals("DrugIdentifier")) {
// 				return DRUG_IDENTIFIER;
// 			}
// 			if (type.equals("DiseaseIdentifier")) {
// 				return DISEASE_IDENTIFIER;
// 			}
// 			if (type.equals("ProteinIdentifier")) {
// 				return PROTEIN_IDENTIFIER;
// 			}
// 			if (type.equals("DNAIdentifier")) {
// 				return DNA_IDENTIFIER;
// 			}
// 			if (type.equals("RNAIdentifier")) {
// 				return RNA_IDENTIFIER;
// 			}
// 			if (type.equals("mRNAIdentifier")) {
// 				return mRNA_IDENTIFIER;
// 			}
// 			throw new IllegalArgumentException("Unknown identifier type: " + type);
// 		}

// 	}

// 	private static final String ICE1_BINDING_NAME = "ice1";
// 	private static final String ICE2_BINDING_NAME = "ice2";
// 	private static final String ID_TYPE_BINDING_NAME = "idType";

// 	/**
// 	 * Queries for pairs of ICE URIs connected via skos:exactMatch, also returns the id type for one
// 	 * member of the pair:
// 	 * 
// 	 * <pre>
// 	 * SELECT ?ice1 ?ice2 ?idType 
// 	 * WHERE { ?ice1 <http://www.w3.org/2004/02/skos/core#exactMatch> ?ice2 .
// 	 *  ?ice1 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?idType .
// 	 * }
// 	 * </pre>
// 	 */
// 	private static final String EXACT_MATCH_SPARQL_QUERY = "SELECT ?" + ICE1_BINDING_NAME + " ?" + ICE2_BINDING_NAME
// 			+ " ?" + ID_TYPE_BINDING_NAME + " \n" + "WHERE { ?" + ICE1_BINDING_NAME
// 			+ " <http://www.w3.org/2004/02/skos/core#exactMatch> ?" + ICE2_BINDING_NAME + " .\n" + " ?"
// 			+ ICE1_BINDING_NAME + " <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?" + ID_TYPE_BINDING_NAME
// 			+ " .\n}";

// 	/**
// 	 * Returns a SPARQL query for all ICE URIs for a given {@link IdentifierType}. This query should
// 	 * return only those ICE URIs that were not linked to another ICE URI using the skos:exactMatch
// 	 * predicate.<br>
// 	 * <br>
// 	 * Example:
// 	 * 
// 	 * <pre>
// 	 * SELECT ?ice1 
// 	 * WHERE { ?ice1 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://kabob.ucdenver.edu/iao/ProteinIdentifier> . }
// 	 * </pre>
// 	 * 
// 	 * In the future we might want to try to add the following to cut down on the processing time.
// 	 * They have been left out at this point simply to keep thing simple.
// 	 * 
// 	 * <pre>
// 	 * OPTIONAL { ?ice1 <http://www.w3.org/2004/02/skos/core#exactMatch> ?otherice } . 
// 	 * OPTIONAL { ?otherice2 <http://www.w3.org/2004/02/skos/core#exactMatch> ?ice1 } . 
// 	 * FILTER (!bound(?otherice)||!bound(?otherice2)) }
// 	 * </pre>
// 	 * 
// 	 * @param identifierType
// 	 * @return
// 	 */
// 	private static String getIdentifierSparqlQuery(IdentifierType identifierType) {
// 		return "SELECT ?" + ICE1_BINDING_NAME + " \n" + "WHERE { ?" + ICE1_BINDING_NAME
// 				+ " <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://kabob.ucdenver.edu/iao/"
// 				+ identifierType.type() + "> . }";
// 		// \n" + "OPTIONAL { ?" + ICE1_BINDING_NAME
// 		// + " <http://www.w3.org/2004/02/skos/core#exactMatch> ?otherice } . \n" +
// 		// "FILTER (!bound(?otherice)) }";
// 	}

// 	private KabobEntityGenerator() {
// 		// constructor should not be instantiated
// 	}

// 	/**
// 	 * This method builds a network based on the skos:exactMatch identifier pairs in a specified
// 	 * BigData journal file. Orphan nodes are then added to the network, and the resulting network
// 	 * is used to produce "kabob entities", where each "kabob entity" serves to merge identifiers
// 	 * related by skos:exactMatch.
// 	 * 
// 	 * @param journalFile
// 	 * @param propertiesFile
// 	 * @param skosNetworkDirectory
// 	 * @param skosNetworkBackupDirectory
// 	 * @param kabobEntityRdfOutputFile
// 	 * @throws IOException
// 	 *             if an error occurs while backing up the Neo4j graph
// 	 */
// 	public static void mergeEntitiesFromBigDataKb(File journalFile, File propertiesFile, File skosNetworkDirectory,
// 			File skosNetworkBackupDirectory, File kabobEntityRdfOutputFile) throws IOException {
// 		Neo4jNetworkSettings networkSettings = new Neo4jNetworkSettings(skosNetworkDirectory,
// 				skosNetworkBackupDirectory);
// 		// try {
// 		// createSkosExactMatchNetworkBuilderFromBigDataKb(journalFile, propertiesFile,
// 		// networkSettings);
// 		// Neo4jUtil.logGraphStatistics(networkSettings.getNeo4jGraphSettings().getGraphDirectory(),
// 		// logger);
// 		// } catch (RepositoryException e) {
// 		// throw new RuntimeException(e);
// 		// } catch (QueryEvaluationException e) {
// 		// throw new RuntimeException(e);
// 		// }
// 		// validateSkosExactMatchNetwork(networkSettings);
// 		// Neo4jUtil.logGraphStatistics(networkSettings.getNeo4jGraphSettings().getGraphDirectory(),
// 		// logger);
// 		//
// 		// // new Neo4jNetworkBuilder(networkSettings).backupGraph("skos-exact-match-merged");
// 		// //
// 		// Neo4jUtil.logGraphStatistics(networkSettings.getNeo4jGraphSettings().getGraphDirectory(),
// 		// logger);
// //		addOrphanEntitiesFromBigDataKbToSkosMatchNetwork(journalFile, propertiesFile, networkSettings);
// 		// // new
// 		// Neo4jNetworkBuilder(networkSettings).backupGraph("skos-exact-match-with-orphan-nodes");
// 		 outputKabobEntityRdf(skosNetworkDirectory, kabobEntityRdfOutputFile);
// 	}

// 	/**
// 	 * Performs simple validation checks, e.g. there should be no Entrez Gene Identifier collapse
// 	 * 
// 	 * @param networkSettings
// 	 */
// 	private static void validateSkosExactMatchNetwork(Neo4jNetworkSettings networkSettings) {
// 		GraphDatabaseService graphDb = Neo4jUtil.loadOrCreateGraph(networkSettings.getNeo4jGraphSettings()
// 				.getGraphDirectory());
// 		Transaction tx = graphDb.beginTx();
// 		try {
// 			int count = 0;
// 			for (Node node : GlobalGraphOperations.at(graphDb).getAllNodes()) {
// 				if (count++ % 1000 == 0) {
// 					logger.info("Node validation progress: " + (count - 1));
// 				}
// 				if (hasMultipleEntrezGeneIdentifiers(node)) {
// 					throw new RuntimeException(
// 							"Merging error detected. Collapsed Entrez Gene Identifiers detected: Node " + node.getId()
// 									+ " -- " + Neo4jUtil.getProperties(node));
// 				}
// 			}
// 			logger.info("Graph validation successful.");
// 			tx.success();
// 		} finally {
// 			tx.finish();
// 			graphDb.shutdown();
// 		}

// 	}

// 	/**
// 	 * @param node
// 	 * @return
// 	 */
// 	private static boolean hasMultipleEntrezGeneIdentifiers(Node node) {
// 		Map<String, Object> properties = Neo4jUtil.getProperties(node);
// 		if (properties.containsKey(DataSource.EG.name())) {
// 			String egIds = properties.get(DataSource.EG.name()).toString();
// 			if (egIds.split(Neo4jIdentifierNodeUtil.ID_STORAGE_DELIMITER_REGEX).length > 1) {
// 				return true;
// 			}
// 		}
// 		return false;
// 	}

// 	/**
// 	 * @param journalFile
// 	 * @param propertiesFile
// 	 * @param networkSettings
// 	 */
// 	private static void addOrphanEntitiesFromBigDataKbToSkosMatchNetwork(File journalFile, File propertiesFile,
// 			Neo4jNetworkSettings networkSettings) {
// 		logger.info("Adding orphan nodes (nodes not connected via skos:exactMatch) to the graph...");
// 		alreadyInsertedIdNodes = new HashSet<DataSourceIdentifier<?>>();
// 		idNodeInsertCount = 0;
// 		for (IdentifierType idType : IdentifierType.values()) {
// 			try {
// 				insertIdNodesOfType(journalFile, propertiesFile, networkSettings, idType);
// 			} catch (RepositoryException e) {
// 				throw new RuntimeException(e);
// 			} catch (QueryEvaluationException e) {
// 				throw new RuntimeException(e);
// 			}
// 		}
// 	}

// 	/**
// 	 * Queries a BigData journal for ID URIs of the input {@link IdentifierType} and inserts them
// 	 * into the specified skos:match network as orphan nodes if they are not already present in the
// 	 * network.
// 	 * 
// 	 * @param journalFile
// 	 * @param propertiesFile
// 	 * @param networkSettings
// 	 * @param idType
// 	 * @throws RepositoryException
// 	 * @throws QueryEvaluationException
// 	 */
// 	private static void insertIdNodesOfType(File journalFile, File propertiesFile,
// 			Neo4jNetworkSettings networkSettings, IdentifierType idType) throws RepositoryException,
// 			QueryEvaluationException {
// 		long startTime = System.currentTimeMillis();
// 		SkosMatchNetworkBuilder networkBuilder = initSkosMatchNetworkBuilder(networkSettings);
// 		TupleQueryResult result = null;
// 		BigdataSailRepository sailRepository = null;
// 		BigdataSailRepositoryConnection connection = null;
// 		try {
// 			logger.info("Executing query: " + getIdentifierSparqlQuery(idType));
// 			Properties p = PropertiesUtil.loadProperties(propertiesFile);
// 			p.setProperty(BigdataSail.Options.FILE, journalFile.getAbsolutePath());
// 			p.setProperty(BigdataSail.Options.NAMESPACE, "kabob");
// 			BigdataSail sail = new BigdataSail(p);
// 			sailRepository = new BigdataSailRepository(sail);
// 			sailRepository.initialize();
// 			connection = sailRepository.getConnection();
// 			connection.setAutoCommit(false);
// 			TupleQuery query = connection.prepareTupleQuery(QueryLanguage.SPARQL, getIdentifierSparqlQuery(idType));
// 			query.setIncludeInferred(false);
// 			long time = System.currentTimeMillis();
// 			result = query.evaluate();
// 			logger.info("Elapsed time for query: " + ((System.currentTimeMillis() - time) / 1000.0) + "s. \nQuery: "
// 					+ getIdentifierSparqlQuery(idType));
// 			int count = 0;
// 			logger.info("Query result binding names: " + result.getBindingNames().toString());
// 			while (result.hasNext()) {
// 				if (count++ % 10000 == 0) {
// 					logger.info("Node (" + idType.name() + ") insertion progress: " + (count - 1));
// 				}
// 				BindingSet bindingSet = result.next();
// 				insertIdNode(networkBuilder, bindingSet, idType);
// 			}
// 			logger.info("Node (" + idType.name() + ") insertion complete. Elapsed time: "
// 					+ ((System.currentTimeMillis() - startTime) / 1000) + "s");
// 		} catch (MalformedQueryException e) {
// 			throw new RuntimeException(e);
// 		} catch (QueryEvaluationException e) {
// 			throw new RuntimeException(e);
// 		} finally {
// 			networkBuilder.close();
// 			if (result != null) {
// 				result.close();
// 			}
// 			if (connection != null) {
// 				connection.close();
// 			}
// 			if (sailRepository != null) {
// 				sailRepository.shutDown();
// 			}
// 		}
// 	}

// 	/**
// 	 * Extracts single ICE URI from the input {@link BindingSet} and inserts the corresponding
// 	 * {@link DataSourceIdentifier} into the skos:match network. To ensure no duplicate nodes are
// 	 * inserted into the graph, this method occasionally flushes the idNode index. Normal behavior
// 	 * stores the ID in the alreadyInsertedIdNodes Set then inserts the id into the network. For
// 	 * every N insertions, when N is a multiple of {@link #ID_NODE_INSERT_INDEX_FLUSH_THRESHOLD} the
// 	 * alreadyInsertedIdNodes Set is reset and the idNode index is flushed after insertion. This
// 	 * will slow the insertion process somewhat (turns out the the slowdown is minimal, an extra 5.5
// 	 * minutes for 8+ million inserts), but is worth the slowdown by preventing duplicate nodes from
// 	 * being inserted unintentionally. In between index flushes, the alreadyInsertedIdNodes Set
// 	 * prevents duplicate IDs from being inserted.
// 	 * 
// 	 * @param networkBuilder
// 	 * @param bindingSet
// 	 * @param idType
// 	 */
// 	private static void insertIdNode(SkosMatchNetworkBuilder networkBuilder, BindingSet bindingSet,
// 			IdentifierType idType) {
// 		String ice = bindingSet.getBinding(ICE1_BINDING_NAME).getValue().stringValue();
// 		DataSourceIdentifier<?> id = getIdentifierFromIceUri(ice);
// 		if (!alreadyInsertedIdNodes.contains(id)) {
// 			if (idNodeInsertCount++ % ID_NODE_INSERT_INDEX_FLUSH_THRESHOLD == 0) {
// 				alreadyInsertedIdNodes = new HashSet<DataSourceIdentifier<?>>();
// 				networkBuilder.insertIdentifierNode(id, idType.type(), IndexOperation.FLUSH);
// 			} else {
// 				alreadyInsertedIdNodes.add(id);
// 				networkBuilder.insertIdentifierNode(id, idType.type(), IndexOperation.NONE);
// 			}
// 		}
// 	}

// 	/**
// 	 * @param networkSettings
// 	 * @return an initialized {@link SkosMatchNetworkBuilder}
// 	 */
// 	private static SkosMatchNetworkBuilder initSkosMatchNetworkBuilder(Neo4jNetworkSettings networkSettings) {
// 		Neo4jIdentifierNodeInserter idNodeInserter = new Neo4jIdentifierNodeInserter(networkSettings);
// 		Neo4jIdentifierEdgeInserter idEdgeInserter = new Neo4jIdentifierEdgeInserter(networkSettings,
// 				Neo4jSkosMatchRelationshipType.class);
// 		Neo4jIdentifierNodeMerger idNodeMerger = new Neo4jIdentifierNodeMerger(networkSettings);
// 		return new SkosMatchNetworkBuilder(networkSettings, idNodeInserter, idEdgeInserter, idNodeMerger);
// 	}

// 	/**
// 	 * Queries a BigData journal file for the skos:exactMatch entity pairings
// 	 * 
// 	 * @param journalFile
// 	 * @param propertiesFile
// 	 * @param networkSettings
// 	 * @throws RepositoryException
// 	 * @throws QueryEvaluationException
// 	 * @throws SailException
// 	 */
// 	private static void createSkosExactMatchNetworkBuilderFromBigDataKb(File journalFile, File propertiesFile,
// 			Neo4jNetworkSettings networkSettings) throws RepositoryException, QueryEvaluationException {
// 		logger.info("Creating SKOS:EXACTMATCH Network...");
// 		long startTime = System.currentTimeMillis();
// 		new Neo4jNetworkBuilder(networkSettings).cleanNetwork();
// 		SkosMatchNetworkBuilder networkBuilder = initSkosMatchNetworkBuilder(networkSettings);
// 		TupleQueryResult result = null;
// 		BigdataSailRepository sailRepository = null;
// 		BigdataSailRepositoryConnection connection = null;
// 		try {
// 			logger.info("Executing query: " + EXACT_MATCH_SPARQL_QUERY);
// 			Properties p = PropertiesUtil.loadProperties(propertiesFile);
// 			p.setProperty(BigdataSail.Options.FILE, journalFile.getAbsolutePath());
// 			p.setProperty(BigdataSail.Options.NAMESPACE, "kabob");
// 			BigdataSail sail = new BigdataSail(p);
// 			sailRepository = new BigdataSailRepository(sail);
// 			sailRepository.initialize();
// 			connection = sailRepository.getConnection();
// 			connection.setAutoCommit(false);
// 			TupleQuery query = connection.prepareTupleQuery(QueryLanguage.SPARQL, EXACT_MATCH_SPARQL_QUERY);
// 			query.setIncludeInferred(false);
// 			long time = System.currentTimeMillis();
// 			result = query.evaluate();
// 			logger.info("Elapsed time for query: " + ((System.currentTimeMillis() - time) / 1000.0) + "s. \nQuery: "
// 					+ EXACT_MATCH_SPARQL_QUERY);
// 			int count = 0;
// 			logger.info("Query result binding names: " + result.getBindingNames().toString());
// 			while (result.hasNext()) {
// 				if (count++ % 1000 == 0) {
// 					logger.info("Exact match insertion progress: " + (count - 1));
// 				}
// 				BindingSet bindingSet = result.next();
// 				addExactMatchPair(networkBuilder, bindingSet);
// 			}
// 			logger.info("Exact match insertion complete. Elapsed time: "
// 					+ ((System.currentTimeMillis() - startTime) / 1000) + "s");
// 			networkBuilder.mergeExactMatches();
// 		} catch (MalformedQueryException e) {
// 			throw new RuntimeException(e);
// 		} catch (QueryEvaluationException e) {
// 			throw new RuntimeException(e);
// 		} finally {
// 			networkBuilder.close();
// 			if (result != null) {
// 				result.close();
// 			}
// 			if (connection != null) {
// 				connection.close();
// 			}
// 			if (sailRepository != null) {
// 				sailRepository.shutDown();
// 			}
// 		}
// 	}

// 	/**
// 	 * @param networkBuilder
// 	 * @param bindingSet
// 	 */
// 	private static void addExactMatchPair(SkosMatchNetworkBuilder networkBuilder, BindingSet bindingSet) {
// 		String ice1 = bindingSet.getBinding(ICE1_BINDING_NAME).getValue().stringValue();
// 		String ice2 = bindingSet.getBinding(ICE2_BINDING_NAME).getValue().stringValue();
// 		String idType = bindingSet.getBinding(ID_TYPE_BINDING_NAME).getValue().stringValue();
// 		DataSourceIdentifier<?> id1 = getIdentifierFromIceUri(ice1);
// 		DataSourceIdentifier<?> id2 = getIdentifierFromIceUri(ice2);
// 		logger.debug("Inserting EXACT match between identifiers: " + id1.getDataSource() + ";"
// 				+ id1.getDataElement().toString() + " -- " + id2.getDataSource() + ";"
// 				+ id2.getDataElement().toString() + "  ID TYPE=" + idType);
// 		networkBuilder.insertExactMatch(id1, id2, idType);
// 	}

// 	/**
// 	 * Converts from the input ICE URI to a {@link DataSourceIdentifier}.<br>
// 	 * For example,
// 	 * 
// 	 * <pre>
// 	 * <http://kabob.ucdenver.edu/iao/eg/EG_3280_ICE>
// 	 * </pre>
// 	 * 
// 	 * is broken down into the EG data source + "EG_3280_ICE" which is converted to "3280" and then
// 	 * returned as an {@link EntrezGeneID}
// 	 * 
// 	 * @param iceUriStr
// 	 * @return
// 	 */
// 	private static DataSourceIdentifier<?> getIdentifierFromIceUri(String iceUriStr) {
// 		int namespaceEndIndex = Math.max(iceUriStr.lastIndexOf('/'), iceUriStr.lastIndexOf('#')) + 1;
// 		String namespace = iceUriStr.substring(0, namespaceEndIndex);
// 		RdfNamespace iceNamespace = RdfNamespace.getNamespace(namespace); // should begin with KIAO
// 		RdfNamespace nonKiaoIceNamespace = RdfNamespace.valueOf(StringUtil.removePrefix(iceNamespace.name(), "KIAO"));
// 		DataSource dataSource = RdfUtil.getDataSource(nonKiaoIceNamespace);
// 		String iceId = iceUriStr.substring(namespaceEndIndex);
// 		String idPlusDataSource = StringUtil.removeSuffix(iceId, "_ICE");
// 		String idStr = StringUtil.removePrefix(idPlusDataSource, dataSource.name() + "_");
// 		if (!dataSource.equals(DataSource.REFSEQ)) {
// 			idStr = idStr.replaceFirst("_", ":"); // converts MGI_12345 to the expected MGI:12345;
// 													// need to skip this for RefSeq identifiers
// 		}
// 		return DataSourceIdResolver.resolveId(dataSource, idStr);
// 	}

// 	/**
// 	 * @param skosNetworkDirectory
// 	 * @param kabobEntityRdfOutputFile
// 	 */
// 	private static void outputKabobEntityRdf(File skosNetworkDirectory, File kabobEntityRdfOutputFile) {
// 		logger.info("Outputting KaBOB entities to RDF...");
// 		RDFWriter rdfWriter = RdfUtil.openWriter(kabobEntityRdfOutputFile, CharacterEncoding.UTF_8, RdfFormat.NTRIPLES);
// 		Map<IdentifierType, Integer> kabobEntityCountMap = initKabobEntityCountMap();
// 		GraphDatabaseService graphDb = null;

// 		try {
// 			graphDb = Neo4jUtil.loadOrCreateGraph(skosNetworkDirectory);
// 			if (Neo4jUtil.exactRelationshipCount(graphDb) > 0) {
// 				throw new IllegalStateException(
// 						"There should be no relationships in this graph as all skos:exactMatches should have been merged.");
// 			}
// 			int count = 0;
// 			for (Node node : GlobalGraphOperations.at(graphDb).getAllNodes()) {
// 				if (node.getId() != 0) { // skip node 0 which is always in a neo4j graph
// 											// (automatically)
// 					if (count++ % 10000 == 0) {
// 						logger.info("RDF Serialization progress: " + (count - 1));
// 					}
// 					URI kabobEntityUri = getKabobEntityUri(node, kabobEntityCountMap);
// 					for (Entry<DataSource, Set<DataSourceIdentifier<?>>> entry : Neo4jIdentifierNodeUtil
// 							.getDataSourceIdentifiers(node).entrySet()) {
// 						if (!entry.getKey().equals(DataSource.PR)) {
// 							for (DataSourceIdentifier<?> id : entry.getValue()) {
// 								URI idUri = getDenotingIceUri(id);
// 								Statement stmt = new StatementImpl(idUri, IAO_DENOTES, kabobEntityUri);
// 								try {
// 									rdfWriter.handleStatement(stmt);
// 								} catch (RDFHandlerException e) {
// 									throw new RuntimeException(e);
// 								}
// 							}
// 						}
// 					}
// 				}
// 			}
// 		} finally {
// 			RdfUtil.closeWriter(rdfWriter);
// 			if (graphDb != null) {
// 				graphDb.shutdown();
// 			}
// 		}

// 		logger.info("Validating RDF in serialized file: " + kabobEntityRdfOutputFile.getAbsolutePath());
// 		RdfValidator rdfValidator = RdfValidatorFactory.getRdfValidator(RdfFormat.NTRIPLES);
// 		if (rdfValidator.isValidRdf(kabobEntityRdfOutputFile, CharacterEncoding.UTF_8, "http://kabob", logger)) {
// 			logger.info("RDF validation successful.");
// 		} else {
// 			throw new IllegalStateException("Invalid RDF detected. Please check the log for details.");
// 		}

// 	}

// 	private static URI getDenotingIceUri(DataSourceIdentifier<?> id) {
// 		RdfNamespace ns = RdfNamespace.valueOf("KIAO" + id.getDataSource().name());
// 		return new URIImpl(RdfUtil.createUri(ns.longName(), new RdfId(id).getICE_ID().toString(), id.getDataSource())
// 				.toString());
// 	}

// 	/**
// 	 * If the input node represents a protein identifier, then we check to see if it contains an
// 	 * ontology term from the Protein Ontology. If it does, then we use that as its URI. If not,
// 	 * then we simply create a generic Kabob entity URI as we do with the other identifier types.
// 	 * 
// 	 * @param node
// 	 * @param kabobEntityCountMap
// 	 * @return
// 	 */
// 	private static URI getKabobEntityUri(Node node, Map<IdentifierType, Integer> kabobEntityCountMap) {
// 		String idTypeUri = Neo4jIdentifierNodeUtil.getIdType(node);
// 		// logger.debug("idTypeURI: " + idTypeUri);
// 		String idTypeName = idTypeUri.substring(idTypeUri.lastIndexOf('/') + 1);
// 		// logger.debug("idType name: " + idTypeName);
// 		IdentifierType idType = IdentifierType.getIdentifierType(idTypeName);
// 		if (idType.equals(IdentifierType.PROTEIN_IDENTIFIER)) {
// 			Map<DataSource, Set<DataSourceIdentifier<?>>> dataSourceToIdsMap = Neo4jIdentifierNodeUtil
// 					.getDataSourceIdentifiers(node);
// 			if (dataSourceToIdsMap.containsKey(DataSource.PR)) {
// 				DataSourceIdentifier<?> prId = CollectionsUtil.getSingleElement(dataSourceToIdsMap.get(DataSource.PR));
// 				return new URIImpl(RdfUtil
// 						.createUri(RdfNamespace.valueOf(prId.getDataSource().name()), prId.toString()).toString());
// 			}
// 		}
// 		return getKabobEntityUri(idType, kabobEntityCountMap);
// 	}

// 	/**
// 	 * @return
// 	 */
// 	private static Map<IdentifierType, Integer> initKabobEntityCountMap() {
// 		Map<IdentifierType, Integer> kabobEntityCountMap = new HashMap<KabobEntityGenerator.IdentifierType, Integer>();
// 		for (IdentifierType idType : IdentifierType.values()) {
// 			kabobEntityCountMap.put(idType, 0);
// 		}
// 		return kabobEntityCountMap;
// 	}

// 	/**
// 	 * @param idType
// 	 * @param kabobEntityCountMap
// 	 * @return a generic URI representing one of the KABOB Entity types
// 	 */
// 	private static URI getKabobEntityUri(IdentifierType idType, Map<IdentifierType, Integer> kabobEntityCountMap) {
// 		int count = CollectionsUtil.addToCountMap(idType, kabobEntityCountMap);
// 		switch (idType) {
// 		case DNA_IDENTIFIER:
// 			return new URIImpl(RdfUtil.createUri(RdfNamespace.KABOB, "KABOB_DNA_" + count).toString());
// 		case DISEASE_IDENTIFIER:
// 			return new URIImpl(RdfUtil.createUri(RdfNamespace.KABOB, "KABOB_DISEASE_" + count).toString());
// 		case DRUG_IDENTIFIER:
// 			return new URIImpl(RdfUtil.createUri(RdfNamespace.KABOB, "KABOB_DRUG_" + count).toString());
// 		case PROTEIN_IDENTIFIER:
// 			return new URIImpl(RdfUtil.createUri(RdfNamespace.KABOB, "KABOB_PROTEIN_" + count).toString());
// 		case RNA_IDENTIFIER:
// 			return new URIImpl(RdfUtil.createUri(RdfNamespace.KABOB, "KABOB_RNA_" + count).toString());
// 		case mRNA_IDENTIFIER:
// 			return new URIImpl(RdfUtil.createUri(RdfNamespace.KABOB, "KABOB_mRNA_" + count).toString());
// 		default:
// 			throw new IllegalArgumentException("Unknown IdentifierType: " + idType.name());
// 		}
// 	}

// 	/**
// 	 * Use to produce RDF of Kabob entities, querying from a BigData journal file
// 	 * 
// 	 * @param args
// 	 *            args[0]: BigData journal file<br>
// 	 *            args[1]: BigData properties file<br>
// 	 *            args[2]: work directory (where Neo4j graph files will be stored)<br>
// 	 *            args[3]: backup directory for the SkosExactMatch network (backups are produced
// 	 *            during the processing)<br>
// 	 *            args[4]: resulting RDF output file containing Kabob entity RDF
// 	 */
// 	public static final void main(String[] args) {
// 		BasicConfigurator.configure();
// 		Logger.getRootLogger().setLevel(Level.INFO);

// 		File journalFile = new File(args[0]);
// 		File propertiesFile = new File(args[1]);
// 		File skosNetworkDirectory = new File(args[2]);
// 		File skosNetworkBackupDirectory = new File(args[3]);
// 		File kabobEntityRdfOutputFile = new File(args[4]);

// 		try {
// 			FileUtil.validateFile(journalFile);
// 			FileUtil.validateFile(propertiesFile);
// 			FileUtil.validateDirectory(skosNetworkDirectory);
// 			FileUtil.validateDirectory(skosNetworkBackupDirectory);
// 			FileUtil.validateDirectory(kabobEntityRdfOutputFile.getParentFile());
// 			mergeEntitiesFromBigDataKb(journalFile, propertiesFile, skosNetworkDirectory, skosNetworkBackupDirectory,
// 					kabobEntityRdfOutputFile);
// 		} catch (IOException e) {
// 			throw new IllegalArgumentException(e);
// 		}

// 	}

// }
