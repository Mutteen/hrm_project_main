package com.hrm.model.data_access_object;

public class db_info {

	public db_info() {
		// TODO Auto-generated constructor stub
	}
	
	public static String getServer() {
		 return "localhost";
	}
	
	public static String getPort() {
		return "3306";
	}

	public static String getDatabase() {
		return "hrm";
	}
	
	public static String getUsername() {
		return "admin";
	}
	
	public static String getPassword() {
		return "nghia2811";
	}
	
	public static String getDbUrl() {
		return "jdbc:mysql://"+ getServer() +":"+getPort()+"/"+getDatabase();
	}

}
