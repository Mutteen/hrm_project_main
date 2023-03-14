package com.hrm.model;

import java.sql.Date;

public class position_employee {
	private int position_employee_id;
	private int employee_id;
	private int position_id;
	private String description;
	private Date created_at;
	private int flag;

	public position_employee() {
		// TODO Auto-generated constructor stub
	}

	public position_employee(int position_employee_id, int employee_id, int position_id, String description,
			Date created_at, int flag) {
		this.position_employee_id = position_employee_id;
		this.employee_id = employee_id;
		this.position_id = position_id;
		this.description = description;
		this.created_at = created_at;
		this.flag = flag;
	}

	public int getPosition_employee_id() {
		return position_employee_id;
	}

	public void setPosition_employee_id(int position_employee_id) {
		this.position_employee_id = position_employee_id;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public int getPosition_id() {
		return position_id;
	}

	public void setPosition_id(int position_id) {
		this.position_id = position_id;
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
