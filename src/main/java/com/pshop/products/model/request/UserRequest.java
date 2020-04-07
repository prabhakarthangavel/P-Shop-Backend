package com.pshop.products.model.request;

import java.util.Set;

public class UserRequest {
	private String username;
	private String password;
	private Set<RoleRequest> roles;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<RoleRequest> getRoles() {
		return roles;
	}
	public void setRoles(Set<RoleRequest> roles) {
		this.roles = roles;
	}
}
