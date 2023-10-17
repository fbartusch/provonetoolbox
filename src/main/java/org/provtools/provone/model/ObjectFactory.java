package org.provtools.provone.model;

import org.openprovenance.prov.vanilla.WasDerivedFrom;

import org.openprovenance.prov.model.Agent;
import org.openprovenance.prov.model.Entity;
import org.openprovenance.prov.model.Used;
import org.openprovenance.prov.model.WasAssociatedWith;
import org.openprovenance.prov.model.WasGeneratedBy;
import org.openprovenance.prov.model.WasInformedBy;
import org.openprovenance.prov.vanilla.Document;
import org.openprovenance.prov.vanilla.HadMember;
import org.provtools.provone.vanilla.Channel;
import org.provtools.provone.vanilla.Collection;
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
    
    /*
    *  ProvONE Aspect: Workflow
    */
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
    // From ProvToolBox:
    WasDerivedFrom createWasDerivedFrom();

    /*
    *  ProvONE Aspect: Trace
    */
    Execution createExecution();

    /* Association
     * ProvONE uses the PROV-O Association class from the PROV Ontology (PROV-O).
     * But the ProvToolbox implements the PROV Data Model (PROV-DM) has no Association class.
     * There is also no qualifiedAssociation in ProvToolbox.
     * But the wasGeneratedBy class in ProvToolbox provides everything we need:
     *  - Linking an Activity (Execution) to
     *  - An Agent (User) and
     *  - A Plan (Program)
     * 
     * So programmatically and semantically WasAssociatedWith would provides everything we need.
     * But when ProvToolbox serialises the document, there is no prov:qualifiedAssociation and prov:Association output.
     * Everything is written as prov:wasAssociatedWith.
     * 
     * But at first we stick to wasAssociatedWith. Same for Usage and Generation.
     */
    //Association createAssociation();
    //Usage
    //Generation
    User createUser();

    // used: The prov:Used class is used directly from ProvToolBox
    Used createUsed();

    // Generation: The prov:WasGeneratedBy class is used directly from the PROV Ontology
    WasGeneratedBy createWasGeneratedBy();

    // wasAssociatedWith: the prov:WasAssocidatedWith is used directly from the PROV Ontology
    WasAssociatedWith createWasAssociatedWith();

    // wasInformedBy: the prov:WasInformedBy is used directly from the PROV Ontology
    WasInformedBy createWasInformedBy();

    WasPartOf createWasPartOf();
    // See comment for Association. We stick just to wasAssociatedWith for the time beeing.
    // QualifiedAssocitation

    // Agent: we don't need this. It's part of an Association. The agent there is an User.
    // See comment for Association. We stick just to wasAssociatedWith for the time beeing. We can state a plan for wasAssociatedWith.
    // Agent createAgent();

    // See comment for Association. We stick just to wasAssociatedWith for the time beeing. We can state a plan for wasAssociatedWith.
    // hadPlan

    // Same as for QualifiedAssociation? But Maybe we have to implement something, as an Usage in PROVOne also uses non PROV relation hadInPort 
    // QualifiedUsage

    HadInPort createHadInPort();
    HadEntity createHadEntity();

    // Same as for QualifiedAssociation? But Maybe we have to implement something, as an Usage in PROVOne also uses non PROV relation hadInPort 
    // QualifiedGeneration
    HadOutPort createHadOutPort();

    /*
    *  ProvONE Aspect: Data Structure
    */
    // The prov:Entity class is adopted directly from the PROV Ontology
    Entity createEntity();
    // Maybe we don't need Collection and work just with HadMember?
    // Collection createCollection();
    Data createData();
    Visualization createVisualization();
    // From ProvToolBox:
    Document createDocument();
    // The prov:hadMember object property is adopted directly from the PROV Ontology
    HadMember createHadMember();
}