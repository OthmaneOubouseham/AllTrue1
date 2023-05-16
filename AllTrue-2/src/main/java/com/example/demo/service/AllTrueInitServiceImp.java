package com.example.demo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.ClientRepository;
import com.example.demo.dao.ImageNewsRepository;
import com.example.demo.dao.NewsRepository;
import com.example.demo.dao.Resultatrepository;
import com.example.demo.dao.RoleRepository;
import com.example.demo.dao.UtilisateurRepository;
import com.example.demo.entity.Client;
import com.example.demo.entity.ImageNews;
import com.example.demo.entity.News;
import com.example.demo.entity.Resultat;
import com.example.demo.entity.Role;
import com.example.demo.entity.Utilisateur;
import com.example.demo.util.ImageUtils;
import com.example.demo.vo.ImageVO;

@CrossOrigin("*")
@Service
@Transactional
public class AllTrueInitServiceImp implements AllTrueInitServie{
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	public BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	public RoleRepository roleRepository;
	@Autowired
	public Resultatrepository resultatrepository;
	@Autowired
	public NewsRepository newsRepository;
	@Autowired
	public ImageNewsRepository imageNews;


	@Override
	public Utilisateur inscription(String nom, String prenom, String email, String numTel, String sexe, String password,
			String adresse, Date dateInscription) {
		Utilisateur user = utilisateurRepository.findUtilisateurByEmail(email);
		if(user!= null) throw new RuntimeException("user existe déja!") ;
		
		String password1 = bCryptPasswordEncoder.encode(password);
		Client client = new Client();
		client.setNom(nom);
		client.setPrenom(prenom);
		client.setAdresse(adresse);
		client.setNumTel(numTel);
		client.setEmail(email);
		client.setSexe(sexe);
		client.setDateInscription(dateInscription);
		client.setPassword(password1);
		clientRepository.save(client);
		addRoleToUser(email, "Client");
		
		
		return client;
	}
	@Override
	public Role save(Role role) {
		return roleRepository.save(role);
//		return null;
		
	}
	@Override
	public Utilisateur loadUserByUsername(String email) {
		
		return utilisateurRepository.findUtilisateurByEmail(email);
//		return null;
	}
	@Override
	public void addRoleToUser(String email, String rolename) {
		Utilisateur user = utilisateurRepository.findUtilisateurByEmail(email);
		Role role = roleRepository.findByRole(rolename);
		user.getRoles().add(role);
	}
	@Override
	public void lancerRecher(String titre) {
		News news = new News();
		news.setTitre(titre);
		Resultat resultat = new Resultat();
		resultat.setNews(news);
		newsRepository.save(news);
		resultatrepository.save(resultat);
		
	}
	@Override
	public Resultat getResultat() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<News> historique(String email) {
		List<News> historiques = new ArrayList<>();
		Utilisateur user = utilisateurRepository.findUtilisateurByEmail(email);
		long id = user.getId();
		newsRepository.findAll().forEach(n->{
			if(n.getUtilisateur().getId() == id) {
				historiques.add(n);
			}
		});
		return historiques;

		}
	
	@Override
	public List<Client> getClients() {
		List<Client> clients = new ArrayList<>();
		clientRepository.findAll().forEach(c->{
			clients.add(c);
		});
		return clients;
	}
	@Override
	public String offStatut(String email) {
		Utilisateur user = utilisateurRepository.findUtilisateurByEmail(email);
		user.setStatut(false);
		return "Statut de l'utilisateur "+ email+" est modifier !";
	}
	@Override
	public long getCountClient() {
		List<Client> clients = new ArrayList<>();
		long count = 0;
		clientRepository.findAll().forEach(c->{
			clients.add(c);
		});
		return clients.size();
	}
	@Override
	public String onStatut(String email) {
		Utilisateur user = utilisateurRepository.findUtilisateurByEmail(email);
		user.setStatut(true);
		return "Statut de l'utilisateur "+ email+" est modifier !";
	}
	@Override
	public String uploadImage(MultipartFile file, String email) throws IOException {
		Utilisateur user = utilisateurRepository.findUtilisateurByEmail(email);		
		ImageNews image =  imageNews.save(ImageNews.builder()
				.name(file.getOriginalFilename())
				.type(file.getContentType())
				.utilisateur(user)
				.imageData(ImageUtils.compressImage(file.getBytes())).build());
		if(image !=null) {
			return "l'image à bien etais télécharger: "+file.getOriginalFilename();
		}
		return null;
	}
	@Override
	public byte[] downoalImage(String fileName) {
		ImageNews dbImage = imageNews.findByName(fileName);
		byte[] imageFinal = ImageUtils.decompressImage(dbImage.getImageData());
		return imageFinal;
	}
	@Override
	public ImageVO getProfil(String email) {
		Utilisateur user = utilisateurRepository.findUtilisateurByEmail(email);
		String imageName = user.getImageName();
		ImageVO userVO = new ImageVO();
		imageNews.findAll().forEach(i->{
			long idUser = user.getId();
			if(i.getUtilisateur() == null) {userVO.setFile(null);}
			if(i.getUtilisateur().getId() == idUser) {
				userVO.setFile(i.getImageData());
			}
			else {
				userVO.setFile(null);
			}
		});
		userVO.setProfile(user);
		
		
		return userVO;
	}
	
}
