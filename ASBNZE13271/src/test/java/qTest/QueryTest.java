package qTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.BorderLayout;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JTextArea;

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
		
		kSession.setGlobal("prehlada", prehlada);
		kSession.setGlobal("groznica", groznica);
		kSession.setGlobal("upalaKrajnika", upalaKrajnika);
		kSession.setGlobal("sinusnaInfekcija", sinusnaInfekcija);

		kSession.setGlobal("dijabetes", dijabetes);
		kSession.setGlobal("hipertenzija", hipertenzija);
		
		kSession.setGlobal("hronicnaBubreznaBolest", hronicnaBubreznaBolest);
		kSession.setGlobal("akutnaBubreznaBolest", akutnaBubreznaBolest);
		
		//TODO OBRISI OVO JE DA PROVERM DA LI RADI NA DRUGI NACIN PREKO SIMBOLESTNUMBER
		kSession.insert(prehlada);
		kSession.insert(groznica);
		kSession.insert(upalaKrajnika);
		kSession.insert(sinusnaInfekcija);

		kSession.insert(dijabetes);
		kSession.insert(hipertenzija);
		
		kSession.insert(hronicnaBubreznaBolest);
		kSession.insert(akutnaBubreznaBolest);

		
		
		//prehlada
//		kSession.insert(Simptomi.CURENJE_IZ_NOSA);
//		kSession.insert(Simptomi.BOL_U_GRLU);
//		kSession.insert(Simptomi.GLAVOBOLJA);
//		kSession.insert(Simptomi.KIJANJE);
//		kSession.insert(Simptomi.KASALJ);
//		
//		// groznica
//		kSession.insert(Simptomi.KIJANJE);
//		kSession.insert(Simptomi.BOL_U_GRLU);
//		kSession.insert(Simptomi.KASALJ);
//		kSession.insert(Simptomi.TEMPERATURA_VECA_OD_38);
//		kSession.insert(Simptomi.CURENJE_IZ_NOSA);
//		kSession.insert(Simptomi.GLAVOBOLJA);
//		kSession.insert(Simptomi.DRHTAVICA);
//		
//		//upala krajnija
//		kSession.insert(Simptomi.BOL_U_GRLU);
//		kSession.insert(Simptomi.BOL_KOJI_SE_SIRI_OD_USIJU);
//		kSession.insert(Simptomi.GLAVOBOLJA);
//		kSession.insert(Simptomi.TEMPERATURA_OD_40_DO_41);
//		kSession.insert(Simptomi.DRHTAVICA);
//		kSession.insert(Simptomi.GUBITAK_APETITA);
//		kSession.insert(Simptomi.UMOR);
//		kSession.insert(Simptomi.ZUTI_SEKRET_IZ_NOSA);
//		
//		
//		// sinusna infekcija
//		kSession.insert(Simptomi.OTICANJE_OKO_OCIJU);
//		kSession.insert(Simptomi.GLAVOBOLJA);
//		kSession.insert(Simptomi.ZUTI_SEKRET_IZ_NOSA);
//		kSession.insert(Simptomi.BOL_U_GRLU);
//		kSession.insert(Simptomi.TEMPERATURA_VECA_OD_38);
//		kSession.insert(Simptomi.KASALJ);
//		kSession.insert(Simptomi.BOLOVANJE_OD_PREHLADE_ILI_GROZNICE_U_POSEDNJIH_60_DANA);
//		
//		
//		
//		//-------------------------------------------------------------------------------------
//		//hipertenzija
//		kSession.insert(Simptomi.U_POSLEDNJIH_6_MESECI_ZABELEZNO_BAREM_10_SLUCAJEVA_UKOJEM_JE_PACIJENT_IMAO_VISOK_PRITISAK);
//		
//		
//		//dijabetes
		kSession.insert(Simptomi.CESTO_URINIRANJE);
		kSession.insert(Simptomi.GUBITAK_TELESNE_TEZINE);
		kSession.insert(Simptomi.ZAMOR);
		kSession.insert(Simptomi.MUCNINA_I_POVRACANJE);
//		
//		
//		
//		//-------------------------------------------------------------------------------------
//		//hronicna bubrezna bolest
//		kSession.insert(Simptomi.ZAMOR);
//		kSession.insert(Simptomi.NOCTURIA);
//		kSession.insert(Simptomi.OTOCI_NOGU_I_ZGLOBOVA);
//		kSession.insert(Simptomi.GUSENJE);
//		kSession.insert(Simptomi.BOL_U_GRUDIMA);
//		
//		//akutna bubrezna piovreda
//		
//		kSession.insert(Simptomi.ZAMOR);
//		kSession.insert(Simptomi.GUSENJE);
//		kSession.insert(Simptomi.OTOCI_NOGU_I_ZGLOBOVA);
//		kSession.insert(Simptomi.DIJAREJA);
		
		
		
		kSession.insert(new JTextArea());

// TODO OBRISHI PROBAQUERRY
//		ProbaQuerry pQ  = new ProbaQuerry(s);

		kSession.fireAllRules();
		

	}

}
