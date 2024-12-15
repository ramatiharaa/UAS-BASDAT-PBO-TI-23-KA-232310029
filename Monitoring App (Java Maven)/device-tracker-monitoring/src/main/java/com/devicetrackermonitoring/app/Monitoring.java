package com.devicetrackermonitoring.app;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import com.devicetrackermonitoring.dao.Device;
import com.devicetrackermonitoring.dao.Zone;
import com.devicetrackermonitoring.dto.DeviceDTO;
import com.devicetrackermonitoring.dto.ZoneDTO;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;
import java.time.LocalTime;
import java.util.List;

public class Monitoring extends JFrame {

    private static final long serialVersionUID = 1L;
    private DefaultTableModel model;

    public Monitoring() {
        setTitle("Monitoring");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 600);
        setLocationRelativeTo(null);

        model = new DefaultTableModel();
        model.addColumn("Device Code");
        model.addColumn("Latitude");
        model.addColumn("Longitude");
        model.addColumn("Last Access");
        model.addColumn("Zone");
        model.addColumn("Inside Zone?");
        model.addColumn("CP on Working Hours?");
        model.addColumn("Google Maps Link");

        JTable table = new JTable(model);
        setupTable(table);

        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        loadData();

        Timer timer = new Timer(5000, e -> loadData());
        timer.start();

        setVisible(true);
    }

    private void setupTable(JTable table) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        table.setRowHeight(30);
        table.setDefaultEditor(Object.class, null);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());
                if (col == 7) {
                    String latitude = table.getValueAt(row, 1).toString();
                    String longitude = table.getValueAt(row, 2).toString();
                    openGoogleMaps(latitude, longitude);
                }
            }
        });
    }

    private void loadData() {
        List<Device> devices = DeviceDTO.getAllDevices();

        model.setRowCount(0);

        for (Device device : devices) {
        	String zoneName = getZoneName(device.getZoneId());
            String insideArea = (device.getLatitude() == 0 || device.getLongitude() == 0) ? "" : isInsideArea(device) ? "Inside Zone" : "Outside Zone";
            String workingHours = workingHours(device.getLastAccess());
            String googleMapsLink = (device.getLatitude() == 0 || device.getLongitude() == 0) ? "" : "<html><a href=''>View on Maps</a></html>";

            model.addRow(new Object[] {
                device.getDeviceCode(),
                (device.getLatitude() == 0) ? "" : device.getLatitude(),
                (device.getLongitude() == 0) ? "" : device.getLongitude(),
                device.getLastAccess(),
                zoneName,
                insideArea,
                workingHours,
                googleMapsLink
            });
        }
        
    }

    // Helper methods ------------------------------------------------------------------------------------------------

    private String getZoneName(int id) {
        Zone zone = ZoneDTO.getZoneById(id);
        return zone.getName();
    }

    private boolean isInsideArea(Device device) {
        Zone zone = ZoneDTO.getZoneById(device.getZoneId());

        double deviceLat = device.getLatitude();
        double deviceLon = device.getLongitude();
        
        if (zone.getMinLatitude() <= deviceLat && deviceLat <= zone.getMaxLatitude()) {
        	if (zone.getMinLongitude() <= deviceLon && deviceLon <= zone.getMaxLongitude()) {
        		return true;
        	}
        }
        return false;
    }

    private String workingHours(String lastAccess) {
        if (lastAccess == null) {
            return "";
        }

        try {
            LocalTime accessTime = LocalTime.parse(lastAccess.substring(11, 19));
            LocalTime startTime = LocalTime.of(8, 0);
            LocalTime endTime = LocalTime.of(17, 0);
            
            if (accessTime.isAfter(startTime) && accessTime.isBefore(endTime)) {
            	return "Yes";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "No";
    }

    private void openGoogleMaps(String latitude, String longitude) {
        try {
            String url = "https://www.google.com/maps?q=" + latitude + "," + longitude;
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

