package com.hrm.model.business_objects;

import com.hrm.model.beans.module;
import com.hrm.model.data_access_object.DAO;
import com.hrm.model.data_access_object.moduleDAO;

import javafx.collections.ObservableList;

public class bo_module implements DAO<module> {
	static moduleDAO dataDao = new moduleDAO();

	public bo_module() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ObservableList<module> getAll() {
		// TODO Auto-generated method stub
		return dataDao.getAll();
	}

	@Override
	public boolean save(module t) {
		// TODO Auto-generated method stub
		return dataDao.save(t);
	}

	@Override
	public boolean update(module t) {
		// TODO Auto-generated method stub
		return dataDao.update(t);
	}

	@Override
	public boolean delete(module t) {
		// TODO Auto-generated method stub
		return dataDao.delete(t);
	}

}
