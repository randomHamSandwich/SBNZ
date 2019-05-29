package com.baske.cep;

import java.io.Serializable;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
@Expires("15m")
public class KiseonikRasteEvent  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6531869854530243426L;

	public KiseonikRasteEvent() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
