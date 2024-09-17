package org.openprovenance.prov.interop;

import org.openprovenance.prov.model.Attribute;
import org.openprovenance.prov.model.Document;
import org.openprovenance.prov.model.Namespace;
import org.openprovenance.prov.model.ProvFactory;
import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.model.QualifiedRelation;
import org.openprovenance.prov.model.StatementOrBundle;
import org.openprovenance.prov.vanilla.Activity;
import org.openprovenance.prov.vanilla.Agent;
import org.openprovenance.prov.vanilla.Entity;
import org.openprovenance.prov.vanilla.HadMember;
import org.openprovenance.prov.vanilla.LangString;
import org.openprovenance.prov.vanilla.Used;
import org.openprovenance.prov.vanilla.WasAssociatedWith;
import org.openprovenance.prov.vanilla.WasDerivedFrom;
import org.openprovenance.prov.vanilla.WasGeneratedBy;
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
import org.provtools.provone.vanilla.User;
import org.provtools.provone.vanilla.Visualization;
import org.provtools.provone.vanilla.WasPartOf;
import org.provtools.provone.vanilla.Workflow;
import java.util.Map;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.impl.PropertyImpl;
import org.apache.jena.rdf.model.impl.ResourceImpl;
import org.apache.jena.vocabulary.RDF;

/*
 * An interop framework for Apache Jena
 * 
 * Creates Apache Jena models from ProvONE and PROV documents.
 * These models can be added to triplestores like Apache Fuseki.
 */

public class ApacheJenaInterop {

    ProvFactory pFactory;
    Namespace ns;

    public ApacheJenaInterop(ProvFactory pFactory) {
        this.pFactory = pFactory;
    }

    public Model createJenaModel(Document doc) {
        Model m = ModelFactory.createDefaultModel();

        // Add namespaces to the model
        this.ns = doc.getNamespace();
        Map<String, String> prefixes = ns.getPrefixes();
        m.setNsPrefixes(prefixes);

        pFactory.newQualifiedName(null, null, null);

        // Iterate over the statements and add them to the RDF model.
        for (StatementOrBundle s: doc.getStatementOrBundle()) {
            if (s.getKind() == null) {
                if (s.getClass() == org.provtools.provone.vanilla.HasInPort.class) {
                    HasInPort statement = (HasInPort) s;
                    Resource program = m.createResource(ns.qualifiedNameToString(statement.getProgram()), new ResourceImpl("provone:Program"));
                    Resource port = m.createResource(ns.qualifiedNameToString(statement.getPort()), new ResourceImpl("provone:Port"));
                    Property hasInPortProperty = new PropertyImpl("provone:hasInPort");
                    m.add(program, hasInPortProperty, port);
                }
                else if (s.getClass() == org.provtools.provone.vanilla.HasOutPort.class) {
                    HasOutPort statement = (HasOutPort) s;
                    Resource program =  m.createResource(ns.qualifiedNameToString(statement.getProgram()), new ResourceImpl("provone:Program"));
                    Resource port = m.createResource(ns.qualifiedNameToString(statement.getPort()), new ResourceImpl("provone:Port"));
                    Property hasOutPortProperty = new PropertyImpl("provone:hasOutPort");
                    m.add(program, hasOutPortProperty, port);
                }
                else if (s.getClass() == org.provtools.provone.vanilla.HasSubProgram.class) {
                    HasSubProgram statement = (HasSubProgram) s;
                    Resource parent = m.createResource(ns.qualifiedNameToString(statement.getParent()), new ResourceImpl("provone:Program"));
                    Resource child = m.createResource(ns.qualifiedNameToString(statement.getChild()), new ResourceImpl("provone:Program"));
                    Property hasSubProgramProperty = new PropertyImpl("provone:hasSubProgram");
                    m.add(parent, hasSubProgramProperty, child);
                }
                else if (s.getClass() == org.provtools.provone.vanilla.ControlledBy.class) {
                    ControlledBy statement = (ControlledBy) s;
                    Resource program = m.createResource(ns.qualifiedNameToString(statement.getProgram()), new ResourceImpl("provone:Program"));
                    Resource controller = m.createResource(ns.qualifiedNameToString(statement.getController()), new ResourceImpl("provone:Controller"));
                    Property controlledByProperty = new PropertyImpl("provone:controlledBy");
                    m.add(program, controlledByProperty, controller);
                }
                else if (s.getClass() == org.provtools.provone.vanilla.Controls.class) {
                    Controls statement = (Controls) s;
                    Resource controller = m.createResource(ns.qualifiedNameToString(statement.getController()), new ResourceImpl("provone:Controller"));
                    Resource program = m.createResource(ns.qualifiedNameToString(statement.getProgram()), new ResourceImpl("provone:Program"));
                    Property controlsProperty = new PropertyImpl("provone:controls");
                    m.add(controller, controlsProperty, program);
                }
                else if (s.getClass() == org.provtools.provone.vanilla.HasDefaultParam.class) {
                    HasDefaultParam statement = (HasDefaultParam) s;
                    Resource port = m.createResource(ns.qualifiedNameToString(statement.getPort()), new ResourceImpl("provone:Port"));
                    Resource defaultParam = m.createResource(ns.qualifiedNameToString(statement.getDefaultParam()), new ResourceImpl("prov:Entity"));
                    Property hasDefaultParamProperty = new PropertyImpl("provone:hasDefaultParam");
                    m.add(port, hasDefaultParamProperty, defaultParam);
                }
                else if (s.getClass() == org.provtools.provone.vanilla.ConnectsTo.class) {
                    ConnectsTo statement = (ConnectsTo) s;
                    Resource port = m.createResource(ns.qualifiedNameToString(statement.getPort()), new ResourceImpl("provone:Port"));
                    Resource channel = m.createResource(ns.qualifiedNameToString(statement.getChannel()), new ResourceImpl("provone:Channel"));
                    Property connectsToProperty = new PropertyImpl("provone:connectsTo");
                    m.add(port, connectsToProperty, channel);
                }
                else if (s.getClass() == org.provtools.provone.vanilla.WasPartOf.class) {
                    WasPartOf statement = (WasPartOf) s;
                    Resource childExec = m.createResource(ns.qualifiedNameToString(statement.getChild()), new ResourceImpl("provone:Execution"));
                    Resource parentExec = m.createResource(ns.qualifiedNameToString(statement.getParent()), new ResourceImpl("provone:Execution"));
                    Property wasPartOfProperty = new PropertyImpl("provone:wasPartOf");
                    m.add(childExec, wasPartOfProperty, parentExec);
                }
                else if (s.getClass() == org.provtools.provone.vanilla.HadEntity.class) {
                    HadEntity statement = (HadEntity) s;
                    Resource activity = m.createResource(ns.qualifiedNameToString(statement.getActivity()), new ResourceImpl("prov:Activity"));
                    Resource entity = m.createResource(ns.qualifiedNameToString(statement.getEntity()), new ResourceImpl("prov:Entity"));
                    Property hadEntityProperty = new PropertyImpl("provone:hadEntity");
                    m.add(activity, hadEntityProperty, entity);
                }
                else if (s.getClass() == org.provtools.provone.vanilla.HadInPort.class) {
                    HadInPort statement = (HadInPort) s;
                    Resource usage = m.createResource(ns.qualifiedNameToString(statement.getUsage()), new ResourceImpl("prov:Usage"));
                    Resource port = m.createResource(ns.qualifiedNameToString(statement.getPort()), new ResourceImpl("provone:Port"));
                    Property hadInPortProperty = new PropertyImpl("provone:hadInPort");
                    m.add(usage, hadInPortProperty, port);
                }
                else if (s.getClass() == org.provtools.provone.vanilla.HadOutPort.class) {
                    HadOutPort statement = (HadOutPort) s;
                    Resource generation = m.createResource(ns.qualifiedNameToString(statement.getGeneration()), new ResourceImpl("prov:Generation"));
                    Resource port = m.createResource(ns.qualifiedNameToString(statement.getPort()), new ResourceImpl("provone:Port"));
                    Property hadOutPortProperty = new PropertyImpl("provone:hadOutPort");
                    m.add(generation, hadOutPortProperty, port);
                }
                continue;
            }

            switch (s.getKind()) {
                case PROV_ENTITY:
                    Entity entity = (Entity) s;
                    Resource entityResource = m.createResource(ns.qualifiedNameToString(entity.getId()));

                    if (s.getClass() == Entity.class)  {
                        m.add(entityResource, RDF.type, new ResourceImpl("prov:Entity"));
                    } else if (s.getClass() == Channel.class) {
                        m.add(entityResource, RDF.type, new ResourceImpl("provone:Channel"));
                    } else if (s.getClass() == Controller.class) {
                        m.add(entityResource, RDF.type, new ResourceImpl("provone:Controller"));
                    } else if (s.getClass() == Data.class) {
                        m.add(entityResource, RDF.type, new ResourceImpl("provone:Data"));
                    } else if (s.getClass() == Channel.class) {
                        m.add(entityResource, RDF.type, new ResourceImpl("provone:Channel"));
                    } else if (s.getClass() == org.provtools.provone.vanilla.Document.class) {
                        m.add(entityResource, RDF.type, new ResourceImpl("provone:Document"));
                    } else if (s.getClass() == Port.class) {
                        m.add(entityResource, RDF.type, new ResourceImpl("provone:Port"));
                    } else if (s.getClass() == Program.class) {
                        m.add(entityResource, RDF.type, new ResourceImpl("provone:Program"));
                    } else if (s.getClass() == Visualization.class) {
                        m.add(entityResource, RDF.type, new ResourceImpl("provone:Visualization"));
                    } else if (s.getClass() == Workflow.class) {
                        m.add(entityResource, RDF.type, new ResourceImpl("provone:Workflow"));
                    }
                    addAttributes(m, entity, entityResource);
                    break;
                case PROV_ACTIVITY:
                    Activity activity = (Activity) s;
                    Resource activityResource = m.createResource(ns.qualifiedNameToString(activity.getId()));

                    if (s.getClass() == Activity.class)  {
                        m.add(activityResource, RDF.type, new ResourceImpl("prov:Activity"));
                    } else if (s.getClass() == Execution.class) {
                        m.add(activityResource, RDF.type, new ResourceImpl("provone:Execution"));
                        Property startTimeProperty = new PropertyImpl("prov:startedAtTime");
                        Property endTimeProperty = new PropertyImpl("prov:endedAtTime");
                        //Property startTimeProperty = new PropertyImpl("prov", "startedAtTime");
                        //Property endTimeProperty = new PropertyImpl("prov", "endedAtTime");
                        Literal startTime = m.createTypedLiteral(activity.getStartTime().toString(), XSDDatatype.XSDdateTime);
                        Literal endTime = m.createTypedLiteral(activity.getEndTime().toString(), XSDDatatype.XSDdateTime);
                        m.add(activityResource, startTimeProperty, startTime);
                        m.add(activityResource, endTimeProperty, endTime);
                    }
                    addAttributes(m, activity, activityResource);
                    break;
                case PROV_AGENT:
                    Agent agent = (Agent) s;
                    Resource agentResource = m.createResource(ns.qualifiedNameToString(agent.getId()));

                    if (s.getClass() == Agent.class)  {
                        m.add(agentResource, RDF.type, new ResourceImpl("prov:Agent"));
                    } else if (s.getClass() == User.class) {
                        m.add(agentResource, RDF.type, new ResourceImpl("provone:User"));
                    }
                    addAttributes(m, agent, agentResource);
                    break;
                case PROV_USAGE:
                    //TODO Qualified Usage? See PROV_ALTERNATE case
                    Used usage = (Used) s;
                    activityResource = m.createResource(ns.qualifiedNameToString(usage.getActivity()));
                    entityResource = m.createResource(ns.qualifiedNameToString(usage.getEntity()));
                    Property usedProperty = new PropertyImpl("prov:used");
                    m.add(activityResource, usedProperty, entityResource);
                    break;
                case PROV_GENERATION:
                    //TODO Qualified Generation? See PROV_ALTERNATE case
                    WasGeneratedBy generatedBy = (WasGeneratedBy) s;
                    activityResource = m.createResource(ns.qualifiedNameToString(generatedBy.getActivity()));
                    entityResource = m.createResource(ns.qualifiedNameToString(generatedBy.getEntity()));
                    Property generatedByProperty = new PropertyImpl("prov:wasGeneratedBy");
                    m.add(entityResource, generatedByProperty, activityResource);
                    break;
                case PROV_INVALIDATION:
                    //TODO Implement case
                    break;
                case PROV_START:
                    //TODO Implement case
                    break;
                case PROV_END:
                    //TODO Implement case
                    break;
                case PROV_COMMUNICATION:
                    //TODO Implement case
                    break;
                case PROV_DERIVATION:
                    //TODO Qualified Derivation?
                    WasDerivedFrom wasDerivedFrom = (WasDerivedFrom) s;
                    Resource usedEntityResource = m.createResource(ns.qualifiedNameToString(wasDerivedFrom.getUsedEntity()));
                    Resource generatedEntityResource = m.createResource(ns.qualifiedNameToString(wasDerivedFrom.getGeneratedEntity()));
                    Property wasDerivedFromProperty = new PropertyImpl("prov:wasDerivedFrom");
                    m.add(generatedEntityResource, wasDerivedFromProperty, usedEntityResource);
                    break;
                case PROV_ASSOCIATION:
                    //TODO Qualified Association?
                    WasAssociatedWith wasAssociatedWith = (WasAssociatedWith) s;
                    activityResource = m.createResource(ns.qualifiedNameToString(wasAssociatedWith.getActivity()));
                    agentResource = m.createResource(ns.qualifiedNameToString(wasAssociatedWith.getAgent()));
                    Property wasAssociatedWithProperty = new PropertyImpl("prov:wasAssociatedWith");
                    m.add(agentResource, wasAssociatedWithProperty, activityResource);
                    break;
                case PROV_ATTRIBUTION:
                    //TODO Implement case
                case PROV_DELEGATION:
                    //TODO Implement case
                    break;
                case PROV_INFLUENCE:
                    //TODO Implement case
                    break;
                case PROV_ALTERNATE:
                    //TODO Implement case
                    if (s instanceof QualifiedRelation) {
                    } else {
                    }
                    break;
                case PROV_SPECIALIZATION:
                    //TODO Implement case
                    if (s instanceof QualifiedRelation) {
                    } else {
                    }
                case PROV_MENTION:
                    //TODO Implement case
                    break;
                case PROV_MEMBERSHIP:
                    if (s instanceof QualifiedRelation) {
                        //put(qualifiedHadMember,s);
                    } else {
                        HadMember collection = (HadMember) s;
                        Resource collectionResource = m.createResource(ns.qualifiedNameToString(collection.getCollection()));
                        for (QualifiedName qn : collection.getEntity()) {
                            entityResource = m.createResource(ns.qualifiedNameToString(qn));
                            Property hadMemberProperty = new PropertyImpl("prov:hadMember");
                            m.add(collectionResource, hadMemberProperty, entityResource);
                        }
                    }
                    break;
                case PROV_BUNDLE:
                    //TODO Implement case
                    break;
                case PROV_DICTIONARY_INSERTION:
                    break;
                case PROV_DICTIONARY_REMOVAL:
                    break;
                case PROV_DICTIONARY_MEMBERSHIP:
                    break;
            }
        }

        return m;
    }

    /**
     * Add the attributes of an Prov Entity to the Jena RDF model. 
     * @param e
     * @param r
     */
    private void addAttributes(Model model, Entity entity, Resource resource) {
        for (Attribute a : entity.getAttributes()) {
            Property property = new PropertyImpl(ns.qualifiedNameToString(a.getElementName()));
            Literal literal = attributeToLiteral(a);
            model.add(resource, property, literal);
        };
    }

    /**
     * Add the attributes of an Prov Activity to the Jena RDF model. 
     * @param e
     * @param r
     */
    private void addAttributes(Model model, Activity activity, Resource resource) {
        for (Attribute a : activity.getAttributes()) {
            Property property = new PropertyImpl(ns.qualifiedNameToString(a.getElementName()));
            Literal literal = attributeToLiteral(a);
            model.add(resource, property, literal);
        };
    }

    /**
     * Add the attributes of an Prov Agent to the Jena RDF model. 
     * @param e
     * @param r
     */
    private void addAttributes(Model model, Agent agent, Resource resource) {
        for (Attribute a : agent.getAttributes()) {
            Property property = new PropertyImpl(ns.qualifiedNameToString(a.getElementName()));
            Literal literal = attributeToLiteral(a);
            model.add(resource, property, literal);
        };
    }

    private Literal attributeToLiteral(Attribute a) {
        Literal literal = null;

        Model m = ModelFactory.createDefaultModel();
        if (a.getValue().getClass() == org.openprovenance.prov.vanilla.LangString.class) {
            LangString langString = (LangString) a.getValue();
            literal = m.createTypedLiteral(langString.getValue(), ns.qualifiedNameToString(a.getType()));
        } else {
            literal = m.createTypedLiteral(a.getValue(), ns.qualifiedNameToString(a.getType()));
        }

        return literal;
    }
}
