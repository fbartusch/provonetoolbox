package org.provtools.provone.model;

import org.provtools.provone.vanilla.Execution;

/**
 * <p>Interface for ProvONE objects that have subProgram.
 * <p>In ProvONE, <a href="http://jenkins-1.dataone.org/jenkins/view/Documentation%20Projects/job/ProvONE-Documentation-trunk/ws/provenance/ProvONE/v1/provone.html#waspartof-specification">WasPartOf</a> 
 * enables the specification of the structure of Execution instances in that a parent Execution (associated with a Workflow) has child Executions (associated with Programs and subworkflows). 
 *
 */
public interface WasPartOf {
    
    /*
    * 
    */ 
    public void setParentExecution();

    /*
     * 
     */
    public void setChildExecution();

    /*
     * 
     */
    public Execution getParentExecution();

    /*
     * 
     */
    public Execution getChildExecution();
} 