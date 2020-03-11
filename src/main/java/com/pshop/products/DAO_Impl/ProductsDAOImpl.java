package com.pshop.products.DAO_Impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pshop.products.DAO.ProductsDAO;
import com.pshop.products.entity.AllProducts;
import com.pshop.products.entity.Role;
import com.pshop.products.entity.User;

@Repository
public class ProductsDAOImpl implements ProductsDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public void save(AllProducts product) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.save(product);
	}

	@Override
	public List<AllProducts> getProducts(String product) {
		List<AllProducts> list = null;
		if(product.equals("null")) {
			Session currentSession = entityManager.unwrap(Session.class);
			Query<AllProducts> query = currentSession.createNativeQuery("select * from products", AllProducts.class);
			list = query.getResultList();
		}else {
			Session currentSession = entityManager.unwrap(Session.class);
			Query<AllProducts> query = currentSession.createNativeQuery("select * from products where category = ?1", AllProducts.class);
			query.setParameter(1, product);
			list = query.getResultList();
			System.out.println("inside where caluse "+product.length());
		}
		return list;
	}
	
	

	@Override
	public User findByUsername(String username) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<User> query = currentSession.createNativeQuery("select * from User where username=?1",User.class);
		User user = query.uniqueResult();
		return user;
	}

	@Override
	public void saveuser(User user) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.save(user);
	}
}
