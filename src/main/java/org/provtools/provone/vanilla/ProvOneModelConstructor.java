package org.provtools.provone.vanilla;

import java.util.Collection;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.openprovenance.prov.model.Attribute;
import org.openprovenance.prov.model.Bundle;
import org.openprovenance.prov.vanilla.Entity;
import org.openprovenance.prov.model.HadMember;
import org.openprovenance.prov.model.ModelConstructor;
import org.openprovenance.prov.model.Namespace;
import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.model.Statement;
import org.openprovenance.prov.model.Used;
import org.openprovenance.prov.model.WasAssociatedWith;
import org.openprovenance.prov.model.WasGeneratedBy;
import org.openprovenance.prov.model.WasInformedBy;
import org.openprovenance.prov.vanilla.Document;
import org.openprovenance.prov.vanilla.WasDerivedFrom;
import org.provtools.provone.vanilla.Controls;
import org.provtools.provone.vanilla.Data;
import org.provtools.provone.vanilla.Execution;
import org.provtools.provone.vanilla.Port;
import org.provtools.provone.vanilla.Program;
import org.provtools.provone.model.ConnectsTo;
import org.provtools.provone.model.ControlledBy;
import org.provtools.provone.model.HadEntity;
import org.provtools.provone.model.HadInPort;
import org.provtools.provone.model.HadOutPort;
import org.provtools.provone.model.WasPartOf;
import org.provtools.provone.vanilla.Channel;
import org.provtools.provone.vanilla.Controller;
import org.provtools.provone.vanilla.Visualization;
import org.provtools.provone.vanilla.Workflow;

/** Interface for constructing concrete representations of the ProvONE data model. */

final public class ProvOneModelConstructor implements org.provtools.provone.model.ProvOneModelConstructor {

    @Override
    public Program newProgram(QualifiedName id, List<Program> subPrograms, List<Port> inPorts,
            List<Port> outPorts, Collection<Attribute> attributes) {
        return new org.provtools.provone.vanilla.Program(id, subPrograms, inPorts, outPorts, attributes);
    }

    @Override
    public Port newPort(QualifiedName id, Entity defaultParam, List<Channel> channels,
            Collection<Attribute> attributes) {
        return new org.provtools.provone.vanilla.Port(id, defaultParam, channels, attributes);
    }

    @Override
    public Channel newChannel(QualifiedName id, Collection<Attribute> attributes) {
        return new org.provtools.provone.vanilla.Channel(id, attributes);
    }

    @Override
    public Controller newController(QualifiedName id, Collection<Attribute> attributes) {
        return new org.provtools.provone.vanilla.Controller(id, attributes);
    }

    @Override
    public Workflow newWorkflow(QualifiedName id, List<Program> subPrograms, List<Port> inPorts,
            List<Port> outPorts, Collection<Attribute> attributes) {
        return new org.provtools.provone.vanilla.Workflow(id, subPrograms, inPorts, outPorts, attributes);
    }

    @Override
    public ControlledBy newControlledBy(QualifiedName id, QualifiedName program, QualifiedName controller) {
        return new org.provtools.provone.vanilla.ControlledBy(id, program, controller);
    }

    @Override
    public Controls newControls(QualifiedName id, QualifiedName controller, QualifiedName program) {
        return new org.provtools.provone.vanilla.Controls(id, controller, program);
    }

    @Override
    public ConnectsTo newConnectsTo(QualifiedName id, QualifiedName port, QualifiedName channel) {
        return new org.provtools.provone.vanilla.ConnectsTo(id, port, channel);
    }

    @Override
    public WasDerivedFrom newWasDerivedFrom(QualifiedName id, QualifiedName derivate, QualifiedName original,
            Collection<Attribute> attributes) {
        // TODO Use prov functions here!
    }

    @Override
    public Execution newExecution(QualifiedName id, XMLGregorianCalendar startTime, XMLGregorianCalendar endTime,
            Collection<Attribute> attributes) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'newExecution'");
    }

    @Override
    public User newUser(QualifiedName id, Collection<Attribute> attributes) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'newUser'");
    }

    @Override
    public Used newUsed(QualifiedName id, QualifiedName activity, QualifiedName entity, XMLGregorianCalendar time,
            Collection<Attribute> attributes) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'newUsed'");
    }

    @Override
    public WasGeneratedBy newWasGeneratedBy(QualifiedName id, QualifiedName entity, QualifiedName activity,
            XMLGregorianCalendar time, Collection<Attribute> attributes) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'newWasGeneratedBy'");
    }

    @Override
    public WasAssociatedWith newWasAssociatedWith(QualifiedName id, QualifiedName activity, QualifiedName agent,
            QualifiedName plan, Collection<Attribute> attributes) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'newWasAssociatedWith'");
    }

    @Override
    public WasInformedBy newWasInformedBy(QualifiedName id, QualifiedName child, QualifiedName parent,
            Collection<Attribute> attributes) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'newWasInformedBy'");
    }

    @Override
    public WasPartOf newWasPartOf(QualifiedName id, QualifiedName child, QualifiedName parent,
            Collection<Attribute> attributes) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'newWasPartOf'");
    }

    @Override
    public HadInPort newHadInPort(QualifiedName id, QualifiedName usage, QualifiedName port,
            Collection<Attribute> attributes) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'newHadInPort'");
    }

    @Override
    public HadEntity newHadEntity(QualifiedName id, QualifiedName usage, QualifiedName generation, QualifiedName entity,
            Collection<Attribute> attributes) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'newHadEntity'");
    }

    @Override
    public HadOutPort newHadOutPort(QualifiedName id, QualifiedName generation, QualifiedName port,
            Collection<Attribute> attributes) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'newHadOutPort'");
    }

    @Override
    public Entity newEntity(QualifiedName id, Collection<Attribute> attributes) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'newEntity'");
    }

    @Override
    public Data newData(QualifiedName id, Collection<Attribute> attributes) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'newData'");
    }

    @Override
    public Visualization newVisualization(QualifiedName id, Collection<Attribute> attributes) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'newVisualization'");
    }

    @Override
    public Document newDocument(Namespace namespace, Collection<Statement> statements, Collection<Bundle> bundles) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'newDocument'");
    }

    @Override
    public HadMember newHadMember(QualifiedName id, Collection<QualifiedName> entities) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'newHadMember'");
    }

    
}
