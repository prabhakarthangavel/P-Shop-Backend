package com.pshop.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pshop.products.entity.ShoppingCart;

@Repository
public interface CartRepo extends CrudRepository<ShoppingCart, String> {
//	@Query(value="select * from shopping_cart where unique_id=?1",nativeQuery=true)
//	ShoppingCart addToCart(ShoppingCart cart);
//	
//	@Modifying(clearAutomatically = true)
//	@Query(value="insert into shopping_cart values(?1,?2,?3)",nativeQuery=true)
//	void createCart(String uniqueId,String product,int quantity);
//	
//	@Modifying(clearAutomatically = true)
//	@Query(value="update shopping_cart set quantity=?1 where unique_id=?2 and product=?3",nativeQuery=true)
//	void addQuantity(int quantity,String uiniqueId,String product);
//
//	@Modifying(clearAutomatically = true)
//	@Query(value="update shopping_cart set quantity=?1 where unique_id=?2 and product=?3",nativeQuery=true)
//	void addProduct(String product, int quantity);
	
	ShoppingCart findByid(String id);
	
//	@Modifying(clearAutomatically = true)
//	@Query(value="update cart_product set quantity = ?1 where cp_fk=(select unique_id from shopping_cart where unique_id=?2)",nativeQuery=true)
//	void addQuantity(int quantity, String id);
}
