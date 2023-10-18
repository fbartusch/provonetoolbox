package org.provtools.provone.vanilla;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.vanilla.Entity;
import org.provtools.provone.model.HasInPort;
import org.provtools.provone.model.HasOutPort;
import org.provtools.provone.model.HasSubProgram;
import org.openprovenance.prov.model.Attribute;


public class Program extends Entity implements HasSubProgram, HasInPort, HasOutPort {

    private List<org.provtools.provone.vanilla.Program> subPrograms = new LinkedList<>();
    private List<org.provtools.provone.vanilla.Port> inPorts = new LinkedList<>();
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
    public List<Program> getSubPrograms() {
        return subPrograms;
    }

    @Override
    public void addSubProgram(Program p) {
        subPrograms.add(p);
    }

    @Override
    public void addOutPort(Port p) {
        outPorts.add(p);
    }

    @Override
    public List<Port> getOutPorts() {
        return outPorts;
    }

    @Override
    public void addInPort(Port p) {
        inPorts.add(p);
    }

    @Override
    public List<Port> getInPorts() {
        return inPorts;
    }
}