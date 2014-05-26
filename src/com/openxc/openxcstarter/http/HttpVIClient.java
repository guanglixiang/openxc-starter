package com.openxc.openxcstarter.http;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpVIClient {

	public HttpVIClient() {}
	
	public static HttpClient getHttpVIClient(){
		HttpClient httpClient = new DefaultHttpClient();
		return httpClient;
	}
	
	public static String sendPost(String url, Map<String, String> params)
			throws ParseException, IOException {
		String response = null;

		List<NameValuePair> valuePairs = new ArrayList<NameValuePair>();
		if (params != null) {
			for (String str : params.keySet()) {
				valuePairs.add(new BasicNameValuePair(str, params.get(str)));
			}
		}

		HttpPost httpPost = new HttpPost(url);
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(valuePairs));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HttpResponse httpResponse = null;
		try {
			httpResponse = getHttpVIClient().execute(httpPost);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			httpPost.abort();
		}

		if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			response = EntityUtils.toString(httpResponse.getEntity());
		}

		return response;
	}
}
