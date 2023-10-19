package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.vanilla.Agent;
import org.openprovenance.prov.model.Attribute;


public class User extends Agent {

    public User(QualifiedName id, java.util.Collection<Attribute> attributes) {
        super(id, attributes);
    }
}