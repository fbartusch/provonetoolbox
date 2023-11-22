package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.vanilla.Entity;
import org.openprovenance.prov.model.Attribute;


public class Channel extends Entity {

    protected Channel() {
        super(null, null);
    }

    public Channel(QualifiedName id, java.util.Collection<Attribute> attributes) {
        super(id, attributes);
    }
}