package org.provtools.provone.model;

import org.openprovenance.prov.model.QualifiedName;

/**
 * <p>Interface for ProvONE objects that have outPort.
 * <p>In ProvONE, <a href="http://jenkins-1.dataone.org/jenkins/view/Documentation%20Projects/job/ProvONE-Documentation-trunk/ws/provenance/ProvONE/v1/provone.html#hasoutport-specification">hasInPort</a> 
 * specifies the Ports of a particular Program that are used as output ports.
 *
 */
public interface HasOutPort {

    void setOutPort(QualifiedName pid);

    QualifiedName getOutPort();
} 