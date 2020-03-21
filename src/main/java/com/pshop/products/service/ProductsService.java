package com.pshop.products.service;

import java.util.List;

import com.pshop.products.model.request.AuthRequest;
import com.pshop.products.model.request.RegisterRequest;
import com.pshop.products.model.request.ShoppingCartRequest;
import com.pshop.products.model.request.UserRequest;
import com.pshop.products.model.response.LoginResponse;
import com.pshop.products.model.response.ProductsResponse;
import com.pshop.products.model.response.SaveResponse;
import com.pshop.products.model.response.ShoppingCartResponse;

public interface ProductsService {
	SaveResponse save();
	List<ProductsResponse> getProducts(String product);
	void saveUser(UserRequest user);
	LoginResponse authenticate(AuthRequest request);
	ShoppingCartResponse addToCart(ShoppingCartRequest request);
}
