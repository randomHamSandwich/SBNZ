package com.baske.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Administratori implements Serializable {
	ArrayList<Admin> administratori;

	public ArrayList<Admin> getAdministratori() {
		return administratori;
	}

	public void setAdministratori(ArrayList<Admin> administratori) {
		this.administratori = administratori;
	}

	public Administratori() {
		super();
		// TODO Auto-generated constructor stub
		administratori = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "Administratori [administratori=" + administratori + "]";
	}

	public Admin proveriUSerPass(String username, String pass) {
		// TODO Auto-generated method stub
		for (Admin admin : administratori) {
			if (admin.getKorisnickoIme().equals(username) && admin.getLozinka().equals(pass)) {
				return admin;
			}
		}
		return null;
	}

}
