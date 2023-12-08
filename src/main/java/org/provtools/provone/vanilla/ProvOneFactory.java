package org.provtools.provone.vanilla;

import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import java.util.Collection;

import org.apache.logging.log4j.LogManager;

import org.openprovenance.prov.model.*;
import org.provtools.provone.model.HasDefaultParam;
import org.provtools.provone.model.HasOutPort;
import org.provtools.provone.model.HasSubProgram;
import org.provtools.provone.model.ProvOneModelConstructor;
import org.provtools.provone.model.HadInPort;
import org.provtools.provone.model.HadOutPort;
import org.provtools.provone.model.WasPartOf;
import org.provtools.provone.model.HadEntity;

//TODO A generic interface for attribute kinds. Then new schemas can be implemented as extension of the generic interface and used with ProvFactoriy's newAttribute method

/** A stateless factory for PROV and ProvONE objects. */

public class ProvOneFactory extends org.openprovenance.prov.vanilla.ProvFactory {

    static Logger logger = LogManager.getLogger(ProvOneFactory.class);

    private final static ProvOneFactory oFactory = new ProvOneFactory();
    final org.provtools.provone.model.ProvOneModelConstructor mc;
    final AtomConstructor ac;

    public ProvOneFactory(ObjectFactory of) {
        super(of);
        mc = new org.provtools.provone.vanilla.ProvOneModelConstructor();
        ac = (AtomConstructor) mc;
    }

    public ProvOneFactory () {
        super(null);
        mc = new org.provtools.provone.vanilla.ProvOneModelConstructor();
        ac = (AtomConstructor) mc;
    }

    public ProvOneFactory(ObjectFactory of, ProvOneModelConstructor mc) {
        super(of);
        this.mc = mc;
        ac = (AtomConstructor) mc;
    }

    public static ProvOneFactory getFactory() {
        return oFactory;
    }

    /*
    *
    *  ProvONE Aspect: Workflow
    *
    */

    public Program newProgram(QualifiedName id, Collection<Attribute> attributes) {
        return mc.newProgram(id, attributes);
    }

    public Program newProgram(QualifiedName id, String label) {
        Collection<Attribute> attrs = new LinkedList<>();
        attrs.add(newAttribute(Attribute.AttributeKind.PROV_LABEL, newInternationalizedString(label), getName().XSD_STRING));
        return mc.newProgram(id, attrs);
    }

    public Program newProgram(QualifiedName id, String label, String version, String suite, String downloadURL, String citation, String comment) {
        Collection<Attribute> attrs=new LinkedList<>();
        attrs.add(newAttribute(Attribute.AttributeKind.PROV_LABEL, newInternationalizedString(label), getName().XSD_STRING));
        if(version != null) {
            attrs.add(newAttribute("https://schema.org/", "softwareVersion", "schema", version,
                                   newQualifiedName("https://schema.org/", "Text", "schema")));
        }
        if(suite != null) {
            attrs.add(newAttribute("https://schema.org/", "applicationSuite", "schema", suite,
                                   newQualifiedName("https://schema.org/", "Text", "schema")));        
        }
        if(downloadURL != null) {
            attrs.add(newAttribute("https://schema.org/", "downloadUrl", "schema", downloadURL,
                                   newQualifiedName("https://schema.org/", "URL", "schema")));     
        }
        if(citation != null) {
            attrs.add(newAttribute("https://schema.org/", "citation", "schema", citation,
                                   newQualifiedName("https://schema.org/", "URL", "schema")));  
        }
        if(comment != null)  {
            attrs.add(newAttribute("https://schema.org/", "comment", "schema", comment,
                                   newQualifiedName("https://schema.org/", "Text", "schema")));                      
        }

        return mc.newProgram(id, attrs);
    }

    public Port newPort(QualifiedName id, Entity defaultParam, List<Channel> channels, Collection<Attribute> attributes) {
        return mc.newPort(id, defaultParam, channels, attributes);
    }

    public Port newPort(QualifiedName id, String label) {
        Collection<Attribute> attrs = new LinkedList<>();
        attrs.add(newAttribute(Attribute.AttributeKind.PROV_LABEL, newInternationalizedString(label), getName().XSD_STRING));
        return mc.newPort(id, null, null, attrs);
    }

    public Channel newChannel(QualifiedName id, String label) {
        Collection<Attribute> attrs = new LinkedList<>();
        attrs.add(newAttribute(Attribute.AttributeKind.PROV_LABEL, newInternationalizedString(label), getName().XSD_STRING));
        return mc.newChannel(id, attrs);
    }

    public Controller newController(QualifiedName id, Collection<Attribute> attributes) {
        return new org.provtools.provone.vanilla.Controller(id, attributes);
    }
    
    public Controller newController(QualifiedName id, String label) {
        Collection<Attribute> attrs = new LinkedList<>();
        attrs.add(newAttribute(Attribute.AttributeKind.PROV_LABEL, newInternationalizedString(label), getName().XSD_STRING));
        return new org.provtools.provone.vanilla.Controller(id, attrs);
    }
    
    public Workflow newWorkflow(QualifiedName id, Collection<Attribute> attributes) {
        return mc.newWorkflow(id, attributes);
    }

    public Workflow newWorkflow(QualifiedName id, String label) {
        Collection<Attribute> attrs = new LinkedList<>();
        attrs.add(newAttribute(Attribute.AttributeKind.PROV_LABEL, newInternationalizedString(label), getName().XSD_STRING));
        return mc.newWorkflow(id, attrs);
    }

    public HasSubProgram newHasSubProgram(QualifiedName id, QualifiedName parent, QualifiedName child, Collection<Attribute> attributes) {
        return mc.newHasSubProgram(id, parent, child, attributes);
    }

    public HasSubProgram newHasSubProgram(QualifiedName parent, QualifiedName child, String label) {
        Collection<Attribute> attrs = new LinkedList<>();
        attrs.add(newAttribute(Attribute.AttributeKind.PROV_LABEL, newInternationalizedString(label), getName().XSD_STRING));
        return mc.newHasSubProgram(null, parent, child, attrs);
    }

    public HasSubProgram newHasSubProgram(QualifiedName parent, QualifiedName child) {
        return mc.newHasSubProgram(null, parent, child, null);
    }

    public Controls newControls(QualifiedName controller, QualifiedName program) {
        return mc.newControls(controller, program);
    }

    public org.provtools.provone.model.ControlledBy newControlledBy(QualifiedName program, QualifiedName controller) {
        return mc.newControlledBy(program, controller);
    }

    public HasInPort newHasInPort(QualifiedName id, QualifiedName program, QualifiedName port, Collection<Attribute> attributes) {
        return mc.newHasInPort(id, program, port, attributes);
    }

    public HasInPort newHasInPort(QualifiedName program, QualifiedName port) {
        return mc.newHasInPort(null, program, port, null);
    }

    public HasOutPort newHasOutPort(QualifiedName id, QualifiedName program, QualifiedName port, Collection<Attribute> attributes) {
        return mc.newHasOutPort(id, program, port, attributes);
    }

    public HasOutPort newHasOutPort(QualifiedName program, QualifiedName port) {
        return mc.newHasOutPort(null, program, port, null);
    }

    public HasDefaultParam newHasDefaultParam(QualifiedName port, QualifiedName param) {
        return mc.newHasDefaultParam(port, param);
    }

    public org.provtools.provone.model.ConnectsTo newConnectsTo(QualifiedName port, QualifiedName channel) {
        return mc.newConnectsTo(port, channel);
    }


    // /*
    // *
    // *  ProvONE Aspect: Trace
    // *
    // */


    public Execution newExecution(QualifiedName id, XMLGregorianCalendar startTime, XMLGregorianCalendar endTime,
            Collection<Attribute> attributes) {
        return mc.newExecution(id, startTime, endTime, attributes);
    }

    public Execution newExecution(QualifiedName id, XMLGregorianCalendar startTime, XMLGregorianCalendar endTime, String label) {
        Collection<Attribute> attrs = new LinkedList<>();
        attrs.add(newAttribute(Attribute.AttributeKind.PROV_LABEL, newInternationalizedString(label), getName().XSD_STRING));
        return mc.newExecution(id, startTime, endTime, attrs);
    }

    public User newUser(QualifiedName id, Collection<Attribute> attributes) {
        return mc.newUser(id, attributes);
    }

    public User newUser(QualifiedName id, String label) {
        Collection<Attribute> attrs = new LinkedList<>();
        attrs.add(newAttribute(Attribute.AttributeKind.PROV_LABEL, newInternationalizedString(label), getName().XSD_STRING));
        return mc.newUser(id,  attrs);
    }

    public WasPartOf newWasPartOf(QualifiedName child, QualifiedName parent, Collection<Attribute> attributes) {
        return mc.newWasPartOf(child, parent, attributes);
    }

    public WasPartOf newWasPartOf(QualifiedName child, QualifiedName parent) {
        return mc.newWasPartOf(child, parent, null);
    }

    public HadInPort newHadInPort(QualifiedName usage, QualifiedName port) {
        return mc.newHadInPort(usage, port);
    }

    public HadEntity newHadEntity(QualifiedName usage, QualifiedName entity) {
        return mc.newHadEntity(usage, entity);
    }

    public HadOutPort newHadOutPort(QualifiedName generation, QualifiedName port) {
        return mc.newHadOutPort(generation, port);
    }

    // /*
    // *
    // *  ProvONE Aspect: Data Structure
    // *
    // */

    public Data newData(QualifiedName id, Collection<Attribute> attributes) {
        return mc.newData(id, attributes);
    }

    public Data newData(QualifiedName id, String label) {
        Collection<Attribute> attrs = new LinkedList<>();
        attrs.add(newAttribute(Attribute.AttributeKind.PROV_LABEL, newInternationalizedString(label), getName().XSD_STRING));
        return mc.newData(id,  attrs);
    }

    public Visualization newVisualization(QualifiedName id, Collection<Attribute> attributes) {
        return mc.newVisualization(id, attributes);
    }

    public Visualization newVisualization(QualifiedName id, String label) {
        Collection<Attribute> attrs = new LinkedList<>();
        attrs.add(newAttribute(Attribute.AttributeKind.PROV_LABEL, newInternationalizedString(label), getName().XSD_STRING));
        return mc.newVisualization(id,  attrs);
    }

    public Document newDocument(QualifiedName id, Collection<Attribute> attributes) {
        return mc.newDocument(id, attributes);
    }

    public Document newDocument(QualifiedName id, String label) {
        Collection<Attribute> attrs = new LinkedList<>();
        attrs.add(newAttribute(Attribute.AttributeKind.PROV_LABEL, newInternationalizedString(label), getName().XSD_STRING));
        return mc.newDocument(id, attrs);
    }
}