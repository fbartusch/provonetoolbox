package org.provtools.provone.model;

import org.provtools.provone.vanilla.Program;

/**
 * <p>Interface for ProvONE objects that have subProgram.
 * <p>In ProvONE, <a href="http://jenkins-1.dataone.org/jenkins/view/Documentation%20Projects/job/ProvONE-Documentation-trunk/ws/provenance/ProvONE/v1/provone.html#hassubprogram-specification">hasSubProgram</a> 
 * specifies the recursive composition of Programs, a parent Program includes a child Program as part of its specification.
 *
 */
public interface HasSubProgram {
    
    /**
     */
    public Program getSubProgram();
    
    /**
     */
    public void setSubProgram(Program time);
} 