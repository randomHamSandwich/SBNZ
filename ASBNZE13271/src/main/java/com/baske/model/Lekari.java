package com.baske.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Lekari implements Serializable {
	ArrayList<Lekar> lekari;

	public ArrayList<Lekar> getLekari() {
		return lekari;
	}

	public void setLekari(ArrayList<Lekar> lekari) {
		this.lekari = lekari;
	}

	public Lekari() {
		super();
		// TODO Auto-generated constructor stub
		lekari = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "Lekari [lekari=" + lekari + "]";
	}

	public Lekar proveriUserPass(String username, String pass) {
		// TODO Auto-generated method stub
		for (Lekar l : lekari) {
			if (l.getKorisnickoIme().equals(username) && l.getLozinka().equals(pass)) {
				return l;
			}
		}
		return null;

	}

	public boolean addLekar(Lekar lekar) {
		return lekari.add(lekar);
	}

	public static void writeLekari(Lekari lekari) throws Exception {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("lekari.dat"));
			out.writeObject(lekari);
			out.close();
			System.out.println("snimio lekare");

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static Lekari readLekari() throws Exception {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("lekari.dat"));
		Lekari lekari = (Lekari) in.readObject();
		in.close();
		System.out.println("trebalo bi da je procitao");
		System.out.println(lekari.getLekari());
		return lekari;

	}

}
