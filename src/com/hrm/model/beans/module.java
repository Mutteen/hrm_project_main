package com.hrm.model.beans;

public class module {
	private int id;
	private String module_name;
	private String description;
	private int flag;

	public module() {
		// TODO Auto-generated constructor stub
	}

	public module(int id, String module_name, String description, int flag) {
		this.id = id;
		this.module_name = module_name;
		this.description = description;
		this.flag = flag;
	}

	public int getid() {
		return id;
	}

	public void setid(int id) {
		this.id = id;
	}

	public String getModule_name() {
		return module_name;
	}

	public void setModule_name(String module_name) {
		this.module_name = module_name;
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
