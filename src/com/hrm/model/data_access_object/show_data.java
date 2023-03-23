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
		String query = "SELECT employee.last_name,employee.middle_name,employee.first_name, employee.dob, employee.email, employee.telephone, employee.address, employee.on_leave, employee.description,\r\n"
				+ "		 department.department_name,\r\n" + "		 `position`.position_name,\r\n"
				+ "		 salary.value_money,\r\n" + "		 (principal.value_money) AS 'Principal'\r\n"
				+ "FROM position_employee `N`\r\n" + "INNER JOIN employee employee ON `N`.employee_id = employee.id\r\n"
				+ "INNER JOIN department department ON department.id = employee.department_id\r\n"
				+ "INNER JOIN `position` `position` ON `position`.id = `N`.position_id\r\n"
				+ "INNER JOIN salary salary ON salary.employee_id  = employee.id\r\n"
				+ "INNER JOIN principal principal ON principal.employee_id = employee.id\r\n"
				+ "WHERE employee.id = 2;";

		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			T item = parseResultSet(rs);
			result.add(item);
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	protected T parseResultSet(ResultSet rs) throws SQLException {
		T item = null;

		if (rs.getMetaData().getTableName(1).equalsIgnoreCase("employee")) {
			employee employee = new employee();
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
//			employee.setStatus(rs.getInt("status"));
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
			principal.setValue_money(rs.getInt("Principal"));
			employee.setPrincipal(principal);
			System.out.println(employee.getPrincipal().getValue_money());

			item = (T) employee;
		}

		return item;
	}

}