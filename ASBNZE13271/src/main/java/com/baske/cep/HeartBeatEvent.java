package com.baske.cep;

import java.io.Serializable;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

@Role(Role.Type.EVENT)
//@Timestamp("executionTime")
@Expires("10s")
public class HeartBeatEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    public HeartBeatEvent() {
        super();
    }
}

