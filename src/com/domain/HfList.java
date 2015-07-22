package com.domain;

public class HfList {
	private String tzid;
	private String uid;
	private String uname;
	private String nr;
	private String hftime;

	public HfList(String tzid, String uid, String uname, String nr,
			String hftime) {
		super();
		this.tzid = tzid;
		this.uid = uid;
		this.uname = uname;
		this.nr = nr;
		this.hftime = hftime;
	}

	public HfList() {
		// TODO Auto-generated constructor stub
	}

	public String getTzid() {
		return tzid;
	}

	public void setTzid(String tzid) {
		this.tzid = tzid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getNr() {
		return nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	public String getHftime() {
		return hftime;
	}

	public void setHftime(String hftime) {
		this.hftime = hftime;
	}

	@Override
	public String toString() {
		return "HfList [tzid=" + tzid + ", uid=" + uid + ", uname=" + uname
				+ ", nr=" + nr + ", hftime=" + hftime + "]";
	}

}
