package com.example.netserviceframework.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.example.netserviceframework.model.DateDemo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
/**
 * ��ĳ������Ĺ��ܣ�����Ҫ���������������в������������ڼ���List����
 * @author Administrator
 *
 */
public class TestNetService extends AbstractNetService {
	public List<DateDemo> getDate(Map<String, String> taskParams,String path){
		System.out.println("====6���������");
		try {
			InputStream is = netServiceByHttpPost(taskParams, path);
			List<DateDemo> result = parseJsontoObject(is);
			
			return result;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * ���ӷ�������ȡ���� is ������List�ķ���
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public List<DateDemo> parseJsontoObject(InputStream is) throws IOException{
		System.out.println("====8���ݽ�����");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuffer sb = new StringBuffer();
		String line = null;
		while((line=br.readLine())!=null){
			sb.append(line);
		}
		br.close();
		String content = sb.toString();
		Gson gson = new Gson();
		TypeToken<List<DateDemo>> typeToken = new TypeToken<List<DateDemo>>(){};
		List<DateDemo> list = gson.fromJson(content, typeToken.getType());
		return list;
	}
}
