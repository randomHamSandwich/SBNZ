package com.baske.cep;

import java.io.Serializable;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
//@Timestamp("executionTime")
@Expires("10m")
public class PotrebnaDijalizaEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2648754924152295750L;

	public PotrebnaDijalizaEvent() {
		super();
		// TODO Auto-generated constructor stub
	}

}
