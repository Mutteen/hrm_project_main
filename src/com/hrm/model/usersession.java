package com.hrm.model;

import java.util.HashSet;
import java.util.Set;

public final class usersession {

	private static usersession instance;

	public void name() {

	}

	static String userName;
	private Set<String> privileges;

	private usersession(String userName, Set<String> privileges) {
		this.userName = userName;
		this.privileges = privileges;
	}

	public static usersession getInstace(String userName, Set<String> privileges) {
		if (instance == null) {
			instance = new usersession(userName, privileges);
		}
		return instance;
	}

	public static String getUserName() {
		return userName;
	}

	public Set<String> getPrivileges() {
		return privileges;
	}

	public void cleanUserSession() {
		userName = "";// or null
		privileges = new HashSet<>();// or null
	}

	@Override
	public String toString() {
		return "UserSession{" + "userName='" + userName + '\'' + ", privileges=" + privileges + '}';
	}
}