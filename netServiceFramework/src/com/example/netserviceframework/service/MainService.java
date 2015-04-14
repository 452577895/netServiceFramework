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
 * 用于后台监听网络访问任务的服务类，在程序运行期间，它一直存在
 * @author hjw
 *
 */
public class MainService extends Service implements Runnable {
	/**任务队列，装载可执行任务*/
	public static Queue<Task> tasks = new LinkedList<Task>();
	/**记录发送任务对应Activity的List*/
	public static List<BaseActivity> activityList = new ArrayList<BaseActivity>();
	@Override
	public void onCreate() {
		//一启动服务，便开启线程，循环监听是否有任务发来
		System.out.println("====1服务启动了");
		Thread thread = new Thread(this);
		thread.start();
	}
	/**
	 * 提供外部调动此方法给任务队列增加任务
	 * @param newTask
	 */
	public static void addTask(Task newTask){
		System.out.println("====2任务添加了");
		tasks.add(newTask);
	}
	
	/**
	 * 提供外部调用此方法让发送任务的Activity自身添加到List中
	 * @param activity
	 */
	public static void addActivity(BaseActivity activity){
		System.out.println("====3act添加了");
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
		//线程循环，读取任务队列的任务，并处理
		while(true){
			Task task = null;
			if(!tasks.isEmpty()){
				task = tasks.poll();
				System.out.println("====4任务读取了");
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
				System.out.println("====9消息收到了");
				List<DateDemo> list = (List<DateDemo>) msg.obj;
				MainActivity activity = (MainActivity) getActivityByName("MainActivity");
				activity.refresh(list);
				break;
			}
			
		};
	};
	private void doTask(Task task) {
		System.out.println("====5任务执行了");
		//执行一个任务，用消息机制去处理UI
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
