package com.baske.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.baske.model.Bolest;
import com.baske.model.Bolesti;
import com.baske.model.Lek;
import com.baske.model.Lekovi;

public class PrepisiTerapijuPanel extends JPanel {
//	public PrepisanaTerapija(Bolest bolest, ArrayList<Lek> prepisaniLekovi, String preporukaLekar,
//			LocalDate datumPrepisaneTerapiej, double temperatur, boolean visokPritisak
	JLabel lblBolest;
	JLabel lblPrepisaniLekovi;
	JLabel lblPrepoprukaLekara;
	JComboBox<Object> cmbBolesti;
	DualListBox dualListBoxPrepisaniLekovi;
	JTextField tfPreporukaLekara;

	public JComboBox<Object> getCmbBolesti() {
		return cmbBolesti;
	}

	public void setCmbBolesti(JComboBox<Object> cmbBolesti) {
		this.cmbBolesti = cmbBolesti;
	}

	public JTextField getTfPreporukaLekara() {
		return tfPreporukaLekara;
	}

	public void setTfPreporukaLekara(JTextField tfPreporukaLekara) {
		this.tfPreporukaLekara = tfPreporukaLekara;
	}

	public DualListBox getDualListBoxPrepisaniLekovi() {
		return dualListBoxPrepisaniLekovi;
	}

	public void setDualListBoxPrepisaniLekovi(DualListBox dualListBoxPrepisaniLekovi) {
		this.dualListBoxPrepisaniLekovi = dualListBoxPrepisaniLekovi;
	}

	public PrepisiTerapijuPanel() throws Exception {
		super();
		Bolesti bolesti = main.before();
		// TODO Auto-generated constructor stub
		lblBolest = new JLabel("Bolest");
		lblPrepisaniLekovi = new JLabel("Prepisani lekovi");
		lblPrepoprukaLekara = new JLabel("Preporuka lekara");

		cmbBolesti = new JComboBox<>(bolesti.getBolesti().toArray());
		tfPreporukaLekara = new JTextField("Preporuka lekara");
		dualListBoxPrepisaniLekovi = new DualListBox();
		dualListBoxPrepisaniLekovi.getSourceLabel().setText("Lekovi");
		dualListBoxPrepisaniLekovi.getDestLabel().setText("Prepisani lekovi");
		dualListBoxPrepisaniLekovi.printChosenContent.setText("Proveri alergije");
		dualListBoxPrepisaniLekovi.btnUpit.setText("Prikazi simptome ove bolesti");
//		dualListBoxPrepisaniLekovi.printChosenContent.setEnabled(false);
		ArrayList<String> sviLekovi = new ArrayList<>();
		Lekovi lekovi = Lekovi.readLekovi();
		for (Lek l : lekovi.getLekovi()) {
			sviLekovi.add(l.getNaziv());
		}
		dualListBoxPrepisaniLekovi.addSourceElements(sviLekovi.toArray());

		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new GridLayout(2, 2));
		leftPanel.add(lblBolest);
		leftPanel.add(cmbBolesti);
		leftPanel.add(lblPrepoprukaLekara);
		leftPanel.add(tfPreporukaLekara);

		setLayout(new GridLayout(1, 2));
		add(leftPanel);
		add(dualListBoxPrepisaniLekovi);

		setVisible(true);

	}

}
