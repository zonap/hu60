package com.domain;

public class HfInfoHead {

	private String tzid;
	private String count;
	private int offset;
	private int size;

	public HfInfoHead(String tzid, String count, int offset, int size) {
		super();
		this.tzid = tzid;
		this.count = count;
		this.offset = offset;
		this.size = size;
	}

	public HfInfoHead() {
		// TODO Auto-generated constructor stub
	}

	public String getTzid() {
		return tzid;
	}

	public void setTzid(String tzid) {
		this.tzid = tzid;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
