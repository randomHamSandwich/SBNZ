package com.baske.model;

import java.util.ArrayList;

public class IspisPacijentMonitoring {
	private ArrayList<String> ispis;

	public IspisPacijentMonitoring() {
		super();
		// TODO Auto-generated constructor stub
		ispis = new ArrayList<>();
	}

	public ArrayList<String> getIspis() {
		return ispis;
	}

	public void setIspis(ArrayList<String> ispis) {
		this.ispis = ispis;
	}

	public boolean add(String s) {
		return ispis.add(s);
	}

}
