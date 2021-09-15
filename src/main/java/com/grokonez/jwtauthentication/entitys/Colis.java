package com.grokonez.jwtauthentication.entitys;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.grokonez.jwtauthentication.model.User;
 
@Entity
@Table(name = "colis")
public class Colis {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(length = 300)
	private String adrExpediteur;
    private Double poids;
    private Double hauteur;
    private Double largeur;
    private boolean collect;
    
    @Column(length = 30)
    private String type;
    
    @Column(length = 30)
    private String urgence;
     
    private int etat = 0;
    
    @CreationTimestamp
    private Date dateCreation;
    
    private String adrDistinataire;
	private String villeDistinataire;
	private String codePostalDistinataire;
	private String govDistinataire; 
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;
    
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdrExpediteur() {
		return adrExpediteur;
	}

	public void setAdrExpediteur(String adrExpediteur) {
		this.adrExpediteur = adrExpediteur;
	}

	public Double getPoids() {
		return poids;
	}

	public void setPoids(Double poids) {
		this.poids = poids;
	}

	public Double getHauteur() {
		return hauteur;
	}

	public void setHauteur(Double hauteur) {
		this.hauteur = hauteur;
	}

	public Double getLargeur() {
		return largeur;
	}

	public void setLargeur(Double largeur) {
		this.largeur = largeur;
	}

	public boolean isCollect() {
		return collect;
	}

	public void setCollect(boolean collect) {
		this.collect = collect;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrgence() {
		return urgence;
	}

	public void setUrgence(String urgence) {
		this.urgence = urgence;
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getAdrDistinataire() {
		return adrDistinataire;
	}

	public void setAdrDistinataire(String adrDistinataire) {
		this.adrDistinataire = adrDistinataire;
	}

	public String getVilleDistinataire() {
		return villeDistinataire;
	}

	public void setVilleDistinataire(String villeDistinataire) {
		this.villeDistinataire = villeDistinataire;
	}

	public String getCodePostalDistinataire() {
		return codePostalDistinataire;
	}

	public void setCodePostalDistinataire(String codePostalDistinataire) {
		this.codePostalDistinataire = codePostalDistinataire;
	}

	public String getGovDistinataire() {
		return govDistinataire;
	}

	public void setGovDistinataire(String govDistinataire) {
		this.govDistinataire = govDistinataire;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Colis() {
		super();
	}
    
    
    
}
