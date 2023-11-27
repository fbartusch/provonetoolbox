package org.openprovenance.prov.core.jsonld.serialization;

import java.io.IOException;
import java.io.OutputStream;

import javax.xml.datatype.XMLGregorianCalendar;

import org.openprovenance.prov.core.json.serialization.SortedProvOneDocument;
import org.openprovenance.prov.core.jsonld.ProvOneMixin;
import org.openprovenance.prov.core.jsonld11.serialization.ProvSerialiser;
import org.openprovenance.prov.core.jsonld11.serialization.serial.CustomDateSerializer;
import org.openprovenance.prov.core.jsonld11.serialization.serial.CustomNamespaceSerializer;
import org.openprovenance.prov.core.jsonld11.serialization.serial.CustomOverridingAttributeSerializer;
import org.openprovenance.prov.core.jsonld11.serialization.serial.CustomQualifiedNameSerializer;
import org.openprovenance.prov.model.Document;
import org.openprovenance.prov.model.Namespace;
import org.openprovenance.prov.model.exception.UncheckedException;
import org.openprovenance.prov.vanilla.QualifiedName;
import org.openprovenance.prov.vanilla.TypedValue;
import org.provtools.provone.vanilla.ProvOneDocument;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.Module.SetupContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

public class ProvOneSerialiser extends ProvSerialiser {

    protected final boolean embedContext;
    // create two independent mappers, with formatting or not
    final ObjectMapper mapper;
    final ObjectMapper mapperWithFormat = new ObjectMapper();
    final ObjectWriter writer;

    public ProvOneSerialiser () {
        this(new ObjectMapper(), false);
    }

    public ProvOneSerialiser (ObjectMapper mapper, boolean embedContext) {
        this.embedContext = embedContext;
        this.mapper = mapper;
        customize(mapper);
        customize(mapperWithFormat);
        mapperWithFormat.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.disable(SerializationFeature.INDENT_OUTPUT);
        writer = mapper.writer().withDefaultPrettyPrinter();

    }

    public ProvOneMixin provOneMixin() {
        return new ProvOneMixin();
    }


    @Override
    public void serialiseDocument(OutputStream out, Document document, boolean formatted) {
        try {
            if (formatted) {
                ProvOneDocument doc2 = new ProvOneDocument(document);
                mapperWithFormat.writeValue(out, doc2);
            } else {
                writer.writeValue(out, new ProvOneDocument(document));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new UncheckedException(e);
        }
    }

    public ObjectMapper customize(ObjectMapper mapper) {

        SimpleModule module =
                new SimpleModule("CustomKindSerializer",
                        new Version(1, 0, 0, null, null, null));

        module.addSerializer(QualifiedName.class, new CustomQualifiedNameSerializer());
        module.addSerializer(XMLGregorianCalendar.class, new CustomDateSerializer());
        module.addSerializer(Namespace.class, new CustomNamespaceSerializer(embedContext));
        mapper.registerModule(module);

        // See https://www.baeldung.com/jackson-serialize-field-custom-criteria#2-custom-serializer

        mapper.registerModule(new SimpleModule() {
            @Override
            public void setupModule(SetupContext context) {
                super.setupModule(context);
                context.addBeanSerializerModifier(new BeanSerializerModifier() {
                    @Override
                    public JsonSerializer<?> modifySerializer(SerializationConfig config, BeanDescription desc, JsonSerializer<?> serializer) {
                        if (TypedValue.class.isAssignableFrom(desc.getBeanClass())) {
                            return new CustomOverridingAttributeSerializer((JsonSerializer<TypedValue>) serializer);
                        }
                        return serializer;
                    }
                });
            }
        });

        provOneMixin().addProvMixin(mapper);
        return mapper;
    }
}
