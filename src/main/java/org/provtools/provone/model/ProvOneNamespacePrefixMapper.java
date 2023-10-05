package org.provtools.provone.model;

import org.openprovenance.prov.model.NamespacePrefixMapper;

/** Namespace and prefix declarations for common namespaces manipulated by ProvOneToolbox. API to
 *  Sun's NamespacePrefixMapper.
 * @author Felix Bartusch
 */
public interface ProvOneNamespacePrefixMapper extends NamespacePrefixMapper {
    // According to Table 1: http://jenkins-1.dataone.org/jenkins/view/Documentation%20Projects/job/ProvONE-Documentation-trunk/ws/provenance/ProvONE/v1/provone.html
    
    // The ProvONE namespace
    static final public String PROVONE_NS = "http://purl.dataone.org/provone/2015/01/15/ontology#";
    static final public String PROVONE_PREFIX = "provone";
    // OWL 2 specification namespace
    static final public String OWL_NS = "http://www.w3.org/2002/07/owl#";
    static final public String OWL_PREFIX = "owl";
    // Dublin Core Metadata Elements namespace
    static final public String DC_NS = "http://purl.org/dc/terms/#";
    static final public String DC_PREFIX = "dcterms";
    // The Bibliographic Ontology namespace
    static final public String BIBO_NS = "http://purl.org/ontology/bibo/#";
    static final public String BIBO_PREFIX = "bibo";
    // Placeholder example WfMS namespace
    static final public String WFMS_NS = "http://www.wfms.org/registry.xsd/#";
    static final public String WFMS_PREFIX = "wfms";
}
