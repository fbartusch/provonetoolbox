package org.openprovenance.prov.interopt;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;

import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.openprovenance.prov.interop.GenericInteropFramework;
import org.openprovenance.prov.interop.InteropFramework;
import org.openprovenance.prov.model.Document;
import org.openprovenance.prov.model.StatementOrBundle;
import org.openprovenance.prov.model.StatementOrBundle.Kind;
import org.openprovenance.prov.vanilla.Entity;
import org.provtools.provone.vanilla.FMRI;
import org.provtools.provone.vanilla.ProvOneFactory;

public class GenericInteropFrameworkTest {

    @TempDir
    static Path tmpDir;

    @Test
    @DisplayName("Serialize Document into JSON")
    void testSerializeModelasJSON() throws URISyntaxException, FileNotFoundException, IOException, ConfigurationException {

        ProvOneFactory pFactory = new ProvOneFactory();

        // This is a class that creates a model of an example workflow
        FMRI fmri = new FMRI();
        Document doc = fmri.makeDocument();

        Path outFile = tmpDir.resolve("serialization_test.json");
        String outFilePath = outFile.toString();

        // Serialize
        InteropFramework intF = new GenericInteropFramework(pFactory);
        intF.writeDocument(outFilePath, doc); 
    }


    @Test
    @DisplayName("Serialize Document into JSON-LD")
    void testSerializeModelasJSONLD() throws URISyntaxException, FileNotFoundException, IOException, ConfigurationException {
        ProvOneFactory pFactory = new ProvOneFactory();

        // This is a class that creates a model of an example workflow
        FMRI fmri = new FMRI();
        Document doc = fmri.makeDocument();

        Path outFile = tmpDir.resolve("serialization_test.jsonld");
        String outFilePath = outFile.toString();

        // Serialize
        InteropFramework intF = new GenericInteropFramework(pFactory);
        intF.writeDocument(outFilePath, doc); 
    }

    @Test
    @DisplayName("Deserialize from JSON")
    void testDeserializeModelFromJSON() throws URISyntaxException, FileNotFoundException, IOException, ConfigurationException {
        ProvOneFactory pFactory = new ProvOneFactory();

        // The ProvONE document for testing
        ClassLoader classLoader = getClass().getClassLoader();
        Path file = Path.of(classLoader.getResource("fmri_provenance.json").toURI());
        
        // Read the document that should be converted
        InteropFramework intF = new GenericInteropFramework(pFactory);
        Document doc = intF.readDocumentFromFile(file.toString());

        // Check if attributes in the converted Jena RDF model and the original ProvONE document matches
        // We cannot search statements, so we have to iterate over it ...
        // Just make sure that some elements are present and deserialization worked.
        String label = "";
        for (StatementOrBundle statement : doc.getStatementOrBundle()) {
            if (statement.getKind() == Kind.PROV_ENTITY) {
                Entity e = (Entity) statement;
                if ("anatomy4.img".equals(e.getLabel().get(0).getValue())) {
                    label = e.getLabel().get(0).getValue();
                }
            }
        }

        assertEquals("anatomy4.img", label);
    }

    @Test
    @DisplayName("Deserialize from JSONLD")
    void testDeserializeModelFromJSONLD() throws URISyntaxException, FileNotFoundException, IOException, ConfigurationException {
        ProvOneFactory pFactory = new ProvOneFactory();

        // The ProvONE document for testing
        ClassLoader classLoader = getClass().getClassLoader();
        Path file = Path.of(classLoader.getResource("fmri_provenance.jsonld").toURI());
        Path file2 = Path.of(classLoader.getResource("fmri_provenance.json").toURI());
        
        // Read the document that should be converted
        InteropFramework intF = new GenericInteropFramework(pFactory);
        // TODO Find bug and fix this
        Document doc2 = intF.readDocumentFromFile(file2.toString());
        Document doc = intF.readDocumentFromFile(file.toString());

        // Check if attributes in the converted Jena RDF model and the original ProvONE document matches
        // We cannot search statements, so we have to iterate over it ...
        // Just make sure that some elements are present and deserialization worked.
        String label = "";
        for (StatementOrBundle statement : doc.getStatementOrBundle()) {
            if (statement.getKind() == Kind.PROV_ENTITY) {
                Entity e = (Entity) statement;
                if ("anatomy4.img".equals(e.getLabel().get(0).getValue())) {
                    label = e.getLabel().get(0).getValue();
                }
            }
        }

        assertEquals("anatomy4.img", label);
    }
}