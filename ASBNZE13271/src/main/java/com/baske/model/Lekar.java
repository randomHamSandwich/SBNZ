package com.baske.model;

import java.io.Serializable;

public class Lekar extends Osoba implements Serializable{

	private String korisnickoIme;
	private String lozinka;
	
	
	
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


	public Lekar(String ime, String prezime, String korisnickoIme, String lozinka) {
		super(ime, prezime);
		// TODO Auto-generated constructor stub
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
	}


	@Override
	public String toString() {
		return "Lekar [korisnickoIme=" + korisnickoIme + ", getIme()=" + getIme() + ", getPrezime()=" + getPrezime()
				+ "]";
	}

	
}
