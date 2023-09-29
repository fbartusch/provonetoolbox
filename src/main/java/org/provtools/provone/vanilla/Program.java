package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.vanilla.HasAttributes;
import org.openprovenance.prov.vanilla.Label;
import org.openprovenance.prov.vanilla.ProvUtilities;
import org.openprovenance.apache.commons.lang.builder.*;
import org.openprovenance.prov.model.Attribute;
import org.openprovenance.prov.model.Value;
import org.openprovenance.prov.model.Role;
import org.openprovenance.prov.model.StatementOrBundle;

import java.util.*;
import java.util.stream.Collectors;


public class Program implements org.provtools.provone.model.Program {

    private Optional<QualifiedName> id;

    private List<org.openprovenance.prov.model.LangString> labels = new LinkedList<>();
    private List<org.openprovenance.prov.model.Location> location = new LinkedList<>();
    private List<org.openprovenance.prov.model.Other> other = new LinkedList<>();
    private List<org.openprovenance.prov.model.Type> type = new LinkedList<>();


    static final ProvUtilities u=new ProvUtilities();



    private Program() {}

    public Program(QualifiedName id,
                 Collection<Attribute> attributes) {
        this.setId(id);
        // FIXME: populateAttributes from ProvUtilities is not visible
        u.populateAttributes(attributes, labels, location, type, new LinkedList<Role>(),other, null);

    }
}