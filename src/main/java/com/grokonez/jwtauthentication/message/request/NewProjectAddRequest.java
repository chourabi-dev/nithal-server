package com.grokonez.jwtauthentication.message.request;


public class NewProjectAddRequest {

	
    private String name;
    private String start_date;
    private String end_date;
    private String country;
	private long clientId;
    private String activity;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public long getClientId() {
		return clientId;
	}
	public void setClientId(long clientId) {
		this.clientId = clientId;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public NewProjectAddRequest(String name, String start_date, String end_date, String country, long clientId,
			String activity) {
		super();
		this.name = name;
		this.start_date = start_date;
		this.end_date = end_date;
		this.country = country;
		this.clientId = clientId;
		this.activity = activity;
	}
	public NewProjectAddRequest() {
		super();
	}
    
    
    
}
