package org.provtools.provone.model;

import org.openprovenance.prov.model.QualifiedName;

public interface HadEntity extends ProvOneStatementOrBundle {

    void setUsage(QualifiedName uid);

    void setEntity(QualifiedName eid);

    QualifiedName getUsage();

    QualifiedName getEntity();
}
