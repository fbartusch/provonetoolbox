package org.openprovenance.prov.core.json.serialization;

import java.util.HashMap;
import java.util.Map;

import org.openprovenance.prov.model.Bundle;
import org.openprovenance.prov.model.Namespace;
import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.model.QualifiedRelation;
import org.openprovenance.prov.model.Statement;
import org.provtools.provone.model.HasInPort;
import org.provtools.provone.vanilla.Port;
import org.provtools.provone.vanilla.Program;

/*
 * Provides methods for ProvOne elements.
 */

public class SortedProvOneBundle extends SortedBundle {

    Map<QualifiedName, Program> program = new HashMap<>();
    Map<QualifiedName, Port> port = new HashMap<>();
    Map<QualifiedName, HasInPort> hasInPort = new HashMap<>();

    private QualifiedName id;
    //final boolean deferredId;

    public SortedProvOneBundle() {
        super();
    }

    public SortedProvOneBundle(Bundle bun)  {
        this.namespace=new Namespace(bun.getNamespace());
        // prov-json assumes default namespace to be listed with the "reserved" prefix "default"
        if (namespace.getDefaultNamespace()!=null) this.namespace.register("default", namespace.getDefaultNamespace());
        this.id=bun.getId();
        //this.deferredId=false;
        for (Statement s: bun.getStatement()) {
            switch (s.getKind()) {
                case PROV_ENTITY:
                    put(entity,s);
                    break;
                case PROV_ACTIVITY:
                    put(activity,s);
                    break;
                case PROV_AGENT:
                    put(agent,s);
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
                    break;
                case PROV_DICTIONARY_INSERTION:
                    break;
                case PROV_DICTIONARY_REMOVAL:
                    break;
                case PROV_DICTIONARY_MEMBERSHIP:
                    break;
            }
        }
    }


    public Map<QualifiedName, Program> getProgram() {
        return program;
    }

    public Map<QualifiedName, Port> getPort() {
        return port;
    }
    
    public Map<QualifiedName, HasInPort> getHasInPort() {
        return hasInPort;
    }
}
