/**
 * 
 */
package com.smart.util.freemarker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * @Project: jietu
 * @author Zhang Xiao Dong
 * @Date: 2012-7-2
 * 
 */
public class GetDateDifference implements TemplateMethodModel {

	@SuppressWarnings("rawtypes")
	@Override
	public Object exec(List arg0) throws TemplateModelException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int size = arg0.size();
		if (size > 0) {
			Date dt1;
			Date dt2 = null;
			long l;
			try {
				dt1 = formatter.parse((String) arg0.get(0));
				if (size == 1) {
					dt2 = new Date();
				}
				else if (size >= 2) {
					dt2 = formatter.parse((String) arg0.get(1));
				}
				l = (dt2.getTime() - dt1.getTime()) / 1000;
				if (l < 60) {
					return (int) l + "秒";
				}
				else if (l >= 60 && l < 3600) {
					return (int) l / 60 + "分钟";
				}
				else if (l >= 3600 && l < 86400) {
					return (int) l / 3600 + "小时";
				}
				else {
					return (int) l / 86400 + "天";
				}
			}
			catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
