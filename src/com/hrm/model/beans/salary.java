package com.hrm.model.beans;

import java.sql.Date;

public class salary {
	private int id;
	private int value_money;
	private Date time_to_pay;
	private String who_pay;
	private int value_money_reward;
	private int working_day;
	private String description;
	private Date created_at;
	private int flag;
	private int total_salary;
	private String month_to_pay;
	private employee employee;
	private String sEmployee;
	private int employee_id;
	private String moneyString;
	private String rewardString;

	public employee getEmployee() {
		return employee;
	}

	public void setEmployee(employee employee) {
		this.employee = employee;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getValue_money() {
		return value_money;
	}

	public void setValue_money(int value_money) {
		this.value_money = value_money;
	}

	public Date getTime_to_pay() {
		return time_to_pay;
	}

	public void setTime_to_pay(Date time_to_pay) {
		this.time_to_pay = time_to_pay;
	}

	public String getWho_pay() {
		return who_pay;
	}

	public void setWho_pay(String who_pay) {
		this.who_pay = who_pay;
	}

	public int getValue_money_reward() {
		return value_money_reward;
	}

	public void setValue_money_reward(int value_money_reward) {
		this.value_money_reward = value_money_reward;
	}

	public int getWorking_day() {
		return working_day;
	}

	public void setWorking_day(int working_day) {
		this.working_day = working_day;
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

	public int getTotal_salary() {
		return total_salary;
	}

	public void setTotal_salary(int total_salary) {
		this.total_salary = total_salary;
	}

	public String getMonth_to_pay() {
		return month_to_pay;
	}

	public void setMonth_to_pay(String month_to_pay) {
		this.month_to_pay = month_to_pay;
	}

	public void setSEmployee(String sEmployee) {
		this.sEmployee = sEmployee;
	}

	public String getSEmployee() {
		return sEmployee;
	}

		public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public void setMoneyString(String moneyString) {
		this.moneyString = moneyString;
	}

	public String getMoneyString() {
		return moneyString;
	}

	public void setRewardString(String rewardString) {
		this.rewardString = rewardString;
	}

	public String getRewardString() {
		return rewardString;
	}


	public salary() {
		// TODO Auto-generated constructor stub
	}


	public salary(int id, int value_money, Date time_to_pay, String who_pay, int value_money_reward,
			int working_day, String description, Date created_at, int flag) {
		this.id = id;
		this.value_money = value_money;
		this.time_to_pay = time_to_pay;
		this.who_pay = who_pay;
		this.value_money_reward = value_money_reward;
		this.working_day = working_day;
		this.description = description;
		this.created_at = created_at;
		this.flag = flag;
	}

}
