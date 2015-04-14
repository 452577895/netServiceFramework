package com.example.netserviceframework.model;
/**
 * 示范的实体类
 * @author hjw
 *
 */
public class DateDemo{
	private String thisIsDate;

	public String getThisIsDate() {
		return thisIsDate;
	}

	public void setThisIsDate(String thisIsDate) {
		this.thisIsDate = thisIsDate;
	}

	public DateDemo(String thisIsDate) {
		super();
		this.thisIsDate = thisIsDate;
	}

	public DateDemo() {
		super();
	}
	
}
