package com.grokonez.jwtauthentication.message.request;

public class TeamAffectProject {

	private Long id_team;
	private Long id_project;
	public Long getId_team() {
		return id_team;
	}
	public void setId_team(Long id_team) {
		this.id_team = id_team;
	}
	public Long getId_project() {
		return id_project;
	}
	public void setId_project(Long id_project) {
		this.id_project = id_project;
	}
	public TeamAffectProject(Long id_team, Long id_project) {
		super();
		this.id_team = id_team;
		this.id_project = id_project;
	}
	
	
	
}
