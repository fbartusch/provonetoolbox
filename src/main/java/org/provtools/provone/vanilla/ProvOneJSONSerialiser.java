package org.provtools.provone.vanilla;

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
    }

    public void customize(ObjectMapper mapper) {
        registerModule(mapper);
        registerFilter(mapper);
        provOneMixin.addProvMixin(mapper);
    }



}
