package com.example.netserviceframework.adapter;

import java.util.List;

import com.example.netserviceframework.R;
import com.example.netserviceframework.model.DateDemo;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
/**
 * 自定义，已优化的ListView加载数据的Adapter
 * @author Administrator
 *
 */
public class MyAdapter extends BaseAdapter {
	private List<DateDemo> list;
	private Context context;
	private LayoutInflater mInflater;
	
	
	public List<DateDemo> getList() {
		return list;
	}

	public void setList(List<DateDemo> list) {
		this.list = list;
	}

	public MyAdapter(List<DateDemo> list, Context context) {
		super();
		this.list = list;
		this.context = context;
		this.mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int positon, View convertView, ViewGroup parent) {
		View view = null;
		ViewHolder viewHolder = null;
		if(convertView==null)
		{
			view = mInflater.inflate(R.layout.listview_item, null);
			viewHolder = new ViewHolder();
			viewHolder.tv_content = (TextView) view.findViewById(R.id.tv_content);
			view.setTag(viewHolder);
		}else{
			view =convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		DateDemo dateDemo = list.get(positon);
		viewHolder.tv_content.setText(dateDemo.getThisIsDate());
		return view;
	}
	private  class ViewHolder{
		TextView tv_content;
	}

}
