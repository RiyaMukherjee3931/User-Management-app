package com.example.demo.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.User;

public interface Userepo extends JpaRepository<User,Serializable>{
	
	public User findByUserEmail(String email);

}
