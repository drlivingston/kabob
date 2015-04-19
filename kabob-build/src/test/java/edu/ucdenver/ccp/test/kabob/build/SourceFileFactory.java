// /**
//  * 
//  */
// package edu.ucdenver.ccp.test.kabob.build;

// import java.io.File;
// import java.io.FileNotFoundException;
// import java.io.IOException;
// import java.util.Collection;
// import java.util.List;

// import org.apache.log4j.BasicConfigurator;

// import edu.ucdenver.ccp.common.collections.CollectionsUtil;
// import edu.ucdenver.ccp.common.file.CharacterEncoding;
// import edu.ucdenver.ccp.common.file.FileUtil;
// import edu.ucdenver.ccp.common.file.FileWriterUtil;
// import edu.ucdenver.ccp.common.file.FileWriterUtil.FileSuffixEnforcement;
// import edu.ucdenver.ccp.common.file.FileWriterUtil.WriteMode;
// import edu.ucdenver.ccp.datasource.fileparsers.RecordReader;
// import edu.ucdenver.ccp.fileparsers.ncbi.gene.EntrezGene2RefseqFileParser;
// import edu.ucdenver.ccp.rdfizer.rdf.NoOpDuplicateTripleFilter;
// import edu.ucdenver.ccp.rdfizer.rdf.RdfRecordWriterImpl;
// import edu.ucdenver.ccp.rdfizer.rdf.RdfUtil.RdfFormat;

// /**
//  * this class contains factory methods that create ICE RDF
//  * 
//  * @author Center for Computational Pharmacology, UC Denver; ccpsupport@ucdenver.edu
//  * 
//  */
// public class SourceFileFactory {

// 	/**
// 	 * @return a sample gene2refseq file
// 	 * @throws IOException
// 	 */
// 	public static File createFile_gene2refseq() throws IOException {
// 		File file = File.createTempFile("gene2refseq", "");
// 		List<String> lines = CollectionsUtil
// 				.createList(
// 						"#Format: tax_id GeneID status RNA_nucleotide_accession.version RNA_nucleotide_gi protein_accession.version protein_gi genomic_nucleotide_accession.version genomic_nucleotide_gi start_position_on_the_genomic_accession end_position_on_the_genomic_accession orientation assembly (tab is used as a separator, pound sign - start of a comment)",
// 						"9606\t23\tREVIEWED\tNM_001025091.1\t69354670\tNP_001020262.1\t69354671\tNT_167246.1\t224515579\t1887241\t1907380\t+\tReference GRCh37.p10 ALT_REF_LOCI_4");
// 		FileWriterUtil.printLines(lines, file, CharacterEncoding.UTF_8, WriteMode.OVERWRITE, FileSuffixEnforcement.OFF);
// 		return file;
// 	}

// 	/**
// 	 * @return a file containing ICE RDF from the gene2refseq file
// 	 * @throws IOException
// 	 */
// 	public static File createIceRdf_gene2refseq() throws IOException {
// 		File sourceFile = createFile_gene2refseq();
// 		EntrezGene2RefseqFileParser parser = new EntrezGene2RefseqFileParser(sourceFile, CharacterEncoding.UTF_8);
// 		File rdfFile = outputRdf(parser);
// 		sourceFile.delete();
// 		return rdfFile;

// 	}

// 	/**
// 	 * @param recordReader
// 	 * @return a file containing ICE RDFs
// 	 * @throws FileNotFoundException
// 	 * @throws IOException
// 	 */
// 	private static File outputRdf(RecordReader<?> recordReader) throws FileNotFoundException, IOException {
// 		boolean compress = false;
// 		File tempDir = FileUtil.createTemporaryDirectory("tmp");
// 		RdfRecordWriterImpl<?> recordWriter = new RdfRecordWriterImpl(tempDir, RdfFormat.NTRIPLES, compress, -1, 0,
// 				new NoOpDuplicateTripleFilter());
// 		Collection<File> generatedRdfFiles = recordWriter.processRecordReader(recordReader, System.currentTimeMillis(),
// 				0, -1);
// 		if (generatedRdfFiles.size() != 2) {
// 			throw new IllegalStateException(
// 					"Expected 2 files (one for the schema and one for the triples) to be generated, but there were "
// 							+ generatedRdfFiles.size() + " files generated instead.");
// 		}
// 		for (File f : generatedRdfFiles) {
// 			if (!f.getName().contains("schema")) {
// 				return f;
// 			}
// 		}
// 		throw new IllegalStateException("There were no files without 'schema' in their file names to return.");
// 	}

// }
