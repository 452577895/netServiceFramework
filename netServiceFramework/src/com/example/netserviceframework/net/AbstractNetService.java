package com.example.netserviceframework.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;

import com.example.netserviceframework.model.DateDemo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
/**
 * 所有具体访问网络类的父类，提供网络访问方法和返回结果解析方法
 * @author hjw
 *
 */
public class AbstractNetService {
	/**
	 * 用Post方式访问网络服务器的方法
	 * @param taskParams
	 * @param path
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public InputStream netServiceByHttpPost(Map<String, String> taskParams,String path) throws ClientProtocolException, IOException{
		System.out.println("====7网络访问了");
		//1.打开浏览器
		HttpClient client = new DefaultHttpClient();
		//2.创造一个请求，传入地址，并设置提交方式,这里用post
		HttpPost request = new HttpPost(path);
		//3.设置超时 5s
		request.getParams().setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
		//4.将参数赋给请求,如果taskParams不为空的话
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		if(!taskParams.isEmpty()){
			for (Entry<String, String> date : taskParams.entrySet()) {
				NameValuePair nameValuePair = new BasicNameValuePair(date.getKey(), date.getValue());
				parameters.add(nameValuePair);
			}
		}
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters);
		request.setEntity(entity);
		//发送请求，得到响应
		HttpResponse response = client.execute(request);
		//获取响应码
		int code = response.getStatusLine().getStatusCode();
		if(code==200){
			InputStream is = response.getEntity().getContent();
			return is;
		}
		return null;
	}
	
}
