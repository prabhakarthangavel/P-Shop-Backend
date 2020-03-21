package com.pshop.products.DAO;

import java.util.List;

import com.pshop.products.entity.AllProducts;
import com.pshop.products.entity.ShoppingCart;
import com.pshop.products.entity.User;

public interface ProductsDAO {
	void save(AllProducts product);
	List<AllProducts> getProducts(String product);
	void saveuser(User user);
//	ShoppingCart addToCart(ShoppingCart cart);
//	int createCart(ShoppingCart cart);
//	int addQuantity(ShoppingCart cart);
}
