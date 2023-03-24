package com.hrm.model.data_access_object;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hrm.model.beans.department;
import com.hrm.model.beans.employee;
import com.hrm.model.beans.position;
import com.hrm.model.beans.principal;
import com.hrm.model.beans.principal.Type;
import com.hrm.model.beans.salary;

public abstract class crud <T> {
	private Connection connection;
	
	public crud(Connection connection) {
		this.connection = connection;
	}
	
	public ArrayList<T> getAll(String table) throws SQLException {
		ArrayList<T> result = new ArrayList<>();
		String query = "SELECT * FROM " + table;
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()) {
			T item = parseResultSet(rs);
			result.add(item);
		}
		return result;
	}
	
	public T getById(String table, int id) throws SQLException {
		T item;
		String query = "SELECT * FROM " + table + " WHERE id = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet rs = statement.executeQuery();
		statement.setInt(1, id);
		while(rs.next()) {
			item = parseResultSet(rs);
			return item; 
		}
		return null;
	}
	
	public void add(String table, T item) throws IllegalArgumentException, SQLException {
		String query = "INSERT INTO " + table + " VALUES(";
					    Field[] fields = item.getClass().getDeclaredFields();
					    for(int i=0; i< fields.length; i++) {
					    	query += "?";
					    	if(i < fields.length - 1) {
					    		query += ",";
					    	}
					    }
					    query += ")";
		PreparedStatement statement = connection.prepareStatement(query);
		int index = 1;
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				statement.setObject(index, field.get(item));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			index ++;
		}
		statement.executeUpdate();
	}
	
	public void update(String table, T item, int id) throws IllegalArgumentException, SQLException {
		String query = "UPDATE " + table + " SET ";
					    Field[] fields = item.getClass().getDeclaredFields();
					    for(int i=0; i< fields.length; i++) {
					    	query += fields[i].getName() + "= ?";
					    	if(i < fields.length - 1) {
					    		query += ",";
					    	}
					    }
					    query += "WHERE id = ?)";
		PreparedStatement statement = connection.prepareStatement(query);
		int index = 1;
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				statement.setObject(index, field.get(item));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			index ++;
		}
		statement.setInt(index, id);
		statement.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	protected T parseResultSet (ResultSet rs) throws SQLException {
		T item = null;
		
		if(rs.getMetaData().getTableName(1).equalsIgnoreCase("employee")) {
			item = (T) new employee();
			((employee)item).setId(rs.getInt("id"));
			((employee)item).setRole_id(rs.getInt("role_id"));
			((employee)item).setUsername(rs.getString("username"));
			((employee)item).setPassword(rs.getString("password"));
//			System.out.println(rs.getString("username"));
//			System.out.println(rs.getString("password"));
			((employee)item).setOn_leave(rs.getInt("on_leave"));
			((employee)item).setLast_name(rs.getString("last_name"));
			((employee)item).setMiddle_name(rs.getString("middle_name"));
			((employee)item).setFirst_name(rs.getString("first_name"));
			((employee)item).setEmail(rs.getString("email"));
			((employee)item).setAddress(rs.getString("address"));
			((employee)item).setTelephone(rs.getString("telephone"));
			((employee)item).setAvatar(rs.getString("avatar"));
			((employee)item).setDescription(rs.getString("description"));
			((employee)item).setDob(rs.getDate("dob"));
			((employee)item).setStatus(rs.getInt("status"));
			((employee)item).setFlag(rs.getInt("flag"));
		}else if(rs.getMetaData().getTableName(1).equalsIgnoreCase("department")) {
			item = (T)new department();
			((department)item).setId(rs.getInt("id"));
			((department)item).setDepartment_name(rs.getString("department_name"));
			((department)item).setDescription(rs.getString("description"));
			((department)item).setCreated_at(rs.getDate("created_at"));
			((department)item).setFlag(rs.getInt("flag"));
		}else if(rs.getMetaData().getTableName(1).equalsIgnoreCase("position")) {
			item = (T) new position();
			((position)item).setId(rs.getInt("id"));
			((position)item).setPosition_name(rs.getString("position_name"));
			((position)item).setDescription(rs.getString("description"));
			((position)item).setCreated_at(rs.getDate("created_at"));
			((position)item).setFlag(rs.getInt("flag"));
		}else if(rs.getMetaData().getTableName(1).equalsIgnoreCase("principal")) {
			item = (T) new principal();
			((principal)item).setId(rs.getInt("id"));
			((principal)item).setEmployee_id(rs.getInt("employee_id"));
			((principal)item).setDescription(rs.getString("description"));
			((principal)item).setType(Type.valueOf(rs.getString("priority")));
			((principal)item).setDate_principal(rs.getDate("date_principal"));
			((principal)item).setValue_money(rs.getInt("value_money"));
			((principal)item).setCreated_at(rs.getDate("created_at"));
			((principal)item).setFlag(rs.getInt("flag"));
		}else if(rs.getMetaData().getTableName(1).equalsIgnoreCase("salary")) {
			item = (T) new salary();
			((salary)item).setId(rs.getInt("id"));
			((salary)item).setEmployee_id(rs.getInt("employee_id"));
			((salary)item).setValue_money(rs.getInt("value_money"));
			((salary)item).setTime_to_pay(rs.getDate("time_to_pay"));
			((salary)item).setWho_pay(rs.getString("who_pay"));
			((salary)item).setValue_money_reward(rs.getInt("value_money_reward"));
			((salary)item).setWorking_day(rs.getInt("working_day"));
			((salary)item).setDescription(rs.getString("description"));
			((salary)item).setCreated_at(rs.getDate("created_at"));
			((salary)item).setFlag(rs.getInt("flag"));
		}
		
		return item;
	}
}


