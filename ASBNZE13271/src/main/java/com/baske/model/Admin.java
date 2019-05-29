package com.baske.model;

import java.io.Serializable;

public class Admin extends Osoba implements Serializable {
	private String korisnickoIme;
	private String lozinka;

	public Admin(String ime, String prezime,String kI, String lozinka) {
		super(ime, prezime);
		// TODO Auto-generated constructor stub
		this.korisnickoIme = kI;
		this.lozinka = lozinka;
	}

	@Override
	public String toString() {
		return "Admin [korisnickoIme=" + korisnickoIme + ", getIme()=" + getIme() + ", getPrezime()=" + getPrezime()
				+ "]";
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	
	

}
