package com.example.demo.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Transactional

@Entity
@Data @AllArgsConstructor @NoArgsConstructor 
public class Abonnement {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long name;
	private Date dateDebut;
	private Date dateFin;
	@OneToMany(mappedBy = "abonnement")
	@JsonProperty(access=Access.READ_WRITE)
	private Collection<Utilisateur> utilisateur;
}