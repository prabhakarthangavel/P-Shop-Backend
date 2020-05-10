package com.pshop.products.model.request;

import java.util.List;

public class CheckoutRequest {
	private CheckoutAddress address;
	private List<ChekoutCart> cartList;
	public CheckoutAddress getAddress() {
		return address;
	}
	public void setAddress(CheckoutAddress address) {
		this.address = address;
	}
	public List<ChekoutCart> getCartList() {
		return cartList;
	}
	public void setCartList(List<ChekoutCart> cartList) {
		this.cartList = cartList;
	}
}
