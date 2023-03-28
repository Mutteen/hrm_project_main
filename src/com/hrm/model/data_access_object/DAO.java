package com.hrm.model.data_access_object;


public interface DAO<T> {
	boolean save(T t);

	boolean update(T t);

	boolean delete(T t);

}
