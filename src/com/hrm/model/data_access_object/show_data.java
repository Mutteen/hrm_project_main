package com.hrm.model.data_access_object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hrm.model.beans.department;
import com.hrm.model.beans.employee;
import com.hrm.model.beans.position;
import com.hrm.model.beans.principal;
import com.hrm.model.beans.salary;

public class show_data<T> {
	private Connection connection;

	public show_data(Connection connection) {
		this.connection = connection;
	}

	public ArrayList<T> getProfile(int idUser) throws SQLException {

		ArrayList<T> result = new ArrayList<>();

		String query = "SELECT employee.id, employee.last_name,employee.middle_name,employee.first_name, employee.dob, employee.email, employee.telephone, employee.address, employee.on_leave, employee.description,employee.`status`, employee.hire_date,\r\n"
				+ "		 department.department_name,\r\n"
				+ "		 all_position_name.position_name,\r\n"
				+ "		 salary.value_money,\r\n"
				+ "		 value_money_principal.value_money_update AS 'principal'\r\n"
				+ "FROM position_employee `N`\r\n"
				+ "INNER JOIN employee  ON `N`.employee_id = employee.id\r\n"
				+ "INNER JOIN department  ON department.id = employee.department_id\r\n"
				+ "INNER JOIN all_position_name ON all_position_name.employee_id = employee.id\r\n"
				+ "INNER JOIN salary  ON salary.employee_id  = employee.id\r\n"
				+ "INNER JOIN value_money_principal ON value_money_principal.employee_id = employee.id\r\n"
				+ "WHERE MONTH(time_to_pay) = MONTH(CURRENT_TIMESTAMP()) AND employee.id = ?\r\n"
				+ "GROUP BY employee.id;";
		
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, idUser);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			T item = parseResultSet(rs);
			result.add(item);
		}		
		return result;
	}
	
	public ArrayList<T> getAllEmployee() throws SQLException{
		ArrayList<T> result = new ArrayList<>();
		String query = "SELECT employee.id ,employee.last_name,employee.middle_name,employee.first_name, employee.dob, employee.email, employee.telephone, employee.address, employee.on_leave, employee.description,employee.`status`, employee.hire_date,\r\n"
				+ "		 department.department_name,\r\n"
				+ "		 all_position_name.position_name,\r\n"
				+ "		 salary.value_money,\r\n"
				+ "		 value_money_principal.value_money_update AS 'principal'\r\n"
				+ "FROM position_employee `N`\r\n"
				+ "INNER JOIN employee  ON `N`.employee_id = employee.id\r\n"
				+ "INNER JOIN department  ON department.id = employee.department_id\r\n"
				+ "INNER JOIN all_position_name ON all_position_name.employee_id = employee.id\r\n"
				+ "INNER JOIN salary  ON salary.employee_id  = employee.id\r\n"
				+ "INNER JOIN value_money_principal ON value_money_principal.employee_id = employee.id\r\n"
				+ "WHERE MONTH(time_to_pay) = MONTH(CURRENT_TIMESTAMP())"
				+ "GROUP BY employee.id;";
		
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()) {
			T item = parseResultSet(rs);
			result.add(item);
		}

		return result;
	}
	
	public ArrayList<T> getAllSalary() throws SQLException{
		ArrayList<T> result = new ArrayList<>();
		String query = "SELECT salary.*,\r\n"
				+ "		 employee.last_name, employee.middle_name, employee.first_name\r\n"
				+ "FROM salary\r\n"
				+ "INNER JOIN employee ON employee.id = salary.employee_id\r\n"
				+ "ORDER BY time_to_pay ASC";
		
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()) {
			T item = parseResultSet(rs);
			result.add(item);
		}

		return result;
		
	}
	
	@SuppressWarnings("unchecked")
	protected T parseResultSet(ResultSet rs) throws SQLException {
		T item = null;
		
		
		if(rs.getMetaData().getTableName(1).equalsIgnoreCase("employee")) {
			employee employee = new employee();
			employee.setId(rs.getInt("id"));
			employee.setOn_leave(rs.getInt("on_leave"));
			employee.setLast_name(rs.getString("last_name"));
			employee.setMiddle_name(rs.getString("middle_name"));
			employee.setFirst_name(rs.getString("first_name"));
			employee.setEmail(rs.getString("email"));
			employee.setAddress(rs.getString("address"));
			employee.setTelephone(rs.getString("telephone"));
//			employee.setAvatar(rs.getString("avatar"));
			employee.setDescription(rs.getString("description"));
			employee.setDob(rs.getDate("dob"));
			employee.setHire_date(rs.getDate("hire_date"));
			employee.setStatus(rs.getInt("status"));
//			employee.setFlag(rs.getInt("flag"));
	        
	        department department = new department();
	        department.setDepartment_name(rs.getString("department_name"));
	        employee.setDepartment(department);
	        
	        position position = new position();
	        position.setPosition_name(rs.getString("position_name"));
	        employee.setPosition(position);
	        
	        salary salary = new salary();
	        salary.setValue_money(rs.getInt("value_money"));
	        employee.setSalary(salary);
	        
	        principal principal = new principal();
	        principal.setValue_money(rs.getInt("principal"));
	        employee.setPrincipal(principal);
	        
	        item = (T) employee;	        
		}else if(rs.getMetaData().getTableName(1).equalsIgnoreCase("salary")) {
			salary salary = new salary();
			salary.setId(rs.getInt("id"));
			salary.setValue_money(rs.getInt("value_money"));
			salary.setTime_to_pay(rs.getDate("time_to_pay"));
			salary.setWho_pay(rs.getString("who_pay"));
			salary.setValue_money_reward(rs.getInt("value_money_reward"));
			salary.setWorking_day(rs.getInt("working_day"));
			salary.setCreated_at(rs.getDate("created_at"));
//			salary.setFlag(rs.getInt("flag"));
			
			employee employee = new employee();
			employee.setLast_name(rs.getString("last_name"));
			employee.setMiddle_name(rs.getString("middle_name"));
			employee.setFirst_name(rs.getString("first_name"));
			salary.setEmployee(employee);
			
			item = (T) salary;
		}

		return item;
	}

}