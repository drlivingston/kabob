`{:name "link-bio-with-nil"
  :description "This link-bio-with-properties rule requires both
  things being linked to be 'bioentities' and thus this rule excludes
  links within RDF lists that connect to rdf:nil. This rule fills in
  those missing links."
  :head ((?/bio ?/p rdf/nil))
  :body ((?/c obo/IAO_0000219 ?/bio)
         (?/c ?/p rdf/nil))}