package com.baske.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.baske.cep.HeartBeatEvent;
import com.baske.cep.MokrenjeEvent;
import com.baske.model.Admin;
import com.baske.model.Administratori;
import com.baske.model.Bolest;
import com.baske.model.Bolesti;
import com.baske.model.IspisPacijentMonitoring;
import com.baske.model.Lek;
import com.baske.model.Lekari;
import com.baske.model.Lekovi;
import com.baske.model.MogucaBolest;
import com.baske.model.Osoba;
import com.baske.model.Pacient;
import com.baske.model.Pacijenti;
import com.baske.model.Pregled;
import com.baske.model.PrepisanaTerapija;
import com.baske.model.SastojakLeka;
import com.baske.model.Simptomi;
import com.baske.model.SpecificanSimptom;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;
import com.sun.crypto.provider.AESParameters;
import java.util.Collections;

public class main {
	public static Lekovi lekovi;
//	static JTextField tfUsername;
//	static JTextField tfPass ;
	static Lekari lekari;
	static Pacijenti pacijenti;
//	static Lekar logovaniLekar ;
//	static Admin logovaniAdmin;
	static Osoba logovanaOsoba;
	static Administratori administratori;

	public static Bolesti before() throws Exception {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("bolesti.dat"));
		Bolesti b = (Bolesti) in.readObject();
		in.close();
		return b;
	}

	public static Pacijenti beforePacijenti() throws Exception {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("pacijenti.dat"));
		Pacijenti p = (Pacijenti) in.readObject();
		in.close();
		System.out.println("trebalo bi da je procitao");
		System.out.println(p.getPacijenti());
		return p;
	}

	public static void resetujMogucaBolest(HashSet<MogucaBolest> mog) {
		for (MogucaBolest m : mog) {
			m.setMogucnost(-1.0);
		}

	}

	public static void moguceBolestiPrvaGrupa(HashSet<MogucaBolest> mog) {
		mog.clear();
		MogucaBolest bolest1 = new MogucaBolest("prehlada", -1.0);
		MogucaBolest bolest2 = new MogucaBolest("groznica", -1.0);
		MogucaBolest bolest3 = new MogucaBolest("upalaKrajnika", -1.0);
		MogucaBolest bolest4 = new MogucaBolest("sinusnaInfekcija", -1.0);

		mog.add(bolest1);
		mog.add(bolest2);
		mog.add(bolest3);
		mog.add(bolest4);

	}

	public static void moguceBolestiDrugaGrupa(HashSet<MogucaBolest> mog) {
		mog.clear();
		MogucaBolest bolest1 = new MogucaBolest("dijabetes", -1.0);
		MogucaBolest bolest2 = new MogucaBolest("hipertenzija", -1.0);

		mog.add(bolest1);
		mog.add(bolest2);

	}

	public static void moguceBolestitrecaGrupa(HashSet<MogucaBolest> mog) {
		mog.clear();
		MogucaBolest bolest1 = new MogucaBolest("hronicnaBubreznaBolest", -1.0);
		MogucaBolest bolest2 = new MogucaBolest("akutnaBubreznaBolest", -1.0);

		mog.add(bolest1);
		mog.add(bolest2);

	}

	public static Lekovi beforeLekovi() throws Exception {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("lekovi.dat"));
		Lekovi l = (Lekovi) in.readObject();
		in.close();
		System.out.println("procitao lekove");
		return l;
	}

	public static Lekari beforeLekari() throws Exception {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("lekari.dat"));
		Lekari lekari = (Lekari) in.readObject();
		in.close();
		System.out.println("procitao LEKARE");
		return lekari;
	}

	public static Administratori beforeAdministratori() throws Exception {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("administratori.dat"));
		Administratori administratori = (Administratori) in.readObject();
		in.close();
		System.out.println("procitao ADMINISTRATORE");
		return administratori;
	}

	public static int logIn() {
		JPanel panel = new JPanel();
		JLabel lblUsername = new JLabel("Korisnicko ime");
		JLabel lblPass = new JLabel("Lozinka");
		JTextField tfUsername = new JTextField(20);
		JTextField tfPass = new JTextField(20);
		panel.setLayout(new GridLayout(2, 2));

		panel.add(lblUsername);
		panel.add(tfUsername);
		panel.add(lblPass);
		panel.add(tfPass);
		int a = JOptionPane.showConfirmDialog((null), panel, "log in", JOptionPane.YES_NO_OPTION);
		System.out.println(a);
//		TODO proveri if
		logovanaOsoba = administratori.proveriUSerPass(tfUsername.getText(), tfPass.getText());
		if (logovanaOsoba == null) {
			logovanaOsoba = lekari.proveriUserPass(tfUsername.getText(), tfPass.getText());
		}

		if (logovanaOsoba == null && a != -1 && a != 1) {
			a = -10;
		}
		System.out.println(
				"USERNAME AND PASS AND RETURN :" + tfUsername.getText() + " ___:" + tfPass.getText() + " " + a);
		return a;
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Bolesti bolesti = before();
		pacijenti = beforePacijenti();
		lekovi = beforeLekovi();
		lekari = beforeLekari();
		administratori = beforeAdministratori();

		int b = logIn();
//		
//		while(b==1 || b==-10) {
		while (b == -10) {
			b = logIn();
		}
		if (b == -1 || b == 1) {
			System.exit(0);
		}

		DoctorFrame df = DoctorFrame.getInstance(pacijenti);
		df.setTitle("CDSSom, ulogovan ime: " + logovanaOsoba.getIme() + " " + logovanaOsoba.getPrezime());
		// omoguciti adminu dodavanje novih lekara
		if (logovanaOsoba instanceof Admin) {
			df.getBtnDodajLekara().setEnabled(true);
			df.getBtnDodajLekara().setVisible(true);
		}
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
//		Object[] pacijentTemppp = pacijenti.getPacijenti().toArray();
//		System.out.println(pacijentTemppp[0].toString() + " xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
//		JComboBox<Object> zzzzzz = new JComboBox<>(pacijentTemppp);
		df.setVisible(true);
		df.repaint();

		Bolest prehlada = bolesti.getBolesti().get(0);
		Bolest groznica = bolesti.getBolesti().get(1);
		Bolest upalaKrajnika = bolesti.getBolesti().get(2);
		Bolest sinusnaInfekcija = bolesti.getBolesti().get(3);

		Bolest dijabetes = bolesti.getBolesti().get(4);
		Bolest hipertenzija = bolesti.getBolesti().get(5);
		Bolest hronicnaBubreznaBolest = bolesti.getBolesti().get(6);
		Bolest akutnaBubreznaBolest = bolesti.getBolesti().get(7);

		HashSet<MogucaBolest> moguceBolesti = new HashSet<>();
		MogucaBolest bolest1 = new MogucaBolest("prehlada", -1.0);
		MogucaBolest bolest2 = new MogucaBolest("groznica", -1.0);
		MogucaBolest bolest3 = new MogucaBolest("upalaKrajnika", -1.0);
		MogucaBolest bolest4 = new MogucaBolest("sinusnaInfekcija", -1.0);

		moguceBolesti.add(bolest1);
		moguceBolesti.add(bolest2);
		moguceBolesti.add(bolest3);
		moguceBolesti.add(bolest4);

		// Pacient pacient1 = new Pacient("Pera", "Peric");

		// sesija se ne kreira svaki put kada se klikne dugme nego jednom
		KieSession kSession = kContainer.newKieSession("ksession-rules");
		kSession.setGlobal("prehlada", prehlada);
		kSession.setGlobal("groznica", groznica);
		kSession.setGlobal("upalaKrajnika", upalaKrajnika);
		kSession.setGlobal("sinusnaInfekcija", sinusnaInfekcija);

		kSession.setGlobal("dijabetes", dijabetes);
		kSession.setGlobal("hipertenzija", hipertenzija);
		kSession.setGlobal("hronicnaBubreznaBolest", hronicnaBubreznaBolest);
		kSession.setGlobal("akutnaBubreznaBolest", akutnaBubreznaBolest);

		df.getDualListBox().getPrintChosenContent().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				KieSession kSession = kContainer.newKieSession("ksession-rules");
//				kSession.setGlobal("prehlada", prehlada);
//				kSession.setGlobal("groznica", groznica);
//				kSession.setGlobal("upalaKrajnika", upalaKrajnika);
//				kSession.setGlobal("sinusnaInfekcija", sinusnaInfekcija);
				moguceBolestiPrvaGrupa(moguceBolesti);

				ArrayList<String> chosenContentList = new ArrayList<>();

				for (int i = 0; i < df.getDualListBox().destList.getModel().getSize(); i++) {
					chosenContentList.add(df.getDualListBox().destList.getModel().getElementAt(i).toString());
				}

				HashSet<Simptomi> sIzabrani = new HashSet<>();
				for (Simptomi s : Simptomi.values()) {
					if (chosenContentList.contains(s.toString())) {
						sIzabrani.add(s);
					}
				}

				System.out.println("izabrani simptomi iz gui-a: " + chosenContentList);
//				System.out.println("trebalo bi da radi kako treba");
//				Pregled p = new Pregled(pacient1, null, null, 66, true, moguceBolesti);
				Double d = -1.0;
				String s = df.getCustoPanel().getCltf1().getTf().getText();
				try {
					d = Double.valueOf(s);
					df.getTfError().setText("");
				} catch (NumberFormatException nuberFormaTEXeption) {
//					df.getTfError().setForeground(Color.RED);
					df.getTfError().setText("Temperatura nije valdina");

				}
				System.out.println("yeeeeeeeeeeaaaaaaaaaaaaa: " + s + "\n" + d + "\n" + "visok pritisak: "
						+ df.getCustoPanel().getVisokPritisak().isSelected());

				Pacient pacijentSelektovan = (Pacient) df.getCmbPacijent().getSelectedItem();
				Pregled p = new Pregled(pacijentSelektovan, sIzabrani, null, d,
						df.getCustoPanel().getVisokPritisak().isSelected(), moguceBolesti);
				
				
				
				df.getTfPreporukaPrvaGrupa().setText("");
				kSession.insert(df.getTfPreporukaPrvaGrupa());
				
				kSession.insert(p);
				kSession.getAgenda().getAgendaGroup("prva").setFocus();
				kSession.fireAllRules();
			
				df.getTfPreporukaPrvaGrupa().setText(df.getTfPreporukaPrvaGrupa().getText()+"	mogBol:"+moguceBolesti.toString());
				
				
				
				
				// radio sam dispose ranije al sam napravio delete pravilo yea
//				kSession.dispose();
//				kSession.retract(kSession.getFactHandle(p));
//				kSession.delete(kSession.getFactHandle(p));
				resetujMogucaBolest(moguceBolesti);
				// ne ujaj sesiju nego napravi pravilo koje ce da izvbaci sve sto ne treba yea..

				moguceBolestiDrugaGrupa(moguceBolesti);
				p = new Pregled(pacijentSelektovan, sIzabrani, null, d,
						df.getCustoPanel().getVisokPritisak().isSelected(), moguceBolesti);
				
				
				
				df.getTfPreporukaDrugaGrupa().setText("");
				kSession.insert(df.getTfPreporukaDrugaGrupa());
				
				kSession.insert(p);
				kSession.getAgenda().getAgendaGroup("druga").setFocus();
				kSession.fireAllRules();
				
				
				df.getTfPreporukaDrugaGrupa().setText(moguceBolesti.toString());

				
				
				
				
				moguceBolestitrecaGrupa(moguceBolesti);
				
				kSession.insert(df.getTfPreporukaTrecaGrupa());
				
				HashSet<SpecificanSimptom> specificniSimptomi = new HashSet<>();
				if (df.getCustoPanel().getOpeoravljaSeOdOperacije().isSelected()) {
					specificniSimptomi.add(SpecificanSimptom.OPORAVLJA_SE_OD_OPERACIJE);
				}
				p = new Pregled(pacijentSelektovan, sIzabrani, specificniSimptomi, d,
						df.getCustoPanel().getVisokPritisak().isSelected(), moguceBolesti);
				kSession.insert(p);
				kSession.getAgenda().getAgendaGroup("treca").setFocus();
				kSession.fireAllRules();
				df.getTfPreporukaTrecaGrupa().setText(moguceBolesti.toString());

			}
		});

		df.getPrepisiTerapijuPanel().getDualListBoxPrepisaniLekovi().printChosenContent
				.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						df.getTaAlergijeNaSastojak().setText("");
						df.getTaAlergijeNaLek().setText("");
						Double d = -1.0;
						String s = df.getCustoPanel().getCltf1().getTf().getText();
						try {
							d = Double.valueOf(s);
							df.getTfError().setText("");
						} catch (NumberFormatException nuberFormaTEXeption) {
//					df.getTfError().setForeground(Color.RED);
							df.getTfError().setText("Temperatura nije valdina");

						}

						Pacient pacijentSelektovan = (Pacient) df.getCmbPacijent().getSelectedItem();
						// nije nam bitno sta je izabrano od simptoma vec samo da li ima alergije na
						// nesto
						Pregled p = new Pregled(pacijentSelektovan, null, null, d, true, moguceBolesti);
						kSession.insert(p);

						HashSet<String> lekoviList = new HashSet<>();
						for (int i = 0; i < df.getPrepisiTerapijuPanel().getDualListBoxPrepisaniLekovi().destList
								.getModel().getSize(); i++) {
							lekoviList.add(df.getPrepisiTerapijuPanel().getDualListBoxPrepisaniLekovi().destList
									.getModel().getElementAt(i).toString());
						}

						kSession.insert(lekoviList);
						kSession.getAgenda().getAgendaGroup("proveriAlergije").setFocus();
						kSession.fireAllRules();
						
						//TODO ako nema problema omoguci ubacivanje 
						HashSet<Lek> selektovaniPacAlergijeNaLekove =pacijentSelektovan.getAlergijaNaLekove();
						HashSet<String> selektovanPacAlergijeNaSastojke =pacijentSelektovan.getAlergijanaSastojkeIzLeka();
						
						
						ArrayList<Lek> prepisaniLekoviLek = new ArrayList<>();
						HashSet<String> prepisaniLekoviString = new HashSet<>();

						for (int i = 0; i < df.getPrepisiTerapijuPanel().getDualListBoxPrepisaniLekovi().destList.getModel()
								.getSize(); i++) {
							prepisaniLekoviString.add(df.getPrepisiTerapijuPanel().getDualListBoxPrepisaniLekovi().destList
									.getModel().getElementAt(i).toString());

						}
						for (Lek l : lekovi.getLekovi()) {
							for (String ssss : prepisaniLekoviString) {
								if (l.getNaziv().equals(ssss)) {
									prepisaniLekoviLek.add(l);
								}
							}
						}
						boolean alergicanNaLekIliSast = false;
						String stringIspisiSastojke ="";
						String stringIspisiLek="";
						for(Lek l :prepisaniLekoviLek) {
							for(Lek l2 : selektovaniPacAlergijeNaLekove) {
								if(l2.getNaziv().equals(l.getNaziv())) {
									alergicanNaLekIliSast = true;
									stringIspisiLek+= l.getNaziv()+"\n";
									df.getTaAlergijeNaLek().setText(stringIspisiLek);
								}
							}
								
							for(SastojakLeka sastojakLekas : l.getSastojci()) {
								if(selektovanPacAlergijeNaSastojke.contains(sastojakLekas.toString())) {
									stringIspisiSastojke+=sastojakLekas.toString()+" iz "+l.getNaziv()+"\n";
									alergicanNaLekIliSast = true;
									df.getTaAlergijeNaSastojak().setText(stringIspisiSastojke);
								}
							}
						}
						
						
						
						
						
						df.getBtnDodajPrepisiTerapiju().setEnabled(!alergicanNaLekIliSast);
					}
				});
		
		df.getPrepisiTerapijuPanel().getDualListBoxPrepisaniLekovi().getAddButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				df.getBtnDodajPrepisiTerapiju().setEnabled(false);
				
			}
		});
		
		df.getPrepisiTerapijuPanel().getDualListBoxPrepisaniLekovi().getRemoveButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				df.getBtnDodajPrepisiTerapiju().setEnabled(false);
				
			}
		});
		
		df.getBtnIzmeniPacijent().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				df.getBtnDodajPrepisiTerapiju().setEnabled(false);
				
			}
		});
		
		df.getBtnObrisiPacijenta().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				df.getBtnDodajPrepisiTerapiju().setEnabled(false);
				
			}
		});
		df.getCmbPacijent().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				df.getBtnDodajPrepisiTerapiju().setEnabled(false);
				
			}
		});;
		
		

		
		df.getBtnMonitoringPacijenta().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				KieSession ksession111 = kContainer.newKieSession("cepConfigKsessionRealtimeClock");	
				MonitoringPacijentaDialog d = new MonitoringPacijentaDialog(df, (Pacient) df.getCmbPacijent().getSelectedItem(), true, ksession111);
				d.addWindowListener( new WindowAdapter() {

					@Override
					public void windowDeactivated(WindowEvent e) {
						// TODO Auto-generated method stub
						super.windowDeactivated(e);
						System.out.println("ako je izasho alt f4 ili x bez da je zaustavio zaustavi thread i ksession");
						try {
							d.stopTread();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							System.out.println("stop hread exeption");
						}
					}


					
					
				});
//				d.setKs(ksession111);
//				KieServices ks = KieServices.Factory.get();
//				KieContainer kContainer = ks.getKieClasspathContainer();
//				ksession1.insert((Pacient) df.getCmbPacijent().getSelectedItem());
//				ksession1.insert(d.getTaIspis());
//				ksession1.getAgenda().getAgendaGroup("testttttttttttt").setFocus();
//				ksession1.fireUntilHalt();

//				ksession111.fireUntilHalt();
//				Thread t = new Thread() {
//					@Override
//					public void run() {
//						for (int index = 0; index < 150; index++) {
//							HeartBeatEvent beep = new HeartBeatEvent();
//							ksession111.insert(beep);
//							try {
//							Thread.sleep(1);
//								//Thread.sleep(500);
//							} catch (InterruptedException e) {
//								// do nothing
//							}
//						}
//					}
//				};
//				t.setDaemon(true);
//				t.start();
//				
//				System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
			}
		});
		//simptomi koje ima ova bolest
		df.getPrepisiTerapijuPanel().getDualListBoxPrepisaniLekovi().btnUpit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				Bolest bolestSelektovana =(Bolest)df.getPrepisiTerapijuPanel().getCmbBolesti().getSelectedItem();
//				String sssTemp ="";
//				try {
//					HashSet<SpecificanSimptom> setSpecSim =	bolestSelektovana.getSpecificniSimptomi();
//					for(SpecificanSimptom s :setSpecSim) {
//						sssTemp+=s+"\n";	
//					}
//					
//				} catch (Exception e2) {
//					// TODO: handle exception
//				}
//				
//				HashSet<Simptomi> setSimp =bolestSelektovana.getSimptomi();
//				for(Simptomi sSimp : setSimp) {
//					sssTemp+=sSimp+"\n";
//				}
				
				KieSession kSessionQerry = kContainer.newKieSession("ksession-Querry");
				kSessionQerry.getAgenda().getAgendaGroup("prikaziSimptomeNekeBolesti").setFocus();
				kSessionQerry.setGlobal("prehlada", prehlada);
				kSessionQerry.setGlobal("groznica", groznica);
				kSessionQerry.setGlobal("upalaKrajnika", upalaKrajnika);
				kSessionQerry.setGlobal("sinusnaInfekcija", sinusnaInfekcija);

				kSessionQerry.setGlobal("dijabetes", dijabetes);
				kSessionQerry.setGlobal("hipertenzija", hipertenzija);
				kSessionQerry.setGlobal("hronicnaBubreznaBolest", hronicnaBubreznaBolest);
				kSessionQerry.setGlobal("akutnaBubreznaBolest", akutnaBubreznaBolest);
				kSessionQerry.insert(bolestSelektovana);
				
				JTextArea taSimpomiZaOvuBolest = new JTextArea();
				taSimpomiZaOvuBolest.setEditable(false);
				kSessionQerry.insert(taSimpomiZaOvuBolest);
				
				kSessionQerry.fireAllRules();
				//JOptionPane.showMessageDialog(df, sssTemp, "Simptomi: "+bolestSelektovana.getNazivBolesti(), JOptionPane.YES_OPTION );
				JOptionPane.showMessageDialog(df, taSimpomiZaOvuBolest, "Simptomi: "+bolestSelektovana.getNazivBolesti(), JOptionPane.YES_OPTION );
				
			}
		});
		//koje bolesti imaju ove simptome
		df.getDualListBox().btnUpit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ArrayList<String> chosenContentList = new ArrayList<>();
				
				for (int i = 0; i < df.getDualListBox().destList.getModel().getSize(); i++) {
					chosenContentList.add(df.getDualListBox().destList.getModel().getElementAt(i).toString());
				}

				HashSet<Simptomi> sIzabrani = new HashSet<>();
				for (Simptomi s : Simptomi.values()) {
					if (chosenContentList.contains(s.toString())) {
						sIzabrani.add(s);
					}
				}
				
				//System.out.println("zateceno u chosenContentList: "+chosenContentList);
				
//				class C  {
//					ArrayList<InnerC> list = new ArrayList<>();
//					
//					boolean addB(String s) {
//						for (InnerC innerC : list) {
//							if(innerC.naziv.equals(s)) {
//								innerC.broj++;
//								return true;
//							}
//						}
//						return list.add(new InnerC(1, s));
//					}
//					void sort() {
//						Collections.sort(list,  Collections.reverseOrder());
//					}
//					
//					class InnerC  implements Comparable< InnerC >{
//						int broj;
//						String naziv;
//						public InnerC(int broj, String naziv) {
//							super();
//							this.broj = broj;
//							this.naziv = naziv;
//						}
//						@Override
//						public String toString() {
//							return "InnerC [broj=" + broj + ", naziv=" + naziv + "]";
//						}
//						@Override
//						public int compareTo(InnerC o) {
//							 return Integer.valueOf(this.broj).compareTo(Integer.valueOf(o.broj));
//						}
//						
//					}
//					
//				}
//				
//				C c = new C();
//				for(Bolest bb : bolesti.getBolesti()) {
//					int i =1;
//					
//					for(Simptomi sss : sIzabrani) {
//						if(bb.getSimptomi().contains(sss)) {
//							System.out.println("ngggggggg "+sss+"\n");
//							c.addB(bb.getNazivBolesti());
//						}
//					}
//				}
//				c.sort();
//				String sIspis ="";
//				for(int i=0;i< c.list.size();i++) {
//					sIspis+= "Bolest: "+c.list.get(i).naziv +"  ima simptoma: " +c.list.get(i).broj+"\n";
//				}
//				JOptionPane.showMessageDialog(df, sIspis, "Bolesti: ", JOptionPane.YES_OPTION );
				KieSession kSessionQerry = kContainer.newKieSession("ksession-Querry");
				kSessionQerry.setGlobal("prehlada", prehlada);
				kSessionQerry.setGlobal("groznica", groznica);
				kSessionQerry.setGlobal("upalaKrajnika", upalaKrajnika);
				kSessionQerry.setGlobal("sinusnaInfekcija", sinusnaInfekcija);

				kSessionQerry.setGlobal("dijabetes", dijabetes);
				kSessionQerry.setGlobal("hipertenzija", hipertenzija);
				kSessionQerry.setGlobal("hronicnaBubreznaBolest", hronicnaBubreznaBolest);
				kSessionQerry.setGlobal("akutnaBubreznaBolest", akutnaBubreznaBolest);
				
				for(Simptomi sTempIzabrani : sIzabrani) {
					kSessionQerry.insert(sTempIzabrani);
				}
				
				
				JTextArea taIspis = new JTextArea(); 
				taIspis.setEditable(false);
				kSessionQerry.insert(taIspis);
				kSessionQerry.getAgenda().getAgendaGroup("SimpUBolestima").setFocus();
				
				
				kSessionQerry.fireAllRules();
				
		
				JOptionPane.showMessageDialog(df, taIspis, "Bolesti: ", JOptionPane.YES_OPTION );
				
			}
		});
		
		
		df.getBtnIzbeshtaji().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				
				KieSession kSessionIzvestaji = kContainer.newKieSession("ksession-rulesIzveshtaji");
				
				ArrayList<Pacient> pTemp = pacijenti.getPacijenti();
				for(Pacient ppp : pTemp) {
					kSessionIzvestaji.insert(ppp);
				}
				IzveshtanjiDialog iDialog = new IzveshtanjiDialog(df,kSessionIzvestaji,true);
				
			}
		});
		

		df.getBtnDodajPrepisiTerapiju().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Lek> prepisaniLekoviLek = new ArrayList<>();
				HashSet<String> prepisaniLekoviString = new HashSet<>();

				for (int i = 0; i < df.getPrepisiTerapijuPanel().getDualListBoxPrepisaniLekovi().destList.getModel()
						.getSize(); i++) {
					prepisaniLekoviString.add(df.getPrepisiTerapijuPanel().getDualListBoxPrepisaniLekovi().destList
							.getModel().getElementAt(i).toString());

				}
				Bolest selektovanaBolest = (Bolest) df.getPrepisiTerapijuPanel().getCmbBolesti().getSelectedItem();
				String preporukaLekara = df.getPrepisiTerapijuPanel().getTfPreporukaLekara().getText();
				LocalDate datumPrepisaneTerapiej = LocalDate.now();
				ArrayList<Lek> prepisaniLekoviTemp = new ArrayList<>();
				for (Lek l : lekovi.getLekovi()) {
					for (String s : prepisaniLekoviString) {
						if (l.getNaziv().equals(s)) {
							prepisaniLekoviLek.add(l);
						}
					}
				}

				Boolean visokPritisak = df.getCustoPanel().getVisokPritisak().isSelected();
				Double temperatura = -1.0;
				try {
					temperatura = Double.valueOf(df.getCustoPanel().getCltf1().getTf().getText());
				} catch (Exception e) {
					// TODO: handle exception
				}

				PrepisanaTerapija prepisanaTerapija = new PrepisanaTerapija(selektovanaBolest, prepisaniLekoviLek,
						preporukaLekara, datumPrepisaneTerapiej, temperatura, visokPritisak);
				prepisanaTerapija.setLerarIliAdmin(logovanaOsoba);

				Pacient pacijentSelektovan = (Pacient) df.getCmbPacijent().getSelectedItem();
				pacijentSelektovan.addTerapija(prepisanaTerapija);
//				PrepisanaTerapija prepisanaTerapija = new PrepisanaTerapija(, prepisaniLekovi, 
//				preporukaLekar, datumPrepisaneTerapiej)
				int idPacijentaOdArrayListe = -100;
				try {
					// zato sto prilikom readPacijenti sami pacijenti kolekcija bi se promenila
					// i ne bi se trajno sinmila vishe od jedne terapije

					for (int i = 0; i < pacijenti.getPacijenti().size(); i++) {
						if (pacijenti.getPacijenti().get(i).getId() == pacijentSelektovan.getId()) {
							// pacijenti.getPacijenti().get(i) = pacijentSelektovan;
							idPacijentaOdArrayListe = i;
						}
					}
					pacijenti.getPacijenti().set(idPacijentaOdArrayListe, pacijentSelektovan);
//					
					pacijenti.writePacijenti(pacijenti);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					pacijenti = pacijenti.readPacijenti();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println("pacijent kome je dodata terapija: " + pacijentSelektovan + " "
						+ pacijentSelektovan.getTerapije().get(pacijentSelektovan.getTerapije().size() - 1));

			}
		});
	}

}
