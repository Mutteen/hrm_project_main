package com.hrm.model;

import java.util.HashSet;
import java.util.Set;

public final class usersession {

	private static usersession instance;
	private static String userName;
	private static int idUser;

	public void name() {

	}

	private static Set<String> privileges;

	private usersession(int idUser, String userName, Set<String> privileges) {
		this.idUser = idUser;
		this.userName = userName;
		this.privileges = privileges;
	}

	public static usersession getInstace(int idUser,String userName, Set<String> privileges) {
		if (instance == null) {
			instance = new usersession(idUser,userName, privileges);
		}
		return instance;
	}

	public static int getIdUser() {
		return idUser;
	}
	
	public static String getUserName() {
		return userName;
	}

	public Set<String> getPrivileges() {
		return privileges;
	}

	public static void cleanUserSession() {
		userName = null;// or null
		idUser = 0;
		privileges = new HashSet<>();// or null
	}

	@Override
	public String toString() {
		return "UserSession{" + "userName='" + userName + '\'' + ", privileges=" + privileges + '}';
	}
}