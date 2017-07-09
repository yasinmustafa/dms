package com.dms.model.domain;

public class UserDomain extends BaseDomain implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long usrId;
	private String name;
	private String password;

	public Long getUsrId() {
		return this.usrId;
	}

	public void setUsrId(Long usrId) {
		this.usrId = usrId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String usrName) {
		this.name = usrName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}