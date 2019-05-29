package com.baske.cep;

import java.io.Serializable;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
//@Timestamp("executionTime")
@Expires("10m")
public class UbrzanSrcaniRtamEvent implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 3362761228084384304L;

	public UbrzanSrcaniRtamEvent() {
        super();
    }
}

