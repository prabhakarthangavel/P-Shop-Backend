package com.pshop.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pshop.products.DAO.Auth;
import com.pshop.products.entity.ShoppingCart;
import com.pshop.products.model.request.AuthRequest;
import com.pshop.products.model.request.RegisterRequest;
import com.pshop.products.model.request.ShoppingCartRequest;
import com.pshop.products.model.response.LoginResponse;
import com.pshop.products.model.response.SaveResponse;
import com.pshop.products.model.response.ShoppingCartResponse;
import com.pshop.products.service.ProductsService;
import com.pshop.repo.CartRepo;

@RestController
@RequestMapping("/auth")
public class Authenticate {
	@Autowired
	private CartRepo repo;
	
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
	
	@PostMapping("/addToCart")
	public ShoppingCartResponse cart(@RequestBody ShoppingCartRequest request) {
		ShoppingCartResponse response = service.addToCart(request);
		return response;
	}
	
	@PostMapping("/removeFromCart")
	public ShoppingCartResponse removecart(@RequestBody ShoppingCartRequest request) {
		ShoppingCartResponse response = service.removeFromCart(request);
		return response;
	}

	@GetMapping("/clearCart")
	public ShoppingCartResponse clearCart(@RequestHeader(value="Authorization") String authorizationHeader) {
		ShoppingCartResponse response = service.clearCart(authorizationHeader);
		return response;
	}
	
	@GetMapping("/getCart")
	public ShoppingCart getCart(@RequestHeader(value="Authorization") String authorizationHeader) {
		return repo.findByid(authorizationHeader);
	}
}
