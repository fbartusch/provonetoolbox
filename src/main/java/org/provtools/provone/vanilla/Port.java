package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.model.Entity;
import org.provtools.provone.model.HasDefaultParam;
import org.openprovenance.prov.model.Attribute;
import java.util.*;


public class Port extends org.openprovenance.prov.vanilla.Entity implements HasDefaultParam {

    
    private Entity defaultParam = null;
    private List<org.provtools.provone.vanilla.Channel> channels = new LinkedList<>();


    public Port(QualifiedName id, java.util.Collection<Attribute> attributes) {
        super(id, attributes);
    }

    public Port(QualifiedName id, Entity defaultParam, List<Channel> channels,
            java.util.Collection<Attribute> attributes) {
        super(id, attributes);
        this.defaultParam = defaultParam;
        this.channels = channels;
    }

    @Override
    public void setDefaultParam(Entity e) {
        this.defaultParam = e;
    }

    @Override
    public Entity getDefaultParam() {
        return this.defaultParam;
    }
}