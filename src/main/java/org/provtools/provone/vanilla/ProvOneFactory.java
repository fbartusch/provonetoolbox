package org.provtools.provone.vanilla;

import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.GregorianCalendar;

import org.apache.commons.configuration2.INIConfiguration;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.logging.log4j.LogManager;

import org.openprovenance.prov.model.*;
import org.provtools.provone.model.HasDefaultParam;
import org.provtools.provone.model.HasOutPort;
import org.provtools.provone.model.HasSubProgram;
import org.provtools.provone.model.ProvOneModelConstructor;
import org.provtools.provone.model.HadInPort;
import org.provtools.provone.model.HadOutPort;
import org.provtools.provone.model.WasPartOf;
import org.provtools.provone.model.HadEntity;

//TODO A generic interface for attribute kinds. Then new schemas can be implemented as extension of the generic interface and used with ProvFactoriy's newAttribute method

/** A stateless factory for PROV and ProvONE objects. */

public class ProvOneFactory extends org.openprovenance.prov.vanilla.ProvFactory {

    static Logger logger = LogManager.getLogger(ProvOneFactory.class);

    private final static ProvOneFactory oFactory = new ProvOneFactory();
    final org.provtools.provone.model.ProvOneModelConstructor mc;
    final AtomConstructor ac;

    public ProvOneFactory(ObjectFactory of) {
        super(of);
        mc = new org.provtools.provone.vanilla.ProvOneModelConstructor();
        ac = (AtomConstructor) mc;
    }

    public ProvOneFactory () {
        super(null);
        mc = new org.provtools.provone.vanilla.ProvOneModelConstructor();
        ac = (AtomConstructor) mc;
    }

    public ProvOneFactory(ObjectFactory of, ProvOneModelConstructor mc) {
        super(of);
        this.mc = mc;
        ac = (AtomConstructor) mc;
    }

    public static ProvOneFactory getFactory() {
        return oFactory;
    }

    /*
    *
    *  ProvONE Aspect: Workflow
    *
    */

    public Program newProgram(QualifiedName id, Collection<Attribute> attributes) {
        return mc.newProgram(id, attributes);
    }

    public Program newProgram(QualifiedName id, String label) {
        Collection<Attribute> attrs = new LinkedList<>();
        attrs.add(newAttribute(Attribute.AttributeKind.PROV_LABEL, newInternationalizedString(label), getName().XSD_STRING));
        return mc.newProgram(id, attrs);
    }

    public Program newProgram(QualifiedName id, String label, String version, String suite, String downloadURL, String citation, String comment) {
        Collection<Attribute> attrs=new LinkedList<>();
        attrs.add(newAttribute(Attribute.AttributeKind.PROV_LABEL, newInternationalizedString(label), getName().XSD_STRING));
        if(version != null) {
            attrs.add(newAttribute("https://schema.org/", "softwareVersion", "schema", version,
                                   newQualifiedName("https://schema.org/", "Text", "schema")));
        }
        if(suite != null) {
            attrs.add(newAttribute("https://schema.org/", "applicationSuite", "schema", suite,
                                   newQualifiedName("https://schema.org/", "Text", "schema")));        
        }
        if(downloadURL != null) {
            attrs.add(newAttribute("https://schema.org/", "downloadUrl", "schema", downloadURL,
                                   newQualifiedName("https://schema.org/", "URL", "schema")));     
        }
        if(citation != null) {
            attrs.add(newAttribute("https://schema.org/", "citation", "schema", citation,
                                   newQualifiedName("https://schema.org/", "URL", "schema")));  
        }
        if(comment != null)  {
            attrs.add(newAttribute("https://schema.org/", "comment", "schema", comment,
                                   newQualifiedName("https://schema.org/", "Text", "schema")));                      
        }

        return mc.newProgram(id, attrs);
    }

    public Port newPort(QualifiedName id, String label) {
        Collection<Attribute> attrs = new LinkedList<>();
        attrs.add(newAttribute(Attribute.AttributeKind.PROV_LABEL, newInternationalizedString(label), getName().XSD_STRING));
        return mc.newPort(id, attrs);
    }

    //qn, label, List of schema:encodingFormat, description
    public Port newPort(QualifiedName id, String label, List<String> encodingFormats, String description) {
        Collection<Attribute> attrs=new LinkedList<>();
        attrs.add(newAttribute(Attribute.AttributeKind.PROV_LABEL, newInternationalizedString(label), getName().XSD_STRING));
        if(description != null) {
            attrs.add(newAttribute("https://schema.org/", "description", "schema", description,
                                   newQualifiedName("https://schema.org/", "Text", "schema")));
        }
        if(encodingFormats != null) {
            for (String format : encodingFormats) {
                attrs.add(newAttribute("https://schema.org/", "encodingFormat", "schema", format,
                                       newQualifiedName("https://schema.org/", "Text", "schema")));        
            }
        }

        return mc.newPort(id, attrs);
    }

    public Channel newChannel(QualifiedName id, String label) {
        Collection<Attribute> attrs = new LinkedList<>();
        attrs.add(newAttribute(Attribute.AttributeKind.PROV_LABEL, newInternationalizedString(label), getName().XSD_STRING));
        return mc.newChannel(id, attrs);
    }

    public Controller newController(QualifiedName id, Collection<Attribute> attributes) {
        return new org.provtools.provone.vanilla.Controller(id, attributes);
    }
    
    public Controller newController(QualifiedName id, String label) {
        Collection<Attribute> attrs = new LinkedList<>();
        attrs.add(newAttribute(Attribute.AttributeKind.PROV_LABEL, newInternationalizedString(label), getName().XSD_STRING));
        return new org.provtools.provone.vanilla.Controller(id, attrs);
    }

    public Controller newController(QualifiedName id, String label, String version, String downloadURL, String citation, String comment) {
        Collection<Attribute> attrs=new LinkedList<>();
        attrs.add(newAttribute(Attribute.AttributeKind.PROV_LABEL, newInternationalizedString(label), getName().XSD_STRING));
        if(version != null) {
            attrs.add(newAttribute("https://schema.org/", "softwareVersion", "schema", version,
                                   newQualifiedName("https://schema.org/", "Text", "schema")));
        }
        if(downloadURL != null) {
            attrs.add(newAttribute("https://schema.org/", "downloadUrl", "schema", downloadURL,
                                   newQualifiedName("https://schema.org/", "URL", "schema")));     
        }
        if(citation != null) {
            attrs.add(newAttribute("https://schema.org/", "citation", "schema", citation,
                                   newQualifiedName("https://schema.org/", "URL", "schema")));  
        }
        if(comment != null)  {
            attrs.add(newAttribute("https://schema.org/", "comment", "schema", comment,
                                   newQualifiedName("https://schema.org/", "Text", "schema")));                      
        }

        return mc.newController(id, attrs);
    }
    
    public Workflow newWorkflow(QualifiedName id, Collection<Attribute> attributes) {
        return mc.newWorkflow(id, attributes);
    }

    public Workflow newWorkflow(QualifiedName id, String label) {
        Collection<Attribute> attrs = new LinkedList<>();
        attrs.add(newAttribute(Attribute.AttributeKind.PROV_LABEL, newInternationalizedString(label), getName().XSD_STRING));
        return mc.newWorkflow(id, attrs);
    }

    // This method is for creating workflow representations in the Nextflow plugin.
    // TODO Create a more generic factory method for workflows. We cannot create a new factory method for every set of information we stumble upon ...
    public Workflow newWorkflow (String scriptID, String label, String location, String repository, String commitID, String revision) {
        Collection<Attribute> attrs=new LinkedList<>();
        attrs.add(newAttribute(Attribute.AttributeKind.PROV_LABEL, newInternationalizedString(label), getName().XSD_STRING));

        QualifiedName id = newQualifiedName("https://example.org/", scriptID, "exa");

        if(location != null) {
            attrs.add(newAttribute(Attribute.AttributeKind.PROV_LOCATION, newInternationalizedString(location), getName().PROV_LOCATION));
        }

        if(repository != null) {
            attrs.add(newAttribute("https://example.org/", "repository", "exa", repository,
                                   newQualifiedName("https://schema.org/", "Text", "schema")));
        }
        if(commitID != null) {
            attrs.add(newAttribute("https://example.org/", "commitID", "exa", commitID,
                                   newQualifiedName("https://schema.org/", "Text", "schema")));        
        }
        if(revision != null) {
            attrs.add(newAttribute("https://example.org/", "revision", "exa", revision,
                                   newQualifiedName("https://schema.org/", "URL", "schema")));                         
        }

        return mc.newWorkflow(id, attrs);
    }

    public Workflow newWorkflow(QualifiedName id, String label, String version, String suite, String downloadURL, String citation, String comment) {
        Collection<Attribute> attrs=new LinkedList<>();
        attrs.add(newAttribute(Attribute.AttributeKind.PROV_LABEL, newInternationalizedString(label), getName().XSD_STRING));
        if(version != null) {
            attrs.add(newAttribute("https://schema.org/", "softwareVersion", "schema", version,
                                   newQualifiedName("https://schema.org/", "Text", "schema")));
        }
        if(suite != null) {
            attrs.add(newAttribute("https://schema.org/", "applicationSuite", "schema", suite,
                                   newQualifiedName("https://schema.org/", "Text", "schema")));        
        }
        if(downloadURL != null) {
            attrs.add(newAttribute("https://schema.org/", "downloadUrl", "schema", downloadURL,
                                   newQualifiedName("https://schema.org/", "URL", "schema")));     
        }
        if(citation != null) {
            attrs.add(newAttribute("https://schema.org/", "citation", "schema", citation,
                                   newQualifiedName("https://schema.org/", "URL", "schema")));  
        }
        if(comment != null)  {
            attrs.add(newAttribute("https://schema.org/", "comment", "schema", comment,
                                   newQualifiedName("https://schema.org/", "Text", "schema")));                      
        }

        return mc.newWorkflow(id, attrs);
    }


    public HasSubProgram newHasSubProgram(QualifiedName id, QualifiedName parent, QualifiedName child, Collection<Attribute> attributes) {
        return mc.newHasSubProgram(id, parent, child, attributes);
    }

    public HasSubProgram newHasSubProgram(QualifiedName parent, QualifiedName child, String label) {
        Collection<Attribute> attrs = new LinkedList<>();
        attrs.add(newAttribute(Attribute.AttributeKind.PROV_LABEL, newInternationalizedString(label), getName().XSD_STRING));
        return mc.newHasSubProgram(null, parent, child, attrs);
    }

    public HasSubProgram newHasSubProgram(QualifiedName parent, QualifiedName child) {
        return mc.newHasSubProgram(null, parent, child, null);
    }

    public Controls newControls(QualifiedName controller, QualifiedName program) {
        return mc.newControls(controller, program);
    }

    public org.provtools.provone.model.ControlledBy newControlledBy(QualifiedName program, QualifiedName controller) {
        return mc.newControlledBy(program, controller);
    }

    public HasInPort newHasInPort(QualifiedName id, QualifiedName program, QualifiedName port, Collection<Attribute> attributes) {
        return mc.newHasInPort(id, program, port, attributes);
    }

    public HasInPort newHasInPort(QualifiedName program, QualifiedName port) {
        return mc.newHasInPort(null, program, port, null);
    }

    public HasOutPort newHasOutPort(QualifiedName id, QualifiedName program, QualifiedName port, Collection<Attribute> attributes) {
        return mc.newHasOutPort(id, program, port, attributes);
    }

    public HasOutPort newHasOutPort(QualifiedName program, QualifiedName port) {
        return mc.newHasOutPort(null, program, port, null);
    }

    public HasDefaultParam newHasDefaultParam(QualifiedName port, QualifiedName param) {
        return mc.newHasDefaultParam(port, param);
    }

    public org.provtools.provone.model.ConnectsTo newConnectsTo(QualifiedName port, QualifiedName channel) {
        return mc.newConnectsTo(port, channel);
    }


    // /*
    // *
    // *  ProvONE Aspect: Trace
    // *
    // */

    public Execution newExecution(QualifiedName id, OffsetDateTime startTime, OffsetDateTime endTime, String label) {
        Collection<Attribute> attrs = new LinkedList<>();
        attrs.add(newAttribute(Attribute.AttributeKind.PROV_LABEL, newInternationalizedString(label), getName().XSD_STRING));
        return newExecution(id, startTime, endTime, attrs);
    }

    public Execution newExecution(QualifiedName id, OffsetDateTime startTime, OffsetDateTime endTime, Collection<Attribute> attributes) {
        XMLGregorianCalendar xmlGregStartTime = null;
        XMLGregorianCalendar xmlGregEndTime = null;

        if (startTime != null) {
            // 1. Convert OffsetDateTime -> GregorianCalendar
            GregorianCalendar gregStartTime = GregorianCalendar.from(startTime.toZonedDateTime());
            // 2. Convert GregorianCalendar to XMLGregorianCalendar
            try {
                xmlGregStartTime = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregStartTime);
            } catch (DatatypeConfigurationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (endTime != null) {
            // 1. Convert OffsetDateTime -> GregorianCalendar
            GregorianCalendar gregEndTime = GregorianCalendar.from(endTime.toZonedDateTime());
            try {
                xmlGregEndTime = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregEndTime);
            } catch (DatatypeConfigurationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return mc.newExecution(id, xmlGregStartTime, xmlGregEndTime, attributes);
    }

    public Execution newExecution(QualifiedName id, XMLGregorianCalendar startTime, XMLGregorianCalendar endTime,
            Collection<Attribute> attributes) {
        return mc.newExecution(id, startTime, endTime, attributes);
    }

    public Execution newExecution(QualifiedName id, XMLGregorianCalendar startTime, XMLGregorianCalendar endTime, String label) {
        Collection<Attribute> attrs = new LinkedList<>();
        attrs.add(newAttribute(Attribute.AttributeKind.PROV_LABEL, newInternationalizedString(label), getName().XSD_STRING));
        return mc.newExecution(id, startTime, endTime, attrs);
    }

    public User newUser(QualifiedName id, Collection<Attribute> attributes) {
        return mc.newUser(id, attributes);
    }

    public User newUser(QualifiedName id, String label) {
        Collection<Attribute> attrs = new LinkedList<>();
        attrs.add(newAttribute(Attribute.AttributeKind.PROV_LABEL, newInternationalizedString(label), getName().XSD_STRING));
        return mc.newUser(id,  attrs);
    }

    //TODO This is quick&dirty. We have to check if fields in ini file are not present
    //TODO Catch Exceptions
    public User newUser(Path iniFile, String namespace, String prefix) throws FileNotFoundException, IOException, ConfigurationException {
        Collection<Attribute> attrs = new LinkedList<>();
        User user = null;

        File f = iniFile.toFile();
        if(f.exists() && !f.isDirectory()) { 
            INIConfiguration iniConfiguration = new INIConfiguration();

            try (FileReader fileReader = new FileReader(f)) {
                iniConfiguration.read(fileReader);

                // Get information from INI file
                String title = iniConfiguration.getSection(null).getProperty("title").toString();
                String givenName = iniConfiguration.getSection(null).getProperty("givenName").toString();
                String familyName = iniConfiguration.getSection(null).getProperty("familyName").toString();
                String mbox = iniConfiguration.getSection(null).getProperty("mbox").toString();
                String homepage = iniConfiguration.getSection(null).getProperty("homepage").toString();
                String workplaceHomepage = iniConfiguration.getSection(null).getProperty("workplaceHomepage").toString();
                String orcid = iniConfiguration.getSection(null).getProperty("orcid").toString();

                String label = givenName + " " + familyName;
                attrs.add(newAttribute(Attribute.AttributeKind.PROV_LABEL, newInternationalizedString(label), getName().XSD_STRING));
                if(title != null) {
                    attrs.add(newAttribute("http://xmlns.com/foaf/0.1/", "title", "foaf", title,
                                           getName().XSD_STRING));
                }
                if(givenName != null) {
                    attrs.add(newAttribute("http://xmlns.com/foaf/0.1/", "givenName", "foaf", givenName,
                                           getName().XSD_STRING));        
                }
                if(familyName != null) {
                    attrs.add(newAttribute("http://xmlns.com/foaf/0.1/", "familyName", "foaf", familyName,
                                           getName().XSD_STRING));     
                }
                if(mbox != null) {
                    attrs.add(newAttribute("http://xmlns.com/foaf/0.1/", "mbox", "foaf", mbox,
                                           getName().XSD_STRING));  
                }
                if(homepage != null)  {
                    attrs.add(newAttribute("http://xmlns.com/foaf/0.1/", "homepage", "foaf", homepage,
                                           getName().XSD_STRING));                      
                }
                if(workplaceHomepage != null)  {
                    attrs.add(newAttribute("http://xmlns.com/foaf/0.1/", "workplaceHomepage", "foaf", workplaceHomepage,
                                           getName().XSD_STRING));                      
                }
                if(orcid != null)  {
                    attrs.add(newAttribute("http://purl.org/spar/scoro/", "hasORCID", "scoro", orcid,
                                           getName().XSD_STRING));                      
                }

                QualifiedName id = newQualifiedName(namespace, orcid, prefix);
        
                user = mc.newUser(id,  attrs);
            }
        }

        return user;
    }

    public User newUser(QualifiedName id, String label, String title, String givenName, String familyName, String mbox,
                        String homepage, String workplaceHomepage, String orcid) {
        Collection<Attribute> attrs=new LinkedList<>();                            
        attrs.add(newAttribute(Attribute.AttributeKind.PROV_LABEL, newInternationalizedString(label), getName().XSD_STRING));
        if(title != null) {
            attrs.add(newAttribute("http://xmlns.com/foaf/0.1/", "title", "foaf", title,
                                   getName().XSD_STRING));
        }
        if(givenName != null) {
            attrs.add(newAttribute("http://xmlns.com/foaf/0.1/", "givenName", "foaf", givenName,
                                   getName().XSD_STRING));        
        }
        if(familyName != null) {
            attrs.add(newAttribute("http://xmlns.com/foaf/0.1/", "familyName", "foaf", familyName,
                                   getName().XSD_STRING));     
        }
        if(mbox != null) {
            attrs.add(newAttribute("http://xmlns.com/foaf/0.1/", "mbox", "foaf", mbox,
                                   getName().XSD_STRING));  
        }
        if(homepage != null)  {
            attrs.add(newAttribute("http://xmlns.com/foaf/0.1/", "homepage", "foaf", homepage,
                                   getName().XSD_STRING));                      
        }
        if(workplaceHomepage != null)  {
            attrs.add(newAttribute("http://xmlns.com/foaf/0.1/", "workplaceHomepage", "foaf", workplaceHomepage,
                                   getName().XSD_STRING));                      
        }
        if(orcid != null)  {
            attrs.add(newAttribute("http://purl.org/spar/scoro/", "hasORCID", "scoro", orcid,
                                   getName().XSD_STRING));                      
        }

        return mc.newUser(id, attrs);
    }

    public WasPartOf newWasPartOf(QualifiedName child, QualifiedName parent, Collection<Attribute> attributes) {
        return mc.newWasPartOf(child, parent, attributes);
    }

    public WasPartOf newWasPartOf(QualifiedName child, QualifiedName parent) {
        return mc.newWasPartOf(child, parent, null);
    }

    public HadInPort newHadInPort(QualifiedName usage, QualifiedName port) {
        return mc.newHadInPort(usage, port);
    }

    public HadEntity newHadEntity(QualifiedName usage, QualifiedName entity) {
        return mc.newHadEntity(usage, entity);
    }

    public HadOutPort newHadOutPort(QualifiedName generation, QualifiedName port) {
        return mc.newHadOutPort(generation, port);
    }

    // /*
    // *
    // *  ProvONE Aspect: Data Structure
    // *
    // */

    public Data newData(QualifiedName id, Collection<Attribute> attributes) {
        return mc.newData(id, attributes);
    }

    public Data newData(QualifiedName id, String label) {
        Collection<Attribute> attrs = new LinkedList<>();
        attrs.add(newAttribute(Attribute.AttributeKind.PROV_LABEL, newInternationalizedString(label), getName().XSD_STRING));
        return mc.newData(id,  attrs);
    }

    //TODO This is quick&dirty. Have a look at Data Catalog Vocabulary: https://www.w3.org/TR/vocab-dcat/#introduction
    public Data newData(QualifiedName id, String label, String location, String sha256, String format, String archivedAt, String description) {
        Collection<Attribute> attrs = new LinkedList<>();
        attrs.add(newAttribute(Attribute.AttributeKind.PROV_LABEL, newInternationalizedString(label), getName().XSD_STRING));
        if(location != null) {
            attrs.add(newAttribute("https://schema.org/", "location", "schema", location,
                                   getName().XSD_STRING));
        }
        if(sha256 != null) {
            attrs.add(newAttribute("https://schema.org/", "sha256", "schema", sha256,
                                   getName().XSD_STRING));        
        }
        if(format != null) {
            attrs.add(newAttribute("https://schema.org/", "format", "schema", format,
                                   getName().XSD_STRING));     
        }
        if(archivedAt != null) {
            attrs.add(newAttribute("https://schema.org/", "archivedAt", "schema", archivedAt,
                                   getName().XSD_STRING));  
        }
        if(description != null) {
            attrs.add(newAttribute("https://schema.org/", "description", "schema", description,
                                   getName().XSD_STRING));  
        }

        return mc.newData(id, attrs);
    }




    public Visualization newVisualization(QualifiedName id, Collection<Attribute> attributes) {
        return mc.newVisualization(id, attributes);
    }

    public Visualization newVisualization(QualifiedName id, String label) {
        Collection<Attribute> attrs = new LinkedList<>();
        attrs.add(newAttribute(Attribute.AttributeKind.PROV_LABEL, newInternationalizedString(label), getName().XSD_STRING));
        return mc.newVisualization(id,  attrs);
    }

    //TODO This is quick&dirty. Have a look at Data Catalog Vocabulary: https://www.w3.org/TR/vocab-dcat/#introduction
    public Visualization newVisualization(QualifiedName id, String label, String location, String sha256, String format, String archivedAt, String description) {
        Collection<Attribute> attrs = new LinkedList<>();
        attrs.add(newAttribute(Attribute.AttributeKind.PROV_LABEL, newInternationalizedString(label), getName().XSD_STRING));
        if(location != null) {
            attrs.add(newAttribute("https://schema.org/", "location", "schema", location,
                                   getName().XSD_STRING));
        }
        if(sha256 != null) {
            attrs.add(newAttribute("https://schema.org/", "sha256", "schema", sha256,
                                   getName().XSD_STRING));        
        }
        if(format != null) {
            attrs.add(newAttribute("https://schema.org/", "format", "schema", format,
                                   getName().XSD_STRING));     
        }
        if(archivedAt != null) {
            attrs.add(newAttribute("https://schema.org/", "archivedAt", "schema", archivedAt,
                                   getName().XSD_STRING));  
        }
        if(description != null) {
            attrs.add(newAttribute("https://schema.org/", "description", "schema", description,
                                   getName().XSD_STRING));  
        }

        return mc.newVisualization(id, attrs);
    }

    public Document newDocument(QualifiedName id, Collection<Attribute> attributes) {
        return mc.newDocument(id, attributes);
    }

    public Document newDocument(QualifiedName id, String label) {
        Collection<Attribute> attrs = new LinkedList<>();
        attrs.add(newAttribute(Attribute.AttributeKind.PROV_LABEL, newInternationalizedString(label), getName().XSD_STRING));
        return mc.newDocument(id, attrs);
    }
}