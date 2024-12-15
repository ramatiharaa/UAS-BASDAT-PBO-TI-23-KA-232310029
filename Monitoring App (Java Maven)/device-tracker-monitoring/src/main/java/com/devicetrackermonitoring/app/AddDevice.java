package com.devicetrackermonitoring.app;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import com.devicetrackermonitoring.dto.DeviceDTO;
import com.devicetrackermonitoring.dto.ZoneDTO;

public class AddDevice extends JFrame {

    private static final long serialVersionUID = 1L;

    public AddDevice() {
        setTitle("Add Device");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel1 = new JPanel();
        panel1.setLayout(null);

        // Device Code
        JLabel lblDeviceCode = new JLabel("Device Code");
        lblDeviceCode.setBounds(22, 23, 100, 16);
        panel1.add(lblDeviceCode);

        JTextField textDeviceCode = new JTextField();
        textDeviceCode.setBounds(105, 18, 169, 30);
        textDeviceCode.setEditable(true);
        textDeviceCode.setBackground(Color.white);
        panel1.add(textDeviceCode);

        // Zone
        JLabel lblZone = new JLabel("Zone");
        lblZone.setBounds(22, 66, 100, 16);
        panel1.add(lblZone);

        String[] zoneNames = ZoneDTO.getAllZoneName();
        JComboBox<String> selectionZone = new JComboBox<>(zoneNames);
        selectionZone.setBounds(105, 58, 169, 30);
        panel1.add(selectionZone);

        // Save
        JButton btnSave = new JButton("Save");
        btnSave.setBounds(105, 110, 169, 30);
        panel1.add(btnSave);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String deviceCode = textDeviceCode.getText();
                String selectedZone = selectionZone.getSelectedItem().toString();
                
                if (deviceCode.isEmpty() || selectedZone.isEmpty()) {
                	JOptionPane.showMessageDialog(null, "Cannot be empty!");
				} else {
					
					if (DeviceDTO.getDeviceIdFromName(deviceCode) ==  1) {
						JOptionPane.showMessageDialog(null, "Device code exists!");
					} else {
						 int zoneId = ZoneDTO.getZoneIdFromName(selectedZone);
						 DeviceDTO.addDevice(deviceCode, zoneId);
			             JOptionPane.showMessageDialog(null, "Device successfully added!");
					}
					
				}
            }
        });

        setContentPane(panel1);
        setVisible(true);
    }
  
}