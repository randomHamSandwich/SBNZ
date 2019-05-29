package duga;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.HashSet;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.baske.model.Bolest;
import com.baske.model.MogucaBolest;
import com.baske.model.Pacient;
import com.baske.model.Pregled;
import com.baske.model.PrepisanaTerapija;
import com.baske.model.Simptomi;

public class HiperttenziaTest {


	@Test
	public void test1() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession("ksession-rules");
		
		HashSet<Simptomi> s = new HashSet<>();

		s.add(Simptomi.CESTO_URINIRANJE);
		s.add(Simptomi.GUBITAK_TELESNE_TEZINE);
		s.add(Simptomi.ZAMOR);
		s.add(Simptomi.MUCNINA_I_POVRACANJE);
	  	Bolest dijabetes = new Bolest("dijabetes", s, null);
	  	
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
	  	s3.add(Simptomi.U_POSLEDNJIH_6_MESECI_ZABELEZNO_BAREM_10_SLUCAJEVA_UKOJEM_JE_PACIJENT_IMAO_VISOK_PRITISAK);
	  	Bolest hipertenzija = new Bolest("hipertenzija", s3, null);
	
		HashSet<MogucaBolest> moguceBolesti = new HashSet<>();
		MogucaBolest bolest1 = new MogucaBolest("dijabetes", -1.0);
		MogucaBolest bolest2 = new MogucaBolest("hipertenzija", -1.0);

		moguceBolesti.add(bolest1);
		moguceBolesti.add(bolest2);

     	Pacient pacient1 = new Pacient("Pera", "Peric");
//     	pacient1.getTerapije().add(new PrepisanaTerapija(groznica, null, null, LocalDate.of(1991, 2, 2)));
//     	pacient1.getTerapije().add(new PrepisanaTerapija(groznica, null, null, LocalDate.now().minusDays(5)));
     	pacient1.getTerapije().add(new PrepisanaTerapija(groznica, null, null, LocalDate.now().minusDays(2), 37.0, true));
    	pacient1.getTerapije().add(new PrepisanaTerapija(groznica, null, null, LocalDate.now().minusMonths(5), 37.0, true));
    	pacient1.getTerapije().add(new PrepisanaTerapija(groznica, null, null, LocalDate.now().minusMonths(3), 37.0, true));
    	pacient1.getTerapije().add(new PrepisanaTerapija(groznica, null, null, LocalDate.now().minusDays(23), 37.0, true));
    	pacient1.getTerapije().add(new PrepisanaTerapija(groznica, null, null, LocalDate.now().minusDays(44), 37.0, true));
    	pacient1.getTerapije().add(new PrepisanaTerapija(groznica, null, null, LocalDate.now().minusDays(12), 37.0, true));
    	pacient1.getTerapije().add(new PrepisanaTerapija(groznica, null, null, LocalDate.now().minusDays(1), 37.0, true));
    	pacient1.getTerapije().add(new PrepisanaTerapija(groznica, null, null, LocalDate.now().minusDays(32), 37.0, true));
    	pacient1.getTerapije().add(new PrepisanaTerapija(groznica, null, null, LocalDate.now().minusDays(12), 37.0, true));
    	pacient1.getTerapije().add(new PrepisanaTerapija(groznica, null, null, LocalDate.now().minusDays(32), 37.0, true));

		HashSet<Simptomi> sTemp = new HashSet<>();
		//ne saljemo U_POSLEDNJIH_6_MESECI_ZABELEZNO_BAREM_10_SLUCAJEVA_UKOJEM_JE_PACIJENT_IMAO_VISOK_PRITISAK
		//jer to treba da se zakljuci
		sTemp.add(Simptomi.CESTO_URINIRANJE);
		sTemp.add(Simptomi.KIJANJE);
		Pregled p = new Pregled(sTemp, null, moguceBolesti, pacient1);

		// global za bolest
		kSession.setGlobal("dijabetes", dijabetes);
		kSession.setGlobal("hipertenzija", hipertenzija);
		// global za odabir

		kSession.insert(p);
		kSession.getAgenda().getAgendaGroup("druga").setFocus();
		kSession.fireAllRules();
		// get(1) jer ima vec jednu bolest koju smo setovali da bi videli za 60 dana
		int tempTemp = pacient1.getTerapije().size()-1;
		//program ce samo da predlozi bolest, a sam lekar ce odluciti koja bolest je upitanju
		//assertEquals(hipertenzija, pacient1.getTerapije().get(tempTemp).getBolest());
		
		for (MogucaBolest mmm : moguceBolesti) {
			if (mmm.getNaziv() == "hipertenzija") {
				assertEquals(1.0, mmm.getMogucnost());
			}
		}
	
	}

}
