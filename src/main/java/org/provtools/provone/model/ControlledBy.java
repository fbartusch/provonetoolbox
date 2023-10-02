package org.provtools.provone.model;

import org.openprovenance.prov.model.HasLabel;
import org.openprovenance.prov.model.HasType;
import org.openprovenance.prov.model.Identifiable;
import org.openprovenance.prov.model.QualifiedName;

public interface ControlledBy  extends Identifiable, HasLabel, HasType {
    void setProgram(QualifiedName pid);

    void setController(QualifiedName aid);

    QualifiedName getProgram();

    QualifiedName getController();
}
