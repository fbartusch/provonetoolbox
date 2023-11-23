package org.provtools.provone.vanilla;

import org.openprovenance.prov.core.json.serialization.deserial.CustomQualifiedNameDeserializer;
import org.openprovenance.prov.model.QualifiedName;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonPropertyOrder({ "provone:port", "provone:channel" })
@JsonInclude(JsonInclude.Include.NON_EMPTY)

public interface JSON_ConnectsTo {
    @JsonProperty("provone:port")
    @JsonDeserialize(using = CustomQualifiedNameDeserializer.class)
    public QualifiedName getPort();

    @JsonProperty("provone:channel")
    @JsonDeserialize(using = CustomQualifiedNameDeserializer.class)
    public QualifiedName getChannel();
}
