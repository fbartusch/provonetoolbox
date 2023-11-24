package org.openprovenance.prov.core.jsonld;

import org.openprovenance.prov.core.json.serialization.deserial.CustomQualifiedNameDeserializer;
import org.openprovenance.prov.model.QualifiedName;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonPropertyOrder({ "provone:port", "prov:entity" })
@JsonInclude(JsonInclude.Include.NON_EMPTY)

public interface JLD_HasDefaultParam {
    @JsonProperty("provone:port")
    @JsonDeserialize(using = CustomQualifiedNameDeserializer.class)
    public QualifiedName getPort();

    @JsonProperty("prov:entity")
    @JsonDeserialize(using = CustomQualifiedNameDeserializer.class)
    public QualifiedName getDefaultParam();
}
