package com.grokonez.jwtauthentication.message.request;

public class CordsUpdate {

    private Double longitde; 
	private Double altitude;
	
	public Double getLongitde() {
		return longitde;
	}
	public void setLongitde(Double longitde) {
		this.longitde = longitde;
	}
	public Double getAltitude() {
		return altitude;
	}
	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}
	public CordsUpdate(Double longitde, Double altitude) {
		super();
		this.longitde = longitde;
		this.altitude = altitude;
	} 
	
	
}
