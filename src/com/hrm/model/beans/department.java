package com.hrm.model.beans;

import java.sql.Date;

public class department {
	private int id;
	private String department_name;
	private String description;
	private Date created_at;
	private int flag;

	public department() {
		// TODO Auto-generated constructor stub
	}

	public department(int id, String department_name, String description, Date created_at, int flag) {
		this.id = id;
		this.department_name = department_name;
		this.description = description;
		this.created_at = created_at;
		this.flag = flag;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

}
