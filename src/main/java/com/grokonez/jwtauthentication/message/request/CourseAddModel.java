package com.grokonez.jwtauthentication.message.request;

public class CourseAddModel {

	private Long id_livreur  ;
	private Long id_colis  ;
    
    public Long getId_colis() {
		return id_colis;
	}
	public void setId_colis(Long id_colis) {
		this.id_colis = id_colis;
	}
	private Long id_vehicule  ;
    private String description  ;
    
    
	public Long getId_livreur() {
		return id_livreur;
	}
	public void setId_livreur(Long id_livreur) {
		this.id_livreur = id_livreur;
	}
	public Long getId_vehicule() {
		return id_vehicule;
	}
	public void setId_vehicule(Long id_vehicule) {
		this.id_vehicule = id_vehicule;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public CourseAddModel(Long id_livreur, Long id_vehicule, String description) {
		super();
		this.id_livreur = id_livreur;
		this.id_vehicule = id_vehicule;
		this.description = description;
	}
	public CourseAddModel() {
		super();
	}
    
    
     
     
}
