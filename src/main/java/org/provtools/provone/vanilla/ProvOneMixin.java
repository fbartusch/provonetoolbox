package org.provtools.provone.vanilla;

import org.openprovenance.prov.core.json.serialization.SortedBundle;
import org.openprovenance.prov.core.json.serialization.SortedDocument;
import org.openprovenance.prov.core.json.serialization.SortedProvOneBundle;
import org.openprovenance.prov.core.json.serialization.SortedProvOneDocument;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.openprovenance.prov.vanilla.*;

/*
 * Jackson mixin for ProvOne elements.
 */

public class ProvOneMixin {

    public ProvOneMixin() {
    }

    public void addProvMixin(ObjectMapper mapper) {
        mapper.addMixIn(Program.class,               org.provtools.provone.vanilla.JSON_Program.class);
        mapper.addMixIn(Port.class,                  org.provtools.provone.vanilla.JSON_Port.class);
        mapper.addMixIn(Channel.class,               org.provtools.provone.vanilla.JSON_Channel.class);
        mapper.addMixIn(Controller.class,            org.provtools.provone.vanilla.JSON_Controller.class);
        mapper.addMixIn(Workflow.class,              org.provtools.provone.vanilla.JSON_Workflow.class);
        mapper.addMixIn(HasSubProgram.class,         org.provtools.provone.vanilla.JSON_HasSubProgram.class);
        mapper.addMixIn(ControlledBy.class,          org.provtools.provone.vanilla.JSON_ControlledBy.class);
        mapper.addMixIn(Controls.class,              org.provtools.provone.vanilla.JSON_Controls.class);
        mapper.addMixIn(HasInPort.class,             org.provtools.provone.vanilla.JSON_HasInPort.class);
        mapper.addMixIn(HasOutPort.class,            org.provtools.provone.vanilla.JSON_HasOutPort.class);
        mapper.addMixIn(HasDefaultParam.class,       org.provtools.provone.vanilla.JSON_HasDefaultParam.class);
        mapper.addMixIn(ConnectsTo.class,            org.provtools.provone.vanilla.JSON_ConnectsTo.class);
        mapper.addMixIn(Execution.class,             org.provtools.provone.vanilla.JSON_Execution.class);
        mapper.addMixIn(User.class,                  org.provtools.provone.vanilla.JSON_User.class);
        mapper.addMixIn(WasPartOf.class,             org.provtools.provone.vanilla.JSON_WasPartOf.class);
        mapper.addMixIn(HadEntity.class,             org.provtools.provone.vanilla.JSON_HadEntity.class);
        mapper.addMixIn(HadInPort.class,             org.provtools.provone.vanilla.JSON_HadInPort.class);
        mapper.addMixIn(HadOutPort.class,            org.provtools.provone.vanilla.JSON_HadOutPort.class);
        mapper.addMixIn(Data.class,                  org.provtools.provone.vanilla.JSON_Data.class);
        mapper.addMixIn(Document.class,              org.provtools.provone.vanilla.JSON_Document.class);
        mapper.addMixIn(SortedProvOneDocument.class, org.provtools.provone.vanilla.JSON_SortedProvOneDocument.class);
        mapper.addMixIn(SortedProvOneBundle.class,   org.provtools.provone.vanilla.JSON_SortedProvOneBundle.class);

        mapper.addMixIn(Document.class,             org.openprovenance.prov.core.json.JSON_Document.class);
        mapper.addMixIn(SortedDocument.class,       org.openprovenance.prov.core.json.JSON_SortedDocument.class);
        mapper.addMixIn(ActedOnBehalfOf.class,      org.openprovenance.prov.core.json.JSON_ActedOnBehalfOf.class);
        mapper.addMixIn(Activity.class,             org.openprovenance.prov.core.json.JSON_Activity.class);
        mapper.addMixIn(HadMember.class,            org.openprovenance.prov.core.json.JSON_HadMember.class);
        mapper.addMixIn(Agent.class,                org.openprovenance.prov.core.json.JSON_Agent.class);
        mapper.addMixIn(AlternateOf.class,          org.openprovenance.prov.core.json.JSON_AlternateOf.class);
        mapper.addMixIn(Entity.class,               org.openprovenance.prov.core.json.JSON_Entity.class);
        mapper.addMixIn(SpecializationOf.class,     org.openprovenance.prov.core.json.JSON_SpecializationOf.class);
        mapper.addMixIn(Used.class,                 org.openprovenance.prov.core.json.JSON_Used.class);
        mapper.addMixIn(WasAssociatedWith.class,    org.openprovenance.prov.core.json.JSON_WasAssociatedWith.class);
        mapper.addMixIn(WasAttributedTo.class,      org.openprovenance.prov.core.json.JSON_WasAttributedTo.class);
        mapper.addMixIn(WasDerivedFrom.class,       org.openprovenance.prov.core.json.JSON_WasDerivedFrom.class);
        mapper.addMixIn(WasEndedBy.class,           org.openprovenance.prov.core.json.JSON_WasEndedBy.class);
        mapper.addMixIn(WasStartedBy.class,         org.openprovenance.prov.core.json.JSON_WasStartedBy.class);
        mapper.addMixIn(WasGeneratedBy.class,       org.openprovenance.prov.core.json.JSON_WasGeneratedBy.class);
        mapper.addMixIn(WasInvalidatedBy.class,     org.openprovenance.prov.core.json.JSON_WasInvalidatedBy.class);
        mapper.addMixIn(WasInformedBy.class,        org.openprovenance.prov.core.json.JSON_WasInformedBy.class);
        mapper.addMixIn(WasInfluencedBy.class,      org.openprovenance.prov.core.json.JSON_WasInfluencedBy.class);
        mapper.addMixIn(LangString.class,           org.openprovenance.prov.core.json.JSON_LangString.class);
        mapper.addMixIn(Role.class,                 org.openprovenance.prov.core.json.JSON_Attribute.class);
        mapper.addMixIn(Label.class,                org.openprovenance.prov.core.json.JSON_Attribute.class);
        mapper.addMixIn(Value.class,                org.openprovenance.prov.core.json.JSON_Attribute.class);
        mapper.addMixIn(Location.class,             org.openprovenance.prov.core.json.JSON_Attribute.class);
        mapper.addMixIn(Other.class,                org.openprovenance.prov.core.json.JSON_Attribute.class);
        mapper.addMixIn(Type.class,                 org.openprovenance.prov.core.json.JSON_Attribute.class);
        mapper.addMixIn(SortedBundle.class,         org.openprovenance.prov.core.json.JSON_SortedBundle.class);

        mapper.addMixIn(QualifiedSpecializationOf.class,  org.openprovenance.prov.core.json.JSON_QualifiedSpecializationOf.class);
        mapper.addMixIn(QualifiedAlternateOf.class,       org.openprovenance.prov.core.json.JSON_QualifiedAlternateOf.class);
        mapper.addMixIn(QualifiedHadMember.class,         org.openprovenance.prov.core.json.JSON_QualifiedHadMember.class);

    }
    
}
