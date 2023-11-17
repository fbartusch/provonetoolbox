package org.provtools.provone.model;

import org.openprovenance.prov.model.QualifiedName;

/**
 * <p>Interface for ProvONE objects that have inPort.
 * <p>In ProvONE, <a href="http://jenkins-1.dataone.org/jenkins/view/Documentation%20Projects/job/ProvONE-Documentation-trunk/ws/provenance/ProvONE/v1/provone.html#hadinport-specification">hadInPort</a> 
 * specifies the Port of a particular Execution that was used as input ports, described in a given Usage. 
 */
public interface HadInPort extends ProvOneStatementOrBundle {
    
    void setUsage(QualifiedName uid);

    void setPort(QualifiedName pid);

    QualifiedName getUsage();

    QualifiedName getPort();
} 