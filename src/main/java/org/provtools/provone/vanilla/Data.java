package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.vanilla.Entity;
import org.openprovenance.prov.model.Attribute;
import java.util.Collection;


public class Data extends Entity {

    protected Data() {
        super(null, null);
    }

    public Data(QualifiedName id, Collection<Attribute> attributes) {
        super(id, attributes);
    }
}