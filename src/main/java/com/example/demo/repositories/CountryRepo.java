package com.example.demo.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Country;

public interface CountryRepo extends JpaRepository<Country, Serializable>{

}
