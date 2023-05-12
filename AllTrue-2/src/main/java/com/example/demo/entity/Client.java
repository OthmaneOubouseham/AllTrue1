package com.example.demo.entity;


import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
@PrimaryKeyJoinColumn(name = "id")
public class Client extends Utilisateur {

	private long id;
	
}
