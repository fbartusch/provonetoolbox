package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


public class HadOutPort implements org.provtools.provone.model.HadOutPort {
    
    @JsonProperty("prov:generation")
    private QualifiedName generation = null;
    @JsonProperty("provone:port")
    private QualifiedName port = null;

    public HadOutPort(QualifiedName generation, QualifiedName port) {
        this.generation = generation;
        this.port = port;
    }

    @Override
    public void setGeneration(QualifiedName gid) {
        this.generation = gid;
    }

    @Override
    public void setPort(QualifiedName pid) {
        this.port = pid;
    }

    @Override
    public QualifiedName getGeneration() {
        return this.generation;
    }

    @Override
    public QualifiedName getPort() {
        return this.port;
    }

    @JsonIgnore
    @Override
    public ProvOneKind getProvOneKind() {
        return ProvOneKind.PROVONE_HADOUTPORT;
    }

    @JsonIgnore
    @Override
    public Kind getKind() {
        return null;
    }
}