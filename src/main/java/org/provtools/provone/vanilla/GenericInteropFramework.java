package org.provtools.provone.vanilla;

import java.io.OutputStream;

import org.openprovenance.prov.interop.CommandLineArguments;
import org.openprovenance.prov.interop.Formats.ProvFormat;
import org.openprovenance.prov.interop.Inputer;
import org.openprovenance.prov.interop.InteropException;
import org.openprovenance.prov.interop.InteropFramework;
import org.openprovenance.prov.interop.Outputer;
import org.openprovenance.prov.model.Document;
import org.openprovenance.prov.model.ProvFactory;

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

     public GenericInteropFramework() {
        this(new CommandLineArguments(), defaultFactory, null);
    }

    public GenericInteropFramework(ProvFactory pFactory) {
        this(new CommandLineArguments(), pFactory, null);
    }

    public GenericInteropFramework(ProvFactory pFactory, Outputer outputer) {
        this(new CommandLineArguments(), pFactory, outputer);
    }

    public GenericInteropFramework(CommandLineArguments config, ProvFactory pFactory, Outputer outputer) {
        super(config, pFactory);

        if (outputer == null) {
            this.outputer = new ProvOneOutputer(this, pFactory);
        } else {
            this.outputer = outputer;
        }

        this.inputer = new Inputer(this, pFactory);
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

    public void setOutputer(Outputer outputer) {
        this.outputer = outputer;
    }
}