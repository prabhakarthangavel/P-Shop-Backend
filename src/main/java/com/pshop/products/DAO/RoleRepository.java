package com.pshop.products.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pshop.products.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer>{

}