package com.smart.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class BeanUtil {
	public static Map<String, Object> bean2map(Object entity) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		Field[] fields = entity.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			String fieldName = fields[i].getName();
			if ("serialVersionUID".equals(fieldName)) {
				continue;
			}
			Object o = null;
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getMethodName = "get" + firstLetter + fieldName.substring(1);
			Method getMethod;
			try {
				getMethod = entity.getClass().getMethod(getMethodName,
						new Class[] {});
				o = getMethod.invoke(entity, new Object[] {});
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (o != null) {
				parameter.put(fieldName, o);
			}
		}
		return parameter;  
	}

	public static Object map2bean(Class<Object> type, Map<String, Object> map) {
		Object t = null;
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
			t = type.newInstance();
			// 给 JavaBean 对象的属性赋值
			for (PropertyDescriptor descriptor : beanInfo
					.getPropertyDescriptors()) {
				String propertyName = descriptor.getName();
				if (map.containsKey(propertyName)) {
					// 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
					try {
						descriptor.getWriteMethod().invoke(t,
								map.get(propertyName));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return t;
	}
	public static Object request2bean(Class<?> type, HttpServletRequest req) {
		Object t = null;
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
			t = type.newInstance();
			// 给 JavaBean 对象的属性赋值
			for (PropertyDescriptor descriptor : beanInfo
					.getPropertyDescriptors()) {
				String propertyName = descriptor.getName();
				
				if(propertyName.equals("class")) continue;
				
				try {
					descriptor.getWriteMethod().invoke(t, req.getParameter(propertyName));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
				
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return t;
	}

	public static void main(String[] args) {

	}
	
}

