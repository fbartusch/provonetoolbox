package org.provtools.provone.vanilla;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openprovenance.prov.model.QualifiedName;

public class ProvOneFactoryTest {

    ProvOneFactory pFactory = new ProvOneFactory();

    QualifiedName getMockID() {
        return pFactory.newQualifiedName("https://example.com", "test", "ex");
    }

    @Test
    @DisplayName("newUser from INI file")
    void testNewUserFromINI() throws URISyntaxException, FileNotFoundException, IOException, ConfigurationException {
        ProvOneFactory pFactory = new ProvOneFactory();

        // The INI file for testing
        ClassLoader classLoader = getClass().getClassLoader();
        Path file = Path.of(classLoader.getResource("user.ini").toURI());
        
        // Create user from INI file
        User testUser = pFactory.newUser(file, "https://example.com/", "exa");

        // Check if Orcid is part of ID
        assertEquals("'exa:{{https://example.com/}}0000-0003-0711-5196'", testUser.getId().toString());
    }


    @Test
    @DisplayName("newWorkflow: Nextflow factory method")
    void testNewWorkflow1() {
        ProvOneFactory pFactory = new ProvOneFactory();

        // Create a workflow using some real data from the Nextflow plugin. Repository,commitID,and revision won't work in real life.
        Workflow testWorkflow = pFactory.newWorkflow("6c520db86010b45d517f2e7dad93eb34", "main.nf",
                                        "/home/felix/github/nf-provone/plugins/nf-provone/src/testResources/main.nf",
                                        "https://github.com/fbartusch/nextflow-example-workflows",
                                        "32ed9dd28a978d73f5b05ad868982017e49edd5d",
                                        "master");
        // 
        assertEquals("'exa:{{https://example.org/}}6c520db86010b45d517f2e7dad93eb34'", testWorkflow.getId().toString());
    }


    @Test
    @DisplayName("newExecution: Convert from OffsetDateTime to XMLGregorianCalendar")
    void testNewExecution1() {
        ProvOneFactory pFactory = new ProvOneFactory();

        OffsetDateTime startTime = OffsetDateTime.parse("2024-01-23T07:32:14.832378232+01:00");
        Execution testExe = pFactory.newExecution(getMockID(), startTime, null, "test");

        assertInstanceOf(Execution.class, testExe);
    }
}
