package com.baske.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

public class Lek implements Serializable{

	private String naziv;
	private TipLeka tipLeka;
	private HashSet<SastojakLeka> sastojci;


	public TipLeka getTipLeka() {
		return tipLeka;
	}

	public void setTipLeka(TipLeka tipLeka) {
		this.tipLeka = tipLeka;
	}

	public HashSet<SastojakLeka> getSastojci() {
		return sastojci;
	}

	public void setSastojci(HashSet<SastojakLeka> sastojci) {
		this.sastojci = sastojci;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Lek(TipLeka tipLeka, HashSet<SastojakLeka> sastojci, String naziv) {
		super();
		this.tipLeka = tipLeka;
		this.sastojci = sastojci;
		this.naziv = naziv;
	}

	public boolean jeAntibiotik() {
		if (tipLeka == null) {
			return false;
		}
		if (tipLeka == TipLeka.ANTIBIOTIK) {
			return true;
		} else {
			return false;
		}

	}

//	@Override
//	public String toString() {
//		return "Lek [naziv=" + naziv + ", tipLeka=" + tipLeka + ", sastojci=" + sastojci + "]";
//	}

	@Override
	public String toString() {
		return "Lek [naziv=" + naziv + "]";
	}

	

}
