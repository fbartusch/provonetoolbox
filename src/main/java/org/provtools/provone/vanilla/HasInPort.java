package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.model.StatementOrBundle;
import org.openprovenance.prov.model.Value;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.openprovenance.prov.model.Attribute;
import org.openprovenance.prov.model.LangString;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


public class HasInPort implements org.provtools.provone.model.HasInPort {

    @JsonProperty("provone:program")
    private QualifiedName program = null;
    @JsonProperty("provone:port")
    private QualifiedName port = null;

    private List<org.openprovenance.prov.model.LangString> labels = new LinkedList<>();
    private List<org.openprovenance.prov.model.Location> location = new LinkedList<>();
    private List<org.openprovenance.prov.model.Other> other = new LinkedList<>();
    private List<org.openprovenance.prov.model.Type> type = new LinkedList<>();
    private Optional<org.openprovenance.prov.model.Value> value = Optional.empty();


    final ProvOneUtilities u = new ProvOneUtilities();

    protected HasInPort() {};

    public HasInPort(QualifiedName id, QualifiedName program, QualifiedName port, Collection<Attribute> attributes) {
        this.program = program;
        this.port = port;

        location = new LinkedList<>();
        type = new LinkedList<>();
        other = new LinkedList<>();
        Value [] valueHolder = new Value[1];
        valueHolder[0] = null;
        u.populateAttributes(attributes, labels, location, type, new LinkedList<>(), other, valueHolder);
        value = Optional.ofNullable(valueHolder[0]);
    }

    @Override
    public List<LangString> getLabel() {
        return labels;
    }

    @Override
    public void setProgram(QualifiedName pid) {
        this.program = pid;
    }

    @Override
    public void setPort(QualifiedName pid) {
        this.port = pid;
    }

    @Override
    public QualifiedName getProgram() {
        return this.program;
    }

    @Override
    public QualifiedName getPort() {
        return this.port;
    }

    @JsonIgnore
    @Override
    public ProvOneKind getProvOneKind() {
        return ProvOneKind.PROVONE_HASINPORT;
    }

    @JsonIgnore
    @Override
    public Kind getKind() {
        return null;
    }
}