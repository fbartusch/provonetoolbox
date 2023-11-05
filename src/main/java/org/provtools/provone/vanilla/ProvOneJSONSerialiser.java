package org.provtools.provone.vanilla;

import java.io.IOException;
import java.io.OutputStream;

import org.openprovenance.prov.core.json.serialization.SortedDocument;
import org.openprovenance.prov.core.json.serialization.SortedProvOneDocument;
import org.openprovenance.prov.model.Document;
import org.openprovenance.prov.model.exception.UncheckedException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/*
 * Serialiser for ProvOne documents.
 */

public class ProvOneJSONSerialiser extends org.openprovenance.prov.core.json.serialization.ProvSerialiser {
    
    final ObjectMapper mapper = new ObjectMapper();
    final ObjectMapper mapperWithFormat = new ObjectMapper();
    private final ProvOneMixin provOneMixin = new ProvOneMixin();

    public ProvOneJSONSerialiser() {
        super();
        // Configure the mappers here and not in customize
        this.mapperWithFormat.enable(SerializationFeature.INDENT_OUTPUT);
        this.mapper.disable(SerializationFeature.INDENT_OUTPUT);
        provOneMixin.addProvMixin(this.mapper);
        registerModule(this.mapper);
        registerFilter(this.mapper);
        provOneMixin.addProvMixin(this.mapperWithFormat);
        registerModule(this.mapperWithFormat);
        registerFilter(this.mapperWithFormat);
    }

    @Override
    public void customize(ObjectMapper mapper) {
    }

    @Override
    public void serialiseDocument(OutputStream out, Document document, boolean formatted) {
        try {
            if (formatted) {
                this.mapperWithFormat.writeValue(out, new SortedProvOneDocument(document));
            } else {
                this.mapper.writeValue(out, new SortedProvOneDocument(document));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new UncheckedException(e);
        }
    }
}
