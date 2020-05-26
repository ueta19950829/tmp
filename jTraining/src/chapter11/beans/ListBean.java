package chapter11.beans;

import java.io.Serializable;
import java.util.Date;

public class ListBean implements Serializable{
	private int code;
	private String name;
	private Date releasedate;

	public ListBean(int code,String name, Date releasedate) {
		this.code = code;
		this.name = name;
		this.releasedate = releasedate;
	}

	public ListBean() {
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code  =code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getReleasedate() {
		return releasedate;
	}

	public void setReleasedate(Date releasedate) {
		this.releasedate= releasedate;
	}

}
