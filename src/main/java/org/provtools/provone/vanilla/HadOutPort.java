package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.model.Attribute;
import java.util.Collection;


public class HadOutPort implements org.provtools.provone.model.HadOutPort {

    private QualifiedName generation = null;
    private QualifiedName port = null;

    public HadOutPort(QualifiedName id, QualifiedName generation, QualifiedName port,
            Collection<Attribute> attributes) {
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
    public QualifiedName getGneration() {
        return this.generation;
    }

    @Override
    public QualifiedName getPort() {
        return this.port;
    }
}