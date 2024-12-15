package com.devicetrackermonitoring.app;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.devicetrackermonitoring.dto.ZoneDTO;

public class AddZone extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public AddZone() {
		setTitle("Add Zone");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(330, 320);
		setLocationRelativeTo(null);
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		
		// Name
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(22, 23, 100, 16);
		panel1.add(lblName);
		
		JTextField textName = new JTextField();
		textName.setBounds(120, 18, 169, 30);
		textName.setEditable(true);
		textName.setBackground(Color.white);
		panel1.add(textName);
		
		// Min Latitude
	    JLabel lblMinLatitude = new JLabel("Min Latitude");
	    lblMinLatitude.setBounds(22, 66, 100, 16);
		panel1.add(lblMinLatitude);
				
	    JTextField textMinLatitude = new JTextField();
	    textMinLatitude.setBounds(120, 58, 169, 30);
	    textMinLatitude.setEditable(true);
	    textMinLatitude.setBackground(Color.white);
		panel1.add(textMinLatitude);
		
		// Max Latitude
	    JLabel lblMaxLatitude = new JLabel("Max Latitude");
	    lblMaxLatitude.setBounds(22, 105, 100, 16);
		panel1.add(lblMaxLatitude);
				
	    JTextField textMaxLatitude = new JTextField();
	    textMaxLatitude.setBounds(120, 98, 169, 30);
	    textMaxLatitude.setEditable(true);
	    textMaxLatitude.setBackground(Color.white);
		panel1.add(textMaxLatitude);
		
		// Min Longitude
	    JLabel lblMinLongitude = new JLabel("Min Longitude");
	    lblMinLongitude.setBounds(22, 143, 100, 16);
		panel1.add(lblMinLongitude);
				
	    JTextField textMinLongitude = new JTextField();
	    textMinLongitude.setBounds(120, 138, 169, 30);
	    textMinLongitude.setEditable(true);
	    textMinLongitude.setBackground(Color.white);
		panel1.add(textMinLongitude);
		
		// Max Longitude
	    JLabel lblMaxLongitude = new JLabel("Max Longitude");
	    lblMaxLongitude.setBounds(22, 183, 100, 16);
		panel1.add(lblMaxLongitude);
		
				
	    JTextField textMaxLongitude = new JTextField();
	    textMaxLongitude.setBounds(120, 178, 169, 30);
	    textMaxLongitude.setEditable(true);
	    textMaxLongitude.setBackground(Color.white);
		panel1.add(textMaxLongitude);
		
		// Save
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(120, 230, 169, 30);
		panel1.add(btnSave);
		
		setContentPane(panel1);
		setVisible(true);
		
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(isValidDouble(textMinLatitude.getText()) && isValidDouble(textMaxLatitude.getText()) &&
						isValidDouble(textMinLongitude.getText()) && isValidDouble(textMaxLongitude.getText())) {
					String name = textName.getText();
					double minLatitude = Double.parseDouble(textMinLatitude.getText());
					double maxLatitude = Double.parseDouble(textMaxLatitude.getText());
					double minLongitude = Double.parseDouble(textMinLongitude.getText());
					double maxLongitude = Double.parseDouble(textMaxLongitude.getText());
					
					ZoneDTO.addZone(name, minLatitude, maxLatitude, minLongitude, maxLongitude);
					
					JOptionPane.showMessageDialog(null, "Zone sucessfully added!");
				} else {
					JOptionPane.showMessageDialog(null, "Format input not applicable!");
				}
			}
				
		});
	}
	
	public boolean isValidDouble(String str) {
	    if (str == null || str.isEmpty()) {
	        return false;
	    }
	    try {
	        Double.parseDouble(str);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
	
 }