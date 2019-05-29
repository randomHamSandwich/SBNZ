package treca;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.baske.model.Bolest;
import com.baske.model.Lek;
import com.baske.model.Lekar;
import com.baske.model.MogucaBolest;
import com.baske.model.Pacient;
import com.baske.model.Pregled;
import com.baske.model.PrepisanaTerapija;
import com.baske.model.Simptomi;
import com.baske.model.SpecificanSimptom;
import com.baske.model.TipLeka;

public class AkutnaBubreznaBolestTest {
	
	//ne korisitm ovaj test jer mi ne dodajemo samo terapiju nego sam preporuku)
	//@Test
	public void akutnaBubreznaBolestTest() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession("ksession-rules");

		HashSet<Simptomi> s = new HashSet<>();

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

	//	Pregled p = new Pregled(s, null);
	//	Pregled p2 = new Pregled(s, null);
		
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
		//s6RegularniSimptomi.add(Simptomi.OTICANJE_OKO_OCIJU);
		s6RegularniSimptomi.add(Simptomi.OTOCI_NOGU_I_ZGLOBOVA);
		s6RegularniSimptomi.add(Simptomi.DIJAREJA);
		
		HashSet<SpecificanSimptom> s6Specifnici = new HashSet<>();
		s6Specifnici.add(SpecificanSimptom.OPORAVLJA_SE_OD_OPERACIJE);
		s6Specifnici.add(SpecificanSimptom.U_POSLEDNJIH_14_DAN_POVISENA_TEMPERATURA);
		s6Specifnici.add(SpecificanSimptom.U_POSLEDNJIH_21_DAN_PREPISANI_ANTIBIOTICI);
		Bolest akutnaBubreznaBolest = new Bolest("akutnaBubreznaBolest", s6RegularniSimptomi, s6Specifnici);
		
		
		HashSet<MogucaBolest> moguceBolesti = new HashSet<>();
		MogucaBolest bolest1 = new MogucaBolest("hronicnaBubreznaBolest", -1.0);
		MogucaBolest bolest2 = new MogucaBolest("akutnaBubreznaBolest", -1.0);
//		MogucaBolest bolest3 = new MogucaBolest("upalaKrajnika", -1.0);
//		MogucaBolest bolest4 = new MogucaBolest("sinusnaInfekcija", -1.0);

		moguceBolesti.add(bolest1);
		moguceBolesti.add(bolest2);

		Pacient pacient1 = new Pacient("Pera", "Peric");
		Lek andol = new Lek(TipLeka.ANTIBIOTIK, null, "andol");
		ArrayList<Lek > prepisaniLekovi = new ArrayList<>();
		prepisaniLekovi.add(andol);
		
		PrepisanaTerapija terapija = new PrepisanaTerapija(groznica, prepisaniLekovi, null, LocalDate.now().minusDays(3));
		terapija.setLerarIliAdmin(new Lekar("Aleksa", "aleksic", "n", "n"));
		pacient1.getTerapije().add(terapija);
	//	pacient1.getTerapije().add(new PrepisanaTerapija(groznica, prepisaniLekovi, null, LocalDate.now().minusDays(3)));

		HashSet<Simptomi> sTemp = new HashSet<>(s6RegularniSimptomi);
		sTemp.add(Simptomi.TEMPERATURA_OD_40_DO_41);

		HashSet<SpecificanSimptom> sTempSpec = new HashSet<>();
		sTempSpec.add(SpecificanSimptom.OPORAVLJA_SE_OD_OPERACIJE);
		Pregled p = new Pregled(sTemp,sTempSpec, moguceBolesti, pacient1);

		// global za bolest
		kSession.setGlobal("prehlada", prehlada);
		kSession.setGlobal("groznica", groznica);
		kSession.setGlobal("upalaKrajnika", upalaKrajnika);
		kSession.setGlobal("sinusnaInfekcija", sinusnaInfekcija);
		kSession.setGlobal("hronicnaBubreznaBolest", hronicnaBubreznaBolest);
		kSession.setGlobal("akutnaBubreznaBolest", akutnaBubreznaBolest);
		//kSession.setGlobal(arg0, arg1);
		
		
		kSession.insert(p);
		kSession.getAgenda().getAgendaGroup("treca").setFocus();
		kSession.fireAllRules();

		assertEquals(akutnaBubreznaBolest, pacient1.getTerapije().get(1).getBolest());
	}
	
	
	@Test
	public void uPoslednjih14DanaPovishenaTemperaturaDodajSimptom() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession("ksession-rules");

		HashSet<Simptomi> s = new HashSet<>();

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

	//	Pregled p = new Pregled(s, null);
	//	Pregled p2 = new Pregled(s, null);
		
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
		s6RegularniSimptomi.add(Simptomi.OTICANJE_OKO_OCIJU);
		s6RegularniSimptomi.add(Simptomi.DIJAREJA);
		
		HashSet<SpecificanSimptom> s6Specifnici = new HashSet<>();
		s6Specifnici.add(SpecificanSimptom.OPORAVLJA_SE_OD_OPERACIJE);
		s6Specifnici.add(SpecificanSimptom.U_POSLEDNJIH_14_DAN_POVISENA_TEMPERATURA);
		s6Specifnici.add(SpecificanSimptom.U_POSLEDNJIH_21_DAN_PREPISANI_ANTIBIOTICI);
		Bolest akutnaBubreznaBolest = new Bolest("akutnaBubreznaBolest", s6RegularniSimptomi, s6Specifnici);
		
		
		HashSet<MogucaBolest> moguceBolesti = new HashSet<>();
		MogucaBolest bolest1 = new MogucaBolest("hronicnaBubreznaBolest", -1.0);
		MogucaBolest bolest2 = new MogucaBolest("akutnaBubreznaBolest", -1.0);
//		MogucaBolest bolest3 = new MogucaBolest("upalaKrajnika", -1.0);
//		MogucaBolest bolest4 = new MogucaBolest("sinusnaInfekcija", -1.0);

		moguceBolesti.add(bolest1);
		moguceBolesti.add(bolest2);

		Pacient pacient1 = new Pacient("Pera", "Peric");
		Lek andol = new Lek(TipLeka.ANTIBIOTIK, null, "andol");
		ArrayList<Lek > prepisaniLekovi = new ArrayList<>();
		prepisaniLekovi.add(andol);
		
		PrepisanaTerapija terapija = new PrepisanaTerapija(groznica, prepisaniLekovi, null, LocalDate.now().minusDays(3));
		terapija.setTemperatur(39);
		terapija.setLerarIliAdmin(new Lekar("Aleksa", "aleksic", "n", "n"));
		pacient1.getTerapije().add(terapija);
//		pacient1.getTerapije().add(new PrepisanaTerapija(groznica, prepisaniLekovi, null, LocalDate.now().minusDays(3)));

//		HashSet<Simptomi> sTemp = new HashSet<>(s6RegularniSimptomi);
//		sTemp.add(Simptomi.TEMPERATURA_OD_40_DO_41);

		HashSet<SpecificanSimptom> sTempSpec = new HashSet<>();
		sTempSpec.add(SpecificanSimptom.OPORAVLJA_SE_OD_OPERACIJE);
//		Pregled p = new Pregled(sTemp,sTempSpec, moguceBolesti, pacient1);
		Pregled p = new Pregled(s6RegularniSimptomi,sTempSpec, moguceBolesti, pacient1);

		// global za bolest
		kSession.setGlobal("prehlada", prehlada);
		kSession.setGlobal("groznica", groznica);
		kSession.setGlobal("upalaKrajnika", upalaKrajnika);
		kSession.setGlobal("sinusnaInfekcija", sinusnaInfekcija);
		kSession.setGlobal("hronicnaBubreznaBolest", hronicnaBubreznaBolest);
		kSession.setGlobal("akutnaBubreznaBolest", akutnaBubreznaBolest);
		//kSession.setGlobal(arg0, arg1);
		
		
		kSession.insert(p);
		kSession.getAgenda().getAgendaGroup("treca").setFocus();
		kSession.fireAllRules();

		assertEquals(true, p.getSpecificniSimptomi().contains(SpecificanSimptom.U_POSLEDNJIH_14_DAN_POVISENA_TEMPERATURA));
	}
	
	@Test
	public void uPoslednjih21DanPrepisaniAntibiotici() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession("ksession-rules");

		HashSet<Simptomi> s = new HashSet<>();

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

	//	Pregled p = new Pregled(s, null);
	//	Pregled p2 = new Pregled(s, null);
		
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
		//s6RegularniSimptomi.add(Simptomi.OTICANJE_OKO_OCIJU);
		s6RegularniSimptomi.add(Simptomi.OTOCI_NOGU_I_ZGLOBOVA);
		s6RegularniSimptomi.add(Simptomi.DIJAREJA);
		
		HashSet<SpecificanSimptom> s6Specifnici = new HashSet<>();
		s6Specifnici.add(SpecificanSimptom.OPORAVLJA_SE_OD_OPERACIJE);
		s6Specifnici.add(SpecificanSimptom.U_POSLEDNJIH_14_DAN_POVISENA_TEMPERATURA);
		s6Specifnici.add(SpecificanSimptom.U_POSLEDNJIH_21_DAN_PREPISANI_ANTIBIOTICI);
		Bolest akutnaBubreznaBolest = new Bolest("akutnaBubreznaBolest", s6RegularniSimptomi, s6Specifnici);
		
		
		HashSet<MogucaBolest> moguceBolesti = new HashSet<>();
		MogucaBolest bolest1 = new MogucaBolest("hronicnaBubreznaBolest", -1.0);
		MogucaBolest bolest2 = new MogucaBolest("akutnaBubreznaBolest", -1.0);
//		MogucaBolest bolest3 = new MogucaBolest("upalaKrajnika", -1.0);
//		MogucaBolest bolest4 = new MogucaBolest("sinusnaInfekcija", -1.0);

		moguceBolesti.add(bolest1);
		moguceBolesti.add(bolest2);

		Pacient pacient1 = new Pacient("Pera", "Peric");
		Lek andol = new Lek(TipLeka.ANTIBIOTIK, null, "andol");
		ArrayList<Lek > prepisaniLekovi = new ArrayList<>();
		prepisaniLekovi.add(andol);
		
		PrepisanaTerapija terapija = new PrepisanaTerapija(groznica, prepisaniLekovi, null, LocalDate.now().minusDays(3));
		terapija.setLerarIliAdmin(new Lekar("Aleksa", "aleksic", "n", "n"));
		pacient1.getTerapije().add(terapija);
		
//		pacient1.getTerapije().add(new PrepisanaTerapija(groznica, prepisaniLekovi, null, LocalDate.now().minusDays(3)));

		HashSet<Simptomi> sTemp = new HashSet<>(s6RegularniSimptomi);
		sTemp.add(Simptomi.TEMPERATURA_OD_40_DO_41);

		HashSet<SpecificanSimptom> sTempSpec = new HashSet<>();
		sTempSpec.add(SpecificanSimptom.OPORAVLJA_SE_OD_OPERACIJE);
		Pregled p = new Pregled(sTemp,sTempSpec, moguceBolesti, pacient1);

		// global za bolest
		kSession.setGlobal("prehlada", prehlada);
		kSession.setGlobal("groznica", groznica);
		kSession.setGlobal("upalaKrajnika", upalaKrajnika);
		kSession.setGlobal("sinusnaInfekcija", sinusnaInfekcija);
		kSession.setGlobal("hronicnaBubreznaBolest", hronicnaBubreznaBolest);
		kSession.setGlobal("akutnaBubreznaBolest", akutnaBubreznaBolest);
		//kSession.setGlobal(arg0, arg1);
		
		
		kSession.insert(p);
		kSession.getAgenda().getAgendaGroup("treca").setFocus();
		kSession.fireAllRules();

		assertEquals(true, p.getSpecificniSimptomi().contains(SpecificanSimptom.U_POSLEDNJIH_21_DAN_PREPISANI_ANTIBIOTICI));
	}

	
	@Test
	public void akutnaBubreznaBolestMozeBiti() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession("ksession-rules");

		HashSet<Simptomi> s = new HashSet<>();

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

	//	Pregled p = new Pregled(s, null);
	//	Pregled p2 = new Pregled(s, null);
		
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
		//s6RegularniSimptomi.add(Simptomi.OTICANJE_OKO_OCIJU);
		s6RegularniSimptomi.add(Simptomi.OTOCI_NOGU_I_ZGLOBOVA);
		s6RegularniSimptomi.add(Simptomi.DIJAREJA);
		
		HashSet<SpecificanSimptom> s6Specifnici = new HashSet<>();
		s6Specifnici.add(SpecificanSimptom.OPORAVLJA_SE_OD_OPERACIJE);
		s6Specifnici.add(SpecificanSimptom.U_POSLEDNJIH_14_DAN_POVISENA_TEMPERATURA);
		s6Specifnici.add(SpecificanSimptom.U_POSLEDNJIH_21_DAN_PREPISANI_ANTIBIOTICI);
		Bolest akutnaBubreznaBolest = new Bolest("akutnaBubreznaBolest", s6RegularniSimptomi, s6Specifnici);
		
		
		HashSet<MogucaBolest> moguceBolesti = new HashSet<>();
		MogucaBolest bolest1 = new MogucaBolest("hronicnaBubreznaBolest", -1.0);
		MogucaBolest bolest2 = new MogucaBolest("akutnaBubreznaBolest", -1.0);
//		MogucaBolest bolest3 = new MogucaBolest("upalaKrajnika", -1.0);
//		MogucaBolest bolest4 = new MogucaBolest("sinusnaInfekcija", -1.0);

		moguceBolesti.add(bolest1);
		moguceBolesti.add(bolest2);

		Pacient pacient1 = new Pacient("Pera", "Peric");
		Lek andol = new Lek(TipLeka.ANTIBIOTIK, null, "andol");
		ArrayList<Lek > prepisaniLekovi = new ArrayList<>();
		prepisaniLekovi.add(andol);
		
		
//		PrepisanaTerapija terapija =new PrepisanaTerapija(groznica, prepisaniLekovi, null, LocalDate.now().minusDays(3));
		PrepisanaTerapija terapija  = new PrepisanaTerapija(groznica, prepisaniLekovi, "", LocalDate.now().minusDays(3), 39, false);
		terapija.setLerarIliAdmin(new Lekar("Aleksa", "aleksic", "n", "n"));
		pacient1.getTerapije().add(terapija);
		
		//pacient1.getTerapije().add(new PrepisanaTerapija(groznica, prepisaniLekovi, null, LocalDate.now().minusDays(3)));

//		HashSet<Simptomi> sTemp = new HashSet<>(s6RegularniSimptomi);
//		sTemp.add(Simptomi.TEMPERATURA_OD_40_DO_41);

		HashSet<SpecificanSimptom> sTempSpec = new HashSet<>();
		sTempSpec.add(SpecificanSimptom.OPORAVLJA_SE_OD_OPERACIJE);
		//Pregled p = new Pregled(sTemp,sTempSpec, moguceBolesti, pacient1);
		
		sTempSpec.add(SpecificanSimptom.OPORAVLJA_SE_OD_OPERACIJE);
		Pregled p = new Pregled(s6RegularniSimptomi,sTempSpec, moguceBolesti, pacient1);

		// global za bolest
		kSession.setGlobal("prehlada", prehlada);
		kSession.setGlobal("groznica", groznica);
		kSession.setGlobal("upalaKrajnika", upalaKrajnika);
		kSession.setGlobal("sinusnaInfekcija", sinusnaInfekcija);
		kSession.setGlobal("hronicnaBubreznaBolest", hronicnaBubreznaBolest);
		kSession.setGlobal("akutnaBubreznaBolest", akutnaBubreznaBolest);
		//kSession.setGlobal(arg0, arg1);
		
		
		kSession.insert(p);
		kSession.getAgenda().getAgendaGroup("treca").setFocus();
		kSession.fireAllRules();

		for(MogucaBolest mmm : moguceBolesti) {
			if(mmm.getNaziv()=="akutnaBubreznaBolest") {
				assertEquals(1, mmm.getMogucnost());
			}
		}
		//assertEquals(1, p.getMoguceBolesti().);
		
		kSession.insert(p);

		
	}
	
	@Test
	public void uPoslednjih14NeceProci() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession("ksession-rules");

		HashSet<Simptomi> s = new HashSet<>();

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

	//	Pregled p = new Pregled(s, null);
	//	Pregled p2 = new Pregled(s, null);
		
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
		//s6RegularniSimptomi.add(Simptomi.OTICANJE_OKO_OCIJU);
		s6RegularniSimptomi.add(Simptomi.OTOCI_NOGU_I_ZGLOBOVA);
		s6RegularniSimptomi.add(Simptomi.DIJAREJA);
		
		HashSet<SpecificanSimptom> s6Specifnici = new HashSet<>();
		s6Specifnici.add(SpecificanSimptom.OPORAVLJA_SE_OD_OPERACIJE);
		s6Specifnici.add(SpecificanSimptom.U_POSLEDNJIH_14_DAN_POVISENA_TEMPERATURA);
		s6Specifnici.add(SpecificanSimptom.U_POSLEDNJIH_21_DAN_PREPISANI_ANTIBIOTICI);
		Bolest akutnaBubreznaBolest = new Bolest("akutnaBubreznaBolest", s6RegularniSimptomi, s6Specifnici);
		
		
		HashSet<MogucaBolest> moguceBolesti = new HashSet<>();
		MogucaBolest bolest1 = new MogucaBolest("hronicnaBubreznaBolest", -1.0);
		MogucaBolest bolest2 = new MogucaBolest("akutnaBubreznaBolest", -1.0);
//		MogucaBolest bolest3 = new MogucaBolest("upalaKrajnika", -1.0);
//		MogucaBolest bolest4 = new MogucaBolest("sinusnaInfekcija", -1.0);

		moguceBolesti.add(bolest1);
		moguceBolesti.add(bolest2);

		Pacient pacient1 = new Pacient("Pera", "Peric");
		Lek andol = new Lek(TipLeka.ANTIBIOTIK, null, "andol");
		ArrayList<Lek > prepisaniLekovi = new ArrayList<>();
		prepisaniLekovi.add(andol);
		
		
//		PrepisanaTerapija terapija =new PrepisanaTerapija(groznica, prepisaniLekovi, null, LocalDate.now().minusDays(3));
		PrepisanaTerapija terapija  = new PrepisanaTerapija(groznica, prepisaniLekovi, "", LocalDate.now().minusDays(16), 39, false);
		terapija.setLerarIliAdmin(new Lekar("Aleksa", "aleksic", "n", "n"));
		pacient1.getTerapije().add(terapija);
		
		//pacient1.getTerapije().add(new PrepisanaTerapija(groznica, prepisaniLekovi, null, LocalDate.now().minusDays(3)));

//		HashSet<Simptomi> sTemp = new HashSet<>(s6RegularniSimptomi);
//		sTemp.add(Simptomi.TEMPERATURA_OD_40_DO_41);

		HashSet<SpecificanSimptom> sTempSpec = new HashSet<>();
		sTempSpec.add(SpecificanSimptom.OPORAVLJA_SE_OD_OPERACIJE);
		//Pregled p = new Pregled(sTemp,sTempSpec, moguceBolesti, pacient1);
		
		sTempSpec.add(SpecificanSimptom.OPORAVLJA_SE_OD_OPERACIJE);
		Pregled p = new Pregled(s6RegularniSimptomi,sTempSpec, moguceBolesti, pacient1);
			
		// global za bolest
		kSession.setGlobal("prehlada", prehlada);
		kSession.setGlobal("groznica", groznica);
		kSession.setGlobal("upalaKrajnika", upalaKrajnika);
		kSession.setGlobal("sinusnaInfekcija", sinusnaInfekcija);
		kSession.setGlobal("hronicnaBubreznaBolest", hronicnaBubreznaBolest);
		kSession.setGlobal("akutnaBubreznaBolest", akutnaBubreznaBolest);
		//kSession.setGlobal(arg0, arg1);
		
		
		kSession.insert(p);
		kSession.getAgenda().getAgendaGroup("treca").setFocus();
		kSession.fireAllRules();
		//
		//jer imamo 6 regular i 2 spec
		//6*1+2*2/10 a imamo jedan manje spec pa je 6+2/10
		for(MogucaBolest mmm : moguceBolesti) {
			if(mmm.getNaziv()=="akutnaBubreznaBolest") {
				assertEquals(0.8, mmm.getMogucnost());
			}
		}
		//assertEquals(1, p.getMoguceBolesti().);
		

		
	}
	

}