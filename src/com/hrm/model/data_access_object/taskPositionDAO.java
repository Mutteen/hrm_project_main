package com.hrm.model.data_access_object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.text.DefaultEditorKit.PasteAction;

import com.hrm.model.beans.taskPosition;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class taskPositionDAO implements DAO<taskPosition> {
	static Connection conn = connection_db.getConnection();
	static String sql = "";

	public taskPositionDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ObservableList<taskPosition> getAll() {
		ObservableList<taskPosition> List = FXCollections.observableArrayList();
		// TODO Auto-generated method stub
		try {
			Connection conn = connection_db.getConnection();
			// Step 2
			sql = "SELECT TP.id,TP.position_id,TP.task_id,T.title,P.position_name FROM hrm.task_position AS TP \r\n"
					+ "INNER JOIN hrm.`position` AS P ON P.id=TP.position_id\r\n"
					+ "INNER JOIN hrm.task AS T ON T.id=TP.task_id  \r\n"
					+ "WHERE T.flag =0\r\n"
					+ "ORDER  BY TP.id DESC";
			// Step 3
			PreparedStatement pst = conn.prepareStatement(sql);
			// Step 4
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				taskPosition TP = new taskPosition();
				TP.setId(rs.getInt(1));
				TP.setPosition_id(rs.getInt(2));
				TP.setTask_id(rs.getInt(3));
				TP.setTaskString(rs.getString(4));
				TP.setPositionString(rs.getString(5));
				List.add(TP);

			}
			// Step 5
//			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return List;
	}

	@Override
	public boolean save(taskPosition t) {
		boolean check = false;
		// TODO Auto-generated method stub
		try {

			sql = "INSERT INTO `hrm`.`task_position` (`position_id`, `task_id`) VALUES (?, ?);";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, t.getPosition_id());
			pst.setInt(2, t.getTask_id());

			int rowInsert = pst.executeUpdate();
			if (rowInsert > 0) {
				check = true;
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}

	@Override
	public boolean update(taskPosition t) {
		// TODO Auto-generated method stub
		boolean check = false;
		try {

			sql = "UPDATE `hrm`.`task_position` SET `position_id`=?, `task_id`=? WHERE  `id`=?;";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, t.getPosition_id());
			pst.setInt(2, t.getTask_id());
			pst.setInt(3, t.getId());

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

	@Override
	public boolean delete(taskPosition t) {
		/// TODO Auto-generated method stub
		boolean check = false;
		try {

			sql = "UPDATE `hrm`.`task_position` SET `flag`='1' WHERE  `id`=?;";
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
