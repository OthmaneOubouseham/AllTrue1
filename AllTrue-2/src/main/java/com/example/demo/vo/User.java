package com.example.demo.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class User {
	private String nom;
	private String prenom;
	private String email;
	private String numTel;
	private String sexe;
	private String password;
	private String adresse;
	private Date dateInscription;
	private String role;

}
