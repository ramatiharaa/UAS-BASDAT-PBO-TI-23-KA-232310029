package com.devicetrackermonitoring.dao;

public class Device {
	private int deviceId;
	private String deviceCode;
	private double latitude;
	private double longitude;
	private String lastAccess;
	private int zoneId;
	
	public Device(int deviceId, String deviceCode, double latitude, double longitude, String lastAccess, int zoneId) {
		this.deviceId = deviceId;
		this.deviceCode = deviceCode;
		this.latitude = latitude;
		this.longitude = longitude;
		this.lastAccess = lastAccess;
		this.zoneId = zoneId;
	}
	
	public int getDeviceId() {
		return deviceId;
	}
	
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	
	public String getDeviceCode() {
		return deviceCode;
	}
	
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public String getLastAccess() {
		return lastAccess;
	}
	
	public void setLastAccess(String lastAccess) {
		this.lastAccess = lastAccess;
	}
	
	public int getZoneId() {
		return zoneId;
	}
	
	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}
}
