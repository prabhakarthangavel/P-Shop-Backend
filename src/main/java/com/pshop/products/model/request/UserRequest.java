package com.pshop.products.model.request;

import java.util.Set;

public class UserRequest {
	private int user_id;
	private String username;
	private String password;
	private Set<RoleRequest> roles;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
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
