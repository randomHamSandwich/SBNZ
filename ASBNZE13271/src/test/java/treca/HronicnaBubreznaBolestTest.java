package treca;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.HashSet;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.baske.model.Bolest;
import com.baske.model.Lekar;
import com.baske.model.MogucaBolest;
import com.baske.model.Pacient;
import com.baske.model.Pregled;
import com.baske.model.PrepisanaTerapija;
import com.baske.model.Simptomi;
import com.baske.model.SpecificanSimptom;

public class HronicnaBubreznaBolestTest {
	//ne dodajemo terapiju tako da netreba ova provera
	//@Test
	public void hronicnaBubreznaBolestTest() {
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

		// Pregled p = new Pregled(s, null);
		// Pregled p2 = new Pregled(s, null);

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

		HashSet<Simptomi> sHiperenzija = new HashSet<>();
		sHiperenzija.add(
				Simptomi.U_POSLEDNJIH_6_MESECI_ZABELEZNO_BAREM_10_SLUCAJEVA_UKOJEM_JE_PACIJENT_IMAO_VISOK_PRITISAK);
		Bolest hipertenzija = new Bolest("hipertenzija", sHiperenzija, null);

		HashSet<Simptomi> sDijabetes = new HashSet<>();
		sDijabetes.add(Simptomi.CESTO_URINIRANJE);
		sDijabetes.add(Simptomi.GUBITAK_TELESNE_TEZINE);
		sDijabetes.add(Simptomi.ZAMOR);
		sDijabetes.add(Simptomi.MUCNINA_I_POVRACANJE);
		Bolest dijabetes = new Bolest("dijabetes", sDijabetes, null);

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
		pacient1.getTerapije()
				.add(new PrepisanaTerapija(hipertenzija, null, null, LocalDate.now().minusMonths(9), 36, true));
		pacient1.getTerapije().add(new PrepisanaTerapija(dijabetes, null, null, LocalDate.now(), 35, false));

		HashSet<Simptomi> sTemp = new HashSet<>(s5RegularniSimptomi);
		sTemp.add(Simptomi.TEMPERATURA_OD_40_DO_41);
		HashSet<SpecificanSimptom> sTempSpec = new HashSet<>();

		Pregled p = new Pregled(sTemp, sTempSpec, moguceBolesti, pacient1);

		// global za bolest
		kSession.setGlobal("prehlada", prehlada);
		kSession.setGlobal("groznica", groznica);
		kSession.setGlobal("upalaKrajnika", upalaKrajnika);
		kSession.setGlobal("sinusnaInfekcija", sinusnaInfekcija);
		kSession.setGlobal("hronicnaBubreznaBolest", hronicnaBubreznaBolest);
		kSession.setGlobal("akutnaBubreznaBolest", akutnaBubreznaBolest);
		kSession.setGlobal("hipertenzija", hipertenzija);
		kSession.setGlobal("dijabetes", dijabetes);

		// kSession.setGlobal(arg0, arg1);

		kSession.insert(p);
		kSession.getAgenda().getAgendaGroup("treca").setFocus();
		kSession.fireAllRules();

		assertEquals(hronicnaBubreznaBolest, pacient1.getTerapije().get(2).getBolest());
	}

	@Test
	public void hipertenzijaVisheOd6MeseciDodajSimptom() {
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

		// Pregled p = new Pregled(s, null);
		// Pregled p2 = new Pregled(s, null);

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

		HashSet<Simptomi> sHiperenzija = new HashSet<>();
		sHiperenzija.add(
				Simptomi.U_POSLEDNJIH_6_MESECI_ZABELEZNO_BAREM_10_SLUCAJEVA_UKOJEM_JE_PACIJENT_IMAO_VISOK_PRITISAK);
		Bolest hipertenzija = new Bolest("hipertenzija", sHiperenzija, null);

		HashSet<Simptomi> sDijabetes = new HashSet<>();
		sDijabetes.add(Simptomi.CESTO_URINIRANJE);
		sDijabetes.add(Simptomi.GUBITAK_TELESNE_TEZINE);
		sDijabetes.add(Simptomi.ZAMOR);
		sDijabetes.add(Simptomi.MUCNINA_I_POVRACANJE);
		Bolest dijabetes = new Bolest("dijabetes", sDijabetes, null);

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
		
		PrepisanaTerapija terapija = new PrepisanaTerapija(hipertenzija, null, null, LocalDate.now().minusMonths(9), 36, true);
		terapija.setLerarIliAdmin(new Lekar("Aleksa", "aleksic", "n", "n"));
		pacient1.getTerapije().add(terapija);
		
		//pacient1.getTerapije().add(new PrepisanaTerapija(hipertenzija, null, null, LocalDate.now().minusMonths(9), 36, true));
//		pacient1.getTerapije().add(new PrepisanaTerapija(dijabetes, null, null, LocalDate.now(), 35, false));

		HashSet<Simptomi> sTemp = new HashSet<>(s5RegularniSimptomi);
		sTemp.add(Simptomi.TEMPERATURA_OD_40_DO_41);
		HashSet<SpecificanSimptom> sTempSpec = new HashSet<>();

		Pregled p = new Pregled(sTemp, sTempSpec, moguceBolesti, pacient1);

		// global za bolest
		kSession.setGlobal("prehlada", prehlada);
		kSession.setGlobal("groznica", groznica);
		kSession.setGlobal("upalaKrajnika", upalaKrajnika);
		kSession.setGlobal("sinusnaInfekcija", sinusnaInfekcija);
		kSession.setGlobal("hronicnaBubreznaBolest", hronicnaBubreznaBolest);
		kSession.setGlobal("akutnaBubreznaBolest", akutnaBubreznaBolest);
		kSession.setGlobal("hipertenzija", hipertenzija);
		kSession.setGlobal("dijabetes", dijabetes);

		// kSession.setGlobal(arg0, arg1);

		kSession.insert(p);
		kSession.getAgenda().getAgendaGroup("treca").setFocus();
		kSession.fireAllRules();

		assertEquals(true,
				p.getSpecificniSimptomi().contains(SpecificanSimptom.PACIENT_BOLUJE_OD_HIPERTENZIJE_VISE_OD_6_MESECI));
	}

	@Test
	public void pacijentBolujeOdDijabetesaDodajSimptom() {
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

		// Pregled p = new Pregled(s, null);
		// Pregled p2 = new Pregled(s, null);

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

		HashSet<Simptomi> sHiperenzija = new HashSet<>();
		sHiperenzija.add(
				Simptomi.U_POSLEDNJIH_6_MESECI_ZABELEZNO_BAREM_10_SLUCAJEVA_UKOJEM_JE_PACIJENT_IMAO_VISOK_PRITISAK);
		Bolest hipertenzija = new Bolest("hipertenzija", sHiperenzija, null);

		HashSet<Simptomi> sDijabetes = new HashSet<>();
		sDijabetes.add(Simptomi.CESTO_URINIRANJE);
		sDijabetes.add(Simptomi.GUBITAK_TELESNE_TEZINE);
		sDijabetes.add(Simptomi.ZAMOR);
		sDijabetes.add(Simptomi.MUCNINA_I_POVRACANJE);
		Bolest dijabetes = new Bolest("dijabetes", sDijabetes, null);

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
//			MogucaBolest bolest3 = new MogucaBolest("upalaKrajnika", -1.0);
//			MogucaBolest bolest4 = new MogucaBolest("sinusnaInfekcija", -1.0);

		moguceBolesti.add(bolest1);
		moguceBolesti.add(bolest2);

		Pacient pacient1 = new Pacient("Pera", "Peric");
//			pacient1.getTerapije().add(new PrepisanaTerapija(hipertenzija, null, null, LocalDate.now().minusMonths(9), 36, true));
		
		PrepisanaTerapija terapija = new PrepisanaTerapija(dijabetes, null, null, LocalDate.now(), 35, false);
		terapija.setLerarIliAdmin(new Lekar("Aleksa", "aleksic", "n", "n"));
		pacient1.getTerapije().add(terapija);
		
//		pacient1.getTerapije().add(new PrepisanaTerapija(dijabetes, null, null, LocalDate.now(), 35, false));

		HashSet<Simptomi> sTemp = new HashSet<>(s5RegularniSimptomi);
		sTemp.add(Simptomi.TEMPERATURA_OD_40_DO_41);
		HashSet<SpecificanSimptom> sTempSpec = new HashSet<>();

		Pregled p = new Pregled(sTemp, sTempSpec, moguceBolesti, pacient1);

		// global za bolest
		kSession.setGlobal("prehlada", prehlada);
		kSession.setGlobal("groznica", groznica);
		kSession.setGlobal("upalaKrajnika", upalaKrajnika);
		kSession.setGlobal("sinusnaInfekcija", sinusnaInfekcija);
		kSession.setGlobal("hronicnaBubreznaBolest", hronicnaBubreznaBolest);
		kSession.setGlobal("akutnaBubreznaBolest", akutnaBubreznaBolest);
		kSession.setGlobal("hipertenzija", hipertenzija);
		kSession.setGlobal("dijabetes", dijabetes);

		// kSession.setGlobal(arg0, arg1);

		kSession.insert(p);
		kSession.getAgenda().getAgendaGroup("treca").setFocus();
		kSession.fireAllRules();

		assertEquals(true, p.getSpecificniSimptomi().contains(SpecificanSimptom.PACIENT_BOLUJE_OD_DIJABETESA));
	}

	@Test
	public void hronicnaBubreznaBolestMozeBiti() {
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

		// Pregled p = new Pregled(s, null);
		// Pregled p2 = new Pregled(s, null);

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

		HashSet<Simptomi> sHiperenzija = new HashSet<>();
		sHiperenzija.add(
				Simptomi.U_POSLEDNJIH_6_MESECI_ZABELEZNO_BAREM_10_SLUCAJEVA_UKOJEM_JE_PACIJENT_IMAO_VISOK_PRITISAK);
		Bolest hipertenzija = new Bolest("hipertenzija", sHiperenzija, null);

		HashSet<Simptomi> sDijabetes = new HashSet<>();
		sDijabetes.add(Simptomi.CESTO_URINIRANJE);
		sDijabetes.add(Simptomi.GUBITAK_TELESNE_TEZINE);
		sDijabetes.add(Simptomi.ZAMOR);
		sDijabetes.add(Simptomi.MUCNINA_I_POVRACANJE);
		Bolest dijabetes = new Bolest("dijabetes", sDijabetes, null);

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
		
		PrepisanaTerapija terapija = new PrepisanaTerapija(hipertenzija, null, null, LocalDate.now().minusMonths(9), 36, true);
		terapija.setLerarIliAdmin(new Lekar("Aleksa", "aleksic", "n", "n"));
		pacient1.getTerapije().add(terapija);
		
		PrepisanaTerapija terapija2222 = new PrepisanaTerapija(dijabetes, null, null, LocalDate.now(), 35, false);
		terapija2222.setLerarIliAdmin(new Lekar("Aleksa", "aleksic", "n", "n"));
		pacient1.getTerapije().add(terapija2222);
		
		
		//pacient1.getTerapije().add(new PrepisanaTerapija(hipertenzija, null, null, LocalDate.now().minusMonths(9), 36, true));
		//pacient1.getTerapije().add(new PrepisanaTerapija(dijabetes, null, null, LocalDate.now(), 35, false));

		HashSet<Simptomi> sTemp = new HashSet<>(s5RegularniSimptomi);
		sTemp.add(Simptomi.TEMPERATURA_OD_40_DO_41);
		HashSet<SpecificanSimptom> sTempSpec = new HashSet<>();

		Pregled p = new Pregled(sTemp, sTempSpec, moguceBolesti, pacient1);

		// global za bolest
		kSession.setGlobal("prehlada", prehlada);
		kSession.setGlobal("groznica", groznica);
		kSession.setGlobal("upalaKrajnika", upalaKrajnika);
		kSession.setGlobal("sinusnaInfekcija", sinusnaInfekcija);
		kSession.setGlobal("hronicnaBubreznaBolest", hronicnaBubreznaBolest);
		kSession.setGlobal("akutnaBubreznaBolest", akutnaBubreznaBolest);
		kSession.setGlobal("hipertenzija", hipertenzija);
		kSession.setGlobal("dijabetes", dijabetes);

		// kSession.setGlobal(arg0, arg1);

		kSession.insert(p);
		kSession.getAgenda().getAgendaGroup("treca").setFocus();
		kSession.fireAllRules();

		for(MogucaBolest mmm : moguceBolesti) {
			if(mmm.getNaziv()=="hronicnaBubreznaBolest") {
				assertEquals(1, mmm.getMogucnost());
			}
		}
	}
}
