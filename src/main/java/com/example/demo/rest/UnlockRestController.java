package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bindings.UnlockAccrequest;
import com.example.demo.service.UnlockServiceImpl;

@RestController
public class UnlockRestController {

	@Autowired
	private UnlockServiceImpl service;
	
	@PostMapping("/unlock")
	public String unlock(@RequestBody UnlockAccrequest req) {
		return service.unlockaccount(req);
	}
}
