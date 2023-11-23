package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


public class ConnectsTo implements org.provtools.provone.model.ConnectsTo {

    @JsonProperty("provone:port")
    QualifiedName port;
    @JsonProperty("provone:channel")
    QualifiedName channel;

    protected ConnectsTo() {};

    public ConnectsTo(QualifiedName port, QualifiedName channel) {
        this.port = port;
        this.channel = channel;
    }

    @Override
    public void setPort(QualifiedName pid) {
        this.port = pid;
    }

    @Override
    public void setChannel(QualifiedName cid) {
        this.channel = cid;
    }

    @Override
    public QualifiedName getPort() {
        return this.port;
    }

    @Override
    public QualifiedName getChannel() {
        return channel;
    }

    @JsonIgnore
    @Override
    public ProvOneKind getProvOneKind() {
        return ProvOneKind.PROVONE_CONNECTSTO;
    }

    @Override
    public Kind getKind() {
        return null;
    }
}