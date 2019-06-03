package com.baske.view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.kie.api.KieServices;
import org.kie.api.cdi.KSession;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import com.baske.cep.DisanjeEvent;
import com.baske.cep.HeartBeatEvent;
import com.baske.model.IspisPacijentMonitoring;
import com.baske.model.Pacient;

public class MonitoringPacijentaDialog extends JDialog {

	private JTextArea taIspis;
	private JTextField tfHeartBeat;
	private JTextField tfKiseonikUKrvi;
	private Pacient selektovaniPacijent;
	private IspisPacijentMonitoring ispisPacijentMonitoring;
	private JButton btnStart;
	private KieSession ks;
	private Thread thread = new Thread();
	private JButton btnStop;
	private boolean shouldBeat = true;
	private double n = 1;
	private JButton ubrzajSrcaniRitam;
	private JButton usporiSrcaniRitam;
	private JButton povecajKiseonik;
	private JButton smanjiKiseonik;
	private int kiseonik = 0;

	public KieSession getKs() {
		return ks;
	}

	public void setKs(KieSession ks) {
		this.ks = ks;
	}

	public JTextArea getTaIspis() {
		return taIspis;
	}

	public void setTaIspis(JTextArea taIspis) {
		this.taIspis = taIspis;
	}

	public JTextField getTfHeartBeat() {
		return tfHeartBeat;
	}

	public void setTfHeartBeat(JTextField tfHeartBeat) {
		this.tfHeartBeat = tfHeartBeat;
	}

	public JTextField getTfKiseonikUKrvi() {
		return tfKiseonikUKrvi;
	}

	public void setTfKiseonikUKrvi(JTextField tfKiseonikUKrvi) {
		this.tfKiseonikUKrvi = tfKiseonikUKrvi;
	}

	public IspisPacijentMonitoring getIspisPacijentMonitoring() {
		return ispisPacijentMonitoring;
	}

	public void setIspisPacijentMonitoring(IspisPacijentMonitoring ispisPacijentMonitoring) {
		this.ispisPacijentMonitoring = ispisPacijentMonitoring;
	}

	public MonitoringPacijentaDialog(Frame owner, Pacient pacient, boolean modal, KieSession s) {
		super(owner, "xxxxxxxxxxxxxxx", modal);
		selektovaniPacijent = pacient;
		ks = s;
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		setSize(900, 900);
		setLayout(new GridLayout(2, 1));

		taIspis = new JTextArea();
		taIspis.setEditable(false);
		tfHeartBeat = new JTextField(20);
		tfKiseonikUKrvi = new JTextField(20);
		tfHeartBeat.setEditable(false);
		tfKiseonikUKrvi.setEditable(false);
		ispisPacijentMonitoring = new IspisPacijentMonitoring();

		JPanel panelNorth = new JPanel();
		panelNorth.setLayout(new BorderLayout());
		JPanel panelSouth = new JPanel();
		panelSouth.setLayout(new BorderLayout());

		JScrollPane scrollPane = new JScrollPane(taIspis);
		panelSouth.add(new JLabel("Poruke "), BorderLayout.NORTH);
		panelSouth.add(scrollPane, BorderLayout.CENTER);

		btnStop = new JButton("Zaustavi za 10 sekundi");
		btnStop.setEnabled(false);

		ubrzajSrcaniRitam = new JButton("Ubrzaj srcani ritam");
		usporiSrcaniRitam = new JButton("Uspori srcani ritam");
		ubrzajSrcaniRitam.setEnabled(false);
		usporiSrcaniRitam.setEnabled(false);
		povecajKiseonik = new JButton("Povecaj kolicinu kiseonika");
		smanjiKiseonik = new JButton("Smanji kolicinu kiseonika");
		povecajKiseonik.setEnabled(false);
		smanjiKiseonik.setEnabled(false);

		btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnStart.setEnabled(false);
				btnStop.setEnabled(true);
				usporiSrcaniRitam.setEnabled(true);
				ubrzajSrcaniRitam.setEnabled(true);
				povecajKiseonik.setEnabled(true);
				smanjiKiseonik.setEnabled(true);

				System.out.println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV");
				ks.insert(selektovaniPacijent);
				ks.insert(taIspis);
//				Thread t = new Thread() {
				thread = new Thread() {
					@Override

					public void run() {
//						for (int index = 0; index < 1500; index++) {
						while (shouldBeat) {
							HeartBeatEvent beep = new HeartBeatEvent();
							DisanjeEvent d = new DisanjeEvent(75 + kiseonik);
							ks.insert(beep);
							ks.insert(d);
							tfHeartBeat.setText("" + 1 * n + " otkucaja u sec");
							tfKiseonikUKrvi.setText("" + (75 + kiseonik) + " mmHg kiseonika u krvi\n");
							try {
								int a = (int) Math.round(1000 / n);
								System.out.println(a);
								Thread.sleep(a);
								// Thread.sleep(500);
							} catch (InterruptedException e) {
								// do nothing
							}
						}
					}
				};
				thread.setDaemon(true);
				thread.start();

				Thread t2 = new Thread() {
					@Override

					public void run() {
						ks.fireUntilHalt();
					}
				};

				t2.setDaemon(true);
				t2.start();

			}
		});

		btnStop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				shouldBeat = false;
				btnStop.setEnabled(false);
				usporiSrcaniRitam.setEnabled(false);
				ubrzajSrcaniRitam.setEnabled(false);
				povecajKiseonik.setEnabled(false);
				smanjiKiseonik.setEnabled(false);

			}
		});

		ubrzajSrcaniRitam.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				n *= 2;
				// tada je thread sleep(125)
				ubrzajSrcaniRitam.setEnabled(true);
				usporiSrcaniRitam.setEnabled(true);
				if (n == 8) {
					ubrzajSrcaniRitam.setEnabled(false);
				}

			}
		});

		usporiSrcaniRitam.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				n /= 2;
				ubrzajSrcaniRitam.setEnabled(true);
				usporiSrcaniRitam.setEnabled(true);
				if (n == 0.125) {
					usporiSrcaniRitam.setEnabled(false);
				}

			}
		});

		povecajKiseonik.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				kiseonik += 5;

			}
		});

		smanjiKiseonik.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (kiseonik > -65) {
					kiseonik -= 5;
				}

			}
		});

		JPanel panelNTemp = new JPanel();
		panelNTemp.setLayout(new GridLayout(4, 2));
		panelNTemp.add(tfHeartBeat);
		panelNTemp.add(tfKiseonikUKrvi);

		panelNTemp.add(usporiSrcaniRitam);
		panelNTemp.add(smanjiKiseonik);

		panelNTemp.add(ubrzajSrcaniRitam);
		panelNTemp.add(povecajKiseonik);

		panelNTemp.add(btnStop);
		panelNTemp.add(btnStart);

		panelNorth.add(panelNTemp, BorderLayout.CENTER);
		add(panelNorth);
		add(panelSouth);

		setVisible(true);
	}

	public JButton getBtnStart() {
		return btnStart;
	}

	public void setBtnStart(JButton btnStart) {
		this.btnStart = btnStart;
	}

	// ako izadje na alt f4 ili X
	public void stopTread() throws Exception {
		// TODO Auto-generated method stub
		if (thread.isAlive()) {
			thread.stop();

		}
		ks.halt();

	}

	// dodao
	public void deleteAllFactHandles() {
		if (!ks.getFactHandles().isEmpty()) {
			for (FactHandle f : ks.getFactHandles()) {
				ks.delete(f);
				System.out.println("Deleting FactHandle " + f.toString());
			}
		}else {
			System.out.println("FactHandles is EMPTY!!!!");
		}
		ks.halt();
	}

}
