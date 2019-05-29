package cepTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JTextArea;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.baske.cep.HronicnaBubreznaBolestEvent;
import com.baske.model.Bolest;
import com.baske.model.Bolesti;
import com.baske.model.Pacient;
import com.baske.model.PrepisanaTerapija;
import com.baske.view.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class HronicaBubreznaBolestEventTest {
	
	@Test
	public void proveriHBEImaBolest() throws Exception {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession ksession1 = kContainer.newKieSession("cepConfigKsessionRealtimeClock");
		JTextArea ta = new JTextArea();
		ksession1.insert(ta);

		Bolesti b = main.before();
		Bolest hronicnaBubreznaBolest = null;
		ArrayList<Bolest>bolesti =b.getBolesti();
		for(Bolest b1 : bolesti) {
			if(b1.getNazivBolesti().equals("hronicnaBubreznaBolest")) {
				hronicnaBubreznaBolest = b1;
			}
		}
		
		Pacient p = new Pacient("pera", "peric");
		PrepisanaTerapija t = new PrepisanaTerapija(hronicnaBubreznaBolest, null, "", LocalDate.now(), 36, true);
		p.addTerapija(t);
		
		ksession1.insert(p);
		ksession1.fireAllRules();
		
		Collection<?> newEvents = ksession1.getObjects(new ClassObjectFilter(HronicnaBubreznaBolestEvent.class));
		assertEquals(1, newEvents.size());
	}
	
	@Test
	public void proveriHBENemaBolest() throws Exception {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession ksession1 = kContainer.newKieSession("cepConfigKsessionRealtimeClock");
		JTextArea ta = new JTextArea();
		ksession1.insert(ta);


		
		Pacient p = new Pacient("pera", "peric");
		
		ksession1.insert(p);
		ksession1.fireAllRules();
		Collection<?> newEvents = ksession1.getObjects(new ClassObjectFilter(HronicnaBubreznaBolestEvent.class));
		assertEquals(0, newEvents.size());
	}

}
