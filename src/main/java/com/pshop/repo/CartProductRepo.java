package com.pshop.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pshop.products.entity.CartProduct;

@Repository
public interface CartProductRepo extends CrudRepository<CartProduct, String> {

	@Modifying(clearAutomatically = true)
	@Query(value="update cart_product set quantity = quantity-1 , total_price = ?3*quantity where cp_fk=?1 and title = ?2"
	,nativeQuery=true)
	void removeItem(String id, String product, float price);

	@Modifying(clearAutomatically = true)
	@Query(value="delete from cart_product where title = ?1 and cp_fk = ?2"
	,nativeQuery=true)
	void deleteProduct(String product,String id);

}
