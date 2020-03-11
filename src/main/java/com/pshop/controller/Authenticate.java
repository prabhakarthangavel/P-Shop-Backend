package com.pshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pshop.products.DAO.Auth;
import com.pshop.products.entity.User;
import com.pshop.products.model.request.AuthRequest;
import com.pshop.products.model.response.LoginResponse;
import com.pshop.products.model.response.SaveResponse;
import com.pshop.products.service.ProductsService;

@RestController
@RequestMapping("/auth")
public class Authenticate {
	
	@Autowired
	private ProductsService service;
	
	@Autowired
	Auth auth;
	
	@GetMapping("/logintest")
	public String logintest() {
		return "logged in";
	}
	
	@PostMapping("/login")
	public LoginResponse login(@RequestBody AuthRequest request) {
		LoginResponse response = service.authenticate(request);
		return response;
	}
	
	@GetMapping("/admin")
	public SaveResponse admin() {
		SaveResponse response = new SaveResponse();
		response.setStatus("ADMIN working");
		return response;
	}
	
	@GetMapping("/user")
	public String user() {
		return "USER working";
	}
	
	
	@GetMapping("/authentication")
	public SaveResponse authentication() {
		SaveResponse response = new SaveResponse();
		response.setStatus("Authenticated");
		return response;
	}
	
	@GetMapping("/allow/")
	public String allow() {
		User user= auth.findUser("prabhakar");
		System.out.println("inside allow"+user.getUsername()+user.getPassword());
		return user.getUsername();
	}

}
