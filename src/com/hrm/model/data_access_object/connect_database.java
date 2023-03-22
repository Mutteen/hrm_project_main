package com.hrm.model.data_access_object;

import java.sql.Connection;
import java.sql.DriverManager;

public class connect_database {

	public connect_database() {
		// TODO Auto-generated constructor stub
	}

	public static Connection getConnection(String dbURL, String userName, String password) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
  
            conn = DriverManager.getConnection(dbURL, userName, password);  
            
            System.out.println("Successfully!");
        } catch (Exception ex) {
            System.out.println("Error");
            ex.printStackTrace();
        }
        return conn;
	}
	
}
