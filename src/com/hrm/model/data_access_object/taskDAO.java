package com.hrm.model.data_access_object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hrm.model.beans.task;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class taskDAO implements DAO<task>{
	static String sql = "";
	public taskDAO(){
		// TODO Auto-generated constructor stub
	}

	@Override
	public ObservableList<task> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(task t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(task t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(task t) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public static ObservableList<task> getMonthByTask(){
		ObservableList<task> list = FXCollections.observableArrayList();
		try {
			Connection conn = connection_db.getConnection();
			sql = "SELECT COUNT(task.title)AS 'title task', task.`status`, MONTH(task.created_at) AS 'month'\r\n"
					+ "FROM task \r\n"
					+ "GROUP BY  MONTH(task.created_at), task.`status`\r\n"
					+ "ORDER BY  MONTH(task.created_at), task.`status`";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {			
				task task = new task();
				task.setQuantity_task(rs.getInt("title task"));
				task.setMonth_by_task(rs.getString("month"));
				task.setStatus(rs.getString("status"));
				list.add(task);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
