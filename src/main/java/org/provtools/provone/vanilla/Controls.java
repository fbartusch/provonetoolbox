package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

// The class is the inverse of ControlledBy. ProvONE specifies both controlledBy and controls, so I add this here, although it does not add
// any new functionality

public class Controls implements org.provtools.provone.model.ControlledBy {

    @JsonProperty("provone:program")
    QualifiedName program = null;
    @JsonProperty("provone:controller")
    QualifiedName controller = null;

    public Controls(QualifiedName controller, QualifiedName program) {
        this.controller = controller;
        this.program = program;
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
        return ProvOneKind.PROVONE_CONTROLS;
    }

    @Override
    public Kind getKind() {
        return null;
    }
}
