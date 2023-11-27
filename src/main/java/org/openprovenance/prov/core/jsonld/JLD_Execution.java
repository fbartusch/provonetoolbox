package org.openprovenance.prov.core.jsonld;

import javax.xml.datatype.XMLGregorianCalendar;

import org.openprovenance.prov.core.jsonld11.JLD_Activity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)

public interface JLD_Execution extends JLD_Activity {
//    
//    @JsonProperty("prov:startTime")
//    XMLGregorianCalendar getStartTime();
//
//    void setStartTime(XMLGregorianCalendar value);
//
//    @JsonProperty("prov:endTime")
//    XMLGregorianCalendar getEndTime();
//
//    void setEndTime(XMLGregorianCalendar value);
}
