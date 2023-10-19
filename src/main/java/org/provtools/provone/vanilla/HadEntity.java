package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.model.Attribute;
import java.util.Collection;


public class HadEntity implements org.provtools.provone.model.HadEntity {

    private QualifiedName property = null;
    private QualifiedName entity = null;

    public HadEntity(QualifiedName id, QualifiedName property, QualifiedName entity, Collection<Attribute> attributes) {
    }

    @Override
    public void setProperty(QualifiedName pid) {
        this.property = pid;
    }

    @Override
    public void setEntity(QualifiedName eid) {
        this.entity = eid;
    }

    @Override
    public QualifiedName getProperty() {
        return this.property;
    }

    @Override
    public QualifiedName getEntity() {
        return this.entity;
    }


}