package com.example.demo.vo;

import com.example.demo.entity.Utilisateur;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @AllArgsConstructor @NoArgsConstructor
public class ImageVO {
	public byte[] file;
	public Utilisateur profile;

}
