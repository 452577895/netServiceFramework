package com.example.netserviceframework.interf;
/**
 * ���е�Activity��Ҫ�̳иýӿڣ��ѻ��init()����refresh����
 * @author Administrator
 *
 */
public interface BaseActivity {
	/**
	 * �ؼ���ʼ�����������ڶԲ����ļ��пؼ��ĳ�ʼ��
	 */
	public void init();
	/**
	 * ����ˢ�·��������벻������Object���飬����ˢ�½���
	 * @param objects
	 */
	public void refresh(Object... objects);
	
}
