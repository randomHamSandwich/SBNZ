package com.baske.cep;

import java.io.Serializable;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
@Expires("30m")
public class HronicnaBubreznaBolestEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2548716361989035410L;

	public HronicnaBubreznaBolestEvent() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
