package org.provtools.provone.vanilla;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.vanilla.Entity;
import org.provtools.provone.model.ProvOneStatementOrBundle;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.openprovenance.prov.model.Attribute;


public class Program extends Entity implements ProvOneStatementOrBundle {

    @JsonIgnore
    private List<org.provtools.provone.vanilla.Program> subPrograms = new LinkedList<>();
    @JsonIgnore
    private List<org.provtools.provone.vanilla.Port> inPorts = new LinkedList<>();
    @JsonIgnore
    private List<org.provtools.provone.vanilla.Port> outPorts = new LinkedList<>();

    protected Program() {
        //TODO: default constructor of Entity is private -.-
        super(null, null);
    };

    public Program(QualifiedName id, Collection<Attribute> attributes) {
        super(id, attributes);
    }

    public Program(QualifiedName id, List<Program> subPrograms, List<Port> inPorts,
            List<Port> outPorts, Collection<Attribute> attributes) {
        super(id, attributes);
        this.subPrograms = subPrograms;
        this.inPorts = inPorts;
        this.outPorts = outPorts;
    }

    @Override
    @JsonIgnore
    public ProvOneKind getProvOneKind() {
        return ProvOneKind.PROVONE_PROGRAM;
    }
}