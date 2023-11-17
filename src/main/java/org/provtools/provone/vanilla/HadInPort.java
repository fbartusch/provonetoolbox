package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


public class HadInPort implements org.provtools.provone.model.HadInPort {

    @JsonProperty("prov:usage")
    private QualifiedName usage = null;
    @JsonProperty("provone:port")
    private QualifiedName port = null;

    public HadInPort(QualifiedName usage, QualifiedName port) {
        this.usage = usage;
        this.port = port;
    }

    @Override
    public void setUsage(QualifiedName uid) {
        this.usage = uid;
    }

    @Override
    public void setPort(QualifiedName pid) {
        this.port = pid;
    }

    @Override
    public QualifiedName getUsage() {
        return this.usage;
    }

    @Override
    public QualifiedName getPort() {
        return this.port;
    }

    @JsonIgnore
    @Override
    public ProvOneKind getProvOneKind() {
        return ProvOneKind.PROVONE_HADINPORT;
    }

    @Override
    public Kind getKind() {
        return null;
    }
}