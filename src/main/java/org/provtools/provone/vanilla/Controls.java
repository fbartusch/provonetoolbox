package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;

// The class is the inverse of ControlledBy. ProvONE specifies both controlledBy and controls, so I add this here, although it does not add
// any new functionality

//TODO This is the total opposite of ControlledBy. One could change the ontology and make Controls/ControlledBy inverse properties?
// Having both is somehow useless, but the ontologies defines both ...

public class Controls implements org.provtools.provone.model.ControlledBy {

    QualifiedName program = null;
    QualifiedName controller = null;

    public Controls(QualifiedName id, QualifiedName controller, QualifiedName program) {
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

    
}
