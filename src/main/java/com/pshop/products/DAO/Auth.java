package com.pshop.products.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pshop.products.entity.User;

@Repository
public interface Auth extends CrudRepository<User, Integer> {

	@Query(value="select * from user where username = ?1",nativeQuery=true)
	User findUser(String user);
}
