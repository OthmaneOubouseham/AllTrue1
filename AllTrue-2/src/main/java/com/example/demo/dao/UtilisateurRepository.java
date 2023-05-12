package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.entity.Utilisateur;

@RepositoryRestResource
@CrossOrigin("*")
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{
	public Utilisateur findUtilisateurByEmail(String email); 
}
