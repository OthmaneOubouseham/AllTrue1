package com.example.demo.securite;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UtilisateurRepository;
import com.example.demo.entity.Utilisateur;
import com.example.demo.service.AllTrueInitServiceImp;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private AllTrueInitServiceImp service;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Utilisateur u = utilisateurRepository.findUtilisateurByEmail(email);
//		if(u.isStatut()) {
			Utilisateur user = service.loadUserByUsername(email);
			if(user == null) throw new UsernameNotFoundException("invalide user");
			Collection<GrantedAuthority> authorities = new ArrayList<>();
			user.getRoles().forEach(r->{
				authorities.add(new SimpleGrantedAuthority(r.getRole()));
			});
			return new User(user.getEmail(), user.getPassword(), authorities);
//		}
//		return null;

	}

}
