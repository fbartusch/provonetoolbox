package org.provtools.provone.vanilla;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.openprovenance.prov.core.json.JSON_SortedDocument;
import org.openprovenance.prov.core.json.serialization.deserial.CustomKeyDeserializer;
import org.openprovenance.prov.core.json.serialization.serial.CustomQualifiedNameSerializerAsField;
import org.openprovenance.prov.vanilla.*;

import java.util.Map;

@JsonPropertyOrder({ "prefix"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public interface JSON_SortedProvOneDocument extends JSON_SortedDocument {
    
    @JsonSerialize(keyUsing = CustomQualifiedNameSerializerAsField.class, contentAs = Program.class)
    @JsonDeserialize(keyUsing = CustomKeyDeserializer.class, contentAs = Program.class )
    Map<QualifiedName, Program> getProgram();

    @JsonSerialize(keyUsing = CustomQualifiedNameSerializerAsField.class, contentAs = Port.class)
    @JsonDeserialize(keyUsing = CustomKeyDeserializer.class, contentAs = Port.class )
    Map<QualifiedName, Port> getPort();

    @JsonSerialize(keyUsing = CustomQualifiedNameSerializerAsField.class, contentAs = Channel.class)
    @JsonDeserialize(keyUsing = CustomKeyDeserializer.class, contentAs = Channel.class )
    Map<QualifiedName, Channel> getChannel();

    @JsonSerialize(keyUsing = CustomQualifiedNameSerializerAsField.class, contentAs = Controller.class)
    @JsonDeserialize(keyUsing = CustomKeyDeserializer.class, contentAs = Controller.class )
    Map<QualifiedName, Controller> getController();

    @JsonSerialize(keyUsing = CustomQualifiedNameSerializerAsField.class, contentAs = Workflow.class)
    @JsonDeserialize(keyUsing = CustomKeyDeserializer.class, contentAs = Workflow.class )
    Map<QualifiedName, Workflow> getWorkflow();

    @JsonSerialize(keyUsing = CustomQualifiedNameSerializerAsField.class, contentAs = HasSubProgram.class)
    @JsonDeserialize(keyUsing = CustomKeyDeserializer.class, contentAs = HasSubProgram.class )
    Map<QualifiedName, HasSubProgram> getHasSubProgram();

    @JsonSerialize(keyUsing = CustomQualifiedNameSerializerAsField.class, contentAs = ControlledBy.class)
    @JsonDeserialize(keyUsing = CustomKeyDeserializer.class, contentAs = ControlledBy.class )    
    Map<QualifiedName, ControlledBy> getControlledBy();

    @JsonSerialize(keyUsing = CustomQualifiedNameSerializerAsField.class, contentAs = Controls.class)
    @JsonDeserialize(keyUsing = CustomKeyDeserializer.class, contentAs = Controls.class )
    Map<QualifiedName, Controls> getControls();
    
    @JsonSerialize(keyUsing = CustomQualifiedNameSerializerAsField.class, contentAs = HasInPort.class)
    @JsonDeserialize(keyUsing = CustomKeyDeserializer.class, contentAs = HasInPort.class )
    Map<QualifiedName, HasInPort> getHasInPort();

    @JsonSerialize(keyUsing = CustomQualifiedNameSerializerAsField.class, contentAs = HasOutPort.class)
    @JsonDeserialize(keyUsing = CustomKeyDeserializer.class, contentAs = HasOutPort.class )
    Map<QualifiedName, HasOutPort> getHasOutPort();

    @JsonSerialize(keyUsing = CustomQualifiedNameSerializerAsField.class, contentAs = HasDefaultParam.class)
    @JsonDeserialize(keyUsing = CustomKeyDeserializer.class, contentAs = HasDefaultParam.class )
    Map<QualifiedName, HasDefaultParam> getHasDefaultParam();

    @JsonSerialize(keyUsing = CustomQualifiedNameSerializerAsField.class, contentAs = ConnectsTo.class)
    @JsonDeserialize(keyUsing = CustomKeyDeserializer.class, contentAs = ConnectsTo.class )
    Map<QualifiedName, ConnectsTo> getConnectsTo();

    @JsonSerialize(keyUsing = CustomQualifiedNameSerializerAsField.class, contentAs = Execution.class)
    @JsonDeserialize(keyUsing = CustomKeyDeserializer.class, contentAs = Execution.class )
    Map<QualifiedName, Execution> getExecution();

    @JsonSerialize(keyUsing = CustomQualifiedNameSerializerAsField.class, contentAs = User.class)
    @JsonDeserialize(keyUsing = CustomKeyDeserializer.class, contentAs = User.class )
    Map<QualifiedName, User> getUser();

    @JsonSerialize(keyUsing = CustomQualifiedNameSerializerAsField.class, contentAs = WasPartOf.class)
    @JsonDeserialize(keyUsing = CustomKeyDeserializer.class, contentAs = WasPartOf.class )
    Map<QualifiedName, WasPartOf> getWasPartOf();

    @JsonSerialize(keyUsing = CustomQualifiedNameSerializerAsField.class, contentAs = HadInPort.class)
    @JsonDeserialize(keyUsing = CustomKeyDeserializer.class, contentAs = HadInPort.class )
    Map<QualifiedName, HadInPort> getHadInPort();

    @JsonSerialize(keyUsing = CustomQualifiedNameSerializerAsField.class, contentAs = HadOutPort.class)
    @JsonDeserialize(keyUsing = CustomKeyDeserializer.class, contentAs = HadOutPort.class )
    Map<QualifiedName, HadOutPort> getHadOutPort();

    @JsonSerialize(keyUsing = CustomQualifiedNameSerializerAsField.class, contentAs = HadEntity.class)
    @JsonDeserialize(keyUsing = CustomKeyDeserializer.class, contentAs = HadEntity.class )
    Map<QualifiedName, HadEntity> getHadEntity();

    @JsonSerialize(keyUsing = CustomQualifiedNameSerializerAsField.class, contentAs = Data.class)
    @JsonDeserialize(keyUsing = CustomKeyDeserializer.class, contentAs = Data.class )
    Map<QualifiedName, Data> getData();

    @JsonSerialize(keyUsing = CustomQualifiedNameSerializerAsField.class, contentAs = Visualization.class)
    @JsonDeserialize(keyUsing = CustomKeyDeserializer.class, contentAs = Visualization.class )
    Map<QualifiedName, Visualization> getVisualization();

    @JsonSerialize(keyUsing = CustomQualifiedNameSerializerAsField.class, contentAs = Document.class)
    @JsonDeserialize(keyUsing = CustomKeyDeserializer.class, contentAs = Document.class )
    Map<QualifiedName, Document> getDocument();

}
