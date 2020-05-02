package com.pshop.products.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.pshop.exception_handling.SqlException;
import com.pshop.products.entity.User;
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
	void saveUser(UserRequest user) throws SqlException;
	LoginResponse authenticate(AuthRequest request);
	ShoppingCartResponse addToCart(ShoppingCartRequest request);
	ShoppingCartResponse removeFromCart(ShoppingCartRequest request);
	ShoppingCartResponse clearCart(String id);
	List<ProductsResponse> pagableProduct(int firstIndex, int lastIndex);
	List<ProductsResponse> searchProduct(String value);
	ProductsResponse getProductByTitle(String product);
	SaveResponse updateProduct(ProductsResponse request);
}
