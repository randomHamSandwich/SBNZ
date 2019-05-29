package cepTest;

import static org.junit.Assert.assertThat;

import java.util.Collection;

import javax.swing.JTextArea;

import com.baske.cep.HeartBeatEvent;
import com.baske.cep.UbrzanSrcaniRtamEvent;
import com.baske.model.*;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UbrzanRadSrcaTest2 {


	
	@Test
	public void proverRad() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession ksession1 = kContainer.newKieSession("cepConfigKsessionRealtimeClock");
		JTextArea ta = new JTextArea();
		ksession1.insert(ta);

		runRealtimeClockExample2(ksession1);

	}


	
	

	private void runRealtimeClockExample2(KieSession ksession1) {
		Thread t = new Thread() {
			@Override
			public void run() {
				for (int index = 0; index < 30; index++) {
					HeartBeatEvent beep = new HeartBeatEvent();
					ksession1.insert(beep);
					try {
					Thread.sleep(1);
						//Thread.sleep(500);
					} catch (InterruptedException e) {
						// do nothing
					}
				}
				
				
				try {
					Thread.sleep(1100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				for (int index = 0; index < 30; index++) {
					HeartBeatEvent beep = new HeartBeatEvent();
					ksession1.insert(beep);
					try {
					Thread.sleep(1);
						//Thread.sleep(500);
					} catch (InterruptedException e) {
						// do nothing
					}
				}
				System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxx");
				
			}
		};
		t.setDaemon(true);
		t.start();
		try {
			Thread.sleep(3000);
			System.out.println("sleeep ends yyyyyyyyyyyyyyyyyyyyyyy");
		} catch (InterruptedException e) {
			// do nothing
		}
		ksession1.fireUntilHalt();
//		ksession.fireAllRules();
		Collection<?> newEvents = ksession1.getObjects(new ClassObjectFilter(UbrzanSrcaniRtamEvent.class));
		// assertThat(newEvents.size(), equalTo(1));
		assertEquals(1, newEvents.size());
	}
	
	

}
