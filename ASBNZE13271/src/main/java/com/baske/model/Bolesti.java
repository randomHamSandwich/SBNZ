package com.baske.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Bolesti implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3041593227738117511L;
	ArrayList<Bolest> bolesti;

	public ArrayList<Bolest> getBolesti() {
		return bolesti;
	}

	public void setBolesti(ArrayList<Bolest> bolesti) {
		this.bolesti = bolesti;
	}

	public Bolesti() {
		super();
		bolesti = new ArrayList<>();
	}
	
	

}
