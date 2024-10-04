package org.openprovenance.prov.interopt;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;

import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.jena.query.Dataset;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.impl.PropertyImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openprovenance.prov.interop.ApacheJenaInterop;
import org.openprovenance.prov.interop.GenericInteropFramework;
import org.openprovenance.prov.interop.InteropFramework;
import org.openprovenance.prov.model.Document;
import org.provtools.provone.vanilla.ProvOneFactory;

public class ApacheJenaInteropTest {

    @Test
    @DisplayName("Create an Jena RDF model from a ProvONE document")
    void testCreateJenaModel() throws URISyntaxException, FileNotFoundException, IOException, ConfigurationException {

        ProvOneFactory pFactory = new ProvOneFactory();

        // The ProvONE document for testing
        ClassLoader classLoader = getClass().getClassLoader();
        Path file = Path.of(classLoader.getResource("fmri_provenance.json").toURI());
        // Read the document that should be converted
        InteropFramework intF = new GenericInteropFramework(pFactory);
        Document doc = intF.readDocumentFromFile(file.toString());

        // Convert the document to an Jena RDF model
        ApacheJenaInterop converter = new ApacheJenaInterop(pFactory);
        String graphURI = "http://example.com/testGraph";
        Dataset ds = converter.createJenaDataset(doc, graphURI);

        // Check if attributes in the converted Jena RDF model and the original ProvONE document matches
        String label  = ds.getNamedModel(graphURI).getResource("fmri:anatomy-img4").getProperty(new PropertyImpl("prov:label")).getString();
        String sha256  = ds.getNamedModel(graphURI).getResource("fmri:anatomy-img4").getProperty(new PropertyImpl("schema:sha256")).getString();
        assertEquals("anatomy4.img", label);
        assertEquals("6b549bd112d865e7cfb0d4309b46987517ec7623832d78a79f2229fc6b24dddf", sha256);
    }
}