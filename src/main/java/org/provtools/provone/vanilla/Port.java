package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.vanilla.Entity;
import org.provtools.provone.model.HasDefaultParam;
import org.openprovenance.prov.model.Attribute;
import java.util.*;


public class Port extends Entity implements HasDefaultParam {

    public Port(QualifiedName id, Collection<Attribute> attributes) {
        super(id, attributes);
    }

    @Override
    public void setDefaultParam(QualifiedName pid) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setDefaultParam'");
    }

    @Override
    public QualifiedName getDefaultParam() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDefaultParam'");
    }
}