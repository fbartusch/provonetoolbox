package org.provtools.provone.model;

import java.util.List;

import org.provtools.provone.vanilla.Program;

/**
 * <p>Interface for ProvONE objects that can have subPrograms.
 * <p>In ProvONE, <a href="http://jenkins-1.dataone.org/jenkins/view/Documentation%20Projects/job/ProvONE-Documentation-trunk/ws/provenance/ProvONE/v1/provone.html#hassubprogram-specification">hasSubProgram</a> 
 * specifies the recursive composition of Programs, a parent Program includes a child Program as part of its specification.
 *
 */
public interface HasSubProgram {
    
    /**
     */
    public List<Program> getSubPrograms();
    
    /**
     */
    public void addSubProgram(Program p);
} 