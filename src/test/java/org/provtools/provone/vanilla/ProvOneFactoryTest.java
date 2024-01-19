package org.provtools.provone.vanilla;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;

import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProvOneFactoryTest {

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
}
