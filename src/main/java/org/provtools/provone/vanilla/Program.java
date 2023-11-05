package org.provtools.provone.vanilla;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.vanilla.Entity;
import org.provtools.provone.model.HasInPort;
import org.provtools.provone.model.HasOutPort;
import org.provtools.provone.model.HasSubProgram;
import org.provtools.provone.model.ProvOneStatementOrBundle;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.openprovenance.prov.model.Attribute;


public class Program extends Entity implements ProvOneStatementOrBundle, HasSubProgram, HasInPort, HasOutPort {

    @JsonIgnore
    private List<org.provtools.provone.vanilla.Program> subPrograms = new LinkedList<>();
    @JsonIgnore
    private List<org.provtools.provone.vanilla.Port> inPorts = new LinkedList<>();
    @JsonIgnore
    private List<org.provtools.provone.vanilla.Port> outPorts = new LinkedList<>();

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
    public List<Program> getSubPrograms() {
        return subPrograms;
    }

    @Override
    @JsonIgnore
    public void addSubProgram(Program p) {
        subPrograms.add(p);
    }

    @Override
    @JsonIgnore
    public void addOutPort(Port p) {
        outPorts.add(p);
    }

    @Override
    @JsonIgnore
    public List<Port> getOutPorts() {
        return outPorts;
    }

    @Override
    @JsonIgnore
    public void addInPort(Port p) {
        inPorts.add(p);
    }

    @Override
    @JsonIgnore
    public List<Port> getInPorts() {
        return inPorts;
    }

    @Override
    @JsonIgnore
    public ProvOneKind getProvOneKind() {
        return ProvOneKind.PROVONE_PROGRAM;
    }
}