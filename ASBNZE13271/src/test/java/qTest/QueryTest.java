package qTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.BorderLayout;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.baske.model.Bolest;
import com.baske.model.Bolesti;
import com.baske.model.SimptomBolest;
import com.baske.model.SimptomBolestList;
import com.baske.model.SimptomBolestNumber;
import com.baske.model.Simptomi;
import com.sample.ProbaQuerry;
import com.sun.org.apache.bcel.internal.generic.SIPUSH;

public class QueryTest {

	public Bolesti before() throws Exception {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("bolesti.dat"));
		Bolesti b = (Bolesti) in.readObject();
		in.close();
		return b;
	}

	@Test
	public void test() throws Exception {
		//

		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession("ksession-Querry");

//		kSession.getAgenda().getAgendaGroup("prikaziSimptomeNekeBolesti").setFocus();
		kSession.getAgenda().getAgendaGroup("SimpUBolestima").setFocus();
		
		Bolesti bolesti = before();
		assertEquals("prehlada", bolesti.getBolesti().get(0).getNazivBolesti());
		assertEquals("groznica", bolesti.getBolesti().get(1).getNazivBolesti());
		assertEquals("upalaKrajnika", bolesti.getBolesti().get(2).getNazivBolesti());
		assertEquals("sinusnaInfekcija", bolesti.getBolesti().get(3).getNazivBolesti());
		
		assertEquals("dijabetes", bolesti.getBolesti().get(4).getNazivBolesti());
		assertEquals("hipertenzija", bolesti.getBolesti().get(5).getNazivBolesti());
		
		assertEquals("hronicnaBubreznaBolest", bolesti.getBolesti().get(6).getNazivBolesti());
		assertEquals("akutnaBubreznaBolest", bolesti.getBolesti().get(7).getNazivBolesti());

		Bolest prehlada = bolesti.getBolesti().get(0);
		Bolest groznica = bolesti.getBolesti().get(1);
		Bolest upalaKrajnika = bolesti.getBolesti().get(2);
		Bolest sinusnaInfekcija = bolesti.getBolesti().get(3);
		
		Bolest dijabetes = bolesti.getBolesti().get(4);
		Bolest hipertenzija = bolesti.getBolesti().get(5);
		
		Bolest hronicnaBubreznaBolest = bolesti.getBolesti().get(6);
		Bolest akutnaBubreznaBolest = bolesti.getBolesti().get(7);
		
		
		System.out.println("prehada------------------------------------" + prehlada.getNazivBolesti());
		for (Simptomi sss : prehlada.getSimptomi()) {
			kSession.insert(new SimptomBolest(sss, prehlada));
			System.out.println(sss);
		}
		System.out.println("groznica------------------------------------" + groznica.getNazivBolesti());
		for (Simptomi sss : groznica.getSimptomi()) {
			kSession.insert(new SimptomBolest(sss, groznica));
			System.out.println(sss);
		}
		System.out.println("upalaKrajniksa------------------------------------" + upalaKrajnika.getNazivBolesti());
		for (Simptomi sss : upalaKrajnika.getSimptomi()) {
			System.out.println(sss);
			kSession.insert(new SimptomBolest(sss, upalaKrajnika));
		}
		System.out.println("sinusna infekcija------------------------------------" + sinusnaInfekcija.getNazivBolesti());
		for(Simptomi sss: sinusnaInfekcija.getSimptomi()) {
			System.out.println(sss);
			kSession.insert(new SimptomBolest(sss, sinusnaInfekcija));
		}
		
		
		
		
		System.out.println("dijabetes------------------------------------" + dijabetes.getNazivBolesti());
		for(Simptomi sss: dijabetes.getSimptomi()) {
			System.out.println(sss);
			kSession.insert(new SimptomBolest(sss, dijabetes));
		}
		System.out.println("hipertenzija------------------------------------" + hipertenzija.getNazivBolesti());
		for(Simptomi sss: hipertenzija.getSimptomi()) {
			System.out.println(sss);
			kSession.insert(new SimptomBolest(sss, hipertenzija));
		}
		
		
		
		System.out.println("hronicnaBubreznaBolest------------------------------------" + hronicnaBubreznaBolest.getNazivBolesti());
		for(Simptomi sss: hronicnaBubreznaBolest.getSimptomi()) {
			System.out.println(sss);
			kSession.insert(new SimptomBolest(sss, hronicnaBubreznaBolest));
		}
		
		System.out.println("hipertenzija------------------------------------" + akutnaBubreznaBolest.getNazivBolesti());
		for(Simptomi sss: akutnaBubreznaBolest.getSimptomi()) {
			System.out.println(sss);
			kSession.insert(new SimptomBolest(sss, akutnaBubreznaBolest));
		}
		
		
		

		
		
//		kSession.insert(new SimptomBolest(s, prehlada));
//		kSession.insert(new SimptomBolest(Simptomi.KASALJ, prehlada));
//		kSession.insert(new SimptomBolest(Simptomi.GLAVOBOLJA, prehlada));
//		kSession.insert(new SimptomBolest(Simptomi.BOL_U_GRLU,prehlada));
//		kSession.insert(new SimptomBolest(Simptomi.c, prehlada));

		SimptomBolestNumber nPrehlda = new SimptomBolestNumber(prehlada, 0);
		SimptomBolestNumber nGroznica = new SimptomBolestNumber(groznica, 0);
		SimptomBolestNumber nUpalaKrajnika = new SimptomBolestNumber(upalaKrajnika, 0);
		SimptomBolestNumber nSinusnaInfekcija = new SimptomBolestNumber(sinusnaInfekcija, 0);
		
		SimptomBolestNumber nDijabetes = new SimptomBolestNumber(dijabetes, 0);
		SimptomBolestNumber nHipertenzija= new SimptomBolestNumber(hipertenzija, 0);
		
		SimptomBolestNumber nHronicnaBubreznaBolest = new SimptomBolestNumber(hronicnaBubreznaBolest, 0);
		SimptomBolestNumber nAkutnaBubreznaBolest = new SimptomBolestNumber(akutnaBubreznaBolest, 0);
		ArrayList<SimptomBolestNumber> list = new ArrayList<>();
		list.add(nPrehlda);
		list.add(nGroznica);
		list.add(nUpalaKrajnika);
		list.add(nSinusnaInfekcija);
		
		list.add(nDijabetes);
		list.add(nHipertenzija);
		
		list.add(nHronicnaBubreznaBolest);
		list.add(nAkutnaBubreznaBolest);
		SimptomBolestList bl = new  SimptomBolestList(list);
		
		kSession.insert(bl);
		
		
		kSession.insert(prehlada);
		kSession.insert(groznica);
		kSession.insert(upalaKrajnika);
		kSession.insert(sinusnaInfekcija);
		
		kSession.insert(dijabetes);
		kSession.insert(nHipertenzija);
		
		kSession.insert(hronicnaBubreznaBolest);
		kSession.insert(akutnaBubreznaBolest);
		
		
//		kSession.insert(nPrehlda);
//		kSession.insert(nGroznica);
//		kSession.insert(nUpalaKrajnika);
//		kSession.insert(nSinusnaInfekcija);
//		
//		kSession.insert(nDijabetes);
//		kSession.insert(nHipertenzija);
//		
//		kSession.insert(nHronicnaBubreznaBolest);
//		kSession.insert(nAkutnaBubreznaBolest);
//		
		
		
//		kSession.insert(Simptomi.GLAVOBOLJA);
//		kSession.insert(Simptomi.KIJANJE);
//		kSession.insert(Simptomi.KASALJ);
//		kSession.insert(Simptomi.BOL_U_GRLU);
//		kSession.insert(Simptomi.CURENJE_IZ_NOSA);
//		kSession.insert(Simptomi.OTICANJE_OKO_OCIJU);
		
//		kSession.insert(Simptomi.DIJAREJA);
//		kSession.insert(Simptomi.ZAMOR);
//		
		
		kSession.insert(Simptomi.GLAVOBOLJA);
		kSession.insert(Simptomi.GUBITAK_APETITA);
		kSession.insert(Simptomi.GUBITAK_TELESNE_TEZINE);
		kSession.insert(Simptomi.GUSENJE);
		
		kSession.insert(Simptomi.UMOR);
		kSession.insert(Simptomi.ZAMOR);
		
		

		
//		ArrayList<Simptomi> s = new ArrayList<>();
//		s.add(Simptomi.DIJAREJA);
//		s.add(Simptomi.ZAMOR);
//		
//		TODO obrishi ProbaQuerry
//		ProbaQuerry pQ  = new ProbaQuerry(s);
//		ProbaQuerry pQ  = new ProbaQuerry(s);

//		kSession.insert(pQ);
		kSession.fireAllRules();
//		System.out.println(nAkutnaBubreznaBolest);
//		System.out.println(nHronicnaBubreznaBolest);
//		System.out.println(nPrehlda);
//		
		///for()
		
	}

}
