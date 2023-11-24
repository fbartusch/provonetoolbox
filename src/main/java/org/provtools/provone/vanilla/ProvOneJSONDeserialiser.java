package org.provtools.provone.vanilla;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import java.util.TimeZone;

import javax.xml.datatype.XMLGregorianCalendar;

import org.openprovenance.prov.core.json.serialization.CustomProvOneBundleDeserializer;
import org.openprovenance.prov.core.json.serialization.SortedProvOneBundle;
import org.openprovenance.prov.core.json.serialization.SortedProvOneDocument;
import org.openprovenance.prov.core.json.serialization.deserial.CustomAttributeSetDeserializer;
import org.openprovenance.prov.core.json.serialization.deserial.CustomKindDeserializer;
import org.openprovenance.prov.core.json.serialization.deserial.CustomProvOneKindDeserializer;
import org.openprovenance.prov.core.json.serialization.deserial.CustomXMLGregorianCalendarDeserializer;
import org.openprovenance.prov.model.DateTimeOption;
import org.openprovenance.prov.model.Document;
import org.openprovenance.prov.model.exception.UncheckedException;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import static org.openprovenance.prov.core.json.serialization.deserial.CustomThreadConfig.JSON_CONTEXT_KEY_NAMESPACE;
import static org.openprovenance.prov.core.json.serialization.deserial.CustomThreadConfig.getAttributes;


/*
 * Serialiser for ProvOne documents.
 */

public class ProvOneJSONDeserialiser implements org.openprovenance.prov.model.ProvDeserialiser {
    ProvOneFactory pf =new ProvOneFactory();
    private final ProvOneMixin provOneMixin = new ProvOneMixin();
    final ObjectMapper mapper;
    private final DateTimeOption dateTimeOption;
    private final TimeZone optionalTimeZone;

    public ProvOneJSONDeserialiser()  {
        this(new ObjectMapper());
    }

    public ProvOneJSONDeserialiser(ObjectMapper mapper) {
        this.mapper = mapper;
        this.dateTimeOption = DateTimeOption.PRESERVE;
        this.optionalTimeZone = null;
        this.customize(mapper);
    }

    public ProvOneJSONDeserialiser(ObjectMapper mapper, DateTimeOption dateTimeOption) {
        this.mapper = mapper;
        this.dateTimeOption = dateTimeOption;
        this.optionalTimeZone = null;
        customize(mapper);
    }

    public ProvOneJSONDeserialiser(ObjectMapper mapper, DateTimeOption dateTimeOption, TimeZone optionalTimeZone) {
        this.mapper = mapper;
        this.dateTimeOption = dateTimeOption;
        this.optionalTimeZone = optionalTimeZone;
        customize(mapper);
    }

    public org.openprovenance.prov.model.Document deserialiseDocument (File serialised) throws IOException {
        return deserialiseDocument(new FileInputStream(serialised));
    }

    public Document deserialiseDocument(InputStream in) throws IOException {
        getAttributes().get().remove(JSON_CONTEXT_KEY_NAMESPACE);
        SortedProvOneDocument doc = null;
        try {
            doc = mapper.readValue(in, SortedProvOneDocument.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new UncheckedException(e);
        }
        //TODO check implementation of toDocument in SortedProvOneDocument
        return doc.toDocument(pf);
    }

public void customize(ObjectMapper mapper) {
        SimpleModule module =
                new SimpleModule("CustomKindSerializer", new Version(1, 0, 0, null, null, null));

        //TODO irrelevant? 
        module.addDeserializer(org.openprovenance.prov.model.StatementOrBundle.Kind.class, new CustomKindDeserializer());
        module.addDeserializer(org.provtools.provone.model.ProvOneStatementOrBundle.ProvOneKind.class, new CustomProvOneKindDeserializer());

        TypeFactory typeFactory = mapper.getTypeFactory();

        //TODO There is no ProvOneBundle, only SortedProvOneBundle. ProvToolbox also implements Bundle. Should we implement and use ProvOneBundle?
        //module.addDeserializer(Bundle.class, new CustomBundleDeserializer());
        module.addDeserializer(ProvOneBundle.class, new CustomProvOneBundleDeserializer());

        CollectionType setType = typeFactory.constructCollectionType(Set.class, org.openprovenance.prov.model.Attribute.class);
        module.addDeserializer(Set.class,new CustomAttributeSetDeserializer(setType));
        module.addDeserializer(XMLGregorianCalendar.class, new CustomXMLGregorianCalendarDeserializer(dateTimeOption,optionalTimeZone));

        provOneMixin.addProvMixin(mapper);

        mapper.registerModule(module);
    }
}
