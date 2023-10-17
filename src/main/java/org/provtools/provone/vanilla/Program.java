package org.provtools.provone.vanilla;

import java.util.Collection;

import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.vanilla.Entity;
import org.provtools.provone.model.HasInPort;
import org.provtools.provone.model.HasOutPort;
import org.provtools.provone.model.HasSubProgram;
import org.openprovenance.prov.model.Attribute;


public class Program extends Entity implements HasSubProgram, HasInPort, HasOutPort {

    public Program(QualifiedName id, Collection<Attribute> attributes) {
        super(id, attributes);
    }

    @Override
    public Program getSubProgram() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSubProgram'");
    }

    @Override
    public void setSubProgram(Program time) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setSubProgram'");
    }

    @Override
    public void setOutPort(QualifiedName pid) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setOutPort'");
    }

    @Override
    public QualifiedName getOutPort() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOutPort'");
    }

    @Override
    public void setInPort(QualifiedName pid) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setInPort'");
    }

    @Override
    public QualifiedName getInPort() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInPort'");
    }
}