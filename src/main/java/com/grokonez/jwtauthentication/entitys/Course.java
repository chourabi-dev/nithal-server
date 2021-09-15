package com.grokonez.jwtauthentication.entitys;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.grokonez.jwtauthentication.model.User;
 
@Entity
@Table(name = "courses")
public class Course {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@CreationTimestamp
	private Date date;
	
	
	private String description;
     
	@ManyToOne()
	@JoinColumn(name = "vehicules_id", nullable = false) 
	private Vehicules vehicule;
	 
	
	@ManyToOne()
	@JoinColumn(name = "colis_id", nullable = false) 
	private Colis colis;
	 
	
	
	@ManyToOne()
	@JoinColumn(name = "users_id", nullable = false) 
	private User livreur;



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public Vehicules getVehicule() {
		return vehicule;
	}



	public void setVehicule(Vehicules vehicule) {
		this.vehicule = vehicule;
	}



	public Colis getColis() {
		return colis;
	}



	public void setColis(Colis colis) {
		this.colis = colis;
	}



	public User getLivreur() {
		return livreur;
	}



	public void setLivreur(User livreur) {
		this.livreur = livreur;
	}



	public Course(Long id, Date date, String description, Vehicules vehicule, Colis colis, User livreur) {
		super();
		this.id = id;
		this.date = date;
		this.description = description;
		this.vehicule = vehicule;
		this.colis = colis;
		this.livreur = livreur;
	}



	public Course() {
		super();
	}
	
	
	
}
