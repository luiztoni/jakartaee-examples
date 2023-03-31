package br.luiztoni.servlet.config.auth;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 72384783892345578L;
	private int id;
	private String email;
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}