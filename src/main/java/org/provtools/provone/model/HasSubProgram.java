package org.provtools.provone.model;

import org.openprovenance.prov.model.HasLabel;
import org.openprovenance.prov.model.QualifiedName;

/**
 * <p>Interface for ProvONE objects that can have subPrograms.
 * <p>In ProvONE, <a href="http://jenkins-1.dataone.org/jenkins/view/Documentation%20Projects/job/ProvONE-Documentation-trunk/ws/provenance/ProvONE/v1/provone.html#hassubprogram-specification">hasSubProgram</a> 
 * specifies the recursive composition of Programs, a parent Program includes a child Program as part of its specification.
 *
 */
public interface HasSubProgram extends HasLabel, ProvOneStatementOrBundle{
    
    void setParent(QualifiedName pid);

    void setChild(QualifiedName pid);

    QualifiedName getParent();

    QualifiedName getChild();
} 