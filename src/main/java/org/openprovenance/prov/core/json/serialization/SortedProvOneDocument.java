package org.openprovenance.prov.core.json.serialization;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.openprovenance.prov.model.*;
import org.provtools.provone.vanilla.Channel;
import org.provtools.provone.vanilla.Controller;
import org.provtools.provone.vanilla.Data;
import org.provtools.provone.vanilla.Execution;
import org.provtools.provone.vanilla.Port;
import org.provtools.provone.vanilla.Program;
import org.provtools.provone.vanilla.User;
import org.provtools.provone.vanilla.Visualization;
import org.provtools.provone.vanilla.Workflow;


/*
 * Provides methods needed for ProvOne elements.
 */

public class SortedProvOneDocument extends SortedProvOneBundle {

    Map<QualifiedName,Bundle> theBundles = new HashMap<>();

    public SortedProvOneDocument(Document doc) {

        this.namespace=new Namespace(doc.getNamespace());

        // prov-json assumes default namespace to be listed with the "reserved" prefix "default"
        if (namespace.getDefaultNamespace()!=null) this.namespace.register("default", namespace.getDefaultNamespace());

        // We cannot add new kinds, but we can define new kinds for ProvOne elements.
        // E.g. we add a new switch statement inside case PROV_ENTITY for Program etc.
        for (StatementOrBundle s: doc.getStatementOrBundle()) {
            if (s.getKind() == null) {
                // For relations like hasInPort that relates two entities.
                // But there is no suitable relation between two entities in provtoolbox.
                if (s.getClass() == org.provtools.provone.vanilla.HasInPort.class) {
                    put(hasInPort, s);
                }
                else if (s.getClass() == org.provtools.provone.vanilla.HasOutPort.class) {
                    put(hasOutPort, s);
                }
                else if (s.getClass() == org.provtools.provone.vanilla.HasSubProgram.class) {
                    put(hasSubProgram, s);
                }
                else if (s.getClass() == org.provtools.provone.vanilla.ControlledBy.class) {
                    put(controlledBy, s);
                }
                else if (s.getClass() == org.provtools.provone.vanilla.Controls.class) {
                    put(controls, s);
                }
                else if (s.getClass() == org.provtools.provone.vanilla.HasDefaultParam.class) {
                    put(hasDefaultParam, s);
                }
                else if (s.getClass() == org.provtools.provone.vanilla.ConnectsTo.class) {
                    put(connectsTo, s);
                }
                else if (s.getClass() == org.provtools.provone.vanilla.WasPartOf.class) {
                    put(wasPartOf, s);
                }
                else if (s.getClass() == org.provtools.provone.vanilla.HadEntity.class) {
                    put(hadEntity, s);
                }
                else if (s.getClass() == org.provtools.provone.vanilla.HadInPort.class) {
                    put(hadInPort, s);
                }
                else if (s.getClass() == org.provtools.provone.vanilla.HadOutPort.class) {
                    put(hadOutPort, s);
                }
                continue;
            }
            switch (s.getKind()) {
                case PROV_ENTITY:
                    if (s.getClass() == org.provtools.provone.vanilla.Program.class) {
                        //TODO is type casting needed here?
                        program.put(((Program) s).getId(), (Program) s);
                    } else if (s.getClass() == org.provtools.provone.vanilla.Port.class) {
                        port.put(((Port) s).getId(), (Port) s);
                    } else if (s.getClass() == org.provtools.provone.vanilla.Channel.class) {
                        channel.put(((Channel) s).getId(), (Channel) s);
                    } else if (s.getClass() == org.provtools.provone.vanilla.Controller.class) {
                        controller.put(((Controller) s).getId(), (Controller) s);
                    } else if (s.getClass() == org.provtools.provone.vanilla.Workflow.class) {
                        workflow.put(((Workflow) s).getId(), (Workflow) s);
                    } else if (s.getClass() == org.provtools.provone.vanilla.Data.class) {
                        data.put(((Data) s).getId(), (Data) s);
                    } else if (s.getClass() == org.provtools.provone.vanilla.Visualization.class) {
                        visualization.put(((Visualization) s).getId(), (Visualization) s);
                    } else if (s.getClass() == org.provtools.provone.vanilla.Document.class) {
                        document.put(((org.provtools.provone.vanilla.Document) s).getId(), (org.provtools.provone.vanilla.Document) s);
                    }
                    else {
                        entity.put(((Entity) s).getId(), (Entity) s);
                    }
                    break;
                case PROV_ACTIVITY:
                    if (s.getClass() == org.provtools.provone.vanilla.Execution.class) {
                        //TODO is type casting needed here?
                        execution.put(((Execution) s).getId(), (Execution) s);
                    } else {
                        put(activity,s);
                    }
                    break;
                case PROV_AGENT:
                    if (s.getClass() == org.provtools.provone.vanilla.User.class) {
                        //TODO is type casting needed here?
                        user.put(((User) s).getId(), (User) s);
                    } else {
                        put(agent,s);
                    }
                    break;
                case PROV_USAGE:
                    put(used,s);
                    break;
                case PROV_GENERATION:
                    put(wgb,s);
                    break;
                case PROV_INVALIDATION:
                    put(wib,s);
                    break;
                case PROV_START:
                    put(wasStartedBy,s);
                    break;
                case PROV_END:
                    put(wasEndedBy,s);
                    break;
                case PROV_COMMUNICATION:
                    put(wasInformedBy,s);
                    break;
                case PROV_DERIVATION:
                    put(wasDerivedFrom,s);
                    break;
                case PROV_ASSOCIATION:
                    put(wasAssociatedWith,s);
                    break;
                case PROV_ATTRIBUTION:
                    put(wasAttributedTo,s);
                    break;
                case PROV_DELEGATION:
                    put(actedOnBehalfOf,s);
                    break;
                case PROV_INFLUENCE:
                    put(wasInfluencedBy,s);
                    break;
                case PROV_ALTERNATE:
                    if (s instanceof QualifiedRelation) {
                        put(qualifiedAlternateOf,s);
                    } else {
                        put(alternateOf,s);
                    }
                    break;
                case PROV_SPECIALIZATION:
                    if (s instanceof QualifiedRelation) {
                        put(qualifiedSpecializationOf,s);
                    } else {
                        put(specializationOf,s);
                    }
                case PROV_MENTION:
                    break;
                case PROV_MEMBERSHIP:
                    if (s instanceof QualifiedRelation) {
                        put(qualifiedHadMember,s);
                    } else {
                        put(hadMember,s);
                    }
                    break;
                case PROV_BUNDLE:
                    put(theBundles,(Bundle)s);
                    break;
                case PROV_DICTIONARY_INSERTION:
                    break;
                case PROV_DICTIONARY_REMOVAL:
                    break;
                case PROV_DICTIONARY_MEMBERSHIP:
                    break;
            }
        }

        if (count>0)
        namespace.register(SortedBundle.bnPrefix,bnNS);
    }

    public Map<QualifiedName, Bundle> getBundle() {
        return theBundles;
    }




    public Document toDocument(ProvFactory provFactory) {

        List<Statement> ss=new LinkedList<>();
        // ProvOne constructs
        ss.addAll(reassignId(getProgram()).values());
        ss.addAll(reassignId(getPort()).values());
        ss.addAll(reassignId(getChannel()).values());
        ss.addAll(reassignId(getController()).values());
        ss.addAll(reassignId(getWorkflow()).values());
        ss.addAll(getHasSubProgram().values());
        ss.addAll(getControlledBy().values());
        ss.addAll(getControls().values());
        ss.addAll(getHasInPort().values());
        ss.addAll(getHasOutPort().values());
        ss.addAll(getHasDefaultParam().values());
        ss.addAll(getConnectsTo().values());
        ss.addAll(getExecution().values());
        ss.addAll(getUser().values());
        ss.addAll(getWasPartOf().values());
        ss.addAll(getHadEntity().values());
        ss.addAll(getHadInPort().values());
        ss.addAll(getHadOutPort().values());
        ss.addAll(getData().values());
        ss.addAll(getVisualization().values());
        ss.addAll(getDocument().values());

        // PROV constructs
        ss.addAll(reassignId(getEntity()).values());
        ss.addAll(reassignId(getActivity()).values());
        ss.addAll(reassignId(getAgent()).values());
        ss.addAll(reassignId(getUsed()).values());
        ss.addAll(reassignId(getWasGeneratedBy()).values());
        ss.addAll(reassignId(getWasInvalidatedBy()).values());

        ss.addAll(reassignId(getWasAssociatedWith()).values());
        ss.addAll(reassignId(getWasAttributedTo()).values());
        ss.addAll(reassignId(getActedOnBehalfOf()).values());
        ss.addAll(reassignId(getWasStartedBy()).values());
        ss.addAll(reassignId(getWasEndedBy()).values());
        ss.addAll(reassignId(getWasInformedBy()).values());
        ss.addAll(reassignId(getWasInfluencedBy()).values());
        ss.addAll(getAlternateOf().values());
        ss.addAll(getSpecializationOf().values());
        ss.addAll(getHadMember().values());
        ss.addAll(reassignId(getWasDerivedFrom()).values());
        ss.addAll(reassignId(getQualifiedSpecializationOf()).values());
        ss.addAll(reassignId(getQualifiedAlternateOf()).values());
        ss.addAll(reassignId(getQualifiedHadMember()).values());

        String defaultJsonStyle=namespace.getPrefixes().get("default");
        if (defaultJsonStyle!=null) {
            namespace.unregister("default", defaultJsonStyle);
        }
        namespace.setDefaultNamespace(defaultJsonStyle);
        // return provFactory.newDocument(namespace,ss, reassignId(theBundles).values());
        // NO reassignement here, as it was done in the SortedBundle
        return provFactory.newDocument(namespace,ss,theBundles.values());
    }
}
