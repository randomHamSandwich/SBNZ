package izveshtaji;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JTextArea;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.baske.model.*;
import com.baske.view.main;

public class HronicnaUpalaKrajnikaTest {
	

	@Test
	public void test() throws Exception{
		Bolesti b = main.before();
		Bolest upalaKrajnika = null;
		ArrayList<Bolest>bolesti =b.getBolesti();
		for(Bolest b1 : bolesti) {
			if(b1.getNazivBolesti().equals("upalaKrajnika")) {
				upalaKrajnika = b1;
			}
		}
		
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession("ksession-rulesIzveshtaji");
		kSession.getAgenda().getAgendaGroup("hronicni").setFocus();
		Pacient pacijent = new Pacient("Testo", "Testic");
		PrepisanaTerapija p0 = new PrepisanaTerapija(upalaKrajnika, null, "", LocalDate.now(), 36, true);
		PrepisanaTerapija p1 = new PrepisanaTerapija(upalaKrajnika, null, "", LocalDate.now(), 36, true);
		PrepisanaTerapija p2 = new PrepisanaTerapija(upalaKrajnika, null, "", LocalDate.now(), 36, true);
		PrepisanaTerapija p3 = new PrepisanaTerapija(upalaKrajnika, null, "", LocalDate.now(), 36, true);
		PrepisanaTerapija p4 = new PrepisanaTerapija(upalaKrajnika, null, "", LocalDate.now(), 36, true);
		PrepisanaTerapija p5 = new PrepisanaTerapija(upalaKrajnika, null, "", LocalDate.now(), 36, true);
		PrepisanaTerapija p6 = new PrepisanaTerapija(upalaKrajnika, null, "", LocalDate.now(), 36, true);
		
		pacijent.getTerapije().add(p0);
		pacijent.getTerapije().add(p1);
		pacijent.getTerapije().add(p2);
		pacijent.getTerapije().add(p3);
		pacijent.getTerapije().add(p4);
		pacijent.getTerapije().add(p5);
		pacijent.getTerapije().add(p6);
		JTextArea ta = new JTextArea();
		kSession.insert(ta);
		kSession.insert(pacijent);
		
		kSession.fireAllRules();
		String s ="Spisak pacijenata sa mogucmom hronicim upalom krajnika: \n" + 
				"	Pacient [Id=0, getIme()=Testo, getPrezime()=Testic]\n" +
				"Spisak pacijenata sa mogucmom hronicnom sinusnom infekcijom: \n" + 
				"Spisak pacijenata sa mogucmom hronicnom hipertenzijom: \n" + 
				"Spisak pacijenata sa mogucmom hronicnom dijabetesom: \n" + 
				"Spisak pacijenata sa mogucmom hronicnom hronicnom bubreznom bolesti: \n" + 
				"Spisak pacijenata sa mogucmom hronicnom akutnom bubreznom povredom: \n" + 
				"";
		assertEquals(s, ta.getText());
		
	}
	
	
	

}
