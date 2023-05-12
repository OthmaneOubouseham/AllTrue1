package com.example.demo.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Entity  @Data @AllArgsConstructor @NoArgsConstructor @ToString
public class ImageNews {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String type;
	@ManyToOne
	private News news;
	@Lob
	private byte[] imageData;
	public byte[] getImageData() {
		// TODO Auto-generated method stub
		return imageData;
	}
}