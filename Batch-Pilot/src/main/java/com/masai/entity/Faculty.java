package com.masai.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
@Entity
public class Faculty {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int faculty_id;
	private String first_name;
	private String last_name;
	private String username;
	private String pass;
	private String mobile;

	private String address;
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name = "BatchFaculty",joinColumns = {
			@JoinColumn(name = "F_id")
	},inverseJoinColumns = {@JoinColumn(name = "B_id")})
	private List<Batch> batchList;
	public Faculty(String first_name, String last_name, String username, String pass, String mobile, String address,
			List<Batch> batchList) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.username = username;
		this.pass = pass;
		this.mobile = mobile;
		this.address = address;
		this.batchList = batchList;
	}
	public Faculty() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getFaculty_id() {
		return faculty_id;
	}
	public void setFaculty_id(int faculty_id) {
		this.faculty_id = faculty_id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<Batch> getBatchList() {
		return batchList;
	}
	public void setBatchList(List<Batch> batchList) {
		this.batchList = batchList;
	}
	@Override
	public String toString() {
		return "Faculty [faculty_id=" + faculty_id + ", first_name=" + first_name + ", last_name=" + last_name
				 + "]";
	}
	
}
