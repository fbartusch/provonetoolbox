package org.provtools.provone.vanilla;

import org.openprovenance.prov.core.json.serialization.deserial.CustomQualifiedNameDeserializer;
import org.openprovenance.prov.model.QualifiedName;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonPropertyOrder({ "prov:activity", "prov:entity" })
@JsonInclude(JsonInclude.Include.NON_EMPTY)

public interface JSON_HadEntity {
    @JsonProperty("prov:activity")
    @JsonDeserialize(using = CustomQualifiedNameDeserializer.class)
    public QualifiedName getActivity();

    @JsonProperty("prov:entity")
    @JsonDeserialize(using = CustomQualifiedNameDeserializer.class)
    public QualifiedName getEntity();
}
