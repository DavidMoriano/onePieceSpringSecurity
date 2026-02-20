package com.adrian.onepiece.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "user_passwd")
	private String userPasswd;

	@Column(name = "user_email")
	private String userEmail;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<RoleEntity> roles;
	
	 public UserEntity() {}

	 public Integer getUserId() {
		 return userId;
	 }

	 public void setUserId(Integer userId) {
		 this.userId = userId;
	 }

	 public String getUserName() {
		 return userName;
	 }

	 public void setUserName(String userName) {
		 this.userName = userName;
	 }

	 public String getUserPasswd() {
		 return userPasswd;
	 }

	 public void setUserPasswd(String userPasswd) {
		 this.userPasswd = userPasswd;
	 }

	 public String getUserEmail() {
		 return userEmail;
	 }

	 public void setUserEmail(String userEmail) {
		 this.userEmail = userEmail;
	 }

	 public Set<RoleEntity> getRoles() {
		 return roles;
	 }

	 public void setRoles(Set<RoleEntity> roles) {
		 this.roles = roles;
	 }
	 
	 

}
