package com.baske.cep;

import java.io.Serializable;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
@Expires("30m")
public class VisheOdDesetOtkucajaEvet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9183315240483356043L;

	public VisheOdDesetOtkucajaEvet() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
