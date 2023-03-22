package com.hrm.model.beans;

import java.sql.Date;

public class position {
	private int position_id;
	private String position_name;
	private String description;
	private Date created_at;
	private int flag;

	public position() {
		// TODO Auto-generated constructor stub
	}

	public position(int position_id, String position_name, String description, Date created_at, int flag) {
		this.position_id = position_id;
		this.position_name = position_name;
		this.description = description;
		this.created_at = created_at;
		this.flag = flag;
	}

	public int getPosition_id() {
		return position_id;
	}

	public void setId(int position_id) {
		this.position_id = position_id;
	}

	public String getPosition_name() {
		return position_name;
	}

	public void setPosition_name(String position_name) {
		this.position_name = position_name;
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
