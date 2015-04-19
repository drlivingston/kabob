#!/bin/bash

#NOTE: due to CD'ing in script use absolute file names!!

EXPECTED_ARGS=2

if [ $# -ne $EXPECTED_ARGS ]
then
  echo "Usage: LOG_FILE TARGET_DOWNLOAD_DIR"
  exit 1
fi

LOG_FILE=$1
TARGET_DIR=$2

#ROOT_URL="http://purl.org/obo/obo-all/"
ROOT_URL="http://purl.obolibrary.org/obo/uberon/ext.owl"
EXTENSION=".owl"

# URLS="http://purl.org/obo/obo-all/relationship/relationship.owl
# http://purl.org/obo/obo-all/ro_proposed/ro_proposed.owl
# http://purl.org/obo/obo-all/biological_process/biological_process.owl
# http://purl.org/obo/obo-all/cell/cell.owl
# http://purl.org/obo/obo-all/cellular_component/cellular_component.owl
# http://purl.org/obo/obo-all/biological_process_xp_cell/biological_process_xp_cell.owl
# http://purl.org/obo/obo-all/biological_process_xp_cellular_component/biological_process_xp_cellular_component.owl
# http://purl.org/obo/obo-all/biological_process_xp_self/biological_process_xp_self.owl"


NAMES="uberon"


mkdir -p $TARGET_DIR
cd $TARGET_DIR

touch $LOG_FILE

for name in $NAMES
do
  #url=${ROOT_URL}${name}/${name}${EXTENSION}
  url=${ROOT_URL}
  echo "downloading $url" 
  date | tee -a $LOG_FILE
  curl --remote-name --write-out "file: %{filename_effective} final-url: %{url_effective} size: %{size_download} time: %{time_total} final-time: " -L $url | tee -a $LOG_FILE
  date | tee -a $LOG_FILE
done





# biological_process
# biological_process_xp_cell
# biological_process_xp_cellular_component
# biological_process_xp_chebi
# biological_process_xp_multi_organism_process
# biological_process_xp_plant_anatomy
# biological_process_xp_self
# biological_process_xp_uber_anatomy
# cell
# cellular_component
# cellular_component_xp_cell
# cellular_component_xp_go
# cellular_component_xp_self
# chebi
# disease_ontology
# disease_xp_all
# disease_xp_fma
# evidence_code
# fly_anatomy
# flybase_vocab
# # gene_ontology
# # go_xp_all
# # go_xp_chebi
# # go_xp_internal
# # go_xp_regulation

# homology_ontology
# human_phenotype
# # human_phenotype_xp
# # human_phenotype_xp_all
# human_phenotype_xp_anatomy
# human_phenotype_xp_relations
# information_artifact
# interpro
# interpro2go
# mammalian_phenotype
# #mammalian_phenotype_xp
# molecular_function
# molecular_function_xp_chebi
# molecular_function_xp_regulators
# molecular_function_xp_uber_anatomy
# ncbi_taxonomy
# #ncithesaurus
# obi
# pfam2go
# protein
# psi-mi
# reactome2go
# ro
# ro_bfo_bridge1_1
# sequence
# #sequence_xp
# uberon
# # uberon_anatomy_ontologies_bridge
# # worm_anatomy
# # worm_development
# # worm_phenotype
# # worm_phenotype_xp
# # worm_phenotype_xp_anatomy
# # worm_phenotype_xp_anatomy_and_stages
# # worm_phenotype_xp_chebi
# # worm_phenotype_xp_go
# # worm_phenotype_xp_go_chebi
# # worm_phenotype_xp_relations
# # yeast_phenotype





# biological_process
# biological_process_prerelease
# biological_process_xp_cell
# biological_process_xp_cellular_component
# biological_process_xp_chebi
# biological_process_xp_multi_organism_process
# biological_process_xp_plant_anatomy
# biological_process_xp_self
# biological_process_xp_uber_anatomy
# brenda
# caro
# caro_to_bfo
# cdao
# cell
# cell_prerelease
# cellular_component
# cellular_component_xp_cell
# cellular_component_xp_go
# cellular_component_xp_self
# cereal_anatomy
# chebi
# cheminf
# cob
# cog2go
# dendritic_cell
# dendritic_cell_prerelease
# dictyostelium_discoideum_anatomy
# disease_ontology
# disease_xp_all
# disease_xp_fma
# ec2go
# egad2go
# emap
# envo
# envo_prerelease
# envo_xp
# ero
# event
# evidence_code
# evoc
# exo
# fix
# fly_anatomy
# fly_anatomy_prerelease
# fly_anatomy_xp
# fly_development
# fly_taxonomy
# flybase_vocab
# fma_lite
# fma_lite_prerelease
# fungal_anatomy
# fypo
# gazetteer
# gemina_symptom
# gene_ontology
# gene_regulation
# genes-7227
# genes-7955
# genes-9096
# genes-10090
# genprotec2go
# go_xp_all
# go_xp_chebi
# go_xp_internal
# go_xp_regulation
# go_xrf_metadata
# habronattus_courtship
# hamap2go
# homology_ontology
# human-dev-anat-abstract
# human-dev-anat-abstract2
# human-dev-anat-staged
# human_embryology_terminology
# human_histology_terminology
# human_phenotype
# human_phenotype_xp
# human_phenotype_xp_all
# human_phenotype_xp_anatomy
# human_phenotype_xp_relations
# hymenoptera_anatomy
# image
# infectious_disease_ontology
# influenza_ontology
# information_artifact
# interpro
# interpro2go
# kisao
# lipid
# loggerhead_nesting
# ma2ncit
# malaria_ontology
# mammalian_phenotype
# mammalian_phenotype_xp
# mao
# map_CL-EMAPA
# medaka_anatomy_development
# metacyc2go
# mged
# minimal_anatomical_terminology
# mips2go
# molecular_function
# molecular_function_xp_chebi
# molecular_function_xp_regulators
# molecular_function_xp_uber_anatomy
# molecule_role
# mosquito_anatomy
# mosquito_insecticide_resistance
# mouse_pathology
# multifun2go
# ncbi_taxonomy
# ncithesaurus
# neuro_behavior_ontology
# nmr
# obi
# oboInOwl
# omrse
# opl
# pathway
# pfam2go
# pharmacogenomics
# plant_environment
# plant_ontology
# plant_trait
# plant_trait_xp
# plasmodium_life_cycle
# platynereis_stage_ontology
# po_anatomy
# po_temporal
# poro
# prints2go
# prodom2go
# prosite2go
# protein
# protein_prerelease
# provenance
# psi-mi
# psi-mod
# psi-ms
# quality
# quality_bfo_bridge
# reactome2go
# relationship
# rex
# rfam2go
# rnao
# ro
# ro_bfo_bridge1_1
# ro_proposed
# ro_ucdhsc
# sao
# sep
# sequence
# sequence_prerelease
# sequence_xp
# smart2go
# software
# spatial
# spider_anatomy
# spkw2go
# systems_biology
# taxonomic_rank
# teleost_anatomy
# teleost_taxonomy
# temporal_gramene
# tick_anatomy
# tigr2go
# tigrfams2go
# tmp
# transmission
# uberon
# uberon_anatomy_ontologies_bridge
# uberon_prerelease
# um-bbd_enzymeid2go
# um-bbd_pathwayid2go
# unit
# vHOG
# vHOG_associations
# vaccine
# vertebrate_anatomy
# worm_anatomy
# worm_development
# worm_phenotype
# worm_phenotype_xp
# worm_phenotype_xp_anatomy
# worm_phenotype_xp_anatomy_and_stages
# worm_phenotype_xp_chebi
# worm_phenotype_xp_go
# worm_phenotype_xp_go_chebi
# worm_phenotype_xp_relations
# xenopus_anatomy
# yeast_phenotype
# zea_mays_anatomy
# zebrafish_anatomy
# zebrafish_anatomy_prerelease
