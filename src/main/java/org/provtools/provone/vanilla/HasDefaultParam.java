package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


public class HasDefaultParam implements org.provtools.provone.model.HasDefaultParam {

    @JsonProperty("provone:port")
    private QualifiedName port = null;
    @JsonProperty("prov:entity")
    private QualifiedName defaultParam = null;

    protected HasDefaultParam() {};

    public HasDefaultParam(QualifiedName port, QualifiedName defaultParam) {
        this.port = port;
        this.defaultParam = defaultParam;
    }

    @JsonIgnore
    @Override
    public ProvOneKind getProvOneKind() {
        return ProvOneKind.PROVONE_HASDEFAULTPARAM;
    }

    @JsonIgnore
    @Override
    public Kind getKind() {
        return null;
    }

    @Override
    public void setDefaultParam(QualifiedName eid) {
        this.defaultParam = eid;
    }

    @Override
    public QualifiedName getDefaultParam() {
        return this.defaultParam;
    }

    @Override
    public void setPort(QualifiedName pid) {
        this.port = pid;
    }

    @Override
    public QualifiedName getPort() {
        return this.port;
    }
}