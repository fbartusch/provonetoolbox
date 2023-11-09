package org.provtools.provone.vanilla;

import org.openprovenance.prov.model.Namespace;
import org.openprovenance.prov.vanilla.ProvFactory;
import org.provtools.provone.model.ProvOneNamespace;

import java.util.Arrays;
import java.util.List;

import org.openprovenance.prov.interop.InteropFramework;
import org.openprovenance.prov.interop.Outputer;
import org.openprovenance.prov.model.Activity;
import org.openprovenance.prov.model.Agent;
import org.openprovenance.prov.model.Document;
import org.openprovenance.prov.model.Entity;
import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.model.Statement;

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
        ns.register(FMRI_PREFIX, FMRI_NS);
    }

    public QualifiedName qn(String n) {
        return ns.qualifiedName(FMRI_PREFIX, n, pFactory);
    }

    public Document makeDocument() {    

        /*
         * Workflow Representation
         */

        // Programs/Executables used in the Workflow
        Program prog_alignWarp = pFactory.newProgram(qn("align-warp"), "align_warp");
        Program prog_reslice = pFactory.newProgram(qn("reslice"), "reslice");
        Program prog_softmean = pFactory.newProgram(qn("softmean"), "softmean");
        Program prog_slicer = pFactory.newProgram(qn("slicer"), "slicer");
        Program prog_convert = pFactory.newProgram(qn("convert"), "convert");

        // Ports
        // align_warp in/out 
        Port port_alignWarpIn1 = pFactory.newPort(qn("align-warp-inPort1"), "anatomy_image");
        Port port_alignWarpIn2 = pFactory.newPort(qn("align-warp-inPort2"), "anatomy_header");
        Port port_alignWarpIn3 = pFactory.newPort(qn("align-warp-inPort3"), "reference_image");
        Port port_alignWarpIn4 = pFactory.newPort(qn("align-warp-inPort4"), "reference_header");
        Port port_alignWarpOut = pFactory.newPort(qn("align-warp-out"), "warp_params");
        // Reslice
        Port port_reslice_in = pFactory.newPort(qn("reslice-in"), "reslice_in");
        
        Statement alignWarp_hasInPort1 = pFactory.newHasInPort(prog_alignWarp.getId(), port_alignWarpIn1.getId());
        Statement alignWarp_hasOutPort = pFactory.newHasOutPort(prog_alignWarp.getId(), port_alignWarpOut.getId());

        // Channels
        Channel ch_align_warp_reslice = pFactory.newChannel(qn("ch-align-warp-reslice"), "ch_align_warp_reslice");

        // Controller





        // Entities
        // Input files
        // Entity anatomy_img1 = pFactory.newEntity(qn("anatomy-img1"), "anatomy_img1");
        // Entity anatomy_hdr1 = pFactory.newEntity(qn("anatomy-hdr1"), "anatomy_hdr1");
        // Entity anatomy_img2 = pFactory.newEntity(qn("anatomy-img2"), "anatomy_img1");
        // Entity anatomy_hdr2 = pFactory.newEntity(qn("anatomy-hdr2"), "anatomy_hdr2");
        // Entity anatomy_img3 = pFactory.newEntity(qn("anatomy-img3"), "anatomy_img3");
        // Entity anatomy_hdr3 = pFactory.newEntity(qn("anatomy-hdr3"), "anatomy_hdr3");
        // Entity anatomy_img4 = pFactory.newEntity(qn("anatomy-img4"), "anatomy_img4");
        // Entity anatomy_hdr4 = pFactory.newEntity(qn("anatomy-hdr4"), "anatomy_hdr4");
        // Entity reference_img = pFactory.newEntity(qn("reference-img"), "reference_img");
        // Entity reference_hdr = pFactory.newEntity(qn("reference-hdr"), "reference_hdr");

        // Output files
        // Stage 1: align_warp
        // Entity warp1 = pFactory.newEntity(qn("warp1"), "warp1");
        // Entity warp2 = pFactory.newEntity(qn("warp2"), "warp2");
        // Entity warp3 = pFactory.newEntity(qn("warp3"), "warp3");
        // Entity warp4 = pFactory.newEntity(qn("warp4"), "warp4");

        // Stage 2: reslice
        // Entity resliced_img1 = pFactory.newEntity(qn("resliced-img1"), "resliced_img1");
        // Entity resliced_hdr1 = pFactory.newEntity(qn("resliced-hdr1"), "resliced_hdr1");
        // Entity resliced_img2 = pFactory.newEntity(qn("resliced-img2"), "resliced_img2");
        // Entity resliced_hdr2 = pFactory.newEntity(qn("resliced-hdr2"), "resliced_hdr2");
        // Entity resliced_img3 = pFactory.newEntity(qn("resliced-img3"), "resliced_img3");
        // Entity resliced_hdr3 = pFactory.newEntity(qn("resliced-hdr3"), "resliced_hdr3");
        // Entity resliced_img4 = pFactory.newEntity(qn("resliced-img4"), "resliced_img4");
        // Entity resliced_hdr4 = pFactory.newEntity(qn("resliced-hdr4"), "resliced_hdr4");

        // Stage 3: softmean
        // Entity atlas_hdr = pFactory.newEntity(qn("atlas-hdr"), "atlas_hdr");
        // Entity atlas_img = pFactory.newEntity(qn("atlas-img"), "atlas_img");

        // Stage 4: slicer
        // Entity slice_x = pFactory.newEntity(qn("slice-x"), "slice_x");
        // Entity slice_y = pFactory.newEntity(qn("slice-y"), "slice_y");
        // Entity slice_z = pFactory.newEntity(qn("slice-z"), "slice_z");

        // Stage 5: convert
        // Entity slice_x_img = pFactory.newEntity(qn("slice-x-img"), "slice_x_image");
        // Entity slice_y_img = pFactory.newEntity(qn("slice-y-img"), "slice_y_image");
        // Entity slice_z_img = pFactory.newEntity(qn("slice-z-img"), "slice_z_image");

        // Activities
        // Each workflow step (e.g. instance of a rule with new in/output) is a single activity
        // Start and End time were taken from Snakemake report function -> Statistics -> Runtimes -> View Source
        // Activity align_warp1 = pFactory.newActivity(qn("align-warp1"),
        //                                            pFactory.newISOTime("2023-08-21T05:44:05.105160"),
        //                                            pFactory.newISOTime("2023-08-21T05:44:05.361159"),
        //                                            null);
        // Activity align_warp2 = pFactory.newActivity(qn("align-warp2"),
        //                                            pFactory.newISOTime("2023-08-21T05:44:05.365159"),
        //                                            pFactory.newISOTime("2023-08-21T05:44:05.617157"),
        //                                            null);
        // Activity align_warp3 = pFactory.newActivity(qn("align-warp3"),
        //                                            pFactory.newISOTime("2023-08-21T05:44:05.885155"),
        //                                            pFactory.newISOTime("2023-08-21T05:44:06.137154"),
        //                                            null);
        // Activity align_warp4 = pFactory.newActivity(qn("align-warp4"),
        //                                            pFactory.newISOTime("2023-08-21T05:44:05.617157"),
        //                                            pFactory.newISOTime("2023-08-21T05:44:05.881155"),
        //                                            null);                                                   
        // Activity reslice1 = pFactory.newActivity(qn("reslice1"),
        //                                         pFactory.newISOTime("2023-08-21T05:44:07.121147"),
        //                                         pFactory.newISOTime("2023-08-21T05:44:07.449145"),
        //                                         null);
        // Activity reslice2 = pFactory.newActivity(qn("reslice2"),
        //                                         pFactory.newISOTime("2023-08-21T05:44:06.461152"),
        //                                         pFactory.newISOTime("2023-08-21T05:44:06.793149"),
        //                                         null);
        // Activity reslice3 = pFactory.newActivity(qn("reslice3"),
        //                                         pFactory.newISOTime("2023-08-21T05:44:06.137154"),
        //                                         pFactory.newISOTime("2023-08-21T05:44:06.461152"),
        //                                         null);
        // Activity reslice4 = pFactory.newActivity(qn("reslice4"),
        //                                         pFactory.newISOTime("2023-08-21T05:44:06.797149"),
        //                                         pFactory.newISOTime("2023-08-21T05:44:07.117147"),
        //                                         null);                                                                                                                                             
        // Activity softmean = pFactory.newActivity(qn("softmean"),
        //                                          pFactory.newISOTime("2023-08-21T05:44:07.449145"),
        //                                          pFactory.newISOTime("2023-08-21T05:44:08.525138"),
        //                                          null);

        // Although there are three slices, slicer is only called once.
        // The reason is, that all inputs/outputs are hard-coded and the rule is only invoked once.
        // This is in contrast to 'reslice', where the 'reslice' rule is invoked once for every input.                                         
        // Activity slicer = pFactory.newActivity(qn("slicer"),
        //                                        pFactory.newISOTime("2023-08-21T05:44:08.525138"),
        //                                        pFactory.newISOTime("2023-08-21T05:44:10.789124"),                                               
        //                                        null);

        // 'convert' is also only called once with all three inputs and outputs hard-coded in the rule
        // Activity convert = pFactory.newActivity(qn("convert"),
        //                                         pFactory.newISOTime("2023-08-21T05:44:10.793124"),
        //                                         pFactory.newISOTime("2023-08-21T05:44:10.821124"),
        //                                         null);

        // Agents
        // Agent snakemake = pFactory.newAgent(qn("snakemake"), "snakemake");

        // used/was generated by properties
        
        // Stage 1: align_warp
        // Statement warp1_used_img1 = pFactory.newUsed(align_warp1.getId(), anatomy_img1.getId());
        // Statement warp1_used_hdr1 = pFactory.newUsed(align_warp1.getId(), anatomy_hdr1.getId());
        // Statement warp2_used_img2 = pFactory.newUsed(align_warp2.getId(), anatomy_img2.getId());
        // Statement warp2_used_hdr2 = pFactory.newUsed(align_warp2.getId(), anatomy_hdr2.getId());
        // Statement warp3_used_img3 = pFactory.newUsed(align_warp3.getId(), anatomy_img3.getId());
        // Statement warp3_used_hdr3 = pFactory.newUsed(align_warp3.getId(), anatomy_hdr3.getId());
        // Statement warp4_used_img4 = pFactory.newUsed(align_warp4.getId(), anatomy_img4.getId());
        // Statement warp4_used_hdr4 = pFactory.newUsed(align_warp4.getId(), anatomy_hdr4.getId());

        // Statement warp1_used_refhdr = pFactory.newUsed(align_warp1.getId(), reference_hdr.getId());
        // Statement warp1_used_refimg = pFactory.newUsed(align_warp1.getId(), reference_img.getId());
        // Statement warp2_used_refhdr = pFactory.newUsed(align_warp2.getId(), reference_hdr.getId());
        // Statement warp2_used_refimg = pFactory.newUsed(align_warp2.getId(), reference_img.getId());
        // Statement warp3_used_refhdr = pFactory.newUsed(align_warp3.getId(), reference_hdr.getId());
        // Statement warp3_used_refimg = pFactory.newUsed(align_warp3.getId(), reference_img.getId());
        // Statement warp4_used_refhdr = pFactory.newUsed(align_warp4.getId(), reference_hdr.getId());
        // Statement warp4_used_refimg = pFactory.newUsed(align_warp4.getId(), reference_img.getId());

        // Statement warp1_gen_align_warp1 = pFactory.newWasGeneratedBy(warp1, null, align_warp1);
        // Statement warp2_gen_align_warp2 = pFactory.newWasGeneratedBy(warp2, null, align_warp2);
        // Statement warp3_gen_align_warp3 = pFactory.newWasGeneratedBy(warp3, null, align_warp3);
        // Statement warp4_gen_align_warp4 = pFactory.newWasGeneratedBy(warp4, null, align_warp4);

        // Stage 2: reslice
        // Statement reslice1_used_warp1 = pFactory.newUsed(reslice1.getId(), warp1.getId());
        // Statement reslice2_used_warp2 = pFactory.newUsed(reslice2.getId(), warp2.getId());
        // Statement reslice3_used_warp3 = pFactory.newUsed(reslice3.getId(), warp3.getId());
        // Statement reslice4_used_warp4 = pFactory.newUsed(reslice4.getId(), warp4.getId());
        // Statement reslice1_gen_resimg1 = pFactory.newWasGeneratedBy(resliced_img1, null, reslice1);
        // Statement reslice1_gen_reshdr1 = pFactory.newWasGeneratedBy(resliced_hdr1, null, reslice1);
        // Statement reslice2_gen_resimg2 = pFactory.newWasGeneratedBy(resliced_img2, null, reslice2);
        // Statement reslice2_gen_reshdr2 = pFactory.newWasGeneratedBy(resliced_hdr2, null, reslice2);
        // Statement reslice3_gen_resimg3 = pFactory.newWasGeneratedBy(resliced_img3, null, reslice3);
        // Statement reslice3_gen_reshdr3 = pFactory.newWasGeneratedBy(resliced_hdr3, null, reslice3);
        // Statement reslice4_gen_resimg4 = pFactory.newWasGeneratedBy(resliced_img4, null, reslice4);
        // Statement reslice4_gen_reshdr4 = pFactory.newWasGeneratedBy(resliced_hdr4, null, reslice4);

        // Stage 3: softmean
        // Statement softmean_used_resimg1 = pFactory.newUsed(softmean.getId(), resliced_img1.getId());
        // Statement softmean_used_reshdr1 = pFactory.newUsed(softmean.getId(), resliced_hdr1.getId());
        // Statement softmean_used_resimg2 = pFactory.newUsed(softmean.getId(), resliced_img2.getId());
        // Statement softmean_used_reshdr2 = pFactory.newUsed(softmean.getId(), resliced_hdr2.getId());
        // Statement softmean_used_resimg3 = pFactory.newUsed(softmean.getId(), resliced_img3.getId());
        // Statement softmean_used_reshdr3 = pFactory.newUsed(softmean.getId(), resliced_hdr3.getId());
        // Statement softmean_used_resimg4 = pFactory.newUsed(softmean.getId(), resliced_img4.getId());
        // Statement softmean_used_reshdr4 = pFactory.newUsed(softmean.getId(), resliced_hdr4.getId());
        // Statement softmean_gen_atlasimg = pFactory.newWasGeneratedBy(atlas_img, null, softmean);
        // Statement softmean_gen_atlashdr = pFactory.newWasGeneratedBy(atlas_hdr, null, softmean);

        // Stage 4: slicer
        // Statement slicer_used_atlasimg = pFactory.newUsed(slicer.getId(), atlas_img.getId());
        // Statement slicer_used_atlashdr = pFactory.newUsed(slicer.getId(), atlas_hdr.getId());
        // Statement slicer_gen_xslice = pFactory.newWasGeneratedBy(slice_x, null, slicer);
        // Statement slicer_gen_yslice = pFactory.newWasGeneratedBy(slice_y, null, slicer);
        // Statement slicer_gen_zslice = pFactory.newWasGeneratedBy(slice_z, null, slicer);

        // Stage 5: convert
        // Statement convert_used_xslice = pFactory.newUsed(convert.getId(), slice_x.getId());
        // Statement convert_used_yslice = pFactory.newUsed(convert.getId(), slice_y.getId());
        // Statement convert_used_zslice = pFactory.newUsed(convert.getId(), slice_z.getId());
        // Statement convert_gen_ximage = pFactory.newWasGeneratedBy(slice_x_img, null, convert);
        // Statement convert_gen_yimage = pFactory.newWasGeneratedBy(slice_y_img, null, convert);
        // Statement convert_gen_zimage = pFactory.newWasGeneratedBy(slice_z_img, null, convert);

        // Lists of all elements in the document
        List<Program> programs = Arrays.asList(prog_alignWarp, prog_reslice, prog_softmean, prog_slicer, prog_convert);
        List<Port> ports = Arrays.asList(port_alignWarpIn1, port_alignWarpIn2, port_alignWarpIn3, port_alignWarpIn4,
                                         port_alignWarpOut, port_reslice_in);
        List<Channel> channels = Arrays.asList(ch_align_warp_reslice);
        //List<Entity> entities = Arrays.asList();
        //List<Activity> activities = Arrays.asList();
        //List<Agent> agents = Arrays.asList();
        List<Statement> statements = Arrays.asList(alignWarp_hasInPort1, alignWarp_hasOutPort);
        
        Document document = pFactory.newDocument();
        document.getStatementOrBundle().addAll(programs);
        document.getStatementOrBundle().addAll(ports);
        document.getStatementOrBundle().addAll(channels);
        // document.getStatementOrBundle().addAll(entities);
        // document.getStatementOrBundle().addAll(activities);
        // document.getStatementOrBundle().addAll(agents);
        document.getStatementOrBundle().addAll(statements);
        document.setNamespace(ns);
        return document;
    }
    
    public void doConversions(Document document, String file) {
        InteropFramework intF = new GenericInteropFramework(this.pFactory, null);
        //Outputer outputer = new ProvOneOutputer(null, this.pFactory);
        intF.writeDocument(file, document);     
    }

    public void doConversionsOrig(Document document, String file) {
        InteropFramework intF=new InteropFramework();
        intF.writeDocument(file, document);    
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
        
        // JSON
        String filename_json = prefix + ".json";
        fmri.doConversions(document, filename_json);
        
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