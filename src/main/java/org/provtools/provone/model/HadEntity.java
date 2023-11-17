package org.provtools.provone.model;

import org.openprovenance.prov.model.QualifiedName;

public interface HadEntity extends ProvOneStatementOrBundle {

    void setActivity(QualifiedName uid);

    void setEntity(QualifiedName eid);

    QualifiedName getActivity();

    QualifiedName getEntity();
}
