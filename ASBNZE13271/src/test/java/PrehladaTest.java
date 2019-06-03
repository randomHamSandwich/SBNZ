import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;

import javax.swing.JTextField;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.baske.model.*;

public class PrehladaTest {
	
	//@Test
	public void prehladaTest() {
		 KieServices ks = KieServices.Factory.get();
 	    KieContainer kContainer = ks.getKieClasspathContainer();
     	KieSession kSession = kContainer.newKieSession("ksession-rules");
     	

     	HashSet<Simptomi> s = new HashSet<>();
     	
     	s.add(Simptomi.CURENJE_IZ_NOSA);
     	s.add(Simptomi.BOL_U_GRLU);
     	s.add(Simptomi.GLAVOBOLJA);
     	s.add(Simptomi.KIJANJE);
     	s.add(Simptomi.KASALJ);
     	Bolest prehlada = new Bolest("prehlada",s, null);
     	
     	HashSet<Simptomi> s2 = new HashSet<>();
     	s2.add(Simptomi.KIJANJE);
     	s2.add(Simptomi.BOL_U_GRLU);
     	s2.add(Simptomi.KASALJ);
     	s2.add(Simptomi.TEMPERATURA_VECA_OD_38);
     	s2.add(Simptomi.CURENJE_IZ_NOSA);
     	s2.add(Simptomi.GLAVOBOLJA);
     	s2.add(Simptomi.DRHTAVICA);
     	Bolest groznica = new Bolest("groznica", s2, null);
     	
     	HashSet<Simptomi> s3 = new HashSet<>();
     	s3.add(Simptomi.BOL_U_GRLU);
     	s3.add(Simptomi.BOL_KOJI_SE_SIRI_OD_USIJU);
     	s3.add(Simptomi.GLAVOBOLJA);
     	s3.add(Simptomi.TEMPERATURA_OD_40_DO_41);
     	s3.add(Simptomi.DRHTAVICA);
     	s3.add(Simptomi.GUBITAK_APETITA);
     	s3.add(Simptomi.UMOR);
     	s3.add(Simptomi.ZUTI_SEKRET_IZ_NOSA);
     	Bolest upalaKrajnika = new Bolest("upalaKrajnika", s3, null);
     	
     	HashSet<Simptomi> s4 = new HashSet<>();
     	s4.add(Simptomi.OTICANJE_OKO_OCIJU);
     	s4.add(Simptomi.GLAVOBOLJA);
     	s4.add(Simptomi.ZUTI_SEKRET_IZ_NOSA);
     	s4.add(Simptomi.BOL_U_GRLU);
     	s4.add(Simptomi.TEMPERATURA_VECA_OD_38);
     	s4.add(Simptomi.KASALJ);
     	s4.add(Simptomi.BOLOVANJE_OD_PREHLADE_ILI_GROZNICE_U_POSEDNJIH_60_DANA);
     	Bolest sinusnaInfekcija = new Bolest("sinusnaInfekcija", s4, null);
     	
     	
     	
     	
     
     	
//     	Pregled p = new Pregled(s, null);
//     	Pregled p2 = new Pregled(s, null);
     	HashSet<MogucaBolest> moguceBolesti = new HashSet<>();
     	MogucaBolest bolest1 = new MogucaBolest("prehlada", -1.0);
     	MogucaBolest bolest2 = new MogucaBolest("groznica", -1.0);
     	MogucaBolest bolest3 = new MogucaBolest("upalaKrajnika", -1.0);
     	MogucaBolest bolest4 = new MogucaBolest("sinusnaInfekcija", -1.0);
     	
     	moguceBolesti.add(bolest1);
     	moguceBolesti.add(bolest2);
     	moguceBolesti.add(bolest3);
     	moguceBolesti.add(bolest4);
     	
     	Pacient pacient1 = new Pacient("Pera", "Peric");
     	
     	HashSet<Simptomi> sTemp = new HashSet<>(s);
     	sTemp.add(Simptomi.BOL_KOJI_SE_SIRI_OD_USIJU);
     	sTemp.add(Simptomi.ZUTI_SEKRET_IZ_NOSA);
     	sTemp.add(Simptomi.UMOR);
     	sTemp.add(Simptomi.GUBITAK_APETITA);
     	Pregled p = new Pregled(sTemp,null, moguceBolesti,pacient1);

     	
     	//global za bolest
     	kSession.setGlobal("prehlada", prehlada);
     	kSession.setGlobal("groznica", groznica);
     	kSession.setGlobal("upalaKrajnika", upalaKrajnika);
     	kSession.setGlobal("sinusnaInfekcija", sinusnaInfekcija);
     	//global za odabir
//     	Double najboljaProcena=-1.0;
//     	kSession.setGlobal("najboljaProcena", najboljaProcena);
     	
		JTextField tf = new JTextField();
		kSession.insert(tf);
     	
         kSession.insert(p);
         kSession.getAgenda().getAgendaGroup("prva").setFocus();
         kSession.fireAllRules();
         
         assertEquals(prehlada, pacient1.getTerapije().get(0).getBolest());
	}
	
	
	@Test
	public void prehladaMozeBiti() {
		 KieServices ks = KieServices.Factory.get();
 	    KieContainer kContainer = ks.getKieClasspathContainer();
     	KieSession kSession = kContainer.newKieSession("ksession-rules");
     	

     	HashSet<Simptomi> s = new HashSet<>();
     	
     	s.add(Simptomi.CURENJE_IZ_NOSA);
     	s.add(Simptomi.BOL_U_GRLU);
     	s.add(Simptomi.GLAVOBOLJA);
     	s.add(Simptomi.KIJANJE);
     	s.add(Simptomi.KASALJ);
     	Bolest prehlada = new Bolest("prehlada",s, null);
     	
     	HashSet<Simptomi> s2 = new HashSet<>();
     	s2.add(Simptomi.KIJANJE);
     	s2.add(Simptomi.BOL_U_GRLU);
     	s2.add(Simptomi.KASALJ);
     	s2.add(Simptomi.TEMPERATURA_VECA_OD_38);
     	s2.add(Simptomi.CURENJE_IZ_NOSA);
     	s2.add(Simptomi.GLAVOBOLJA);
     	s2.add(Simptomi.DRHTAVICA);
     	Bolest groznica = new Bolest("groznica", s2, null);
     	
     	HashSet<Simptomi> s3 = new HashSet<>();
     	s3.add(Simptomi.BOL_U_GRLU);
     	s3.add(Simptomi.BOL_KOJI_SE_SIRI_OD_USIJU);
     	s3.add(Simptomi.GLAVOBOLJA);
     	s3.add(Simptomi.TEMPERATURA_OD_40_DO_41);
     	s3.add(Simptomi.DRHTAVICA);
     	s3.add(Simptomi.GUBITAK_APETITA);
     	s3.add(Simptomi.UMOR);
     	s3.add(Simptomi.ZUTI_SEKRET_IZ_NOSA);
     	Bolest upalaKrajnika = new Bolest("upalaKrajnika", s3, null);
     	
     	HashSet<Simptomi> s4 = new HashSet<>();
     	s4.add(Simptomi.OTICANJE_OKO_OCIJU);
     	s4.add(Simptomi.GLAVOBOLJA);
     	s4.add(Simptomi.ZUTI_SEKRET_IZ_NOSA);
     	s4.add(Simptomi.BOL_U_GRLU);
     	s4.add(Simptomi.TEMPERATURA_VECA_OD_38);
     	s4.add(Simptomi.KASALJ);
     	s4.add(Simptomi.BOLOVANJE_OD_PREHLADE_ILI_GROZNICE_U_POSEDNJIH_60_DANA);
     	Bolest sinusnaInfekcija = new Bolest("sinusnaInfekcija", s4, null);
     	
     	
     	
     	
     
     	
//     	Pregled p = new Pregled(s, null);
//     	Pregled p2 = new Pregled(s, null);
     	HashSet<MogucaBolest> moguceBolesti = new HashSet<>();
     	MogucaBolest bolest1 = new MogucaBolest("prehlada", -1.0);
     	MogucaBolest bolest2 = new MogucaBolest("groznica", -1.0);
     	MogucaBolest bolest3 = new MogucaBolest("upalaKrajnika", -1.0);
     	MogucaBolest bolest4 = new MogucaBolest("sinusnaInfekcija", -1.0);
     	
     	moguceBolesti.add(bolest1);
     	moguceBolesti.add(bolest2);
     	moguceBolesti.add(bolest3);
     	moguceBolesti.add(bolest4);
     	
     	Pacient pacient1 = new Pacient("Pera", "Peric");
     	
     	HashSet<Simptomi> sTemp = new HashSet<>(s);
     	sTemp.add(Simptomi.BOL_KOJI_SE_SIRI_OD_USIJU);
     	sTemp.add(Simptomi.ZUTI_SEKRET_IZ_NOSA);
     	sTemp.add(Simptomi.UMOR);
     	sTemp.add(Simptomi.GUBITAK_APETITA);
     	Pregled p = new Pregled(sTemp,null, moguceBolesti,pacient1);

     	
     	//global za bolest
     	kSession.setGlobal("prehlada", prehlada);
     	kSession.setGlobal("groznica", groznica);
     	kSession.setGlobal("upalaKrajnika", upalaKrajnika);
     	kSession.setGlobal("sinusnaInfekcija", sinusnaInfekcija);
     	//global za odabir
//     	Double najboljaProcena=-1.0;
//     	kSession.setGlobal("najboljaProcena", najboljaProcena);
     	
		JTextField tf = new JTextField();
		kSession.insert(tf);
     	
         kSession.insert(p);
         kSession.getAgenda().getAgendaGroup("prva").setFocus();
         kSession.fireAllRules();
         
 		for(MogucaBolest mmm : moguceBolesti) {
			if(mmm.getNaziv()=="prehlada") {
				assertEquals(1.0, mmm.getMogucnost());
			}
			
			assertEquals("Prehlada 100.00%	UpalaKrajnika 75.00%	Groznica 71.43%	SinusnaInfekcija 57.14%	", tf.getText());
		}
 		
         
	}

}
