package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.vanilla.Entity;
import org.openprovenance.prov.model.Attribute;


public class Channel extends Entity {

    public Channel(QualifiedName id, java.util.Collection<Attribute> attributes) {
        super(id, attributes);
    }
}