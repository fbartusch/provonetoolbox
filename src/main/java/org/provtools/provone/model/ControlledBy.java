package org.provtools.provone.model;

import org.openprovenance.prov.model.QualifiedName;

public interface ControlledBy {
    void setProgram(QualifiedName pid);

    void setController(QualifiedName aid);

    QualifiedName getProgram();

    QualifiedName getController();
}
