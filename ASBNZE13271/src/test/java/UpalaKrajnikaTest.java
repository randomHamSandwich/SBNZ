import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashSet;

import javax.swing.JTextField;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.baske.model.Bolest;
import com.baske.model.Bolesti;
import com.baske.model.MogucaBolest;
import com.baske.model.Pacient;
import com.baske.model.Pregled;
import com.baske.model.Simptomi;

public class UpalaKrajnikaTest {

	public Bolesti before() throws Exception {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("bolesti.dat"));
		Bolesti b = (Bolesti) in.readObject();
		in.close();
		return b;
	}

//	@Test
	public void upalaKrajnikaTest() throws Exception {
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

// s3.add(Simptomi.TEMPERATURA_OD_40_DO_41); to zakljucujemo
		Pacient pacient1 = new Pacient("Pera", "Peric");
		HashSet<Simptomi> sTemp = new HashSet<>();
		sTemp.add(Simptomi.BOL_U_GRLU);
		sTemp.add(Simptomi.BOL_KOJI_SE_SIRI_OD_USIJU);
		sTemp.add(Simptomi.GLAVOBOLJA);
		//sTemp.add(Simptomi.TEMPERATURA_OD_40_DO_41);
		sTemp.add(Simptomi.DRHTAVICA);
		sTemp.add(Simptomi.GUBITAK_APETITA);
		sTemp.add(Simptomi.UMOR);
		sTemp.add(Simptomi.ZUTI_SEKRET_IZ_NOSA);

		sTemp.add(Simptomi.KASALJ);
		sTemp.add(Simptomi.KIJANJE);
		// Pregled p = new Pregled(sTemp, null, moguceBolesti, pacient1);
		Pregled p = new Pregled(pacient1, sTemp, null, 40.1, true, moguceBolesti);

		// global za bolest
		kSession.setGlobal("prehlada", prehlada);
		kSession.setGlobal("groznica", groznica);
		kSession.setGlobal("upalaKrajnika", upalaKrajnika);
		kSession.setGlobal("sinusnaInfekcija", sinusnaInfekcija);

		JTextField tf = new JTextField();
		kSession.insert(tf);
		
		kSession.insert(p);
		kSession.getAgenda().getAgendaGroup("prva").setFocus();
		kSession.fireAllRules();

		assertEquals(upalaKrajnika, pacient1.getTerapije().get(0).getBolest());
	}

	@Test
	public void upalaKrajnikaMozeBiti() throws Exception {
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

		// s3.add(Simptomi.TEMPERATURA_OD_40_DO_41); to zakljucujemo
		Pacient pacient1 = new Pacient("Pera", "Peric");
		HashSet<Simptomi> sTemp = new HashSet<>();
		sTemp.add(Simptomi.BOL_U_GRLU);
		sTemp.add(Simptomi.BOL_KOJI_SE_SIRI_OD_USIJU);
		sTemp.add(Simptomi.GLAVOBOLJA);
		//sTemp.add(Simptomi.TEMPERATURA_OD_40_DO_41);
		sTemp.add(Simptomi.DRHTAVICA);
		sTemp.add(Simptomi.GUBITAK_APETITA);
		sTemp.add(Simptomi.UMOR);
		sTemp.add(Simptomi.ZUTI_SEKRET_IZ_NOSA);

		sTemp.add(Simptomi.KASALJ);
		sTemp.add(Simptomi.KIJANJE);
		// Pregled p = new Pregled(sTemp, null, moguceBolesti, pacient1);
		Pregled p = new Pregled(pacient1, sTemp, null, 40.1, true, moguceBolesti);

		// global za bolest
		kSession.setGlobal("prehlada", prehlada);
		kSession.setGlobal("groznica", groznica);
		kSession.setGlobal("upalaKrajnika", upalaKrajnika);
		kSession.setGlobal("sinusnaInfekcija", sinusnaInfekcija);
		
		JTextField tf = new JTextField();
		kSession.insert(tf);

		kSession.insert(p);
		kSession.getAgenda().getAgendaGroup("prva").setFocus();
		kSession.fireAllRules();

		for (MogucaBolest mmm : moguceBolesti) {
			if (mmm.getNaziv() == "upalaKrajnika") {
				assertEquals(1.0, mmm.getMogucnost());
			}
		}
		
		assertEquals("UpalaKrajnika 100.00%	Groznica 85.71%	Prehlada 80.00%	SinusnaInfekcija 71.43%	", tf.getText());
	}

}