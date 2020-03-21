package com.pshop.products.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity(name="shopping_cart")
public class ShoppingCart implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="unique_id")
	private String id;
	
	@OneToMany(targetEntity =CartProduct.class,cascade=CascadeType.ALL)
	@JoinColumn(name ="cp_fk",referencedColumnName="unique_id")
	private List<CartProduct> cartProduct;
	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<CartProduct> getCartProduct() {
		return cartProduct;
	}
	public void setCartProduct(List<CartProduct> cartProduct) {
		this.cartProduct = cartProduct;
	}
	
}
