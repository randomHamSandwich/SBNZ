package com.baske.view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.kie.api.runtime.KieSession;

public class IzveshtanjiDialog extends JDialog{
	private JButton btnHronicnaOboljenjaIzvestaj;
	private JButton btnMoguciZavisnici;
	private JButton btnOslabljenImunitet;
	private JTextArea taIspis;
	private JButton btnOk;
	private KieSession ks;
	
	
	public JButton getBtnHronicnaOboljenjaIzvestaj() {
		return btnHronicnaOboljenjaIzvestaj;
	}


	public void setBtnHronicnaOboljenjaIzvestaj(JButton btnHronicnaOboljenjaIzvestaj) {
		this.btnHronicnaOboljenjaIzvestaj = btnHronicnaOboljenjaIzvestaj;
	}


	public JButton getBtnMoguciZavisnici() {
		return btnMoguciZavisnici;
	}


	public void setBtnMoguciZavisnici(JButton btnMoguciZavisnici) {
		this.btnMoguciZavisnici = btnMoguciZavisnici;
	}


	public JButton getBtnOslabljenImunitet() {
		return btnOslabljenImunitet;
	}


	public void setBtnOslabljenImunitet(JButton btnOslabljenImunitet) {
		this.btnOslabljenImunitet = btnOslabljenImunitet;
	}


	public JTextArea getTaIspis() {
		return taIspis;
	}


	public void setTaIspis(JTextArea taIspis) {
		this.taIspis = taIspis;
	}


	public IzveshtanjiDialog(Frame owner,KieSession ksess,  boolean modal) {
		super(owner,modal);
		ks = ksess;
		setTitle("Izvestaji");
		setSize(900, 500);
		
		btnHronicnaOboljenjaIzvestaj = new JButton("Spisak pacijenata sa mogucim hronicim oboljenjima");
		btnMoguciZavisnici = new JButton("Spiska mogucih zavisnika");
		btnOslabljenImunitet = new JButton("Spisak pacijenata sa oslabljenim imunitetom");
		btnOk = new JButton("Ok");
		taIspis = new JTextArea();
		taIspis.setEditable(false);
		
		
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				
			}
		});
		btnHronicnaOboljenjaIzvestaj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ks.getAgenda().getAgendaGroup("hronicni").setFocus();
				ks.insert(taIspis);
				ks.fireAllRules();
				
			}
		});
		
		btnOslabljenImunitet.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ks.getAgenda().getAgendaGroup("osabljeniImunitet").setFocus();
				ks.insert(taIspis);
				ks.fireAllRules();
				
			}
		});
		
		btnMoguciZavisnici.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ks.getAgenda().getAgendaGroup("moguciZavisnici").setFocus();
				ks.insert(taIspis);
				ks.fireAllRules();
				
			}
		});
		
		this.setLayout(new  BorderLayout());
		JPanel top = new JPanel();
		top.setLayout(new GridLayout(1, 3));
		top.add(btnHronicnaOboljenjaIzvestaj);
		top.add(btnMoguciZavisnici);
		top.add(btnOslabljenImunitet);
		
		add(top,BorderLayout.NORTH);
		
		JScrollPane sp = new JScrollPane(taIspis);
		add(sp, BorderLayout.CENTER);
		add(btnOk, BorderLayout.SOUTH);
		
	
		
		setVisible(true);
		
		
	}

}
