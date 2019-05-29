package cepTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;

import javax.swing.JTextArea;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.baske.cep.HeartBeatEvent;
import com.baske.cep.VisheOdDesetOtkucajaEvet;

public class UPoslednjihDesetSekundiTest {
	
	@Test
	public void prvoeriImaEventa() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession ksession1 = kContainer.newKieSession("cepConfigKsessionRealtimeClock");
		JTextArea ta = new JTextArea();
		ksession1.insert(ta);

		runRealtimeClockExample(ksession1);

	}
	
	//@Test
	public void proveriNEMAVisheOdDesetOtkucajaEvet() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession ksession1 = kContainer.newKieSession("cepConfigKsessionRealtimeClock");
		JTextArea ta = new JTextArea();
		ksession1.insert(ta);

		runNema(ksession1);

	}



	private void runRealtimeClockExample(KieSession ksession) {
		Thread t = new Thread() {
			@Override
			public void run() {
				for (int index = 0; index < 150; index++) {
					HeartBeatEvent beep = new HeartBeatEvent();
					ksession.insert(beep);
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
		Collection<?> newEvents = ksession.getObjects(new ClassObjectFilter(VisheOdDesetOtkucajaEvet.class));
		// assertThat(newEvents.size(), equalTo(1));
		assertEquals(1, newEvents.size());
	}
	
	private void runNema(KieSession ksession) {
		Thread t = new Thread() {
			@Override
			public void run() {
				for (int index = 0; index < 6; index++) {
					HeartBeatEvent beep = new HeartBeatEvent();
					ksession.insert(beep);
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
		Collection<?> newEvents = ksession.getObjects(new ClassObjectFilter(VisheOdDesetOtkucajaEvet.class));
		// assertThat(newEvents.size(), equalTo(1));
		assertEquals(0, newEvents.size());
	}
}
