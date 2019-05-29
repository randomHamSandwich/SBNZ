package com.baske.model;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Lekovi implements Serializable {

	ArrayList<Lek> lekovi;

	public ArrayList<Lek> getLekovi() {
		return lekovi;
	}

	public void setLekovi(ArrayList<Lek> lekovi) {
		this.lekovi = lekovi;
	}

	public Lekovi() {
		super();
		// TODO Auto-generated constructor stub
		lekovi = new ArrayList<>();
	}
	
	public static Lekovi readLekovi()  {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("lekovi.dat"));
			Lekovi lekovi = (Lekovi) in.readObject();
			in.close();
			System.out.println("trebalo bi da je procitao lekove");
			System.out.println(lekovi.getLekovi());
			return lekovi;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	
}
