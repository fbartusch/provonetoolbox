package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ControlledBy implements org.provtools.provone.model.ControlledBy {

    @JsonProperty("provone:program")
    QualifiedName program = null;
    @JsonProperty("provone:controller")
    QualifiedName controller = null;

    public ControlledBy(QualifiedName program, QualifiedName controller) {
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

    @JsonIgnore
    @Override
    public ProvOneKind getProvOneKind() {
        return ProvOneKind.PROVONE_CONTROLLEDBY;
    }

    @Override
    public Kind getKind() {
        return null;
    }


}
