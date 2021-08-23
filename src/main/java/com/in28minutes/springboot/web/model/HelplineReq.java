package com.in28minutes.springboot.web.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;


@Entity
public class HelplineReq {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String category; // payroll ,  technical , process
	
	@Size(min=10, message="Enter at least 10 Characters...")
	private String description;
	private String status;
	
	@Size(min=10, message="Enter at least 10 Characters...")
	private String resolution;
	
	
	public HelplineReq(int id, String category,
			@Size(min = 10, message = "Enter at least 10 Characters...") String description, String status,
			@Size(min = 10, message = "Enter at least 10 Characters...") String resolution, Date createddate,
			Date lastupdated, String user, String assignee) {
		super();
		this.id = id;
		this.category = category;
		this.description = description;
		this.status = status;
		this.resolution = resolution;
		this.createddate = createddate;
		this.lastupdated = lastupdated;
		this.user = user;
		this.assignee = assignee;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	private Date createddate;
	private Date lastupdated;
	private String user;
	private String assignee;
	
	
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getResoultion() {
		return resolution;
	}
	public void setResoultion(String resolution) {
		this.resolution = resolution;
	}
	public Date getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}
	public Date getLastupdated() {
		return lastupdated;
	}
	public void setLastupdated(Date lastupdated) {
		this.lastupdated = lastupdated;
	}
	
	@Override
	public String toString() {
		return "HelplineReq [id=" + id + ", category=" + category + ", description=" + description + ", status="
				+ status + ", resolution=" + resolution + ", createddate=" + createddate + ", lastupdated="
				+ lastupdated + ", user=" + user + ", assignee=" + assignee + "]";
	}
	public HelplineReq(int id, String category,
			@Size(min = 10, message = "Enter at least 10 Characters...") String description, String status,
			@Size(min = 10, message = "Enter at least 10 Characters...") String resoultion, Date createddate,
			Date lastupdated, String user) {
		super();
		this.id = id;
		this.category = category;
		this.description = description;
		this.status = status;
		this.resolution = resoultion;
		this.createddate = createddate;
		this.lastupdated = lastupdated;
		this.user = user;
	}
	public HelplineReq() {
		super();
	}
	
	
	
	
}
