package cepTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import javax.swing.JTextArea;

import org.drools.core.time.SessionPseudoClock;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.baske.cep.MokrenjeEvent;
import com.baske.cep.SumaKolicineMokrenjaEvent;

public class KolicinaMokraceuPoslednjihTest {
	
	@Test
	public void proveriSumuRealTimeClock() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession ksession1 = kContainer.newKieSession("cepConfigKsessionRealtimeClock");
		JTextArea ta = new JTextArea();
		ksession1.insert(ta);

		runRealtimeSuma(ksession1);

	}
	
	@Test
	public void proveriSumuPseudoTimeClock() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession ksession1 = kContainer.newKieSession("cepConfigKsessionPseudoClock");
		JTextArea ta = new JTextArea();
		ksession1.insert(ta);

		runPseudoSuma(ksession1);

	}

	private void runPseudoSuma(KieSession ksession) {
		// TODO Auto-generated method stub
		 SessionPseudoClock clock = ksession.getSessionClock();
		 MokrenjeEvent m = new MokrenjeEvent(11);
		 ksession.insert(m);
         clock.advanceTime(11, TimeUnit.HOURS);
         MokrenjeEvent m2 = new MokrenjeEvent(33);
         ksession.insert(m2);
         
         clock.advanceTime(5, TimeUnit.HOURS);
         MokrenjeEvent m3 = new MokrenjeEvent(55);
         ksession.insert(m3);
         
         ksession.fireAllRules();
         Collection<?> newEvents = ksession.getObjects(new ClassObjectFilter(SumaKolicineMokrenjaEvent.class));
         Collection<?> mokrenjeEvents = ksession.getObjects(new ClassObjectFilter(MokrenjeEvent.class));
		assertEquals(1, newEvents.size());
		assertEquals(2,mokrenjeEvents.size());
	}

	private void runRealtimeSuma(KieSession ksession) {
		Thread t = new Thread() {
			@Override
			public void run() {
				for (int index = 0; index < 4; index++) {
					MokrenjeEvent m = new MokrenjeEvent(24);
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
		Collection<?> newEvents = ksession.getObjects(new ClassObjectFilter(SumaKolicineMokrenjaEvent.class));
		// assertThat(newEvents.size(), equalTo(1));
		assertEquals(1, newEvents.size());
	}
	
}
