package com.pshop.products.model.response;

public class SaveResponse {

	private String status;

	public SaveResponse() {

	}

	public SaveResponse(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
