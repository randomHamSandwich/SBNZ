import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.util.HashSet;

import javax.swing.JTextField;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.baske.model.Bolest;
import com.baske.model.Bolesti;
import com.baske.model.Lekar;
import com.baske.model.MogucaBolest;
import com.baske.model.Pacient;
import com.baske.model.Pregled;
import com.baske.model.PrepisanaTerapija;
import com.baske.model.Simptomi;

public class SinusnaInfekcijaTest {
	
	public Bolesti before() throws Exception {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("bolesti.dat"));
		Bolesti b = (Bolesti) in.readObject();
		in.close();
		return b;
	}
	//jer sistem ne prepisuje novu terapij vec samo daje predlog
	//@Test
	public void sinusnaInfekcijaTest() throws Exception {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession("ksession-rules");

		Bolesti bolesti = before();
		assertEquals("prehlada", bolesti.getBolesti().get(0).getNazivBolesti());
		assertEquals("groznica", bolesti.getBolesti().get(1).getNazivBolesti());
		assertEquals("upalaKrajnika", bolesti.getBolesti().get(2).getNazivBolesti());
		assertEquals("sinusnaInfekcija", bolesti.getBolesti().get(3).getNazivBolesti());

		Bolest prehlada = bolesti.getBolesti().get(0);
		Bolest groznica = bolesti.getBolesti().get(1);
		Bolest upalaKrajnika = bolesti.getBolesti().get(2);
		Bolest sinusnaInfekcija = bolesti.getBolesti().get(3);

		HashSet<MogucaBolest> moguceBolesti = new HashSet<>();
		MogucaBolest bolest1 = new MogucaBolest("prehlada", -1.0);
		MogucaBolest bolest2 = new MogucaBolest("groznica", -1.0);
		MogucaBolest bolest3 = new MogucaBolest("upalaKrajnika", -1.0);
		MogucaBolest bolest4 = new MogucaBolest("sinusnaInfekcija", -1.0);
     
		moguceBolesti.add(bolest1);
		moguceBolesti.add(bolest2);
		moguceBolesti.add(bolest3);
		moguceBolesti.add(bolest4);

		Pacient pacient1 = new Pacient("Pera", "Peric");
//		pacient1.getTerapije().add(new PrepisanaTerapija(groznica, null, null, LocalDate.now().minusDays(5)));
		pacient1.getTerapije().add(new PrepisanaTerapija(groznica, null, null, LocalDate.now().minusDays(4), 41, true));
		

		// ne salje lekar ovu informaciju
		// =>>>>BOLOVANJE_OD_PREHLADE_ILI_GROZNICE_U_POSEDNJIH_60_DANA
		// vec iz sitema tako da to ne saljemo pa simptome ne kopiramo od s4
		// poslati svi simptomi sem ovog gore pusj Kijanje
		HashSet<Simptomi> sTemp = new HashSet<>();
		sTemp.add(Simptomi.OTICANJE_OKO_OCIJU);
		sTemp.add(Simptomi.GLAVOBOLJA);
		sTemp.add(Simptomi.ZUTI_SEKRET_IZ_NOSA);
		sTemp.add(Simptomi.BOL_U_GRLU);
		//sTemp.add(Simptomi.TEMPERATURA_VECA_OD_38);
		sTemp.add(Simptomi.KASALJ);
		sTemp.add(Simptomi.KIJANJE);
		//Pregled p = new Pregled(sTemp, null, moguceBolesti, pacient1);
		Pregled p = new Pregled(pacient1, sTemp, null, 40, false, moguceBolesti);

		// global za bolest
		kSession.setGlobal("prehlada", prehlada);
		kSession.setGlobal("groznica", groznica);
		kSession.setGlobal("upalaKrajnika", upalaKrajnika);
		kSession.setGlobal("sinusnaInfekcija", sinusnaInfekcija);
		// global za odabir
//     	Double najboljaProcena=-1.0;
//     	kSession.setGlobal("najboljaProcena", najboljaProcena);

		JTextField tf = new JTextField();
		kSession.insert(tf);
		
		kSession.insert(p);
		kSession.getAgenda().getAgendaGroup("prva").setFocus();
		kSession.fireAllRules();
		// get(1) jer ima vec jednu bolest koju smo setovali da bi videli za 60 dana
		assertEquals(sinusnaInfekcija, pacient1.getTerapije().get(1).getBolest());
	}

	@Test
	public void bolovanjePrehladaIliGrozniceUPoslednjih60Dana() {
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

		HashSet<MogucaBolest> moguceBolesti = new HashSet<>();
		MogucaBolest bolest1 = new MogucaBolest("prehlada", -1.0);
		MogucaBolest bolest2 = new MogucaBolest("groznica", -1.0);
		MogucaBolest bolest3 = new MogucaBolest("upalaKrajnika", -1.0);
		MogucaBolest bolest4 = new MogucaBolest("sinusnaInfekcija", -1.0);

		moguceBolesti.add(bolest1);
		moguceBolesti.add(bolest2);
		moguceBolesti.add(bolest3);
		moguceBolesti.add(bolest4);

		Pacient pacient1 = new Pacient("Pera", "Peric");
//     	pacient1.getTerapije().add(new PrepisanaTerapija(groznica, null, null, LocalDate.of(1991, 2, 2)));
		PrepisanaTerapija terapija = new PrepisanaTerapija(groznica, null, null, LocalDate.now().minusDays(5));
		terapija.setLerarIliAdmin(new Lekar("Aleksa", "aleksic", "n", "n"));
		pacient1.getTerapije().add(terapija);
		

		// ne salje lekar ovu informaciju
		// =>>>>BOLOVANJE_OD_PREHLADE_ILI_GROZNICE_U_POSEDNJIH_60_DANA
		// vec iz sitema tako da to ne saljemo pa simptome ne kopiramo od s4
		// poslati svi simptomi sem ovog gore pusj Kijanje
		HashSet<Simptomi> sTemp = new HashSet<>();
		sTemp.add(Simptomi.OTICANJE_OKO_OCIJU);
		sTemp.add(Simptomi.GLAVOBOLJA);
		sTemp.add(Simptomi.ZUTI_SEKRET_IZ_NOSA);
		sTemp.add(Simptomi.BOL_U_GRLU);
		//sTemp.add(Simptomi.TEMPERATURA_VECA_OD_38);
		sTemp.add(Simptomi.KASALJ);
		sTemp.add(Simptomi.KIJANJE);
		Pregled p = new Pregled(sTemp, null, moguceBolesti, pacient1);
		p.setTemeratura(40);

		// global za bolest
		kSession.setGlobal("prehlada", prehlada);
		kSession.setGlobal("groznica", groznica);
		kSession.setGlobal("upalaKrajnika", upalaKrajnika);
		kSession.setGlobal("sinusnaInfekcija", sinusnaInfekcija);
		// global za odabir
//     	Double najboljaProcena=-1.0;
//     	kSession.setGlobal("najboljaProcena", najboljaProcena);

		JTextField tf = new JTextField();
		kSession.insert(tf);
		
		kSession.insert(p);
		kSession.getAgenda().getAgendaGroup("prva").setFocus();
		kSession.fireAllRules();
		// get(1) jer ima vec jednu bolest koju smo setovali da bi videli za 60 dana
		assertEquals(true, p.getSimptomi().contains(Simptomi.BOLOVANJE_OD_PREHLADE_ILI_GROZNICE_U_POSEDNJIH_60_DANA));
	}
	
	@Test
	public void sinusnaInfekcijaMozeBiti() {
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

		HashSet<MogucaBolest> moguceBolesti = new HashSet<>();
		MogucaBolest bolest1 = new MogucaBolest("prehlada", -1.0);
		MogucaBolest bolest2 = new MogucaBolest("groznica", -1.0);
		MogucaBolest bolest3 = new MogucaBolest("upalaKrajnika", -1.0);
		MogucaBolest bolest4 = new MogucaBolest("sinusnaInfekcija", -1.0);

		moguceBolesti.add(bolest1);
		moguceBolesti.add(bolest2);
		moguceBolesti.add(bolest3);
		moguceBolesti.add(bolest4);

		Pacient pacient1 = new Pacient("Pera", "Peric");
//     	pacient1.getTerapije().add(new PrepisanaTerapija(groznica, null, null, LocalDate.of(1991, 2, 2)));
		PrepisanaTerapija terapija = new PrepisanaTerapija(groznica, null, null, LocalDate.now().minusDays(5));
		terapija.setLerarIliAdmin(new Lekar("Aleksa", "aleksic", "n", "n"));
		pacient1.getTerapije().add(terapija);

		// ne salje lekar ovu informaciju
		// =>>>>BOLOVANJE_OD_PREHLADE_ILI_GROZNICE_U_POSEDNJIH_60_DANA
		// vec iz sitema tako da to ne saljemo pa simptome ne kopiramo od s4
		// poslati svi simptomi sem ovog gore pusj Kijanje
		HashSet<Simptomi> sTemp = new HashSet<>();
		sTemp.add(Simptomi.OTICANJE_OKO_OCIJU);
		sTemp.add(Simptomi.GLAVOBOLJA);
		sTemp.add(Simptomi.ZUTI_SEKRET_IZ_NOSA);
		sTemp.add(Simptomi.BOL_U_GRLU);
		//sTemp.add(Simptomi.TEMPERATURA_VECA_OD_38);
		sTemp.add(Simptomi.KASALJ);
		sTemp.add(Simptomi.KIJANJE);
		Pregled p = new Pregled(sTemp, null, moguceBolesti, pacient1);
		p.setTemeratura(40);

		// global za bolest
		kSession.setGlobal("prehlada", prehlada);
		kSession.setGlobal("groznica", groznica);
		kSession.setGlobal("upalaKrajnika", upalaKrajnika);
		kSession.setGlobal("sinusnaInfekcija", sinusnaInfekcija);
		// global za odabir
//     	Double najboljaProcena=-1.0;
//     	kSession.setGlobal("najboljaProcena", najboljaProcena);

		JTextField tf = new JTextField();
		kSession.insert(tf);
		
		kSession.insert(p);
		kSession.getAgenda().getAgendaGroup("prva").setFocus();
		kSession.fireAllRules();
		
 		for(MogucaBolest mmm : moguceBolesti) {
			if(mmm.getNaziv()=="sinusnaInfekcija") {
				assertEquals(1.0, mmm.getMogucnost());
			}
			
		assertEquals("SinusnaInfekcija 100.00%	Prehlada 80.00%	Groznica 71.43%	", tf.getText());	
		}
	}


}
