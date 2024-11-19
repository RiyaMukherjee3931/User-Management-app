package com.example.demo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.binding.Userb;
import com.example.demo.entities.City;
import com.example.demo.entities.Country;
import com.example.demo.entities.State;
import com.example.demo.entities.User;
import com.example.demo.repositories.CityRepo;
import com.example.demo.repositories.CountryRepo;
import com.example.demo.repositories.StateRepo;
import com.example.demo.repositories.Userepo;

import Util.Mailutil;

public class ServiceImpl implements RegService{
	
	@Autowired
	private Userepo repos;
	@Autowired
	private CountryRepo crepo;
	@Autowired
	private StateRepo srepo;
	@Autowired
	private CityRepo cityrepo;
	@Autowired
	private Mailutil util;

	@Override
	public boolean uniqueEmail(String email) {
		User user = repos.findByUserEmail(email);
		if(user != null) return false;
		else return true;
	}

	@Override
	public Map<Integer, String> getcontlist() {
		HashMap<Integer,String> hm = new HashMap<>();
		List<Country> contlist = crepo.findAll();
		for(Country cl: contlist) {
			hm.put(cl.getCountryId(),cl.getCountryname());
		}
		return hm;
		
	}

	@Override
	public Map<Integer, String> getstlist(Integer contId) {
		HashMap<Integer,String> hm = new HashMap<>();
		List<State> stlist = srepo.findByCountryId(contId);
		for(State sl: stlist) {
			hm.put(sl.getStateId(),sl.getStatename());
		}
		return hm;
	}

	@Override
	public Map<Integer, String> getCitylist(Integer stateId) {
		HashMap<Integer,String> hm = new HashMap<>();
		List<City> ctlist = cityrepo.findByStateId(stateId);
		for(City ct: ctlist) {
			hm.put(ct.getCityId(),ct.getCityname());
		}
		return hm;
	}

	
    @Override
	public boolean registerUser(Userb u) {
		u.setUser_pwd(generatePwd());
		u.setAcc_status("Locked");
		User entity = new User();
		BeanUtils.copyProperties(u, entity);
		User ue = repos.save(entity);
		if(ue.getUser_id() != null) {
			return sendRegMail(ue);
		}
		return false;
	}

	private String generatePwd() {
		 
		    int leftLimit = 97; // letter 'a'
		    int rightLimit = 122; // letter 'z'
		    int targetStringLength = 10;
		    Random random = new Random();
		    StringBuilder buffer = new StringBuilder(targetStringLength);
		    for (int i = 0; i < targetStringLength; i++) {
		        int randomLimitedInt = leftLimit + (int) 
		          (random.nextFloat() * (rightLimit - leftLimit + 1));
		        buffer.append((char) randomLimitedInt);
		    }
		    String generatedString = buffer.toString();

		    return generatedString;
		}
	
	
	private boolean sendRegMail(User u) {
		boolean emailSent = false;
		
		String subject="User Registration Success";
		String body= readMailBody("emailtemp.txt", u);
	
		
		util.sendEmail(subject, body, u.getEmail());
		return emailSent;
	}
	
	public String readMailBody(String fileName, User user) {
		String mailBody = null;
		StringBuffer buffer = new StringBuffer();
		
		Path path = Paths.get(fileName);
		try (Stream<String> stream = Files.lines(path)){
			stream.forEach(line ->{
				buffer.append(line);
			});
			
			mailBody = mailBody = buffer.toString();
			mailBody = mailBody.replace("{FNAME}", user.getFirstname());
			mailBody = mailBody.replace("{EMAIL}", user.getEmail());
			mailBody = mailBody.replace("{TEMP-PWD}", user.getEmail());
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return mailBody;
	}

}
