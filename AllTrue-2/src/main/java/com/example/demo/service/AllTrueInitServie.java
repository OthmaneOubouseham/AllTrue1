package com.example.demo.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Client;
import com.example.demo.entity.Resultat;
import com.example.demo.entity.Role;
import com.example.demo.entity.Utilisateur;

public interface AllTrueInitServie {
	public Utilisateur inscription(String nom, String prenom, String email, String numTel, String sexe, String password, String adresse, Date dateInscription);
	public Role save(Role role);
	public Utilisateur loadUserByUsername(String username);
	public void addRoleToUser(String email, String rolename);
	public void lancerRecher(String titre);
	public Resultat getResultat();
	public List<Resultat> historique();
	public List<Client> getClients();
	public String offStatut(String email);
	public String onStatut(String email);
	public long getCountClient();
	public String uploadImage(MultipartFile file) throws IOException;
	public byte[] downoalImage(String fileName);
	
}
