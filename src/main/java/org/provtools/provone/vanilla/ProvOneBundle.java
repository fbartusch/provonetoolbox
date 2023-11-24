package org.provtools.provone.vanilla;

import java.util.Collection;

import org.openprovenance.prov.model.Namespace;
import org.openprovenance.prov.model.Statement;
import org.openprovenance.prov.vanilla.Bundle;

public class ProvOneBundle extends Bundle {

    public ProvOneBundle() {
        super();
    }

    public ProvOneBundle(org.openprovenance.prov.model.QualifiedName id, Namespace namespace, Collection<Statement> statements) {
        super(id, namespace, statements);
    }
}
