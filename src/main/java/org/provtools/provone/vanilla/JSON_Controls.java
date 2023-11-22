package org.provtools.provone.vanilla;

import org.openprovenance.prov.core.json.serialization.deserial.CustomQualifiedNameDeserializer;
import org.openprovenance.prov.model.QualifiedName;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonPropertyOrder({ "provone:controller", "provone:program" })
@JsonInclude(JsonInclude.Include.NON_EMPTY)

public interface JSON_Controls {
    
    @JsonProperty("provone:controller")
    @JsonDeserialize(using = CustomQualifiedNameDeserializer.class)
    public QualifiedName getController();

    @JsonProperty("provone:program")
    @JsonDeserialize(using = CustomQualifiedNameDeserializer.class)
    public QualifiedName getProgram();
}
