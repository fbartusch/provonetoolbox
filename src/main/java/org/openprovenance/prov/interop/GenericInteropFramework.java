//package org.provtools.provone.vanilla;
package org.openprovenance.prov.interop;

import static org.openprovenance.prov.interop.Formats.ProvFormat.JSON;
import static org.openprovenance.prov.interop.Formats.ProvFormat.JSONLD;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.openprovenance.prov.interop.CommandLineArguments;
import org.openprovenance.prov.interop.Formats.ProvFormat;
import org.openprovenance.prov.interop.Inputer;
import org.openprovenance.prov.interop.InteropException;
import org.openprovenance.prov.interop.InteropFramework;
import org.openprovenance.prov.interop.Outputer;
import org.openprovenance.prov.model.Document;
import org.openprovenance.prov.model.ProvFactory;
import org.provtools.provone.vanilla.ProvOneOutputer;

import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * A generic interop framework
 * 
 * For Serializing ProvOneDocuments we will need a special Outputer whose serializerMaps
 * contains ProvOneSerializers for the output formats.
 * This interop frameworks should be generic, e.g. allows setting custom Outputer implementations.
 */

//public class GenericInteropFramework implements InteropMediaType, org.openprovenance.prov.model.ProvDocumentWriter {
public class GenericInteropFramework extends InteropFramework {
    
    // We need more control over outputer and inputer
    private Outputer outputer;
    private Inputer inputer;
    // And also more control over the deserializers ...
    final private Map<ProvFormat, DeserializerFunction> deserializerMap;
    final private Map<ProvFormat, DeserializerFunction2> deserializerMap2;

     public GenericInteropFramework() {
        this(new CommandLineArguments(), defaultFactory, null, null);
    }

    public GenericInteropFramework(ProvFactory pFactory) {
        this(new CommandLineArguments(), pFactory, null, null);
    }

    public GenericInteropFramework(CommandLineArguments config, ProvFactory pFactory, Outputer outputer, Inputer inputer) {
        super(config, pFactory);

        if (outputer == null) {
            this.outputer = new ProvOneOutputer(this, pFactory);
        } else {
            this.outputer = outputer;
        }

        if (inputer == null) {
            this.inputer = new ProvOneInputer(this, pFactory);
        } else {
            this.inputer = inputer;
        }

        this.deserializerMap = createDeserializerMap();
        this.deserializerMap2 = this.inputer.deserializerMap2;
    }


    //TODO Ugly hack because we don't get the correct DeserializerMap otherwise?
    // Inputer/ProvOneInputer nevern returned the map with the ProvOne Deserializers
    public Map<Formats.ProvFormat, DeserializerFunction> createDeserializerMap() {

        //NOTE: Syntax restricted to 10 entries
        Map<Formats.ProvFormat, DeserializerFunction> deserializer = new HashMap<Formats.ProvFormat, DeserializerFunction>();
        deserializer.putAll(
                Map.of(//PROVN, () -> new ProvDeserialiser(pFactory, interopFramework.getConfig().dateTime, interopFramework.getConfig().timeZone),
                       //PROVX, () -> new org.openprovenance.prov.core.xml.serialization.ProvDeserialiser(interopFramework.getConfig().dateTime, interopFramework.getConfig().timeZone),
                       //JSONLD, () -> new org.openprovenance.prov.core.jsonld11.serialization.ProvDeserialiser(new ObjectMapper(), this.getConfig().dateTime, this.getConfig().timeZone),
                       JSONLD, () -> new org.openprovenance.prov.core.jsonld.serialization.ProvOneDeserialiser(new ObjectMapper(), this.getConfig().dateTime, this.getConfig().timeZone),
                       JSON, () -> new org.provtools.provone.vanilla.ProvOneJSONDeserialiser(new ObjectMapper(), this.getConfig().dateTime, this.getConfig().timeZone))
        );

        return deserializer;
    }

    /**
     * Write a {@link Document} to file, serialized according to the file extension. If extension is not known, throws an exception.
     * @param filename path of the file to write the Document to
     * @param document a {@link Document} to serialize
     * @throws InteropException if the extension of the file is not known
     */
    @Override
    public void writeDocument(String filename, Document document) {
        this.outputer.writeDocument(filename, document);
    }


    /**
     * Write a {@link Document} to file, serialized according to format {@link ProvFormat}
     *
     * @param filename path of the file to write the Document to
     * @param document a {@link Document} to serialize
     * @param format   a {@link ProvFormat} to serialize the document to
     */
    @Override
    public void writeDocument(String filename, Document document, ProvFormat format) {
        this.outputer.writeDocument(filename, document, format);
    }


    /**
     * Write a {@link Document} to output stream, according to specified {@link ProvFormat}
     *
     * @param os       an {@link OutputStream} to write the Document to
     * @param document a {@link Document} to serialize
     * @param format   a {@link ProvFormat}
     */
    @Override
    public void writeDocument(OutputStream os, Document document, ProvFormat format) {
        this.outputer.writeDocument(os, document, format);
    }

    /*
     * (non-Javadoc)
     * @see org.openprovenance.prov.model.ProvDocumentWriter#writeDocument(java.io.OutputStream, org.openprovenance.prov.model.Document, java.lang.String, boolean)
     */
    @Override
    public void writeDocument(OutputStream out, Document document, String mediaType, boolean formatted) {
        this.outputer.writeDocument(out, document, mediaType, formatted);
    }

    /**
     * Reads a document from a file, using the file extension to decide which parser to read the file with.
     * @param filename the file to read a document from
     * @return a Document
     */
    public Document readDocumentFromFile(String filename) {
        return this.inputer.readDocumentFromFile(filename);
    }

    public void setOutputer(Outputer outputer) {
        this.outputer = outputer;
    }

    public void setInputer(Inputer inputer) {
        this.inputer = inputer;
    }

    @Override
    public Map<ProvFormat, DeserializerFunction> getDeserializerMap() {
        return this.deserializerMap;
    }

    @Override
    public Map<ProvFormat, DeserializerFunction2> getDeserializerMap2() {
        return this.deserializerMap2;
    }
}