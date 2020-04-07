package com.pshop.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pshop.products.entity.AllProducts;

@Repository
public interface ProductsRepo extends PagingAndSortingRepository<AllProducts, Integer> {

	@Query(value = "select * from products where title like ?1%", nativeQuery = true)
	List<AllProducts> findProducts(String str);

	AllProducts findByTitle(String product);

	@Modifying(clearAutomatically = true)
	@Query(value = "update products set title=?2, price =?3, category =?4, image_url =?5, stock =?6 where id=?1", nativeQuery = true)
	int updateProduct(int id, String title, double price, String category, String image_url, int stock);
}
