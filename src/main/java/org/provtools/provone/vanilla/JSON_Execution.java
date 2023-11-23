package org.provtools.provone.vanilla;

import javax.xml.datatype.XMLGregorianCalendar;

import org.openprovenance.prov.core.json.HasAttributes;
import org.openprovenance.prov.core.json.HasKind;
import org.openprovenance.prov.core.json.HasLabel;
import org.openprovenance.prov.core.json.HasLocation;
import org.openprovenance.prov.core.json.HasOther;
import org.openprovenance.prov.core.json.HasType;
import org.openprovenance.prov.core.json.JSON_Activity;
import org.openprovenance.prov.core.json.JSON_Identifiable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "@id", "prov:startTime", "prov:endTime" })
@JsonInclude(JsonInclude.Include.NON_NULL)

public interface JSON_Execution extends JSON_Identifiable, HasKind, HasLabel, HasLocation, HasType, HasOther, HasAttributes {
    
    @JsonProperty("prov:startTime")
    XMLGregorianCalendar getStartTime();

    void setStartTime(XMLGregorianCalendar value);

    @JsonProperty("prov:endTime")
    XMLGregorianCalendar getEndTime();

    void setEndTime(XMLGregorianCalendar value);
}
