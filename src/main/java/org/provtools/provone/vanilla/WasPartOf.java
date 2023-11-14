package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.openprovenance.prov.model.Attribute;
import java.util.Collection;


public class WasPartOf implements org.provtools.provone.model.WasPartOf {

    @JsonProperty("child")
    private QualifiedName child = null;
    @JsonProperty("parent")
    private QualifiedName parent = null;

    public WasPartOf(QualifiedName child, QualifiedName parent, Collection<Attribute> attributes) {
        this.child = child;
        this.parent = parent;
    }

    @Override
    public void setParent(QualifiedName pid) {
        this.parent = pid;
    }

    @Override
    public void setChild(QualifiedName cid) {
        this.child = cid;
    }

    @Override
    public QualifiedName getParent() {
        return this.parent;
    }

    @Override
    public QualifiedName getChild() {
        return this.child;
    }

    @JsonIgnore
    @Override
    public ProvOneKind getProvOneKind() {
        return ProvOneKind.PROVONE_WASPARTOF;
    }

    @Override
    public Kind getKind() {
        return null;
    }
}