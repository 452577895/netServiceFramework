package com.example.netserviceframework.interf;
/**
 * 所有的Activity都要继承该接口，已获得init()，和refresh方法
 * @author Administrator
 *
 */
public interface BaseActivity {
	/**
	 * 控件初始化方法，用于对布局文件中控件的初始化
	 */
	public void init();
	/**
	 * 界面刷新方法，传入不定长的Object数组，用来刷新界面
	 * @param objects
	 */
	public void refresh(Object... objects);
	
}
