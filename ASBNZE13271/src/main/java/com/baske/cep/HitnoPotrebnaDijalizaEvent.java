package com.baske.cep;

import java.io.Serializable;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
@Expires("30m")
public class HitnoPotrebnaDijalizaEvent implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1324837989205264773L;

	public HitnoPotrebnaDijalizaEvent() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
