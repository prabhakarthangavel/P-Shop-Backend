package com.pshop.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pshop.products.entity.Address;
import com.sun.xml.bind.v2.model.core.ID;

@Repository
public interface AddressRepo extends CrudRepository<Address, Integer> {

}
