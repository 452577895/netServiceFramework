package com.example.netserviceframework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import com.example.netserviceframework.adapter.MyAdapter;
import com.example.netserviceframework.interf.BaseActivity;
import com.example.netserviceframework.model.DateDemo;
import com.example.netserviceframework.service.MainService;
import com.example.netserviceframework.service.Task;
/**
 * 显示界面，显示从网上加载的数据,以ListView的方式
 * @author Administrator
 *
 */
public class MainActivity extends Activity implements BaseActivity {
	private ListView lv_demo;
	private List<DateDemo> list = new ArrayList<DateDemo>();
	private MyAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//设置铺满屏幕
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_main);
		
		Intent intent = new Intent(this,MainService.class);
		startService(intent);
		init();
		Map<String, String> taskParam = new HashMap<String, String>();
		taskParam.put("name", "test");
		//当不需要参数的时候，也要传入一个没有值的taskParams，否则会报错
		Task task = new Task(Task.Task_LOAD_DATE_DEMO, taskParam);
		
		MainService.addTask(task);
		MainService.addActivity(this);
		adapter = new MyAdapter(list, this);
		lv_demo.setAdapter(adapter);
	}


	@Override
	public void init() {
		lv_demo = (ListView) findViewById(R.id.lv_demo);
	}

	@Override
	public void refresh(Object... objects) {
		System.out.println("====10界面更新了");
		if(objects!=null){
		List<DateDemo> tlist = (List<DateDemo>)objects[0];
		list.addAll(tlist);
		adapter.setList(list);
		adapter.notifyDataSetChanged();
		}
	}

}
