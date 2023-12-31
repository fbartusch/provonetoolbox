package org.provtools.provone.model;

import org.openprovenance.prov.model.QualifiedName;

/**
 * <p>Interface for ProvONE objects that have subProgram.
 * <p>In ProvONE, <a href="http://jenkins-1.dataone.org/jenkins/view/Documentation%20Projects/job/ProvONE-Documentation-trunk/ws/provenance/ProvONE/v1/provone.html#waspartof-specification">WasPartOf</a> 
 * enables the specification of the structure of Execution instances in that a parent Execution (associated with a Workflow) has child Executions (associated with Programs and subworkflows). 
 *
 */
public interface WasPartOf extends ProvOneStatementOrBundle {
    
    /*
    * 
    */ 
    public void setParent(QualifiedName pid);

    /*
     * 
     */
    public void setChild(QualifiedName cid);

    /*
     * 
     */
    public QualifiedName getParent();

    /*
     * 
     */
    public QualifiedName getChild();
} 