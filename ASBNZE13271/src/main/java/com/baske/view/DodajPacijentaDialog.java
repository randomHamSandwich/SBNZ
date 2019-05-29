package com.baske.view;

import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
@Deprecated
public class DodajPacijentaDialog extends JDialog{
	private JLabel lblIme;
	private JLabel lblPrezime;
	private JLabel lblAlergijaNaLekove;
	private JLabel lblAlergijaNaSastojkeIzLeka;
	
	private JTextField tfIme;
	private JTextField ftPrezime;
	
	
	@Deprecated
	public DodajPacijentaDialog(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		// TODO Auto-generated constructor stub
	}

	
}
