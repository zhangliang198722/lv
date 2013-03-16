package com.smart.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

public class Util {

	/**
	 * @function 字符转码
	 * @param text
	 * @param newEncode
	 * @return
	 */

	public static String getEncodeParam(String text, String newEncode) {

		String s = null;
		if (text != null && !"".equals(text)) {
			try {
				s = new String(text.getBytes("ISO-8859-1"), newEncode);
			}
			catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return s;
	}

	/**
	 * @function 获取uuid
	 * @author wpf
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}

	/**
	 * @author wpf
	 * @function 获取当前日期
	 */
	public static String getCurrentTime() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return df.format(System.currentTimeMillis());

	}

	/**
	 * 
	 * @Project: www.ishowchina.com
	 * @Method resultSetToJSON
	 * @returnType String
	 * @author Zhang Xiao Dong
	 * @Date: 2011-12-2 把result转换成为json字符�?
	 * @param rs
	 * @return
	 */
	public static String resultSetToJSON(ResultSet rs) {
		StringBuilder sb = new StringBuilder();
		sb.append("{\"result\":[");
		int t = 0;
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				// 判断是否为第�?��
				if (t > 0) {
					sb.append(",");
				}
				sb.append("{");
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					if (i > 0) {
						sb.append(",");
					}
					sb.append("\"").append(rsmd.getColumnName(i + 1)).append("\":\"").append(rs.getString(rsmd.getColumnName(i + 1))).append("\"");
				}
				sb.append("}");
				t++;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			sb.append("]}");
		}
		return sb.toString().replace(" ", "");
	}

	// 把字符串中的特殊字符转换为安全字�?
	public static String SafeJSON(String sIn) {
		if(sIn==null){
			return "";
		}
		StringBuilder sbOut = new StringBuilder(sIn.length());
		char[] ch = sIn.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			if (Character.isISOControl(ch[i]) || ch[i] == '\'') {
				String str = "000" + Integer.toHexString(ch[i]);
				sbOut.append("\\u" + str.substring(str.length() - 4, str.length()));
				continue;
			}
			else if (ch[i] == '\"' || ch[i] == '\\' || ch[i] == '/') {
				sbOut.append('\\');
			}
			sbOut.append(ch[i]);
		}
		return sbOut.toString();
	}

	public static String convertStreamToString(InputStream is, boolean isBr) throws UnsupportedEncodingException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			if (isBr) {
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
			}
			else {
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				is.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}

		return sb.toString();
	}

	public static String urlGetString(String url) {
		HttpClient client = new HttpClient();
		// 设置代理服务器地�?��端口
		// 使用GET方法，如果服务器�?��通过HTTPS连接，那只需要将下面URL中的http换成https
		HttpMethod method = new GetMethod(url);
		String reStr = "";
		// 使用POST方法
		// HttpMethod method = new PostMethod("http://java.sun.com");
		try {
			client.executeMethod(method);
			// 打印服务器返回的状�?
			// System.out.println(method.getStatusLine());
			reStr = method.getResponseBodyAsString();
		}
		catch (HttpException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		// 释放连接
		method.releaseConnection();
		return reStr;
	}
	
	/**
	 * @author wpf
	 * @function  获取当前日期
	 */
	public static java.sql.Date getDate() {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		try {
			String date = sdf.format(new Date(System.currentTimeMillis()));
			java.util.Date d = sdf.parse(date);
			java.sql.Date d1 = new java.sql.Date(d.getTime());
			return d1;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * @function 比较时间前后
	 * @param start
	 * @param end
	 * @return
	 */
	public static Map<String,String> compareDate(String start, String end) {

		DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate;
		Date endDate;
		Map<String, String> m = new HashMap<String, String>();

		try {
			startDate = dateformat.parse(start);
			endDate = dateformat.parse(end);
			if (startDate.before(endDate)) {
				m.put("start", start);
				m.put("end", end);
			} else {
				m.put("start", end);
				m.put("end", start);
			}
			return m;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public static java.sql.Date formatDate(String date) {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		try {
			java.util.Date d = sdf.parse(date);
			java.sql.Date d1 = new java.sql.Date(d.getTime());
			return d1;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * 获得格式化后的时�?
	 * 
	 * @returnType String
	 * @author Zhang Xiao Dong
	 * @Date: 2012-4-20
	 * 
	 * @return
	 */
	public static String getCurrentTime(String partten) {
		DateFormat df = new SimpleDateFormat(partten);

		return df.format(System.currentTimeMillis());
	}

	/**
	 * 获得带有毫秒的时�?
	 * 
	 * @returnType String
	 * @author Zhang Xiao Dong
	 * @Date: 2012-6-12
	 * 
	 */
	public static String getTimeWithMillisecond() {
		return getCurrentTime("yyyy-MM-dd HH:mm:ss.SSS");
	}

	/**
	 * 获得日期
	 * 
	 * @returnType String
	 * @author Zhang Xiao Dong
	 * @Date: 2012-4-20
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		return getCurrentTime("yyyy-MM-dd");
	}

}
