package com.baske.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.HashSet;

import com.baske.model.Admin;
import com.baske.model.Administratori;
import com.baske.model.Bolest;
import com.baske.model.Bolesti;
import com.baske.model.Lek;
import com.baske.model.Lekar;
import com.baske.model.Lekari;
import com.baske.model.Lekovi;
import com.baske.model.MogucaBolest;
import com.baske.model.Pacient;
import com.baske.model.Pacijenti;
import com.baske.model.PrepisanaTerapija;
import com.baske.model.SastojakLeka;
import com.baske.model.Simptomi;
import com.baske.model.SpecificanSimptom;
import com.baske.model.TipLeka;

public class UbaciPodatke {

	public static void main(String[] args) {
		
		HashSet<Simptomi> s = new HashSet<>();
		//Prva grupa
		s.add(Simptomi.CURENJE_IZ_NOSA);
		s.add(Simptomi.BOL_U_GRLU);
		s.add(Simptomi.GLAVOBOLJA);
		s.add(Simptomi.KIJANJE);
		s.add(Simptomi.KASALJ);
		Bolest prehlada = new Bolest("prehlada", s, null);

		HashSet<Simptomi> s2 = new HashSet<>();
		s2.add(Simptomi.KIJANJE);
		s2.add(Simptomi.BOL_U_GRLU);
		s2.add(Simptomi.KASALJ);
		s2.add(Simptomi.TEMPERATURA_VECA_OD_38);
		s2.add(Simptomi.CURENJE_IZ_NOSA);
		s2.add(Simptomi.GLAVOBOLJA);
		s2.add(Simptomi.DRHTAVICA);
		Bolest groznica = new Bolest("groznica", s2, null);

		HashSet<Simptomi> s3 = new HashSet<>();
		s3.add(Simptomi.BOL_U_GRLU);
		s3.add(Simptomi.BOL_KOJI_SE_SIRI_OD_USIJU);
		s3.add(Simptomi.GLAVOBOLJA);
		s3.add(Simptomi.TEMPERATURA_OD_40_DO_41);
		s3.add(Simptomi.DRHTAVICA);
		s3.add(Simptomi.GUBITAK_APETITA);
		s3.add(Simptomi.UMOR);
		s3.add(Simptomi.ZUTI_SEKRET_IZ_NOSA);
		Bolest upalaKrajnika = new Bolest("upalaKrajnika", s3, null);

		HashSet<Simptomi> s4 = new HashSet<>();
		s4.add(Simptomi.OTICANJE_OKO_OCIJU);
		s4.add(Simptomi.GLAVOBOLJA);
		s4.add(Simptomi.ZUTI_SEKRET_IZ_NOSA);
		s4.add(Simptomi.BOL_U_GRLU);
		s4.add(Simptomi.TEMPERATURA_VECA_OD_38);
		s4.add(Simptomi.KASALJ);
		s4.add(Simptomi.BOLOVANJE_OD_PREHLADE_ILI_GROZNICE_U_POSEDNJIH_60_DANA);
		Bolest sinusnaInfekcija = new Bolest("sinusnaInfekcija", s4, null);
		
		
		//Druga grupa
		
		HashSet<Simptomi> sDijabetes = new HashSet<>();
		sDijabetes.add(Simptomi.CESTO_URINIRANJE);
		sDijabetes.add(Simptomi.GUBITAK_TELESNE_TEZINE);
		sDijabetes.add(Simptomi.ZAMOR);
		sDijabetes.add(Simptomi.MUCNINA_I_POVRACANJE);
	  	Bolest dijabetes = new Bolest("dijabetes", sDijabetes, null);
		
	  	HashSet<Simptomi> sHipertenzija = new HashSet<>();
	  	sHipertenzija.add(Simptomi.U_POSLEDNJIH_6_MESECI_ZABELEZNO_BAREM_10_SLUCAJEVA_UKOJEM_JE_PACIJENT_IMAO_VISOK_PRITISAK);
	  	Bolest hipertenzija = new Bolest("hipertenzija", sHipertenzija, null);
		
		//Treca grupa
	  	HashSet<Simptomi> s5RegularniSimptomi = new HashSet<>();
		s5RegularniSimptomi.add(Simptomi.ZAMOR);
		s5RegularniSimptomi.add(Simptomi.NOCTURIA);
		s5RegularniSimptomi.add(Simptomi.OTOCI_NOGU_I_ZGLOBOVA);
		s5RegularniSimptomi.add(Simptomi.GUSENJE);
		s5RegularniSimptomi.add(Simptomi.BOL_U_GRUDIMA);
		
		HashSet<SpecificanSimptom> s5Specificni = new HashSet<>();
		s5Specificni.add(SpecificanSimptom.PACIENT_BOLUJE_OD_DIJABETESA);
		s5Specificni.add(SpecificanSimptom.PACIENT_BOLUJE_OD_HIPERTENZIJE_VISE_OD_6_MESECI);
		Bolest hronicnaBubreznaBolest = new Bolest("hronicnaBubreznaBolest", s5RegularniSimptomi, s5Specificni);
		
		
		HashSet<Simptomi> s6RegularniSimptomi = new HashSet<>();
		s6RegularniSimptomi.add(Simptomi.ZAMOR);
		s6RegularniSimptomi.add(Simptomi.GUSENJE);
		s6RegularniSimptomi.add(Simptomi.OTOCI_NOGU_I_ZGLOBOVA);
		s6RegularniSimptomi.add(Simptomi.DIJAREJA);
		
		HashSet<SpecificanSimptom> s6Specifnici = new HashSet<>();
		s6Specifnici.add(SpecificanSimptom.OPORAVLJA_SE_OD_OPERACIJE);
		s6Specifnici.add(SpecificanSimptom.U_POSLEDNJIH_14_DAN_POVISENA_TEMPERATURA);
		s6Specifnici.add(SpecificanSimptom.U_POSLEDNJIH_21_DAN_PREPISANI_ANTIBIOTICI);
		Bolest akutnaBubreznaBolest = new Bolest("akutnaBubreznaBolest", s6RegularniSimptomi, s6Specifnici);
		
		
		
		Bolesti bolesti = new Bolesti();
		bolesti.getBolesti().add(prehlada);
		bolesti.getBolesti().add(groznica);
		bolesti.getBolesti().add(upalaKrajnika);
		bolesti.getBolesti().add(sinusnaInfekcija);
		bolesti.getBolesti().add(dijabetes);
		bolesti.getBolesti().add(hipertenzija);
		bolesti.getBolesti().add(hronicnaBubreznaBolest);
		bolesti.getBolesti().add(akutnaBubreznaBolest);
		System.out.println("pre try ");
		

		
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("bolesti.dat"));
			out.writeObject(bolesti);
			out.close();
			System.out.println("snimio");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("bolesti.dat"));
			Bolesti b = (Bolesti)in.readObject();
			in.close();
			System.out.println("trebalo bi da je procitao");
			System.out.println(b.getBolesti());
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Pacient pacient1 = new Pacient("Pera", "Peric");
		pacient1.addAlergijaNaSastojak(SastojakLeka.ZELATIN.toString());
		Pacient pacient2 = new Pacient("Mitar", "Mitrovic");
		Pacient pacient3 = new Pacient("Neko", "Nekic");
		
		Pacijenti pac = new Pacijenti();
		pac.addWithIncrementedId(pacient1);
		pac.addWithIncrementedId(pacient2);
		pac.addWithIncrementedId(pacient3);
		
		Lekar lekar1 = new Lekar("Leonard", "Maxwell", "l1", "l1");
		Lekar lekar2 = new Lekar("Laktomir", "Lemic", "l2", "l2");
		Lekar lekar3 = new Lekar("Laza" , "Mokic", "l3", "l3");
		
	//	PrepisanaTerapija terapijaTemp =new PrepisanaTerapija(groznica, null, null, LocalDate.now().minusWeeks(3), 43, true);
	//	terapijaTemp.setLerarIliAdmin(lekar1);
	//	pacient1.getTerapije().add(terapijaTemp);
		
		
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
		
		
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("pacijenti.dat"));
			Pacijenti p = (Pacijenti)in.readObject();
			in.close();
			System.out.println("trebalo bi da je procitao");
			System.out.println(p.getPacijenti());
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HashSet<SastojakLeka> sastojciFarin = new HashSet<>();
		sastojciFarin.add(SastojakLeka.VARFARIN_NATRIJUM);
		sastojciFarin.add(SastojakLeka.LAKTOZA_MONOHIDRAT);
		sastojciFarin.add(SastojakLeka.KUKURUZNI_SKROB);
		sastojciFarin.add(SastojakLeka.CELULOZA_MIKROKRISTAL);
		sastojciFarin.add(SastojakLeka.POVIDON);
		sastojciFarin.add(SastojakLeka.MAGNEZIJUM_STEARAT);
		
		Lek farin = new Lek(TipLeka.OSTALO, sastojciFarin, "Farin");
		
		
		HashSet<SastojakLeka> sastojciAndol = new HashSet<>();
		sastojciAndol.add(SastojakLeka.ACETILSALICILNE_KISELINE);
		sastojciAndol.add(SastojakLeka.MAGNEZIJUM_HIDROKSID);
		sastojciAndol.add(SastojakLeka.POVIDON_K_30);
		sastojciAndol.add(SastojakLeka.KROSPOVIDON);
		sastojciAndol.add(SastojakLeka.MAGNEZIJUM_STEARAT);
		
		Lek andol = new Lek(TipLeka.ANELGETIK, sastojciAndol, "Andol");
		
		HashSet<SastojakLeka> sastojciPalitrex = new HashSet<>();
		sastojciPalitrex.add(SastojakLeka.CEFALEKSIN_MONOHIDRAT);
		sastojciPalitrex.add(SastojakLeka.CELULOZA_MIKROKRISTAL);
		sastojciPalitrex.add(SastojakLeka.MAGNEZIJUM_STEARAT);
		sastojciPalitrex.add(SastojakLeka.ZELATIN);
		sastojciPalitrex.add(SastojakLeka.TITAN_DIOKSID_E171);
		sastojciPalitrex.add(SastojakLeka.BOJA_PATENT_BLUE_E131);
		sastojciPalitrex.add(SastojakLeka.BOJA_ERYTROSINE_E127);
		
		Lek palitrex = new Lek(TipLeka.ANTIBIOTIK, sastojciPalitrex, "Palitrex");
		
		Lekovi lekovi = new Lekovi();
		lekovi.getLekovi().add(farin);
		lekovi.getLekovi().add(andol);
		lekovi.getLekovi().add(palitrex);
		
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("lekovi.dat"));
			out.writeObject(lekovi);
			out.close();
			System.out.println("snimio");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("lekovi.dat"));
			Lekovi l = (Lekovi)in.readObject();
			in.close();
			System.out.println("trebalo bi da je procitao");
			System.out.println(l.getLekovi());
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		
		Lekari lekariXXX = new Lekari();
		lekariXXX.getLekari().add(lekar1);
		lekariXXX.getLekari().add(lekar2);
		lekariXXX.getLekari().add(lekar3);
		
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("lekari.dat"));
			out.writeObject(lekariXXX);
			out.close();
			System.out.println("snimio lekare");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("lekari.dat"));
			Lekari l = (Lekari)in.readObject();
			in.close();
			System.out.println("trebalo bi da je procitao");
			System.out.println(l.getLekari());
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Admin a1 = new  Admin("Aleksandar", "Alovic", "a1","a1");
		Admin a2 = new Admin("Antonije", "Aleksic", "a2", "a2");
		Admin a3 = new Admin("Ana", "Aramovic", "a3", "a3");
		Administratori admini = new Administratori();
		admini.getAdministratori().add(a1);
		admini.getAdministratori().add(a2);
		admini.getAdministratori().add(a3);
		
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("administratori.dat"));
			out.writeObject(admini);
			out.close();
			System.out.println("snimio administratore");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("administratori.dat"));
			Administratori a = (Administratori)in.readObject();
			in.close();
			System.out.println("trebalo bi da je procitao");
			System.out.println(a.getAdministratori());
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	

}
