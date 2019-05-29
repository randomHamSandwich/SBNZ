package com.baske.cep;

import java.io.Serializable;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
@Expires("15m")
public class NivoKisonikaUKrviManjiEvenet implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5806949753889330902L;

	public NivoKisonikaUKrviManjiEvenet() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
