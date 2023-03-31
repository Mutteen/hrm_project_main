package com.hrm.model;

import java.util.HashSet;
import java.util.Set;

public final class usersession {

	private static usersession instance;
	private static int id;
	private static String userName;

	private usersession(int id, String userName) {
		this.userName = userName;
		this.id = id;
	}

	public static usersession getInstace(int id, String userName) {
		if (instance == null) {
			instance = new usersession(id, userName);
		}
		return instance;
	}

	public String getUserName() {
		return userName;
	}

	public static int getId() {
		return id;
	}

	public static void cleanUserSession() {
		instance = null;// or null
//        privileges = new HashSet<>();// or null
	}

	@Override
	public String toString() {
		return "UserSession{" + "userName='" + userName + '\'' + ", privileges=" + '}';
	}
}