package org.provtools.provone.model;

import org.openprovenance.prov.vanilla.Document;
import org.provtools.provone.vanilla.Channel;
import org.provtools.provone.vanilla.Controller;
import org.provtools.provone.vanilla.Controls;
import org.provtools.provone.vanilla.Data;
import org.provtools.provone.vanilla.Execution;
import org.provtools.provone.vanilla.Port;
import org.provtools.provone.vanilla.Program;
import org.provtools.provone.vanilla.User;
import org.provtools.provone.vanilla.Visualization;
import org.provtools.provone.vanilla.Workflow;

/**
 * 
 * This interface specifies core functionality similar to the JAXB ObjectFactory.
 * It contains a factory method for each Java element interface 
 * in the org.provtools.provonetoolbox.model package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new org.provtools.provonetoolbox.model.instances of the Java representation.
 * for XML content. These factory methods create empty instances, in which
 * no field has been initialized yet.
 * 
 */

public interface ObjectFactory {

    // In the original ProvToolBox Code the model constructs were alphabetically ordered.
    // I stick to the ordering presented in Table 2 from the specification:
    // http://jenkins-1.dataone.org/jenkins/view/Documentation%20Projects/job/ProvONE-Documentation-trunk/ws/provenance/ProvONE/v1/provone.html
    
    // ProvONE Aspect: Workflow
    Program createProgram();
    Port createPort();
    Channel createChannel();
    Controller createController();
    Workflow createWorkflow();
    // HasSubProgram is an Interface that is implemented by Program
    // HasSubProgram createHasSubProgram();
    ControlledBy createControlledBy();
    Controls createControls();
    // HasInPort is an Interface that is implemented by Program
    // HasOutPort is an Interface that is implemented by Program
    // HasDefaultParam is an Interface that is implemented by Port
    ConnectsTo createConnectsTo();
    // WasDerivedFrom The prov:WasDerivedFrom class is adopted directly from the PROV Ontology

    // ProvONE Aspect: Trace
    Execution createExecution();
    // Association: The prov:Association class is adopted directly from the PROV Ontology
    // Usage: The prov:Usage class is adopted directly from the PROV Ontology,
    // Generation: The prov:Generation class is adopted directly from the PROV Ontology
    User createUser();
    // Used: prov:used is adopted in ProvONE
    // WasGeneratedBy: 
    // WasAssociatedWith
    // WasInformedBy
    WasPartOf createWasPartOf();
    // QualifiedAssocitation()
    // agent
    // hadPlan
    // qualified Usage
    HadInPort createHadInPort();
    // hadEntity
    // qualifiedGeneration
    HadOutPort createHadOutPort();

    // ProvONE Aspect: Data Structure
    // Entity:     The prov:Entity class is adopted directly from the PROV Ontology
    // Collection: The prov:Collection class is adopted directly from the PROV Ontology
    Data createData();
    Visualization createVisualization();
    Document createDocument();
    // wasDerivedFrom: The prov:wasDerivedFrom object property is adopted directly from the PROV Ontology
    // hadMember: The prov:hadMember object property is adopted directly from the PROV Ontology
}