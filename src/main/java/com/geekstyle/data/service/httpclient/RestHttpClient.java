package com.geekstyle.data.service.httpclient;

import java.util.HashMap;

import org.springframework.util.MultiValueMap;

public interface RestHttpClient {
	
	/**
	 * 
	 * @param url
	 * @return
	 */
	public HashMap<?,?> postForm(String url,MultiValueMap<String, String> formData);
	
	public HashMap<?,?> simpleGetData(String url);
	
}
