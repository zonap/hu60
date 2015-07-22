package com.domain;

import java.io.Serializable;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean islogin;
	private String name;
	private String uid;
	private String sid;
	private int errid;
	public String errmsg;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(boolean islogin, String name, String uid, String sid,
			int errid, String errmsg) {
		this.islogin = islogin;
		this.name = name;
		this.uid = uid;
		this.sid = sid;
		this.errid = errid;
		this.errmsg = errmsg;
	}

	public boolean isIslogin() {
		return islogin;
	}

	public void setIslogin(boolean islogin) {
		this.islogin = islogin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public int getErrid() {
		return errid;
	}

	public void setErrid(int errid) {
		this.errid = errid;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	@Override
	public String toString() {
		return "User [islogin=" + islogin + ", name=" + name + ", uid=" + uid
				+ ", sid=" + sid + ", errid=" + errid + ", errmsg=" + errmsg
				+ "]";
	}

	

}
