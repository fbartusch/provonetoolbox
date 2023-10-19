package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.model.Attribute;
import java.util.Collection;


public class HadInPort implements org.provtools.provone.model.HadInPort {

    private QualifiedName usage = null;
    private QualifiedName port = null;

    //TODO HadInPort is used 

    public HadInPort(QualifiedName id, QualifiedName usage, QualifiedName port, Collection<Attribute> attributes) {
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

}