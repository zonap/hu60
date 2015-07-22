package com.domain;

import java.io.Serializable;

public class TzList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int size;
	private String count;
	private String id;
	private String bkid;
	private String title;
	private String uid;
	private String uname;
	private String fttime;
	private String hftime;
	private String hfcount;
	private String rdcount;

	public TzList() {

	}

	public TzList(int size, String count, String id, String bkid, String title,
			String uid, String uname, String fttime, String hftime,
			String hfcount, String rdcount) {
		super();
		this.size = size;
		this.count = count;
		this.id = id;
		this.bkid = bkid;
		this.title = title;
		this.uid = uid;
		this.uname = uname;
		this.fttime = fttime;
		this.hftime = hftime;
		this.hfcount = hfcount;
		this.rdcount = rdcount;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBkid() {
		return bkid;
	}

	public void setBkid(String bkid) {
		this.bkid = bkid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
		return "Tz [size=" + size + ", count=" + count + ", id=" + id
				+ ", bkid=" + bkid + ", title=" + title + ", uid=" + uid
				+ ", uname=" + uname + ", fttime=" + fttime + ", hftime="
				+ hftime + ", hfcount=" + hfcount + ", rdcount=" + rdcount
				+ "]";
	}

}
