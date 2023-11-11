package org.provtools.provone.model;

import java.util.Collection;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.openprovenance.prov.model.Attribute;
import org.openprovenance.prov.model.Bundle;
import org.openprovenance.prov.model.Entity;
import org.openprovenance.prov.model.HadMember;
import org.openprovenance.prov.model.Namespace;
import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.model.Statement;
import org.openprovenance.prov.model.Used;
import org.openprovenance.prov.model.WasAssociatedWith;
import org.openprovenance.prov.model.WasGeneratedBy;
import org.openprovenance.prov.model.WasInformedBy;
import org.provtools.provone.vanilla.Controls;
import org.provtools.provone.vanilla.Data;
import org.openprovenance.prov.vanilla.Document;
import org.openprovenance.prov.model.WasDerivedFrom;
import org.provtools.provone.vanilla.Execution;
import org.provtools.provone.vanilla.Port;
import org.provtools.provone.vanilla.Program;
import org.provtools.provone.vanilla.User;
import org.provtools.provone.vanilla.Channel;
import org.provtools.provone.vanilla.Controller;
import org.provtools.provone.vanilla.Visualization;
import org.provtools.provone.vanilla.Workflow;
import org.provtools.provone.vanilla.HasInPort;

/** Interface for constructing concrete representations of the ProvONE data model. */

public interface ProvOneModelConstructor {

    // Elements of the ProvONE data model:
    // https://github.com/DataONEorg/sem-prov-ontologies/blob/b25fe3c4579f7909fba9e29b0fc094e8ee74e808/provenance/ProvONE/v1/owl/provone.owl

    /*
    *
    *  ProvONE Aspect: Workflow
    *
    */

    /** A factory method to create an instance of the Program class {@link Program}
     * @param id a mandatory identifier for the Program
     * @param subPrograms an optional list of subPrograms
     * @param inPorts an optional list of input ports
     * @param outPorts an optional list of output ports
     * @param attributes an optional set of attribute-value pairs representing additional information about the Program
     * @return an instance of {@link Program}
     */
    public Program newProgram(QualifiedName id, List<Program> subPrograms, List<Port> inPorts, List<Port> outPorts, Collection<Attribute> attributes);


    /** A factory method to create an instance of the Port class {@link Port}
     * @param id a mandatory identifier for the Port
     * @param defaultParam an optional default parameter for input ports
     * @param channels an optional list of channels this port connects to
     * @param attributes an optional set of attribute-value pairs representing additional information about the Port
     * @return an instance of {@link Port}
     */
    public Port newPort(QualifiedName id, Entity defaultParam, List<Channel> channels, Collection<Attribute> attributes);


    /** A factory method to create an instance of the Channel class {@link Channel}
     * @param id a mandatory identifier for the Channel
     * @param attributes an optional set of attribute-value pairs representing additional information about the Channel
     * @return an instance of {@link Channel}
     */
    public Channel newChannel(QualifiedName id, Collection<Attribute> attributes);


    /** A factory method to create an instance of the Controller class {@link Controller}
     * @param id a mandatory identifier for the Controller
     * @param attributes an optional set of attribute-value pairs representing additional information about the Controller
     * @return an instance of {@link Controller}
     */
    public Controller newController(QualifiedName id, Collection<Attribute> attributes);


    /** A factory method to create an instance of the Workflow class {@link Workflow}
     * @param id a mandatory identifier for the Workflow
     * @param attributes an optional set of attribute-value pairs representing additional information about the Workflow
     * @return an instance of {@link Workflow}
     */
    public Workflow newWorkflow(QualifiedName id, Collection<Attribute> attributes);


    /** A factory method to create an instance of the hasSubProgram relation {@link HasSubProgram}
     * @param id a mandatory identifier for the Workflow
     * @param parent a mandatory identifier for the parent program
     * @param child a mandatory identifier for the child program
     * @param attributes an optional set of attribute-value pairs representing additional information about the Workflow
     * @return an instance of {@link HasSubProgram}
     */
    public HasSubProgram newHasSubProgram(QualifiedName id, QualifiedName parent, QualifiedName child, Collection<Attribute> attributes);


    /** A factory method to create an instance of the ControlledBy object property {@link ControlledBy}
     * @param id an optional identifier for the controlledBy object property
     * @param program the identifier for the <a href="http://jenkins-1.dataone.org/jenkins/view/Documentation%20Projects/job/ProvONE-Documentation-trunk/ws/provenance/ProvONE/v1/provone.html#program-specification">program</a> being controlled
     * @param controller the identifier of the <a href="http://jenkins-1.dataone.org/jenkins/view/Documentation%20Projects/job/ProvONE-Documentation-trunk/ws/provenance/ProvONE/v1/provone.html#controller-specification">controller</a> that controls the program
     * @return an instance of {@link ControlledBy}
     */
    public ControlledBy newControlledBy(QualifiedName id, QualifiedName program, QualifiedName controller);


    /** A factory method to create an instance of a controls object property {@link Controls}
     * Relates a Controller to its destination Program
     * @param id an optional identifier for the controls object property
     * @param controller the identifier of the <a href="http://jenkins-1.dataone.org/jenkins/view/Documentation%20Projects/job/ProvONE-Documentation-trunk/ws/provenance/ProvONE/v1/provone.html#controller-specification">controller</a> that controls the program
     * @param program the identifier for the <a href="http://jenkins-1.dataone.org/jenkins/view/Documentation%20Projects/job/ProvONE-Documentation-trunk/ws/provenance/ProvONE/v1/provone.html#program-specification">program</a> being controlled
     * @return an instance of {@link Controls}
     */
    public Controls newControls(QualifiedName id, QualifiedName controller, QualifiedName program);


    /** A factory method to create an instance of a hasInPort relation {@link HasInPort}
     * Relates a Program to one of its input ports
     * @param id an optional identifier for the hasInPort relation
     * @param program the identifier of the <a href="http://jenkins-1.dataone.org/jenkins/view/Documentation%20Projects/job/ProvONE-Documentation-trunk/ws/provenance/ProvONE/v1/provone.html#program-specification">program</a>
     * @param port the identifier for the <a href="http://jenkins-1.dataone.org/jenkins/view/Documentation%20Projects/job/ProvONE-Documentation-trunk/ws/provenance/ProvONE/v1/provone.html#port-specification">port</a>
     * @param attributes an optional set of attribute-value pairs representing additional information about input port
     * @return an instance of {@link HasInPort}
     */
    public HasInPort newHasInPort(QualifiedName id, QualifiedName program, QualifiedName port, Collection<Attribute> attributes);


    /** A factory method to create an instance of a hasOutPort relation {@link HasOutPort}
     * Relates a Program to one of its output ports
     * @param id an optional identifier for the hasOutPort relation
     * @param program the identifier of the <a href="http://jenkins-1.dataone.org/jenkins/view/Documentation%20Projects/job/ProvONE-Documentation-trunk/ws/provenance/ProvONE/v1/provone.html#program-specification">program</a>
     * @param port the identifier for the <a href="http://jenkins-1.dataone.org/jenkins/view/Documentation%20Projects/job/ProvONE-Documentation-trunk/ws/provenance/ProvONE/v1/provone.html#port-specification">port</a>
     * @param attributes an optional set of attribute-value pairs representing additional information about output port
     * @return an instance of {@link HasOutPort}
     */
    public HasOutPort newHasOutPort(QualifiedName id, QualifiedName program, QualifiedName port, Collection<Attribute> attributes);


    /** A factory method to create an instance of a hasDefaultParam relation {@link HasDefaultParam}
     * Relates an input port to its default parameter.
     * @param port the identifier of the <a href="http://jenkins-1.dataone.org/jenkins/view/Documentation%20Projects/job/ProvONE-Documentation-trunk/ws/provenance/ProvONE/v1/provone.html#port-specification">port</a>
     * @param param the identifier for the <a href="">entity</a>
     * @return an instance of {@link HasDefaultParam}
     */
    public HasDefaultParam newHasDefaultParam(QualifiedName program, QualifiedName param);


    /** A factory method to create an instance of the ConnectsTo object property {@link ConnectsTo}
     * @param id an optional identifier for the connection
     * @param port the identifier of the Port
     * @param channel the identifier of the Channel
     * @return an instance of {@link ConnectsTo}
     */
    public ConnectsTo newConnectsTo(QualifiedName id, QualifiedName port, QualifiedName channel);

    /** A factory method to create an instance of a derivation {@link WasDerivedFrom}
     * @param id an optional identifier for a derivation
     * @param e2 the identifier  of the <a href="http://www.w3.org/TR/prov-dm/#derivation.generatedEntity">entity generated</a> by the derivation 
     * @param e1 the identifier  of the <a href="http://www.w3.org/TR/prov-dm/#derivation.usedEntity">entity used</a> by the derivation
     * @param activity an identifier for the <a href="http://www.w3.org/TR/prov-dm/#derivation.activity">activity</a> underpinning the derivation
     * @param generation an identifier for the <a href="http://www.w3.org/TR/prov-dm/#derivation.genertion">generation</a> associated with the derivation
     * @param usage an identifier for the <a href="http://www.w3.org/TR/prov-dm/#derivation.usage">usage</a> associated with the derivation
     * @param attributes an optional set of <a href="http://www.w3.org/TR/prov-dm/#end.attributes">attribute-value pairs</a> representing additional information about this derivation
     * @return an instance of {@link WasDerivedFrom}
     */
    public WasDerivedFrom newWasDerivedFrom(QualifiedName id, QualifiedName e2, QualifiedName e1, QualifiedName activity, QualifiedName generation, QualifiedName usage,  Collection<Attribute> attributes);


    /*
    *
    *  ProvONE Aspect: Trace
    *
    */

    
    /** A factory method to create an instance of the Execution class {@link Execution}
     * @param id a mandatory identifier for the Execution
     * @param startTime start time of the execution
     * @param endTime end time of the execution
     * @param attributes an optional set of attribute-value pairs representing additional information about the Channel
     * @return an instance of {@link Execution}
     */
    public Execution newExecution(QualifiedName id, XMLGregorianCalendar startTime, XMLGregorianCalendar endTime, Collection<Attribute> attributes);


    /** A factory method to create an instance of the User class {@link User}
     * @param id a mandatory identifier for the user
     * @param attributes an optional set of attribute-value pairs representing additional information about the user
     * @return an instance of {@link User}
     */
    public User newUser(QualifiedName id, Collection<Attribute> attributes);

    
    /** A factory method to create an instance of a Usage {@link Used}
     * Copied directly from: org.provtools.provone.model.ModelConstructor
     * @param id an optional identifier for a usage
     * @param activity the identifier  of the <a href="http://www.w3.org/TR/prov-dm/#usage.activity">activity</a> that used an entity
     * @param entity an optional identifier for the <a href="http://www.w3.org/TR/prov-dm/#usage.entity">entity</a> being used
     * @param time an optional "usage time", the <a href="http://www.w3.org/TR/prov-dm/#usage.time">time</a> at which the entity started to be used
     * @param attributes an optional set of attribute-value pairs representing additional information about this usage
     * @return an instance of {@link Used}
     */
    public Used newUsed(QualifiedName id, QualifiedName activity, QualifiedName entity, XMLGregorianCalendar time, Collection<Attribute> attributes);
    

    /** A factory method to create an instance of a generation {@link WasGeneratedBy}
     * Copied directly from: org.provtools.provone.model.ModelConstructor
     * @param id an optional identifier for a usage
     * @param entity an identifier for the created <a href="http://www.w3.org/TR/prov-dm/#generation.entity">entity</a>
     * @param activity an optional identifier  for the <a href="http://www.w3.org/TR/prov-dm/#generation.activity">activity</a> that creates the entity
     * @param time an optional "generation time", the time at which the entity was completely created
     * @param attributes an optional set of attribute-value pairs representing additional information about this generation
     * @return an instance of {@link WasGeneratedBy}
     */    
     public WasGeneratedBy newWasGeneratedBy(QualifiedName id, QualifiedName entity, QualifiedName activity, XMLGregorianCalendar time, Collection<Attribute> attributes);


    /** A factory method to create an instance of an Association {@link WasAssociatedWith}
     * Copied directly from: org.provtools.provone.model.ModelConstructor
     * @param id an optional identifier for the association between an activity and an agent
     * @param activity an identifier for the activity
     * @param agent an optional identifier for the agent associated with the activity
     * @param plan an optional identifier for the plan the agent relied on in the context of this activity
     * @param attributes an optional set of attribute-value pairs representing additional information about this association of this activity with this agent.
     * @return an instance of {@link WasAssociatedWith}
     */ 
    public WasAssociatedWith newWasAssociatedWith(QualifiedName id, QualifiedName activity, QualifiedName agent, QualifiedName plan, Collection<Attribute> attributes);
 
    
    /** A factory method to create an instance of an communication {@link WasInformedBy}
     * @param id an optional identifier identifying the association;
     * @param informed the identifier of the informed activity;
     * @param informant the identifier of the informant activity;
     * @param attributes an optional set of attribute-value pairs representing additional information about this communication.
     * @return an instance of {@link WasInformedBy}
     */
    public WasInformedBy newWasInformedBy(QualifiedName id, QualifiedName informed, QualifiedName informant, Collection<Attribute> attributes);


    /** A factory method to create an instance of the wasPartOf property {@link WasPartOf}
     * @param id an optional identifier identifying for the object property;
     * @param child execution associated with a program or subworkflow
     * @param parent execution associated with a workflow
     * @param attributes an optional set of attribute-value pairs representing additional information about this communication.
     * @return an instance of {@link WasPartOf}
     */
    public WasPartOf newWasPartOf(QualifiedName id, QualifiedName child, QualifiedName parent, Collection<Attribute> attributes);


    /** A factory method to create an instance of the HadOutputPort object property {@link HadInPort}
     * @param id an optional identifier for the property
     * @param property The Usage or Generatation that had the input port.
     * @param port The input port.
     * @param attributes an optional set of attribute-value pairs representing additional information about the Port
     * @return an instance of {@link HadInPort}
     */
    public HadInPort newHadInPort(QualifiedName id, QualifiedName usage, QualifiedName port, Collection<Attribute> attributes);


    /** A factory method to create an instance of the HadEntity object property {@link HadEntity}
     * @param id an optional identifier for the property
     * @param property The Usage/Generation property that used/generated the Entity.
     * @param entity The entity used in the Usage or generated in the Generation
     * @param attributes an optional set of attribute-value pairs representing additional information about the Channel
     * @return an instance of {@link HadEntity}
     */
    public HadEntity newHadEntity(QualifiedName id, QualifiedName property, QualifiedName entity, Collection<Attribute> attributes);


    /** A factory method to create an instance of the HadOutputPort object property {@link HadOutPort}
     * @param id an optional identifier for the property
     * @param generation The Generation that had the output port.
     * @param port The output port.
     * @param attributes an optional set of attribute-value pairs representing additional information about the Channel
     * @return an instance of {@link HadOutPort}
     */
    public HadOutPort newHadOutPort(QualifiedName id, QualifiedName generation, QualifiedName port, Collection<Attribute> attributes);


    /*
    *
    *  ProvONE Aspect: Data Structure
    *
    */


    /** A factory method to create an instance of the Entity class {@link Entity}
     * @param id a mandatory identifier for the Entity
     * @param attributes an optional set of attribute-value pairs representing additional information about the Entity
     * @return an instance of {@link Entity}
     */
    public Entity newEntity(QualifiedName id, Collection<Attribute> attributes);


    /** A factory method to create an instance of the Data class {@link Data}
     * @param id a mandatory identifier for the Data
     * @param attributes an optional set of attribute-value pairs representing additional information about the Data
     * @return an instance of {@link Data}
     */
    public Data newData(QualifiedName id, Collection<Attribute> attributes);


    /** A factory method to create an instance of the Visualization class {@link Visualization}
     * @param id a mandatory identifier for the Visualization
     * @param attributes an optional set of attribute-value pairs representing additional information about the Visualization
     * @return an instance of {@link Visualization}
     */
    public Visualization newVisualization(QualifiedName id, Collection<Attribute> attributes);


    /** A factory method to create an instance of a {@link Document}
     * @param namespace the prefix namespace mapping for the current document
     * @param statements a collection of statements
     * @param bundles a collection of bundles
     * @return an instance of {@link Document}, with this prefix-namespace mapping, statements, and bundles
     */
    public Document newDocument(Namespace namespace,
                                Collection<Statement> statements, 
                                Collection<Bundle> bundles);

                                
    /** A factory method to create an instance of Collection Membershop {@link HadMember}
     * @param id a mandatory identifier for the Collection
     * @param entities Entities making up the collection
     * @return an instance of {@link HadMember}
     */                            
    public HadMember newHadMember(QualifiedName id, Collection<QualifiedName> entities);
}
