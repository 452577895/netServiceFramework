package com.example.netserviceframework.service;

import java.util.Map;
/**
 * ��װ�������࣬��ÿ����������Ҫ����������·�� path �� �������params(Map����) 
 * ��װ��һ�����񣬲�����һ������ID
 * @author hjw
 *
 */
public class Task {
	/**����ĳ�������ID���ó�����ʾ*/
	public static final int Task_LOAD_DATE_DEMO = 0;
	
	/**����ID*/
	private int taskId;
	/**����������������*/
	private Map<String, String> taskParam;
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public Map<String, String> getTaskParam() {
		return taskParam;
	}
	public void setTaskParam(Map<String, String> taskParam) {
		this.taskParam = taskParam;
	}
	public Task(int taskId, Map<String, String> taskParam) {
		super();
		this.taskId = taskId;
		this.taskParam = taskParam;
	}
	public Task() {
		super();
	}
	
}
