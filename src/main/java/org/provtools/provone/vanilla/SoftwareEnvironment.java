package org.provtools.provone.vanilla;

import java.util.Collection;

import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.vanilla.Entity;
import org.provtools.provone.model.ProvOneStatementOrBundle;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.openprovenance.prov.model.Attribute;


public class SoftwareEnvironment extends Entity implements ProvOneStatementOrBundle {

    protected SoftwareEnvironment() {
        super(null, null);
    };

    public SoftwareEnvironment(QualifiedName id, Collection<Attribute> attributes) {
        super(id, attributes);
    }

    @Override
    @JsonIgnore
    public ProvOneKind getProvOneKind() {
        return ProvOneKind.SEO_SOFTWAREENVIRONMENT;
    }
}