package com.sample;

import java.io.Serializable;
import java.util.ArrayList;

import com.baske.model.Simptomi;

public class ProbaQuerry implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Simptomi> izabraniSimptomi;

	public ProbaQuerry(ArrayList<Simptomi> izabraniSimptomi) {
		super();
		this.izabraniSimptomi = izabraniSimptomi;
	}

	public ProbaQuerry() {
		super();
		// TODO Auto-generated constructor stub
		izabraniSimptomi= new ArrayList<>();
	}

	public ArrayList<Simptomi> getIzabraniSimptomi() {
		return izabraniSimptomi;
	}

	public void setIzabraniSimptomi(ArrayList<Simptomi> izabraniSimptomi) {
		this.izabraniSimptomi = izabraniSimptomi;
	}

}
