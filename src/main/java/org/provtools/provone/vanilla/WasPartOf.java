package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.model.Attribute;
import java.util.Collection;


public class WasPartOf implements org.provtools.provone.model.WasPartOf {

    private QualifiedName child = null;
    private QualifiedName parent = null;

    public WasPartOf(QualifiedName id, QualifiedName child, QualifiedName parent, Collection<Attribute> attributes) {
        this.child = child;
        this.parent = parent;
    }

    @Override
    public void setParentExecution(QualifiedName pid) {
        this.parent = pid;
    }

    @Override
    public void setChildExecution(QualifiedName cid) {
        this.child = cid;
    }

    @Override
    public QualifiedName getParentExecution() {
        return this.parent;
    }

    @Override
    public QualifiedName getChildExecution() {
        return this.child;
    }



}