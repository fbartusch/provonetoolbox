package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.vanilla.Entity;
import org.openprovenance.prov.model.Attribute;
import java.util.Collection;


public class Data extends Entity {

    private String sha256;

    protected Data() {
        super(null, null);
        sha256 = null;
    }

    public Data(QualifiedName id, Collection<Attribute> attributes, String sha256) {
        super(id, attributes);
        this.sha256 = sha256;
    }

    protected void setSha256(String s) {
        sha256 = s;
    }

    public String getSha256() {
        return sha256;
    }
}