package com.baske.cep;

import java.io.Serializable;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
//@Timestamp("executionTime")
@Expires("10m")
public class ProblemSaKiseonikomEvent implements Serializable{

	public ProblemSaKiseonikomEvent() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
