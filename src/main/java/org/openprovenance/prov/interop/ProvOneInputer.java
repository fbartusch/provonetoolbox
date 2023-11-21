package org.openprovenance.prov.interop;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.openprovenance.prov.interop.DeserializerFunction;
import org.openprovenance.prov.interop.DeserializerFunction2;
import org.openprovenance.prov.interop.Formats;
import org.openprovenance.prov.interop.Formats.ProvFormat;
import org.openprovenance.prov.interop.Inputer;
import org.openprovenance.prov.interop.InteropException;
import org.openprovenance.prov.interop.InteropFramework;
import org.openprovenance.prov.model.DateTimeOption;
import org.openprovenance.prov.model.ProvFactory;
import org.provtools.provone.vanilla.Document;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.openprovenance.prov.interop.Formats.ProvFormat.*;


/*
 * Inputer for ProvOne documents.
 * 
 * Allows setting custom Serializers that can deserialize ProvOne elements.
 */

public class ProvOneInputer extends Inputer {

    private final InteropFramework interopFramework;
    static String SEPARATOR = ",";
    private final ProvFactory pFactory;
    final Map<Formats.ProvFormat, DeserializerFunction> deserializerMap;
    final Map<Formats.ProvFormat, DeserializerFunction2> deserializerMap2;
    List<Formats.ProvFormat> preferredOrder = List.of(PROVN, JSONLD, JSON, PROVX);

    public ProvOneInputer(InteropFramework interopFramework, ProvFactory pFactory) {
        super(interopFramework, pFactory);
        
        this.interopFramework = interopFramework;
        this.pFactory = pFactory;
        // not visible, private:
        // this.deserializerMap = createDeserializerMap();
        this.deserializerMap = this.createDeserializerMap();
        // We need our own deserializers
        // deserializerMap2 = createDeserializerMap2();
        // The mapping to ProvONE Deserializers
        this.deserializerMap2 = createDeserializerMap3();
    }

    public Map<Formats.ProvFormat, DeserializerFunction> createDeserializerMap() {

        //NOTE: Syntax restricted to 10 entries
        Map<Formats.ProvFormat, DeserializerFunction> deserializer = new HashMap<Formats.ProvFormat, DeserializerFunction>();
        deserializer.putAll(
                Map.of(//PROVN, () -> new ProvDeserialiser(pFactory, interopFramework.getConfig().dateTime, interopFramework.getConfig().timeZone),
                       //PROVX, () -> new org.openprovenance.prov.core.xml.serialization.ProvDeserialiser(interopFramework.getConfig().dateTime, interopFramework.getConfig().timeZone),
                       //JSONLD, () -> new org.openprovenance.prov.core.jsonld11.serialization.ProvDeserialiser(new ObjectMapper(), interopFramework.getConfig().dateTime, interopFramework.getConfig().timeZone),
                       JSON, () -> new org.provtools.provone.vanilla.ProvOneJSONDeserialiser(new ObjectMapper(), interopFramework.getConfig().dateTime, interopFramework.getConfig().timeZone))
        );

        return deserializer;
    }

    final public Map<Formats.ProvFormat, DeserializerFunction2> createDeserializerMap3() {

        //NOTE: Syntax restricted to 10 entries
        Map<Formats.ProvFormat, DeserializerFunction2> deserializer = new HashMap<Formats.ProvFormat, DeserializerFunction2>();
        deserializer.putAll(
                Map.of(//PROVN, (DateTimeOption dateTime, TimeZone timeZone) -> new ProvDeserialiser(pFactory, dateTime, timeZone),
                       //PROVX, (DateTimeOption dateTime, TimeZone timeZone) -> new org.openprovenance.prov.core.xml.serialization.ProvDeserialiser(dateTime, timeZone),
                       //JSONLD, (DateTimeOption dateTime, TimeZone timeZone) -> new org.openprovenance.prov.core.jsonld11.serialization.ProvDeserialiser(new ObjectMapper(), dateTime, timeZone),
                       //JSON, (DateTimeOption dateTime, TimeZone timeZone) -> new org.openprovenance.prov.core.json.serialization.ProvDeserialiser(new ObjectMapper(), dateTime, timeZone)),
                       JSON, (DateTimeOption dateTime, TimeZone timeZone) -> new org.provtools.provone.vanilla.ProvOneJSONDeserialiser(new ObjectMapper(), dateTime, timeZone))
        );

        return deserializer;
    }

    @Override
    org.openprovenance.prov.model.Document readDocumentFromFile(String filename) {
        Formats.ProvFormat format = interopFramework.getTypeForFile(filename);
        if (format == null) {
            throw new InteropException("Unknown output file format: " + filename);
        }
        try {
            return deserialiseDocument(Files.newInputStream(Paths.get(filename)), format);
        } catch (IOException e) {
            throw new InteropException(e);
        }
    }

    @Override
        org.openprovenance.prov.model.Document deserialiseDocument(InputStream is, Formats.ProvFormat format) throws IOException {
        DeserializerFunction deserializer = this.interopFramework.getDeserializerMap().get(format);
        logger.debug("deserializer " + format + " " + deserializer);
        return deserializer.apply().deserialiseDocument(is);
    }
}
