package com.hrm.model;

import java.util.Set;

public final class usersession {

	private static usersession instance;
	private static String userName;
	private static int idUser;
	private static int role_id;
	private static int department_id;
	
	public void name() {

	}

	private Set<String> privileges;

	private usersession(int idUser, String userName, int role_id, int department_id) {
		this.idUser = idUser;
		this.userName = userName;
		this.role_id = role_id;
		this.department_id = department_id;

	}

	public static usersession getInstace(int idUser, String userName, int role_id, int department_id) {
		if (instance == null) {
			instance = new usersession(idUser, userName, role_id, department_id);

		}
		return instance;
	}

	public static int getIdUser() {
		return idUser;
	}

	public static String getUserName() {
		return userName;
	}

	public static int getRole_id() {
		return role_id;
	}
	
	public static int getDepartment_id() {
		return department_id;
	}

	public Set<String> getPrivileges() {
		return privileges;
	}

	public static void cleanUserSession() {
		instance = null;
//		userName = "";// or null
//		privileges = new HashSet<>();// or null

	}

	@Override
	public String toString() {
		return "UserSession{" + "userName='" + userName + '\'' + ", privileges=" + privileges + '}';
	}
}