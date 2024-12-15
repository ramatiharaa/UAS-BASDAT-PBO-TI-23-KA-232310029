package com.devicetrackermonitoring.dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.devicetrackermonitoring.dao.Zone;
import com.devicetrackermonitoring.db.ConnectionDB;

public class ZoneDTO {
    
    public static Zone getZoneById(int id) {
        String query = "SELECT * FROM zone WHERE zone_id = ? LIMIT 1";
        
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                int zoneId = rs.getInt("zone_id");
                String name = rs.getString("name");
                double minLatitude = rs.getDouble("min_latitude");
                double maxLatitude = rs.getDouble("max_latitude");
                double minLongitude = rs.getDouble("min_longitude");
                double maxLongitude = rs.getDouble("max_longitude");
                
                Zone zone = new Zone(zoneId, name, minLatitude, maxLatitude, minLongitude, maxLongitude);
                return zone;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public static List<Zone> getAllZones() {
    	List<Zone> zoneList = new ArrayList<>();
    	String query = "SELECT * FROM zone";
    	
    	try (Connection conn = ConnectionDB.getConnection();
    		PreparedStatement stmt = conn.prepareStatement(query)) {
    		
    		ResultSet rs = stmt.executeQuery(query);
    		
    		while (rs.next()) {
    			int zoneId = rs.getInt("zone_id");
    			String name = rs.getString("Name");
    			double minLatitude = rs.getDouble("min_latitude");
                double maxLatitude = rs.getDouble("max_latitude");
                double minLongitude = rs.getDouble("min_longitude");
                double maxLongitude = rs.getDouble("max_longitude");
                Zone zone = new Zone(zoneId, name, minLatitude, maxLatitude, minLongitude, maxLongitude);
                zoneList.add(zone);
    		}
    		
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return zoneList;
    }
    
    public static String[] getAllZoneName() {
        List<Zone> zones = getAllZones();
        String[] zoneNames = new String[zones.size()];

        for (int i = 0; i < zones.size(); i++) {
            zoneNames[i] = zones.get(i).getName();
        }

        return zoneNames;
    }

    public static int getZoneIdFromName(String zoneName) {
        String query = "SELECT zone_id FROM zone WHERE name = ?";
        
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, zoneName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("zone_id");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public static void addZone(String name,double minLatitude, double maxLatitude, double minLongitude, double maxLongitude) {
    	String query = "INSERT INTO zone (name, min_latitude, max_latitude, min_longitude, max_longitude) VALUES (?, ?, ?, ?, ?)";
    	
    	try (Connection conn = ConnectionDB.getConnection();
    			PreparedStatement stmt = conn.prepareStatement(query)) { 
    		
    		stmt.setString(1, name);
    		stmt.setDouble(2, minLatitude);
    		stmt.setDouble(3, maxLatitude);
    		stmt.setDouble(4, minLongitude);
    		stmt.setDouble(5, maxLongitude);
    		
    		stmt.executeUpdate();
    		
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    			
    }
    
    
    
}