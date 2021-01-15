package com.qimh.springbootredis.domain;

import java.io.Serializable;

public class Emp implements Serializable{
	
	private Integer id;
	private String name;
	private String gender;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "Emp [id=" + id + ", name=" + name + ", gender=" + gender + "]";
	}
	
	
	

}
