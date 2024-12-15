package com.devicetrackermonitoring.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	public static Connection getConnection() {
		try {
			String url = "jdbc:mysql://localhost:3306/device_tracker";
			String username = "root";
			String password = "";
			Connection conn = DriverManager.getConnection(url, username, password);
            return conn;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}