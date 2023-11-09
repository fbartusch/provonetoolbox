package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.model.Attribute;

public class Workflow extends Program {

    public Workflow(QualifiedName id, java.util.Collection<Attribute> attributes) {
        super(id, attributes);
    }
}