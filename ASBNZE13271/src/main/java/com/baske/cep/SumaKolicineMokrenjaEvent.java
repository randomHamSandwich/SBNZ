package com.baske.cep;

import java.io.Serializable;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
//@Timestamp("executionTime")
@Expires("12h")
public class SumaKolicineMokrenjaEvent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2392052214382840137L;

	public SumaKolicineMokrenjaEvent() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
