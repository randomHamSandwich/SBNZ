package com.baske.cep;

import java.io.Serializable;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
//@Timestamp("executionTime")
@Expires("12h")
public class MokrenjeEvent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int kolicinaMili;
	public MokrenjeEvent() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MokrenjeEvent(int kolicinaMili) {
		super();
		this.kolicinaMili = kolicinaMili;
	}
	public int getKolicinaMili() {
		return kolicinaMili;
	}
	public void setKolicinaMili(int kolicinaMili) {
		this.kolicinaMili = kolicinaMili;
	}


}
