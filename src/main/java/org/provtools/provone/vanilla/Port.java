package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.model.Entity;
import org.provtools.provone.model.HasDefaultParam;
import org.provtools.provone.model.ProvOneStatementOrBundle;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.openprovenance.prov.model.Attribute;
import java.util.*;


public class Port extends org.openprovenance.prov.vanilla.Entity implements ProvOneStatementOrBundle {

    
    private Entity defaultParam = null;
    private List<org.provtools.provone.vanilla.Channel> channels = new LinkedList<>();

    protected Port() {
        super(null, null);
    }

    public Port(QualifiedName id, java.util.Collection<Attribute> attributes) {
        super(id, attributes);
    }

    public Port(QualifiedName id, Entity defaultParam, List<Channel> channels,
            java.util.Collection<Attribute> attributes) {
        super(id, attributes);
        this.defaultParam = defaultParam;
        this.channels = channels;
    }
    
    @JsonIgnore
    @Override
    public ProvOneKind getProvOneKind() {
        return ProvOneKind.PROVONE_PORT;
    }
}