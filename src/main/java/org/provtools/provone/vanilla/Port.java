package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.model.Entity;
import org.provtools.provone.model.ProvOneStatementOrBundle;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.openprovenance.prov.model.Attribute;
import java.util.*;


public class Port extends org.openprovenance.prov.vanilla.Entity implements ProvOneStatementOrBundle {

    protected Port() {
        super(null, null);
    }

    public Port(QualifiedName id, java.util.Collection<Attribute> attributes) {
        super(id, attributes);
    }

    public Port(QualifiedName id, Entity defaultParam, List<Channel> channels,
            java.util.Collection<Attribute> attributes) {
        super(id, attributes);
    }
    
    @JsonIgnore
    @Override
    public ProvOneKind getProvOneKind() {
        return ProvOneKind.PROVONE_PORT;
    }
}