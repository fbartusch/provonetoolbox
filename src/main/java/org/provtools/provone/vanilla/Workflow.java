package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.model.Attribute;
import java.util.*;


// TODO: Workflow also has super-class prov:Plan in the OWL document, but there is no plan interface to implement.

public class Workflow extends Program {

    public Workflow(QualifiedName id, Collection<Attribute> attributes) {
        super(id, attributes);
    }
}