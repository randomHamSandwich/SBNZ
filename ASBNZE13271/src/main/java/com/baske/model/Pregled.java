package com.baske.model;

import java.util.HashSet;
import java.util.Set;

public class Pregled {
	private Pacient pacient;
	private Set<Simptomi> simptomi;
//	private Set<String> specificniSimptomi;
	private Set<SpecificanSimptom> specificniSimptomi;
	private double najboljaProcenaBolesti;
	private double temeratura;
	private boolean visokPritisak;
//	private MoguceBolesti moguceBolesti;
//
//	public Pregled(Set<Simptomi> simptomi, HashSet<String> specificniSimptomi) {
//		super();
//		this.simptomi = simptomi;
//		this.specificniSimptomi = specificniSimptomi;
//		this.moguceBolesti = new MoguceBolesti();
//	}
//
//	public MoguceBolesti getMoguceBolesti() {
//		return moguceBolesti;
//	}
//
//	public void setMoguceBolesti(MoguceBolesti moguceBolesti) {
//		this.moguceBolesti = moguceBolesti;
//	}

	private Set<MogucaBolest> moguceBolesti;

	public double getTemeratura() {
		return temeratura;
	}

	public void setTemeratura(double temeratura) {
		this.temeratura = temeratura;
	}

	public boolean isVisokPritisak() {
		return visokPritisak;
	}

	public void setVisokPritisak(boolean visokPritisak) {
		this.visokPritisak = visokPritisak;
	}

	@Deprecated
	public Pregled(Set<Simptomi> simptomi, Set<SpecificanSimptom> specificniSimptomi, Set<MogucaBolest> moguceBolesti,
			Pacient pacient) {
		super();
		this.simptomi = simptomi;
		this.specificniSimptomi = specificniSimptomi;
		this.moguceBolesti = moguceBolesti;
		this.pacient = pacient;
		this.najboljaProcenaBolesti = -10.0;
	}

	public Pregled(Pacient pacient, Set<Simptomi> simptomi, Set<SpecificanSimptom> specificniSimptomi, double temeratura, boolean visokPritisak, Set<MogucaBolest> moguceBolesti) {
		super();
		this.pacient = pacient;
		this.simptomi = simptomi;
		this.specificniSimptomi = specificniSimptomi;
		this.najboljaProcenaBolesti = -10.0;
		this.temeratura = temeratura;
		this.visokPritisak = visokPritisak;
		this.moguceBolesti = moguceBolesti;
	}

	public Set<MogucaBolest> getMoguceBolesti() {
		return moguceBolesti;
	}

	public void setMoguceBolesti(Set<MogucaBolest> moguceBolesti) {
		this.moguceBolesti = moguceBolesti;
	}

	public double getNajboljaProcenaBolesti() {
		return najboljaProcenaBolesti;
	}

	public void setNajboljaProcenaBolesti(double najboljaProcenaBolesti) {
		this.najboljaProcenaBolesti = najboljaProcenaBolesti;
	}

	public Pacient getPacient() {
		return pacient;
	}

	public void setPacient(Pacient pacient) {
		this.pacient = pacient;
	}

	public Set<Simptomi> getSimptomi() {
		return simptomi;
	}

	public void setSimptomi(Set<Simptomi> simptomi) {
		this.simptomi = simptomi;
	}

	public Set<SpecificanSimptom> getSpecificniSimptomi() {
		return specificniSimptomi;
	}

	public void setSpecificniSimptomi(Set<SpecificanSimptom> specificniSimptomi) {
		this.specificniSimptomi = specificniSimptomi;
	}

//	public Set<String> getSpecificniSimptomi() {
//		return specificniSimptomi;
//	}
//
//	public void setSpecificniSimptomi(Set<String> specificniSimptomi) {
//		this.specificniSimptomi = specificniSimptomi;
//	}

}
