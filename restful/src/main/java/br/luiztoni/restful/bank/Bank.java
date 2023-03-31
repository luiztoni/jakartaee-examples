package br.luiztoni.restful.bank;

import jakarta.json.bind.annotation.JsonbProperty;

public class Bank {
	
	@JsonbProperty("ispb")
	private String ispb;
	
	@JsonbProperty("name")
	private String name;
	
	@JsonbProperty("code")
	private String code;
	
	@JsonbProperty("fullName")
	private String fullName;
	
	public Bank() {
		
	}
	
	public Bank(String ispb, String name, String code, String fullName) {
		super();
		this.ispb = ispb;
		this.name = name;
		this.code = code;
		this.fullName = fullName;
	}
	
	public String getIspb() {
		return ispb;
	}
	public void setIspb(String ispb) {
		this.ispb = ispb;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}
