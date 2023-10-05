package org.provtools.provone.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProvOneNamespaceTest {

    @Test
    @DisplayName("Custom test name containing spaces")
    void testConstructorProvOneNamespace() {
        ProvOneNamespace ns = new ProvOneNamespace();
        // There should be 3 namespaces configured
        assertEquals(3, ns.getPrefixes().size());
    }
} 
