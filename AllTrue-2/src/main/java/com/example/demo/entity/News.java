package com.example.demo.entity;


import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.transaction.Transactional;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Transactional
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class News {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String titre;
	@OneToMany(mappedBy = "news")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Collection<ImageNews> imageNews;
	@OneToMany(mappedBy = "news")
	@JsonProperty(access=Access.WRITE_ONLY)
	private Collection<Resultat> resultat;
	@ManyToOne
	private Utilisateur utilisateur;

}