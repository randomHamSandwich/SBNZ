package duga;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.baske.model.Bolest;
import com.baske.model.MogucaBolest;
import com.baske.model.Pacient;
import com.baske.model.Pregled;
import com.baske.model.Simptomi;

public class DijabetesTest {

	@Test
	public void dijabetesTest() {
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
	  	s2.add(Simptomi.U_POSLEDNJIH_6_MESECI_ZABELEZNO_BAREM_10_SLUCAJEVA_UKOJEM_JE_PACIJENT_IMAO_VISOK_PRITISAK);
	  	Bolest hipertenzija = new Bolest("hipertenzija", s2, null);
	
		HashSet<MogucaBolest> moguceBolesti = new HashSet<>();
		MogucaBolest bolest1 = new MogucaBolest("dijabetes", -1.0);
		MogucaBolest bolest2 = new MogucaBolest("hipertenzija", -1.0);

		moguceBolesti.add(bolest1);
		moguceBolesti.add(bolest2);

		Pacient pacient1 = new Pacient("Pera", "Peric");
		HashSet<Simptomi> sTemp = new HashSet<>();
		sTemp.add(Simptomi.CESTO_URINIRANJE);
		sTemp.add(Simptomi.GUBITAK_TELESNE_TEZINE);
		sTemp.add(Simptomi.ZAMOR);
		sTemp.add(Simptomi.MUCNINA_I_POVRACANJE);
		
		sTemp.add(Simptomi.TEMPERATURA_VECA_OD_38);
		sTemp.add(Simptomi.KASALJ);
		sTemp.add(Simptomi.KIJANJE);
		Pregled p = new Pregled(sTemp, null, moguceBolesti, pacient1);
		//Pregled p2 = new Pregled(pacient1, sTemp, null, -1.0, 38.0, true, moguceBolesti);

		// global za bolest
		kSession.setGlobal("dijabetes", dijabetes);
		kSession.setGlobal("hipertenzija", hipertenzija);
		// global za odabir

		kSession.insert(p);
		kSession.getAgenda().getAgendaGroup("druga").setFocus();
		kSession.fireAllRules();
		// get(1) jer ima vec jednu bolest koju smo setovali da bi videli za 60 dana
		//program ce samo da predlozi bolest, a sam lekar ce odluciti koja bolest je upitanju
//		assertEquals(dijabetes, pacient1.getTerapije().get(0).getBolest());
		
		for (MogucaBolest mmm : moguceBolesti) {
			if (mmm.getNaziv() == "dijabetes") {
				assertEquals(1.0, mmm.getMogucnost());
			}
		}

	}
	
	

}
