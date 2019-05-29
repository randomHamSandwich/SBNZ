package com.baske.model;

public class MogucaBolest {
	private String naziv;
	private double mogucnost;

	public MogucaBolest(String naziv, double mogucnost) {
		super();
		this.naziv = naziv;
		this.mogucnost = mogucnost;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public double getMogucnost() {
		return mogucnost;
	}

	public void setMogucnost(double mogucnost) {
		this.mogucnost = mogucnost;
	}

//	@Override
//	public String toString() {
//		return "MogucaBolest [naziv= " + naziv + ", mogucnost= " + mogucnost + "]";
//	}
//	
	
	@Override
	public String toString() {
		if(mogucnost==-1.0) {
			return "";
		}
		return "MogucaBolest [naziv= " + naziv + ", mogucnost= " + String.format("%.2f", mogucnost*100 ) + "%]";
	}

}
