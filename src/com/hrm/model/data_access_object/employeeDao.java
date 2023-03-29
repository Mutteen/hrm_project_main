package com.hrm.model.data_access_object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hrm.model.beans.department;
import com.hrm.model.beans.employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class employeeDao implements DAO<employee> {
	static String sql = "";
	public employeeDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ObservableList<employee> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(employee employee) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(employee employee) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(employee employee) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public ObservableList<employee> getHireDate(){
		ObservableList<employee> list = FXCollections.observableArrayList();
		try {
			Connection conn = connection_db.getConnection();
			sql = "SELECT MONTH(hire_date) AS \"Month\", COUNT(id) AS \"Quality employee\"\r\n"
					+ "FROM employee\r\n"
					+ "WHERE STATUS = 0 AND YEAR(hire_date) = \"2023\"\r\n"
					+ "GROUP BY MONTH(hire_date)\r\n"
					+ "ORDER BY MONTH(hire_date);";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {			
				employee employee = new employee();
				employee.setHire_month(rs.getString("Month"));
				employee.setQuantity_employee(rs.getInt("Quality employee"));
				list.add(employee);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ObservableList<employee> getTerDate(){
		ObservableList<employee> list = FXCollections.observableArrayList();
		try {
			Connection conn = connection_db.getConnection();
			sql = "SELECT MONTH(hire_date) AS \"Month\", COUNT(id) AS \"Quality employee\"\r\n"
					+ "FROM employee\r\n"
					+ "WHERE STATUS = 1 AND YEAR(hire_date) = \"2023\"\r\n"
					+ "GROUP BY MONTH(hire_date)\r\n"
					+ "ORDER BY MONTH(hire_date);";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {			
				employee employee = new employee();
				employee.setTermination_month(rs.getString("Month"));
				employee.setQuantity_employee(rs.getInt("Quality employee"));
				list.add(employee);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static ObservableList<employee> getData(){
		ObservableList<employee> list = FXCollections.observableArrayList();
		try {
			Connection conn = connection_db.getConnection();
			sql = "SELECT COUNT(`E`.department_id)AS 'Quatity employee', `D`.department_name\r\n"
					+ "FROM employee `E`\r\n"
					+ "INNER JOIN department `D` ON `E`.department_id = `D`.id\r\n"
					+ "GROUP BY department_id\r\n"
					+ "ORDER BY department_id;";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {			
				employee employee = new employee();
				employee.setDepartment_id(rs.getInt("Quatity employee"));
				
				department deparment = new department();
				deparment.setDepartment_name(rs.getString("department_name"));
				employee.setDepartment(deparment);
				
				list.add(employee);				
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static String getTotal() {
		String total = "";
		try {
			Connection conn = connection_db.getConnection();
			sql = "SELECT COUNT(id) AS 'Total'\r\n"
					+ "FROM employee\r\n"
					+ "WHERE flag = 0 AND `status` = 0";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {			
				total = rs.getString("Total");
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}
	
	public static String getAvgDob() {
		String avg = "";
		try {
			Connection conn = connection_db.getConnection();
			sql = "SELECT AVG(YEAR(CURDATE())-YEAR(dob)) AS 'avg'\r\n"
					+ "FROM employee\r\n";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {			
				avg = rs.getString("avg");
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return avg;
	}
	
	public static String getTotalMale() {
		String gender = "";
		try {
			Connection conn = connection_db.getConnection();
			sql = "SELECT COUNT(gender)  AS 'total'\r\n"
					+ "FROM employee\r\n"
					+ "WHERE gender = 'male'";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {			
				gender = rs.getString("total");
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gender;
	}
	
	public static String getTotalFemale() {
		String gender = "";
		try {
			Connection conn = connection_db.getConnection();
			sql = "SELECT COUNT(gender)  AS 'total'\r\n"
					+ "FROM employee\r\n"
					+ "WHERE gender = 'female'";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {			
				gender = rs.getString("total");
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gender;
	}
}
