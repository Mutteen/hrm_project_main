package com.hrm.model.business_objects;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hrm.model.usersession;
import com.hrm.model.beans.employee;
import com.hrm.model.data_access_object.connect_database;
import com.hrm.model.data_access_object.crud;
import com.hrm.model.data_access_object.db_info;
import com.hrm.model.data_access_object.show_data;


public class bo_employee {
	private static Connection connection = connect_database.getConnection(db_info.getDbUrl(), db_info.getUsername(), db_info.getPassword());
	
	public bo_employee() {
		// TODO Auto-generated constructor stub
	}	
	
	public static boolean login(String username, String password) throws SQLException {
		
		crud<employee> crudEmployee = new crud<employee>(connection){};
		
		ArrayList<employee> listEmployee =  crudEmployee.getAll("employee");
		 
		for (employee employee : listEmployee) {
			if(username.equals(employee.getUsername()) && password.equals(employee.getPassword())) {
				usersession.getInstace(employee.getId() ,employee.getFirst_name(), null);
				return true;					
			}
		}
		return false;
	
	}
	
	public static ArrayList<employee> getProfile(int idUser) throws SQLException{
		
		show_data<employee> showProfile = new show_data<>(connection);
		
		ArrayList<employee> listData = showProfile.getProfile(idUser);	
		
		for (employee employee : listData) {
			System.out.println(employee.getFirst_name());
		}
		
		return listData;
		
	}

	public static ArrayList<employee> getListEmployee() throws SQLException{
		show_data<employee> showList = new show_data<>(connection);
		ArrayList<employee> listData = showList.getAll();

		return listData;
	}
	
}
