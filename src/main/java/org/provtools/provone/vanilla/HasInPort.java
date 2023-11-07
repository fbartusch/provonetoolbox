package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.model.StatementOrBundle;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.openprovenance.prov.model.Attribute;
import org.openprovenance.prov.model.LangString;
import org.openprovenance.prov.model.ProvUtilities;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


public class HasInPort implements org.provtools.provone.model.HasInPort {

    private QualifiedName program = null;
    private QualifiedName port = null;
    private List<org.openprovenance.prov.model.LangString> labels = new LinkedList<>();
    Collection<Attribute> attributes;

    final ProvUtilities u=new ProvUtilities();

    public HasInPort(QualifiedName id, QualifiedName program, QualifiedName port, Collection<Attribute> attributes) {
        this.program = program;
        this.port = port;
        this.attributes = attributes;
    }

    @JsonIgnore
    @Override
    public List<LangString> getLabel() {
        return labels;
    }

    @JsonIgnore
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