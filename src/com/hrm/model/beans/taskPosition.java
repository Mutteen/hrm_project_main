package com.hrm.model.beans;

public class taskPosition {
	private int Id;
	private int position_id;
	private int task_id;
	private String taskString;
	private String positionString;
	private int flag;

	public taskPosition() {
		// TODO Auto-generated constructor stub
	}

	public void setId(int id) {
		Id = id;
	}

	public void setPosition_id(int position_id) {
		this.position_id = position_id;
	}

	public void setPositionString(String positionString) {
		this.positionString = positionString;
	}

	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}

	public void setTaskString(String taskString) {
		this.taskString = taskString;
	}

	public int getId() {
		return Id;
	}

	public int getPosition_id() {
		return position_id;
	}

	public String getPositionString() {
		return positionString;
	}

	public int getTask_id() {
		return task_id;
	}

	public String getTaskString() {
		return taskString;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getFlag() {
		return flag;
	}

}
