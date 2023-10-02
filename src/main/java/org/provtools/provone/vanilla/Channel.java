package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.vanilla.Entity;
import org.openprovenance.prov.model.Attribute;
import java.util.*;


public class Channel extends Entity {

    public Channel(QualifiedName id, Collection<Attribute> attributes) {
        super(id, attributes);
    }
}