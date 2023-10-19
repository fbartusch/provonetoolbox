package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.vanilla.Activity;
import org.openprovenance.prov.model.Attribute;

import javax.xml.datatype.XMLGregorianCalendar;


public class Execution extends Activity {

    public Execution(QualifiedName id, XMLGregorianCalendar startTime, XMLGregorianCalendar endTime,
            java.util.Collection<Attribute> attributes) {
        super(id, startTime, endTime, attributes);
    }
}