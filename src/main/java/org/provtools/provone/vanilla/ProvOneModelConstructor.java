package org.provtools.provone.vanilla;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.openprovenance.prov.model.AtomConstructor;
import org.openprovenance.prov.model.Attribute;
import org.openprovenance.prov.model.Bundle;
import org.openprovenance.prov.model.Entity;
import org.openprovenance.prov.model.HadMember;
import org.openprovenance.prov.model.Label;
import org.openprovenance.prov.model.LangString;
import org.openprovenance.prov.model.Location;
import org.openprovenance.prov.model.Namespace;
import org.openprovenance.prov.model.Other;
import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.model.Role;
import org.openprovenance.prov.model.Statement;
import org.openprovenance.prov.model.Type;
import org.openprovenance.prov.model.Used;
import org.openprovenance.prov.model.Value;
import org.openprovenance.prov.model.WasAssociatedWith;
import org.openprovenance.prov.model.WasGeneratedBy;
import org.openprovenance.prov.model.WasInformedBy;
import org.openprovenance.prov.vanilla.Document;
import org.provtools.provone.model.ConnectsTo;
import org.provtools.provone.model.ControlledBy;
import org.provtools.provone.model.HadEntity;
import org.provtools.provone.model.HadInPort;
import org.provtools.provone.model.HadOutPort;
import org.provtools.provone.model.WasPartOf;

/** Interface for constructing concrete representations of the ProvONE data model. */

final public class ProvOneModelConstructor implements org.provtools.provone.model.ProvOneModelConstructor, AtomConstructor {

    /*
    *
    *  ProvONE Aspect: Workflow
    *
    */

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
    public org.openprovenance.prov.model.WasDerivedFrom newWasDerivedFrom(QualifiedName id, QualifiedName e2, QualifiedName e1, QualifiedName activity, QualifiedName generation, QualifiedName usage, Collection<Attribute> attributes) {
        return new org.openprovenance.prov.vanilla.WasDerivedFrom(id,e2,e1,activity,generation,usage,attributes);
    }


    /*
    *
    *  ProvONE Aspect: Trace
    *
    */


    @Override
    public Execution newExecution(QualifiedName id, XMLGregorianCalendar startTime, XMLGregorianCalendar endTime,
            Collection<Attribute> attributes) {
        return new org.provtools.provone.vanilla.Execution(id, startTime, endTime, attributes);
    }

    @Override
    public User newUser(QualifiedName id, Collection<Attribute> attributes) {
        return new org.provtools.provone.vanilla.User(id, attributes);
    }

    @Override
    public Used newUsed(QualifiedName id, QualifiedName activity, QualifiedName entity, XMLGregorianCalendar time,
            Collection<Attribute> attributes) {
        return new org.openprovenance.prov.vanilla.Used(id, activity, entity, time, attributes);
    }

    @Override
    public WasGeneratedBy newWasGeneratedBy(QualifiedName id, QualifiedName entity, QualifiedName activity,
            XMLGregorianCalendar time, Collection<Attribute> attributes) {
        return new org.openprovenance.prov.vanilla.WasGeneratedBy(id, entity, activity, time, attributes);
    }

    @Override
    public WasAssociatedWith newWasAssociatedWith(QualifiedName id, QualifiedName activity, QualifiedName agent,
            QualifiedName plan, Collection<Attribute> attributes) {
        return new org.openprovenance.prov.vanilla.WasAssociatedWith(id, activity, agent, plan, attributes);
    }

    @Override
    public WasInformedBy newWasInformedBy(QualifiedName id, QualifiedName informed, QualifiedName informant,
            Collection<Attribute> attributes) {
        return new org.openprovenance.prov.vanilla.WasInformedBy(id, informed, informant, attributes);
    }

    @Override
    public WasPartOf newWasPartOf(QualifiedName id, QualifiedName child, QualifiedName parent,
            Collection<Attribute> attributes) {
        return new org.provtools.provone.vanilla.WasPartOf(id, child, parent, attributes);
    }

    @Override
    public HadInPort newHadInPort(QualifiedName id, QualifiedName usage, QualifiedName port,
            Collection<Attribute> attributes) {
        return new org.provtools.provone.vanilla.HadInPort(id, usage, port, attributes);
    }

    @Override
    public HadEntity newHadEntity(QualifiedName id, QualifiedName property, QualifiedName entity,
            Collection<Attribute> attributes) {
        return new org.provtools.provone.vanilla.HadEntity(id, property, entity, attributes);
    }

    @Override
    public HadOutPort newHadOutPort(QualifiedName id, QualifiedName generation, QualifiedName port,
            Collection<Attribute> attributes) {
        return new org.provtools.provone.vanilla.HadOutPort(id, generation, port, attributes);
    }


    /*
    *
    *  ProvONE Aspect: Data Structure
    *
    */


    @Override
    public Entity newEntity(QualifiedName id, Collection<Attribute> attributes) {
        return new org.openprovenance.prov.vanilla.Entity(id, attributes);
    }

    @Override
    public Data newData(QualifiedName id, Collection<Attribute> attributes) {
        return new org.provtools.provone.vanilla.Data(id, attributes);
    }

    @Override
    public Visualization newVisualization(QualifiedName id, Collection<Attribute> attributes) {
        return new org.provtools.provone.vanilla.Visualization(id, attributes);
    }

    @Override
    public Document newDocument(Namespace namespace, Collection<Statement> statements, Collection<Bundle> bundles) {
        return new Document(namespace, statements, bundles);    
    }

    @Override
    public HadMember newHadMember(QualifiedName id, Collection<QualifiedName> entities) {
        List<org.openprovenance.prov.model.QualifiedName> ll=new LinkedList<>();
        ll.addAll(entities);
        return new org.openprovenance.prov.vanilla.HadMember(id,ll);
    }

    @Override
    public LangString newInternationalizedString(String s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'newInternationalizedString'");
    }

    @Override
    public LangString newInternationalizedString(String s, String lang) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'newInternationalizedString'");
    }

    @Override
    public Label newLabel(Object arg0, QualifiedName arg1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'newLabel'");
    }

    @Override
    public Location newLocation(Object arg0, QualifiedName arg1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'newLocation'");
    }

    @Override
    public Other newOther(QualifiedName elementName, Object value, QualifiedName type) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'newOther'");
    }

    @Override
    public Role newRole(Object arg0, QualifiedName arg1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'newRole'");
    }

    @Override
    public Type newType(Object arg0, QualifiedName arg1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'newType'");
    }

    @Override
    public Value newValue(Object arg0, QualifiedName arg1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'newValue'");
    }
}
