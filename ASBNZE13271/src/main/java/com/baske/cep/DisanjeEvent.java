package com.baske.cep;

import java.io.Serializable;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
@Expires("15m")
public class DisanjeEvent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7536966058009936966L;
	
	private int nivoKiseonikaUKrvi_mmHg;

	public int getNivoKiseonikaUKrvi_mmHg() {
		return nivoKiseonikaUKrvi_mmHg;
	}

	public void setNivoKiseonikaUKrvi_mmHg(int nivoKiseonikaUKrvi_mmHg) {
		this.nivoKiseonikaUKrvi_mmHg = nivoKiseonikaUKrvi_mmHg;
	}

	public DisanjeEvent(int nivoKiseonikaUKrvi_mmHg) {
		super();
		this.nivoKiseonikaUKrvi_mmHg = nivoKiseonikaUKrvi_mmHg;
	}

	public DisanjeEvent() {
		super();
		// TODO Auto-generated constructor stub
	}
}
