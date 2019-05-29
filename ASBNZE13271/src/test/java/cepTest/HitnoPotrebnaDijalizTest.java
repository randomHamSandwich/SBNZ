package cepTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JTextArea;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.baske.cep.HeartBeatEvent;
import com.baske.cep.HitnoPotrebnaDijalizaEvent;
import com.baske.cep.MokrenjeEvent;
import com.baske.cep.VisheOdDesetOtkucajaEvet;
import com.baske.model.Bolest;
import com.baske.model.Bolesti;
import com.baske.model.Pacient;
import com.baske.model.PrepisanaTerapija;
import com.baske.view.main;

public class HitnoPotrebnaDijalizTest {
	
	@Test
	public void proveriImaEventa() throws Exception{
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession ksession1 = kContainer.newKieSession("cepConfigKsessionRealtimeClock");
		
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
		
		JTextArea ta = new JTextArea();
		ksession1.insert(ta);
		ksession1.insert(p);
		runRealtimeClockExample(ksession1);
	}

	private void runRealtimeClockExample(KieSession ksession) {
		Thread t = new Thread() {
			@Override
			public void run() {
				for (int index = 0; index < 30; index++) {
					HeartBeatEvent beep = new HeartBeatEvent();
					MokrenjeEvent m = new MokrenjeEvent(4);
					ksession.insert(beep);
					ksession.insert(m);
					try {
					Thread.sleep(1);
						//Thread.sleep(500);
					} catch (InterruptedException e) {
						// do nothing
					}
				}
			}
		};
		t.setDaemon(true);
		t.start();
		try {
			// sleep promenio sa 200 jer je ishao paralelno sa main pa su pristizali novi eventovi
			//pa je njih okidao, a trebao je samo poslednje a oni se nisu josh desili a koriscen je 
			//ksession.fireUntilHalt();
			Thread.sleep(2000);
			System.out.println("sleeep ends");
		} catch (InterruptedException e) {
			// do nothing
		}
		ksession.fireUntilHalt();
//		ksession.fireAllRules();
		Collection<?> newEvents = ksession.getObjects(new ClassObjectFilter(HitnoPotrebnaDijalizaEvent.class));
		// assertThat(newEvents.size(), equalTo(1));
		assertEquals(1, newEvents.size());
	}
		
	

}
