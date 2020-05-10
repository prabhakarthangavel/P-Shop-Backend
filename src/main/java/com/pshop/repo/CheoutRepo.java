package com.pshop.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pshop.products.entity.Orders;

@Repository
public interface CheoutRepo extends CrudRepository<Orders, Integer> {
	
}
