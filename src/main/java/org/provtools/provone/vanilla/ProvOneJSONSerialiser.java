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
        customize(mapper);
        customize(mapperWithFormat);
        // Add ProvOneMixin here and not in customize.
        provOneMixin.addProvMixin(mapper);
    }

    @Override
    public void customize(ObjectMapper mapper) {
        registerModule(mapper);
        registerFilter(mapper);
        // We cannot call this here, as the super constructor calls customize and provOneMixin is null at that moment.
        //provOneMixin.addProvMixin(mapper);
    }

    @Override
    public void serialiseDocument(OutputStream out, Document document, boolean formatted) {
        try {
            if (formatted) {
                mapperWithFormat.writeValue(out, new SortedProvOneDocument(document));
            } else {
                mapper.writeValue(out, new SortedProvOneDocument(document));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new UncheckedException(e);
        }
    }
}
