package com.hrm.model.data_access_object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hrm.model.beans.employee;
import com.hrm.model.beans.position;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class positionDAO implements DAO<position> {

	public positionDAO() {
		// TODO Auto-generated constructor stub
	}

	static String sql = "";
	static Connection conn = connection_db.getConnection();

	@Override
	public ObservableList<position> getAll() {

		return null;
	}

	public ObservableList<position> getDepartment(int id) {
		return null;
	}

	@Override
	public boolean save(position t) {

		boolean check = false;
		try {

			sql = "INSERT INTO position (`position_name`,`who_create`,`created_at`,`description`) VALUES (?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, t.getPosition_name());
			pst.setString(2, t.getWho_create());
			pst.setDate(3, t.getCreated_at());
			pst.setString(4, t.getDescription());
			int rowInsert = pst.executeUpdate();

			if (rowInsert > 0) {
				check = true;
			}
//			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return check;
	}

	@Override
	public boolean update(position t) {
		// TODO Auto-generated method stub
		boolean check = false;
		try {

			sql = "UPDATE hrm.position AS P SET P.position_name=?,P.description=?,P.created_at=?,P.who_create=? WHERE P.id= ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, t.getPosition_name());
			pst.setString(2, t.getDescription());
			pst.setDate(3, t.getCreated_at());
			pst.setString(4, t.getWho_create());
			pst.setInt(5, t.getId());
			int rowInsert = pst.executeUpdate();

			if (rowInsert > 0) {
				check = true;
			}
//			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return check;

	}

	@Override
	public boolean delete(position t) {

		// TODO Auto-generated method stub
		boolean check = false;
		try {

			sql = "UPDATE hrm.position AS D SET D.flag=1 WHERE D.id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, t.getId());

			int rowInsert = pst.executeUpdate();

			if (rowInsert > 0) {
				check = true;
			}
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return check;

	}

}
