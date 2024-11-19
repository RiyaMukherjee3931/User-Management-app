package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.binding.Userb;
import com.example.demo.entities.City;
import com.example.demo.entities.Country;
import com.example.demo.entities.State;
import com.example.demo.entities.User;

public interface RegService {
	
    public boolean uniqueEmail(String email);
	public Map<Integer,String> getcontlist();
	public Map<Integer,String> getstlist(Integer contId);
	public Map<Integer,String> getCitylist(Integer stateId);
	public boolean registerUser(Userb u);
	
	
	
}
