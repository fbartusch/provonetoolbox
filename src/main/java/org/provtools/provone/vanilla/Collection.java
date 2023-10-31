package org.provtools.provone.vanilla;

import java.util.List;

import org.openprovenance.prov.model.Attribute;
import org.openprovenance.prov.model.HadMember;
import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.vanilla.Entity;

public class Collection extends Entity implements HadMember{

    public Collection(QualifiedName id, java.util.Collection<Attribute> attributes) {
        super(id, attributes);
    }

    @Override
    public QualifiedName getCollection() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCollection'");
    }

    @Override
    public List<QualifiedName> getEntity() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEntity'");
    }

    @Override
    public void setCollection(QualifiedName collection) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCollection'");
    }


    
}
