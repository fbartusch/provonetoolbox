package org.provtools.provone.model;

import java.util.Collection;

import javax.xml.datatype.XMLGregorianCalendar;

import org.openprovenance.prov.model.Attribute;
import org.openprovenance.prov.model.QualifiedName;
import org.provtools.provone.vanilla.Controls;
import org.provtools.provone.vanilla.Execution;
import org.provtools.provone.vanilla.Port;
import org.provtools.provone.vanilla.Program;
import org.provtools.provone.vanilla.Channel;
import org.provtools.provone.vanilla.Controller;
import org.provtools.provone.vanilla.Workflow;

/** Interface for constructing concrete representations of the ProvONE data model. */

public interface ProvOneModelConstructor {

    // Elements of the ProvONE data model:
    // https://github.com/DataONEorg/sem-prov-ontologies/blob/b25fe3c4579f7909fba9e29b0fc094e8ee74e808/provenance/ProvONE/v1/owl/provone.owl
    // TODO: Declare function for each ProvOne element

    /*
    *
    *  ProvONE Aspect: Workflow
    *
    */

    /** A factory method to create an instance of the Program class {@link Program}
     * @param id a mandatory identifier for the Program
     * @param attributes an optional set of attribute-value pairs representing additional information about the Program
     * @return an instance of {@link Program}
     */
    public Program newProgram(QualifiedName id, Collection<Attribute> attributes);


    /** A factory method to create an instance of the Port class {@link Port}
     * @param id a mandatory identifier for the Port
     * @param attributes an optional set of attribute-value pairs representing additional information about the Port
     * @return an instance of {@link Port}
     */
    public Port newPort(QualifiedName id, Collection<Attribute> attributes);


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


    /** A factory method to create an instance of the ControlledBy object property {@link ControlledBy}
     * @param id an optional identifier for the controlledBy object property
     * @param program the identifier for the <a href="http://jenkins-1.dataone.org/jenkins/view/Documentation%20Projects/job/ProvONE-Documentation-trunk/ws/provenance/ProvONE/v1/provone.html#program-specification">program</a> being controlled
     * @param controller the identifier of the <a href="http://jenkins-1.dataone.org/jenkins/view/Documentation%20Projects/job/ProvONE-Documentation-trunk/ws/provenance/ProvONE/v1/provone.html#controller-specification">controller</a> that controls the program
     * @param attributes an optional set of attribute-value pairs representing additional information about the Workflow
     * @return an instance of {@link ControlledBy}
     */
    public ControlledBy newControlledBy(QualifiedName id, QualifiedName program, QualifiedName controller, Collection<Attribute> attributes);


    /** A factory method to create an instance of a controls object property {@link Controls}
     * Relates a Controller to its destination Program
     * @param id an optional identifier for the controls object property
     * @param controller the identifier of the <a href="http://jenkins-1.dataone.org/jenkins/view/Documentation%20Projects/job/ProvONE-Documentation-trunk/ws/provenance/ProvONE/v1/provone.html#controller-specification">controller</a> that controls the program
     * @param program the identifier for the <a href="http://jenkins-1.dataone.org/jenkins/view/Documentation%20Projects/job/ProvONE-Documentation-trunk/ws/provenance/ProvONE/v1/provone.html#program-specification">program</a> being controlled
     * @param attributes an optional set of attribute-value pairs representing additional information about this usage
     * @return an instance of {@link Controls}
     */
    public Controls newControls(QualifiedName id, QualifiedName controller, QualifiedName program, Collection<Attribute> attributes);


    /** A factory method to create an instance of the ConnectsTo object property {@link ConnectsTo}
     * @param id an optional identifier for the connection
     * @param port the identifier of the Port
     * @param channel the identifier of the Channel
     * @param attributes an optional set of attribute-value pairs representing additional information about the Connections
     * @return an instance of {@link ConnectsTo}
     */
    public ConnectsTo newConnectsTo(QualifiedName id, QualifiedName port, QualifiedName channel, Collection<Attribute> attributes);

    // 3.1.13 wasDerivedFrom object property already implemented by ProvToolbox

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

    //TODO: Maybe we need the hadEntity defined in ProvONE? There is no hadEntity in PROV?

}
