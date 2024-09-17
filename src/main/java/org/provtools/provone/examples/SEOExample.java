package org.provtools.provone.examples;

import org.provtools.provone.model.ProvOneNamespace;
import org.provtools.provone.vanilla.Execution;
import org.provtools.provone.vanilla.Program;
import org.provtools.provone.vanilla.ProvOneFactory;
import org.provtools.provone.vanilla.User;
import java.util.Collection;
import java.util.LinkedList;
import org.openprovenance.prov.interop.GenericInteropFramework;
import org.openprovenance.prov.interop.InteropFramework;
import org.openprovenance.prov.model.Attribute;
import org.openprovenance.prov.model.Document;
import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.model.WasAssociatedWith;

/**
 * This minimal example uses the Software Environment Ontology (SEO) for describing software environments in which
 * workflow steps are executed.
 */
public class SEOExample {

    /*
     * This is totally Work in progress ...
     */

    public static final String EXAMPLE_NS = "http://example.com/";
    public static final String EXAMPLE_PREFIX = "ex";

    private final ProvOneFactory pFactory = new ProvOneFactory();
    private final ProvOneNamespace ns = new ProvOneNamespace();

    public SEOExample() {
        ns.addKnownNamespaces();

        ns.register(EXAMPLE_PREFIX, EXAMPLE_NS);
        ns.register("seo", "http://www.semanticweb.org/fbartusch/ontologies/2024/1/seo");
        ns.register("dcterms", "http://purl.org/dc/terms/");
        ns.register("schema", "http://schema.com/");
        ns.register("foaf", "http://xmlns.com/foaf/0.1/");
        ns.register("scoro", "http://purl.org/spar/scoro/");
    }

    public QualifiedName qn(String n) {
        return ns.qualifiedName(EXAMPLE_PREFIX, n, pFactory);
    }

    public Document makeDocument() {    

        Document document = pFactory.newDocument();

        /*
         * Workflow Representation
         */

        QualifiedName indexQN = qn("-378936176");
        Collection<Attribute> indexAttrs = new LinkedList<>();
        indexAttrs.add(pFactory.newAttribute(Attribute.AttributeKind.PROV_LABEL, "INDEX", pFactory.getName().XSD_STRING));
        indexAttrs.add(pFactory.newAttribute("http://example.com/", "script", "ex",
                "salmon index --threads 1 -t transcriptome.fa -i salmon_index",
                pFactory.getName().XSD_STRING));
        Program indexProg = pFactory.newProgram(indexQN, indexAttrs);
        //document.getStatementOrBundle().add(indexProg);

        /*
         * Trace
         */

        // Execution
        Execution indexExe = pFactory.newExecution(qn("d17fab357a9218b3e4e78fe1b4a07861"),
                                                    pFactory.newISOTime("2024-02-08T05:40:29.713+01:00"),
                                                    pFactory.newISOTime("2024-02-08T05:40:30.905+01:00"),
                                                    "INDEX");
        //document.getStatementOrBundle().add(indexExe);

        // User
        User user = pFactory.newUser(qn("user1"), "user", null, "John", "Doe",
        "john.doe@example.org", null, null, null);
        //document.getStatementOrBundle().add(user);

        // QualifiedAssociations: Relate an execution with a user and a plan (e.g. the program)
        WasAssociatedWith qualAssoc = pFactory.newWasAssociatedWith(null, indexExe.getId(), user.getId(), indexProg.getId(), null);
        //document.getStatementOrBundle().add(qualAssoc);

        /*
         * Software Environment Ontology Elements
         */

        



        document.setNamespace(ns);
        return document;
    }
    
    public void doConversions(Document document, String file) {
        InteropFramework intF = new GenericInteropFramework(this.pFactory);
        intF.writeDocument(file, document);     
    }

    public void doConversionsOrig(Document document, String file) {
        InteropFramework intF=new InteropFramework();
        intF.writeDocument(file, document);    
    }

    public Document deserialize(String file) {
        InteropFramework intF = new GenericInteropFramework(this.pFactory);
        return intF.readDocumentFromFile(file);
    }

    public void closingBanner() {
        System.out.println("*************************");
    }

    public void openingBanner() {
        System.out.println("*************************");
        System.out.println("* Converting document  ");
        System.out.println("*************************");
    }
    
    public static void main(String [] args) {

        SEOExample seo = new SEOExample();
        seo.openingBanner();
        Document document = seo.makeDocument();
        
        /**
         * JSON
         */

        // JSON Serialisation Round 1
        String filename_json_round1 = "./provenance_examples/seo_1.json";
        seo.doConversions(document, filename_json_round1);
        // JSON Deserialisation
        Document jsonDeserialized = seo.deserialize(filename_json_round1);
        // JSON Serialisation Round 2
        //String filename_json_round2 = "./provenance_examples/seo_2.json";
        //seo.doConversions(jsonDeserialized, filename_json_round2);
        
        /**
         * JSON-LD
         */
        // JSON Serialisation Round 1
        String filename_jsonld_round1 = "./provenance_examples/seo_1.jsonld";
        seo.doConversions(document, filename_jsonld_round1);
        // JSON Deserialisation
        Document json_LD_deserialized = seo.deserialize(filename_jsonld_round1);
        // JSON Serialisation Round 2
        String filename_jsonld_round2 = "./provenance_examples/seo_2.jsonld";
        seo.doConversions(json_LD_deserialized, filename_jsonld_round2);
    }
}