package com.pshop.products.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name="checkout_details")
public class Orders implements Serializable {

	private static final long serialVersionUID = -6128380554249374627L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="unique_id")
	private int id;
	
	@Column
	private String name;
	
	@Column
	private long mobile;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;
	
	@OneToMany(targetEntity =CheckoutProduct.class,cascade=CascadeType.ALL)
	@JoinColumn(name ="cart_id",referencedColumnName="unique_id")
	private List<CheckoutProduct> cartList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<CheckoutProduct> getCartList() {
		return cartList;
	}

	public void setCartList(List<CheckoutProduct> cartList) {
		this.cartList = cartList;
	}
}
