package org.openprovenance.prov.core.jsonld;

import org.openprovenance.prov.core.json.serialization.deserial.CustomQualifiedNameDeserializer;
import org.openprovenance.prov.model.QualifiedName;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonPropertyOrder({ "provone:program", "provone:controller" })
@JsonInclude(JsonInclude.Include.NON_EMPTY)

public interface JLD_ControlledBy {
    @JsonProperty("provone:program")
    @JsonDeserialize(using = CustomQualifiedNameDeserializer.class)
    public QualifiedName getProgram();

    @JsonProperty("provone:controller")
    @JsonDeserialize(using = CustomQualifiedNameDeserializer.class)
    public QualifiedName getController();
}
