package com.baske.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.baske.model.Lek;
import com.baske.model.Lekovi;
import com.baske.model.Pacient;
import com.baske.model.Pacijenti;
import com.baske.model.SastojakLeka;

public class IzmeniPacijentaDialog extends JDialog {
	private JButton btnOk;
	private JButton btnCancel;
	private JLabel lblIme;
	private JLabel lblPrezime;
	private JTextField tfIme;
	private JTextField tfPRezime;
	private Pacient selektovaniPacijent;

	private JPanel paneCenterUP;
//	private JPanel paneCenterCenter;
//	private JPanel paneCenterBottom;
	private JPanel paneCenterALL;
	private JPanel panelSouth;
	private Lekovi lekovi;
	
	private JLabel lblPrepisaneTerapije;
	private JComboBox<Object> cmbPrepisaneTerapije;

	private DualListBox dualListBoxAlergijaNaLekove;
	private DualListBox dualListBoxAlergijaNaSastojkeIzLeka;

	public IzmeniPacijentaDialog(Frame owner, Pacient pacijent, boolean modal) {
		super(owner, "Pacijent: " + pacijent.toString(), modal);
		// TODO Auto-generated constructor stub
		selektovaniPacijent = pacijent;
	}

	private void dualLstBoxLekovi() throws Exception {
		dualListBoxAlergijaNaLekove = new DualListBox();
		ArrayList<String> nijeAlergicanNaOveLekove = new ArrayList<>();
		ArrayList<String> lekoviNaKoJePacijentAlergican = new ArrayList<>();
		DoctorFrame d = DoctorFrame.getInstance(null);
//		Pacijenti pacijenti = d.getPacijenti();
		lekovi = main.lekovi;
		
//		for(Lek l : lekovi.getLekovi()) {
//			if(selektovaniPacijent.getAlergijaNaLekove().contains(l)) {
//				lekoviNaKoJePacijentAlergican.add(l.getNaziv());
//			}else {
//				nijeAlergicanNaOveLekove.add(l.getNaziv());
//			}
//		}
//		for(Lek l : lekovi.getLekovi()) {
//			for(Lek l2 :selektovaniPacijent.getAlergijaNaLekove()) {
//				if(l.getNaziv()==l2.getNaziv()){
//					
//				}
//			}
//
//		}
		for(Lek l2 :selektovaniPacijent.getAlergijaNaLekove()) {
			lekoviNaKoJePacijentAlergican.add(l2.getNaziv());
		}
		for(Lek l: lekovi.getLekovi()) {
			nijeAlergicanNaOveLekove.add(l.getNaziv());
		}
		for(Lek l : selektovaniPacijent.getAlergijaNaLekove()) {
			nijeAlergicanNaOveLekove.remove(l.getNaziv());
		}
		
		
		
		
		dualListBoxAlergijaNaLekove.addSourceElements(nijeAlergicanNaOveLekove.toArray());
		dualListBoxAlergijaNaLekove.addDestinationElements(lekoviNaKoJePacijentAlergican.toArray());

		dualListBoxAlergijaNaLekove.printChosenContent.setVisible(false);
		dualListBoxAlergijaNaLekove.getSourceLabel().setText("Nije alergican na lekove");
		dualListBoxAlergijaNaLekove.getDestLabel().setText("Lekovi na koje je alergican");

		System.out.println("xxxxxxxxxxxxxxxxxxxxxx :"+selektovaniPacijent.getAlergijaNaLekove());
		
	}

	private void dualListBoxSastojci() {
		dualListBoxAlergijaNaSastojkeIzLeka = new DualListBox();
		ArrayList<String> sastojciListSVI = new ArrayList<>();
		ArrayList<String> sastojciListAlergicanNa = new ArrayList<>();
		for (SastojakLeka s : SastojakLeka.values()) {
			if (selektovaniPacijent.getAlergijanaSastojkeIzLeka().contains(s.toString())) {
				sastojciListAlergicanNa.add(s.toString());
			} else {
				sastojciListSVI.add(s.toString());
			}
//				sastojciList.add(s.toString());
		}
		dualListBoxAlergijaNaSastojkeIzLeka.addSourceElements(sastojciListSVI.toArray());
		dualListBoxAlergijaNaSastojkeIzLeka.addDestinationElements(sastojciListAlergicanNa.toArray());

		dualListBoxAlergijaNaSastojkeIzLeka.printChosenContent.setVisible(false);
		dualListBoxAlergijaNaSastojkeIzLeka.getSourceLabel().setText("Nije alergican na sastojke iz leka");
		dualListBoxAlergijaNaSastojkeIzLeka.getDestLabel().setText("Sastojci na koje je pacijent alergican");

	}

	public void initialize() {
		setSize(900, 900);
		setLayout(new BorderLayout());

		btnOk = new JButton("Ok");
		btnCancel = new JButton("Cancel");
		panelSouth = new JPanel();
		panelSouth.add(btnOk);
		panelSouth.add(btnCancel);

		lblIme = new JLabel("Ime");
		lblPrezime = new JLabel("Prezime");
		tfIme = new JTextField(30);
		tfPRezime = new JTextField(30);
		tfIme.setText(selektovaniPacijent.getIme());
		tfPRezime.setText(selektovaniPacijent.getPrezime());

		paneCenterUP = new JPanel();
		paneCenterUP.setLayout(new GridLayout(2, 2));
		paneCenterUP.add(lblIme);
		paneCenterUP.add(tfIme);
		paneCenterUP.add(lblPrezime);
		paneCenterUP.add(tfPRezime);

//		paneCenterCenter = new JPanel();
//		paneCenterBottom = new JPanel();
		paneCenterALL = new JPanel();

		try {
			dualLstBoxLekovi();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		dualListBoxSastojci();
//		paneCenterCenter.add(dualListBoxAlergijaNaSastojkeIzLeka);

		paneCenterALL.setLayout(new GridLayout(3, 1));
//		paneCenterALL.add(paneCenterUP);
		paneCenterALL.add(dualListBoxAlergijaNaSastojkeIzLeka);
		paneCenterALL.add(dualListBoxAlergijaNaLekove);
		
		
		lblPrepisaneTerapije = new JLabel("Prepisane terapije");
		cmbPrepisaneTerapije  = new  JComboBox<>(selektovaniPacijent.getTerapije().toArray());
		JPanel tempTep = new JPanel();
		tempTep.setLayout(new GridLayout(2, 1));
		tempTep.add(lblPrepisaneTerapije);
		tempTep.add(cmbPrepisaneTerapije);
		
		paneCenterALL.add(tempTep);
		
		

		add(paneCenterUP, BorderLayout.NORTH);
		add(panelSouth, BorderLayout.SOUTH);
		add(paneCenterALL, BorderLayout.CENTER);

		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selektovaniPacijent.setIme(tfIme.getText());
				selektovaniPacijent.setPrezime(tfPRezime.getText());

				// na cega je pacijent alegican SASTOJCI
				HashSet<String> sastojciAlergijaList = new HashSet<>();
				for (int i = 0; i < dualListBoxAlergijaNaSastojkeIzLeka.destList.getModel().getSize(); i++) {
					sastojciAlergijaList
							.add(dualListBoxAlergijaNaSastojkeIzLeka.destList.getModel().getElementAt(i).toString());
				}
				selektovaniPacijent.setAlergijanaSastojkeIzLeka(sastojciAlergijaList);
				
				
				
				//na koje lekove je pacijent alergican
				HashSet<String> lekoviAlergjaList = new HashSet<>();
				for(int i=0; i<dualListBoxAlergijaNaLekove.destList.getModel().getSize();i++) {
					lekoviAlergjaList.add(dualListBoxAlergijaNaLekove.destList.getModel().getElementAt(i).toString());
				}
				
				selektovaniPacijent.setAlergijaNaLekove(new HashSet<>());
				for(Lek l : lekovi.getLekovi()) {
					if(lekoviAlergjaList.contains(l.getNaziv())) {
						selektovaniPacijent.addAlergijaNaLek(l);
					}
				}
				
				
				
				
				

				DoctorFrame d = DoctorFrame.getInstance(null);
				Pacijenti pacijenti = d.getPacijenti();
				pacijenti.removePacijent(selektovaniPacijent.getId());
				pacijenti.addPacijent(selektovaniPacijent);
//				pacijenti.addWithIncrementedId(selektovaniPacijent);
				
			System.out.println(selektovaniPacijent.getAlergijaNaLekove());

				try {
					pacijenti.writePacijenti(pacijenti);
					pacijenti = pacijenti.readPacijenti();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				setVisible(false);
			}
		});

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});

		setVisible(true);
	}

}
