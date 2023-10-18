package org.provtools.provone.model;

import org.openprovenance.prov.vanilla.Entity;

/**
 * <p>Interface for ProvONE objects that have outPort.
 * <p>In ProvONE, <a href="http://jenkins-1.dataone.org/jenkins/view/Documentation%20Projects/job/ProvONE-Documentation-trunk/ws/provenance/ProvONE/v1/provone.html#hasdefaultparam-specification">hasDefaultParam</a> 
 * specifies that a given input Port has a certain Entity item as a default parameter (usually Data).
 * This enables the pre-configuration of executable Workflow instances with zero or more parameters.
 *
 */
public interface HasDefaultParam {
    
    void setDefaultParam(Entity e);

    Entity getDefaultParam();

} 