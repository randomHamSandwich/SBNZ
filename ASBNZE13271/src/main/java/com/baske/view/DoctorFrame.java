package com.baske.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.drools.persistence.jta.JtaTransactionManager;

import com.baske.model.Admin;
import com.baske.model.Administratori;
import com.baske.model.Lekar;
import com.baske.model.Lekari;
import com.baske.model.Pacient;
import com.baske.model.Pacijenti;
import com.baske.model.Simptomi;

import javafx.scene.layout.Border;

public class DoctorFrame extends JFrame {
	private static DoctorFrame instance;
	private DualListBox dualListBox;
	private CustomPanel custoPanel;
	private JComboBox<Object> cmbPacijent;
	private JPanel panelPreporuke;
	private JTextField tfPreporukaPrvaGrupa;
	private JTextField tfPreporukaDrugaGrupa;
	private JTextField tfPreporukaTrecaGrupa;
	private JTextField tfError;
	private IzmeniPacijentaDialog izmeniPacijentaDialog;
	private JButton btnIzmeniPacijent;
	private JButton btnDodajPacijenta;
	private JButton btnObrisiPacijenta;
	private JButton btnDodajLekara;
	private Pacijenti pacijenti;
	private PrepisiTerapijuPanel prepisiTerapijuPanel;
	private JButton btnDodajPrepisiTerapiju;
	private boolean moguceDodatiTeraiju = false;
	private JTextArea taAlergijeNaSastojak;
	private JTextArea taAlergijeNaLek;
	private JButton btnMonitoringPacijenta;
	private JButton btnIzbeshtaji;
	





	public JButton getBtnIzbeshtaji() {
		return btnIzbeshtaji;
	}

	public void setBtnIzbeshtaji(JButton btnIzbeshtaji) {
		this.btnIzbeshtaji = btnIzbeshtaji;
	}

	public JButton getBtnMonitoringPacijenta() {
		return btnMonitoringPacijenta;
	}

	public void setBtnMonitoringPacijenta(JButton btnMonitoringPacijenta) {
		this.btnMonitoringPacijenta = btnMonitoringPacijenta;
	}

	public JButton getBtnIzmeniPacijent() {
		return btnIzmeniPacijent;
	}

	public void setBtnIzmeniPacijent(JButton btnIzmeniPacijent) {
		this.btnIzmeniPacijent = btnIzmeniPacijent;
	}

	public JButton getBtnObrisiPacijenta() {
		return btnObrisiPacijenta;
	}

	public void setBtnObrisiPacijenta(JButton btnObrisiPacijenta) {
		this.btnObrisiPacijenta = btnObrisiPacijenta;
	}

	public JTextArea getTaAlergijeNaSastojak() {
		return taAlergijeNaSastojak;
	}

	public void setTaAlergijeNaSastojak(JTextArea taAlergijeNaSastojak) {
		this.taAlergijeNaSastojak = taAlergijeNaSastojak;
	}

	public JTextArea getTaAlergijeNaLek() {
		return taAlergijeNaLek;
	}

	public void setTaAlergijeNaLek(JTextArea taAlergijeNaLek) {
		this.taAlergijeNaLek = taAlergijeNaLek;
	}

	public JPanel getPanelPreporuke() {
		return panelPreporuke;
	}

	public void setPanelPreporuke(JPanel panelPreporuke) {
		this.panelPreporuke = panelPreporuke;
	}

	public JTextField getTfPreporukaPrvaGrupa() {
		return tfPreporukaPrvaGrupa;
	}

	public void setTfPreporukaPrvaGrupa(JTextField tfPreporukaPrvaGrupa) {
		this.tfPreporukaPrvaGrupa = tfPreporukaPrvaGrupa;
	}

	public JTextField getTfPreporukaDrugaGrupa() {
		return tfPreporukaDrugaGrupa;
	}

	public void setTfPreporukaDrugaGrupa(JTextField tfPreporukaDrugaGrupa) {
		this.tfPreporukaDrugaGrupa = tfPreporukaDrugaGrupa;
	}

	public JTextField getTfPreporukaTrecaGrupa() {
		return tfPreporukaTrecaGrupa;
	}

	public void setTfPreporukaTrecaGrupa(JTextField tfPreporukaTrecaGrupa) {
		this.tfPreporukaTrecaGrupa = tfPreporukaTrecaGrupa;
	}

	public boolean isMoguceDodatiTeraiju() {
		return moguceDodatiTeraiju;
	}

	public void setMoguceDodatiTeraiju(boolean moguceDodatiTeraiju) {
		this.moguceDodatiTeraiju = moguceDodatiTeraiju;
	}

	public JButton getBtnDodajPrepisiTerapiju() {
		return btnDodajPrepisiTerapiju;
	}

	public void setBtnDodajPrepisiTerapiju(JButton btnDodajPrepisiTerapiju) {
		this.btnDodajPrepisiTerapiju = btnDodajPrepisiTerapiju;
	}

	public PrepisiTerapijuPanel getPrepisiTerapijuPanel() {
		return prepisiTerapijuPanel;
	}

	public void setPrepisiTerapijuPanel(PrepisiTerapijuPanel prepisiTerapijuPanel) {
		this.prepisiTerapijuPanel = prepisiTerapijuPanel;
	}

	public JButton getBtnDodajLekara() {
		return btnDodajLekara;
	}

	public void setBtnDodajLekara(JButton btnDodajLekara) {
		this.btnDodajLekara = btnDodajLekara;
	}

	public Pacijenti getPacijenti() {
		return pacijenti;
	}

	public void setPacijenti(Pacijenti pacijenti) {
		this.pacijenti = pacijenti;
	}

	public JTextField getTfError() {
		return tfError;
	}

	public void setTfError(JTextField tfError) {
		this.tfError = tfError;
	}


	public JComboBox<Object> getCmbPacijent() {
		return cmbPacijent;
	}

	public void setCmbPacijent(JComboBox<Object> cmbPacijent) {
		this.cmbPacijent = cmbPacijent;
	}

	public CustomPanel getCustoPanel() {
		return custoPanel;
	}

	public void setCustoPanel(CustomPanel custoPanel) {
		this.custoPanel = custoPanel;
	}

	public DualListBox getDualListBox() {
		return dualListBox;
	}

	public void setDualListBox(DualListBox dualListBox) {
		this.dualListBox = dualListBox;
	}

	private DoctorFrame(Pacijenti p) throws HeadlessException {
		super("CDSSom, stavi ulogovanog lekara");
		initialize(p);
		// TODO Auto-generated constructor stub
	}

	public static DoctorFrame getInstance(Pacijenti p) {
		if (instance == null) {
			instance = new DoctorFrame(p);
		}
		return instance;
	}

	private void initialize(Pacijenti p) {
		setSize(1300, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		dualListBox = new DualListBox();
		ArrayList<String> simptomiList = new ArrayList<>();
		// ove infromacije se rezonuju tako da ne treba da ih eksplicino unosimo
		for (Simptomi s : Simptomi.values()) {
			if (s != Simptomi.TEMPERATURA_OD_40_DO_41 && s != Simptomi.TEMPERATURA_VECA_OD_38
					&& s != Simptomi.BOLOVANJE_OD_PREHLADE_ILI_GROZNICE_U_POSEDNJIH_60_DANA
					&& s != Simptomi.U_POSLEDNJIH_6_MESECI_ZABELEZNO_BAREM_10_SLUCAJEVA_UKOJEM_JE_PACIJENT_IMAO_VISOK_PRITISAK) {
				simptomiList.add(s.toString());
			}

		}
		pacijenti = p;

		dualListBox.addSourceElements(simptomiList.toArray());
		custoPanel = CustomPanel.getInstance();
		Object[] pacijentTemppp = p.getPacijenti().toArray();
		cmbPacijent = new JComboBox<>(p.getPacijenti().toArray());

		btnIzmeniPacijent = new JButton("Izmeni selektovanog pacijenta");
		btnIzmeniPacijent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// vec imamo singlton instacu pa nije problem yea
				izmeniPacijentaDialog = new IzmeniPacijentaDialog(getInstance(null),
						((Pacient) cmbPacijent.getSelectedItem()), true);
				izmeniPacijentaDialog.initialize();
				cmbPacijent.repaint();
			}
		});

		setLayout(new BorderLayout());
		JPanel palenNorth = new JPanel();
		JPanel panelNorthLeft = new JPanel();
		panelNorthLeft.setLayout(new GridLayout(4, 1));
		panelNorthLeft.add(cmbPacijent);
		panelNorthLeft.add(custoPanel);
		panelNorthLeft.add(btnIzmeniPacijent);
		

		btnDodajPacijenta = new JButton("Dodaj novog pacijenta");
		btnDodajPacijenta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel();
				JLabel lblIme = new JLabel("Ime");
				JLabel lblPrezime = new JLabel("Prezime");
				JTextField tfIme = new JTextField(20);
				JTextField tfPrezime = new JTextField(20);
				panel.setLayout(new GridLayout(2, 2));

				panel.add(lblIme);
				panel.add(tfIme);
				panel.add(lblPrezime);
				panel.add(tfPrezime);

				int a = JOptionPane.showConfirmDialog(getInstance(null), panel, "Dodavanje novog pacijenta",
						JOptionPane.YES_NO_CANCEL_OPTION);
				if (a == 0) {
					Pacient p = new Pacient(tfIme.getText(), tfPrezime.getText());
					pacijenti.addWithIncrementedId(p);

					try {
						pacijenti.writePacijenti(pacijenti);
						pacijenti = pacijenti.readPacijenti();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
//					Object[] pacijentTemppp = pacijenti.getPacijenti().toArray();
//					cmbPacijent = new JComboBox<>(pacijenti.getPacijenti().toArray());
					cmbPacijent.addItem(p);
					cmbPacijent.repaint();
				}

			}
		});

		btnDodajLekara = new JButton("Dodaj novog lekara");
		btnDodajLekara.setVisible(false);
		btnDodajLekara.setEnabled(false);
		panelNorthLeft.add(btnDodajLekara);

		btnDodajLekara.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel();
				JLabel lblIme = new JLabel("Ime");
				JLabel lblPrezime = new JLabel("Prezime");
				JLabel lblKorisnickoIme = new JLabel("Korisnicko ime");
				JLabel lblLozinka = new JLabel("Lozinka");

				JTextField tfIme = new JTextField(20);
				JTextField tfPrezime = new JTextField(20);
				JTextField tfKorisnickoIme = new JTextField(20);
				JTextField tfLozinka = new JTextField();
				panel.setLayout(new GridLayout(4, 2));

				panel.add(lblIme);
				panel.add(tfIme);
				panel.add(lblPrezime);
				panel.add(tfPrezime);
				panel.add(lblKorisnickoIme);
				panel.add(tfKorisnickoIme);
				panel.add(lblLozinka);
				panel.add(tfLozinka);
				

				int a = JOptionPane.showConfirmDialog(getInstance(null), panel, "Dodavanje lekara",
						JOptionPane.YES_NO_CANCEL_OPTION);
				if (a == 0) {
//					Pacient p = new Pacient(tfIme.getText(), tfPrezime.getText());
//					pacijenti.addWithIncrementedId(p);
					try {
						Administratori administatori = main.beforeAdministratori();
						Lekari lekari= main.beforeLekari();
						
						boolean postojiKorisnickoIme =false;
						for(Admin admin :administatori.getAdministratori()) {
							if(admin.getKorisnickoIme().equals(tfKorisnickoIme.getText())) {
								postojiKorisnickoIme=true;
							}
						}
						
						for(Lekar lekar : lekari.getLekari()) {
							if(lekar.getKorisnickoIme().equals(tfKorisnickoIme.getText())) {
								postojiKorisnickoIme=true;
							}
						}
						
						if(!postojiKorisnickoIme) {
							lekari.addLekar(new Lekar(tfIme.getText(), tfPrezime.getText(), tfKorisnickoIme.getText(), tfLozinka.getText()));
							tfError.setText("");
							Lekari.writeLekari(lekari);
							lekari = Lekari.readLekari();
							
						}else
						{tfError.setText("korisnicko ime vec postoji");}
						
						
						
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

					try {
//						pacijenti.writePacijenti(pacijenti);
//						pacijenti = pacijenti.readPacijenti();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
//					Object[] pacijentTemppp = pacijenti.getPacijenti().toArray();
//					cmbPacijent = new JComboBox<>(pacijenti.getPacijenti().toArray());
//					cmbPacijent.addItem(p);
//					cmbPacijent.repaint();
				}

			}
		});

		btnObrisiPacijenta = new JButton("Obrisi selektovanog pacijenta");
		btnObrisiPacijenta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					int optionResoult = JOptionPane.showConfirmDialog(getInstance(null),
							"Da li ste sigurni da hocete da obrisete: \n"
									+ ((Pacient) cmbPacijent.getSelectedItem()).toString(),
							"Brisanje pacijenta", JOptionPane.YES_NO_CANCEL_OPTION);
					System.out.println(optionResoult);
					if (optionResoult == 0) {
						pacijenti.removePacijent((Pacient) cmbPacijent.getSelectedItem());
						System.out.println("removed");
						try {
							pacijenti.writePacijenti(pacijenti);
							pacijenti = pacijenti.readPacijenti();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						//zasto ovako kada sam vec u Doktorfreju? drogosh
						DoctorFrame d = DoctorFrame.getInstance(null);
						d.getCmbPacijent().removeItem((Pacient) cmbPacijent.getSelectedItem());
						getInstance(null).repaint();

//					DoctorFrame d = DoctorFrame.getInstance(null);
//					d.getCmbPacijent().removeItem((Pacient)cmbPacijent.getSelectedItem());
//					d.cmbPacijent = new JComboBox<>(p.getPacijenti().toArray());
//					getInstance(null).repaint();
//					getInstance(null).update(null);;
//					getInstance(null).repaint();

					}

				} catch (Exception eexption) {
					tfError.setText(
							"Nijedan pacijent nije selektovan, prazna lista pacijenata, kreirajte novog pacijenta, ili kotaktirajte administratora");
				}

			}
		});
		
		btnMonitoringPacijenta = new JButton("Monitoring pacijenta");
		
		btnIzbeshtaji = new JButton("Izveshtaji");

		JPanel panelNorthRight = new JPanel();
		panelNorthRight.setLayout(new GridLayout(5, 1));
		panelNorthRight.add(btnIzmeniPacijent);
		panelNorthRight.add(btnDodajPacijenta);
		panelNorthRight.add(btnObrisiPacijenta);
		panelNorthRight.add(btnMonitoringPacijenta);
		panelNorthRight.add(btnIzbeshtaji);

		JPanel panelNorthAll = new JPanel();

		panelNorthAll.setLayout(new GridLayout(1	, 2));
		panelNorthAll.add(panelNorthLeft);
		panelNorthAll.add(panelNorthRight);

		tfPreporukaPrvaGrupa = new JTextField(100);
		tfPreporukaDrugaGrupa = new JTextField(100);
		tfPreporukaTrecaGrupa = new JTextField(100);
		panelPreporuke = new JPanel();
		panelPreporuke.setLayout(new GridLayout(3, 1));
		panelPreporuke.add(tfPreporukaPrvaGrupa);
		panelPreporuke.add(tfPreporukaDrugaGrupa);
		panelPreporuke.add(tfPreporukaTrecaGrupa);
		
		
		tfError = new JTextField(20);
		tfError.setForeground(Color.RED);
		add(panelNorthAll, BorderLayout.NORTH);
		
		
		try {
			prepisiTerapijuPanel = new PrepisiTerapijuPanel();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JPanel temp = new JPanel();
		JPanel tempTitel = new JPanel();
		tempTitel.setLayout(new BorderLayout());
		
		tempTitel.add(new JLabel("XXXXXXXXXXXXXXXXXXX"), BorderLayout.NORTH);
		tempTitel.add(temp, BorderLayout.CENTER);

		
		temp.setLayout(new GridLayout(3, 1));
		temp.add(dualListBox);
		temp.add(new JLabel("Dijagnoziranje i prepisivanje terapije"));
		
		JPanel tempBottomSplit = new JPanel();
		btnDodajPrepisiTerapiju = new JButton("Prepisi terapiju");
		btnDodajPrepisiTerapiju.setEnabled(false);
		btnDodajPrepisiTerapiju.setToolTipText("preverite alergije prvo");
		
		tempBottomSplit.setLayout(new BorderLayout());
		tempBottomSplit.add(prepisiTerapijuPanel, BorderLayout.CENTER);
		tempBottomSplit.add(btnDodajPrepisiTerapiju, BorderLayout.EAST);
		
		temp.add(tempBottomSplit);
//		temp.add(prepisiTerapijuPanel);
		add(tempTitel, BorderLayout.CENTER); 
		tempTitel.setVisible(true);
//		add(dualListBox, BorderLayout.CENTER);
		add(panelPreporuke, BorderLayout.SOUTH);
		JPanel panelEast = new JPanel();
		panelEast.setLayout(new GridLayout(3, 1));
		taAlergijeNaLek = new JTextArea();
		taAlergijeNaSastojak = new JTextArea();
		taAlergijeNaSastojak.setEditable(false);
		taAlergijeNaLek.setEditable(false);	
		tfError.setEditable(false);
		panelEast.add(tfError);
		
		JPanel paneAlergijeNaSastojak = new JPanel();
		paneAlergijeNaSastojak.setLayout(new BorderLayout());
		paneAlergijeNaSastojak.add(new JLabel("Alergije na sastojak iz leka"), BorderLayout.NORTH);
		//paneAlergijeNaSastojak.add()
		JScrollPane scrollPaneAlergijeNaSastojak = new JScrollPane(taAlergijeNaSastojak);
		paneAlergijeNaSastojak.add(scrollPaneAlergijeNaSastojak, BorderLayout.CENTER);
		
		panelEast.add(paneAlergijeNaSastojak);
		
		JPanel panelAlergijeNaLek = new JPanel();
		panelAlergijeNaLek.setLayout(new BorderLayout());
		panelAlergijeNaLek.add(new JLabel("Alergije na Lek"),BorderLayout.NORTH);
		panelAlergijeNaLek.add(taAlergijeNaLek,BorderLayout.CENTER);
		
		panelEast.add(panelAlergijeNaLek);
//		add(tfError, BorderLayout.EAST);
		add(panelEast, BorderLayout.EAST);
		
		
		
		
		
		setVisible(true);
	}

}
