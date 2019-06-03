package com.baske.model;

public class AlergicanPac {
	private boolean alergican;

	public boolean isAlergican() {
		return alergican;
	}

	public void setAlergican(boolean alergican) {
		this.alergican = alergican;
	}

	public AlergicanPac(boolean alergican) {
		super();
		this.alergican = alergican;
	}

	@Override
	public String toString() {
		return "AlergicanPac [alergican=" + alergican + "]";
	}
	
	

}
