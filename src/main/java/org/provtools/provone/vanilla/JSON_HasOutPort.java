package org.provtools.provone.vanilla;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "program", "port" })
@JsonInclude(JsonInclude.Include.NON_EMPTY)

public class JSON_HasOutPort {

}
