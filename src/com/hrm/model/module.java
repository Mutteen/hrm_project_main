package com.hrm.model;

public class module {
	private int module_id;
	private String module_name;
	private String description;
	private int flag;
	
	public module() {
		// TODO Auto-generated constructor stub
	}
	
	public module(int module_id, String module_name, String description, int flag) {
		this.module_id = module_id;
		this.module_name = module_name;
		this.description = description;
		this.flag = flag;
	}

	public int getModule_id() {
		return module_id;
	}

	public void setModule_id(int module_id) {
		this.module_id = module_id;
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
