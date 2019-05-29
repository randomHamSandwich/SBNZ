package cepTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;

import javax.swing.JTextArea;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.baske.cep.DisanjeEvent;
import com.baske.cep.HeartBeatEvent;
import com.baske.cep.NivoKisonikaUKrviManjiEvenet;
import com.baske.cep.UbrzanSrcaniRtamEvent;

//stari testovi sada se nepamte ovi eventovi,
//stvore se i unishte da bi uvek imali zadnji koji je azuran
@Deprecated
public class NivoKiseonikaUKrviManjiTest {


	//@Test
	public void porveriManji() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession ksession1 = kContainer.newKieSession("cepConfigKsessionRealtimeClock");
		JTextArea ta = new JTextArea();
		ksession1.insert(ta);

		runManji(ksession1);

	}
	

	//@Test
	public void proveriVeci() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession ksession1 = kContainer.newKieSession("cepConfigKsessionRealtimeClock");
		JTextArea ta = new JTextArea();
		ksession1.insert(ta);

		runVeci(ksession1);

	}
	
	private void runManji(KieSession ksession) {

		Thread t = new Thread() {
			@Override
			public void run() {
//				for (int index = 0; index < 1500; index++) {
					DisanjeEvent d = new DisanjeEvent(2);
					ksession.insert(d);
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
//		ksession.fireAllRules();
		Collection<?> newEvents = ksession.getObjects(new ClassObjectFilter(NivoKisonikaUKrviManjiEvenet.class));
		// assertThat(newEvents.size(), equalTo(1));
		assertEquals(1, newEvents.size());
	}
		
		
	


	private void runVeci(KieSession ksession) {

		Thread t = new Thread() {
			@Override
			public void run() {
//				for (int index = 0; index < 1500; index++) {
					DisanjeEvent d = new DisanjeEvent(80);
					ksession.insert(d);
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
//		ksession.fireAllRules();
		Collection<?> newEvents = ksession.getObjects(new ClassObjectFilter(NivoKisonikaUKrviManjiEvenet.class));
		// assertThat(newEvents.size(), equalTo(1));
		assertEquals(0, newEvents.size());
	}
	
}
