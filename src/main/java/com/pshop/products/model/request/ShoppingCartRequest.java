package com.pshop.products.model.request;

import java.util.List;

public class ShoppingCartRequest {
	private String id;
	private List<CartProductRequest> cartProduct;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<CartProductRequest> getCartProduct() {
		return cartProduct;
	}
	public void setCartProduct(List<CartProductRequest> cartProduct) {
		this.cartProduct = cartProduct;
	}
}
