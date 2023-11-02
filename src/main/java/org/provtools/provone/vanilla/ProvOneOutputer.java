package org.provtools.provone.vanilla;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.openprovenance.prov.interop.Formats;
import org.openprovenance.prov.interop.Formats.ProvFormat;
import org.openprovenance.prov.interop.InteropException;
import org.openprovenance.prov.interop.InteropFramework;
import org.openprovenance.prov.interop.Outputer;
import org.openprovenance.prov.interop.SerializerFunction;
import org.openprovenance.prov.model.Document;
import org.openprovenance.prov.model.ProvFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.openprovenance.prov.notation.ProvSerialiser;

/*
 * Outputer for ProvOne documents.
 * 
 * Allows setting custom Serializers that can serialize ProvOne elements.
 */

public class ProvOneOutputer extends Outputer {

    private final InteropFramework interopFramework;
    private final ProvFactory pFactory;
    final private Map<Formats.ProvFormat, SerializerFunction> serializerMap;
    Integer maxStringLength = 100;

    public ProvOneOutputer(InteropFramework interopFramework, ProvFactory pFactory) {
        super(interopFramework, pFactory);
        this.interopFramework = interopFramework;
        this.pFactory=pFactory;
        this.serializerMap=createSerializerMap2();
    }

    final public Map<Formats.ProvFormat, SerializerFunction> createSerializerMap2() {

        //NOTE: Syntax restricted to 10 entries
        Map<Formats.ProvFormat, SerializerFunction> serializer = new HashMap<>();
        // TODO Add ProvOne serielizers when they were implemented
        serializer.putAll(
                Map.of( ProvFormat.PROVN,  () -> new ProvSerialiser(pFactory),
                        ProvFormat.PROVX,  () -> new org.openprovenance.prov.core.xml.serialization.ProvSerialiser(true),
                        ProvFormat.JSONLD, () -> new org.openprovenance.prov.core.jsonld11.serialization.ProvSerialiser(new ObjectMapper(), false),
                        //ProvFormat.JSON,   org.openprovenance.prov.core.json.serialization.ProvSerialiser::new,
                        ProvFormat.JSON,   () -> new org.provtools.provone.vanilla.ProvOneJSONSerialiser(),
                        ProvFormat.TURTLE, () -> { throw new UnsupportedOperationException("light turtle converter not integrated yet"); },
                        ProvFormat.TRIG,   () -> { throw new UnsupportedOperationException("light turtle converter not integrated yet"); }
                ));

        serializer.putAll(
                Map.of(ProvFormat.JPEG, () -> new org.openprovenance.prov.dot.ProvSerialiser(pFactory, interopFramework.getExtensionMap().get(ProvFormat.JPEG), maxStringLength),
                        ProvFormat.SVG, () -> new org.openprovenance.prov.dot.ProvSerialiser(pFactory, interopFramework.getExtensionMap().get(ProvFormat.SVG),  maxStringLength),
                        ProvFormat.PDF, () -> new org.openprovenance.prov.dot.ProvSerialiser(pFactory, interopFramework.getExtensionMap().get(ProvFormat.PDF),  maxStringLength),
                        ProvFormat.PNG, () -> new org.openprovenance.prov.dot.ProvSerialiser(pFactory, interopFramework.getExtensionMap().get(ProvFormat.PNG),  maxStringLength),
                        ProvFormat.DOT, () -> new org.openprovenance.prov.dot.ProvSerialiser(pFactory, interopFramework.getExtensionMap().get(ProvFormat.DOT),  maxStringLength)
                ));

        return serializer;
    }

    @Override
    public void writeDocument(OutputStream out, Document document, String mediaType, boolean formatted) {
        Formats.ProvFormat format = interopFramework.getMimeTypeRevMap().get(mediaType);
        if (format == null) {
            throw new InteropException("InteropFramework(): serialisedDocument unknown mediatype " + mediaType);
        }
        SerializerFunction serializerMaker = serializerMap.get(format);
        //InteropFramework.logger.debug("serializer " + format + " " + serializerMaker);
        serializerMaker.apply().serialiseDocument(out, document, formatted);
    }    
}
