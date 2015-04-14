package com.example.netserviceframework.service;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.example.netserviceframework.MainActivity;
import com.example.netserviceframework.Constants.Constants;
import com.example.netserviceframework.interf.BaseActivity;
import com.example.netserviceframework.model.DateDemo;
import com.example.netserviceframework.net.TestNetService;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

/**
 * ���ں�̨���������������ķ����࣬�ڳ��������ڼ䣬��һֱ����
 * @author hjw
 *
 */
public class MainService extends Service implements Runnable {
	/**������У�װ�ؿ�ִ������*/
	public static Queue<Task> tasks = new LinkedList<Task>();
	/**��¼���������ӦActivity��List*/
	public static List<BaseActivity> activityList = new ArrayList<BaseActivity>();
	@Override
	public void onCreate() {
		//һ�������񣬱㿪���̣߳�ѭ�������Ƿ���������
		System.out.println("====1����������");
		Thread thread = new Thread(this);
		thread.start();
	}
	/**
	 * �ṩ�ⲿ�����˷��������������������
	 * @param newTask
	 */
	public static void addTask(Task newTask){
		System.out.println("====2���������");
		tasks.add(newTask);
	}
	
	/**
	 * �ṩ�ⲿ���ô˷����÷��������Activity������ӵ�List��
	 * @param activity
	 */
	public static void addActivity(BaseActivity activity){
		System.out.println("====3act�����");
		activityList.add(activity);
	}
	
	private BaseActivity getActivityByName(String name){
		for (BaseActivity activity : activityList) {
			if(activity.getClass().getName().indexOf(name)>0){
				return activity;
			};
		}
		return null;
	}
	@Override
	public void run() {
		//�߳�ѭ������ȡ������е����񣬲�����
		while(true){
			Task task = null;
			if(!tasks.isEmpty()){
				task = tasks.poll();
				System.out.println("====4�����ȡ��");
			}
			if(task!=null){
				doTask(task);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case Task.Task_LOAD_DATE_DEMO:
				System.out.println("====9��Ϣ�յ���");
				List<DateDemo> list = (List<DateDemo>) msg.obj;
				MainActivity activity = (MainActivity) getActivityByName("MainActivity");
				activity.refresh(list);
				break;
			}
			
		};
	};
	private void doTask(Task task) {
		System.out.println("====5����ִ����");
		//ִ��һ����������Ϣ����ȥ����UI
		Message message = Message.obtain();
		message.what = task.getTaskId();
		switch (task.getTaskId()) {
		case Task.Task_LOAD_DATE_DEMO:
			TestNetService service = new TestNetService();
			List<DateDemo> list = service.getDate(task.getTaskParam(),Constants.TESTPATH);
			message.obj = list;
			handler.sendMessage(message);
			break;
			}
	}
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

}
