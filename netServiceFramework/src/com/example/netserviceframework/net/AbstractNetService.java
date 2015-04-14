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
 * ���о������������ĸ��࣬�ṩ������ʷ����ͷ��ؽ����������
 * @author hjw
 *
 */
public class AbstractNetService {
	/**
	 * ��Post��ʽ��������������ķ���
	 * @param taskParams
	 * @param path
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public InputStream netServiceByHttpPost(Map<String, String> taskParams,String path) throws ClientProtocolException, IOException{
		System.out.println("====7���������");
		//1.�������
		HttpClient client = new DefaultHttpClient();
		//2.����һ�����󣬴����ַ���������ύ��ʽ,������post
		HttpPost request = new HttpPost(path);
		//3.���ó�ʱ 5s
		request.getParams().setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
		//4.��������������,���taskParams��Ϊ�յĻ�
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		if(!taskParams.isEmpty()){
			for (Entry<String, String> date : taskParams.entrySet()) {
				NameValuePair nameValuePair = new BasicNameValuePair(date.getKey(), date.getValue());
				parameters.add(nameValuePair);
			}
		}
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters);
		request.setEntity(entity);
		//�������󣬵õ���Ӧ
		HttpResponse response = client.execute(request);
		//��ȡ��Ӧ��
		int code = response.getStatusLine().getStatusCode();
		if(code==200){
			InputStream is = response.getEntity().getContent();
			return is;
		}
		return null;
	}
	
}
