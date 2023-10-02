package org.provtools.provone.model;

import org.openprovenance.prov.model.QualifiedName;

/**
 * <p>Interface for ProvONE objects that have inPort.
 * <p>In ProvONE, <a href="http://jenkins-1.dataone.org/jenkins/view/Documentation%20Projects/job/ProvONE-Documentation-trunk/ws/provenance/ProvONE/v1/provone.html#connectsto-specification">ConnectsTo</a> 
 * specifies the Channel that the given Port(s) connect to, typically with an output Port connected to an input Port.
 *
 */
public interface ConnectsTo {
    
    void setPort(QualifiedName pid);

    void setChannel(QualifiedName cid);

    QualifiedName getPort();

    QualifiedName getChannel();
} 