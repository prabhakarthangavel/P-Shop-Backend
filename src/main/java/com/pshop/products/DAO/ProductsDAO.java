package com.pshop.products.DAO;

import java.util.List;

import com.pshop.products.entity.AllProducts;
import com.pshop.products.entity.User;

public interface ProductsDAO {
	void save(AllProducts product);
	List<AllProducts> getProducts(String product);
	User findByUsername(String username);
	void saveuser(User user);
}
