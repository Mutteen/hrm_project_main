package com.hrm.model.business_objects;


import java.sql.Array;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import com.hrm.model.usersession;
import com.hrm.model.beans.employee;
import com.hrm.model.data_access_object.DAO;
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

		return listData;

	}


	public static ArrayList<employee> getListEmployee() throws SQLException{
		show_data<employee> showList = new show_data<>(conn);
		ArrayList<employee> listData = showList.getAllEmployee();

		return listData;
	}
	

	@Override
	public boolean save(employee t) {
		
		return false;
	}

	@Override
	public boolean update(employee t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(employee t) { 
		try {
			if(crudEmployee.Delete("employee", t.getId())) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}