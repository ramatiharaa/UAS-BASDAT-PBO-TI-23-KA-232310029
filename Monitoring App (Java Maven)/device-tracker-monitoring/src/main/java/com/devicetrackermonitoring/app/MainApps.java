package com.devicetrackermonitoring.app;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class MainApps extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton monitBtn, deviceBtn, zoneBtn;
	private JPanel contentPane;

	public static void main(String[] args) {
		MainApps frame = new MainApps();
		frame.setVisible(true);
	}

	public MainApps() {
		setTitle("Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 256, 350);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 0, 0, 0));
		
		monitBtn = new JButton("Monitoring");
		contentPane.add(monitBtn);
		
		deviceBtn = new JButton("Add Device");
		contentPane.add(deviceBtn);
		
		zoneBtn = new JButton("Add Zone");
		contentPane.add(zoneBtn);

		monitBtn.addActionListener(this);
		deviceBtn.addActionListener(this);
		zoneBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == monitBtn) {
			new Monitoring();
		} else if (e.getSource() == deviceBtn) {
			new AddDevice();
		} else if (e.getSource() == zoneBtn) {
			new AddZone();
		}
	}

}