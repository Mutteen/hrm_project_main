package com.hrm.model.business_objects;

import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import com.hrm.model.usersession;
import com.hrm.model.beans.employee;
import com.hrm.model.data_access_object.connection_db;
import com.hrm.model.data_access_object.crud;
import com.hrm.model.data_access_object.show_data;

import javafx.collections.ObservableList;

public class bo_employee {
	private static Connection conn = connection_db.getConnection();

	public bo_employee() {
		// TODO Auto-generated constructor stub
	}

	public static boolean login(String username, String password) throws SQLException {

		crud<employee> crudEmployee = new crud<employee>(conn) {
		};

		ArrayList<employee> listEmployee = crudEmployee.getLogin("employee");

		boolean check = false;
		for (employee employee : listEmployee) {
			if (username.equals(employee.getUsername()) && password.equals(employee.getPassword())) {
				usersession.getInstace(employee.getId(), employee.getFirst_name());
				check = true;
				break;
			} else {
				check = false;
			}
			System.out.println(employee.getUsername() + " " + employee.getPassword());
		}
		System.out.print(check);
		return check;

	}

	public static ArrayList<employee> getProfile(int idUser) throws SQLException {

		show_data<employee> showProfile = new show_data<>(conn);

		ArrayList<employee> listData = showProfile.getProfile(idUser);

		for (employee employee : listData) {
			System.out.println(employee.getPrincipal().getValue_money());
			System.out.println(employee.getSalary().getValue_money());
		}

		return listData;

	}

}