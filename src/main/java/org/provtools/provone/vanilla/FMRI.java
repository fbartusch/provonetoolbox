package org.provtools.provone.vanilla;

import org.provtools.provone.model.ProvOneNamespace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.openprovenance.prov.interop.GenericInteropFramework;
import org.openprovenance.prov.interop.InteropFramework;
import org.openprovenance.prov.model.Attribute;
import org.openprovenance.prov.model.Document;
import org.openprovenance.prov.model.Entity;
import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.model.Statement;
import org.openprovenance.prov.model.Used;
import org.openprovenance.prov.model.WasAssociatedWith;
import org.openprovenance.prov.model.WasDerivedFrom;
import org.openprovenance.prov.model.WasGeneratedBy;
import org.openprovenance.prov.model.HadMember;
import org.openprovenance.prov.model.Name;


//TODO A generic interface for attribute kinds. Then new schemas can be implemented as extension of the generic interface and used with ProvFactoriy's newAttribute method

/**
 * Adapted from: ProvToolbox Tutorial 1: creating a provenance document in Java and serializing it
 * 
 * This class will create a ProvONE provenance document for the fMRI workflow implemented with snakemake.
 * It is used to play around with the implemented ProvOneToolbox and check if things work as intended.
 * 
 * @see <a href="https://github.com/fbartusch/fMRI_snakemake">fMRI workflow implementation</a>
 * @see <a href="https://github.com/fbartusch/ProvToolbox-playground">Playing around with ProvToolbox</a>
 * 
 */
public class FMRI {

    /*
     * This is totally Work in progress ...
     */

    public static final String FMRI_NS = "https://example.com/";
    public static final String FMRI_PREFIX = "fmri";

    private final ProvOneFactory pFactory;
    private final ProvOneNamespace ns;

    public FMRI() {
        this.pFactory = new ProvOneFactory();
        ns = new ProvOneNamespace();
        ns.addKnownNamespaces();
        //ns.addSchemaNamespace();
        ns.register(FMRI_PREFIX, FMRI_NS);
        ns.register("dcterms", "http://purl.org/dc/terms/");
        ns.register("schema", "https://schema.org/");
        ns.register("foaf", "http://xmlns.com/foaf/0.1/");
        ns.register("scoro", "http://purl.org/spar/scoro/");
    }

    public QualifiedName qn(String n) {
        return ns.qualifiedName(FMRI_PREFIX, n, pFactory);
    }

    public Document makeDocument() {    

        Name names = new Name(pFactory);

        /*
         * Workflow Representation
         */

        // Programs/Executables used in the Workflow
        // align_warp
        Program prog_alignWarp = pFactory.newProgram(qn("align-warp"), "align_warp", "5.3.0", "AIR",
                                                     "http://air.bmap.ucla.edu/AIR5/index.html",
                                                     "http://air.bmap.ucla.edu/AIR5/AIRrefs.html",
                                                     "AIR Suite needs registering account. No-cost and custom license.");

        // reslice
        Program prog_reslice = pFactory.newProgram(qn("reslice"), "reslice", "5.3.0", "AIR",
                                                     "http://air.bmap.ucla.edu/AIR5/index.html",
                                                     "http://air.bmap.ucla.edu/AIR5/AIRrefs.html",
                                                     "AIR Suite needs registering account. No-cost and custom license.");        

        // softmean
        Program prog_softmean = pFactory.newProgram(qn("softmean"), "softmean", "5.3.0", "AIR",
                                                     "http://air.bmap.ucla.edu/AIR5/index.html",
                                                     "http://air.bmap.ucla.edu/AIR5/AIRrefs.html",
                                                     "AIR Suite needs registering account. No-cost and custom license.");

        // slicer
        Program prog_slicer = pFactory.newProgram(qn("slicer"), "slicer", "3.5.3", "FSL",
                                                     "https://fsl.fmrib.ox.ac.uk/fsl/fslwiki/FslInstallation",
                                                     "https://fsl.fmrib.ox.ac.uk/fsl/fslwiki",
                                                     "FSL Suite needs registering account before downloading. No-cost and custom license.");

        // convert
        Program prog_convert = pFactory.newProgram(qn("convert"), "convert", "6.9.10-23 Q16 x86_64 20190101", "ImageMagick",
                                                     "https://legacy.imagemagick.org/script/download.php",
                                                     "ImageMagick Studio LLC. (2023). ImageMagick. Retrieved from https://imagemagick.org",
                                                     null);

        // Ports
        // align_warp in/out 
        //qn, label, List of schema:encodingFormat, description
        //QualifiedName id, String label, List<String> encodingFormats, String description
        Port port_alignWarpIn1 = pFactory.newPort(qn("align-warp-inPort1"), "Anatomy Image", Arrays.asList("testFormat1", "testFormat2"), "NIfTI files: img part");
        Port port_alignWarpIn2 = pFactory.newPort(qn("align-warp-inPort2"), "Anatomy Header", null, "NIfTI files: hdr part");
        Port port_alignWarpIn3 = pFactory.newPort(qn("align-warp-inPort3"), "Reference Image", null, "NIfTI files: img part");
        Port port_alignWarpIn4 = pFactory.newPort(qn("align-warp-inPort4"), "Reference Header", null, "NIfTI files: hdr part");
        Port port_alignWarpIn5 = pFactory.newPort(qn("align-warp-inPort5"), "-m", null, null);
        Port port_alignWarpOut = pFactory.newPort(qn("align-warp-out"), "warp_params", null, null);
        // Reslice
        Port port_reslice_in = pFactory.newPort(qn("reslice-in"), "reslice_in", null, null);
        
        // Channels
        Channel ch_alignWarp_reslice = pFactory.newChannel(qn("ch-align-warp-reslice"), "ch_align_warp_reslice");

        // Controller
        Controller wfms = pFactory.newController(qn("snakemake"), "snakemake", "5.19.3", "https://snakemake.readthedocs.io/en/stable/getting_started/installation.html",
                                                     "https://snakemake.readthedocs.io/en/stable/project_info/citations.html",
                                                     null);

        // Workflow
        Workflow wf = pFactory.newWorkflow(qn("fmri-workflow"), "fMRI workflow", null, null,
                                           "https://github.com/fbartusch/fMRI_snakemake",
                                           null,
                                           "Implemenation of fMRI workflow used in the Provenance Challenges with Snakemake.");

        Workflow original_wf = pFactory.newWorkflow(qn("original-fmri-workflow"), "fMRI workflow used in the Provenance Challenges", null, null,
                                                      "https://openprovenance.org/provenance-challenge/FirstProvenanceChallenge.html",
                                                      null,
                                                      "fMRI workflow used in the Provenance Challenges");


        // hasSubProgram
        Statement wf_sub_alignWarp = pFactory.newHasSubProgram(wf.getId(), prog_alignWarp.getId());

        // controlledBy
        Statement wfms_controls_wf = pFactory.newControls(wfms.getId(), wf.getId());
        
        // controls
        Statement wf_controlledBy_wfms = pFactory.newControlledBy(wf.getId(), wfms.getId());

        // hasInPort/hasOutPort
        Statement alignWarp_hasInPort1 = pFactory.newHasInPort(prog_alignWarp.getId(), port_alignWarpIn1.getId());
        Statement alignWarp_hasInPort2 = pFactory.newHasInPort(prog_alignWarp.getId(), port_alignWarpIn2.getId());
        Statement alignWarp_hasOutPort = pFactory.newHasOutPort(prog_alignWarp.getId(), port_alignWarpOut.getId());

        // hasDefaultParam
        Entity port_alignWarpIn5_defaultParam = pFactory.newEntity(qn("align-warp-m-default-param"), "align_warp_m_default_param");
        Statement defparam_alignWarp = pFactory.newHasDefaultParam(port_alignWarpIn5.getId(), port_alignWarpIn5_defaultParam.getId());

        // connectsTo
        Statement con_alignWarpOut = pFactory.newConnectsTo(port_alignWarpOut.getId(), ch_alignWarp_reslice.getId());

        // wasDerivedFrom
        Statement wf_wasDerivedFrom_orig = pFactory.newWasDerivedFrom(wf.getId(), original_wf.getId());


        /*
         * Data Structure: Input/Output Files
         */

        // Input files
        Data anatomy_img1 =  pFactory.newData(qn("anatomy-img1"), "anatomy1.img", "~/github/fMRI_snakemake/resources/example_input/samples/anatomy1.img",
                                                 "f4696781e18af5b34ae432f6de97f98608fdc7d16e955110ef82a3719538d226", null, null, null);
        Data anatomy_hdr1 =  pFactory.newData(qn("anatomy-hdr1"), "anatomy1.hdr", "~/github/fMRI_snakemake/resources/example_input/samples/anatomy1.hdr",
                                                 "95c931aef606f6d35a8010c60b30e07dad2fb40c2c89e412372d7d37171f740c", null, null, null);
        Data anatomy_img2 =  pFactory.newData(qn("anatomy-img2"), "anatomy2.img", "~/github/fMRI_snakemake/resources/example_input/samples/anatomy2.img",
                                                 "39e5c7ad3f6bca0c6f983ce6a327b480e7d6f2d0510670867923a1dce94928fd", null, null, null);
        Data anatomy_hdr2 =  pFactory.newData(qn("anatomy-hdr2"), "anatomy2.hdr", "~/github/fMRI_snakemake/resources/example_input/samples/anatomy2.hdr",
                                                 "95c931aef606f6d35a8010c60b30e07dad2fb40c2c89e412372d7d37171f740c", null, null, null);
        Data anatomy_img3 =  pFactory.newData(qn("anatomy-img3"), "anatomy3.img", "~/github/fMRI_snakemake/resources/example_input/samples/anatomy3.img",
                                                 "42869acea37b0c25e6599ca2f342c80cabceeba0d956a0cf0674a3fa406e1fc1", null, null, null);
        Data anatomy_hdr3 =  pFactory.newData(qn("anatomy-hdr3"), "anatomy3.hdr", "~/github/fMRI_snakemake/resources/example_input/samples/anatomy3.hdr",
                                                 "95c931aef606f6d35a8010c60b30e07dad2fb40c2c89e412372d7d37171f740c", null, null, null);
        Data anatomy_img4 =  pFactory.newData(qn("anatomy-img4"), "anatomy4.img", "~/github/fMRI_snakemake/resources/example_input/samples/anatomy4.img",
                                                 "6b549bd112d865e7cfb0d4309b46987517ec7623832d78a79f2229fc6b24dddf", null, null, null);
        Data anatomy_hdr4 =  pFactory.newData(qn("anatomy-hdr4"), "anatomy4.hdr", "~/github/fMRI_snakemake/resources/example_input/samples/anatomy4.hdr",
                                                 "95c931aef606f6d35a8010c60b30e07dad2fb40c2c89e412372d7d37171f740c", null, null, null);
        Data reference_img =  pFactory.newData(qn("reference-img"), "reference.img", "~/github/fMRI_snakemake/resources/example_input/reference/reference.img",
                                                 "fa276671709d87e7ff4907d11e89505c4d9ac37015d273c94e36202ad63b1a44", null, null, null);
        Data reference_hdr =  pFactory.newData(qn("reference-hdr"), "reference.hdr", "~/github/fMRI_snakemake/resources/example_input/reference/reference.hdr",
                                                 "95c931aef606f6d35a8010c60b30e07dad2fb40c2c89e412372d7d37171f740c", null, null, null);

        // Collection for input files
        Collection<Attribute> inputCollectionAttrs = new LinkedList<>();
        inputCollectionAttrs.add(pFactory.newAttribute(Attribute.AttributeKind.PROV_LABEL, "Visualization Test", names.XSD_STRING));
        //TODO How to set type of entity to 'collection'?
        inputCollectionAttrs.add(pFactory.newAttribute(Attribute.AttributeKind.PROV_TYPE, "Visualization Test", names.XSD_STRING));
        Entity inputCollection = pFactory.newEntity(qn("inputFileCollection"), inputCollectionAttrs);
        Collection<QualifiedName> inputQNames = Arrays.asList(anatomy_img1.getId(), anatomy_hdr1.getId(), anatomy_img2.getId(), anatomy_hdr2.getId(),
                                                              anatomy_img3.getId(), anatomy_hdr3.getId(), anatomy_img4.getId(), anatomy_hdr4.getId(),
                                                              reference_img.getId(), reference_hdr.getId());
        HadMember inputCollectionHadMember = pFactory.newHadMember(qn("inputFileCollection"), inputQNames);

        // Output files
        // Stage 1: align_warp
        Data warp1 = pFactory.newData(qn("warp1"), "warp1");
        Data warp2 = pFactory.newData(qn("warp2"), "warp2");
        Data warp3 = pFactory.newData(qn("warp3"), "warp3");
        Data warp4 = pFactory.newData(qn("warp4"), "warp4");


        /*
         * Trace
         */

        // Execution
        Execution wf_exe = pFactory.newExecution(qn("wf-execution"),
                                                    pFactory.newISOTime("2023-08-21T05:44:05.105160"),
                                                    pFactory.newISOTime("2023-08-21T05:44:10.821124"),
                                                    "workflow_execution");
        

    
        Execution alignWarp_exe1 = pFactory.newExecution(qn("align-warp-exe1"),
                                                         pFactory.newISOTime("2023-08-21T05:44:05.105160"),
                                                         pFactory.newISOTime("2023-08-21T05:44:05.361159"),
                                                         "align_warp_execution_1");
        Execution alignWarp_exe2 = pFactory.newExecution(qn("align-warp-exe2"),
                                                         pFactory.newISOTime("2023-08-21T05:44:05.365159"),
                                                         pFactory.newISOTime("2023-08-21T05:44:05.617157"),
                                                         "align_warp_execution_2");
        Execution alignWarp_exe3 = pFactory.newExecution(qn("align-warp-exe3"),
                                                         pFactory.newISOTime("2023-08-21T05:44:05.885155"),
                                                         pFactory.newISOTime("2023-08-21T05:44:06.137154"),
                                                         "align_warp_execution_3");
        Execution alignWarp_exe4 = pFactory.newExecution(qn("align-warp-exe4"),
                                                         pFactory.newISOTime("2023-08-21T05:44:05.617157"),
                                                         pFactory.newISOTime("2023-08-21T05:44:05.881155"),
                                                         "align_warp_execution_4");
        Execution reslice_exe1 =   pFactory.newExecution(qn("reslice-exe1"),
                                                         pFactory.newISOTime("2023-08-21T05:44:07.121147"),
                                                         pFactory.newISOTime("2023-08-21T05:44:07.449145"),
                                                         "reslice_execution_1");

        // User
        User felix = pFactory.newUser(qn("felix"), "felix", null, "Felix", "Bartusch",
                                       "felix.bartusch[at]uni-tuebingen.de", "https://fbartusch.github.io/",
                                       null, "0000-0003-0711-5196");

        // wasAssociatedWith (without a plan)
        WasAssociatedWith felix_assoc_wf_exe = pFactory.newWasAssociatedWith(null, wf_exe.getId(), felix.getId());
        // Association (with a plan)
        // TODO test with role attribute
        WasAssociatedWith felix_qualAssoc_wf_exe = pFactory.newWasAssociatedWith(null, wf_exe.getId(), felix.getId(), wf.getId(), null);
        
        // used
        Statement alignWarp_exe1_used_img1 = pFactory.newUsed(alignWarp_exe1.getId(), anatomy_img1.getId());

        // wasGeneratedBy
        Statement warp1_genBy_alignWarp_exe1 = pFactory.newWasGeneratedBy(warp1, null, alignWarp_exe1);

        // wasInformedBy:  prov:wasInformedBy is adopted in ProvONE to state that an Execution communicates with another
        // Execution through an output-input relation, and thereby triggers its execution. 
        Statement reslice_infBy_alignWarp_1 = pFactory.newWasInformedBy(null, reslice_exe1.getId(), alignWarp_exe1.getId());

        // wasPartOf
        Statement alignWarp_exe1_partOf_wf = pFactory.newWasPartOf(alignWarp_exe1.getId(), wf_exe.getId());

        // Usage / Qualified Usage
        Used alignWarp1_qualUsage_img1 = pFactory.newUsed(qn("usage1"), alignWarp_exe1.getId(), anatomy_img1.getId(), null, inputCollectionAttrs);

        // hadEntity
        // From ProvOne documentation:
        // Through the use of the Usage and Generation classes, whenever an Entity item is sent from an output Port
        // to an input Port, this event is recorded through the hadEntity, hadInPort and hadOutPort properties between
        // the Entity item and the associated Ports. In this manner, the graph structure that represents the provenance
        // of the workflow results is generated. 
        Statement usage1_hadEntity = pFactory.newHadEntity(alignWarp1_qualUsage_img1.getId(), anatomy_img1.getId());

        // hadInPort
        Statement usage1_hadInPort = pFactory.newHadInPort(alignWarp1_qualUsage_img1.getId(), port_alignWarpIn1.getId());
        
        // Generation
        WasGeneratedBy generation1 = pFactory.newWasGeneratedBy(qn("generation1"), warp1.getId(), alignWarp_exe1.getId(),
                                                                                  pFactory.newISOTime("2023-08-21T05:44:05.361159"), null);
        // HadEntity
        Statement generation1_hadEntity = pFactory.newHadEntity(generation1.getId(), warp1.getId());
        // hadOutPort
        Statement generation1_hadOutPort = pFactory.newHadOutPort(generation1.getId(), port_alignWarpOut.getId());


        /*
         * Data Structure
         */

        // Visualization: This is just for testing and doesn't belong to the workflow
        Visualization visTest = pFactory.newVisualization(qn("visTest"), "TestVisualization");

        // Document: This is just for testing and doesn't belong to the workflow
        org.provtools.provone.vanilla.Document docTest = pFactory.newDocument(qn("docTest"), "TestDocument");

        // wasDerivedFrom: This is just for testing and doesn't belong to the workflow
        WasDerivedFrom wdfTest = pFactory.newWasDerivedFrom(visTest.getId(), docTest.getId());

        // Lists of all elements in the document
        List<Program> programs = Arrays.asList(prog_alignWarp, prog_reslice, prog_softmean, prog_slicer, prog_convert);
        List<Port> ports = Arrays.asList(port_alignWarpIn1, port_alignWarpIn2, port_alignWarpIn3, port_alignWarpIn4, port_alignWarpIn5, 
                                         port_alignWarpOut, port_reslice_in);
        List<Channel> channels = Arrays.asList(ch_alignWarp_reslice);
        List<Controller> controller = Arrays.asList(wfms);
        List<Workflow> workflows = Arrays.asList(wf, original_wf);
        List<Execution> executions = Arrays.asList(wf_exe, alignWarp_exe1, alignWarp_exe2, alignWarp_exe3, alignWarp_exe4);
        List<User> user = Arrays.asList(felix);
        List<Data> data = Arrays.asList(anatomy_img1, anatomy_hdr1, anatomy_img2, anatomy_hdr2, anatomy_img3, anatomy_hdr3, anatomy_img4,
                                        anatomy_hdr4, reference_img, reference_hdr, warp1, warp2, warp3, warp4);
        List<Visualization> visualizations = Arrays.asList(visTest);
        List<org.provtools.provone.vanilla.Document> documents = Arrays.asList(docTest);
        List<Entity> entities = Arrays.asList(port_alignWarpIn5_defaultParam, inputCollection);
        List<HadMember> hadMembers = Arrays.asList(inputCollectionHadMember);

        //List<Activity> activities = Arrays.asList();
        //List<Agent> agents = Arrays.asList();
        List<Statement> statements = Arrays.asList(alignWarp_hasInPort1, alignWarp_hasInPort2, alignWarp_hasOutPort, wf_sub_alignWarp, defparam_alignWarp,
                                                   con_alignWarpOut, wf_wasDerivedFrom_orig, wfms_controls_wf, wf_controlledBy_wfms,
                                                   felix_assoc_wf_exe, alignWarp_exe1_partOf_wf, felix_qualAssoc_wf_exe, wdfTest,
                                                   alignWarp_exe1_used_img1, warp1_genBy_alignWarp_exe1, reslice_infBy_alignWarp_1,
                                                   alignWarp1_qualUsage_img1, usage1_hadEntity, usage1_hadInPort, generation1,
                                                   generation1_hadEntity, generation1_hadOutPort);

        Document document = pFactory.newDocument();
        document.getStatementOrBundle().addAll(programs);
        document.getStatementOrBundle().addAll(ports);
        document.getStatementOrBundle().addAll(channels);
        document.getStatementOrBundle().addAll(controller);
        document.getStatementOrBundle().addAll(workflows);
        document.getStatementOrBundle().addAll(executions);
        document.getStatementOrBundle().addAll(user);
        document.getStatementOrBundle().addAll(data);
        document.getStatementOrBundle().addAll(visualizations);
        document.getStatementOrBundle().addAll(documents);
        document.getStatementOrBundle().addAll(entities);
        document.getStatementOrBundle().addAll(hadMembers);
        document.getStatementOrBundle().addAll(statements);
        document.setNamespace(ns);
        return document;
    }
    
    public void doConversions(Document document, String file) {
        InteropFramework intF = new GenericInteropFramework(this.pFactory);
        //Outputer outputer = new ProvOneOutputer(null, this.pFactory);
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

    public Document deserializeOrig(String file) {
        InteropFramework intF = new InteropFramework(this.pFactory);
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
        if (args.length!=1) throw new UnsupportedOperationException("main to be called with filename");
        String prefix=args[0];
        
        // Test Output formats
        // Try out every format listed in that tutorial:
        // https://lucmoreau.wordpress.com/2015/07/05/provtoolbox-tutorial-2-reading-converting-and-saving-prov-documents/

        FMRI fmri=new FMRI();
        fmri.openingBanner();
        Document document = fmri.makeDocument();

        // Try conversions
        // graphviz
        //String filename_gv = prefix + ".gv";
        //fmri.doConversions(document, filename_gv);
        //String filename_dot = prefix + ".dot";
        //fmri.doConversions(document, filename_dot);

        // provenance-notation
        //String filename_prov_asn = prefix + ".prov-asn";
        //fmri.doConversions(document, filename_prov_asn);
        //String filename_pn = prefix + ".pn";
        //fmri.doConversions(document, filename_pn);
        //String filename_asn = prefix + ".asn";
        //fmri.doConversions(document, filename_asn);
        //String filename_provn = prefix + ".provn";
        //fmri.doConversions(document, filename_provn);                
        //fmri.closingBanner();

        // According to comment in ProvFormat.java:
        // TURTLE, RDFXML, TRIG no longer supported by provtoolbox, lead to exceptions at invocation time
        // RDF+XML
        //String filename_rdf = prefix + ".rdf";
        //fmri.doConversions(document, filename_rdf);
        
        /**
         * JSON
         */

        // JSON Serialisation Round 1
        String filename_json_round1 = prefix + ".json";
        fmri.doConversions(document, filename_json_round1);
        // JSON Deserialisation
        Document jsonDeserialized = fmri.deserialize(filename_json_round1);
        // JSON Serialisation Round 2
        String filename_json_round2 = prefix + "_2.json";
        fmri.doConversions(jsonDeserialized, filename_json_round2);
        
        /**
         * JSON-LD
         */
        // JSON Serialisation Round 1
        String filename_jsonld_round1 = prefix + ".jsonld";
        fmri.doConversions(document, filename_jsonld_round1);
        // JSON Deserialisation
        Document json_LD_deserialized = fmri.deserialize(filename_jsonld_round1);
        // JSON Serialisation Round 2
        String filename_jsonld_round2 = prefix + "_2.jsonld";
        fmri.doConversions(json_LD_deserialized, filename_jsonld_round2);
        

        // Turtle
        //String filename_ttl = prefix + ".ttl";
        //fmri.doConversions(document, filename_ttl);
        
        // Trig
        //String filename_trig = prefix + ".trig";
        //fmri.doConversions(document, filename_trig);
        
        // JPEG
        // String filename_jpg = prefix + ".jpg";
        // fmri.doConversions(document, filename_jpg);
        // Error:  Format: "jpg" not recognized. Use one of: canon cmap cmapx cmapx_np dot dot_json eps
        // fig gv imap imap_np ismap json json0 mp pdf pic plain plain-ext png pov ps ps2 svg svgz tk
        // vml vmlz xdot xdot1.2 xdot1.4 xdot_json

        // XML
        //String filename_xml = prefix + ".xml";
        //fmri.doConversions(document, filename_xml);

        // PNG
        //String filename_png = prefix + ".png";
        //fmri.doConversions(document, filename_png);

        // PDF
        //String filename_pdf = prefix + ".pdf";
        //fmri.doConversions(document, filename_pdf);

        // SVG
        //String filename_svg = prefix + ".svg";
        //fmri.doConversions(document, filename_svg);
    }
}