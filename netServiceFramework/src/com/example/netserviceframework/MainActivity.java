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
 * ��ʾ���棬��ʾ�����ϼ��ص�����,��ListView�ķ�ʽ
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
		//����������Ļ
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_main);
		
		Intent intent = new Intent(this,MainService.class);
		startService(intent);
		init();
		Map<String, String> taskParam = new HashMap<String, String>();
		taskParam.put("name", "test");
		//������Ҫ������ʱ��ҲҪ����һ��û��ֵ��taskParams������ᱨ��
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
		System.out.println("====10���������");
		if(objects!=null){
		List<DateDemo> tlist = (List<DateDemo>)objects[0];
		list.addAll(tlist);
		adapter.setList(list);
		adapter.notifyDataSetChanged();
		}
	}

}
