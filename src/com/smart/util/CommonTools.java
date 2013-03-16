package com.smart.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import com.smart.util.freemarker.GetDateDifference;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class CommonTools {

	public static Logger loger = Logger.getLogger(CommonTools.class);
	public static GetDateDifference getDateDifference = new GetDateDifference();

	public static String getMD5String(String str) {
		if (str == null) {
			return "";
		} else {
			String value = null;
			MessageDigest md5 = null;
			try {
				md5 = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException ex) {
				loger.error("无法获取MD5加密实例");
			}
			sun.misc.BASE64Encoder baseEncoder = new sun.misc.BASE64Encoder();
			try {
				value = baseEncoder.encode(md5.digest(str.getBytes("utf-8")));
			} catch (Exception ex) {
				loger.error(ex.getStackTrace());
			}
			return value;
		}
	}

	public static String getFrmString(FreeMarkerConfigurer freemarkerConfig,
			Map<String,Object> map, String ftl) {
		if (map == null) {
			return "";
		}
		Configuration configuration = freemarkerConfig.getConfiguration();
		configuration.setEncoding(Locale.getDefault(), "utf-8");
		Template temp;
		String out = "";
		try {
			// 获得模板文件
			temp = configuration.getTemplate(ftl);
			// 为模板中的变量赋值，并获得赋值后的模板文件字符流
			out = FreeMarkerTemplateUtils.processTemplateIntoString(temp, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}
}
