package org.provtools.provone.model;

import org.openprovenance.prov.model.QualifiedName;

public interface HadEntity {

    void setUsage(QualifiedName uid);

    void setGeneration(QualifiedName pid);

    void setEntity(QualifiedName eid);

    QualifiedName getUsage();

    QualifiedName getGeneration();

    QualifiedName getEntity();
}
