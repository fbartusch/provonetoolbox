package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


public class HadEntity implements org.provtools.provone.model.HadEntity {

    @JsonProperty("prov:usage")
    private QualifiedName usage = null;
    @JsonProperty("prov:entity")
    private QualifiedName entity = null;

    public HadEntity(QualifiedName usage, QualifiedName entity) {
        this.usage = usage;
        this.entity = entity;
    }

    @Override
    public void setUsage(QualifiedName uid) {
        this.usage = uid;
    }

    @Override
    public void setEntity(QualifiedName eid) {
        this.entity = eid;
    }

    @Override
    public QualifiedName getUsage() {
        return this.usage;
    }

    @Override
    public QualifiedName getEntity() {
        return this.entity;
    }

    @JsonIgnore
    @Override
    public ProvOneKind getProvOneKind() {
        return ProvOneKind.PROVONE_HADENTITY;
    }

    @Override
    public Kind getKind() {
        return null;
    }


}