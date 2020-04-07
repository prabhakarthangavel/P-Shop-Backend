package com.pshop.products.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

@Entity(name="products")
public class AllProducts implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2087738713231100878L;
	
	public AllProducts() {
	}

	@Id
	@GeneratedValue
	private int id;
	
	@Column
	@NotNull
	private String category;
	
	@Column
	private String image_url;
	
	@Column
	private double price;
	
	@Column
	private String title;
	
	@Column
	private int stock;
	
	
	
	public AllProducts(String category,String image_url, double price, String title, int stock){
//		this.id = id;
		this.image_url = image_url;
		this.category = category;
		this.price = price;
		this.title = title;
		this.stock = stock;
	}
	

	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
	}



	public String getimage_url() {
		return image_url;
	}



	public void setimage_url(String image_url) {
		this.image_url = image_url;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}


	@Override
	public String toString() {
		return "AllProducts [id=" + id + ", category=" + category + ", =" + image_url + ", price=" + price
				+ ", title=" + title + "]";
	}

}
