package com.pshop.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pshop.products.model.request.AuthRequest;
import com.pshop.products.model.request.UserRequest;
import com.pshop.products.model.response.LoginResponse;
import com.pshop.products.model.response.ProductsResponse;
import com.pshop.products.model.response.SaveResponse;
import com.pshop.products.service.ProductsService;

@RestController
public class ProductController {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private ProductsService service;

	@GetMapping("/save")
	public SaveResponse save() {
		SaveResponse response = service.save();
		return response;
	}
	
	@GetMapping(value = { "/getAllProducts", "/getAllProducts/{product}" })
	public List<ProductsResponse> getProducts(@PathVariable(required = false) String product) {
		System.out.println("product" + product);
		List<ProductsResponse> response = service.getProducts(product);
		return response;
	}
	
	@GetMapping(value = { "/getProduct/{product}" })
	public ProductsResponse getProductByTitle(@PathVariable(required = false) String product) {
		System.out.println("product" + product);
		ProductsResponse response = service.getProductByTitle(product);
		return response;
	}

	@PostMapping(value = { "/pagableProducts" })
	public List<ProductsResponse> pagableProducts(@RequestBody(required = false) HashMap<String, Integer> request) {
		int pageNumber = request.get("page");
		int count = request.get("count");
		List<ProductsResponse> response = service.pagableProduct(pageNumber, count);
		return response;
	}
	
	@GetMapping("/search/{value}")
	public List<ProductsResponse> searchRequest(@PathVariable(name="value") String value){
		List<ProductsResponse> response = service.searchProduct(value);
		return response;
	}
	
	@PostMapping("/register")
	public String addUserByAdmin(@RequestBody UserRequest user) {
		String pwd = user.getPassword();
		String encryptPwd = passwordEncoder.encode(pwd);
		user.setPassword(encryptPwd);
		service.saveUser(user);
		return "user added successfully...";
	}

	@GetMapping("/admin")
	public String securedHello() {
		return "Secured Hello";
	}
}
