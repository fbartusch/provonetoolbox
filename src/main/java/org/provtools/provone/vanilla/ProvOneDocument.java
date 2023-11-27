package org.provtools.provone.vanilla;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.openprovenance.prov.model.Namespace;
import org.openprovenance.prov.model.Statement;
import org.openprovenance.prov.model.StatementOrBundle;
import org.openprovenance.prov.model.Bundle;

import org.openprovenance.prov.model.Document;

public class ProvOneDocument extends org.openprovenance.prov.vanilla.Document {

    private List<StatementOrBundle> statementsOrBundle;
    private Namespace namespace = new Namespace();
    
    public ProvOneDocument() {
        super();
        this.statementsOrBundle=new LinkedList<>();
    }

    public ProvOneDocument(List<StatementOrBundle> statementsOrBundle) {
        super(statementsOrBundle);
        this.statementsOrBundle=statementsOrBundle;
    }

    public ProvOneDocument(Document doc) {
        super(doc.getNamespace(), doc.getStatementOrBundle());
        statementsOrBundle = doc.getStatementOrBundle();
        namespace = doc.getNamespace();
    }

    public ProvOneDocument(Namespace namespace, List<StatementOrBundle> statementsOrBundle) {
        super(namespace, statementsOrBundle);
        if (namespace != null) this.namespace=namespace;
        this.statementsOrBundle = statementsOrBundle;
    }
    public ProvOneDocument(Namespace namespace, Collection<Statement> statements, Collection<Bundle> bundles) {
        super(namespace, statements, bundles);
        if (namespace != null) this.namespace=namespace;
        this.statementsOrBundle = new LinkedList<>();
        if (statements!=null) statementsOrBundle.addAll(statements);
        if (bundles!=null) statementsOrBundle.addAll(bundles);
    }
}
