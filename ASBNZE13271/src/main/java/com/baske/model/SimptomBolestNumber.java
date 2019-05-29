package com.baske.model;

import java.io.Serializable;

public class SimptomBolestNumber  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Bolest bolest;
	private int broj= 0;
	public Bolest getBolest() {
		return bolest;
	}
	public void setBolest(Bolest bolest) {
		this.bolest = bolest;
	}
	public int getBroj() {
		return broj;
	}
	public void setBroj(int broj) {
		this.broj = broj;
	}
	public SimptomBolestNumber(Bolest bolest, int broj) {
		super();
		this.bolest = bolest;
		this.broj = broj;
	}
	@Override
	public String toString() {
		return "SimptomBolestNumber [bolest=" + bolest + ", broj=" + broj + "]";
	}
	
	
	

}
