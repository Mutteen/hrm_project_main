package com.hrm.model.beans;

import java.sql.Date;

public class principal {
	private int id;
	private int employee_id;
	private String description;
	private Boolean type;
	private Date date_principal;
	private int value_money;
	private Date created_at;
	private int flag;
	
	public int getId() {
		return id;
	}

<<<<<<< HEAD
	public principal(int principal_id, int employee_id, String description, Boolean type, Date date_principal,
			int value_money, Date created_at, int flag) {
		this.principal_id = principal_id;
		this.employee_id = employee_id;
		this.description = description;
		this.type = type;
		this.date_principal = date_principal;
		this.value_money = value_money;
		this.created_at = created_at;
		this.flag = flag;
	}

	public int getPrincipal_id() {
		return principal_id;
	}

	public void setId(int principal_id) {
		this.principal_id = principal_id;
=======
	public void setId(int id) {
		this.id = id;
>>>>>>> 1383510e8665447a93507fde4809ec9420e517df
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getType() {
		return type;
	}

	public void setType(Boolean type) {
		this.type = type;
	}

	public Date getDate_principal() {
		return date_principal;
	}

	public void setDate_principal(Date date_principal) {
		this.date_principal = date_principal;
	}

	public int getValue_money() {
		return value_money;
	}

	public void setValue_money(int value_money) {
		this.value_money = value_money;
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

	public principal() {
		// TODO Auto-generated constructor stub
	}

	public principal(int id, int employee_id, String description, Boolean type, Date date_principal,
			int value_money, Date created_at, int flag) {
		this.id = id;
		this.employee_id = employee_id;
		this.description = description;
		this.type = type;
		this.date_principal = date_principal;
		this.value_money = value_money;
		this.created_at = created_at;
		this.flag = flag;
	}
}
