package com.hrm.model.business_objects;

import com.hrm.model.beans.taskPosition;
import com.hrm.model.data_access_object.DAO;
import com.hrm.model.data_access_object.taskPositionDAO;

import javafx.collections.ObservableList;

public class bo_taskPosition implements DAO<taskPosition> {
	private taskPositionDAO dataDAO = new taskPositionDAO();

	public bo_taskPosition() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ObservableList<taskPosition> getAll() {
		// TODO Auto-generated method stub
		return dataDAO.getAll();
	}

	@Override
	public boolean save(taskPosition t) {
		// TODO Auto-generated method stub
		return dataDAO.save(t);
	}

	@Override
	public boolean update(taskPosition t) {
		// TODO Auto-generated method stub
		return dataDAO.update(t);
	}

	@Override
	public boolean delete(taskPosition t) {
		// TODO Auto-generated method stub
		return dataDAO.delete(t);
	}

}
