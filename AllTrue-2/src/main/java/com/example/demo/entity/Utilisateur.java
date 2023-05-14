package com.example.demo.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Transactional
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Data @AllArgsConstructor @NoArgsConstructor 
public class Utilisateur {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private boolean statut;
	private String nom;
	private String prenom;
	private String email;
	private String numTel;
	private String sexe;
	private String imageName;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	private String adresse;
	@Temporal(TemporalType.DATE)
	private Date dateInscription;
	private String resetPasswordToken;
	@ManyToOne(fetch=FetchType.EAGER,cascade = CascadeType.MERGE)
	@JsonProperty(access=Access.READ_WRITE)
	private Abonnement abonnement;
	@JsonProperty(access=Access.WRITE_ONLY)
	@ManyToMany(fetch=FetchType.EAGER)
	private Collection<Role> roles = new ArrayList<>();
	@OneToMany(mappedBy ="utilisateur")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Collection<News> news;
	@OneToMany(mappedBy = "news")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Collection<ImageNews> imageNews;

}
