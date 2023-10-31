package org.provtools.provone.vanilla;

import java.io.OutputStream;
import java.util.Collection;

import org.openprovenance.prov.interop.InteropMediaType;
import org.openprovenance.prov.model.Document;

/*
 * A generic interop framework
 * 
 * For Serializing ProvOneDocuments we will need a special Outputer whose serializerMaps
 * contains ProvOneSerializers for the output formats.
 * This interop frameworks should be generic, e.g. allows setting custom Outputer implementations.
 */

public class GenericInteropFramework implements InteropMediaType, org.openprovenance.prov.model.ProvDocumentWriter {

    @Override
    public void writeDocument(OutputStream out, Document document, String mediaType, boolean formatted) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeDocument'");
    }

    @Override
    public Collection<String> mediaTypes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mediaTypes'");
    }

}
