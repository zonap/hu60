package com.domain;

public class TzInfo {
	private String tzid;
	private String bkid;
	private String uid;
	private String uname;
	private String title;
	private String nr;
	private String fttime;
	private String hftime;
	private String hfcount;
	private String rdcount;

	public TzInfo(String tzid, String bkid, String uid, String uname,
			String title, String nr, String fttime, String hftime,
			String hfcount, String rdcount) {
		super();
		this.tzid = tzid;
		this.bkid = bkid;
		this.uid = uid;
		this.uname = uname;
		this.title = title;
		this.nr = nr;
		this.fttime = fttime;
		this.hftime = hftime;
		this.hfcount = hfcount;
		this.rdcount = rdcount;
	}

	public TzInfo() {
		// TODO Auto-generated constructor stub
	}

	public String getTzid() {
		return tzid;
	}

	public void setTzid(String tzid) {
		this.tzid = tzid;
	}

	public String getBkid() {
		return bkid;
	}

	public void setBkid(String bkid) {
		this.bkid = bkid;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNr() {
		return nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	public String getFttime() {
		return fttime;
	}

	public void setFttime(String fttime) {
		this.fttime = fttime;
	}

	public String getHftime() {
		return hftime;
	}

	public void setHftime(String hftime) {
		this.hftime = hftime;
	}

	public String getHfcount() {
		return hfcount;
	}

	public void setHfcount(String hfcount) {
		this.hfcount = hfcount;
	}

	public String getRdcount() {
		return rdcount;
	}

	public void setRdcount(String rdcount) {
		this.rdcount = rdcount;
	}

	@Override
	public String toString() {
		return "TzInfo [tzid=" + tzid + ", bkid=" + bkid + ", uid=" + uid
				+ ", uname=" + uname + ", title=" + title + ", nr=" + nr
				+ ", fttime=" + fttime + ", hftime=" + hftime + ", hfcount="
				+ hfcount + ", rdcount=" + rdcount + "]";
	}

}
