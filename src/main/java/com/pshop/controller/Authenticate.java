package com.pshop.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pshop.products.DAO.Auth;
import com.pshop.products.entity.ShoppingCart;
import com.pshop.products.model.request.AuthRequest;
import com.pshop.products.model.request.ShoppingCartRequest;
import com.pshop.products.model.response.LoginResponse;
import com.pshop.products.model.response.ProductsResponse;
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
	public SaveResponse logintest() {
		SaveResponse response = new SaveResponse();
		response.setStatus("logged in");
		return response;
	}
	
	@PostMapping("/authentication")
	public LoginResponse login(@RequestBody AuthRequest request) {
		LoginResponse response = service.authenticate(request);
		return response;
	}
	
	@GetMapping("/admin")
	public ResponseEntity<SaveResponse> admin() {
		SaveResponse response = new SaveResponse();
		response.setStatus("ADMIN working");
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/user")
	public SaveResponse user() {
		SaveResponse response = new SaveResponse();
		response.setStatus("User working");
		return response;
	}
	
	
//	@GetMapping("/")
//	public SaveResponse authentication() {
//		SaveResponse response = new SaveResponse();
//		response.setStatus("Authenticated");
//		return response;
//	}
	
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
	
	@PostMapping("/admin/updateProducts")
	public SaveResponse update(@RequestBody ProductsResponse request) {
		SaveResponse response = service.updateProduct(request);
		return response;
	}
}
