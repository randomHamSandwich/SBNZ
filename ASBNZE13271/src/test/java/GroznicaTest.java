import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

public class GroznicaTest {
	
	public Bolesti before() throws Exception{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("bolesti.dat"));
			Bolesti b = (Bolesti)in.readObject();
			in.close();
			return b;
	}
	
	//netrebam da izaberem bolest i da je stavim u pacijenta to ce leka
	//@Test
	public void groznicaTest() throws Exception {
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

		// necemo reci od s2 jer treba da zakljucimo da je temp veca od 38 na osnovu
		// zadate temperature tako
		// da taj simpom mi ne ubacijemo kao takva vec samo temperatru pa ce on sam
		// odrediti da li je >38 i ako
		// treba od 40 do 41
//		 HashSet<Simptomi> sTemp = new HashSet<>(s2);
		HashSet<Simptomi> sTemp = new HashSet<>();
		sTemp.add(Simptomi.KIJANJE);
		sTemp.add(Simptomi.BOL_U_GRLU);
		sTemp.add(Simptomi.KASALJ);
		sTemp.add(Simptomi.CURENJE_IZ_NOSA);
		sTemp.add(Simptomi.GLAVOBOLJA);
		sTemp.add(Simptomi.DRHTAVICA);
		sTemp.toString();
		//Pregled p = new Pregled(sTemp, null, moguceBolesti, pacient1);
		Pregled p = new Pregled(pacient1, sTemp, null, 120, true, moguceBolesti);

		// global za bolest
		kSession.setGlobal("prehlada", prehlada);
		kSession.setGlobal("groznica", groznica);
		kSession.setGlobal("upalaKrajnika", upalaKrajnika);
		kSession.setGlobal("sinusnaInfekcija", sinusnaInfekcija);
		
		JTextField tf = new JTextField();
		kSession.insert(tf);
		
		kSession.insert(p);
		kSession.insert(p);
		kSession.getAgenda().getAgendaGroup("prva").setFocus();
		kSession.fireAllRules();

		assertEquals(groznica, pacient1.getTerapije().get(0).getBolest());
	}

	 @Test
	public void groznicaMozeBiti() throws Exception {
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

		HashSet<Simptomi> sTemp = new HashSet<>();
		sTemp.add(Simptomi.KIJANJE);
		sTemp.add(Simptomi.BOL_U_GRLU);
		sTemp.add(Simptomi.KASALJ);
		sTemp.add(Simptomi.CURENJE_IZ_NOSA);
		sTemp.add(Simptomi.GLAVOBOLJA);
		sTemp.add(Simptomi.DRHTAVICA);
		
		sTemp.toString();
		Pregled p = new Pregled(pacient1, sTemp, null, 44, true, moguceBolesti);

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
			if (mmm.getNaziv() == "groznica") {
				assertEquals(1.0, mmm.getMogucnost());
			}
		}
		assertEquals("Groznica 100.00%	Prehlada 100.00%	SinusnaInfekcija 57.14%	", tf.getText());
		

	}
	 
	 

}
