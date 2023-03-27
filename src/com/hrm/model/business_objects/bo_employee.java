package com.hrm.model.business_objects;

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




public class bo_employee implements DAO<employee>{
	private static Connection conn = connection_db.getConnection();
	static crud<employee> crudEmployee = new crud<employee>(conn){};
	
	public bo_employee() {
		// TODO Auto-generated constructor stub
	}

	public static boolean login(String username, String password) throws SQLException {		
		ObservableList<employee> listEmployee =  crudEmployee.getAll("employee");
		 
		for (employee employee : listEmployee) {
			if(username.equals(employee.getUsername()) && password.equals(employee.getPassword())) {
				usersession.getInstace(employee.getId() ,employee.getFirst_name(), null);
				return true;					
			}
		}
		return false;

	}

	public static ArrayList<employee> getProfile(int idUser) throws SQLException {

		show_data<employee> showProfile = new show_data<>(conn);

		ArrayList<employee> listData = showProfile.getProfile(idUser);

		for (employee employee : listData) {
			System.out.println(employee.getFirst_name());
		}

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