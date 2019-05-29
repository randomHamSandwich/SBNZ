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
import com.baske.cep.NivoKiseonikaUKrviNeRasteEvent;
import com.baske.cep.NivoKisonikaUKrviManjiEvenet;
//stari testovi sada se nepamte ovi eventovi,
//stvore se i unishte da bi uvek imali zadnji koji je azuran
@Deprecated
public class NivoKiseonikaUKrviNeRasteteTest {

	
	
	//@Test
	public void porveriRaste() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession ksession1 = kContainer.newKieSession("cepConfigKsessionRealtimeClock");


		runRaste(ksession1);

	}

	//@Test
	public void proveriNeRaste() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession ksession1 = kContainer.newKieSession("cepConfigKsessionRealtimeClock");
		



		RunNeRaste(ksession1);

	}

	private void runRaste(KieSession ksession) {
		// TODO Auto-generated method stub
		JTextArea ta = new JTextArea();
		ksession.insert(ta);
		Thread t = new Thread() {
			@Override
			public void run() {
				for (int index = 0; index < 5; index++) {
					DisanjeEvent d = new DisanjeEvent(120-index);
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
		Collection<?> newEvents = ksession.getObjects(new ClassObjectFilter(NivoKiseonikaUKrviNeRasteEvent.class));
		// assertThat(newEvents.size(), equalTo(1));
		//10 jer imamo 5 dogadjaja 5 sa (4321), 4 sa (321) , 3 sa (21) , 2 sa (1)
		
		//necuvam vishe ovaj event tj brishe se na kraju rula tako da uvek imam azurnu zadnju verdnost
		assertEquals(10, newEvents.size());
		
		//assertEquals("sss", ta.getText());
	}


	private void RunNeRaste(KieSession ksession) {
		// TODO Auto-generated method stub
		JTextArea ta = new JTextArea();
		ksession.insert(ta);
		Thread t = new Thread() {
			@Override
			public void run() {
				for (int index = 0; index < 20; index++) {
					DisanjeEvent d = new DisanjeEvent(81);
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
//		ksession.fireAllRules();
		Collection<?> newEvents = ksession.getObjects(new ClassObjectFilter(NivoKiseonikaUKrviNeRasteEvent.class));
		// assertThat(newEvents.size(), equalTo(1));
		assertEquals(0, newEvents.size());
//		assertEquals("", ta.getText());

	}
}
