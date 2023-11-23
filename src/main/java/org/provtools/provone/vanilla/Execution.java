package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.vanilla.Activity;
import org.provtools.provone.model.ProvOneStatementOrBundle;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.openprovenance.prov.model.Attribute;

import javax.xml.datatype.XMLGregorianCalendar;


public class Execution extends Activity implements ProvOneStatementOrBundle {

    public Execution()  {
        //super(null, null, null, null);
        super();
    }

    public Execution(QualifiedName id, XMLGregorianCalendar startTime, XMLGregorianCalendar endTime, java.util.Collection<Attribute> attributes) {
        super(id, startTime, endTime, attributes);
    }

    @Override
    @JsonIgnore
    public ProvOneKind getProvOneKind() {
        return ProvOneKind.PROVONE_EXECUTION;
    }

    @Override
    public Kind getKind() {
        return Kind.PROV_ACTIVITY;
    }
}