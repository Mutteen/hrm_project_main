package com.hrm.model.beans;

public class module_role {
	private int id;
	private int module_id;
	private int role_id;
	private String description;
	private int flag;

	public module_role() {
		// TODO Auto-generated constructor stub
	}

	public module_role(int id, int module_id, int role_id, String description, int flag) {
		super();
		this.id = id;
		this.module_id = module_id;
		this.role_id = role_id;
		this.description = description;
		this.flag = flag;
	}

	public int getid() {
		return id;
	}

	public void setid(int id) {
		this.id = id;
	}

	public int getModule_id() {
		return module_id;
	}

	public void setModule_id(int module_id) {
		this.module_id = module_id;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

}
