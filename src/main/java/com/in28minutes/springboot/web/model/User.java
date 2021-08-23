package com.in28minutes.springboot.web.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User {

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public String getRoles() {
		return roles;
	}


	public void setRoles(String roles) {
		this.roles = roles;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String username;
	
	private String password;
	
	
	private boolean active;
	
	
	private String roles;
	private String team;
	public String getTeam() {
		return team;
	}


	public void setTeam(String team) {
		this.team = team;
	}
	
	
	
	
}
