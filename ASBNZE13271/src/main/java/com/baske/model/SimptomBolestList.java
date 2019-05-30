package com.baske.model;

import java.io.Serializable;
import java.util.ArrayList;
@Deprecated
public class SimptomBolestList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<SimptomBolestNumber> simBolNum;

	public ArrayList<SimptomBolestNumber> getSimBolNum() {
		return simBolNum;
	}

	public void setSimBolNum(ArrayList<SimptomBolestNumber> simBolNum) {
		this.simBolNum = simBolNum;
	}

	public SimptomBolestList() {
		super();
		// TODO Auto-generated constructor stub
		simBolNum = new ArrayList<>();
	}

	public SimptomBolestList(ArrayList<SimptomBolestNumber> simBolNum) {
		super();
		this.simBolNum = simBolNum;
	}

	@Override
	public String toString() {
		return "SimptomBolestList [simBolNum=" + simBolNum + "]";
	}

	
	
	
}
