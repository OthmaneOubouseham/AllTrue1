package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.entity.Client;

@RepositoryRestResource
@CrossOrigin("*")
public interface ClientRepository extends JpaRepository<Client, Long> {

}
