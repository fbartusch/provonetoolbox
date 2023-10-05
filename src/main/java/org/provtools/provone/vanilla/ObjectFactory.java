package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.vanilla.Document;
import org.provtools.provone.model.ConnectsTo;
import org.provtools.provone.model.ControlledBy;
import org.provtools.provone.model.HadInPort;
import org.provtools.provone.model.HadOutPort;
import org.provtools.provone.model.WasPartOf;
import org.openprovenance.prov.model.Attribute;
import java.util.*;


public class ObjectFactory implements org.provtools.provone.model.ObjectFactory {

    public ObjectFactory(QualifiedName id, Collection<Attribute> attributes) {

    }

    @Override
    public Program createProgram() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createProgram'");
    }

    @Override
    public Port createPort() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createPort'");
    }

    @Override
    public Channel createChannel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createChannel'");
    }

    @Override
    public Controller createController() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createController'");
    }

    @Override
    public Workflow createWorkflow() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createWorkflow'");
    }

    @Override
    public ControlledBy createControlledBy() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createControlledBy'");
    }

    @Override
    public Controls createControls() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createControls'");
    }

    @Override
    public ConnectsTo createConnectsTo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createConnectsTo'");
    }

    @Override
    public Execution createExecution() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createExecution'");
    }

    @Override
    public User createUser() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createUser'");
    }

    @Override
    public WasPartOf createWasPartOf() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createWasPartOf'");
    }

    @Override
    public HadInPort createHadInPort() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createHadInPort'");
    }

    @Override
    public HadOutPort createHadOutPort() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createHadOutPort'");
    }

    @Override
    public Data createData() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createData'");
    }

    @Override
    public Visualization createVisualization() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createVisualization'");
    }

    @Override
    public Document createDocument() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createDocument'");
    }


}