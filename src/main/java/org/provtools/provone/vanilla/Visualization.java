package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.vanilla.Entity;
import org.openprovenance.prov.model.Attribute;
import java.util.Collection;


public class Visualization extends Entity {

    protected Visualization() {
        super(null, null);
    }

    public Visualization(QualifiedName id, Collection<Attribute> attributes) {
        super(id, attributes);
    }
}