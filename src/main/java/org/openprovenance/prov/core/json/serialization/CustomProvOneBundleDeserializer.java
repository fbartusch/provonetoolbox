package org.openprovenance.prov.core.json.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.openprovenance.prov.model.Namespace;
import org.provtools.provone.vanilla.ProvOneBundle;
import org.provtools.provone.vanilla.ProvOneFactory;

import java.io.IOException;

import static org.openprovenance.prov.core.json.serialization.deserial.CustomThreadConfig.JSON_CONTEXT_KEY_NAMESPACE;
import static org.openprovenance.prov.core.json.serialization.deserial.CustomThreadConfig.getAttributes;

public class CustomProvOneBundleDeserializer extends JsonDeserializer<ProvOneBundle> {
    
    static final ProvOneFactory pf = org.provtools.provone.vanilla.ProvOneFactory.getFactory();

    @Override
    public ProvOneBundle deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        // get the document namespace from the context
        Namespace docNs = getAttributes().get().get(JSON_CONTEXT_KEY_NAMESPACE);

        // read the bundle (which has its own namespace)
        SortedProvOneBundle sbun= jsonParser.readValueAs(SortedProvOneBundle.class);

        // restore document namespace
        getAttributes().get().put(JSON_CONTEXT_KEY_NAMESPACE,docNs);

        // patch the bundle namespace, so that it points to the document namespace
        org.openprovenance.prov.model.Bundle theBun = sbun.toBundle(pf);
        theBun.getNamespace().setParent(docNs);

        return (ProvOneBundle) theBun;
    }
}
