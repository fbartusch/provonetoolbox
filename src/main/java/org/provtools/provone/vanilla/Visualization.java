package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.vanilla.Entity;
import org.openprovenance.prov.model.Attribute;
import java.util.*;


public class Visualization extends Entity {

    public Visualization(QualifiedName id, Collection<Attribute> attributes) {
        super(id, attributes);
    }
}