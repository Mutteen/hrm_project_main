package com.hrm.model.data_access_object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.hrm.model.beans.salary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class salaryDao implements DAO<salary> {
	static String sql = "";
	public salaryDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ObservableList<salary> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(salary salary) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(salary salary) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(salary salary) {
		// TODO Auto-generated method stub
		return false;
	}

	public static ObservableList<salary> getData(){
		ObservableList<salary> list = FXCollections.observableArrayList();
		try {
			Connection conn = connection_db.getConnection();
			sql = "SELECT (SUM(value_money) + SUM(value_money_reward)) AS 'Money', MONTH(created_at) AS 'Month'\r\n"
					+ "FROM salary\r\n"
					+ "WHERE YEAR(created_at) =\"2023\"\r\n"
					+ "GROUP BY MONTH(created_at)\r\n"
					+ "ORDER BY  MONTH(created_at)";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {			
				salary salary = new salary();
				salary.setTotal_salary(rs.getInt("Money"));
				salary.setMonth_to_pay(rs.getString("Month"));
				list.add(salary);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
