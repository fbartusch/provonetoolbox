package org.provtools.provone.vanilla;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.openprovenance.prov.model.Namespace;
import org.openprovenance.prov.model.Statement;
import org.openprovenance.prov.model.StatementOrBundle;
import org.openprovenance.prov.vanilla.Bundle;

public class ProvOneDocument extends org.openprovenance.prov.vanilla.Document {

    private List<StatementOrBundle> statementsOrBundle;
    private Namespace namespace=new Namespace();
    
    public ProvOneDocument() {
        this.statementsOrBundle=new LinkedList<>();
    }

    public ProvOneDocument(List<StatementOrBundle> statementsOrBundle) {
        this.statementsOrBundle=statementsOrBundle;
    }

    public ProvOneDocument(Namespace namespace, List<StatementOrBundle> statementsOrBundle) {
        if (namespace!=null) this.namespace=namespace;
        this.statementsOrBundle=statementsOrBundle;
    }

    public ProvOneDocument(Namespace namespace, Collection<Statement> statements, Collection<Bundle> bundles) {
        if (namespace!=null) this.namespace=namespace;
        this.statementsOrBundle=new LinkedList<>();
        if (statements!=null) statementsOrBundle.addAll(statements);
        if (bundles!=null) statementsOrBundle.addAll(bundles);
    }
}
