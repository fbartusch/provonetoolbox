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

    protected Program() {
        super(null, null);
    };

    public Program(QualifiedName id, Collection<Attribute> attributes) {
        super(id, attributes);
    }

    @Override
    @JsonIgnore
    public ProvOneKind getProvOneKind() {
        return ProvOneKind.PROVONE_PROGRAM;
    }
}