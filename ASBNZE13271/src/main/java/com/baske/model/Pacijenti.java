package com.baske.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Pacijenti implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1830403529490688862L;
	ArrayList<Pacient> pacijenti;

	public ArrayList<Pacient> getPacijenti() {
		return pacijenti;
	}

	public void setPacijenti(ArrayList<Pacient> pacijenti) {
		this.pacijenti = pacijenti;
	}

	public Pacijenti() {
		super();
		// TODO Auto-generated constructor stub
		pacijenti = new ArrayList<>();
	}
	
	public void addPacijent(Pacient p) {
		pacijenti.add(p);
	}

	public void addWithIncrementedId(Pacient p) {
		// imamo vishe pacijenata, obrishemo jednog, pa dodamo drugog, dobijamo isti id
		p.setId(pacijenti.size() + 1);
		//p.setId(pacijenti.size());

		for (Pacient pacijent : pacijenti) {
			while (pacijent.getId() == p.getId()) {
				int tempID = p.getId();
				p.setId(++tempID);
			}
		}
		pacijenti.add(p);
	}

//	public boolean removePacijent(Pacient p) {
//		if (pacijenti.remove(p)) {
//			System.out.println("obrisan pacijent " +p);
//			return true;
//			
//		} else {
//			System.out.println("xxxxxxxxxxxxxxxxx NIJE!!!! obrisan pacijent " +p);
//			return false;
//		}
//	}

	public boolean removePacijent(Pacient p) {
		for (Pacient pacijent : pacijenti) {
			if (pacijent.getId() == p.getId()) {
				pacijenti.remove(pacijent);
				return true;
			}
		}
		return false;
	}
	
	public boolean removePacijent(int i) {
		for (Pacient pacijent : pacijenti) {
			if (pacijent.getId() == i) {
				pacijenti.remove(pacijent);
				return true;
			}
		}
		return false;
	}

	public Pacijenti readPacijenti() throws Exception {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("pacijenti.dat"));
		Pacijenti p = (Pacijenti) in.readObject();
		in.close();
		System.out.println("trebalo bi da je procitao");
		System.out.println(p.getPacijenti());
		return p;

	}

	public void writePacijenti(Pacijenti pac) throws Exception {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("pacijenti.dat"));
			out.writeObject(pac);
			out.close();
			System.out.println("snimio");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
}
