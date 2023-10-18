package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;


public class ConnectsTo implements org.provtools.provone.model.ConnectsTo {

    QualifiedName port;
    QualifiedName channel;

    public ConnectsTo(QualifiedName id, QualifiedName port, QualifiedName channel) {
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
}