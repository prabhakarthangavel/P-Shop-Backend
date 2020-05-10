package com.pshop.products.model.request;

public class CheckoutAddress {
	private String name;
	private AddressRequest address;
	private long mobile;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public AddressRequest getAddress() {
		return address;
	}
	public void setAddress(AddressRequest address) {
		this.address = address;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
}
