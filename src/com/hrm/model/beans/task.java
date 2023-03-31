package com.hrm.model.beans;

import java.sql.Date;

import com.mysql.cj.protocol.a.NativeConstants.StringLengthDataType;

public class task {
	private int Id;
	private String title;
	private String description;
	private int assignee;
	private Date deadline;
	private String priority;
	private String status;
	private Date create_at;
	private int flag;
	private String nameString;

	public task() {
		// TODO Auto-generated constructor stub
	}

	public void setAssignee(int assignee) {
		this.assignee = assignee;
	}

	public void setCreate_at(Date create_at) {
		this.create_at = create_at;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public void setId(int id) {
		Id = id;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getAssignee() {
		return assignee;
	}

	public Date getCreate_at() {
		return create_at;
	}

	public Date getDeadline() {
		return deadline;
	}

	public String getDescription() {
		return description;
	}

	public int getFlag() {
		return flag;
	}

	public int getId() {
		return Id;
	}

	public String getPriority() {
		return priority;
	}

	public String getStatus() {
		return status;
	}

	public String getTitle() {
		return title;
	}

	public void setNameString(String nameString) {
		this.nameString = nameString;
	}

	public String getNameString() {
		return nameString;
	}

}
