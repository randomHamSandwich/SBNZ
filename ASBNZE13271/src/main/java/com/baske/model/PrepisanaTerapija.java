package com.baske.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class PrepisanaTerapija implements Serializable{
	private LocalDate datumPrepisaneTerapiej;
	private Bolest bolest;
//	TODO terapija da moze da ima antibijotike and so on
	private ArrayList<Lek> prepisaniLekovi;
	// preporukaLekara tipa odmor, mnogo vode, c vitamin, svez bazduh idt...
	private String preporukaLekar;

	private double temperatur;
	private boolean visokPritisak;
	//treba za izveshtaj "Spisak mogucih zavisnika"
	private Osoba lerarIliAdmin;
	

	public Osoba getLerarIliAdmin() {
		return lerarIliAdmin;
	}

	public void setLerarIliAdmin(Osoba lerarIliAdmin) {
		this.lerarIliAdmin = lerarIliAdmin;
	}

	public double getTemperatur() {
		return temperatur;
	}

	public void setTemperatur(double temperatur) {
		this.temperatur = temperatur;
	}

	public boolean isVisokPritisak() {
		return visokPritisak;
	}

	public void setVisokPritisak(boolean visokPritisak) {
		this.visokPritisak = visokPritisak;
	}

	public Bolest getBolest() {
		return bolest;
	}

	public void setBolest(Bolest bolest) {
		this.bolest = bolest;
	}

	public ArrayList<Lek> getPrepisaniLekovi() {
		return prepisaniLekovi;
	}

	public void setPrepisaniLekovi(ArrayList<Lek> prepisaniLekovi) {
		this.prepisaniLekovi = prepisaniLekovi;
	}

	public String getPreporukaLekar() {
		return preporukaLekar;
	}

	public void setPreporukaLekar(String preporukaLekar) {
		this.preporukaLekar = preporukaLekar;
	}

	public LocalDate getDatumPrepisaneTerapiej() {
		return datumPrepisaneTerapiej;
	}

	public void setDatumPrepisaneTerapiej(LocalDate datumPrepisaneTerapiej) {
		this.datumPrepisaneTerapiej = datumPrepisaneTerapiej;
	}
	@Deprecated
	public PrepisanaTerapija(Bolest bolest, ArrayList<Lek> prepisaniLekovi, String preporukaLekar,
			LocalDate datumPrepisaneTerapiej) {
		super();
		this.bolest = bolest;
		this.prepisaniLekovi = prepisaniLekovi;
		this.preporukaLekar = preporukaLekar;
		this.datumPrepisaneTerapiej = datumPrepisaneTerapiej;
	}

	public PrepisanaTerapija(Bolest bolest, ArrayList<Lek> prepisaniLekovi, String preporukaLekar,
			LocalDate datumPrepisaneTerapiej, double temperatur, boolean visokPritisak) {
		super();
		this.bolest = bolest;
		this.prepisaniLekovi = prepisaniLekovi;
		this.preporukaLekar = preporukaLekar;
		this.datumPrepisaneTerapiej = datumPrepisaneTerapiej;
		this.temperatur = temperatur;
		this.visokPritisak = visokPritisak;
	}


	@Override
	public String toString() {
		return "[datum=" + datumPrepisaneTerapiej + ", bolest=" + bolest.getNazivBolesti()
				+ ", prepisaniLekovi=" + prepisaniLekovi +  ", temperatur="
				+ temperatur + ", visokPritisak=" + visokPritisak + ", preporukaLekar=" + preporukaLekar +"]" +lerarIliAdmin.getIme();
	}

	public boolean terapijaPrepisanaDoSestMeseciPre() {
		if (datumPrepisaneTerapiej == null)
			return false;
		if (datumPrepisaneTerapiej.isAfter(LocalDate.now().minusDays(60))) {
			return true;
		} else {
			return false;
		}
	}

	public boolean uPosednjihSestMesecBarDesetPutaVisokPritisak() {
		if (datumPrepisaneTerapiej == null)
			return false;
		if (datumPrepisaneTerapiej.isAfter(LocalDate.now().minusMonths(6)) && visokPritisak) {
			return true;
		} else {
			return false;
		}
	}

	public boolean bolujeOdHupertenzijeVisheOd6Meseci() {
		if (datumPrepisaneTerapiej == null)
			return false;
		if (datumPrepisaneTerapiej.isBefore(LocalDate.now().minusMonths(6))) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean uPoslednjih14Dana() {
		if (datumPrepisaneTerapiej == null)
			return false;
		if (datumPrepisaneTerapiej.isAfter(LocalDate.now().minusDays(14))) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean uPoslednjih21Dana() {
		if (datumPrepisaneTerapiej == null)
			return false;
		if (datumPrepisaneTerapiej.isAfter(LocalDate.now().minusDays(21))) {
			return true;
		} else {
			return false;
		}
	}
	@Deprecated
	public boolean prepisanAntibiotik() {
		if (prepisaniLekovi == null) {
			return false;
		}
		for(Lek l : prepisaniLekovi) {
			if(l.getTipLeka()==TipLeka.ANTIBIOTIK) {
				return true;
			}
		}
		return false;
	}
	


}
