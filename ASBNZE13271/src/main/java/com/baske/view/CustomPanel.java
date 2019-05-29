package com.baske.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CustomPanel extends JPanel {
	private static CustomPanel instance = null;
	private CustomLableTextField cltf1;
	private JCheckBox visokPritisak;
	private JCheckBox opeoravljaSeOdOperacije;

	public JCheckBox getOpeoravljaSeOdOperacije() {
		return opeoravljaSeOdOperacije;
	}

	public void setOpeoravljaSeOdOperacije(JCheckBox opeoravljaSeOdOperacije) {
		this.opeoravljaSeOdOperacije = opeoravljaSeOdOperacije;
	}

	private JPanel content;

	private CustomPanel() {
		initialize();
	}

	public static CustomPanel getInstance() {
		if (instance == null) {
			instance = new CustomPanel();
		}
		return instance;
	}

	private void initialize() {
		setSize(300, 300);
		cltf1 = new CustomLableTextField("temperatura");
		visokPritisak = new JCheckBox("visok pritisak");
		opeoravljaSeOdOperacije = new JCheckBox("oporavlja se od operacije");
		content = new JPanel();
		content.add(cltf1);
		content.add(visokPritisak);
		content.add(opeoravljaSeOdOperacije);
		add(content);
		setVisible(true);

	}

	class CustomLableTextField extends JPanel {
		private JTextField tf;
		private JLabel lable;

		public CustomLableTextField(String s) {
			tf = new JTextField(5);
			lable = new JLabel(s);
			setLayout(new GridLayout(2, 1));
			add(lable);
			add(tf);
		}

		public JTextField getTf() {
			return tf;
		}

		public void setTf(JTextField tf) {
			this.tf = tf;
		}

		public JLabel getLable() {
			return lable;
		}

		public void setLable(JLabel lable) {
			this.lable = lable;
		}

	}

	public CustomLableTextField getCltf1() {
		return cltf1;
	}

	public void setCltf1(CustomLableTextField cltf1) {
		this.cltf1 = cltf1;
	}

	public JCheckBox getVisokPritisak() {
		return visokPritisak;
	}

	public void setVisokPritisak(JCheckBox visokPritisak) {
		this.visokPritisak = visokPritisak;
	}

	public JPanel getContent() {
		return content;
	}

	public void setContent(JPanel content) {
		this.content = content;
	}

}
