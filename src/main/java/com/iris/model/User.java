package com.iris.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="UsertableEx")
public class User {
	
	@Id
	@NotNull(message="User Id required!")
	private int userId;
	
	@NotEmpty(message="User Name required!")
	@Size(min=4,message="Name must have minm of 4 characters!")
	private String userName;
	
	@NotEmpty
	@Size(min=5,max=12,message="Pswd must between 5-12 charcters")
	@Column(name="UserPass")
	private String password;
	
	@NotEmpty
	private String gender;
	
	@NotEmpty
	private String role;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", gender=" + gender
				+ ", role=" + role + "]";
	}
	
	

}
