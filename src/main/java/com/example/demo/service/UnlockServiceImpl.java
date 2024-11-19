package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bindings.UnlockAccrequest;
import com.example.demo.entities.Userentity;
import com.example.demo.repositories.Userrepo;

@Service
public class UnlockServiceImpl implements UnlockService{
	
	@Autowired
	private Userrepo repos;

	@Override
	public String unlockaccount(UnlockAccrequest Unlockaccreq) {
		
		Userentity en = repos.findByUserEmailandpwd(Unlockaccreq.getEmail(),Unlockaccreq.getTempPwd());
		if(en != null) {
			en.setUser_pwd(Unlockaccreq.getNewPwd());
			en.setAcc_status("Unlocked");
			repos.save(en);
			return "User account unlocked successfully";
		}
		return "Invalid Temp Password";
	}

	
	
}
