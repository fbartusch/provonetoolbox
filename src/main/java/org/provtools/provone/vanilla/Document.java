package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.vanilla.Entity;
import org.openprovenance.prov.model.Attribute;
import java.util.Collection;


public class Document extends Entity {

    protected Document() {
        super(null, null);
    }

    public Document(QualifiedName id, Collection<Attribute> attributes) {
        super(id, attributes);
    }
}