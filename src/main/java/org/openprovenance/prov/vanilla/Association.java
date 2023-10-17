package org.openprovenance.prov.vanilla;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.openprovenance.prov.model.Attribute;
import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.model.Role;

public class Association implements org.openprovenance.prov.model.Association {

    private Optional<QualifiedName> id;

    private List<org.openprovenance.prov.model.LangString> labels = new LinkedList<>();
    private List<org.openprovenance.prov.model.Location> location = new LinkedList<>();
    private List<org.openprovenance.prov.model.Other> other = new LinkedList<>();
    private List<org.openprovenance.prov.model.Type> type = new LinkedList<>();
    private List<org.openprovenance.prov.model.Role> roles = new LinkedList<>();


    static final ProvUtilities u=new ProvUtilities();



    private Association() {};

    public Association(QualifiedName id,
                       Collection<Attribute> attributes) {
        this.setId(id);
        u.populateAttributes(attributes, labels, location, type, roles ,other, null);
    }


    @Override
    public QualifiedName getId() {
        return id.orElse(null);
    }

    @Override
    public void setId(QualifiedName value) {
        id = Optional.ofNullable(value);
    }

    @Override
    public List<org.openprovenance.prov.model.LangString> getLabel() {
        return labels;
    }

    @Override
    public List<org.openprovenance.prov.model.Type> getType() {
        return type;
    }

    @Override
    public List<org.openprovenance.prov.model.Location> getLocation() {
        return location;
    }

    @Override
    public List<org.openprovenance.prov.model.Other> getOther() {
        return other;
    }

    @Override
    public List<Role> getRole() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRole'");
    }

    @Override
    public Kind getKind() {
        return Kind.PROV_ASSOCIATION;
    }
    
}
