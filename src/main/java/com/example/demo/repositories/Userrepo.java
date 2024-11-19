package com.example.demo.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Userentity;

public interface Userrepo extends JpaRepository<Userentity,Serializable>{
	
	public Userentity findByUserEmailandpwd(String email,String user_pwd);

}
