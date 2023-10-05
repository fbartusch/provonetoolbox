package org.provtools.provone.model;

import java.util.Collection;

import org.openprovenance.prov.model.Attribute;
import org.openprovenance.prov.model.QualifiedName;
import org.provtools.provone.vanilla.Controls;
import org.provtools.provone.vanilla.Port;
import org.provtools.provone.vanilla.Program;

/** Interface for constructing concrete representations of the ProvONE data model */

public interface ProvOneModelConstructor {

    // Elements of the ProvONE data model:
    // https://github.com/DataONEorg/sem-prov-ontologies/blob/b25fe3c4579f7909fba9e29b0fc094e8ee74e808/provenance/ProvONE/v1/owl/provone.owl

    // ProvONE Aspect: Workflow
    public Program newProgram(QualifiedName id, Collection<Attribute> attributes);
    public Port newPort(QualifiedName id, Collection<Attribute> attributes);




    /** A factory method to create an instance of a controls object property {@link Controls}
     * Relates a Controller to its destination Program
     * @param id an optional identifier for the controls object property
     * @param controller the identifier of the <a href="http://jenkins-1.dataone.org/jenkins/view/Documentation%20Projects/job/ProvONE-Documentation-trunk/ws/provenance/ProvONE/v1/provone.html#controller-specification">controller</a> that controls the program
     * @param program the identifier for the <a href="http://jenkins-1.dataone.org/jenkins/view/Documentation%20Projects/job/ProvONE-Documentation-trunk/ws/provenance/ProvONE/v1/provone.html#program-specification">program</a> being controlled
     * @param attributes an optional set of attribute-value pairs representing additional information about this usage
     * @return an instance of {@link Controls}
     */
    public Controls newControls(QualifiedName id, QualifiedName controller, QualifiedName program, Collection<Attribute> attributes);


}
