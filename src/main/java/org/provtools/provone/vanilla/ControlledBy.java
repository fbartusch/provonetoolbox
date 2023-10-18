package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;

public class ControlledBy implements org.provtools.provone.model.ControlledBy {

    QualifiedName program = null;
    QualifiedName controller = null;

    public ControlledBy(QualifiedName id, QualifiedName program, QualifiedName controller) {
        this.program = program;
        this.controller = controller;
    }

    @Override
    public void setProgram(QualifiedName pid) {
        this.program = pid;
    }

    @Override
    public void setController(QualifiedName cid) {
        this.controller = cid;
    }

    @Override
    public QualifiedName getProgram() {
        return this.program;
    }

    @Override
    public QualifiedName getController() {
        return this.controller;
    }


}
