package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.model.Attribute;
import java.util.*;


public class ConnectsTo implements org.provtools.provone.model.ConnectsTo {

    public ConnectsTo(QualifiedName id, Collection<Attribute> attributes) {

    }

    @Override
    public void setPort(QualifiedName pid) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPort'");
    }

    @Override
    public void setChannel(QualifiedName cid) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setChannel'");
    }

    @Override
    public QualifiedName getPort() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPort'");
    }

    @Override
    public QualifiedName getChannel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getChannel'");
    }
}