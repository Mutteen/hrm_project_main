package com.hrm.model.beans;

import java.sql.Date;

public class task {
	private int id;
	private String title;
	private String description;
	private int assignee;
	private Date deadline;
	private String priority;
	private String status;
	private Date created_at;
	private int quantity_task;
	private int quantity_task_done;
	private String month_by_task;
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getAssignee() {
		return assignee;
	}


	public void setAssignee(int assignee) {
		this.assignee = assignee;
	}


	public Date getDeadline() {
		return deadline;
	}


	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}


	public String getPriority() {
		return priority;
	}


	public void setPriority(String priority) {
		this.priority = priority;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Date getCreated_at() {
		return created_at;
	}


	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}


	public int getQuantity_task() {
		return quantity_task;
	}


	public void setQuantity_task(int quantity_task) {
		this.quantity_task = quantity_task;
	}


	public int getQuantity_task_done() {
		return quantity_task_done;
	}


	public void setQuantity_task_done(int quantity_task_done) {
		this.quantity_task_done = quantity_task_done;
	}


	public String getMonth_by_task() {
		return month_by_task;
	}


	public void setMonth_by_task(String month_by_task) {
		this.month_by_task = month_by_task;
	}


	public task() {
		// TODO Auto-generated constructor stub
	}

}
