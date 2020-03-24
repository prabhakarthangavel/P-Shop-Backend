package com.pshop.products.model.request;

import java.util.List;

public class ShoppingCartRequest {
	private String id;
	private String product;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
}
