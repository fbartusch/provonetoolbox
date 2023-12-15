package org.provtools.provone.vanilla;

import org.provtools.provone.model.ProvOneNamespace;

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
        Port port_alignWarpIn1 = pFactory.newPort(qn("align-warp-inPort1"), "Anatomy Image", Arrays.asList("testFormat1", "testFormat2"), "NIfTI files: img part");
        Port port_alignWarpIn2 = pFactory.newPort(qn("align-warp-inPort2"), "Anatomy Header", null, "NIfTI files: hdr part");
        Port port_alignWarpIn3 = pFactory.newPort(qn("align-warp-inPort3"), "Reference Image", null, "NIfTI files: img part");
        Port port_alignWarpIn4 = pFactory.newPort(qn("align-warp-inPort4"), "Reference Header", null, "NIfTI files: hdr part");
        Port port_alignWarpIn5 = pFactory.newPort(qn("align-warp-inPort5"), "-m", null, null);
        Port port_alignWarpOut = pFactory.newPort(qn("align-warp-out"), "warp_params", null, null);
        // Reslice in/out
        Port port_reslice_in = pFactory.newPort(qn("reslice-in"), "Warp parameters input", null, "Input port for warp parameters");
        Port port_reslice_out_img = pFactory.newPort(qn("reslice-out-img"), "Resliced img part", null, "Output for resliced NIfTI files: img part");
        Port port_reslice_out_hdr = pFactory.newPort(qn("reslice-out-hdr"), "Resliced hdr part", null, "Output for resliced NIfTI files: hdr part");
        // softmean in/out
        Port port_softmean_in_img = pFactory.newPort(qn("softmean-in-img"), "Softmean img input", null, "Input port for NIfTI files: img part");
        Port port_softmean_in_hdr = pFactory.newPort(qn("softmean-in-hdr"), "Softmean hdr input", null, "Input port for NIfTI files: hdr part");
        Port port_softmean_out_img = pFactory.newPort(qn("softmean-out-img"), "Softmean img output", null, "Output port for NIfTI files: img part");
        Port port_softmean_out_hdr = pFactory.newPort(qn("softmean-out-hdr"), "Softmean hdr output", null, "Output port for NIfTI files: hdr part");
        // slicer in/out
        Port port_slicer_in_img = pFactory.newPort(qn("slicer-in-img"), "Slicer img input", null, "Input port for NIfTI files: img part");
        Port port_slicer_in_hdr = pFactory.newPort(qn("slicer-in-hdr"), "Slicer hdr input", null, "Input port for NIfTI files: hdr part");
        Port port_slicer_out = pFactory.newPort(qn("slicer-out"), "Slicer output", Arrays.asList("image/x-portable-greymap"), "Slicer output in pgm format");
        // convert in/out
        Port port_convert_in = pFactory.newPort(qn("convert-in"), "Convert input", Arrays.asList("image/x-portable-greymap"), "Convert input port");
        Port port_convert_out = pFactory.newPort(qn("convert-out"), "Coinvert output", Arrays.asList("image/gif"), "Convert output port");

        // hasInPort/hasOutPort
        // align_warp
        Statement alignWarp_hasInPort1 = pFactory.newHasInPort(prog_alignWarp.getId(), port_alignWarpIn1.getId());
        Statement alignWarp_hasInPort2 = pFactory.newHasInPort(prog_alignWarp.getId(), port_alignWarpIn2.getId());
        Statement alignWarp_hasInPort3 = pFactory.newHasInPort(prog_alignWarp.getId(), port_alignWarpIn3.getId());
        Statement alignWarp_hasInPort4 = pFactory.newHasInPort(prog_alignWarp.getId(), port_alignWarpIn4.getId());
        Statement alignWarp_hasInPort5 = pFactory.newHasInPort(prog_alignWarp.getId(), port_alignWarpIn5.getId());
        Statement alignWarp_hasOutPort = pFactory.newHasOutPort(prog_alignWarp.getId(), port_alignWarpOut.getId());
        // Reslice
        Statement reslice_hasInPort = pFactory.newHasInPort(prog_reslice.getId(), port_reslice_in.getId());
        Statement reslice_hasOutPort1 = pFactory.newHasOutPort(prog_reslice.getId(), port_reslice_out_img.getId());
        Statement reslice_hasOutPort2 = pFactory.newHasOutPort(prog_reslice.getId(), port_reslice_out_hdr.getId());
        // softmean
        Statement softmean_hasInPort1 = pFactory.newHasInPort(prog_softmean.getId(), port_softmean_in_img.getId());
        Statement softmean_hasInPort2 = pFactory.newHasInPort(prog_softmean.getId(), port_softmean_in_hdr.getId());
        Statement softmean_hasOutPort1 = pFactory.newHasOutPort(prog_softmean.getId(), port_softmean_out_img.getId());
        Statement softmean_hasOutPort2 = pFactory.newHasOutPort(prog_softmean.getId(), port_softmean_out_hdr.getId());
        // slicer
        Statement slicer_hasInPort1 = pFactory.newHasInPort(prog_slicer.getId(), port_slicer_in_img.getId());
        Statement slicer_hasInPort2 = pFactory.newHasInPort(prog_slicer.getId(), port_slicer_in_hdr.getId());
        Statement slicer_hasOutPort = pFactory.newHasOutPort(prog_slicer.getId(), port_slicer_out.getId());
        // convert in/out
        Statement convert_hasInPort = pFactory.newHasInPort(prog_convert.getId(), port_convert_in.getId());
        Statement convert_hasOutPort = pFactory.newHasOutPort(prog_convert.getId(), port_convert_out.getId());

        // Channels
        // align_warp -> reslice
        Channel ch_alignWarpOut_resliceIn = pFactory.newChannel(qn("ch-alignWarpOut-resliceIn"), "Channel between align_warp output and reslice input.");
        // reslice -> softmean
        Channel ch_resliceOutImg_softmeanInImg = pFactory.newChannel(qn("ch-resliceOutImg-softmeanInImg"), "Channel between reslice output and softmean input for NIfTI files: img part");
        Channel ch_resliceOutHdr_softmeanInHdr = pFactory.newChannel(qn("ch-resliceOutHdr-softmeanInHdr"), "Channel between reslice output and softmean input for NIfTI files: hdr part");
        // softmean -> slicer
        Channel ch_softmeanOutImg_slicerInImg = pFactory.newChannel(qn("ch-softmeanOutImg-slicerInImg"), "Channel between softmean output and slicer input for NIfTI files: img part");
        Channel ch_softmeanOutHdr_slicerInHdr = pFactory.newChannel(qn("ch-softmeanOutHdr-slicerInHdr"), "Channel between softmean output and slicer input for NIfTI files: hdr part");
        // slicer -> convert
        Channel ch_slicerOut_convertIn = pFactory.newChannel(qn("ch-slicerOut-ConvertIn"), "Channel between slicer output and convert input.");

        // connectsTo (connect Ports to Channels)
        // align_warp out ports / reslice input ports
        Statement con_alignWarpOut = pFactory.newConnectsTo(port_alignWarpOut.getId(), ch_alignWarpOut_resliceIn.getId());
        Statement con_resliceIn = pFactory.newConnectsTo(port_reslice_in.getId(), ch_alignWarpOut_resliceIn.getId());
        // reslice out ports / softmean input ports
        Statement con_reslice_out_img = pFactory.newConnectsTo(port_reslice_out_img.getId(), ch_resliceOutImg_softmeanInImg.getId());
        Statement con_softmean_in_img = pFactory.newConnectsTo(port_softmean_in_img.getId(), ch_resliceOutImg_softmeanInImg.getId());
        Statement con_reslice_out_hdr = pFactory.newConnectsTo(port_reslice_out_hdr.getId(), ch_resliceOutHdr_softmeanInHdr.getId());
        Statement con_softmean_in_hdr = pFactory.newConnectsTo(port_softmean_in_hdr.getId(), ch_resliceOutHdr_softmeanInHdr.getId());
        // softmean out ports / slicer input ports
        Statement con_softmean_out_img = pFactory.newConnectsTo(port_softmean_out_img.getId(), ch_softmeanOutImg_slicerInImg.getId());
        Statement con_slicer_in_img = pFactory.newConnectsTo(port_slicer_in_img.getId(), ch_softmeanOutImg_slicerInImg.getId());
        Statement con_softmean_out_hdr = pFactory.newConnectsTo(port_softmean_out_hdr.getId(), ch_softmeanOutHdr_slicerInHdr.getId());
        Statement con_slicer_in_hdr = pFactory.newConnectsTo(port_slicer_in_hdr.getId(), ch_softmeanOutHdr_slicerInHdr.getId());
        // slicer out port / convert input port
        Statement con_slicer_out = pFactory.newConnectsTo(port_slicer_out.getId(), ch_slicerOut_convertIn.getId());
        Statement con_convert_in = pFactory.newConnectsTo(port_convert_in.getId(), ch_slicerOut_convertIn.getId());

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
        Statement wf_wasDerivedFrom_orig = pFactory.newWasDerivedFrom(wf.getId(), original_wf.getId());


        // hasSubProgram
        Statement wf_sub_alignWarp = pFactory.newHasSubProgram(wf.getId(), prog_alignWarp.getId());
        Statement wf_sub_reslice = pFactory.newHasSubProgram(wf.getId(), prog_reslice.getId());
        Statement wf_sub_softmean = pFactory.newHasSubProgram(wf.getId(), prog_softmean.getId());
        Statement wf_sub_slicer = pFactory.newHasSubProgram(wf.getId(), prog_slicer.getId());
        Statement wf_sub_convert = pFactory.newHasSubProgram(wf.getId(), prog_convert.getId());

        // controlledBy
        Statement wfms_controls_wf = pFactory.newControls(wfms.getId(), wf.getId());
        
        // controls
        Statement wf_controlledBy_wfms = pFactory.newControlledBy(wf.getId(), wfms.getId());

        // hasDefaultParam
        //TODO set value
        Entity port_alignWarpIn5_defaultParam = pFactory.newEntity(qn("align-warp-m-default-param"), "align_warp_m_default_param");
        port_alignWarpIn5_defaultParam.setValue(pFactory.newValue("1 2"));
        Statement defparam_alignWarp = pFactory.newHasDefaultParam(port_alignWarpIn5.getId(), port_alignWarpIn5_defaultParam.getId());

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
        // align_warp output files
        Data warp_file1 =  pFactory.newData(qn("warp-file1"), "anatomy1.warp", "~/github/fMRI_snakemake/results/warp_files/anatomy1.warp",
                                                 "b891d3b87bcf8ec9c4d4e9f481468e9a2acfda255e61e7f72706a3aea29db293", null, null, null);        
        Data warp_file2 =  pFactory.newData(qn("warp-file2"), "anatomy2.warp", "~/github/fMRI_snakemake/results/warp_files/anatomy2.warp",
                                                 "c083a225db55828179cb92387f1fb75b963f5b013e4f762ef432030836fae7bc", null, null, null);
        Data warp_file3 =  pFactory.newData(qn("warp-file3"), "anatomy3.warp", "~/github/fMRI_snakemake/results/warp_files/anatomy3.warp",
                                                 "074f3ddfa1dd2b10c3736f32c716fe5a59871957c69966cd9f25d6760b5f9fea", null, null, null);
        Data warp_file4 =  pFactory.newData(qn("warp-file4"), "anatomy4.warp", "~/github/fMRI_snakemake/results/warp_files/anatomy4.warp",
                                                 "5ac417afc592ce434c2b236623c5145d4daac0c0c304af1a9baa974834524d88", null, null, null);
        // reslice output files
        Data anatomy_img1_resliced =  pFactory.newData(qn("anatomy-img1-resliced"), "anatomy1_resliced.img", "~/github/fMRI_snakemake/results/resliced_images/anatomy1_resliced.img",
                                                 "13b391af7ffaea0f8a6752e7c9a6dfddc4f2541eefb38f712912801fb9218887", null, null, null);        
        Data anatomy_hdr1_resliced =  pFactory.newData(qn("anatomy-hdr1-resliced"), "anatomy1_resliced.hdr", "~/github/fMRI_snakemake/results/resliced_images/anatomy1_resliced.hdr",
                                                 "0f9a1ed3db481499a4ec66947db970648fcdad303129a0821d8e1cb8d1b9d55d", null, null, null);
        Data anatomy_img2_resliced =  pFactory.newData(qn("anatomy-img2-resliced"), "anatomy2_resliced.img", "~/github/fMRI_snakemake/results/resliced_images/anatomy2_resliced.img",
                                                 "45288258afe701bd00dc1385217efb0fcd1806eff1ad2e278c5be9b421bb8c88", null, null, null);
        Data anatomy_hdr2_resliced =  pFactory.newData(qn("anatomy-hdr2-resliced"), "anatomy2_resliced.hdr", "~/github/fMRI_snakemake/results/resliced_images/anatomy2_resliced.hdr",
                                                 "0f9a1ed3db481499a4ec66947db970648fcdad303129a0821d8e1cb8d1b9d55d", null, null, null);
        Data anatomy_img3_resliced =  pFactory.newData(qn("anatomy-img3-resliced"), "anatomy3_resliced.img", "~/github/fMRI_snakemake/results/resliced_images/anatomy3_resliced.img",
                                                 "3184307ef781d7aad8b7d62110a39a525a96a97e61f30f762f15379cacc33def3", null, null, null);      
        Data anatomy_hdr3_resliced =  pFactory.newData(qn("anatomy-hdr3-resliced"), "anatomy3_resliced.hdr", "~/github/fMRI_snakemake/results/resliced_images/anatomy3_resliced.hdr",
                                                 "0f9a1ed3db481499a4ec66947db970648fcdad303129a0821d8e1cb8d1b9d55d", null, null, null);
        Data anatomy_img4_resliced =  pFactory.newData(qn("anatomy-img4-resliced"), "anatomy4_resliced.img", "~/github/fMRI_snakemake/results/resliced_images/anatomy4_resliced.img",
                                                 "707bb33cc601979ea66a582d409cb065b56862d0b61999eee6c3ba81a3187158", null, null, null);
        Data anatomy_hdr4_resliced =  pFactory.newData(qn("anatomy-hdr4-resliced"), "anatomy4_resliced.hdr", "~/github/fMRI_snakemake/results/resliced_images/anatomy4_resliced.hdr",
                                                 "0f9a1ed3db481499a4ec66947db970648fcdad303129a0821d8e1cb8d1b9d55d", null, null, null);        
        // softmean output files
        Data atlas_img =  pFactory.newData(qn("atlas-img"), "atlas.img", "~/github/fMRI_snakemake/results/softmean/atlas.img",
                                                 "73bf1d5e1cb5cc70477bd44cc927538b61193c8cd8e246c120a58018257b0552", null, null, null);
        Data atlas_hdr =  pFactory.newData(qn("atlas-hdr"), "atlas.hdr", "~/github/fMRI_snakemake/results/softmean/atlas.hdr",
                                                 "d381e02cd28085d1996729ae2de0ca760036993f4f6783806332486462c47835", null, null, null);           
        // slicer output files
        Data slicer_x =  pFactory.newData(qn("slicer-x"), "atlas_x.pgm", "~/github/fMRI_snakemake/results/slices/atlas_x.pgm",
                                                 "31499fcfc4e3100c46fb24f912cf8f3c008a36f80a277fde5e702f75e6cf38a8", null, null, null);
        Data slicer_y =  pFactory.newData(qn("slicer-y"), "atlas_y.pgm", "~/github/fMRI_snakemake/results/slices/atlas_y.pgm",
                                                 "54c8eee603bcfd86d7816f7a5e399fb5abd434540fdfe95b2f1f81da4a0cf47c", null, null, null);    
        Data slicer_z =  pFactory.newData(qn("slicer-z"), "atlas_z.pgm", "~/github/fMRI_snakemake/results/slices/atlas_z.pgm",
                                                 "5a5254e7c6ac1a91c76e881defe75a878ffb403f12173055afa5eb22a8e71275", null, null, null);
        // convert output files
        Data convert_x =  pFactory.newData(qn("convert-x"), "atlas_x.gif", "~/github/fMRI_snakemake/results/slices/atlas_x.gif",
                                                 "882fe0286e2fae0c5c1e9f3420bb7da6004a6900c96ef8f018c2c44a7c8b0a1a", null, null, null);
        Data convert_y =  pFactory.newData(qn("convert-y"), "atlas_y.gif", "~/github/fMRI_snakemake/results/slices/atlas_y.gif",
                                                 "a5ab1129f701f461fb54923563cd7cab9ba4071db1073144e3d51358dafd174d", null, null, null);    
        Data convert_z =  pFactory.newData(qn("convert-z"), "atlas_z.gif", "~/github/fMRI_snakemake/results/slices/atlas_z.gif",
                                                 "84f8d7c5580c15b6bf52ffe0cf8e1571041c4bc3eb4ce0978530a673d81a3828", null, null, null);

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
        Execution reslice_exe2 =   pFactory.newExecution(qn("reslice-exe2"),
                                                         pFactory.newISOTime("2023-08-21T05:44:06.461152"),
                                                         pFactory.newISOTime("2023-08-21T05:44:06.793149"),
                                                         "reslice_execution_2");
        Execution reslice_exe3 =   pFactory.newExecution(qn("reslice-exe3"),
                                                         pFactory.newISOTime("2023-08-21T05:44:06.137154"),
                                                         pFactory.newISOTime("2023-08-21T05:44:06.461152"),
                                                         "reslice_execution_3");
        Execution reslice_exe4 =   pFactory.newExecution(qn("reslice-exe4"),
                                                         pFactory.newISOTime("2023-08-21T05:44:06.797149"),
                                                         pFactory.newISOTime("2023-08-21T05:44:07.117147"),
                                                         "reslice_execution_4");
        Execution softmean_exe =   pFactory.newExecution(qn("softmean-exe"),
                                                         pFactory.newISOTime("2023-08-21T05:44:07.449145"),
                                                         pFactory.newISOTime("2023-08-21T05:44:08.525138"),
                                                         "softmean_execution");
        Execution slicer_exe =   pFactory.newExecution(qn("slicer-exe"),
                                                         pFactory.newISOTime("2023-08-21T05:44:08.525138"),
                                                         pFactory.newISOTime("2023-08-21T05:44:10.789124"),
                                                         "slicer_execution");
        Execution convert_exe =   pFactory.newExecution(qn("convert-exe"),
                                                         pFactory.newISOTime("2023-08-21T05:44:10.793124"),
                                                         pFactory.newISOTime("2023-08-21T05:44:10.821124"),
                                                         "convert execution");

        // User
        User felix = pFactory.newUser(qn("felix"), "felix", null, "Felix", "Bartusch",
                                       "felix.bartusch[at]uni-tuebingen.de", "https://fbartusch.github.io/",
                                       null, "0000-0003-0711-5196");

        // QualifiedAssociations: Relate an execution with a user and a plan (e.g. the program)
        //TODO
        WasAssociatedWith felix_qualAssoc_wf_exe = pFactory.newWasAssociatedWith(null, wf_exe.getId(), felix.getId(), wf.getId(), null);
        WasAssociatedWith felix_qualAssoc_alignWarp_exe1 = pFactory.newWasAssociatedWith(null, alignWarp_exe1.getId(), felix.getId(), wf.getId(), null);
        WasAssociatedWith felix_qualAssoc_alignWarp_exe2 = pFactory.newWasAssociatedWith(null, alignWarp_exe2.getId(), felix.getId(), prog_alignWarp.getId(), null);
        WasAssociatedWith felix_qualAssoc_alignWarp_exe3 = pFactory.newWasAssociatedWith(null, alignWarp_exe3.getId(), felix.getId(), prog_alignWarp.getId(), null);
        WasAssociatedWith felix_qualAssoc_alignWarp_exe4 = pFactory.newWasAssociatedWith(null, alignWarp_exe4.getId(), felix.getId(), prog_alignWarp.getId(), null);
        WasAssociatedWith felix_qualAssoc_reslice_exe1 = pFactory.newWasAssociatedWith(null, reslice_exe1.getId(), felix.getId(), prog_reslice.getId(), null);
        WasAssociatedWith felix_qualAssoc_reslice_exe2 = pFactory.newWasAssociatedWith(null, reslice_exe2.getId(), felix.getId(), prog_reslice.getId(), null);
        WasAssociatedWith felix_qualAssoc_reslice_exe3 = pFactory.newWasAssociatedWith(null, reslice_exe3.getId(), felix.getId(), prog_reslice.getId(), null);
        WasAssociatedWith felix_qualAssoc_reslice_exe4 = pFactory.newWasAssociatedWith(null, reslice_exe4.getId(), felix.getId(), prog_reslice.getId(), null);
        WasAssociatedWith felix_qualAssoc_softmean_exe = pFactory.newWasAssociatedWith(null, softmean_exe.getId(), felix.getId(), prog_softmean.getId(), null);
        WasAssociatedWith felix_qualAssoc_slicer_exe = pFactory.newWasAssociatedWith(null, slicer_exe.getId(), felix.getId(), prog_slicer.getId(), null);
        WasAssociatedWith felix_qualAssoc_convert_exe = pFactory.newWasAssociatedWith(null, convert_exe.getId(), felix.getId(), prog_convert.getId(), null);

        // Relate the single executions to the workflow execution
        Statement alignWarp_exe1_partOf_wf = pFactory.newWasPartOf(alignWarp_exe1.getId(), wf_exe.getId());
        Statement alignWarp_exe2_partOf_wf = pFactory.newWasPartOf(alignWarp_exe2.getId(), wf_exe.getId());
        Statement alignWarp_exe3_partOf_wf = pFactory.newWasPartOf(alignWarp_exe3.getId(), wf_exe.getId());
        Statement alignWarp_exe4_partOf_wf = pFactory.newWasPartOf(alignWarp_exe4.getId(), wf_exe.getId());
        Statement reslice_exe1_partOf_wf = pFactory.newWasPartOf(reslice_exe1.getId(), wf_exe.getId());
        Statement reslice_exe2_partOf_wf = pFactory.newWasPartOf(reslice_exe2.getId(), wf_exe.getId());
        Statement reslice_exe3_partOf_wf = pFactory.newWasPartOf(reslice_exe3.getId(), wf_exe.getId());
        Statement reslice_exe4_partOf_wf = pFactory.newWasPartOf(reslice_exe4.getId(), wf_exe.getId());
        Statement softmean_exe_partOf_wf = pFactory.newWasPartOf(softmean_exe.getId(), wf_exe.getId());
        Statement slicer_exe_partOf_wf = pFactory.newWasPartOf(slicer_exe.getId(), wf_exe.getId());
        Statement convert_exe_partOf_wf = pFactory.newWasPartOf(convert_exe.getId(), wf_exe.getId());

        //TODO: Should we use used and QualifiedUsage together? QualifiedUsage should make 'used' obsolete?
        // used
        Statement alignWarp_exe1_used_img1 = pFactory.newUsed(alignWarp_exe1.getId(), anatomy_img1.getId());
        Statement alignWarp_exe1_used_hdr1 = pFactory.newUsed(alignWarp_exe1.getId(), anatomy_hdr1.getId());
        Statement alignWarp_exe2_used_img2 = pFactory.newUsed(alignWarp_exe2.getId(), anatomy_img2.getId());
        Statement alignWarp_exe2_used_hdr2 = pFactory.newUsed(alignWarp_exe2.getId(), anatomy_hdr2.getId());
        Statement alignWarp_exe3_used_img3 = pFactory.newUsed(alignWarp_exe3.getId(), anatomy_img3.getId());
        Statement alignWarp_exe3_used_hdr3 = pFactory.newUsed(alignWarp_exe3.getId(), anatomy_hdr3.getId());
        Statement alignWarp_exe4_used_img4 = pFactory.newUsed(alignWarp_exe4.getId(), anatomy_img4.getId());
        Statement alignWarp_exe4_used_hdr4 = pFactory.newUsed(alignWarp_exe4.getId(), anatomy_hdr4.getId());
        Statement reslice_exe1_used_warp1 = pFactory.newUsed(reslice_exe1.getId(), warp_file1.getId());
        Statement reslice_exe2_used_warp2 = pFactory.newUsed(reslice_exe2.getId(), warp_file2.getId());
        Statement reslice_exe3_used_warp3 = pFactory.newUsed(reslice_exe3.getId(), warp_file3.getId());
        Statement reslice_exe4_used_warp4 = pFactory.newUsed(reslice_exe4.getId(), warp_file4.getId());
        Statement softmean_exe_used_res_img1 = pFactory.newUsed(softmean_exe.getId(), anatomy_img1_resliced.getId());
        Statement softmean_exe_used_res_hdr1 = pFactory.newUsed(softmean_exe.getId(), anatomy_hdr1_resliced.getId());
        Statement softmean_exe_used_res_img2 = pFactory.newUsed(softmean_exe.getId(), anatomy_img2_resliced.getId());
        Statement softmean_exe_used_res_hdr2 = pFactory.newUsed(softmean_exe.getId(), anatomy_hdr2_resliced.getId());
        Statement softmean_exe_used_res_img3 = pFactory.newUsed(softmean_exe.getId(), anatomy_img3_resliced.getId());
        Statement softmean_exe_used_res_hdr3 = pFactory.newUsed(softmean_exe.getId(), anatomy_hdr3_resliced.getId());
        Statement softmean_exe_used_res_img4 = pFactory.newUsed(softmean_exe.getId(), anatomy_img4_resliced.getId());
        Statement softmean_exe_used_res_hdr4 = pFactory.newUsed(softmean_exe.getId(), anatomy_hdr4_resliced.getId());
        Statement slicer_exe_used_atlas_img = pFactory.newUsed(slicer_exe.getId(), atlas_img.getId());
        Statement slicer_exe_used_atlas_hdr = pFactory.newUsed(slicer_exe.getId(), atlas_hdr.getId());
        Statement convert_exe_used_slice_x = pFactory.newUsed(convert_exe.getId(), slicer_x .getId());
        Statement convert_exe_used_slice_y = pFactory.newUsed(convert_exe.getId(), slicer_y .getId());
        Statement convert_exe_used_slice_z = pFactory.newUsed(convert_exe.getId(), slicer_z .getId());

        // Usage / Qualified Usage
        //Used alignWarp1_qualUsage_img1 = pFactory.newUsed(qn("usage1"), alignWarp_exe1.getId(), anatomy_img1.getId(), null, null);
 
        // wasGeneratedBy
        Statement warp1_genBy_alignWarp_exe1 = pFactory.newWasGeneratedBy(warp_file1, null, alignWarp_exe1);
        Statement warp2_genBy_alignWarp_exe2 = pFactory.newWasGeneratedBy(warp_file2, null, alignWarp_exe2);
        Statement warp3_genBy_alignWarp_exe3 = pFactory.newWasGeneratedBy(warp_file3, null, alignWarp_exe3);
        Statement warp4_genBy_alignWarp_exe4 = pFactory.newWasGeneratedBy(warp_file4, null, alignWarp_exe4);
        Statement img1_res_genBy_reslice_exe1 = pFactory.newWasGeneratedBy(anatomy_img1_resliced, null, reslice_exe1);
        Statement hdr1_res_genBy_reslice_exe1 = pFactory.newWasGeneratedBy(anatomy_hdr1_resliced, null, reslice_exe1);
        Statement img2_res_genBy_reslice_exe2 = pFactory.newWasGeneratedBy(anatomy_img2_resliced, null, reslice_exe2);
        Statement hdr2_res_genBy_reslice_exe2 = pFactory.newWasGeneratedBy(anatomy_hdr2_resliced, null, reslice_exe2);
        Statement img3_res_genBy_reslice_exe3 = pFactory.newWasGeneratedBy(anatomy_img3_resliced, null, reslice_exe3);
        Statement hdr3_res_genBy_reslice_exe3 = pFactory.newWasGeneratedBy(anatomy_hdr3_resliced, null, reslice_exe3);
        Statement img4_res_genBy_reslice_exe4 = pFactory.newWasGeneratedBy(anatomy_img4_resliced, null, reslice_exe4);
        Statement hdr4_res_genBy_reslice_exe4 = pFactory.newWasGeneratedBy(anatomy_hdr4_resliced, null, reslice_exe4);
        Statement atlas_img_genBy_softmean_exe = pFactory.newWasGeneratedBy(atlas_img, null, softmean_exe);
        Statement atlas_hdr_genBy_softmean_exe = pFactory.newWasGeneratedBy(atlas_hdr, null, softmean_exe);
        Statement slicer_x_genBy_slicer_exe = pFactory.newWasGeneratedBy(slicer_x, null, slicer_exe);
        Statement slicer_y_genBy_slicer_exe = pFactory.newWasGeneratedBy(slicer_y, null, slicer_exe);
        Statement slicer_z_genBy_slicer_exe = pFactory.newWasGeneratedBy(slicer_z, null, slicer_exe);
        Statement convert_x_genBy_convert_exe = pFactory.newWasGeneratedBy(convert_x, null, convert_exe);
        Statement convert_y_genBy_convert_exe = pFactory.newWasGeneratedBy(convert_y, null, convert_exe);
        Statement convert_z_genBy_convert_exe = pFactory.newWasGeneratedBy(convert_z, null, convert_exe);

        // wasInformedBy:  prov:wasInformedBy is adopted in ProvONE to state that an Execution communicates with another
        // Execution through an output-input relation, and thereby triggers its execution. 
        //Statement reslice_infBy_alignWarp_1 = pFactory.newWasInformedBy(null, reslice_exe1.getId(), alignWarp_exe1.getId());

        // hadEntity
        // From ProvOne documentation:
        // Through the use of the Usage and Generation classes, whenever an Entity item is sent from an output Port
        // to an input Port, this event is recorded through the hadEntity, hadInPort and hadOutPort properties between
        // the Entity item and the associated Ports. In this manner, the graph structure that represents the provenance
        // of the workflow results is generated. 
        //Statement usage1_hadEntity = pFactory.newHadEntity(alignWarp1_qualUsage_img1.getId(), anatomy_img1.getId());

        // hadInPort
        //Statement usage1_hadInPort = pFactory.newHadInPort(alignWarp1_qualUsage_img1.getId(), port_alignWarpIn1.getId());
        
        // Generation
        //WasGeneratedBy generation1 = pFactory.newWasGeneratedBy(qn("generation1"), warp1.getId(), alignWarp_exe1.getId(),
        //                                                                          pFactory.newISOTime("2023-08-21T05:44:05.361159"), null);
        // HadEntity
        //Statement generation1_hadEntity = pFactory.newHadEntity(generation1.getId(), warp1.getId());
        // hadOutPort
        //Statement generation1_hadOutPort = pFactory.newHadOutPort(generation1.getId(), port_alignWarpOut.getId());


        /*
         * Data Structure
         */

        // Visualization: This is just for testing and doesn't belong to the workflow
        //Visualization visTest = pFactory.newVisualization(qn("visTest"), "TestVisualization");

        // Document: This is just for testing and doesn't belong to the workflow
        //org.provtools.provone.vanilla.Document docTest = pFactory.newDocument(qn("docTest"), "TestDocument");

        // wasDerivedFrom: This is just for testing and doesn't belong to the workflow
        //WasDerivedFrom wdfTest = pFactory.newWasDerivedFrom(visTest.getId(), docTest.getId());

        // Lists of all elements in the document
        List<Program> programs = Arrays.asList(prog_alignWarp, prog_reslice, prog_softmean, prog_slicer, prog_convert);
        List<Port> ports = Arrays.asList(port_alignWarpIn1, port_alignWarpIn2, port_alignWarpIn3, port_alignWarpIn4, port_alignWarpIn5, 
                                         port_alignWarpOut, port_reslice_in, port_reslice_out_img, port_reslice_out_hdr, port_softmean_in_img,
                                         port_softmean_in_hdr, port_softmean_out_img, port_softmean_out_hdr, port_slicer_in_img, port_slicer_in_hdr,
                                         port_slicer_out, port_convert_in, port_convert_out);
        List<Channel> channels = Arrays.asList(ch_alignWarpOut_resliceIn, ch_resliceOutImg_softmeanInImg, ch_resliceOutHdr_softmeanInHdr,
                                               ch_softmeanOutImg_slicerInImg, ch_softmeanOutHdr_slicerInHdr, ch_slicerOut_convertIn);
        List<Controller> controller = Arrays.asList(wfms);
        List<Workflow> workflows = Arrays.asList(wf, original_wf);
        List<Execution> executions = Arrays.asList(wf_exe, alignWarp_exe1, alignWarp_exe2, alignWarp_exe3, alignWarp_exe4, reslice_exe2, reslice_exe3,
                                                   reslice_exe4, softmean_exe, slicer_exe, convert_exe);
        List<User> user = Arrays.asList(felix);
        List<Data> data = Arrays.asList(anatomy_img1, anatomy_hdr1, anatomy_img2, anatomy_hdr2, anatomy_img3, anatomy_hdr3, anatomy_img4,
                                        anatomy_hdr4, reference_img, reference_hdr, warp_file1, warp_file2, warp_file3, warp_file4,
                                        anatomy_img1_resliced, anatomy_hdr1_resliced, anatomy_img2_resliced, anatomy_hdr2_resliced,
                                        anatomy_img3_resliced, anatomy_hdr3_resliced, anatomy_img4_resliced, anatomy_hdr4_resliced,
                                        atlas_img, atlas_hdr, slicer_x, slicer_y, slicer_z, convert_x, convert_y, convert_z);
        //List<Visualization> visualizations = Arrays.asList(visTest);
        //List<org.provtools.provone.vanilla.Document> documents = Arrays.asList(docTest);
        List<Entity> entities = Arrays.asList(port_alignWarpIn5_defaultParam, inputCollection);
        List<HadMember> hadMembers = Arrays.asList(inputCollectionHadMember);

        //List<Activity> activities = Arrays.asList();
        //List<Agent> agents = Arrays.asList();
        List<Statement> statements = Arrays.asList(alignWarp_hasInPort1, alignWarp_hasInPort2, alignWarp_hasInPort3,
            alignWarp_hasInPort4, alignWarp_hasInPort5, alignWarp_hasOutPort, reslice_hasInPort, reslice_hasOutPort1,
            reslice_hasOutPort2, softmean_hasInPort1, softmean_hasInPort2, softmean_hasOutPort1, softmean_hasOutPort2,
            slicer_hasInPort1, slicer_hasInPort2, slicer_hasOutPort, convert_hasInPort, convert_hasOutPort, con_alignWarpOut,
            con_resliceIn, con_reslice_out_img, con_softmean_in_img, con_reslice_out_hdr, con_softmean_in_hdr,
            con_softmean_out_img, con_slicer_in_img, con_softmean_out_hdr, con_slicer_in_hdr, con_slicer_out, con_convert_in,
            wf_sub_alignWarp, wf_sub_reslice, wf_sub_softmean, wf_sub_slicer, wf_sub_convert, defparam_alignWarp,
            wf_wasDerivedFrom_orig, wfms_controls_wf, wf_controlledBy_wfms, alignWarp_exe1_partOf_wf, alignWarp_exe2_partOf_wf,
            alignWarp_exe3_partOf_wf, alignWarp_exe4_partOf_wf, reslice_exe1_partOf_wf, reslice_exe2_partOf_wf,
            reslice_exe3_partOf_wf, reslice_exe4_partOf_wf, softmean_exe_partOf_wf, slicer_exe_partOf_wf, convert_exe_partOf_wf,
            felix_qualAssoc_wf_exe, alignWarp_exe1_used_img1, alignWarp_exe1_used_hdr1, alignWarp_exe2_used_img2,
            alignWarp_exe2_used_hdr2, alignWarp_exe3_used_img3, alignWarp_exe3_used_hdr3, alignWarp_exe4_used_img4,
            alignWarp_exe4_used_hdr4, reslice_exe1_used_warp1, reslice_exe2_used_warp2, reslice_exe3_used_warp3,
            reslice_exe4_used_warp4, softmean_exe_used_res_img1, softmean_exe_used_res_hdr1, softmean_exe_used_res_img2,
            softmean_exe_used_res_hdr2, softmean_exe_used_res_img3, softmean_exe_used_res_hdr3, softmean_exe_used_res_img4,
            softmean_exe_used_res_hdr4, slicer_exe_used_atlas_img, slicer_exe_used_atlas_hdr, convert_exe_used_slice_x,
            convert_exe_used_slice_y, convert_exe_used_slice_z, felix_qualAssoc_wf_exe, felix_qualAssoc_alignWarp_exe1,
            felix_qualAssoc_alignWarp_exe2, felix_qualAssoc_alignWarp_exe3, felix_qualAssoc_alignWarp_exe4,
            felix_qualAssoc_reslice_exe1, felix_qualAssoc_reslice_exe2, felix_qualAssoc_reslice_exe3, felix_qualAssoc_reslice_exe4,
            felix_qualAssoc_softmean_exe, felix_qualAssoc_slicer_exe, felix_qualAssoc_convert_exe, warp1_genBy_alignWarp_exe1,
            warp2_genBy_alignWarp_exe2, warp3_genBy_alignWarp_exe3, warp4_genBy_alignWarp_exe4, img1_res_genBy_reslice_exe1,
            hdr1_res_genBy_reslice_exe1, img2_res_genBy_reslice_exe2, hdr2_res_genBy_reslice_exe2, img3_res_genBy_reslice_exe3,
            hdr3_res_genBy_reslice_exe3, img4_res_genBy_reslice_exe4, hdr4_res_genBy_reslice_exe4, atlas_img_genBy_softmean_exe,
            atlas_hdr_genBy_softmean_exe, slicer_x_genBy_slicer_exe, slicer_y_genBy_slicer_exe, slicer_z_genBy_slicer_exe,
            convert_x_genBy_convert_exe, convert_y_genBy_convert_exe, convert_z_genBy_convert_exe);

        Document document = pFactory.newDocument();
        document.getStatementOrBundle().addAll(programs);
        document.getStatementOrBundle().addAll(ports);
        document.getStatementOrBundle().addAll(channels);
        document.getStatementOrBundle().addAll(controller);
        document.getStatementOrBundle().addAll(workflows);
        document.getStatementOrBundle().addAll(executions);
        document.getStatementOrBundle().addAll(user);
        document.getStatementOrBundle().addAll(data);
        //document.getStatementOrBundle().addAll(visualizations);
        //document.getStatementOrBundle().addAll(documents);
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