package org.provtools.provone.vanilla;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.NoSuchFileException;
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

    @Test
    @DisplayName("newData: File exists")
    void testNewData1() throws URISyntaxException {

        ProvOneFactory pFactory = new ProvOneFactory();

        // The test file
        ClassLoader classLoader = getClass().getClassLoader();
        Path file = Path.of(classLoader.getResource("sha256_testfile.txt").toURI());

        Data data = null;
        try {
            data = pFactory.newData(file, "https://example.org/", "ex");
        } catch (NoSuchFileException e) {
            e.printStackTrace();
        }

        // Test checksum
        assertEquals("9b3946c9f114a64c65571a81437f7706cdfffaac47be4bd29784c8473b0c0a86", data.getSha256());

        // Test ID: We can test for the path part, as this depends where the ProvONE toolbox is located on the file system
        // Therefore just test for the sha256 part of the id ...
        assertEquals("'ex:{{https://example.org/}}9b3946c", data.getId().toString().split("_")[0]);
    }

    @Test
    @DisplayName("newData: Path is directory, not a file")
    void testNewData2() throws URISyntaxException {

        ProvOneFactory pFactory = new ProvOneFactory();

        ClassLoader classLoader = getClass().getClassLoader();
        Path file = Path.of(classLoader.getResource("sha256_testfile.txt").toURI());
        Path dir = file.getParent();

        Exception exception = assertThrows(NoSuchFileException.class, () -> {
            pFactory.newData(dir, "https://example.org/", "ex");
        });

        String expectedMessage = "Path is a directory, file expected: " + dir.toString();
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("newData: File does not exist")
    void testNewData3() throws URISyntaxException {
        
        ProvOneFactory pFactory = new ProvOneFactory();

        Path file = Path.of("/this/file/does/not/exist.txt");

        Exception exception = assertThrows(NoSuchFileException.class, () -> {
            pFactory.newData(file, "https://example.org/", "ex");
        });

        String expectedMessage = "File does not exist: " + file.toString();
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
