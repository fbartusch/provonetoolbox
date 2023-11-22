package org.provtools.provone.vanilla;

import org.openprovenance.prov.core.json.serialization.deserial.CustomQualifiedNameDeserializer;
import org.openprovenance.prov.model.QualifiedName;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonPropertyOrder({ "parent", "child" })
@JsonInclude(JsonInclude.Include.NON_EMPTY)

public interface JSON_HasSubProgram {

    @JsonProperty("parent")
    @JsonDeserialize(using = CustomQualifiedNameDeserializer.class)
    public QualifiedName getParent();

    @JsonProperty("child")
    @JsonDeserialize(using = CustomQualifiedNameDeserializer.class)
    public QualifiedName getChild();
}
