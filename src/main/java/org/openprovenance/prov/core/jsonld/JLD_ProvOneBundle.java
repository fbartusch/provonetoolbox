package org.openprovenance.prov.core.jsonld;

import com.fasterxml.jackson.annotation.*;

import org.openprovenance.prov.core.jsonld11.JLD_Bundle;
import org.openprovenance.prov.core.jsonld11.serialization.Constants;
import org.openprovenance.prov.core.jsonld.serialization.ProvOneConstants;
import org.openprovenance.prov.model.Namespace;
import org.openprovenance.prov.model.Statement;
import org.openprovenance.prov.vanilla.Document;
import org.provtools.provone.vanilla.*;

import java.util.List;

@JsonPropertyOrder({ "@context", "@id", "@graph" })

public interface JLD_ProvOneBundle extends JLD_Bundle {
    @JsonProperty("@context")
    Namespace getNamespace();

    @JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY, property = Constants.PROPERTY_BLOCK_TYPE)
    @JsonSubTypes({
            @JsonSubTypes.Type(value = Program.class,           name = ProvOneConstants.PROPERTY_PROVONE_PROGRAM),
            @JsonSubTypes.Type(value = Port.class,              name = ProvOneConstants.PROPERTY_PROVONE_PORT),
            @JsonSubTypes.Type(value = Channel.class,           name = ProvOneConstants.PROPERTY_PROVONE_CHANNEL),
            @JsonSubTypes.Type(value = Controller.class,        name = ProvOneConstants.PROPERTY_PROVONE_CONTROLLER),
            @JsonSubTypes.Type(value = Workflow.class,          name = ProvOneConstants.PROPERTY_PROVONE_WORKFLOW),
            @JsonSubTypes.Type(value = HasSubProgram.class,     name = ProvOneConstants.PROPERTY_PROVONE_HASSUBPROGRAM),
            @JsonSubTypes.Type(value = ControlledBy.class,      name = ProvOneConstants.PROPERTY_PROVONE_CONTROLLEDBY),
            @JsonSubTypes.Type(value = Controls.class,          name = ProvOneConstants.PROPERTY_PROVONE_CONTROLS),
            @JsonSubTypes.Type(value = HasInPort.class,         name = ProvOneConstants.PROPERTY_PROVONE_HASINPORT),
            @JsonSubTypes.Type(value = HasOutPort.class,        name = ProvOneConstants.PROPERTY_PROVONE_HASOUTPORT),
            @JsonSubTypes.Type(value = HasDefaultParam.class,   name = ProvOneConstants.PROPERTY_PROVONE_HASDEFAULTPARAM),
            @JsonSubTypes.Type(value = ConnectsTo.class,        name = ProvOneConstants.PROPERTY_PROVONE_CONNECTSTO),
            @JsonSubTypes.Type(value = Execution.class,         name = ProvOneConstants.PROPERTY_PROVONE_EXECUTION),
            @JsonSubTypes.Type(value = User.class,              name = ProvOneConstants.PROPERTY_PROVONE_USER),
            @JsonSubTypes.Type(value = WasPartOf.class,         name = ProvOneConstants.PROPERTY_PROVONE_WASPARTOF),
            @JsonSubTypes.Type(value = HadEntity.class,         name = ProvOneConstants.PROPERTY_PROVONE_HADENTITY),
            @JsonSubTypes.Type(value = HadInPort.class,         name = ProvOneConstants.PROPERTY_PROVONE_HADINPORT),
            @JsonSubTypes.Type(value = Data.class,              name = ProvOneConstants.PROPERTY_PROVONE_DATA),
            @JsonSubTypes.Type(value = Document.class,          name = ProvOneConstants.PROPERTY_PROVONE_DOCUMENT),
            @JsonSubTypes.Type(value = Visualization.class,     name = ProvOneConstants.PROPERTY_PROVONE_VISUALIZATION)
    })
    
    @JsonProperty("@graph")
    List<Statement> getStatement();
}
