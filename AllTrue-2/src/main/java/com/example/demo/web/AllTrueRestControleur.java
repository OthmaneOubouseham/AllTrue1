package com.example.demo.web;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.example.demo.dao.ImageNewsRepository;
import com.example.demo.entity.Client;
import com.example.demo.entity.ImageNews;
import com.example.demo.entity.News;
import com.example.demo.entity.Utilisateur;
import com.example.demo.service.AllTrueInitServiceImp;
import com.example.demo.vo.ImageVO;
import com.example.demo.vo.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin("*")
@RestController


public class AllTrueRestControleur {
	
	@Autowired
	public AllTrueInitServiceImp service;
	@Autowired 
	public ImageNewsRepository imageNewsRepository;
	
	@PostMapping("/inscription")
	public Utilisateur inscrire(@RequestBody User user) {
		System.out.println(1);
		String nom = user.getNom();
		String prenom = user.getPrenom();
		String email = user.getEmail();
		String numTel = user.getNumTel();
		String sexe = user.getSexe();
		String adresse = user.getAdresse();
		Date dateInscription = user.getDateInscription();
		String password = user.getPassword();
		
		return service.inscription(nom, prenom, email, numTel, sexe, password, adresse, dateInscription);
		
	}
	@PostMapping("/cherche")
	public void Chercher(@RequestBody String titre, @RequestParam String email) {
		this.service.lancerRecher(titre, email);
	}
	@GetMapping("/historiques")
	public List<News> getHistoriques(@RequestParam String email){
		return this.service.historique(email);
	}
	@GetMapping("/clients")
	public List<Client> getClients(){
		return this.service.getClients();
	}
	@PostMapping("/offStatut")
	public String offStatut(@RequestBody String email) {
		return this.service.offStatut(email);
	}
	@PostMapping("/onStatut")
	public String onStatut(@RequestBody String email) {
		return this.service.onStatut(email);
	}
	@GetMapping("/countClients")
	public long CountClients() {
		return this.service.getCountClient();
		
	}
	@PostMapping("/uploadImage")
	public String uploadImage(@RequestPart("image") MultipartFile image, @RequestParam("email") String email) throws IOException{
//		MultipartFile file = image.getFile();
//		String email = image.getEmail();
		return service.uploadImage(image, email);
	}
	@GetMapping("/downloadImage/{ImageName}")
	public ResponseEntity<?> downloadImage(@PathVariable String ImageName){
		byte[] imageData = service.downoalImage(ImageName);
		ImageNews dbImage = imageNewsRepository.findByName(ImageName);

		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf(dbImage.getType()))
				.body(imageData);
	}
	@GetMapping("/getProfile")
	@ResponseBody
	public ImageVO getProfile(@RequestParam("email") String email) {
		
		return this.service.getProfil(email);
		
	}
	@GetMapping("/googleSearch")
    public String performSearch(@RequestParam String query) {
        try {
        	String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8.toString());
            // Effectuer une requête vers Google
            Document doc = Jsoup.connect("https://www.google.com/search?q=" + encodedQuery).get();
            // Extraire les résultats de recherche
            Elements results = doc.select("div.g");
            StringBuilder response = new StringBuilder();
            // Parcourir les résultats et les ajouter à la réponse
            for (Element result : results) {
                String title = result.select("h3").text();
                String url = result.select("a[href]").attr("href");
                response.append(title).append(" - ").append(url).append("\n");
            }
            String text = response.toString();
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(text);
            
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la recherche.";
        }
    }
	
	

}
