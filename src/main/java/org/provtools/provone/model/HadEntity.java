package org.provtools.provone.model;

import org.openprovenance.prov.model.QualifiedName;

public interface HadEntity {

    void setProperty(QualifiedName pid);

    void setEntity(QualifiedName eid);

    QualifiedName getProperty();

    QualifiedName getEntity();
}
