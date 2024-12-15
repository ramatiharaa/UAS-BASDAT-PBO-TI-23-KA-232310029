package com.devicetrackermonitoring.dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.devicetrackermonitoring.dao.Device;
import com.devicetrackermonitoring.db.ConnectionDB;

public class DeviceDTO {

	public static List<Device> getAllDevices() {
        List<Device> deviceList = new ArrayList<>();
        String query = "SELECT * FROM device";
        
        try (Connection conn = ConnectionDB.getConnection();
        	PreparedStatement stmt = conn.prepareStatement(query)) {
        		
        	ResultSet rs = stmt.executeQuery(query);
        		
        	while (rs.next()) {
	            int deviceId = rs.getInt("device_id");
	            String deviceCode = rs.getString("device_code");
	            double latitude = rs.getDouble("latitude");
	            double longitude = rs.getDouble("longitude");
	            String lastAccess = rs.getString("last_access");
	            int zoneId = rs.getInt("zone_id");
	            Device device = new Device(deviceId, deviceCode, latitude, longitude, lastAccess, zoneId);
	            deviceList.add(device);   
        	}
        	
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return deviceList;
	}
	
	public static void addDevice(String deviceCode, int zoneId) {
	    String query = "INSERT INTO device (device_code, zone_id) VALUES (?, ?)";
	    
	    try (Connection conn = ConnectionDB.getConnection();
	    	PreparedStatement stmt = conn.prepareStatement(query)) {
	    	
	    	stmt.setString(1, deviceCode);
		    stmt.setInt(2, zoneId);
		    
		    stmt.executeUpdate();
	    	
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
	}
	
	public static int getDeviceIdFromName(String zoneName) {
        String query = "SELECT device_id FROM device WHERE device_code = ?";
        
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, zoneName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return 1;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

}