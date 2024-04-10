package org.openprovenance.prov.interopt;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;

import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.jena.rdf.model.Model;
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
        Path file = Path.of(classLoader.getResource("fmri_provenance.jsonld").toURI());
        
        // Read the document that should be converted
        InteropFramework intF = new GenericInteropFramework(pFactory);
        System.out.println(file.toString());
        Document doc = intF.readDocumentFromFile("/home/felix/github/provonetoolbox/fmri_provenance.jsonld");

        // Convert the document to an Jena RDF model
        //ApacheJenaInterop converter = new ApacheJenaInterop(pFactory);
        //Model m = converter.createJenaModel(doc);

        //TODO Check if attributes in the converted Jena RDF model and the original ProvONE document matches
        //assertEquals("'exa:{{https://example.com/}}0000-0003-0711-5196'", testUser.getId().toString());
    }

}
