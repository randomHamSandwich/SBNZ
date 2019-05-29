package com.baske.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Pacient extends Osoba implements Serializable {

	List<PrepisanaTerapija> terapije;
	private int Id;
	private HashSet<Lek> alergijaNaLekove;
	private HashSet<String> alergijaNaSastojkeIzLeka;

	public HashSet<Lek> getAlergijaNaLekove() {
		return alergijaNaLekove;
	}

	public void setAlergijaNaLekove(HashSet<Lek> alergijaNaLekove) {
		this.alergijaNaLekove = alergijaNaLekove;
	}

	public HashSet<String> getAlergijanaSastojkeIzLeka() {
		return alergijaNaSastojkeIzLeka;
	}

	public void setAlergijanaSastojkeIzLeka(HashSet<String> alergijanaSastojkeIzLeka) {
		this.alergijaNaSastojkeIzLeka = alergijanaSastojkeIzLeka;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public List<PrepisanaTerapija> getTerapije() {
		return terapije;
	}

	public void setTerapije(List<PrepisanaTerapija> terapije) {
		this.terapije = terapije;
	}

	public Pacient(String ime, String prezime) {
		super(ime, prezime);
		// TODO Auto-generated constructor stub
		this.terapije = new ArrayList<>();
		this.alergijaNaLekove = new HashSet<>();
		this.alergijaNaSastojkeIzLeka = new HashSet<>();
	}

	@Override
	public String toString() {
		return "Pacient [Id=" + Id + ", getIme()=" + getIme() + ", getPrezime()=" + getPrezime() + "]";
	}

	public boolean addAlergijaNaLek(Lek l) {
		if (alergijaNaLekove.add(l)) {
			return true;
		} else {
			return false;
		}

	}

	public boolean addAlergijaNaSastojak(String s) {
		if (alergijaNaSastojkeIzLeka.add(s)) {
			return true;
		} else {
			return false;
		}
	}

	public void addTerapija(PrepisanaTerapija prepisanaTerapija) {
		// TODO Auto-generated method stub
		terapije.add(prepisanaTerapija);
	}

//	@Override
//	public String toString() {
//		return "Pacient [Id="+Id  +", getIme()=" +getIme()+", getPrezime()="+ getPrezime()+", terapije=" + terapije +  "]\n";
//	}

	
}
