package izveshtaji;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JTextArea;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.baske.model.Bolest;
import com.baske.model.Bolesti;
import com.baske.model.Lek;
import com.baske.model.Lekar;
import com.baske.model.Lekari;
import com.baske.model.Lekovi;
import com.baske.model.Pacient;
import com.baske.model.PrepisanaTerapija;
import com.baske.view.main;

public class ZavisniciTest {
	
	@Test
	public void testImaOI() throws Exception {
		
		Lekari l =main.beforeLekari();
		ArrayList<Lekar> lekariList = l.getLekari();
		
		Bolesti b = main.before();
		Bolest dijabetes = null;
		Bolest sinusnaInfekcija = null;
		ArrayList<Bolest> bolesti = b.getBolesti();
		for (Bolest b1 : bolesti) {
			if (b1.getNazivBolesti().equals("dijabetes")) {
				dijabetes = b1;
			}
			if(b1.getNazivBolesti().equals("sinusnaInfekcija")) {
				sinusnaInfekcija = b1;
			}
		}
		 // palitrex antibiotik
		Lekovi lekovi = Lekovi.readLekovi();
		 Lek farin =lekovi.getLekovi().get(0);
		 Lek andol =lekovi.getLekovi().get(1);
		 Lek palitrex =lekovi.getLekovi().get(2);
		 assertEquals("Farin", farin.getNaziv());
		 assertEquals("Andol", andol.getNaziv());
		 assertEquals("Palitrex", palitrex.getNaziv());
		 
		 ArrayList<Lek> l1 = new ArrayList<>();
		 l1.add(farin);
		 
		 ArrayList<Lek> l2 = new ArrayList<>();
		 l2.add(andol);
		 l2.add(andol);
		 // palitrex antibiotik
		 ArrayList<Lek> l3 = new ArrayList<>();
		 l3.add(andol);
		 l3.add(palitrex);
		 
		 l3.add(farin);
		 
		 ArrayList<Lek> l4 = new ArrayList<>();
		 l4.add(palitrex);
		 l4.add(palitrex);
		 
		 l4.add(palitrex);
		 
		 
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession("ksession-rulesIzveshtaji");
		kSession.getAgenda().getAgendaGroup("moguciZavisnici").setFocus();
		Pacient pacijent = new Pacient("Testo", "Testic");
		ArrayList<Lek> listaLekovaSa = new ArrayList<>();
		ArrayList<Lek> listaLekovaBez = new ArrayList<>();
		listaLekovaSa.add(andol);
		listaLekovaBez.add(farin);
		
		PrepisanaTerapija p0 = new PrepisanaTerapija(dijabetes,listaLekovaSa, "", LocalDate.now(), 36, true);
		PrepisanaTerapija p1 = new PrepisanaTerapija(dijabetes, listaLekovaSa, "", LocalDate.now(), 36, true);
		PrepisanaTerapija p2 = new PrepisanaTerapija(dijabetes, listaLekovaSa, "", LocalDate.now().minusMonths(3), 36, true);
		PrepisanaTerapija p3 = new PrepisanaTerapija(dijabetes, listaLekovaSa, "", LocalDate.now().minusMonths(1), 36, true);
		PrepisanaTerapija p4 = new PrepisanaTerapija(sinusnaInfekcija, listaLekovaSa, "", LocalDate.now(), 36, true);
		PrepisanaTerapija p5 = new PrepisanaTerapija(dijabetes, listaLekovaSa, "", LocalDate.now(), 36, true);
		PrepisanaTerapija p6 = new PrepisanaTerapija(dijabetes, listaLekovaSa, "", LocalDate.now().minusMonths(3), 36, true);
		PrepisanaTerapija p7 = new PrepisanaTerapija(dijabetes, listaLekovaSa, "", LocalDate.now().minusMonths(3), 36, true);
	
		p0.setLerarIliAdmin(lekariList.get(0));
		p1.setLerarIliAdmin(lekariList.get(0));
		p2.setLerarIliAdmin(lekariList.get(1));
		p3.setLerarIliAdmin(lekariList.get(0));
		p4.setLerarIliAdmin(lekariList.get(0));
		p5.setLerarIliAdmin(lekariList.get(2));
		p6.setLerarIliAdmin(lekariList.get(0));
		p7.setLerarIliAdmin(lekariList.get(0));
		
		pacijent.getTerapije().add(p0);
		pacijent.getTerapije().add(p1);
		pacijent.getTerapije().add(p2);
		pacijent.getTerapije().add(p3);
		pacijent.getTerapije().add(p4);
		pacijent.getTerapije().add(p5);
		pacijent.getTerapije().add(p6);
		pacijent.getTerapije().add(p7);


		
		
		
		JTextArea ta = new JTextArea();
		kSession.insert(ta);
		kSession.insert(pacijent);

		kSession.fireAllRules();

		String s ="Spisak pacijenata sa oslabljenim imunitetom: \n" + 
				"	Pacient [Id=0, getIme()=Testo, getPrezime()=Testic]\n" + 
				"";
		
//		assertEquals(s, ta.getText());
	}

}
