package org.openprovenance.prov.core.jsonld;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.openprovenance.prov.core.jsonld11.serialization.MisnamedBundle;
import org.openprovenance.prov.vanilla.*;
import org.provtools.provone.vanilla.Channel;
import org.provtools.provone.vanilla.ConnectsTo;
import org.provtools.provone.vanilla.ControlledBy;
import org.provtools.provone.vanilla.Controller;
import org.provtools.provone.vanilla.Controls;
import org.provtools.provone.vanilla.Data;
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
import org.provtools.provone.vanilla.ProvOneBundle;
import org.provtools.provone.vanilla.User;
import org.provtools.provone.vanilla.WasPartOf;
import org.provtools.provone.vanilla.Workflow;

/*
 * Jackson mixin for ProvOne elements.
 */

public class ProvOneMixin {

    public ProvOneMixin() {
    }

    public void addProvMixin(ObjectMapper mapper) {
        mapper.addMixIn(Program.class,               org.openprovenance.prov.core.jsonld.JLD_Program.class);
        mapper.addMixIn(Port.class,                  org.openprovenance.prov.core.jsonld.JLD_Port.class);
        mapper.addMixIn(Channel.class,               org.openprovenance.prov.core.jsonld.JLD_Channel.class);
        mapper.addMixIn(Controller.class,            org.openprovenance.prov.core.jsonld.JLD_Controller.class);
        mapper.addMixIn(Workflow.class,              org.openprovenance.prov.core.jsonld.JLD_Workflow.class);
        mapper.addMixIn(HasSubProgram.class,         org.openprovenance.prov.core.jsonld.JLD_HasSubProgram.class);
        mapper.addMixIn(ControlledBy.class,          org.openprovenance.prov.core.jsonld.JLD_ControlledBy.class);
        mapper.addMixIn(Controls.class,              org.openprovenance.prov.core.jsonld.JLD_Controls.class);
        mapper.addMixIn(HasInPort.class,             org.openprovenance.prov.core.jsonld.JLD_HasInPort.class);
        mapper.addMixIn(HasOutPort.class,            org.openprovenance.prov.core.jsonld.JLD_HasOutPort.class);
        mapper.addMixIn(HasDefaultParam.class,       org.openprovenance.prov.core.jsonld.JLD_HasDefaultParam.class);
        mapper.addMixIn(ConnectsTo.class,            org.openprovenance.prov.core.jsonld.JLD_ConnectsTo.class);
        mapper.addMixIn(Execution.class,             org.openprovenance.prov.core.jsonld.JLD_Execution.class);
        mapper.addMixIn(User.class,                  org.openprovenance.prov.core.jsonld.JLD_User.class);
        mapper.addMixIn(WasPartOf.class,             org.openprovenance.prov.core.jsonld.JLD_WasPartOf.class);
        mapper.addMixIn(HadEntity.class,             org.openprovenance.prov.core.jsonld.JLD_HadEntity.class);
        mapper.addMixIn(HadInPort.class,             org.openprovenance.prov.core.jsonld.JLD_HadInPort.class);
        mapper.addMixIn(HadOutPort.class,            org.openprovenance.prov.core.jsonld.JLD_HadOutPort.class);
        mapper.addMixIn(Data.class,                  org.openprovenance.prov.core.jsonld.JLD_Data.class);
        mapper.addMixIn(org.provtools.provone.vanilla.Document.class,              org.openprovenance.prov.core.jsonld.JLD_Document.class);
        mapper.addMixIn(ProvOneBundle.class,         org.openprovenance.prov.core.jsonld.JLD_ProvOneBundle.class);

        mapper.addMixIn(org.openprovenance.prov.vanilla.Document.class,             JLD_Document.class);
        mapper.addMixIn(ActedOnBehalfOf.class,      org.openprovenance.prov.core.jsonld11.JLD_ActedOnBehalfOf.class);
        mapper.addMixIn(Activity.class,             org.openprovenance.prov.core.jsonld11.JLD_Activity.class);
        mapper.addMixIn(HadMember.class,            org.openprovenance.prov.core.jsonld11.JLD_HadMember.class);
        mapper.addMixIn(Agent.class,                org.openprovenance.prov.core.jsonld11.JLD_Agent.class);
        mapper.addMixIn(AlternateOf.class,          org.openprovenance.prov.core.jsonld11.JLD_AlternateOf.class);
        mapper.addMixIn(Entity.class,               org.openprovenance.prov.core.jsonld11.JLD_Entity.class);
        mapper.addMixIn(SpecializationOf.class,     org.openprovenance.prov.core.jsonld11.JLD_SpecializationOf.class);
        mapper.addMixIn(Used.class,                 org.openprovenance.prov.core.jsonld11.JLD_Used.class);
        mapper.addMixIn(WasAssociatedWith.class,    org.openprovenance.prov.core.jsonld11.JLD_WasAssociatedWith.class);
        mapper.addMixIn(WasAttributedTo.class,      org.openprovenance.prov.core.jsonld11.JLD_WasAttributedTo.class);
        mapper.addMixIn(WasDerivedFrom.class,       org.openprovenance.prov.core.jsonld11.JLD_WasDerivedFrom.class);
        mapper.addMixIn(WasEndedBy.class,           org.openprovenance.prov.core.jsonld11.JLD_WasEndedBy.class);
        mapper.addMixIn(WasStartedBy.class,         org.openprovenance.prov.core.jsonld11.JLD_WasStartedBy.class);
        mapper.addMixIn(WasGeneratedBy.class,       org.openprovenance.prov.core.jsonld11.JLD_WasGeneratedBy.class);
        mapper.addMixIn(WasInvalidatedBy.class,     org.openprovenance.prov.core.jsonld11.JLD_WasInvalidatedBy.class);
        mapper.addMixIn(WasInformedBy.class,        org.openprovenance.prov.core.jsonld11.JLD_WasInformedBy.class);
        mapper.addMixIn(WasInfluencedBy.class,      org.openprovenance.prov.core.jsonld11.JLD_WasInfluencedBy.class);
        mapper.addMixIn(LangString.class,           org.openprovenance.prov.core.jsonld11.JLD_LangString.class);
        mapper.addMixIn(Role.class,                 org.openprovenance.prov.core.jsonld11.JLD_Attribute.class);
        mapper.addMixIn(Label.class,                org.openprovenance.prov.core.jsonld11.JLD_Attribute.class);
        mapper.addMixIn(Value.class,                org.openprovenance.prov.core.jsonld11.JLD_Attribute.class);
        mapper.addMixIn(Location.class,             org.openprovenance.prov.core.jsonld11.JLD_Attribute.class);
        mapper.addMixIn(Other.class,                org.openprovenance.prov.core.jsonld11.JLD_Attribute.class);
        mapper.addMixIn(Type.class,                 org.openprovenance.prov.core.jsonld11.JLD_Attribute.class);
        mapper.addMixIn(TypedValue.class,           org.openprovenance.prov.core.jsonld11.JLD_Attribute.class); //FIXME LUC
        mapper.addMixIn(Bundle.class,               org.openprovenance.prov.core.jsonld11.JLD_Bundle.class);
        mapper.addMixIn(MisnamedBundle.class,       org.openprovenance.prov.core.jsonld11.JLD_Bundle.class);
        mapper.addMixIn(QualifiedSpecializationOf.class,  org.openprovenance.prov.core.jsonld11.JLD_QualifiedSpecializationOf.class);
        mapper.addMixIn(QualifiedAlternateOf.class,       org.openprovenance.prov.core.jsonld11.JLD_QualifiedAlternateOf.class);
        mapper.addMixIn(QualifiedHadMember.class,         org.openprovenance.prov.core.jsonld11.JLD_QualifiedHadMember.class);
    }
    
}
