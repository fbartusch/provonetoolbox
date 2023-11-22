package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.vanilla.Entity;
import org.openprovenance.prov.model.Attribute;


public class Controller extends Entity {

    protected Controller() {
        super(null, null);
    }

    public Controller(QualifiedName id, java.util.Collection<Attribute> attributes) {
        super(id, attributes);
    }
}