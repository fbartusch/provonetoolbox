package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.vanilla.Activity;
import org.provtools.provone.model.ProvOneStatementOrBundle;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.openprovenance.prov.model.Attribute;

import java.time.OffsetDateTime;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;


public class Execution extends Activity implements ProvOneStatementOrBundle {

    public Execution()  {
        //super(null, null, null, null);
        super();
    }

    public Execution(QualifiedName id, XMLGregorianCalendar startTime, XMLGregorianCalendar endTime, java.util.Collection<Attribute> attributes) {
        super(id, startTime, endTime, attributes);
    }

    @JsonIgnore
    public void setStartTime(OffsetDateTime endTime) {
        // 1. Convert OffsetDateTime -> GregorianCalendar
        GregorianCalendar gregStartTime = GregorianCalendar.from(endTime.toZonedDateTime());
        // 2. Convert GregorianCalendar to XMLGregorianCalendar
        try {
            XMLGregorianCalendar xmlGregStartTime = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregStartTime);
            this.setEndTime(xmlGregStartTime);
        } catch (DatatypeConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @JsonIgnore
    public void setEndTime(OffsetDateTime endTime) {
        // 1. Convert OffsetDateTime -> GregorianCalendar
        GregorianCalendar gregEndTime = GregorianCalendar.from(endTime.toZonedDateTime());
        // 2. Convert GregorianCalendar to XMLGregorianCalendar
        try {
            XMLGregorianCalendar xmlGregEndTime = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregEndTime);
            this.setEndTime(xmlGregEndTime);
        } catch (DatatypeConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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