package org.openprovenance.prov.core.jsonld;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import org.openprovenance.prov.core.jsonld.serialization.ProvOneConstants;
import org.openprovenance.prov.core.jsonld11.JLD_Document;
import org.openprovenance.prov.core.jsonld11.serialization.Constants;
import org.openprovenance.prov.model.Namespace;
import org.openprovenance.prov.model.StatementOrBundle;
import org.openprovenance.prov.vanilla.ActedOnBehalfOf;
import org.openprovenance.prov.vanilla.Activity;
import org.openprovenance.prov.vanilla.Agent;
import org.openprovenance.prov.vanilla.AlternateOf;
import org.openprovenance.prov.vanilla.Bundle;
import org.openprovenance.prov.vanilla.Entity;
import org.openprovenance.prov.vanilla.HadMember;
import org.openprovenance.prov.vanilla.QualifiedAlternateOf;
import org.openprovenance.prov.vanilla.QualifiedHadMember;
import org.openprovenance.prov.vanilla.QualifiedSpecializationOf;
import org.openprovenance.prov.vanilla.SpecializationOf;
import org.openprovenance.prov.vanilla.Used;
import org.openprovenance.prov.vanilla.WasAssociatedWith;
import org.openprovenance.prov.vanilla.WasAttributedTo;
import org.openprovenance.prov.vanilla.WasDerivedFrom;
import org.openprovenance.prov.vanilla.WasEndedBy;
import org.openprovenance.prov.vanilla.WasGeneratedBy;
import org.openprovenance.prov.vanilla.WasInfluencedBy;
import org.openprovenance.prov.vanilla.WasInformedBy;
import org.openprovenance.prov.vanilla.WasInvalidatedBy;
import org.openprovenance.prov.vanilla.WasStartedBy;
import org.provtools.provone.model.ProvOneStatementOrBundle;
import org.provtools.provone.vanilla.Channel;
import org.provtools.provone.vanilla.ConnectsTo;
import org.provtools.provone.vanilla.ControlledBy;
import org.provtools.provone.vanilla.Controller;
import org.provtools.provone.vanilla.Controls;
import org.provtools.provone.vanilla.Data;
import org.provtools.provone.vanilla.Document;
import org.provtools.provone.vanilla.Execution;
import org.provtools.provone.vanilla.HadEntity;
import org.provtools.provone.vanilla.HadInPort;
import org.provtools.provone.vanilla.HadOutPort;
import org.provtools.provone.vanilla.HasDefaultParam;
import org.provtools.provone.vanilla.HasInPort;
import org.provtools.provone.vanilla.HasOutPort;
import org.provtools.provone.vanilla.HasSubProgram;
import org.provtools.provone.vanilla.Port;
import org.provtools.provone.vanilla.Program;
import org.provtools.provone.vanilla.User;
import org.provtools.provone.vanilla.Visualization;
import org.provtools.provone.vanilla.WasPartOf;
import org.provtools.provone.vanilla.Workflow;

import java.util.List;

@JsonPropertyOrder({ "@context", "@graph"})
public interface JLD_ProvOneDocument extends JLD_Document {
  //  @JsonFilter("nsFilter")
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
        @JsonSubTypes.Type(value = HadOutPort.class,         name = ProvOneConstants.PROPERTY_PROVONE_HADOUTPORT),
        @JsonSubTypes.Type(value = Data.class,              name = ProvOneConstants.PROPERTY_PROVONE_DATA),
        @JsonSubTypes.Type(value = Document.class,          name = ProvOneConstants.PROPERTY_PROVONE_DOCUMENT),
        @JsonSubTypes.Type(value = Visualization.class,     name = ProvOneConstants.PROPERTY_PROVONE_VISUALIZATION),

        // These are needed. The JsonSubTypes from JLD_Document aren't used?
        @JsonSubTypes.Type(value = WasEndedBy.class,         name = Constants.PROPERTY_PROV_END),
        @JsonSubTypes.Type(value = WasStartedBy.class,       name = Constants.PROPERTY_PROV_START),
        @JsonSubTypes.Type(value = WasInvalidatedBy.class,   name = Constants.PROPERTY_PROV_INVALIDATION),
        @JsonSubTypes.Type(value = HadMember.class,          name = Constants.PROPERTY_PROV_MEMBERSHIP),
        @JsonSubTypes.Type(value = WasInfluencedBy.class,    name = Constants.PROPERTY_PROV_INFLUENCE),
        @JsonSubTypes.Type(value = WasInformedBy.class,      name = Constants.PROPERTY_PROV_COMMUNICATION),
        @JsonSubTypes.Type(value = WasDerivedFrom.class,     name = Constants.PROPERTY_PROV_DERIVATION),
        @JsonSubTypes.Type(value = AlternateOf.class,        name = Constants.PROPERTY_PROV_ALTERNATE),
        @JsonSubTypes.Type(value = SpecializationOf.class,   name = Constants.PROPERTY_PROV_SPECIALIZATION),
        @JsonSubTypes.Type(value = WasAttributedTo.class,    name = Constants.PROPERTY_PROV_ATTRIBUTION),
        @JsonSubTypes.Type(value = WasAssociatedWith.class,  name = Constants.PROPERTY_PROV_ASSOCIATION),
        @JsonSubTypes.Type(value = WasGeneratedBy.class,     name = Constants.PROPERTY_PROV_GENERATION),
        @JsonSubTypes.Type(value = Used.class,               name = Constants.PROPERTY_PROV_USED),
        @JsonSubTypes.Type(value = Activity.class,           name = Constants.PROPERTY_PROV_ACTIVITY),
        @JsonSubTypes.Type(value = Agent.class,              name = Constants.PROPERTY_PROV_AGENT),
        @JsonSubTypes.Type(value = Entity.class,             name = Constants.PROPERTY_PROV_ENTITY),
        @JsonSubTypes.Type(value = ActedOnBehalfOf.class,    name = Constants.PROPERTY_PROV_DELEGATION),
        @JsonSubTypes.Type(value = Bundle.class,             name = Constants.PROPERTY_PROV_BUNDLE),
        @JsonSubTypes.Type(value = QualifiedSpecializationOf.class,        name = Constants.PROPERTY_PROV_QUALIFIED_SPECIALIZATION),
        @JsonSubTypes.Type(value = QualifiedAlternateOf.class,             name = Constants.PROPERTY_PROV_QUALIFIED_ALTERNATE),
        @JsonSubTypes.Type(value = QualifiedHadMember.class,               name = Constants.PROPERTY_PROV_QUALIFIED_MEMBERSHIP)
    })
    @JsonProperty("@graph")
    List<StatementOrBundle> getStatementOrBundle();
    //List<ProvOneStatementOrBundle> getStatementOrBundles();
}
