package cepTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;

import javax.swing.JTextArea;

import org.kie.api.KieServices;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.baske.cep.DisanjeEvent;
import com.baske.cep.HeartBeatEvent;
import com.baske.cep.KiseonikRasteEvent;
import com.baske.cep.NivoKisonikaUKrviManjiEvenet;
import com.baske.cep.ProblemSaKiseonikomEvent;

import org.junit.Test;
public class KiseonikTest {

	@Test
	public void kiseonikNeRaste() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession ksession1 = kContainer.newKieSession("cepConfigKsessionRealtimeClock");
		JTextArea ta = new JTextArea();
		ksession1.insert(ta);

		runKiseonikNeRaste(ksession1);

	}
	
	@Test
	public void raste() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession ksession1 = kContainer.newKieSession("cepConfigKsessionRealtimeClock");
		JTextArea ta = new JTextArea();
		ksession1.insert(ta);

		runRaste(ksession1);

	}
	
	@Test
	public void Opada() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession ksession1 = kContainer.newKieSession("cepConfigKsessionRealtimeClock");
		JTextArea ta = new JTextArea();
		ksession1.insert(ta);

		runOpada(ksession1);

	}



	private void runKiseonikNeRaste(KieSession ksession) {

		Thread t = new Thread() {
			@Override
			public void run() {
				for (int index = 0; index < 9; index++) {
					DisanjeEvent d = new DisanjeEvent(45);
					HeartBeatEvent beep = new HeartBeatEvent();
					ksession.insert(beep);
					ksession.insert(d);
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
			Thread.sleep(3000);
			System.out.println("sleeep ends");
		} catch (InterruptedException e) {
			// do nothing
		}
		ksession.fireUntilHalt();
		Collection<?> newEvents = ksession.getObjects(new ClassObjectFilter(KiseonikRasteEvent.class));
		assertEquals(0, newEvents.size());
	}
	
	
	private void runRaste(KieSession ksession1) {
		// TODO Auto-generated method stub
		Thread t = new Thread() {
			@Override
			public void run() {
				for (int index = 0; index < 3; index++) {
					DisanjeEvent d = new DisanjeEvent(45+index);
					HeartBeatEvent beep = new HeartBeatEvent();
					ksession1.insert(beep);
					ksession1.insert(d);
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
			Thread.sleep(3000);
			System.out.println("sleeep ends");
		} catch (InterruptedException e) {
			// do nothing
		}
		ksession1.fireUntilHalt();
		Collection<?> newEvents = ksession1.getObjects(new ClassObjectFilter(KiseonikRasteEvent.class));
		assertEquals(true, newEvents.size()>0);
	}
		
	

	private void runOpada(KieSession ksession1){
		// TODO Auto-generated method stub
		Thread t = new Thread() {
			@Override
			public void run() {
				for (int index = 0; index < 4; index++) {
					DisanjeEvent d = new DisanjeEvent(71-index);
					HeartBeatEvent beep = new HeartBeatEvent();
					ksession1.insert(beep);
					ksession1.insert(d);
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
			Thread.sleep(3000);
			System.out.println("sleeep ends");
		} catch (InterruptedException e) {
			// do nothing
		}
		ksession1.fireUntilHalt();
		Collection<?> newEvents = ksession1.getObjects(new ClassObjectFilter(KiseonikRasteEvent.class));
		Collection<?> problemEvent = ksession1.getObjects(new ClassObjectFilter(ProblemSaKiseonikomEvent.class));
		assertEquals(0, newEvents.size());
		assertEquals(true, problemEvent.size()>0);
	}
	


}
