package com.hrm.model.business_objects;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hrm.model.beans.employee;
import com.hrm.model.beans.salary;
import com.hrm.model.data_access_object.DAO;
import com.hrm.model.data_access_object.connection_db;
import com.hrm.model.data_access_object.crud;
import com.hrm.model.data_access_object.show_data;

public class bo_salary{
	private static Connection conn = connection_db.getConnection();
	static crud<salary> crudEmployee = new crud<salary>(conn){};
	
	public bo_salary() {
		// TODO Auto-generated constructor stub
	}
	
	public static ArrayList<salary> getListSalary() throws SQLException{
		show_data<salary> showList = new show_data<>(conn);
		ArrayList<salary> listData = showList.getAllSalary();

		return listData;
	}

	public boolean save(salary salary) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean update(salary salary) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean delete(salary salary) {
		// TODO Auto-generated method stub
		return false;
	}

	

	


}
