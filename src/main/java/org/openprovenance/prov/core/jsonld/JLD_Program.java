package org.openprovenance.prov.core.jsonld;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "@id" })
@JsonInclude(JsonInclude.Include.NON_EMPTY)

public class JLD_Program {
    @JsonIgnore
    public List<org.provtools.provone.vanilla.Program> getSubPrograms;
}
