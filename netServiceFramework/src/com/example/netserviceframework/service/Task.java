package com.example.netserviceframework.service;

import java.util.Map;
/**
 * 封装的任务类，将每个功能所需要的网络请求路径 path 和 任务参数params(Map类型) 
 * 封装成一个任务，并给与一个任务ID
 * @author hjw
 *
 */
public class Task {
	/**具体某个任务的ID，用常量表示*/
	public static final int Task_LOAD_DATE_DEMO = 0;
	
	/**任务ID*/
	private int taskId;
	/**任务的网络请求参数*/
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
