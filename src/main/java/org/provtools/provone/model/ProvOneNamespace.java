package org.provtools.provone.model;

/** A class to manipulate Namespaces when creating, serializing and converting ProvONE documents. 
 * @author Felix Bartusch 
 * */

public class ProvOneNamespace extends org.openprovenance.prov.model.Namespace {

    // In contrast to the generic Namespace class we want
    // to have namespaces added with the default constructor.
    public ProvOneNamespace() {
        super();
        this.addKnownNamespaces();
    }

    public void addKnownNamespaces() {
        // Add prov and xsd namespace
        super.addKnownNamespaces();

        // Add provone namespace
        getPrefixes().put("provone", ProvOneNamespacePrefixMapper.PROVONE_NS);
        getNamespaces().put(ProvOneNamespacePrefixMapper.PROVONE_NS, "provone");

        // Set ProvOne as default Namespace
        setDefaultNamespace(ProvOneNamespacePrefixMapper.PROVONE_NS);
    }
}
