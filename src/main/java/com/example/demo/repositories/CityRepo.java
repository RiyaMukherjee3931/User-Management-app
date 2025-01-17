package com.example.demo.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.City;

public interface CityRepo extends JpaRepository<City,Serializable>{

	public List<City> findByStateId(Integer StateId);
}
