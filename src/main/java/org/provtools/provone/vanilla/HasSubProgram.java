package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.model.Value;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.openprovenance.prov.model.Attribute;
import org.openprovenance.prov.model.LangString;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


public class HasSubProgram implements org.provtools.provone.model.HasSubProgram {

    @JsonProperty("parent")
    private QualifiedName parent = null;
    @JsonProperty("child")
    private QualifiedName child = null;

    private List<org.openprovenance.prov.model.LangString> labels = new LinkedList<>();
    private List<org.openprovenance.prov.model.Location> location = new LinkedList<>();
    private List<org.openprovenance.prov.model.Other> other = new LinkedList<>();
    private List<org.openprovenance.prov.model.Type> type = new LinkedList<>();
    private Optional<org.openprovenance.prov.model.Value> value = Optional.empty();


    final ProvOneUtilities u = new ProvOneUtilities();

    protected HasSubProgram() {};

    public HasSubProgram(QualifiedName id, QualifiedName parent, QualifiedName child, Collection<Attribute> attributes) {
        this.parent = parent;
        this.child = child;

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
    public void setParent(QualifiedName pid) {
        this.parent = pid;
    }

    @Override
    public void setChild(QualifiedName pid) {
        this.child = pid;
    }

    @Override
    public QualifiedName getParent() {
        return this.parent;
    }

    @Override
    public QualifiedName getChild() {
        return this.child;
    }

    @JsonIgnore
    @Override
    public ProvOneKind getProvOneKind() {
        return ProvOneKind.PROVONE_HASSUBPROGRAM;
    }

    @JsonIgnore
    @Override
    public Kind getKind() {
        return null;
    }
}