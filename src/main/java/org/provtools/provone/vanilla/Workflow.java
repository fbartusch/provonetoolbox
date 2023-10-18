package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.model.Attribute;
import java.util.*;

public class Workflow extends Program {

    public Workflow(QualifiedName id, java.util.Collection<Attribute> attributes) {
        super(id, attributes);
    }

    public Workflow(QualifiedName id, List<Program> subPrograms, List<Port> inPorts, List<Port> outPorts,
            java.util.Collection<Attribute> attributes) {
        super(id, subPrograms, inPorts, outPorts, attributes);
    }
}