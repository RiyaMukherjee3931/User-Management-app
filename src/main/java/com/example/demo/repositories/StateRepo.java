package com.example.demo.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.State;

public interface StateRepo extends JpaRepository<State,Serializable>{

	public List<State> findByCountryId(Integer countryId);
}
