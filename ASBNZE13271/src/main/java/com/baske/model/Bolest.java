package com.baske.model;

import java.io.Serializable;
import java.util.HashSet;

public class Bolest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8471497732370513050L;
	private String nazivBolesti;
	private HashSet<Simptomi> simptomi;
	private HashSet<SpecificanSimptom> specificniSimptomi;

	public Bolest(String nazivBolesti, HashSet<Simptomi> simptomi, HashSet<SpecificanSimptom> specificniSimptomi) {
		super();
		this.nazivBolesti = nazivBolesti;
		this.simptomi = simptomi;
		this.specificniSimptomi = specificniSimptomi;
	}

	public String getNazivBolesti() {
		return nazivBolesti;
	}

	public void setNazivBolesti(String nazivBolesti) {
		this.nazivBolesti = nazivBolesti;
	}

	public HashSet<Simptomi> getSimptomi() {
		return simptomi;
	}

	public void setSimptomi(HashSet<Simptomi> simptomi) {
		this.simptomi = simptomi;
	}

	public HashSet<SpecificanSimptom> getSpecificniSimptomi() {
		return specificniSimptomi;
	}

	public void setSpecificniSimptomi(HashSet<SpecificanSimptom> specificniSimptomi) {
		this.specificniSimptomi = specificniSimptomi;
	}

//	@Override
//	public String toString() {
//		return "Bolest [nazivBolesti=" + nazivBolesti + ", simptomi=" + simptomi + ", specificniSimptomi="
//				+ specificniSimptomi + "]";
//	}
	
	
	@Deprecated
	public boolean povisenaTemperatura() {
		if(simptomi==null)
			return false;
		if(simptomi.contains(Simptomi.TEMPERATURA_OD_40_DO_41) || simptomi.contains(Simptomi.TEMPERATURA_VECA_OD_38)) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public String toString() {
		return  nazivBolesti ;
	}
	

}
