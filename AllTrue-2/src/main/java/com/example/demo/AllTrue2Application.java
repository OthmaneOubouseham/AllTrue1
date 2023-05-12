package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.entity.Role;
import com.example.demo.service.AllTrueInitServiceImp;
@CrossOrigin("*")
@SpringBootApplication
public class AllTrue2Application {

	public static void main(String[] args) {
		SpringApplication.run(AllTrue2Application.class, args);
	}
	@Bean
	BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Bean
	CommandLineRunner start( AllTrueInitServiceImp service) {
		return args->{
			service.save(new Role(1, "Client") );
			service.save(new Role(2, "Administrateur") );
			

//			Stream.of("user4", "user5", "user6", "medecin2").forEach(un->{
//				service.saveUser(un, "1234", "1234", "mmmm", "sdgsg", "5dsfsd");
//			});
		};
		
	}
}
