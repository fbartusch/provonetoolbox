package org.openprovenance.prov.core.json.serialization.deserial;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.provtools.provone.model.ProvOneStatementOrBundle;

import java.io.IOException;


public class CustomProvOneKindDeserializer extends StdDeserializer<ProvOneStatementOrBundle.ProvOneKind> {

    public CustomProvOneKindDeserializer() {
        super(ProvOneStatementOrBundle.ProvOneKind.class);
    }

    @Override
    public ProvOneStatementOrBundle.ProvOneKind deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return ProvOneStatementOrBundle.ProvOneKind.PROVONE_CHANNEL;
    }

    protected CustomProvOneKindDeserializer(Class<ProvOneStatementOrBundle.ProvOneKind> t) {
        super(t);
    }

}