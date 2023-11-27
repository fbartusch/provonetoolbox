package org.openprovenance.prov.core.jsonld;

import java.util.List;

import org.openprovenance.prov.core.jsonld11.JLD_Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "@id" })
@JsonInclude(JsonInclude.Include.NON_EMPTY)

public interface JLD_Workflow extends JLD_Entity {

    @JsonIgnore
    public List<org.provtools.provone.vanilla.Program> getSubPrograms();
}
