package com.pshop.products.model.response;

import java.util.List;

public class ShoppingCartResponse {
	private String id;
	private List<CartProductResponse> cartProduct;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<CartProductResponse> getCartProduct() {
		return cartProduct;
	}
	public void setCartProduct(List<CartProductResponse> cartProduct) {
		this.cartProduct = cartProduct;
	}
}
