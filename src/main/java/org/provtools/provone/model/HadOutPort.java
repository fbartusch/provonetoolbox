package org.provtools.provone.model;

import org.openprovenance.prov.model.QualifiedName;

/**
 * <p>Interface for ProvONE objects that have inPort.
 * <p>In ProvONE, <a href="http://jenkins-1.dataone.org/jenkins/view/Documentation%20Projects/job/ProvONE-Documentation-trunk/ws/provenance/ProvONE/v1/provone.html#hadoutport-specification">hadOutPort</a> 
 * specifies the Port of a particular Execution that was used as an output port, described in a given Generation.
 */
public interface HadOutPort {
    
    void setGeneration(QualifiedName uid);

    void setPort(QualifiedName pid);

    QualifiedName getGneration();

    QualifiedName getPort();
} 