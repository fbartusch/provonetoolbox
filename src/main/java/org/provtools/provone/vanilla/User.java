package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.vanilla.Agent;
import org.openprovenance.prov.model.Attribute;
import java.util.*;


public class User extends Agent {

    public User(QualifiedName id, Collection<Attribute> attributes) {
        super(id, attributes);
    }
}