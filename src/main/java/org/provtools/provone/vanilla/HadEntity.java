package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


public class HadEntity implements org.provtools.provone.model.HadEntity {

    @JsonProperty("prov:activity")
    private QualifiedName activity = null;
    @JsonProperty("prov:entity")
    private QualifiedName entity = null;

    public HadEntity(QualifiedName activity, QualifiedName entity) {
        this.activity = activity;
        this.entity = entity;
    }

    @Override
    public void setActivity(QualifiedName uid) {
        this.activity = uid;
    }

    @Override
    public void setEntity(QualifiedName eid) {
        this.entity = eid;
    }

    @Override
    public QualifiedName getActivity() {
        return this.activity;
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