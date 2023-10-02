package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.vanilla.Entity;
import org.openprovenance.prov.model.Attribute;
import java.util.*;


public class Controller extends Entity {

    public Controller(QualifiedName id, Collection<Attribute> attributes) {
        super(id, attributes);
    }
}