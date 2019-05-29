package cepTest;


import java.util.Collection;

import javax.swing.JTextArea;

import com.baske.cep.HeartBeatEvent;
import com.baske.cep.UbrzanSrcaniRtamEvent;
import com.baske.model.IspisPacijentMonitoring;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UbrzanRadSrcaTest {

	@Test
	public void proverRadSrcaRealTimeClck1() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession ksession1 = kContainer.newKieSession("cepConfigKsessionRealtimeClock");
//		IspisPacijentMonitoring i = new IspisPacijentMonitoring();
		JTextArea ta = new JTextArea();
		ksession1.insert(ta);
		runRealtimeClockExample(ksession1);

	}
	


	private void runRealtimeClockExample(KieSession ksession) {
		Thread t = new Thread() {
			@Override
			public void run() {
				for (int index = 0; index < 150; index++) {
//				for (int index = 0; index < 1500; index++) {
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
			Thread.sleep(3000);
			//Thread.sleep(6000);
			System.out.println("sleeep ends");
		} catch (InterruptedException e) {
			// do nothing
		}
		ksession.fireUntilHalt();
//		ksession.fireAllRules();
		Collection<?> newEvents = ksession.getObjects(new ClassObjectFilter(UbrzanSrcaniRtamEvent.class));
		// assertThat(newEvents.size(), equalTo(1));
		assertEquals(1, newEvents.size());
	}
	
	

	

}
