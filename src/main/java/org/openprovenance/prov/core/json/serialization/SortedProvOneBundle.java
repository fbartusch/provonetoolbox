package org.openprovenance.prov.core.json.serialization;

import java.util.HashMap;
import java.util.Map;

import org.openprovenance.prov.model.Bundle;
import org.openprovenance.prov.model.Namespace;
import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.model.QualifiedRelation;
import org.openprovenance.prov.model.Statement;
import org.provtools.provone.model.ConnectsTo;
import org.provtools.provone.model.ControlledBy;
import org.provtools.provone.model.HadEntity;
import org.provtools.provone.model.HasInPort;
import org.provtools.provone.model.HasOutPort;
import org.provtools.provone.model.HasSubProgram;
import org.provtools.provone.model.WasPartOf;
import org.provtools.provone.vanilla.Channel;
import org.provtools.provone.vanilla.Controller;
import org.provtools.provone.vanilla.Controls;
import org.provtools.provone.vanilla.Data;
import org.provtools.provone.vanilla.Document;
import org.provtools.provone.vanilla.Execution;
import org.provtools.provone.vanilla.HasDefaultParam;
import org.provtools.provone.vanilla.Port;
import org.provtools.provone.vanilla.Program;
import org.provtools.provone.vanilla.User;
import org.provtools.provone.vanilla.Visualization;
import org.provtools.provone.vanilla.Workflow;

/*
 * Provides methods for ProvOne elements.
 */

public class SortedProvOneBundle extends SortedBundle {

    Map<QualifiedName, Program> program = new HashMap<>();
    Map<QualifiedName, Port> port = new HashMap<>();
    Map<QualifiedName, Channel> channel = new HashMap<>();
    Map<QualifiedName, Controller> controller = new HashMap<>();
    Map<QualifiedName, Workflow> workflow = new HashMap<>();
    Map<QualifiedName, HasSubProgram> hasSubProgram = new HashMap<>();
    Map<QualifiedName, ControlledBy> controlledBy = new HashMap<>();
    Map<QualifiedName, Controls> controls = new HashMap<>();
    Map<QualifiedName, HasInPort> hasInPort = new HashMap<>();
    Map<QualifiedName, HasOutPort> hasOutPort = new HashMap<>();
    Map<QualifiedName, HasDefaultParam> hasDefaultParam = new HashMap<>();
    Map<QualifiedName, ConnectsTo> connectsTo = new HashMap<>();

    Map<QualifiedName, Execution> execution = new HashMap<>();
    Map<QualifiedName, User> user = new HashMap<>();
    Map<QualifiedName, WasPartOf> wasPartOf = new HashMap<>();
    Map<QualifiedName, HadEntity> hadEntity = new HashMap<>();

    Map<QualifiedName, Data> data = new HashMap<>();
    Map<QualifiedName, Visualization> visualization = new HashMap<>();
    Map<QualifiedName, Document> document = new HashMap<>();

    private QualifiedName id;
    //final boolean deferredId;

    public SortedProvOneBundle() {
        super();
    }

    public SortedProvOneBundle(Bundle bun)  {
        this.namespace=new Namespace(bun.getNamespace());
        // prov-json assumes default namespace to be listed with the "reserved" prefix "default"
        if (namespace.getDefaultNamespace()!=null) this.namespace.register("default", namespace.getDefaultNamespace());
        this.id = bun.getId();
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

    public Map<QualifiedName, Channel> getChannel() {
        return channel;
    }

    public Map<QualifiedName, Controller> getController() {
        return controller;
    }

    public Map<QualifiedName, Workflow> getWorkflow() {
        return workflow;
    }

    public Map<QualifiedName, HasSubProgram> getHasSubProgram() {
        return hasSubProgram;
    }

    public Map<QualifiedName, ControlledBy> getControlledBy() {
        return controlledBy;
    }

    public Map<QualifiedName, Controls> getControls() {
        return controls;
    }
    
    public Map<QualifiedName, HasInPort> getHasInPort() {
        return hasInPort;
    }

    public Map<QualifiedName, HasOutPort> getHasOutPort() {
        return hasOutPort;
    }

    public Map<QualifiedName, HasDefaultParam> getHasDefaultParam() {
        return hasDefaultParam;
    }

    public Map<QualifiedName, ConnectsTo> getConnectsTo() {
        return connectsTo;
    }

    public Map<QualifiedName, Execution> getExecution() {
        return execution;
    }

    public Map<QualifiedName, User> getUser() {
        return user;
    }

    public Map<QualifiedName, WasPartOf> getWasPartOf() {
        return wasPartOf;
    }

    public Map<QualifiedName, HadEntity> getHadEntity() {
        return hadEntity;
    }

    public Map<QualifiedName, Data> getData() {
        return data;
    }

    public Map<QualifiedName, Visualization> getVisualization() {
        return visualization;
    }

    public Map<QualifiedName, Document> getDocument() {
        return document;
    }
}
