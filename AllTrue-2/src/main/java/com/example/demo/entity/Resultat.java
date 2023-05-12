package com.example.demo.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dao.NewsRepository;
import com.example.demo.dao.Resultatrepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Transactional
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Resultat {	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	private News news;
	@OneToMany(mappedBy = "resultat")
	private Collection<Document> documents;
	
	public void chercherInfo(String titre) {
		News news = new News();
		news.setTitre(titre);
	}
	public Resultat getresultat() {
		return null;}
	

}
