(ns kabob.core.namespace
  (use kr.core.kb
       kr.core.rdf))

(def ^:dynamic *namespaces*
     '(;;standard namespaces
       ("rdf" "http://www.w3.org/1999/02/22-rdf-syntax-ns#")
       ("rdfs" "http://www.w3.org/2000/01/rdf-schema#")
       ("owl" "http://www.w3.org/2002/07/owl#")
       ("xsd" "http://www.w3.org/2001/XMLSchema#")

       ;;testing
       ("ex" "http://www.example.com/")

       ;;a few bonus namespaces
       ("skos" "http://www.w3.org/2004/02/skos/core#")
       ("vcard" "http://www.w3.org/2001/vcard-rdf/3.0#")
       ("rss" "http://purl.org/rss/1.0/")
       ("daml" "http://www.daml.org/2001/03/daml+oil#")
       ("dc" "http://purl.org/dc/elements/1.1/")
       ("dcterms" "http://purl.org/dc/terms/")
       ("oboInOwl" "http://www.geneontology.org/formats/oboInOwl#")
       ("foaf" "http://xmlns.com/foaf/0.1/")

       ("ja" "http://jena.hpl.hp.com/2005/11/Assembler#")


       ("obo" "http://purl.obolibrary.org/obo/")


       ;;("iao" "http://purl.obolibrary.org/obo/")
       ("ccp" "http://ccp.ucdenver.edu/obo/ext/")

        ;; ---------------------------------------------------------------------------
        ;; the following namespaces are each a workaround for the 'http:/' bug as
        ;; documented here: https://trello.com/c/6adKzKOB. These namespaces can/should
        ;; be removed once the 'http:/' bug is resolved.
        ;; ---------------------------------------------------------------------------
        ("bnode" "http://ccp.ucdenver.edu/bnode/")
        ("cgnc_pr","http://birdgenenames.org/cgnc/")
        ("rgd_pr","http://rgd.mcw.edu/rgdweb/report/gene/")
        ("tair_pr","http://www.arabidopsis.org/servlets/")
        ("hgnc_pr","http://www.genenames.org/cgi-bin/")
        ("sgd_pr","http://www.yeastgenome.org/cgi-bin/")
        ("flybase_pr","http://flybase.org/reports/")
        ("eco_pr","http://www.ecogene.org/gene/")
        ("pombase_pr","http://www.pombase.org/spombe/result/")
        ("wormbase_pr","http://www.wormbase.org/species/c_elegans/gene/")
        ("zfin_pr","http://zfin.org/action/marker/view/")
        ("mgi_pr","http://www.informatics.jax.org/marker/")
        ("ncbi_pr","http://www.ncbi.nlm.nih.gov/")
        ;; ---------------------------------------------------------------------------

       ;("kiao" "http://kabob.ucdenver.edu/iao/")
       ;("kabob" "http://kabob.ucdenver.edu/")
       ;("kbio" "http://kabob.ucdenver.edu/bio/")
       ;
       ;;;in domain
       ;("bfo" "http://www.ifomis.org/bfo/1.1#")
       ;("iaobfo" "http://kabob.ucdenver.edu/iao/bfo/")
       ;
       ;;;("bto" "http://purl.org/obo/owl/BTO#")
       ;("iaobto" "http://kabob.ucdenver.edu/iao/bto/")
       ;
       ;;;("chebi" "http://purl.org/obo/owl/CHEBI#")
       ;("iaochebi" "http://kabob.ucdenver.edu/iao/chebi/")
       ;
       ;;;("cl" "http://purl.org/obo/owl/CL#")
       ;("iaocl" "http://kabob.ucdenver.edu/iao/cl/")
       ;
       ;("dip" "http://dip.doe-mbi.ucla.edu/dip/")
       ;("iaodip" "http://kabob.ucdenver.edu/iao/dip/")
       ;
       ;;;("drugbank" "")
       ;("iaodrugbank" "http://kabob.ucdenver.edu/iao/drugbank/")
       ;
       ;("ecocyc" "http://ecocyc.org/")
       ;("iaoecocyc" "http://kabob.ucdenver.edu/iao/ecocyc/")
       ;
       ;("eg" "http://www.ncbi.nlm.nih.gov/gene/")
       ;("iaoeg" "http://kabob.ucdenver.edu/iao/eg/")
       ;
       ;("embl" "http://www.ebi.ac.uk/embl/")
       ;("iaoembl" "http://kabob.ucdenver.edu/iao/embl/")
       ;
       ;("gad" "http://geneticassociationdb.nih.gov/")
       ;("iaogad" "http://kabob.ucdenver.edu/iao/gad/")
       ;
       ;("gensat" "http://gensat.org/")
       ;("iaogensat" "http://kabob.ucdenver.edu/iao/gensat/")
       ;
       ;;;("go" "http://purl.org/obo/owl/GO#")
       ;("iaogo" "http://kabob.ucdenver.edu/iao/go/")
       ;
       ;("goa" "http://www.ei.ac.uk/GOA/")
       ;("iaogoa" "http://kabob.ucdenver.edu/iao/goa/")
       ;
       ;;("hgnc" "http://www.genenames.org/hgnc/")
       ;;("iaohgnc" "http://kabob.ucdenver.edu/iao/hgnc/")
       ;
       ;("homologene" "http://www.ncbi.nlm.nih.gov/homologene/")
       ;("iaohomologene" "http://kabob.ucdenver.edu/iao/homologene/")
       ;
       ;("iaohp" "http://kabob.ucdenver.edu/iao/hp/")
       ;
       ;("hprd" "http://www.hprd.org/")
       ;("iaohprd" "http://kabob.ucdenver.edu/iao/hprd/")
       ;
       ;("interpro" "http://www.ebi.ac.uk/interpro/")
       ;("iaointerpro" "http://kabob.ucdenver.edu/iao/interpro/")
       ;
       ;("irefweb" "http://wodaklab.org/iRefWeb/")
       ;("iaoirefweb" "http://kabob.ucdenver.edu/iao/irefweb/")
       ;
       ;("kegg" "http://www.genome.jp/kegg/")
       ;("iaokegg" "http://kabob.ucdenver.edu/iao/kegg/")
       ;
       ;("obomi" "http://purl.obolibrary.org/obo/mi#")
       ;;;("mi" "http://purl.org/obo/owl/MI#")
       ;("iaomi" "http://kabob.ucdenver.edu/iao/mi/")
       ;
       ;("mgi" "http://www.informatics.jax.org/")
       ;("iaomgi" "http://kabob.ucdenver.edu/iao/mgi/")
       ;
       ;;;("mod" "http://purl.org/obo/owl/MOD#")
       ;("iaomod" "http://kabob.ucdenver.edu/iao/mod/")
       ;
       ;;;("mpheno" "http://purl.org/obo/owl/MP#")
       ;("iaompheno" "http://kabob.ucdenver.edu/iao/mpheno/")
       ;
       ;;;("ncbitaxon" "http://purl.org/obo/owl/NCBITaxon#")
       ;("iaoncbitaxon" "http://kabob.ucdenver.edu/iao/ncbitaxon/")
       ;
       ;("omim" "http://www.ncbi.nlm.nih.gov/omim/")
       ;("iaoomim" "http://kabob.ucdenver.edu/iao/omim/")
       ;
       ;("pharmgkb" "http://www.pharmgkb.org/")
       ;("iaopharmgkb" "http://kabob.ucdenver.edu/iao/pharmgkb/")
       ;
       ;("premod" "http://genomequebec.mcgill.ca/PReMod/")
       ;("iaopremod" "http://kabob.ucdenver.edu/iao/premod/")
       ;
       ;("iaopw" "http://kabob.ucdenver.edu/iao/pw/")
       ;
       ;;;("pr" "http://purl.obolibrary.org/obo/")
       ;("iaopr" "http://kabob.ucdenver.edu/iao/pr/")
       ;
       ;("reactome" "http://www.reactome.org/")
       ;("iaoreactome" "http://kabob.ucdenver.edu/iao/reactome/")
       ;
       ;("refseq" "http://www.ncbi.nlm.nih.gov/refseq/")
       ;("iaorefseq" "http://kabob.ucdenver.edu/iao/refseq/")
       ;
       ;;;("rgd" "http://rgd/")
       ;("iaorgd" "http://kabob.ucdenver.edu/iao/rgd/")
       ;
       ;;;("ro" "http://www.obofoundry.org/ro/ro.owl#")
       ;("iaoro" "http://kabob.ucdenver.edu/iao/ro/")
       ;;;("kro" "http://kabob.ucdenver.edu/iao/ro/")
       ;("kro" "http://kabob.ucdenver.edu/ro/")
       ;
       ;;;("so" "http://purl.org/obo/owl/SO#")
       ;("iaoso" "http://kabob.ucdenver.edu/iao/so/")
       ;("kso" "http://kabob.ucdenver.edu/so/")
       ;
       ;("transfac" "http://www.gene-regulation.com/transfac/")
       ;("iaotransfac" "http://kabob.ucdenver.edu/iao/transfac/")
       ;
       ;("uniprot" "http://purl.uniprot.org/uniprot/")
       ;("iaouniprot" "http://kabob.ucdenver.edu/iao/uniprot/")
       ;
       ;("iaouberon" "http://kabob.ucdenver.edu/iao/uberon/")

       ("iaohan" "http://kabob.ucdenver.edu/iao/hanalyzer/")
       ))

