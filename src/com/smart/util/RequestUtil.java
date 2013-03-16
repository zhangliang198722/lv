package com.smart.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
	/**
	 * 这个方法会把request的parameter 转化为map，一个name下多个值的为数组类型
	 * @param req HttpServletRequest
	 * @return Map 型参数
	 */
	@SuppressWarnings("unchecked")
	static public Map<String,Object> requestParameterMap(HttpServletRequest req){
		Enumeration<String> en = req.getParameterNames();
		Map<String,Object> map = new HashMap<String,Object>();
		while(en.hasMoreElements()){
			String key = en.nextElement();
			if(((Object[])req.getParameterMap().get(key)).length==1){
				map.put(key, req.getParameter(key));
			}else{
				map.put(key, req.getParameterMap().get(key));
			}
		}
		return map;
	}
	/**
	 * 这个方法会把request的parameter 转化为map，一个name下多个值值存第一个值
	 * @param req HttpServletRequest
	 * @return Map 型参数
	 */
	@SuppressWarnings("unchecked")
	static public Map<String,Object> simpleRequestParameterMap(HttpServletRequest req){
		Enumeration<String> en = req.getParameterNames();
		Map<String,Object> map = new HashMap<String,Object>();
		while(en.hasMoreElements()){
			String key = en.nextElement();
			map.put(key, req.getParameter(key));
		}
		return map;
	}
}
